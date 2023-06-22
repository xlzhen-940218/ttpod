package com.sds.android.ttpod.media.audiofx;

/* loaded from: classes.dex */
public class TTReverb extends TTAudioEffect {
    public static final short PRESET_LARGEHALL = 5;
    public static final short PRESET_LARGEROOM = 3;
    public static final short PRESET_MEDIUMHALL = 4;
    public static final short PRESET_MEDIUMROOM = 2;
    public static final short PRESET_NONE = 0;
    public static final short PRESET_PLATE = 6;
    public static final short PRESET_SMALLROOM = 1;

    public TTReverb() {
        super(EffectUUID.EFFECT_ID_REVERB);
    }

    public short getPreset() {
        short[] sArr = {0};
        TTAudioEffect.getEffectParams(EffectUUID.EFFECT_ID_REVERB, new int[]{0}, sArr);
        return sArr[0];
    }

    public int setPreset(short s) {
        return TTAudioEffect.setEffectParams(EffectUUID.EFFECT_ID_REVERB, new int[]{0}, new short[]{s});
    }

    public void setBoostLimitEnabled(boolean z) {
        int[] iArr = {1};
        short[] sArr = new short[1];
        sArr[0] = z ? (short) 1 : (short) 0;
        TTAudioEffect.setEffectParams(EffectUUID.EFFECT_ID_REVERB, iArr, sArr);
    }
}
