package com.pronque.speedquiz.Models;

import android.database.Cursor;

/**
 * Classe qui représente une question
 */
public class Question {
    private String question;
    private int answer;

    /**
     * Construit une question
     *
     * @param cursor tableau de la db
     */
    public Question(Cursor cursor) {
        question = cursor.getString(cursor.getColumnIndexOrThrow("question"));
        answer = cursor.getInt(cursor.getColumnIndexOrThrow("reponse"));
    }

    /**
     * Obtient la question
     *
     * @return la question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Obtient la réponse
     *
     * @return la réponse
     */
    public int getAnswer() {
        return answer;
    }
}
