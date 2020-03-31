package com.bsuir.german.quizapp.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bsuir.german.quizapp.entity.Record;

import java.util.ArrayList;
import java.util.List;

public class DAORecord {

    private SQLiteDatabase db;

    public DAORecord(SQLiteDatabase db) {
        this.db = db;
    }


    public void insertRecord(SQLiteDatabase db, String name,
                             int score, String date) {
        ContentValues userValues = new ContentValues();
        userValues.put("name", name);
        userValues.put("score", score);
        userValues.put("date", date);

        db.insertOrThrow("RECORD", null, userValues);
    }

    public void updateRecord(Record record, int id) {
        db.execSQL("UPDATE QUESTION SET name = \"" + record.getName() + "\"," +
                "score = \"" + record.getScore() + "\"," + "date = \"" + record.getDate() +
                "\"where _id = \"" + id + "\";\"");
    }

    public void deleteRecord(int id) {
        db.execSQL("DELETE FROM USER where _id=" + id + ";");
    }

    public List<Record> selectAllRecordsOrderedByScore() {
        List<Record> records = new ArrayList<>();
        Record record;
        Cursor cursor = db.query("RECORD", new String[]{"name", "score", "date"},
                null, null, null, null, "score DESC");
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                int score = cursor.getInt(1);
                String date = cursor.getString(2);

                record = new Record(name,score,date);
                records.add(record);
            } while (cursor.moveToNext());
        }
        return records;
    }
}
