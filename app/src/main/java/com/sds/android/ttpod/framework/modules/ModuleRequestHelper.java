package com.sds.android.ttpod.framework.modules;

import android.os.Handler;

import com.sds.android.cloudapi.ttpod.api.UrlList;
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
    public static <Result extends BaseResult, TargetResult extends BaseResult> void execute(Request<Result> request, CommandID commandID, ModuleID moduleID, ResultConvert<Result, TargetResult> resultConvert) {
        execute(request, commandID, moduleID, resultConvert, null);
    }

    /* renamed from: a */
    public static <Result extends BaseResult, TargetResult extends BaseResult> void execute(final Request<Result> request, final CommandID commandID, final ModuleID moduleID, final ResultConvert<Result, TargetResult> resultConvert, final String str) {
        final String host = isRecommandReplace(request);
        DebugUtils.m8426a(request, "do request is null");
        final Handler handler = new Handler();
        final long currentTimeMillis = System.currentTimeMillis();
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.e.1
            @Override // java.lang.Runnable
            public void run() {
                BaseResult result = null;
                ValidityResult m3207a = Cache.getInstance().m3207a(host);
                if (ModuleRequestHelper.checkValidityResult(m3207a, EnvironmentUtils.DeviceConfig.isConnected())) {
                    long currentTimeMillis2 = System.currentTimeMillis();
                    result = request.execute();
                    LogUtils.warning("ModuleRequestHelper", "request.execute cost--> " + (System.currentTimeMillis() - currentTimeMillis2) + "ms  " + request.m8532e());
                    //new //SSystemEvent("SYS_PAGE_REQUEST", "finish").append("uri", request.m8532e()).append("duration", Long.valueOf(System.currentTimeMillis() - currentTimeMillis)).append("error_code", Integer.valueOf(result != null ? result.getCode() : -1)).post();
                    if (result != null && !ModuleRequestHelper.m4085a(result)) {
                        boolean z = (result instanceof DataListResult) && ListUtils.m4718a(((DataListResult) result).getDataList());
                        if (result.isSuccess() && !z) {
                            Cache.getInstance().m3206a(host, new ValidityResult(result, host));
                        }
                    }
                } else if (m3207a != null) {
                    result = m3207a.m4562a();
                }
                if (resultConvert != null) {
                    result = resultConvert.mo4042a((Result) result);
                }
                final BaseResult finalM4562a = result;
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
    protected static String isRecommandReplace(Request request) {
        if (request.toString().contains(UrlList.recommend + "/recomm_modules")) {
            return "online.dongting.com/recomm/recomm_modules";
        }
        return request.toString();
    }

    /* renamed from: a */
    protected static boolean m4085a(BaseResult baseResult) {
        return (baseResult instanceof FindSongModuleResult) && baseResult.isSuccess() && ((FindSongModuleResult) baseResult).size() == 0;
    }

    /* renamed from: a */
    protected static boolean checkValidityResult(ValidityResult validityResult, boolean z) {
        return validityResult == null || validityResult.m4562a() == null || (validityResult.m4561b() && z);
    }
}
