package com.example.feelgoodinc;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText emailTextView, passwordTextView;

    /**
     * It initializes the UI components
     * Initialises Firebase Authentication
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Initialise Firebase Authentication
        mAuth = FirebaseAuth.getInstance();
        emailTextView = findViewById(R.id.username);
        passwordTextView = findViewById(R.id.password);
        Button btn = findViewById(R.id.login);
        TextView loginLink = findViewById(R.id.loginLink);

        loginLink.setOnClickListener(v -> {
            // redirect to Log In page
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            finish();
        });
        btn.setOnClickListener(v -> registerNewUser());
    }

    /**
     *  Check if user is signed in (non-null) and update UI
     *  If a user is signed in, it triggers a refresh of the user's data
     */
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    /**
     *  This method handles the registration process for the user
     *  It retrieves the email and password entered by the user
     */
    private void registerNewUser() {
        String email, password;
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter your email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter your password", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(getApplicationContext(),
                        "Registration successful", Toast.LENGTH_LONG).show();
                // if the user created intent to login activity
                Intent intent = new Intent(SignupActivity.this, HomePage.class);
                startActivity(intent);
                finish();
            } else {
                // Registration failed
                Toast.makeText(getApplicationContext(),
                        "Could not sign up", Toast.LENGTH_LONG).show();
            }
        });
    }
}
