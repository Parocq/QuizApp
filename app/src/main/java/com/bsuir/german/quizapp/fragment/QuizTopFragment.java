package com.bsuir.german.quizapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bsuir.german.quizapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizTopFragment extends Fragment {

    private TextView questionNumber, points;
    private ImageButton goNextButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quiz_top, null);
        initializeViews(v);

        View.OnClickListener onNextButtonClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionFragment questionFragment = (QuestionFragment) getFragmentManager().findFragmentByTag("QuestionFrag");
                questionFragment.getNextQuestion();
            }
        };
        goNextButton.setOnClickListener(onNextButtonClick);

        return v;
    }

    public void setPoints (String s){
        points.setText(s);
    }

    public void setQuestionNumber (String s){
        questionNumber.setText(s);
    }

    public String getPoints (){
        return String.valueOf(points.getText());
    }

    public String getQuestionNumber (){
        return String.valueOf(questionNumber.getText());
    }

    public void initializeViews(View v) {
        goNextButton = v.findViewById(R.id.goNext);
        questionNumber = v.findViewById(R.id.questionNum);
        points = v.findViewById(R.id.points);
    }

    public void incrementQuestionNumber (){
        int number = Integer.parseInt(String.valueOf(questionNumber.getText()));
        String s = Integer.parseInt(String.valueOf(questionNumber.getText()))+1+"";
        questionNumber.setText(s);
    }
}
