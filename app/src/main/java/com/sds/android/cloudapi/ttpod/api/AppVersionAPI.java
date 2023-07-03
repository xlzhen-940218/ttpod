package com.sds.android.cloudapi.ttpod.api;

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
        Request<AppVersionResult> m8537b = new GetMethodRequest(AppVersionResult.class
                , "http://v1.ard.update.itlily.com/version", "check")
                .putParams("st", "other_update")
                .putParams("vip", Integer.valueOf(z ? 1 : 0))
                .putParams("s", str2)
                .putParams("v", str).putParams("f", str3);
        if (!StringUtils.isEmpty(str4)) {
            m8537b.putParams("app", str4);
        }
        return m8537b;
    }
}
