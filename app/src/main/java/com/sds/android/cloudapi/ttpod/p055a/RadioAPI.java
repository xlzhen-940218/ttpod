package com.sds.android.cloudapi.ttpod.p055a;

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
        return new GetMethodRequest(RadioChannelListResult.class, "http://fm.api.ttpod.com").m8542a("channellist").m8537b("image_type", "240_200");
    }

    /* renamed from: a */
    public static Request<OnlineMediaItemsResult> m8836a(int i, int i2) {
        return new GetMethodRequest(OnlineMediaItemsResult.class, "http://fm.api.ttpod.com").m8542a("channelsong").m8537b("tagid", Integer.valueOf(i)).m8537b("page", Integer.valueOf(i2)).m8537b("size", "50");
    }

    /* renamed from: b */
    public static Request<RadioCategoryListResult> m8834b() {
        return new GetMethodRequest(RadioCategoryListResult.class, "http://fm.api.ttpod.com").m8542a("radiolist").m8537b("image_type", "240_200");
    }

    /* renamed from: a */
    public static Request<OnlineMediaItemsResult> m8835a(String str, int i) {
        return new GetMethodRequest(OnlineMediaItemsResult.class, "http://fm.api.ttpod.com").m8542a("radiosong").m8537b("userid", str).m8537b("tagid", Integer.valueOf(i)).m8537b("num", "20");
    }
}
