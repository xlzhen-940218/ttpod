package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;


/* loaded from: classes.dex */
public class LabeledTTPodUser extends TTPodUser {
    private static final String KEY_LABEL = "label";
    @SerializedName(value = KEY_LABEL)
    private String mLabel;

    public String getLabel() {
        return this.mLabel;
    }
}
