package com.sds.android.cloudapi.ttpod.api;

import com.sds.android.cloudapi.ttpod.result.FindSongHandpickResult;
import com.sds.android.cloudapi.ttpod.result.FindSongHotListResultNew;
import com.sds.android.cloudapi.ttpod.result.FindSongHotModuleResult;
import com.sds.android.cloudapi.ttpod.result.FindSongModuleResult;
import com.sds.android.cloudapi.ttpod.result.HotSongOnlineMediaItemsResultNew;
import com.sds.android.cloudapi.ttpod.result.OperationZoneResult;
import com.sds.android.cloudapi.ttpod.result.SingerListResult;

import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import java.util.Collection;
import java.util.Map;

/* renamed from: com.sds.android.cloudapi.ttpod.a.i */
/* loaded from: classes.dex */
public class FindSongHotModuleAPI {

    /* renamed from: a */
    private static final String f2266a = UrlList.getHost();

    /* renamed from: a */
    public static Request<FindSongModuleResult> m8899a(long j) {
        return new GetMethodRequest(FindSongModuleResult.class, UrlList.recommend + "/recomm_modules").putParams("version", Long.valueOf(j)).putParams("s", EnvironmentUtils.UUIDConfig.m8494b()).putParams("v", EnvironmentUtils.UUIDConfig.m8491c()).putParams("f", "f" + EnvironmentUtils.AppConfig.getChannelType()).putParams("rom", EnvironmentUtils.UUIDConfig.m8488e().get("rom")).putParams("userId", EnvironmentUtils.UUIDConfig.m8488e().get("tid")).putParams("deviceId", EnvironmentUtils.UUIDConfig.m8488e().get("uid"));
    }

    /* renamed from: a */
    public static Request<FindSongHotModuleResult> m8900a() {
        return new GetMethodRequest(FindSongHotModuleResult.class, "http://star.dongting.com", "recommend/modulenew").putParams("v", EnvironmentUtils.UUIDConfig.m8491c());
    }

    /* renamed from: a */
    public static Request<HotSongOnlineMediaItemsResultNew> m8898a(String str, int i, int i2) {
        return new GetMethodRequest(HotSongOnlineMediaItemsResultNew.class, str).putParams("page", Integer.valueOf(i)).putParams("size", Integer.valueOf(i2));
    }

    /* renamed from: b */
    public static Request<FindSongHotListResultNew> m8895b(String str, int i, int i2) {
        return new GetMethodRequest(FindSongHotListResultNew.class, str).putParams("page", Integer.valueOf(i)).putParams("size", Integer.valueOf(i2));
    }

    /* renamed from: a */
    public static Request<SingerListResult> m8897a(String str, int i, int i2, String str2, Collection collection) {
        return new GetMethodRequest(SingerListResult.class, str).putParams("v", EnvironmentUtils.UUIDConfig.m8491c()).putParams("singer_name", str2).putParams("singer_id", collection).putParams("page", Integer.valueOf(i)).putParams("size", Integer.valueOf(i2));
    }

    /* renamed from: b */
    public static Request<FindSongHandpickResult> m8896b() {
        return new GetMethodRequest(FindSongHandpickResult.class, "http://star.dongting.com", "recommend/selection");
    }

    /* renamed from: c */
    public static Request<OperationZoneResult> m8894c() {
        return new GetMethodRequest(OperationZoneResult.class, f2266a, "module/operationzone").m8540a((Map<String, Object>) EnvironmentUtils.UUIDConfig.m8488e());
    }
}
