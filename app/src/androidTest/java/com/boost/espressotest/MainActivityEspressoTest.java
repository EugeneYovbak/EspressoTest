package com.boost.espressotest;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.boost.espressotest.screen.main.view.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

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
        onView(withId(R.id.et_input)).perform(typeText("Hello"), closeSoftKeyboard());
        onView(withId(R.id.btn_example)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.et_input)).check(matches(withText("Simple Text")));
    }

    @Test
    public void ensureTextChangeOnAnotherActivityWork() {
        // Type text and then press the button.
        onView(withId(R.id.et_input)).perform(typeText("New Text"), closeSoftKeyboard());
        onView(withId(R.id.btn_switch)).perform(click());

        // This view is in a different Activity, no need to tell Espresso.
        onView(withId(R.id.tv_result)).check(matches(withText("New Text")));
    }
}
