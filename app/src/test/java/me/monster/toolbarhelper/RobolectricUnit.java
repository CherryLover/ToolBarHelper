package me.monster.toolbarhelper;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

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
import me.monster.toolbarhelper.fragment.ListFragment;

import static org.junit.Assert.assertEquals;

/**
 * @description
 * @author: Created jiangjiwei in 2020-01-06 14:32
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class RobolectricUnit {

    private static final String TAG = "RobolectricUnit";
    private ListFragment listFragment;
    private View rootView;
    private ListView lvMenus;

    @Before
    public void setLog() {
        ShadowLog.stream = System.out;
        launchListFragment();
    }

    private void launchListFragment() {
        ActivityScenario<NavigationActivity> activityScenario = ActivityScenario.launch(NavigationActivity.class)
                .moveToState(Lifecycle.State.RESUMED);
        activityScenario.onActivity(new ActivityScenario.ActivityAction<NavigationActivity>() {
            @Override
            public void perform(NavigationActivity activity) {
                FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
                Fragment primaryNavigationFragment = supportFragmentManager.getPrimaryNavigationFragment();
                if (primaryNavigationFragment != null) {
                    if (primaryNavigationFragment.getClass().getSimpleName().equals("NavHostFragment")) {
                        FragmentManager childFragmentManager = primaryNavigationFragment.getChildFragmentManager();
                        Fragment childPrimaryFrag = childFragmentManager.getPrimaryNavigationFragment();
                        if (childPrimaryFrag != null) {
                            if (childPrimaryFrag.getClass().getSimpleName().equals("ListFragment")) {
                                listFragment = (ListFragment) childPrimaryFrag;
                                rootView = listFragment.requireView();
                                lvMenus = childPrimaryFrag.requireView().findViewById(R.id.lv_list_menu);
                            }
                        }
                    }
                }
            }
        });
    }

    @Test
    public void test_menu_0_change_title() {
        lvMenus.performItemClick(lvMenus, 0, 0);
        TextView tvTitle = rootView.findViewById(R.id.tv_tool_title);
        tvTitle.getText();
        assertEquals(tvTitle.getText(), getString(R.string.title_list_after_click));
    }

    @Test
    public void test_menu_1_change_menu() {
        lvMenus.performItemClick(lvMenus, 1, 0);
        TextView tvMenu = rootView.findViewById(R.id.tv_tool_menu);
        assertEquals(tvMenu.getText(), getString(R.string.menu_list_after_click));
    }

    private String getString(int id) {
        return listFragment.getString(id);
    }
}
