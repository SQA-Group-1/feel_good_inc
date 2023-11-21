package com.example.feelgoodinc.models;

import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;

public class MoodTest {
	@Test
	public void Mood() {
		Mood.MoodType moodType = Mood.MoodType.AWFUL;
		Date moodWhen = null;
		Mood expected = new Mood(Mood.MoodType.AWFUL, null);
		Mood actual = new Mood(moodType, moodWhen);

		assertEquals(expected, actual);
	}

	@Test
	public void getMoodType() {
		Mood m = new Mood(Mood.MoodType.AWFUL, null);
		Mood.MoodType expected = Mood.MoodType.AWFUL;
		Mood.MoodType actual = m.getMoodType();

		assertEquals(expected, actual);
	}

	@Test
	public void setMoodType() {
		Mood m = new Mood(Mood.MoodType.AWFUL, null);
		Mood.MoodType moodType = Mood.MoodType.AWFUL;
		m.setMoodType(moodType);
	}

	@Test
	public void getMoodWhen() {
		Mood m = new Mood(Mood.MoodType.AWFUL, null);
		Date expected = null;
		Date actual = m.getMoodWhen();

		assertEquals(expected, actual);
	}

	@Test
	public void setMoodWhen() {
		Mood m = new Mood(Mood.MoodType.AWFUL, null);
		Date moodWhen = null;
		m.setMoodWhen(moodWhen);
	}

	@Test
	public void toMap() {
		Mood m = new Mood(Mood.MoodType.AWFUL, null);
		Map<String, Object> expected = null;
		Map<String, Object> actual = m.toMap();

		assertEquals(expected, actual);
	}

	@Test
	public void fromMap() {
		Map<String, Object> map = null;
		Mood expected = new Mood(Mood.MoodType.AWFUL, null);
		Mood actual = Mood.fromMap(map);

		assertEquals(expected, actual);
	}
}
