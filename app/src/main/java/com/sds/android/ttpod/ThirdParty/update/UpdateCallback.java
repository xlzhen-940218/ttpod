package com.sds.android.ttpod.ThirdParty.update;

/* loaded from: classes.dex */
public interface UpdateCallback {
    void downloadApp(String str, String str2);

    void installApp(String str, String str2);

    boolean needInstallApp(String str, String str2);

    void statisticAppInstall(boolean z, String str);

    void updateInfo(VersionUpdateData versionUpdateData);
}
