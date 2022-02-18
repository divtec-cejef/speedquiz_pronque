package com.pronque.speedquiz.Controllers;

import com.pronque.speedquiz.Models.Question;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe qui représente un manager de question
 */
public class QuestionManager {
    private ArrayList<Question> questionsList = new ArrayList<>();

    /**
     * Construit un manager de question
     */
    public QuestionManager() {
        initQuestionsList(questionsList);
    }

    /**
     * Obtient l'index de la question
     * @return l'index
     */
    public int getQuestionIndex() {
        Random random = new Random();
        return random.nextInt(questionsList.size());
    }

    /**
     * Obtient une question aléatoire
     * @return la question
     */
    public Question getRandomQuestion() {
        int randomIndex = getQuestionIndex();
        Question question = questionsList.get(randomIndex);
        questionsList.remove(randomIndex);
        return question;
    }

    /**
     * Teste si c'est la dernière question de la liste
     * @return le résultat du test
     */
    public boolean isLastQuestion() {
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
