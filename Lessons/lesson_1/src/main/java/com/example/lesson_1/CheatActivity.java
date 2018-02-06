package com.example.lesson_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER_IS_TRUE = "ANSWER_IS_TRUE";
    public static final String EXTRA_ANSWER_SHOWED = "ANSWER_SHOWED";
    private static final String IS_SHOW = "IS_SHOW";
    private static final String ANSWER = "ANSWER";
    private boolean mAnswer;
    private boolean isShow;
    private Button mShowButton;
    private TextView mAnswerText;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(IS_SHOW, isShow);
        outState.putBoolean(ANSWER, mAnswer);;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cheat_activity);

        mAnswerText = (TextView)findViewById(R.id.answer_text);
        mShowButton = (Button)findViewById(R.id.show_answer_button);


        if (savedInstanceState != null) {
            isShow = savedInstanceState.getBoolean(IS_SHOW, false);
            mAnswer = savedInstanceState.getBoolean(ANSWER);
            if (isShow) {
                printResult();
            }
        }
        else {
            isShow = getIntent().getBooleanExtra(EXTRA_ANSWER_SHOWED,false);
            mAnswer = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, true);
        }

        setAnswerShowResult(isShow);
        mShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShow = true;
                printResult();
                setAnswerShowResult(isShow);
            }
        });

    }

    private void printResult() {
        if (mAnswer) {
            mAnswerText.setText(R.string.true_button);
        }
        else {
            mAnswerText.setText(R.string.false_button);
        }
    }

    private void setAnswerShowResult(boolean isAnswerShow) {
        Intent i = new Intent();
        i.putExtra(EXTRA_ANSWER_SHOWED, isAnswerShow);
        setResult(RESULT_OK, i);
    }
}
