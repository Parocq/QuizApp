package com.bsuir.german.quizapp.fragment;

import android.os.Bundle;

//import android.app.Fragment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bsuir.german.quizapp.R;

public class QuestionFragment extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question_fragment, null);

//        Button button1 = v.findViewById(R.id.answer1);
//        Button button2 = v.findViewById(R.id.answer2);
//        Button button3 = v.findViewById(R.id.answer3);
//        Button button4 = v.findViewById(R.id.answer4);
//
//        View.OnClickListener clickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()) {
//                    case R.id.answer1:
//                        ((Button) getActivity().findViewById(R.id.answer1)).setText("SSSSSSS");
//                        break;
//                    case R.id.answer2:
//                        ((Button) getActivity().findViewById(R.id.answer2)).setText("qqqqq");
//                        break;
//                    case R.id.answer3:
//                        ((Button) getActivity().findViewById(R.id.answer3)).setText("tgregergerg");
//                        break;
//                    case R.id.answer4:
//                        ((Button) getActivity().findViewById(R.id.answer4)).setText("cccc");
//                        break;
//
//                }
//            }
//        };
//        button1.setOnClickListener(clickListener);
//        button2.setOnClickListener(clickListener);
//        button3.setOnClickListener(clickListener);
//        button4.setOnClickListener(clickListener);

        return v;
    }
}
