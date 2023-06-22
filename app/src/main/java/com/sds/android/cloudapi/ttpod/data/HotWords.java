package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/* loaded from: classes.dex */
public class HotWords implements Serializable {
    @SerializedName(value = User.KEY_AVATAR)
    private String mPictureUrl;
    @SerializedName(value = "word")
    private String mWord;

    public String getWord() {
        return this.mWord;
    }

    public String getPictureUrl() {
        return this.mPictureUrl;
    }
}
