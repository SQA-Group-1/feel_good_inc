package com.example.feelgoodinc.fragments;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.feelgoodinc.CalendarUtility;
import com.example.feelgoodinc.R;
import com.example.feelgoodinc.TutorialActivity;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ImageButton button = view.findViewById(R.id.tutorialButton);
        button.setOnClickListener(l -> account(view));
        CompactCalendarView compactCalendarView = view.findViewById(R.id.calendar);
        CalendarUtility.addDateColourWithData(compactCalendarView,"14/11/2023","Yippee",Color.BLUE);
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = compactCalendarView.getEvents(dateClicked);
                if(!events.isEmpty()){
                    View coordinatorLayout = view.findViewById(R.id.coordinator);
                    Snackbar snackbar = Snackbar.make(coordinatorLayout,dateClicked + ": "+Objects.requireNonNull(events.get(0).getData()).toString(), BaseTransientBottomBar.LENGTH_SHORT);
                    snackbar.show();
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {

            }
        });
        return view;
    }

    //Example implementation of the calendar event add
    public void eventAdder(View view) {
        CompactCalendarView calendarView = view.findViewById(R.id.calendar);
        CalendarUtility.addDateColourWithData(calendarView,"27/10/2023","This is an event", Color.GREEN);
    }

    public void account(View view){
        Intent intent = new Intent(getActivity(), TutorialActivity.class);
        startActivity(intent);
    }

}