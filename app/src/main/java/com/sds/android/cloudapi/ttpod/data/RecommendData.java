package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;


import com.sds.android.ttpod.component.scaleimage.ScaleImageActivity;

import java.io.Serializable;

/* loaded from: classes.dex */
public class RecommendData implements Serializable {
    public static final String KEY_LISTEN_COUNT = "listenCount";
    @SerializedName(value = "desc")
    private String mDesc;
    @SerializedName(value = "_id")
    private long mId;
    @SerializedName(value = "name")
    private String mName;
    @SerializedName(value = ScaleImageActivity.KEY_PIC_URL)
    private String mPicUrl;
    @SerializedName(value = "tag")
    private int mTag = -1;
    @SerializedName(value = "action")
    private ForwardAction mForwardAction = new ForwardAction();

    public long getId() {
        return this.mId;
    }

    public int getTag() {
        return this.mTag;
    }

    public String getName() {
        return this.mName;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }

    public String getDesc() {
        return this.mDesc;
    }

    public ForwardAction getForwardAction() {
        return this.mForwardAction;
    }
}
