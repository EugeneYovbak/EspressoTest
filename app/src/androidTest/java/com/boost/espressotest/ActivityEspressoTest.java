package com.boost.espressotest;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.AutoCompleteTextView;

import com.boost.espressotest.presentation.screen.main.view.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.boost.espressotest.RecyclerViewMatcher.atPosition;
import static org.hamcrest.core.IsNot.not;

/**
 * @author PerSpiKyliaTor on 17.01.18.
 */

@RunWith(AndroidJUnit4.class)
public class ActivityEspressoTest {

    private static final int FIRST_ITEM = 0;
    private static final int ITEM_TO_SCROLL = 27;

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void scrollToItem_verifyItemDisplayed() {
        onView(withId(R.id.rv_products)).perform(scrollToPosition(ITEM_TO_SCROLL));
        String name = mMainActivityRule.getActivity().getResources().getString(R.string.mock_name) + String.valueOf(ITEM_TO_SCROLL);
        onView(withText(name)).check(matches(isDisplayed()));

        onView(withId(R.id.rv_products)).perform(scrollToPosition(ITEM_TO_SCROLL / 2));
        String name2 = mMainActivityRule.getActivity().getResources().getString(R.string.mock_name) + String.valueOf(ITEM_TO_SCROLL / 2);
        onView(withText(name2)).check(matches(isDisplayed()));
    }

    @Test
    public void searchItemByName_verifyItemIsOnTopAndSingle() {
        onView(withId(R.id.sv_search)).perform(click());
        onView(isAssignableFrom(AutoCompleteTextView.class)).perform(typeText(String.valueOf(ITEM_TO_SCROLL)));

        String name = mMainActivityRule.getActivity().getResources().getString(R.string.mock_name) + String.valueOf(ITEM_TO_SCROLL);

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
    public void navigateToDetailedScreen_verifyDetailedItemData() {
        String name = mMainActivityRule.getActivity().getResources().getString(R.string.mock_name) + String.valueOf(FIRST_ITEM);
        String price = String.valueOf(FIRST_ITEM * 100);
        String producer = mMainActivityRule.getActivity().getResources().getString(R.string.mock_producer) + String.valueOf(FIRST_ITEM);

        onView(withText(name)).perform(click());
        onView(withId(R.id.tv_product_title)).check(matches(withText(name)));
        onView(withId(R.id.tv_product_price)).check(matches(withText(price)));
        onView(withId(R.id.tv_product_description)).check(matches(withText(producer)));
    }

    @Test
    public void clickFavoriteButtonAndGoBack_verifyNavigationOnBack() {
        String name = mMainActivityRule.getActivity().getResources().getString(R.string.mock_name) + String.valueOf(FIRST_ITEM);
        onView(withText(name)).perform(click());

        onView(withId(R.id.iv_favorite)).perform(click());
        pressBack();
    }
}
