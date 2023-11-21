package com.example.feelgoodinc.fragments;

import org.junit.Test;
import static org.junit.Assert.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

public class ResourcesFragmentTest {
	@Test
	public void newInstance() {
		String param1 = "abc";
		String param2 = "abc";
		ResourcesFragment expected = new ResourcesFragment();
		ResourcesFragment actual = ResourcesFragment.newInstance(param1, param2);

		assertEquals(expected, actual);
	}

	@Test
	public void onCreate() {
		ResourcesFragment r = new ResourcesFragment();
		Bundle savedInstanceState = null;
		r.onCreate(savedInstanceState);
	}

	@Test
	public void onCreateView() {
		ResourcesFragment r = new ResourcesFragment();
		LayoutInflater inflater = null;
		ViewGroup container = null;
		Bundle savedInstanceState = null;
		View expected = null;
		View actual = r.onCreateView(inflater, container, savedInstanceState);

		assertEquals(expected, actual);
	}
}
