package com.sds.android.ttpod.framework.modules.p109a;

import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.base.BaseModule;
import com.sds.android.ttpod.framework.base.CommonResult;
import com.sds.android.ttpod.framework.base.ObserverCommandID;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.framework.p106a.OnlineMediaItemUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@ObserverCommandID(m4563a = {CommandID.NET_WORK_TYPE_CHANGED, CommandID.LOGIN_FINISHED, CommandID.UPDATE_MEDIA_LIBRARY_CHANGED, CommandID.LOGOUT_FINISHED, CommandID.DO_VERSION_COMPACT_FINISHED})
/* renamed from: com.sds.android.ttpod.framework.modules.a.a */
/* loaded from: classes.dex */
public class FavoriteModule extends BaseModule {

    /* renamed from: a */
    private FavoriteServerManager.InterfaceC1815a f5735a = new FavoriteServerManager.InterfaceC1815a() { // from class: com.sds.android.ttpod.framework.modules.a.a.1
        @Override // com.sds.android.ttpod.framework.modules.p109a.FavoriteServerManager.InterfaceC1815a
        /* renamed from: a */
        public void mo4520a() {
            LogUtils.error("FavoriteModule", "PUSH_COMPLETE");
            CommandCenter.getInstance().m4595b(new Command(CommandID.PUSH_FAVORITE_ONLINE_MEDIA_LIST_COMPLETE, new Object[0]), ModuleID.FAVORITE);
        }

        @Override // com.sds.android.ttpod.framework.modules.p109a.FavoriteServerManager.InterfaceC1815a
        /* renamed from: b */
        public void mo4519b() {
            LogUtils.error("FavoriteModule", "PUSH_ERROR");
            CommandCenter.getInstance().m4595b(new Command(CommandID.PUSH_FAVORITE_ONLINE_MEDIA_LIST_ERROR, new Object[0]), ModuleID.FAVORITE);
        }

        @Override // com.sds.android.ttpod.framework.modules.p109a.FavoriteServerManager.InterfaceC1815a
        /* renamed from: c */
        public void mo4518c() {
            LogUtils.error("FavoriteModule", "PUSH_INVALID");
            CommandCenter.getInstance().m4595b(new Command(CommandID.PUSH_FAVORITE_ONLINE_MEDIA_LIST_INVALID, new Object[0]), ModuleID.FAVORITE);
        }
    };

