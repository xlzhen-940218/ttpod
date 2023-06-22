package com.sds.android.ttpod.media.audiofx;

/* loaded from: classes.dex */
public class TTAudioEffect {
    public static final int BASSBOOST_PARAM_STRENGTH = 1;
    public static final int BASSBOOST_PARAM_STRENGTH_SUPPORTED = 0;
    public static final int EQ_PARAM_BAND_FREQ_RANGE = 4;
    public static final int EQ_PARAM_BAND_LEVEL = 2;
    public static final int EQ_PARAM_CENTER_FREQ = 3;
    public static final int EQ_PARAM_CUR_PRESET = 6;
    public static final int EQ_PARAM_GET_BAND = 5;
    public static final int EQ_PARAM_GET_NUM_OF_PRESETS = 7;
    public static final int EQ_PARAM_GET_PRESET_NAME = 8;
    public static final int EQ_PARAM_LEVEL_RANGE = 1;
    public static final int EQ_PARAM_NUM_BANDS = 0;
    public static final int EQ_PARAM_PROPERTIES = 9;
    private static final String LOG_TAG = "TTAudioEffect";
    public static final int PARAM_STRING_SIZE_MAX = 32;
    public static final int REVERB_PARAM_LIMIT = 1;
    public static final int REVERB_PARAM_PRESET = 0;
    public static final int TREBLEBOOST_PARAM_STRENGTH = 1;
    public static final int TREBLEBOOST_PARAM_STRENGTH_SUPPORTED = 0;
    public static final int VIRTUALIZER_PARAM_STRENGTH = 1;
    public static final int VIRTUALIZER_PARAM_STRENGTH_SUPPORTED = 0;
    public static final int VOLUME_PARAM_LIMIT_ENABLED = 5;
    private EffectUUID mEffectUuid;

    public static native void configEffect(EffectConfig effectConfig);

    public static native void createEffect(EffectUUID effectUUID);

    public static native void disable(EffectUUID effectUUID);

    public static native void enable(EffectUUID effectUUID);

    public static native void getEffectParams(EffectUUID effectUUID, int[] iArr, short[] sArr);

    private static native void nativeInit();

    private static native void nativeRelease(EffectUUID effectUUID);

    private static native void nativeReset(EffectUUID effectUUID);

    public static native int setEffectParams(EffectUUID effectUUID, int[] iArr, short[] sArr);

    static {
        try {
            System.loadLibrary("osal");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
        try {
            System.loadLibrary("audiofx");
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
        }
        nativeInit();
    }

    public TTAudioEffect(EffectUUID effectUUID) {
        this.mEffectUuid = effectUUID;
    }

    public void setEnabled(boolean z) {
        if (z) {
            enable(this.mEffectUuid);
        } else {
            disable(this.mEffectUuid);
        }
    }

    public void reset() {
        nativeReset(this.mEffectUuid);
    }

    public void release() {
        nativeRelease(this.mEffectUuid);
    }

    public static void reset(EffectUUID effectUUID) {
        nativeReset(effectUUID);
    }

    public static void release(EffectUUID effectUUID) {
        nativeRelease(effectUUID);
    }
}
