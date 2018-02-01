package com.boost.espressotest.presentation.screen.detail.view;


import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.boost.espressotest.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    private static final int FIRST_ITEM = 0;

    @Rule
    public ActivityTestRule<DetailActivity> mDetailActivityRule = new ActivityTestRule<DetailActivity>(DetailActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, DetailActivity.class);
            result.putExtra(DetailActivity.ARG_PRODUCT_ID, FIRST_ITEM);
            return result;
        }
    };

    @Test
    public void openScreen_verifyTitleIsCorrect() {
        String title = mDetailActivityRule.getActivity().getResources().getString(R.string.detail_title);
        onView(withId(R.id.tv_title)).check(matches(withText(title)));
    }

    @Test
    public void openDetailedScreen_verifyDetailedItemData() {
        String name = mDetailActivityRule.getActivity().getResources().getString(R.string.mock_name) + String.valueOf(FIRST_ITEM);
        String price = String.valueOf(FIRST_ITEM * 100);
        String producer = mDetailActivityRule.getActivity().getResources().getString(R.string.mock_producer) + String.valueOf(FIRST_ITEM);

        onView(withId(R.id.tv_product_title)).check(matches(withText(name)));
        onView(withId(R.id.tv_product_price)).check(matches(withText(price)));
        onView(withId(R.id.tv_product_description)).check(matches(withText(producer)));
    }

    @Test
    public void clickBackButton_verifyBackNavigation() {
        onView(withId(R.id.iv_back)).perform(click());
    }

    @Test
    public void clickFavoriteButton_verifyNavigation() {
        onView(withId(R.id.iv_favorite)).perform(click());
    }
}
