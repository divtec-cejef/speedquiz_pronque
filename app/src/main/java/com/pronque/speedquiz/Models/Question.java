package com.pronque.speedquiz.Models;

public class Question {
    private String question;
    private int answer;

    public Question() {};

    public Question(String question, int answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public int getAnswer() {
        return answer;
    }
}
