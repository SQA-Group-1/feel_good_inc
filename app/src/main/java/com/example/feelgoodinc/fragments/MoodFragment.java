package com.example.feelgoodinc.fragments;



import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

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
import com.example.feelgoodinc.database.JournalDatabaseHelper;
import com.example.feelgoodinc.database.MoodDatabaseHelper;
import com.example.feelgoodinc.models.Journal;
import com.example.feelgoodinc.models.Mood;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A Fragment that allows the user to record their mood and associated journal entry for a given date.
 * The user can choose a mood from a set of predefined options and enter a journal text.
 * The selected mood and journal entry can be confirmed and saved to a database.
 *
 * This fragment includes UI elements such as a RadioGroup for mood selection, a button to confirm
 * the mood and journal entry.
 *
 * The class utilizes a MoodDatabaseHelper and JournalDatabaseHelper for managing mood and journal data storage.
 * MoodFragment also provides a method to convert the selected mood text to the corresponding MoodType.
 *
 */
public class MoodFragment extends Fragment {
    JournalDatabaseHelper journalDatabaseHelper;
    MoodDatabaseHelper moodDatabaseHelper;
    Journal journalObj;
    Mood moodObj;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mood, container, false);
        RadioGroup moodsGroup = view.findViewById(R.id.moodsGroup);
        Button confirmButton = view.findViewById(R.id.moodComfirmButton);
        TextView dateText = view.findViewById(R.id.date);
        TextInputEditText journalEntry = view.findViewById(R.id.journalEntry);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat currentDate = new SimpleDateFormat("dd.MM.yyyy");
        Date todayDate = new Date();
        String dateStr = currentDate.format(todayDate);
        dateText.setText(dateStr);

        moodDatabaseHelper = new MoodDatabaseHelper();
        journalDatabaseHelper = new JournalDatabaseHelper();

        RadioButton selectedRadioButton = view.findViewById(moodsGroup.getCheckedRadioButtonId());
        selectedRadioButton.setTextColor(Color.BLUE);

        confirmButton.setOnClickListener(view1 -> {
            String selectedMood = "";
            int selectedId = moodsGroup.getCheckedRadioButtonId();
            if(selectedId != -1) {
                RadioButton moodButton = (RadioButton) view.findViewById(selectedId);
                selectedMood = moodButton.getText().toString();
                moodObj = new Mood(convertTextToMoodType(selectedMood), todayDate);
            }

            String journalText = String.valueOf(journalEntry.getText());
            if(journalText.length() == 0) {
                Toast.makeText(getActivity(), "Don't forget to write something for your feeling!", Toast.LENGTH_SHORT).show();
            }
            else{
                journalObj = new Journal("THIS IS TESTING ONLY", todayDate, todayDate, journalText);
                journalDatabaseHelper.addNewJournal(journalObj, getActivity());
                moodDatabaseHelper.addNewMood(moodObj,getActivity());
            }
        });


        moodsGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedRadioButton = view.findViewById(checkedId);

                for (int i = 0; i < moodsGroup.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) moodsGroup.getChildAt(i);

                    if (radioButton == selectedRadioButton) {
                        radioButton.setTextColor(Color.BLUE); // Change the text color for the selected radio button
                    } else {
                        radioButton.setTextColor(Color.GRAY); // Set the text color for other radio buttons to gray
                    }
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