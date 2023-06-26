package com.sds.android.sdk.lib.request;

import com.google.gson.annotations.SerializedName;
import com.tencent.open.SocialConstants;

import java.io.Serializable;

/* loaded from: classes.dex */
public class BaseResult implements Serializable {
    public static final int APP_RUN_EXCEPTION = -3;
    public static final int ERROR = 0;

    /* renamed from: OK */
    public static final int f2406OK = 1;
    public static final int UNABLE_CONNECT_TO_SERVER = -1;
    public static final int UNABLE_PARSE_DATA = -2;
    @SerializedName(value = "code")
    private int mCode;
    @SerializedName(value = "msg")
    private String mMessage;
    @SerializedName(value = "ttl")
    private int mTTL;

    public String toString() {
        return getClass().getSimpleName() + "{mCode=" + this.mCode + ", mMessage='" + this.mMessage + "'}";
    }

    public BaseResult() {
    }

    public BaseResult(int i, String str) {
        this.mCode = i;
        this.mMessage = str;
    }

    public int getCode() {
        return this.mCode;
    }

    public boolean isSuccess() {
        return this.mCode == 1;
    }

    public boolean isError() {
        return this.mCode == 0;
    }

    public boolean isUnableConnectServer() {
        return this.mCode == -1;
    }

    public boolean isUnableParseResultData() {
        return this.mCode == -2;
    }

    public void setCode(int i) {
        this.mCode = i;
    }

    public String getMessage() {
        return this.mMessage == null ? "" : this.mMessage;
    }

    public void setMessage(String str) {
        this.mMessage = str;
    }

    public int getTTL() {
        return this.mTTL;
    }

    public void setmTTL(int mTTL) {
        this.mTTL = mTTL;
    }
}
