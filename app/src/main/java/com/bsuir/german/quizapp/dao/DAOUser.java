package com.bsuir.german.quizapp.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bsuir.german.quizapp.entity.Question;
import com.bsuir.german.quizapp.entity.User;

import java.util.ArrayList;
import java.util.List;

public class DAOUser {

    private SQLiteDatabase db;

    public DAOUser(SQLiteDatabase db) {
        this.db = db;
    }


    public void insertUser(SQLiteDatabase db, String name,
                                   int score, String date) {
        ContentValues userValues = new ContentValues();
        userValues.put("name", name);
        userValues.put("score", score);
        userValues.put("date", date);

        db.insertOrThrow("USER", null, userValues);
    }

    public void updateUser(User user, int id) {
        db.execSQL("UPDATE QUESTION SET name = \"" + user.getName() + "\"," +
                "score = \"" + user.getScore() + "\"," + "date = \"" + user.getDate() +
                "\"where _id = \"" + id + "\";\"");
    }

    public void deleteUser (int id) {
        db.execSQL("DELETE FROM USER where _id=" + id + ";");
    }

    public List<User> selectAllUsersOrderedByScore() {
        List<User> users = new ArrayList<>();
        User user;
        Cursor cursor = db.query("USER", new String[]{"name", "score", "date"},
                null, null, null, null, "score DESC");
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                int score = cursor.getInt(1);
                String date = cursor.getString(2);

                user = new User(name,score,date);
                users.add(user);
            } while (cursor.moveToNext());
        }
        return users;
    }
}
