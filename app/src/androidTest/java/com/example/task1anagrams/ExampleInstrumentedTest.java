package com.example.task1anagrams;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import android.view.Gravity;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.contrib.DrawerActions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        onView(withId(R.id.home_et_typeAWord)).perform(typeText("Foxminded cool 24/7"));
        onView(withId(R.id.home_btn_convert)).perform(click());
        onView(withId(R.id.home_tv_outputText)).check(matches(withText("dednimxoF looc 7/42")));
    }

    @Test
    public void testWithIgnoreNumbers() {
        onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open(Gravity.LEFT));
        onView(withId(R.id.nav_settings)).perform(click());
        onView(withId(R.id.setting_et_ignoredLetters)).perform(typeText("123456789!/"));
        onView(withId(R.id.setting_btn_save)).perform(click());
        onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open(Gravity.LEFT));
        onView(withId(R.id.nav_home)).perform(click());

        onView(withId(R.id.home_et_typeAWord)).perform(typeText("Foxminded cool 24/7"));
        onView(withId(R.id.home_btn_convert)).perform(click());
        Espresso.pressBack();
        onView(withId(R.id.home_tv_outputText)).check(matches(withText("dednimxoF looc 24/7")));

        onView(withId(R.id.home_et_typeAWord)).perform(clearText());
        onView(withId(R.id.home_et_typeAWord)).perform(typeText("abcd efgh"));
        Espresso.pressBack();
        onView(withId(R.id.home_btn_convert)).perform(click());
        onView(withId(R.id.home_tv_outputText)).check(matches(withText("dcba hgfe")));
    }
}
