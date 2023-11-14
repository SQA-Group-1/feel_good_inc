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
public class resourceTestContactUI {

    /*

     README 14/11/23

     - resources not complete - all placeholders
     - implement full ui testing later on
     - at current stage of development this test represents all four resource types

     */
    @Rule
    public ActivityScenarioRule<HomePage> mActivityScenarioRule =
            new ActivityScenarioRule<>(HomePage.class);

    @Test
    public void resourceTestContactUI() {
        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.resourcesButton), withContentDescription("Resources"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavigationView),
                                        0),
                                3),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.button7), withText("Emergency Contacts"),
                        childAtPosition(
                                allOf(withId(R.id.frameLayout2),
                                        childAtPosition(
                                                withId(R.id.flFragment),
                                                0)),
                                1),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction button = onView(
                allOf(withId(R.id.button7), withText("Sample"),
                        withParent(allOf(withId(R.id.placeholderResources),
                                withParent(withId(R.id.frameLayout2)))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.button8), withText("Sample"),
                        withParent(allOf(withId(R.id.placeholderResources),
                                withParent(withId(R.id.frameLayout2)))),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction button3 = onView(
                allOf(withId(R.id.button9), withText("Sample"),
                        withParent(allOf(withId(R.id.placeholderResources),
                                withParent(withId(R.id.frameLayout2)))),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction button4 = onView(
                allOf(withId(R.id.button10), withText("Sample"),
                        withParent(allOf(withId(R.id.placeholderResources),
                                withParent(withId(R.id.frameLayout2)))),
                        isDisplayed()));
        button4.check(matches(isDisplayed()));

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTextText11), withText("PLACEHOLDER"),
                        childAtPosition(
                                allOf(withId(R.id.placeholderResources),
                                        childAtPosition(
                                                withId(R.id.frameLayout2),
                                                5)),
                                5),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("contact"));

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editTextText11), withText("contact"),
                        childAtPosition(
                                allOf(withId(R.id.placeholderResources),
                                        childAtPosition(
                                                withId(R.id.frameLayout2),
                                                5)),
                                5),
                        isDisplayed()));
        appCompatEditText2.perform(closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.button7), withText("Sample"),
                        childAtPosition(
                                allOf(withId(R.id.placeholderResources),
                                        childAtPosition(
                                                withId(R.id.frameLayout2),
                                                5)),
                                1),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.editTextText11), withText("contact"),
                        withParent(allOf(withId(R.id.placeholderResources),
                                withParent(withId(R.id.frameLayout2)))),
                        isDisplayed()));
        editText.check(matches(withText("contact")));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.editTextText11), withText("contact"),
                        withParent(allOf(withId(R.id.placeholderResources),
                                withParent(withId(R.id.frameLayout2)))),
                        isDisplayed()));
        editText2.check(matches(withText("contact")));
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
