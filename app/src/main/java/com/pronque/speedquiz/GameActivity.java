package com.pronque.speedquiz;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    private TextView TV_name_player1;
    private TextView TV_name_player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TV_name_player1 = findViewById(R.id.name_player1);
        TV_name_player2 = findViewById(R.id.name_player2);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Permet de récupérer les extras de l'intent
        Bundle extras = getIntent().getExtras();

        // Récupère le nom des joueurs
        String namePlayer1 = extras.getString("namePlayer1");
        String namePlayer2 = extras.getString("namePlayer2");

        // Défini le texte pour les TextViews
        TV_name_player1.setText(namePlayer1);
        TV_name_player2.setText(namePlayer2);
    }
}
