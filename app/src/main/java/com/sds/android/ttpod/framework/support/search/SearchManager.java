package com.sds.android.ttpod.framework.support.search;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.sds.android.sdk.core.download.Manager;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.search.p127a.SearchTaskInfoUtils;
import com.sds.android.ttpod.framework.p106a.ImageSwitcherEngine;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.minilyric.MiniLyricManager;
import com.sds.android.ttpod.framework.support.p134a.Player;
import com.sds.android.ttpod.framework.support.search.p135a.LyricSearchTaskInfo;
import com.sds.android.ttpod.framework.support.search.p135a.PictureSearchTaskInfo;
import com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask;
import com.sds.android.ttpod.framework.support.search.task.LyricSearchTask;
import com.sds.android.ttpod.framework.support.search.task.PictureSearchTask;
import com.sds.android.ttpod.framework.support.search.task.ResultData;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;

import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sds.android.ttpod.framework.support.search.a */
/* loaded from: classes.dex */
public final class SearchManager {

    /* renamed from: a */
    public static final SearchStatus SEARCH_ONLINE_FINISHED = SearchStatus.SEARCH_ONLINE_FINISHED;

    /* renamed from: b */
    public static final SearchStatus SEARCH_ONLINE_FAILURE = SearchStatus.SEARCH_ONLINE_FAILURE;

    /* renamed from: c */
    public static final SearchStatus SEARCH_LOCAL_FINISHED = SearchStatus.SEARCH_LOCAL_FINISHED;

    /* renamed from: d */
    private static SearchManager searchManager = null;

    /* renamed from: f */
    private InterfaceC2089b f7256f;

    /* renamed from: h */
    private List<String> f7258h;

    /* renamed from: i */
    private int f7259i;

    /* renamed from: j */
    private int successCount;

    /* renamed from: k */
    private int failedCount;

    /* renamed from: l */
    private int skipCount;

    /* renamed from: m */
    private int totalCount;

    /* renamed from: n */
    private String mediaItemId;

    /* renamed from: o */
    private boolean f7265o;

    /* renamed from: p */
    private boolean f7266p;

    /* renamed from: q */
    private SearchStatus lyricSearchStatus;

    /* renamed from: r */
    private SearchStatus pictureSearchStatus;

    /* renamed from: s */
    private Handler handler;

    /* renamed from: e */
    private String mediaId = "";

    /* renamed from: g */
    private SearchManagerReceiver searchManagerReceiver = new SearchManagerReceiver();

    /* compiled from: SearchManager.java */
    /* renamed from: com.sds.android.ttpod.framework.support.search.a$b */
    /* loaded from: classes.dex */
    public interface InterfaceC2089b {
        /* renamed from: b */
        void mo2190b();
    }

