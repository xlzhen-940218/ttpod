package com.sds.android.ttpod.framework.support;

import android.os.RemoteException;

import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.lang.ref.SoftReference;
import java.util.List;
import java.util.Map;

/* renamed from: com.sds.android.ttpod.framework.support.f */
/* loaded from: classes.dex */
public class SupportServiceStub extends ISupportService.AbstractBinderC2061a {

    /* renamed from: b */
    private static final short[] f7156b = new short[2048];

    /* renamed from: a */
    protected SoftReference<SupportService> softReference;

    public SupportServiceStub(SupportService supportService) {
        this.softReference = new SoftReference<>(supportService);
    }

    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: a */
    public void mo2395a() throws RemoteException {
        this.softReference.get().m2789c();
    }

    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: b */
    public int mo2383b() throws RemoteException {
        return this.softReference.get().m2785d();
    }

    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: c */
    public int mo2378c() throws RemoteException {
        return this.softReference.get().m2782e();
    }

    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: d */
    public float mo2376d() throws RemoteException {
        return this.softReference.get().m2781f();
    }

    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: a */
    public void mo2394a(int i) throws RemoteException {
        this.softReference.get().m2797b(i);
    }

    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: e */
    public int mo2374e() throws RemoteException {
        return this.softReference.get().m2780g();
    }

    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: a */
    public boolean mo2384a(int[] iArr, int i) throws RemoteException {
        return this.softReference.get().m2799a(iArr, i);
    }

    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: b */
    public boolean mo2379b(int[] iArr, int i) throws RemoteException {
        if (this.softReference.get().m2798a(f7156b, i)) {
            for (int i2 = 0; i2 < i; i2++) {
                iArr[i2] = f7156b[i2];
            }
            return true;
        }
        return false;
    }

    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: g */
    public void mo2372g() throws RemoteException {
        this.softReference.get().m2778i();
    }

    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: h */
    public void mo2371h() throws RemoteException {
        this.softReference.get().m2777j();
    }

    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: a */
    public void mo2386a(boolean z) throws RemoteException {
        this.softReference.get().m2801a(z);
    }

    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: b */
    public void mo2382b(long j) throws RemoteException {
        this.softReference.get().m2796b(j);
    }

    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: l */
    public void mo2367l() throws RemoteException {
        this.softReference.get().m2776k();
    }

    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: a */
    public void mo2388a(String str, boolean z) {
        this.softReference.get().m2802a(str, z);
    }

    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: i */
    public String mo2370i() throws RemoteException {
        return this.softReference.get().m2779h();
    }

    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: j */
    public String mo2369j() throws RemoteException {
        return this.softReference.get().m2775l();
    }

    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: f */
    public MediaItem mo2373f() {
        return this.softReference.get().m2774m();
    }



    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: a */
    public void mo2389a(String str, int i, int i2, String str2, String str3, boolean z) throws RemoteException {
        this.softReference.get().m2804a(str, i, i2, str2, str3, z);
    }

    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: k */
    public long mo2368k() throws RemoteException {
        return this.softReference.get().m2773n();
    }

    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: a */
    public void mo2393a(long j) throws RemoteException {
        this.softReference.get().m2788c(j);
    }

    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: a */
    public void mo2387a(Map map) {
        LogUtils.debug("SupportServiceStub", "setOnlineMediaOrigin origin = " + map.get("origin"));
        //OnlineMediaStatistic.m5044a(map);
    }

    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: a */
    public void mo2390a(String str) throws RemoteException {
        this.softReference.get().m2805a(str);
    }

    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: b */
    public void mo2380b(String str) throws RemoteException {
        this.softReference.get().m2791b(str);
    }

    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: a */
    public void mo2391a(DownloadTaskInfo downloadTaskInfo) throws RemoteException {
        this.softReference.get().m2806a(downloadTaskInfo);
    }

    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: b */
    public DownloadTaskInfo mo2381b(DownloadTaskInfo downloadTaskInfo) throws RemoteException {
        return this.softReference.get().m2793b(downloadTaskInfo);
    }

    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: c */
    public DownloadTaskInfo mo2377c(DownloadTaskInfo downloadTaskInfo) throws RemoteException {
        return this.softReference.get().m2786c(downloadTaskInfo);
    }

    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: d */
    public int mo2375d(DownloadTaskInfo downloadTaskInfo) throws RemoteException {
        return this.softReference.get().m2783d(downloadTaskInfo);
    }

    @Override // com.sds.android.ttpod.framework.support.ISupportService
    /* renamed from: a */
    public List<DownloadTaskInfo> mo2385a(int[] iArr) {
        return this.softReference.get().m2800a(iArr);
    }
}
