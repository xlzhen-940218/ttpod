package com.sds.android.ttpod.framework.support.search.task;

import android.content.Intent;

import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.sdk.core.download.Manager;
import com.sds.android.sdk.core.download.Task;
import com.sds.android.sdk.core.download.TaskInfo;

import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.core.p113b.AutoDownloadNetworkType;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.search.p127a.SearchTaskInfoUtils;
import com.sds.android.ttpod.framework.p106a.ImageSwitcherEngine;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.search.SearchManager;
import com.sds.android.ttpod.framework.support.search.SearchStatus;
import com.sds.android.ttpod.framework.support.search.SearchTaskType;
import com.sds.android.ttpod.framework.support.search.p135a.LyrPicSearchTaskBaseInfo;
import com.sds.android.ttpod.framework.support.search.p135a.LyricSearchTaskInfo;
import com.sds.android.ttpod.framework.support.search.p135a.PictureSearchTaskInfo;
import com.sds.android.ttpod.media.mediastore.MediaItem;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.sds.android.ttpod.framework.support.search.task.a */
/* loaded from: classes.dex */
public abstract class LyrPicBaseSearchTask implements Runnable {

    /* renamed from: a */
    private int f7297a = 0;

    /* renamed from: b */
    private ArrayList<String> f7298b = new ArrayList<>();

    /* renamed from: a */
    protected abstract String buildUrl();

    /* renamed from: a */
    protected abstract String getMediaItem(MediaItem mediaItem);

    /* renamed from: a */
    protected abstract ArrayList<ResultData> mo2140a(KXmlParser kXmlParser) throws Exception;

    /* renamed from: a */
    protected abstract ArrayList<String> mo2135a(String str, String str2);

    /* renamed from: a */
    protected abstract void startDownloadLyric(List<ResultData> list);

    /* renamed from: a */
    protected abstract boolean mo2133a(ArrayList<String> arrayList);

    /* renamed from: b */
    protected abstract LyrPicSearchTaskBaseInfo getLyricSearchTaskInfo();

    /* renamed from: b */
    protected abstract String mo2129b(MediaItem mediaItem);

    @Override // java.lang.Runnable
    public void run() {
        String mo2129b;
        this.f7298b.clear();
        try {
            LyrPicSearchTaskBaseInfo lyricSearchTaskInfo = getLyricSearchTaskInfo();
            LogUtils.info("LyrPicBaseSearchTask", "in run lookLyricPic begin task search title=%s", lyricSearchTaskInfo.getTitle());
            if (!mediaItemNotNull(lyricSearchTaskInfo)) {
                throw new IllegalArgumentException("search no invalid args");
            }
            if (lyricSearchTaskInfo.isAuto()) {
                searchLyricOrPicture(true, lyricSearchTaskInfo);
                return;
            }
            m2155b(lyricSearchTaskInfo, SearchStatus.SEARCH_LOCAL_STARTED);
            MediaItem mediaItem = lyricSearchTaskInfo.getMediaItem();
            if (lyricSearchTaskInfo.getSearchTaskType() == SearchTaskType.PICTURE_SEARCH_TASK_TYPE && Preferences.m3072E() && (mo2129b = mo2129b(mediaItem)) != null) {
                this.f7298b.add(mo2129b);
                m2154b(lyricSearchTaskInfo, SearchStatus.SEARCH_LOCAL_FINISHED, null, this.f7298b, 0);
                m2164a(SearchManager.SEARCH_LOCAL_FINISHED);
                return;
            }
            ArrayList<String> m2151e = m2151e();
            if (m2151e != null && !m2151e.isEmpty()) {
                this.f7298b.addAll(m2151e);
                m2154b(lyricSearchTaskInfo, SearchStatus.SEARCH_LOCAL_FINISHED, null, this.f7298b, 0);
                if (lyricSearchTaskInfo.m2211a()) {
                    m2158a(m2151e, lyricSearchTaskInfo.getSearchTaskType() == SearchTaskType.PICTURE_SEARCH_TASK_TYPE);
                    return;
                } else if (lyricSearchTaskInfo.m2203c() || mo2133a(m2151e)) {
                    m2164a(SearchManager.SEARCH_LOCAL_FINISHED);
                    return;
                } else {
                    searchLyricOrPicture(false, lyricSearchTaskInfo);
                    return;
                }
            }
            m2155b(lyricSearchTaskInfo, SearchStatus.SEARCH_LOCAL_FAILURE);
            if (!lyricSearchTaskInfo.m2203c()) {
                searchLyricOrPicture(false, lyricSearchTaskInfo);
            }
        } catch (Exception e) {
            LyrPicSearchTaskBaseInfo mo2131b2 = getLyricSearchTaskInfo();
            MediaItem m2194i2 = mo2131b2.getMediaItem();
            Object[] objArr = new Object[3];
            objArr[0] = mo2131b2 instanceof LyricSearchTaskInfo ? "lyric" : User.KEY_AVATAR;
            objArr[1] = m2194i2 != null ? m2194i2.getTitle() : null;
            objArr[2] = e.toString();
            LogUtils.error("LyrPicBaseSearchTask", "in run searchTask lookLyricPic type=%s title=%s exception=%s", objArr);
            m2155b(mo2131b2, SearchStatus.SEARCH_DOWNLOAD_FAILURE);
            m2164a(SearchManager.SEARCH_ONLINE_FAILURE);
        }
    }

