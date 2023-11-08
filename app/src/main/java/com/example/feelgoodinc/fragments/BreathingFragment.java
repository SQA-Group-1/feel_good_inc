package com.example.feelgoodinc.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.feelgoodinc.R;

public class BreathingFragment extends Fragment {
    int breathingTime;
    int exhale = 4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_breathing, container, false);
        Button close = view.findViewById(R.id.breathActivityClose);
        close.setOnClickListener(l ->{closeActivity();});
        ProgressBar progressBar = view.findViewById(R.id.progress_bar);
        TextView progressText = view.findViewById(R.id.progress_text);
        TextView instructions = view.findViewById(R.id.breathInstruction);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // set the limitations for the numeric
                // text under the progress bar
                if (breathingTime <= 4) {
                    instructions.setText(R.string.breathe_in);
                    String time = "" + breathingTime;
                    progressText.setText(time);
                    progressBar.setProgress(breathingTime,true);
                    breathingTime++;
                    handler.postDelayed(this, 1000);
                }else{
                    instructions.setText(R.string.breathe_out);
                    if(exhale >= 0){
                        String time = "" + exhale;
                        progressText.setText(time);
                        progressBar.setProgress(exhale,true);
                        exhale--;
                        handler.postDelayed(this, 1000);
                    }else{
                        instructions.setText("");
                        progressText.setText(R.string.congratulations);
                        handler.removeCallbacks(this);
                    }
                }
            }
        }, 1000);
        return view;

    }

    public void closeActivity(){
        FragmentManager fm = requireActivity().getSupportFragmentManager();
        ActivityFragment activityFragment = new ActivityFragment();
        fm.beginTransaction()
                .replace(R.id.flFragment, activityFragment)
                .commit();
    }
}