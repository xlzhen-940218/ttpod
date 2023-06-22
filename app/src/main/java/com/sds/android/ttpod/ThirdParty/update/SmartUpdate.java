package com.sds.android.ttpod.ThirdParty.update;

import android.content.Context;
import com.sds.android.ttpod.ThirdParty.p066a.InstanceUtils;

/* loaded from: classes.dex */
public class SmartUpdate {
    private static UpdateInterface mUpdateInterface = null;

    private static UpdateInterface getUpdateInstance() {
        if (mUpdateInterface != null) {
            return mUpdateInterface;
        }
        mUpdateInterface = null;
        mUpdateInterface = (UpdateInterface) InstanceUtils.m8328a("com.sds.android.ttpod.ThirdParty.update.Wandoujia");
        if (mUpdateInterface != null) {
            return mUpdateInterface;
        }
        mUpdateInterface = (UpdateInterface) InstanceUtils.m8328a("com.sds.android.ttpod.ThirdParty.update.Qihoo360");
        if (mUpdateInterface != null) {
            return mUpdateInterface;
        }
        mUpdateInterface = (UpdateInterface) InstanceUtils.m8328a("com.sds.android.ttpod.ThirdParty.update.HiApk");
        if (mUpdateInterface != null) {
            return mUpdateInterface;
        }
        mUpdateInterface = (UpdateInterface) InstanceUtils.m8328a("com.sds.android.ttpod.ThirdParty.update.Baidu");
        if (mUpdateInterface != null) {
            return mUpdateInterface;
        }
        mUpdateInterface = (UpdateInterface) InstanceUtils.m8328a("com.sds.android.ttpod.ThirdParty.update.Tencent");
        if (mUpdateInterface != null) {
            return mUpdateInterface;
        }
        mUpdateInterface = (UpdateInterface) InstanceUtils.m8328a("com.sds.android.ttpod.ThirdParty.update.Anzhi");
        return mUpdateInterface;
    }

    public static boolean check(Context context, VersionUpdateData versionUpdateData, UpdateCallback updateCallback) {
        UpdateInterface updateInstance = getUpdateInstance();
        if (updateInstance != null) {
            return updateInstance.check(context, versionUpdateData, updateCallback);
        }
        return false;
    }

    public static void update(boolean z, UpdateCallback updateCallback) {
        UpdateInterface updateInstance = getUpdateInstance();
        if (updateInstance != null) {
            updateInstance.update(z, updateCallback);
        }
    }

    public static void setDownloadProgressListener(DownloadProgressListener downloadProgressListener) {
        UpdateInterface updateInstance = getUpdateInstance();
        if (updateInstance != null) {
            updateInstance.setDownloadProgressListener(downloadProgressListener);
        }
    }

    public static void cancelUpdate() {
        UpdateInterface updateInstance = getUpdateInstance();
        if (updateInstance != null) {
            updateInstance.cancelUpdate();
        }
    }

    public static void onResume() {
        UpdateInterface updateInstance = getUpdateInstance();
        if (updateInstance != null) {
            updateInstance.onResume();
        }
    }

    public static void onDestroy() {
        UpdateInterface updateInstance = getUpdateInstance();
        if (updateInstance != null) {
            updateInstance.onDestroy();
        }
    }
}
