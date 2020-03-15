package com.bsuir.german.quizapp.entity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class User {

    private String name;
    private int score;
    private String date;

    public User(String name, int score, String date) {
        this.name = name;
        this.score = score;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }
}
