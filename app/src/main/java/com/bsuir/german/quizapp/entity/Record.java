package com.bsuir.german.quizapp.entity;

import java.util.Objects;

public class Record {
    private int score;
    private String name;
    private String date;

    public Record() {
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public Record(String name, int score, String date) {
        this.name = name;
        this.score = score;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Record{" +
                "score=" + score +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        Record record = (Record) o;
        if (this.date == record.date &&
                this.name == record.name &&
                this.score == record.score
        ) {
            return true;
        } else return false;
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

