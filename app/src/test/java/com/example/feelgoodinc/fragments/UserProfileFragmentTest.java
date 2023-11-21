package com.example.feelgoodinc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserProfileFragmentTest {
	@Test
	public void onCreateView() {
		UserProfileFragment u = new UserProfileFragment();
		LayoutInflater inflater = null;
		ViewGroup container = null;
		Bundle savedInstanceState = null;
		View expected = null;
		View actual = u.onCreateView(inflater, container, savedInstanceState);

		assertEquals(expected, actual);
	}

	@Test
	public void onStart() {
		UserProfileFragment u = new UserProfileFragment();
		u.onStart();
	}

	@Test
	public void onStop() {
		UserProfileFragment u = new UserProfileFragment();
		u.onStop();
		assertEquals(u.isBound, false);
	}

	@Test
	public void changePasswordClick() {
		UserProfileFragment u = new UserProfileFragment();
		View v = null;
		u.changePasswordClick(v);
	}

	@Test
	public void submitNewPassword() {
		UserProfileFragment u = new UserProfileFragment();
		View v = null;
		u.submitNewPassword(v);
		assertEquals(u.oldPassword.getText(), null);
		assertEquals(u.newPassword.getText(), null);
	}
}
