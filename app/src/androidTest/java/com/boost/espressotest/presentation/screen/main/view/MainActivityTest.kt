package com.boost.espressotest.presentation.screen.main.view


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import android.support.test.espresso.intent.Intents.intending
import android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.AutoCompleteTextView
import com.boost.espressotest.R
import com.boost.espressotest.matcher.RecyclerViewMatcher.atPosition
import com.boost.espressotest.presentation.screen.detail.view.DetailActivity
import org.hamcrest.core.IsNot.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    companion object {
        private const val FIRST_ITEM = 0
        private const val ITEM_TO_SCROLL = 27
    }

    @Rule
    var mMainActivityIntentRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun openScreen_verifyTitleIsCorrect() {
        val title = mMainActivityIntentRule.activity.resources.getString(R.string.main_title)
        onView(withId(R.id.tv_title)).check(matches(withText(title)))
    }

    @Test
    fun scrollToItem_verifyItemDisplayed() {
        onView(withId(R.id.rv_products)).perform(scrollToPosition<RecyclerView.ViewHolder>(ITEM_TO_SCROLL))
        val name = mMainActivityIntentRule.activity.resources.getString(R.string.mock_name) + ITEM_TO_SCROLL.toString()
        onView(withText(name)).check(matches(isDisplayed()))

        onView(withId(R.id.rv_products)).perform(scrollToPosition<RecyclerView.ViewHolder>(ITEM_TO_SCROLL / 2))
        val name2 = mMainActivityIntentRule.activity.resources.getString(R.string.mock_name) + (ITEM_TO_SCROLL / 2).toString()
        onView(withText(name2)).check(matches(isDisplayed()))
    }

    @Test
    fun searchItemByName_verifyItemIsOnTopAndSingle() {
        onView(withId(R.id.sv_search)).perform(click())
        onView(isAssignableFrom(AutoCompleteTextView::class.java)).perform(typeText(ITEM_TO_SCROLL.toString()))

        val name = mMainActivityIntentRule.activity.resources.getString(R.string.mock_name) + ITEM_TO_SCROLL.toString()

        onView(withText((ITEM_TO_SCROLL - 1).toString())).check(doesNotExist())
        onView(withId(R.id.rv_products)).check(matches(atPosition(FIRST_ITEM, hasDescendant(withText(name)))))
    }

    @Test
    fun searchExampleText_verifyAnyItemIsDisplayed() {
        onView(withId(R.id.sv_search)).perform(click())
        onView(isAssignableFrom(AutoCompleteTextView::class.java)).perform(typeText("test empty search"))
        onView(withId(R.id.rv_products)).check(matches(not<View>(atPosition(FIRST_ITEM, hasDescendant(withId(R.id.tv_product_title))))))
    }

    @Test
    fun navigateToDetailedScreen_verifyNavigation() {
        val name = mMainActivityIntentRule.activity.resources.getString(R.string.mock_name) + FIRST_ITEM.toString()

        onView(withText(name)).perform(click())

        intending(hasExtra(DetailActivity.ARG_PRODUCT_ID, FIRST_ITEM))
    }
}