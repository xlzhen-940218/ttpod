package com.sds.android.ttpod.activities.musiccircle;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.radar.ScanRadarToFindFriendsFragment;
import com.sds.android.ttpod.activities.musiccircle.search.SearchFriendsFragment;
import com.sds.android.ttpod.activities.musiccircle.shake.ShakeToFindFriendsFragment;
import com.sds.android.ttpod.adapter.SlidingTabFragmentPagerAdapter;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.musiccircle.StarCategoryFragment;
import com.sds.android.ttpod.fragment.musiccircle.StarListOfRecommendFragment;
import com.sds.android.ttpod.fragment.musiccircle.StarRankFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.utils.EntryUtils;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.SlidingTabHost;
import java.util.Arrays;

/* loaded from: classes.dex */
public class FindFriendsFragment extends SlidingClosableFragment {
    private static final int FRAGMENT_ID_CATEGORY = 2;
    private static final int FRAGMENT_ID_RANK = 1;
    private static final int FRAGMENT_ID_RECOMMEND = 0;
    public static final String SOCIAL_CATEGORY = "social_category";
    public static final String SOCIAL_RANK = "social_rank";
    public static final String SOCIAL_RECOMMEND = "social_recommend";
    private int mCurrentItem;
    private ActionBarController.C1070a mRadarAction;
    private View mRootView;
    private ActionBarController.C1070a mSearchAction;
    private ActionBarController.C1070a mShakeAction;
    private SlidingTabHost mSlidingTabHost;
    private C0791a mTabsAdapter;
    private ViewPager mViewPager;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.musiccircle_find_friend_layout, viewGroup, false);
        initView();
        MusicCircleStatistic.m7968g();
        return this.mRootView;
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected void addCustomActions() {
        ActionBarController actionBarController = getActionBarController();
        this.mRadarAction = actionBarController.m7178d(R.drawable.img_musiccircle_small_radar);
        this.mShakeAction = actionBarController.m7178d(R.drawable.img_musiccircle_shake);
        this.mSearchAction = actionBarController.m7178d(R.drawable.img_search);
        ActionBarController.InterfaceC1072b interfaceC1072b = new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.activities.musiccircle.FindFriendsFragment.1
            @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
            /* renamed from: a */
            public void mo5433a(ActionBarController.C1070a c1070a) {
                if (Preferences.m2954aq() != null) {
                    if (c1070a == FindFriendsFragment.this.mRadarAction) {
                        FindFriendsFragment.this.launchFragment(new ScanRadarToFindFriendsFragment());
                        return;
                    } else if (c1070a == FindFriendsFragment.this.mShakeAction) {
                        FindFriendsFragment.this.launchFragment(new ShakeToFindFriendsFragment());
                        return;
                    } else if (c1070a == FindFriendsFragment.this.mSearchAction) {
                        FindFriendsFragment.this.launchFragment(new SearchFriendsFragment());
                        return;
                    } else {
                        return;
                    }
                }
                EntryUtils.m8297a(true);
            }
        };
        this.mRadarAction.m7167a(interfaceC1072b);
        this.mShakeAction.m7167a(interfaceC1072b);
        this.mSearchAction.m7167a(interfaceC1072b);
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        ThemeUtils.m8168a(this.mRadarAction, "", (int) R.string.icon_radar, ThemeElement.TOP_BAR_TEXT);
        ThemeUtils.m8168a(this.mShakeAction, "", (int) R.string.icon_shake_switch_song, ThemeElement.TOP_BAR_TEXT);
        ThemeUtils.m8168a(this.mSearchAction, "", (int) R.string.icon_search, ThemeElement.TOP_BAR_TEXT);
        getActionBarController().onThemeLoaded();
    }

    private void initView() {
        getActionBarController().m7189b(R.string.find_friend_title);
        this.mTabsAdapter = new C0791a(getActivity(), getChildFragmentManager(), new SlidingTabFragmentPagerAdapter.C0998a[]{new SlidingTabFragmentPagerAdapter.C0998a(0L, (int) R.string.findfriend_recommend, 0, new StarListOfRecommendFragment()), new SlidingTabFragmentPagerAdapter.C0998a(1L, (int) R.string.findfriend_rank, 0, new StarRankFragment()), new SlidingTabFragmentPagerAdapter.C0998a(2L, (int) R.string.findfriend_category, 0, new StarCategoryFragment())});
        this.mViewPager = (ViewPager) this.mRootView.findViewById(R.id.viewpager);
        this.mViewPager.setCurrentItem(this.mCurrentItem);
        this.mViewPager.setAdapter(this.mTabsAdapter);
        setSlidingCloseMode(2);
        this.mSlidingTabHost = (SlidingTabHost) this.mRootView.findViewById(R.id.slidingtabshost);
        this.mSlidingTabHost.setTabLayoutAverageSpace(true);
        this.mSlidingTabHost.setViewPager(this.mViewPager);
        this.mSlidingTabHost.setOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.sds.android.ttpod.activities.musiccircle.FindFriendsFragment.2
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                FindFriendsFragment.this.mCurrentItem = i;
                int i2 = 0;
                if (!FindFriendsFragment.this.isSlidingAtTheLeftEdge(i)) {
                    if (FindFriendsFragment.this.isSlidingAtTheRightEdge(i)) {
                        i2 = 1;
                    }
                } else {
                    i2 = 2;
                }
                FindFriendsFragment.this.setSlidingCloseMode(i2);
                switch (i) {
                    case 0:
                        MusicCircleStatistic.m7958q();
                        return;
                    case 1:
                        MusicCircleStatistic.m7955t();
                        return;
                    case 2:
                        MusicCircleStatistic.m7954u();
                        return;
                    default:
                        return;
                }
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSlidingAtTheLeftEdge(int i) {
        return i == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSlidingAtTheRightEdge(int i) {
        return i == this.mTabsAdapter.getCount() + (-1);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
    }

    /* renamed from: com.sds.android.ttpod.activities.musiccircle.FindFriendsFragment$a */
    /* loaded from: classes.dex */
    public class C0791a extends SlidingTabFragmentPagerAdapter {
        public C0791a(Context context, FragmentManager fragmentManager, SlidingTabFragmentPagerAdapter.C0998a[] c0998aArr) {
            super(context, fragmentManager, Arrays.asList(c0998aArr));
        }
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mRadarAction.m7167a((ActionBarController.InterfaceC1072b) null);
        this.mShakeAction.m7167a((ActionBarController.InterfaceC1072b) null);
        this.mSearchAction.m7167a((ActionBarController.InterfaceC1072b) null);
        this.mSlidingTabHost.setOnPageChangeListener(null);
        this.mViewPager.setOnPageChangeListener(null);
        this.mSlidingTabHost = null;
        this.mViewPager = null;
        this.mTabsAdapter.m7428a();
        this.mTabsAdapter = null;
    }
}
