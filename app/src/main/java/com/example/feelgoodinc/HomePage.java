package com.example.feelgoodinc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.feelgoodinc.database.MoodDatabaseHelper;
import com.example.feelgoodinc.fragments.HomeFragment;
import com.example.feelgoodinc.models.Mood;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

import org.w3c.dom.Document;

public class HomePage extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    BottomNavigationView bottomNavigationView;
//    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        MoodDatabaseHelper moodHelper = new MoodDatabaseHelper();
//        moodHelper.addNewMood(new Mood(Mood.MoodType.GOOD, Date.from(Instant.now())), HomePage.this);
        moodHelper.getMoodsForMonth(Date.from(Instant.now()));

//        firestore = FirebaseFirestore.getInstance();

        // create navbar and set selected item to HomePage
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();
        bottomNavigationView.setSelectedItemId(R.id.homeButton);

        // setup Firebase stuff
//        Map<String, Object> users = new HashMap<>();
//        users.put("username", "isaac");
//        users.put("password", "test");
//        users.put("lastLoginWhen", Instant.now().getEpochSecond());
//
//        firestore.collection("users").add(users).addOnSuccessListener(documentReference ->
//                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show()
//        ).addOnFailureListener(e ->
//                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_LONG).show());


//        CollectionReference journalsRef = firestore.collection("users").document("Q1owyXZv2qytn1QXHmPs").collection("journals");
//
//        CollectionReference moodsRef = firestore.collection("users").document("Q1owyXZv2qytn1QXHmPs").collection("moods");
//
//        Map<String, Object> moods = new HashMap<>();
//        Random rand = new Random();
//        moods.put("mood", rand.nextInt(3));
//        moods.put("moodWhen", Instant.now().getEpochSecond());
//
//        moodsRef.add(moods).addOnSuccessListener(
//                documentReference -> Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show())
//                .addOnFailureListener(e -> {
//                    Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_LONG).show();
//                });
//
//        Map<String, Object> journals = new HashMap<>();
//        journals.put("journalName", "test name");
//        journals.put("createdWhen", Instant.now().getEpochSecond());
//        journals.put("lastEditedWhen", Instant.now().getEpochSecond());
//        journals.put("content", "This is a not really super long string telling you about my day and emotions.");
//
//
//        journalsRef.add(journals).addOnSuccessListener(
//                        documentReference -> Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show())
//                .addOnFailureListener(e -> {
//                    Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_LONG).show();
//                });

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
        //Create a new event with the color, date in miliseconds and extra data if needed
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