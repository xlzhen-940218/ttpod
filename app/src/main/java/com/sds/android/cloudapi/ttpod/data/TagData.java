package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class TagData implements Serializable {
    private static final String KEY_CLASSIFIER = "classifier";
    private static final String KEY_ID = "id";
    private static final String KEY_PARENTID = "parentId";
    private static final String KEY_STANDARDID = "standardId";
    private static final String KEY_TAG = "tag";
    private static final String KEY_TYPE = "type";
    @SerializedName(value = KEY_CLASSIFIER)
    private int mClassifier;
    @SerializedName(value = "id")
    private long mId;
    @SerializedName(value = KEY_PARENTID)
    private List<Long> mParentId;
    @SerializedName(value = KEY_STANDARDID)
    private long mStandardId;
    @SerializedName(value = KEY_TAG)
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
        if (this.mParentId == null) {
            this.mParentId = new ArrayList();
        }
        return this.mParentId;
    }

    public long getStandardId() {
        return this.mStandardId;
    }
}
