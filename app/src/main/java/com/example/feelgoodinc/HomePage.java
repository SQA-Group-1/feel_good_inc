package com.example.feelgoodinc;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.feelgoodinc.fragments.calmingActivities.ActivityFragment;
import com.example.feelgoodinc.fragments.HomeFragment;
import com.example.feelgoodinc.fragments.ResourcesFragment;
import com.example.feelgoodinc.fragments.UserProfileFragment;
import com.example.feelgoodinc.fragments.MoodFragment;
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

    ActivityFragment activityFragment = new ActivityFragment();
    HomeFragment homeFragment = new HomeFragment();
    MoodFragment moodFragment = new MoodFragment();

    ResourcesFragment resourcesFragment = new ResourcesFragment();

    @Override
    public boolean
    onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.homeButton){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, homeFragment)
                    .commit();
            return true;
        }else if(item.getItemId() == R.id.activityButton){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, activityFragment)
                    .commit();
            return true;
        }
        if(item.getItemId() == R.id.accountButton){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, new UserProfileFragment())
                    .commit();
        }



        if(item.getItemId() == R.id.moodButton){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, moodFragment)
                    .commit();
        }


        if(item.getItemId() == R.id.tutorialButton) {
                Intent intent = new Intent(HomePage.this, TutorialActivity.class);
                startActivity(intent);
                return true;
        }
        if(item.getItemId() == R.id.resourcesButton){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, resourcesFragment)
                    .commit();
        }
    return true;
    }
}