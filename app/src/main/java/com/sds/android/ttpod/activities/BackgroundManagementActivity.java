package com.sds.android.ttpod.activities;

import android.os.Bundle;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.ThemeManagementBaseActivity;
import com.sds.android.ttpod.adapter.SlidingTabFragmentPagerAdapter;
import com.sds.android.ttpod.component.soundsearch.EditModeToggle;
import com.sds.android.ttpod.fragment.skinmanager.BackgroundCategoryFragment;
import com.sds.android.ttpod.fragment.skinmanager.BackgroundRecommendFragment;
import com.sds.android.ttpod.fragment.skinmanager.MyBackgroundFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.p107a.ActionPage;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class BackgroundManagementActivity extends ThemeManagementBaseActivity implements EditModeToggle, ThemeManager.InterfaceC2019b {
    public static final int FRAGMENT_BACKGROUND_MORE = 1;
    public static final int FRAGMENT_BACKGROUND_MY = 2;
    public static final int FRAGMENT_BACKGROUND_RECOMMEND = 0;
    private static final ArrayList<ActionPage> SLIST = new ArrayList<ActionPage>(3) { // from class: com.sds.android.ttpod.activities.BackgroundManagementActivity.1
        {
            add(new ActionPage(SAction.ACTION_CLICK_BKG_RECOMMEND, SPage.PAGE_NICE_BACKGROUND));
            add(new ActionPage(SAction.ACTION_CLICK_BKG_CATEGORY, SPage.PAGE_BACKGROUND_CATEGORY));
            add(new ActionPage(SAction.ACTION_CLICK_BACKGROUND_MY_TAB, SPage.PAGE_MY_BACKGROUND));
        }
    };

    @Override // com.sds.android.ttpod.activities.base.ThemeManagementBaseActivity, com.sds.android.ttpod.activities.base.SlidingPagerActivity, com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(getTitleString());
    }



    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        refreshEditButton();
    }

    @Override // com.sds.android.ttpod.activities.base.SlidingPagerActivity
    protected void onBuildFragmentBinderList(List<SlidingTabFragmentPagerAdapter.C0998a> list) {
        list.add(new SlidingTabFragmentPagerAdapter.C0998a(0L, (int) R.string.recommend, 0, new BackgroundRecommendFragment()));
        if (Preferences.m2910bs()) {
            list.add(new SlidingTabFragmentPagerAdapter.C0998a(1L, (int) R.string.category_tab, 0, new BackgroundCategoryFragment()));
        }
        list.add(new SlidingTabFragmentPagerAdapter.C0998a(2L, (int) R.string.my, 0, new MyBackgroundFragment()));
    }

    @Override // com.sds.android.ttpod.component.soundsearch.EditModeToggle
    public void toggleEditMode() {
        EditModeToggle editModeToggle = getEditModeToggle();
        if (editModeToggle != null) {
            this.mInEditMode = !this.mInEditMode;
            showEditActionItem();
            editModeToggle.toggleEditMode();
        }
    }

    @Override // com.sds.android.ttpod.activities.base.ThemeManagementBaseActivity
    protected void clickStatistic(int i) {
        switch (i) {
            case 0:
                //ThemeStatistic.m4871u();
                break;
            case 1:
                //ThemeStatistic.m4872t();
                break;
            case 2:
                //ThemeStatistic.m4870v();
                break;
        }
        int i2 = this.mCurrentPage;
        this.mCurrentPage = i;
        //SUserUtils.m4953a("PAGE_CLICK", SLIST.get(this.mCurrentPage).m5275a(), SLIST.get(i2).m5274b(), SLIST.get(i).m5274b());
    }

    @Override // com.sds.android.ttpod.activities.base.ThemeManagementBaseActivity
    protected String getOnSelectedCountChangedString(int i) {
        return getString(R.string.select_background_with_count, new Object[]{Integer.valueOf(i)});
    }

    @Override // com.sds.android.ttpod.activities.base.ThemeManagementBaseActivity
    protected String getTitleString() {
        return getString(R.string.background);
    }
}
