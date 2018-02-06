package com.example.lesson_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String INDEX = "CURRENT_INDEX";
    private static final String IS_SHOW = "IS_SHOW";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestion;
    private Question[] questions;
    private int mCurrentIndex;
    private boolean isShow;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(INDEX, mCurrentIndex);
        outState.putBoolean(IS_SHOW, isShow);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (intent == null) return;
        isShow = intent.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWED, false);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(INDEX);
            isShow = savedInstanceState.getBoolean(IS_SHOW, false);
        }
        else {
            mCurrentIndex = 0;
            isShow = false;
        }

        mTrueButton = (Button)findViewById(R.id.true_button);
        mFalseButton = (Button)findViewById(R.id.false_button);
        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        mQuestion = (TextView)findViewById(R.id.question_text);

        questions = new Question[] {
                new Question(R.string.question_africa, true),
                new Question(R.string.question_americas, true),
                new Question(R.string.question_asia, true),
                new Question(R.string.question_mideast, false),
                new Question(R.string.question_oceans, false)
        };

        updateQuestion();

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CheatActivity.class);
                i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, questions[mCurrentIndex].isAnswer());
                startActivityForResult(i, 0);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % questions.length;
                isShow = false;
                updateQuestion();
            }
        });

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (questions.length + mCurrentIndex - 1) % questions.length;
                isShow = false;
                updateQuestion();
            }
        });

        mQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestion();
            }
        });
    }

    private void updateQuestion() {
        int question = questions[mCurrentIndex].getQuestion();
        mQuestion.setText(question);
    }

    private void checkAnswer(boolean userAnswer) {
        int result;
        if (isShow) {
            result = R.string.warning_toast;
        }
        else {
            if (questions[mCurrentIndex].isAnswer() == userAnswer) {
                result = R.string.correct_toast;
            } else {
                result = R.string.incorrect_toast;
            }
        }

        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
    }
}
