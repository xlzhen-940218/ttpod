package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes.dex */
public class AppBaseInfo implements Serializable {
    @SerializedName(value = "adid")
    protected long mAdid;
    @SerializedName(value = "app_type")
    protected int mAppType;
    @SerializedName(value = "description")
    protected String mDescription;
    @SerializedName(value = "download_count")
    protected int mDownloadCount;
    @SerializedName(value = "download_url")
    protected String mDownloadUrl;
    @SerializedName(value = "id")
    protected int mId;
    @SerializedName(value = "logo")
    protected String mLogoURL;
    @SerializedName(value = "name")
    protected String mName;
    @SerializedName(value = "package_name")
    protected String mPackageName;
    @SerializedName(value = "short_name")
    protected String mShortName;
    @SerializedName(value = "size")
    protected String mSize;
    @SerializedName(value = "source")
    protected String mSource;
    @SerializedName(value = "version_code")
    protected String mVersionCode;
    @SerializedName(value = "version_name")
    protected String mVersionName;

    /* loaded from: classes.dex */
    public enum AppType {
        SOFTWARE(1),
        GAME(2);
        
        private int mValue;

        AppType(int i) {
            this.mValue = i;
        }

        public int value() {
            return this.mValue;
        }
    }

    public int getId() {
        return this.mId;
    }

    public long getAdid() {
        return this.mAdid;
    }

    public int getAppType() {
        return this.mAppType;
    }

    public String getLogoURL() {
        return this.mLogoURL;
    }

    public String getName() {
        return this.mName;
    }

    public String getShortName() {
        return this.mShortName;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public int getDownloadCount() {
        return this.mDownloadCount;
    }

    public String getDownloadUrl() {
        return this.mDownloadUrl;
    }

    public String getSize() {
        return this.mSize;
    }

    public String getSource() {
        return this.mSource;
    }

    public String getVersionName() {
        return this.mVersionName;
    }

    public String getVersionCode() {
        return this.mVersionCode;
    }

    public int getVersionCodeNumber() {
        try {
            return Integer.parseInt(this.mVersionCode);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
