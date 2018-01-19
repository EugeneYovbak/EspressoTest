package com.boost.espressotest;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.boost.espressotest.presentation.screen.main.view.MainActivity;

import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author PerSpiKyliaTor on 17.01.18.
 */

@RunWith(AndroidJUnit4.class)
public class ActivityEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityRule = new ActivityTestRule<>(MainActivity.class);
}
