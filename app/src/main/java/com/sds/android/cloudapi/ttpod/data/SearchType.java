package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class SearchType implements Serializable {
    @SerializedName(value = "hidden")
    private int mHidden;
    @SerializedName(value = "_id")
    private String mId;
    @SerializedName(value = "words")
    private ArrayList<String> mWords = new ArrayList<>();

    public String getId() {
        return this.mId;
    }

    public int getHidden() {
        return this.mHidden;
    }

    public ArrayList<String> getWords() {
        return this.mWords;
    }
}
