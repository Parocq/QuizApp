package com.bsuir.german.quizapp.entity;

public class Question {
    private String question;
    private int points;
    private String answer1,answer2,answer3,answer4;
    private int rightAnswerId;

    public Question(String question, int points, String answer1, String answer2, String answer3, String answer4, int rightAnswerId) {
        this.question = question;
        this.points = points;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.rightAnswerId = rightAnswerId;
    }

    public String getQuestion() {
        return question;
    }

    public int getPoints() {
        return points;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public int getRightAnswerId() {
        return rightAnswerId;
    }
}
