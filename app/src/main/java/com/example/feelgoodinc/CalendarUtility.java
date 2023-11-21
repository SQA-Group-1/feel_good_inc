package com.example.feelgoodinc;

import android.graphics.Color;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/***
 * @description this class should be used to create a calendar that can have colored dates
 * Usage:
 * <pre>
 *       // inside the onCreate method of an activity
 *       CompactCalendarView calendarView = view.findViewById(R.id.calendar);
 *       CalendarUtility.addDateColourWithData(calendarView,"27/10/2023","This is an event", Color.GREEN);
 * </pre>
 * OR
 * <pre>
 *      // inside the onCreate method of an activity
 *      CompactCalendarView calendarView = view.findViewById(R.id.calendar);
 *     CalendarUtility.addDateColourWithData(calendarView,"27/10/2023", Color.GREEN);
 * </pre>
 */
public class CalendarUtility {

    /***
     * This function will create a coloured event on the calendar without event details,
     * @param c a calendar element on the XML layout
     * @param date is the date you want to add to
     * @param color using the {@link Color} enum to decide color (i.e. Color.GREEN)
     */
    public static void addDateColour(CompactCalendarView c, Date date, int color){
        //Create a new event with the color, date in miliseconds and extra data if needed
        Event ev1 = new Event(color, date.getTime());
        //Add event
        c.addEvent(ev1);
    }

    /***
     * This function takes an additional data string to add data about the event on the calendar
     * @param c a calendar element on the XML layout
     * @param date is the date added
     * @param eventDetails a string describing the event (i.e.
     * @param color using the {@link Color} enum to decide color (i.e. Color.GREEN)
     */
    public static void addDateColourWithData(CompactCalendarView c, Date date, String eventDetails, int color){
        //Create a new event with the color, date in miliseconds and extra data if needed
        Event ev1 = new Event(color, date.getTime(), eventDetails);
        //Add event
        c.addEvent(ev1);
    }
}
