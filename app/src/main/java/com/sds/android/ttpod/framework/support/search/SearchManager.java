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
    public static final SearchStatus f7251a = SearchStatus.SEARCH_ONLINE_FINISHED;

    /* renamed from: b */
    public static final SearchStatus f7252b = SearchStatus.SEARCH_ONLINE_FAILURE;

    /* renamed from: c */
    public static final SearchStatus f7253c = SearchStatus.SEARCH_LOCAL_FINISHED;

    /* renamed from: d */
    private static SearchManager f7254d = null;

    /* renamed from: f */
    private InterfaceC2089b f7256f;

    /* renamed from: h */
    private List<String> f7258h;

    /* renamed from: i */
    private int f7259i;

    /* renamed from: j */
    private int f7260j;

    /* renamed from: k */
    private int f7261k;

    /* renamed from: l */
    private int f7262l;

    /* renamed from: m */
    private int f7263m;

    /* renamed from: n */
    private String f7264n;

    /* renamed from: o */
    private boolean f7265o;

    /* renamed from: p */
    private boolean f7266p;

    /* renamed from: q */
    private SearchStatus f7267q;

    /* renamed from: r */
    private SearchStatus f7268r;

    /* renamed from: s */
    private Handler f7269s;

    /* renamed from: e */
    private String f7255e = "";

    /* renamed from: g */
    private C2088a f7257g = new C2088a();

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
            this.f7269s = new Handler() { // from class: com.sds.android.ttpod.framework.support.search.a.1
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
            if (f7254d == null) {
                f7254d = new SearchManager();
                Manager.m8744a().m8742a("lyrics_picture_file_download", 3);
                ImageSwitcherEngine.m4724d().m4722e();
                f7254d.m2218d();
            }
        }
        return f7254d;
    }

    /* renamed from: b */
    public void m2222b() {
        ImageSwitcherEngine.m4724d().m4721f();
        Manager.m8744a().m8739b("lyrics_picture_file_download");
        BaseApplication.getApplication().unregisterReceiver(this.f7257g);
        this.f7257g = null;
        f7254d = null;
    }

    private SearchManager() {
        BaseApplication.getApplication().registerReceiver(this.f7257g, this.f7257g.m2212a());
    }

    /* renamed from: a */
    public void m2230a(InterfaceC2089b interfaceC2089b) {
        this.f7256f = interfaceC2089b;
    }

    /* renamed from: c */
    public String m2219c() {
        return this.f7255e;
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
            LogUtils.m8380c("SearchManager", "handleIntent lookLyricPic command=%s type=%s artist=%s title=%s onlyLocal=%b mediaItemNotNull:%b", objArr);
            if (stringExtra2 == null) {
                m2227a(mediaItem);
                return true;
            } else if (booleanExtra && "picture_type".equals(stringExtra2)) {
                m2213i();
                return true;
            } else if (mediaItem != null) {
                if ("lyric_type".equals(stringExtra2)) {
                    m2225a(mediaItem, stringExtra3, stringExtra4);
                    TaskScheduler.m8581a(new LyricSearchTask(SearchTaskInfoUtils.m3887a(mediaItem, stringExtra4, stringExtra3)));
                    return true;
                } else if ("picture_type".equals(stringExtra2)) {
                    m2226a(mediaItem, stringExtra3);
                    TaskScheduler.m8581a(new PictureSearchTask(SearchTaskInfoUtils.m3885b(mediaItem, stringExtra4, stringExtra3)));
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
            LogUtils.m8380c("SearchManager", "handleIntent lookLyricPic command=%s type=%s mediaItemNotNull:%b", objArr2);
            if (mediaItem2 != null) {
                LyrPicBaseSearchTask.m2160a(stringExtra2, item, mediaItem2);
                return true;
            }
            return true;
        } else if ("batch_search_lyric_pic_command".equals(stringExtra)) {
            String stringExtra5 = intent.getStringExtra("type");
            LogUtils.m8386a("SearchManager", "handleIntent BATCH_SEARCH_LYRIC_PIC_COMMAND type=%s", stringExtra5);
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
                TaskScheduler.m8581a(new LyricSearchTask(SearchTaskInfoUtils.m3884c(mediaItem3)));
                return true;
            } else if ("picture_type".equals(stringExtra2)) {
                TaskScheduler.m8581a(new PictureSearchTask(SearchTaskInfoUtils.m3883d(mediaItem3)));
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
        this.f7263m = this.f7258h.size();
        if (this.f7263m == 0) {
            this.f7258h = null;
            m2214h();
            return;
        }
        this.f7259i = -1;
        this.f7260j = 0;
        this.f7261k = 0;
        this.f7262l = 0;
        if (this.f7269s != null) {
            this.f7269s.sendEmptyMessage(1);
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
        while (this.f7258h != null && this.f7259i < this.f7263m && (mediaItem = MediaStorage.queryMediaItem(BaseApplication.getApplication(), MediaStorage.GROUP_ID_ALL_LOCAL, this.f7258h.get(this.f7259i))) == null) {
            this.f7262l++;
            this.f7259i++;
        }
        this.f7265o = false;
        this.f7266p = false;
        this.f7264n = mediaItem.getID();
        LyricSearchTaskInfo m3888a = SearchTaskInfoUtils.m3888a(mediaItem);
        m3888a.m2204b(true);
        TaskScheduler.m8581a(new LyricSearchTask(m3888a));
        PictureSearchTaskInfo m3886b = SearchTaskInfoUtils.m3886b(mediaItem);
        m3886b.m2204b(true);
        TaskScheduler.m8581a(new PictureSearchTask(m3886b));
    }

    /* renamed from: g */
    private void m2215g() {
        this.f7258h = null;
        if (this.f7269s != null) {
            this.f7269s.removeMessages(1);
        }
        m2214h();
    }

    /* renamed from: h */
    private void m2214h() {
        Intent intent = new Intent(Action.LYRIC_PIC_BATCH_OPERATE_RESULT);
        intent.putExtra("state", this.f7258h != null);
        intent.putExtra("total_count", this.f7263m);
        intent.putExtra("success_count", this.f7260j);
        intent.putExtra("failed_count", this.f7261k);
        intent.putExtra("skip_count", this.f7262l);
        BaseApplication.getApplication().sendBroadcast(intent);
    }

    /* renamed from: i */
    private void m2213i() {
        MediaItem queryMediaItem = MediaStorage.queryMediaItem(BaseApplication.getApplication(), Preferences.m2858m(), Preferences.m2854n());
        if (queryMediaItem != null && !queryMediaItem.isNull()) {
            LogUtils.m8380c("SearchManager", "searchLocalArtistPicture lookLyricPic create local pic search task, artist=%s title=%s", queryMediaItem.getArtist(), queryMediaItem.getTitle());
            PictureSearchTaskInfo m3886b = SearchTaskInfoUtils.m3886b(queryMediaItem);
            m3886b.m2201c(true);
            TaskScheduler.m8581a(new PictureSearchTask(m3886b));
        }
    }

    /* renamed from: a */
    public void m2227a(MediaItem mediaItem) {
        if (mediaItem == null) {
            mediaItem = MediaStorage.queryMediaItem(BaseApplication.getApplication(), Preferences.m2858m(), Preferences.m2854n());
        }
        if (mediaItem != null && !mediaItem.isNull()) {
            ImageSwitcherEngine.m4724d().m4726c();
            LogUtils.m8380c("SearchManager", "searchLyricPicture lookLyricPic create lyric pic search task, artist=%s title=%s", mediaItem.getArtist(), mediaItem.getTitle());
            m2225a(mediaItem, (String) null, (String) null);
            TaskScheduler.m8581a(new LyricSearchTask(SearchTaskInfoUtils.m3888a(mediaItem)));
            m2226a(mediaItem, (String) null);
            TaskScheduler.m8581a(new PictureSearchTask(SearchTaskInfoUtils.m3886b(mediaItem)));
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
        LogUtils.m8386a("SearchManager", "logForSearchLyric lookLyricPic manual=%b artist=%s title=%s", objArr);
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
        LogUtils.m8386a("SearchManager", "logForSearchPicture lookLyricPic manual=%b artist=%s title=%s", objArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SearchManager.java */
    /* renamed from: com.sds.android.ttpod.framework.support.search.a$a */
    /* loaded from: classes.dex */
    public class C2088a extends BroadcastReceiver {
        private C2088a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ArrayList<String> stringArrayListExtra;
            if (intent != null) {
                if (Action.PLAY_MEDIA_CHANGED.equals(intent.getAction())) {
                    LogUtils.m8388a("SearchManager", "SearchManager LyricPicSearchMonitor PLAY_MEDIA_CHANGED lookLyricPic");
                    SearchManager.m2232a().m2227a((MediaItem) null);
                } else if (Action.LYRIC_PIC_OPERATE_RESULT.equals(intent.getAction())) {
                    String stringExtra = intent.getStringExtra("type");
                    SearchStatus searchStatus = (SearchStatus) intent.getSerializableExtra("state");
                    String stringExtra2 = intent.getStringExtra("media_id");
                    if ((searchStatus == SearchStatus.SEARCH_LOCAL_FINISHED || searchStatus == SearchStatus.SEARCH_DOWNLOAD_FINISHED) && (stringArrayListExtra = intent.getStringArrayListExtra("download_result_list")) != null && !stringArrayListExtra.isEmpty()) {
                        if ("picture_type".equals(stringExtra)) {
                            SearchManager.this.f7255e = intent.getStringExtra("media_id") + stringArrayListExtra.get(0);
                            MediaItem m2606g = Player.m2611e().m2606g();
                            if (m2606g != null && StringUtils.m8344a(m2606g.getID(), stringExtra2)) {
                                Preferences.m3011a(stringArrayListExtra.get(0), m2606g);
                            }
                            SearchManager.this.f7256f.mo2190b();
                        } else if ("lyric_type".equals(stringExtra)) {
                            String str = stringArrayListExtra.get(0);
                            MediaItem m2606g2 = Player.m2611e().m2606g();
                            if (m2606g2 != null && StringUtils.m8344a(m2606g2.getID(), stringExtra2)) {
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
        if (this.f7269s != null && this.f7258h != null && str != null && StringUtils.m8344a(str, this.f7264n)) {
            synchronized (f7254d) {
                m2220b(str, str2, searchStatus);
            }
        }
    }

    /* renamed from: b */
    private void m2220b(String str, String str2, SearchStatus searchStatus) {
        MediaItem queryMediaItem;
        if (this.f7258h != null && str != null && StringUtils.m8344a(str, this.f7264n)) {
            if (EnvironmentUtils.C0602a.m8503h() && (queryMediaItem = MediaStorage.queryMediaItem(BaseApplication.getApplication(), MediaStorage.GROUP_ID_ALL_LOCAL, str)) != null) {
                LogUtils.m8386a("SearchManager", "dealBatchItemSearchState type=%s status=%s mediaId=%s artist=%s title=%s", str2, searchStatus, str, queryMediaItem.getArtist(), queryMediaItem.getTitle());
            }
            if ("lyric_type".equals(str2)) {
                this.f7265o = true;
                this.f7267q = searchStatus;
            } else {
                this.f7266p = true;
                this.f7268r = searchStatus;
            }
            if (m2224a(str)) {
                if (this.f7267q == f7251a || this.f7268r == f7251a) {
                    this.f7260j++;
                } else if (this.f7267q == f7252b || this.f7268r == f7252b) {
                    this.f7261k++;
                } else {
                    this.f7262l++;
                }
                m2214h();
                if (this.f7269s != null) {
                    this.f7269s.sendEmptyMessage(1);
                }
            }
        }
    }

    /* renamed from: a */
    private boolean m2224a(String str) {
        return this.f7266p && this.f7265o;
    }
}
