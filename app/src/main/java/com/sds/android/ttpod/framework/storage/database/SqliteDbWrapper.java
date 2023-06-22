package com.sds.android.ttpod.framework.storage.database;

import android.content.Context;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sds.android.ttpod.framework.storage.database.c */
/* loaded from: classes.dex */
public class SqliteDbWrapper {
    /* renamed from: a */
    public void m3114a(DownloadTaskInfo downloadTaskInfo) {
        SqliteDb.m3122a(downloadTaskInfo);
    }

    /* renamed from: b */
    public List<DownloadTaskInfo> m3112b(DownloadTaskInfo downloadTaskInfo) {
        List<DownloadTaskInfo> m3118b = SqliteDb.m3118b(downloadTaskInfo);
        return m3118b == null ? new ArrayList() : m3118b;
    }

    /* renamed from: a */
    public void m3115a(Context context) {
        SqliteDb.m3126a(context);
    }

    /* renamed from: a */
    public void m3113a(DownloadTaskInfo downloadTaskInfo, DownloadTaskInfo downloadTaskInfo2) {
        SqliteDb.m3121a(downloadTaskInfo, downloadTaskInfo2);
    }

    /* renamed from: c */
    public void m3111c(DownloadTaskInfo downloadTaskInfo) {
        SqliteDb.m3117c(downloadTaskInfo);
    }
}
