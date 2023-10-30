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
    // This function will create a coloured event on the calendar without event details, date is formatted dd/mm/yyyy
    public static void addDateColour(CompactCalendarView c, String stringDate, int color){
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
        Event ev1 = new Event(color, date.getTime());
        //Add event
        c.addEvent(ev1);
    }
    //This function takes an additional data string to add data about the event on the calendar
    public static void addDateColourWithData(CompactCalendarView c, String stringDate, String eventDetails, int color){
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
        Event ev1 = new Event(color, date.getTime(), eventDetails);
        //Add event
        c.addEvent(ev1);
    }
}
