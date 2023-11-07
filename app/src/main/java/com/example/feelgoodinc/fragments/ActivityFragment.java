package com.example.feelgoodinc.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feelgoodinc.R;
import com.example.feelgoodinc.activityRecyclerView.ActivityData;
import com.example.feelgoodinc.activityRecyclerView.ClickListener;
import com.example.feelgoodinc.adapters.ActivityGalleryAdapter;

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
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        List<ActivityData> activityList = populateList();
        listener = new ClickListener(){
            //Choose what you do with each item
            @Override
            public void click(int index){
                ActivityData data = activityList.get(index);
                Log.d("SQA", data.description);
            }
        };
        adapter = new ActivityGalleryAdapter(activityList, requireActivity().getApplication(), listener);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
    }

    public List<ActivityData> populateList(){
        List<ActivityData> list = new ArrayList<>();
        ActivityData meditateActivity = new ActivityData("Meditate", "Calm down with our meditation routines", 20, R.drawable.meditating);
        ActivityData breathingActivity = new ActivityData("Breathing Exercises", "Slow your heart rate with our breathing exercises",5, R.drawable.loving);
        list.add(meditateActivity);
        list.add(breathingActivity);
        return list;
    }
}