package com.example.feelgoodinc;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    public void addEvent(View view){
        //For the date format
        SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
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
}