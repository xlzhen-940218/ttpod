package com.sds.android.ttpod.media.audiofx;

/* loaded from: classes.dex */
public class EffectConfig {
    private int mChannels;
    private int mInputSamplingRate;
    private int mOutputSamplingRate;

    public int getInputSamplingRate() {
        return this.mInputSamplingRate;
    }

    public void setInputSamplingRate(int i) {
        this.mInputSamplingRate = i;
    }

    public int getOutputSamplingRate() {
        return this.mOutputSamplingRate;
    }

    public void setOutputSamplingRate(int i) {
        this.mOutputSamplingRate = i;
    }

    public int getChannels() {
        return this.mChannels;
    }

    public void setChannels(int i) {
        this.mChannels = i;
    }
}
