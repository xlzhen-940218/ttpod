package com.sds.android.cloudapi.ttpod.p055a;

import com.sds.android.cloudapi.ttpod.result.NewFollowersListResult;
import com.sds.android.cloudapi.ttpod.result.NewNoticeCountResult;
import com.sds.android.cloudapi.ttpod.result.NoticeListResult;
import com.sds.android.cloudapi.ttpod.result.SystemNoticeListResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.Request;


/* renamed from: com.sds.android.cloudapi.ttpod.a.p */
/* loaded from: classes.dex */
public class NoticeAPI {
    /* renamed from: a */
    public static Request<NewNoticeCountResult> m8874a(String str) {
        return new GetMethodRequest(NewNoticeCountResult.class, "http://v1.ard.q.itlily.com/notice", "get_notice_tips").m8537b("access_token", str);
    }

    /* renamed from: a */
    public static Request<BaseResult> m8873a(String str, int i) {
        return new GetMethodRequest(BaseResult.class, "http://v1.ard.q.itlily.com/notice", "clear_unread").m8537b("access_token", str).m8537b("type", Integer.valueOf(i));
    }

    /* renamed from: a */
    public static Request<BaseResult> m8870a(String str, String str2) {
        return new GetMethodRequest(BaseResult.class, "http://v1.ard.q.itlily.com/notice", "del_notice").m8537b("access_token", str).m8537b("notice_id", str2);
    }

    /* renamed from: a */
    public static Request<NoticeListResult> m8872a(String str, int i, int i2, int i3) {
        return new GetMethodRequest(NoticeListResult.class, "http://v1.ard.q.itlily.com/notice", "get_notice").m8537b("access_token", str).m8537b("type", Integer.valueOf(i)).m8537b("offset", Integer.valueOf(i2)).m8537b("size", Integer.valueOf(i3));
    }

    /* renamed from: b */
    public static Request<NewFollowersListResult> m8869b(String str) {
        return new GetMethodRequest(NewFollowersListResult.class, "http://v1.ard.q.itlily.com/notice", "get_new_followers").m8537b("access_token", str);
    }

    /* renamed from: a */
    public static Request<SystemNoticeListResult> m8871a(String str, long j, int i) {
        Request<SystemNoticeListResult> m8537b = new GetMethodRequest(SystemNoticeListResult.class, "http://v1.ard.q.itlily.com/notice", "get_system_msg").m8537b("access_token", str).m8537b("size", Integer.valueOf(i));
        if (j > 0) {
            m8537b.m8537b("timestamp", Long.valueOf(j));
        }
        return m8537b;
    }
}
