package com.sds.android.ttpod.media.player;

import android.media.AudioTrack;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import com.sds.android.cloudapi.ttpod.data.VIPPolicy;
import com.sds.android.sdk.lib.util.LogUtils;
import java.util.Random;

/* loaded from: classes.dex */
public class TTAudioTrack {
    private static final int AUDIO_RENDER_THREAD_PRIORITY = -8;
    private static final int BUFFER_SIZE = 40960;
    private static final Object CRITICAL_OBJECT = new Object();
    private static final int DECODE_THREAD_PRIORITY = -8;
    private static final int DEFAULT_CH = 2;
    private static final int DEFAULT_DELAY = 200;
    private static final int ERR_ACCESS_DENIED = -21;
    private static final int ERR_NONE = 0;
    private static final float HARDWARE_COFF = 990.0f;
    private static final String LOG_TAG = "TTAudioTrack";
    private static final int MAX_HEAD_POSITION_MILLIS = 200;
    private static final int MILLISECOND2SECOND = 1000;
    private static final int MIN_HARDWARE_VOLUME = -990;
    private static final int NO_DELAY = 0;
    private static final int SAMPLERATE_48K = 48000;
    private static final int SEND_MSG = 5;
    private static final int STATUS_PAUSED = 1;
    private static final int STATUS_PLAYING = 2;
    private static final int STATUS_STOPPED = 3;
    private static final int WRITE_COMPLETE = 0;
    private static final int WRITE_FAILE = 2;
    private static final int WRITE_INCOMPLETE = 1;
    private static final int WRITE_INVALID = 3;
    private static int mAudioSessionId;
    private AudioTrack mAudioTrack;
    private int mBytesInPCMBuffer;
    private int mChannels;
    private int mHeadPosition;
    private int mNativeAudioSinkContext;
    private Handler mRenderHandler;
    private HandlerThread mRenderThread;
    private int mSampleRate;
    private int mStatus;
    private int mPrevPosInSamples = 0;
    private long mPrevTimeInMills = 0;
    private byte[] mPCMBuffer = new byte[BUFFER_SIZE];

    /* JADX INFO: Access modifiers changed from: private */
    public native int fillPCMBuffer(byte[] bArr, int i, int i2);

    static /* synthetic */ int access$020(TTAudioTrack tTAudioTrack, int i) {
        int i2 = tTAudioTrack.mBytesInPCMBuffer - i;
        tTAudioTrack.mBytesInPCMBuffer = i2;
        return i2;
    }

    public static int audioSessionId() {
        return mAudioSessionId;
    }

    public TTAudioTrack() {
        createRenderThread();
    }

    public static int maxOutputSamplerate() {
        int abs = Math.abs(new Random().nextInt());
        if (abs == 0) {
            abs = VIPPolicy.Entry.MAX_LIMIT;
        }
        try {
            new AudioTrack(3, SAMPLERATE_48K, 2, 2, BUFFER_SIZE, 1, abs).release();
            return SAMPLERATE_48K;
        } catch (Exception e) {
            return AudioTrack.getNativeOutputSampleRate(3);
        }
    }

