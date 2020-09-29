package com.bsuir.german.quizapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bsuir.german.quizapp.FirebaseInstance;
import com.bsuir.german.quizapp.R;
import com.bsuir.german.quizapp.entity.Record;
import com.bsuir.german.quizapp.fragment.MenuBannerFragment;
import com.bsuir.german.quizapp.fragment.MenuFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.firebase.FirebaseApp;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RewardedVideoAdListener {

    //    private SQLiteOpenHelper dbHelper;
//    public static SQLiteDatabase db;

    private static int rewardCounter = 0;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setTitle("Rewards count: "+ rewardCounter);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        if (isOnline()) {
            mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
            mRewardedVideoAd.setRewardedVideoAdListener(this);
            loadRewardedVideoAd();

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
            Toast.makeText(this, "Необходимо подключение к интернету\nПерезапустите приложение при включенном интернете", Toast.LENGTH_LONG).show();
        }

//        DBHelper dbHelper = new DBHelper(this);
//        try {
//            db = dbHelper.getWritableDatabase();
//        } catch (SQLiteException e) {
//            Toast toast = Toast.makeText(this, "Не удалось получить ссылку на базу данных", Toast.LENGTH_SHORT);
//            toast.show();
//        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.update_records_table: {
                FirebaseInstance.extractRecordsFromFirebase();
            }
            case R.id.button_menu_watch_ad: {
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                }
            }
        }

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


    //video ad
    @Override
    public void onRewardedVideoAdLoaded() {
        Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoStarted() {
        Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
        loadRewardedVideoAd();
        Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        Toast.makeText(this, "onRewarded", Toast.LENGTH_SHORT).show();
        rewardCounter++;
        setTitle("Rewards count: "+ rewardCounter);
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Toast.makeText(this, "onRewardedVideoAdLeftApplication", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        Toast.makeText(this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoCompleted() {
        Toast.makeText(this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();
    }


    //Loading ad
    private RewardedVideoAd mRewardedVideoAd;
    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                new AdRequest.Builder().build());
    }


    //Life-cycle
    @Override
    public void onResume() {
        mRewardedVideoAd.resume(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        mRewardedVideoAd.pause(this);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mRewardedVideoAd.destroy(this);
        super.onDestroy();
    }
}
