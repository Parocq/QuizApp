package com.bsuir.german.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "quizDB";
    private static final int DB_VERSION = 1;

    DBHelper (Context context){
        super(context, DB_NAME,null, DB_VERSION);
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
                + "right_answers_id INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static void insertQuestion(SQLiteDatabase db,String question,
                                        int points, String a1,String a2,
                                        String a3,String a4,int rightAnswer){
        ContentValues questionValues = new ContentValues();
        questionValues.put("question", question);
        questionValues.put("points", points);
        questionValues.put("answer1", a1);
        questionValues.put("answer2", a2);
        questionValues.put("answer3", a3);
        questionValues.put("answer4", a4);
        questionValues.put("right_answer_id", rightAnswer);
    }
}
