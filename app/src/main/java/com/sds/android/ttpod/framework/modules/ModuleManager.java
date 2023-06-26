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
    private static final Map<CommandID, Set<ModuleID>> commandIdModuleIdMaps = new EnumMap(CommandID.class);

    /* renamed from: f */
    private static final Map<ModuleID, Class> moduleIdClassMaps = new EnumMap(ModuleID.class);

    /* renamed from: b */
    private Map<ModuleID, BaseModule> moduleIdModuleMaps = new EnumMap(ModuleID.class);

    /* renamed from: c */
    private Map<ModuleID, Long> moduleIdTimeMaps = new EnumMap(ModuleID.class);

    /* renamed from: d */
    private Handler f6096d = new Handler() { // from class: com.sds.android.ttpod.framework.modules.d.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            long currentTimeMillis = System.currentTimeMillis();
            HashSet<ModuleID> hashSet = new HashSet();
            for (ModuleID moduleID : ModuleManager.this.moduleIdTimeMaps.keySet()) {
                if (((Long) ModuleManager.this.moduleIdTimeMaps.get(moduleID)).longValue() + ((BaseModule) ModuleManager.this.moduleIdModuleMaps.get(moduleID)).timeOutInMills() < currentTimeMillis) {
                    hashSet.add(moduleID);
                }
            }
            for (ModuleID moduleID2 : hashSet) {
                ModuleManager.this.m4113a(moduleID2);
            }
            ModuleManager.this.f6096d.removeMessages(1);
            if (!ModuleManager.this.moduleIdTimeMaps.isEmpty()) {
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
        moduleIdClassMaps.put(ModuleID.DOWNLOAD_MANAGER, DownloadManagerModule.class);
        moduleIdClassMaps.put(ModuleID.FAVORITE, FavoriteModule.class);
        moduleIdClassMaps.put(ModuleID.MEDIA_SCAN, MediaScanModule.class);
        moduleIdClassMaps.put(ModuleID.MEDIA_ACCESS, MediaAccessModule.class);
        moduleIdClassMaps.put(ModuleID.MONITOR, MonitorModule.class);
        moduleIdClassMaps.put(ModuleID.MUSIC_CIRCLE, MusicCircleModule.class);
        moduleIdClassMaps.put(ModuleID.SUPPORT, SupportModule.class);
        moduleIdClassMaps.put(ModuleID.FIND_SONG, FindSongModule.class);
        moduleIdClassMaps.put(ModuleID.SEARCH, SearchModule.class);
        moduleIdClassMaps.put(ModuleID.SKIN, SkinModule.class);
        moduleIdClassMaps.put(ModuleID.SPLASH, SplashModule.class);
        moduleIdClassMaps.put(ModuleID.USER_SYSTEM, UserSystemModule.class);
        moduleIdClassMaps.put(ModuleID.VERSION, VersionUpdateModule.class);
        moduleIdClassMaps.put(ModuleID.GLOBAL, GlobalModule.class);
        moduleIdClassMaps.put(ModuleID.LOCK_SCREEN, LockScreenModule.class);
        moduleIdClassMaps.put(ModuleID.AUDIO_EFFECT, AudioEffectModule.class);
        moduleIdClassMaps.put(ModuleID.THEME, ThemeModule.class);
        moduleIdClassMaps.put(ModuleID.VERSION_COMPACT, VersionCompactModule.class);
        moduleIdClassMaps.put(ModuleID.MUSIC_LIBRARY, MusicLibraryModule.class);
        moduleIdClassMaps.put(ModuleID.UNICOM_FLOW, UnicomFlowModule.class);
        moduleIdClassMaps.put(ModuleID.FEEDBACK, FeedbackModule.class);
    }

    /* renamed from: e */
    private static void m4103e() {
        CommandID[] m4563a;
        try {
            for (ModuleID moduleID : moduleIdClassMaps.keySet()) {
                ObserverCommandID observerCommandID = (ObserverCommandID) moduleIdClassMaps.get(moduleID).getAnnotation(ObserverCommandID.class);
                if (observerCommandID != null) {
                    for (CommandID commandID : observerCommandID.m4563a()) {
                        if (EnvironmentUtils.AppConfig.getTestMode() && commandID.getCommandType() != CommandType.FROM_MODULE) {
                            throw new IllegalArgumentException("ObserverCommandID must contain command with CommandType = FROM_MODULE");
                        }
                        Set<ModuleID> set = commandIdModuleIdMaps.get(commandID);
                        if (set == null) {
                            set = new HashSet<>();
                        }
                        set.add(moduleID);
                        commandIdModuleIdMaps.put(commandID, set);
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
        Set<ModuleID> set = commandIdModuleIdMaps.get(commandID);
        return set != null && set.contains(moduleID);
    }

    /* renamed from: a */
    public static ModuleManager getInstance() {
        return instance;
    }

    private ModuleManager() {
    }

    /* renamed from: a */
    public void loadCommandById(CommandID commandID) {
        if (commandID.getCommandType().equals(CommandType.TO_MODULE)) {
            add(commandID.getModuleID());
            return;
        }
        Set<ModuleID> set = commandIdModuleIdMaps.get(commandID);
        if (set != null) {
            for (ModuleID moduleID : set) {
                add(moduleID);
            }
        }
    }

    /* renamed from: c */
    private void add(ModuleID moduleID) {
        if (!containsModule(moduleID)) {
            LogUtils.debug("ModuleManager", "LoadModule:" + moduleID.name());
            BaseModule module = loadModulebyId(moduleID);
            module.onCreate();
            this.moduleIdModuleMaps.put(moduleID, module);
            if (module.timeOutInMills() != Long.MIN_VALUE) {
                this.moduleIdTimeMaps.put(moduleID, Long.valueOf(System.currentTimeMillis()));
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
        BaseModule baseModule = this.moduleIdModuleMaps.get(moduleID);
        if (baseModule != null) {
            baseModule.onPreDestroy();
            baseModule.onDestroy();
            this.moduleIdModuleMaps.remove(moduleID);
            this.moduleIdTimeMaps.remove(moduleID);
        }
    }

    /* renamed from: b */
    public void m4110b(ModuleID moduleID) {
        if (this.moduleIdTimeMaps.containsKey(moduleID)) {
            this.moduleIdTimeMaps.put(moduleID, Long.valueOf(System.currentTimeMillis()));
        }
    }

    /* renamed from: a */
    public void init(Context context) {
        BaseModule.setContext(context);
        initNormalModule();
    }

    /* renamed from: f */
    private void initNormalModule() {
        add(ModuleID.GLOBAL);
        add(ModuleID.FAVORITE);
        add(ModuleID.SUPPORT);
        add(ModuleID.MONITOR);
    }

    /* renamed from: b */
    public void m4111b() {
        for (BaseModule baseModule : this.moduleIdModuleMaps.values()) {
            baseModule.onPreDestroy();
        }
    }

    /* renamed from: c */
    public void m4108c() {
        DebugUtils.m8427a();
        this.f6096d.removeMessages(1);
        LogUtils.debug("ModuleManager", "unInitModule search lookLyricPic");
        for (BaseModule baseModule : this.moduleIdModuleMaps.values()) {
            baseModule.onDestroy();
        }
        this.moduleIdModuleMaps.clear();
    }

    /* renamed from: d */
    private boolean containsModule(ModuleID moduleID) {
        DebugUtils.m8427a();
        return this.moduleIdModuleMaps.containsKey(moduleID);
    }

    /* renamed from: e */
    private BaseModule loadModulebyId(ModuleID moduleID) {
        Class cls = moduleIdClassMaps.get(moduleID);
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
