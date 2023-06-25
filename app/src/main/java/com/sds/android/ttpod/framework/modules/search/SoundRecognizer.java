package com.sds.android.ttpod.framework.modules.search;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.mradar.sdk.record.DoresoManager;
import com.mradar.sdk.record.MRadarSdkListener;
import com.voicedragon.musicclient.record.DoresoMusicTrack;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/* renamed from: com.sds.android.ttpod.framework.modules.search.b */
/* loaded from: classes.dex */
public class SoundRecognizer implements MRadarSdkListener {

    /* renamed from: a */
    private static final String f6379a = SoundRecognizer.class.getSimpleName();

    /* renamed from: h */
    private static SoundRecognizer f6380h;

    /* renamed from: b */
    private double f6381b;

    /* renamed from: c */
    private DoresoManager f6382c;

    /* renamed from: d */
    private boolean f6383d;

    /* renamed from: f */
    private boolean f6385f;

    /* renamed from: g */
    private long f6386g;

    /* renamed from: e */
    private boolean f6384e = true;

    /* renamed from: i */
    private Handler f6387i = new Handler() { // from class: com.sds.android.ttpod.framework.modules.search.b.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            List list;
            switch (message.what) {
                case 1:
                    LogUtils.debug(SoundRecognizer.f6379a, "MSG_FAILED: " + message.obj);
                    CommandCenter.getInstance().m4604a(new Command(CommandID.SEARCH_RECOGNIZE_ERROR, (EnumC1975a) message.obj), ModuleID.SEARCH);
                    return;
                case 2:
                    DoresoMusicTrack[] doresoMusicTrackArr = (DoresoMusicTrack[]) message.obj;
                    LogUtils.debug(SoundRecognizer.f6379a, "MSG_RESULT_OK: " + (doresoMusicTrackArr == null ? "null" : "length" + doresoMusicTrackArr.length));
                    if (doresoMusicTrackArr == null || doresoMusicTrackArr.length <= 0) {
                        list = null;
                    } else {
                        list = Arrays.asList(doresoMusicTrackArr);
                    }
                    CommandCenter.getInstance().m4604a(new Command(CommandID.SEARCH_RECOGNIZE_SUCCESS, list), ModuleID.SEARCH);
                    return;
                default:
                    return;
            }
        }
    };

    /* compiled from: SoundRecognizer.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.search.b$a */
    /* loaded from: classes.dex */
    public enum EnumC1975a {
        NOT_CONNECT,
        NO_RESULT,
        TIME_OUT
    }

    /* renamed from: b */
    public static SoundRecognizer m3878b() {
        synchronized (SoundRecognizer.class) {
            if (f6380h == null) {
                f6380h = new SoundRecognizer(BaseApplication.getApplication());
            }
        }
        return f6380h;
    }

    public SoundRecognizer(Context context) {
        this.f6382c = new DoresoManager(context, "0D5C1E6FF3701743C34A02BE7CF3A96E");
        this.f6382c.setmRadarSdkListener(this);
    }

    /* renamed from: c */
    public synchronized void m3877c() {
        if (!this.f6383d) {
            LogUtils.debug(f6379a, "recognize start");
            this.f6386g = System.currentTimeMillis();
            this.f6382c.m8997a((File) null);
            this.f6383d = true;
            this.f6384e = true;
            this.f6385f = false;
        }
    }

    /* renamed from: d */
    public synchronized void m3876d() {
        if (this.f6383d) {
            LogUtils.debug(f6379a, "stop()");
            this.f6382c.onRecordEnd();
        }
    }

    /* renamed from: e */
    public synchronized void m3875e() {
        if (!this.f6385f) {
            LogUtils.debug(f6379a, "cancel()");
            this.f6382c.m8996b();
            this.f6383d = false;
            this.f6384e = false;
            this.f6385f = true;
            f6380h = null;
        }
    }

    /* renamed from: f */
    public double m3874f() {
        return this.f6381b;
    }

    @Override // com.mradar.sdk.record.DoresoListener
    /* renamed from: a */
    public void onVolumeChanged(double d) {
        this.f6381b = d;
    }

    @Override // com.mradar.sdk.record.DoresoListener
    /* renamed from: a */
    public void onError(int i, String str) {
        LogUtils.debug(f6379a, "onError errorcode=" + i + ":" + str);
        LogUtils.debug(f6379a, "recognize onError cost time: " + (System.currentTimeMillis() - this.f6386g) + "ms");
        if (!this.f6385f) {
            LogUtils.debug(f6379a, "cancel");
            this.f6382c.m8996b();
            this.f6385f = true;
            this.f6383d = false;
        }
        if (this.f6384e) {
            EnumC1975a enumC1975a = EnumC1975a.NO_RESULT;
            if (i == 4001 || i == 4002) {
                enumC1975a = EnumC1975a.NOT_CONNECT;
            }
            this.f6387i.sendMessage(this.f6387i.obtainMessage(1, enumC1975a));
        }
    }

    @Override // com.mradar.sdk.record.DoresoListener
    /* renamed from: a */
    public void onRecordEnd() {
        LogUtils.debug(f6379a, "onRecordEnd");
        this.f6382c.onRecordEnd();
        this.f6383d = false;
    }

    @Override // com.mradar.sdk.record.DoresoListener
    /* renamed from: a */
    public void onFinish(DoresoMusicTrack[] doresoMusicTrackArr,String str) {
        LogUtils.debug(f6379a, "onFinish");
        LogUtils.debug(f6379a, "recognize onFinish cost time: " + (System.currentTimeMillis() - this.f6386g) + "ms");
        this.f6382c.onRecordEnd();
        this.f6383d = false;
        if (this.f6384e) {
            this.f6387i.sendMessage(this.f6387i.obtainMessage(2, doresoMusicTrackArr));
        }
    }
}
