package com.sds.android.ttpod.framework.base;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Process;
import android.provider.Settings;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.sdk.core.p058b.ExceptionReporter;

import com.sds.android.sdk.lib.p059a.FakeHttpServer;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.BuildConfig;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.modules.ModuleManager;
import com.sds.android.ttpod.framework.modules.core.p113b.GlobalModule;
import com.sds.android.ttpod.framework.storage.database.SearchSqliteDb;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.media.audiofx.EffectDetect;

import java.io.IOException;
import java.util.List;

/* loaded from: classes.dex */
public class BaseApplication extends MultiDexApplication {

    /* renamed from: a */
    private static BaseApplication application = null;

    /* renamed from: b */
    private static String f5694b;
    private static FakeHttpServer fakeHttpServer;

    @Override // android.app.Application
    public final void onCreate() {
        super.onCreate();
        startFakeServer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                try {
                    Uri uri = Uri.parse("package:" + BuildConfig.APPLICATION_ID);
                    Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, uri);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } catch (Exception ex) {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        }
        Preferences.m3024a(this);
        BaseModule.setContext(this);
        DisplayUtils.m7228a(this);
        TTPodConfig.m5307a(true);
        application = this;
        f5694b = m4625m();
        EnvironmentUtils.m8525a(this);
        LogUtils.setEnableLog(EnvironmentUtils.AppConfig.getTestMode());
        EffectDetect.detectAudioPlus(this);
        ExceptionReporter.m8750a(this, Action.EXCEPTION_REPORT);
        //UmengStatisticUtils.m4867a(this, EnvironmentUtils.C0602a.m8512b());
        //UTAnalyticsUtils.m4869a(this);
        if (m4631g()) {
            mo4637a();
        } else if (m4630h()) {
            m4634d();
        } else if (m4628j()) {
            m4632f();
        } else if (m4629i()) {
            m4633e();
        }


    }

    private void startFakeServer() {
        if (fakeHttpServer == null) {
            new Thread(() -> {
                fakeHttpServer = new FakeHttpServer();
                try {
                    fakeHttpServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    /* renamed from: c */
    public static BaseApplication getApplication() {
        return application;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo4637a() {
        try {
            m4622p();
            Preferences.m3033X(true);
            ModuleManager.getInstance().init(this);
            m4626l();
            //StartupStatistic.m4923a(f5694b);
            //AppRuntimeStatistic.m5273a();
            //StartupStatistic.m4920c();
            //AppRuntimeStatistic.m5272a(//AppRuntimeStatistic.EnumC1767a.STARTUP_STATE);
            //StartupStatistic.m4921b("com.sds.android.ttpod.main");
            //AudioEffectStatistic.m5271a();
            //new //SSystemEvent("SYS_SETTING", "audio_effect").append("status", Boolean.valueOf(Preferences.m2974ad())).post();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /* renamed from: l */
    private void m4626l() {

    }

    /* renamed from: d */
    protected void m4634d() {
        m4622p();
        SearchSqliteDb.m3133a(this);
        //AppRuntimeStatistic.m5273a();
        //AppRuntimeStatistic.m5272a(//AppRuntimeStatistic.EnumC1767a.STARTUP_STATE);
        //StartupStatistic.m4921b("com.sds.android.ttpod.support");
        m4626l();
        //StartupStatistic.m4923a(f5694b);
        new Handler().postDelayed(new Runnable() { // from class: com.sds.android.ttpod.framework.base.BaseApplication.1
            @Override // java.lang.Runnable
            public void run() {
                TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.base.BaseApplication.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        GlobalModule.setContext(BaseApplication.this);
                        GlobalModule.loadGBKToUnicodeData();
                    }
                });
            }
        }, 1000L);
    }

    /* renamed from: e */
    protected void m4633e() {
        TTPodConfig.m5307a(false);
    }

    /* renamed from: f */
    protected void m4632f() {
    }

    /* renamed from: b */
    public void mo4636b() {
        if (m4631g()) {
            ModuleManager.getInstance().m4111b();
            com.sds.android.ttpod.framework.base.ActivityManager.m4618a().m4616b();
            //SEngine.instance();
            //SEngine.unbindFromService(this);
            new Handler().postDelayed(new Runnable() { // from class: com.sds.android.ttpod.framework.base.BaseApplication.2
                @Override // java.lang.Runnable
                public void run() {
                    ModuleManager.getInstance().m4108c();
                    //UmengStatisticUtils.m4864c(BaseApplication.getApplication());
                    Process.killProcess(Process.myPid());
                }
            }, 500L);
        }
    }

    /* renamed from: g */
    public boolean m4631g() {
        return "com.sds.android.ttpod.main".equals(f5694b);
    }

    /* renamed from: h */
    protected boolean m4630h() {
        return "com.sds.android.ttpod.support".equals(f5694b);
    }

    /* renamed from: i */
    protected boolean m4629i() {
        return "com.sds.android.ttpod.appwidget".equals(f5694b);
    }

    /* renamed from: j */
    protected boolean m4628j() {
        return "com.sds.android.ttpod.pushservice".equals(f5694b);
    }

    /* renamed from: m */
    private String m4625m() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE)).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (Process.myPid() == runningAppProcessInfo.pid) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        return null;
    }

    /* renamed from: k */
    public boolean m4627k() {
        return m4624n() || m4623o();
    }

    /* renamed from: n */
    private boolean m4624n() {
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE)).getRunningTasks(1);
        return (runningTasks == null || runningTasks.size() <= 0 || getApplicationInfo().packageName.equals(runningTasks.get(0).topActivity.getPackageName())) ? false : true;
    }

    /* renamed from: o */
    private boolean m4623o() {
        return Preferences.m2990aQ();
    }

    /* renamed from: p */
    private void m4622p() {
        TTPodUser m2954aq = Preferences.m2954aq();
        EnvironmentUtils.UUIDConfig.m8498a(m2954aq != null ? m2954aq.getUserId() : 0L);
    }
}
