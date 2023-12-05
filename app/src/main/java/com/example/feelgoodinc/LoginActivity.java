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
 * description: creates log in page and binds to UserService to access firebase data
 */
public class LoginActivity extends AppCompatActivity {
    private UserService userService;
    private boolean isBound = false;
    private EditText emailTextView, passwordTextView;

    /***
     * Create service connection for binding the service
     */
    private final ServiceConnection serviceConnection = new ServiceConnection() {
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
        setContentView(R.layout.activity_login);


        emailTextView = findViewById(R.id.username);
        passwordTextView = findViewById(R.id.password);
        Button btn = findViewById(R.id.login);

        // set up navigation to create account through text link
        TextView createLink = findViewById(R.id.createLink);

        createLink.setOnClickListener(v -> {
            // redirect to sign up page
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            finish();
        });

        btn.setOnClickListener(v -> loginUserAccount());
    }



    /**
     *  Binds the service on start
     */
    @Override
    public void onStart() {
        super.onStart();

        // Bind to the service
        Intent intent = new Intent(this, UserService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    /***
     * Unbinds the service on stop
     */
    @Override
    public void onStop() {
        super.onStop();

        // Unbind from the service
        if (isBound){
            unbindService(serviceConnection);
            isBound = false;
        }
    }

    /**
     *  This method handles the login process for the user
     *  It retrieves the email and password entered by the user
     *  Service returns a User object
     *  Displays Toast messages to inform user of success/failure
     */
    void loginUserAccount() {

        String email = emailTextView.getText().toString();
        String password = passwordTextView.getText().toString();

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
            userService.loginUser(email, password, new UserService.LoginCallback() {
                @Override
                public void onSuccess(User user) {
                    // if task succeeds
                    Toast.makeText(getApplicationContext(),
                            "Logged in", Toast.LENGTH_LONG).show();
                    // if the user created intent to login activity
                    Intent intent = new Intent(LoginActivity.this, HomePage.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onError(Exception e) {
                    Toast.makeText(getApplicationContext(), "Could not log in",
                            Toast.LENGTH_LONG).show();
                }
            });
        }
    }

}
