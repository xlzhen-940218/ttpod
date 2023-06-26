package com.sds.android.ttpod.framework.modules.core.p113b;

import android.os.Handler;
import android.os.SystemClock;

import com.sds.android.cloudapi.ttpod.p055a.GlobalAPI;
import com.sds.android.cloudapi.ttpod.result.GlobalResult;
import com.sds.android.cloudapi.ttpod.result.OperatorPageResult;


import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.framework.base.BaseModule;
import com.sds.android.ttpod.framework.base.ErrCode;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.modules.core.p113b.p114a.ShakeMonitor;
import com.sds.android.ttpod.framework.modules.core.p113b.p114a.ShakeSensitivityType;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.SupportCallback;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.MediaTag;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Map;

/* renamed from: com.sds.android.ttpod.framework.modules.core.b.b */
/* loaded from: classes.dex */
public class GlobalModule extends BaseModule {
    public static final long INIT_GBK_MAP_DELAY = 1000;
    public static final long SLEEP_PROMPT_AHEAD_TIME_IN_MILLI = 5;

    /* renamed from: a */
    private static final String f5925a = GlobalModule.class.getSimpleName();

    /* renamed from: c */
    private ShakeMonitor f5927c;

    /* renamed from: d */
    private long f5928d;

    /* renamed from: b */
    private boolean f5926b = false;

    /* renamed from: e */
    private SupportCallback f5929e = new SupportCallback();

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    /* renamed from: id */
    protected ModuleID id() {
        return ModuleID.GLOBAL;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public void onCreate() {
        super.onCreate();
        SupportFactory.m2397a(sContext).mo2497a(this.f5929e);
        if (Preferences.m2818w()) {
            m4292d();
        }
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.core.b.b.1
            @Override // java.lang.Runnable
            public void run() {
                if (EnvironmentUtils.DeviceConfig.m8474e()) {
                    GlobalModule.this.m4295b();
                }
                GlobalModule.loadGBKToUnicodeData();
                GlobalModule.this.m4293c();
            }
        });
        new Handler().postDelayed(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.core.b.b.2
            @Override // java.lang.Runnable
            public void run() {
                if (Preferences.m3068G()) {
                    //PushManager.getInstance().initialize(GlobalModule.sContext.getApplicationContext());
                }
            }
        }, 8000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m4295b() {
        GlobalResult m8531f = GlobalAPI.m8887a().m8531f();
        if (m8531f != null) {
            LogUtils.debug(f5925a, "Global Api finish, IPSupport: %b, version: %s, isSearchRestricted: %b, is360GuideEnabled: %b, isShow360Union: %b", Boolean.valueOf(m8531f.isIPSupported()), m8531f.getVersion(), Boolean.valueOf(m8531f.isSearchRestricted()), Boolean.valueOf(m8531f.is360GuideEnabled()), Boolean.valueOf(m8531f.is360UnoinEnabled()));
            Preferences.m3055M(m8531f.isIPSupported());
            Preferences.m3051O(m8531f.is360GuideEnabled());
            Preferences.m3049P(m8531f.is360UnoinEnabled());
            Preferences.m3053N(m8531f.isSearchRestricted());
        }
        //SSystemEvent //SSystemEvent = //new //SSystemEvent("SYS_GLOBAL", "start");
        //SSystemEvent.setPostStrategy(SPostStrategy.IMMEDIATELAY_POST);
        //SSystemEvent.post();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m4293c() {
        OperatorPageResult m8531f = GlobalAPI.m8886a("f" + EnvironmentUtils.AppConfig.getChannelType(), "v" + EnvironmentUtils.AppConfig.getAppVersion()).m8531f();
        if (m8531f != null && m8531f.getData() != null) {
            boolean z = m8531f.getData().getRecommend() != 0;
            LogUtils.debug(f5925a, "Market Global Api recommand enable: %s ", Boolean.valueOf(z));
            Preferences.m2965ah(z);
        }
    }

