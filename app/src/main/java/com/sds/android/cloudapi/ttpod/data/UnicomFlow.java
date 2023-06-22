package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/* loaded from: classes.dex */
public class UnicomFlow implements Serializable {
    @SerializedName(value = "trial")
    private boolean mIsTrial;
    @SerializedName(value = "valid")
    private boolean mIsValidOpen;
    @SerializedName(value = "status_open")
    private int mOpenStatus;
    @SerializedName(value = "status_trial")
    private int mTrialStatus;
    @SerializedName(value = "time_trial")
    private String mTrialTime = "";
    @SerializedName(value = "time_server")
    private String mServerTime = "";
    @SerializedName(value = "time_open")
    private String mOpenTime = "";
    @SerializedName(value = "time_unsubscribe")
    private String mUnsubscribeTime = "";
    @SerializedName(value = "tariff")
    private String mPrice = "";
    @SerializedName(value = "overdue")
    private String mTrialDay = "";
    @SerializedName(value = "attention")
    private String mAttention = "";
    @SerializedName(value = "phone")
    private String mPhone = "";
    @SerializedName(value = "token")
    private String mToken = "";

    public int getTrialStatus() {
        return this.mTrialStatus;
    }

    public int getOpenStatus() {
        return this.mOpenStatus;
    }

    public String getTrialTime() {
        return this.mTrialTime;
    }

    public String getServerTime() {
        return this.mServerTime;
    }

    public String getOpenTime() {
        return this.mOpenTime;
    }

    public String getUnsubscribeTime() {
        return this.mUnsubscribeTime;
    }

    public boolean isTrial() {
        return this.mIsTrial;
    }

    public boolean isValidOpen() {
        return this.mIsValidOpen;
    }

    public String getPrice() {
        return this.mPrice;
    }

    public String getTrialDay() {
        return this.mTrialDay;
    }

    public String getAttention() {
        return this.mAttention;
    }

    public String getPhone() {
        return this.mPhone;
    }

    public String getToken() {
        return this.mToken;
    }
}
