package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/* loaded from: classes.dex */
public class VoiceOfChina implements Serializable {
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    @SerializedName(value = "id")
    private int mId;
    @SerializedName(value = "title")
    private String mTitle;

    public int getId() {
        return this.mId;
    }

    public String getTitle() {
        return this.mTitle;
    }
}
