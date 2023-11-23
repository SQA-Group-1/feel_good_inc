package com.example.feelgoodinc.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.IBinder;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.feelgoodinc.R;
import com.example.feelgoodinc.models.Journal;
import com.example.feelgoodinc.models.Mood;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>Uses the {@link JournalService} and {@link MoodService} to retrieve the {@link Journal} and {@link Mood} of the month</p>
 *
 */
public class CalendarService {
    public MoodService moodService;
    public JournalService journalService;
    Context context;
    private boolean isBound = false;

    /**
     * Creates the calendar service object
     * @param context the context of the app
     */
    public CalendarService(Context context){
        this.context = context;

        Intent journalIntent = new Intent(context, JournalService.class);
        context.startService(journalIntent);
        context.bindService(journalIntent, serviceConnection1, Context.BIND_AUTO_CREATE);
        Intent moodIntent = new Intent(context, MoodService.class);
        context.startService(moodIntent);
        context.bindService(moodIntent, serviceConnection2, Context.BIND_AUTO_CREATE);
    }

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
        AtomicReference<List<Journal>> journals = new AtomicReference<>();
        AtomicReference<List<Mood>> moods = new AtomicReference<>();
        if(isBound) {
            journalService.getJournalsForMonth(date, journals1 -> {
                journals.set(journals1);

                // Fetch moods asynchronously
                moodService.getMoodsForMonth(date, moods1 -> {
                    moods.set(moods1);

                    for(Mood mood : moods.get()){
                        Log.d("Test", mood.getMoodType().getClass().getName());
                        Journal journal = findJournal(journals.get(),mood.getMoodWhen());
                        if(journal != null) {
                            if (mood.getMoodType().equals(Mood.MoodType.RAD)) {
                                int colour = ContextCompat.getColor(context, R.color.rad);
                                addDateColourWithData(calendarView, mood.getMoodWhen(), journal.getContent(), colour);
                                if(areDatesOnSameDay(mood.getMoodWhen(),Calendar.getInstance().getTime())){
                                    calendarView.setCurrentDayBackgroundColor(colour);
                                }
                            }
                            if (mood.getMoodType().equals(Mood.MoodType.GOOD)) {
                                int colour = ContextCompat.getColor(context, R.color.good);
                                addDateColourWithData(calendarView, mood.getMoodWhen(), journal.getContent(), colour);
                                if(areDatesOnSameDay(mood.getMoodWhen(),Calendar.getInstance().getTime())){
                                    calendarView.setCurrentDayBackgroundColor(colour);
                                }
                            }
                            if (mood.getMoodType().equals(Mood.MoodType.MEH)) {
                                int colour = ContextCompat.getColor(context, R.color.meh);
                                addDateColourWithData(calendarView, mood.getMoodWhen(), journal.getContent(), colour);
                                if(areDatesOnSameDay(mood.getMoodWhen(),Calendar.getInstance().getTime())){
                                    calendarView.setCurrentDayBackgroundColor(colour);
                                }
                            }
                            if (mood.getMoodType().equals(Mood.MoodType.SAD)) {
                                int colour = ContextCompat.getColor(context, R.color.sad);
                                addDateColourWithData(calendarView, mood.getMoodWhen(), journal.getContent(), colour);
                                if(areDatesOnSameDay(mood.getMoodWhen(),Calendar.getInstance().getTime())){
                                    calendarView.setCurrentDayBackgroundColor(colour);
                                }
                            }
                            if (mood.getMoodType().equals(Mood.MoodType.AWFUL)) {
                                int colour = ContextCompat.getColor(context, R.color.awful);
                                addDateColourWithData(calendarView, mood.getMoodWhen(), journal.getContent(), colour);
                                if(areDatesOnSameDay(mood.getMoodWhen(),Calendar.getInstance().getTime())){
                                    calendarView.setCurrentDayBackgroundColor(colour);
                                }
                            }

                        }
                    }
                });
            });

        }
    }

    /**
     * Unbinds the {@link JournalService} and {@link MoodService} from the app
     */
    public void unBindServices(){
        context.unbindService(serviceConnection1);
        context.unbindService(serviceConnection2);
    }

    /**
     * Checks if two dates are on the same day
     * @param date1 the first {@link Date) object
     * @param date2 the second {@link Date} object
     * @return True if the dates are on the same day, false if otherwise
     */
    public static boolean areDatesOnSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Finds the journal in a list on a given date
     * @param journals the list of journals
     * @param date the date of creation
     * @return {@link Journal} created on date
     */
    private Journal findJournal(List<Journal> journals, Date date){
        for(Journal journal : journals) {
            if(areDatesOnSameDay(journal.getCreatedWhen(),date)) {
                return journal;
            }
        }
        return null;
    }

    /**
     * The service connection used to bind the {@link JournalService}
     */
    final ServiceConnection serviceConnection1 = new ServiceConnection() {
        /**
         * This binds the {@link JournalService} to the app
         * @param componentName The concrete component name of the service that has
         * been connected.
         *
         * @param iBinder The IBinder of the Service's communication channel,
         * which you can now make calls on.
         */
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            JournalService.LocalBinder binder =  (JournalService.LocalBinder) iBinder;
            journalService = binder.getService();
            isBound = true;
        }

        /**
         * This stops the {@link JournalService} when unbound
         * @param componentName The concrete component name of the service whose
         * connection has been lost.
         */
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Intent journalIntent = new Intent(context, JournalService.class);
            context.stopService(journalIntent);
            isBound = false;
        }
    };

    final ServiceConnection serviceConnection2 = new ServiceConnection() {
        /**
         * This binds the {@link MoodService} to the app
         * @param componentName The concrete component name of the service that has
         * been connected.
         *
         * @param iBinder The IBinder of the Service's communication channel,
         * which you can now make calls on.
         */
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MoodService.LocalBinder binder =  (MoodService.LocalBinder) iBinder;
            moodService = binder.getService();
            isBound = true;
        }

        /**
         * This stops the {@link MoodService} when unbound
         * @param componentName The concrete component name of the service whose
         * connection has been lost.
         */
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Intent moodIntent = new Intent(context, MoodService.class);
            context.stopService(moodIntent);
            isBound = false;
        }
    };
}
