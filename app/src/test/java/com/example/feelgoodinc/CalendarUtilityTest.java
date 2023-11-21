package com.example.feelgoodinc;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalendarUtilityTest {
	@Test
	public void addDateColour() {
		CompactCalendarView c = null;
		String stringDate = "abc";
		int color = 123;
		CalendarUtility.addDateColour(c, stringDate, color);
	}

	@Test
	public void addDateColourWithData() {
		CompactCalendarView c = null;
		String stringDate = "abc";
		String eventDetails = "abc";
		int color = 123;
		CalendarUtility.addDateColourWithData(c, stringDate, eventDetails, color);
	}
}
