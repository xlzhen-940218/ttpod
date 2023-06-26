package com.sds.android.ttpod.framework.storage.p133a;

import com.sds.android.cloudapi.ttpod.data.Billboards;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.cloudapi.ttpod.data.HotWords;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.result.SplashDataResult;
import com.sds.android.sdk.core.p057a.ObjectCache;
import com.sds.android.sdk.lib.util.SecurityUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.ValidityResult;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import com.sds.android.ttpod.framework.modules.skin.SkinItem;
import com.sds.android.ttpod.framework.modules.theme.BackgroundItem;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.media.mediastore.GroupItem;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* renamed from: com.sds.android.ttpod.framework.storage.a.a */
/* loaded from: classes.dex */
public final class Cache {

    /* renamed from: b */
    private static Cache instance = null;

    /* renamed from: a */
    private volatile ObjectCache objectCache;

    private Cache() {
    }

    /* renamed from: a */
    public static synchronized Cache getInstance() {
        Cache cache;
        synchronized (Cache.class) {
            if (instance == null) {
                instance = new Cache();
                instance.m3219T();
            }
            cache = instance;
        }
        return cache;
    }

    /* renamed from: T */
    private void m3219T() {
        this.objectCache = ObjectCache.m8776a(0.05f, TTPodConfig.m5299i());
    }

    /* renamed from: b */
    public void m3196b() {
        this.objectCache.m8777a();
    }

