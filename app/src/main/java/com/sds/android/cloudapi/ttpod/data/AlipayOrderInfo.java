package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class AlipayOrderInfo {
    private static final String KEY_CONTENT = "content";
    private static final String KEY_SIGN = "sign";
    @SerializedName(value = KEY_CONTENT)
    private String mContent;
    @SerializedName(value = KEY_SIGN)
    private String mSign;

    public String getContent() {
        return this.mContent;
    }

    public String getSign() {
        return this.mSign;
    }
}
