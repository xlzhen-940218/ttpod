package com.sds.android.ttpod.media.audiofx;

/* loaded from: classes.dex */
public class TTVolume extends TTAudioEffect {
    public TTVolume() {
        super(EffectUUID.EFFECT_ID_VOLUME);
    }

    public void setBoostLimitEnabled(boolean z) {
        int[] iArr = {5};
        short[] sArr = {0, 0};
        if (z) {
            sArr[0] = 1;
        }
        setEnabled(z);
        TTAudioEffect.setEffectParams(EffectUUID.EFFECT_ID_VOLUME, iArr, sArr);
    }

    public boolean boostLimitEnabled() {
        short[] sArr = {0, 0};
        TTAudioEffect.getEffectParams(EffectUUID.EFFECT_ID_VOLUME, new int[]{5}, sArr);
        return sArr[0] != 0;
    }
}
