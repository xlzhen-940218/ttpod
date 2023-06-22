package com.sds.android.ttpod.media.player;

import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;

/* loaded from: classes.dex */
public class SystemMediaPlayer extends MediaPlayer implements IMediaPlayer {
    private static final int BYTE_MAX_VALUE = 255;
    private static final float KCOFF = 0.22813341f;
    private static final int LSB_8_MASK = 255;
    private static final int MAX_KFFT_DATA_SIZE = 512;
    private static final String TAG = "SystemMediaPlayer";
    private static int[] mVisualizerCaptureSizeRange;
    private byte[] mRawVizData;
    private Visualizer mVisualizer;

    static {
        try {
            mVisualizerCaptureSizeRange = Visualizer.getCaptureSizeRange();
        } catch (Throwable th) {
            mVisualizerCaptureSizeRange = null;
            th.printStackTrace();
        }
    }

    public SystemMediaPlayer() {
        try {
            setAudioStreamType(3);
            this.mVisualizer = new Visualizer(getAudioSessionId());
            this.mVisualizer.setEnabled(true);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public void setDataSourceAsync(String str) throws Exception {
        super.setDataSource(str);
        prepareAsync();
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public void setActiveNetWorkType(int i) {
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public void setCacheFilePath(String str) {
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public int play() {
        try {
            super.start();
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public void resume() {
        start();
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public void setPosition(int i) {
        super.seekTo(i);
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public void setPlayRange(int i, int i2) {
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public int getPosition() {
        try {
            return super.getCurrentPosition();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public float getBufferPercent() {
        return 1.0f;
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public int duration() {
        return super.getDuration();
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public int bufferedPercent() {
        return 0;
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public int getCurFreqAndWave(short[] sArr, short[] sArr2, int i) {
        return 0;
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public int getCurFreq(short[] sArr, int i) {
        if (i > 512 || this.mVisualizer == null) {
            return -1;
        }
        setVisualizerCaptureSize(i);
        if (this.mVisualizer.getFft(this.mRawVizData) == 0) {
            int min = Math.min(this.mRawVizData.length >> 1, sArr.length);
            int i2 = this.mRawVizData[0] & 255;
            int i3 = this.mRawVizData[1] & 255;
            sArr[0] = (short) (Math.sqrt((i2 * i2) + (i3 * i3)) * 0.2281334102153778d);
            for (int i4 = 1; i4 < min; i4++) {
                byte b = this.mRawVizData[i4 * 2];
                byte b2 = this.mRawVizData[(i4 * 2) + 1];
                sArr[i4] = (short) (Math.sqrt((b * b) + (b2 * b2)) * 2.0d * 0.2281334102153778d);
            }
            return 0;
        }
        return -1;
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public int getCurWave(short[] sArr, int i) {
        if (this.mVisualizer != null) {
            setVisualizerCaptureSize(i << 1);
            if (this.mVisualizer != null && this.mVisualizer.getFft(this.mRawVizData) == 0) {
                for (int i2 = 0; i2 < sArr.length; i2++) {
                    sArr[i2] = (short) ((this.mRawVizData[i2] & 255) - 127);
                }
                return 0;
            }
        }
        return -1;
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public int getBufferSize() {
        return 0;
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public int getFileSize() {
        return 0;
    }

    void setVisualizerCaptureSize(int i) {
        int i2 = i * 2;
        if (mVisualizerCaptureSizeRange != null) {
            if (i2 < mVisualizerCaptureSizeRange[0]) {
                i2 = mVisualizerCaptureSizeRange[0];
            }
            if (i2 > mVisualizerCaptureSizeRange[1]) {
                i2 = mVisualizerCaptureSizeRange[1];
            }
        }
        if (this.mRawVizData == null || i2 != this.mRawVizData.length) {
            if (this.mVisualizer != null && i2 != this.mVisualizer.getCaptureSize()) {
                try {
                    this.mVisualizer.setEnabled(false);
                    this.mVisualizer.setCaptureSize(i2);
                    this.mVisualizer.setEnabled(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.mRawVizData = new byte[i2];
        }
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public void setChannelBalance(float f) {
        setVolume(1.0f - f, 1.0f + f);
    }

    @Override // android.media.MediaPlayer, com.sds.android.ttpod.media.player.IMediaPlayer
    public void release() {
        if (this.mVisualizer != null) {
            this.mVisualizer.release();
            this.mVisualizer = null;
        }
        super.stop();
        super.release();
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public void setAudioEffectLowDelay(boolean z) {
    }

    @Override // com.sds.android.ttpod.media.player.IMediaPlayer
    public void setProxyServerConfig(String str, int i, String str2, boolean z) {
    }
}
