package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;


/* loaded from: classes.dex */
public class StarCategory {
    public static final String KEY_STAR_CATEGORY_ID = "id";
    public static final String KEY_STAR_CATEGORY_NAME = "name";
    @SerializedName(value = "id")
    private int mId;
    @SerializedName(value = "name")
    private String mName;

    public int getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public void setId(int i) {
        this.mId = i;
    }

    public void setName(String str) {
        this.mName = str;
    }
}
