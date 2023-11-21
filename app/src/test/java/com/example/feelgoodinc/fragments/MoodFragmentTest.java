package com.example.feelgoodinc.fragments;

import org.junit.Test;
import static org.junit.Assert.*;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.View;
import com.example.feelgoodinc.models.Mood;

public class MoodFragmentTest {
	@Test
	public void onStop() {
		MoodFragment m = new MoodFragment();
		m.onStop();
	}

	@Test
	public void onStart() {
		MoodFragment m = new MoodFragment();
		m.onStart();
	}

	@Test
	public void onCreateView() {
		MoodFragment m = new MoodFragment();
		LayoutInflater inflater = null;
		ViewGroup container = null;
		Bundle savedInstanceState = null;
		View expected = null;
		View actual = m.onCreateView(inflater, container, savedInstanceState);

		assertEquals(expected, actual);
	}

	@Test
	public void convertTextToMoodType() {
		MoodFragment m = new MoodFragment();
		String buttonText = "Awful";
		Mood.MoodType expected = Mood.MoodType.AWFUL;
		Mood.MoodType actual = m.convertTextToMoodType(buttonText);

		assertEquals(expected, actual);
	}
}
