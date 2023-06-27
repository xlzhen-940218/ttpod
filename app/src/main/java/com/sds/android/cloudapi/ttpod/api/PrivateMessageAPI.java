package com.sds.android.cloudapi.ttpod.api;

import com.sds.android.cloudapi.ttpod.result.PrivateMessageContentListResult;
import com.sds.android.cloudapi.ttpod.result.PrivateMessageOverViewListResult;

import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.PostMethodRequestV2;
import com.sds.android.sdk.lib.request.Request;


/* renamed from: com.sds.android.cloudapi.ttpod.a.t */
/* loaded from: classes.dex */
public class PrivateMessageAPI {
    /* renamed from: a */
    public static Request<PrivateMessageOverViewListResult> m8841a(String str, long j, int i) {
        Request<PrivateMessageOverViewListResult> m8537b = new GetMethodRequest(PrivateMessageOverViewListResult.class, "http://v1.ard.q.itlily.com/privatemsg", "get_list").m8543a(PrivateMessageAPI.class).putParams("access_token", str).putParams("size", Integer.valueOf(i));
        if (j > 0) {
            m8537b.putParams("last_modified", Long.valueOf(j));
        }
        return m8537b;
    }

    /* renamed from: a */
    public static Request<PrivateMessageContentListResult> m8840a(String str, long j, long j2, int i) {
        Request<PrivateMessageContentListResult> m8537b = new GetMethodRequest(PrivateMessageContentListResult.class, "http://v1.ard.q.itlily.com/privatemsg", "get_pm").m8543a(PrivateMessageAPI.class).putParams("access_token", str).putParams("to", Long.valueOf(j)).putParams("size", Integer.valueOf(i));
        if (j2 > 0) {
            m8537b.putParams("last_modified", Long.valueOf(j2));
        }
        return m8537b;
    }

    /* renamed from: a */
    public static Request<BaseResult> m8839a(String str, long j, String str2) {
        return new PostMethodRequestV2(BaseResult.class, "http://v1.ard.q.itlily.com/privatemsg", "send").putParams("access_token", str).putParams("to", Long.valueOf(j)).putParams("message", str2);
    }

    /* renamed from: a */
    public static Request<BaseResult> m8838a(String str, String str2) {
        return new PostMethodRequestV2(BaseResult.class, "http://v1.ard.q.itlily.com/privatemsg", "delete_pm").putParams("access_token", str).putParams("pm_id", str2);
    }

    /* renamed from: a */
    public static Request<BaseResult> m8842a(String str, long j) {
        return new PostMethodRequestV2(BaseResult.class, "http://v1.ard.q.itlily.com/privatemsg", "delete_list").putParams("access_token", str).putParams("to", Long.valueOf(j));
    }
}