    /* renamed from: c */
    public SplashDataResult m3185c() {
        try {
            return (SplashDataResult) this.objectCache.m8765b(ObjectCacheID.SPLASH_CONFIG.name(), null);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public void m3214a(SplashDataResult splashDataResult) {
        if (this.objectCache != null) {
            this.objectCache.m8771a(ObjectCacheID.SPLASH_CONFIG.name(), splashDataResult);
        }
    }

    /* renamed from: d */
    public LinkedList<Long> m3176d() {
        LinkedList<Long> linkedList = new LinkedList<>();
        try {
            return (LinkedList) this.objectCache.m8765b(ObjectCacheID.FAVORITE_ADDED_REQUEST_CACHE.name(), linkedList);
        } catch (Exception e) {
            return linkedList;
        }
    }

    /* renamed from: a */
    public void m3200a(LinkedList<Long> linkedList) {
        this.objectCache.m8771a(ObjectCacheID.FAVORITE_ADDED_REQUEST_CACHE.name(), linkedList);
    }

    /* renamed from: e */
    public LinkedList<Long> m3171e() {
        LinkedList<Long> linkedList = new LinkedList<>();
        try {
            return (LinkedList) this.objectCache.m8765b(ObjectCacheID.FAVORITE_REMOVED_REQUEST_CACHE.name(), linkedList);
        } catch (Exception e) {
            return linkedList;
        }
    }

    /* renamed from: b */
    public void m3188b(LinkedList<Long> linkedList) {
        this.objectCache.m8771a(ObjectCacheID.FAVORITE_REMOVED_REQUEST_CACHE.name(), linkedList);
    }

    /* renamed from: a */
    public void m3205a(String str, MediaItem mediaItem) {
        if (this.objectCache != null && !mediaItem.isNull()) {
            this.objectCache.m8771a(ObjectCacheID.CURRENT_ARTIST_BITMAP_PATH.name(), str);
        }
    }

    /* renamed from: f */
    public void m3167f() {
        this.objectCache.m8766b(ObjectCacheID.CURRENT_ARTIST_BITMAP_PATH.name());
    }

    /* renamed from: g */
    public String m3164g() {
        return m3209a(getInstance().getCurrentPlayMediaItem());
    }

    /* renamed from: a */
    public String m3209a(MediaItem mediaItem) {
        try {
            if (mediaItem.isNull()) {
                return null;
            }
            return (String) this.objectCache.m8765b(ObjectCacheID.CURRENT_ARTIST_BITMAP_PATH.name(), null);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: b */
    public void m3191b(String str, MediaItem mediaItem) {
        if (this.objectCache != null && !mediaItem.isNull()) {
            this.objectCache.m8771a(ObjectCacheID.CURRENT_LYRIC_PATH.name(), mediaItem.getID() + str);
        }
    }

    /* renamed from: h */
    public void m3161h() {
        this.objectCache.m8766b(ObjectCacheID.CURRENT_LYRIC_PATH.name());
    }

    /* renamed from: i */
    public String m3159i() {
        return m3193b(getInstance().getCurrentPlayMediaItem());
    }

    /* renamed from: b */
    public String m3193b(MediaItem mediaItem) {
        if (!mediaItem.isNull()) {
            try {
                String str = (String) this.objectCache.m8765b(ObjectCacheID.CURRENT_LYRIC_PATH.name(), "");
                if (str.startsWith(mediaItem.getID())) {
                    return str.substring(mediaItem.getID().length());
                }
            } catch (Exception e) {
                return "";
            }
        }
        return "";
    }

    /* renamed from: j */
    public List<HotWords> m3157j() {
        ArrayList arrayList = new ArrayList();
        try {
            return (List) this.objectCache.m8765b(ObjectCacheID.HOTWORDS_CACHE.name(), arrayList);
        } catch (Exception e) {
            return arrayList;
        }
    }

    /* renamed from: a */
    public void m3199a(List<HotWords> list) {
        this.objectCache.m8771a(ObjectCacheID.HOTWORDS_CACHE.name(), list);
    }

    /* renamed from: a */
    public void m3215a(long j, List<Post> list) {
        if (list == null) {
            throw new IllegalArgumentException("posts should not be null");
        }
        this.objectCache.m8771a(ObjectCacheID.MUSIC_CIRCLE_ENTRY_POST_INFOS.name() + j, list);
    }

    /* renamed from: a */
    public List<Post> m3216a(long j) {
        ArrayList arrayList = new ArrayList();
        try {
            return (List) this.objectCache.m8765b(ObjectCacheID.MUSIC_CIRCLE_ENTRY_POST_INFOS.name() + j, arrayList);
        } catch (Exception e) {
            return arrayList;
        }
    }

    /* renamed from: b */
    public void m3187b(List<GroupItem> list) {
        ArrayList arrayList = new ArrayList();
        for (GroupItem groupItem : list) {
            arrayList.add(groupItem);
        }
        this.objectCache.m8771a(ObjectCacheID.CUSTOM_GROUP_ITEMS.name(), arrayList);
    }

    /* renamed from: k */
    public List<GroupItem> m3155k() {
        List<GroupItem> list;
        if (this.objectCache == null || !this.objectCache.m8773a(ObjectCacheID.CUSTOM_GROUP_ITEMS.name())) {
            return new ArrayList();
        }
        try {
            list = (List) this.objectCache.m8765b(ObjectCacheID.CUSTOM_GROUP_ITEMS.name(), null);
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        ArrayList arrayList = new ArrayList();
        for (GroupItem groupItem : list) {
            if (!StringUtils.equals(groupItem.getGroupID(), MediaStorage.GROUP_ID_RECENTLY_PLAY) && !StringUtils.equals(groupItem.getGroupID(), MediaStorage.GROUP_ID_RECENTLY_ADD)) {
                arrayList.add(groupItem);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public void m3213a(SkinCache skinCache) {
        this.objectCache.m8771a(ObjectCacheID.SKIN_CACHE.name(), skinCache);
    }

    /* renamed from: l */
    public void m3153l() {
        this.objectCache.m8766b(ObjectCacheID.SKIN_CACHE.name());
    }

    /* renamed from: m */
    public SkinCache m3151m() {
        try {
            return (SkinCache) this.objectCache.m8765b(ObjectCacheID.SKIN_CACHE.name(), null);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public void m3206a(String str, ValidityResult validityResult) {
        this.objectCache.m8771a(SecurityUtils.C0610b.m8361a(str), validityResult);
    }

    /* renamed from: a */
    public ValidityResult m3207a(String str) {
        try {
            return (ValidityResult) this.objectCache.m8765b(SecurityUtils.C0610b.m8361a(str), null);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public List<String> m3211a(GroupType groupType) {
        try {
            return (List) this.objectCache.m8765b(ObjectCacheID.GROUP_TYPE_ORDER_PREFIX + groupType.name(), null);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public void m3210a(GroupType groupType, List<String> list) {
        this.objectCache.m8771a(ObjectCacheID.GROUP_TYPE_ORDER_PREFIX + groupType.name(), list);
    }

    /* renamed from: b */
    public <T> List<T> m3192b(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            return (List) this.objectCache.m8765b(ObjectCacheID.HISTORY_PREFIX.name() + str, arrayList);
        } catch (Exception e) {
            return arrayList;
        }
    }

    /* renamed from: a */
    public <T> void m3204a(String str, List<T> list) {
        this.objectCache.m8771a(ObjectCacheID.HISTORY_PREFIX.name() + str, list);
    }

    /* renamed from: a */
    public void m3212a(BackgroundItem backgroundItem) {
        this.objectCache.m8771a(ObjectCacheID.BACKGROUND.name(), backgroundItem);
    }

    /* renamed from: n */
    public BackgroundItem m3150n() {
        try {
            return (BackgroundItem) this.objectCache.m8765b(ObjectCacheID.BACKGROUND.name(), null);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: o */
    public void m3149o() {
        this.objectCache.m8766b(ObjectCacheID.ARTIST_BITMAP_PATH.name());
    }

    /* renamed from: p */
    public HashMap<String, Boolean> m3148p() {
        HashMap<String, Boolean> hashMap = new HashMap<>();
        try {
            return (HashMap) this.objectCache.m8765b(ObjectCacheID.PICKED_EFFECT_RECORDS.name(), hashMap);
        } catch (Exception e) {
            return hashMap;
        }
    }

    /* renamed from: a */
    public void m3201a(HashMap<String, Boolean> hashMap) {
        this.objectCache.m8771a(ObjectCacheID.PICKED_EFFECT_RECORDS.name(), hashMap);
    }

    /* renamed from: q */
    public Map<String, FeedbackItem> m3147q() {
        try {
            return (Map) this.objectCache.m8765b(ObjectCacheID.FEEDBACK.name(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public void m3198a(Map<String, FeedbackItem> map) {
        this.objectCache.m8771a(ObjectCacheID.FEEDBACK.name(), map);
    }

    /* renamed from: c */
    public void m3181c(String str) {
        this.objectCache.m8771a(ObjectCacheID.MV_PLUGIN_INSTALLED_NAME.name(), str);
    }

    /* renamed from: r */
    public String m3146r() {
        try {
            return (String) this.objectCache.m8765b(ObjectCacheID.MV_PLUGIN_INSTALLED_NAME.name(), "");
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: a */
    public void m3203a(ArrayList<SkinItem> arrayList) {
        this.objectCache.m8771a(ObjectCacheID.RECOMMEND_SKIN_ITEMS.name(), arrayList);
    }

    /* renamed from: s */
    public ArrayList<SkinItem> m3145s() {
        try {
            return (ArrayList) this.objectCache.m8765b(ObjectCacheID.RECOMMEND_SKIN_ITEMS.name(), new ArrayList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public void m3190b(ArrayList<SkinItem> arrayList) {
        this.objectCache.m8771a(ObjectCacheID.RANK_SKIN_ITEMS.name(), arrayList);
    }

    /* renamed from: t */
    public ArrayList<SkinItem> m3144t() {
        try {
            return (ArrayList) this.objectCache.m8765b(ObjectCacheID.RANK_SKIN_ITEMS.name(), new ArrayList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    public void m3180c(ArrayList<SkinItem> arrayList) {
        this.objectCache.m8771a(ObjectCacheID.LOCAL_SKIN_ITEMS.name(), arrayList);
    }

    /* renamed from: u */
    public ArrayList<SkinItem> m3143u() {
        try {
            return (ArrayList) this.objectCache.m8765b(ObjectCacheID.LOCAL_SKIN_ITEMS.name(), new ArrayList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: v */
    public void m3142v() {
        this.objectCache.m8766b(ObjectCacheID.LOCAL_SKIN_ITEMS.name());
    }

    /* renamed from: a */
    public void m3208a(Integer num) {
        this.objectCache.m8771a(ObjectCacheID.INTERNAL_SKIN_COUNT.name(), num);
    }

    /* renamed from: w */
    public Integer m3141w() {
        try {
            return (Integer) this.objectCache.m8765b(ObjectCacheID.INTERNAL_SKIN_COUNT.name(), new Integer(0));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    public void m3178c(List<Billboards> list) {
        this.objectCache.m8771a(ObjectCacheID.BILLBOARD_CACHE.name(), list);
    }

    /* renamed from: x */
    public List<Billboards> m3140x() {
        try {
            return (List) this.objectCache.m8765b(ObjectCacheID.BILLBOARD_CACHE.name(), null);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: y */
    public int m3139y() {
        try {
            return ((Integer) this.objectCache.m8765b(ObjectCacheID.UNICOM_FLOW_TRIAL.name(), 0)).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    /* renamed from: z */
    public int m3138z() {
        try {
            return ((Integer) this.objectCache.m8765b(ObjectCacheID.UNICOM_FLOW_OPEN.name(), 0)).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    /* renamed from: a */
    public void m3217a(int i) {
        this.objectCache.m8771a(ObjectCacheID.UNICOM_FLOW_TRIAL.name(), Integer.valueOf(i));
    }

    /* renamed from: b */
    public void m3195b(int i) {
        this.objectCache.m8771a(ObjectCacheID.UNICOM_FLOW_OPEN.name(), Integer.valueOf(i));
    }

    /* renamed from: d */
    public void m3174d(String str) {
        this.objectCache.m8771a(ObjectCacheID.PHONE.name(), str);
    }

    /* renamed from: A */
    public String m3238A() {
        try {
            return (String) this.objectCache.m8765b(ObjectCacheID.PHONE.name(), "");
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: e */
    public void m3170e(String str) {
        this.objectCache.m8771a(ObjectCacheID.TOKEN.name(), str);
    }

    /* renamed from: f */
    public void m3166f(String str) {
        this.objectCache.m8771a(ObjectCacheID.MATTERS_ATTENTION.name(), str);
    }

    /* renamed from: g */
    public String m3163g(String str) {
        try {
            String str2 = (String) this.objectCache.m8765b(ObjectCacheID.MATTERS_ATTENTION.name(), str);
            return "".equals(str2) ? str : str2;
        } catch (Exception e) {
            return str;
        }
    }

    /* renamed from: c */
    public void m3184c(int i) {
        this.objectCache.m8771a(ObjectCacheID.UNICOM_FLOW_PRICE.name(), Integer.valueOf(i));
    }

    /* renamed from: d */
    public void m3175d(int i) {
        this.objectCache.m8771a(ObjectCacheID.TRIAL_DAY.name(), Integer.valueOf(i));
    }

    /* renamed from: h */
    public void m3160h(String str) {
        this.objectCache.m8771a(ObjectCacheID.UNICOM_FLOW_OPEN_TIME.name(), str);
    }

    /* renamed from: B */
    public String m3237B() {
        try {
            return (String) this.objectCache.m8765b(ObjectCacheID.UNICOM_FLOW_OPEN_TIME.name(), "");
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: i */
    public void m3158i(String str) {
        this.objectCache.m8771a(ObjectCacheID.UNICOM_FLOW_TRIAL_TIME.name(), str);
    }

    /* renamed from: j */
    public void m3156j(String str) {
        this.objectCache.m8771a(ObjectCacheID.UNICOM_FLOW_UNSUBSCRIBE_TIME.name(), str);
    }

    /* renamed from: C */
    public String m3236C() {
        try {
            return (String) this.objectCache.m8765b(ObjectCacheID.UNICOM_FLOW_UNSUBSCRIBE_TIME.name(), "");
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: a */
    public void m3197a(boolean z) {
        this.objectCache.m8771a(ObjectCacheID.UNICOM_FLOW_BEGIN_MONTH_DIALOG.name(), Boolean.valueOf(z));
    }

    /* renamed from: D */
    public boolean m3235D() {
        try {
            return ((Boolean) this.objectCache.m8765b(ObjectCacheID.UNICOM_FLOW_BEGIN_MONTH_DIALOG.name(), true)).booleanValue();
        } catch (Exception e) {
            return true;
        }
    }

    /* renamed from: a */
    public void m3202a(Date date) {
        this.objectCache.m8771a(ObjectCacheID.UNICOM_FLOW_BEGIN_MONTH_DIALOG_DATE.name(), date);
    }

    /* renamed from: b */
    public Date m3189b(Date date) {
        try {
            return (Date) this.objectCache.m8765b(ObjectCacheID.UNICOM_FLOW_BEGIN_MONTH_DIALOG_DATE.name(), date);
        } catch (Exception e) {
            return date;
        }
    }

    /* renamed from: b */
    public void m3186b(boolean z) {
        this.objectCache.m8771a(ObjectCacheID.UNICOM_FLOW_IS_POPUP_DIALOG.name(), Boolean.valueOf(z));
    }

    /* renamed from: E */
    public boolean m3234E() {
        try {
            return ((Boolean) this.objectCache.m8765b(ObjectCacheID.UNICOM_FLOW_IS_POPUP_DIALOG.name(), true)).booleanValue();
        } catch (Exception e) {
            return true;
        }
    }

    /* renamed from: c */
    public void m3177c(boolean z) {
        this.objectCache.m8771a(ObjectCacheID.UNICOM_FLOW_30M_DIALOG.name(), Boolean.valueOf(z));
    }

    /* renamed from: F */
    public boolean m3233F() {
        try {
            return ((Boolean) this.objectCache.m8765b(ObjectCacheID.UNICOM_FLOW_30M_DIALOG.name(), true)).booleanValue();
        } catch (Exception e) {
            return true;
        }
    }

    /* renamed from: c */
    public void m3179c(Date date) {
        this.objectCache.m8771a(ObjectCacheID.UNICOM_FLOW_IS_POPUP_DIALOG_DATE.name(), date);
    }

    /* renamed from: G */
    public Date m3232G() {
        try {
            return (Date) this.objectCache.m8765b(ObjectCacheID.UNICOM_FLOW_IS_POPUP_DIALOG_DATE.name(), new Date());
        } catch (Exception e) {
            return new Date();
        }
    }

    /* renamed from: d */
    public void m3172d(boolean z) {
        this.objectCache.m8771a(ObjectCacheID.UNICOM_FLOW_ENABLE.name(), Boolean.valueOf(z));
    }

    /* renamed from: H */
    public boolean m3231H() {
        try {
            return ((Boolean) this.objectCache.m8765b(ObjectCacheID.UNICOM_FLOW_ENABLE.name(), true)).booleanValue();
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: e */
    public void m3168e(boolean z) {
        this.objectCache.m8771a(ObjectCacheID.UNICOM_FLOW_USABLE.name(), Boolean.valueOf(z));
    }

    /* renamed from: I */
    public boolean m3230I() {
        try {
            return ((Boolean) this.objectCache.m8765b(ObjectCacheID.UNICOM_FLOW_USABLE.name(), true)).booleanValue();
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: f */
    public void m3165f(boolean z) {
        this.objectCache.m8771a(ObjectCacheID.UNICOM_FLOW_TRIAL_ENABLE.name(), Boolean.valueOf(z));
    }

    /* renamed from: J */
    public boolean m3229J() {
        try {
            return ((Boolean) this.objectCache.m8765b(ObjectCacheID.UNICOM_FLOW_TRIAL_ENABLE.name(), false)).booleanValue();
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: b */
    public void m3194b(long j) {
        this.objectCache.m8771a(ObjectCacheID.UNICOM_GPRS_TOTAL_FLOW.name(), Long.valueOf(j));
    }

    /* renamed from: K */
    public long m3228K() {
        try {
            return ((Long) this.objectCache.m8765b(ObjectCacheID.UNICOM_GPRS_TOTAL_FLOW.name(), 0L)).longValue();
        } catch (Exception e) {
            return 0L;
        }
    }

    /* renamed from: c */
    public void m3183c(long j) {
        this.objectCache.m8771a(ObjectCacheID.UNICOM_PROXY_TOTAL_FLOW.name(), Long.valueOf(j));
    }

    /* renamed from: L */
    public long m3227L() {
        try {
            return ((Long) this.objectCache.m8765b(ObjectCacheID.UNICOM_PROXY_TOTAL_FLOW.name(), 0L)).longValue();
        } catch (Exception e) {
            return 0L;
        }
    }

    /* renamed from: g */
    public void m3162g(boolean z) {
        this.objectCache.m8771a(ObjectCacheID.IS_SHOW_MY_FRAGMENT_UNICOM.name(), Boolean.valueOf(z));
    }

    /* renamed from: M */
    public boolean m3226M() {
        try {
            return ((Boolean) this.objectCache.m8765b(ObjectCacheID.IS_SHOW_MY_FRAGMENT_UNICOM.name(), true)).booleanValue();
        } catch (Exception e) {
            return true;
        }
    }

    /* renamed from: d */
    public void m3173d(List<String> list) {
        this.objectCache.m8768a(ObjectCacheID.LOCAL_FAV_MEDIA_ID_LIST.name(), (Object) list, false);
    }

    /* renamed from: N */
    public MediaItem getCurrentPlayMediaItem() {
        try {
            MediaItem mediaItem = (MediaItem) this.objectCache.m8764b(ObjectCacheID.CURRENT_PLAYING_MEDIAITEM.name(), MediaItem.MEDIA_ITEM_NULL, false);
            if (mediaItem.isOnline()) {
                mediaItem.setFav(MediaItemUtils.m4715a(mediaItem));
                return mediaItem;
            }
            return mediaItem;
        } catch (Exception e) {
            e.printStackTrace();
            return MediaItem.MEDIA_ITEM_NULL;
        }
    }

    /* renamed from: c */
    public void m3182c(MediaItem mediaItem) {
        this.objectCache.m8768a(ObjectCacheID.CURRENT_PLAYING_MEDIAITEM.name(), (Object) mediaItem, false);
    }

    /* renamed from: O */
    public List<String> m3224O() {
        try {
            return (List) this.objectCache.m8764b(ObjectCacheID.ONLINE_FAV_MEDIA_ID_LIST.name(), new ArrayList(), false);
        } catch (Exception e) {
            return new ArrayList();
        }
    }

    /* renamed from: k */
    public void m3154k(String str) {
        this.objectCache.m8771a(ObjectCacheID.UNICOM_IMSI.name(), str);
    }

    /* renamed from: P */
    public String m3223P() {
        try {
            return (String) this.objectCache.m8765b(ObjectCacheID.UNICOM_IMSI.name(), "");
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: l */
    public void m3152l(String str) {
        this.objectCache.m8771a(ObjectCacheID.UNICOM_SERVER_TIME.name(), str);
    }

    /* renamed from: Q */
    public String m3222Q() {
        try {
            return (String) this.objectCache.m8765b(ObjectCacheID.UNICOM_SERVER_TIME.name(), "");
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: e */
    public void m3169e(List<String> list) {
        this.objectCache.m8768a(ObjectCacheID.ONLINE_FAV_MEDIA_ID_LIST.name(), (Object) list, false);
    }

    /* renamed from: R */
    public void m3221R() {
        this.objectCache.m8766b(ObjectCacheID.ONLINE_FAV_MEDIA_ID_LIST.name());
    }

    /* renamed from: S */
    public List<String> m3220S() {
        try {
            return (List) this.objectCache.m8764b(ObjectCacheID.LOCAL_FAV_MEDIA_ID_LIST.name(), new ArrayList(), false);
        } catch (Exception e) {
            return new ArrayList();
        }
    }
}
