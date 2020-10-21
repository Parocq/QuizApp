package com.bsuir.german.quizapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bsuir.german.quizapp.FirebaseInstance;
import com.bsuir.german.quizapp.R;
import com.bsuir.german.quizapp.entity.Question;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

//import android.app.Fragment;

//import static com.bsuir.german.quizapp.activity.MainActivity.db;

public class QuestionFragment extends Fragment {

    //    private Button button1, button2, button3, button4;
    private ArrayList<Button> buttonsArray;
    private LinearLayout linearLayoutTop, linearLayoutBottom;
    private ImageView imageView;
    private TextView questionField;
    //    private DAOQuestion daoQuestion = new DAOQuestion(db);
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
        generateButtons();
        getNextQuestion();
        return v;
    }

    public void initializeViews(View v) {
        linearLayoutTop = v.findViewById(R.id.linear_layout_question_fragment_top_line);
        linearLayoutBottom = v.findViewById(R.id.linear_layout_question_fragment_bottom_line);
        questionField = v.findViewById(R.id.question);
        imageView = v.findViewById(R.id.imageView);
    }

    public void generateButtons() {
        ViewGroup.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(
                        0,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        (float) (1.0)
                );

        for (int j = 1; j < 3; j++) {
            Button button = new Button(getContext());
            button.setId(100 + j);
            linearLayoutTop.addView(button, layoutParams);

            buttonsArray.add(button);
        }
        for (int j = 1; j < 3; j++) {
            Button button = new Button(getContext());
            button.setId(102 + j);
            linearLayoutBottom.addView(button, layoutParams);

            buttonsArray.add(button);
        }
    }

    public void setOnClickListeners(final Question question) {

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 101; i < 105; i++) {
                    if (v.getId() == i) {
                        if (question.getRightAnswerId() == i - 100) {
                            quizTopFragment.setPoints(
                                    String.valueOf(Integer.parseInt(quizTopFragment.getPoints()) + question.getPoints())
                            );
                            Toast.makeText(getContext(), "Верно", Toast.LENGTH_SHORT).show();
                            v.setBackgroundColor(getResources().getColor(R.color.colorEasy));
                        } else {
                            Toast.makeText(getContext(), "Не верно", Toast.LENGTH_SHORT).show();
                            v.setBackgroundColor(getResources().getColor(R.color.colorHard));
                        }

                        v.postDelayed(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        getNextQuestion();
                                    }
                                }
                                , 1000
                        );
                    }
                }
            }
        };

        for (Button b : buttonsArray) {
            b.setOnClickListener(clickListener);
        }
    }

    public void getNextQuestion() {
        for (Button button : buttonsArray) {
            button.setBackgroundColor(getResources().getColor(R.color.colorLowGray));
        }
        if (!questionsForThisTime.isEmpty()) {
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

    //тут берутся все вопросы и выбираются 10 для захода.
    public void fillQuestionsForQuiz() {
        List<Question> allQuestions = new ArrayList<>();
        if (level == 0) {
//            allQuestions = daoQuestion.selectAllQuestions();
            allQuestions = new FirebaseInstance().getQuestions();
        } else {
//            allQuestions = daoQuestion.selectAllQuestionsByLevel(level);
            //выбирает только вопросы с нужной сложностью
            allQuestions = new FirebaseInstance().getQuestions(level);
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

        String url = question.getImageName();
        Glide.with(getActivity().getApplicationContext()).load(url).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    }
}
