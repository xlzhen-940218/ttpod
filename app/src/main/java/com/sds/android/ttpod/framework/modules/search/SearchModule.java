package com.sds.android.ttpod.framework.modules.search;

import android.content.Intent;
import android.graphics.Bitmap;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.api.AssociateWordsAPI;
import com.sds.android.cloudapi.ttpod.api.BillboardAPI;
import com.sds.android.cloudapi.ttpod.api.HotWordsAPI;
import com.sds.android.cloudapi.ttpod.api.OnlineMediaSearchAPI;
import com.sds.android.cloudapi.ttpod.result.AlbumItemsResult;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseModule;
import com.sds.android.ttpod.framework.base.ObserverCommandID;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.modules.ModuleRequestHelper;
import com.sds.android.ttpod.framework.modules.skin.p132d.Lyric;
import com.sds.android.ttpod.framework.modules.skin.p132d.LyricParser;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.framework.support.search.SearchStatus;
import com.sds.android.ttpod.framework.support.search.task.ReportTask;
import com.sds.android.ttpod.framework.support.search.task.ResultData;
import com.sds.android.ttpod.media.mediastore.MediaItem;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

@ObserverCommandID(m4563a = {CommandID.PLAY_MEDIA_CHANGED})
/* renamed from: com.sds.android.ttpod.framework.modules.search.a */
/* loaded from: classes.dex */
public class SearchModule extends BaseModule {
    @Override // com.sds.android.ttpod.framework.base.BaseModule
    /* renamed from: id */
    protected ModuleID id() {
        return ModuleID.SEARCH;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    protected void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        Class<?> cls = getClass();
        map.put(CommandID.REPORT_LYRIC_PICTURE, ReflectUtils.m8375a(cls, "reportLyricPicture", ReportTask.EnumC2097b.class, ReportTask.EnumC2096a.class, MediaItem.class));
        map.put(CommandID.START_SEARCH_SONG, ReflectUtils.m8375a(cls, "aggregateSearch", String.class, Integer.class, Integer.class, String.class));
        map.put(CommandID.START_SEARCH_HOT_WORDS, ReflectUtils.m8375a(cls, "searchHotWords", new Class[0]));
        map.put(CommandID.START_SEARCH_ASSOCIATE, ReflectUtils.m8375a(cls, "searchAssociateWords", String.class));
        map.put(CommandID.START_SEARCH_BILLBOARD, ReflectUtils.m8375a(cls, "searchBillboard", Integer.class));
        map.put(CommandID.START_REPORT_SONG, ReflectUtils.m8375a(cls, "reportSong", String.class));
        map.put(CommandID.START_SEARCH_RECOGNIZE, ReflectUtils.m8375a(cls, "startRecognize", new Class[0]));
        map.put(CommandID.STOP_SEARCH_RECOGNIZE, ReflectUtils.m8375a(cls, "stopRecognize", new Class[0]));
        map.put(CommandID.CANCEL_SEARCH_RECOGNIZE, ReflectUtils.m8375a(cls, "cancelRecognize", new Class[0]));
        map.put(CommandID.GET_SEARCH_RECOGNIZE_VOLUME, ReflectUtils.m8375a(cls, "getRecognizeVolume", new Class[0]));
        map.put(CommandID.PLAY_MEDIA_CHANGED, ReflectUtils.m8375a(cls, "playMediaChanged", new Class[0]));
        map.put(CommandID.START_SEARCH_ALBUM, ReflectUtils.m8375a(cls, "searchAlbum", String.class, Integer.class, Integer.class, String.class));
        map.put(CommandID.START_SEARCH_PLAY_LIST, ReflectUtils.m8375a(cls, "searchPlaylist", String.class, Integer.class, Integer.class, String.class));
        map.put(CommandID.RECEIVED_SEARCH_EVENT, ReflectUtils.m8375a(cls, "onReceive", Intent.class));
        map.put(CommandID.GET_SEARCH_TYPES, ReflectUtils.m8375a(cls, "getSearchTypes", new Class[0]));
        map.put(CommandID.START_SEARCH_LYRIC, ReflectUtils.m8375a(cls, "searchLyric", MediaItem.class, String.class, String.class));
        map.put(CommandID.START_SEARCH_PICTURE, ReflectUtils.m8375a(cls, "searchPicture", MediaItem.class, String.class, String.class));
        map.put(CommandID.START_DOWNLOAD_SEARCH_PICTURE, ReflectUtils.m8375a(cls, "downloadPicture", String.class, String.class, MediaItem.class));
        map.put(CommandID.START_DOWNLOAD_SEARCH_LYRIC, ReflectUtils.m8375a(cls, "downloadLyric", ResultData.Item.class, MediaItem.class));
    }

