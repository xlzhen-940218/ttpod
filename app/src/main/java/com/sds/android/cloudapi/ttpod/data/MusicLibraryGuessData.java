package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;import com.google.gson.annotations.SerializedName;

import com.sds.android.ttpod.component.scaleimage.ScaleImageActivity;
import java.io.Serializable;

/* loaded from: classes.dex */
public class MusicLibraryGuessData implements Serializable {
    public static final int TYPE_CLASSIFACATION = 1;
    public static final int TYPE_RADIO = 2;
    public static final int TYPE_SINGER = 3;
    @SerializedName(value = ScaleImageActivity.KEY_PIC_URL)
    private String mPicUrl;
    @SerializedName(value = "type")
    private int mType;
    @SerializedName(value = "type_id")
    private int mTypeId;
    @SerializedName(value = "type_name")
    private String mTypeName;

    public int getType() {
        return this.mType;
    }

    public int getTypeId() {
        return this.mTypeId;
    }

    public String getTypeName() {
        return this.mTypeName;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }
}
