package com.sds.android.ttpod.media.audiofx;

/* loaded from: classes.dex */
public class TTBassBoost extends TTAudioEffect {
    public TTBassBoost() {
        super(EffectUUID.EFFECT_ID_BASSBOOST);
    }

    public boolean getStrengthSupported() {
        return true;
    }

    public void setStrength(short s) {
        TTAudioEffect.setEffectParams(EffectUUID.EFFECT_ID_BASSBOOST, new int[]{1}, new short[]{s});
    }

    public short getRoundedStrength() {
        short[] sArr = {0};
        TTAudioEffect.getEffectParams(EffectUUID.EFFECT_ID_BASSBOOST, new int[]{1}, sArr);
        return sArr[0];
    }
}