    /* renamed from: a */
    private void m2158a(ArrayList<String> arrayList, boolean z) {
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            LogUtils.debug("LyrPicBaseSearchTask", "removeResult %s", next);
            FileUtils.exists(next);
        }
        if (z) {
            FileUtils.exists(new File(arrayList.get(0)).getParentFile());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m2164a(SearchStatus searchStatus) {
        LyrPicSearchTaskBaseInfo lyricSearchTaskInfo = getLyricSearchTaskInfo();
        SearchManager.m2232a().m2223a(lyricSearchTaskInfo.getMediaItem().getID(), lyricSearchTaskInfo.getSearchTaskType() == SearchTaskType.PICTURE_SEARCH_TASK_TYPE ? "picture_type" : "lyric_type", searchStatus);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m2155b(LyrPicSearchTaskBaseInfo lyrPicSearchTaskBaseInfo, SearchStatus searchStatus) {
        m2154b(lyrPicSearchTaskBaseInfo, searchStatus, null, null, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m2154b(LyrPicSearchTaskBaseInfo lyrPicSearchTaskBaseInfo, SearchStatus searchStatus, ArrayList<ResultData> arrayList, ArrayList<String> arrayList2, int i) {
        if (!lyrPicSearchTaskBaseInfo.m2206b() && !lyrPicSearchTaskBaseInfo.m2211a()) {
            Object[] objArr = new Object[4];
            objArr[0] = lyrPicSearchTaskBaseInfo instanceof LyricSearchTaskInfo ? "lyric" : "picture";
            objArr[1] = searchStatus.name();
            objArr[2] = lyrPicSearchTaskBaseInfo.getMediaItem().getTitle();
            objArr[3] = Integer.valueOf(arrayList2 != null ? arrayList2.size() : 0);
            LogUtils.debug("LyrPicBaseSearchTask", "notifySearchTaskStatusChanged lookLyricPic looklyric type=%s SearchStatus=%s title=%s dAmount=%d", objArr);
            MediaItem m2194i = lyrPicSearchTaskBaseInfo.getMediaItem();
            if (lyrPicSearchTaskBaseInfo instanceof LyricSearchTaskInfo) {
                m2166a(lyrPicSearchTaskBaseInfo, searchStatus, "lyric", i);
            } else if (lyrPicSearchTaskBaseInfo instanceof PictureSearchTaskInfo) {
                if (searchStatus == SearchStatus.SEARCH_LOCAL_FINISHED || searchStatus == SearchStatus.SEARCH_DOWNLOAD_FINISHED || searchStatus == SearchStatus.SEARCH_LOCAL_FAILURE) {
                    ImageSwitcherEngine.m4724d().m4729a(m2194i.getID(), arrayList2);
                }
                m2166a(lyrPicSearchTaskBaseInfo, searchStatus, "picture", i);
            }
            m2159a(m2194i.getID(), lyrPicSearchTaskBaseInfo instanceof LyricSearchTaskInfo ? "lyric_type" : "picture_type", searchStatus, arrayList, arrayList2);
        }
    }

    /* renamed from: a */
    private static void m2159a(String str, String str2, SearchStatus searchStatus, ArrayList<ResultData> arrayList, ArrayList<String> arrayList2) {
        Intent intent = new Intent(Action.LYRIC_PIC_OPERATE_RESULT);
        intent.putExtra("media_id", str);
        intent.putExtra("type", str2);
        intent.putExtra("state", searchStatus);
        if (arrayList != null) {
            intent.putParcelableArrayListExtra("search_result_list", arrayList);
        }
        if (arrayList2 != null) {
            intent.putStringArrayListExtra("download_result_list", arrayList2);
        }
        BaseApplication.getApplication().sendBroadcast(intent);
    }

    /* renamed from: a */
    private static void m2166a(LyrPicSearchTaskBaseInfo lyrPicSearchTaskBaseInfo, SearchStatus searchStatus, String str, int i) {
        MediaItem m2194i = lyrPicSearchTaskBaseInfo.getMediaItem();
        switch (searchStatus) {
            case SEARCH_ONLINE_NET_EXCEPTION:
            case SEARCH_ONLINE_FAILURE:
               // LyricPicStatistic.m5078a(str, m2194i.getSongID(), lyrPicSearchTaskBaseInfo.m2192k(), lyrPicSearchTaskBaseInfo.m2193j(), (Integer) 2);
                return;
            case SEARCH_ONLINE_FINISHED:
               // LyricPicStatistic.m5078a(str, m2194i.getSongID(), lyrPicSearchTaskBaseInfo.m2192k(), lyrPicSearchTaskBaseInfo.m2193j(), (Integer) 1);
                return;
            case SEARCH_DOWNLOAD_FAILURE:
               // LyricPicStatistic.m5079a(str, i, lyrPicSearchTaskBaseInfo.m2192k(), lyrPicSearchTaskBaseInfo.m2193j(), (Integer) 2);
                return;
            case SEARCH_DOWNLOAD_FINISHED:
               // LyricPicStatistic.m5079a(str, i, lyrPicSearchTaskBaseInfo.m2192k(), lyrPicSearchTaskBaseInfo.m2193j(), (Integer) 1);
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    protected boolean mediaItemNotNull(LyrPicSearchTaskBaseInfo lyrPicSearchTaskBaseInfo) {
        return lyrPicSearchTaskBaseInfo.getMediaItem() != null;
    }

    /* renamed from: a */
    protected void searchLyricOrPicture(boolean isManual, LyrPicSearchTaskBaseInfo lyrPicSearchTaskBaseInfo) throws Exception {
        m2155b(lyrPicSearchTaskBaseInfo, SearchStatus.SEARCH_ONLINE_STARTED);
        String mo2142a = buildUrl();
        if (m2153c() || !hasNetwork()) {
            LogUtils.debug("LyrPicBaseSearchTask", "searchTaskFromNetwork lookLyricPic no permission url = " + mo2142a);
            m2155b(lyrPicSearchTaskBaseInfo, SearchStatus.SEARCH_ONLINE_SETTING_EXCEPTION);
            m2164a(SearchManager.SEARCH_LOCAL_FINISHED);
            return;
        }
        LogUtils.debug("LyrPicBaseSearchTask", "searchTaskFromNetwork lookLyricPic list url = " + mo2142a);
        ArrayList<ResultData> resultDataArrayList = requestResultDataArrayList(mo2142a);
        if (resultDataArrayList == null) {
            LogUtils.debug("LyrPicBaseSearchTask", "searchTaskFromNetwork lookLyricPic getResultDataList url = %s, net exception", mo2142a);
            m2155b(lyrPicSearchTaskBaseInfo, SearchStatus.SEARCH_ONLINE_NET_EXCEPTION);
            m2164a(SearchManager.SEARCH_ONLINE_FAILURE);
        } else if (resultDataArrayList.isEmpty()) {
            LogUtils.debug("LyrPicBaseSearchTask", "searchTaskFromNetwork lookLyricPic getResultDataList url = %s, server no result", mo2142a);
            m2155b(lyrPicSearchTaskBaseInfo, SearchStatus.SEARCH_ONLINE_FAILURE);
            m2164a(SearchManager.SEARCH_LOCAL_FINISHED);
        } else if (isManual) {
            LogUtils.debug("LyrPicBaseSearchTask", "searchTaskFromNetwork lookLyricPic getResultDataList url = %s, manual result_group=%d", mo2142a, Integer.valueOf(resultDataArrayList.size()));
            m2154b(lyrPicSearchTaskBaseInfo, SearchStatus.SEARCH_ONLINE_FINISHED, resultDataArrayList, null, 0);
            m2164a(SearchManager.SEARCH_LOCAL_FINISHED);
        } else {
            LogUtils.debug("LyrPicBaseSearchTask", "searchTaskFromNetwork lookLyricPic getResultDataList url = %s, auto result_group=%d", mo2142a, Integer.valueOf(resultDataArrayList.size()));
            m2155b(lyrPicSearchTaskBaseInfo, SearchStatus.SEARCH_ONLINE_FINISHED);
            startDownloadLyric(resultDataArrayList);
        }
    }

    /* renamed from: c */
    protected boolean m2153c() {
        return false;
    }

    /* renamed from: d */
    protected boolean hasNetwork() {
        AutoDownloadNetworkType autoDownloadNetworkType = Preferences.getDownloadLyricNetWorkType();
        int m8476d = EnvironmentUtils.DeviceConfig.getNetworkType();
        if (getLyricSearchTaskInfo().isAuto() || ((AutoDownloadNetworkType.WIFI == autoDownloadNetworkType && 2 == m8476d) || AutoDownloadNetworkType.ALL == autoDownloadNetworkType)) {
            return m8476d == 2 || !Preferences.m3066H() || (getLyricSearchTaskInfo().isAuto() && Preferences.m3066H());
        }
        return false;
    }

    /*  JADX ERROR: IF instruction can be used only in fallback mode
        jadx.core.utils.exceptions.CodegenException: IF instruction can be used only in fallback mode
        	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:686)
        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:544)
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.dex.regions.Region.generate(Region.java:35)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
        	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:175)
        	at jadx.core.dex.regions.loops.LoopRegion.generate(LoopRegion.java:171)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.dex.regions.Region.generate(Region.java:35)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
        	at jadx.core.codegen.RegionGen.makeCatchBlock(RegionGen.java:365)
        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:313)
        	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.dex.regions.Region.generate(Region.java:35)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.dex.regions.Region.generate(Region.java:35)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.dex.regions.Region.generate(Region.java:35)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:296)
        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:275)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:377)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:306)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:272)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
        */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x004a, code lost:
        if (r1.isEmpty() == false) goto L15;
     */
    /* JADX WARN: Removed duplicated region for block: B:107:0x014e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:119:0x004c A[EDGE_INSN: B:119:0x004c->B:15:0x004c ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0092 A[Catch: all -> 0x0184, Exception -> 0x018a, TRY_LEAVE, TryCatch #15 {Exception -> 0x018a, all -> 0x0184, blocks: (B:34:0x007f, B:36:0x0092, B:74:0x0152), top: B:115:0x007f }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0152 A[Catch: all -> 0x0184, Exception -> 0x018a, TRY_ENTER, TRY_LEAVE, TryCatch #15 {Exception -> 0x018a, all -> 0x0184, blocks: (B:34:0x007f, B:36:0x0092, B:74:0x0152), top: B:115:0x007f }] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0068 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected ArrayList<ResultData> requestResultDataArrayList(String url) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void downloadLyric(final ResultData.Item item, final boolean z) {
        DebugUtils.m8426a(item, "resultDataItem");
        LogUtils.debug("LyrPicBaseSearchTask", "doDownload add for execute lookLyricPic url = " + item.getUrl());
        Manager.getInstance().start("lyrics_picture_file_download", new TaskInfo(item.getUrl(), item.getLocalLyricPath(), false), new Task.TaskCallback() { // from class: com.sds.android.ttpod.framework.support.search.task.a.1
            @Override // com.sds.android.sdk.core.download.Task.AbstractC0578a
            /* renamed from: b */
            public void start(TaskInfo taskInfo) {
                super.start(taskInfo);
                LogUtils.debug("LyrPicBaseSearchTask", "down lookLyricPic start %s", taskInfo.getSavePath());
                LyrPicBaseSearchTask.m2155b(LyrPicBaseSearchTask.this.getLyricSearchTaskInfo(), SearchStatus.SEARCH_DOWNLOAD_STARTED);
            }

            @Override // com.sds.android.sdk.core.download.Task.AbstractC0578a
            /* renamed from: c */
            public void downloaded(TaskInfo taskInfo) {
                super.downloaded(taskInfo);
                LogUtils.debug("LyrPicBaseSearchTask", "down finish lookLyricPic %s", taskInfo.getSavePath());
                synchronized (LyrPicBaseSearchTask.this.f7298b) {
                    LyrPicBaseSearchTask.this.f7298b.add(taskInfo.getSavePath());
                    LyrPicBaseSearchTask.m2154b(LyrPicBaseSearchTask.this.getLyricSearchTaskInfo(), SearchStatus.SEARCH_DOWNLOAD_FINISHED, null, LyrPicBaseSearchTask.this.f7298b, item.getId());
                }
                if (z) {
                    LyrPicBaseSearchTask.this.m2164a(SearchManager.SEARCH_ONLINE_FINISHED);
                }
            }

            @Override // com.sds.android.sdk.core.download.Task.AbstractC0578a
            /* renamed from: a */
            public void error(TaskInfo taskInfo, Task.ErrorCodeType enumC0579b) {
                LogUtils.debug("LyrPicBaseSearchTask", "down error lookLyricPic %s msg=%s", taskInfo.getSavePath(), enumC0579b.name());
                LyrPicBaseSearchTask.m2154b(LyrPicBaseSearchTask.this.getLyricSearchTaskInfo(), SearchStatus.SEARCH_DOWNLOAD_FAILURE, null, null, item.getId());
                LyrPicSearchTaskBaseInfo mo2131b = LyrPicBaseSearchTask.this.getLyricSearchTaskInfo();
                String sourceUrl = taskInfo.getSourceUrl();
                if (mo2131b instanceof LyricSearchTaskInfo) {
                    LyrPicBaseSearchTask.m2156b(taskInfo, "error", enumC0579b, "lyric_type");
                    //ErrorStatistic.m5242a(sourceUrl);
                } else if (mo2131b instanceof PictureSearchTaskInfo) {
                    LyrPicBaseSearchTask.m2156b(taskInfo, "error", enumC0579b, "picture_type");
                    //ErrorStatistic.m5237b(sourceUrl);
                }
                if (z) {
                    LyrPicBaseSearchTask.this.m2164a(SearchManager.SEARCH_ONLINE_FAILURE);
                }
            }
        });
    }

    /* renamed from: e */
    protected ArrayList<String> m2151e() {
        String title = getLyricSearchTaskInfo().getMediaItem().getTitle();
        String artist = getLyricSearchTaskInfo().getMediaItem().getArtist();
        new ArrayList();
        if (title == null) {
            title = getLyricSearchTaskInfo().getSongInfo()[1];
        }
        if (artist == null) {
            artist = "";
        }
        ArrayList<String> mo2135a = mo2135a(title, artist);
        LogUtils.info("LyrPicBaseSearchTask", "searchTaskFromLocal lookLyricPic title=%s firstPath=%s", title, (mo2135a == null || mo2135a.isEmpty()) ? null : mo2135a.get(0));
        return mo2135a;
    }

    /* renamed from: a */
    public static void m2160a(final String str, final ResultData.Item item, MediaItem mediaItem) {
        final LyrPicSearchTaskBaseInfo m3886b;
        if ("lyric_type".equals(str)) {
            m3886b = SearchTaskInfoUtils.m3888a(mediaItem);
            String m2172d = item.getLocalLyricPath();
            if (m2172d != null && m2172d.endsWith(".lrc")) {
                FileUtils.exists(m2172d.replace(".lrc", ".trc"));
            }
        } else {
            m3886b = SearchTaskInfoUtils.m3886b(mediaItem);
        }
        m3886b.setTitle(mediaItem.getTitle());
        m3886b.setSinger(mediaItem.getArtist());
        TaskInfo taskInfo = new TaskInfo(item.getUrl(), item.getLocalLyricPath(), false);
        Task.TaskCallback abstractC0578a = new Task.TaskCallback() { // from class: com.sds.android.ttpod.framework.support.search.task.a.2
            @Override // com.sds.android.sdk.core.download.Task.AbstractC0578a
            /* renamed from: b */
            public void start(TaskInfo taskInfo2) {
                super.start(taskInfo2);
                LogUtils.info("LyrPicBaseSearchTask", "doDownloadResultItem lookLyricPic onStarted down type=%s url=%s", str, item.getUrl());
            }

            @Override // com.sds.android.sdk.core.download.Task.AbstractC0578a
            /* renamed from: c */
            public void downloaded(TaskInfo taskInfo2) {
                super.downloaded(taskInfo2);
                LogUtils.info("LyrPicBaseSearchTask", "doDownloadResultItem lookLyricPic onFinished down type=%s url=%s", str, item.getUrl());
                ArrayList arrayList = new ArrayList();
                arrayList.add(item.getLocalLyricPath());
                LyrPicBaseSearchTask.m2154b(m3886b, SearchStatus.SEARCH_DOWNLOAD_FINISHED, null, arrayList, item.getId());
            }

            @Override // com.sds.android.sdk.core.download.Task.AbstractC0578a
            /* renamed from: a */
            public void error(TaskInfo taskInfo2, Task.ErrorCodeType enumC0579b) {
                String sourceUrl = taskInfo2.getSourceUrl();
                LogUtils.info("LyrPicBaseSearchTask", "doDownloadResultItem lookLyricPic onError down type=%s url=%s", str, sourceUrl);
                LyrPicBaseSearchTask.m2154b(m3886b, SearchStatus.SEARCH_DOWNLOAD_FAILURE, null, null, item.getId());
                if ("lyric_type".equals(str)) {
                    //ErrorStatistic.m5242a(sourceUrl);
                    LyrPicBaseSearchTask.m2156b(taskInfo2, "error", enumC0579b, str);
                    return;
                }
                //ErrorStatistic.m5237b(sourceUrl);
                LyrPicBaseSearchTask.m2156b(taskInfo2, "error", enumC0579b, str);
            }
        };
        LogUtils.info("LyrPicBaseSearchTask", "doDownloadResultItem lookLyricPic will execute down type=%s url=%s", str, item.getUrl());
        Manager.getInstance().start("lyrics_picture_file_download", taskInfo, abstractC0578a);
        m2155b(m3886b, SearchStatus.SEARCH_DOWNLOAD_STARTED);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m2156b(TaskInfo taskInfo, String str, Task.ErrorCodeType enumC0579b, String str2) {
        //SSystemEvent //SSystemEvent = //new //SSystemEvent("SYS_DOWNLOAD", str);
        //SSystemEvent.append("uri", taskInfo.getSourceUrl()).append(DownloadManagerFragment.DOWNLOAD_TYPE, str2).append("path", taskInfo.getSavePath());
        if (enumC0579b != null) {
            //SSystemEvent.append("error_code", enumC0579b);
        }
        //SSystemEvent.post();
    }

    protected String requestData(String url) {
        String response = null;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setRequestMethod("GET");
            response = new String(getBytesByInputStream(httpURLConnection.getInputStream()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private byte[] getBytesByInputStream(InputStream is) {
        byte[] bytes = null;
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(baos);
        byte[] buffer = new byte[1024 * 8];
        int length = 0;
        try {
            while ((length = bis.read(buffer)) > 0) {
                bos.write(buffer, 0, length);
            }
            bos.flush();
            bytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bytes;
    }
}
