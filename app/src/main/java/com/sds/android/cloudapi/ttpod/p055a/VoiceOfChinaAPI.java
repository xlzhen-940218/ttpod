package com.sds.android.cloudapi.ttpod.p055a;

import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.cloudapi.ttpod.result.VoiceOfChinaListResult;

import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.Request;

/* renamed from: com.sds.android.cloudapi.ttpod.a.ac */
/* loaded from: classes.dex */
public class VoiceOfChinaAPI {
    /* renamed from: a */
    public static Request<VoiceOfChinaListResult> m8937a(int i, int i2, int i3) {
        return new GetMethodRequest(VoiceOfChinaListResult.class, "http://v1.ard.tj.itlily.com/ttpod").m8537b("id", Integer.valueOf(i)).m8537b("page", Integer.valueOf(i2)).m8537b("size", Integer.valueOf(i3));
    }

    /* renamed from: b */
    public static Request<OnlineMediaItemsResult> m8936b(int i, int i2, int i3) {
        return new GetMethodRequest(OnlineMediaItemsResult.class, "http://v1.ard.tj.itlily.com/ttpod").m8537b("id", Integer.valueOf(i)).m8537b("page", Integer.valueOf(i2)).m8537b("size", Integer.valueOf(i3));
    }
}
