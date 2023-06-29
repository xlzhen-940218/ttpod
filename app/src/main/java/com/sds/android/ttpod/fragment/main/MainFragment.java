package com.sds.android.ttpod.fragment.main;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;

import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.user.UserInfoActivity;
import com.sds.android.ttpod.adapter.SlidingTabFragmentPagerAdapter;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.OnPageSelectedListener;
import com.sds.android.ttpod.fragment.base.ActionBarFragment;
import com.sds.android.ttpod.fragment.main.findsong.RankCategoryFragment;
import com.sds.android.ttpod.fragment.musiccircle.MusicCircleEntryFragment;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.CommonResult;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeFramework;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.BitmapUtils;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.p106a.p107a.ActionPage;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.utils.EntryUtils;
import com.sds.android.ttpod.utils.OfflineModeUtils;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.SlidingTabHost;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class MainFragment extends ActionBarFragment {
    private static final long ID_FRAGMENT_FINDSONG = 1;
    private static final long ID_FRAGMENT_MUSIC_LIBRARY = 3;
    private static final long ID_FRAGMENT_MY = 0;
    private static final long ID_FRAGMENT_RANK = 2;
    private static final ArrayList<ActionPage> SLIST = new ArrayList<ActionPage>(4) { // from class: com.sds.android.ttpod.fragment.main.MainFragment.1
        {
            add(new ActionPage(SAction.ACTION_MY, SPage.PAGE_MY));
            add(new ActionPage(SAction.ACTION_ONLINE_FIND_SONG, SPage.PAGE_ONLINE_FIND_SONG));
            add(new ActionPage(SAction.ACTION_ACCESS_RANK_PAGE, SPage.PAGE_ONLINE_RANK));
            add(new ActionPage(SAction.ACTION_LIBRARY, SPage.PAGE_MUSIC_LIBRARY));
        }
    };
    private InterfaceC1462a mCurrentFragmentChangeListener;
    private int mCurrentItem = 0;
    private SlidingTabFragmentPagerAdapter mMainFragmentPagerAdapter;
    private View mMainView;
    private View mOfflineModeView;
    private SlidingTabHost mSlidingTabHost;
    private ViewPager mViewPager;

    /* renamed from: com.sds.android.ttpod.fragment.main.MainFragment$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1462a {
        void onCurrentFragmentChanged(BaseFragment baseFragment);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        //ThemeStatistic.m4876p();
        setPage(SPage.PAGE_NONE);
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mMainView = layoutInflater.inflate(R.layout.fragment_main, (ViewGroup) null);
        this.mViewPager = (ViewPager) this.mMainView.findViewById(R.id.viewpager);
        this.mSlidingTabHost = (SlidingTabHost) this.mMainView.findViewById(R.id.slidingtabshost_main);
        attachSlidingTabHost(this.mSlidingTabHost, this.mViewPager);
        getActionBarController().m7185b(true);
        loadUserInfoView();
        return this.mMainView;
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        if (this.mMainFragmentPagerAdapter != null) {
            this.mMainFragmentPagerAdapter.m7428a();
        }
        this.mSlidingTabHost.setOnPageChangeListener(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_BUFFERING_STATE_STARTED, ReflectUtils.m8375a(MainFragment.class, "bufferingStarted", new Class[0]));
        map.put(CommandID.UPDATE_BACKGROUND, ReflectUtils.m8375a(getClass(), "updateBackground", Drawable.class));
        map.put(CommandID.LOGIN_FINISHED, ReflectUtils.m8375a(getClass(), "loginFinished", CommonResult.class));
        map.put(CommandID.LOGOUT_FINISHED, ReflectUtils.m8375a(getClass(), "logoutFinished", new Class[0]));
    }

    public void bufferingStarted() {
        PopupsUtils.m6760a((int) R.string.buffering_started);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        loadUserInfoView();
    }

    public void setOnCurrentFragmentChangeListener(InterfaceC1462a interfaceC1462a) {
        this.mCurrentFragmentChangeListener = interfaceC1462a;
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        ThemeUtils.m8166a(this.mSlidingTabHost);
        updateBackground(ThemeUtils.m8182a());
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onPostViewCreated(View view, Bundle bundle) {
        super.onPostViewCreated(view, bundle);
        updateBackground(ThemeUtils.m8182a());
    }

    public void updateBackground(Drawable drawable) {
        if (drawable == null) {
            LogUtils.error("MainFragment", "MainFragment.updateBackground background is null");
        } else {
            ThemeManager.m3260b(getRootView(), drawable);
        }
    }

    private void attachSlidingTabHost(SlidingTabHost slidingTabHost, ViewPager viewPager) {
        this.mMainFragmentPagerAdapter = new SlidingTabFragmentPagerAdapter(getActivity(), getChildFragmentManager(), buildFragmentBinders());
        viewPager.setAdapter(this.mMainFragmentPagerAdapter);
        viewPager.setCurrentItem(this.mCurrentItem);
        slidingTabHost.setTabLayoutAverageSpace(true);
        slidingTabHost.setViewPager(viewPager);
        slidingTabHost.setOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.sds.android.ttpod.fragment.main.MainFragment.2
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {

                MainFragment.this.mCurrentItem = i;
                final BaseFragment baseFragment = (BaseFragment) MainFragment.this.mMainFragmentPagerAdapter.getItem(i);
                MainFragment.this.doStatistic(i);
                if (OfflineModeUtils.m8256a() && baseFragment.isSupportOfflineMode()) {
                    MainFragment.this.mOfflineModeView = OfflineModeUtils.m8253a(MainFragment.this.mViewPager, new OfflineModeUtils.InterfaceC0635a() { // from class: com.sds.android.ttpod.fragment.main.MainFragment.2.1
                        @Override // com.sds.android.ttpod.p067a.OfflineModeUtils.InterfaceC0635a
                        /* renamed from: a */
                        public void mo5379a() {
                            m5696a(baseFragment);
                        }
                    });
                } else {
                    m5696a(baseFragment);
                }
                if (baseFragment instanceof OnPageSelectedListener) {
                    ((OnPageSelectedListener) baseFragment).onPageSelected();
                }
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* renamed from: a */
            public void m5696a(BaseFragment baseFragment) {
                if (MainFragment.this.mOfflineModeView != null) {
                    MainFragment.this.mOfflineModeView.setVisibility(View.GONE);
                }
                if (MainFragment.this.mCurrentFragmentChangeListener != null) {
                    MainFragment.this.mCurrentFragmentChangeListener.onCurrentFragmentChanged(baseFragment);
                }
            }
        });
        //LocalStatistic.m5179D();
        //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_MY, SPage.PAGE_NONE, SPage.PAGE_MY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doStatistic(int i) {
        switch (i) {
            case 0:
                //LocalStatistic.m5179D();
                return;
            case 1:
                //LocalStatistic.m5110aw();
               // FindSongNewStatistic.m5231a();
                return;
            case 2:
               // RankStatistic.m4959c();
                //LocalStatistic.m5109ax();
                return;
            case 3:
                //MusicLibraryStatistic.m5064b();
                //LocalStatistic.m5108ay();
                return;
            default:
                return;
        }
    }

    private List<SlidingTabFragmentPagerAdapter.C0998a> buildFragmentBinders() {
        BaseFragment findSongFragment;
        BaseFragment baseFragment;
        BaseFragment baseFragment2;
        ArrayList arrayList = new ArrayList();
        BaseFragment myFragment = (BaseFragment) Fragment.instantiate(getActivity(), MyFragment.class.getName());
        myFragment.setPage(SPage.PAGE_MY);
        arrayList.add(new SlidingTabFragmentPagerAdapter.C0998a(0L, (int) R.string.my, 0, myFragment));
        if (!Preferences.m2998aI()) {
            findSongFragment = new IPUnSupportedFragment();
            BaseFragment iPUnSupportedFragment = new IPUnSupportedFragment();
            baseFragment = new IPUnSupportedFragment();
            baseFragment2 = iPUnSupportedFragment;
        } else {
            findSongFragment = new FindSongFragment();
            findSongFragment.setPage(SPage.PAGE_ONLINE_FIND_SONG);
            BaseFragment rankCategoryFragment = new RankCategoryFragment();
            rankCategoryFragment.setPage(SPage.PAGE_ONLINE_RANK);
            BaseFragment musicLibraryFragment = new MusicLibraryFragment();
            musicLibraryFragment.setPage(SPage.PAGE_MUSIC_LIBRARY);
            baseFragment = musicLibraryFragment;
            baseFragment2 = rankCategoryFragment;
        }
        arrayList.add(new SlidingTabFragmentPagerAdapter.C0998a(1L, (int) R.string.findsong, 0, findSongFragment));
        arrayList.add(new SlidingTabFragmentPagerAdapter.C0998a(2L, (int) R.string.rank_tab, 0, baseFragment2));
        arrayList.add(new SlidingTabFragmentPagerAdapter.C0998a(3L, (int) R.string.music_library, 0, baseFragment));
        return arrayList;
    }

    public void toggleFindSongFragment() {
        this.mCurrentItem = 1;
        if (this.mViewPager != null) {
            this.mViewPager.setCurrentItem(this.mCurrentItem);
        }
    }

    public void loginFinished(CommonResult commonResult) {
        loadUserInfoView();
    }

    public void logoutFinished() {
        loadUserInfoView();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public void onTitleClicked() {
        if (Preferences.m2954aq() == null) {
            EntryUtils.m8297a(false);
            //LocalStatistic.m5113at();
        } else if (Preferences.m2998aI()) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("new_flag", false);
            bundle.putSerializable("user", Preferences.m2954aq());
            MusicCircleEntryFragment musicCircleEntryFragment = new MusicCircleEntryFragment();
            musicCircleEntryFragment.setArguments(bundle);
            launchFragment(musicCircleEntryFragment);
            //LocalStatistic.m5112au();
        } else {
            launchFragment(new IPUnSupportedFragment());
        }
    }

    private void loadUserInfoView() {
        ActionBarController actionBarController = getActionBarController();
        if (isViewAccessAble()) {
            TTPodUser m2954aq = Preferences.m2954aq();
            ThemeFramework.AbstractC2016e m3258b = ThemeManager.m3258b(ThemeElement.SETTING_AVATAR);
            final int m3285g = m3258b != null ? m3258b.m3285g() : (int) getResources().getDimension(R.dimen.avatar_frame_width);
            IconTextView m7190b = getActionBarController().m7190b();
            if (m2954aq == null) {
                m7190b.setImageDrawable(null);
                BitmapDrawable bitmapDrawable = (BitmapDrawable) ThemeManager.m3265a(ThemeElement.SETTING_AVATAR_IMAGE);
                if (bitmapDrawable != null) {
                    m7190b.setImageDrawable(new BitmapDrawable(getResources(), BitmapUtils.m4785a(bitmapDrawable.getBitmap(), m3285g, false)));
                } else {
                    m7190b.setText(R.string.icon_avatar_hollow);
                    ThemeUtils.m8173a(m7190b, ThemeElement.TOP_BAR_TEXT);
                }
                actionBarController.m7189b(R.string.login_register);
                return;
            }
            ImageCacheUtils.m4749a(m7190b, m2954aq.getAvatarUrl(), (int) getResources().getDimension(R.dimen.avatar_width), (int) getResources().getDimension(R.dimen.avatar_height), String.format(UserInfoActivity.LOCAL_AVATAR_IMAGE_PATH_FORMAT, Long.valueOf(m2954aq.getUserId() + (m2954aq.getAvatarUrl() == null ? 0 : m2954aq.getAvatarUrl().hashCode()))), new ImageCacheUtils.InterfaceC1786a() { // from class: com.sds.android.ttpod.fragment.main.MainFragment.3
                @Override // com.sds.android.ttpod.framework.p106a.ImageCacheUtils.InterfaceC1786a
                /* renamed from: a */
                public Bitmap mo4732a(Bitmap bitmap) {
                    return BitmapUtils.m4785a(bitmap, m3285g, false);
                }
            });
            actionBarController.m7193a((CharSequence) m2954aq.getNickName());
        }
    }
}
