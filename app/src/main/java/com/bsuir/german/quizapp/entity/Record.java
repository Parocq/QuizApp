package com.bsuir.german.quizapp.entity;

public class Record {
    private int score;
    private String name;
    private String date;

    public Record(String name, int score, String date) {
        this.name = name;
        this.score = score;
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

