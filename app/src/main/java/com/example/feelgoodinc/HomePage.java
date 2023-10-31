package com.example.feelgoodinc;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.feelgoodinc.fragments.HomeFragment;
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

        /*Button button = findViewById(R.id.button);

        button.setOnClickListener(view -> {
            Intent myIntent = new Intent(HomePage.this, TutorialActivity.class);
//            myIntent.putExtra("key", value); //Optional parameters
            HomePage.this.startActivity(myIntent);
        });*/
    }
    //ActivitiesFragment activityFragment = new ActivitiesFragment();
    HomeFragment homeFragment = new HomeFragment();
    //MoodFragment moodFragment = new MoodFragment();
    //ResourcesFragment resourcesFragment = new ResourcesFragment();

    @Override
    public boolean
    onNavigationItemSelected(@NonNull MenuItem item) {
        //if(item.getItemId() == R.id.activityButton){
                /*getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, firstFragment)
                        .commit();*/
        //return true;
    //}
        if(item.getItemId() == R.id.homeButton){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, homeFragment)
                    .commit();
        }
                /**/
                //return true;

            //case R.id.resourcesButton:
                /*getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, thirdFragment)
                        .commit();*/
                //return true;
            /*if(item.getItemId() == R.id.account) {

                return true;
            }*/
    return true;
    }
}