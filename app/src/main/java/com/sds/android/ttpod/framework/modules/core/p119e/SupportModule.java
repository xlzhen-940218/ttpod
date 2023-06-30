package com.sds.android.ttpod.framework.modules.core.p119e;

import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.base.BaseModule;
import com.sds.android.ttpod.framework.base.ObserverCommandID;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.modules.p126h.UnicomFlowUtil;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.environment.PreferencesID;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.Support;
import com.sds.android.ttpod.framework.support.SupportCallback;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.framework.support.p134a.PlayMode;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

@ObserverCommandID(m4563a = {CommandID.SCAN_FINISHED})
/* renamed from: com.sds.android.ttpod.framework.modules.core.e.a */
/* loaded from: classes.dex */
public final class SupportModule extends BaseModule {

    /* renamed from: d */
    private Support f6027d;

    /* renamed from: a */
    private Random f6024a = new Random();

    /* renamed from: b */
    private HashSet<String> f6025b = new HashSet<>();

    /* renamed from: c */
    private SupportCallback f6026c = new SupportCallback() { // from class: com.sds.android.ttpod.framework.modules.core.e.a.1
        @Override // com.sds.android.ttpod.framework.support.SupportCallback
        /* renamed from: a */
        public void mo2448a() {
            super.mo2448a();
            Cache.getInstance().m3182c(MediaStorage.queryMediaItem(SupportModule.sContext, Preferences.getLocalGroupId(), Preferences.getMediaId()));
            CommandCenter.getInstance().m4595b(new Command(CommandID.PLAY_MEDIA_CHANGED, new Object[0]), ModuleID.SUPPORT);
            CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_PLAYING_MEDIA_INFO, new Object[0]), ModuleID.SUPPORT);
            CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_PLAY_POSITION, SupportModule.this.f6027d.m2465k()), ModuleID.SUPPORT);
            CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_PLAY_STATUS, SupportModule.this.f6027d.m2463m()), ModuleID.SUPPORT);
            PlayStatus m2463m = SupportModule.this.f6027d.m2463m();
            if (Preferences.m3076C() && m2463m != PlayStatus.STATUS_PLAYING && m2463m != PlayStatus.STATUS_PAUSED) {
                SupportModule.this.start();
            }
        }

        @Override // com.sds.android.ttpod.framework.support.SupportCallback
        /* renamed from: a */
        public void mo2446a(MediaItem mediaItem) {
            super.mo2446a(mediaItem);
            SupportModule.this.m4173a(mediaItem);
        }

        @Override // com.sds.android.ttpod.framework.support.SupportCallback
        /* renamed from: a */
        public void mo2445a(PlayStatus playStatus) {
            super.mo2445a(playStatus);
            CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_PLAY_STATUS, playStatus), ModuleID.SUPPORT);
            MediaItem m3225N = Cache.getInstance().getCurrentPlayMediaItem();
            if (playStatus == PlayStatus.STATUS_PLAYING) {
                if (!m3225N.isNull() && !m3225N.isOnline() && m3225N.getErrorStatus().intValue() == 1) {
                    m3225N.setErrorStatus(0);
                    MediaStorage.updateMediaItem(SupportModule.sContext, m3225N);
                    CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_PLAYING_MEDIA_INFO, new Object[0]), ModuleID.SUPPORT);
                }
            } else if ((playStatus == PlayStatus.STATUS_STOPPED || playStatus == PlayStatus.STATUS_ERROR) && StringUtils.isEmpty(Preferences.getMediaId())) {
                Cache.getInstance().m3182c((MediaItem) null);
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_PLAYING_MEDIA_INFO, new Object[0]), ModuleID.SUPPORT);
            }
        }

        @Override // com.sds.android.ttpod.framework.support.SupportCallback
        /* renamed from: b */
        public void mo2443b() {
            super.mo2443b();
            CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_BUFFERING_STATE_STARTED, new Object[0]), ModuleID.SUPPORT);
        }

        @Override // com.sds.android.ttpod.framework.support.SupportCallback
        /* renamed from: a */
        public void mo2444a(String str, int i) {
            super.mo2444a(str, i);
            LogUtils.debug("SupportModule", "onMediaDurationUpdated");
            MediaItem m3225N = Cache.getInstance().getCurrentPlayMediaItem();
            if (m3225N.getID().equals(str) && m3225N.getDuration().intValue() != i) {
                MediaItem queryMediaItem = MediaStorage.queryMediaItem(SupportModule.sContext, m3225N.getGroupID(), m3225N.getID());
                m3225N.setDuration(Integer.valueOf(i));
                if (queryMediaItem != null && !queryMediaItem.isNull()) {
                    queryMediaItem.setDuration(Integer.valueOf(i));
                    queryMediaItem.setDateLastPlayInMills(queryMediaItem.getDateLastAccessInMills());
                    if (!StringUtils.equals(queryMediaItem.getGroupID(), MediaStorage.GROUP_ID_ONLINE_TEMPORARY)) {
                        MediaStorage.updateMediaItem(SupportModule.sContext, queryMediaItem);
                    }
                    Cache.getInstance().m3182c(queryMediaItem);
                }
                CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_PLAYING_MEDIA_INFO, new Object[0]), ModuleID.SUPPORT);
            }
        }

        @Override // com.sds.android.ttpod.framework.support.SupportCallback
        /* renamed from: a */
        public void mo2447a(int i) {
            super.mo2447a(i);
            MediaItem m3225N = Cache.getInstance().getCurrentPlayMediaItem();
            if (!m3225N.isNull() && !m3225N.isOnline()) {
                CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_PLAYING_MEDIA_INFO, new Object[0]), ModuleID.SUPPORT);
            }
            CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_PLAY_ERROR, Integer.valueOf(i)), ModuleID.SUPPORT);
        }
    };

    /* renamed from: e */
    private Preferences.InterfaceC2031a f6028e = new Preferences.InterfaceC2031a() { // from class: com.sds.android.ttpod.framework.modules.core.e.a.2
        @Override // com.sds.android.ttpod.framework.storage.environment.Preferences.InterfaceC2031a
        /* renamed from: a */
        public void mo2553a(PreferencesID preferencesID) {
            if (PreferencesID.PLAY_MODE == preferencesID) {
                CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_PLAY_MODE, new Object[0]), ModuleID.SUPPORT);
            }
        }
    };

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    /* renamed from: id */
    protected ModuleID id() {
        return ModuleID.SUPPORT;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public void onCreate() {
        super.onCreate();
        this.f6027d = SupportFactory.getInstance(sContext);
        this.f6027d.mo2497a(this.f6026c);
        Preferences.m3019a(PreferencesID.PLAY_MODE, this.f6028e);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public void onDestroy() {
        Preferences.m2938b(PreferencesID.PLAY_MODE, this.f6028e);
        this.f6027d.m2473d();
        super.onDestroy();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    protected void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        Class<?> cls = getClass();
        map.put(CommandID.PLAY, ReflectUtils.loadMethod(cls, "play", String.class));
        map.put(CommandID.PLAY_GROUP, ReflectUtils.loadMethod(cls, "playGroup", String.class, MediaItem.class));
        map.put(CommandID.SYNC_GROUP, ReflectUtils.loadMethod(cls, "syncGroup", String.class, MediaItem.class));
        map.put(CommandID.SYNC_PLAYING_GROUP, ReflectUtils.loadMethod(cls, "syncPlayingGroup", new Class[0]));
        map.put(CommandID.SCAN_FINISHED, ReflectUtils.loadMethod(cls, "scanFinished", Integer.class));
        map.put(CommandID.START, ReflectUtils.loadMethod(cls, "start", new Class[0]));
        map.put(CommandID.STOP, ReflectUtils.loadMethod(cls, "stop", new Class[0]));
        map.put(CommandID.PAUSE, ReflectUtils.loadMethod(cls, "pause", new Class[0]));
        map.put(CommandID.RESUME, ReflectUtils.loadMethod(cls, "resume", new Class[0]));
        map.put(CommandID.PREVIOUS, ReflectUtils.loadMethod(cls, "previous", new Class[0]));
        map.put(CommandID.NEXT, ReflectUtils.loadMethod(cls, "next", new Class[0]));
        map.put(CommandID.EXIT, ReflectUtils.loadMethod(cls, "exit", new Class[0]));
        map.put(CommandID.SWITCH_PLAY_MODE, ReflectUtils.loadMethod(cls, "switchPlayMode", new Class[0]));
        map.put(CommandID.SET_POSITION, ReflectUtils.loadMethod(cls, "setPosition", Integer.class));
        map.put(CommandID.PLAY_LOCAL_RANDOM, ReflectUtils.loadMethod(cls, "playLocalRandom", new Class[0]));
        map.put(CommandID.SYNC_CUR_MEDIA_INFO, ReflectUtils.loadMethod(cls, "ayncCurMediaInfo", new Class[0]));
        map.put(CommandID.RESUME_IMAGE_SWITCHER, ReflectUtils.loadMethod(cls, "resumeImageSwitcher", new Class[0]));
        map.put(CommandID.PAUSE_IMAGE_SWITCHER, ReflectUtils.loadMethod(cls, "pauseImageSwitcher", new Class[0]));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m4173a(MediaItem mediaItem) {
        MediaItem m3225N = Cache.getInstance().getCurrentPlayMediaItem();
        if ((m3225N != null && !m3225N.equals(mediaItem)) || (mediaItem != null && !mediaItem.equals(m3225N))) {
            Cache.getInstance().m3182c(mediaItem);
            m4171b(mediaItem);
            CommandCenter.getInstance().m4604a(new Command(CommandID.PLAY_MEDIA_CHANGED, new Object[0]), ModuleID.SUPPORT);
            CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_PLAYING_MEDIA_INFO, new Object[0]), ModuleID.SUPPORT);
        }
    }

    /* renamed from: b */
    private void m4171b(MediaItem mediaItem) {
        if (mediaItem != null && mediaItem.isOnline() && !m4168e()) {
            CommandCenter.getInstance().postInvokeResult(new Command(CommandID.CHECK_USE_GPRS_POPUP_DIALOG, new Object[0]));
        }
    }

    public void play(String str) {
        MediaItem m4712a = MediaItemUtils.m4712a(str);
        if (m4712a != null) {
            if (MediaStorage.queryMediaItem(sContext, MediaStorage.GROUP_ID_ALL_LOCAL, m4712a.getID()) == null) {
                MediaStorage.insertMediaItem(sContext, MediaStorage.GROUP_ID_ALL_LOCAL, m4712a);
            }
            playGroup(MediaStorage.GROUP_ID_ALL_LOCAL, MediaStorage.queryMediaItem(sContext, MediaStorage.GROUP_ID_ALL_LOCAL, m4712a.getID()));
        }
    }

    public Boolean playLocalRandom() {
        List<String> queryMediaIDs = MediaStorage.queryMediaIDs(sContext, MediaStorage.GROUP_ID_ALL_LOCAL, Preferences.m2860l(MediaStorage.GROUP_ID_ALL_LOCAL));
        int size = queryMediaIDs.size();
        if (size == 0) {
            return false;
        }
        if (this.f6025b.size() < size) {
            queryMediaIDs.removeAll(this.f6025b);
            size = queryMediaIDs.size();
        } else {
            this.f6025b.clear();
        }
        String str = queryMediaIDs.get(this.f6024a.nextInt(size));
        this.f6025b.add(str);
        playGroup(MediaStorage.GROUP_ID_ALL_LOCAL, MediaStorage.queryMediaItem(sContext, MediaStorage.GROUP_ID_ALL_LOCAL, str));
        return true;
    }

    public void playGroup(String str, MediaItem mediaItem) {
        boolean z = !StringUtils.equals(str, Preferences.getLocalGroupId());
        if (MediaStorage.GROUP_ID_ONLINE_TEMPORARY.equals(str)) {
            z = true;
        }
        if (!StringUtils.equals(str, MediaStorage.GROUP_ID_ONLINE_TEMPORARY)) {
            Preferences.setOnlineMediaListGroupName("");
        }
        if (z) {
            CommandCenter.getInstance().m4595b(new Command(CommandID.PLAY_GROUP_CHANGED, new Object[0]), ModuleID.SUPPORT);
            Preferences.m2894d(str);
        }
        boolean z2 = StringUtils.equals(mediaItem.getID(), Preferences.getMediaId()) ? false : true;
        if (z || z2) {
            Preferences.getMediaId(mediaItem.getID());
            m4173a(mediaItem);
        }
        this.f6027d.mo2489a(str, mediaItem.getID());
    }

    public void syncGroup(String str, MediaItem mediaItem) {
        DebugUtils.m8426a(mediaItem, "mediaItem");
        Preferences.getMediaId(mediaItem.getID());
        this.f6027d.mo2479b(str, mediaItem.getID());
    }

    public void syncPlayingGroup() {
        this.f6027d.mo2479b(Preferences.getLocalGroupId(), Preferences.getMediaId());
    }

    public void scanFinished(Integer num) {
        syncPlayingGroup();
    }

    public void start() {
        this.f6027d.mo2505a();
    }

    public void stop() {
        this.f6027d.m2470f();
    }

    public void pause() {
        this.f6027d.m2469g();
    }

    public void resume() {
        this.f6027d.m2468h();
    }

    public void previous() {
        this.f6027d.mo2485b();
    }

    public void next() {
        this.f6027d.mo2478c();
    }

    public void resumeImageSwitcher() {
        this.f6027d.m2467i();
    }

    public void pauseImageSwitcher() {
        this.f6027d.m2466j();
    }

    public void exit() {
        CommandCenter.getInstance().execute(new Command(CommandID.SAVE_UNICOM_TOTAL_FLOW, new Object[0]));
        this.f6027d.m2471e();
    }

    public void switchPlayMode() {
        Preferences.m3018a(PlayMode.values()[(Preferences.m2862l().ordinal() + 1) % PlayMode.values().length]);
        CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_PLAY_MODE, new Object[0]), ModuleID.SUPPORT);
    }

    public void setPosition(Integer num) {
        this.f6027d.m2491a(num);
    }

    public void syncCurMediaInfo() {
        String m2858m = Preferences.getLocalGroupId();
        String m2854n = Preferences.getMediaId();
        Cache.getInstance().m3182c(MediaStorage.queryMediaItem(sContext, m2858m, m2854n));
        this.f6027d.mo2474c(m2858m, m2854n);
        CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_PLAYING_MEDIA_INFO, new Object[0]), ModuleID.SUPPORT);
        this.f6027d.m2462n();
    }

    /* renamed from: e */
    private boolean m4168e() {
        if (HttpRequest.m8701c()) {
            long m2453w = this.f6027d.m2453w();
            long m8718a = m2453w + HttpRequest.getContentLength() + Cache.getInstance().m3228K();
            LogUtils.debug("SupportModule", "unicom flow handler greater than 30 size:" + m8718a);
            if (m8718a > 31457280) {
                Preferences.m2943b((float) UnicomFlowUtil.m3955a(m8718a));
                CommandCenter.getInstance().postInvokeResult(new Command(CommandID.UNICOM_FLOW_30M_DIALOG, new Object[0]));
                return true;
            }
            return false;
        }
        return false;
    }

    public void ayncCurMediaInfo() {
        CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_PLAYING_MEDIA_INFO, new Object[0]), ModuleID.SUPPORT);
        syncPlayingGroup();
    }
}
