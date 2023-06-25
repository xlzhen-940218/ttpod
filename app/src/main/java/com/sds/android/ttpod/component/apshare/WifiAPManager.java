package com.sds.android.ttpod.component.apshare;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;

import androidx.core.app.ActivityCompat;

import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.TTPodApplication;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sds.android.ttpod.component.apshare.j */
/* loaded from: classes.dex */
public final class WifiAPManager {

    /* renamed from: a */
    public static final String f3783a = "TTPODShare-" + Build.MODEL;

    /* renamed from: b */
    public static final int f3784b;

    /* renamed from: c */
    public static final int f3785c;

    /* renamed from: d */
    public static final int f3786d;

    /* renamed from: e */
    public static final int f3787e;

    /* renamed from: f */
    public static final int f3788f;

    /* renamed from: g */
    public static final String f3789g;

    /* renamed from: h */
    public static final String f3790h;

    /* renamed from: i */
    public static final String f3791i;

    /* renamed from: j */
    private static int f3792j;

    /* renamed from: k */
    private static int f3793k;

    /* renamed from: l */
    private static int f3794l;

    /* renamed from: m */
    private static int f3795m;

    /* renamed from: n */
    private static int f3796n;

    /* renamed from: o */
    private static String f3797o;

    /* renamed from: p */
    private static String f3798p;

    /* renamed from: q */
    private static String f3799q;

    /* renamed from: w */
    private static WifiAPManager f3800w;

    /* renamed from: r */
    private WifiManager f3801r;

    /* renamed from: s */
    private ConnectivityManager f3802s;

    /* renamed from: t */
    private InterfaceC1132a f3803t;

    /* renamed from: u */
    private WifiManager.WifiLock f3804u;

    /* renamed from: v */
    private Context f3805v;

    /* renamed from: x */
    private BroadcastReceiver f3806x = new BroadcastReceiver() { // from class: com.sds.android.ttpod.component.apshare.j.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.net.wifi.SCAN_RESULTS".equals(action)) {
                if (ActivityCompat.checkSelfPermission(TTPodApplication.getApplication(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                List<ScanResult> m7039b = WifiAPManager.this.m7039b(WifiAPManager.this.f3801r.getScanResults());
                if (WifiAPManager.this.f3803t != null) {
                    WifiAPManager.this.f3803t.mo7017a(m7039b);
                }
            } else if ("android.net.wifi.RSSI_CHANGED".equals(action)) {
                if (WifiAPManager.this.f3803t != null) {
                    WifiAPManager.this.f3803t.mo7025a(intent.getIntExtra("newRssi", 0));
                }
            } else if ("android.net.wifi.STATE_CHANGE".equals(action)) {
                NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                WifiInfo wifiInfo = (WifiInfo) intent.getParcelableExtra("wifiInfo");
                if (WifiAPManager.this.f3803t != null) {
                    if (!networkInfo.isAvailable() || !networkInfo.isConnected() || wifiInfo == null) {
                        WifiAPManager.this.f3803t.mo7022a(networkInfo);
                    } else {
                        WifiAPManager.this.f3803t.mo7021a(networkInfo, wifiInfo);
                    }
                }
            } else if ("android.net.wifi.supplicant.CONNECTION_CHANGE".equals(action)) {
                if (WifiAPManager.this.f3803t != null) {
                    WifiAPManager.this.f3803t.mo7016a(intent.getBooleanExtra("connected", false));
                }
            } else if ("android.net.wifi.supplicant.STATE_CHANGE".equals(action)) {
                if (WifiAPManager.this.f3803t != null) {
                    WifiAPManager.this.f3803t.mo7020a((SupplicantState) intent.getParcelableExtra("newState"), intent.getIntExtra("supplicantError", 1));
                }
            } else if ("android.net.wifi.WIFI_STATE_CHANGED".equals(action) && WifiAPManager.this.f3803t != null) {
                WifiAPManager.this.f3803t.mo7024a(intent.getIntExtra("wifi_state", 4), intent.getIntExtra("previous_wifi_state", 4));
            }
            if (WifiAPManager.f3789g.equals(action) && WifiAPManager.this.f3803t != null) {
                WifiAPManager.this.f3803t.mo7019a(WifiAPManager.this, intent.getIntExtra(WifiAPManager.f3790h, WifiAPManager.f3788f));
            }
        }
    };

    /* compiled from: WifiAPManager.java */
    /* renamed from: com.sds.android.ttpod.component.apshare.j$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1132a {
        /* renamed from: a */
        void mo7025a(int i);

        /* renamed from: a */
        void mo7024a(int i, int i2);

        /* renamed from: a */
        void mo7022a(NetworkInfo networkInfo);

        /* renamed from: a */
        void mo7021a(NetworkInfo networkInfo, WifiInfo wifiInfo);

        /* renamed from: a */
        void mo7020a(SupplicantState supplicantState, int i);

        /* renamed from: a */
        void mo7019a(WifiAPManager wifiAPManager, int i);

        /* renamed from: a */
        void mo7018a(String str);

        /* renamed from: a */
        void mo7017a(List<ScanResult> list);

        /* renamed from: a */
        void mo7016a(boolean z);

        /* renamed from: a */
        void mo7015a(boolean z, String str);
    }

