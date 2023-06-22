package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import com.sds.android.ttpod.component.scaleimage.ScaleImageActivity;

import java.io.Serializable;

/* loaded from: classes.dex */
public class HotSongOnlineMediaItem extends OnlineMediaItem implements Serializable {
    @SerializedName(value = "desc")
    private String mDescription;
    @SerializedName(value = ScaleImageActivity.KEY_PIC_URL)
    private String mPicUrl;

    public String getDescription() {
        return this.mDescription;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }
}
