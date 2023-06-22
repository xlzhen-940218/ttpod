package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class AudioEffectItem implements Serializable {
    @SerializedName(value = "_id")
    private String mID = "";
    @SerializedName(value = "style")
    private int mStyle = 0;
    @SerializedName(value = "device")
    private String mDevice = "";
    @SerializedName(value = "output")
    private int mOutput = 0;
    @SerializedName(value = User.KEY_NICK_NAME)
    private String mNickName = "";
    @SerializedName(value = User.KEY_AVATAR)
    private String mPic = "";
    @SerializedName(value = "exp")
    private AudioEffectUserExp mExp = new AudioEffectUserExp();
    @SerializedName(value = "pick_count")
    private int mPickCount = 0;
    @SerializedName(value = "user_id")
    private int mUserId = 0;
    @SerializedName(value = "audio_effect")
    private AudioEffectItemData mEffectData = new AudioEffectItemData();
    @SerializedName(value = "pick_lru")
    private ArrayList<AudioEffectPickLruItem> mLru = new ArrayList<>();

    public ArrayList<AudioEffectPickLruItem> getLru() {
        return this.mLru;
    }

    public void setLru(ArrayList<AudioEffectPickLruItem> arrayList) {
        this.mLru = arrayList;
    }

    public String getID() {
        return this.mID;
    }

    public void setPickCount(int i) {
        this.mPickCount = i;
    }

    public void setID(String str) {
        this.mID = str;
    }

    public void setStyle(int i) {
        this.mStyle = i;
    }

    public void setDevice(String str) {
        this.mDevice = str;
    }

    public void setOutput(int i) {
        this.mOutput = i;
    }

    public void setNickName(String str) {
        this.mNickName = str;
    }

    public void setPic(String str) {
        this.mPic = str;
    }

    public void setUserId(int i) {
        this.mUserId = i;
    }

    public int getStyle() {
        return this.mStyle;
    }

    public String getDevice() {
        return this.mDevice;
    }

    public int getOutput() {
        return this.mOutput;
    }

    public String getNickName() {
        return this.mNickName;
    }

    public String getPic() {
        return this.mPic;
    }

    public int getTotal() {
        return this.mExp.getTotal();
    }

    public int getPickCount() {
        return this.mPickCount;
    }

    public short[] getDataEqualizer() {
        return this.mEffectData.getEqualizer();
    }

    public int getDataBass() {
        if (this.mEffectData != null) {
            return this.mEffectData.getBass();
        }
        return 0;
    }

    public int getDataTreble() {
        if (this.mEffectData != null) {
            return this.mEffectData.getTreble();
        }
        return 0;
    }

    public int getDataReverb() {
        if (this.mEffectData != null) {
            return this.mEffectData.getReverb();
        }
        return 0;
    }

    public int getDataVirtualizer() {
        if (this.mEffectData != null) {
            return this.mEffectData.getVirtualizer();
        }
        return 0;
    }

    public float getDataBalance() {
        return this.mEffectData.getBalance();
    }

    public boolean getDataIsLimit() {
        return this.mEffectData.getIsLimit();
    }

    public int getUserId() {
        return this.mUserId;
    }
}