    /* renamed from: d */
    private void m2218d() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.handler = new Handler() { // from class: com.sds.android.ttpod.framework.support.search.a.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    switch (message.what) {
                        case 1:
                            SearchManager.this.m2216f();
                            return;
                        default:
                            return;
                    }
                }
            };
        }
    }

    /* renamed from: a */
    public static SearchManager m2232a() {
        synchronized (SearchManager.class) {
            if (searchManager == null) {
                searchManager = new SearchManager();
                Manager.getInstance().addThreadPool("lyrics_picture_file_download", 3);
                ImageSwitcherEngine.m4724d().m4722e();
                searchManager.m2218d();
            }
        }
        return searchManager;
    }

    /* renamed from: b */
    public void m2222b() {
        ImageSwitcherEngine.m4724d().m4721f();
        Manager.getInstance().removeThreadPoolByName("lyrics_picture_file_download");
        BaseApplication.getApplication().unregisterReceiver(this.searchManagerReceiver);
        this.searchManagerReceiver = null;
        searchManager = null;
    }

    private SearchManager() {
        BaseApplication.getApplication().registerReceiver(this.searchManagerReceiver, this.searchManagerReceiver.m2212a());
    }

    /* renamed from: a */
    public void m2230a(InterfaceC2089b interfaceC2089b) {
        this.f7256f = interfaceC2089b;
    }

    /* renamed from: c */
    public String m2219c() {
        return this.mediaId;
    }

    /* renamed from: a */
    public boolean m2231a(Intent intent) {
        if (intent == null) {
            throw new NullPointerException("intent should not be null!");
        }
        String stringExtra = intent.getStringExtra("command");
        String stringExtra2 = intent.getStringExtra("type");
        if ("search_lyric_pic_command".equals(stringExtra)) {
            String stringExtra3 = intent.getStringExtra("artist_parameter");
            String stringExtra4 = intent.getStringExtra("title_parameter");
            MediaItem mediaItem = (MediaItem) intent.getParcelableExtra("media");
            boolean booleanExtra = intent.getBooleanExtra("only_local_search_parameter", false);
            Object[] objArr = new Object[6];
            objArr[0] = stringExtra;
            objArr[1] = stringExtra2;
            objArr[2] = stringExtra3;
            objArr[3] = stringExtra4;
            objArr[4] = Boolean.valueOf(booleanExtra);
            objArr[5] = Boolean.valueOf(mediaItem != null);
            LogUtils.info("SearchManager", "handleIntent lookLyricPic command=%s type=%s artist=%s title=%s onlyLocal=%b mediaItemNotNull:%b", objArr);
            if (stringExtra2 == null) {
                m2227a(mediaItem);
                return true;
            } else if (booleanExtra && "picture_type".equals(stringExtra2)) {
                m2213i();
                return true;
            } else if (mediaItem != null) {
                if ("lyric_type".equals(stringExtra2)) {
                    m2225a(mediaItem, stringExtra3, stringExtra4);
                    TaskScheduler.start(new LyricSearchTask(SearchTaskInfoUtils.m3887a(mediaItem, stringExtra4, stringExtra3)));
                    return true;
                } else if ("picture_type".equals(stringExtra2)) {
                    m2226a(mediaItem, stringExtra3);
                    TaskScheduler.start(new PictureSearchTask(SearchTaskInfoUtils.m3885b(mediaItem, stringExtra4, stringExtra3)));
                    return true;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        } else if ("download_lyric_pic_command".equals(stringExtra)) {
            ResultData.Item item = (ResultData.Item) intent.getParcelableExtra("item");
            MediaItem mediaItem2 = (MediaItem) intent.getParcelableExtra("media");
            Object[] objArr2 = new Object[3];
            objArr2[0] = stringExtra;
            objArr2[1] = stringExtra2;
            objArr2[2] = Boolean.valueOf(mediaItem2 != null);
            LogUtils.info("SearchManager", "handleIntent lookLyricPic command=%s type=%s mediaItemNotNull:%b", objArr2);
            if (mediaItem2 != null) {
                LyrPicBaseSearchTask.m2160a(stringExtra2, item, mediaItem2);
                return true;
            }
            return true;
        } else if ("batch_search_lyric_pic_command".equals(stringExtra)) {
            String stringExtra5 = intent.getStringExtra("type");
            LogUtils.debug("SearchManager", "handleIntent BATCH_SEARCH_LYRIC_PIC_COMMAND type=%s", stringExtra5);
            if ("search".equals(stringExtra5)) {
                m2217e();
                return true;
            } else if ("stop".equals(stringExtra5)) {
                m2215g();
                return true;
            } else if ("query".equals(stringExtra5)) {
                m2214h();
                return true;
            } else {
                return true;
            }
        } else if ("remove_lyric_pic_command".equals(stringExtra)) {
            MediaItem mediaItem3 = (MediaItem) intent.getParcelableExtra("media");
            if ("lyric_type".equals(stringExtra2)) {
                TaskScheduler.start(new LyricSearchTask(SearchTaskInfoUtils.m3884c(mediaItem3)));
                return true;
            } else if ("picture_type".equals(stringExtra2)) {
                TaskScheduler.start(new PictureSearchTask(SearchTaskInfoUtils.m3883d(mediaItem3)));
                return true;
            } else {
                return true;
            }
        } else if ("resume_image_switcher".equals(stringExtra)) {
            ImageSwitcherEngine.m4724d().m4731a();
            return true;
        } else if ("pause_image_switcher".equals(stringExtra)) {
            ImageSwitcherEngine.m4724d().m4728b();
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: e */
    private void m2217e() {
        this.f7258h = MediaStorage.queryMediaIDs(BaseApplication.getApplication(), MediaStorage.GROUP_ID_ALL_LOCAL, Preferences.m2860l(MediaStorage.GROUP_ID_ALL_LOCAL));
        this.totalCount = this.f7258h.size();
        if (this.totalCount == 0) {
            this.f7258h = null;
            m2214h();
            return;
        }
        this.f7259i = -1;
        this.successCount = 0;
        this.failedCount = 0;
        this.skipCount = 0;
        if (this.handler != null) {
            this.handler.sendEmptyMessage(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x002c, code lost:
        r7.f7258h = null;
        m2214h();
        r7.f7263m = 0;
        r7.f7260j = 0;
        r7.f7261k = 0;
        r7.f7262l = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0039, code lost:
        return;
     */
    /* renamed from: f */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void m2216f() {
        this.f7259i++;
        MediaItem mediaItem = null;
        while (this.f7258h != null && this.f7259i < this.totalCount && (mediaItem = MediaStorage.queryMediaItem(BaseApplication.getApplication(), MediaStorage.GROUP_ID_ALL_LOCAL, this.f7258h.get(this.f7259i))) == null) {
            this.skipCount++;
            this.f7259i++;
        }
        this.f7265o = false;
        this.f7266p = false;
        this.mediaItemId = mediaItem.getID();
        LyricSearchTaskInfo m3888a = SearchTaskInfoUtils.m3888a(mediaItem);
        m3888a.m2204b(true);
        TaskScheduler.start(new LyricSearchTask(m3888a));
        PictureSearchTaskInfo m3886b = SearchTaskInfoUtils.m3886b(mediaItem);
        m3886b.m2204b(true);
        TaskScheduler.start(new PictureSearchTask(m3886b));
    }

    /* renamed from: g */
    private void m2215g() {
        this.f7258h = null;
        if (this.handler != null) {
            this.handler.removeMessages(1);
        }
        m2214h();
    }

    /* renamed from: h */
    private void m2214h() {
        Intent intent = new Intent(Action.LYRIC_PIC_BATCH_OPERATE_RESULT);
        intent.putExtra("state", this.f7258h != null);
        intent.putExtra("total_count", this.totalCount);
        intent.putExtra("success_count", this.successCount);
        intent.putExtra("failed_count", this.failedCount);
        intent.putExtra("skip_count", this.skipCount);
        BaseApplication.getApplication().sendBroadcast(intent);
    }

    /* renamed from: i */
    private void m2213i() {
        MediaItem queryMediaItem = MediaStorage.queryMediaItem(BaseApplication.getApplication(), Preferences.getLocalGroupId(), Preferences.getMediaId());
        if (queryMediaItem != null && !queryMediaItem.isNull()) {
            LogUtils.info("SearchManager", "searchLocalArtistPicture lookLyricPic create local pic search task, artist=%s title=%s", queryMediaItem.getArtist(), queryMediaItem.getTitle());
            PictureSearchTaskInfo m3886b = SearchTaskInfoUtils.m3886b(queryMediaItem);
            m3886b.m2201c(true);
            TaskScheduler.start(new PictureSearchTask(m3886b));
        }
    }

    /* renamed from: a */
    public void m2227a(MediaItem mediaItem) {
        if (mediaItem == null) {
            mediaItem = MediaStorage.queryMediaItem(BaseApplication.getApplication(), Preferences.getLocalGroupId(), Preferences.getMediaId());
        }
        if (mediaItem != null && !mediaItem.isNull()) {
            ImageSwitcherEngine.m4724d().m4726c();
            LogUtils.info("SearchManager", "searchLyricPicture lookLyricPic create lyric pic search task, artist=%s title=%s", mediaItem.getArtist(), mediaItem.getTitle());
            m2225a(mediaItem, (String) null, (String) null);
            TaskScheduler.start(new LyricSearchTask(SearchTaskInfoUtils.m3888a(mediaItem)));
            m2226a(mediaItem, (String) null);
            TaskScheduler.start(new PictureSearchTask(SearchTaskInfoUtils.m3886b(mediaItem)));
        }
    }

    /* renamed from: a */
    private static void m2225a(MediaItem mediaItem, String str, String str2) {
        Object[] objArr = new Object[3];
        objArr[0] = Boolean.valueOf(str != null);
        if (str == null) {
            str = mediaItem.getArtist();
        }
        objArr[1] = str;
        if (str2 == null) {
            str2 = mediaItem.getTitle();
        }
        objArr[2] = str2;
        LogUtils.debug("SearchManager", "logForSearchLyric lookLyricPic manual=%b artist=%s title=%s", objArr);
    }

    /* renamed from: a */
    private static void m2226a(MediaItem mediaItem, String str) {
        Object[] objArr = new Object[3];
        objArr[0] = Boolean.valueOf(str != null);
        if (str == null) {
            str = mediaItem.getArtist();
        }
        objArr[1] = str;
        objArr[2] = mediaItem.getTitle();
        LogUtils.debug("SearchManager", "logForSearchPicture lookLyricPic manual=%b artist=%s title=%s", objArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SearchManager.java */
    /* renamed from: com.sds.android.ttpod.framework.support.search.a$a */
    /* loaded from: classes.dex */
    public class SearchManagerReceiver extends BroadcastReceiver {
        private SearchManagerReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ArrayList<String> stringArrayListExtra;
            if (intent != null) {
                if (Action.PLAY_MEDIA_CHANGED.equals(intent.getAction())) {
                    LogUtils.debug("SearchManager", "SearchManager LyricPicSearchMonitor PLAY_MEDIA_CHANGED lookLyricPic");
                    SearchManager.m2232a().m2227a((MediaItem) null);
                } else if (Action.LYRIC_PIC_OPERATE_RESULT.equals(intent.getAction())) {
                    String stringExtra = intent.getStringExtra("type");
                    SearchStatus searchStatus = (SearchStatus) intent.getSerializableExtra("state");
                    String stringExtra2 = intent.getStringExtra("media_id");
                    if ((searchStatus == SearchStatus.SEARCH_LOCAL_FINISHED || searchStatus == SearchStatus.SEARCH_DOWNLOAD_FINISHED) && (stringArrayListExtra = intent.getStringArrayListExtra("download_result_list")) != null && !stringArrayListExtra.isEmpty()) {
                        if ("picture_type".equals(stringExtra)) {
                            SearchManager.this.mediaId = intent.getStringExtra("media_id") + stringArrayListExtra.get(0);
                            MediaItem mediaItem = Player.getInstance().getMediaItem();
                            if (mediaItem != null && StringUtils.equals(mediaItem.getID(), stringExtra2)) {
                                Preferences.m3011a(stringArrayListExtra.get(0), mediaItem);
                            }
                            SearchManager.this.f7256f.mo2190b();
                        } else if ("lyric_type".equals(stringExtra)) {
                            String str = stringArrayListExtra.get(0);
                            MediaItem m2606g2 = Player.getInstance().getMediaItem();
                            if (m2606g2 != null && StringUtils.equals(m2606g2.getID(), stringExtra2)) {
                                Preferences.m2932b(str, m2606g2);
                                MiniLyricManager.m2344a().m2331f();
                            }
                        }
                    }
                }
            }
        }

        /* renamed from: a */
        public IntentFilter m2212a() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Action.LYRIC_PIC_OPERATE_RESULT);
            intentFilter.addAction(Action.PLAY_MEDIA_CHANGED);
            return intentFilter;
        }
    }

    /* renamed from: a */
    public void m2223a(String str, String str2, SearchStatus searchStatus) {
        if (this.handler != null && this.f7258h != null && str != null && StringUtils.equals(str, this.mediaItemId)) {
            synchronized (searchManager) {
                m2220b(str, str2, searchStatus);
            }
        }
    }

    /* renamed from: b */
    private void m2220b(String str, String str2, SearchStatus searchStatus) {
        MediaItem queryMediaItem;
        if (this.f7258h != null && str != null && StringUtils.equals(str, this.mediaItemId)) {
            if (EnvironmentUtils.AppConfig.getTestMode() && (queryMediaItem = MediaStorage.queryMediaItem(BaseApplication.getApplication(), MediaStorage.GROUP_ID_ALL_LOCAL, str)) != null) {
                LogUtils.debug("SearchManager", "dealBatchItemSearchState type=%s status=%s mediaId=%s artist=%s title=%s", str2, searchStatus, str, queryMediaItem.getArtist(), queryMediaItem.getTitle());
            }
            if ("lyric_type".equals(str2)) {
                this.f7265o = true;
                this.lyricSearchStatus = searchStatus;
            } else {
                this.f7266p = true;
                this.pictureSearchStatus = searchStatus;
            }
            if (m2224a(str)) {
                if (this.lyricSearchStatus == SEARCH_ONLINE_FINISHED || this.pictureSearchStatus == SEARCH_ONLINE_FINISHED) {
                    this.successCount++;
                } else if (this.lyricSearchStatus == SEARCH_ONLINE_FAILURE || this.pictureSearchStatus == SEARCH_ONLINE_FAILURE) {
                    this.failedCount++;
                } else {
                    this.skipCount++;
                }
                m2214h();
                if (this.handler != null) {
                    this.handler.sendEmptyMessage(1);
                }
            }
        }
    }

    /* renamed from: a */
    private boolean m2224a(String str) {
        return this.f7266p && this.f7265o;
    }
}
