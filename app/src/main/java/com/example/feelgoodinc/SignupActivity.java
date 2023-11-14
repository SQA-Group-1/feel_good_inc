package com.example.feelgoodinc;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.feelgoodinc.models.User;
import com.example.feelgoodinc.services.UserService;

/***
 * description: creates sign up page and binds to UserService to access firebase data
 */
public class SignupActivity extends AppCompatActivity {
    private UserService userService;
    private boolean isBound = false;
    private EditText emailTextView, passwordTextView;

    /***
     * creates service connection for binding to UserService
     */
    private final ServiceConnection serviceConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            UserService.LocalBinder binder = (UserService.LocalBinder) service;
            userService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    /**
     * It initializes the UI components
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
     *  Binds the UserService to the sign up activity
     */
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, UserService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    /***
     * Unbinds the service when activity stops
     */
    @Override
    protected void onStop(){
        super.onStop();

        // Unbind from the service
        if (isBound){
            unbindService(serviceConnection);
            isBound = false;
        }
    }

    /**
     *  This method handles the registration process for the user by calling to the service
     *  It retrieves the email and password entered by the user
     *  A User object is returned
     *  Displays Toast messages to inform user of success/failure
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

        if (isBound){
            userService.registerUser(email, password, new UserService.FirebaseCallback() {
                @Override
                public void onSuccess(User user) {
                    Toast.makeText(getApplicationContext(),
                            "Registration successful", Toast.LENGTH_LONG).show();
                    // if the user created intent to login activity
                    Intent intent = new Intent(SignupActivity.this, HomePage.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onError(Exception e) {
                    // Registration failed
                    Toast.makeText(getApplicationContext(),
                            "Could not sign up", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
