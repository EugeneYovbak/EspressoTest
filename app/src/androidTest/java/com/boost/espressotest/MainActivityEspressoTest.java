package com.boost.espressotest;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.boost.espressotest.screen.main.view.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author PerSpiKyliaTor on 17.01.18.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureTextExampleWork() {
        // Type text and then press the button.
        Espresso.onView(ViewMatchers.withId(R.id.et_input)).perform(ViewActions.typeText("Hello"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.btn_example)).perform(ViewActions.click());

        // Check that the text was changed.
        Espresso.onView(ViewMatchers.withId(R.id.et_input)).check(ViewAssertions.matches(ViewMatchers.withText("Simple Text")));
    }

    @Test
    public void ensureTextChangeOnAnotherActivityWork() {
        // Type text and then press the button.
        Espresso.onView(ViewMatchers.withId(R.id.et_input)).perform(ViewActions.typeText("New Text"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.btn_switch)).perform(ViewActions.click());

        // This view is in a different Activity, no need to tell Espresso.
        Espresso.onView(ViewMatchers.withId(R.id.tv_result)).check(ViewAssertions.matches(ViewMatchers.withText("New Text")));
    }
}
