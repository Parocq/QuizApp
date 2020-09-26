package com.bsuir.german.quizapp;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bsuir.german.quizapp.entity.Question;
import com.bsuir.german.quizapp.entity.Record;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FirebaseInstance {

    public static FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    public static DatabaseReference firebaseQuestionsReference = firebaseDatabase.getReference("questions");
    public static DatabaseReference firebaseRecordsReference = firebaseDatabase.getReference("records");
    private static List<Question> allQuestions = new ArrayList<>();
    private static List<Record> allRecords = new ArrayList<>();


    public static void extractQuestionsFromFirebase() {
        allQuestions.clear();
        firebaseQuestionsReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Question question = snapshot.getValue(Question.class);
                allQuestions.add(question);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public List<Question> getQuestions() {
        return allQuestions;
    }

    public List<Question> getQuestions(int lvl) {
        List<Question> list = new ArrayList<>();
        for (Question q : allQuestions) {
            if (q.getPoints() == lvl) {
                list.add(q);
            }
        }
        return list;
    }

    public static void extractRecordsFromFirebase() {
        allRecords.clear();
        firebaseRecordsReference.orderByChild("score").limitToFirst(50).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Record record = snapshot.getValue(Record.class);
                Log.e("TAG", "onChildAdded: received object record");
                allRecords.add(record);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public List<Record> getRecords(){
        return allRecords;
    }

    /*
            DatabaseReference reference = FirebaseInstance.firebaseRecordsReference.child("/record_2");
        Record record = new Record("Test2",0,"10-20-3000");
        reference.setValue(record);
     */

    //возвращает данные каждый раз как происходит обновление в firebase
    /*myRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            Question question = snapshot.getValue(Question.class);
            System.out.println(question);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            System.out.println("The read failed: " + error.getCode());
        }
    });*/

    //Доставать картинку и парсить
    /*ImageView imageView = findViewById(R.id.imageView);
    String url="https://firebasestorage.googleapis.com/v0/b/quiz-app-aecd6.appspot.com/o/bg.png?alt=media&token=b5a254a5-befc-4407-8bd4-04aa5e33db92";

    Glide.with(getApplicationContext()).load(url).into(imageView);*/

    //end
}
