package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;


import java.io.Serializable;

/* loaded from: classes.dex */
public class FindSongHotModule implements Serializable {
    @SerializedName(value = "name")
    private String mName;
    @SerializedName(value = "prm")
    private int mPrm;
    @SerializedName(value = "size")
    private int mSize;
    @SerializedName(value = "url")
    private String mUrl;

    public FindSongHotModule() {
    }

    public FindSongHotModule(String str, int i, String str2, int i2) {
        this.mName = str;
        this.mSize = i;
        this.mUrl = str2;
        this.mPrm = i2;
    }

    public String getName() {
        return this.mName;
    }

    public int getSize() {
        return this.mSize;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public boolean needRequestParam() {
        return this.mPrm == 1;
    }
}
