package com.sds.android.ttpod.framework.support.download;

import com.sds.android.sdk.core.download.Manager;
import com.sds.android.sdk.core.download.Task;
import com.sds.android.sdk.core.download.TaskInfo;

/* renamed from: com.sds.android.ttpod.framework.support.download.b */
/* loaded from: classes.dex */
public class ManagerWrapper {
    /* renamed from: a */
    public void m2399a(String str, TaskInfo taskInfo, Task.TaskCallback abstractC0578a) {
        Manager.getInstance().start(str, taskInfo, abstractC0578a);
    }
}
