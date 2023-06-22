package com.sds.android.ttpod.activities.setting;

import android.os.Bundle;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingPagerActivity;
import com.sds.android.ttpod.adapter.SlidingTabFragmentPagerAdapter;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.fragment.WebFragment;
import com.sds.android.ttpod.fragment.settings.feedback.FeedbackComposeFragment;
import com.sds.android.ttpod.fragment.settings.feedback.FeedbackListFragment;
import com.sds.android.ttpod.framework.p106a.p107a.ActionPage;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.p106a.p107a.SUserUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class HelpFeedbackActivity extends SlidingPagerActivity {
    private static final String HELP_CONTENT_EN_URL = "http://m.dongting.com/help/feedback-en.html";
    private static final String HELP_CONTENT_URL = "http://m.dongting.com/help/feedback.html";
    private static final ArrayList<ActionPage> SLIST = new ArrayList<ActionPage>(4) { // from class: com.sds.android.ttpod.activities.setting.HelpFeedbackActivity.1
        {
            add(new ActionPage(SAction.ACTION_SETTING_QUESTION, SPage.PAGE_SETTING_QUESTION));
            add(new ActionPage(SAction.ACTION_SETTING_REPLAY, SPage.PAGE_SETTING_FEEDBACK));
            add(new ActionPage(SAction.ACTION_SETTING_MY_REPLAY, SPage.PAGE_SETTING_MY_FEEDBACK));
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingPagerActivity, com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPagerContent.setOffscreenPageLimit(3);
        initView();
    }

    private void initView() {
        SettingUtils.m7778a(this);
        getRootView().setBackgroundResource(17170443);
        this.mPagerTitle.setBackgroundDrawable(getResources().getDrawable(R.drawable.xml_background_tab_feedback));
        this.mPagerTitle.setTextColor(getResources().getColorStateList(R.drawable.xml_tab_text_feedback));
        this.mPagerTitle.setIndicatorColorResource(R.color.tab_indicator_feedback_selected);
        this.mPagerTitle.setUnderlineColorResource(R.color.tab_indicator_feedback_normal);
        this.mPagerTitle.setUnderlineHeight(1);
        this.mPagerTitle.setIndicatorHeight(DisplayUtils.m7229a(2));
    }

    @Override // com.sds.android.ttpod.activities.base.SlidingPagerActivity
    protected void onBuildActionBar(ActionBarController actionBarController) {
    }

    @Override // com.sds.android.ttpod.activities.base.SlidingPagerActivity
    protected void onBuildFragmentBinderList(List<SlidingTabFragmentPagerAdapter.C0998a> list) {
        Bundle bundle = new Bundle();
        if (getResources().getConfiguration().locale.getCountry().equals("CN") || getResources().getConfiguration().locale.getCountry().equals("TW") || getResources().getConfiguration().locale.getCountry().equals("HK")) {
            bundle.putString(WebFragment.EXTRA_URL, HELP_CONTENT_URL);
        } else {
            bundle.putString(WebFragment.EXTRA_URL, HELP_CONTENT_EN_URL);
        }
        WebFragment webFragment = new WebFragment();
        webFragment.setArguments(bundle);
        webFragment.setPage(SPage.PAGE_SETTING_QUESTION);
        webFragment.initBundle(String.valueOf(SPage.PAGE_SETTING_QUESTION.getValue()), (String) null);
        list.add(new SlidingTabFragmentPagerAdapter.C0998a(0L, (int) R.string.ttpod_helpbook, 0, webFragment));
        FeedbackComposeFragment feedbackComposeFragment = new FeedbackComposeFragment();
        feedbackComposeFragment.initBundle(String.valueOf(SPage.PAGE_SETTING_FEEDBACK.getValue()), (String) null);
        list.add(new SlidingTabFragmentPagerAdapter.C0998a(1L, (int) R.string.feedback_suggestion, 0, feedbackComposeFragment));
        FeedbackListFragment feedbackListFragment = new FeedbackListFragment();
        feedbackListFragment.initBundle(String.valueOf(SPage.PAGE_SETTING_MY_FEEDBACK.getValue()), (String) null);
        list.add(new SlidingTabFragmentPagerAdapter.C0998a(2L, (int) R.string.my_feedback, 0, feedbackListFragment));
    }

    @Override // com.sds.android.ttpod.activities.base.SlidingPagerActivity, androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        super.onPageSelected(i);
        SUserUtils.m4956a(SLIST.get(i).m5275a(), SLIST.get(i).m5274b());
    }
}
