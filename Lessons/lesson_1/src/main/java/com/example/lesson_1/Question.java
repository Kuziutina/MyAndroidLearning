package com.example.lesson_1;

/**
 * Created by Sofia on 05.02.2018.
 */

public class Question {
    private int mQuestion;
    private boolean answer;
    private boolean showed;

    public Question(int mQuestion, boolean answer) {
        this.mQuestion = mQuestion;
        this.answer = answer;
        showed = false;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int mQuestion) {
        this.mQuestion = mQuestion;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public boolean isShowed() {
        return showed;
    }

    public void setShowed(boolean showed) {
        this.showed = showed;
    }
}
