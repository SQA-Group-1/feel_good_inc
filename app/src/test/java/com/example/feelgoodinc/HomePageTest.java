package com.example.feelgoodinc;

import android.os.Bundle;
import org.junit.Test;
import static org.junit.Assert.*;
import android.view.MenuItem;

public class HomePageTest {
	@Test
	public void onCreate() {
		HomePage h = new HomePage();
		Bundle savedInstanceState = null;
		h.onCreate(savedInstanceState);
	}

	@Test
	public void onResume() {
		HomePage h = new HomePage();
		h.onResume();
	}

	@Test
	public void onNavigationItemSelected() {
		HomePage h = new HomePage();
		MenuItem item = null;
		boolean expected = true;
		boolean actual = h.onNavigationItemSelected(item);

		assertEquals(expected, actual);
	}

	@Test
	public void selectFragment() {
		HomePage h = new HomePage();
		int id = 123;
		h.selectFragment(id);
	}
}
