package com.sds.android.ttpod.framework.modules;

import android.os.Handler;

import com.sds.android.cloudapi.ttpod.p055a.UrlList;
import com.sds.android.cloudapi.ttpod.result.FindSongModuleResult;

import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.DataListResult;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.framework.base.ValidityResult;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.p106a.ListUtils;
import com.sds.android.ttpod.framework.storage.p133a.Cache;

/* renamed from: com.sds.android.ttpod.framework.modules.e */
/* loaded from: classes.dex */
public class ModuleRequestHelper {
    /* renamed from: a */
    public static <Result extends BaseResult, TargetResult extends BaseResult> void m4083a(Request<Result> request, CommandID commandID, ModuleID moduleID, ResultConvert<Result, TargetResult> resultConvert) {
        m4082a(request, commandID, moduleID, resultConvert, null);
    }

    /* renamed from: a */
    public static <Result extends BaseResult, TargetResult extends BaseResult> void m4082a(final Request<Result> request, final CommandID commandID, final ModuleID moduleID, final ResultConvert<Result, TargetResult> resultConvert, final String str) {
        final String m4084a = m4084a(request);
        DebugUtils.m8426a(request, "do request is null");
        final Handler handler = new Handler();
        final long currentTimeMillis = System.currentTimeMillis();
        TaskScheduler.m8581a(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.e.1
            @Override // java.lang.Runnable
            public void run() {
                BaseResult m4562a = null;
                BaseResult m4562a1;
                ValidityResult m3207a = Cache.getInstance().m3207a(m4084a);
                if (ModuleRequestHelper.m4081a(m3207a, EnvironmentUtils.C0604c.m8474e())) {
                    long currentTimeMillis2 = System.currentTimeMillis();
                    m4562a1 = request.m8531f();
                    LogUtils.warning("ModuleRequestHelper", "request.execute cost--> " + (System.currentTimeMillis() - currentTimeMillis2) + "ms  " + request.m8532e());
                    //new //SSystemEvent("SYS_PAGE_REQUEST", "finish").append("uri", request.m8532e()).append("duration", Long.valueOf(System.currentTimeMillis() - currentTimeMillis)).append("error_code", Integer.valueOf(m4562a1.getCode())).post();
                    if (!ModuleRequestHelper.m4085a(m4562a1)) {
                        boolean z = (m4562a1 instanceof DataListResult) && ListUtils.m4718a(((DataListResult) m4562a1).getDataList());
                        if (m4562a1.isSuccess() && !z) {
                            Cache.getInstance().m3206a(m4084a, new ValidityResult(m4562a1, m4084a));
                        }
                    }
                } else {
                    m4562a1 = m3207a.m4562a();
                }
                if (resultConvert != null) {
                    m4562a1 = resultConvert.mo4042a((Result) m4562a);
                }
                m4562a = m4562a1;
                BaseResult finalM4562a = m4562a;
                handler.post(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.e.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (str == null) {
                            CommandCenter.getInstance().m4604a(new Command(commandID, finalM4562a), moduleID);
                        } else {
                            CommandCenter.getInstance().m4604a(new Command(commandID, finalM4562a, str), moduleID);
                        }
                    }
                });
            }
        });
    }

    /* renamed from: a */
    protected static String m4084a(Request request) {
        if (request.toString().contains(UrlList.f2260a + "/recomm_modules")) {
            return "online.dongting.com/recomm/recomm_modules";
        }
        return request.toString();
    }

    /* renamed from: a */
    protected static boolean m4085a(BaseResult baseResult) {
        return (baseResult instanceof FindSongModuleResult) && baseResult.isSuccess() && ((FindSongModuleResult) baseResult).size() == 0;
    }

    /* renamed from: a */
    protected static boolean m4081a(ValidityResult validityResult, boolean z) {
        return validityResult == null || validityResult.m4562a() == null || (validityResult.m4561b() && z);
    }
}
