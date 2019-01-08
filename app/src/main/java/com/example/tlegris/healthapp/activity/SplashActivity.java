package com.example.tlegris.healthapp.activity;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionInflater;

import com.example.tlegris.healthapp.R;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private Timer timer;

    private static final long SPLASH_DURATION = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
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
        Intent intent = new Intent(this, HomeActivity.class);
        Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(this, android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
        startActivity(intent, bundle);    }
}
