package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class RadioCategory implements Serializable {
    @SerializedName(value = "data")
    private ArrayList<RadioCategoryChannel> mRadioCategoryChannels;
    @SerializedName(value = "tag_type_name")
    private String mRadioCategoryName;

    public ArrayList<RadioCategoryChannel> getRadioCategoryChannels() {
        return this.mRadioCategoryChannels;
    }

    public String getRadioCategoryName() {
        return this.mRadioCategoryName;
    }

    public void setRadioCategoryName(String str) {
        this.mRadioCategoryName = str;
    }

    public void setRadioCategoryChannels(ArrayList<RadioCategoryChannel> arrayList) {
        this.mRadioCategoryChannels = arrayList;
    }
}
