package com.bsuir.german.quizapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.bsuir.german.quizapp.DBHelper;
import com.bsuir.german.quizapp.R;
import com.bsuir.german.quizapp.fragment.MenuBannerFragment;
import com.bsuir.german.quizapp.fragment.MenuFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    //    private SQLiteOpenHelper dbHelper;
    public static SQLiteDatabase db;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

        MenuFragment menuFrag = new MenuFragment();
        MenuBannerFragment banner = new MenuBannerFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.mainFragment, menuFrag, "MenuFragment");
        fragmentTransaction.add(R.id.topFragment, banner);
        fragmentTransaction.commit();

        Bundle bundle = new Bundle();

        DBHelper dbHelper = new DBHelper(this);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Не удалось получить ссылку на базу данных", Toast.LENGTH_SHORT);
            toast.show();
        }

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