    public void reportLyricPicture(ReportTask.EnumC2097b enumC2097b, ReportTask.EnumC2096a enumC2096a, MediaItem mediaItem) {
        TaskScheduler.start(new ReportTask(enumC2097b, enumC2096a, mediaItem));
    }

    public void aggregateSearch(final String str, final Integer num, final Integer num2, final String str2) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.search.a.1
            @Override // java.lang.Runnable
            public void run() {
                String m2945az = Preferences.m2945az();
                if (m2945az == null) {
                    m2945az = "";
                }
                LogUtils.debug("SearchModule", "search song searchWord=" + str + ",page=" + num + ",size=" + num2 + ",clientId=" + m2945az + ",sugg=" + str2);
                OnlineMediaItemsResult m8531f = OnlineMediaSearchAPI.m8860a(str, num.intValue(), num2.intValue(), m2945az).execute();
                int pages = m8531f.getPages();
                if (m8531f.isSuccess()) {
                    ArrayList<OnlineMediaItem> dataList = m8531f.getDataList();
                    ArrayList arrayList = new ArrayList(dataList.size());
                    for (OnlineMediaItem onlineMediaItem : dataList) {
                        arrayList.add(MediaItemUtils.m4716a(onlineMediaItem));
                    }
                    CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_SEARCH_SONG_FINISHED, Integer.valueOf(m8531f.getCode()), Integer.valueOf(pages), arrayList, str), ModuleID.SEARCH);
                }
            }
        });
    }

    public void searchAlbum(final String str, final Integer num, final Integer num2, final String str2) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.search.a.3
            @Override // java.lang.Runnable
            public void run() {
                Request<AlbumItemsResult> m8858b;
                if (StringUtils.isEmpty(str2)) {
                    m8858b = OnlineMediaSearchAPI.m8859b(str, num.intValue(), num2.intValue());
                } else {
                    m8858b = OnlineMediaSearchAPI.m8858b(str, num.intValue(), num2.intValue(), str2);
                }
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_SEARCH_ALBUM_FINISHED, m8858b.execute()), ModuleID.SEARCH);
            }
        });
    }

    public void searchPlaylist(final String str, final Integer num, final Integer num2, final String str2) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.search.a.4
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_SEARCH_PLAY_LIST_RESULT, OnlineMediaSearchAPI.m8857c(str, num.intValue(), num2.intValue(), str2 == null ? "" : str2).execute()), ModuleID.SEARCH);
            }
        });
    }

    public void searchHotWords() {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.search.a.5
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_SEARCH_HOT_WORDS_FINISHED, HotWordsAPI.m8884a().execute().getDataList()), ModuleID.SEARCH);
            }
        });
    }

    public void searchAssociateWords(final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.search.a.6
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_SEARCH_ASSOCIATE_FINISHED, AssociateWordsAPI.m8935a(str).execute().getDataList()), ModuleID.SEARCH);
            }
        });
    }

    public void searchBillboard(final Integer num) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.search.a.7
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_SEARCH_BILLBOARD_FINISHED, BillboardAPI.m8934a(num.intValue()).execute().getDataList()), ModuleID.SEARCH);
            }
        });
    }

    public void reportSong(final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.search.a.8
            @Override // java.lang.Runnable
            public void run() {
                BaseResult m8531f = OnlineMediaSearchAPI.m8862a(str).execute();
                CommandCenter m4607a = CommandCenter.getInstance();
                CommandID commandID = CommandID.UPDATE_REPORT_SONG_FINISHED;
                Object[] objArr = new Object[1];
                objArr[0] = Boolean.valueOf(m8531f.getCode() == 1);
                m4607a.m4595b(new Command(commandID, objArr), ModuleID.SEARCH);
            }
        });
    }

    public void startRecognize() {
        SoundRecognizer.m3878b().m3877c();
    }

    public void stopRecognize() {
        SoundRecognizer.m3878b().m3876d();
    }

    public void cancelRecognize() {
        SoundRecognizer.m3878b().m3875e();
    }

    public Double getRecognizeVolume() {
        return Double.valueOf(SoundRecognizer.m3878b().m3874f());
    }

    public void onReceive(Intent intent) {
        Object[] objArr = new Object[1];
        objArr[0] = intent != null ? intent.getAction() : "intent=null";
        LogUtils.debug("SearchModule", "onReceive artistPic lookLyricPic action=%s", objArr);
        String action = intent.getAction();
        if (Action.LYRIC_PIC_OPERATE_RESULT.equals(action)) {
            m3923a(intent, intent.getStringExtra("media_id"));
        } else if (Action.SWITCH_ARTIST_BITMAP.equals(action)) {
            String stringExtra = intent.getStringExtra("media_id");
            String stringExtra2 = intent.getStringExtra("path");
            if (FileUtils.m8414b(stringExtra2)) {
                m3918a(stringExtra, stringExtra2);
            }
        }
    }

    /* renamed from: a */
    private void m3918a(final String str, final String str2) {
        TaskScheduler.m8582a(new TaskScheduler.AbstractAsyncTaskC0595a<Void, Bitmap>(null) { // from class: com.sds.android.ttpod.framework.modules.search.a.9
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public Bitmap mo1981a(Void r3) {
                return SearchModule.this.m3919a(str2);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
            /* renamed from: a */
            public void postExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    CommandCenter.getInstance().m4595b(new Command(CommandID.SWITCH_ARTIST_PICTURE, str, str2, bitmap), ModuleID.SEARCH);
                }
            }
        });
    }

    /* renamed from: a */
    private void m3923a(Intent intent, String str) {
        String stringExtra = intent.getStringExtra("type");
        SearchStatus searchStatus = (SearchStatus) intent.getSerializableExtra("state");
        ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("search_result_list");
        if (m3921a(searchStatus)) {
            ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("download_result_list");
            if (stringArrayListExtra != null && !stringArrayListExtra.isEmpty()) {
                if ("lyric_type".equals(stringExtra)) {
                    m3917b(searchStatus, str, stringArrayListExtra);
                    return;
                } else {
                    m3920a(searchStatus, str, stringArrayListExtra);
                    return;
                }
            }
            LogUtils.error("SearchModule", "handleLyricPictureIntent lookLyricPic and %s but no result, should not appear.", searchStatus.name());
            return;
        }
        CommandCenter.getInstance().m4595b(new Command("lyric_type".equals(stringExtra) ? CommandID.UPDATE_SEARCH_LYRIC_STATE : CommandID.UPDATE_SEARCH_PICTURE_STATE, searchStatus, parcelableArrayListExtra, str, null), ModuleID.SEARCH);
    }

    /* renamed from: a */
    private void m3920a(final SearchStatus searchStatus, final String str, ArrayList<String> arrayList) {
        MediaItem m3225N = Cache.getInstance().getCurrentPlayMediaItem();
        String str2 = (arrayList == null || arrayList.isEmpty()) ? null : arrayList.get(0);
        if (StringUtils.equals(m3225N.getID(), str)) {
            Cache m3218a = Cache.getInstance();
            if (!StringUtils.equals(m3218a.m3164g(), str2)) {
                m3218a.m3205a(str2, m3225N);
            } else {
                return;
            }
        }
        LogUtils.debug("SearchModule", "asyncLoadPicture artistPic lookLyricPic will begin path=%s", str2);
        final String str3 = str2;
        TaskScheduler.m8582a(new TaskScheduler.AbstractAsyncTaskC0595a<String, Bitmap>(str2) { // from class: com.sds.android.ttpod.framework.modules.search.a.10
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public Bitmap mo1981a(String str4) {
                LogUtils.debug("SearchModule", "asyncLoadPicture artistPic lookLyricPic now begin path=%s", str4);
                return SearchModule.this.m3919a(str4);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
            /* renamed from: a */
            public void postExecute(Bitmap bitmap) {
                Object[] objArr = new Object[2];
                objArr[0] = str3;
                objArr[1] = Boolean.valueOf(bitmap != null);
                LogUtils.debug("SearchModule", "asyncLoadPicture artistPic lookLyricPic end path=%s, result!=null:%b", objArr);
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_SEARCH_PICTURE_STATE, searchStatus, null, str, bitmap), ModuleID.SEARCH);
            }

            @Override // android.os.AsyncTask
            protected void onCancelled() {
                LogUtils.debug("SearchModule", "asyncLoadPicture lartistPic ookLyricPic onCancelled path=%s", str3);
            }
        });
    }

    /* renamed from: b */
    private void m3917b(final SearchStatus searchStatus, final String str, ArrayList<String> arrayList) {
        final String str2 = arrayList.get(0);
        MediaItem m3225N = Cache.getInstance().getCurrentPlayMediaItem();
        if (StringUtils.equals(m3225N.getID(), str)) {
            Cache.getInstance().m3191b(str2, m3225N);
        }
        LogUtils.debug("SearchModule", "asyncLoadLyric lookLyricPic will begin path=%s", str2);
        TaskScheduler.m8582a(new TaskScheduler.AbstractAsyncTaskC0595a<Void, Lyric>(null) { // from class: com.sds.android.ttpod.framework.modules.search.a.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public Lyric mo1981a(Void r6) {
                LogUtils.debug("SearchModule", "asyncLoadLyric lookLyricPic now begin path=%s", str2);
                return LyricParser.m3647b(str2);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
            /* renamed from: a */
            public void postExecute(Lyric lyric) {
                Object[] objArr = new Object[2];
                objArr[0] = str2;
                objArr[1] = Boolean.valueOf(lyric != null);
                LogUtils.debug("SearchModule", "asyncLoadLyric lookLyricPic end path=%s, result!=null:%b", objArr);
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_SEARCH_LYRIC_STATE, searchStatus, null, str, lyric), ModuleID.SEARCH);
            }

            @Override // android.os.AsyncTask
            protected void onCancelled() {
                LogUtils.debug("SearchModule", "asyncLoadLyric lookLyricPic onCancelled path=%s", str2);
            }
        });
    }

    /* renamed from: a */
    private boolean m3921a(SearchStatus searchStatus) {
        return searchStatus == SearchStatus.SEARCH_LOCAL_FINISHED || searchStatus == SearchStatus.SEARCH_DOWNLOAD_FINISHED;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public synchronized Bitmap m3919a(String str) {
        return ImageCacheUtils.m4745a(str, DisplayUtils.m7225c(), DisplayUtils.m7224d(), false);
    }

    public void playMediaChanged() {
        Cache.getInstance().m3167f();
        Cache.getInstance().m3161h();
    }

    public void getSearchTypes() {
        ModuleRequestHelper.m4083a(OnlineMediaSearchAPI.m8863a(), CommandID.UPDATE_SEARCH_TYPES, id(), null);
    }

    public void downloadPicture(String str, String str2, MediaItem mediaItem) {
    }

    public void downloadLyric(ResultData.Item item, MediaItem mediaItem) {
        SupportFactory.getInstance(sContext).m2495a(item, mediaItem);
    }

    public void searchPicture(MediaItem mediaItem, String str, String str2) {
        SupportFactory.getInstance(sContext).m2494a(mediaItem, str, str2);
    }

    public void searchLyric(MediaItem mediaItem, String singer, String songName) {
        SupportFactory.getInstance(sContext).m2480b(mediaItem, singer, songName);
    }
}
