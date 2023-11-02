package com.example.feelgoodinc.fragments;


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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoodFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mood, container, false);
        RadioGroup moodsGroup = view.findViewById(R.id.moodsGroup);
        Button confirmButton = view.findViewById(R.id.moodComfirmButton);
        TextView dateText = view.findViewById(R.id.date);
        RadioButton moodButton;
        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        String dateStr = currentDate.format(todayDate);

        dateText.setText(dateStr);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int selectedId = moodsGroup.getCheckedRadioButtonId();
                RadioButton moodButton = (RadioButton) view.findViewById(selectedId);
            }

        });

        moodsGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });

        return view;
    }





}