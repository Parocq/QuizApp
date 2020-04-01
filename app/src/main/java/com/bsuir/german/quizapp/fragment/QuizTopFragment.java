package com.bsuir.german.quizapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bsuir.german.quizapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizTopFragment extends Fragment {

    QuestionFragment questionFragment = new QuestionFragment();
    Button button1,button2,button3,button4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quiz_top, null);
        questionFragment.initializeViews(v);
        Button next = v.findViewById(R.id.goNext);

        View.OnClickListener onNextButtonClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionFragment.getNextQuestion();
            }
        };
        next.setOnClickListener(onNextButtonClick);

        return v;
    }
}
