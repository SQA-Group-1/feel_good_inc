package com.example.feelgoodinc;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class moodTest {

    @Rule
    public ActivityScenarioRule<HomePage> mActivityScenarioRule =
            new ActivityScenarioRule<>(HomePage.class);

    @Test
    public void activityTest() {
        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.moodButton), withContentDescription("Add mood"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavigationView),
                                        0),
                                2),
                        isDisplayed())); //check button exists after fragment invoked
        bottomNavigationItemView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.MoodTitle), withText("How are you feeling?"),
                        withParent(allOf(withId(R.id.frameLayout2),
                                withParent(withId(R.id.flFragment)))),
                        isDisplayed()));
        textView.check(matches(withText("How are you feeling?"))); //check default text exists


        ViewInteraction textView2 = onView(
                allOf(withId(R.id.date), withText("14/11/2023"), //need edit
                        withParent(allOf(withId(R.id.frameLayout2),
                                withParent(withId(R.id.flFragment)))),
                        isDisplayed())); //check date is displayed with correct date
        textView2.check(matches(isDisplayed()));

        ViewInteraction radioGroup = onView(
                allOf(withId(R.id.moodsGroup),
                        withParent(allOf(withId(R.id.frameLayout2),
                                withParent(withId(R.id.flFragment)))),
                        isDisplayed()));
        radioGroup.check(matches(isDisplayed())); //check moods are displayed to user

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.textView3), withText("Let's write about it"),
                        withParent(allOf(withId(R.id.frameLayout2),
                                withParent(withId(R.id.flFragment)))),
                        isDisplayed()));
        textView3.check(matches(withText("Let's write about it"))); //check default message exists

        ViewInteraction button = onView(
                allOf(withId(R.id.moodComfirmButton), withText("Log feeling"),
                        withParent(allOf(withId(R.id.frameLayout2),
                                withParent(withId(R.id.flFragment)))),
                        isDisplayed()));
        button.check(matches(isDisplayed())); //check button to log feelings exists

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.journalEntry),
                        childAtPosition(
                                allOf(withId(R.id.frameLayout2),
                                        childAtPosition(
                                                withId(R.id.flFragment),
                                                0)),
                                1),
                        isDisplayed()));
        textInputEditText.perform(replaceText("good"), closeSoftKeyboard()); //edit text entry

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.moodComfirmButton), withText("Log feeling"),
                        childAtPosition(
                                allOf(withId(R.id.frameLayout2),
                                        childAtPosition(
                                                withId(R.id.flFragment),
                                                0)),
                                3),
                        isDisplayed()));
        materialButton.perform(click()); //click to log the current mood

        ViewInteraction editText = onView(
                allOf(withId(R.id.journalEntry), withText("good"),
                        withParent(allOf(withId(R.id.frameLayout2),
                                withParent(withId(R.id.flFragment)))),
                        isDisplayed()));
        editText.check(matches(withText("good"))); //check once a mood has been input, the message is not lost
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
