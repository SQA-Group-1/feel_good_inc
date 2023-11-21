package com.example.feelgoodinc.services;

import android.graphics.Color;

import com.example.feelgoodinc.models.Journal;
import com.example.feelgoodinc.models.Mood;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Uses the {@link JournalService} and {@link MoodService} to retrieve the {@link Journal} and {@link Mood} of the month</p>
 *
 */
public class CalendarService {

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

    /**
     * Reads in all of the {@link Journal} and {@link Mood} for the month and adds them to a calendar
     * @param calendarView the calendar you want to display the moods and journals
     * @param date the date containing the month you wish to use
     */
    public void populateCalendarMonth(CompactCalendarView calendarView, Date date){
        JournalService journalDatabaseHelper = new JournalService();
        List<Journal> journals = journalDatabaseHelper.getJournalsForMonth(date);
        if(journals.isEmpty()){
            return;
        }
        MoodService moodDatabaseHelper = new MoodService();
        List<Mood> moods = moodDatabaseHelper.getMoodsForMonth(date);
        if(moods.isEmpty()){
            return;
        }
        for(Mood mood : moods){
            Journal journal = findJournal(journals,date);
            int colour = 0;
            switch(mood.getMoodType()){
                case RAD:
                    //colour = R.color.rad;
                    break;
                case GOOD:
                    //colour = R.color.good;
                    break;
                case MEH:
                    //colour = R.color.meh;
                    break;
                case SAD:
                    //colour = R.color.sad;
                    break;
                case AWFUL:
                    //colour = R.color.awful;
                    break;
            }
            addDateColourWithData(calendarView,mood.getMoodWhen(),journal.getContent(),colour);
        }
    }

    /**
     * Finds the journal in a list on a given date
     * @param journals the list of journals
     * @param date the date of creation
     * @return {@link Journal} created on date
     */
    private Journal findJournal(List<Journal> journals, Date date){
        Journal journal;
        journal = journals.stream()
                .filter(s -> s.getCreatedWhen().equals(date))
                .collect(Collectors.toList()).get(0);
        return journal;
    }
}
