package com.boost.espressotest.presentation.screen.detail.view

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.boost.espressotest.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailActivityTest {

    companion object {
        private const val FIRST_ITEM = 0
    }

    @Rule @JvmField
    var mDetailActivityRule: ActivityTestRule<DetailActivity> = object : ActivityTestRule<DetailActivity>(DetailActivity::class.java) {
        override fun getActivityIntent(): Intent {
            val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
            val result = Intent(targetContext, DetailActivity::class.java)
            result.putExtra(DetailActivity.ARG_PRODUCT_ID, FIRST_ITEM)
            return result
        }
    }

    @Test
    fun openScreen_verifyTitleIsCorrect() {
        val title = mDetailActivityRule.activity.resources.getString(R.string.detail_title)
        onView(withId(R.id.tv_title)).check(matches(withText(title)))
    }

    @Test
    fun openDetailedScreen_verifyDetailedItemData() {
        val name = mDetailActivityRule.activity.resources.getString(R.string.mock_name) + FIRST_ITEM.toString()
        val price = (FIRST_ITEM * 100).toString()
        val producer = mDetailActivityRule.activity.resources.getString(R.string.mock_producer) + FIRST_ITEM.toString()

        onView(withId(R.id.tv_product_title)).check(matches(withText(name)))
        onView(withId(R.id.tv_product_price)).check(matches(withText(price)))
        onView(withId(R.id.tv_product_description)).check(matches(withText(producer)))
    }

    @Test
    fun clickBackButton_verifyBackNavigation() {
        onView(withId(R.id.iv_back)).perform(click())
    }

    @Test
    fun clickFavoriteButton_verifyNavigation() {
        onView(withId(R.id.iv_favorite)).perform(click())
    }
}
