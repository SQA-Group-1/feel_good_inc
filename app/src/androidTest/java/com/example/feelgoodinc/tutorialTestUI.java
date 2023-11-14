package com.example.feelgoodinc;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class tutorialTestUI {
//tests tutorial page ui functionality

    @Rule
    public ActivityScenarioRule<HomePage> mActivityScenarioRule =
            new ActivityScenarioRule<>(HomePage.class);

    @Test
    public void tutorialTestUI() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.tutorialButton), withContentDescription("Tutorial"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.flFragment),
                                        0),
                                0),
                        isDisplayed()));
        appCompatImageButton.perform(click()); //click tutorial button

        ViewInteraction viewGroup = onView(
                allOf(withId(R.id.activity_tutorial),
                        withParent(allOf(withId(android.R.id.content),
                                withParent(withId(androidx.appcompat.R.id.action_bar_root)))),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed())); //check if tutorial page exists after button click

        ViewInteraction horizontalScrollView = onView(
                allOf(withId(R.id.tab_layout),
                        withParent(allOf(withId(R.id.activity_tutorial),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        horizontalScrollView.check(matches(isDisplayed())); //check the buttons to navigate the page exist

        ViewInteraction tabView = onView(
                allOf(childAtPosition(
                                childAtPosition(
                                        withId(R.id.tab_layout),
                                        0),
                                1),
                        isDisplayed()));
        tabView.perform(click()); //click to page 2

        ViewInteraction tabView2 = onView(
                allOf(childAtPosition(
                                childAtPosition(
                                        withId(R.id.tab_layout),
                                        0),
                                2),
                        isDisplayed()));
        tabView2.perform(click()); //click to page 3

        ViewInteraction tabView3 = onView(
                allOf(childAtPosition(
                                childAtPosition(
                                        withId(R.id.tab_layout),
                                        0),
                                3),
                        isDisplayed()));
        tabView3.perform(click()); //click to page 4

        ViewInteraction scrollView = onView(
                allOf(withId(R.id.scrollView2),
                        withParent(allOf(withId(R.id.frameLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class)))),
                        isDisplayed()));
        scrollView.check(matches(isDisplayed())); //check the scrollable page exists

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.acceptEulaButton), withText("Accept"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrollView2),
                                        0),
                                1)));
        materialButton.perform(scrollTo(), click()); //scroll down the page to the button then click it

        ViewInteraction frameLayout = onView(
                allOf(withId(R.id.flFragment),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        frameLayout.check(matches(isDisplayed())); //check if after you accept the EULA you are sent back to the main page
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
