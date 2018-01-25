package com.boost.espressotest;


import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.boost.espressotest.presentation.screen.detail.view.DetailActivity;
import com.boost.espressotest.presentation.screen.main.view.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

@RunWith(AndroidJUnit4.class)
public class IntentEspressoTest {

    private static final int FIRST_ITEM = 0;

    @Rule
    public IntentsTestRule<MainActivity> mMainActivityRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void checkDetailIntentAfterClick() {
        String name = mMainActivityRule.getActivity().getResources().getString(R.string.mock_name) + String.valueOf(FIRST_ITEM);
        onView(withText(name)).perform(click());

        intended(allOf(
                hasExtra(DetailActivity.ARG_PRODUCT_ID, (long) FIRST_ITEM),
                toPackage("com.boost.espressotest")));
    }

    @Test
    public void checkDetailIntentAfterResult() {
        String name = mMainActivityRule.getActivity().getResources().getString(R.string.mock_name) + String.valueOf(FIRST_ITEM);
        intending(allOf(
                hasExtra(DetailActivity.ARG_PRODUCT_ID, (long) FIRST_ITEM),
                toPackage("com.boost.espressotest")));

        onView(withText(name)).perform(click());
    }
}
