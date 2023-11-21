package com.example.feelgoodinc.models;

import org.junit.Test;
import static org.junit.Assert.*;

public class ActivityTest {
	@Test
	public void Activity() {
		String title = "abc";
		String description = "abc";
		int estimatedTime = 123;
		int imageID = 123;
		Activity expected = new Activity("abc", "abc", 123, 123);
		Activity actual = new Activity(title, description, estimatedTime, imageID);

		assertEquals(expected, actual);
	}
}
