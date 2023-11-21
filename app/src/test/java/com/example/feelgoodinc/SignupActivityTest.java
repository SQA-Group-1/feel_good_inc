package com.example.feelgoodinc;

import android.os.Bundle;
import org.junit.Test;
import static org.junit.Assert.*;

public class SignupActivityTest {
	@Test
	public void onCreate() {
		SignupActivity s = new SignupActivity();
		Bundle savedInstanceState = null;
		s.onCreate(savedInstanceState);
	}

	@Test
	public void onStart() {
		SignupActivity s = new SignupActivity();
		s.onStart();
	}

	@Test
	public void onStop() {
		SignupActivity s = new SignupActivity();
		s.onStop();
	}
}
