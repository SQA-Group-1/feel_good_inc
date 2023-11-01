package com.example.feelgoodinc.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.feelgoodinc.CalendarUtility;
import com.example.feelgoodinc.R;
import com.example.feelgoodinc.TutorialActivity;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ImageButton button = view.findViewById(R.id.tutorialButton);
        button.setOnClickListener(l -> account(view));
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