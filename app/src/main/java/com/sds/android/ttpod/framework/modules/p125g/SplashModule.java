package com.sds.android.ttpod.framework.modules.p125g;

import android.graphics.Bitmap;

import com.sds.android.cloudapi.ttpod.data.SplashItem;
import com.sds.android.cloudapi.ttpod.api.SplashAPI;
import com.sds.android.cloudapi.ttpod.result.SplashDataResult;
import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.util.BitmapUtils;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.sdk.lib.util.ZIPUtils;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseModule;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.apache.http.client.methods.HttpGet;

/* renamed from: com.sds.android.ttpod.framework.modules.g.d */
/* loaded from: classes.dex */
public final class SplashModule extends BaseModule {

    /* renamed from: a */
    private long f6286a;

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    /* renamed from: id */
    protected ModuleID id() {
        return ModuleID.SPLASH;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public long timeOutInMills() {
        return 15000L;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    protected void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        Class<?> cls = getClass();
        map.put(CommandID.LOAD_SPLASH, ReflectUtils.loadMethod(cls, "loadSplash", Integer.class, Integer.class));
        map.put(CommandID.SET_AUDIO_ENABLED, ReflectUtils.loadMethod(cls, "setAudioEnabled", Boolean.class));
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public void onCreate() {
        super.onCreate();
    }

    public void loadSplash(final Integer num, final Integer num2) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.g.d.1
            @Override // java.lang.Runnable
            public void run() {
                Bitmap bitmap;
                int i;
                String str;
                long j;
                long j2 = 1500;
                SplashModule.this.m4004a(num2);
                SplashDataResult m3185c = Cache.getInstance().m3185c();
                if (m3185c == null) {
                    bitmap = null;
                    i = 0;
                    str = null;
                    j = 1500;
                } else {
                    SplashInfoParser m4014a = SplashInfoParser.m4014a(m3185c);
                    int m4015a = m4014a.m4015a();
                    SplashItem m4013b = m4014a.m4013b();
                    if (m4013b != null) {
                        String m3991c = SplashModule.this.m3991c(m4013b.getSuitFile(DisplayUtils.getDpi()));
                        if (!FileUtils.isDir(m3991c)) {
                            m4015a = 0;
                        }
                        String str2 = m3991c + File.separator + "background.jpg";
                        String str3 = m3991c + File.separator + "index.html";
                        Bitmap m3989e = SplashModule.this.m3989e(str2);
                        if (FileUtils.m8414b(str3)) {
                            j2 = 4000;
                        }
                        if (m3989e != null && FileUtils.m8419a((String) null) && Preferences.m3028a()) {
                            SplashModule.this.f6286a = System.currentTimeMillis();
                            SplashModule.this.m4003a((String) null);
                            i = m4015a;
                            bitmap = m3989e;
                            str = str3;
                            j = 10000;
                        } else {
                            j = j2;
                            i = m4015a;
                            str = str3;
                            bitmap = m3989e;
                        }
                    } else {
                        bitmap = null;
                        j = 1500;
                        str = null;
                        i = m4015a;
                    }
                }
                if (bitmap == null) {
                    bitmap = SplashModule.this.m4010a(num.intValue());
                }
                Bitmap m4000b = SplashModule.this.loadChannelsBitmap();
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_SPLASH, m4000b, bitmap, str, false), ModuleID.SPLASH);
                if (bitmap == null) {
                    j = m4000b != null ? 200L : 0L;
                }
                CommandCenter.getInstance().m4603a(new Command(CommandID.FINISH_SPLASH, new Object[0]), ModuleID.SPLASH, (int) j);
                //StartupStatistic.m4919d();
                try {
                    Thread.sleep(20000L);
                    SplashModule.this.m3999b(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public Bitmap m4010a(int i) {
        Bitmap m4011a = m4011a();
        return m4011a != null ? m4011a : m3993c(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m3999b(int i) {
        SplashDataResult m8531f;
        if (EnvironmentUtils.DeviceConfig.hasNetwork() == 2 && (m8531f = SplashAPI.m8824a(i).execute()) != null && 1 == m8531f.getCode()) {
            List<SplashItem> m4012c = SplashInfoParser.m4014a(m8531f).m4012c();
            if (m4001a(m4012c)) {
                Cache.getInstance().m3214a(m8531f);
                m3994b(m4012c);
            }
        }
    }

    /* renamed from: a */
    private boolean m4001a(List<SplashItem> list) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        Exception e;
        Throwable th;
        if (list != null) {
            int m7220h = DisplayUtils.getDpi();
            for (SplashItem splashItem : list) {
                String suitFile = splashItem.getSuitFile(m7220h);
                String m3995b = m3995b(suitFile);
                if (!m4002a(suitFile, m3995b)) {
                    return false;
                }
                try {
                    fileInputStream2 = new FileInputStream(m3995b);
                } catch (Exception e1) {
                    e = e1;
                    fileInputStream = null;
                } catch (Throwable th1) {
                    th = th1;
                    fileInputStream = null;
                }
                try {
                    ZIPUtils.m8330a(fileInputStream2, m3991c(suitFile));
                    FileUtils.exists(m3995b);
                    try {
                        fileInputStream2.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } catch (Exception e3) {
                    e = e3;
                    fileInputStream = fileInputStream2;
                    try {
                        LogUtils.error("SplashModule", "downloadNewSplashInfo doUnZipFolder exception=%s", e.toString());
                        FileUtils.exists(m3995b);
                        try {
                            fileInputStream.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        FileUtils.exists(m3995b);
                        try {
                            fileInputStream.close();
                        } catch (Exception e5) {
                            e5.printStackTrace();
                        }
                        //throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream = fileInputStream2;
                    FileUtils.exists(m3995b);
                    try {
                        fileInputStream.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    //throw th;
                }
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m4004a(Integer num) {
        String str = TTPodConfig.getSplashPath() + File.separator + "帮助.txt";
        if (!FileUtils.m8419a(str)) {
            FileUtils.m8416a(BaseApplication.getApplication().getString(num.intValue()), str);
        }
    }

    /* renamed from: a */
    private boolean m4002a(String str, String str2) {
        if (!StringUtils.isEmpty(str) && !StringUtils.isEmpty(str2)) {
            String str3 = str2 + ".tmp";
            HttpRequest.Response m8708a = HttpRequest.m8708a(new HttpGet(str), (HashMap<String, Object>) null, (HashMap<String, Object>) null);
            if (m8708a != null && 200 == m8708a.getStatusCode()) {
                if (FileUtils.m8420a(m8708a.getInputStream(), str3)) {
                    return FileUtils.m8410c(str3, str2);
                }
                FileUtils.exists(str3);
            }
        }
        return false;
    }

    /* renamed from: b */
    private void m3994b(List<SplashItem> list) {
        String str = TTPodConfig.getSplashPath() + File.separator;
        String[] list2 = new File(str).list();
        if (list2 != null) {
            HashSet hashSet = new HashSet();
            if (list != null) {
                for (SplashItem splashItem : list) {
                    hashSet.add(m3990d(splashItem.getSuitFile(DisplayUtils.getDpi())));
                }
            }
            hashSet.add("帮助.txt");
            hashSet.add(".nomedia");
            for (String str2 : SplashConstant.f6284a) {
                hashSet.add(str2);
            }
            for (String str3 : list2) {
                String lowerCase = str3.toLowerCase();
                if (!hashSet.contains(lowerCase)) {
                    String str4 = str + lowerCase;
                    if (!lowerCase.startsWith(SplashConstant.f6284a[0]) || FileUtils.isDir(str4)) {
                        FileUtils.exists(str4);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m4003a(String str) {
        MediaPlayerWrapper.m4021a().m4019a(new MediaPlayerWrapper.InterfaceC1953a() { // from class: com.sds.android.ttpod.framework.modules.g.d.2
            @Override // com.sds.android.ttpod.framework.modules.p125g.MediaPlayerWrapper.InterfaceC1953a
            /* renamed from: a */
            public void mo3988a() {
                long currentTimeMillis = System.currentTimeMillis() - SplashModule.this.f6286a;
                if (currentTimeMillis < 1500) {
                    CommandCenter.getInstance().m4603a(new Command(CommandID.FINISH_SPLASH, new Object[0]), ModuleID.SPLASH, (int) (1500 - currentTimeMillis));
                } else {
                    CommandCenter.getInstance().execute(new Command(CommandID.FINISH_SPLASH, new Object[0]));
                }
            }
        });
        MediaPlayerWrapper.m4021a().m4018a(str);
    }

    /* renamed from: a */
    private Bitmap m4011a() {
        String[] strArr = SplashConstant.f6284a;
        int length = SplashConstant.f6284a.length;
        Bitmap bitmap = null;
        for (int i = 0; i < length; i++) {
            String str = TTPodConfig.getSplashPath() + File.separator + strArr[i];
            if (FileUtils.m8419a(str) && (bitmap = m3989e(str)) != null) {
                break;
            }
        }
        return bitmap;
    }

    /* renamed from: b */
    private String m3995b(String str) {
        String m8402j = FileUtils.getFilename(str);
        if (StringUtils.isEmpty(m8402j)) {
            return null;
        }
        return TTPodConfig.getSplashPath() + File.separator + m8402j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public String m3991c(String str) {
        return TTPodConfig.getSplashPath() + File.separator + m3990d(str);
    }

    /* renamed from: d */
    private String m3990d(String str) {
        String m8401k = FileUtils.m8401k(str);
        if (StringUtils.isEmpty(m8401k)) {
            return null;
        }
        return m8401k + "dir";
    }

    public void setAudioEnabled(Boolean bool) {
        Preferences.m3007a(bool.booleanValue());
        if (!bool.booleanValue() && MediaPlayerWrapper.m4021a().m4016c()) {
            MediaPlayerWrapper.m4021a().m4020a(0.0f, 0.0f);
            MediaPlayerWrapper.m4021a().m4017b();
        }
    }

    /* renamed from: c */
    private Bitmap m3993c(int i) {
        if (i == 0) {
            return null;
        }
        return new BitmapUtils().m8454a(BaseApplication.getApplication().getResources(), i, DisplayUtils.getWidth(), DisplayUtils.getHeight());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public Bitmap m3989e(String str) {
        return BitmapUtils.m8435b(str, DisplayUtils.getWidth(), DisplayUtils.getHeight());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public Bitmap loadChannelsBitmap() {
        return null;
        /*try {
            return BitmapFactory.decodeStream(BaseApplication.getApplication().getResources().getAssets().open("channel_logo.png"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }*/
    }
}
