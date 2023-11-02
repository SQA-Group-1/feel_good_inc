package com.example.feelgoodinc.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.feelgoodinc.R;

public class UserProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO: fetch user info from backend to display
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false);

    }
}