package com.example.feelgoodinc.fragments;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.feelgoodinc.R;

public class TutorialFragment4 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tutorial4, container, false);
        TextView tv = (v.findViewById(R.id.eulaText));
        tv.setMovementMethod(new ScrollingMovementMethod());
        return v;
    }
}