    /* renamed from: b */
    private FavoriteServerManager.InterfaceC1815a f5736b = new FavoriteServerManager.InterfaceC1815a() { // from class: com.sds.android.ttpod.framework.modules.a.a.2
        @Override // com.sds.android.ttpod.framework.modules.p109a.FavoriteServerManager.InterfaceC1815a
        /* renamed from: a */
        public void mo4520a() {
            LogUtils.error("FavoriteModule", "PULL_COMPLETE");
            List<MediaItem> queryMediaItemList = MediaStorage.queryMediaItemList(FavoriteModule.sContext, MediaStorage.buildOnlineFavGroupID(), Preferences.m2860l(MediaStorage.buildOnlineFavGroupID()));
            List<String> m3224O = Cache.getInstance().m3224O();
            for (MediaItem mediaItem : queryMediaItemList) {
                if (!m3224O.contains(mediaItem.getID())) {
                    m3224O.add(mediaItem.getID());
                }
            }
            Cache.getInstance().m3169e(m3224O);
            FavoriteModule.this.m4549f();
            CommandCenter.getInstance().m4595b(new Command(CommandID.PULL_FAVORITE_ONLINE_MEDIA_LIST_COMPLETE, new Object[0]), ModuleID.FAVORITE);
        }

        @Override // com.sds.android.ttpod.framework.modules.p109a.FavoriteServerManager.InterfaceC1815a
        /* renamed from: b */
        public void mo4519b() {
            LogUtils.error("FavoriteModule", "PULL_ERROR");
            CommandCenter.getInstance().m4595b(new Command(CommandID.PULL_FAVORITE_ONLINE_MEDIA_LIST_ERROR, new Object[0]), ModuleID.FAVORITE);
        }

        @Override // com.sds.android.ttpod.framework.modules.p109a.FavoriteServerManager.InterfaceC1815a
        /* renamed from: c */
        public void mo4518c() {
            LogUtils.error("FavoriteModule", "PULL_INVALID");
            CommandCenter.getInstance().m4595b(new Command(CommandID.PULL_FAVORITE_ONLINE_MEDIA_LIST_COMPLETE, new Object[0]), ModuleID.FAVORITE);
        }
    };

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    /* renamed from: id */
    protected ModuleID id() {
        return ModuleID.FAVORITE;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public void onCreate() {
        super.onCreate();
        m4551d();
        m4548g();
        m4546i();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public void onDestroy() {
        m4550e();
        super.onDestroy();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    protected void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        Class<?> cls = getClass();
        map.put(CommandID.ADD_FAVORITE_MEDIA_ITEM, ReflectUtils.m8375a(cls, "addFavoriteMediaItem", MediaItem.class));
        map.put(CommandID.DELETE_FAVORITE_MEDIA_ITEM, ReflectUtils.m8375a(cls, "deleteFavoriteMediaItem", MediaItem.class, Boolean.class));
        map.put(CommandID.DELETE_FAVORITE_MEDIA_ITEM_LIST, ReflectUtils.m8375a(cls, "deleteFavoriteMediaItemList", Collection.class, Boolean.class));
        map.put(CommandID.SYNC_FAVORITE_ONLINE_MEDIA_LIST, ReflectUtils.m8375a(cls, "syncFavoriteOnlineMediaList", new Class[0]));
        map.put(CommandID.PUSH_FAVORITE_ONLINE_MEDIA_LIST, ReflectUtils.m8375a(cls, "pushFavoriteOnlineMediaList", new Class[0]));
        map.put(CommandID.LOGIN_FINISHED, ReflectUtils.m8375a(cls, "loginFinished", CommonResult.class));
        map.put(CommandID.UPDATE_MEDIA_LIBRARY_CHANGED, ReflectUtils.m8375a(cls, "updateOnlineMediaItemList", String.class));
        map.put(CommandID.LOGOUT_FINISHED, ReflectUtils.m8375a(cls, "logoutFinished", new Class[0]));
        map.put(CommandID.DO_VERSION_COMPACT_FINISHED, ReflectUtils.m8375a(cls, "doVersionCompactFinished", new Class[0]));
    }

    public void logoutFinished() {
        LogUtils.error("FavoriteModule", "logoutFinished");
        m4550e();
        m4546i();
    }

    public void loginFinished(CommonResult commonResult) {
        LogUtils.error("FavoriteModule", "loginFinished");
        m4551d();
        syncFavoriteOnlineMediaList();
        m4548g();
    }

    /* renamed from: d */
    private void m4551d() {
        LogUtils.error("FavoriteModule", "initOnlineFavorite");
        FavoriteServerManager.m4544a().m4529d();
        FavoriteServerManager.m4544a().m4530c(this.f5736b);
        FavoriteServerManager.m4544a().m4541a(this.f5735a);
    }

    /* renamed from: e */
    private void m4550e() {
        LogUtils.error("FavoriteModule", "unInitOnlineFavorite");
        FavoriteServerManager.m4544a().m4534b(this.f5735a);
        FavoriteServerManager.m4544a().m4528d(this.f5736b);
        FavoriteServerManager.m4544a().m4527e();
        Cache.getInstance().m3221R();
        m4549f();
    }

    /* renamed from: a */
    protected void m4560a() {
        for (MediaItem mediaItem : MediaStorage.queryMediaItemList(sContext, MediaStorage.buildOnlineFavGroupID(), Preferences.m2860l(MediaStorage.buildOnlineFavGroupID()))) {
            if (!StringUtils.isEmpty(mediaItem.getLocalDataSource()) && !FileUtils.m8419a(mediaItem.getLocalDataSource())) {
                MediaItemUtils.m4714a(mediaItem, mediaItem.getLocalDataSource());
                MediaStorage.updateMediaItem(sContext, mediaItem);
            }
        }
        m4549f();
    }

    public void updateOnlineMediaItemList(String str) {
        if (Preferences.m2954aq() == null || str == null || str.equals(MediaStorage.buildOnlineFavGroupID())) {
            return;
        }
        if (str.equals(MediaStorage.GROUP_ID_DOWNLOAD) || MediaStorage.GROUP_ID_ALL_LOCAL.equals(str)) {
            TaskScheduler.m8581a(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.a.a.3
                @Override // java.lang.Runnable
                public void run() {
                    FavoriteModule.this.m4560a();
                }
            });
        }
    }

    public void addFavoriteMediaItem(MediaItem mediaItem) {
        LogUtils.error("FavoriteModule", "addFavoriteMediaItem");
        DebugUtils.m8426a(mediaItem, "mediaItem");
        if (mediaItem.isOnline()) {
            DebugUtils.m8426a(Preferences.m2954aq(), "UserInfo");
            CommandCenter.getInstance().m4606a(new Command(CommandID.ADD_MEDIA_ITEM, MediaStorage.buildOnlineFavGroupID(), mediaItem));
            FavoriteServerManager.m4544a().m4542a(mediaItem.getSongID().longValue());
            m4558a(MediaStorage.queryMediaItem(sContext, MediaStorage.buildOnlineFavGroupID(), mediaItem.getID()));
            List<String> m3224O = Cache.getInstance().m3224O();
            m3224O.add(mediaItem.getID());
            Cache.getInstance().m3169e(m3224O);
        } else {
            CommandCenter.getInstance().m4606a(new Command(CommandID.ADD_MEDIA_ITEM, MediaStorage.GROUP_ID_FAV_LOCAL, mediaItem));
            List<String> m3220S = Cache.getInstance().m3220S();
            m3220S.add(mediaItem.getID());
            Cache.getInstance().m3173d(m3220S);
        }
        CommandCenter.getInstance().m4604a(new Command(CommandID.USER_ADDED_FAVORITE_MEDIA, mediaItem), ModuleID.FAVORITE);
        m4549f();
    }

    /* renamed from: a */
    private void m4558a(MediaItem mediaItem) {
        if (mediaItem != null) {
            String m4690a = OnlineMediaItemUtils.m4690a((OnlineMediaItem) JSONUtils.fromJson(mediaItem.getExtra(), OnlineMediaItem.class));
            if (!StringUtils.isEmpty(m4690a)) {
                MediaItemUtils.m4714a(mediaItem, m4690a);
                MediaStorage.updateMediaItem(sContext, mediaItem);
            }
        }
    }

    public void deleteFavoriteMediaItem(MediaItem mediaItem, Boolean bool) {
        LogUtils.error("FavoriteModule", "deleteFavoriteMediaItem");
        DebugUtils.m8426a(mediaItem, "mediaItem");
        if (mediaItem.getSongID() != null && mediaItem.getSongID().longValue() != 0) {
            if (!mediaItem.isOnline()) {
                MediaItem queryMediaItem = MediaStorage.queryMediaItem(sContext, MediaStorage.GROUP_ID_FAV, MediaItem.genIDWithSongID(mediaItem.getSongID()));
                if (queryMediaItem != null) {
                    m4557a(queryMediaItem, bool);
                }
                m4554b(mediaItem, bool);
            } else {
                MediaItem queryMediaItemBySongID = MediaStorage.queryMediaItemBySongID(sContext, MediaStorage.GROUP_ID_FAV_LOCAL, mediaItem.getSongID());
                if (queryMediaItemBySongID != null && !queryMediaItemBySongID.isNull()) {
                    MediaStorage.deleteMediaItem(sContext, MediaStorage.GROUP_ID_FAV_LOCAL, queryMediaItemBySongID.getID());
                }
                m4557a(mediaItem, bool);
            }
        } else {
            m4554b(mediaItem, bool);
        }
        m4549f();
    }

    /* renamed from: a */
    private void m4557a(final MediaItem mediaItem, final Boolean bool) {
        DebugUtils.m8426a(Preferences.m2954aq(), "UserInfo");
        CommandCenter.getInstance().m4596b(new Command(CommandID.DELETE_MEDIA_ITEM, MediaStorage.buildOnlineFavGroupID(), mediaItem, false));
        FavoriteServerManager.m4544a().m4535b(mediaItem.getSongID().longValue());
        List<String> m3224O = Cache.getInstance().m3224O();
        m3224O.remove(mediaItem.getID());
        Cache.getInstance().m3169e(m3224O);
        if (bool.booleanValue()) {
            TaskScheduler.m8581a(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.a.a.4
                @Override // java.lang.Runnable
                public void run() {
                    MediaItem m4712a = MediaItemUtils.m4712a(mediaItem.getLocalDataSource());
                    if (m4712a != null) {
                        CommandCenter.getInstance().m4596b(new Command(CommandID.DELETE_MEDIA_ITEM, MediaStorage.GROUP_ID_ALL_LOCAL, MediaStorage.queryMediaItem(FavoriteModule.sContext, MediaStorage.GROUP_ID_ALL_LOCAL, m4712a.getID()), bool));
                    }
                }
            });
        }
    }

