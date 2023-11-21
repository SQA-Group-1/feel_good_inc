package com.example.feelgoodinc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.junit.Test;
import static org.junit.Assert.*;

public class HomeFragmentTest {
	@Test
	public void onCreateView() {
		HomeFragment h = new HomeFragment();
		LayoutInflater inflater = null;
		ViewGroup container = null;
		Bundle savedInstanceState = null;
		View expected = null;
		View actual = h.onCreateView(inflater, container, savedInstanceState);

		assertEquals(expected, actual);
	}

	@Test
	public void account() {
		HomeFragment h = new HomeFragment();
		h.account();
	}
}
