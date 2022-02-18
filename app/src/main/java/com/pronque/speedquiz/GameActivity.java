package com.pronque.speedquiz;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pronque.speedquiz.Controllers.QuestionManager;
import com.pronque.speedquiz.Models.Question;

import java.util.ArrayList;

/**
 * Classe de l'activité de jeu
 */
public class GameActivity extends AppCompatActivity {
    private TextView TV_name_player1;
    private TextView TV_name_player2;
    private TextView TV_question_player1;
    private TextView TV_question_player2;
    private TextView TV_score_player1;
    private TextView TV_score_player2;
    private Button BT_player1;
    private Button BT_player2;
    private Button BT_game_menu;
    private Button BT_game_replay;
    private FrameLayout FL_game_end;

    private QuestionManager questionManager;

    Runnable questionRunnable = null;

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
        BT_game_menu = findViewById(R.id.bt_game_menu);
        BT_game_replay = findViewById(R.id.bt_game_replay);
        FL_game_end = findViewById(R.id.framelayout_end);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Permet de récupérer les extras de l'intent
        Bundle extras = getIntent().getExtras();

        // Récupère le nom des joueurs, la durée des questions et la liste des questions
        String namePlayer1 = extras.getString("namePlayer1");
        String namePlayer2 = extras.getString("namePlayer2");
        long lengthQuestions = extras.getLong("lengthQuestions");
        ArrayList<Question> questionsList = (ArrayList<Question>) extras.get("questionsList");

        long timerIterationStartMillis = 5000;
        int scorePlayer1 = 0;
        int scorePlayer2 = 0;

        // Défini le texte pour les TextViews
        TV_name_player1.setText(namePlayer1);
        TV_name_player2.setText(namePlayer2);

        questionManager = new QuestionManager();

        Handler handler = new Handler();
        questionRunnable = new Runnable() {
            @Override
            public void run() {
                if (questionManager.isLastQuestion()) {
                    finalResult(scorePlayer1, scorePlayer2);
                    handler.removeCallbacks(this);
                    // DO_OTHER_EXIT_CODE
                    FL_game_end.setVisibility(View.VISIBLE);
                } else {
                    Question questionPlayer1 = questionManager.getRandomQuestion();
                    Question questionPlayer2 = questionManager.getRandomQuestion();

                    TV_question_player1.setText(questionPlayer1.getQuestion());
                    TV_question_player2.setText(questionPlayer2.getQuestion());

                    BT_player1.setOnClickListener(v -> {
                        BT_player2.setEnabled(false);
                        TV_score_player1.setText(scorePlayerString(scorePlayer(questionPlayer1, scorePlayer1)));
                    });

                    BT_player2.setOnClickListener(v -> {
                        BT_player1.setEnabled(false);
                        TV_score_player2.setText(scorePlayerString(scorePlayer(questionPlayer2, scorePlayer2)));
                    });

                    BT_player1.setEnabled(true);
                    BT_player2.setEnabled(true);
                    handler.postDelayed(this, lengthQuestions);
                }
            }
        };
        BT_player1.setEnabled(false);
        BT_player2.setEnabled(false);
        TV_question_player1.setText("Prêt ?");
        TV_question_player2.setText("Prêt ?");
        handler.postDelayed(questionRunnable, timerIterationStartMillis);

        BT_game_menu.setOnClickListener(v -> {
            // Permet d'appeller une activité
            Intent i = new Intent(getApplicationContext(), StartActivity.class);
            startActivity(i);
        });

        BT_game_replay.setOnClickListener(v -> {
        });
    }

    public int scorePlayer(Question question, int scorePlayer) {
        if (question.getAnswer() == 1) {
            scorePlayer++;
        }
        return scorePlayer;
    }

    public String scorePlayerString(int scorePlayer) {
        return String.valueOf(scorePlayer);
    }

    private void finalResult(int scorePlayer1, int scorePlayer2) {
        BT_player1.setEnabled(false);
        BT_player2.setEnabled(false);

        if (scorePlayer1 > scorePlayer2) {
            TV_question_player1.setText("Gagné");
            TV_question_player2.setText("Perdu");
        } else if (scorePlayer2 > scorePlayer1) {
            TV_question_player1.setText("Perdu");
            TV_question_player2.setText("Gagné");
        } else {
            TV_question_player1.setText("Égalité");
            TV_question_player2.setText("Égalité");
        }
    }
}
