package com.sds.android.ttpod.activities.ktv;

import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.Request;
import java.util.Map;

/* renamed from: com.sds.android.ttpod.activities.ktv.b */
/* loaded from: classes.dex */
public class KtvApi {
    /* renamed from: a */
    public static Request<KtvResult> m8124a(String str) {
        return new GetMethodRequest(KtvResult.class, str, null);
    }

    /* renamed from: a */
    public static Request<KtvResult> m8123a(String str, Map<String, Object> map) {
        return new GetMethodRequest(KtvResult.class, str, "bind").m8540a(map);
    }
}
