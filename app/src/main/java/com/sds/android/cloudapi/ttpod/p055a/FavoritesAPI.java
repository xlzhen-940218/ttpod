package com.sds.android.cloudapi.ttpod.p055a;

import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemIdListResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import java.util.Collection;
import java.util.Map;

/* renamed from: com.sds.android.cloudapi.ttpod.a.f */
/* loaded from: classes.dex */
public class FavoritesAPI {
    /* renamed from: a */
    public static Request<BaseResult> m8922a(String str, Collection<Long> collection) {
        return new GetMethodRequest(BaseResult.class, "http://v1.ard.h.itlily.com/favorites", "create").m8542a(str).m8542a(collection).m8540a((Map<String, Object>) EnvironmentUtils.C0603b.m8488e());
    }

    /* renamed from: b */
    public static Request<BaseResult> m8921b(String str, Collection<Long> collection) {
        return new GetMethodRequest(BaseResult.class, "http://v1.ard.h.itlily.com/favorites", "destroy").m8542a(str).m8542a(collection).m8540a((Map<String, Object>) EnvironmentUtils.C0603b.m8488e());
    }

    /* renamed from: a */
    public static Request<OnlineMediaItemIdListResult> m8923a(long j) {
        return new GetMethodRequest(OnlineMediaItemIdListResult.class, "http://v1.ard.y.itlily.com/favorites", "song_ids/by_user").m8542a(Long.valueOf(j));
    }
}
