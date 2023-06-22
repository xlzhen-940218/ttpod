package com.sds.android.ttpod.framework.p106a.p107a;

import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.activities.search.OnlineSearchEntryActivity;
import com.sds.android.ttpod.framework.storage.environment.Preferences;

/* renamed from: com.sds.android.ttpod.framework.a.a.b */
/* loaded from: classes.dex */
public class AppRuntimeStatistic {

    /* compiled from: AppRuntimeStatistic.java */
    /* renamed from: com.sds.android.ttpod.framework.a.a.b$a */
    /* loaded from: classes.dex */
    public enum EnumC1767a {
        NONE_STATE,
        STARTUP_STATE,
        EXIT_STATE
    }

    /* renamed from: a */
    public static void m5273a() {
        switch (EnumC1767a.values()[Preferences.m2925bd()]) {
            case EXIT_STATE:
                LogUtils.m8388a("AppRuntimeStatistic", "sendLastRunningTime = " + ((Preferences.m2923bf() - Preferences.m2924be()) / 1000));
                StatisticUtils.m4909a(OnlineSearchEntryActivity.KEY_THIRD_APP_IDENTITY, "time", OnlineSearchEntryActivity.KEY_THIRD_APP_IDENTITY, (Preferences.m2923bf() - Preferences.m2924be()) / 1000);
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public static void m5272a(EnumC1767a enumC1767a) {
        LogUtils.m8381c("AppRuntimeStatistic", enumC1767a.name());
        switch (enumC1767a) {
            case EXIT_STATE:
                Preferences.m2902c(System.currentTimeMillis());
                break;
            case STARTUP_STATE:
                Preferences.m2941b(System.currentTimeMillis());
                break;
        }
        Preferences.m2825u(enumC1767a.ordinal());
    }
}
