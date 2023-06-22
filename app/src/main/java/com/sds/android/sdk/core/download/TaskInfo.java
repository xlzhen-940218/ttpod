package com.sds.android.sdk.core.download;

import android.os.Parcel;
import android.os.Parcelable;

import com.sds.android.sdk.lib.p061c.p062a.p063a.Field;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class TaskInfo implements Parcelable {
    public static final Parcelable.Creator<TaskInfo> CREATOR = new Parcelable.Creator<TaskInfo>() { // from class: com.sds.android.sdk.core.download.TaskInfo.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public TaskInfo createFromParcel(Parcel parcel) {
            return new TaskInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public TaskInfo[] newArray(int i) {
            return new TaskInfo[i];
        }
    };
    public static final int DB_VERSION = 16777216;
    private static final String NONE = "none";
    private static final int NUM_100 = 100;
    private Task mAttachTask;
    private String mConnectFailedIPs;
    private String mConnectedIP;
    private AtomicInteger mDownloadLength;
    private AtomicInteger mDownloadSpeed;
    private Integer mFileLength;
    private Boolean mIsResumeBrokenTransferSupported;
    private Integer mResponseCode;
    private String mSavePath;
    private String mSourceUrl;
    private Integer mState;
    private boolean mStatisticRequest;
    private Object mTag;

    public TaskInfo(String str, String str2, Boolean bool) {
        this.mConnectFailedIPs = "none";
        this.mConnectedIP = "none";
        this.mResponseCode = -1;
        this.mDownloadSpeed = new AtomicInteger(0);
        this.mDownloadLength = new AtomicInteger(0);
        this.mSourceUrl = str;
        this.mSavePath = str2;
        this.mIsResumeBrokenTransferSupported = bool;
    }

    public TaskInfo() {
        this.mConnectFailedIPs = "none";
        this.mConnectedIP = "none";
        this.mResponseCode = -1;
        this.mDownloadSpeed = new AtomicInteger(0);
        this.mDownloadLength = new AtomicInteger(0);
    }

    public TaskInfo(String str, String str2) {
        this(str, str2, null);
    }

    private void loadDownloadLength() {
        if (FileUtils.m8414b(buildTmpPath())) {
            this.mDownloadLength.set((int) FileUtils.m8405g(buildTmpPath()));
        } else if (FileUtils.m8414b(this.mSavePath)) {
            this.mDownloadLength.set((int) FileUtils.m8411c(this.mSavePath));
        }
    }

    public void setStatisticRequest(boolean z) {
        this.mStatisticRequest = z;
    }

    public boolean getStatisticRequest() {
        return this.mStatisticRequest;
    }

    public void statisticConnectFailedIPs(String str) {
        if (str == null) {
            str = "none";
        }
        this.mConnectFailedIPs = str;
    }

    public String getStatisticConnectFailedIP() {
        return this.mConnectFailedIPs;
    }

    public void setConnectedIP(String str) {
        this.mConnectedIP = str;
    }

    public String getConnectedIP() {
        return this.mConnectedIP;
    }

    public void setResponseCode(int i) {
        this.mResponseCode = Integer.valueOf(i);
    }

    public int getResponseCode() {
        return this.mResponseCode.intValue();
    }

    public Object getTag() {
        return this.mTag;
    }

    public void setTag(Object obj) {
        this.mTag = obj;
    }

    @Field(m8643a = 0, m8642b = 16777216, m8641c = true)
    public final String getSavePath() {
        return this.mSavePath;
    }

    public void setSavePath(String str) {
        this.mSavePath = str;
        loadDownloadLength();
    }

    @Field(m8643a = 1, m8642b = 16777216)
    public final String getSourceUrl() {
        return this.mSourceUrl;
    }

    public void setSourceUrl(String str) {
        this.mSourceUrl = str;
    }

    @Field(m8643a = 2, m8642b = 16777216)
    public Integer getFileLength() {
        return this.mFileLength;
    }

    public void setFileLength(Integer num) {
        this.mFileLength = num;
    }

    @Field(m8643a = 3, m8642b = 16777216)
    public Integer getState() {
        return this.mState;
    }

    public void setState(Integer num) {
        this.mState = num;
    }

    @Field(m8643a = 4, m8642b = 16777216)
    public Boolean getIsResumeBrokenTransferSupported() {
        return this.mIsResumeBrokenTransferSupported;
    }

    public void setIsResumeBrokenTransferSupported(Boolean bool) {
        this.mIsResumeBrokenTransferSupported = bool;
    }

    public boolean resumeBrokenTransferSupported() {
        return this.mIsResumeBrokenTransferSupported != null && this.mIsResumeBrokenTransferSupported.booleanValue();
    }

    public int getDownloadLength() {
        return this.mDownloadLength.get();
    }

    public void setDownloadLength(int i) {
        this.mDownloadLength.set(i);
    }

    public int getDownloadSpend() {
        return this.mDownloadSpeed.get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDownloadSpend(int i) {
        this.mDownloadSpeed.set(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAttachTask(Task task) {
        this.mAttachTask = task;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Task getAttachTask() {
        return this.mAttachTask;
    }

    public Integer getDownloadProgress() {
        if (this.mFileLength == null || this.mFileLength.intValue() == 0) {
            return 0;
        }
        return Integer.valueOf((int) ((this.mDownloadLength.get() * 100) / this.mFileLength.intValue()));
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return StringUtils.m8344a(this.mSavePath, ((TaskInfo) obj).getSavePath());
    }

    public int hashCode() {
        if (this.mSavePath != null) {
            return this.mSavePath.hashCode();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String buildTmpPath() {
        return this.mSavePath + ".tmp";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mSavePath);
        parcel.writeString(this.mSourceUrl);
        parcel.writeInt(this.mFileLength == null ? 0 : this.mFileLength.intValue());
        parcel.writeInt(this.mState == null ? 3 : this.mState.intValue());
        parcel.writeBooleanArray(new boolean[]{this.mIsResumeBrokenTransferSupported.booleanValue()});
        parcel.writeInt(this.mDownloadSpeed.get());
        parcel.writeInt(this.mDownloadLength.get());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TaskInfo(Parcel parcel) {
        this.mConnectFailedIPs = "none";
        this.mConnectedIP = "none";
        this.mResponseCode = -1;
        this.mDownloadSpeed = new AtomicInteger(0);
        this.mDownloadLength = new AtomicInteger(0);
        this.mSavePath = parcel.readString();
        this.mSourceUrl = parcel.readString();
        this.mFileLength = Integer.valueOf(parcel.readInt());
        this.mState = Integer.valueOf(parcel.readInt());
        boolean[] zArr = new boolean[1];
        parcel.readBooleanArray(zArr);
        this.mIsResumeBrokenTransferSupported = Boolean.valueOf(zArr[0]);
        this.mDownloadSpeed.set(parcel.readInt());
        this.mDownloadLength.set(parcel.readInt());
    }
}
