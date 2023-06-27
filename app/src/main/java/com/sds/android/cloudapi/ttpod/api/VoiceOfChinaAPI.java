package com.sds.android.cloudapi.ttpod.api;

import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.cloudapi.ttpod.result.VoiceOfChinaListResult;

import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.Request;

/* renamed from: com.sds.android.cloudapi.ttpod.a.ac */
/* loaded from: classes.dex */
public class VoiceOfChinaAPI {
    /* renamed from: a */
    public static Request<VoiceOfChinaListResult> m8937a(int i, int i2, int i3) {
        return new GetMethodRequest(VoiceOfChinaListResult.class, "http://v1.ard.tj.itlily.com/ttpod").putParams("id", Integer.valueOf(i)).putParams("page", Integer.valueOf(i2)).putParams("size", Integer.valueOf(i3));
    }

    /* renamed from: b */
    public static Request<OnlineMediaItemsResult> m8936b(int i, int i2, int i3) {
        return new GetMethodRequest(OnlineMediaItemsResult.class, "http://v1.ard.tj.itlily.com/ttpod").putParams("id", Integer.valueOf(i)).putParams("page", Integer.valueOf(i2)).putParams("size", Integer.valueOf(i3));
    }
}
