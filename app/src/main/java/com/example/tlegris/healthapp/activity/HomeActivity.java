package com.example.tlegris.healthapp.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tlegris.healthapp.R;
import com.example.tlegris.healthapp.fragment.MealFragment;
import com.example.tlegris.healthapp.fragment.ProductFragment;
import com.example.tlegris.healthapp.fragment.ProfileFragment;

import java.util.Objects;

@SuppressLint("Registered")
public class HomeActivity extends AppCompatActivity {

    Fragment mProfileFragment;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupToolbar();
        BottomNavigationView bottomNavigation = (BottomNavigationView) findViewById(R.id.home_navigation_view);
        setupBottomToolbar(bottomNavigation);
        getProfileInformation();
    }

    private void getProfileInformation() {
        SharedPreferences sharedPreferences = getSharedPreferences("health-app", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", null);
        Bundle bundle = new Bundle();
        mProfileFragment = ProfileFragment.newInstance();
        if (name == null) {
            bundle.putBoolean("haveInfos", false);
            mProfileFragment.setArguments(bundle);
        } else {
            Toast.makeText(this, "Bienvenue " + name.toString(), Toast.LENGTH_SHORT).show();
            bundle.putBoolean("haveInfos", true);
            mProfileFragment.setArguments(bundle);
        }
        showFragment(mProfileFragment);

        //add the first fragment that we want _ one time
    }

    private void setupToolbar() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
    }

    @SuppressLint("ResourceType")
    private void setupBottomToolbar(BottomNavigationView bottomNavigation) {

        // set the listener to switch between fragment
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.navigation_profile:
                        fragment = ProfileFragment.newInstance();
                        break;
                    case R.id.navigation_product_info:
                        fragment = ProductFragment.newInstance();
                        break;
                    case R.id.navigation_meal_info:
                        fragment = MealFragment.newInstance();
                        break;
                }
                Bundle bundle = new Bundle();
                if (getSharedPreferences("health-app", MODE_PRIVATE).getString("name", null) == null) {
                    bundle.putBoolean("haveInfos", false);
                } else {
                    bundle.putBoolean("haveInfos", true);
                }
                fragment.setArguments(bundle);

                showFragment(fragment);
                return true;
            }
        });
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }
}
