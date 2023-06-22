package com.sds.android.ttpod.ThirdParty.update;

/* loaded from: classes.dex */
public interface DownloadProgressListener {
    void onDownloadProgressChanged(long j, long j2);

    void onDownloadStateChanged(DownloadState downloadState, int i, String str);
}
