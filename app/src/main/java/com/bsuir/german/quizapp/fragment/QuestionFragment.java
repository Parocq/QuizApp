package com.bsuir.german.quizapp.fragment;

import android.graphics.Color;
import android.os.Bundle;

//import android.app.Fragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bsuir.german.quizapp.R;
import com.bsuir.german.quizapp.dao.DAOQuestion;
import com.bsuir.german.quizapp.entity.Question;

import java.util.ArrayList;
import java.util.List;

import static com.bsuir.german.quizapp.activity.MainActivity.db;

public class QuestionFragment extends Fragment {

    private Button button1, button2, button3, button4;
    private TextView questionField;
    private DAOQuestion daoQuestion = new DAOQuestion(db);
    //    private static ArrayList<Integer> usedQuestion = new ArrayList<>();
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
//        usedQuestion.add(-1);
        questionsForThisTime = new ArrayList<>();
        fillQuestionsForQuiz();

        getNextQuestion();

        return v;
    }

    public void initializeViews(View v) {
        button1 = v.findViewById(R.id.answer1);
        button2 = v.findViewById(R.id.answer2);
        button3 = v.findViewById(R.id.answer3);
        button4 = v.findViewById(R.id.answer4);
        questionField = v.findViewById(R.id.question);
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
                            button4.setBackgroundColor(Color.parseColor("#558B2F"));
                        }
                        button4.setBackgroundColor(Color.parseColor("#D84315"));
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        getNextQuestion();
                        break;
                    case R.id.answer2:
                        if (question.getRightAnswerId() == 2) {
                            quizTopFragment.setPoints(
                                    String.valueOf(Integer.parseInt(quizTopFragment.getPoints()) + question.getPoints())
                            );
                            button4.setBackgroundColor(Color.parseColor("#558B2F"));
                        }
                        button4.setBackgroundColor(Color.parseColor("#D84315"));
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        getNextQuestion();
                        break;
                    case R.id.answer3:
                        if (question.getRightAnswerId() == 3) {
                            quizTopFragment.setPoints(
                                    String.valueOf(Integer.parseInt(quizTopFragment.getPoints()) + question.getPoints())
                            );
                            button4.setBackgroundColor(Color.parseColor("#558B2F"));
                        }
                        button4.setBackgroundColor(Color.parseColor("#D84315"));
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        getNextQuestion();
                        break;
                    case R.id.answer4:
                        if (question.getRightAnswerId() == 4) {
                            quizTopFragment.setPoints(
                                    String.valueOf(Integer.parseInt(quizTopFragment.getPoints()) + question.getPoints())
                            );
                            button4.setBackgroundColor(Color.parseColor("#558B2F"));
                        }
                        button4.setBackgroundColor(Color.parseColor("#D84315"));
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        getNextQuestion();
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
        if (!questionsForThisTime.isEmpty()){
//        Question newQuestion = getNewQuestion();
            setupNewQuestion(questionsForThisTime.get(0));
            setOnClickListeners(questionsForThisTime.get(0));
            questionsForThisTime.remove(0);
            quizTopFragment.incrementQuestionNumber();
        } else {
            ResultsFragment resultsFragment = new ResultsFragment();
            MenuBannerFragment banner = new MenuBannerFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("points",Integer.parseInt(quizTopFragment.getPoints()));
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

    public int generateNumber (int size){
        return (int) (Math.random() * size - 1);
    }

//    public int getRandomQuestions() {
//        int size = daoQuestion.getTableSize();
//        int num = (int) (Math.random() * size - 1);
//        if (size == usedQuestion.size()) {
//            num = -1;
//        }
//        return num;
//    }

    public void setupNewQuestion(Question question) {
        button1.setText(question.getAnswer1());
        button2.setText(question.getAnswer2());
        button3.setText(question.getAnswer3());
        button4.setText(question.getAnswer4());
        questionField.setText(question.getQuestion());
    }

//    public Question getNewQuestion() {
//        Question question;
//        List<Question> questionList = null;
//        int questionId = 0;
//
//        boolean alreadyMentioned = true;
//        while (alreadyMentioned) {
//            if (level == 0){
//                questionId = getRandomQuestionId();
//            }
//            else{
//                questionList = new ArrayList<>();
//                questionList = daoQuestion.selectAllQuestionsByLevel(level);
//                int num = (int) (Math.random() * questionList.size() - 1);
//                questionId = num;
//            }
//            if (questionId == -1) {
//                usedQuestion.clear();
//                Toast toast = Toast.makeText( getContext(),"Вопросы в базе данных кончились.\n" +
//                        " Теперь вопросы могут повторяться", Toast.LENGTH_LONG);
//                toast.show();
//            } else {
//                alreadyMentioned = false;
//                for (Integer q : usedQuestion) {
//                    if (q == questionId) {
//                        alreadyMentioned = true;
//                        break;
//                    }
//                }
//            }
//        }
//        usedQuestion.add(questionId);
//        if (level == 0){
//            question = daoQuestion.selectQuestionByPosition(questionId + 1);
//        } else {
//            question = questionList.get(questionId);
//        }
//
//        return question;
//    }


//    public int getRandomQuestionIdByLevel (){
//        List<Question> questionList = new ArrayList<>();
//        questionList = daoQuestion.selectAllQuestionsByLevel(level);
//        return questionList.size();
//    }
//
//    public int getQuestionIdByLevel() {
//        int size = daoQuestion.getTableSizeByLevel(level);
//        int num = (int) (Math.random() * size - 1);
//        if (size == usedQuestion.size()) {
//            return -1;
//        }
//        return num;
//    }

}