    /* renamed from: b */
    private void m4554b(MediaItem mediaItem, Boolean bool) {
        CommandCenter.getInstance().m4606a(new Command(CommandID.DELETE_MEDIA_ITEM, MediaStorage.GROUP_ID_FAV_LOCAL, mediaItem, bool));
        List<String> m3220S = Cache.getInstance().m3220S();
        m3220S.remove(mediaItem.getID());
        Cache.getInstance().m3173d(m3220S);
    }

    public void deleteFavoriteMediaItemList(Collection<MediaItem> collection, Boolean bool) {
        for (MediaItem mediaItem : collection) {
            deleteFavoriteMediaItem(mediaItem, bool);
        }
    }

    public void pushFavoriteOnlineMediaList() {
        LogUtils.error("FavoriteModule", "pushFavoriteOnlineMediaList");
        if (Preferences.m2954aq() != null) {
            FavoriteServerManager.m4544a().m4531c();
        }
    }

    public void syncFavoriteOnlineMediaList() {
        LogUtils.error("FavoriteModule", "syncFavoriteOnlineMediaList");
        if (Preferences.m2954aq() != null) {
            FavoriteServerManager.m4544a().m4531c();
            FavoriteServerManager.m4544a().m4537b();
        }
    }

    public void doVersionCompactFinished() {
        m4545j();
        m4547h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public void m4549f() {
        CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_FAVORITE_CHANGED, new Object[0]), ModuleID.FAVORITE);
    }

    /* renamed from: g */
    private void m4548g() {
        LogUtils.error("FavoriteModule", "asynReloadOnlineFavMediaIDs");
        TaskScheduler.m8580a(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.a.a.5
            @Override // java.lang.Runnable
            public void run() {
                FavoriteModule.this.m4547h();
            }
        }, new Runnable() { // from class: com.sds.android.ttpod.framework.modules.a.a.6
            @Override // java.lang.Runnable
            public void run() {
                FavoriteModule.this.m4549f();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public void m4547h() {
        try {
            if (Preferences.m2954aq() != null) {
                String buildOnlineFavGroupID = MediaStorage.buildOnlineFavGroupID();
                Cache.getInstance().m3169e(MediaStorage.queryMediaIDs(sContext, buildOnlineFavGroupID, Preferences.m2860l(buildOnlineFavGroupID)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: i */
    private void m4546i() {
        TaskScheduler.m8580a(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.a.a.7
            @Override // java.lang.Runnable
            public void run() {
                FavoriteModule.this.m4545j();
            }
        }, new Runnable() { // from class: com.sds.android.ttpod.framework.modules.a.a.8
            @Override // java.lang.Runnable
            public void run() {
                FavoriteModule.this.m4549f();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public void m4545j() {
        Cache.getInstance().m3173d(MediaStorage.queryMediaIDs(sContext, MediaStorage.GROUP_ID_FAV_LOCAL, Preferences.m2860l(MediaStorage.GROUP_ID_FAV_LOCAL)));
    }
}
