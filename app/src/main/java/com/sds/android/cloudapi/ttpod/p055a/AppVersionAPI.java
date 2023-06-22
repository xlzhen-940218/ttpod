package com.sds.android.cloudapi.ttpod.p055a;

import com.sds.android.cloudapi.ttpod.result.AppVersionResult;
import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.activities.search.OnlineSearchEntryActivity;

/* renamed from: com.sds.android.cloudapi.ttpod.a.a */
/* loaded from: classes.dex */
public class AppVersionAPI {
    /* renamed from: a */
    public Request<AppVersionResult> m8964a(String str, String str2, String str3, boolean z) {
        return m8963a(str, str2, str3, z, null);
    }

    /* renamed from: a */
    public Request<AppVersionResult> m8963a(String str, String str2, String str3, boolean z, String str4) {
        Request<AppVersionResult> m8537b = new GetMethodRequest(AppVersionResult.class, "http://v1.ard.update.itlily.com/version", "check").m8537b("st", "other_update").m8537b("vip", Integer.valueOf(z ? 1 : 0)).m8537b("s", str2).m8537b("v", str).m8537b("f", str3);
        if (!StringUtils.m8346a(str4)) {
            m8537b.m8537b(OnlineSearchEntryActivity.KEY_THIRD_APP_IDENTITY, str4);
        }
        return m8537b;
    }
}
