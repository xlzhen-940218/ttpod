package com.sds.android.ttpod.ThirdParty.update;

import android.os.Parcel;
import android.os.Parcelable;
import com.sds.android.cloudapi.ttpod.result.AppVersionResult;

/* loaded from: classes.dex */
public class VersionUpdateData implements Parcelable {
    private Boolean mAppstoreInstalled;
    private String mDownloadUrl;
    private Object mExtra;
    private boolean mIsUpdateMandatory;
    private String mLatestVersion;
    private String mMessage;
    private double mPatchSize;
    private double mSize;
    private String mToolName;
    private String mUpdateDescription;
    private UpdateState mUpdateState;
    private String mUpdateUrl;
    private int mUpgradeType;

    /* loaded from: classes.dex */
    public enum UpdateState {
        FAILED,
        NO_NEED,
        NEED
    }

    public VersionUpdateData(boolean z, String str, String str2, String str3) {
        this.mLatestVersion = "";
        this.mUpdateUrl = "";
        this.mUpdateDescription = "";
        this.mMessage = "";
        this.mUpdateState = UpdateState.NO_NEED;
        this.mExtra = new Object();
        this.mDownloadUrl = "";
        this.mAppstoreInstalled = false;
        this.mToolName = "";
        this.mLatestVersion = str;
        this.mUpdateUrl = str2;
        this.mUpdateDescription = str3;
        this.mIsUpdateMandatory = z;
    }

    public VersionUpdateData(AppVersionResult appVersionResult) {
        this(appVersionResult.isUpdateMandatory(), appVersionResult.getLatestVersion(), appVersionResult.getUpdateUrl(), appVersionResult.getUpdateDescription());
    }

    public int getUpgradeType() {
        return this.mUpgradeType;
    }

    public void setUpgradeType(int i) {
        this.mUpgradeType = i;
    }

    public double getSize() {
        return this.mSize;
    }

    public void setSize(double d) {
        this.mSize = d;
    }

    public Object getExtra() {
        return this.mExtra;
    }

    public void setExtra(Object obj) {
        this.mExtra = obj;
    }

    public boolean isUpdateMandatory() {
        return this.mIsUpdateMandatory;
    }

    public String getLatestVersion() {
        return this.mLatestVersion;
    }

    public String getUpdateUrl() {
        return this.mUpdateUrl;
    }

    public String getUpdateDescription() {
        return this.mUpdateDescription;
    }

    public UpdateState getUpdateState() {
        return this.mUpdateState;
    }

    public void setUpdateState(UpdateState updateState) {
        this.mUpdateState = updateState;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public void setMessage(String str) {
        this.mMessage = str;
    }

    public String getDownloadUrl() {
        return this.mDownloadUrl;
    }

    public void setDownloadUrl(String str) {
        this.mDownloadUrl = str;
    }

    public double getPatchSize() {
        return this.mPatchSize;
    }

    public void setPatchSize(double d) {
        this.mPatchSize = d;
    }

    public Boolean getAppstoreInstalled() {
        return this.mAppstoreInstalled;
    }

    public void setAppstoreInstalled(Boolean bool) {
        this.mAppstoreInstalled = bool;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VersionUpdateData versionUpdateData = (VersionUpdateData) obj;
        return this.mIsUpdateMandatory == versionUpdateData.mIsUpdateMandatory && this.mLatestVersion.equals(versionUpdateData.mLatestVersion) && this.mUpdateDescription.equals(versionUpdateData.mUpdateDescription) && this.mUpdateUrl.equals(versionUpdateData.mUpdateUrl);
    }

    public int hashCode() {
        return ((((((this.mIsUpdateMandatory ? 1 : 0) * 31) + this.mLatestVersion.hashCode()) * 31) + this.mUpdateUrl.hashCode()) * 31) + this.mUpdateDescription.hashCode();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    public String getToolName() {
        return this.mToolName;
    }

    public void setToolName(String str) {
        this.mToolName = str;
    }
}
