package com.example.feelgoodinc.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.example.feelgoodinc.R;
import com.example.feelgoodinc.TutorialActivity;
import com.example.feelgoodinc.services.CalendarService;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * The fragment that allows the user to view their moods and journal entries via the calendar on this view.
 */
public class HomeFragment extends Fragment implements CalendarService.CalendarServiceCallback{
    CalendarService calendarService;

    /**
     * Initialises the {@link CalendarService} and adds listeners to {@link CompactCalendarView}
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return fragment view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ImageButton button = view.findViewById(R.id.tutorialButton);
        button.setOnClickListener(l -> tutorial());
        CompactCalendarView compactCalendarView = view.findViewById(R.id.calendar);
        //Example usage of adding event
        calendarService = new CalendarService(this.getContext(),this);
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            /**
             * Displays a popup message detailing the Journal entry for the Mood on the calendar
             * @param dateClicked the date the user selects
             */
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = compactCalendarView.getEvents(dateClicked);
                if(!events.isEmpty()){
                    compactCalendarView.setCurrentSelectedDayBackgroundColor(events.get(0).getColor());
                    View coordinatorLayout = view.findViewById(R.id.coordinator);
                    String date = dateClicked.toString().substring(0,10);
                    Snackbar snackbar = Snackbar.make(coordinatorLayout,date + ": "+ Objects.requireNonNull(events.get(0).getData()), BaseTransientBottomBar.LENGTH_SHORT);
                    snackbar.show();
                }else{
                    compactCalendarView.setCurrentSelectedDayBackgroundColor(Color.GRAY);
                }
            }

            /**
             * Repopulates the {@link CompactCalendarView} with events on month scroll
              * @param firstDayOfNewMonth first date of the new month
             */
            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                calendarService.populateCalendarMonth(compactCalendarView,firstDayOfNewMonth);
            }
        });
        return view;
    }

    /**
     * Moves to the account page on button press
     */
    public void tutorial(){
        Intent intent = new Intent(getActivity(), TutorialActivity.class);
        startActivity(intent);
    }

    /**
     * This unbinds and destroys the services in the calendar service class
     */
    @Override
    public void onDestroy(){
        calendarService.unBindServices();
        super.onDestroy();
    }

    /**
     * This class is called when the CalendarService class is initialised and the services are bound in it
     */
    @Override
    public void onServicesBound() {
        CompactCalendarView compactCalendarView = this.requireView().findViewById(R.id.calendar);
        calendarService.populateCalendarMonth(compactCalendarView, Calendar.getInstance().getTime());
    }
}