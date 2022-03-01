package com.pronque.speedquiz.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SpeedQuizSQLiteOpenHelper extends SQLiteOpenHelper {

    static String DB_NAME = "SpeedQuiz.db";
    static int DB_VERSION = 1;

    public SpeedQuizSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlCreateDatabaseQuiz = "CREATE TABLE quiz(idQuiz INTEGER PRIMARY KEY, question TEXT, reponse INTEGER);";
        db.execSQL(sqlCreateDatabaseQuiz);
        db.execSQL("INSERT INTO quiz VALUES(1,\"Internet a été crée en 1969\",0)");
        db.execSQL("INSERT INTO quiz VALUES(2,\"Sidney est la capitale de l'Australie\",0)");
        db.execSQL("INSERT INTO quiz VALUES(3,\"Istanbul est la capitale de la Turquie\",0)");
        db.execSQL("INSERT INTO quiz VALUES(4,\"Le foot est le sport le plus regardé dans le monde\",1)");
        db.execSQL("INSERT INTO quiz VALUES(5,\"Cristiano Ronaldo a gagné 5 ballons d'Or\",1)");
        db.execSQL("INSERT INTO quiz VALUES(6,\"Lionel Messi est le meilleur joueur de l'Histoire\",1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
