package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import com.sds.android.ttpod.component.scaleimage.ScaleImageActivity;
import java.io.Serializable;

/* loaded from: classes.dex */
public class MVOnlineData implements Serializable {
    @SerializedName(value = "url_high")
    private String mHightQualityUrl;
    @SerializedName(value = "id")
    private int mId;
    @SerializedName(value = "mv_name")
    private String mName;
    @SerializedName(value = "url_low")
    private String mNormalQualityUrl;
    @SerializedName(value = ScaleImageActivity.KEY_PIC_URL)
    private String mPicUrl;
    @SerializedName(value = "singer")
    private String mSinger;

    public int getId() {
        return this.mId;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }

    public String getName() {
        return this.mName;
    }

    public String getSinger() {
        return this.mSinger;
    }

    public String getNormalQualityUrl() {
        return this.mNormalQualityUrl;
    }

    public String getHighQualityUrl() {
        return this.mHightQualityUrl;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setSinger(String str) {
        this.mSinger = str;
    }
}
