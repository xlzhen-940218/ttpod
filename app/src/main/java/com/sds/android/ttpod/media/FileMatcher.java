package com.sds.android.ttpod.media;

import com.sds.android.sdk.lib.util.LogUtils;

/* loaded from: classes.dex */
public class FileMatcher {
    private CallBack mCallBack;
    private int mNativeRef = nativeSetup();

    /* loaded from: classes.dex */
    public interface CallBack {
        void onFileMatched(String str);

        void onFolderMatched(String str);
    }

    private native void nativeRelease(int i);

    private native int nativeSetup();

    private native void nativeStart(String str, String str2, boolean z, String str3, int i);

    private native void nativeStop(int i);

    static {
        try {
            System.loadLibrary("filematcher");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
    }

    public FileMatcher(CallBack callBack) {
        this.mCallBack = callBack;
        LogUtils.m8381c("ss", "mNativeRef:" + this.mNativeRef);
    }

    public void start(String str, String str2, boolean z, String str3) {
        LogUtils.m8381c("ss", "mNativeRef2:" + this.mNativeRef);
        nativeStart(str, str2, z, str3, this.mNativeRef);
    }

    public void stop() {
        nativeStop(this.mNativeRef);
    }

    public void release() {
        nativeRelease(this.mNativeRef);
        this.mNativeRef = 0;
    }

    private static void postEventFormNative(Object obj, int i, String str) {
        CallBack callBack = ((FileMatcher) obj).mCallBack;
        if (callBack != null) {
            if (i == 0) {
                callBack.onFileMatched(str);
            } else {
                callBack.onFolderMatched(str);
            }
        }
    }
}
