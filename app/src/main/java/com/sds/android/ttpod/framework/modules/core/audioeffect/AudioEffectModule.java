package com.sds.android.ttpod.framework.modules.core.audioeffect;

import android.os.Build;
import android.os.Handler;
import com.sds.android.cloudapi.ttpod.data.AudioEffectItem;
import com.sds.android.cloudapi.ttpod.data.AudioEffectItemData;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.p055a.CloudAudioEffectAPI;
import com.sds.android.cloudapi.ttpod.p055a.OnlineMediaItemAPI;
import com.sds.android.cloudapi.ttpod.result.AudioEffectAddResult;
import com.sds.android.cloudapi.ttpod.result.AudioEffectCommResult;
import com.sds.android.cloudapi.ttpod.result.AudioEffectItemResult;
import com.sds.android.cloudapi.ttpod.result.AudioEffectUserResult;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.SecurityUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseModule;
import com.sds.android.ttpod.framework.base.ObserverCommandID;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.audiofx.TTEqualizer;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

@ObserverCommandID(m4563a = {CommandID.AUDIOEFFECT_CHANGED})
/* renamed from: com.sds.android.ttpod.framework.modules.core.audioeffect.c */
/* loaded from: classes.dex */
public class AudioEffectModule extends BaseModule {

    /* renamed from: a */
    private static final ReentrantLock f5858a = new ReentrantLock();

    /* renamed from: d */
    private HashMap<String, Boolean> f5861d;

    /* renamed from: b */
    private volatile List<String> f5859b = new ArrayList();

    /* renamed from: c */
    private volatile List<String> f5860c = new ArrayList();

