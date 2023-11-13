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
import com.example.feelgoodinc.R;
import com.example.feelgoodinc.database.JournalDatabaseHelper;
import com.example.feelgoodinc.database.MoodDatabaseHelper;
import com.example.feelgoodinc.models.Journal;
import com.example.feelgoodinc.models.Mood;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
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
        @SuppressLint("SimpleDateFormat") SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        String dateStr = currentDate.format(todayDate);
        dateText.setText(dateStr);

        moodDatabaseHelper = new MoodDatabaseHelper();
        journalDatabaseHelper = new JournalDatabaseHelper();


        confirmButton.setOnClickListener(view1 -> {
            String selectedMood = "";
            int selectedId = moodsGroup.getCheckedRadioButtonId();

            if(selectedId != -1) {
                RadioButton moodButton = (RadioButton) view.findViewById(selectedId);
                selectedMood = moodButton.getText().toString();
                moodObj = new Mood(convertTextToMoodType(selectedMood), todayDate);
                moodDatabaseHelper.addNewMood(moodObj,getActivity());
            }

            String journalText = String.valueOf(journalEntry.getText());
            journalObj = new Journal("THIS IS TESTING ONLY", todayDate, todayDate, journalText);
            journalDatabaseHelper.addNewJournal(journalObj,getActivity());
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