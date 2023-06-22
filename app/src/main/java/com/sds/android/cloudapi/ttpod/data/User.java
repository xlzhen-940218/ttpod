package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class User implements Serializable {
    public static final int FEMALE = 0;
    private static final String KEY_ACCESS_TOKEN = "access_token";
    public static final String KEY_AVATAR = "pic";
    public static final String KEY_BIRTHDAY = "birthday";
    public static final String KEY_COVER = "cover_url";
    private static final String KEY_CREATE_AT = "create_at";
    private static final String KEY_EXPIRE_AT = "expires_at";
    private static final String KEY_INFO = "info";
    private static final String KEY_IS_LOCAL_BIND = "is_local_bind";
    public static final String KEY_NICK_NAME = "nick_name";
    private static final String KEY_OLD_NAME = "old_name";
    public static final String KEY_SEX = "sex";
    public static final String KEY_USER_EMAIL = "user_name";
    public static final String KEY_USER_ID = "tuid";
    private static final String KEY_VIA = "via";
    private static final String KEY_VIP = "vip";
    private static final String KEY_VIP_EXPIRES_AT = "vip_expires_at";
    private static final String KEY_WEIBO_ENABLED = "weibo_enabled";
    public static final int MALE = 1;
    private static final long serialVersionUID = 1;
    @SerializedName(value = "access_token")
    private String mAccessToken;
    @SerializedName(value = KEY_CREATE_AT)
    private long mCreateAtInSecond;
    @SerializedName(value = KEY_EXPIRE_AT)
    private long mExpiresAtSecond;
    @SerializedName(value = KEY_INFO)
    private Map<String, Object> mInfo;
    @SerializedName(value = KEY_IS_LOCAL_BIND)
    private Object mIsLocalBind;
    @SerializedName(value = KEY_OLD_NAME)
    private String mOldName;
    @SerializedName(value = KEY_USER_ID)
    private long mUserId;
    @SerializedName(value = KEY_VIA)
    private String mVia;
    @SerializedName(value = KEY_VIP_EXPIRES_AT)
    private long mVipExpiresAt;
    @SerializedName(value = KEY_VIP)
    private int mVipLevel;
    @SerializedName(value = KEY_WEIBO_ENABLED)
    private boolean mWeiboEnabled;
    @SerializedName(value = KEY_USER_EMAIL)
    private String mUserName = "";
    @SerializedName(value = KEY_NICK_NAME)
    private String mNickName = "";
    @SerializedName(value = KEY_AVATAR)
    private String mAvatarUrl = "";
    @SerializedName(value = KEY_COVER)
    private String mCoverUrl = "";
    @SerializedName(value = KEY_SEX)
    private int mSex = 1;
    @SerializedName(value = KEY_BIRTHDAY)
    private long mBirthdayInSecond = 0;

    public String getUserName() {
        return this.mUserName;
    }

    public String getProfileCoverUrl() {
        return this.mCoverUrl;
    }

    public int getSex() {
        return this.mSex;
    }

    public long getBirthdayInSecond() {
        return this.mBirthdayInSecond;
    }

    public String getNickName() {
        return this.mNickName;
    }

    public String getAvatarUrl() {
        return this.mAvatarUrl;
    }

    public long getUserId() {
        return this.mUserId;
    }

    public void setUserName(String str) {
        this.mUserName = str;
    }

    public void setProfileCoverUrl(String str) {
        this.mCoverUrl = str;
    }

    public void setSex(int i) {
        if (i != 1 && i != 0) {
            i = this.mSex;
        }
        this.mSex = i;
    }

    public void setBirthday(long j) {
        this.mBirthdayInSecond = j;
    }

    public void setNickName(String str) {
        this.mNickName = str;
    }

    public void setAvatarUrl(String str) {
        this.mAvatarUrl = str;
    }

    public void setUserId(long j) {
        this.mUserId = j;
    }

    public String getOldName() {
        return this.mOldName == null ? "" : this.mOldName;
    }

    public void setOldName(String str) {
        this.mOldName = str;
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    public void setAccessToken(String str) {
        this.mAccessToken = str;
    }

    public long getCreateAtInSecond() {
        return this.mCreateAtInSecond;
    }

    public void setCreateAtInSecond(long j) {
        this.mCreateAtInSecond = j;
    }

    public long getExpiresAtSecond() {
        return this.mExpiresAtSecond;
    }

    public void setExpiresAtSecond(long j) {
        this.mExpiresAtSecond = j;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0033 -> B:7:0x0016). Please submit an issue!!! */
    public Object getIsLocalBind() {
        try {
            if (this.mIsLocalBind != null) {
                if (this.mIsLocalBind instanceof Number) {
                    return Integer.valueOf(((Number) this.mIsLocalBind).intValue());
                } else if (this.mIsLocalBind instanceof Boolean) {
                    return Integer.valueOf(((Boolean) this.mIsLocalBind).booleanValue() ? 0 : 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.mIsLocalBind;
    }

    public void setIsLocalBind(Object obj) {
        this.mIsLocalBind = obj;
    }

    public String getVia() {
        return this.mVia;
    }

    public void setVia(String str) {
        this.mVia = str;
    }

    public int getVipLevel() {
        return this.mVipLevel;
    }

    public long getVipExpiresAt() {
        return this.mVipExpiresAt;
    }

    public boolean getWeiboEnabled() {
        return this.mWeiboEnabled;
    }

    public void setWeiboEnabled(boolean z) {
        this.mWeiboEnabled = z;
    }

    public Map<String, Object> getInfo() {
        return this.mInfo == null ? new HashMap() : this.mInfo;
    }

    public void setInfo(Map<String, Object> map) {
        this.mInfo = map;
    }
}
