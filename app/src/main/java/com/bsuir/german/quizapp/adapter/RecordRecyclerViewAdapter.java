package com.bsuir.german.quizapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bsuir.german.quizapp.R;
import com.bsuir.german.quizapp.entity.Record;

import java.util.ArrayList;
import java.util.List;

public class RecordRecyclerViewAdapter extends RecyclerView.Adapter<RecordRecyclerViewAdapter.ViewHolder> {

    List<Record> recordList = new ArrayList<>();
    Context context;

    public RecordRecyclerViewAdapter(List<Record> recordList, Context context) {
        this.recordList = recordList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.records_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.number.setText(""+position+1);
        holder.name.setText(recordList.get(position).getName());
        holder.date.setText(recordList.get(position).getDate());
        holder.score.setText(""+recordList.get(position).getScore());
//        holder.layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView number;
        TextView name;
        TextView date;
        TextView score;
        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.textNumber);
            name = itemView.findViewById(R.id.textName);
            date = itemView.findViewById(R.id.textDate);
            score = itemView.findViewById(R.id.textScore);
            layout = itemView.findViewById(R.id.list_item);
        }
    }
}
