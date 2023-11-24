package com.example.feelgoodinc.services;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.Intent;
import android.os.IBinder;

import com.example.feelgoodinc.models.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class UserServiceTest {
    @InjectMocks
    private FirebaseAuth mockedFirebaseAuth;

    @InjectMocks
    private FirebaseUser mockedFirebaseUser;

    @InjectMocks
    private UserService userServiceMock;

    @Mock
    private UserService.UserCallback userCallback;

    @Before
    public void setup() {
        //set up mocks
        mockedFirebaseAuth = mock(FirebaseAuth.class);
        mockedFirebaseUser = mock(FirebaseUser.class);
        MockitoAnnotations.openMocks(this);

        //mock firebase auth
        when(mockedFirebaseAuth.getCurrentUser()).thenReturn(mockedFirebaseUser);

        //mock user data
        when(mockedFirebaseUser.getEmail()).thenReturn("test@example.com");
        when(mockedFirebaseUser.getUid()).thenReturn("aBcDefGH");

        //mock user
        userServiceMock = new UserService();
        userServiceMock.mAuth = mockedFirebaseAuth;
    }

    @Test
    public void getCurrentUserKey() {
        //mock service
        UserService userService = new UserService();
        userService.mAuth = mockedFirebaseAuth;

        //get logged in user
        User result = userService.getCurrentUser();

        //verify user
        verify(mockedFirebaseUser).reload();

        //asserts
        assertNotNull(result);
        assertEquals("aBcDefGH", result.getCurrentUserKey());
    }

    @Test
    public void getCurrentUser() {
        //mock service
        UserService userService = new UserService();
        userService.mAuth = mockedFirebaseAuth;

        //get logged in user
        User result = userService.getCurrentUser();

        //verify user
        verify(mockedFirebaseUser).reload();

        //asserts
        assertNotNull(result);
        assertEquals("test@example.com", result.getUsername());
        assertEquals("aBcDefGH", result.getCurrentUserKey());
    }

    @Test
    public void loginUser_Successful() {
        //user inputs
        String email = "test@example.com";
        String password = "password123";

        //mock signInWithEmailAndPassword
        FirebaseAuth mockedFirebaseAuth = mock(FirebaseAuth.class);
        FirebaseUser mockFirebaseUser = mock(FirebaseUser.class);
        when(mockedFirebaseAuth.getCurrentUser()).thenReturn(mockFirebaseUser);

        UserService userServiceMock = new UserService();
        userServiceMock.mAuth = mockedFirebaseAuth;

        @SuppressWarnings("unchecked")
        Task<AuthResult> mockTask = mock(Task.class);//keep it type-safe
        when(mockedFirebaseAuth.signInWithEmailAndPassword(eq(email), eq(password)))
                .thenReturn(mockTask);

        //log in user
        userServiceMock.loginUser(email, password, userCallback);

        //verify behaviour
        verify(mockedFirebaseAuth).signInWithEmailAndPassword(eq(email), eq(password));
        verify(userCallback, never()).onError(any());
    }

    @Test
    public void loginUser_Failed() {
        //mock user inputs
        String email = "test@example.com";
        String password = "incorrectPassword";

        //mock signInWithEmailAndPassword failure
        Exception mockException = mock(Exception.class);
        Task<AuthResult> failedTask = createFailedTask(mockException);//keep it type-safe
        when(mockedFirebaseAuth.signInWithEmailAndPassword(eq(email), eq(password)))
                .thenReturn(failedTask);

        //log in user
        userServiceMock.loginUser(email, password, userCallback);
    }

    @Test
    public void registerUser_Successful() {
        //mock user inputs
        String email = "test@example.com";
        String password = "password123";

        //set up mAuth mock
        FirebaseUser mockFirebaseUser = mock(FirebaseUser.class);
        userServiceMock.mAuth = mockedFirebaseAuth;

        when(mockedFirebaseAuth.getCurrentUser()).thenReturn(mockFirebaseUser);
        when(mockFirebaseUser.getUid()).thenReturn("mockUserId");

        //mock createUserWithEmailAndPassword
        @SuppressWarnings("unchecked")
        Task<AuthResult> mockTask = mock(Task.class); //keep it type-safe
        when(mockedFirebaseAuth.createUserWithEmailAndPassword(eq(email), eq(password)))
                .thenReturn(mockTask);

        //register user
        userServiceMock.registerUser(email, password, userCallback);

        //verify behaviour
        verify(mockedFirebaseAuth).createUserWithEmailAndPassword(eq(email), eq(password));
        verify(userCallback, never()).onError(any());
    }

    @Test
    public void registerUser_Failed() {
        //mock user inputs
        String email = "test@example.com";
        String password = "invalidPassword";

        //mock createUserWithEmailAndPassword
        Exception mockException = mock(Exception.class);
        Task<AuthResult> failedTask = createFailedTask(mockException);//keep it type-safe
        when(mockedFirebaseAuth.createUserWithEmailAndPassword(eq(email), eq(password)))
                .thenReturn(failedTask);

        //register user
        userServiceMock.registerUser(email, password, userCallback);
  }

    @Test
    public void onBind() {
        UserService userServiceMock = new UserService();
        Intent serviceIntent = new Intent();
        IBinder binder = userServiceMock.onBind(serviceIntent);

        //make sure binder is an instance of LocalBinder
        assertNotNull(binder);
        assertEquals(UserService.LocalBinder.class, binder.getClass());
    }

    @SuppressWarnings("unchecked")
    private static <T> Task<T> createFailedTask(Exception exception) {
        Task<T> failedTask = mock(Task.class);
        when(failedTask.isSuccessful()).thenReturn(false);
        when(failedTask.getException()).thenReturn(exception);
        return failedTask;
    }
}