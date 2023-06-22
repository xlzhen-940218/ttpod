package com.voicedragon.musicclient.util;

import android.content.Context;
import android.telephony.TelephonyManager;

/* loaded from: classes.dex */
public class PhoneUtil {
    public static String getUserid(Context c) {
        try {
            TelephonyManager tm = (TelephonyManager) c.getSystemService("phone");
            return tm.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
