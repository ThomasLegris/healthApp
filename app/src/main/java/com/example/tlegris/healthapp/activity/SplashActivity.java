package com.example.tlegris.healthapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tlegris.healthapp.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private Timer timer;

    private static final long SPLASH_DURATION = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTimerSplash();
    }

    @Override
    protected void onStop() {
        if (timer != null) {
            timer.cancel();
        }
        super.onStop();
    }

    private void setTimerSplash() {
        timer = new Timer("myTimer");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                openNextActivity();
            }
        }, SPLASH_DURATION);
    }

    private void openNextActivity() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
