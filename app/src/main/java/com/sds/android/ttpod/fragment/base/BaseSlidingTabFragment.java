package com.sds.android.ttpod.fragment.base;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.SlidingTabFragmentPagerAdapter;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.widget.SlidingTabHost;
import java.util.List;

/* loaded from: classes.dex */
public abstract class BaseSlidingTabFragment extends SlidingClosableFragment implements ViewPager.OnPageChangeListener {
    public static final int BLUR_RADIUS = 60;
    public static final float DEFAULT_RATIO = 2.13f;
    public static final int SINGER_AVATAR_WIDTH = 64;
    protected int mCurrentItem;
    protected View mHeaderView;
    protected SlidingTabFragmentPagerAdapter mPagerAdapter;
    protected View mRootView;
    protected SlidingTabHost mSlidingTabHost;
    protected ViewPager mViewPager;

    /* renamed from: com.sds.android.ttpod.fragment.base.BaseSlidingTabFragment$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1421a {
        /* renamed from: a */
        void m5717a(int i);
    }

    protected abstract List<SlidingTabFragmentPagerAdapter.C0998a> buildFragmentBinders();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.fragment_base_sliding_tab, viewGroup, false);
            this.mHeaderView = this.mRootView.findViewById(R.id.header_layout);
            this.mSlidingTabHost = (SlidingTabHost) this.mRootView.findViewById(R.id.slidingtabhost);
            this.mSlidingTabHost.setTabLayoutAverageSpace(true);
            this.mViewPager = (ViewPager) this.mRootView.findViewById(R.id.pager_content);
            this.mPagerAdapter = new SlidingTabFragmentPagerAdapter(getActivity(), getChildFragmentManager(), buildFragmentBinders());
            onConfigHeader((ViewGroup) this.mHeaderView);
            this.mViewPager.setAdapter(this.mPagerAdapter);
            this.mViewPager.setOffscreenPageLimit(this.mPagerAdapter.getCount());
            this.mSlidingTabHost.setViewPager(this.mViewPager);
            this.mSlidingTabHost.setOnPageChangeListener(this);
            setCurrentPosition(0);
        }
        return this.mRootView;
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

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        this.mCurrentItem = i;
        setCurrentPosition(i);
    }

    public Fragment currentFragment() {
        return this.mPagerAdapter.getItem(this.mViewPager.getCurrentItem());
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    protected void onConfigHeader(ViewGroup viewGroup) {
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mSlidingTabHost != null) {
            ThemeManager.m3269a(this.mSlidingTabHost, ThemeElement.SUB_BAR_BACKGROUND);
            this.mSlidingTabHost.setTextColor(ThemeManager.m3254c(ThemeElement.SUB_BAR_TEXT));
            this.mSlidingTabHost.setIndicatorDrawable(ThemeManager.m3265a(ThemeElement.SUB_BAR_INDICATOR));
        }
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.m7428a();
        }
        super.onDestroyView();
    }
}
