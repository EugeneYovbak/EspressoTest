package com.boost.espressotest;

import android.support.test.espresso.intent.rule.IntentsTestRule;

import com.boost.espressotest.screen.main.view.MainActivity;
import com.boost.espressotest.screen.second.view.SecondActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

public class IntentEspressoTest {

    @Rule
    public IntentsTestRule<MainActivity> mIntentRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void triggerIntentTest() {
        onView(withId(R.id.btn_example)).perform(click());
        onView(withId(R.id.btn_switch)).perform(click());
        intended(allOf(
                hasExtra(SecondActivity.ARG_INPUT, "Sample Text"),
                toPackage("com.boost.espressotest")));
    }
}
