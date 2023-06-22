package com.sds.android.ttpod.framework.p106a.p107a;

import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;

import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.framework.a.a.f */
/* loaded from: classes.dex */
public class ListStatistic {

    /* renamed from: a */
    private static int f5569a = -1;

    /* renamed from: b */
    private static String f5570b = "";

    /* renamed from: c */
    private static String f5571c = "";

    /* renamed from: a */
    public static int m5212a() {
        return f5569a;
    }

    /* renamed from: a */
    public static void m5211a(int i) {
        f5569a = i;
    }

    /* renamed from: b */
    public static String m5203b() {
        return f5570b;
    }

    /* renamed from: a */
    public static void m5205a(String str) {
        f5570b = str;
    }

    /* renamed from: c */
    public static String m5201c() {
        return f5571c;
    }

    /* renamed from: b */
    public static void m5202b(String str) {
        f5571c = str;
    }

    /* renamed from: a */
    public static void m5207a(int i, String str, long j, long j2, int i2, String str2) {
        m5210a(i, str, j2 == j ? 1 : 0, j2, i2, str2);
        LogUtils.m8388a("ListStatics", "listType:" + i + " listId:" + str + " startPlaySongId:" + j + " songId:" + j2 + " uuid:" + str2);
    }

    /* renamed from: a */
    public static void m5208a(int i, String str, long j, int i2) {
        m5210a(i, str, 2, j, i2, f5571c);
    }

    /* renamed from: a */
    public static void m5206a(long j, int i, boolean z) {
        if (z) {
            m5210a(f5569a, f5570b, 3, j, i, f5571c);
        }
    }

    /* renamed from: a */
    private static void m5210a(int i, String str, int i2, long j, int i3, String str2) {
        String m2945az = Preferences.m2945az();
        if (i == -1 || StringUtils.m8346a(str) || StringUtils.m8346a(m2945az) || j < 0 || i3 < 0 || StringUtils.m8346a(str2)) {
            LogUtils.m8388a("ListStatistic", "error listType=" + i + ",listId=" + str + ",clientId=" + m2945az + ",off=" + i3 + ",uuid=" + str2);
        } else {
            m5209a(i, str, i2, j, i3, m2945az, EnvironmentUtils.C0604c.m8476d(), str2);
        }
    }

    /* renamed from: a */
    private static void m5209a(int i, String str, int i2, long j, int i3, String str2, int i4, String str3) {
        if (i3 > 0) {
            HashMap hashMap = new HashMap();
            hashMap.put("l", Integer.valueOf(i));
            hashMap.put("lid", str);
            hashMap.put("a", Integer.valueOf(i2));
            hashMap.put("sid", Long.valueOf(j));
            hashMap.put("off", Integer.valueOf(i3));
            hashMap.put("client_id", str2);
            hashMap.put("net", Integer.valueOf(i4));
            hashMap.put("uuid", str3);
            hashMap.put("f", EnvironmentUtils.C0603b.m8489d());
            m5204a(hashMap);
        }
    }

    /* renamed from: a */
    private static void m5204a(final HashMap<String, Object> hashMap) {
        TaskScheduler.m8581a(new Runnable() { // from class: com.sds.android.ttpod.framework.a.a.f.1
            @Override // java.lang.Runnable
            public void run() {
                LogUtils.m8388a("ListStatistic", hashMap.toString());
                HttpRequest.m8713a("http://tj.itlily.com/ol", (HashMap<String, Object>) null, hashMap);
            }
        });
    }
}
