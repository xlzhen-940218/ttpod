package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes.dex */
public class AudioEffectUser implements Serializable {
    @SerializedName(value = "_id")
    private long mId = 0;
    @SerializedName(value = "exp")
    private AudioEffectUserExp mExp = new AudioEffectUserExp();
    @SerializedName(value = User.KEY_NICK_NAME)
    private String mNickName = "";
    @SerializedName(value = User.KEY_AVATAR)
    private String mPic = "";
    @SerializedName(value = "allow_add")
    private boolean mAllowAdd = false;

    public long getId() {
        return this.mId;
    }

    public AudioEffectUserExp getExp() {
        return this.mExp;
    }

    public String getNickName() {
        return this.mNickName;
    }

    public String getPic() {
        return this.mPic;
    }

    public boolean getAllowAdd() {
        return this.mAllowAdd;
    }
}
