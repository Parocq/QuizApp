package com.bsuir.german.quizapp.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bsuir.german.quizapp.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class DAOQuestion {

    private SQLiteDatabase db;

    public DAOQuestion(SQLiteDatabase db) {
        this.db = db;
    }


    public void insertQuestion(Question question) {
        ContentValues questionValues = new ContentValues();
        questionValues.put("question", question.getQuestion());
        questionValues.put("points", question.getPoints());
        questionValues.put("answer1", question.getAnswer1());
        questionValues.put("answer2", question.getAnswer2());
        questionValues.put("answer3", question.getAnswer3());
        questionValues.put("answer4", question.getAnswer4());
        questionValues.put("right_answer_id", question.getRightAnswerId());

        db.insertOrThrow("QUESTION", null, questionValues);
    }

    public void updateQuestion(Question question, int id) {
        db.execSQL("UPDATE QUESTION SET question = \"" + question.getQuestion() + "\"," +
                "points = \"" + question.getPoints() + "\", answer1 =  \"" + question.getAnswer1() + "\"," +
                "answer2 = \"" + question.getAnswer2() + "\", answer3 =  \"" + question.getAnswer3() + "\"," +
                "answer4 = \"" + question.getAnswer4() + "\"," + "right_answer_id = \"" + question.getRightAnswerId() +
                "\"where _id = \"" + id + "\";\"");
    }

    public void deleteQuestion(int id) {
        db.execSQL("DELETE FROM QUESTION where _id=" + id + ";");
    }

    public List<Question> selectAllQuestions() {
        List<Question> questions = new ArrayList<>();
        Question q;
        Cursor cursor = db.query("QUESTION", new String[]{"question", "points", "answer1", "answer2",
                "answer3", "answer4", "right_answer_id"}, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String question = cursor.getString(0);
                int points = cursor.getInt(1);
                String answer1 = cursor.getString(2);
                String answer2 = cursor.getString(3);
                String answer3 = cursor.getString(4);
                String answer4 = cursor.getString(5);
                int right_answer_id = cursor.getInt(6);

                q = new Question(question, points, answer1, answer2, answer3, answer4, right_answer_id);
                questions.add(q);
            } while (cursor.moveToNext());
        }
        return questions;
    }

    public Question selectQuestionByPosition(int id) {
        Question q = null;
        Cursor cursor = db.query("QUESTION", new String[]{"question", "points", "answer1", "answer2",
                "answer3", "answer4", "right_answer_id"}, "_id = ?", new String[]{Integer.toString(id)}, null, null, null);
        if (cursor.moveToFirst()) {
            String question = cursor.getString(0);
            int points = cursor.getInt(1);
            String answer1 = cursor.getString(2);
            String answer2 = cursor.getString(3);
            String answer3 = cursor.getString(4);
            String answer4 = cursor.getString(5);
            int right_answer_id = cursor.getInt(6);

            q = new Question(question, points, answer1, answer2, answer3, answer4, right_answer_id);
        }
        return q;
    }

    public int getTableSize (){//через какое-то время вылетает.
        int size = 1;
        List<String> strings = new ArrayList<>();
        Cursor cursor = db.query("QUESTION", new String[]{"question"}, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                size++;
            } while (cursor.moveToNext());
        }
        return size;
    }
}
