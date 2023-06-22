package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/* loaded from: classes.dex */
public class NewFollowers implements Serializable {
    private static final String KEY_USER = "user";
    @SerializedName(value = KEY_USER)
    private TTPodUser mUser;

    public TTPodUser getUser() {
        return this.mUser;
    }
}