    static {
        try {
            f3792j = WifiManager.class.getField("WIFI_AP_STATE_DISABLED").getInt(WifiManager.class);
            f3793k = WifiManager.class.getField("WIFI_AP_STATE_DISABLING").getInt(WifiManager.class);
            f3794l = WifiManager.class.getField("WIFI_AP_STATE_ENABLED").getInt(WifiManager.class);
            f3795m = WifiManager.class.getField("WIFI_AP_STATE_ENABLING").getInt(WifiManager.class);
            f3796n = WifiManager.class.getField("WIFI_AP_STATE_FAILED").getInt(WifiManager.class);
            f3797o = (String) WifiManager.class.getField("WIFI_AP_STATE_CHANGED_ACTION").get(WifiManager.class);
            f3798p = (String) WifiManager.class.getField("EXTRA_WIFI_AP_STATE").get(WifiManager.class);
            f3799q = (String) WifiManager.class.getField("EXTRA_PREVIOUS_WIFI_AP_STATE").get(WifiManager.class);
        } catch (Exception e) {
            LogUtils.error("WifiAPManager", e.getMessage(), e);
        }
        f3784b = f3792j;
        f3785c = f3793k;
        f3786d = f3794l;
        f3787e = f3795m;
        f3788f = f3796n;
        f3789g = f3797o;
        f3790h = f3798p;
        f3791i = f3799q;
        f3800w = null;
    }

    /* renamed from: a */
    public static synchronized WifiAPManager m7053a(Context context) {
        WifiAPManager wifiAPManager;
        synchronized (WifiAPManager.class) {
            if (f3800w == null) {
                if (context == null) {
                    throw new IllegalArgumentException("Create WifiAPManager instances. context can NOT be null");
                }
                f3800w = new WifiAPManager(context, null);
            }
            wifiAPManager = f3800w;
        }
        return wifiAPManager;
    }

    private WifiAPManager(Context context, InterfaceC1132a interfaceC1132a) {
        this.f3801r = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        this.f3802s = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        this.f3803t = interfaceC1132a;
        this.f3804u = this.f3801r.createWifiLock(3, "TTPODShare-");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
        intentFilter.addAction("android.net.wifi.RSSI_CHANGED");
        intentFilter.addAction("android.net.wifi.NETWORK_IDS_CHANGED");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.supplicant.CONNECTION_CHANGE");
        intentFilter.addAction("android.net.wifi.supplicant.STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.PICK_WIFI_NETWORK");
        if (!TextUtils.isEmpty(f3789g)) {
            intentFilter.addAction(f3789g);
        }
        this.f3805v = context;
        context.registerReceiver(this.f3806x, intentFilter);
    }

    /* renamed from: a */
    public void m7049a(InterfaceC1132a interfaceC1132a) {
        this.f3803t = interfaceC1132a;
    }

