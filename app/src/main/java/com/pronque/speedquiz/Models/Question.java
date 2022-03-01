package com.pronque.speedquiz.Models;

import android.database.Cursor;

import java.util.Currency;

/**
 * Classe qui représente une question
 */
public class Question {
    private String question;
    private int answer;

    /**
     * Construit une question
     * @param question la question
     * @param answer la réponse
     */
    public Question(String question, int answer) {
        this.question = question;
        this.answer = answer;
    }

    public Question(Cursor cursor) {
        question = cursor.getString(cursor.getColumnIndex("question"));
        answer = cursor.getInt(cursor.getColumnIndex("reponse"));
    }

    /**
     * Obtient la question
     * @return la question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Obtient la réponse
     * @return la réponse
     */
    public int getAnswer() {
        return answer;
    }
}
