package com.sds.android.ttpod.activities.base;

import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.SlidingTabFragmentPagerAdapter;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.widget.FreezableViewPager;
import com.sds.android.ttpod.widget.SlidingTabHost;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public abstract class SlidingPagerActivity extends SlidingClosableActivity implements ViewPager.OnPageChangeListener {
    protected int mCurrentPage = 0;
    protected SlidingTabFragmentPagerAdapter mPagerAdapter;
    protected FreezableViewPager mPagerContent;
    protected SlidingTabHost mPagerTitle;

    protected abstract void onBuildActionBar(ActionBarController actionBarController);

    protected abstract void onBuildFragmentBinderList(List<SlidingTabFragmentPagerAdapter.C0998a> list);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_common_sliding_pager);
        this.mPagerTitle = (SlidingTabHost) findViewById(R.id.sliding_pager_title);
        this.mPagerContent = (FreezableViewPager) findViewById(R.id.sliding_pager_content);
        this.mPagerAdapter = new SlidingTabFragmentPagerAdapter(this, getSupportFragmentManager(), buildFragmentBinders());
        this.mPagerContent.setAdapter(this.mPagerAdapter);
        this.mPagerTitle.setTabLayoutAverageSpace(true);
        this.mPagerTitle.setViewPager(this.mPagerContent);
        onBuildActionBar(getActionBarController());
        this.mPagerTitle.setOnPageChangeListener(this);
        setCurrentPosition(0);
    }

    private void setCurrentPosition(int i) {
        int i2 = 0;
        if (isSlidingAtTheLeftEdge(i)) {
            i2 = 2;
        } else if (isSlidingAtTheRightEdge(i)) {
            i2 = 1;
        }
        setSlidingCloseMode(i2);
    }

    private boolean isSlidingAtTheLeftEdge(int i) {
        return i == 0;
    }

    private boolean isSlidingAtTheRightEdge(int i) {
        return i == this.mPagerAdapter.getCount() + (-1);
    }

    private List<SlidingTabFragmentPagerAdapter.C0998a> buildFragmentBinders() {
        ArrayList arrayList = new ArrayList();
        onBuildFragmentBinderList(arrayList);
        return arrayList;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        setCurrentPosition(i);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.m7428a();
        }
        super.onDestroy();
    }
}
