package com.pronque.speedquiz.Controllers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pronque.speedquiz.Models.Question;
import com.pronque.speedquiz.Models.SpeedQuizSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe qui représente un manager de question
 */
public class QuestionManager {
    private ArrayList<Question> questionsList;

    /**
     * Construit un manager de question
     */
    public QuestionManager(Context context) {
        questionsList = initQuestionsList(context);
    }

    /**
     * Obtient l'index de la question
     *
     * @return l'index
     */
    public int getQuestionIndex() {
        Random random = new Random();
        return random.nextInt(questionsList.size());
    }

    /**
     * Obtient une question aléatoire
     *
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
     *
     * @return le résultat du test
     */
    public boolean isLastQuestion() {
        return questionsList.isEmpty();
    }

    /*
    /**
     * Initialise la liste de questions
     * @param questionsList la liste de questions
     */
    /*
    private void initQuestionsList(ArrayList<Question> questionsList) {
        questionsList.add(new Question("Internet a été crée en 1969", 0));
        questionsList.add(new Question("Sidney est la capitale de l'Australie", 0));
        questionsList.add(new Question("Istanbul est la capitale de la Turquie", 0));
        questionsList.add(new Question("Le foot est le sport le plus regardé dans le monde", 1));
        questionsList.add(new Question("Cristiano Ronaldo a gagné 5 ballons d'Or", 1));
        questionsList.add(new Question("Lionel Messi est le meilleur joueur de l'Histoire", 1));
    }
    */

    /**
     * Charge une liste de question depuis la DB.
     *
     * @param context Le contexte de l'application pour passer la query
     * @return Une arraylist chargée de questions
     */
    private ArrayList<Question> initQuestionsList(Context context) {
        ArrayList<Question> listQuestion = new ArrayList<>();
        SpeedQuizSQLiteOpenHelper helper = new SpeedQuizSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(true, "quiz", new String[]{"idQuiz", "question", "reponse"}, null, null, null, null, "idquiz", null);

        while (cursor.moveToNext()) {
            listQuestion.add(new Question(cursor));
        }

        cursor.close();
        db.close();
        return listQuestion;
    }
}
