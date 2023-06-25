package com.sds.android.ttpod.activities;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;

import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;

import com.sds.android.cloudapi.ttpod.data.User;

import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.ThirdParty.ThirdPartyManager;
import com.sds.android.ttpod.activities.base.ThemeActivity;
import com.sds.android.ttpod.activities.musiccircle.p068a.CheckerManager;
import com.sds.android.ttpod.activities.user.LoginActivity;
import com.sds.android.ttpod.component.landscape.LandscapeFragment;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.GlobalMenuDialog;
import com.sds.android.ttpod.component.p096h.Register;
import com.sds.android.ttpod.fragment.OnClosePlayerPanelRequestListener;
import com.sds.android.ttpod.fragment.PlayControlBarFragment;
import com.sds.android.ttpod.fragment.ViewPagerGuideFragment;
import com.sds.android.ttpod.fragment.downloadmanager.DownloadManagerFragment;
import com.sds.android.ttpod.fragment.main.MainFragment;
import com.sds.android.ttpod.fragment.main.PortraitPlayerFragment;
import com.sds.android.ttpod.fragment.skinmanager.base.ThemeListObserver;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.FragmentBackStackManager;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.p126h.UnicomFlowUtil;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.utils.EntryUtils;
import com.sds.android.ttpod.utils.StartAction;
import com.sds.android.ttpod.widget.SlidingClosableRelativeLayout;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class MainActivity extends ThemeActivity implements GlobalMenuDialog.InterfaceC1152b, OnClosePlayerPanelRequestListener, MainFragment.InterfaceC1462a, ThemeManager.InterfaceC2019b {
    private static final String LOG_TAG = "MainActivity";
    private static final long READY_BACKGROUND_TIMEOUT = 3500;
    private static final int TIME_DELAY_MILLISECOND = 30000;
    private GlobalMenuDialog mGlobalMenuDialog;
    private LandscapeFragment mLandscapeFragment;
    private Long mLastReadyBackgroundTimeStamp;
    private View mMainContentView;
    private SlidingClosableRelativeLayout mPanelPlayerLayout;
    private PlayControlBarFragment mPlayControlBarFragment;
    private PortraitPlayerFragment mPortraitPlayerFragment;
    private static boolean sHasCheckSkinListUpdate = false;
    private static Boolean mIsShowViewPagerGuideEnabled = null;
    private boolean mAudioStaticTracked = false;
    private int mWaitDealMenuId = -1;
    private boolean mIsCheckMonthBeginPopupDialog = true;

    public static void handleAccessTokenInvalid() {
        Preferences.m3022a((User) null);
        PopupsUtils.m6760a((int) R.string.please_login);
        BaseApplication m4635c = BaseApplication.getApplication();
        m4635c.startActivity(new Intent(m4635c, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override
    // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mMainContentView = new FrameLayout(this);
        this.mMainContentView.setId(R.id.main_content);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addContentView(this.mMainContentView, layoutParams);
        addContentView(getLayoutInflater().inflate(R.layout.activity_main, (ViewGroup) null, false), layoutParams);
        if (!this.mAudioStaticTracked) {
            this.mAudioStaticTracked = true;
            this.mMainContentView.postDelayed(new Runnable() { // from class: com.sds.android.ttpod.activities.MainActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    //StatisticUtils.m4906a("local", "click", "fade-over-start", 0L, Preferences.m2985aV() + "_" + Preferences.m2984aW() + "_" + Preferences.m2982aY(), "");
                }
            }, 30000L);
        }
        setLaunchFragmentAttr(R.id.main_content, R.anim.slide_in_right, R.anim.slide_out_right);
        loadPrimaryFragment();
        loadPlayControlBar();
        new Handler().postDelayed(new Runnable() { // from class: com.sds.android.ttpod.activities.MainActivity.2
            @Override // java.lang.Runnable
            public void run() {
                //MVStatistic.m5073b(StormPlayer.m5787a(BaseApplication.getApplication()));
            }
        }, 30000L);
        onNewIntent(getIntent());
        checkExternalStorageExisted();
        if (EnvironmentUtils.C0604c.m8474e()) {
            if (new Date().getTime() - Preferences.m2953ar().longValue() > 86400000) {
                CommandCenter.getInstance().m4605a(new Command(CommandID.CHECK_UPGRADE, Boolean.TRUE), 30);
            }
            requestUpdateSkinList();
        }
        Preferences.m2877h(getResources().getStringArray(R.array.environment_title).length);
        CommandCenter.getInstance().m4605a(new Command(CommandID.PRELOAD_ASYNCLOAD_MEDIA_ITEM_LIST, MediaStorage.GROUP_ID_ALL_LOCAL, Preferences.m2860l(MediaStorage.GROUP_ID_ALL_LOCAL)), 1000);
        CommandCenter.getInstance().m4596b(new Command(CommandID.QUERY_GROUP_ITEM_LIST, GroupType.DEFAULT_ALBUM));
        CommandCenter.getInstance().m4596b(new Command(CommandID.QUERY_GROUP_ITEM_LIST, GroupType.DEFAULT_FOLDER));
        CommandCenter.getInstance().m4596b(new Command(CommandID.QUERY_GROUP_ITEM_LIST, GroupType.DEFAULT_ARTIST));
        UnicomFlowUtil.m3954a(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override
    // com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_MEDIA_ITEM_STARTED, ReflectUtils.m8375a(cls, "updateMediaItemStarted", MediaItem.class));
        map.put(CommandID.UPDATE_MEDIA_ITEM_FINISHED, ReflectUtils.m8375a(cls, "updateMediaItemFinished", MediaItem.class));
        map.put(CommandID.DO_VERSION_COMPACT_STARTED, ReflectUtils.m8375a(cls, "doVersionCompactStarted", new Class[0]));
        map.put(CommandID.DO_VERSION_COMPACT_FINISHED, ReflectUtils.m8375a(cls, "doVersionCompactFinished", new Class[0]));
        map.put(CommandID.FINISH_UPDATE_RECOMMEND_SKIN_LIST, ReflectUtils.m8375a(cls, "updateRecommendSkinListFinished", Boolean.class));
        map.put(CommandID.FINISH_UPDATE_RECOMMEND_BACKGROUND_LIST, ReflectUtils.m8375a(cls, "updateRecommendBackgroundListFinished", Boolean.class));
        map.put(CommandID.UPDATE_PLAY_MODE, ReflectUtils.m8375a(cls, "updatePlayMode", new Class[0]));
        map.put(CommandID.UPDATE_SLEEP_MODE, ReflectUtils.m8375a(cls, "updateSleepMode", new Class[0]));
        map.put(CommandID.UPDATE_UNICOM_FLOW_STATUS, ReflectUtils.m8375a(cls, "updateFlowStatus", Boolean.class));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Action.NOTIFICATION_START_DOWNLOAD_MANAGER.equals(intent.getAction())) {
            closeLandscapeFragment();
            if (this.mPanelPlayerLayout != null && (this.mPanelPlayerLayout.m1494h() || this.mPanelPlayerLayout.m1534a())) {
                this.mPanelPlayerLayout.m1520a(true);
            }
            BaseFragment topFragment = getTopFragment();
            int intExtra = intent.getIntExtra("fragment_page_index", 0);
            if (!(topFragment instanceof DownloadManagerFragment)) {
                Bundle bundle = new Bundle(1);
                bundle.putInt(DownloadManagerFragment.FRAGMENT_TAB_ID, intExtra);
                launchFragment((BaseFragment) Fragment.instantiate(this, DownloadManagerFragment.class.getName(), bundle));
            } else {
                ((DownloadManagerFragment) topFragment).setCurrentPage(intExtra);
            }
            Preferences.m3043S(false);
            return;
        }
        StartAction startAction = new StartAction(this, getPrimaryFragment());
        if (!startAction.m8216b(intent.getData())) {
            startAction.m8228a(intent.getExtras());
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            if (this.mPanelPlayerLayout == null) {
                loadPlayerPanel();
            }
            setOpenPlayerPanelEnable(true);
            Register.m6402a(this);
            tryToShowViewPagerGuide();
            //StartupStatistic.m4924a();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override
    // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        reloadRequestedOrientation();
        checkoutVersionCompact();
        CheckerManager.m7949a().m7941c();
        if (this.mIsCheckMonthBeginPopupDialog) {
            this.mIsCheckMonthBeginPopupDialog = false;
            CommandCenter.getInstance().m4606a(new Command(CommandID.CHECK_BEGIN_MONTH_POPUP_DIALOG, new Object[0]));
        }
        ThirdPartyManager.m8312c();
        GLSurfaceView gLSurfaceView = (GLSurfaceView) findViewById(R.id.surface_view_scene);
        if (gLSurfaceView != null) {
            gLSurfaceView.onResume();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override
    // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        GLSurfaceView gLSurfaceView = (GLSurfaceView) findViewById(R.id.surface_view_scene);
        if (gLSurfaceView != null) {
            gLSurfaceView.onPause();
        }
        CheckerManager.m7949a().m7943b();
        PopupsUtils.m6761a();
    }

    @Override
    // android.support.v4.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mPanelPlayerLayout != null && this.mPanelPlayerLayout.m1494h() && configuration.orientation == 2) {
            startLandscapeFragment();
        } else {
            closeLandscapeFragment();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reloadRequestedOrientation() {
        super.setRequestedOrientation((Preferences.m2842q() && this.mPanelPlayerLayout != null
                && this.mPanelPlayerLayout.m1494h()) ? ActivityInfo.SCREEN_ORIENTATION_SENSOR : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void setOpenPlayerPanelEnable(boolean z) {
        if (this.mPanelPlayerLayout != null) {
            if (z) {
                Rect playerPanelAttachRawRect = this.mPlayControlBarFragment.getPlayerPanelAttachRawRect();
                LogUtils.debug(LOG_TAG, "playerPanel rect:" + playerPanelAttachRawRect.toString());
                this.mPanelPlayerLayout.m1530a(playerPanelAttachRawRect.left, playerPanelAttachRawRect.top, playerPanelAttachRawRect.right, playerPanelAttachRawRect.bottom);
            } else {
                this.mPanelPlayerLayout.m1530a(0, 0, 0, 0);
            }
            this.mPanelPlayerLayout.setEnableMarginOpen(z);
        }
    }

    public void updateMediaItemStarted(MediaItem mediaItem) {
        if (status() == 2) {
            PopupsUtils.m6748a(this, (int) R.string.saving);
        }
    }

    public void updateMediaItemFinished(MediaItem mediaItem) {
        if (status() == 2) {
            PopupsUtils.m6761a();
        }
    }

    @Override
    // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.mLandscapeFragment == null) {
            if (this.mPanelPlayerLayout != null && (this.mPanelPlayerLayout.m1494h() || this.mPanelPlayerLayout.m1534a())) {
                this.mPanelPlayerLayout.m1520a(true);
            } else if (!isFragmentBackStackEmpty()) {
                this.mLastReadyBackgroundTimeStamp = null;
                super.onBackPressed();
            } else if (StartAction.m8233a()) {
                moveTaskToBack(true);
                this.mLastReadyBackgroundTimeStamp = null;
                StartAction.m8219a(false);
            } else if (this.mLastReadyBackgroundTimeStamp == null || System.currentTimeMillis() - this.mLastReadyBackgroundTimeStamp.longValue() > READY_BACKGROUND_TIMEOUT) {
                this.mLastReadyBackgroundTimeStamp = Long.valueOf(System.currentTimeMillis());
                PopupsUtils.m6760a((int) R.string.ready_to_move_back);
            } else if (this.mLastReadyBackgroundTimeStamp != null) {
                moveTaskToBack(true);
                this.mLastReadyBackgroundTimeStamp = null;
            }
        }
    }

    @Override
    // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        BaseFragment topFragment = getTopFragment();
        FragmentBackStackManager childFragmentBackStackManager = topFragment.getChildFragmentBackStackManager();
        if (childFragmentBackStackManager != null) {
            List<BaseFragment> m4575d = childFragmentBackStackManager.m4575d();
            if (m4575d != null) {
                for (BaseFragment baseFragment : m4575d) {
                    if ((baseFragment instanceof BaseFragment) && baseFragment.getUserVisibleHint()) {
                        baseFragment.onKeyPressed(i, keyEvent);
                    }
                }
            }
        } else if (topFragment != null) {
            topFragment.onKeyPressed(i, keyEvent);
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 82) {
            //LocalStatistic.m5107az();
            toggleMenu();
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    public void onOpenRightMenuRequested() {
        toggleMenu();
    }

    @Override // com.sds.android.ttpod.activities.base.ThemeActivity
    public void toggleMenu() {
        this.mGlobalMenuDialog = new GlobalMenuDialog(this, this);
        this.mGlobalMenuDialog.m6872c();
        if (!this.mGlobalMenuDialog.isShowing() && this.mWaitDealMenuId < 0) {
            this.mGlobalMenuDialog.m6877a(this.mPanelPlayerLayout != null && (this.mPanelPlayerLayout.m1494h() || this.mPanelPlayerLayout.m1534a()));
            this.mGlobalMenuDialog.show();
        }
    }

    @Override // com.sds.android.ttpod.fragment.OnClosePlayerPanelRequestListener
    public void onClosePlayerPanelRequested() {
        if (this.mPanelPlayerLayout != null) {
            this.mPanelPlayerLayout.m1520a(true);
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.MainFragment.InterfaceC1462a
    public final void onCurrentFragmentChanged(BaseFragment baseFragment) {
        setCurrentFragment(baseFragment);
    }

    MainFragment mainFragment;

    private void loadPrimaryFragment() {
        mainFragment = new MainFragment();
        mainFragment.setOnCurrentFragmentChangeListener(this);
        setPrimaryFragment(mainFragment);
    }

    private void loadPlayControlBar() {
        this.mPlayControlBarFragment = new PlayControlBarFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.playcontrolbar_content, this.mPlayControlBarFragment).commitAllowingStateLoss();
    }

    private void loadPlayerPanel() {
        this.mPortraitPlayerFragment = new PortraitPlayerFragment();
        this.mPanelPlayerLayout = new SlidingClosableRelativeLayout(this);
        addContentView(this.mPanelPlayerLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        this.mPanelPlayerLayout.setId(R.id.main_pannel_player);
        this.mPanelPlayerLayout.setSlidingCloseMode(8);
        this.mPanelPlayerLayout.setInitSlidingOpenState(false);
        getSupportFragmentManager().beginTransaction().replace(this.mPanelPlayerLayout.getId(), this.mPortraitPlayerFragment).hide(this.mPortraitPlayerFragment).commitAllowingStateLoss();
        this.mPanelPlayerLayout.setOnSlidingScrollListener(new SlidingClosableRelativeLayout.InterfaceC2239c() { // from class: com.sds.android.ttpod.activities.MainActivity.3
            @Override // com.sds.android.ttpod.widget.SlidingClosableRelativeLayout.InterfaceC2239c
            /* renamed from: a */
            public void mo1481a() {
                MainActivity.this.mPortraitPlayerFragment.pauseRefresh();
                MainActivity.this.mMainContentView.setVisibility(View.VISIBLE);
                MainActivity.this.mPlayControlBarFragment.getView().setVisibility(View.VISIBLE);
                if (!MainActivity.this.mPanelPlayerLayout.m1494h() && MainActivity.this.mPortraitPlayerFragment.getView() != null) {
                    MainActivity.this.mPortraitPlayerFragment.getView().setVisibility(View.VISIBLE);
                    MainActivity.this.mPortraitPlayerFragment.onPreVisible();
                    MainActivity.this.reloadRequestedOrientation();
                    //LocalStatistic.m5094l();
                }
            }

            @Override // com.sds.android.ttpod.widget.SlidingClosableRelativeLayout.InterfaceC2239c
            /* renamed from: b */
            public void mo1480b() {
                boolean m1494h = MainActivity.this.mPanelPlayerLayout.m1494h();
                MainActivity.this.mPortraitPlayerFragment.getView().setVisibility(m1494h ? View.VISIBLE : View.GONE);
                MainActivity.this.mMainContentView.setVisibility(m1494h ? View.GONE : View.VISIBLE);
                MainActivity.this.mPlayControlBarFragment.getView().setVisibility(m1494h ? View.INVISIBLE : View.VISIBLE);
                if (MainActivity.this.mPanelPlayerLayout.m1494h()) {
                    MainActivity.this.mPortraitPlayerFragment.resumeRefresh();
                    //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_PORTRAIT_PLAYER.getValue(), SPage.PAGE_NONE.getValue(), SPage.PAGE_PORTRAIT_PLAYER.getValue()).post();
                    MainActivity.this.mPortraitPlayerFragment.updatePage(SPage.PAGE_PORTRAIT_PLAYER);
                } else {
                    BaseFragment topFragment = MainActivity.this.getTopFragment();
                    if (topFragment != null) {
                        topFragment.pageBack();
                    }
                }
                MainActivity.this.reloadRequestedOrientation();
            }
        });
    }

    private void startLandscapeFragment() {
        if (this.mLandscapeFragment == null) {
            setContentViewVisible(false);
            if (this.mPortraitPlayerFragment != null) {
                this.mPortraitPlayerFragment.pauseRefresh();
            }
            this.mLandscapeFragment = new LandscapeFragment();
            this.mLandscapeFragment.initLyricArtistBitmap(this.mPortraitPlayerFragment.getCurrentLyric(), this.mPortraitPlayerFragment.getCurrentArtistBitmap(), this.mPortraitPlayerFragment.getCurrentArtistBitmapPath());
            this.mLandscapeFragment.show(getSupportFragmentManager());
            getWindow().setFlags(1024, 1024);
        }
    }

    private void closeLandscapeFragment() {
        if (this.mLandscapeFragment != null) {
            getWindow().clearFlags(1024);
            this.mLandscapeFragment.dismiss();
            this.mLandscapeFragment = null;
            setContentViewVisible(true);
            if (this.mPortraitPlayerFragment != null) {
                this.mPortraitPlayerFragment.resumeRefresh();
            }
        }
    }

    @SuppressLint("WrongConstant")
    private void setContentViewVisible(boolean z) {
        int i = z ? 0 : 8;
        this.mMainContentView.setVisibility(i);
        this.mPanelPlayerLayout.setVisibility(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override
    // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        CheckerManager.m7949a().m7938e();
        ThirdPartyManager.m8313b();
    }

    private void checkoutVersionCompact() {
        if (!((Boolean) CommandCenter.getInstance().m4602a(new Command(CommandID.CHECK_VERSION_COMPACT, new Object[0]), Boolean.class)).booleanValue()) {
            CommandCenter.getInstance().m4596b(new Command(CommandID.DO_VERSION_COMPACT, new Object[0]));
        }
    }

    private void tryToShowViewPagerGuide() {
        if (mIsShowViewPagerGuideEnabled == null) {
            mIsShowViewPagerGuideEnabled = Boolean.valueOf(Preferences.m2996aK());
            if (mIsShowViewPagerGuideEnabled.booleanValue()) {
                new ViewPagerGuideFragment().show(getSupportFragmentManager(), "main");
                Preferences.m3047Q(false);
            }
        }
    }

    public void doVersionCompactStarted() {
        if (status() == 2) {
            //PopupsUtils.m6744a((Context) this, (int) R.string.updating, false, false);
        }
    }

    public void doVersionCompactFinished() {
        if (status() == 2) {
            PopupsUtils.m6761a();
        }
        CommandCenter.getInstance().m4596b(new Command(CommandID.SYNC_PLAYING_GROUP, new Object[0]));
    }

    public void updateRecommendSkinListFinished(Boolean bool) {
        sHasCheckSkinListUpdate = true;
        if (bool.booleanValue()) {
            GlobalMenuDialog.m6873b(true);
        }
        LogUtils.debug(LOG_TAG, "updateRecommendSkinListFinished----update: " + bool);
    }

    public void updateRecommendBackgroundListFinished(Boolean bool) {
        if (bool.booleanValue()) {
            GlobalMenuDialog.m6869c(true);
        }
        LogUtils.debug(LOG_TAG, "updateRecommendBackgroundListFinished----update: " + bool);
    }

    private void requestUpdateSkinList() {
        if (!sHasCheckSkinListUpdate) {
            CommandCenter.getInstance().m4605a(new Command(CommandID.REQUEST_UPDATE_RECOMMEND_SKIN_LIST, new Object[0]), 15000);
            CommandCenter.getInstance().m4605a(new Command(CommandID.REQUEST_UPDATE_RECOMMEND_BACKGROUND_LIST, new Object[0]), 15000);
            LogUtils.debug("MyFragment", "requestUpdateSkinList [skin]--->");
            //ThemeStatistic.m4889f();
            //ThemeStatistic.m4882j();
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        if (this.mGlobalMenuDialog != null) {
            this.mGlobalMenuDialog.m6872c();
        }
        if (mainFragment != null) {
            mainFragment.onThemeLoaded();
        }
        if (mPlayControlBarFragment != null) {
            mPlayControlBarFragment.onThemeLoaded();
        }
    }

    @Override
    // android.support.v4.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        LogUtils.error(LOG_TAG, "onLowMemory");
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    @TargetApi(14)
    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        LogUtils.error(LOG_TAG, "onTrimMemory level=%d", Integer.valueOf(i));
    }

    public void updatePlayMode() {
        if (this.mGlobalMenuDialog != null) {
            this.mGlobalMenuDialog.m6883a();
        }
    }

    public void updateFlowStatus(Boolean bool) {
        if (this.mGlobalMenuDialog != null) {
            this.mGlobalMenuDialog.m6879a(bool);
        }
    }

    public void updateSleepMode() {
        if (this.mGlobalMenuDialog != null) {
            this.mGlobalMenuDialog.m6876b();
        }
    }

    @Override // com.sds.android.ttpod.component.p087d.p088a.GlobalMenuDialog.InterfaceC1152b
    public void onMenuItemClicked(int i) {
        this.mWaitDealMenuId = i;
        this.mGlobalMenuDialog.dismiss();
        if (this.mWaitDealMenuId >= 0) {
            this.mMainContentView.postDelayed(new Runnable() { // from class: com.sds.android.ttpod.activities.MainActivity.4
                @Override // java.lang.Runnable
                public void run() {
                    MainActivity.this.processMenuClick(MainActivity.this.mWaitDealMenuId);
                    MainActivity.this.mWaitDealMenuId = -1;
                }
            }, 100L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processMenuClick(int i) {
        switch (i) {
            case 0:
                EntryUtils.m8292c((Context) this);
                //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_GLOBAL_MENU_SETTING, SPage.PAGE_GLOBAL_MENU, SPage.PAGE_SETTING_PAGE);
                return;
            case 1:
                EntryUtils.m8287h(this);
                //LocalStatistic.m5171L();
                return;
            case 2:
                EntryUtils.m8289f(this);
                //LocalStatistic.m5175H();
                //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_GLOBAL_MENU_SCAN, SPage.PAGE_GLOBAL_MENU, SPage.PAGE_SCAN_MUSIC);
                return;
            case 3:
                EntryUtils.m8303a();
                //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_GLOBAL_MENU_PLAY_MODE, SPage.PAGE_GLOBAL_MENU, SPage.PAGE_NONE);
                return;
            case 4:
                EntryUtils.m8290e(this);
                //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_GLOBAL_MENU_THEME, SPage.PAGE_GLOBAL_MENU, SPage.PAGE_THEME_BACKGROUND);
                return;
            case 5:
                EntryUtils.m8286i(this);
                return;
            case 6:
                EntryUtils.m8302a((Activity) this);
                //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_GLOBAL_MENU_AUDIO_EFFECT, SPage.PAGE_GLOBAL_MENU, SPage.PAGE_AUDIO_BOOST);
                return;
            case 7:
                EntryUtils.m8299a(this, (String) null);
                //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_GLOBAL_MENU_UPLOAD_SONG, SPage.PAGE_GLOBAL_MENU, SPage.PAGE_UPLOAD_SONG);
                return;
            case 8:
                EntryUtils.m8291d(this);
                //LocalStatistic.m5128ae();
                //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_GLOBAL_MENU_SOUND_RECOGNIZE, SPage.PAGE_GLOBAL_MENU, SPage.PAGE_RECOGNIZE);
                return;
            case 9:
                //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_GLOBAL_MENU_APP, SPage.PAGE_GLOBAL_MENU, SPage.PAGE_MARKET_APP);
                EntryUtils.m8298a((BaseActivity) this);
                return;
            case 10:
                //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_GLOBAL_MENU_EXIT, SPage.PAGE_GLOBAL_MENU, SPage.PAGE_NONE);
                EntryUtils.m8296b();
                return;
            case 11:
                EntryUtils.m8294b((Context) this);
                //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_GLOBAL_MENU_KTV, SPage.PAGE_GLOBAL_MENU, SPage.PAGE_KTV);
                return;
            case 12:
                //UnicomFlowStatistic.m4817j();
                //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_GLOBAL_MENU_UNION_FLOW, SPage.PAGE_GLOBAL_MENU, SPage.PAGE_UNICOM_SUBSCRIBE);
                EntryUtils.m8295b((Activity) this);
                return;
            case 13:
                EntryUtils.m8300a((Context) this);
                return;
            default:
                return;
        }
    }
}
