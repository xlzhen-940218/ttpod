package com.sds.android.ttpod.activities.ktv;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.MessageDialog;
import com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.DraggableMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.MediaListFragment;
import com.sds.android.ttpod.fragment.main.list.SubMediaListFragment;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.p106a.DownloadUtils;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.utils.GroupItemUtils;
import com.sds.android.ttpod.utils.ThemeUtils;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class KtvActivity extends SlidingClosableActivity implements View.OnClickListener, KtvConnectCallback {
    private static final int KEY_CLICK_KTV_CONNECT = 167;
    private static final int KEY_CLICK_KTV_CONNECT_SUCCESS = 168;
    private static final int KEY_CLICK_KTV_DOWNLOAD = 166;
    private static final int KEY_CLICK_KTV_PLAY = 165;
    private static final String LOG_TAG = "KtvActivity";
    private static final String REQUEST_AD = "http://api.busdh.com/market-api/api/sdk?sdk_category=%d&version=%s";
    private static final String TAG_APK_URL = "apk_url";
    private static final String TAG_DATA = "data";
    private static final long TIME_DELAY_DISMISS = 500;
    private static final long TIME_UPDATE_PROGRESS = 300;
    private static String mApkUrl;
    private DownloadProgressDialog mDownloadDialog;
    private SubMediaListFragment mFragment;
    private KtvMediaListFragment mKtvMediaListFragment;
    private View mViewKtvConnect;
    private View mViewKtvSelectSong;
    private View mViewPlayAllSong;
    private DownloadTaskInfo mDownloadTaskInfo = null;
    private volatile boolean mIsStopDownloading = true;
    private volatile boolean mIsRequestingApkUrl = false;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setPage(SPage.PAGE_KTV);
        setTitle(R.string.my_ktv);
        tryCreateKtvGroup(this);
        hideActionBar();
        setContentView(R.layout.ktv_main);
        initBackground();
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        this.mFragment = new SubMediaListFragment() { // from class: com.sds.android.ttpod.activities.ktv.KtvActivity.1
            @Override // com.sds.android.ttpod.fragment.main.list.SubMediaListFragment
            protected MediaListFragment initMediaListFragment() {
                KtvActivity.this.mKtvMediaListFragment = new KtvMediaListFragment();
                KtvActivity.this.mKtvMediaListFragment.setArguments(getArguments());
                getChildFragmentManager().beginTransaction().replace(R.id.content_local_media_list, KtvActivity.this.mKtvMediaListFragment).commitAllowingStateLoss();
                return KtvActivity.this.mKtvMediaListFragment;
            }

            @Override // com.sds.android.ttpod.fragment.main.list.SubMediaListFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment
            public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle2) {
                super.onCreateContentView(layoutInflater, viewGroup, bundle2);
                return layoutInflater.inflate(R.layout.ktv_fragment_local_sub_media_list, (ViewGroup) null);
            }

            @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment
            public void updateBackground(Drawable drawable) {
            }

            @Override // com.sds.android.ttpod.fragment.main.list.SubMediaListFragment, com.sds.android.ttpod.fragment.main.list.IEditAble.InterfaceC1677a
            public void onStopEditRequested() {
                super.onStopEditRequested();
                KtvActivity.this.updateView();
            }

            @Override // com.sds.android.ttpod.fragment.main.list.SubMediaListFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.fragment.base.OnDropdownMenuClickListener
            public void onDropDownMenuClicked(int i, ActionItem actionItem) {
                if (i == 17) {
                    String string = getArguments().getString(AbsMediaListFragment.KEY_GROUP_ID);
                    if (KtvActivity.this.mKtvMediaListFragment instanceof DraggableMediaListFragment) {
                        KtvActivity.this.mKtvMediaListFragment.addMedia(KtvActivity.hasLocalMedia());
                    }
                    if (GroupItemUtils.m8267b(string)) {
                        //LocalStatistic.m5120am();
                        return;
                    }
                    return;
                }
                super.onDropDownMenuClicked(i, actionItem);
            }
        };
        Bundle bundle2 = new Bundle();
        bundle2.putString(SubMediaListFragment.KEY_GROUP_NAME, getString(R.string.my_ktv));
        bundle2.putString(AbsMediaListFragment.KEY_GROUP_ID, MediaStorage.GROUP_ID_KTV);
        this.mFragment.setArguments(bundle2);
        beginTransaction.replace(R.id.layout_fragment, this.mFragment);
        beginTransaction.commit();
        KtvSongControl.m8118a().m8114a((KtvConnectCallback) this);
        bindView();
    }

    private void initBackground() {
        Drawable m8182a = ThemeUtils.m8182a();
        if (m8182a != null) {
            findViewById(R.id.layout_bkg_ktv).setBackgroundDrawable(m8182a.getConstantState().newDrawable());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        updateView();
    }

    public void updateView() {
        if (this.mKtvMediaListFragment.isEmpty()) {
            this.mViewKtvSelectSong.setVisibility(View.VISIBLE);
            this.mViewKtvConnect.setVisibility(View.GONE);
            this.mViewPlayAllSong.setVisibility(View.GONE);
        } else if (!KtvSongControl.m8118a().m8104c()) {
            this.mViewKtvSelectSong.setVisibility(View.GONE);
            this.mViewKtvConnect.setVisibility(View.VISIBLE);
            this.mViewPlayAllSong.setVisibility(View.GONE);
        } else {
            this.mViewKtvSelectSong.setVisibility(View.GONE);
            this.mViewKtvConnect.setVisibility(View.GONE);
            this.mViewPlayAllSong.setVisibility(View.VISIBLE);
        }
    }

    private void bindView() {
        this.mViewKtvSelectSong = findViewById(R.id.layout_add_media);
        this.mViewKtvConnect = findViewById(R.id.layout_connect_ktv);
        this.mViewPlayAllSong = findViewById(R.id.layout_play_all_song);
        this.mViewKtvSelectSong.setOnClickListener(this);
        this.mViewKtvConnect.setOnClickListener(this);
        this.mViewPlayAllSong.setOnClickListener(this);
    }

    private void tryCreateKtvGroup(Context context) {
        try {
            if (!MediaStorage.isGroupExisted(BaseApplication.getApplication(), MediaStorage.GROUP_ID_KTV)) {
                MediaStorage.insertGroup(BaseApplication.getApplication(), context.getString(R.string.my_ktv), MediaStorage.GROUP_ID_KTV, GroupType.CUSTOM_LOCAL);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private boolean handlerStopEdit() {
        if (PopupsUtils.m6716b()) {
            this.mFragment.onStopEditRequested();
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        handlerStopEdit();
    }

    @Override // com.sds.android.ttpod.activities.ktv.KtvConnectCallback
    public void success() {
        updateView();
    }

    @Override // com.sds.android.ttpod.activities.ktv.KtvConnectCallback
    public void fail() {
        updateView();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtils.debug(LOG_TAG, "onActivityResult requestCode=" + i + " resultCode=" + i2 + " " + intent);
        if (i == 8 && i2 == 2) {
            String stringExtra = intent.getStringExtra("code");
            LogUtils.debug(LOG_TAG, "onActivityResult code=" + stringExtra);
            KtvSongControl.m8118a().m8116a(this, stringExtra);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_ALL_UPGRADE_PROGRESS_INFO, ReflectUtils.m8375a(getClass(), "updateProgressInfo", DownloadTaskInfo.class));
    }

    public void showDownloadDialog() {
        MessageDialog messageDialog = new MessageDialog(this, "需要安装KTV插件(大约120kb)才能使用，要下载吗?", (BaseDialog.InterfaceC1064a<MessageDialog>) null, (BaseDialog.InterfaceC1064a<MessageDialog>) null);
        messageDialog.setTitle(R.string.prompt_title);
        messageDialog.m7261a(R.string.ok, new BaseDialog.InterfaceC1064a<MessageDialog>() { // from class: com.sds.android.ttpod.activities.ktv.KtvActivity.2
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(MessageDialog messageDialog2) {
                if (EnvironmentUtils.C0604c.m8474e()) {
                    KtvActivity.this.startDownload();
                    KtvActivity.this.startDownloadDialog();
                    //SUserUtils.m4951b("PAGE_CLICK", SAction.ACTION_KTV_DOWNLOAD_PLUGIN, SPage.PAGE_KTV, SPage.PAGE_NONE);
                    return;
                }
                PopupsUtils.m6760a((int) R.string.network_unavailable);
            }
        }, R.string.cancel, (BaseDialog.InterfaceC1064a) null);
        messageDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startDownloadDialog() {
        if (this.mDownloadDialog == null) {
            this.mDownloadDialog = new DownloadProgressDialog(this, new BaseDialog.InterfaceC1064a() { // from class: com.sds.android.ttpod.activities.ktv.KtvActivity.3
                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a */
                public void mo2038a(Object obj) {
                    if (KtvActivity.this.mIsRequestingApkUrl) {
                        KtvActivity.this.mIsStopDownloading = true;
                        return;
                    }
                    if (KtvActivity.this.mDownloadTaskInfo != null) {
                        CommandCenter.getInstance().m4606a(new Command(CommandID.DELETE_DOWNLOAD_TASK, KtvActivity.this.mDownloadTaskInfo, Boolean.TRUE));
                    }
                    KtvActivity.this.mDownloadDialog.dismiss();
                }
            }, null);
        }
        this.mDownloadDialog.m8125a(0, 0);
        this.mDownloadDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startDownload() {
        TaskScheduler.m8580a(new Runnable() { // from class: com.sds.android.ttpod.activities.ktv.KtvActivity.4
            @Override // java.lang.Runnable
            public void run() {
                KtvActivity.this.mIsRequestingApkUrl = true;
                KtvActivity.this.mIsStopDownloading = false;
                KtvActivity.this.requestApkData("");
                KtvActivity.this.mIsRequestingApkUrl = false;
            }
        }, new Runnable() { // from class: com.sds.android.ttpod.activities.ktv.KtvActivity.5
            @Override // java.lang.Runnable
            public void run() {
                if (!KtvActivity.this.mIsStopDownloading) {
                    KtvActivity.this.downLoadApk(KtvActivity.mApkUrl, "Ktv");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean requestApkData(String str) {
        String format = String.format(REQUEST_AD, 11, str);
        HttpRequest.C0586a m8708a = HttpRequest.m8708a(new HttpGet(format), (HashMap<String, Object>) null, (HashMap<String, Object>) null);
        JSONObject m8393a = m8708a != null ? JSONUtils.create(m8708a.m8688e()) : null;
        if (m8393a != null) {
            mApkUrl = getApkUrl(m8393a);
        } else {
            //ErrorStatistic.m5235d(format);
            //ErrorStatistic.m5239a("ad_sdk", format);
        }
        return false;
    }

    private String getApkUrl(JSONObject jSONObject) {
        try {
            return jSONObject.getJSONArray("data").getJSONObject(0).getString(TAG_APK_URL);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.mViewKtvSelectSong) {
            this.mKtvMediaListFragment.addMedia(hasLocalMedia());
            //SUserUtils.m4951b("PAGE_CLICK", SAction.ACTION_KTV_SELECT_MUSIC, SPage.PAGE_KTV, SPage.PAGE_KTV_CHOOSE_MEDIA);
        } else if (view == this.mViewKtvConnect) {
            if (!KtvSongControl.m8108b(this)) {
                showDownloadDialog();
                return;
            }
            KtvSongControl.m8118a();
            KtvSongControl.m8117a((Context) this);
            //StatisticUtils.m4917a(167, (int) 65537, 1L);
            //SUserUtils.m4951b("PAGE_CLICK", SAction.ACTION_KTV_CONNECTION, SPage.PAGE_KTV, SPage.PAGE_KTV_CONNECTION);
        } else if (view == this.mViewPlayAllSong) {
            playAllKtvSong();
        }
    }

    private void playAllKtvSong() {
        List<MediaItem> mediaItemList = this.mKtvMediaListFragment.getMediaItemList();
        ArrayList arrayList = new ArrayList();
        if (mediaItemList != null) {
            for (MediaItem mediaItem : mediaItemList) {
                arrayList.add(new KtvSongInfo(mediaItem.getTitle(), mediaItem.getArtist()));
            }
        }
        if (arrayList.size() > 0) {
            KtvSongControl.m8118a().m8115a(this, arrayList);
        } else {
            this.mKtvMediaListFragment.addMedia(hasLocalMedia());
        }
    }

    private String getSavePath(String str, String str2) {
        return TTPodConfig.m5285w() + File.separator + str2 + str.hashCode() + ".apk";
    }

    public static boolean hasLocalMedia() {
        return MediaStorage.queryMediaCount(BaseApplication.getApplication(), MediaStorage.GROUP_ID_ALL_LOCAL, Preferences.m2860l(MediaStorage.GROUP_ID_ALL_LOCAL)) > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downLoadApk(String str, String str2) {
        if (StringUtils.isEmpty(str)) {
            PopupsUtils.m6721a("无法获取插件下载地址！");
            return;
        }
        //StatisticUtils.m4910a("ktv", "click", "download");
        String savePath = getSavePath(str, str2);
        LogUtils.debug(LOG_TAG, "downLoadApk savePath=" + savePath + " url=" + str);
        DownloadTaskInfo m4760a = DownloadUtils.m4760a(str, savePath, 0L, FileUtils.getFilename(savePath), DownloadTaskInfo.TYPE_PLUGIN, true, "connect");
        m4760a.setTag(str);
        this.mDownloadTaskInfo = m4760a;
        CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_SHOW_DOWNLOAD_PROGRESS, Boolean.FALSE), ModuleID.VERSION);
        TaskScheduler.m8581a(new Runnable() { // from class: com.sds.android.ttpod.activities.ktv.KtvActivity.6
            /* JADX WARN: Can't wrap try/catch for region: R(9:9|(5:36|37|38|40|27)(1:15)|16|(2:31|(1:35))|22|23|24|26|27) */
            /* JADX WARN: Code restructure failed: missing block: B:37:0x010d, code lost:
                r0 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:38:0x010e, code lost:
                r0.printStackTrace();
                r8.f2631a.mIsStopDownloading = true;
             */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void run() {
                KtvActivity.this.mIsStopDownloading = false;
                if (FileUtils.m8419a(KtvActivity.this.mDownloadTaskInfo.getSavePath())) {
                    FileUtils.m8404h(KtvActivity.this.mDownloadTaskInfo.getSavePath());
                }
                CommandCenter.getInstance().m4596b(new Command(CommandID.ADD_DOWNLOAD_TASK, KtvActivity.this.mDownloadTaskInfo));
                while (!KtvActivity.this.mIsStopDownloading) {
                    if (KtvActivity.this.mDownloadTaskInfo == null) {
                        KtvActivity.this.mIsStopDownloading = true;
                        return;
                    }
                    if (KtvActivity.this.mDownloadTaskInfo.getState() == null || KtvActivity.this.mDownloadTaskInfo.getState().intValue() == 0 || 1 == KtvActivity.this.mDownloadTaskInfo.getState().intValue()) {
                        try {
                            Thread.sleep(1000L);
                        } catch (Exception e) {
                            e.printStackTrace();
                            KtvActivity.this.mIsStopDownloading = true;
                        }
                    }
                    CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_ALL_UPGRADE_PROGRESS_INFO, KtvActivity.this.mDownloadTaskInfo), ModuleID.VERSION);
                    if (5 == KtvActivity.this.mDownloadTaskInfo.getState().intValue() || 3 == KtvActivity.this.mDownloadTaskInfo.getState().intValue() || 4 == KtvActivity.this.mDownloadTaskInfo.getState().intValue()) {
                        KtvActivity.this.mIsStopDownloading = true;
                        if (4 == KtvActivity.this.mDownloadTaskInfo.getState().intValue() && !KtvSongControl.m8108b(KtvActivity.this)) {
                            FragmentLoaderActivity.installPlugin(KtvActivity.this, KtvActivity.this.mDownloadTaskInfo.getSavePath());
                        }
                    }
                    try {
                        Thread.sleep(KtvActivity.TIME_UPDATE_PROGRESS);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public void onDetachedFromWindow() {
        this.mIsStopDownloading = true;
        super.onDetachedFromWindow();
    }

    private void updateProgress(DownloadTaskInfo downloadTaskInfo) {
        if (this.mIsStopDownloading) {
            this.mIsStopDownloading = false;
        }
        if (this.mDownloadDialog != null) {
            this.mDownloadDialog.m8125a(downloadTaskInfo.getDownloadLength(), downloadTaskInfo.getFileLength().intValue());
        }
    }

    public void updateProgressInfo(DownloadTaskInfo downloadTaskInfo) {
        switch (downloadTaskInfo.getState().intValue()) {
            case 0:
            case 1:
            case 2:
                if (this.mDownloadTaskInfo == null) {
                    this.mDownloadTaskInfo = downloadTaskInfo;
                    startDownloadDialog();
                }
                updateProgress(downloadTaskInfo);
                return;
            case 3:
                this.mIsStopDownloading = true;
                updateView();
                return;
            case 4:
                PopupsUtils.m6721a("下载完成！");
                updateProgress(downloadTaskInfo);
                new Handler().postDelayed(new Runnable() { // from class: com.sds.android.ttpod.activities.ktv.KtvActivity.7
                    @Override // java.lang.Runnable
                    public void run() {
                        if (KtvActivity.this.mDownloadDialog != null) {
                            KtvActivity.this.mDownloadDialog.dismiss();
                        }
                    }
                }, TIME_DELAY_DISMISS);
                if (!KtvSongControl.m8108b(this)) {
                    FragmentLoaderActivity.installPlugin(this, downloadTaskInfo.getSavePath());
                }
                this.mIsStopDownloading = true;
                updateView();
                return;
            default:
                return;
        }
    }
}
