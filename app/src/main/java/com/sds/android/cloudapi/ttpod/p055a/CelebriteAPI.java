package com.sds.android.cloudapi.ttpod.p055a;

import com.sds.android.cloudapi.ttpod.result.LabeledTTPodUserListResult;
import com.sds.android.cloudapi.ttpod.result.StarCategoryResult;
import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.Request;

/* renamed from: com.sds.android.cloudapi.ttpod.a.d */
/* loaded from: classes.dex */
public class CelebriteAPI {
    /* renamed from: a */
    public static Request<StarCategoryResult> m8933a() {
        return new GetMethodRequest(StarCategoryResult.class, "http://v2.ttus.ttpod.com", "categories");
    }

    /* renamed from: a */
    public static Request<LabeledTTPodUserListResult> m8932a(int i, int i2, int i3) {
        return new GetMethodRequest(LabeledTTPodUserListResult.class, "http://v2.ttus.ttpod.com", "category").m8542a(Integer.valueOf(i)).m8542a(Integer.valueOf(i2)).m8542a(Integer.valueOf(i3));
    }

    /* renamed from: b */
    public static Request<LabeledTTPodUserListResult> m8931b(int i, int i2, int i3) {
        return new GetMethodRequest(LabeledTTPodUserListResult.class, "http://v2.ttus.ttpod.com", "celebrities").m8542a(Integer.valueOf(i)).m8542a(Integer.valueOf(i2)).m8542a(Integer.valueOf(i3));
    }
}