    /* renamed from: e */
    private Runnable f5862e = new Runnable() { // from class: com.sds.android.ttpod.framework.modules.core.audioeffect.c.1
        @Override // java.lang.Runnable
        public void run() {
            CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_SAVE_EFFECT_TO_LOCAL, Boolean.TRUE), ModuleID.AUDIO_EFFECT);
        }
    };

    /* renamed from: f */
    private Handler f5863f = new Handler();

    /* renamed from: g */
    private RunnableC1855a f5864g = new RunnableC1855a();

    /* compiled from: AudioEffectModule.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.core.audioeffect.c$a */
    /* loaded from: classes.dex */
    private class RunnableC1855a implements Runnable {
        private RunnableC1855a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AudioEffectModule.this.m4355h();
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    /* renamed from: id */
    protected ModuleID id() {
        return ModuleID.AUDIO_EFFECT;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public void onCreate() {
        super.onCreate();
        m4357f();
        this.f5861d = Cache.getInstance().m3148p();
    }

    /* renamed from: f */
    private void m4357f() {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.core.audioeffect.c.5
            @Override // java.lang.Runnable
            public void run() {
                AudioEffectModule.f5858a.lock();
                if (!MediaStorage.isGroupExisted(AudioEffectModule.sContext, MediaStorage.GROUP_ID_EFFECT_LOCAL)) {
                    MediaStorage.insertGroup(AudioEffectModule.sContext, MediaStorage.GROUP_NAME_EFFECT_LOCAL, MediaStorage.GROUP_ID_EFFECT_LOCAL, GroupType.CUSTOM_LOCAL);
                } else {
                    AudioEffectModule.this.f5859b = AudioEffectModule.this.m4358e(MediaStorage.GROUP_ID_EFFECT_LOCAL);
                }
                if (!MediaStorage.isGroupExisted(AudioEffectModule.sContext, MediaStorage.GROUP_ID_EFFECT_ONLINE)) {
                    MediaStorage.insertGroup(AudioEffectModule.sContext, MediaStorage.GROUP_NAME_EFFECT_ONLINE, MediaStorage.GROUP_ID_EFFECT_ONLINE, GroupType.CUSTOM_ONLINE);
                } else {
                    AudioEffectModule.this.f5860c = AudioEffectModule.this.m4358e(MediaStorage.GROUP_ID_EFFECT_ONLINE);
                }
                AudioEffectModule.f5858a.unlock();
            }
        });
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public void onDestroy() {
        super.onDestroy();
        Cache.getInstance().m3201a(this.f5861d);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public long timeOutInMills() {
        return 60000;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    protected void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        Class<?> cls = getClass();
        map.put(CommandID.SET_CLOUD_AUDIO_EFFECT_ENABLED, ReflectUtils.m8375a(cls, "setCloudAudioEffectEnabled", Boolean.class));
        map.put(CommandID.SET_CLOUD_AUDIO_EFFECT, ReflectUtils.m8375a(cls, "applyCloudAudioEffect", AudioEffectItem.class, Boolean.class));
        map.put(CommandID.SET_LOCAL_AUDIO_EFFECT, ReflectUtils.m8375a(cls, "setLocalAudioEffectEnabled", Boolean.class));
        map.put(CommandID.SET_EQUALIZER, ReflectUtils.m8375a(cls, "setEqualizer", TTEqualizer.Settings.class));
        map.put(CommandID.GET_EQUALIZER, ReflectUtils.m8375a(cls, "getEqualizer", new Class[0]));
        map.put(CommandID.SET_BASSBOOST, ReflectUtils.m8375a(cls, "setBassBoost", Integer.class));
        map.put(CommandID.SET_BOOSTLIMIT_ENABLED, ReflectUtils.m8375a(cls, "setBoostLimitEnabled", Boolean.class));
        map.put(CommandID.SET_TREBLEBOOST, ReflectUtils.m8375a(cls, "setTrebleBoost", Integer.class));
        map.put(CommandID.SET_VIRTUALIZER, ReflectUtils.m8375a(cls, "setVirtualizer", Integer.class));
        map.put(CommandID.SET_REVERB, ReflectUtils.m8375a(cls, "setReverb", Integer.class));
        map.put(CommandID.SET_CHANNELBALANCE, ReflectUtils.m8375a(cls, "setChannelBalance", Float.class));
        map.put(CommandID.SAVE_CUSTOM_EQUALIZER, ReflectUtils.m8375a(cls, "saveCustomEqualizer", TTEqualizer.Settings.class));
        map.put(CommandID.DELETE_CUSTOM_EQUALIZER, ReflectUtils.m8375a(cls, "deleteCustomEqualizer", String.class));
        map.put(CommandID.QUERY_CUSTOM_EQUALIZER_LIST, ReflectUtils.m8375a(cls, "queryCustomEqualizerList", new Class[0]));
        map.put(CommandID.QUERY_EFFECT_USERINFO, ReflectUtils.m8375a(cls, "queryEffectUserInfo", new Class[0]));
        map.put(CommandID.QUERY_EFFECT, ReflectUtils.m8375a(cls, "queryEffect", MediaItem.class, Integer.class, Integer.class));
        map.put(CommandID.PICK_EFFECT, ReflectUtils.m8375a(cls, "pickEffect", String.class));
        map.put(CommandID.BIND_EFFECT, ReflectUtils.m8375a(cls, "bindEffect", String.class));
        map.put(CommandID.QUERY_PRIVATE_EFFECT, ReflectUtils.m8375a(cls, "queryPrivateEffect", new Class[0]));
        map.put(CommandID.DELETE_PRIVATE_EFFECT_LIST, ReflectUtils.m8375a(cls, "deletePrivateEffectList", List.class));
        map.put(CommandID.SET_EFFECT_ITEM, ReflectUtils.m8375a(cls, "setEffectItem", AudioEffectItem.class));
        map.put(CommandID.SAVE_EFFECT_TO_LOCAL, ReflectUtils.m8375a(cls, "saveEffectToLocal", MediaItem.class, AudioEffectCache.class));
        map.put(CommandID.SAVE_EFFECT_TO_NETWORK, ReflectUtils.m8375a(cls, "saveEffectToNetwork", MediaItem.class, AudioEffectItemData.class, Integer.class, Integer.class));
        map.put(CommandID.SAVE_EFFECT, ReflectUtils.m8375a(cls, "saveEffect", MediaItem.class, AudioEffectCache.class, Boolean.class));
        map.put(CommandID.IS_PICKED_EFFECT, ReflectUtils.m8375a(cls, "isPickedEffect", String.class));
        map.put(CommandID.SET_AUDIO_EFFECT_TRY_MODE, ReflectUtils.m8375a(cls, "setAudioEffectTryMode", Boolean.class));
        map.put(CommandID.SET_AUDIO_EFFECT_RESET, ReflectUtils.m8375a(cls, "setAudioEffectReset", new Class[0]));
        map.put(CommandID.AUDIOEFFECT_CHANGED, ReflectUtils.m8375a(cls, "audioeffectChanged", new Class[0]));
    }

    public void setCloudAudioEffectEnabled(Boolean bool) {
        Preferences.m3079A(bool.booleanValue());
    }

    public void setLocalAudioEffectEnabled(Boolean bool) {
        if (bool.booleanValue()) {
            SupportFactory.m2397a(BaseApplication.getApplication()).m2455u();
        } else {
            SupportFactory.m2397a(BaseApplication.getApplication()).m2456t();
        }
    }

    public void setAudioEffectTryMode(Boolean bool) {
        SupportFactory.m2397a(BaseApplication.getApplication()).m2492a(bool);
    }

    public void applyCloudAudioEffect(AudioEffectItem audioEffectItem, Boolean bool) {
        SupportFactory.m2397a(BaseApplication.getApplication()).m2501a(audioEffectItem, bool.booleanValue());
    }

    public void setEqualizer(TTEqualizer.Settings settings) {
        Preferences.m2868j(settings.toString());
        m4376a(7);
    }

    /* renamed from: g */
    private void m4356g() {
        CommandCenter.getInstance().m4603a(new Command(CommandID.UPDATE_MANUAL_SETTING_EFFECT, new Object[0]), ModuleID.AUDIO_EFFECT, 500);
    }

    /* renamed from: a */
    private void m4376a(int i) {
        SupportFactory.m2397a(BaseApplication.getApplication()).m2504a(i);
        m4356g();
    }

    public TTEqualizer.Settings getEqualizer() {
        TTEqualizer.Settings settings;
        try {
            AudioEffectParam m2457s = SupportFactory.m2397a(BaseApplication.getApplication()).m2457s();
            if (m2457s == null) {
                settings = new TTEqualizer.Settings(EqualizerPreset.m4334b(), (short) 10, EqualizerPreset.m4333b(EqualizerPreset.m4334b()));
            } else {
                settings = new TTEqualizer.Settings(m2457s.m4421g());
            }
            return settings;
        } catch (Exception e) {
            return null;
        }
    }

    public void setBassBoost(Integer num) {
        Preferences.m2869j(num.intValue());
        m4376a(1);
    }

    public void setAudioEffectReset() {
        SupportFactory.m2397a(BaseApplication.getApplication()).m2461o();
        m4356g();
    }

    public void setBoostLimitEnabled(Boolean bool) {
        Preferences.m3073D(bool.booleanValue());
        m4376a(6);
    }

    public void setTrebleBoost(Integer num) {
        Preferences.m2865k(num.intValue());
        m4376a(2);
    }

    public void setVirtualizer(Integer num) {
        Preferences.m2861l(num.intValue());
        m4376a(3);
    }

    public void setReverb(Integer num) {
        Preferences.m2873i(num.intValue());
        m4376a(4);
    }

    public void setChannelBalance(Float f) {
        Preferences.m3027a(f.floatValue());
        m4376a(5);
    }

    public void saveCustomEqualizer(TTEqualizer.Settings settings) {
        DebugUtils.m8426a(settings, "TTEqualizer.Settings");
        m4364b(settings.getName());
        String m4369a = m4369a(settings.getName());
        FileUtils.m8404h(m4369a);
        FileUtils.m8416a(settings.toString(), m4369a);
        CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_SAVE_CUSTOM_EQUALIZER, settings), ModuleID.AUDIO_EFFECT);
    }

    public void deleteCustomEqualizer(String str) {
        DebugUtils.m8426a(str, "equalizerName");
        m4364b(str);
        FileUtils.m8404h(m4369a(str));
    }

    /* renamed from: a */
    private String m4369a(String str) {
        return TTPodConfig.m5286v() + File.separator + str + ".tteq";
    }

    public void queryCustomEqualizerList() {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.core.audioeffect.c.6
            @Override // java.lang.Runnable
            public void run() {
                ArrayList arrayList = new ArrayList();
                File[] listFiles = new File(TTPodConfig.m5286v()).listFiles();
                if (listFiles != null) {
                    Arrays.sort(listFiles, new Comparator<File>() { // from class: com.sds.android.ttpod.framework.modules.core.audioeffect.c.6.1
                        @Override // java.util.Comparator
                        /* renamed from: a */
                        public int compare(File file, File file2) {
                            long lastModified = file.lastModified() - file2.lastModified();
                            if (lastModified < 0) {
                                return 1;
                            }
                            if (lastModified > 0) {
                                return -1;
                            }
                            return 0;
                        }
                    });
                    for (File file : listFiles) {
                        if (file.getAbsolutePath().endsWith(".tteq")) {
                            try {
                                TTEqualizer.Settings settings = new TTEqualizer.Settings(StringUtils.streamToString(new FileInputStream(file)));
                                settings.setName(FileUtils.m8401k(file.getAbsolutePath()));
                                arrayList.add(settings);
                            } catch (Exception e) {
                                e.printStackTrace();
                                try {
                                    String[] split = StringUtils.streamToString(new FileInputStream(file)).split(" ");
                                    if (split != null && split.length == 10) {
                                        try {
                                            short[] sArr = new short[10];
                                            int length = split.length;
                                            int i = 0;
                                            int i2 = 0;
                                            while (i < length) {
                                                sArr[i2] = Short.parseShort(split[i]);
                                                i++;
                                                i2++;
                                            }
                                            arrayList.add(new TTEqualizer.Settings(FileUtils.m8401k(file.getAbsolutePath()), (short) 10, sArr));
                                        } catch (NumberFormatException e2) {
                                            e.printStackTrace();
                                        }
                                    }
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                            }
                        }
                    }
                }
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_CUSTOM_EQUALIZER_LIST, arrayList), ModuleID.AUDIO_EFFECT);
            }
        });
    }

    /* renamed from: b */
    private void m4364b(String str) {
        if (EnvironmentUtils.AppConfig.getTestMode() && EqualizerPreset.m4337a().contains(str)) {
            throw new IllegalArgumentException(str + " is default preset!");
        }
    }

    public void queryEffectUserInfo() {
        TTPodUser m2954aq = Preferences.m2954aq();
        if (m2954aq != null) {
            CloudAudioEffectAPI.m8929a(m2954aq.getAccessToken()).m8544a(new RequestCallback<AudioEffectUserResult>() { // from class: com.sds.android.ttpod.framework.modules.core.audioeffect.c.7
                @Override // com.sds.android.sdk.lib.request.RequestCallback
                /* renamed from: a */
                public void onRequestSuccess(AudioEffectUserResult audioEffectUserResult) {
                    CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_QUERY_EFFECT_USERINFO, audioEffectUserResult), ModuleID.AUDIO_EFFECT);
                }

                @Override // com.sds.android.sdk.lib.request.RequestCallback
                /* renamed from: b */
                public void onRequestFailure(AudioEffectUserResult audioEffectUserResult) {
                    CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_QUERY_EFFECT_USERINFO, audioEffectUserResult), ModuleID.AUDIO_EFFECT);
                }
            });
        }
    }

    public void queryEffect(MediaItem mediaItem, Integer num, Integer num2) {
        Request<AudioEffectItemResult> m8927a;
        if (mediaItem.getSongID() != null && mediaItem.getSongID().longValue() != 0) {
            m8927a = CloudAudioEffectAPI.m8930a(mediaItem.getSongID().longValue(), num.intValue(), num2.intValue());
        } else {
            m8927a = CloudAudioEffectAPI.m8927a(mediaItem.getTitle(), mediaItem.getArtist(), num.intValue(), num2.intValue());
        }
        m8927a.m8544a(new RequestCallback<AudioEffectItemResult>() { // from class: com.sds.android.ttpod.framework.modules.core.audioeffect.c.8
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: a */
            public void onRequestSuccess(AudioEffectItemResult audioEffectItemResult) {
                CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_QUERY_EFFECT, audioEffectItemResult), ModuleID.AUDIO_EFFECT);
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(AudioEffectItemResult audioEffectItemResult) {
                CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_QUERY_EFFECT, audioEffectItemResult), ModuleID.AUDIO_EFFECT);
            }
        });
    }

    public void pickEffect(final String str) {
        TTPodUser m2954aq = Preferences.m2954aq();
        if (m2954aq != null) {
            CloudAudioEffectAPI.m8928a(m2954aq.getAccessToken(), str).m8544a(new RequestCallback<AudioEffectCommResult>() { // from class: com.sds.android.ttpod.framework.modules.core.audioeffect.c.9
                @Override // com.sds.android.sdk.lib.request.RequestCallback
                /* renamed from: a */
                public void onRequestSuccess(AudioEffectCommResult audioEffectCommResult) {
                    AudioEffectModule.this.f5861d.put(str, Boolean.TRUE);
                    CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_PICK_EFFECT, audioEffectCommResult, str), ModuleID.AUDIO_EFFECT);
                }

                @Override // com.sds.android.sdk.lib.request.RequestCallback
                /* renamed from: b */
                public void onRequestFailure(AudioEffectCommResult audioEffectCommResult) {
                    AudioEffectModule.this.f5861d.put(str, Boolean.TRUE);
                    CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_PICK_EFFECT, audioEffectCommResult, str), ModuleID.AUDIO_EFFECT);
                }
            });
        }
    }

    public void bindEffect(String str) {
        TTPodUser m2954aq = Preferences.m2954aq();
        if (m2954aq != null) {
            CloudAudioEffectAPI.m8924b(m2954aq.getAccessToken(), str).m8544a(new RequestCallback<AudioEffectCommResult>() { // from class: com.sds.android.ttpod.framework.modules.core.audioeffect.c.10
                @Override // com.sds.android.sdk.lib.request.RequestCallback
                /* renamed from: a */
                public void onRequestSuccess(AudioEffectCommResult audioEffectCommResult) {
                }

                @Override // com.sds.android.sdk.lib.request.RequestCallback
                /* renamed from: b */
                public void onRequestFailure(AudioEffectCommResult audioEffectCommResult) {
                }
            });
        }
    }

    public void queryPrivateEffect() {
        TaskScheduler.start(this.f5864g);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public void m4355h() {
        m4354i();
        File file = new File(TTPodConfig.m5303e());
        if (!file.exists()) {
            file.mkdirs();
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_QUERY_PRIVATE_EFFECT, new ArrayList(), new ArrayList()), ModuleID.AUDIO_EFFECT);
            return;
        }
        ArrayList<PrivateEffectItem> arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList<PrivateEffectItem> arrayList3 = new ArrayList();
        for (File file2 : listFiles) {
            PrivateEffectItem m4360d = m4360d(file2.getAbsolutePath());
            if (m4360d != null) {
                long m4326e = m4360d.m4326e();
                if (m4326e == 0 && !StringUtils.isEmpty(m4360d.m4327d())) {
                    MediaItem m4712a = MediaItemUtils.m4712a(m4360d.m4327d());
                    LogUtils.debug("AudioEffectModule", "buildMediaItem from " + m4360d.m4327d() + ", mediaitem=" + m4712a);
                    if (m4712a != null) {
                        m4712a.setSongID(Long.valueOf(m4326e));
                        m4360d.m4330a(m4712a);
                        arrayList.add(m4360d);
                    }
                } else if (m4326e > 0) {
                    String m4370a = m4370a(Long.valueOf(m4326e));
                    f5858a.lock();
                    if (this.f5860c.contains(m4370a)) {
                        f5858a.unlock();
                        MediaItem queryMediaItem = MediaStorage.queryMediaItem(sContext, MediaStorage.GROUP_ID_EFFECT_ONLINE, m4370a);
                        if (queryMediaItem != null) {
                            queryMediaItem.setLocalDataSource(m4360d.m4327d());
                            m4360d.m4330a(queryMediaItem);
                            arrayList.add(m4360d);
                        } else {
                            arrayList2.add(Long.valueOf(m4326e));
                            arrayList3.add(m4360d);
                        }
                    } else {
                        f5858a.unlock();
                        arrayList2.add(Long.valueOf(m4326e));
                        arrayList3.add(m4360d);
                    }
                }
            }
        }
        if (arrayList2.size() > 0) {
            OnlineMediaItemsResult m8531f = OnlineMediaItemAPI.m8867a(arrayList2).execute();
            if (m8531f.getCode() == 1) {
                for (OnlineMediaItem onlineMediaItem : m8531f.getDataList()) {
                    for (PrivateEffectItem privateEffectItem : arrayList3) {
                        if (privateEffectItem.m4326e() == onlineMediaItem.getSongId()) {
                            MediaItem m4716a = MediaItemUtils.m4716a(onlineMediaItem);
                            m4716a.setLocalDataSource(privateEffectItem.m4327d());
                            privateEffectItem.m4330a(m4716a);
                            LogUtils.debug("AudioEffectModule", "insert MediaItem GROUP_ID_EFFECT_ONLINE " + m4716a.getTitle() + "-" + m4716a.getArtist());
                            MediaStorage.insertMediaItem(sContext, MediaStorage.GROUP_ID_EFFECT_ONLINE, m4716a);
                            f5858a.lock();
                            if (!this.f5860c.contains(m4716a.getID())) {
                                this.f5860c.add(m4716a.getID());
                            }
                            f5858a.unlock();
                        }
                    }
                }
                arrayList.addAll(arrayList3);
            }
        }
        Collections.sort(arrayList, new Comparator<PrivateEffectItem>() { // from class: com.sds.android.ttpod.framework.modules.core.audioeffect.c.11
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(PrivateEffectItem privateEffectItem2, PrivateEffectItem privateEffectItem3) {
                long m4324g = privateEffectItem2.m4324g() - privateEffectItem3.m4324g();
                if (m4324g < 0) {
                    return 1;
                }
                if (m4324g > 0) {
                    return -1;
                }
                return 0;
            }
        });
        ArrayList arrayList4 = new ArrayList(arrayList.size());
        for (PrivateEffectItem privateEffectItem2 : arrayList) {
            arrayList4.add(privateEffectItem2.m4325f());
        }
        CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_QUERY_PRIVATE_EFFECT, arrayList, arrayList4), ModuleID.AUDIO_EFFECT);
    }

    /* renamed from: a */
    private String m4370a(Long l) {
        if (l == null) {
            throw new IllegalArgumentException("SongId must not be null!");
        }
        return SecurityUtils.C0610b.m8361a(String.valueOf(l));
    }

    /* renamed from: i */
    private void m4354i() {
        m4362c(MediaStorage.GROUP_ID_EFFECT_LOCAL);
        m4362c(MediaStorage.GROUP_ID_EFFECT_ONLINE);
    }

    /* renamed from: c */
    private void m4362c(String str) {
        Iterator<MediaItem> it = MediaStorage.queryMediaItemList(sContext, str, Preferences.m2860l(str)).iterator();
        while (it.hasNext()) {
            MediaItem next = it.next();
            if (!FileUtils.m8414b(AudioEffectUtils.m4341a(next))) {
                MediaStorage.deleteMediaItem(sContext, str, next.getID());
                it.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public PrivateEffectItem m4360d(String str) {
        AudioEffectCache audioEffectCache = (AudioEffectCache) JSONUtils.fromJson(FileUtils.m8403i(str), AudioEffectCache.class);
        if (audioEffectCache == null) {
            return null;
        }
        PrivateEffectItem privateEffectItem = new PrivateEffectItem(str, audioEffectCache);
        privateEffectItem.m4331a(audioEffectCache.m4381o());
        return privateEffectItem;
    }

    public void deletePrivateEffectList(final List list) {
        if (list != null && list.size() != 0) {
            if (list.get(0).getClass().equals(PrivateEffectItem.class)) {
                TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.core.audioeffect.c.12
                    @Override // java.lang.Runnable
                    public void run() {
                        for (Object privateEffectItem : list) {
                            FileUtils.m8404h(((PrivateEffectItem)privateEffectItem).m4328c());
                        }
                        CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_DELETE_PRIVATE_EFFECT_LIST, new Object[0]), ModuleID.AUDIO_EFFECT);
                    }
                });
            } else if (list.get(0).getClass().equals(MediaItem.class)) {
                TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.core.audioeffect.c.2
                    @Override // java.lang.Runnable
                    public void run() {
                        int i = 0;
                        File file = new File(TTPodConfig.m5303e());
                        if (file.exists()) {
                            File[] listFiles = file.listFiles();
                            ArrayList arrayList = new ArrayList();
                            for (File file2 : listFiles) {
                                PrivateEffectItem m4360d = AudioEffectModule.this.m4360d(file2.getAbsolutePath());
                                if (m4360d != null) {
                                    arrayList.add(m4360d);
                                }
                            }
                            int size = arrayList.size();
                            if (size != 0) {
                                int size2 = list.size();
                                int i2 = 0;
                                for (int i3 = 0; i3 < size2; i3++) {
                                    MediaItem mediaItem = (MediaItem) list.get(i3);
                                    int i4 = 0;
                                    while (i4 < size) {
                                        PrivateEffectItem privateEffectItem = (PrivateEffectItem) arrayList.get(i4);
                                        if (privateEffectItem != null) {
                                            if (privateEffectItem.m4327d() == null) {
                                                i = i2;
                                            } else if (StringUtils.equals(privateEffectItem.m4327d(), mediaItem.getLocalDataSource())) {
                                                FileUtils.m8404h(privateEffectItem.m4328c());
                                                i = i2 + 1;
                                            }
                                            i4++;
                                            i2 = i;
                                        }
                                        i = i2;
                                        i4++;
                                        i2 = i;
                                    }
                                    FileUtils.m8404h(AudioEffectUtils.m4340a(mediaItem.getSongID(), mediaItem.getTitle(), mediaItem.getArtist()));
                                    if (Cache.getInstance().getCurrentPlayMediaItem().equals(mediaItem)) {
                                        CommandCenter.getInstance().m4596b(new Command(CommandID.SET_LOCAL_AUDIO_EFFECT, true));
                                        CommandCenter.getInstance().m4603a(new Command(CommandID.UPDATE_AUDIO_EFFECT_INFO, new Object[0]), ModuleID.AUDIO_EFFECT, 500);
                                    }
                                }
                                if (i2 > 0) {
                                    CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_DELETE_PRIVATE_EFFECT_LIST, new Object[0]), ModuleID.AUDIO_EFFECT);
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    public void setEffectItem(AudioEffectItem audioEffectItem) {
        setEqualizer(new TTEqualizer.Settings("", (short) 10, audioEffectItem.getDataEqualizer()));
        setReverb(Integer.valueOf(audioEffectItem.getDataReverb()));
        setBassBoost(Integer.valueOf(audioEffectItem.getDataBass()));
        setTrebleBoost(Integer.valueOf(audioEffectItem.getDataTreble()));
        setVirtualizer(Integer.valueOf(audioEffectItem.getDataVirtualizer()));
        setChannelBalance(Float.valueOf(audioEffectItem.getDataBalance()));
        setBoostLimitEnabled(Boolean.valueOf(audioEffectItem.getDataIsLimit()));
    }

    public void saveEffectToLocal(MediaItem mediaItem, AudioEffectCache audioEffectCache) {
        BufferedWriter bufferedWriter;
        Exception e;
        this.f5863f.removeCallbacks(this.f5862e);
        try {
            String title = mediaItem.getTitle();
            String artist = mediaItem.getArtist();
            AudioEffectID audioEffectID = new AudioEffectID();
            TTPodUser m2954aq = Preferences.m2954aq();
            audioEffectID.m4378a(m2954aq == null ? 0L : m2954aq.getUserId(), title, artist, audioEffectCache);
            audioEffectCache.m4411a(audioEffectCache.m4416a());
            bufferedWriter = new BufferedWriter(new FileWriter(AudioEffectUtils.m4341a(mediaItem), false));
        } catch (Exception e1) {
            e = e1;
            bufferedWriter = null;
        }
        try {
            bufferedWriter.write(JSONUtils.toJson(audioEffectCache));
            bufferedWriter.flush();
            bufferedWriter.close();
            m4371a(mediaItem);
            this.f5863f.postDelayed(this.f5862e, 160L);
        } catch (Exception e2) {
            e = e2;
            LogUtils.error("AudioEffectModule", "saveEffect error", e);
            try {
                bufferedWriter.close();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_SAVE_EFFECT_TO_LOCAL, Boolean.FALSE), ModuleID.AUDIO_EFFECT);
        }
    }

    public void saveEffect(final MediaItem mediaItem, final AudioEffectCache audioEffectCache, final Boolean bool) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.core.audioeffect.c.3
            /* JADX WARN: Removed duplicated region for block: B:12:0x00c5  */
            /* JADX WARN: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void run() {
                BufferedWriter bufferedWriter;
                Request<AudioEffectAddResult> m8925a;
                String title = mediaItem.getTitle();
                String artist = mediaItem.getArtist();
                Long songID = mediaItem.getSongID();
                String str = Build.MODEL;
                Integer valueOf = Integer.valueOf(audioEffectCache.m4398e());
                Integer valueOf2 = Integer.valueOf(audioEffectCache.m4395f());
                AudioEffectID audioEffectID = new AudioEffectID();
                TTPodUser m2954aq = Preferences.m2954aq();
                audioEffectID.m4378a(m2954aq == null ? 0L : m2954aq.getUserId(), title, artist, audioEffectCache);
                String m4379a = audioEffectID.m4379a();
                Exception e;
                try {
                    audioEffectCache.m4411a(m4379a);
                    bufferedWriter = new BufferedWriter(new FileWriter(AudioEffectUtils.m4341a(mediaItem), false));
                    try {
                        bufferedWriter.write(JSONUtils.toJson(audioEffectCache));
                        bufferedWriter.flush();
                        bufferedWriter.close();
                        AudioEffectModule.this.m4371a(mediaItem);
                        CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_SAVE_EFFECT_TO_LOCAL, Boolean.TRUE), ModuleID.AUDIO_EFFECT);
                        if (!bool.booleanValue()) {
                            CommandCenter.getInstance().m4595b(new Command(CommandID.EFFECT_SAVE_RESULT, Boolean.TRUE), ModuleID.AUDIO_EFFECT);
                        }
                    } catch (Exception e1) {
                        e = e1;
                        LogUtils.error("AudioEffectModule", "saveEffect error", e);
                        try {
                            bufferedWriter.close();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_SAVE_EFFECT_TO_LOCAL, Boolean.FALSE), ModuleID.AUDIO_EFFECT);
                        if (!bool.booleanValue()) {
                            CommandCenter.getInstance().m4595b(new Command(CommandID.EFFECT_SAVE_RESULT, Boolean.FALSE), ModuleID.AUDIO_EFFECT);
                        }
                        if (!bool.booleanValue()) {
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    bufferedWriter = null;
                }
                if (!bool.booleanValue()) {
                    AudioEffectItemData audioEffectItemData = new AudioEffectItemData();
                    audioEffectItemData.setEqualizer(audioEffectCache.m4382n());
                    audioEffectItemData.setBalance(audioEffectCache.m4384l());
                    audioEffectItemData.setBass(audioEffectCache.m4389h());
                    audioEffectItemData.setTreble(audioEffectCache.m4387i());
                    audioEffectItemData.setVirtualizer(audioEffectCache.m4386j());
                    audioEffectItemData.setReverb(audioEffectCache.m4385k());
                    audioEffectItemData.setIsLimit(audioEffectCache.m4383m());
                    if (m2954aq != null) {
                        if (songID == null || songID.longValue() == 0) {
                            m8925a = CloudAudioEffectAPI.m8925a(m2954aq.getAccessToken(), m4379a, valueOf.intValue(), title, artist, valueOf2.intValue(), str, audioEffectItemData);
                        } else {
                            m8925a = CloudAudioEffectAPI.m8926a(m2954aq.getAccessToken(), m4379a, valueOf.intValue(), songID.longValue(), title, artist, valueOf2.intValue(), str, audioEffectItemData);
                        }
                        CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_SAVE_EFFECT_TO_NETWORK, m8925a.execute()), ModuleID.AUDIO_EFFECT);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m4371a(MediaItem mediaItem) {
        f5858a.lock();
        if (mediaItem.isOnline()) {
            if (this.f5860c.contains(mediaItem.getID())) {
                MediaStorage.deleteMediaItem(sContext, MediaStorage.GROUP_ID_EFFECT_ONLINE, mediaItem.getID());
            } else {
                this.f5860c.add(mediaItem.getID());
            }
            CommandCenter.getInstance().execute(new Command(CommandID.ADD_MEDIA_ITEM, MediaStorage.GROUP_ID_EFFECT_ONLINE, mediaItem));
        } else {
            if (this.f5859b.contains(mediaItem.getID())) {
                MediaStorage.deleteMediaItem(sContext, MediaStorage.GROUP_ID_EFFECT_LOCAL, mediaItem.getID());
            } else {
                this.f5859b.add(mediaItem.getID());
            }
            CommandCenter.getInstance().execute(new Command(CommandID.ADD_MEDIA_ITEM, MediaStorage.GROUP_ID_EFFECT_LOCAL, mediaItem));
        }
        f5858a.unlock();
    }

    public void saveEffectToNetwork(MediaItem mediaItem, AudioEffectItemData audioEffectItemData, Integer num, Integer num2) {
        Request<AudioEffectAddResult> m8925a;
        TTPodUser m2954aq = Preferences.m2954aq();
        if (mediaItem != null && m2954aq != null) {
            String title = mediaItem.getTitle();
            String artist = mediaItem.getArtist();
            Long songID = mediaItem.getSongID();
            String m4379a = new AudioEffectID(m2954aq == null ? 0L : m2954aq.getUserId(), title, artist, audioEffectItemData.getEqualizer(), audioEffectItemData.getBass(), audioEffectItemData.getTreble(), audioEffectItemData.getVirtualizer(), audioEffectItemData.getReverb(), audioEffectItemData.getBalance(), audioEffectItemData.getIsLimit()).m4379a();
            String str = Build.MODEL;
            if (songID == null || songID.longValue() == 0) {
                m8925a = CloudAudioEffectAPI.m8925a(m2954aq.getAccessToken(), m4379a, num.intValue(), title, artist, num2.intValue(), str, audioEffectItemData);
            } else {
                m8925a = CloudAudioEffectAPI.m8926a(m2954aq.getAccessToken(), m4379a, num.intValue(), songID.longValue(), title, artist, num2.intValue(), str, audioEffectItemData);
            }
            m8925a.m8544a(new RequestCallback<AudioEffectAddResult>() { // from class: com.sds.android.ttpod.framework.modules.core.audioeffect.c.4
                @Override // com.sds.android.sdk.lib.request.RequestCallback
                /* renamed from: a */
                public void onRequestSuccess(AudioEffectAddResult audioEffectAddResult) {
                    CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_SAVE_EFFECT_TO_NETWORK, audioEffectAddResult), ModuleID.AUDIO_EFFECT);
                }

                @Override // com.sds.android.sdk.lib.request.RequestCallback
                /* renamed from: b */
                public void onRequestFailure(AudioEffectAddResult audioEffectAddResult) {
                    CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_SAVE_EFFECT_TO_NETWORK, audioEffectAddResult), ModuleID.AUDIO_EFFECT);
                }
            });
        }
    }

    public void audioeffectChanged() {
        CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_AUDIO_EFFECT_INFO, new Object[0]), ModuleID.AUDIO_EFFECT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public List<String> m4358e(String str) {
        return MediaStorage.queryMediaIDs(sContext, str, Preferences.m2860l(str));
    }

    public Boolean isPickedEffect(String str) {
        return this.f5861d.get(str);
    }
}
