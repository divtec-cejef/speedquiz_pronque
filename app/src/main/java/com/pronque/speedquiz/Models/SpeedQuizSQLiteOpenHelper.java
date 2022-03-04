package com.pronque.speedquiz.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Classe qui représente un SQLite Open Helper
 */
public class SpeedQuizSQLiteOpenHelper extends SQLiteOpenHelper {

    static String DB_NAME = "SpeedQuiz.db";
    static int DB_VERSION = 1;

    /**
     * Construit un SQLite Open Helper
     *
     * @param context le contexte de l'application
     */
    public SpeedQuizSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateDatabaseQuiz = "CREATE TABLE quiz(idQuiz INTEGER PRIMARY KEY AUTOINCREMENT, question TEXT, reponse INTEGER);";
        db.execSQL(sqlCreateDatabaseQuiz);
        db.execSQL("INSERT INTO quiz VALUES(null,\"Internet a été crée en 1969\",0)");
        db.execSQL("INSERT INTO quiz VALUES(null,\"Sidney est la capitale de l'Australie\",0)");
        db.execSQL("INSERT INTO quiz VALUES(null,\"Istanbul est la capitale de la Turquie\",0)");
        db.execSQL("INSERT INTO quiz VALUES(null,\"Le foot est le sport le plus regardé dans le monde\",1)");
        db.execSQL("INSERT INTO quiz VALUES(null,\"Cristiano Ronaldo a gagné 5 ballons d'Or\",1)");
        db.execSQL("INSERT INTO quiz VALUES(null,\"Lionel Messi est le meilleur joueur de l'Histoire\",1)");
        db.execSQL("INSERT INTO quiz VALUES(null,\"Minecraft est le jeu le plus vendu au monde\",1)");
        db.execSQL("INSERT INTO quiz VALUES(null,\"Neil Armstrong a posé le pied sur la Lune en 1969\",1)");
        db.execSQL("INSERT INTO quiz VALUES(null,\"La 2ème guerre mondiale à durée de 1939 à 1944\",0)");
        db.execSQL("INSERT INTO quiz VALUES(null,\"Le canton du Jura est entré dans la Confédération en 1978\",0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "quiz");
        onCreate(db);
    }
}
