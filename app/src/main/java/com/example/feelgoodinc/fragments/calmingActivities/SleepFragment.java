package com.example.feelgoodinc.fragments.calmingActivities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.feelgoodinc.R;

public class SleepFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sleep, container, false);
        Button rainforest = view.findViewById(R.id.rainforestSounds);
        rainforest.setOnClickListener(l->play(R.id.rainforestSounds));
        Button ocean = view.findViewById(R.id.oceanSounds);
        ocean.setOnClickListener(l->play(R.id.oceanSounds));
        Button animal = view.findViewById(R.id.animalSounds);
        animal.setOnClickListener(l->play(R.id.animalSounds));
        Button close = view.findViewById(R.id.closeSoundActivity);
        close.setOnClickListener(l->closeSoundActivity());
        return view;
    }

    public void closeSoundActivity(){

    }

    public void play(int buttonID){
        if(buttonID == R.id.oceanSounds){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=bn9F19Hi1Lk")));
        }else if(buttonID == R.id.rainforestSounds){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=ubNfkpbxXUs")));
        }else{
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=2G8LAiHSCAs")));
        }
    }
}