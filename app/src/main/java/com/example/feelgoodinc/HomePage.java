package com.example.feelgoodinc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.feelgoodinc.database.JournalDatabaseHelper;
import com.example.feelgoodinc.database.MoodDatabaseHelper;
import com.example.feelgoodinc.fragments.HomeFragment;
import com.example.feelgoodinc.models.Journal;
import com.example.feelgoodinc.models.Mood;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;

import android.content.Intent;

public class HomePage extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        MoodDatabaseHelper moodHelper = new MoodDatabaseHelper();
//        moodHelper.addNewMood(new Mood(Mood.MoodType.MEH, Date.from(Instant.now())), HomePage.this);
        moodHelper.getMoodsForMonth(Date.from(Instant.now()));

//        JournalDatabaseHelper journalDatabaseHelper = new JournalDatabaseHelper();
////        journalDatabaseHelper.addNewJournal(new Journal("name", new Date(0), Date.from(Instant.now()), "content"), HomePage.this);
//        journalDatabaseHelper.getJournalsForMonth(Date.from(Instant.now()));

        // create navbar and set selected item to HomePage
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();
        bottomNavigationView.setSelectedItemId(R.id.homeButton);
    }

    HomeFragment homeFragment = new HomeFragment();


    public void addEvent(View view) {
        //For the date format
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        //Create a date object
        Date date;
        try {
            //Parse inputted date string and assign to date
            date = sdf.parse("26/10/2023");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        assert date != null;
        //Create a new event with the color, date in milliseconds and extra data if needed
        Event ev1 = new Event(Color.GREEN, date.getTime(), "Some extra data that I want to store.");
        //Find calendar on display
        CompactCalendarView c = findViewById(R.id.calendar);
        //Add event
        c.addEvent(ev1);
    }

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
        if (item.getItemId() == R.id.homeButton) {
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
        if (item.getItemId() == R.id.tutorial) {
            Intent intent = new Intent(HomePage.this, TutorialActivity.class);
            startActivity(intent);
            return true;
        }
        return true;
    }
}