package com.sds.android.ttpod.framework.p106a;

import com.sds.android.sdk.lib.util.SDKVersionUtils;

/* renamed from: com.sds.android.ttpod.framework.a.o */
/* loaded from: classes.dex */
public class PlatformUtils {

    /* renamed from: a */
    private static boolean lowSDK14;

    static {
        lowSDK14 = false;
        if (!SDKVersionUtils.sdkThan14()) {
            lowSDK14 = true;
        }
    }

    /* renamed from: a */
    public static boolean sdkThan14() {
        return !lowSDK14;
    }
}
