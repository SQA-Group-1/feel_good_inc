package com.example.feelgoodinc.fragments.calmingActivities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feelgoodinc.R;
import com.example.feelgoodinc.activityRecyclerView.ClickListener;
import com.example.feelgoodinc.adapters.ActivityGalleryAdapter;
import com.example.feelgoodinc.models.Activity;

import java.util.ArrayList;
import java.util.List;


public class ActivityFragment extends Fragment {
    ActivityGalleryAdapter adapter;
    ClickListener listener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        List<Activity> activityList = populateList();
        listener = new ClickListener(){
            //Choose what you do with each item
            @Override
            public void click(int index){
                FragmentManager fm = requireActivity().getSupportFragmentManager();
                switch(index){
                    case 0:
                        GroundingFragment groundingFragment = new GroundingFragment();
                        fm.beginTransaction()
                                .replace(R.id.flFragment, groundingFragment)
                                .commit();
                        break;
                    case 1:
                        BreathingFragment breathingFragment = new BreathingFragment();
                        fm.beginTransaction()
                                .replace(R.id.flFragment, breathingFragment)
                                .commit();
                        break;
                    case 2:
                        SleepFragment sleepFragment = new SleepFragment();
                        fm.beginTransaction()
                                .replace(R.id.flFragment, sleepFragment)
                                .commit();
                        break;
                }

            }
        };
        adapter = new ActivityGalleryAdapter(activityList, requireActivity().getApplication(), listener);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
    }

    public List<Activity> populateList(){
        List<Activity> list = new ArrayList<>();
        Activity groundingActivity = new Activity("Grounding", "Calm down with our meditation routines", 20, R.drawable.meditating);
        Activity breathingActivity = new Activity("Breathing Exercises", "Slow your heart rate with our breathing exercises",5, R.drawable.loving);
        Activity sleepingActivity = new Activity("Sleeping Aid","Listen to calming nature sounds to help sleep",60,R.drawable.levitate);
        list.add(groundingActivity);
        list.add(breathingActivity);
        list.add(sleepingActivity);
        return list;
    }
}