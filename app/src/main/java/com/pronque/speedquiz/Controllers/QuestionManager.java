package com.pronque.speedquiz.Controllers;

import com.pronque.speedquiz.Models.Question;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe qui représente un manager de question
 */
public class QuestionManager {
    private ArrayList<Question> questionsList;

    /**
     * Construit un manager de question
     * @param questionsList la liste de questions
     */
    public QuestionManager(ArrayList<Question> questionsList) {
        initQuestionsList(questionsList);
    }

    /**
     * Obtient la liste de questions
     * @return la liste de questions
     */
    public ArrayList<Question> getQuestionsList() {
        return questionsList;
    }

    /**
     * Obtient l'index de la question
     * @param questionsList la liste de questions
     * @return l'index
     */
    public int getQuestionIndex(ArrayList<Question> questionsList) {
        Random random = new Random();
        return random.nextInt(questionsList.size());
    }

    /**
     * Obtient une question aléatoire
     * @param questionsList la liste de questions
     * @return la question
     */
    public Question getRandomQuestion(ArrayList<Question> questionsList) {
        int randomIndex = getQuestionIndex(questionsList);
        Question question = questionsList.get(randomIndex);
        questionsList.remove(randomIndex);
        return question;
    }

    /**
     * Teste si c'est la dernière question de la liste
     * @param questionsList la liste de questions
     * @return le résultat du test
     */
    public boolean isLastQuestion(ArrayList<Question> questionsList) {
        return questionsList.isEmpty();
    }

    /**
     * Initialise la liste de questions
     * @param questionsList la liste de questions
     */
    private void initQuestionsList(ArrayList<Question> questionsList) {
        questionsList.add(new Question("Internet a été crée en 1969", 0));
        questionsList.add(new Question("Sidney est la capitale de l'Australie", 0));
        questionsList.add(new Question("Istanbul est la capitale de la Turquie", 0));
        questionsList.add(new Question("Le foot est le sport le plus regardé dans le monde", 1));
        questionsList.add(new Question("Cristiano Ronaldo a gagné 5 ballons d'Or", 1));
        questionsList.add(new Question("Lionel Messi est le meilleur joueur de l'Histoire", 1));
    }
}
