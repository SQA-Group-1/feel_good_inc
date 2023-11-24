package com.example.feelgoodinc.fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feelgoodinc.R;
import com.example.feelgoodinc.models.User;
import com.example.feelgoodinc.services.UserService;

public class UserProfileFragment extends Fragment {
    private UserService userService;
    private boolean isBound = false;

    /***
     * Create service connection for binding the service
     */
    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            UserService.LocalBinder binder = (UserService.LocalBinder) service;
            userService = binder.getService();
            isBound = true;

            User currentUser = userService.getCurrentUser();
            View v = getView();
            // display user's email
            if (v != null){
                TextView userEmail = getView().findViewById(R.id.userEmail);
                userEmail.setText(currentUser.getUsername());
            }


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    /***
     * set up onclick listeners for form buttons
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_profile, container, false);

        // Set up fragment button listeners
        Button changePasswordButton = v.findViewById(R.id.changePassword);
        changePasswordButton.setOnClickListener(l -> changePasswordClick(v));

        Button submitButton = v.findViewById(R.id.submitPasswordButton);
        submitButton.setOnClickListener(l -> submitNewPassword(v));

        return v;

    }

    /**
     *  Binds the service on start
     */
    @Override
    public void onStart() {
        super.onStart();

        // Bind to the service
        Intent intent = new Intent(getContext(), UserService.class);
        Context c = getContext();

        if (c != null){
            c.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        }
    }

    /***
     * Unbinds the service on stop
     */
    @Override
    public void onStop() {
        super.onStop();

        // Unbind from the service
        if (isBound){
            Context c = getContext();
            if (c != null){
                getContext().unbindService(serviceConnection);
                isBound = false;
            }
        }
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

    /***
     * Get old and new passwords entered by the user and pass them to the UserService, which
     * re-authenticates the user with the old password and if this passes, changes their password to
     * the new one
     * @param v view
     */
    public void submitNewPassword(View v){
        EditText oldPassword = v.findViewById(R.id.oldPassword);
        EditText newPassword = v.findViewById(R.id.newPassword);
        TextView passwordRequirements = v.findViewById(R.id.passwordRequirements);

        if (isBound){
            // change user's password
            userService.assignNewPassword(oldPassword.getText().toString(), newPassword.getText().toString(),
                    new UserService.UserCallback() {
                @Override
                public void onSuccess(User user) {
                    // if task succeeds
                    // hide form now that password has been changed
                    View passwordForm = v.findViewById(R.id.passwordForm);
                    passwordForm.setVisibility(View.INVISIBLE);
                    passwordRequirements.setVisibility(View.INVISIBLE);

                    Toast.makeText(getContext(),
                            "Password changed", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onError(Exception e) {
                    if (e instanceof IllegalArgumentException && e.getMessage() != null) {
                        // Password does not meet requirements
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        // show requirements
                        passwordRequirements.setVisibility(View.VISIBLE);

                    } else {
                        // Other errors
                        Log.d("COMP3013", "Error: " + e.toString());
                        Toast.makeText(getContext(), "Password could not be changed", Toast.LENGTH_LONG).show();
                    }
                }
            });

            // clear fields
            oldPassword.setText(null);
            newPassword.setText(null);

        }
    }
}