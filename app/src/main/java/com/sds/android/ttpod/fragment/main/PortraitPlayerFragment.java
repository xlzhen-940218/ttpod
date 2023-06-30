package com.sds.android.ttpod.fragment.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.ThirdParty.App360Callback;
import com.sds.android.ttpod.ThirdParty.ThirdPartyManager;
import com.sds.android.ttpod.activities.PictureManagerActivity;
import com.sds.android.ttpod.activities.PlayingListActivity;
import com.sds.android.ttpod.activities.ThemeManagementActivity;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p086c.DownloadMenuHandler;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.EditTextDialog;
import com.sds.android.ttpod.component.p087d.p088a.ListDialog;
import com.sds.android.ttpod.component.p087d.p088a.MessageDialog;
import com.sds.android.ttpod.component.p087d.p088a.PlayerMenuDialog;
import com.sds.android.ttpod.component.p090f.SceneFactory;
import com.sds.android.ttpod.component.p091g.p093b.PlayerPortraitViewController;
import com.sds.android.ttpod.fragment.OnClosePlayerPanelRequestListener;
import com.sds.android.ttpod.fragment.base.ActionBarFragment;
import com.sds.android.ttpod.fragment.base.OnDropdownMenuClickListener;
import com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.PlayerMediaListFragment;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import com.sds.android.ttpod.framework.modules.skin.serialskin.SPlaylistView;
import com.sds.android.ttpod.framework.modules.skin.lyric.Lyric;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.framework.support.SupportService;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.framework.support.search.task.ReportTask;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.utils.ApkUtils;
import com.sds.android.ttpod.utils.EntryUtils;
import com.sds.android.ttpod.utils.OfflineModeUtils;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.ListPopupWindow;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes.dex */
public class PortraitPlayerFragment extends BasePlayerFragment implements PlayerMenuDialog.InterfaceC1165a, PlayerMenuDialog.InterfaceC1166b, OnDropdownMenuClickListener {
    private static final int REQUEST_CODE_PLAY_LIST = 1002;
    private static final int SEARCH_LYRIC = 1000;
    private static final int SEARCH_PICTURE = 1001;
    private static final String TAG = "PortraitPlayerFragment";
    private ListPopupWindow mDropDownMenu;
    private PlayerMediaListFragment mMediaListFragment;
    private DefaultSkinEventHandler mSkinEventHandler;
    private PlayerPortraitViewController mViewController;
    private SkinCache mSkinCache = null;
    private App360Callback mApp360Callback = new App360Callback() { // from class: com.sds.android.ttpod.fragment.main.PortraitPlayerFragment.5
    };

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivity().setVolumeControlStream(3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.SKIN_CHANGED, ReflectUtils.loadMethod(cls, "skinChanged", new Class[0]));
        map.put(CommandID.LOAD_SKIN_FINISHED, ReflectUtils.loadMethod(cls, "loadSkinFinished", SkinCache.class));
        map.put(CommandID.UPDATE_PLAY_MODE, ReflectUtils.loadMethod(cls, "updatePlayMode", new Class[0]));
        map.put(CommandID.UPDATE_SLEEP_MODE, ReflectUtils.loadMethod(cls, "updateSleepMode", new Class[0]));
        map.put(CommandID.UPDATE_LYRIC_DELETED, ReflectUtils.loadMethod(cls, "lyricDeleted", MediaItem.class));
        map.put(CommandID.UPDATE_PICTURE_DELETED, ReflectUtils.loadMethod(cls, "pictureDeleted", MediaItem.class));
        map.put(CommandID.UPDATE_REPORT, ReflectUtils.loadMethod(cls, "updateReport", ReportTask.EnumC2097b.class, MediaItem.class, Boolean.class));
        map.put(CommandID.UPDATE_BACKGROUND, ReflectUtils.loadMethod(cls, "updateBackground", Drawable.class));
        map.put(CommandID.UPDATE_FAVORITE_CHANGED, ReflectUtils.loadMethod(cls, "updateFavoriteChanged", new Class[0]));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.framelayout_container, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1002 && this.mViewController != null) {
            this.mViewController.mo6434b("OnPlaylistDisappear");
        }
    }

    public void resumeRefresh() {
        if (this.mViewController != null) {
            this.mViewController.m6550y();
            this.mViewController.onPanelShow();
        }
        LogUtils.error("Sun Hao", "ProtraitPlayerFragment resumeRefresh");
        startUpdatePlayPosition();
    }

    public void pauseRefresh() {
        if (this.mViewController != null) {
            this.mViewController.m6551x();
            this.mViewController.onPanelDisappear();
        }
        stopUpdatePlayPosition();
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mViewController != null && getUserVisibleHint()) {
            this.mViewController.onPanelShow();
        }
        startUpdatePlayPosition();
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        if (this.mViewController != null && getUserVisibleHint()) {
            this.mViewController.onPanelDisappear();
            Preferences.m2897d(this.mViewController.getLastDisplayPanelId());
        }
        stopUpdatePlayPosition();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        SkinCache m3151m = Cache.getInstance().getSkinCache();
        if (m3151m == null || m3151m.getSerializableSkin() == null) {
            CommandCenter.getInstance().postInvokeResult(new Command(CommandID.LOAD_SKIN, new Object[0]));
        } else {
            loadSkinFinished(m3151m);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        if (this.mViewController != null) {
            this.mViewController.mo6432c();
        }
        PopupsUtils.m6723a(this.mDropDownMenu);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        if (this.mViewController != null) {
            this.mViewController.mo6441b();
        }
    }

    private void addPlayingList(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.playing));
        bundle.putString(AbsMediaListFragment.KEY_GROUP_ID, Preferences.getLocalGroupId());
        this.mMediaListFragment = (PlayerMediaListFragment) Fragment.instantiate(getActivity(), PlayerMediaListFragment.class.getName(), bundle);
        getChildFragmentManager().beginTransaction().replace(view.getId(), this.mMediaListFragment).commitAllowingStateLoss();
    }

    public void onPreVisible() {
        if (this.mMediaListFragment != null) {
            this.mMediaListFragment.scrollToPlayingRow();
        }
    }

    public void loadSkinFinished(SkinCache skinCache) {
        final FragmentActivity activity = getActivity();
        View view = getView();
        if (skinCache != null && skinCache.serializableSkinNotNull() && view != null && activity != null) {
            FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.container);
            if (this.mViewController != null) {
                this.mViewController.mo6441b();
            }
            this.mViewController = new PlayerPortraitViewController(getActivity(), skinCache);
            this.mViewController.mo6452a(getSkinEventHandler());
            this.mSkinEventHandler.m5674a(this.mViewController);
            View mo6461a = this.mViewController.getMultiScreenLayout();
            frameLayout.removeAllViews();
            if (mo6461a != null) {
                frameLayout.addView(mo6461a);
                View findViewWithTag = mo6461a.findViewWithTag("PlayingList");
                if (findViewWithTag instanceof ViewGroup) {
                    findViewWithTag.setId(R.id.playing_list);
                    addPlayingList(findViewWithTag);
                }
            }
            if (this.mSkinCache != null) {
                this.mSkinCache.clear();
            }
            this.mSkinCache = skinCache;
            if (!Preferences.m3032Y()) {
                updateBackground(ThemeUtils.m8182a());
            }
            if (Build.VERSION.SDK_INT > 10) {
                addTestParticleScene(skinCache);
            }
            frameLayout.post(new Runnable() { // from class: com.sds.android.ttpod.fragment.main.PortraitPlayerFragment.1
                @Override // java.lang.Runnable
                public void run() {
                    AudioManager audioManager = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
                    PortraitPlayerFragment.this.mViewController.mo6439b(audioManager.getStreamVolume(3), audioManager.getStreamMaxVolume(3));
                    PortraitPlayerFragment.this.initViewController();
                    if (PortraitPlayerFragment.this.getUserVisibleHint()) {
                        PortraitPlayerFragment.this.mViewController.onPanelShow();
                    }
                }
            });
        }
    }

    private void addTestParticleScene(SkinCache skinCache) {
        FragmentActivity activity = getActivity();
        View findViewById = activity.findViewById(R.id.surface_view_scene);
        if (findViewById != null) {
            ((ViewGroup) findViewById.getParent()).removeView(findViewById);
        }
        GLSurfaceView.Renderer m6632a = SceneFactory.m6632a(activity, skinCache);
        if (m6632a != null) {
            GLSurfaceView gLSurfaceView = new GLSurfaceView(activity);
            gLSurfaceView.setId(R.id.surface_view_scene);
            gLSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
            gLSurfaceView.setRenderer(m6632a);
            gLSurfaceView.getHolder().setFormat(-3);
            gLSurfaceView.setZOrderOnTop(true);
            activity.addContentView(gLSurfaceView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }

    public void updateBackground(Drawable drawable) {
        if (drawable == null) {
            LogUtils.info(TAG, "PortraitPlayerFragment.updateBackground background is null");
        } else if (this.mViewController != null) {
            if (Preferences.m3032Y()) {
                SkinCache m3151m = Cache.getInstance().getSkinCache();
                if (m3151m != null) {
                    drawable = m3151m.m3589b(BaseApplication.getApplication());
                    if (drawable == null) {
                        drawable = m3151m.getBackground(BaseApplication.getApplication());
                    }
                } else {
                    LogUtils.debug(TAG, "PortraitPlayerFragment.updateBackground skin cache is null!");
                }
            }
            this.mViewController.getMultiScreenLayout().setBackground(drawable);
        }
    }

    public void updateFavoriteChanged() {
        if (this.mViewController != null) {
            MediaItem m3225N = Cache.getInstance().getCurrentPlayMediaItem();
            if (!m3225N.isNull()) {
                if (MediaItemUtils.m4715a(m3225N)) {
                    m3225N.setFav(true);
                    this.mViewController.mo6443a(true);
                    return;
                }
                m3225N.setFav(false);
                this.mViewController.mo6443a(false);
            }
        }
    }

    public void updateDownloadTaskState(DownloadTaskInfo downloadTaskInfo) {
        //StatisticUtils.m4910a("360", "click", "install_app");
        ApkUtils.m8311a(getActivity(), downloadTaskInfo.getSavePath());
    }

    public void skinChanged() {
        CommandCenter.getInstance().postInvokeResult(new Command(CommandID.LOAD_SKIN, new Object[0]));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void artistBitmapLoadFinished() {
        super.artistBitmapLoadFinished();
        if (this.mViewController != null) {
            this.mViewController.mo6457a(getCurrentArtistBitmap());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void switchArtistPicture(Bitmap bitmap, String str) {
        super.switchArtistPicture(bitmap, str);
        if (this.mViewController != null) {
            this.mViewController.mo6438b(bitmap);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void artistBitmapSearchStarted() {
        super.artistBitmapSearchStarted();
        if (this.mViewController != null) {
            this.mViewController.mo6411p();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void artistBitmapDownloadStarted() {
        super.artistBitmapDownloadStarted();
        if (this.mViewController != null) {
            this.mViewController.mo6413n();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void artistBitmapDownloadError() {
        super.artistBitmapDownloadError();
        if (this.mViewController != null) {
            this.mViewController.mo6412o();
        }
    }

    public void pictureDeleted(MediaItem mediaItem) {
        if (this.mViewController != null) {
            this.mViewController.mo6457a((Bitmap) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void lyricLoadFinished() {
        super.lyricLoadFinished();
        if (this.mViewController != null) {
            this.mViewController.mo6451a(getCurrentLyric());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void lyricSearchStarted() {
        super.lyricSearchStarted();
        if (this.mViewController != null) {
            this.mViewController.mo6415m();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void lyricDownloadStarted() {
        super.lyricDownloadStarted();
        if (this.mViewController != null) {
            this.mViewController.mo6423h();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void lyricSearchFailed() {
        super.lyricSearchFailed();
        if (this.mViewController != null) {
            this.mViewController.mo6422i();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void lyricDownloadError() {
        super.lyricDownloadError();
        if (this.mViewController != null) {
            this.mViewController.mo6421j();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void lyricNetError() {
        super.lyricNetError();
        if (this.mViewController != null) {
            this.mViewController.mo6419k();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void lyricSearchStop() {
        super.lyricSearchStop();
        if (this.mViewController != null) {
            this.mViewController.mo6417l();
        }
    }

    public void lyricDeleted(MediaItem mediaItem) {
        if (this.mViewController != null) {
            this.mViewController.mo6451a((Lyric) null);
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void playMediaChanged() {
        super.playMediaChanged();
        if (this.mViewController != null) {
            loadLyric();
            loadArtistBitmap();
            this.mViewController.mo6459a(SupportFactory.getInstance(BaseApplication.getApplication()).m2465k().intValue(), 0.0f);
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void updatePlayMediaInfo() {
        if (this.mViewController != null) {
            this.mViewController.onMetaChange(Cache.getInstance().getCurrentPlayMediaItem());
            this.mViewController.mo6459a(SupportFactory.getInstance(BaseApplication.getApplication()).m2465k().intValue(), SupportFactory.getInstance(BaseApplication.getApplication()).m2464l());
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void updatePlayStatus(PlayStatus playStatus) {
        super.updatePlayStatus(playStatus);
        if (this.mViewController != null) {
            this.mViewController.mo6446a(playStatus);
        }
    }

    public void updatePlayMode() {
        if (this.mViewController != null) {
            this.mViewController.onPlayModeChange(Preferences.m2862l());
        }
    }

    public void updateSleepMode() {
        if (this.mViewController != null) {
            this.mViewController.m6525d(((Boolean) CommandCenter.getInstance().m4602a(new Command(CommandID.IS_SLEEP_MODE_ENABLED, new Object[0]), Boolean.class)).booleanValue());
        }
    }

    public void updateReport(ReportTask.EnumC2097b enumC2097b, MediaItem mediaItem, Boolean bool) {
        PopupsUtils.m6760a(bool.booleanValue() ? R.string.report_send_successful : R.string.report_send_failed);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initViewController() {
        MediaItem m3225N = Cache.getInstance().getCurrentPlayMediaItem();
        LogUtils.debug(TAG, "initViewController looklyricloading %s", m3225N.getTitle());
        if (!m3225N.isNull()) {
            this.mViewController.mo6447a(m3225N, (Bitmap) null, (Lyric) null);
            updatePlayMediaInfo();
            loadLyric();
            loadArtistBitmap();
        } else if (!this.mViewController.m6543J()) {
            this.mViewController.mo6447a(m3225N, (Bitmap) null, (Lyric) null);
        }
        updatePlayPosition();
        updatePlayMode();
        updateSleepMode();
        updatePlayStatus(SupportFactory.getInstance(BaseApplication.getApplication()).m2463m());
        startupSearchLyricPic();
    }

    private void startupSearchLyricPic() {
        BaseApplication.getApplication().startService(new Intent(BaseApplication.getApplication(), SupportService.class).putExtra("command", "search_lyric_pic_command"));
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean userVisibleHint) {
        super.setUserVisibleHint(userVisibleHint);
        if (userVisibleHint) {
            if (this.mViewController != null) {
                this.mViewController.onPanelShow();
                getView().requestLayout();
            }
        } else if (this.mViewController != null) {
            this.mViewController.onPanelDisappear();
        }
    }

    private void loadArtistBitmap() {
        if (this.mViewController != null) {
            this.mViewController.mo6457a(getCurrentArtistBitmap());
        }
    }

    private void loadLyric() {
        if (this.mViewController != null) {
            this.mViewController.mo6451a(getCurrentLyric());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DefaultSkinEventHandler getSkinEventHandler() {
        if (this.mSkinEventHandler == null) {
            this.mSkinEventHandler = new DefaultSkinEventHandler(getActivity(), this.mViewController) { // from class: com.sds.android.ttpod.fragment.main.PortraitPlayerFragment.4
                @Override // com.sds.android.ttpod.fragment.main.DefaultSkinEventHandler, com.sds.android.ttpod.framework.modules.skin.p130c.SkinEventHandler
                /* renamed from: a */
                public boolean mo3717a(int actionId, Object obj) {
                    SPlaylistView m3842b;
                    if (super.mo3717a(actionId, obj)) {
                        return true;
                    }
                    Object parent = PortraitPlayerFragment.this.getParent();
                    switch (actionId) {
                        case 1:
                            if (parent instanceof OnClosePlayerPanelRequestListener) {
                                ((OnClosePlayerPanelRequestListener) parent).onClosePlayerPanelRequested();
                                break;
                            }
                            break;
                        case 3:
                            if (PortraitPlayerFragment.this.mViewController != null) {
                                PortraitPlayerFragment.this.mViewController.mo6434b("OnPlaylistShow");
                            }
                            FragmentActivity activity = PortraitPlayerFragment.this.getActivity();
                            PortraitPlayerFragment.this.startActivityForResult(new Intent(activity, PlayingListActivity.class), 1002);
                            if (PortraitPlayerFragment.this.mSkinCache != null && PortraitPlayerFragment.this.mSkinCache.serializableSkinNotNull() && (m3842b = PortraitPlayerFragment.this.mSkinCache.getSerializableSkin().getPlayerListViewByTransForm(0)) != null) {
                                PlayingListActivity.overrideActivityInAnimation(activity, m3842b.m3784f());
                            }
                            //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_PORTRAIT_PLAYING_LIST.getValue(), SPage.PAGE_PORTRAIT_PLAYER.getValue(), SPage.PAGE_NONE.getValue()).post();
                            break;
                        case 7:
                            PortraitPlayerFragment.this.tryShowLyricMenu();
                            break;
                        case 8:
                            PortraitPlayerFragment.this.checkOfflineModeToShowDialog(1000);
                            break;
                        case 9:
                            PortraitPlayerFragment.this.tryShowPictureMenu();
                            break;
                        case 29:
                            PlayerMenuDialog playerMenuDialog = new PlayerMenuDialog(PortraitPlayerFragment.this.getActivity());
                            playerMenuDialog.m6803a((PlayerMenuDialog.InterfaceC1165a) PortraitPlayerFragment.this);
                            playerMenuDialog.m6802a((PlayerMenuDialog.InterfaceC1166b) PortraitPlayerFragment.this);
                            playerMenuDialog.show();
                            //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_PORTRAIT_MENU.getValue(), SPage.PAGE_PORTRAIT_PLAYER.getValue(), SPage.PAGE_PLAYER_MENU.getValue()).post();
                            break;
                        case 30:
                            PortraitPlayerFragment.this.startActivity(new Intent(PortraitPlayerFragment.this.getActivity(), ThemeManagementActivity.class));
                            //ThemeStatistic.m4873s();
                            //ThemeStatistic.m4886g("play");
                            break;
                        case 31:
                            PortraitPlayerFragment.this.showAdjustMoreDialog();
                            break;
                        case 32:
                            PortraitPlayerFragment.this.showDeleteLyricDialog();
                            break;
                        case 33:
                            ThirdPartyManager.m8317a(PortraitPlayerFragment.this.getActivity(), PortraitPlayerFragment.this.mApp360Callback);
                            break;
                        default:
                            return false;
                    }
                    return true;
                }
            };
        }
        return this.mSkinEventHandler;
    }

    @Override // com.sds.android.ttpod.component.p087d.p088a.PlayerMenuDialog.InterfaceC1165a
    public void onPictureOptionSelected() {
        tryShowPictureMenu();
    }

    @Override // com.sds.android.ttpod.component.p087d.p088a.PlayerMenuDialog.InterfaceC1165a
    public void onLyricOptionSelected() {
        checkOfflineModeToShowDialog(1000);
    }

    public void onMediaOptionSelected() {
        getSkinEventHandler().m5672b();
    }

    @Override // com.sds.android.ttpod.component.p087d.p088a.PlayerMenuDialog.InterfaceC1165a
    public void onSetRingtoneSelected() {
        //LocalStatistic.m5141aM();
        PopupsUtils.m6740a((Context) getActivity(), Cache.getInstance().getCurrentPlayMediaItem());
    }

    @Override // com.sds.android.ttpod.component.p087d.p088a.PlayerMenuDialog.InterfaceC1165a
    public void onDownloadSelected() {
        new DownloadMenuHandler(getActivity()).m6927a(Cache.getInstance().getCurrentPlayMediaItem(), null);
    }

    @Override // com.sds.android.ttpod.component.p087d.p088a.PlayerMenuDialog.InterfaceC1165a
    public void onShareOptionSelected() {
        getSkinEventHandler().m5676a();
    }

    @Override // com.sds.android.ttpod.component.p087d.p088a.PlayerMenuDialog.InterfaceC1165a
    public void onAdjustOptionSelected() {
        this.mViewController.m6405w();
    }

    @Override // com.sds.android.ttpod.component.p087d.p088a.PlayerMenuDialog.InterfaceC1165a
    public void onMoreOptionSelected() {
        getSkinEventHandler().m5672b();
    }

    @Override // com.sds.android.ttpod.component.p087d.p088a.PlayerMenuDialog.InterfaceC1166b
    public void onVolumeChanged(int i, int i2) {
        if (this.mViewController != null) {
            this.mViewController.mo6439b(i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void updatePlayPosition() {
        getSkinEventHandler().m5671c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkOfflineModeToShowDialog(final int i) {
        OfflineModeUtils.m8255a(getActivity(), new DialogInterface.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.PortraitPlayerFragment.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                switch (i2) {
                    case -1:
                        if (1001 == i) {
                            PortraitPlayerFragment.startActivityFromBottom(PortraitPlayerFragment.this.getActivity(), PictureManagerActivity.class);
                            return;
                        } else if (1000 == i) {
                            PortraitPlayerFragment.this.showSearchLyricDialog();
                            return;
                        } else {
                            return;
                        }
                    default:
                        return;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryShowLyricMenu() {
        final MediaItem m3225N = Cache.getInstance().getCurrentPlayMediaItem();
        if (!m3225N.isNull()) {
            PopupsUtils.m6725a(getActivity(), "Lyric".equals(this.mViewController.getCurrentViewEventController(this.mViewController.getLastDisplayPanelId()).getControllerName()) ? new ActionItem[]{new ActionItem(0, (int) R.drawable.img_contextmenu_search, (int) R.string.search_lyric), new ActionItem(1, (int) R.drawable.img_contextmenu_remove, (int) R.string.delete_lyric), new ActionItem(2, (int) R.drawable.img_contextmenu_adjust_lyric, (int) R.string.adjust_lyric), new ActionItem(3, (int) R.drawable.img_contextmenu_error_report, (int) R.string.report_lyric_error)} : new ActionItem[]{new ActionItem(0, (int) R.drawable.img_contextmenu_search, (int) R.string.search_lyric), new ActionItem(1, (int) R.drawable.img_contextmenu_remove, (int) R.string.delete_lyric), new ActionItem(3, (int) R.drawable.img_contextmenu_error_report, (int) R.string.report_lyric_error)}, m3225N.getTitle(), new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.fragment.main.PortraitPlayerFragment.7
                @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
                /* renamed from: a */
                public void mo5409a(ActionItem actionItem, int i) {
                    int m7005e = actionItem.m7005e();
                    if (m7005e == 0) {
                        PortraitPlayerFragment.this.checkOfflineModeToShowDialog(1000);
                    } else if (m7005e == 1) {
                        PortraitPlayerFragment.this.showDeleteLyricDialog();
                    } else if (m7005e == 2) {
                        PortraitPlayerFragment.this.mViewController.m6429d();
                    } else {
                        PortraitPlayerFragment.this.showLyricErrorReportDialog(m3225N);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryShowPictureMenu() {
        checkOfflineModeToShowDialog(1001);
    }

    private static Activity getTopActivity(Activity activity) {
        while (activity.getParent() != null) {
            activity = activity.getParent();
        }
        return activity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAdjustMoreDialog() {
        ActionItem actionItem = new ActionItem(3, (int) R.drawable.img_contextmenu_download_song, (int) R.string.download_song);
        ActionItem actionItem2 = new ActionItem(3, (int) R.drawable.img_contextmenu_manager_song, (int) R.string.manager_song);
        ActionItem[] actionItemArr = new ActionItem[5];
        actionItemArr[0] = new ActionItem(0, (int) R.drawable.img_contextmenu_search, (int) R.string.search_lyric);
        actionItemArr[1] = new ActionItem(1, (int) R.drawable.img_contextmenu_remove_lyric, (int) R.string.delete_lyric);
        actionItemArr[2] = new ActionItem(2, (int) R.drawable.img_contextmenu_manager_pic, (int) R.string.manager_picture);
        if (!Cache.getInstance().getCurrentPlayMediaItem().isOnline()) {
            actionItem = actionItem2;
        }
        actionItemArr[3] = actionItem;
        actionItemArr[4] = new ActionItem(4, (int) R.drawable.img_contextmenu_share_song, (int) R.string.share_song);
        FragmentActivity activity = getActivity();
        PopupsUtils.m6725a(activity, actionItemArr, activity.getString(R.string.more), new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.fragment.main.PortraitPlayerFragment.8
            @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
            /* renamed from: a */
            public void mo5409a(ActionItem actionItem3, int i) {
                int m7005e = actionItem3.m7005e();
                if (m7005e == 0) {
                    PortraitPlayerFragment.this.checkOfflineModeToShowDialog(1000);
                } else if (m7005e == 1) {
                    PortraitPlayerFragment.this.showDeleteLyricDialog();
                } else if (m7005e == 2) {
                    PortraitPlayerFragment.this.tryShowPictureMenu();
                } else if (m7005e == 3) {
                    PortraitPlayerFragment.this.getSkinEventHandler().m5672b();
                } else if (m7005e == 4) {
                    PortraitPlayerFragment.this.getSkinEventHandler().m5676a();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void startActivityFromBottom(Context context, Class<?> cls) {
        @SuppressLint("WrongConstant") Intent flags = new Intent(context, cls).setFlags(603979776);
        Activity activity = null;
        if ((context instanceof Activity) && (activity = getTopActivity((Activity) context)) != null) {
            context = activity;
        }
        context.startActivity(flags.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION));
        if (activity != null) {
            activity.overridePendingTransition(R.anim.push_bottom_in, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSearchLyricDialog() {
        final MediaItem m3225N = Cache.getInstance().getCurrentPlayMediaItem();
        if (!m3225N.isNull()) {
            EditTextDialog editTextDialog = new EditTextDialog(getActivity(), new EditTextDialog.C1144a[]{new EditTextDialog.C1144a(1, getString(R.string.title), m3225N.getTitle(), getString(R.string.please_input_title)), new EditTextDialog.C1144a(2, getString(R.string.artist), m3225N.getArtist(), getString(R.string.please_input_artist))}, R.string.search, new BaseDialog.InterfaceC1064a<EditTextDialog>() { // from class: com.sds.android.ttpod.fragment.main.PortraitPlayerFragment.9
                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo2038a(EditTextDialog editTextDialog2) {
                    try {
                        EditTextDialog.C1144a m6902c = editTextDialog2.m6902c(1);
                        CommandCenter.getInstance().postInvokeResult(new Command(CommandID.START_SEARCH_LYRIC, m3225N, editTextDialog2.m6902c(2).m6896d().toString(), m6902c.m6896d().toString()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, null);
            editTextDialog.setTitle(R.string.search_lyric);
            editTextDialog.show();
        }
    }

    private void showSearchPictureDialog() {
        final MediaItem m3225N = Cache.getInstance().getCurrentPlayMediaItem();
        if (!m3225N.isNull()) {
            EditTextDialog editTextDialog = new EditTextDialog(getActivity(), new EditTextDialog.C1144a[]{new EditTextDialog.C1144a(1, getString(R.string.artist), m3225N.getArtist(), getString(R.string.please_input_artist))}, R.string.search, new BaseDialog.InterfaceC1064a<EditTextDialog>() { // from class: com.sds.android.ttpod.fragment.main.PortraitPlayerFragment.10
                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo2038a(EditTextDialog editTextDialog2) {
                    CommandCenter.getInstance().postInvokeResult(new Command(CommandID.START_SEARCH_PICTURE, m3225N, editTextDialog2.m6902c(1).m6896d().toString(), null));
                }
            }, null);
            editTextDialog.setTitle(R.string.search_picture);
            editTextDialog.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDeleteLyricDialog() {
        MessageDialog messageDialog = new MessageDialog(getActivity(), (int) R.string.confirm_delete_lyric, new BaseDialog.InterfaceC1064a<MessageDialog>() { // from class: com.sds.android.ttpod.fragment.main.PortraitPlayerFragment.11
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(MessageDialog messageDialog2) {
                if (!StringUtils.isEmpty(Cache.getInstance().m3159i())) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(Cache.getInstance().getCurrentPlayMediaItem());
                    CommandCenter.getInstance().execute(new Command(CommandID.DELETE_LYRIC, arrayList));
                }
            }
        }, (BaseDialog.InterfaceC1064a<MessageDialog>) null);
        messageDialog.setTitle(R.string.delete_lyric);
        messageDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLyricErrorReportDialog(final MediaItem mediaItem) {
        final ListDialog listDialog = new ListDialog(getActivity(), new ActionItem[]{new ActionItem(0, 0, (int) R.string.download_error), new ActionItem(1, 0, (int) R.string.not_matched), new ActionItem(2, 0, (int) R.string.not_synced_or_misprint)}, (BaseDialog.InterfaceC1064a<? extends ListDialog>) null, (BaseDialog.InterfaceC1064a<? extends ListDialog>) null);
        listDialog.m7254b(R.string.cancel, null);
        listDialog.setTitle(R.string.report_lyric_error);
        listDialog.m6844a(new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.fragment.main.PortraitPlayerFragment.2
            @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
            /* renamed from: a */
            public void mo5409a(ActionItem actionItem, int i) {
                ReportTask.EnumC2096a enumC2096a = ReportTask.EnumC2096a.REPORT_NO_CONTENT_STATE;
                if (i == 2) {
                    enumC2096a = ReportTask.EnumC2096a.REPORT_LOW_QUALITY_STATE;
                } else if (i == 1) {
                    enumC2096a = ReportTask.EnumC2096a.REPORT_NO_MATCH_STATE;
                }
                CommandCenter.getInstance().postInvokeResult(new Command(CommandID.REPORT_LYRIC_PICTURE, ReportTask.EnumC2097b.REPORT_TYPE_LYRIC, enumC2096a, mediaItem));
                PopupsUtils.m6760a((int) R.string.thank_you_for_join);
                listDialog.dismiss();
            }
        });
        listDialog.show();
    }

    private void showPictureErrorReportDialog(final MediaItem mediaItem) {
        final ListDialog listDialog = new ListDialog(getActivity(), new ActionItem[]{new ActionItem(0, 0, (int) R.string.download_error), new ActionItem(1, 0, (int) R.string.not_matched), new ActionItem(2, 0, (int) R.string.low_quality)}, (BaseDialog.InterfaceC1064a<? extends ListDialog>) null, (BaseDialog.InterfaceC1064a<? extends ListDialog>) null);
        listDialog.m7254b(R.string.cancel, null);
        listDialog.setTitle(R.string.report_picture_error);
        listDialog.m6844a(new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.fragment.main.PortraitPlayerFragment.3
            @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
            /* renamed from: a */
            public void mo5409a(ActionItem actionItem, int i) {
                ReportTask.EnumC2096a enumC2096a = ReportTask.EnumC2096a.REPORT_NO_CONTENT_STATE;
                if (i == 1) {
                    enumC2096a = ReportTask.EnumC2096a.REPORT_NO_MATCH_STATE;
                } else if (i == 2) {
                    enumC2096a = ReportTask.EnumC2096a.REPORT_LOW_QUALITY_STATE;
                }
                CommandCenter.getInstance().postInvokeResult(new Command(CommandID.REPORT_LYRIC_PICTURE, ReportTask.EnumC2097b.REPORT_TYPE_LYRIC, enumC2096a, mediaItem));
                PopupsUtils.m6760a((int) R.string.thank_you_for_join);
                listDialog.dismiss();
            }
        });
        listDialog.show();
    }

    public void onToggleMenuView(boolean z) {
        if (this.mDropDownMenu != null && this.mDropDownMenu.isShowing()) {
            this.mDropDownMenu.dismiss();
            this.mDropDownMenu = null;
            return;
        }
        this.mDropDownMenu = ActionBarFragment.popupMenu(getActivity(), null, z, this);
    }

    @Override // com.sds.android.ttpod.fragment.base.OnDropdownMenuClickListener
    public void onDropDownMenuClicked(int i, ActionItem actionItem) {
        this.mDropDownMenu = null;
        switch (i) {
            case 1:
                EntryUtils.m8287h(getActivity());
                return;
            case 2:
                EntryUtils.m8292c((Context) getActivity());
                return;
            case 3:
                EntryUtils.m8296b();
                return;
            default:
                return;
        }
    }
}
