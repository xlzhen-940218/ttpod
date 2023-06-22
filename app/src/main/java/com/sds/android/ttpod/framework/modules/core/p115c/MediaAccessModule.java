package com.sds.android.ttpod.framework.modules.core.p115c;

import android.content.Intent;
import android.os.Parcelable;
import com.sds.android.sdk.core.download.Task;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.p065e.ThreadPool;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseModule;
import com.sds.android.ttpod.framework.base.CommonResult;
import com.sds.android.ttpod.framework.base.ObserverCommandID;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.framework.support.SupportService;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.MediaTag;
import com.sds.android.ttpod.media.mediastore.AsyncLoadMediaItemList;
import com.sds.android.ttpod.media.mediastore.ExchangeOrderEntity;
import com.sds.android.ttpod.media.mediastore.GroupItem;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ObserverCommandID(m4563a = {CommandID.DO_VERSION_COMPACT_FINISHED, CommandID.LOGIN_FINISHED, CommandID.LOGOUT_FINISHED, CommandID.PULL_FAVORITE_ONLINE_MEDIA_LIST_COMPLETE, CommandID.DOWNLOAD_STATE_CHANGED, CommandID.SCAN_FINISHED})
/* renamed from: com.sds.android.ttpod.framework.modules.core.c.a */
/* loaded from: classes.dex */
public class MediaAccessModule extends BaseModule {

    /* renamed from: a */
    private List<String> f5932a;

    /* renamed from: b */
    private ThreadPool f5933b;

    /* renamed from: c */
    private Map<String, AsyncLoadMediaItemList> f5934c = new ConcurrentHashMap();

    /* renamed from: d */
    private Map<GroupType, List<GroupItem>> f5935d = new ConcurrentHashMap();

    /* renamed from: e */
    private List<Long> f5936e = new ArrayList();

