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

    @Override
    public void onResume(){
        super.onResume();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        selectFragment(bottomNavigationView.getSelectedItemId());
    }

    ActivityFragment activityFragment = new ActivityFragment();
    HomeFragment homeFragment = new HomeFragment();
    MoodFragment moodFragment = new MoodFragment();
    ResourcesFragment resourcesFragment = new ResourcesFragment();

    @Override
    public boolean
    onNavigationItemSelected(@NonNull MenuItem item) {
        if(bottomNavigationView.getSelectedItemId() != item.getItemId()){
            selectFragment(item.getItemId());
        }
        return true;
    }

    public void selectFragment(int id){
        if(id == R.id.homeButton){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, homeFragment)
                    .commit();
        }else if(id == R.id.activityButton){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, activityFragment)
                    .commit();
        }
        if(id == R.id.accountButton){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, new UserProfileFragment())
                    .commit();
        }
        if(id == R.id.moodButton){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, moodFragment)
                    .commit();
        }
        if(id == R.id.tutorialButton) {
            Intent intent = new Intent(HomePage.this, TutorialActivity.class);
            startActivity(intent);
        }
        if(id == R.id.resourcesButton){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, resourcesFragment)
                    .commit();
        }
    }

}