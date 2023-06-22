package com.sds.android.ttpod.framework.p106a;

import com.sds.android.sdk.lib.util.SDKVersionUtils;

/* renamed from: com.sds.android.ttpod.framework.a.o */
/* loaded from: classes.dex */
public class PlatformUtils {

    /* renamed from: a */
    private static boolean f5686a;

    static {
        f5686a = false;
        if (!SDKVersionUtils.m8368f()) {
            f5686a = true;
        }
    }

    /* renamed from: a */
    public static boolean m4656a() {
        return !f5686a;
    }
}
