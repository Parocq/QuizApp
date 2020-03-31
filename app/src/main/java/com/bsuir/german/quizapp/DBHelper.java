package com.bsuir.german.quizapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.bsuir.german.quizapp.dao.DAOQuestion;
import com.bsuir.german.quizapp.dao.DAORecord;
import com.bsuir.german.quizapp.entity.Question;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "quizDB";
    private static final int DB_VERSION = 1;


    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE QUESTION ("
                + "_id  INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "question TEXT,"
                + "points INTEGER,"
                + "answer1 TEXT,"
                + "answer2 TEXT,"
                + "answer3 TEXT,"
                + "answer4 TEXT,"
                + "right_answer_id INTEGER);");

        db.execSQL("CREATE TABLE RECORD ("
                + "_id  INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT,"
                + "score INTEGER,"
                + "date TEXT);");


        DAOQuestion daoQuestion = new DAOQuestion(db);
        DAORecord daoRecord = new DAORecord(db);

        daoQuestion.insertQuestion(new Question("hahaha?", 10, "hehe", "hoho", "ahaha", "heh", 3));
        daoQuestion.insertQuestion(new Question("O mae wa mo...", 10, "shindeiry", "nani?", "kavai", "arigato", 1));

        daoRecord.insertRecord(db, "German", 24, "10.05.2020");
        daoRecord.insertRecord(db, "Lesha", 1, "10.05.2020");
        daoRecord.insertRecord(db, "Vladimirovich", 11, "10.05.2020");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
