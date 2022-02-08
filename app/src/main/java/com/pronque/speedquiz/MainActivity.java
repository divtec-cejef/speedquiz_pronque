package com.pronque.speedquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private Button BT_add_player;
    private Button BT_start_new_game;
    private LinearLayout LL_player;
    private FrameLayout FL_settings;
    private FrameLayout FL_questions;
    private EditText ET_name_player1;
    private EditText ET_name_player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar main_toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(main_toolbar);

        BT_add_player = findViewById(R.id.bt_add_player);
        BT_start_new_game = findViewById(R.id.bt_start_new_game);
        LL_player = findViewById(R.id.linearlayout_player);
        FL_settings = findViewById(R.id.framelayout_settings);
        FL_questions = findViewById(R.id.framelayout_questions);
        ET_name_player1 = findViewById(R.id.editext_name_player1);
        ET_name_player2 = findViewById(R.id.editext_name_player2);
    }

    @Override
    protected void onStart() {
        super.onStart();

        BT_start_new_game.setEnabled(false);

        ET_name_player1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                BT_start_new_game.setEnabled(!ET_name_player1.getText().toString().equals("") && !ET_name_player2.getText().toString().equals(""));
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        ET_name_player2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                BT_start_new_game.setEnabled(!ET_name_player1.getText().toString().equals("") && !ET_name_player2.getText().toString().equals(""));
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        BT_add_player.setOnClickListener(v -> {
            BT_add_player.setVisibility(View.INVISIBLE);
            LL_player.setVisibility(View.VISIBLE);
        });

        BT_start_new_game.setOnClickListener(v -> {
            // Récupère le nom des joueurs
            String namePlayer1 = ET_name_player1.getText().toString();
            String namePlayer2 = ET_name_player2.getText().toString();

            // Permet d'appeller une activité
            Intent i = new Intent(getApplicationContext(), GameActivity.class);
            i.putExtra("namePlayer1", namePlayer1);
            i.putExtra("namePlayer2", namePlayer2);
            startActivity(i);
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                LL_player.setVisibility(View.GONE);
                FL_settings.setVisibility(View.VISIBLE);
                FL_questions.setVisibility(View.GONE);
                BT_add_player.setVisibility(View.GONE);
                break;
            case R.id.action_question:
                LL_player.setVisibility(View.GONE);
                FL_settings.setVisibility(View.GONE);
                FL_questions.setVisibility(View.VISIBLE);
                BT_add_player.setVisibility(View.GONE);
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}