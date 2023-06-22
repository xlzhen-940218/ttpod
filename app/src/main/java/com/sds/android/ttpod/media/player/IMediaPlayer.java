package com.sds.android.ttpod.media.player;

/* loaded from: classes.dex */
public interface IMediaPlayer {
    int bufferedPercent();

    int duration();

    float getBufferPercent();

    int getBufferSize();

    int getCurFreq(short[] sArr, int i);

    int getCurFreqAndWave(short[] sArr, short[] sArr2, int i);

    int getCurWave(short[] sArr, int i);

    int getFileSize();

    int getPosition();

    void pause();

    int play();

    void release();

    void resume();

    void setActiveNetWorkType(int i);

    void setAudioEffectLowDelay(boolean z);

    void setCacheFilePath(String str);

    void setChannelBalance(float f);

    void setDataSourceAsync(String str) throws Exception;

    void setPlayRange(int i, int i2);

    void setPosition(int i);

    void setProxyServerConfig(String str, int i, String str2, boolean z);

    void setVolume(float f, float f2);

    void stop();
}
