package com.example.feelgoodinc.fragments.calmingActivities;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.feelgoodinc.R;

public class GroundingFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grounding, container, false);
        Button button = view.findViewById(R.id.submitGrounding);
        button.setOnClickListener(l-> submitGrounding(view));
        return view;
    }


    public void submitGrounding(View view){
        LinearLayout layout = view.findViewById(R.id.groundingHolder);
        final int childCount = layout.getChildCount();
        boolean notEmpty = true;
        for (int i = 0; i < childCount; i++) {
            View v = layout.getChildAt(i);
            if(v instanceof EditText){
                if(((EditText) v).getText().toString().equals("")){
                    notEmpty = false;
                }
                    TextView feedback = view.findViewById(R.id.feedbackGrounding);
                    feedback.setText(R.string.fields_cannot_be_left_empty);
                }
            }
        if(notEmpty){
            layout.removeAllViews();
            Button close = new Button(new ContextThemeWrapper(this.requireContext(),
                    com.google.android.material.R.style.Widget_Material3_Button),
                    null,
                    com.google.android.material.R.style.Widget_Material3_Button);
            TextView congrats = new TextView(this.getContext());
            close.setText(R.string.close_activity);
            congrats.setText(R.string.congratulations);
            congrats.setTextSize(28);
            close.setOnClickListener(l -> {
                FragmentManager fm = requireActivity().getSupportFragmentManager();
                ActivityFragment activityFragment = new ActivityFragment();
                fm.beginTransaction()
                        .replace(R.id.flFragment, activityFragment)
                        .commit();
            });
            layout.addView(congrats);
            layout.addView(close);
        }
    }
}