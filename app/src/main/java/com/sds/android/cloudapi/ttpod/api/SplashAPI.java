package com.sds.android.cloudapi.ttpod.api;

import com.sds.android.cloudapi.ttpod.result.SplashDataResult;
import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.sdk.lib.util.EnvironmentUtils;

/* renamed from: com.sds.android.cloudapi.ttpod.a.y */
/* loaded from: classes.dex */
public final class SplashAPI {

    /* renamed from: a */
    private static final String f2268a = UrlList.getHost() + "/splash/splashes";

    /* renamed from: a */
    public static Request<SplashDataResult> m8824a(int i) {
        return new GetMethodRequest(SplashDataResult.class, f2268a).putParams("version", Integer.valueOf(i)).putParams("s", EnvironmentUtils.UUIDConfig.m8494b()).putParams("f", EnvironmentUtils.UUIDConfig.m8489d()).putParams("v", EnvironmentUtils.UUIDConfig.m8491c());
    }
}
