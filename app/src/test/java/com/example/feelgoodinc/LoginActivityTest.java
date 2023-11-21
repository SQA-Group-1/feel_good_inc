package com.example.feelgoodinc;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.*;
import android.os.Bundle;

public class LoginActivityTest {
	@Test
	public void onStop() {
		LoginActivity l = new LoginActivity();
		l.onStop();
		assertEquals(l.isBound, false);
	}

	@Test
	public void onStart() {
		LoginActivity l = new LoginActivity();
		l.onStart();
	}

	@Test //check email and password boxes exist
	public void onCreate() {
		LoginActivity l = new LoginActivity();
		Bundle savedInstanceState = null;
		l.onCreate(savedInstanceState);
		assertNotEquals(l.emailTextView, null);
		assertNotEquals(l.passwordTextView, null);
	}
	//class NEEDS getters to access private variables
}