    public static void loadGBKToUnicodeData() {
        InputStream inputStream = null;
        try {
            try {
                inputStream = sContext.getAssets().open("gbk2uc.dat");
                byte[] bArr = new byte[inputStream.available()];
                if (inputStream.read(bArr) > 0) {
                    try {
                        MediaTag.initGBKMap(bArr);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Throwable th2) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                throw th2;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public void onPreDestroy() {
        super.onPreDestroy();
        SupportFactory.m2397a(sContext).m2482b(this.f5929e);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public void onDestroy() {
        super.onDestroy();
        m4291e();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    protected void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        Class<?> cls = getClass();
        map.put(CommandID.START_SLEEP_MODE, ReflectUtils.m8375a(cls, "startSleepMode", Integer.class));
        map.put(CommandID.STOP_SLEEP_MODE, ReflectUtils.m8375a(cls, "stopSleepMode", new Class[0]));
        map.put(CommandID.IS_SLEEP_MODE_ENABLED, ReflectUtils.m8375a(cls, "isSleepModeEnabled", new Class[0]));
        map.put(CommandID.SLEEP_MODE_REMAIN_TIME, ReflectUtils.m8375a(cls, "sleepModeRemainTime", new Class[0]));
        map.put(CommandID.SET_SHAKE_SWITCH_SONG_ENABLED, ReflectUtils.m8375a(cls, "setShakeSwitchSongEnabled", Boolean.class));
        map.put(CommandID.SET_SHAKE_SWITCH_SONG_SENSITIVITY, ReflectUtils.m8375a(cls, "setShakeSwitchSongSensitivity", ShakeSensitivityType.class));
    }

    public ErrCode startSleepMode(Integer num) {
        LogUtils.error(f5925a, "startSleepMode delay time = " + num);
        if (!m4297a(num.intValue() * 60000)) {
            LogUtils.error(f5925a, "startSleepMode errArgument");
            return ErrCode.ErrArgument;
        }
        this.f5926b = true;
        SupportFactory.m2397a(sContext).m2484b(num.intValue() * 60000);
        this.f5928d = SystemClock.elapsedRealtime() + (num.intValue() * 60000);
        Preferences.m3026a(num.intValue());
        CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_SLEEP_MODE, new Object[0]), ModuleID.GLOBAL);
        return ErrCode.ErrNone;
    }

    /* renamed from: a */
    private boolean m4297a(long j) {
        return 60000 <= j && 600000000 >= j;
    }

    public void stopSleepMode() {
        LogUtils.error(f5925a, "stopSleepMode");
        this.f5926b = false;
        this.f5928d = 0L;
        LogUtils.error(f5925a, "stopSleepMode");
        SupportFactory.m2397a(sContext).m2451y();
        CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_SLEEP_MODE, new Object[0]), ModuleID.GLOBAL);
    }

    public Long sleepModeRemainTime() {
        return Long.valueOf(this.f5928d > 0 ? ((this.f5928d - SystemClock.elapsedRealtime()) + 500) / 1000 : 0L);
    }

    public Boolean isSleepModeEnabled() {
        return Boolean.valueOf(this.f5926b);
    }

    public void setShakeSwitchSongEnabled(Boolean bool) {
        if (bool.booleanValue()) {
            m4292d();
        } else {
            m4291e();
        }
    }

    public void setShakeSwitchSongSensitivity(ShakeSensitivityType shakeSensitivityType) {
        Preferences.m3021a(shakeSensitivityType);
        if (this.f5927c != null) {
            this.f5927c.m4302a(shakeSensitivityType);
        }
    }

    /* renamed from: d */
    private void m4292d() {
        if (this.f5927c == null) {
            this.f5927c = new ShakeMonitor();
            this.f5927c.m4304a();
        }
    }

    /* renamed from: e */
    private void m4291e() {
        if (this.f5927c != null) {
            this.f5927c.m4301b();
            this.f5927c = null;
        }
    }
}
