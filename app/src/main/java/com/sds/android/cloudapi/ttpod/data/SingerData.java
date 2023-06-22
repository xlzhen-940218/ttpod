package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import com.sds.android.ttpod.component.scaleimage.ScaleImageActivity;
import java.io.Serializable;

/* loaded from: classes.dex */
public class SingerData implements Serializable {
    @SerializedName(value = "singer_id")
    private int mId;
    @SerializedName(value = "singer_name")
    private String mName;
    @SerializedName(value = ScaleImageActivity.KEY_PIC_URL)
    private String mPicUrl;
    @SerializedName(value = "pic_url_140")
    private String mPicUrl140;

    public SingerData() {
    }

    public SingerData(int i, String str, String str2, String str3) {
        this.mId = i;
        this.mName = str;
        this.mPicUrl = str2;
        this.mPicUrl140 = str3;
    }

    public int getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }

    public String getPicUrl140() {
        return this.mPicUrl140;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof SingerData) && this.mId == ((SingerData) obj).mId;
    }

    public int hashCode() {
        return this.mId;
    }
}
