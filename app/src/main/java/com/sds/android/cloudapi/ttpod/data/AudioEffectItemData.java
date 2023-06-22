package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/* loaded from: classes.dex */
public class AudioEffectItemData implements Serializable {
    @SerializedName(value = "eq")
    private short[] mEqualizer = {0};
    @SerializedName(value = "bass")
    private int mBass = 0;
    @SerializedName(value = "treble")
    private int mTreble = 0;
    @SerializedName(value = "virtualizer")
    private int mVirtualizer = 0;
    @SerializedName(value = "reverb")
    private int mReverb = 0;
    @SerializedName(value = "balance")
    private float mBalance = 0.0f;
    @SerializedName(value = "islimit")
    private boolean mIslimit = false;

    public short[] getEqualizer() {
        return this.mEqualizer;
    }

    public int getBass() {
        return this.mBass;
    }

    public int getTreble() {
        return this.mTreble;
    }

    public int getVirtualizer() {
        return this.mVirtualizer;
    }

    public int getReverb() {
        return this.mReverb;
    }

    public float getBalance() {
        return this.mBalance;
    }

    public boolean getIsLimit() {
        return this.mIslimit;
    }

    public void setEqualizer(short[] sArr) {
        this.mEqualizer = sArr;
    }

    public void setBass(int i) {
        this.mBass = i;
    }

    public void setTreble(int i) {
        this.mTreble = i;
    }

    public void setVirtualizer(int i) {
        this.mVirtualizer = i;
    }

    public void setReverb(int i) {
        this.mReverb = i;
    }

    public void setBalance(float f) {
        this.mBalance = f;
    }

    public void setIsLimit(boolean z) {
        this.mIslimit = z;
    }
}
