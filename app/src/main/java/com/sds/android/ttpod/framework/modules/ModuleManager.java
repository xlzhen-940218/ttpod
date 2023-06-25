package com.sds.android.ttpod.framework.modules;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.framework.base.BaseModule;
import com.sds.android.ttpod.framework.base.CommandType;
import com.sds.android.ttpod.framework.base.ObserverCommandID;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectModule;
import com.sds.android.ttpod.framework.modules.core.monitor.MonitorModule;
import com.sds.android.ttpod.framework.modules.core.p112a.DownloadManagerModule;
import com.sds.android.ttpod.framework.modules.core.p113b.GlobalModule;
import com.sds.android.ttpod.framework.modules.core.p115c.MediaAccessModule;
import com.sds.android.ttpod.framework.modules.core.p116d.MediaScanModule;
import com.sds.android.ttpod.framework.modules.core.p119e.SupportModule;
import com.sds.android.ttpod.framework.modules.core.p120f.UserSystemModule;
import com.sds.android.ttpod.framework.modules.core.p121g.VersionCompactModule;
import com.sds.android.ttpod.framework.modules.p109a.FavoriteModule;
import com.sds.android.ttpod.framework.modules.p110b.FindSongModule;
import com.sds.android.ttpod.framework.modules.p110b.MusicLibraryModule;
import com.sds.android.ttpod.framework.modules.p111c.FeedbackModule;
import com.sds.android.ttpod.framework.modules.p123e.LockScreenModule;
import com.sds.android.ttpod.framework.modules.p124f.MusicCircleModule;
import com.sds.android.ttpod.framework.modules.p125g.SplashModule;
import com.sds.android.ttpod.framework.modules.p126h.UnicomFlowModule;
import com.sds.android.ttpod.framework.modules.search.SearchModule;
import com.sds.android.ttpod.framework.modules.skin.SkinModule;
import com.sds.android.ttpod.framework.modules.theme.ThemeModule;
import com.sds.android.ttpod.framework.modules.version.VersionUpdateModule;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* renamed from: com.sds.android.ttpod.framework.modules.d */
/* loaded from: classes.dex */
public final class ModuleManager {

    /* renamed from: a */
    private static ModuleManager instance = new ModuleManager();

    /* renamed from: e */
    private static final Map<CommandID, Set<ModuleID>> f6092e = new EnumMap(CommandID.class);

    /* renamed from: f */
    private static final Map<ModuleID, Class> moduleIdMaps = new EnumMap(ModuleID.class);

    /* renamed from: b */
    private Map<ModuleID, BaseModule> f6094b = new EnumMap(ModuleID.class);

    /* renamed from: c */
    private Map<ModuleID, Long> f6095c = new EnumMap(ModuleID.class);

