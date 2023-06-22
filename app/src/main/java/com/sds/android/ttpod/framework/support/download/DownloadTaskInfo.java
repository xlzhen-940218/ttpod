package com.sds.android.ttpod.framework.support.download;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.sds.android.sdk.core.download.TaskInfo;
import com.sds.android.sdk.lib.p061c.p062a.p063a.Field;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.io.Serializable;

/* loaded from: classes.dex */
public class DownloadTaskInfo extends TaskInfo {
    private Long mAddTime;
    private String mAudioQuality;
    private Long mCompleteTime;
    private Long mConnectTimeStamp;
    private Integer mCutOffTimes;
    private Long mDownloadTime;
    private Long mFileId;
    private String mFileName;
    private String mGroupId;
    private String mListId;
    private int mListType;
    private String mOrigin;
    private Integer mPosition;
    private Long mRespondTime;
    private int mSongType;
    private Bundle mTagBundle;
    private Integer mType;
    private boolean mUrlUpdated;
    public static final Integer TYPE_AUDIO = 0;
    public static final Integer TYPE_VIDEO = Integer.valueOf(TYPE_AUDIO.intValue() + 1);
    public static final Integer TYPE_SKIN = Integer.valueOf(TYPE_VIDEO.intValue() + 1);
    public static final Integer TYPE_APP = Integer.valueOf(TYPE_SKIN.intValue() + 1);
    public static final Integer TYPE_PLUGIN = Integer.valueOf(TYPE_APP.intValue() + 1);
    public static final Integer TYPE_FAVORITE_SONG = Integer.valueOf(TYPE_PLUGIN.intValue() + 1);
    public static final Integer TYPE_FAVORITE_SONG_LIST = Integer.valueOf(TYPE_FAVORITE_SONG.intValue() + 1);
    public static final Integer TYPE_BACKGROUND = Integer.valueOf(TYPE_FAVORITE_SONG_LIST.intValue() + 1);
    public static final Parcelable.Creator<DownloadTaskInfo> CREATOR = new Parcelable.Creator<DownloadTaskInfo>() { // from class: com.sds.android.ttpod.framework.support.download.DownloadTaskInfo.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public DownloadTaskInfo createFromParcel(Parcel parcel) {
            return new DownloadTaskInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public DownloadTaskInfo[] newArray(int i) {
            return new DownloadTaskInfo[i];
        }
    };

    public DownloadTaskInfo() {
        this.mPosition = 0;
        this.mListId = "";
        this.mUrlUpdated = false;
        this.mSongType = -1;
        this.mAddTime = Long.valueOf(System.currentTimeMillis());
    }

    public DownloadTaskInfo(Integer num) {
        this.mPosition = 0;
        this.mListId = "";
        this.mUrlUpdated = false;
        this.mSongType = -1;
        this.mType = num;
    }

    public DownloadTaskInfo(String str) {
        super(null, str);
        this.mPosition = 0;
        this.mListId = "";
        this.mUrlUpdated = false;
        this.mSongType = -1;
    }

    public DownloadTaskInfo(String str, String str2, String str3, Integer num, Boolean bool) {
        super(str2, str3, bool);
        this.mPosition = 0;
        this.mListId = "";
        this.mUrlUpdated = false;
        this.mSongType = -1;
        this.mType = num;
        this.mDownloadTime = 0L;
        this.mAddTime = Long.valueOf(System.currentTimeMillis());
        this.mGroupId = str;
    }

    public int getSongType() {
        return this.mSongType;
    }

    public void setSongType(int i) {
        this.mSongType = i;
    }

    @Field(m8643a = 4, m8642b = 16777217)
    public Integer getType() {
        return this.mType;
    }

    public void setType(Integer num) {
        this.mType = num;
    }

    @Field(m8643a = 4, m8642b = 16777217)
    public Long getAddTime() {
        return this.mAddTime;
    }

    public void setAddTime(Long l) {
        this.mAddTime = l;
    }

