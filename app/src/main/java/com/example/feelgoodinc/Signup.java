package com.example.feelgoodinc;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Signup extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText emailTextView, passwordTextView;
    private Button btn;

    //check to see if user is logged in
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        mAuth = FirebaseAuth.getInstance();
        emailTextView = findViewById(R.id.editTextTextEmailAddress);
        passwordTextView = findViewById(R.id.editTextTextPassword);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                registerNewUser();
            }
        });
    }

    private void registerNewUser() {
        String email, password;
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter your email",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter your password",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

//        //create user
//        mAuth
//                .createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task)
//                    {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(getApplicationContext(),
//                                            "Registration successful!",
//                                            Toast.LENGTH_LONG)
//                                    .show();
//
//                            // hide the progress bar
//                            progressBar.setVisibility(View.GONE);
//
//                            // if the user created intent to login activity
//                            Intent intent
//                                    = new Intent(Signup.this,
//                                    MainActivity.class);
//                            startActivity(intent);
//                        }
//                        else {
//
//                            // Registration failed
//                            Toast.makeText(
//                                            getApplicationContext(),
//                                            "Email or password incorrect",
//                                            Toast.LENGTH_LONG)
//                                    .show();
//                        }
//                    }
//                });
    }

}
