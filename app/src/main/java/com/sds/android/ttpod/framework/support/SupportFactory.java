package com.sds.android.ttpod.framework.support;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import com.sds.android.sdk.lib.util.StringUtils;
import java.util.List;

/* renamed from: com.sds.android.ttpod.framework.support.e */
/* loaded from: classes.dex */
public class SupportFactory {

    /* renamed from: a */
    private static Support instance;

    /* renamed from: a */
    public static synchronized Support getInstance(Context context) {
        Support support;
        synchronized (SupportFactory.class) {
            if (instance != null) {
                support = instance;
            } else {
                if (StringUtils.equals("com.sds.android.ttpod.main", getContextProcessName(context))) {
                    instance = new FastSwitchSupport(context);
                } else {
                    instance = new Support(context);
                }
                support = instance;
            }
        }
        return support;
    }

    /* renamed from: b */
    private static String getContextProcessName(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses
                = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (Process.myPid() == runningAppProcessInfo.pid) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        return null;
    }
}
