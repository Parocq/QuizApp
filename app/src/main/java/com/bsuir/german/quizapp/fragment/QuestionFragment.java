package com.bsuir.german.quizapp.fragment;

import android.os.Bundle;

//import android.app.Fragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bsuir.german.quizapp.R;
import com.bsuir.german.quizapp.dao.DAOQuestion;
import com.bsuir.german.quizapp.entity.Question;

import java.util.ArrayList;

import static com.bsuir.german.quizapp.activity.MainActivity.db;

public class QuestionFragment extends Fragment {

    private Button button1, button2, button3, button4;
    private TextView questionField;
    private DAOQuestion daoQuestion = new DAOQuestion(db);
    private static ArrayList<Integer> usedQuestion = new ArrayList<>();
    //    private static int currentCorrectAnswer;
    private static QuizTopFragment quizTopFragment;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question_fragment, null);
        quizTopFragment = (QuizTopFragment) getFragmentManager().findFragmentByTag("QuizTopFragment");

        initializeViews(v);
        usedQuestion.add(-1);

        getNextQuestion();

        return v;
    }

    public void setOnClickListeners(final Question question) {
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.answer1:
                        if (question.getRightAnswerId() == 1) {
                            quizTopFragment.setPoints(
                                    String.valueOf(Integer.parseInt(quizTopFragment.getPoints()) + question.getPoints())
                            );
                        }
                        break;
                    case R.id.answer2:
                        if (question.getRightAnswerId() == 2) {
                            quizTopFragment.setPoints(
                                    String.valueOf(Integer.parseInt(quizTopFragment.getPoints()) + question.getPoints())
                            );
                        }
                        break;
                    case R.id.answer3:
                        if (question.getRightAnswerId() == 3) {
                            quizTopFragment.setPoints(
                                    String.valueOf(Integer.parseInt(quizTopFragment.getPoints()) + question.getPoints())
                            );
                        }
                        break;
                    case R.id.answer4:
                        if (question.getRightAnswerId() == 4) {
                            quizTopFragment.setPoints(
                                    String.valueOf(Integer.parseInt(quizTopFragment.getPoints()) + question.getPoints())
                            );
                        }
                        break;
                }
            }
        };
        button1.setOnClickListener(clickListener);
        button2.setOnClickListener(clickListener);
        button3.setOnClickListener(clickListener);
        button4.setOnClickListener(clickListener);
    }

    public void getNextQuestion() {
        Question newQuestion = getNewQuestion();
        setupNewQuestion(newQuestion);
        setOnClickListeners(newQuestion);
    }

    public void initializeViews(View v) {
        button1 = v.findViewById(R.id.answer1);
        button2 = v.findViewById(R.id.answer2);
        button3 = v.findViewById(R.id.answer3);
        button4 = v.findViewById(R.id.answer4);
        questionField = v.findViewById(R.id.question);
    }

    public Question getNewQuestion() {
        Question question;
        int questionId = 0;

        boolean alreadyMentioned = true;
        while (alreadyMentioned) {
            questionId = getRandomQuestionId();
            alreadyMentioned = false;

            for (Integer q : usedQuestion) {
                if (q == questionId) {
                    alreadyMentioned = true;
                    break;
                }
            }
        }
        usedQuestion.add(questionId);
        question = daoQuestion.selectQuestionByPosition(questionId + 1);
        return question;
    }

    public void setupNewQuestion(Question question) {
        button1.setText(question.getAnswer1());
        button2.setText(question.getAnswer2());
        button3.setText(question.getAnswer3());
        button4.setText(question.getAnswer4());
        questionField.setText(question.getQuestion() + "\n" + question.getRightAnswerId());
    }

    public int getRandomQuestionId() {
        int size = daoQuestion.getTableSize();
        int num = (int) (Math.random() * size - 1);
        return num;
    }

}
