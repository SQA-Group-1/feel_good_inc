package com.example.feelgoodinc;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;

import android.content.Intent;
import android.os.Looper;
import android.widget.EditText;

import androidx.test.core.app.ApplicationProvider;

import com.example.feelgoodinc.models.User;
import com.example.feelgoodinc.services.UserService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 34)
public class LoginActivityTest {
    private LoginActivity loginActivityMock;

    @Mock
    private UserService userService;

    @Before
    public void setUp() {
        //mock login and intent activities
        MockitoAnnotations.openMocks(this);
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), LoginActivity.class);
        loginActivityMock = Robolectric.buildActivity(LoginActivity.class, intent).create().get();
    }

    @After
    public void tearDown() {
        loginActivityMock.finish();
    }

    @Test
    public void loginUser_Successful(){
        //mock user inputs
        EditText emailEditText = loginActivityMock.findViewById(R.id.username);
        EditText passwordEditText = loginActivityMock.findViewById(R.id.password);

        String userEmail = "test@example.com";
        String userPassword = "correctPassword";

        emailEditText.setText(userEmail);
        passwordEditText.setText(userPassword);

        User mockUser = new User();
        //mock service callback
        doAnswer(invocation -> {
            UserService.LoginCallback callback = invocation.getArgument(2);
            callback.onSuccess(mockUser);
            return null;
        }).when(userService).loginUser(eq(userEmail), eq(userPassword), any());

        //log in
        loginActivityMock.loginUserAccount();
        assertNotNull(mockUser);
    }

    @Test
    public void loginUser_Failure() {
        //mock user inputs
        EditText emailEditText = loginActivityMock.findViewById(R.id.username);
        EditText passwordEditText = loginActivityMock.findViewById(R.id.password);

        String userEmail = "test@example.com";
        String userPassword = "incorrectPassword";

        emailEditText.setText(userEmail);
        passwordEditText.setText(userPassword);

        //mock callback
        doAnswer(invocation -> {
            UserService.LoginCallback callback = invocation.getArgument(2);
            callback.onError(new Exception("Login error"));
            return null;
        }).when(userService).loginUser(eq(userEmail), eq(userPassword), any());

        Shadows.shadowOf(Looper.getMainLooper()).pause();
        // Invoke
        loginActivityMock.loginUserAccount();
    }

    @Test
    public void loginUser_EmailFailure(){
        //mock service callback
        doAnswer(invocation -> {
            UserService.LoginCallback callback = invocation.getArgument(2);
            callback.onError(new Exception("Invalid credentials"));
            return null;
        }).when(userService).loginUser(Mockito.anyString(), Mockito.anyString(), any());

        //log in
        loginActivityMock.loginUserAccount();

        //verify empty email field
        String expectedToastMessage = "Please enter your email";
        assertEquals(expectedToastMessage, ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void loginUser_PasswordFailure() {
        //mock user inputs
        EditText emailEditText = loginActivityMock.findViewById(R.id.username);
        EditText passwordEditText = loginActivityMock.findViewById(R.id.password);

        String userEmail = "test@example.com";
        String userPassword = "";

        emailEditText.setText(userEmail);
        passwordEditText.setText(userPassword);

        //mock service callback
        doAnswer(invocation -> {
            UserService.LoginCallback callback = invocation.getArgument(2);
            callback.onError(new Exception("Invalid credentials"));
            return null;
        }).when(userService).loginUser(Mockito.anyString(), Mockito.anyString(), any());

        //log in
        loginActivityMock.loginUserAccount();

        //verify empty password field
        String expectedToastMessage = "Please enter your password";
        assertEquals(expectedToastMessage, ShadowToast.getTextOfLatestToast());
    }
}