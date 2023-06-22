package com.sds.android.ttpod.cmmusic.p078b;

import android.content.Context;
import android.telephony.SmsManager;

import com.sds.android.sdk.lib.p065e.TaskScheduler;

/* renamed from: com.sds.android.ttpod.cmmusic.b.a */
/* loaded from: classes.dex */
public class CmMusicSdkInitCode {
    /* renamed from: a */
    public static void m7343a(final Context context) {
        TaskScheduler.m8581a(new Runnable() { // from class: com.sds.android.ttpod.cmmusic.b.a.1
            @Override // java.lang.Runnable
            public void run() {
                //SmsManager.getDefault().sendTextMessage("1065843601", null, "CMO_S=" + GetAppInfoInterface.m10412b(context) + "@007317470438422765@S2.1@null", null, null);
            }
        });
    }
}
