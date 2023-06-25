package com.sds.android.cloudapi.ttpod.p055a;

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
    private static final String f2266a = UrlList.m8962a();

    /* renamed from: a */
    public static Request<FindSongModuleResult> m8899a(long j) {
        return new GetMethodRequest(FindSongModuleResult.class, UrlList.f2260a + "/recomm_modules").m8537b("version", Long.valueOf(j)).m8537b("s", EnvironmentUtils.C0603b.m8494b()).m8537b("v", EnvironmentUtils.C0603b.m8491c()).m8537b("f", "f" + EnvironmentUtils.C0602a.m8512b()).m8537b("rom", EnvironmentUtils.C0603b.m8488e().get("rom")).m8537b("userId", EnvironmentUtils.C0603b.m8488e().get("tid")).m8537b("deviceId", EnvironmentUtils.C0603b.m8488e().get("uid"));
    }

    /* renamed from: a */
    public static Request<FindSongHotModuleResult> m8900a() {
        return new GetMethodRequest(FindSongHotModuleResult.class, "http://star.dongting.com", "recommend/modulenew").m8537b("v", EnvironmentUtils.C0603b.m8491c());
    }

    /* renamed from: a */
    public static Request<HotSongOnlineMediaItemsResultNew> m8898a(String str, int i, int i2) {
        return new GetMethodRequest(HotSongOnlineMediaItemsResultNew.class, str).m8537b("page", Integer.valueOf(i)).m8537b("size", Integer.valueOf(i2));
    }

    /* renamed from: b */
    public static Request<FindSongHotListResultNew> m8895b(String str, int i, int i2) {
        return new GetMethodRequest(FindSongHotListResultNew.class, str).m8537b("page", Integer.valueOf(i)).m8537b("size", Integer.valueOf(i2));
    }

    /* renamed from: a */
    public static Request<SingerListResult> m8897a(String str, int i, int i2, String str2, Collection collection) {
        return new GetMethodRequest(SingerListResult.class, str).m8537b("v", EnvironmentUtils.C0603b.m8491c()).m8537b("singer_name", str2).m8537b("singer_id", collection).m8537b("page", Integer.valueOf(i)).m8537b("size", Integer.valueOf(i2));
    }

    /* renamed from: b */
    public static Request<FindSongHandpickResult> m8896b() {
        return new GetMethodRequest(FindSongHandpickResult.class, "http://star.dongting.com", "recommend/selection");
    }

    /* renamed from: c */
    public static Request<OperationZoneResult> m8894c() {
        return new GetMethodRequest(OperationZoneResult.class, f2266a, "module/operationzone").m8540a((Map<String, Object>) EnvironmentUtils.C0603b.m8488e());
    }
}
