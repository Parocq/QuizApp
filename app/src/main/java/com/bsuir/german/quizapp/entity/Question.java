package com.bsuir.german.quizapp.entity;

public class Question {
    String question;
    int points;
    String answer1,answer2,answer3,answer4;
    int rightAnswerId;

    public Question(String question, int points, String answer1, String answer2, String answer3, String answer4, int rightAnswerId) {
        this.question = question;
        this.points = points;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.rightAnswerId = rightAnswerId;
    }
}
