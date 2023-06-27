package com.sds.android.cloudapi.ttpod.api;

import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.cloudapi.ttpod.result.RadioCategoryListResult;
import com.sds.android.cloudapi.ttpod.result.RadioChannelListResult;

import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.Request;

/* renamed from: com.sds.android.cloudapi.ttpod.a.u */
/* loaded from: classes.dex */
public final class RadioAPI {
    /* renamed from: a */
    public static Request<RadioChannelListResult> m8837a() {
        return new GetMethodRequest(RadioChannelListResult.class, "http://fm.api.ttpod.com").m8542a("channellist").putParams("image_type", "240_200");
    }

    /* renamed from: a */
    public static Request<OnlineMediaItemsResult> m8836a(int i, int i2) {
        return new GetMethodRequest(OnlineMediaItemsResult.class, "http://fm.api.ttpod.com").m8542a("channelsong").putParams("tagid", Integer.valueOf(i)).putParams("page", Integer.valueOf(i2)).putParams("size", "50");
    }

    /* renamed from: b */
    public static Request<RadioCategoryListResult> m8834b() {
        return new GetMethodRequest(RadioCategoryListResult.class, "http://fm.api.ttpod.com").m8542a("radiolist").putParams("image_type", "240_200");
    }

    /* renamed from: a */
    public static Request<OnlineMediaItemsResult> m8835a(String str, int i) {
        return new GetMethodRequest(OnlineMediaItemsResult.class, "http://fm.api.ttpod.com").m8542a("radiosong").putParams("userid", str).putParams("tagid", Integer.valueOf(i)).putParams("num", "20");
    }
}
