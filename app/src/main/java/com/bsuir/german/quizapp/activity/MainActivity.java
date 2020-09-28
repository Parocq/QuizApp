package com.bsuir.german.quizapp.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bsuir.german.quizapp.DBHelper;
import com.bsuir.german.quizapp.FirebaseInstance;
import com.bsuir.german.quizapp.R;
import com.bsuir.german.quizapp.dao.DAOQuestion;
import com.bsuir.german.quizapp.entity.Question;
import com.bsuir.german.quizapp.entity.Record;
import com.bsuir.german.quizapp.fragment.MenuBannerFragment;
import com.bsuir.german.quizapp.fragment.MenuFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //    private SQLiteOpenHelper dbHelper;
    public static SQLiteDatabase db;

    private List<Record> a;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (isOnline()) {
            MenuFragment menuFrag = new MenuFragment();
            MenuBannerFragment banner = new MenuBannerFragment();

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.mainFragment, menuFrag, "MenuFragment");
            fragmentTransaction.add(R.id.topFragment, banner);
            fragmentTransaction.commit();

            FirebaseApp.initializeApp(this);
            FirebaseInstance.extractQuestionsFromFirebase();
            FirebaseInstance.extractRecordsFromFirebase();
        } else {
            Toast.makeText(this,"Необходимо подключение к интернету\nПерезапустите приложение при включенном интернете", Toast.LENGTH_LONG).show();
        }

//        DBHelper dbHelper = new DBHelper(this);
//        try {
//            db = dbHelper.getWritableDatabase();
//        } catch (SQLiteException e) {
//            Toast toast = Toast.makeText(this, "Не удалось получить ссылку на базу данных", Toast.LENGTH_SHORT);
//            toast.show();
//        }

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

//        AdView adView = findViewById(R.id.adView);
//
//        AdRequest adRequest = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                .build();
//        adView.loadAd(adRequest);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        FirebaseInstance.extractRecordsFromFirebase();
        return super.onOptionsItemSelected(item);
    }

    protected boolean isOnline() {
        String cs = Context.CONNECTIVITY_SERVICE;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(cs);
        if (cm.getActiveNetworkInfo() == null) {
            return false;
        } else {
            return true;
        }
    }
}
