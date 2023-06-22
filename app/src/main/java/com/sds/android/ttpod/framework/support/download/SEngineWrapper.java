package com.sds.android.ttpod.framework.support.download;

import com.sds.android.sdk.core.statistic.SEngine;
import com.sds.android.sdk.core.statistic.SSystemEvent;

/* renamed from: com.sds.android.ttpod.framework.support.download.c */
/* loaded from: classes.dex */
public class SEngineWrapper {
    /* renamed from: a */
    public void m2398a(SSystemEvent sSystemEvent) {
        SEngine.instance();
        SEngine.post(sSystemEvent);
    }
}
