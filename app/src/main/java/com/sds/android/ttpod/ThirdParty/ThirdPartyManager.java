package com.sds.android.ttpod.ThirdParty;

import android.content.Context;
import com.sds.android.ttpod.ThirdParty.p066a.InstanceUtils;
import com.sds.android.ttpod.ThirdParty.update.DownloadProgressListener;
import com.sds.android.ttpod.ThirdParty.update.SmartUpdate;
import com.sds.android.ttpod.ThirdParty.update.UpdateCallback;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateData;

/* renamed from: com.sds.android.ttpod.ThirdParty.d */
/* loaded from: classes.dex */
public class ThirdPartyManager {
    /* renamed from: a */
    public static boolean m8316a(Context context, VersionUpdateData versionUpdateData, UpdateCallback updateCallback) {
        return SmartUpdate.check(context, versionUpdateData, updateCallback);
    }

    /* renamed from: a */
    public static void m8314a(boolean z, UpdateCallback updateCallback) {
        SmartUpdate.update(z, updateCallback);
    }

    /* renamed from: a */
    public static void m8315a(DownloadProgressListener downloadProgressListener) {
        SmartUpdate.setDownloadProgressListener(downloadProgressListener);
    }

    /* renamed from: a */
    public static void m8318a() {
        SmartUpdate.cancelUpdate();
    }

    /* renamed from: b */
    public static void m8313b() {
        SmartUpdate.onDestroy();
    }

    /* renamed from: c */
    public static void m8312c() {
        SmartUpdate.onResume();
    }

    /* renamed from: a */
    public static void m8317a(Context context, App360Callback app360Callback) {
        App360Interface app360Interface = (App360Interface) InstanceUtils.m8328a("com.sds.android.ttpod.ThirdParty.liangdian.App360Manager");
        if (app360Interface != null) {
            app360Interface.m8327a(context, app360Callback);
        }
    }
}
