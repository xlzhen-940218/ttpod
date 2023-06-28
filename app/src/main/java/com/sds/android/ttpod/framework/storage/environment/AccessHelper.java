package com.sds.android.ttpod.framework.storage.environment;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.util.Pair;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.base.Action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.sds.android.ttpod.framework.storage.environment.a */
/* loaded from: classes.dex */
public final class AccessHelper {

    /* renamed from: a */
    private static C2030a f6986a;

    /* renamed from: b */
    private static Context context = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AccessHelper.java */
    /* renamed from: com.sds.android.ttpod.framework.storage.environment.a$a */
    /* loaded from: classes.dex */
    public static class C2030a extends BroadcastReceiver {

        /* renamed from: a */
        private List<Pair<PreferencesID, Preferences.InterfaceC2031a>> f6988a = new ArrayList();

        C2030a() {
        }

        /* renamed from: a */
        void m3083a(PreferencesID preferencesID, Preferences.InterfaceC2031a interfaceC2031a) {
            this.f6988a.add(new Pair<>(preferencesID, interfaceC2031a));
        }

        /* renamed from: b */
        void m3081b(PreferencesID preferencesID, Preferences.InterfaceC2031a interfaceC2031a) {
            for (Pair<PreferencesID, Preferences.InterfaceC2031a> pair : this.f6988a) {
                if (pair.first == preferencesID && pair.second == interfaceC2031a) {
                    this.f6988a.remove(pair);
                    return;
                }
            }
        }

        /* renamed from: a */
        boolean m3084a() {
            return this.f6988a.isEmpty();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                PreferencesID valueOf = PreferencesID.valueOf(intent.getStringExtra("preferences_id"));
                for (Pair<PreferencesID, Preferences.InterfaceC2031a> pair : this.f6988a) {
                    if (pair.first == valueOf) {
                        ((Preferences.InterfaceC2031a) pair.second).mo2553a(valueOf);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* renamed from: b */
        static IntentFilter m3082b() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Action.PREFERENCES_CHANGED);
            return intentFilter;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m3103a(Context context) {
        AccessHelper.context = context;
    }

    /* renamed from: a */
    private static Uri parse(String scheme, String host) {
        return Uri.parse(scheme + host);
    }

    /* renamed from: a */
    private static String getStringName() {
        return EnvironmentContentProvider.EnumC2029a.STRING.name();
    }

    /* renamed from: b */
    private static String getSetName() {
        return EnvironmentContentProvider.EnumC2029a.SET.name();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m3096a(String str, String str2, Boolean bool) {
        try {
            Cursor query = context.getContentResolver().query(parse(str, str2)
                    , null, getStringName(), null, null);
            if (query != null) {
                if (query.moveToNext()) {
                    bool = Boolean.valueOf(query.getString(0));
                }
                query.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bool.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static void m3087b(String str, String str2, Boolean bool) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str2, bool.toString());
        try {
            context.getContentResolver().update(parse(str, str2), contentValues, getStringName(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String query(String content, String uri, String str3) {
        try {
            Cursor query = context.getContentResolver().query(parse(content, uri), null, getStringName(), null, null);
            if (query != null) {
                if (query.moveToNext()) {
                    str3 = query.getString(0);
                }
                query.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static void m3086b(String str, String str2, String str3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str2, str3);
        try {
            context.getContentResolver().update(parse(str, str2), contentValues, getStringName(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static int queryId(String scheme, String host, int id) {
        try {
            Cursor query = context.getContentResolver().query(parse(scheme, host), null
                    , getStringName(), null, null);
            if (query != null) {
                if (query.moveToNext()) {
                    id = query.getInt(0);
                }
                query.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m3094a(String str, String str2, Integer num) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str2, num.toString());
        try {
            context.getContentResolver().update(parse(str, str2), contentValues, getStringName(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static long m3097a(String str, String str2, long j) {
        try {
            Cursor query = context.getContentResolver().query(parse(str, str2), null, getStringName(), null, null);
            if (query != null) {
                if (query.moveToNext()) {
                    j = query.getLong(0);
                }
                query.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m3093a(String str, String str2, Long l) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str2, l.toString());
        try {
            context.getContentResolver().update(parse(str, str2), contentValues, getStringName(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m3095a(String str, String str2, Float f) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str2, f.toString());
        try {
            context.getContentResolver().update(parse(str, str2), contentValues, getStringName(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static float m3099a(String str, String str2, float f) {
        try {
            Cursor query = context.getContentResolver().query(parse(str, str2), null, getStringName(), null, null);
            if (query != null) {
                if (query.moveToNext()) {
                    f = query.getFloat(0);
                }
                query.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static Set<String> m3091a(String str, String str2, Set<String> set) {
        Exception e;
        try {
            Cursor query = context.getContentResolver().query(parse(str, str2), null, getSetName(), null, null);
            if (query != null) {
                if (query.moveToNext()) {
                    int columnCount = query.getColumnCount();
                    HashSet hashSet = new HashSet(columnCount);
                    for (int i = 0; i < columnCount; i++) {
                        try {
                            hashSet.add(query.getString(i));
                        } catch (Exception e1) {
                            e = e1;
                            set = hashSet;
                            e.printStackTrace();
                            return set;
                        }
                    }
                    set = hashSet;
                }
                query.close();
            }
        } catch (Exception e2) {
            e = e2;
        }
        return set;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static void m3085b(String str, String str2, Set<String> set) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str2, m3090a(set));
        try {
            context.getContentResolver().update(parse(str, str2), contentValues, getSetName(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    static String m3090a(Set<String> set) {
        StringBuilder sb = new StringBuilder();
        for (String str : set) {
            sb.append(str).append("=_=");
        }
        String sb2 = sb.toString();
        if (sb2.endsWith("=_=")) {
            return sb2.substring(0, sb2.length() - "=_=".length());
        }
        return sb2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static Set<String> m3101a(String str) {
        HashSet hashSet = new HashSet();
        if (str != null && !StringUtils.isEmpty(str)) {
            for (String str2 : str.split("=_=")) {
                hashSet.add(str2);
            }
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static synchronized void m3102a(Context context, PreferencesID preferencesID, Preferences.InterfaceC2031a interfaceC2031a) {
        synchronized (AccessHelper.class) {
            if (f6986a == null) {
                f6986a = new C2030a();
                context.registerReceiver(f6986a, C2030a.m3082b());
            }
            f6986a.m3083a(preferencesID, interfaceC2031a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static synchronized void m3088b(Context context, PreferencesID preferencesID, Preferences.InterfaceC2031a interfaceC2031a) {
        synchronized (AccessHelper.class) {
            if (f6986a != null) {
                f6986a.m3081b(preferencesID, interfaceC2031a);
                if (f6986a.m3084a()) {
                    try {
                        context.unregisterReceiver(f6986a);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    f6986a = null;
                }
            }
        }
    }
}
