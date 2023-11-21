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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class accountTestUI {

//tests account page ui functionality
// currently fails as: no views in hierarchy found matching: view.GetId() is com/example/feelgoodinc:id/textPassword

    @Rule
    public ActivityScenarioRule<HomePage> mActivityScenarioRule =
            new ActivityScenarioRule<>(HomePage.class);

    @Test
    public void accountTestUI() {
        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.accountButton), withContentDescription("Account"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavigationView),
                                        0),
                                4),
                        isDisplayed()));
        bottomNavigationItemView.perform(click()); //click account page button

        ViewInteraction viewGroup = onView(
                allOf(withId(R.id.frameLayout2),
                        withParent(allOf(withId(R.id.flFragment),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed())); //check the view has changed after button pressed

        ViewInteraction textView = onView(
                allOf(withId(R.id.textView4), withText("Email:"),
                        withParent(allOf(withId(R.id.frameLayout2),
                                withParent(withId(R.id.flFragment)))),
                        isDisplayed()));
        textView.check(matches(withText("Email:"))); //check correct textView exists at correct position

        //note: this and next paragraph is default test setting (example email)
        ViewInteraction textView2 = onView(
                allOf(withId(R.id.userEmail), withText("exampleuser@feelgood.com"),
                        withParent(allOf(withId(R.id.frameLayout2),
                                withParent(withId(R.id.flFragment)))),
                        isDisplayed()));
        textView2.check(matches(withText("exampleuser@feelgood.com"))); //check correct textView exists at correct position

        ViewInteraction button = onView(
                allOf(withId(R.id.changePassword), withText("Change Password"),
                        withParent(allOf(withId(R.id.frameLayout2),
                                withParent(withId(R.id.flFragment)))),
                        isDisplayed()));
        button.check(matches(isDisplayed())); //check correct button exists with correct text at correct position

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.changePassword), withText("Change Password"),
                        childAtPosition(
                                allOf(withId(R.id.frameLayout2),
                                        childAtPosition(
                                                withId(R.id.flFragment),
                                                0)),
                                2),
                        isDisplayed()));
        materialButton.perform(click()); //click change password button

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.textView7), withText("Enter New Password:"),
                        withParent(allOf(withId(R.id.passwordForm),
                                withParent(withId(R.id.frameLayout2)))),
                        isDisplayed()));
        textView3.check(matches(withText("Enter New Password:"))); //check correct textView exists at correct position

        /*
        ViewInteraction editText = onView(
                allOf(withId(R.id.textPassword), withText("Enter password"),
                        withParent(allOf(withId(R.id.passwordForm),
                                withParent(withId(R.id.frameLayout2)))),
                        isDisplayed()));
        editText.check(matches(withText("Enter password"))); //check correct text field exists at correct position with correct default text
        */
        ViewInteraction button2 = onView(
                allOf(withId(R.id.submitPasswordButton), withText("Submit"),
                        withParent(allOf(withId(R.id.passwordForm),
                                withParent(withId(R.id.frameLayout2)))),
                        isDisplayed()));
        button2.check(matches(isDisplayed())); //check submit button exists in correct place with correct text

        /*
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.textPassword),
                        childAtPosition(
                                allOf(withId(R.id.passwordForm),
                                        childAtPosition(
                                                withId(R.id.frameLayout2),
                                                3)),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("password"), closeSoftKeyboard()); //edit change password default text
         */

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.submitPasswordButton), withText("Submit"),
                        childAtPosition(
                                allOf(withId(R.id.passwordForm),
                                        childAtPosition(
                                                withId(R.id.frameLayout2),
                                                3)),
                                2),
                        isDisplayed()));
        materialButton2.perform(click()); //click change password button

        /*
        ViewInteraction editText2 = onView(
                allOf(withId(R.id.textPassword), withText("��������"),
                        withParent(allOf(withId(R.id.passwordForm),
                                withParent(withId(R.id.frameLayout2)))),
                        isDisplayed()));
        editText2.check(matches(withText("��������"))); //test if password has been hidden once entered
         */

        ViewInteraction bottomNavigationItemView2 = onView(
                allOf(withId(R.id.homeButton), withContentDescription("Home"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavigationView),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView2.perform(click()); //return to main page

        ViewInteraction view = onView(
                allOf(withId(R.id.calendar),
                        withParent(withParent(withId(R.id.flFragment))),
                        isDisplayed()));
        view.check(matches(isDisplayed())); //check if calendar exists (home page)
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
