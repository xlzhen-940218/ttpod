package com.sds.android.ttpod.component.apshare;

import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.os.Handler;
import android.os.Message;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.component.apshare.WifiAPManager;
import java.util.List;

/* renamed from: com.sds.android.ttpod.component.apshare.k */
/* loaded from: classes.dex */
public class WifiStateImpl implements WifiAPManager.InterfaceC1132a {

    /* renamed from: a */
    private Handler f3808a;

    public WifiStateImpl(Handler handler) {
        this.f3808a = handler;
    }

    @Override // com.sds.android.ttpod.component.apshare.WifiAPManager.InterfaceC1132a
    /* renamed from: a */
    public void mo7017a(List<ScanResult> list) {
        if (this.f3808a != null) {
            this.f3808a.sendMessage(m7023a(5, list));
        }
    }

    @Override // com.sds.android.ttpod.component.apshare.WifiAPManager.InterfaceC1132a
    /* renamed from: a */
    public void mo7020a(SupplicantState supplicantState, int i) {
        if (supplicantState != null) {
            if (supplicantState.equals(SupplicantState.SCANNING)) {
                if (this.f3808a != null) {
                    this.f3808a.sendEmptyMessage(4);
                }
            } else if (supplicantState.equals(SupplicantState.DISCONNECTED) && this.f3808a != null) {
                this.f3808a.sendEmptyMessage(8);
            }
            LogUtils.m8379d("WifiStateImpl", "supplicant state: " + supplicantState.toString());
        }
    }

    @Override // com.sds.android.ttpod.component.apshare.WifiAPManager.InterfaceC1132a
    /* renamed from: a */
    public void mo7016a(boolean z) {
    }

    @Override // com.sds.android.ttpod.component.apshare.WifiAPManager.InterfaceC1132a
    /* renamed from: a */
    public void mo7024a(int i, int i2) {
        LogUtils.m8379d("WifiStateImpl", "wifiStateChanged: " + i);
    }

    @Override // com.sds.android.ttpod.component.apshare.WifiAPManager.InterfaceC1132a
    /* renamed from: a */
    public void mo7019a(WifiAPManager wifiAPManager, int i) {
        if (wifiAPManager != null) {
            WifiConfiguration m7043b = wifiAPManager.m7043b();
            if (i == WifiAPManager.f3786d && this.f3808a != null) {
                if (m7043b != null) {
                    wifiAPManager.m7051a(m7043b);
                    this.f3808a.sendMessage(m7023a(14, m7043b.SSID));
                }
            } else if (this.f3808a != null && i == WifiAPManager.f3787e) {
                this.f3808a.sendEmptyMessage(13);
            } else if (this.f3808a != null && i == WifiAPManager.f3788f) {
                this.f3808a.sendEmptyMessage(15);
            } else if (this.f3808a != null && i == WifiAPManager.f3785c) {
                this.f3808a.sendEmptyMessage(11);
            } else if (this.f3808a != null && i == WifiAPManager.f3784b) {
                this.f3808a.sendEmptyMessage(12);
            }
        }
    }

    @Override // com.sds.android.ttpod.component.apshare.WifiAPManager.InterfaceC1132a
    /* renamed from: a */
    public void mo7025a(int i) {
    }

    @Override // com.sds.android.ttpod.component.apshare.WifiAPManager.InterfaceC1132a
    /* renamed from: a */
    public void mo7018a(String str) {
        if (this.f3808a != null) {
            this.f3808a.sendMessage(m7023a(6, str));
        }
        LogUtils.m8379d("WifiStateImpl", "connection preparing: " + str);
    }

    @Override // com.sds.android.ttpod.component.apshare.WifiAPManager.InterfaceC1132a
    /* renamed from: a */
    public void mo7015a(boolean z, String str) {
        if (this.f3808a != null) {
            this.f3808a.sendMessage(m7023a(6, z ? "正在连接热点" + str : "热点" + str + "不能连接"));
        }
    }

    @Override // com.sds.android.ttpod.component.apshare.WifiAPManager.InterfaceC1132a
    /* renamed from: a */
    public void mo7021a(NetworkInfo networkInfo, WifiInfo wifiInfo) {
        if (wifiInfo.getSSID().contains("TTPODShare-")) {
            LogUtils.m8379d("WifiStateImpl", "Connect Network Succeeded");
            if (this.f3808a != null) {
                this.f3808a.sendMessage(m7023a(7, wifiInfo.getSSID()));
            }
        }
    }

    @Override // com.sds.android.ttpod.component.apshare.WifiAPManager.InterfaceC1132a
    /* renamed from: a */
    public void mo7022a(NetworkInfo networkInfo) {
        if (this.f3808a != null) {
            this.f3808a.sendEmptyMessage(9);
        }
        LogUtils.m8379d("WifiStateImpl", "Connect Network Failed");
    }

    /* renamed from: a */
    private Message m7023a(int i, Object obj) {
        if (this.f3808a == null) {
            return null;
        }
        Message obtainMessage = this.f3808a.obtainMessage();
        obtainMessage.what = i;
        obtainMessage.obj = obj;
        return obtainMessage;
    }
}
