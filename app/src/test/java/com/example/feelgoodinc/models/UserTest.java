package com.example.feelgoodinc.models;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;

public class UserTest {
	@Test
	public void getUsername() {
		User u = new User();
		String expected = "abc";
		String actual = u.getUsername();

		assertEquals(expected, actual);
	}

	@Test
	public void setUsername() {
		User u = new User();
		String username = "abc";
		u.setUsername(username);
	}

	@Test
	public void getLastLoginWhen() {
		User u = new User();
		Date expected = null;
		Date actual = u.getLastLoginWhen();

		assertEquals(expected, actual);
	}

	@Test
	public void setLastLoginWhen() {
		User u = new User();
		Date lastLoginWhen = null;
		u.setLastLoginWhen(lastLoginWhen);
	}

	@Test
	public void getCurrentUserKey() {
		User u = new User();
		String expected = "abc";
		String actual = u.getCurrentUserKey();

		assertEquals(expected, actual);
	}

	@Test
	public void setCurrentUserKey() {
		User u = new User();
		String key = "abc";
		u.setCurrentUserKey(key);
	}
}
