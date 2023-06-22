package com.sds.android.cloudapi.ttpod.result;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class SingerTag implements Serializable {
    @SerializedName(value = "classifier")
    private int mClassifier;
    @SerializedName(value = "id")
    private long mId;
    @SerializedName(value = "parentId")
    private List<Long> mParentId = new ArrayList();
    @SerializedName(value = "standardId")
    private long mStandardId;
    @SerializedName(value = "tag")
    private String mTag;
    @SerializedName(value = "type")
    private int mType;

    public long getId() {
        return this.mId;
    }

    public String getTag() {
        return this.mTag;
    }

    public int getType() {
        return this.mType;
    }

    public int getClassifier() {
        return this.mClassifier;
    }

    public List<Long> getParentId() {
        return this.mParentId;
    }

    public long getStandardId() {
        return this.mStandardId;
    }
}
