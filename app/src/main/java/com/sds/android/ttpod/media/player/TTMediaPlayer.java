package com.sds.android.ttpod.media.player;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* loaded from: classes.dex */
public class TTMediaPlayer implements IMediaPlayer {
    public static final int MAX_SAMPLE_NUM = 1024;
    public static final int MEDIASTATUS_PAUSED = 3;
    public static final int MEDIASTATUS_PLAYING = 2;
    public static final int MEDIASTATUS_PREPARED = 5;
    public static final int MEDIASTATUS_STARTING = 1;
    public static final int MEDIASTATUS_STOPPED = 4;
    public static final int MEDIA_BUFFERING_DONE = 17;
    public static final int MEDIA_BUFFERING_START = 16;
    public static final int MEDIA_CACHE_COMPLETED = 23;
    public static final int MEDIA_CLOSE = 5;
    public static final int MEDIA_COMPLETE = 3;
    public static final int MEDIA_CONNECT_DONE = 19;
    public static final int MEDIA_DNS_DONE = 18;
    public static final int MEDIA_EXCEPTION = 6;
    public static final int MEDIA_FADEOUT_START = 24;
    public static final int MEDIA_HTTP_HEADER_RECEIVED = 20;
    public static final int MEDIA_NOP = 0;
    public static final int MEDIA_PAUSE = 4;
    public static final int MEDIA_PLAY = 2;
    public static final int MEDIA_PREFETCH_COMPLETED = 22;
    public static final int MEDIA_PREPARE = 1;
    public static final int MEDIA_START_RECEIVE_DATA = 21;
    public static final int MEDIA_UPDATE_DURATION = 7;
    private static final int MIN_HARDWARE_VOLUME = -990;
    public static final int MIN_SAMPLE_NUM = 256;
    private static final float PERCENT = 0.01f;
    private static final String TAG = "TTMediaPlayer";
    private OnMediaPlayerNotifyEventListener mMediaPlayerNotifyEventListener;
    private boolean mPlayerReleased = false;
    private int mNativePlayerPara = 0;
    private EventHandler mEventHandler = new EventHandler(this, Looper.myLooper());

    /* loaded from: classes.dex */
    public interface OnMediaPlayerNotifyEventListener {
        void onMediaPlayerNotify(int i, int i2, int i3, Object obj);
    }

    private static native void nativeCongfigProxyServer(String str, int i, String str2, boolean z);

    private native int nativeGetCurFreq(short[] sArr, int i);

    private native int nativeGetCurFreqAndWave(short[] sArr, short[] sArr2, int i);

    private native int nativeGetCurWave(short[] sArr, int i);

    private native void nativeRelease();

    private static native void nativeSetAudioEffectLowDelay(boolean z);

    private native void nativeSetCacheFilePath(String str);

    private native int nativeSetDataSourceAsync(String str);

    private native int nativeSetDataSourceSync(String str);

    private native void nativeSetVolume(int i, int i2);

    private native void nativeSetup(Object obj, byte[] bArr, int i, String str);

    private native int nativeStop();

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public native int bufferedPercent();

    public native int bufferedSize();

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public native int duration();

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public native int getPosition();

    public native int getStatus();

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public native void pause();

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public native int play();

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public native void resume();

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public native void setActiveNetWorkType(int i);

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public native void setPlayRange(int i, int i2);

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public native void setPosition(int i);

    public native int size();

    static {
        try {
            System.loadLibrary("osal");
            System.loadLibrary("audiofx");
            System.loadLibrary("mediaplayer");
            System.loadLibrary("resample");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
    }

    public TTMediaPlayer(byte[] bArr, String str) {
        nativeSetup(this, bArr, TTAudioTrack.maxOutputSamplerate(), str);
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public void setCacheFilePath(String str) {
        nativeSetCacheFilePath(str);
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public float getBufferPercent() {
        return bufferedPercent() * PERCENT;
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public int getBufferSize() {
        return bufferedSize();
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public int getFileSize() {
        return size();
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public int getCurFreqAndWave(short[] sArr, short[] sArr2, int i) {
        int i2 = -1;
        synchronized (this) {
            if (!this.mPlayerReleased) {
                i2 = nativeGetCurFreqAndWave(sArr, sArr2, i);
            }
        }
        return i2;
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public int getCurFreq(short[] sArr, int i) {
        int i2 = -1;
        synchronized (this) {
            if (!this.mPlayerReleased) {
                i2 = nativeGetCurFreq(sArr, i);
            }
        }
        return i2;
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public int getCurWave(short[] sArr, int i) {
        int i2 = -1;
        synchronized (this) {
            if (!this.mPlayerReleased) {
                i2 = nativeGetCurWave(sArr, i);
            }
        }
        return i2;
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public void setVolume(float f, float f2) {
        nativeSetVolume((int) ((1.0f - f) * (-990.0f)), (int) ((1.0f - f2) * (-990.0f)));
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public void stop() {
        if (nativeStop() != 0) {
            throw new IllegalStateException("can not be stopped!");
        }
    }

    private int setDataSource(String str, boolean z) {
        if (z) {
            return nativeSetDataSourceSync(str);
        }
        return nativeSetDataSourceAsync(str);
    }

    public int setDataSource(String str) {
        return setDataSource(str, true);
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public void setDataSourceAsync(String str) {
        if (setDataSource(str, false) != 0) {
            throw new IllegalStateException("can not setDataSourceAsync !");
        }
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public void release() {
        synchronized (this) {
            nativeRelease();
            this.mPlayerReleased = true;
        }
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public void setChannelBalance(float f) {
        setVolume(1.0f - f, 1.0f + f);
    }

    /* loaded from: classes.dex */
    private class EventHandler extends Handler {
        public EventHandler(TTMediaPlayer tTMediaPlayer, Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (TTMediaPlayer.this.mMediaPlayerNotifyEventListener != null) {
                TTMediaPlayer.this.mMediaPlayerNotifyEventListener.onMediaPlayerNotify(message.what, message.arg1, message.arg2, message.obj);
            }
        }
    }

    private static void postEventFromNative(Object obj, int i, int i2, int i3, Object obj2) {
        Message obtainMessage;
        TTMediaPlayer tTMediaPlayer = (TTMediaPlayer) obj;
        if (tTMediaPlayer != null && tTMediaPlayer.mEventHandler != null) {
            if (6 == i) {
                obtainMessage = tTMediaPlayer.mEventHandler.obtainMessage(i, i2, i3, new MediaPlayerNotificationInfo((MediaPlayerNotificationInfo) obj2));
            } else {
                obtainMessage = tTMediaPlayer.mEventHandler.obtainMessage(i, i2, i3, obj2);
            }
            tTMediaPlayer.mEventHandler.sendMessage(obtainMessage);
        }
    }

    public void setOnMediaPlayerNotifyEventListener(OnMediaPlayerNotifyEventListener onMediaPlayerNotifyEventListener) {
        this.mMediaPlayerNotifyEventListener = onMediaPlayerNotifyEventListener;
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public void setAudioEffectLowDelay(boolean z) {
        nativeSetAudioEffectLowDelay(z);
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public void setProxyServerConfig(String str, int i, String str2, boolean z) {
        nativeCongfigProxyServer(str, i, str2, z);
    }
}
