package com.example.feelgoodinc;

import android.graphics.Color;
import android.view.View;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CalendarUtility {
    public void addDateColour(CompactCalendarView c, String stringDate){
        //For the date format
        SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        //Create a date object
        Date date;
        try {
            //Parse inputted date string and assign to date
            date = sdf.parse(stringDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        assert date != null;
        //Create a new event with the color, date in miliseconds and extra data if needed
        Event ev1 = new Event(Color.GREEN, date.getTime());
        //Add event
        c.addEvent(ev1);
    }
    public void addDateColour(CompactCalendarView c, String stringDate, String eventDetails){
        //For the date format
        SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        //Create a date object
        Date date;
        try {
            //Parse inputted date string and assign to date
            date = sdf.parse(stringDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        assert date != null;
        //Create a new event with the color, date in miliseconds and extra data if needed
        Event ev1 = new Event(Color.GREEN, date.getTime(), eventDetails);
        //Add event
        c.addEvent(ev1);
    }
}