    @Field(m8643a = 4, m8642b = 16777217)
    public Long getFileId() {
        return this.mFileId;
    }

    public void setFileId(Long l) {
        this.mFileId = l;
    }

    @Field(m8643a = 4, m8642b = 16777217)
    public String getFileName() {
        return this.mFileName;
    }

    public void setFileName(String str) {
        this.mFileName = str;
    }

    @Field(m8643a = 4, m8642b = 16777217)
    public Long getDownloadTime() {
        return this.mDownloadTime;
    }

    public void setDownloadTime(Long l) {
        this.mDownloadTime = l;
    }

    @Field(m8643a = 4, m8642b = 16777217)
    public Long getRespondTime() {
        return this.mRespondTime;
    }

    public void setRespondTime(Long l) {
        if (this.mRespondTime != null && 0 != this.mRespondTime.longValue()) {
            l = this.mRespondTime;
        }
        this.mRespondTime = l;
    }

    @Field(m8643a = 4, m8642b = 16777217)
    public Integer getCutOffTimes() {
        return this.mCutOffTimes;
    }

    public void setCutOffTimes(Integer num) {
        this.mCutOffTimes = num;
    }

    @Field(m8643a = 4, m8642b = 16777218)
    public Long getCompleteTime() {
        return this.mCompleteTime;
    }

    public void setCompleteTime(Long l) {
        this.mCompleteTime = l;
    }

    @Field(m8643a = 4, m8642b = 16777217)
    public String getOrigin() {
        return this.mOrigin;
    }

    public void setOrigin(String str) {
        this.mOrigin = str;
    }

    public Integer getPosition() {
        return this.mPosition;
    }

    public void setPosition(int i) {
        this.mPosition = Integer.valueOf(i);
    }

    public String getListId() {
        return this.mListId;
    }

    public void setListId(String str) {
        this.mListId = str;
    }

    public int getListType() {
        return this.mListType;
    }

    public void setListType(int i) {
        this.mListType = i;
    }

    public Long getConnectTimeStamp() {
        return Long.valueOf(this.mConnectTimeStamp == null ? 0L : this.mConnectTimeStamp.longValue());
    }

    public void setAudioQuality(String str) {
        this.mAudioQuality = str;
    }

    @Field(m8643a = 4, m8642b = 16777222)
    public String getAudioQuality() {
        return this.mAudioQuality;
    }

    @Field(m8643a = 4, m8642b = 16777221)
    public String getGroupId() {
        return this.mGroupId;
    }

    public void setGroupId(String str) {
        this.mGroupId = str;
    }

    public void setConnectTimeStamp(Long l) {
        this.mConnectTimeStamp = l;
    }

    public boolean isUrlUpdated() {
        return this.mUrlUpdated;
    }

    public void setUrlUpdated(boolean z) {
        this.mUrlUpdated = z;
    }

    @Override // com.sds.android.sdk.core.download.TaskInfo
    public Object getTag() {
        if (this.mTagBundle != null && !this.mTagBundle.isEmpty()) {
            String str = (String) this.mTagBundle.keySet().toArray()[0];
            if ("key_parcel".equals(str)) {
                return this.mTagBundle.getParcelable(str);
            }
            if ("key_string".equals(str)) {
                return this.mTagBundle.getString(str);
            }
            if ("key_serial".equals(str)) {
                return this.mTagBundle.getSerializable(str);
            }
            if ("key_bool".equals(str)) {
                return Boolean.valueOf(this.mTagBundle.getBoolean(str));
            }
            if ("key_int".equals(str)) {
                return Integer.valueOf(this.mTagBundle.getInt(str));
            }
            if ("key_float".equals(str)) {
                return Float.valueOf(this.mTagBundle.getFloat(str));
            }
            if ("key_double".equals(str)) {
                return Double.valueOf(this.mTagBundle.getDouble(str));
            }
        }
        return null;
    }

