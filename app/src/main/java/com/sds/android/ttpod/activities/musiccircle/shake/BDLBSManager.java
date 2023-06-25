package com.sds.android.ttpod.activities.musiccircle.shake;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.sds.android.sdk.lib.util.LogUtils;

/* renamed from: com.sds.android.ttpod.activities.musiccircle.shake.a */
/* loaded from: classes.dex */
public final class BDLBSManager {

    /* renamed from: a */
    private LocationClient f2913a;

    /* renamed from: b */
    private BDLocationListener f2914b = new C0847a();

    /* renamed from: c */
    private BDLocation f2915c;

    public BDLBSManager(Context context) throws Exception {
        this.f2913a = new LocationClient(context.getApplicationContext());
        LocationClientOption locationClientOption = new LocationClientOption();
        locationClientOption.setOpenGps(true);
        locationClientOption.setCoorType("gcj02");
        locationClientOption.setScanSpan(2000);
        this.f2913a.setLocOption(locationClientOption);
    }

    /* compiled from: BDLBSManager.java */
    /* renamed from: com.sds.android.ttpod.activities.musiccircle.shake.a$a */
    /* loaded from: classes.dex */
    class C0847a implements BDLocationListener {
        C0847a() {
        }

        @Override // com.baidu.location.BDLocationListener
        public void onReceiveLocation(BDLocation bDLocation) {
            if (bDLocation != null) {
                BDLBSManager.this.f2915c = bDLocation;
                LogUtils.info("BDLBSManager", "onReceiveLocation=>longitude:" + bDLocation.getLongitude() + ":latitude:" + bDLocation.getLatitude());
                return;
            }
            LogUtils.error("BDLBSManager", "onReceiveLocation error...");
        }


    }

    /* renamed from: a */
    public BDLocation m7842a() {
        return this.f2915c;
    }

    /* renamed from: b */
    public void m7840b() {
        this.f2913a.registerLocationListener(this.f2914b);
        if (!this.f2913a.isStarted()) {
            this.f2913a.start();
        }
    }

    /* renamed from: c */
    public int m7839c() {
        return this.f2913a.requestLocation();
    }

    /* renamed from: d */
    public void m7838d() {
        this.f2913a.stop();
    }

    /* renamed from: e */
    public void m7837e() {
        this.f2913a.unRegisterLocationListener(this.f2914b);
    }
}
