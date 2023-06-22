package com.sds.android.ttpod.fragment.main;

import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.ttpod.framework.base.ILoadFinished;

/* renamed from: com.sds.android.ttpod.fragment.main.c */
/* loaded from: classes.dex */
public class ResultHelper {

    /* compiled from: ResultHelper.java */
    /* renamed from: com.sds.android.ttpod.fragment.main.c$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1510a<CallbackResult extends BaseResult> {
        /* renamed from: a */
        void mo5564a(CallbackResult callbackresult);
    }

    /* renamed from: a */
    public static <T extends BaseResult> void m5665a(ILoadFinished iLoadFinished, T t, InterfaceC1510a interfaceC1510a) {
        DebugUtils.m8426a(iLoadFinished, "LoadFinished");
        DebugUtils.m8426a(interfaceC1510a, "Callback");
        if (iLoadFinished.isLoadFinished()) {
            interfaceC1510a.mo5564a(t);
        }
    }
}
