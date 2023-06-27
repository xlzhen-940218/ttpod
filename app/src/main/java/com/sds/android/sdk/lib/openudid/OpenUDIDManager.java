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
    private static String uuid = null;

    /* renamed from: g */
    private static boolean f2399g = false;

    /* renamed from: a */
    private final Context context;

    /* renamed from: b */
    private List<ResolveInfo> resolveInfos;

    /* renamed from: d */
    private final SharedPreferences sharedPreferences;

    /* renamed from: e */
    private final Random random = new Random();

    /* renamed from: c */
    private Map<String, Integer> f2402c = new HashMap();

    private OpenUDIDManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences("openudid_prefs", 0);
        this.context = context;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        String readString;
        try {
            Parcel obtain = Parcel.obtain();
            obtain.writeInt(this.random.nextInt());
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
        this.context.unbindService(this);
        m8567d();
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
    }

    /* renamed from: b */
    private void m8569b() {
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        edit.putString("openudid", uuid);
        edit.commit();
    }

    /* renamed from: c */
    private void m8568c() {
        LogUtils.debug("OpenUDID", "Generating openUDID");
        uuid = Settings.Secure.getString(this.context.getContentResolver(), "android_id");
        if (uuid == null || uuid.equals("9774d56d682e549c") || uuid.length() < 15) {
            uuid = new BigInteger(64, new SecureRandom()).toString(16);
        }
    }

    /* renamed from: d */
    private void m8567d() {
        if (this.resolveInfos.size() > 0) {
            LogUtils.debug("OpenUDID", "Trying service " + ((Object) this.resolveInfos.get(0).loadLabel(this.context.getPackageManager())));
            ServiceInfo serviceInfo = this.resolveInfos.get(0).serviceInfo;
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(serviceInfo.applicationInfo.packageName, serviceInfo.name));
            this.resolveInfos.remove(0);
            try {
                this.context.bindService(intent, this, Context.BIND_AUTO_CREATE);
                return;
            } catch (SecurityException e) {
                m8567d();
                return;
            }
        }
        m8566e();
        if (uuid == null) {
            m8568c();
        }
        LogUtils.debug("OpenUDID", "OpenUDID: " + uuid);
        m8569b();
        f2399g = true;
    }

    /* renamed from: e */
    private void m8566e() {
        if (!this.f2402c.isEmpty()) {
            TreeMap treeMap = new TreeMap(new C0599a());
            treeMap.putAll(this.f2402c);
            uuid = (String) treeMap.firstKey();
        }
    }

    /* renamed from: a */
    public static String m8572a() {
        if (!f2399g) {
            LogUtils.error("OpenUDID", "Initialisation isn't done");
        }
        return uuid;
    }

    /* renamed from: a */
    public static void m8571a(Context context) {
        OpenUDIDManager openUDIDManager = new OpenUDIDManager(context);
        uuid = openUDIDManager.sharedPreferences.getString("openudid", null);
        if (uuid == null) {
            openUDIDManager.resolveInfos = context.getPackageManager().queryIntentServices(new Intent("org.OpenUDID.GETUDID"), 0);
            LogUtils.debug("OpenUDID", openUDIDManager.resolveInfos.size() + " services matches OpenUDID");
            if (openUDIDManager.resolveInfos != null) {
                openUDIDManager.m8567d();
                return;
            }
            return;
        }
        LogUtils.debug("OpenUDID", "OpenUDID: " + uuid);
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
