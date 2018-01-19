package com.boost.espressotest;

import android.app.Activity;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;

import com.boost.espressotest.screen.main.view.MainActivity;
import com.boost.espressotest.screen.second.view.SecondActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.runner.lifecycle.Stage.RESUMED;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.StringStartsWith.startsWith;

/**
 * @author PerSpiKyliaTor on 17.01.18.
 */

@RunWith(AndroidJUnit4.class)
public class ActivityEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public ActivityTestRule<SecondActivity> mSecondActivityRule = new ActivityTestRule(SecondActivity.class, true, false);

    @Test
    public void ensureTextExampleWork() {
        // Type text and then press the button.
        onView(withId(R.id.et_input)).perform(typeText("Hello"), closeSoftKeyboard());
        onView(withId(R.id.btn_example)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.et_input)).check(matches(withText("Sample Text")));
    }

    @Test
    public void ensureTextChangeOnAnotherActivityWork() {
        // Type text and then press the button.
        onView(withId(R.id.et_input)).perform(typeText("New Text"), closeSoftKeyboard());
        onView(withId(R.id.btn_switch)).perform(click());

        // This view is in a different Activity, no need to tell Espresso.
        onView(withId(R.id.tv_result)).check(matches(withText("New Text")));
    }

    @Test
    public void demonstrateIntentPrep() {
        Intent intent = new Intent();
        intent.putExtra(SecondActivity.ARG_INPUT, "Test");
        mSecondActivityRule.launchActivity(intent);
        onView(withId(R.id.tv_result)).check(matches(withText("Test")));
    }

    @Test
    public void navigate() {
        onView(withText("Switch To Another Activity")).perform(click());
        Activity activity = getActivityInstance();
        boolean b = (activity instanceof SecondActivity);
        assertTrue(b);
    }

    public Activity getActivityInstance() {
        final Activity[] activity = new Activity[1];
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                Activity currentActivity = null;
                Collection resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(RESUMED);
                if (resumedActivities.iterator().hasNext()) {
                    currentActivity = (Activity) resumedActivities.iterator().next();
                    activity[0] = currentActivity;
                }
            }
        });
        return activity[0];
    }

    @Test
    public void checkToast() throws Exception {
        onView(withId(R.id.btn_toast)).perform(click());
        onView(withText(containsString("Toast"))).
                inRoot(withDecorView(not(is(mMainActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}
