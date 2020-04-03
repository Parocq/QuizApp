package com.bsuir.german.quizapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bsuir.german.quizapp.R;
import com.bsuir.german.quizapp.dao.DAORecord;
import com.bsuir.german.quizapp.entity.Record;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.bsuir.german.quizapp.activity.MainActivity.db;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultsFragment extends Fragment {

    private Button addResults;
    private EditText inputNameField;
    private TextView resultPoints, dateFinished;
    private Integer points;
    private String timeFinished;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_results, null);

        Bundle bundle = getArguments();
        if (bundle != null) {
            points = bundle.getInt("points");
        }
        initializeViews(v);
        setPoints(points);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = new Date();
        timeFinished = dateFormat.format(date);
        dateFinished.setText(timeFinished);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = String.valueOf(inputNameField.getText());

                DAORecord daoRecord = new DAORecord(db);
                daoRecord.insertRecord(db,name,points,timeFinished);
                clearBackStack();
            }
        };

        addResults.setOnClickListener(onClickListener);

        return v;
    }

    private void clearBackStack() {
        FragmentManager manager = getFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }


    public void initializeViews(View v){
        addResults = v.findViewById(R.id.addRecordButton);
        inputNameField = v.findViewById(R.id.inputNameField);
        resultPoints = v.findViewById(R.id.pointsResult);
        dateFinished = v.findViewById(R.id.dateFinished);
    }

    public void setPoints (int points){
        resultPoints.setText(String.valueOf(points));
    }
}
