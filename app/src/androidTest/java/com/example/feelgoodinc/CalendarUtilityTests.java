package com.example.feelgoodinc;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RunWith(AndroidJUnit4.class)
public class CalendarUtilityTests {
    
    //Test covers the date the event is added on
    @Test
    public void isEventAdded(){
        new Handler(Looper.getMainLooper()).post(() -> {
            Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            CompactCalendarView compactCalendarView = new CompactCalendarView(appContext);
            CalendarUtility.addDateColour(compactCalendarView,"15/11/2023", Color.BLUE);
            SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            //Create a date object
            Date date;
            try {
                //Parse inputted date string and assign to date
                date = sdf.parse("15/11/2023");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            List<Event> events =  compactCalendarView.getEvents(date);
            assertEquals(events.size(),1);
        });

    }

    //This tests that the data for the event is stored correctly
    @Test
    public void isEventDataAdded(){
        new Handler(Looper.getMainLooper()).post(() -> {
            Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            CompactCalendarView compactCalendarView = new CompactCalendarView(appContext);
            CalendarUtility.addDateColourWithData(compactCalendarView, Calendar.getInstance().getTime(), "Test", Color.BLUE);
            SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            //Create a date object
            Date date = Calendar.getInstance().getTime();
            List<Event> events =  compactCalendarView.getEvents(date);
            assertEquals(events.get(0).getData(),"Test");
        });
    }

    //This tests that the colour for the event is stored correctly
    @Test
    public void isEventColourAdded(){
        new Handler(Looper.getMainLooper()).post(() -> {
            Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            CompactCalendarView compactCalendarView = new CompactCalendarView(appContext);
            CalendarUtility.addDateColourWithData(compactCalendarView,Calendar.getInstance().getTime(), "Test", Color.BLUE);
            Date date = Calendar.getInstance().getTime();
            List<Event> events =  compactCalendarView.getEvents(date);
            assertEquals(events.get(0).getColor(),Color.BLUE);
        });
    }
}
