package com.example.feelgoodinc;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.feelgoodinc.fragments.HomeFragment;
import com.example.feelgoodinc.fragments.UserProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomePage extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,homeFragment).commit();
        bottomNavigationView.setSelectedItemId(R.id.homeButton);
    }
    HomeFragment homeFragment = new HomeFragment();

    @Override
    public boolean
    onNavigationItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.homeButton){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, homeFragment)
                    .commit();
        }

        if(item.getItemId() == R.id.accountButton){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, new UserProfileFragment())
                    .commit();
        }

        return true;
    }
}