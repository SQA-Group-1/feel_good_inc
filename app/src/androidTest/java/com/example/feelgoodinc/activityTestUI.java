package com.example.feelgoodinc;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
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
public class activityTestUI {
    //this test checks all components of initial activity page are loaded in correctly

    @Rule
    public ActivityScenarioRule<HomePage> mActivityScenarioRule =
            new ActivityScenarioRule<>(HomePage.class);

    @Test
    public void activityTestUI() {
        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.activityButton), withContentDescription("Activities"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavigationView),
                                        0),
                                0),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());
/*
        ViewInteraction relativeLayout = onView(
                allOf(withParent(allOf(withId(R.id.recyclerView),
                                withParent(withId(R.id.activityFrameLayout)))),
                        isDisplayed()));
        relativeLayout.check(matches(isDisplayed()));
*/
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

        ViewInteraction textView = onView(
                allOf(withId(R.id.activityTitle), withText("Grounding"),
                        withParent(withParent(withId(R.id.recyclerView))),
                        isDisplayed()));
        textView.check(matches(withText("Grounding")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.description), withText("Calm down with our meditation routines"),
                        withParent(withParent(withId(R.id.recyclerView))),
                        isDisplayed()));
        textView2.check(matches(withText("Calm down with our meditation routines")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.timeLeft), withText("20 minutes"),
                        withParent(withParent(withId(R.id.recyclerView))),
                        isDisplayed()));
        textView3.check(matches(withText("20 minutes")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.activityTitle), withText("Breathing Exercises"),
                        withParent(withParent(withId(R.id.recyclerView))),
                        isDisplayed()));
        textView4.check(matches(withText("Breathing Exercises")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.description), withText("Slow your heart rate with our breathing exercises"),
                        withParent(withParent(withId(R.id.recyclerView))),
                        isDisplayed()));
        textView5.check(matches(withText("Slow your heart rate with our breathing exercises")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.timeLeft), withText("5 minutes"),
                        withParent(withParent(withId(R.id.recyclerView))),
                        isDisplayed()));
        textView6.check(matches(withText("5 minutes")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.activityTitle), withText("Sleeping Aid"),
                        withParent(withParent(withId(R.id.recyclerView))),
                        isDisplayed()));
        textView7.check(matches(withText("Sleeping Aid")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.description), withText("Listen to calming nature sounds to help sleep"),
                        withParent(withParent(withId(R.id.recyclerView))),
                        isDisplayed()));
        textView8.check(matches(withText("Listen to calming nature sounds to help sleep")));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.timeLeft), withText("60 minutes"),
                        withParent(withParent(withId(R.id.recyclerView))),
                        isDisplayed()));
        textView9.check(matches(withText("60 minutes")));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.activityPicture), withContentDescription("Activity Image"),
                        withParent(withParent(withId(R.id.recyclerView))),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.activityPicture), withContentDescription("Activity Image"),
                        withParent(withParent(withId(R.id.recyclerView))),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));

        ViewInteraction imageView3 = onView(
                allOf(withId(R.id.activityPicture), withContentDescription("Activity Image"),
                        withParent(withParent(withId(R.id.recyclerView))),
                        isDisplayed()));
        imageView3.check(matches(isDisplayed()));
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
