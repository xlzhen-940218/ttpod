package com.sds.android.sdk.lib.util;

import android.content.Context;
import android.net.wifi.WifiManager;

/* renamed from: com.sds.android.sdk.lib.util.g */
/* loaded from: classes.dex */
public class NetUtils {
    /* renamed from: a */
    public static String m8376a(Context context) {
        int ipAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getIpAddress();
        if (ipAddress == 0) {
            return null;
        }
        return (ipAddress & 255) + "." + ((ipAddress >> 8) & 255) + "." + ((ipAddress >> 16) & 255) + "." + ((ipAddress >> 24) & 255);
    }
}
