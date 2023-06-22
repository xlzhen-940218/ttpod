package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes.dex */
public class GuideConfig {
    @SerializedName(value = "app_id")
    private int mAppId;
    @SerializedName(value = "app_name")
    private String mAppName;
    @SerializedName(value = "down_apk_url")
    private String mDownloadApkUrl;
    @SerializedName(value = "down_pic_url")
    private String mDownloadImageUrl;
    @SerializedName(value = "package_name")
    private String mPackageName;
    @SerializedName(value = "app_type")
    private int mType;

    public int getAppId() {
        return this.mAppId;
    }

    public String getAppName() {
        return this.mAppName;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public String getDownloadApkUrl() {
        return this.mDownloadApkUrl;
    }

    public String getDownloadImageUrl() {
        return this.mDownloadImageUrl;
    }

    public int getType() {
        return this.mType;
    }
}
