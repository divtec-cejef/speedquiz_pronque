package com.pronque.speedquiz;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GameActivity extends AppCompatActivity {
    private TextView TV_name_player1;
    private TextView TV_name_player2;
    private TextView TV_question_player1;
    private TextView TV_question_player2;
    private TextView TV_score_player1;
    private TextView TV_score_player2;
    private Button BT_player1;
    private Button BT_player2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TV_name_player1 = findViewById(R.id.name_player1);
        TV_name_player2 = findViewById(R.id.name_player2);
        TV_question_player1 = findViewById(R.id.question_player_1);
        TV_question_player2 = findViewById(R.id.question_player_2);
        TV_score_player1 = findViewById(R.id.score_player1);
        TV_score_player2 = findViewById(R.id.score_player2);
        BT_player1 = findViewById(R.id.bt_player1);
        BT_player2 = findViewById(R.id.bt_player2);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Permet de récupérer les extras de l'intent
        Bundle extras = getIntent().getExtras();

        // Récupère le nom des joueurs
        String namePlayer1 = extras.getString("namePlayer1");
        String namePlayer2 = extras.getString("namePlayer2");
        ArrayList<String> listQuestions = extras.getStringArrayList("listQuestions");
        float lengthQuestions = extras.getFloat("lengthQuestions");

        addQuestion(listQuestions, "Internet a été crée en 1969", false);
        addQuestion(listQuestions, "Il y a 7 bits dans 1 octet", false);
        addQuestion(listQuestions, "Sidney est la capitale de l'Australie", false);
        addQuestion(listQuestions, "Istanbul n'est pas la capitale de la Turquie", true);

        // Défini le texte pour les TextViews
        TV_name_player1.setText(namePlayer1);
        TV_name_player2.setText(namePlayer2);

        indexQuestionPlayer1(listQuestions);
        indexQuestionPlayer2(listQuestions);

        BT_player1.setOnClickListener(v -> {
            BT_player2.setEnabled(false);
            int score_player1 = 0;
            if (answerQuestionPlayer1(listQuestions)) {
                score_player1++;
            }
            TV_score_player1.setText(score_player1);
        });

        BT_player2.setOnClickListener(v -> {
            BT_player1.setEnabled(false);
            int score_player2 = 0;
            if (answerQuestionPlayer2(listQuestions)) {
                score_player2++;
            }
            TV_score_player2.setText(score_player2);
        });
    }

    public int indexQuestionPlayer1(ArrayList<String> listQuestions) {
        return ThreadLocalRandom.current().nextInt(0, listQuestions.size() - 1);
    }

    public int indexQuestionPlayer2(ArrayList<String> listQuestions) {
        return ThreadLocalRandom.current().nextInt(0, listQuestions.size() - 1);
    }

    public boolean addQuestion(ArrayList<String> listQuestions, String question, boolean answer) {
        listQuestions.add(question);
        return answer;
    }

    public boolean answerQuestionPlayer1(ArrayList<String> listQuestions) {
        String question = listQuestions.get(indexQuestionPlayer1(listQuestions));
        // return ...
    }

    public boolean answerQuestionPlayer2(ArrayList<String> listQuestions) {
        String question = listQuestions.get(indexQuestionPlayer2(listQuestions));
        // return ...
    }

}
