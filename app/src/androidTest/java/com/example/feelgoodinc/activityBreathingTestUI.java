package com.example.feelgoodinc;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class activityBreathingTestUI {
    //this tests the functionality of the breathing exercise ui

    @Rule
    public ActivityScenarioRule<HomePage> mActivityScenarioRule =
            new ActivityScenarioRule<>(HomePage.class);

    @Test
    public void activityBreathingTestUI() {
        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.activityButton), withContentDescription("Activities"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavigationView),
                                        0),
                                0),
                        isDisplayed()));
        bottomNavigationItemView.perform(click()); //click on activity page

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recyclerView),
                        childAtPosition(
                                withId(R.id.activityFrameLayout),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(1, click())); //click on breathing exercise


        ViewInteraction textView = onView(
                allOf(withId(R.id.progress_text), withText("Congratulations, you have finished this activity!"),
                        withParent(allOf(withId(R.id.progress_layout),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class)))),
                        isDisplayed()));
        textView.check(matches(withText("Congratulations, you have finished this activity!")));

        ViewInteraction button = onView(
                allOf(withId(R.id.breathActivityClose), withText("Close activity"),
                        withParent(withParent(withId(R.id.flFragment))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.breathActivityClose), withText("Close activity"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.flFragment),
                                        0),
                                2),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction relativeLayout = onView(
                allOf(withParent(allOf(withId(R.id.recyclerView),
                                withParent(withId(R.id.activityFrameLayout)))),
                        isDisplayed()));
        relativeLayout.check(matches(isDisplayed()));

        ViewInteraction relativeLayout2 = onView(
                allOf(withParent(allOf(withId(R.id.recyclerView),
                                withParent(withId(R.id.activityFrameLayout)))),
                        isDisplayed()));
        relativeLayout2.check(matches(isDisplayed()));

        ViewInteraction relativeLayout3 = onView(
                allOf(withParent(allOf(withId(R.id.recyclerView),
                                withParent(withId(R.id.activityFrameLayout)))),
                        isDisplayed()));
        relativeLayout3.check(matches(isDisplayed()));
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
