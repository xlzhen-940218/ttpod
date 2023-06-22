package com.sds.android.ttpod.media.mediastore;

/* loaded from: classes.dex */
public enum AudioQuality {
    UNDEFINED,
    COMPRESSED,
    STANDARD,
    HIGH,
    SUPER,
    LOSSLESS;
    
    static final int MAX_BITRATE_IN_KBPS_OF_COMPRESS_QUALITY = 80;
    static final int MAX_BITRATE_IN_KBPS_OF_HIGH_QUALITY = 300;
    static final int MAX_BITRATE_IN_KBPS_OF_LOSSLESS_QUALITY = Integer.MAX_VALUE;
    static final int MAX_BITRATE_IN_KBPS_OF_STANDARD_QUALITY = 160;
    static final int MAX_BITRATE_IN_KBPS_OF_SUPER_QUALITY = 320;
    static final int MIN_BITRATE_IN_KBPS_OF_COMPRESS_QUALITY = 0;
    static final int MIN_BITRATE_IN_KBPS_OF_HIGH_QUALITY = 161;
    static final int MIN_BITRATE_IN_KBPS_OF_LOSSLESS_QUALITY = 321;
    static final int MIN_BITRATE_IN_KBPS_OF_STANDARD_QUALITY = 81;
    static final int MIN_BITRATE_IN_KBPS_OF_SUPER_QUALITY = 301;

    public static AudioQuality quality(int i) {
        if (i <= MAX_BITRATE_IN_KBPS_OF_COMPRESS_QUALITY) {
            return COMPRESSED;
        }
        if (i <= MAX_BITRATE_IN_KBPS_OF_STANDARD_QUALITY) {
            return STANDARD;
        }
        if (i <= MAX_BITRATE_IN_KBPS_OF_HIGH_QUALITY) {
            return HIGH;
        }
        if (i <= MAX_BITRATE_IN_KBPS_OF_SUPER_QUALITY) {
            return SUPER;
        }
        return LOSSLESS;
    }

    public static int[] bitrateRange(AudioQuality audioQuality) {
        switch (audioQuality) {
            case COMPRESSED:
                return new int[]{0, MAX_BITRATE_IN_KBPS_OF_COMPRESS_QUALITY};
            case STANDARD:
                return new int[]{MIN_BITRATE_IN_KBPS_OF_STANDARD_QUALITY, MAX_BITRATE_IN_KBPS_OF_STANDARD_QUALITY};
            case HIGH:
                return new int[]{161, MAX_BITRATE_IN_KBPS_OF_HIGH_QUALITY};
            case SUPER:
                return new int[]{MIN_BITRATE_IN_KBPS_OF_SUPER_QUALITY, MAX_BITRATE_IN_KBPS_OF_SUPER_QUALITY};
            default:
                return new int[]{MIN_BITRATE_IN_KBPS_OF_LOSSLESS_QUALITY, Integer.MAX_VALUE};
        }
    }
}
