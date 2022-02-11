package com.pronque.speedquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
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
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button BT_add_player;
    private Button BT_start_new_game;
    private Button BT_settings_apply;
    private Button BT_settings_cancel;
    private Button BT_questions_apply;
    private Button BT_questions_cancel;
    // private  SWITCH_night_mode;
    private LinearLayout LL_player;
    private FrameLayout FL_settings;
    private FrameLayout FL_questions;
    private EditText ET_name_player1;
    private EditText ET_name_player2;
    private EditText ET_add_question;

    private ArrayList<String> listeQuestions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar main_toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(main_toolbar);

        BT_add_player = findViewById(R.id.bt_add_player);
        BT_start_new_game = findViewById(R.id.bt_start_new_game);
        BT_settings_apply = findViewById(R.id.bt_settings_apply);
        BT_settings_cancel = findViewById(R.id.bt_settings_cancel);
        BT_questions_apply = findViewById(R.id.bt_questions_apply);
        BT_questions_cancel = findViewById(R.id.bt_questions_cancel);
        // SWITCH_night_mode = findViewById(R.id.switch_night_mode);
        LL_player = findViewById(R.id.linearlayout_player);
        FL_settings = findViewById(R.id.framelayout_settings);
        FL_questions = findViewById(R.id.framelayout_questions);
        ET_name_player1 = findViewById(R.id.editext_name_player1);
        ET_name_player2 = findViewById(R.id.editext_name_player2);
        ET_add_question = findViewById(R.id.editext_new_question);
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

        /*
        SWITCH_night_mode.setOnSwitchListener(new NightModeButton.OnSwitchListener() {
            @Override
            public void onSwitchListener(boolean isNight) {
                if(isNight){
                    animateBackground(colorFrom,colorTo);
                    animateStatusActionBar(getResources().getColor(R.color.colorPrimary),colorTo);
                    Toast.makeText(getApplicationContext(),"Night Mode On",Toast.LENGTH_SHORT).show();
                } else {
                    animateBackground(colorTo,colorFrom);
                    animateStatusActionBar(colorTo,getResources().getColor(R.color.colorPrimary));
                    Toast.makeText(getApplicationContext(),"Night Mode Off",Toast.LENGTH_SHORT).show();
                }
            }
        });

         */

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
            i.putExtra("listQuestions", listeQuestions);
            i.putExtra("namePlayer1", namePlayer1);
            i.putExtra("namePlayer2", namePlayer2);
            startActivity(i);
        });

        BT_settings_cancel.setOnClickListener(v -> {
            LL_player.setVisibility(View.GONE);
            FL_settings.setVisibility(View.GONE);
            FL_questions.setVisibility(View.GONE);
            BT_add_player.setVisibility(View.VISIBLE);
        });

        BT_questions_cancel.setOnClickListener(v -> {
            LL_player.setVisibility(View.GONE);
            FL_settings.setVisibility(View.GONE);
            FL_questions.setVisibility(View.GONE);
            BT_add_player.setVisibility(View.VISIBLE);
        });

        BT_settings_apply.setOnClickListener(v -> {
            Toast.makeText(this, "Vos paramètres ont bien été sauvegardés", Toast.LENGTH_SHORT).show();
            BT_add_player.setVisibility(View.VISIBLE);
            LL_player.setVisibility(View.GONE);
            FL_settings.setVisibility(View.GONE);
            FL_questions.setVisibility(View.GONE);
        });

        BT_questions_apply.setOnClickListener(v -> {
            Toast.makeText(this, ET_add_question.getText().toString(), Toast.LENGTH_SHORT).show();
            listeQuestions.add(ET_add_question.getText().toString());
            Toast.makeText(this, "Votre question a bien été ajouté", Toast.LENGTH_SHORT).show();
            BT_add_player.setVisibility(View.VISIBLE);
            LL_player.setVisibility(View.GONE);
            FL_settings.setVisibility(View.GONE);
            FL_questions.setVisibility(View.GONE);
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    @SuppressLint("NonConstantResourceId")
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