package com.voicedragon.musicclient.nativemethod;

import com.voicedragon.musicclient.util.Logger;

/* loaded from: classes.dex */
public class SpeexWrapper {
    private static final String TAG = "SpeexWrapper";
    private long mNativeCompression = init();

    private native void finalizer(long j);

    private native long init();

    private native byte[] native_decode(long j, byte[] bArr);

    private native void native_decodeInit(long j, int i);

    private native byte[] native_encode(long j, byte[] bArr, boolean z);

    private native void native_encodeInit(long j, int i);

    private native void native_setQuality(long j, int i, int i2);

    static {
        System.loadLibrary("Speex");
    }

    public void encodeInit(int bwMode) {
        Logger.m2e(TAG, "encodeInit");
        if (this.mNativeCompression != 0) {
            native_encodeInit(this.mNativeCompression, bwMode);
        }
    }

    public void decodeInit(int bwMode) {
        if (this.mNativeCompression != 0) {
            native_decodeInit(this.mNativeCompression, bwMode);
        }
    }

    public byte[] encode(byte[] in, boolean forceZip) {
        Logger.m2e(TAG, "encode");
        if (this.mNativeCompression != 0) {
            return native_encode(this.mNativeCompression, in, forceZip);
        }
        Logger.m2e(TAG, "encode1111111");
        return new byte[0];
    }

    public byte[] decode(byte[] in) {
        Logger.m2e(TAG, "decode");
        return this.mNativeCompression != 0 ? native_decode(this.mNativeCompression, in) : new byte[0];
    }

    public void setQuality(int quality, int complexity) {
        Logger.m2e(TAG, "setQuality");
        if (this.mNativeCompression != 0) {
            native_setQuality(this.mNativeCompression, quality, complexity);
        }
    }

    public void release() {
        Logger.m2e(TAG, "release");
        if (this.mNativeCompression != 0) {
            finalizer(this.mNativeCompression);
        }
        Logger.m2e(TAG, "release1111111");
        this.mNativeCompression = 0L;
    }
}