    private void createRenderThread() {
        Process.setThreadPriority(-8);
        this.mRenderThread = new HandlerThread("AudioRenderThread", -8);
        this.mRenderThread.start();
        while (!this.mRenderThread.isAlive()) {
            Thread.yield();
        }
        this.mRenderHandler = new Handler(this.mRenderThread.getLooper()) { // from class: com.sds.android.ttpod.media.player.TTAudioTrack.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int writePCMBuffer = TTAudioTrack.this.mBytesInPCMBuffer > 0 ? writePCMBuffer(TTAudioTrack.this.mPCMBuffer.length - TTAudioTrack.this.mBytesInPCMBuffer) : 0;
                if (TTAudioTrack.this.mBytesInPCMBuffer == 0) {
                    TTAudioTrack.this.mBytesInPCMBuffer = TTAudioTrack.this.fillPCMBuffer(TTAudioTrack.this.mPCMBuffer, 0, TTAudioTrack.this.mPCMBuffer.length);
                    writePCMBuffer = writePCMBuffer(0);
                }
                tryLoop(writePCMBuffer != 0 ? 200 : 0);
            }

            private int writePCMBuffer(int i) {
                if (TTAudioTrack.this.mBytesInPCMBuffer > 0) {
                    int write = TTAudioTrack.this.mAudioTrack.write(TTAudioTrack.this.mPCMBuffer, i, TTAudioTrack.this.mBytesInPCMBuffer);
                    if (write > 0) {
                        if (TTAudioTrack.this.mBytesInPCMBuffer - write <= 0) {
                            TTAudioTrack.this.mBytesInPCMBuffer = 0;
                            return 0;
                        }
                        TTAudioTrack.access$020(TTAudioTrack.this, write);
                        return 1;
                    }
                    LogUtils.error(TTAudioTrack.LOG_TAG, "AudioTrack write return " + write);
                    return 2;
                }
                TTAudioTrack.this.mBytesInPCMBuffer = 0;
                return 3;
            }

            private void tryLoop(int i) {
                int i2;
                synchronized (TTAudioTrack.CRITICAL_OBJECT) {
                    i2 = TTAudioTrack.this.mStatus;
                }
                if (i2 == 2) {
                    removeMessages(5);
                    sendEmptyMessageDelayed(5, i);
                }
            }
        };
    }

    private int createAudioTrack(int i, int i2) {
        int i3 = i2 == 1 ? 4 : 12;
        try {
            AudioTrack audioTrack = this.mAudioTrack;
            if (Build.VERSION.SDK_INT < 9) {
                this.mAudioTrack = new AudioTrack(3, i, i3, 2, BUFFER_SIZE, 1);
                LogUtils.debug(LOG_TAG, "createAudioTrack-1: " + i + " - " + i2);
            } else if (mAudioSessionId != 0) {
                this.mAudioTrack = new AudioTrack(3, i, i3, 2, BUFFER_SIZE, 1, mAudioSessionId);
                LogUtils.debug(LOG_TAG, "createAudioTrack-2: " + i + " - " + i2);
            } else {
                do {
                    mAudioSessionId = Math.abs(new Random().nextInt());
                    if (mAudioSessionId == 0) {
                        mAudioSessionId = VIPPolicy.Entry.MAX_LIMIT;
                    }
                    this.mAudioTrack = new AudioTrack(3, i, i3, 2, BUFFER_SIZE, 1, mAudioSessionId);
                    LogUtils.debug(LOG_TAG, "createAudioTrack-3: " + i + " - " + i2);
                } while (mAudioSessionId != this.mAudioTrack.getAudioSessionId());
            }
            if (audioTrack != null) {
                audioTrack.release();
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return ERR_ACCESS_DENIED;
        }
    }

    private int audioOpen(int i, int i2) {
        this.mSampleRate = i;
        this.mChannels = i2;
        int createAudioTrack = createAudioTrack(i, i2);
        if (createAudioTrack == 0) {
            this.mPrevPosInSamples = 0;
            this.mBytesInPCMBuffer = 0;
            return 0;
        }
        return createAudioTrack;
    }

    private void audioClose() {
    }

    private void audioDestroy() {
        this.mRenderThread.quit();
    }

    private void audioStart() {
        if (audioTrackIsStopped() || audioTrackIsPaused()) {
            this.mAudioTrack.play();
        }
        synchronized (CRITICAL_OBJECT) {
            this.mStatus = 2;
        }
        this.mRenderHandler.removeMessages(5);
        this.mRenderHandler.sendEmptyMessage(5);
    }

    private void audioFlush() {
    }

    private void audioStop() {
        synchronized (CRITICAL_OBJECT) {
            this.mStatus = 3;
        }
        this.mRenderHandler.removeMessages(5);
        try {
            this.mAudioTrack.setStereoVolume(0.0f, 0.0f);
            this.mAudioTrack.stop();
            this.mAudioTrack.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void audioPause() {
        this.mAudioTrack.pause();
        synchronized (CRITICAL_OBJECT) {
            this.mStatus = 1;
        }
        this.mRenderHandler.removeMessages(5);
    }

    private void audioSetVolume(int i, int i2) {
        float f;
        float f2 = 1.0f;
        if (this.mAudioTrack != null) {
            if (i == 0) {
                f = 1.0f;
            } else {
                f = i == MIN_HARDWARE_VOLUME ? 0.0f : (i + 990) / HARDWARE_COFF;
            }
            if (i2 != 0) {
                f2 = i2 == MIN_HARDWARE_VOLUME ? 0.0f : (i2 + 990) / HARDWARE_COFF;
            }
            try {
                this.mAudioTrack.setStereoVolume(f, f2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private int audioPosition() {
        int i = 0;
        if (audioTrackIsPlaying()) {
            i = this.mAudioTrack.getPlaybackHeadPosition();
        } else if (audioTrackIsPaused()) {
            i = this.mPrevPosInSamples;
        }
        this.mPrevPosInSamples = i;
        return i;
    }

    private int correctPositionDeviation() throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        int playbackHeadPosition = this.mAudioTrack.getPlaybackHeadPosition();
        if (playbackHeadPosition < 0) {
            playbackHeadPosition = 0;
        }
        if (playbackHeadPosition == this.mPrevPosInSamples && playbackHeadPosition != 0) {
            int i = (int) (currentTimeMillis - this.mPrevTimeInMills);
            if (i < 200) {
                playbackHeadPosition += (i * this.mSampleRate) / 1000;
            }
        } else {
            this.mPrevPosInSamples = playbackHeadPosition;
        }
        this.mPrevTimeInMills = currentTimeMillis;
        return playbackHeadPosition;
    }

    private boolean audioTrackIsPlaying() {
        return this.mAudioTrack != null && this.mAudioTrack.getPlayState() == 3;
    }

    private boolean audioTrackIsPaused() {
        return this.mAudioTrack != null && this.mAudioTrack.getPlayState() == 2;
    }

    private boolean audioTrackIsStopped() {
        return this.mAudioTrack != null && this.mAudioTrack.getPlayState() == 1;
    }
}
