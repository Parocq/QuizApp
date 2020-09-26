package com.bsuir.german.quizapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bsuir.german.quizapp.R;
import com.bsuir.german.quizapp.adapter.RecordRecyclerViewAdapter;
import com.bsuir.german.quizapp.dao.DAORecord;
import com.bsuir.german.quizapp.entity.Record;

import java.util.ArrayList;
import java.util.List;

import static com.bsuir.german.quizapp.activity.MainActivity.db;


public class RecordsFragment extends Fragment {

    private DAORecord daoRecord = new DAORecord(db);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_records, container, false);
        List<Record> recordList = new ArrayList<>();
        recordList = fillRecordList(recordList);

        // Inflate the layout for this fragment
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        RecordRecyclerViewAdapter adapter = new RecordRecyclerViewAdapter(recordList, v.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        return v;
    }

    private List<Record> fillRecordList(List<Record> recordList) {
        recordList = daoRecord.selectAllRecordsOrderedByScore();

        return recordList;
    }

//    private List<Record> fillRecordList(){
//
//        return
//    }
}