    /* renamed from: a */
    public boolean m7054a() {
        try {
            return ((Boolean) this.f3802s.getClass().getMethod("getMobileDataEnabled", new Class[0]).invoke(this.f3802s, new Object[0])).booleanValue();
        } catch (Exception e) {
            LogUtils.error("WifiAPManager", e.getMessage(), e);
            return false;
        }
    }

    /* renamed from: a */
    public void m7044a(boolean z) {
        try {
            if (z != m7054a()) {
                this.f3802s.getClass().getMethod("setMobileDataEnabled", Boolean.TYPE).invoke(this.f3802s, Boolean.valueOf(z));
            }
        } catch (Exception e) {
            LogUtils.error("WifiAPManager", e.getMessage(), e);
        }
    }

    /* renamed from: a */
    public boolean m7050a(WifiConfiguration wifiConfiguration, boolean z) {
        if (z) {
            try {
                this.f3801r.setWifiEnabled(false);
            } catch (Exception e) {
                LogUtils.error("WifiAPManager", e.getMessage(), e);
                return false;
            }
        }
        LogUtils.info("WifiAPManager", "set wifi ap configuration: " + m7042b(wifiConfiguration));
        try {
            return ((Boolean) this.f3801r.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, Boolean.TYPE).invoke(this.f3801r, wifiConfiguration, Boolean.valueOf(z))).booleanValue();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: b */
    public WifiConfiguration m7043b() {
        try {
            return (WifiConfiguration) this.f3801r.getClass().getMethod("getWifiApConfiguration", new Class[0]).invoke(this.f3801r, new Object[0]);
        } catch (Exception e) {
            LogUtils.error("WifiAPManager", e.getMessage(), e);
            return null;
        }
    }

    /* renamed from: b */
    private boolean m7042b(WifiConfiguration wifiConfiguration) {
        try {
            return ((Boolean) this.f3801r.getClass().getMethod("setWifiApConfiguration", WifiConfiguration.class).invoke(this.f3801r, wifiConfiguration)).booleanValue();
        } catch (Exception e) {
            LogUtils.error("WifiAPManager", e.getMessage(), e);
            return false;
        }
    }

    /* renamed from: c */
    public boolean m7038c() {
        try {
            return ((Boolean) this.f3801r.getClass().getMethod("isWifiApEnabled", new Class[0]).invoke(this.f3801r, new Object[0])).booleanValue();
        } catch (Exception e) {
            LogUtils.error("WifiAPManager", e.getMessage(), e);
            return false;
        }
    }

    /* renamed from: d */
    public boolean m7036d() {
        return this.f3801r.getWifiState() == 3;
    }

    /* renamed from: e */
    public boolean m7034e() {
        return m7026m();
    }

    /* renamed from: f */
    public void m7033f() {
        m7027l();
        if (this.f3804u.isHeld()) {
            this.f3804u.release();
        }
    }

    /* renamed from: g */
    public void m7032g() {
        if (this.f3801r.isWifiEnabled()) {
            this.f3801r.setWifiEnabled(false);
        }
    }

    /* renamed from: h */
    public void m7031h() {
        if (!this.f3801r.isWifiEnabled()) {
            this.f3801r.setWifiEnabled(true);
        }
    }

    /* renamed from: l */
    private void m7027l() {
        if (m7038c()) {
            m7050a(m7043b(), false);
        }
    }

    /* renamed from: m */
    private boolean m7026m() {
        m7032g();
        m7027l();
        this.f3804u.acquire();
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = f3783a;
        wifiConfiguration.preSharedKey = "ttpod_apshare";
        m7037c(wifiConfiguration);
        return m7050a(wifiConfiguration, true);
    }

    /* renamed from: c */
    private void m7037c(WifiConfiguration wifiConfiguration) {
        wifiConfiguration.hiddenSSID = false;
        wifiConfiguration.status = 2;
        wifiConfiguration.allowedAuthAlgorithms.set(0);
        wifiConfiguration.allowedGroupCiphers.set(2);
        wifiConfiguration.allowedGroupCiphers.set(3);
        wifiConfiguration.allowedPairwiseCiphers.set(1);
        wifiConfiguration.allowedPairwiseCiphers.set(2);
        wifiConfiguration.allowedKeyManagement.set(1);
        wifiConfiguration.allowedProtocols.set(0);
        wifiConfiguration.allowedProtocols.set(1);
        m7035d(wifiConfiguration);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public List<ScanResult> m7039b(List<ScanResult> list) {
        return m7045a(list);
    }

    /* renamed from: a */
    public List<ScanResult> m7045a(List<ScanResult> list) {
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (ScanResult scanResult : list) {
                if (scanResult.SSID.contains("TTPODShare-")) {
                    arrayList.add(scanResult);
                }
            }
            return arrayList;
        }
        return null;
    }

    /* renamed from: a */
    public boolean m7052a(ScanResult scanResult) {
        if (scanResult == null) {
            return false;
        }
        if (this.f3803t != null) {
            this.f3803t.mo7018a(scanResult.SSID);
        }
        m7040b("TTPODShare-");
        if (!scanResult.SSID.equals(this.f3801r.getConnectionInfo().getSSID())) {
            this.f3801r.disconnect();
        }
        if (ActivityCompat.checkSelfPermission(TTPodApplication.getApplication(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return false;
        }
        List<WifiConfiguration> configuredNetworks = this.f3801r.getConfiguredNetworks();
        if (configuredNetworks != null) {
            for (WifiConfiguration wifiConfiguration : configuredNetworks) {
                if (!wifiConfiguration.SSID.equals(scanResult.SSID)) {
                    wifiConfiguration.priority = 0;
                    this.f3801r.updateNetwork(wifiConfiguration);
                }
            }
        }
        WifiConfiguration wifiConfiguration2 = new WifiConfiguration();
        wifiConfiguration2.SSID = "\"" + scanResult.SSID + "\"";
        wifiConfiguration2.preSharedKey = "\"ttpod_apshare\"";
        wifiConfiguration2.BSSID = scanResult.BSSID;
        m7037c(wifiConfiguration2);
        try {
            Field field = wifiConfiguration2.getClass().getField("ipAssignment");
            field.set(wifiConfiguration2, Enum.valueOf( (Class<Enum>)field.getType().asSubclass(Enum.class), "DHCP"));
        } catch (Exception e) {
            LogUtils.error("WifiAPManager", e.getMessage(), e);
        }
        boolean enableNetwork = this.f3801r.enableNetwork(this.f3801r.addNetwork(wifiConfiguration2), true);
        this.f3801r.saveConfiguration();
        this.f3801r.reconnect();
        if (this.f3803t != null) {
            this.f3803t.mo7015a(enableNetwork, scanResult.SSID);
            return enableNetwork;
        }
        return enableNetwork;
    }

    /* renamed from: a */
    public void m7046a(String str) {
        if (ActivityCompat.checkSelfPermission(TTPodApplication.getApplication(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        List<WifiConfiguration> configuredNetworks = this.f3801r.getConfiguredNetworks();
        if (!StringUtils.isEmpty(str) && configuredNetworks != null) {
            String str2 = "\"" + str + "\"";
            for (WifiConfiguration wifiConfiguration : configuredNetworks) {
                if (wifiConfiguration != null && wifiConfiguration.SSID.equals(str2)) {
                    this.f3801r.enableNetwork(wifiConfiguration.networkId, false);
                    this.f3801r.saveConfiguration();
                    this.f3801r.reconnect();
                    this.f3801r.reassociate();
                    return;
                }
            }
        }
    }

    /* renamed from: i */
    public List<ScanResult> m7030i() {
        m7027l();
        m7031h();
        try {
            this.f3804u.acquire();
            this.f3801r.startScan();
            if (ActivityCompat.checkSelfPermission(TTPodApplication.getApplication(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return null;
            }
            return m7045a(this.f3801r.getScanResults());
        } finally {
            this.f3804u.release();
        }
    }

    /* renamed from: d */
    private void m7035d(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration != null) {
            try {
                Field declaredField = WifiConfiguration.class.getDeclaredField("mWifiApProfile");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(wifiConfiguration);
                declaredField.setAccessible(false);
                if (obj != null) {
                    Field declaredField2 = obj.getClass().getDeclaredField("SSID");
                    declaredField2.setAccessible(true);
                    declaredField2.set(obj, wifiConfiguration.SSID);
                    declaredField2.setAccessible(false);
                    Field declaredField3 = obj.getClass().getDeclaredField("BSSID");
                    declaredField3.setAccessible(true);
                    declaredField3.set(obj, wifiConfiguration.BSSID);
                    declaredField3.setAccessible(false);
                    Field declaredField4 = obj.getClass().getDeclaredField("secureType");
                    declaredField4.setAccessible(true);
                    if (TextUtils.isEmpty(wifiConfiguration.preSharedKey)) {
                        declaredField4.set(obj, "open");
                    } else {
                        declaredField4.set(obj, "wpa2-psk");
                    }
                    declaredField4.setAccessible(false);
                    Field declaredField5 = obj.getClass().getDeclaredField("dhcpEnable");
                    declaredField5.setAccessible(true);
                    declaredField5.set(obj, 1);
                    declaredField5.setAccessible(false);
                    Field declaredField6 = obj.getClass().getDeclaredField("key");
                    declaredField6.setAccessible(true);
                    declaredField6.set(obj, wifiConfiguration.preSharedKey);
                    declaredField6.setAccessible(false);
                }
            } catch (Exception e) {
                LogUtils.error("WifiAPManager", e.getMessage(), e);
            }
        }
    }

    /* renamed from: a */
    public void m7051a(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration != null) {
            if (TextUtils.isEmpty(wifiConfiguration.SSID) || TextUtils.isEmpty(wifiConfiguration.BSSID)) {
                try {
                    Field declaredField = WifiConfiguration.class.getDeclaredField("mWifiApProfile");
                    declaredField.setAccessible(true);
                    Object obj = declaredField.get(wifiConfiguration);
                    declaredField.setAccessible(false);
                    if (obj != null) {
                        Field declaredField2 = obj.getClass().getDeclaredField("SSID");
                        declaredField2.setAccessible(true);
                        Object obj2 = declaredField2.get(obj);
                        if (obj2 != null) {
                            wifiConfiguration.SSID = (String) obj2;
                        }
                        declaredField2.setAccessible(false);
                        Field declaredField3 = obj.getClass().getDeclaredField("BSSID");
                        declaredField3.setAccessible(true);
                        Object obj3 = declaredField3.get(obj);
                        if (obj3 != null) {
                            wifiConfiguration.BSSID = (String) obj3;
                        }
                        declaredField3.setAccessible(false);
                    }
                } catch (Exception e) {
                    LogUtils.error("WifiAPManager", e.getMessage(), e);
                }
            }
        }
    }

    /* renamed from: j */
    public WifiManager m7029j() {
        return this.f3801r;
    }

    /* renamed from: k */
    public WifiInfo m7028k() {
        return this.f3801r.getConnectionInfo();
    }

    /* renamed from: b */
    public void m7040b(String str) {
        if (ActivityCompat.checkSelfPermission(TTPodApplication.getApplication(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        List<WifiConfiguration> configuredNetworks = this.f3801r.getConfiguredNetworks();
        if (configuredNetworks != null && str != null) {
            for (WifiConfiguration wifiConfiguration : configuredNetworks) {
                if (wifiConfiguration != null && wifiConfiguration.SSID != null && wifiConfiguration.SSID.contains(str)) {
                    LogUtils.info("WifiAPManager", "disable: " + wifiConfiguration.SSID);
                    this.f3801r.disableNetwork(wifiConfiguration.networkId);
                    this.f3801r.removeNetwork(wifiConfiguration.networkId);
                }
            }
        }
        this.f3801r.saveConfiguration();
    }
}
