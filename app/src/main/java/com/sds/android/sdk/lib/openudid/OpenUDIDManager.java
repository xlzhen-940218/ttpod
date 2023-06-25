package com.sds.android.sdk.lib.openudid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.provider.Settings;
import com.sds.android.sdk.lib.util.LogUtils;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/* renamed from: com.sds.android.sdk.lib.openudid.a */
/* loaded from: classes.dex */
public class OpenUDIDManager implements ServiceConnection {

    /* renamed from: f */
    private static String f2398f = null;

    /* renamed from: g */
    private static boolean f2399g = false;

    /* renamed from: a */
    private final Context f2400a;

    /* renamed from: b */
    private List<ResolveInfo> f2401b;

    /* renamed from: d */
    private final SharedPreferences f2403d;

    /* renamed from: e */
    private final Random f2404e = new Random();

    /* renamed from: c */
    private Map<String, Integer> f2402c = new HashMap();

    private OpenUDIDManager(Context context) {
        this.f2403d = context.getSharedPreferences("openudid_prefs", 0);
        this.f2400a = context;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        String readString;
        try {
            Parcel obtain = Parcel.obtain();
            obtain.writeInt(this.f2404e.nextInt());
            Parcel obtain2 = Parcel.obtain();
            iBinder.transact(1, Parcel.obtain(), obtain2, 0);
            if (obtain.readInt() == obtain2.readInt() && (readString = obtain2.readString()) != null) {
                LogUtils.debug("OpenUDID", "Received " + readString);
                if (this.f2402c.containsKey(readString)) {
                    this.f2402c.put(readString, Integer.valueOf(this.f2402c.get(readString).intValue() + 1));
                } else {
                    this.f2402c.put(readString, 1);
                }
            }
        } catch (RemoteException e) {
            LogUtils.error("OpenUDID", "RemoteException: " + e.getMessage());
        }
        this.f2400a.unbindService(this);
        m8567d();
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
    }

    /* renamed from: b */
    private void m8569b() {
        SharedPreferences.Editor edit = this.f2403d.edit();
        edit.putString("openudid", f2398f);
        edit.commit();
    }

    /* renamed from: c */
    private void m8568c() {
        LogUtils.debug("OpenUDID", "Generating openUDID");
        f2398f = Settings.Secure.getString(this.f2400a.getContentResolver(), "android_id");
        if (f2398f == null || f2398f.equals("9774d56d682e549c") || f2398f.length() < 15) {
            f2398f = new BigInteger(64, new SecureRandom()).toString(16);
        }
    }

    /* renamed from: d */
    private void m8567d() {
        if (this.f2401b.size() > 0) {
            LogUtils.debug("OpenUDID", "Trying service " + ((Object) this.f2401b.get(0).loadLabel(this.f2400a.getPackageManager())));
            ServiceInfo serviceInfo = this.f2401b.get(0).serviceInfo;
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(serviceInfo.applicationInfo.packageName, serviceInfo.name));
            this.f2401b.remove(0);
            try {
                this.f2400a.bindService(intent, this, 1);
                return;
            } catch (SecurityException e) {
                m8567d();
                return;
            }
        }
        m8566e();
        if (f2398f == null) {
            m8568c();
        }
        LogUtils.debug("OpenUDID", "OpenUDID: " + f2398f);
        m8569b();
        f2399g = true;
    }

    /* renamed from: e */
    private void m8566e() {
        if (!this.f2402c.isEmpty()) {
            TreeMap treeMap = new TreeMap(new C0599a());
            treeMap.putAll(this.f2402c);
            f2398f = (String) treeMap.firstKey();
        }
    }

    /* renamed from: a */
    public static String m8572a() {
        if (!f2399g) {
            LogUtils.error("OpenUDID", "Initialisation isn't done");
        }
        return f2398f;
    }

    /* renamed from: a */
    public static void m8571a(Context context) {
        OpenUDIDManager openUDIDManager = new OpenUDIDManager(context);
        f2398f = openUDIDManager.f2403d.getString("openudid", null);
        if (f2398f == null) {
            openUDIDManager.f2401b = context.getPackageManager().queryIntentServices(new Intent("org.OpenUDID.GETUDID"), 0);
            LogUtils.debug("OpenUDID", openUDIDManager.f2401b.size() + " services matches OpenUDID");
            if (openUDIDManager.f2401b != null) {
                openUDIDManager.m8567d();
                return;
            }
            return;
        }
        LogUtils.debug("OpenUDID", "OpenUDID: " + f2398f);
        f2399g = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: OpenUDIDManager.java */
    /* renamed from: com.sds.android.sdk.lib.openudid.a$a */
    /* loaded from: classes.dex */
    public class C0599a implements Comparator {
        private C0599a() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            if (((Integer) OpenUDIDManager.this.f2402c.get(obj)).intValue() >= ((Integer) OpenUDIDManager.this.f2402c.get(obj2)).intValue()) {
                if (OpenUDIDManager.this.f2402c.get(obj) == OpenUDIDManager.this.f2402c.get(obj2)) {
                    return 0;
                }
                return -1;
            }
            return 1;
        }
    }
}
