package com.sds.android.cloudapi.ttpod.api;

import com.sds.android.cloudapi.ttpod.result.MessageCollectListResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.sdk.lib.util.JSONUtils;


import java.util.List;

/* renamed from: com.sds.android.cloudapi.ttpod.a.n */
/* loaded from: classes.dex */
public class MessageCollectAPI {
    /* renamed from: a */
    public static Request<BaseResult> m8882a(String str, List<Long> list) {
        return new GetMethodRequest(BaseResult.class, "http://v1.ard.q.itlily.com/collect", "add_msg").putParams("access_token", str).putParams("msg_ids", JSONUtils.toJson(list));
    }

    /* renamed from: b */
    public static Request<BaseResult> m8881b(String str, List<Long> list) {
        return new GetMethodRequest(BaseResult.class, "http://v1.ard.q.itlily.com/collect", "cancel_msg").putParams("access_token", str).putParams("msg_ids", JSONUtils.toJson(list));
    }

    /* renamed from: a */
    public static Request<MessageCollectListResult> m8883a(String str) {
        return new GetMethodRequest(MessageCollectListResult.class, "http://v1.ard.q.itlily.com/collect", "get_msg_ids").putParams("access_token", str);
    }
}
