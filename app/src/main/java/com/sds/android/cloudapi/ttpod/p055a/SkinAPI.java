package com.sds.android.cloudapi.ttpod.p055a;

import com.sds.android.cloudapi.ttpod.result.BackgroundCheckResult;
import com.sds.android.cloudapi.ttpod.result.BackgroundMoreCheckResult;
import com.sds.android.cloudapi.ttpod.result.OnlinePagedSkinListResult;
import com.sds.android.cloudapi.ttpod.result.SkinListCheckResult;

import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.Request;

/* renamed from: com.sds.android.cloudapi.ttpod.a.x */
/* loaded from: classes.dex */
public class SkinAPI {
    /* renamed from: a */
    public static Request<SkinListCheckResult> m8830a() {
        return new GetMethodRequest(SkinListCheckResult.class, "http://api.skin.ttpod.com/skin/recommend_skin/timestamp.json");
    }

    /* renamed from: b */
    public static Request<SkinListCheckResult> m8828b() {
        return new GetMethodRequest(SkinListCheckResult.class, "http://api.skin.ttpod.com/skin/hot_skin/timestamp.json");
    }

    /* renamed from: c */
    public static Request<BackgroundCheckResult> m8826c() {
        return new GetMethodRequest(BackgroundCheckResult.class, "http://api.skin.ttpod.com/skin/recommend_background/timestamp.json");
    }

    /* renamed from: a */
    public static Request<OnlinePagedSkinListResult> m8829a(int i, int i2, int i3) {
        return new GetMethodRequest(OnlinePagedSkinListResult.class, "http://api.skin.ttpod.com/skin", "apiSkinType/info").m8537b("_id", Integer.valueOf(i)).m8537b("page", Integer.valueOf(i2)).m8537b("size", Integer.valueOf(i3));
    }

    /* renamed from: d */
    public static Request<BackgroundMoreCheckResult> m8825d() {
        return new GetMethodRequest(BackgroundMoreCheckResult.class, "http://api.skin.ttpod.com/skin/apiMore/info");
    }

    /* renamed from: b */
    public static Request<OnlinePagedSkinListResult> m8827b(int i, int i2, int i3) {
        return new GetMethodRequest(OnlinePagedSkinListResult.class, "http://log.topit.me/ttpod/apiSkinTypeInfo.php").m8537b("_id", Integer.valueOf(i)).m8537b("page", Integer.valueOf(i2)).m8537b("size", Integer.valueOf(i3));
    }
}
