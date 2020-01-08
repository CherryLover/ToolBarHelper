package me.monster.toolbarhelper;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Placeholder;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowDrawable;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.shadows.ShadowToast;

import me.monster.toolbarhelper.activity.NavigationActivity;
import me.monster.toolbarhelper.fragment.ListFragment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @description
 * @author: Created jiangjiwei in 2020-01-06 14:32
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class RobolectricUnit {

    private static final String TAG = "RobolectricUnit";
    private AppCompatActivity containerActivity;
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
                containerActivity = activity;
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
        TextView tvTitle = rootView.findViewById(R.id.tv_tool_title);
        assertEquals(tvTitle.getText(), getString(R.string.title_list_default));
        lvMenus.performItemClick(lvMenus, 0, 0);
        tvTitle.getText();
        assertEquals(tvTitle.getText(), getString(R.string.title_list_after_click));
        tvTitle.performClick();
        assertEquals(ShadowToast.getTextOfLatestToast(), getString(R.string.message_list_title));
    }

    @Test
    public void test_menu_1_change_menu() {
        lvMenus.performItemClick(lvMenus, 1, 0);
        TextView tvMenu = rootView.findViewById(R.id.tv_tool_menu);
        assertEquals(tvMenu.getText(), getString(R.string.menu_list_after_click));
        tvMenu.performClick();
        assertEquals(ShadowToast.getTextOfLatestToast(), getString(R.string.message_list_menu));
    }

    @Test
    public void test_menu_2_change_menu_img() {
        lvMenus.performItemClick(lvMenus, 2, 0);
        ImageView ivMenu = rootView.findViewById(R.id.img_tool_menu);
        ShadowDrawable shadowDrawable = Shadows.shadowOf(ivMenu.getDrawable());
        assertEquals(shadowDrawable.getCreatedFromResId(), R.drawable.ic_send_black_24dp);
        ivMenu.performClick();
        assertEquals(ShadowToast.getTextOfLatestToast(), getString(R.string.message_list_menu_img));
    }

    @Test
    public void test_menu_3_change_bg_res() {
        lvMenus.performItemClick(lvMenus, 3, 0);
        ConstraintLayout clRoot = rootView.findViewById(R.id.cl_tool_root);
        ShadowDrawable shadowDrawable = Shadows.shadowOf(clRoot.getBackground());
        assertEquals(shadowDrawable.getCreatedFromResId(), R.drawable.bg_color_gradient);
    }

    @Test
    public void test_menu_4_change_nav() {
        View viewById = rootView.findViewById(R.id.iv_tool_back);
        viewById.performClick();
        String textOfLatestToast = ShadowToast.getTextOfLatestToast();
        assertEquals(textOfLatestToast, getString(R.string.message_list_nav));
        lvMenus.performItemClick(lvMenus, 4, 0);
        viewById.performClick();
        boolean finished = containerActivity.isFinishing();
        System.out.println("container Activity is finished: " + finished);
        assertTrue(finished);
    }

    @Test
    public void test_menu_5_show_check() {
        Placeholder placeholder = rootView.findViewById(R.id.ph_tool_right);
        View content = placeholder.getContent();
        assertEquals(null, content);
        ConstraintLayout clRoot = rootView.findViewById(R.id.cl_tool_root);
        int clChild = clRoot.getChildCount();
        lvMenus.performItemClick(lvMenus, 5, 0);
        int nowCount = clRoot.getChildCount();
        assertEquals(clChild + 1, nowCount);
        assertEquals(true, clRoot.getChildAt(nowCount - 1) instanceof CheckBox);

        lvMenus.performItemClick(lvMenus, 6, 0);
        View goneContent = placeholder.getContent();
        assertEquals(true, goneContent instanceof View);
        int finalCount = clRoot.getChildCount();
        assertEquals(finalCount, nowCount);
    }

    private String getString(int id) {
        return listFragment.getString(id);
    }
}
