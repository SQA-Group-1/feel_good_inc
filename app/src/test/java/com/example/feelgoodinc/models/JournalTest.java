package com.example.feelgoodinc.models;

import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;

public class JournalTest {
	@Test
	public void Journal() {
		String journalName = "abc";
		Date createdWhen = null;
		Date lastEditedWhen = null;
		String content = "abc";
		Journal expected = new Journal("abc", null, null, "abc");
		Journal actual = new Journal(journalName, createdWhen, lastEditedWhen, content);

		assertEquals(expected, actual);
	}

	@Test
	public void getJournalName() {
		Journal j = new Journal("abc", null, null, "abc");
		String expected = "abc";
		String actual = j.getJournalName();

		assertEquals(expected, actual);
	}

	@Test
	public void setJournalName() {
		Journal j = new Journal("abc", null, null, "abc");
		String journalName = "abc";
		j.setJournalName(journalName);
	}

	@Test
	public void getCreatedWhen() {
		Journal j = new Journal("abc", null, null, "abc");
		Date expected = null;
		Date actual = j.getCreatedWhen();

		assertEquals(expected, actual);
	}

	@Test
	public void setCreatedWhen() {
		Journal j = new Journal("abc", null, null, "abc");
		Date createdWhen = null;
		j.setCreatedWhen(createdWhen);
	}

	@Test
	public void getLastEditedWhen() {
		Journal j = new Journal("abc", null, null, "abc");
		Date expected = null;
		Date actual = j.getLastEditedWhen();

		assertEquals(expected, actual);
	}

	@Test
	public void setLastEditedWhen() {
		Journal j = new Journal("abc", null, null, "abc");
		Date lastEditedWhen = null;
		j.setLastEditedWhen(lastEditedWhen);
	}

	@Test
	public void getContent() {
		Journal j = new Journal("abc", null, null, "abc");
		String expected = "abc";
		String actual = j.getContent();

		assertEquals(expected, actual);
	}

	@Test
	public void setContent() {
		Journal j = new Journal("abc", null, null, "abc");
		String content = "abc";
		j.setContent(content);
	}

	@Test
	public void toMap() {
		Journal j = new Journal("abc", null, null, "abc");
		Map<String, Object> expected = null;
		Map<String, Object> actual = j.toMap();

		assertEquals(expected, actual);
	}

	@Test
	public void fromMap() {
		Map<String, Object> map = null;
		Journal expected = new Journal("abc", null, null, "abc");
		Journal actual = Journal.fromMap(map);

		assertEquals(expected, actual);
	}
}
