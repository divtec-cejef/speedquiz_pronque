package com.pronque.speedquiz;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.slider.Slider;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.pronque.speedquiz.Models.Question;
import com.pronque.speedquiz.Models.SpeedQuizSQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Classe de l'activité de départ
 */
public class StartActivity extends AppCompatActivity {
    private Button BT_add_player;
    private Button BT_start_new_game;
    private Button BT_settings_apply;
    private Button BT_settings_cancel;
    private Button BT_questions_apply;
    private Button BT_questions_cancel;
    private SwitchMaterial SW_night_mode;
    private LinearLayout LL_player;
    private FrameLayout FL_settings;
    private FrameLayout FL_questions;
    private EditText ET_name_player1;
    private EditText ET_name_player2;
    private EditText ET_add_question;
    private Slider SL_length_questions;
    private SwitchMaterial SW_answer_question;

    private ArrayList<Question> questionsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toolbar main_toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(main_toolbar);

        BT_add_player = findViewById(R.id.bt_add_player);
        BT_start_new_game = findViewById(R.id.bt_start_new_game);
        BT_settings_apply = findViewById(R.id.bt_settings_apply);
        BT_settings_cancel = findViewById(R.id.bt_settings_cancel);
        BT_questions_apply = findViewById(R.id.bt_questions_apply);
        BT_questions_cancel = findViewById(R.id.bt_questions_cancel);
        SW_night_mode = findViewById(R.id.switch_night_mode);
        SW_answer_question = findViewById(R.id.switch_answer_question);
        LL_player = findViewById(R.id.linearlayout_player);
        FL_settings = findViewById(R.id.framelayout_settings);
        FL_questions = findViewById(R.id.framelayout_questions);
        ET_name_player1 = findViewById(R.id.editext_name_player1);
        ET_name_player2 = findViewById(R.id.editext_name_player2);
        ET_add_question = findViewById(R.id.editext_new_question);
        SL_length_questions = findViewById(R.id.slider_length_questions);
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
            // Insertion d'une question dans la db
            SpeedQuizSQLiteOpenHelper helper = new SpeedQuizSQLiteOpenHelper(getApplicationContext());
            SQLiteDatabase db = helper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("question", ET_add_question.getText().toString());
            values.put("reponse", convertAnswerQuestionSwitchValues());
            long rowId = db.insert("quiz", null, values);

            // Tester si l'insertion est ok
            if (rowId == -1) {
                Toast.makeText(this, "Erreur lors de l'ajout de la question", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Votre question a bien été ajouté", Toast.LENGTH_SHORT).show();
            }

            db.close();

            BT_add_player.setVisibility(View.VISIBLE);
            LL_player.setVisibility(View.GONE);
            FL_settings.setVisibility(View.GONE);
            FL_questions.setVisibility(View.GONE);
        });

        BT_start_new_game.setOnClickListener(v -> {
            // Récupère le nom des joueurs
            String namePlayer1 = ET_name_player1.getText().toString();
            String namePlayer2 = ET_name_player2.getText().toString();
            long lengthQuestions = convertLengthQuestionsSliderValues();

            // Permet d'appeller une activité
            Intent i = new Intent(getApplicationContext(), GameActivity.class);
            i.putExtra("namePlayer1", namePlayer1);
            i.putExtra("namePlayer2", namePlayer2);
            i.putExtra("lengthQuestions", lengthQuestions);
            i.putExtra("questionsList", questionsList);
            startActivity(i);
        });

        SW_night_mode.setOnCheckedChangeListener((compoundButton, b) -> {
            if (SW_night_mode.isChecked()) {
                Toast.makeText(StartActivity.this, "Mode sombre activé", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(StartActivity.this, "Mode clair activé", Toast.LENGTH_SHORT).show();
            }
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

    /**
     * Converti les valeurs du slider des questions en millisecondes
     *
     * @return la valeur du slider en millisecondes
     */
    private long convertLengthQuestionsSliderValues() {
        float sliderValue = SL_length_questions.getValue();
        return (long) (sliderValue * 1000.0);
    }

    /**
     * Converti les valeurs du switch de la réponse à la question en int
     *
     * @return le réponse en int
     */
    public int convertAnswerQuestionSwitchValues() {
        boolean switchValue = SW_answer_question.isChecked();
        if (switchValue) {
            return 1;
        } else {
            return 0;
        }
    }
}