    @Override // com.sds.android.sdk.core.download.TaskInfo
    public void setTag(Object obj) {
        this.mTagBundle = new Bundle();
        if (obj instanceof Parcelable) {
            this.mTagBundle.putParcelable("key_parcel", (Parcelable) obj);
        } else if (obj instanceof String) {
            this.mTagBundle.putString("key_string", (String) obj);
        } else if (obj instanceof Serializable) {
            this.mTagBundle.putSerializable("key_serial", (Serializable) obj);
        } else if (obj instanceof Boolean) {
            this.mTagBundle.putBoolean("key_bool", ((Boolean) obj).booleanValue());
        } else if (obj instanceof Integer) {
            this.mTagBundle.putInt("key_int", ((Integer) obj).intValue());
        } else if (obj instanceof Float) {
            this.mTagBundle.putFloat("key_float", ((Float) obj).floatValue());
        } else if (obj instanceof Double) {
            this.mTagBundle.putDouble("key_double", ((Double) obj).doubleValue());
        }
    }

    @Override // com.sds.android.sdk.core.download.TaskInfo, android.os.Parcelable
    public int describeContents() {
        return super.describeContents();
    }

    @Override // com.sds.android.sdk.core.download.TaskInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.mAddTime.longValue());
        parcel.writeInt(this.mType.intValue());
        parcel.writeString(this.mOrigin);
        parcel.writeLong(this.mDownloadTime == null ? 0L : this.mDownloadTime.longValue());
        parcel.writeLong(this.mRespondTime == null ? 0L : this.mRespondTime.longValue());
        parcel.writeLong(this.mConnectTimeStamp == null ? 0L : this.mConnectTimeStamp.longValue());
        parcel.writeInt(this.mCutOffTimes == null ? 0 : this.mCutOffTimes.intValue());
        parcel.writeString(this.mFileName);
        parcel.writeLong(this.mFileId != null ? this.mFileId.longValue() : 0L);
        parcel.writeString(this.mAudioQuality == null ? "" : this.mAudioQuality);
        parcel.writeString(this.mGroupId == null ? MediaStorage.GROUP_ID_DOWNLOAD : this.mGroupId);
        parcel.writeBooleanArray(new boolean[]{this.mUrlUpdated});
        parcel.writeInt(this.mSongType);
        parcel.writeBundle(this.mTagBundle);
    }

    private DownloadTaskInfo(Parcel parcel) {
        super(parcel);
        this.mPosition = 0;
        this.mListId = "";
        this.mUrlUpdated = false;
        this.mSongType = -1;
        this.mAddTime = Long.valueOf(parcel.readLong());
        this.mType = Integer.valueOf(parcel.readInt());
        this.mOrigin = parcel.readString();
        this.mDownloadTime = Long.valueOf(parcel.readLong());
        this.mRespondTime = Long.valueOf(parcel.readLong());
        this.mConnectTimeStamp = Long.valueOf(parcel.readLong());
        this.mCutOffTimes = Integer.valueOf(parcel.readInt());
        this.mFileName = parcel.readString();
        this.mFileId = Long.valueOf(parcel.readLong());
        this.mAudioQuality = parcel.readString();
        this.mGroupId = parcel.readString();
        boolean[] zArr = new boolean[1];
        parcel.readBooleanArray(zArr);
        this.mUrlUpdated = zArr[0];
        this.mSongType = parcel.readInt();
        this.mTagBundle = parcel.readBundle(DownloadTaskInfo.class.getClassLoader());
    }

    @Override // com.sds.android.sdk.core.download.TaskInfo
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (super.equals(obj)) {
            DownloadTaskInfo downloadTaskInfo = (DownloadTaskInfo) obj;
            if (this.mType != null) {
                if (this.mType.equals(downloadTaskInfo.mType)) {
                    return true;
                }
            } else if (downloadTaskInfo.mType == null) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override // com.sds.android.sdk.core.download.TaskInfo
    public int hashCode() {
        return (this.mType != null ? this.mType.hashCode() : 0) + (super.hashCode() * 31);
    }
}
