package me.monster.toolbarhelper;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import me.monster.toolbarhelper.activity.NavigationActivity;

/**
 * @description
 * @author: Created jiangjiwei in 2020-01-06 14:32
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class RobolectricUnit {

    private static final String TAG = "RobolectricUnit";

    @Before
    public void setLog() {
        ShadowLog.stream = System.out;
    }

    @Test
    public void test() {
        ActivityScenario<NavigationActivity> activityScenario = ActivityScenario.launch(NavigationActivity.class)
                .moveToState(Lifecycle.State.RESUMED);
        activityScenario.onActivity(new ActivityScenario.ActivityAction<NavigationActivity>() {
            @Override
            public void perform(NavigationActivity activity) {
                FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
                Fragment primaryNavigationFragment = supportFragmentManager.getPrimaryNavigationFragment();
                if (primaryNavigationFragment != null) {
                    System.out.println("Primary Fragment is " + primaryNavigationFragment.getClass().getSimpleName());
                }
            }
        });
    }
}
