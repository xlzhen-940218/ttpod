package com.sds.android.ttpod.framework.modules.skin;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.sds.android.cloudapi.ttpod.api.SkinAPI;
import com.sds.android.cloudapi.ttpod.result.BackgroundCheckResult;
import com.sds.android.cloudapi.ttpod.result.SkinListCheckResult;
import com.sds.android.sdk.lib.p065e.ThreadPool;
import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.SecurityUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.BaseModule;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.modules.skin.p130c.OnlineBackgroundListDownloader;
import com.sds.android.ttpod.framework.modules.skin.p130c.OnlineListDownloader;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.SkinUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Map;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.o */
/* loaded from: classes.dex */
public final class SkinModule extends BaseModule {
    public static final String TAG = "SkinModule";

    /* renamed from: a */
    private ThreadPool f6679a = new ThreadPool("skinWorkThreadPool", 4, 0);

    /* renamed from: b */
    private String f6680b;

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public void onCreate() {
        super.onCreate();
        String m3038V = Preferences.m3038V();
        if (StringUtils.isEmpty(m3038V)) {
            m3038V = Preferences.getFirstSkinItemPath();
        }
        this.f6680b = m3038V;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    /* renamed from: id */
    protected ModuleID id() {
        return ModuleID.SKIN;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public long timeOutInMills() {
        return 60000;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    protected void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        Class<?> cls = getClass();
        map.put(CommandID.SET_SKIN, ReflectUtils.loadMethod(cls, "setSkin", String.class, Integer.class));
        map.put(CommandID.GET_SKIN_PROTOCOL_PATH, ReflectUtils.loadMethod(cls, "getSkinProtocolPath", new Class[0]));
        map.put(CommandID.SET_SKIN_PROTOCOL_PATH, ReflectUtils.loadMethod(cls, "setSkinProtocolPath", String.class));
        map.put(CommandID.DELETE_SKIN, ReflectUtils.loadMethod(cls, "deleteSkin", String.class, Integer.class));
        map.put(CommandID.DECODE_SKIN_THUMBNAIL, ReflectUtils.loadMethod(cls, "decodeThumbNail", SkinItem.class));
        map.put(CommandID.LOAD_SKIN, ReflectUtils.loadMethod(cls, "loadSkin", new Class[0]));
        map.put(CommandID.LOAD_SKIN_WITH_PATH, ReflectUtils.loadMethod(cls, "loadSkinWithPath", String.class));
        map.put(CommandID.REQUEST_RECOMMEND_SKIN_LIST, ReflectUtils.loadMethod(cls, "loadRecommendSkinList", new Class[0]));
        map.put(CommandID.REQUEST_UPDATE_RECOMMEND_SKIN_LIST, ReflectUtils.loadMethod(cls, "updateRecommendSkinList", new Class[0]));
        map.put(CommandID.REQUEST_UPDATE_RECOMMEND_BACKGROUND_LIST, ReflectUtils.loadMethod(cls, "updateRecommendBackgroundList", new Class[0]));
        map.put(CommandID.REQUEST_UPDATE_SKIN_RANK_LIST, ReflectUtils.loadMethod(cls, "updateSkinRankList", new Class[0]));
        map.put(CommandID.REQUEST_SKIN_RANK_LIST, ReflectUtils.loadMethod(cls, "loadSkinRankList", Integer.class));
        map.put(CommandID.REQUEST_SKIN_INFO, ReflectUtils.loadMethod(cls, "loadSkinInfo", String.class));
        map.put(CommandID.REQUEST_DOWNLOADED_SKIN_LIST, ReflectUtils.loadMethod(cls, "loadDownloadedSkinList", new Class[0]));
        map.put(CommandID.LOAD_ALL_LOCAL_SKIN_LIST, ReflectUtils.loadMethod(cls, "loadAllLocalSkinList", new Class[0]));
        map.put(CommandID.PARSE_CATEGORY_LIST, ReflectUtils.loadMethod(cls, "parseCategoryList", Integer.class));
        map.put(CommandID.REQUEST_SKIN_CATEGORY_LIST, ReflectUtils.loadMethod(cls, "downloadSkinCategoryList", new Class[0]));
        map.put(CommandID.REQUEST_PAGED_SKIN_LIST, ReflectUtils.loadMethod(cls, "requestPagedSkinList", Integer.class, Integer.class, Integer.class));
        map.put(CommandID.REQUEST_BKG_CATEGORY_LIST, ReflectUtils.loadMethod(cls, "downloadBackgroundCategoryList", new Class[0]));
        map.put(CommandID.REQUEST_PAGED_BKG_LIST, ReflectUtils.loadMethod(cls, "requestPagedBackgroundList", Integer.class, Integer.class, Integer.class));
        map.put(CommandID.NOTIFY_PLAYING_PANEL_ON_SHOW, ReflectUtils.loadMethod(cls, "notifyPlayingPanelOnShow", new Class[0]));
    }

    public void setSkin(String str, Integer num) {
        setSkinProtocolPath(SkinUtils.m4646a(str, num.intValue()));
        Cache.getInstance().m3153l();
        ThemeManager.m3261b();
        CommandCenter.getInstance().m4604a(new Command(CommandID.SKIN_CHANGED, new Object[0]), ModuleID.SKIN);
        CommandCenter.getInstance().m4604a(new Command(CommandID.APP_THEME_CHANGED, new Object[0]), ModuleID.THEME);
    }

    public String getSkinProtocolPath() {
        return this.f6680b;
    }

    public void setSkinProtocolPath(String str) {
        DebugUtils.m8426a(str, "SKinProtocolPath");
        this.f6680b = str;
        Preferences.m2876h(str);
    }

    public Boolean deleteSkin(String str, Integer num) {
        boolean z = false;
        switch (num.intValue()) {
            case 0:
                z = m3542a(str);
                String str2 = TTPodConfig.getCacheTmpPath() + File.separator + SecurityUtils.C0610b.m8361a(SkinUtils.m4646a(str, num.intValue()));
                if (FileUtils.m8419a(str2)) {
                    new File(str2).delete();
                    break;
                }
                break;
            case 2:
                z = m3541b(str);
                break;
            case 3:
                z = m3540c(str);
                break;
        }
        return Boolean.valueOf(z);
    }

    public void decodeThumbNail(SkinItem skinItem) {
        this.f6679a.m8576a((Runnable) new SkinThumbnailCreator(skinItem));
    }

    public void loadSkin() {
        this.f6679a.m8576a((Runnable) new SkinCacheCreator(this.f6680b, Preferences.getFirstSkinItemPath(), CommandID.LOAD_SKIN_FINISHED));
    }

    public void loadSkinWithPath(String str) {
        this.f6679a.m8576a((Runnable) new SkinCacheCreator(str, null, CommandID.LOAD_SKIN_WITH_PATH_FINISHED));
    }

    public void loadRecommendSkinList() {
        this.f6679a.m8576a((Runnable) new LocalSkinListLoader());
    }

    public void updateRecommendSkinList() {
        SkinAPI.m8830a().m8544a(new RequestCallback<SkinListCheckResult>() { // from class: com.sds.android.ttpod.framework.modules.skin.o.1
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: a */
            public void onRequestSuccess(SkinListCheckResult skinListCheckResult) {
                Long data = skinListCheckResult.getData();
                String m4645a = SkinUtils.m4645a(TTPodConfig.getSkinPath(), "list_");
                if (SkinModule.this.m3543a(data, m4645a)) {
                    SkinModule.this.f6679a.m8576a((Runnable) new OnlineListDownloader(data, "http://api.skin.ttpod.com/skin/recommend_skin/list_", m4645a, CommandID.FINISH_UPDATE_RECOMMEND_SKIN_LIST));
                } else {
                    CommandCenter.getInstance().m4595b(new Command(CommandID.FINISH_UPDATE_RECOMMEND_SKIN_LIST, false), ModuleID.SKIN);
                }
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(SkinListCheckResult skinListCheckResult) {
                CommandCenter.getInstance().m4595b(new Command(CommandID.FINISH_UPDATE_RECOMMEND_SKIN_LIST, false), ModuleID.SKIN);
            }
        });
    }

    public void updateSkinRankList() {
        SkinAPI.m8828b().m8544a(new RequestCallback<SkinListCheckResult>() { // from class: com.sds.android.ttpod.framework.modules.skin.o.2
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: a */
            public void onRequestSuccess(SkinListCheckResult skinListCheckResult) {
                Long data = skinListCheckResult.getData();
                String m4645a = SkinUtils.m4645a(TTPodConfig.getSkinPath(), "rank_");
                if (SkinModule.this.m3543a(data, m4645a)) {
                    SkinModule.this.f6679a.m8576a((Runnable) new OnlineSkinRankListDownloader(data, "http://api.skin.ttpod.com/skin/hot_skin/list_", m4645a, CommandID.FINISH_UPDATE_SKIN_RANK_LIST));
                } else {
                    CommandCenter.getInstance().m4595b(new Command(CommandID.FINISH_UPDATE_SKIN_RANK_LIST, false), ModuleID.SKIN);
                }
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(SkinListCheckResult skinListCheckResult) {
                CommandCenter.getInstance().m4595b(new Command(CommandID.FINISH_UPDATE_SKIN_RANK_LIST, false), ModuleID.SKIN);
            }
        });
    }

    public void loadSkinRankList(Integer num) {
        this.f6679a.m8576a((Runnable) new LocalSkinRankListLoader());
    }

    public void loadSkinInfo(String str) {
        this.f6679a.m8576a((Runnable) new SkinInfoLoader(str));
    }

    public void updateRecommendBackgroundList() {
        SkinAPI.m8826c().m8544a(new RequestCallback<BackgroundCheckResult>() { // from class: com.sds.android.ttpod.framework.modules.skin.o.3
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: a */
            public void onRequestSuccess(BackgroundCheckResult backgroundCheckResult) {
                Long data = backgroundCheckResult.getData();
                String m4645a = SkinUtils.m4645a(TTPodConfig.getBkgs(), "list_");
                if (SkinModule.this.m3543a(data, m4645a)) {
                    SkinModule.this.f6679a.m8576a((Runnable) new OnlineBackgroundListDownloader(data, "http://api.skin.ttpod.com/skin/recommend_background/list_", m4645a, CommandID.FINISH_UPDATE_RECOMMEND_BACKGROUND_LIST));
                } else {
                    CommandCenter.getInstance().m4595b(new Command(CommandID.FINISH_UPDATE_RECOMMEND_BACKGROUND_LIST, false), ModuleID.SKIN);
                }
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(BackgroundCheckResult backgroundCheckResult) {
                CommandCenter.getInstance().m4595b(new Command(CommandID.FINISH_UPDATE_RECOMMEND_BACKGROUND_LIST, false), ModuleID.SKIN);
            }
        });
    }

    public static void logE(String str) {
        LogUtils.error(TAG, str);
    }

    public static void logD(String str) {
        LogUtils.debug(TAG, str);
    }

    /* renamed from: a */
    private boolean m3542a(String str) {
        return new File(str).delete();
    }

    /* renamed from: b */
    private boolean m3541b(String str) {
        Intent intent = new Intent("android.intent.action.DELETE", Uri.fromParts("package", str, null));
        intent.addFlags(268435456);
        sContext.startActivity(intent);
        return true;
    }

    /* renamed from: c */
    private boolean m3540c(String str) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m3543a(Long l, String str) {
        String m8401k = FileUtils.m8401k(str);
        if (TextUtils.isEmpty(m8401k)) {
            return true;
        }
        String substring = m8401k.substring(5);
        LogUtils.debug(TAG, getClass() + ".isNeedUpdateFile timeText: " + substring + " localFile: " + m8401k);
        return l.longValue() > Long.valueOf(Long.parseLong(substring)).longValue();
    }

    public void loadDownloadedSkinList() {
        this.f6679a.m8576a((Runnable) new DownloadedSkinListCreator());
    }

    public void loadAllLocalSkinList() {
        this.f6679a.m8576a((Runnable) new AllLocalSkinListLoader());
    }

    public void parseCategoryList(Integer num) {
        this.f6679a.m8576a((Runnable) new CategoryListLoader(num.intValue()));
    }

    public void downloadSkinCategoryList() {
        this.f6679a.m8576a((Runnable) new OnlineCategoryListDownloader("http://api.skin.ttpod.com/skin/apiSkinType/list", CategoryListLoader.categorySkinPath, CommandID.ON_SKIN_CATEGORY_LIST_DOWNLOADED));
    }

    public void requestPagedSkinList(final Integer num, final Integer num2, final Integer num3) {
        this.f6679a.m8576a(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.skin.o.4
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.REQUEST_PAGED_SKIN_LIST_FINISHED, SkinAPI.m8829a(num.intValue(), num2.intValue(), num3.intValue()).execute()), ModuleID.SKIN);
            }
        });
    }

    public void downloadBackgroundCategoryList() {
        this.f6679a.m8576a((Runnable) new OnlineCategoryListDownloader("http://log.topit.me/ttpod/apiSkinTypeList.php", CategoryListLoader.categoryBkgPath, CommandID.ON_BKG_CATEGORY_LIST_DOWNLOADED));
    }

    public void requestPagedBackgroundList(final Integer num, final Integer num2, final Integer num3) {
        this.f6679a.m8576a(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.skin.o.5
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.REQUEST_PAGED_BKG_LIST_FINISHED, SkinAPI.m8827b(num.intValue(), num2.intValue(), num3.intValue()).execute()), ModuleID.SKIN);
            }
        });
    }

    public void notifyPlayingPanelOnShow() {
        CommandCenter.getInstance().m4604a(new Command(CommandID.ON_PLAYING_PANEL_SHOW, new Object[0]), ModuleID.SKIN);
    }
}
