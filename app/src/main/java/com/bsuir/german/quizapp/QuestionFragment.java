package com.bsuir.german.quizapp;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {

//    public QuestionFragment() {
//        // Required empty public constructor
//    }
//

//    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question_fragment, null);
//        return inflater.inflate(R.layout.fragment_question_fragment, container, false);
        Button button1 = v.findViewById(R.id.answer1);
        Button button2 = v.findViewById(R.id.answer2);
        Button button3 = v.findViewById(R.id.answer3);
        Button button4 = v.findViewById(R.id.answer4);
        return v;
    }
}
