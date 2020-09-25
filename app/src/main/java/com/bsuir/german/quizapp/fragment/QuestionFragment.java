package com.bsuir.german.quizapp.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

//import android.app.Fragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bsuir.german.quizapp.R;
import com.bsuir.german.quizapp.dao.DAOQuestion;
import com.bsuir.german.quizapp.entity.Question;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.bsuir.german.quizapp.activity.MainActivity.db;

public class QuestionFragment extends Fragment {

    //    private Button button1, button2, button3, button4;
    private ArrayList<Button> buttonsArray;
    private LinearLayout linearLayoutTop, linearLayoutBottom;
    private ImageView imageView;
    private TextView questionField;
    private DAOQuestion daoQuestion = new DAOQuestion(db);
    private QuizTopFragment quizTopFragment;
    private static int level;
    private static List<Question> questionsForThisTime;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question_fragment, null);
        quizTopFragment = (QuizTopFragment) getFragmentManager().findFragmentByTag("QuizTopFragment");
        Bundle bundle = getArguments();
        if (bundle != null) {
            level = bundle.getInt("level");
        }
        initializeViews(v);
        questionsForThisTime = new ArrayList<>();
        fillQuestionsForQuiz();

        buttonsArray = new ArrayList<>(4);
        generateButtons(0, false);
        getNextQuestion();
        return v;
    }

    public void initializeViews(View v) {
        linearLayoutTop = v.findViewById(R.id.linear_layout_question_fragment_top_line);
        linearLayoutBottom = v.findViewById(R.id.linear_layout_question_fragment_bottom_line);
        questionField = v.findViewById(R.id.question);
        imageView = v.findViewById(R.id.imageView);
    }

    public void generateButtons(int number, boolean wasRight) {
        int color;
        if (wasRight) {
            color = getResources().getColor(R.color.colorEasy);
        } else  color = getResources().getColor(R.color.colorHard);

        ViewGroup.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(
                        0,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        (float) (1.0)
                );
        int colorizedCounter = 1;
        for (int j = 1; j < 3; j++) {
            Button button = new Button(getContext());
            button.setId(100 + j);
            linearLayoutTop.addView(button, layoutParams);

            if (colorizedCounter == number){
                button.setBackgroundColor(color);
            }
            colorizedCounter++;

            buttonsArray.add(button);
        }
        for (int j = 1; j < 3; j++) {
            Button button = new Button(getContext());
            button.setId(102 + j);
            linearLayoutBottom.addView(button, layoutParams);

            if (colorizedCounter == number){
                button.setBackgroundColor(color);
            }
            colorizedCounter++;

            buttonsArray.add(button);
        }
        colorizedCounter = 1;
    }

    private void removeAllButtons() {
        linearLayoutBottom.removeAllViews();
        linearLayoutTop.removeAllViews();
    }

    public void setOnClickListeners(final Question question) {
//        for (int i = 1; i < 5; i++) {
//            if (question.getRightAnswerId() == i){
//                quizTopFragment.setPoints(
//                        String.valueOf(Integer.parseInt(quizTopFragment.getPoints()) + question.getPoints())
//                );
//                buttonsArray.get(i).setBackgroundColor(Color.parseColor("#558B2F"));
//            } else {
//                buttonsArray.get(i).setBackgroundColor(Color.parseColor("#D84315"));
//            }
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            removeAllButtons();
//            generateButtons();
//
//            getNextQuestion();
//        }
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case 101:
                        if (question.getRightAnswerId() == 1) {
                            quizTopFragment.setPoints(
                                    String.valueOf(Integer.parseInt(quizTopFragment.getPoints()) + question.getPoints())
                            );
                            removeAllButtons();
                            generateButtons(1, true);
                        } else {
                            removeAllButtons();
                            generateButtons(1, false);
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        getNextQuestion();
                        break;
                    case 102:
                        if (question.getRightAnswerId() == 2) {
                            quizTopFragment.setPoints(
                                    String.valueOf(Integer.parseInt(quizTopFragment.getPoints()) + question.getPoints())
                            );
                            removeAllButtons();
                            generateButtons(2, true);
                        } else {
                            removeAllButtons();
                            generateButtons(2, false);
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        getNextQuestion();
                        break;
                    case 103:
                        if (question.getRightAnswerId() == 3) {
                            quizTopFragment.setPoints(
                                    String.valueOf(Integer.parseInt(quizTopFragment.getPoints()) + question.getPoints())
                            );
                            removeAllButtons();
                            generateButtons(3, true);
                        } else {
                            removeAllButtons();
                            generateButtons(3, false);
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        getNextQuestion();
                        break;
                    case 104:
                        if (question.getRightAnswerId() == 4) {
                            quizTopFragment.setPoints(
                                    String.valueOf(Integer.parseInt(quizTopFragment.getPoints()) + question.getPoints())
                            );
                            removeAllButtons();
                            generateButtons(4, true);
                        } else {
                            removeAllButtons();
                            generateButtons(4, false);
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        getNextQuestion();
                        break;
                }
            }
        };

        for (Button b : buttonsArray) {
            b.setOnClickListener(clickListener);
        }
    }

    public void getNextQuestion() {
        if (!questionsForThisTime.isEmpty()) {
            removeAllButtons();
            generateButtons(0,false);

            setupNewQuestion(questionsForThisTime.get(0));
            setOnClickListeners(questionsForThisTime.get(0));
            questionsForThisTime.remove(0);
            quizTopFragment.incrementQuestionNumber();
        } else {
            ResultsFragment resultsFragment = new ResultsFragment();
            MenuBannerFragment banner = new MenuBannerFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("points", Integer.parseInt(quizTopFragment.getPoints()));
            resultsFragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.mainFragment, resultsFragment, "ResultsFragment");
            fragmentTransaction.replace(R.id.topFragment, banner);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    public void fillQuestionsForQuiz() {
        List<Question> allQuestions;
        if (level == 0) {
            allQuestions = daoQuestion.selectAllQuestions();
        } else {
            allQuestions = daoQuestion.selectAllQuestionsByLevel(level);
        }
        for (int i = 0; i < 10; i++) {
            int num = generateNumber(allQuestions.size());
            questionsForThisTime.add(allQuestions.get(num));
            allQuestions.remove(num);
        }
    }

    public int generateNumber(int size) {
        return (int) (Math.random() * size - 1);
    }

    public void setupNewQuestion(Question question) {
        buttonsArray.get(0).setText(question.getAnswer1());
        buttonsArray.get(1).setText(question.getAnswer2());
        buttonsArray.get(2).setText(question.getAnswer3());
        buttonsArray.get(3).setText(question.getAnswer4());
        questionField.setText(question.getQuestion());

        String filename = question.getImageName();
        InputStream inputStream = null;
        try {
            inputStream = getActivity().getApplicationContext().getAssets().open(filename);
            Drawable d = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(d);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
