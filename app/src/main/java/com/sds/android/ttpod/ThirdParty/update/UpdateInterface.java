package com.sds.android.ttpod.ThirdParty.update;

import android.content.Context;

/* loaded from: classes.dex */
public interface UpdateInterface {
    void cancelUpdate();

    boolean check(Context context, VersionUpdateData versionUpdateData, UpdateCallback updateCallback);

    void onDestroy();

    void onResume();

    void setDownloadProgressListener(DownloadProgressListener downloadProgressListener);

    void update(boolean z, UpdateCallback updateCallback);
}
