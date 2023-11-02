package com.example.feelgoodinc.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.feelgoodinc.R;

public class UserProfileFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO: fetch user info from backend to display email + possibly name
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_profile, container, false);

        // Set up fragment button listeners
        Button changePasswordButton = v.findViewById(R.id.changePassword);
        changePasswordButton.setOnClickListener(l -> changePasswordClick(v));

        Button submitButton = v.findViewById(R.id.submitPasswordButton);
        submitButton.setOnClickListener(l -> submitNewPassword(v));



        return v;
    }

    /***
     * Toggle visibility of the change password form when button is clicked
     * @param v imports the view for view selection
     */
    public void changePasswordClick(View v){
        View passwordForm = v.findViewById(R.id.passwordForm);
        if (passwordForm.getVisibility() == View.INVISIBLE){
            passwordForm.setVisibility(View.VISIBLE);
        } else {
            passwordForm.setVisibility(View.INVISIBLE);
        }
    }

    public void submitNewPassword(View v){
        // TODO: Password requirements check
        // TODO: Update user password in backend

        Log.d("Feel Good Inc", "submit new password clicked");
    }


}