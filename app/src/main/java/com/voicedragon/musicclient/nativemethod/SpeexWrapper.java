package com.voicedragon.musicclient.nativemethod;

import com.voicedragon.musicclient.util.Logger;

/* loaded from: classes.dex */
public class SpeexWrapper {
    private static final String TAG = "SpeexWrapper";
    private long mNativeCompression = 1L;

    public void encodeInit(int bwMode) {
        Logger.m2e(TAG, "encodeInit stub");
    }

    public void decodeInit(int bwMode) {
        Logger.m2e(TAG, "decodeInit stub");
    }

    public byte[] encode(byte[] in, boolean forceZip) {
        Logger.m2e(TAG, "encode stub");
        return in != null ? in : new byte[0];
    }

    public byte[] decode(byte[] in) {
        Logger.m2e(TAG, "decode stub");
        return in != null ? in : new byte[0];
    }

    public void setQuality(int quality, int complexity) {
        Logger.m2e(TAG, "setQuality stub");
    }

    public void release() {
        Logger.m2e(TAG, "release stub");
        this.mNativeCompression = 0L;
    }
}
