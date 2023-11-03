package com.example.feelgoodinc.fragments;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.feelgoodinc.R;
import com.example.feelgoodinc.activityRecyclerView.ActivityData;
import com.example.feelgoodinc.activityRecyclerView.ClickListener;
import com.example.feelgoodinc.adapters.ActivityGalleryAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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
        ArrayList<ActivityData> activityList = new ArrayList<>();
        adapter = new ActivityGalleryAdapter(activityList, requireActivity().getApplication(), listener);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
    }

    public List<ActivityData> populateList(){
        List<ActivityData> list = new ArrayList<>();
        
        return list;
    }
}