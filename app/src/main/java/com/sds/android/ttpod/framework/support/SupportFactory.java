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
    private static Support f7155a;

    /* renamed from: a */
    public static synchronized Support m2397a(Context context) {
        Support support;
        synchronized (SupportFactory.class) {
            if (f7155a != null) {
                support = f7155a;
            } else {
                if (StringUtils.equals("com.sds.android.ttpod.main", m2396b(context))) {
                    f7155a = new FastSwitchSupport(context);
                } else {
                    f7155a = new Support(context);
                }
                support = f7155a;
            }
        }
        return support;
    }

    /* renamed from: b */
    private static String m2396b(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
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
