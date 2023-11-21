package com.example.feelgoodinc;

import android.os.Bundle;
import org.junit.Test;
import static org.junit.Assert.*;

public class TutorialActivityTest {
	@Test
	public void onCreate() {
		TutorialActivity t = new TutorialActivity();
		Bundle savedInstanceState = null;
		t.onCreate(savedInstanceState);
	}
}
