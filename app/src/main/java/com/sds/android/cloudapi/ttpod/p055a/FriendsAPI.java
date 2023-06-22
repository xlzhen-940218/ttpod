package com.sds.android.cloudapi.ttpod.p055a;

import com.sds.android.cloudapi.ttpod.result.UserListResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.IdListResult;
import com.sds.android.sdk.lib.request.Request;
import java.util.Collection;

/* renamed from: com.sds.android.cloudapi.ttpod.a.j */
/* loaded from: classes.dex */
public class FriendsAPI {
    /* renamed from: a */
    public static Request<BaseResult> m8891a(String str, long j) {
        return new GetMethodRequest(BaseResult.class, "http://v1.ard.y.itlily.com/friends", "add").m8542a(str).m8542a(Long.valueOf(j));
    }

    /* renamed from: b */
    public static Request<BaseResult> m8888b(String str, long j) {
        return new GetMethodRequest(BaseResult.class, "http://v1.ard.y.itlily.com/friends", "del").m8542a(str).m8542a(Long.valueOf(j));
    }

    /* renamed from: a */
    public static Request<IdListResult> m8893a(long j) {
        return new GetMethodRequest(IdListResult.class, "http://v1.ard.y.itlily.com/friends", "followings/by_ids").m8542a(Long.valueOf(j));
    }

    /* renamed from: a */
    public static Request<UserListResult> m8892a(long j, int i, int i2, long j2) {
        return new GetMethodRequest(UserListResult.class, "http://v1.ard.y.itlily.com/friends", "followings/tuid").m8542a(Long.valueOf(j)).m8542a(Integer.valueOf(i)).m8542a(Integer.valueOf(i2)).m8542a(Long.valueOf(j2));
    }

    /* renamed from: b */
    public static Request<UserListResult> m8889b(long j, int i, int i2, long j2) {
        return new GetMethodRequest(UserListResult.class, "http://v1.ard.y.itlily.com/friends", "followers/tuid").m8542a(Long.valueOf(j)).m8542a(Integer.valueOf(i)).m8542a(Integer.valueOf(i2)).m8542a(Long.valueOf(j2));
    }

    /* renamed from: a */
    public static Request<UserListResult> m8890a(Collection<Long> collection) {
        return new GetMethodRequest(UserListResult.class, "http://v1.ard.y.itlily.com/friends", "info").m8542a(collection);
    }
}
