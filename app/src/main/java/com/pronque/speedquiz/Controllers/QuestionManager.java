package com.pronque.speedquiz.Controllers;

import android.content.Context;

import com.pronque.speedquiz.Models.Question;

import java.util.ArrayList;
import java.util.Random;

public class QuestionManager {
    private ArrayList<Question> questionsList;

    public QuestionManager(ArrayList<Question> questionsList) {
        initQuestionsList(questionsList);
    }

    public ArrayList<Question> getQuestionsList() {
        return questionsList;
    }

    public int getQuestionIndex(ArrayList<Question> questionsList) {
        Random random = new Random();
        return random.nextInt(questionsList.size());
    }

    public Question getRandomQuestion(ArrayList<Question> questionsList) {
        int randomIndex = getQuestionIndex(questionsList);
        Question question = questionsList.get(randomIndex);
        questionsList.remove(randomIndex);
        return question;
    }

    public boolean isLastQuestion(ArrayList<Question> questionsList) {
        return questionsList.isEmpty();
    }

    private void initQuestionsList(ArrayList<Question> questionsList) {
        questionsList.add(new Question("Internet a été crée en 1969", 0));
        questionsList.add(new Question("Sidney est la capitale de l'Australie", 0));
        questionsList.add(new Question("Istanbul est la capitale de la Turquie", 0));
        questionsList.add(new Question("Le foot est le sport le plus regardé dans le monde", 1));
        questionsList.add(new Question("Cristiano Ronaldo a gagné 5 ballons d'Or", 1));
        questionsList.add(new Question("Lionel Messi est le meilleur joueur de l'Histoire", 1));
    }
}
