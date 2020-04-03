package com.bsuir.german.quizapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.bsuir.german.quizapp.R;

public class MenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, null);

        final Button playButton = v.findViewById(R.id.play);
        final Button recordsButton = v.findViewById(R.id.records);
        final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        final RecordsFragment recordsFragment = new RecordsFragment();
        final QuestionFragment questionFragment = new QuestionFragment();
        final QuizTopFragment quizTopFragment = new QuizTopFragment();
        final Bundle bundle = new Bundle();

        ///////////////////////////////////////
        final Button button1,button2,button3,button4,button5;
        final TextView textLevels;
        textLevels = v.findViewById(R.id.textLevels);
        button1 = v.findViewById(R.id.buttonSimleLevel);
        button2 = v.findViewById(R.id.buttonMediumLevel);
        button3 = v.findViewById(R.id.buttonHardLevel);
        button4 = v.findViewById(R.id.buttonBack);
        button5 = v.findViewById(R.id.buttonRandomLevel);
        final Animation animAppears = AnimationUtils.loadAnimation(getContext(), R.anim.translate_to_screen);
        final Animation animGone = AnimationUtils.loadAnimation(getContext(), R.anim.translate);
        final Animation scaleFromZero = AnimationUtils.loadAnimation(getContext(), R.anim.scale_from_zero);
        final Animation scaleToZero = AnimationUtils.loadAnimation(getContext(), R.anim.scale_to_zero);
        ///////////////////////////////

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.play:
                        playButton.startAnimation(animGone);
                        recordsButton.startAnimation(animGone);

                        textLevels.setVisibility(View.VISIBLE);
                        textLevels.startAnimation(scaleFromZero);

                        button1.startAnimation(animAppears);
                        button2.startAnimation(animAppears);
                        button3.startAnimation(animAppears);
                        button4.startAnimation(animAppears);
                        button5.startAnimation(animAppears);
                        playButton.setClickable(false);
                        recordsButton.setClickable(false);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        playButton.setVisibility(View.INVISIBLE);
                        recordsButton.setVisibility(View.INVISIBLE);
                        button1.setClickable(true);
                        button2.setClickable(true);
                        button3.setClickable(true);
                        button4.setClickable(true);
                        button5.setClickable(true);
                        button1.setVisibility(View.VISIBLE);
                        button2.setVisibility(View.VISIBLE);
                        button3.setVisibility(View.VISIBLE);
                        button4.setVisibility(View.VISIBLE);
                        button5.setVisibility(View.VISIBLE);
                        break;
                    case R.id.records:
                        fragmentTransaction.replace(R.id.mainFragment, recordsFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        break;
                }
            }
        };

        View.OnClickListener levelsClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonBack:
                        playButton.startAnimation(animAppears);
                        recordsButton.startAnimation(animAppears);

                        textLevels.setAnimation(scaleToZero);

                        button1.startAnimation(animGone);
                        button2.startAnimation(animGone);
                        button3.startAnimation(animGone);
                        button4.startAnimation(animGone);
                        button5.startAnimation(animGone);
                        button1.setClickable(false);
                        button2.setClickable(false);
                        button3.setClickable(false);
                        button4.setClickable(false);
                        button5.setClickable(false);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        playButton.setVisibility(View.VISIBLE);
                        recordsButton.setVisibility(View.VISIBLE);
                        playButton.setClickable(true);
                        recordsButton.setClickable(true);
                        button1.setVisibility(View.INVISIBLE);
                        button2.setVisibility(View.INVISIBLE);
                        button3.setVisibility(View.INVISIBLE);
                        button4.setVisibility(View.INVISIBLE);
                        button5.setVisibility(View.INVISIBLE);
                        textLevels.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.buttonSimleLevel:
                        bundle.putInt("level",5);
                        questionFragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.topFragment, quizTopFragment, "QuizTopFragment");
                        fragmentTransaction.replace(R.id.mainFragment, questionFragment, "QuestionFrag");
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        break;
                    case R.id.buttonMediumLevel:
                        bundle.putInt("level",10);
                        questionFragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.topFragment, quizTopFragment, "QuizTopFragment");
                        fragmentTransaction.replace(R.id.mainFragment, questionFragment, "QuestionFrag");
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        break;
                    case R.id.buttonHardLevel:
                        bundle.putInt("level",15);
                        questionFragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.topFragment, quizTopFragment, "QuizTopFragment");
                        fragmentTransaction.replace(R.id.mainFragment, questionFragment, "QuestionFrag");
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        break;
                    case R.id.buttonRandomLevel:
                        bundle.putInt("level",0);
                        questionFragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.topFragment, quizTopFragment, "QuizTopFragment");
                        fragmentTransaction.replace(R.id.mainFragment, questionFragment, "QuestionFrag");
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        break;
                }
            }
        };

        button1.setOnClickListener(levelsClickListener);
        button2.setOnClickListener(levelsClickListener);
        button3.setOnClickListener(levelsClickListener);
        button4.setOnClickListener(levelsClickListener);
        button5.setOnClickListener(levelsClickListener);
        playButton.setOnClickListener(clickListener);
        recordsButton.setOnClickListener(clickListener);
        return v;
    }

}
