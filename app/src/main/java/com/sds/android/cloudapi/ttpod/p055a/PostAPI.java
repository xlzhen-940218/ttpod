package com.sds.android.cloudapi.ttpod.p055a;

import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.cloudapi.ttpod.result.CommentListResult;
import com.sds.android.cloudapi.ttpod.result.CommentResult;
import com.sds.android.cloudapi.ttpod.result.ListenerCountResult;
import com.sds.android.cloudapi.ttpod.result.PostResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.IdListResult;
import com.sds.android.sdk.lib.request.PostMethodRequestV2;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.sdk.lib.util.StringUtils;

import java.util.Collection;

/* renamed from: com.sds.android.cloudapi.ttpod.a.s */
/* loaded from: classes.dex */
public class PostAPI {
    /* renamed from: a */
    public static Request<BaseResult> m8853a(String str) {
        return new PostMethodRequestV2(BaseResult.class, "http://v1.ard.q.itlily.com/share", "check_update").m8537b("access_token", str);
    }

    /* renamed from: b */
    public static Request<IdListResult> m8844b(String str) {
        return new PostMethodRequestV2(IdListResult.class, "http://v1.ard.q.itlily.com/share", "user_timeline_ids").m8537b("access_token", str);
    }

    /* renamed from: a */
    public static Request<CommentResult> m8849a(String str, long j, String str2, long j2, long j3) {
        Request<CommentResult> m8537b = new PostMethodRequestV2(CommentResult.class, "http://v1.ard.q.itlily.com/share", "comment").m8537b("access_token", str).m8537b("msg_id", Long.valueOf(j)).m8537b("tweet", str2).m8537b("reply_to", Long.valueOf(j2));
        if (j3 != 0) {
            m8537b.m8537b("comment_id", Long.valueOf(j3));
        }
        return m8537b;
    }

    /* renamed from: a */
    public static Request<BaseResult> m8851a(String str, long j, long j2) {
        return new PostMethodRequestV2(BaseResult.class, "http://v1.ard.q.itlily.com/share", "del_comment").m8537b("access_token", str).m8537b("msg_id", Long.valueOf(j)).m8537b("comment_id", Long.valueOf(j2));
    }

    /* renamed from: a */
    public static Request<BaseResult> m8850a(String str, long j, long j2, String str2) {
        Request<BaseResult> m8537b = new PostMethodRequestV2(BaseResult.class, "http://v1.ard.q.itlily.com/share", "repost").m8537b("access_token", str).m8537b("msg_id", Long.valueOf(j)).m8537b("reply_to", Long.valueOf(j2));
        if (!StringUtils.isEmpty(str2)) {
            m8537b.m8537b("tweet", str2);
        }
        return m8537b;
    }

    /* renamed from: a */
    public static Request<IdListResult> m8852a(String str, long j) {
        return new GetMethodRequest(IdListResult.class, "http://v1.ard.q.itlily.com/share", "get_comment_ids").m8537b("access_token", str).m8537b("msg_id", Long.valueOf(j));
    }

    /* renamed from: a */
    public static Request<CommentListResult> m8848a(String str, Collection<Long> collection) {
        return new GetMethodRequest(CommentListResult.class, "http://v1.ard.q.itlily.com/share", "get_comment").m8537b("access_token", str).m8537b("comment_ids", collection);
    }

    /* renamed from: b */
    public static Request<IdListResult> m8843b(String str, long j) {
        return new PostMethodRequestV2(IdListResult.class, "http://v1.ard.q.itlily.com/share", "user_publish_ids").m8537b("access_token", str).m8537b(User.KEY_USER_ID, Long.valueOf(j));
    }

    /* renamed from: a */
    public static Request<PostResult> m8855a(long j) {
        return new GetMethodRequest(PostResult.class, "http://v1.ard.q.itlily.com/share", "get_celebrities").m8537b("timestamp", Long.valueOf(j));
    }

    /* renamed from: a */
    public static Request<IdListResult> m8856a() {
        return new GetMethodRequest(IdListResult.class, "http://star.dongting.com/uploads", "rcommend/index.html");
    }

    /* renamed from: b */
    public static Request<IdListResult> m8846b() {
        return new GetMethodRequest(IdListResult.class, "http://star.dongting.com/uploads", "firstpub/index.html");
    }

    /* renamed from: a */
    public static Request<PostResult> m8847a(Collection<Long> collection) {
        return new GetMethodRequest(PostResult.class, "http://v1.ard.q.itlily.com/share", "user_timeline").m8543a(PostAPI.class).m8537b("msg_ids", collection);
    }

    /* renamed from: a */
    public static Request<PostResult> m8854a(long j, int i) {
        return j <= 0 ? new GetMethodRequest(PostResult.class, "http://v1.ard.q.itlily.com/share", "get_celebrities_by_cat").m8537b("cat", Integer.valueOf(i)) : new GetMethodRequest(PostResult.class, "http://v1.ard.q.itlily.com/share", "get_celebrities_by_cat").m8537b("timestamp", Long.valueOf(j)).m8537b("cat", Integer.valueOf(i));
    }

    /* renamed from: b */
    public static Request<ListenerCountResult> m8845b(long j) {
        return new GetMethodRequest(ListenerCountResult.class, "http://v1.ard.q.itlily.com/share/add_listener").m8537b("msg_id", Long.valueOf(j));
    }
}