    /* renamed from: d */
    private Handler f6096d = new Handler() { // from class: com.sds.android.ttpod.framework.modules.d.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            long currentTimeMillis = System.currentTimeMillis();
            HashSet<ModuleID> hashSet = new HashSet();
            for (ModuleID moduleID : ModuleManager.this.f6095c.keySet()) {
                if (((Long) ModuleManager.this.f6095c.get(moduleID)).longValue() + ((BaseModule) ModuleManager.this.f6094b.get(moduleID)).timeOutInMills() < currentTimeMillis) {
                    hashSet.add(moduleID);
                }
            }
            for (ModuleID moduleID2 : hashSet) {
                ModuleManager.this.m4113a(moduleID2);
            }
            ModuleManager.this.f6096d.removeMessages(1);
            if (!ModuleManager.this.f6095c.isEmpty()) {
                ModuleManager.this.f6096d.sendEmptyMessageDelayed(1, 15000L);
            }
        }
    };

    static {
        m4105d();
        m4103e();
    }

    /* renamed from: d */
    private static void m4105d() {
        moduleIdMaps.put(ModuleID.DOWNLOAD_MANAGER, DownloadManagerModule.class);
        moduleIdMaps.put(ModuleID.FAVORITE, FavoriteModule.class);
        moduleIdMaps.put(ModuleID.MEDIA_SCAN, MediaScanModule.class);
        moduleIdMaps.put(ModuleID.MEDIA_ACCESS, MediaAccessModule.class);
        moduleIdMaps.put(ModuleID.MONITOR, MonitorModule.class);
        moduleIdMaps.put(ModuleID.MUSIC_CIRCLE, MusicCircleModule.class);
        moduleIdMaps.put(ModuleID.SUPPORT, SupportModule.class);
        moduleIdMaps.put(ModuleID.FIND_SONG, FindSongModule.class);
        moduleIdMaps.put(ModuleID.SEARCH, SearchModule.class);
        moduleIdMaps.put(ModuleID.SKIN, SkinModule.class);
        moduleIdMaps.put(ModuleID.SPLASH, SplashModule.class);
        moduleIdMaps.put(ModuleID.USER_SYSTEM, UserSystemModule.class);
        moduleIdMaps.put(ModuleID.VERSION, VersionUpdateModule.class);
        moduleIdMaps.put(ModuleID.GLOBAL, GlobalModule.class);
        moduleIdMaps.put(ModuleID.LOCK_SCREEN, LockScreenModule.class);
        moduleIdMaps.put(ModuleID.AUDIO_EFFECT, AudioEffectModule.class);
        moduleIdMaps.put(ModuleID.THEME, ThemeModule.class);
        moduleIdMaps.put(ModuleID.VERSION_COMPACT, VersionCompactModule.class);
        moduleIdMaps.put(ModuleID.MUSIC_LIBRARY, MusicLibraryModule.class);
        moduleIdMaps.put(ModuleID.UNICOM_FLOW, UnicomFlowModule.class);
        moduleIdMaps.put(ModuleID.FEEDBACK, FeedbackModule.class);
    }

    /* renamed from: e */
    private static void m4103e() {
        CommandID[] m4563a;
        try {
            for (ModuleID moduleID : moduleIdMaps.keySet()) {
                ObserverCommandID observerCommandID = (ObserverCommandID) moduleIdMaps.get(moduleID).getAnnotation(ObserverCommandID.class);
                if (observerCommandID != null) {
                    for (CommandID commandID : observerCommandID.m4563a()) {
                        if (EnvironmentUtils.C0602a.m8503h() && commandID.getCommandType() != CommandType.FROM_MODULE) {
                            throw new IllegalArgumentException("ObserverCommandID must contain command with CommandType = FROM_MODULE");
                        }
                        Set<ModuleID> set = f6092e.get(commandID);
                        if (set == null) {
                            set = new HashSet<>();
                        }
                        set.add(moduleID);
                        f6092e.put(commandID, set);
                    }
                    continue;
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    /* renamed from: a */
    public static boolean m4114a(CommandID commandID, ModuleID moduleID) {
        DebugUtils.m8426a(commandID, "commandID");
        DebugUtils.m8426a(moduleID, "moduleClass");
        Set<ModuleID> set = f6092e.get(commandID);
        return set != null && set.contains(moduleID);
    }

    /* renamed from: a */
    public static ModuleManager getInstance() {
        return instance;
    }

    private ModuleManager() {
    }

    /* renamed from: a */
    public void m4115a(CommandID commandID) {
        if (commandID.getCommandType().equals(CommandType.TO_MODULE)) {
            m4107c(commandID.getModuleID());
            return;
        }
        Set<ModuleID> set = f6092e.get(commandID);
        if (set != null) {
            for (ModuleID moduleID : set) {
                m4107c(moduleID);
            }
        }
    }

    /* renamed from: c */
    private void m4107c(ModuleID moduleID) {
        if (!m4104d(moduleID)) {
            LogUtils.debug("ModuleManager", "LoadModule:" + moduleID.name());
            BaseModule m4102e = loadModulebyId(moduleID);
            m4102e.onCreate();
            this.f6094b.put(moduleID, m4102e);
            if (m4102e.timeOutInMills() != Long.MIN_VALUE) {
                this.f6095c.put(moduleID, Long.valueOf(System.currentTimeMillis()));
                if (!this.f6096d.hasMessages(1)) {
                    this.f6096d.sendEmptyMessageDelayed(1, 15000L);
                }
            }
        }
    }

    /* renamed from: a */
    public void m4113a(ModuleID moduleID) {
        LogUtils.info("ModuleManager", "unloadModule:" + moduleID.name());
        DebugUtils.m8427a();
        BaseModule baseModule = this.f6094b.get(moduleID);
        if (baseModule != null) {
            baseModule.onPreDestroy();
            baseModule.onDestroy();
            this.f6094b.remove(moduleID);
            this.f6095c.remove(moduleID);
        }
    }

    /* renamed from: b */
    public void m4110b(ModuleID moduleID) {
        if (this.f6095c.containsKey(moduleID)) {
            this.f6095c.put(moduleID, Long.valueOf(System.currentTimeMillis()));
        }
    }

    /* renamed from: a */
    public void m4116a(Context context) {
        BaseModule.setContext(context);
        m4101f();
    }

    /* renamed from: f */
    private void m4101f() {
        m4107c(ModuleID.GLOBAL);
        m4107c(ModuleID.FAVORITE);
        m4107c(ModuleID.SUPPORT);
        m4107c(ModuleID.MONITOR);
    }

    /* renamed from: b */
    public void m4111b() {
        for (BaseModule baseModule : this.f6094b.values()) {
            baseModule.onPreDestroy();
        }
    }

    /* renamed from: c */
    public void m4108c() {
        DebugUtils.m8427a();
        this.f6096d.removeMessages(1);
        LogUtils.debug("ModuleManager", "unInitModule search lookLyricPic");
        for (BaseModule baseModule : this.f6094b.values()) {
            baseModule.onDestroy();
        }
        this.f6094b.clear();
    }

    /* renamed from: d */
    private boolean m4104d(ModuleID moduleID) {
        DebugUtils.m8427a();
        return this.f6094b.containsKey(moduleID);
    }

    /* renamed from: e */
    private BaseModule loadModulebyId(ModuleID moduleID) {
        Class cls = moduleIdMaps.get(moduleID);
        if (cls == null) {
            throw new IllegalArgumentException("Module(" + moduleID.name() + " not existed or not be register!");
        }
        try {
            return (BaseModule) cls.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Module(" + moduleID.name() + " can not be loaded!");
        }
    }
}