    /* renamed from: f */
    private List<String> f5937f = new ArrayList();

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    /* renamed from: id */
    protected ModuleID mo3239id() {
        return ModuleID.MEDIA_ACCESS;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public void onCreate() {
        super.onCreate();
        this.f5933b = new ThreadPool("MediaAccess", 10, 15L);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    protected void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        Class<?> cls = getClass();
        map.put(CommandID.QUERY_GROUP_ITEM_LIST, ReflectUtils.m8375a(cls, "queryGroupItemList", GroupType.class));
        map.put(CommandID.QUERY_LOCAL_AND_ONLINE_GROUP_LIST, ReflectUtils.m8375a(cls, "queryGroupItemList", new Class[0]));
        map.put(CommandID.QUERY_GROUP_ITEM_LIST_BY_AMOUNT_ORDER, ReflectUtils.m8375a(cls, "queryGroupItemListByAmountOrder", GroupType.class));
        map.put(CommandID.SEARCH_GROUP_ITEM_LIST, ReflectUtils.m8375a(cls, "searchGroupItemList", GroupType.class, String.class));
        map.put(CommandID.QUERY_MEDIA_ITEM_LIST, ReflectUtils.m8375a(cls, "queryMediaItemList", String.class, String.class));
        map.put(CommandID.QUERY_MEDIA_COUNT, ReflectUtils.m8375a(cls, "queryMediaCount", String.class));
        map.put(CommandID.QUERY_MEDIA_ITEM, ReflectUtils.m8375a(cls, "queryMediaItem", String.class, String.class));
        map.put(CommandID.QUERY_ASYNCLOAD_MEDIA_ITEM_LIST, ReflectUtils.m8375a(cls, "queryAsyncLoadMediaItemList", String.class, String.class));
        map.put(CommandID.PRELOAD_ASYNCLOAD_MEDIA_ITEM_LIST, ReflectUtils.m8375a(cls, "preLoadAsyncLoadMediaItemList", String.class, String.class));
        map.put(CommandID.SEARCH_MEDIA_LIST, ReflectUtils.m8375a(cls, "searchMediaList", String.class, String.class));
        map.put(CommandID.ADD_GROUP, ReflectUtils.m8375a(cls, "addGroup", String.class));
        map.put(CommandID.DELETE_GROUP, ReflectUtils.m8375a(cls, "deleteGroup", String.class));
        map.put(CommandID.ADD_MEDIA_ITEM, ReflectUtils.m8375a(cls, "addMediaItem", String.class, MediaItem.class));
        map.put(CommandID.DELETE_MEDIA_ITEM, ReflectUtils.m8375a(cls, "deleteMediaItem", String.class, MediaItem.class, Boolean.class));
        map.put(CommandID.ADD_MEDIA_ITEM_LIST, ReflectUtils.m8375a(cls, "addMediaItemList", String.class, Collection.class));
        map.put(CommandID.DELETE_MEDIA_ITEM_LIST, ReflectUtils.m8375a(cls, "deleteMediaItemList", String.class, Collection.class, Boolean.class));
        map.put(CommandID.UPDATE_GROUP_ITEM, ReflectUtils.m8375a(cls, "updateGroupItem", GroupItem.class));
        map.put(CommandID.UPDATE_MEDIA_ITEM, ReflectUtils.m8375a(cls, "updateMediaItem", MediaItem.class));
        map.put(CommandID.SYNC_NET_TEMPORARY_GROUP, ReflectUtils.m8375a(cls, "syncNetTemporaryGroup", List.class));
        map.put(CommandID.SYNC_NET_TEMPORARY_GROUP_WITH_NAME, ReflectUtils.m8375a(cls, "syncNetTemporaryGroupWithName", List.class, String.class));
        map.put(CommandID.APPEND_NET_TEMPORARY_MEDIA_ITEMS, ReflectUtils.m8375a(cls, "appendNetTemporaryMediaItems", List.class));
        map.put(CommandID.COMMIT_EXCHANGE_ORDER, ReflectUtils.m8375a(cls, "commitExchangeOrder", String.class, List.class));
        map.put(CommandID.ADD_GROUP_ITEM_EXCHANGE_ORDER_EVENT, ReflectUtils.m8375a(cls, "addGroupItemExchangeOrderEvent", GroupType.class, String.class, String.class));
        map.put(CommandID.COMMIT_GROUP_ITEM_EXCHANGE_ORDER, ReflectUtils.m8375a(cls, "commitGroupItemExchangeOrder", GroupType.class));
        map.put(CommandID.DELETE_PICTURE, ReflectUtils.m8375a(cls, "deletePicture", Collection.class));
        map.put(CommandID.DELETE_LYRIC, ReflectUtils.m8375a(cls, "deleteLyric", Collection.class));
        map.put(CommandID.DO_VERSION_COMPACT_FINISHED, ReflectUtils.m8375a(cls, "doVersionCompactFinished", new Class[0]));
        map.put(CommandID.LOGOUT_FINISHED, ReflectUtils.m8375a(cls, "logoutFinished", new Class[0]));
        map.put(CommandID.LOGIN_FINISHED, ReflectUtils.m8375a(cls, "loginFinished", CommonResult.class));
        map.put(CommandID.PULL_FAVORITE_ONLINE_MEDIA_LIST_COMPLETE, ReflectUtils.m8375a(cls, "pullFavoriteOnlineMediaListComplete", new Class[0]));
        map.put(CommandID.SCAN_FINISHED, ReflectUtils.m8375a(cls, "scanFinished", Integer.class));
        map.put(CommandID.DOWNLOAD_STATE_CHANGED, ReflectUtils.m8375a(cls, "downloadStateChanged", DownloadTaskInfo.class, Task.EnumC0579b.class));
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public void onDestroy() {
        super.onDestroy();
        try {
            this.f5933b.m8575b();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void downloadStateChanged(DownloadTaskInfo downloadTaskInfo, Task.EnumC0579b enumC0579b) {
        if (4 == downloadTaskInfo.getState().intValue()) {
            if ((DownloadTaskInfo.TYPE_AUDIO.equals(downloadTaskInfo.getType()) || DownloadTaskInfo.TYPE_FAVORITE_SONG.equals(downloadTaskInfo.getType()) || DownloadTaskInfo.TYPE_FAVORITE_SONG_LIST.equals(downloadTaskInfo.getType())) && MediaStorage.queryMediaItemBySongID(sContext, MediaStorage.GROUP_ID_ALL_LOCAL, downloadTaskInfo.getFileId()) != null) {
                m4269f();
                CommandCenter.m4607a().m4595b(new Command(CommandID.UPDATE_MEDIA_LIBRARY_CHANGED, MediaStorage.GROUP_ID_ALL_LOCAL), ModuleID.MEDIA_ACCESS);
            }
        }
    }

    public void queryGroupItemList(GroupType groupType) {
        CommandCenter.m4607a().m4604a(new Command(CommandID.UPDATE_GROUP_LIST, groupType, m4285a(groupType)), ModuleID.MEDIA_ACCESS);
    }

    public void queryGroupItemList() {
        List<GroupItem> m4285a = m4285a(GroupType.CUSTOM_LOCAL);
        m4285a.addAll(m4285a(GroupType.CUSTOM_ONLINE));
        CommandCenter.m4607a().m4604a(new Command(CommandID.UPDATE_LOCAL_AND_ONLINE_GROUP_LIST, m4285a), ModuleID.MEDIA_ACCESS);
    }

    public void queryGroupItemListByAmountOrder(GroupType groupType) {
        List<GroupItem> m4285a = m4285a(groupType);
        Collections.sort(m4285a, new Comparator<GroupItem>() { // from class: com.sds.android.ttpod.framework.modules.core.c.a.1
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(GroupItem groupItem, GroupItem groupItem2) {
                return groupItem2.getCount().intValue() - groupItem.getCount().intValue();
            }
        });
        CommandCenter.m4607a().m4604a(new Command(CommandID.UPDATE_GROUP_LIST, groupType, m4285a), ModuleID.MEDIA_ACCESS);
    }

    /* renamed from: a */
    private List<GroupItem> m4285a(GroupType groupType) {
        List<GroupItem> list = this.f5935d.get(groupType);
        if (list == null) {
            List<GroupItem> queryGroupItems = MediaStorage.queryGroupItems(sContext, groupType);
            if (groupType == GroupType.CUSTOM_LOCAL) {
                if (!queryGroupItems.isEmpty()) {
                    Iterator<GroupItem> it = queryGroupItems.iterator();
                    while (it.hasNext()) {
                        String groupID = it.next().getGroupID();
                        if (groupID.equals(MediaStorage.GROUP_ID_ALL_LOCAL) || groupID.equals(MediaStorage.GROUP_ID_FAV_LOCAL) || groupID.startsWith(MediaStorage.GROUP_ID_ONLINE_FAV_PREFIX) || groupID.equals(MediaStorage.GROUP_ID_EFFECT_LOCAL) || groupID.equals(MediaStorage.GROUP_ID_RECENTLY_ADD) || groupID.equals(MediaStorage.GROUP_ID_RECENTLY_PLAY) || groupID.startsWith(MediaStorage.GROUP_ID_EFFECT_ONLINE) || groupID.equals(MediaStorage.GROUP_ID_DOWNLOAD) || groupID.equals(MediaStorage.GROUP_ID_KTV)) {
                            it.remove();
                        }
                    }
                }
                Cache.m3218a().m3187b(queryGroupItems);
            } else if (groupType == GroupType.CUSTOM_ONLINE) {
                Iterator<GroupItem> it2 = queryGroupItems.iterator();
                String buildMusicCircleFavGroupIDPrefix = MediaStorage.buildMusicCircleFavGroupIDPrefix();
                while (it2.hasNext()) {
                    String groupID2 = it2.next().getGroupID();
                    if (groupID2.startsWith(MediaStorage.GROUP_ID_ONLINE_FAV_PREFIX) || groupID2.equals(MediaStorage.GROUP_ID_EFFECT_ONLINE) || (groupID2.startsWith(MediaStorage.GROUP_ID_MUSICCIRCLE_PREFIX) && !groupID2.startsWith(buildMusicCircleFavGroupIDPrefix))) {
                        it2.remove();
                    }
                }
            }
            List<GroupItem> m4284a = groupType == GroupType.CUSTOM_LOCAL ? m4284a(groupType, queryGroupItems) : queryGroupItems;
            if (groupType == GroupType.DEFAULT_ARTIST || groupType == GroupType.DEFAULT_ALBUM || groupType == GroupType.DEFAULT_FOLDER || groupType == GroupType.DEFAULT_GENRE) {
                Collections.sort(m4284a, new Comparator<GroupItem>() { // from class: com.sds.android.ttpod.framework.modules.core.c.a.3
                    @Override // java.util.Comparator
                    /* renamed from: a */
                    public int compare(GroupItem groupItem, GroupItem groupItem2) {
                        return groupItem.getNameIndexKey() - groupItem2.getNameIndexKey();
                    }
                });
                this.f5935d.put(groupType, m4284a);
                return m4284a;
            }
            return m4284a;
        }
        return new ArrayList(list);
    }

    /* renamed from: a */
    private List<GroupItem> m4284a(GroupType groupType, List<GroupItem> list) {
        List<String> m3211a = Cache.m3218a().m3211a(groupType);
        if (m3211a != null) {
            ArrayList arrayList = new ArrayList();
            for (String str : m3211a) {
                GroupItem m4278a = m4278a(list, str);
                if (m4278a != null) {
                    arrayList.add(m4278a);
                }
            }
            int i = 0;
            for (GroupItem groupItem : list) {
                arrayList.add(i, groupItem);
                i++;
            }
            list = arrayList;
        }
        ArrayList arrayList2 = new ArrayList(list.size());
        for (GroupItem groupItem2 : list) {
            arrayList2.add(groupItem2.getGroupID());
        }
        Cache.m3218a().m3210a(groupType, arrayList2);
        return list;
    }

    /* renamed from: a */
    private GroupItem m4278a(List<GroupItem> list, String str) {
        for (GroupItem groupItem : list) {
            if (str.equals(groupItem.getGroupID())) {
                list.remove(groupItem);
                return groupItem;
            }
        }
        return null;
    }

    public void searchGroupItemList(GroupType groupType, String str) {
    }

    public void queryMediaItemList(final String str, final String str2) {
        this.f5933b.m8576a(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.core.c.a.4
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.m4607a().m4595b(new Command(CommandID.UPDATE_MEDIA_LIST, str, MediaStorage.queryMediaItemList(MediaAccessModule.sContext, str, str2)), ModuleID.MEDIA_ACCESS);
                if (!str2.equals(Preferences.m2860l(str))) {
                    Preferences.m3010a(str, str2);
                    MediaAccessModule.this.m4276b(str);
                }
            }
        });
    }

    public Integer queryMediaCount(String str) {
        return Integer.valueOf(MediaStorage.queryMediaCount(sContext, str, Preferences.m2860l(str)));
    }

    public MediaItem queryMediaItem(String str, String str2) {
        return MediaStorage.queryMediaItem(sContext, str, str2);
    }

    public void preLoadAsyncLoadMediaItemList(String str, String str2) {
        m4281a(str, str2).preLoad();
    }

    public void queryAsyncLoadMediaItemList(String str, String str2) {
        CommandCenter.m4607a().m4604a(new Command(CommandID.UPDATE_ASYNCLOAD_MEDIA_ITEM_LIST, str, m4281a(str, str2)), ModuleID.MEDIA_ACCESS);
    }

    /* renamed from: a */
    private AsyncLoadMediaItemList m4281a(String str, String str2) {
        AsyncLoadMediaItemList m4275b = m4275b(str, str2);
        if (m4275b == null) {
            m4275b = MediaStorage.queryAsyncLoadMediaItemList(sContext, str, str2);
            if (!str2.equals(Preferences.m2860l(str))) {
                Preferences.m3010a(str, str2);
                m4276b(str);
            }
            if (m4275b.size() <= 400 && !StringUtils.m8344a(MediaStorage.GROUP_ID_RECENTLY_PLAY, str) && !StringUtils.m8344a(MediaStorage.GROUP_ID_FAV, str) && !StringUtils.m8344a(MediaStorage.GROUP_ID_FAV_LOCAL, str) && !str.startsWith(MediaStorage.GROUP_ID_ONLINE_FAV_PREFIX)) {
                this.f5934c.put(str, m4275b);
            }
        }
        return m4275b;
    }

    /* renamed from: b */
    private AsyncLoadMediaItemList m4275b(String str, String str2) {
        AsyncLoadMediaItemList asyncLoadMediaItemList = this.f5934c.get(str);
        if (asyncLoadMediaItemList != null && !StringUtils.m8344a(asyncLoadMediaItemList.getOrderBy(), str2)) {
            this.f5934c.remove(str);
            asyncLoadMediaItemList.clear();
            return null;
        }
        return asyncLoadMediaItemList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public void m4269f() {
        for (AsyncLoadMediaItemList asyncLoadMediaItemList : this.f5934c.values()) {
            asyncLoadMediaItemList.clear();
        }
        this.f5934c.clear();
        this.f5935d.clear();
    }

    /* renamed from: a */
    private void m4282a(String str) {
        AsyncLoadMediaItemList asyncLoadMediaItemList = this.f5934c.get(str);
        if (asyncLoadMediaItemList != null) {
            asyncLoadMediaItemList.clear();
        }
        this.f5934c.remove(str);
        this.f5935d.clear();
    }

    public void searchMediaList(String str, String str2) {
    }

    public String addGroup(String str) {
        String buildGroupID = MediaStorage.buildGroupID();
        MediaStorage.insertGroup(sContext, str, buildGroupID, GroupType.CUSTOM_LOCAL);
        queryGroupItemList(GroupType.CUSTOM_LOCAL);
        return buildGroupID;
    }

    public void deleteGroup(String str) {
        m4269f();
        MediaStorage.deleteGroup(sContext, str);
        if (StringUtils.m8344a(str, Preferences.m2858m())) {
            m4268g();
        }
        queryGroupItemList(str.startsWith(MediaStorage.GROUP_ID_MUSICCIRCLE_PREFIX) ? GroupType.CUSTOM_ONLINE : GroupType.CUSTOM_LOCAL);
    }

    public void addMediaItem(String str, MediaItem mediaItem) {
        MediaStorage.insertMediaItem(sContext, str, mediaItem);
        m4276b(str);
        CommandCenter.m4607a().m4595b(new Command(CommandID.UPDATE_MEDIA_LIBRARY_CHANGED, m4272c(str)), ModuleID.MEDIA_ACCESS);
        m4269f();
    }

    public void deleteMediaItem(String str, MediaItem mediaItem, Boolean bool) {
        DebugUtils.m8426a(mediaItem, "mediaItem");
        DebugUtils.m8426a(bool, "deleteFile");
        ArrayList arrayList = new ArrayList();
        arrayList.add(mediaItem);
        m4280a(str, arrayList, bool);
    }

    public void addMediaItemList(final String str, Collection<MediaItem> collection) {
        final ArrayList<MediaItem> arrayList = new ArrayList<>(collection);
        this.f5933b.m8576a(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.core.c.a.5
            @Override // java.lang.Runnable
            public void run() {
                ArrayList arrayList2 = new ArrayList();
                List<String> queryMediaIDs = MediaStorage.queryMediaIDs(MediaAccessModule.sContext, str, Preferences.m2860l(str));
                for (MediaItem mediaItem : arrayList) {
                    if (!queryMediaIDs.contains(mediaItem.getID())) {
                        LogUtils.m8381c("Favorite", "title" + mediaItem.getTitle());
                        arrayList2.add(mediaItem);
                    }
                }
                if (!arrayList2.isEmpty()) {
                    MediaStorage.insertMediaItems(MediaAccessModule.sContext, str, arrayList2);
                }
                MediaAccessModule.this.m4269f();
                CommandCenter.m4607a().m4595b(new Command(CommandID.UPDATE_MEDIA_LIBRARY_CHANGED, str), ModuleID.MEDIA_ACCESS);
                MediaAccessModule.this.m4276b(str);
            }
        });
    }

    public void deleteMediaItemList(final String str, Collection<MediaItem> collection, final Boolean bool) {
        DebugUtils.m8426a((Object) collection, "mediaItems");
        DebugUtils.m8426a(bool, "deleteFile");
        final ArrayList arrayList = new ArrayList(collection);
        this.f5933b.m8576a(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.core.c.a.6
            @Override // java.lang.Runnable
            public void run() {
                MediaAccessModule.this.m4280a(str, arrayList, bool);
            }
        });
    }

    public void commitExchangeOrder(final String str, List<ExchangeOrderEntity> list) {
        if (list != null && !list.isEmpty()) {
            final ArrayList arrayList = new ArrayList(list);
            this.f5933b.m8576a(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.core.c.a.7
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (MediaAccessModule.this) {
                        MediaStorage.exchangeMediaItemOrder(MediaAccessModule.sContext, str, arrayList);
                        arrayList.clear();
                    }
                    Preferences.m3010a(str, MediaStorage.MEDIA_ORDER_BY_CUSTOM);
                    MediaAccessModule.this.m4276b(str);
                }
            });
        }
    }

    public void addGroupItemExchangeOrderEvent(GroupType groupType, String str, String str2) {
        if (this.f5932a == null) {
            this.f5932a = Cache.m3218a().m3211a(groupType);
        }
        int indexOf = this.f5932a.indexOf(str);
        int indexOf2 = this.f5932a.indexOf(str2);
        if (indexOf >= 0 && indexOf2 >= 0) {
            this.f5932a.set(indexOf, str2);
            this.f5932a.set(indexOf2, str);
        }
    }

    public void commitGroupItemExchangeOrder(GroupType groupType) {
        if (this.f5932a != null && this.f5932a.size() > 0) {
            Cache.m3218a().m3210a(groupType, this.f5932a);
            this.f5932a = null;
        }
    }

    public void updateGroupItem(GroupItem groupItem) {
        MediaStorage.updateGroupItem(sContext, groupItem);
        queryGroupItemList(GroupType.CUSTOM_LOCAL);
    }

    public void updateMediaItem(final MediaItem mediaItem) {
        m4269f();
        MediaStorage.updateMediaItem(sContext, mediaItem);
        final boolean equals = mediaItem.equals(Cache.m3218a().m3225N());
        final PlayStatus m2463m = SupportFactory.m2397a(BaseApplication.getApplication()).m2463m();
        if (equals) {
            Cache.m3218a().m3182c(mediaItem);
            CommandCenter.m4607a().m4595b(new Command(CommandID.UPDATE_MEDIA_ITEM_STARTED, mediaItem), ModuleID.MEDIA_ACCESS);
            CommandCenter.m4607a().m4606a(new Command(CommandID.PAUSE, new Object[0]));
            Preferences.m2884f(Preferences.m2854n() + File.pathSeparator + SupportFactory.m2397a(sContext).m2465k());
        }
        TaskScheduler.m8581a(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.core.c.a.8
            @Override // java.lang.Runnable
            public void run() {
                MediaTag createMediaTag = MediaTag.createMediaTag(mediaItem.getLocalDataSource(), false);
                if (createMediaTag != null) {
                    createMediaTag.setTitle(mediaItem.getTitle());
                    createMediaTag.setArtist(mediaItem.getArtist());
                    createMediaTag.setAlbum(mediaItem.getAlbum());
                    createMediaTag.setGenre(mediaItem.getGenre());
                    createMediaTag.setComment(mediaItem.getComment());
                    createMediaTag.setTrack(mediaItem.getTrack().intValue());
                    createMediaTag.setYear(mediaItem.getYear().intValue());
                    createMediaTag.save();
                    createMediaTag.close();
                    CommandCenter.m4607a().m4603a(new Command(CommandID.UPDATE_MEDIA_ITEM_FINISHED, mediaItem), ModuleID.MEDIA_ACCESS, 500);
                    CommandCenter.m4607a().m4603a(new Command(CommandID.UPDATE_MEDIA_LIBRARY_CHANGED, mediaItem.getGroupID()), ModuleID.MEDIA_ACCESS, 500);
                    if (equals && m2463m == PlayStatus.STATUS_PLAYING) {
                        CommandCenter.m4607a().m4605a(new Command(CommandID.START, new Object[0]), 1000);
                    }
                }
                if (equals) {
                    CommandCenter.m4607a().m4596b(new Command(CommandID.SYNC_CUR_MEDIA_INFO, new Object[0]));
                }
            }
        });
    }

    /* renamed from: a */
    private boolean m4279a(List<MediaItem> list) {
        if (this.f5936e.isEmpty()) {
            m4274b(list);
            return true;
        }
        int size = this.f5936e.size();
        if (size != list.size()) {
            return true;
        }
        for (int i = 0; i < size; i++) {
            if ((this.f5936e.get(i) != null && !this.f5936e.get(i).equals(list.get(i).getSongID())) || !StringUtils.m8344a(this.f5937f.get(i), list.get(i).getExtra())) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    private void m4274b(List<MediaItem> list) {
        this.f5936e.clear();
        this.f5937f.clear();
        for (MediaItem mediaItem : list) {
            this.f5936e.add(mediaItem.getSongID());
            this.f5937f.add(mediaItem.getExtra());
        }
    }

    public void syncNetTemporaryGroup(List<MediaItem> list) {
        if (m4279a(list)) {
            syncNetTemporaryGroupWithName(list, "");
            m4274b(list);
        }
    }

    public void syncNetTemporaryGroupWithName(List<MediaItem> list, String str) {
        ArrayList arrayList = new ArrayList(list);
        m4282a(MediaStorage.GROUP_ID_ONLINE_TEMPORARY);
        MediaStorage.clearGroup(sContext, MediaStorage.GROUP_ID_ONLINE_TEMPORARY);
        int size = arrayList.size();
        Preferences.m2828t(str);
        for (int i = 0; i < size; i += 50) {
            MediaStorage.insertMediaItems(sContext, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, arrayList.subList(i, Math.min(i + 50, size)));
        }
    }

    public void appendNetTemporaryMediaItems(List<MediaItem> list) {
        m4282a(MediaStorage.GROUP_ID_ONLINE_TEMPORARY);
        MediaStorage.insertMediaItems(sContext, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, list);
        m4276b(MediaStorage.GROUP_ID_ONLINE_TEMPORARY);
    }

    public void deletePicture(final Collection<MediaItem> collection) {
        this.f5933b.m8576a(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.core.c.a.9
            @Override // java.lang.Runnable
            public void run() {
                MediaItem m3225N = Cache.m3218a().m3225N();
                for (MediaItem mediaItem : collection) {
                    if (m3225N.equals(mediaItem)) {
                        Cache.m3218a().m3167f();
                        CommandCenter.m4607a().m4595b(new Command(CommandID.UPDATE_PICTURE_DELETED, mediaItem), ModuleID.MEDIA_ACCESS);
                    }
                    MediaAccessModule.this.m4283a(mediaItem, "picture_type");
                }
            }
        });
    }

    public void deleteLyric(final Collection<MediaItem> collection) {
        this.f5933b.m8576a(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.core.c.a.10
            @Override // java.lang.Runnable
            public void run() {
                MediaItem m3225N = Cache.m3218a().m3225N();
                for (MediaItem mediaItem : collection) {
                    if (!m3225N.equals(mediaItem)) {
                        MediaAccessModule.this.m4283a(mediaItem, "lyric_type");
                    } else {
                        FileUtils.m8404h(Cache.m3218a().m3159i());
                        Cache.m3218a().m3161h();
                        CommandCenter.m4607a().m4595b(new Command(CommandID.UPDATE_LYRIC_DELETED, mediaItem), ModuleID.MEDIA_ACCESS);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m4283a(MediaItem mediaItem, String str) {
        sContext.startService(new Intent(sContext, SupportService.class).putExtra("command", "remove_lyric_pic_command").putExtra("media", (Parcelable) mediaItem).putExtra("type", str));
    }

    public void doVersionCompactFinished() {
        queryAsyncLoadMediaItemList(MediaStorage.GROUP_ID_ALL_LOCAL, Preferences.m2860l(MediaStorage.GROUP_ID_ALL_LOCAL));
        queryGroupItemList(GroupType.CUSTOM_LOCAL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m4276b(String str) {
        if (StringUtils.m8344a(m4272c(str), Preferences.m2858m())) {
            CommandCenter.m4607a().m4596b(new Command(CommandID.SYNC_PLAYING_GROUP, new Object[0]));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m4280a(String str, final Collection<MediaItem> collection, final Boolean bool) {
        List<String> queryMediaIDs;
        int size;
        final ArrayList arrayList = new ArrayList(collection.size());
        for (MediaItem mediaItem : collection) {
            if (mediaItem != null) {
                arrayList.add(mediaItem.getID());
            }
        }
        String m2858m = Preferences.m2858m();
        String m2854n = Preferences.m2854n();
        String str2 = null;
        if (arrayList.contains(m2854n) && (size = (queryMediaIDs = MediaStorage.queryMediaIDs(sContext, m2858m, Preferences.m2860l(m2858m))).size()) > 0) {
            if (m4272c(str).equals(m2858m) && size == arrayList.size()) {
                m4268g();
            } else if (str.equals(m2858m) || ((MediaStorage.GROUP_ID_FAV.equals(m2858m) && (str.startsWith(MediaStorage.GROUP_ID_ONLINE_FAV_PREFIX) || str.equals(MediaStorage.GROUP_ID_FAV_LOCAL))) || bool.booleanValue() || !str.startsWith(MediaStorage.GROUP_ID_CUSTOM_PREFIX) || str.equals(MediaStorage.GROUP_ID_ALL_LOCAL))) {
                int indexOf = queryMediaIDs.indexOf(m2854n);
                int i = 0;
                while (true) {
                    if (i < size) {
                        indexOf = (indexOf + 1) % size;
                        i++;
                        if (!arrayList.contains(queryMediaIDs.get(indexOf))) {
                            str2 = queryMediaIDs.get(indexOf);
                            break;
                        }
                    } else {
                        CommandCenter.m4607a().m4596b(new Command(CommandID.STOP, new Object[0]));
                        break;
                    }
                }
            }
        }
        MediaStorage.deleteMediaItemList(sContext, str, arrayList);
        if (str2 != null) {
            MediaItem queryMediaItem = MediaStorage.queryMediaItem(sContext, m2858m, str2);
            if (SupportFactory.m2397a(BaseApplication.getApplication()).m2463m() == PlayStatus.STATUS_PLAYING) {
                CommandCenter.m4607a().m4596b(new Command(CommandID.PLAY_GROUP, m2858m, queryMediaItem));
            } else {
                CommandCenter.m4607a().m4596b(new Command(CommandID.STOP, new Object[0]));
                CommandCenter.m4607a().m4596b(new Command(CommandID.SYNC_GROUP, m2858m, queryMediaItem));
            }
        }
        m4276b(str);
        CommandCenter.m4607a().m4595b(new Command(CommandID.DELETE_MEDIA_ITEMS_FINISHED, str), ModuleID.MEDIA_ACCESS);
        m4269f();
        if (bool.booleanValue() || !str.startsWith(MediaStorage.GROUP_ID_CUSTOM_PREFIX)) {
            CommandCenter.m4607a().m4595b(new Command(CommandID.UPDATE_MEDIA_LIBRARY_CHANGED, m4272c(str)), ModuleID.MEDIA_ACCESS);
        } else {
            CommandCenter.m4607a().m4595b(new Command(CommandID.UPDATE_MEDIA_LIBRARY_CHANGED, m4272c(str)), ModuleID.MEDIA_ACCESS);
        }
        this.f5933b.m8576a(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.core.c.a.2
            @Override // java.lang.Runnable
            public void run() {
                if (bool.booleanValue()) {
                    MediaStorage.deleteMediaItemList(MediaAccessModule.sContext, MediaStorage.GROUP_ID_ALL_LOCAL, arrayList);
                    for (MediaItem mediaItem2 : collection) {
                        if (mediaItem2 != null) {
                            FileUtils.m8404h(mediaItem2.getLocalDataSource());
                        }
                    }
                }
            }
        });
    }

    /* renamed from: g */
    private void m4268g() {
        CommandCenter.m4607a().m4596b(new Command(CommandID.STOP, new Object[0]));
        Preferences.m2894d(MediaStorage.GROUP_ID_ALL_LOCAL);
        Preferences.m2888e("");
        CommandCenter.m4607a().m4596b(new Command(CommandID.SYNC_PLAYING_GROUP, new Object[0]));
    }

    public void logoutFinished() {
        m4276b(MediaStorage.GROUP_ID_FAV);
        if (StringUtils.m8344a(Preferences.m2858m(), MediaStorage.GROUP_ID_FAV) && Cache.m3218a().m3225N().isOnline()) {
            CommandCenter.m4607a().m4596b(new Command(CommandID.NEXT, new Object[0]));
        }
        m4269f();
        CommandCenter.m4607a().m4595b(new Command(CommandID.UPDATE_MEDIA_LIBRARY_CHANGED, m4272c(MediaStorage.GROUP_ID_FAV)), ModuleID.MEDIA_ACCESS);
    }

    public void loginFinished(CommonResult commonResult) {
        m4269f();
        m4276b(MediaStorage.GROUP_ID_FAV);
    }

    public void pullFavoriteOnlineMediaListComplete() {
        m4269f();
        m4276b(MediaStorage.GROUP_ID_FAV);
    }

    public void scanFinished(Integer num) {
        m4269f();
        CommandCenter.m4607a().m4595b(new Command(CommandID.UPDATE_MEDIA_LIBRARY_CHANGED, m4272c(MediaStorage.GROUP_ID_ALL_LOCAL)), ModuleID.MEDIA_ACCESS);
    }

    /* renamed from: c */
    private String m4272c(String str) {
        if (StringUtils.m8346a(str)) {
            return str;
        }
        if (StringUtils.m8344a(MediaStorage.GROUP_ID_FAV_LOCAL, str) || str.startsWith(MediaStorage.GROUP_ID_ONLINE_FAV_PREFIX)) {
            return MediaStorage.GROUP_ID_FAV;
        }
        return str;
    }
}
