package com.bsuir.german.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Quiz extends AppCompatActivity {

    QuestionFragment frag;
    FragmentTransaction fragmentTransaction;
    Button goNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        goNext = findViewById(R.id.goNext);
        frag = new QuestionFragment();

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.fragment,frag);
        ft.commit();

    }


    public void goNext(View view) {

    }
}
