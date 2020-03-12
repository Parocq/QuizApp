package com.bsuir.german.quizapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bsuir.german.quizapp.R;

public class MenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, null);

        Button playButton = v.findViewById(R.id.play);
        Button recordsButton = v.findViewById(R.id.records);

        final RecordsFragment recordsFragment = new RecordsFragment();

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.play:
//                        Intent intent = new Intent(getActivity(), Quiz.class);
//                        startActivity(intent);
                        break;
                    case R.id.records:
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.topFragment, recordsFragment);
                        fragmentTransaction.commit();
                        break;
                }
            }
        };
        playButton.setOnClickListener(clickListener);
        recordsButton.setOnClickListener(clickListener);

        return v;
    }
}
