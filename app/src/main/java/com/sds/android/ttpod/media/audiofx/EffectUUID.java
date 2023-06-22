package com.sds.android.ttpod.media.audiofx;

/* loaded from: classes.dex */
public final class EffectUUID {
    private static final int MAX_LENGTH = 6;
    private short mClockSeq;
    private byte[] mNode = new byte[6];
    private short mTimeHiAndVersion;
    private int mTimeLow;
    private short mTimeMid;
    public static final EffectUUID EFFECT_ID_EQUALIZER = new EffectUUID(-831049952, (short) -31619, (short) 4575, (short) -17641, new byte[]{0, 2, -91, -43, -59, 27});
    public static final EffectUUID EFFECT_ID_BASSBOOST = new EffectUUID(-2043546880, (short)29410, (short)4575, (short)-19074, new byte[]{0, 2, -91, -43, -59, 27});
    public static final EffectUUID EFFECT_ID_TREBLEBOOST = new EffectUUID(512455796, (short)-17200, (short)17145, (short)184, new byte[]{0, 2, -91, -43, -59, 27});
    public static final EffectUUID EFFECT_ID_VIRTUALIZER = new EffectUUID(490746816, (short)-31401, (short)4575, (short)-24787, new byte[]{0, 2, -91, -43, -59, 27});
    public static final EffectUUID EFFECT_ID_REVERB = new EffectUUID(-224783360, (short)-23621, (short)4575, (short)-29220, new byte[]{0, 2, -91, -43, -59, 27});
    public static final EffectUUID EFFECT_ID_VOLUME = new EffectUUID(294863264, (short)-31639, (short)4575,(short) -32263, new byte[]{0, 2, -91, -43, -59, 27});
    public static final EffectUUID EFFECT_UUID_NULL = new EffectUUID(-328107796, (short)-6687, (short)17458, (short)-23564, new byte[]{70, 87, -26, 121, 82, 16});

    private EffectUUID(int i, short s, short s2, short s3, byte[] bArr) {
        this.mTimeLow = i;
        this.mTimeMid = s;
        this.mTimeHiAndVersion = s2;
        this.mClockSeq = s3;
        System.arraycopy(bArr, 0, this.mNode, 0, this.mNode.length);
    }
}
