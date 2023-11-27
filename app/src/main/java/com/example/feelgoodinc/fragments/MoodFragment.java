package com.example.feelgoodinc.fragments;


import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feelgoodinc.R;
import com.example.feelgoodinc.models.Journal;
import com.example.feelgoodinc.models.Mood;
import com.example.feelgoodinc.services.JournalService;
import com.example.feelgoodinc.services.MoodService;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A Fragment that allows the user to record their mood and associated journal entry for a given date.
 * The user can choose a mood from a set of predefined options and enter a journal text.
 * The selected mood and journal entry can be confirmed and saved to a database.
 * <p>
 * This fragment includes UI elements such as a RadioGroup for mood selection, a button to confirm
 * the mood and journal entry.
 * <p>
 * The class utilizes a MoodDatabaseHelper and JournalDatabaseHelper for managing mood and journal data storage.
 * MoodFragment also provides a method to convert the selected mood text to the corresponding MoodType.
 */
public class MoodFragment extends Fragment {
    Journal journalObj;
    Mood moodObj;
    boolean isClicked;
    MoodService moodService;
    JournalService journalService;
    boolean moodBound = false, journalBound = false;

    //region service
    @Override
    public void onStop() {
        super.onStop();

        // Unbind from the Mood service
        requireContext().stopService(new Intent(getContext(), MoodService.class));
        requireContext().unbindService(moodConnection);

        // Unbind from the Journal Service
        requireContext().stopService(new Intent(getContext(), JournalService.class));
        requireContext().unbindService(journalConnection);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Bind activity to service
        Intent intent = new Intent(this.getActivity(), MoodService.class);
        requireActivity().bindService(intent, moodConnection, Context.BIND_AUTO_CREATE);

        //  Bind activity to Journal service
        Intent moodIntent = new Intent(this.getActivity(), JournalService.class);
        requireActivity().bindService(moodIntent, journalConnection, Context.BIND_AUTO_CREATE);
    }



    protected ServiceConnection moodConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("COMP3013", "onServiceConnected");
            MoodService.LocalBinder binder = (MoodService.LocalBinder) service;
            moodService = binder.getService();
            moodBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("COMP3013", "onServiceDisconnected");
            moodBound = false;
        }
    };

    protected ServiceConnection journalConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("COMP3013", "onServiceConnected");
            JournalService.LocalBinder binder = (JournalService.LocalBinder) service;
            journalService = binder.getService();
            journalBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    //endregion


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        isClicked = false;
        View view = inflater.inflate(R.layout.fragment_mood, container, false);
        RadioGroup moodsGroup = view.findViewById(R.id.moodsGroup);
        Button confirmButton = view.findViewById(R.id.moodComfirmButton);
        TextView dateText = view.findViewById(R.id.date);
        TextInputEditText journalName = view.findViewById(R.id.journalName);
        TextInputEditText journalEntry = view.findViewById(R.id.journalEntry);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        String dateStr = currentDate.format(todayDate);
        dateText.setText(dateStr);

        RadioButton selectedRadioButton = view.findViewById(moodsGroup.getCheckedRadioButtonId());
        selectedRadioButton.setTextColor(Color.BLUE);

        confirmButton.setOnClickListener(view1 -> {
            String selectedMood = "";
            int selectedId = moodsGroup.getCheckedRadioButtonId();
            if (selectedId != -1) {
                RadioButton moodButton = view.findViewById(selectedId);
                selectedMood = moodButton.getText().toString();
                moodObj = new Mood(convertTextToMoodType(selectedMood), todayDate);
            }

            String journalText = String.valueOf(journalEntry.getText());
            String journalTitle = String.valueOf(journalName.getText());
            if (journalText.length() == 0 || journalTitle.length() == 0) {
                Toast.makeText(getActivity(), "Don't forget to write something for your feeling!", Toast.LENGTH_SHORT).show();
            } else {
                journalObj = new Journal(journalTitle, todayDate, todayDate, journalText);

                if (moodBound) {
                    moodService.addNewMood(moodObj, getActivity());
                }
                if (journalBound) {

                        journalService.addNewJournal(journalObj, getActivity());

                }

            }
        });


        moodsGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton selectedRadioButton1 = view.findViewById(checkedId);

            for (int i = 0; i < moodsGroup.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) moodsGroup.getChildAt(i);

                if (radioButton == selectedRadioButton1) {
                    radioButton.setTextColor(Color.BLUE); // Change the text color for the selected radio button
                } else {
                    radioButton.setTextColor(Color.GRAY); // Set the text color for other radio buttons to gray
                }
            }
        });

        return view;
    }

    public Mood.MoodType convertTextToMoodType(String buttonText) {
        switch (buttonText) {
            case "Rad":
                return Mood.MoodType.RAD;
            case "Good":
                return Mood.MoodType.GOOD;
            case "Meh":
                return Mood.MoodType.MEH;
            case "Sad":
                return Mood.MoodType.SAD;
            case "Awful":
                return Mood.MoodType.AWFUL;
            default:
                return null;
        }
    }


}