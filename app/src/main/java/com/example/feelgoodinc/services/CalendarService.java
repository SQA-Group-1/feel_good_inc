package com.example.feelgoodinc.services;

import com.example.feelgoodinc.CalendarUtility;
import com.example.feelgoodinc.R;
import com.example.feelgoodinc.models.Journal;
import com.example.feelgoodinc.models.Mood;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Uses the {@link JournalService} and {@link MoodService} to retrieve the {@link Journal} and {@link Mood} of the month</p>
 *
 */
public class CalendarService {

    /**
     * Reads in all of the {@link Journal} and {@link Mood} for the month and adds them to a calendar
     * @param calendarView the calendar you want to display the moods and journals
     * @param date the date containing the month you wish to use
     */
    public void populateCalendarMonth(CompactCalendarView calendarView, Date date){
        JournalService journalDatabaseHelper = new JournalService();
        List<Journal> journals = journalDatabaseHelper.getJournalsForMonth(date);
        MoodService moodDatabaseHelper = new MoodService();
        List<Mood> moods = moodDatabaseHelper.getMoodsForMonth(date);
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
            CalendarUtility.addDateColourWithData(calendarView,mood.getMoodWhen(),journal.getContent(),colour);
        }
    }

    private Journal findJournal(List<Journal> journals, Date date){
        Journal journal;
        journal = journals.stream()
                .filter(s -> s.getCreatedWhen().equals(date))
                .collect(Collectors.toList()).get(0);
        return journal;
    }
}
