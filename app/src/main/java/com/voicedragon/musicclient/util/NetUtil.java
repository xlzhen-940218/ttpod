package com.voicedragon.musicclient.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* loaded from: classes.dex */
public class NetUtil {
    public static boolean isNetworkEnable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        return nInfo != null && nInfo.isAvailable();
    }

    public static int getNetworkType(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        if (nInfo != null) {
            if (nInfo.getType() == 1) {
                return 3000;
            }
            if (nInfo.getSubtype() == 1 || nInfo.getSubtype() == 4 || nInfo.getSubtype() == 2) {
                return 3001;
            }
            return 3002;
        }
        return 0;
    }
}
