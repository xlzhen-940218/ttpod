package com.mradar.sdk.record;

import android.content.Context;
import java.io.File;
import java.util.concurrent.Future;

/* renamed from: com.mradar.sdk.record.b */
/* loaded from: classes.dex */
public class DoresoManager {

    /* renamed from: a */
    private Context f2206a;

    /* renamed from: b */
    private String f2207b;

    /* renamed from: c */
    private MRadarSdkListener mRadarSdkListener;

    /* renamed from: d */
    private Future<?> f2209d;

    /* renamed from: e */
    private Record record;

    /* renamed from: f */
    private OfflineRecognizer offlineRecognizer;

    /* renamed from: g */
    private OnlineRecognizer onlineRecognizer;

    /* renamed from: h */
    private long f2213h;

    /* renamed from: i */
    private int f2214i;

    public DoresoManager(Context context, String str) {
        this.f2206a = context;
        this.f2207b = str;
        if (!MRadarSdk.IsAdvance) {
            new GetIpAddress(this.f2206a).execute(new Void[0]);
        }
    }

    /* renamed from: a */
    public void setmRadarSdkListener(MRadarSdkListener doresoListener) {
        this.mRadarSdkListener = doresoListener;
    }

    /* renamed from: a */
    public void m8997a(File file) {
        if (this.f2214i == 0) {
            this.f2214i = 1;
            if (this.onlineRecognizer != null) {
                this.onlineRecognizer.reqCancel();
                this.onlineRecognizer = null;
                this.f2209d.cancel(false);
            }
            this.onlineRecognizer = new OnlineRecognizer(this.f2206a, this.f2207b, this.f2213h, file, this.mRadarSdkListener);
            this.f2209d = MRadarSdkThreadPoll.getInstance().getThreadPool().submit(this.onlineRecognizer);
            if (!MRadarSdk.IsLocation) {
                new GetIpAddress(this.f2206a).execute(new Void[0]);
            }
        }
    }

    /* renamed from: a */
    public void onRecordEnd() {
        if (this.f2214i == 1) {
            if (this.onlineRecognizer != null) {
                this.onlineRecognizer.reqStop();
            }
        } else if (this.f2214i == 2) {
            if (this.record != null) {
                this.record.reqStop();
            }
        } else if (this.f2214i == 3 && this.offlineRecognizer != null) {
            this.offlineRecognizer.reqStop();
        }
        this.f2214i = 0;
    }

    /* renamed from: b */
    public void m8996b() {
        if (this.f2214i == 1) {
            if (this.onlineRecognizer != null) {
                this.onlineRecognizer.reqCancel();
            }
        } else if (this.f2214i == 2) {
            if (this.record != null) {
                this.record.reqCancel();
            }
        } else if (this.f2214i == 3 && this.offlineRecognizer != null) {
            this.offlineRecognizer.reqCancel();
        }
        this.f2214i = 0;
    }
}