package com.example.feelgoodinc;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;

import android.content.Intent;
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
public class SignupActivityTest {
    private SignupActivity signupActivityMock;

    @Mock
    private UserService userServiceMock;

    @Before
    public void setUp() {
        //mock signup and intent activities
        MockitoAnnotations.openMocks(this);
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), SignupActivity.class);
        signupActivityMock = Robolectric.buildActivity(SignupActivity.class, intent).create().get();
    }

    @After
    public void tearDown() {
        signupActivityMock.finish();
    }

    @Test
    public void registerUser_Successful() {
        EditText emailEditText = signupActivityMock.findViewById(R.id.username);
        EditText passwordEditText = signupActivityMock.findViewById(R.id.password);

        String userEmail = "test@example.com";
        String userPassword = "correctPassword";

        emailEditText.setText(userEmail);
        passwordEditText.setText(userPassword);

        User mockUser = Mockito.mock(User.class);
        //mock service callback
        doAnswer(invocation -> {
            UserService.UserCallback callback = invocation.getArgument(2);
            callback.onSuccess(mockUser);
            return null;
        }).when(userServiceMock).registerUser(eq(userEmail), eq(userPassword), any());

        //log in
        signupActivityMock.registerNewUser();
        assertNotNull(mockUser);
    }

    @Test
    public void registerUser_EmailFailure(){
        //mock service callback
        doAnswer(invocation -> {
            UserService.UserCallback callback = invocation.getArgument(2);
            callback.onError(new Exception("Registration failed"));
            return null;
        }).when(userServiceMock).registerUser(Mockito.anyString(), Mockito.anyString(), any());

        //signup
        signupActivityMock.registerNewUser();

        //update UI
        Shadows.shadowOf(signupActivityMock.getMainLooper()).idle();

        //verify empty email toast message
        String expectedToastMessage = "Please enter your email";
        assertEquals(expectedToastMessage, ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void registerUser_PasswordFailure() {
        //set up user input
        String userEmail = "test@example.com";
        String userPassword = "";

        EditText emailEditText = signupActivityMock.findViewById(R.id.username);
        EditText passwordEditText = signupActivityMock.findViewById(R.id.password);

        emailEditText.setText(userEmail);
        passwordEditText.setText(userPassword);

        //mock service callback
        doAnswer(invocation -> {
            UserService.UserCallback callback = invocation.getArgument(2);
            callback.onError(new Exception("Registration failed"));
            return null;
        }).when(userServiceMock).registerUser(Mockito.anyString(), Mockito.anyString(), any());

        //sign up
        signupActivityMock.registerNewUser();

        //update UI
        Shadows.shadowOf(signupActivityMock.getMainLooper()).idle();

        //verify empty password toast message
        String expectedToastMessage = "Please enter your password";
        assertEquals(expectedToastMessage, ShadowToast.getTextOfLatestToast());
    }
}