package com.sds.android.ttpod.cmmusic.p081e;

import com.sds.android.sdk.lib.util.EnvironmentUtils;

/* renamed from: com.sds.android.ttpod.cmmusic.e.c */
/* loaded from: classes.dex */
public class CMMusicUtils {
    /* renamed from: a */
    public static boolean m7276a() {
        String m8481b = EnvironmentUtils.C0604c.getSubscriberId();
        return m8481b.startsWith("46000") || m8481b.startsWith("46002") || m8481b.startsWith("46007");
    }
}
