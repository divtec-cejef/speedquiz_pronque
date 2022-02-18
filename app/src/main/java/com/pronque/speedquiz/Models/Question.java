package com.pronque.speedquiz.Models;

/**
 * Classe qui représente une question
 */
public class Question {
    private String question;
    private int answer;

    public Question() {};

    /**
     * Construit une question
     * @param question la question
     * @param answer la réponse
     */
    public Question(String question, int answer) {
        this.question = question;
        this.answer = answer;
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
