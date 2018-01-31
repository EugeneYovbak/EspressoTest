package com.boost.espressotest.presentation.screen.main.view;


import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.AutoCompleteTextView;

import com.boost.espressotest.R;
import com.boost.espressotest.presentation.screen.detail.view.DetailActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.boost.espressotest.matcher.RecyclerViewMatcher.atPosition;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static final int FIRST_ITEM = 0;
    private static final int ITEM_TO_SCROLL = 27;

    @Rule
    public IntentsTestRule<MainActivity> mMainActivityIntentRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void scrollToItem_verifyItemDisplayed() {
        onView(withId(R.id.rv_products)).perform(scrollToPosition(ITEM_TO_SCROLL));
        String name = mMainActivityIntentRule.getActivity().getResources().getString(R.string.mock_name) + String.valueOf(ITEM_TO_SCROLL);
        onView(withText(name)).check(matches(isDisplayed()));

        onView(withId(R.id.rv_products)).perform(scrollToPosition(ITEM_TO_SCROLL / 2));
        String name2 = mMainActivityIntentRule.getActivity().getResources().getString(R.string.mock_name) + String.valueOf(ITEM_TO_SCROLL / 2);
        onView(withText(name2)).check(matches(isDisplayed()));
    }

    @Test
    public void searchItemByName_verifyItemIsOnTopAndSingle() {
        onView(withId(R.id.sv_search)).perform(click());
        onView(isAssignableFrom(AutoCompleteTextView.class)).perform(typeText(String.valueOf(ITEM_TO_SCROLL)));

        String name = mMainActivityIntentRule.getActivity().getResources().getString(R.string.mock_name) + String.valueOf(ITEM_TO_SCROLL);

        onView(withText(String.valueOf(ITEM_TO_SCROLL - 1))).check(doesNotExist());
        onView(withId(R.id.rv_products)).check(matches(atPosition(FIRST_ITEM, hasDescendant(withText(name)))));
    }

    @Test
    public void searchExampleText_verifyAnyItemIsDisplayed() {
        onView(withId(R.id.sv_search)).perform(click());
        onView(isAssignableFrom(AutoCompleteTextView.class)).perform(typeText("test empty search"));
        onView(withId(R.id.rv_products)).check(matches(not(atPosition(FIRST_ITEM, hasDescendant(withId(R.id.tv_product_title))))));
    }

    @Test
    public void navigateToDetailedScreen_verifyNavigation() {
        String name = mMainActivityIntentRule.getActivity().getResources().getString(R.string.mock_name) + String.valueOf(FIRST_ITEM);

        onView(withText(name)).perform(click());

        intending(hasExtra(DetailActivity.ARG_PRODUCT_ID, FIRST_ITEM));
    }
}