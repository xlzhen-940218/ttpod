package com.sds.android.ttpod.media.audiofx;

import com.sds.android.ttpod.media.audiofx.TTEqualizer;

/* loaded from: classes.dex */
public interface IEffectHandle {
    void release();

    void reset();

    void setBassBoost(int i);

    void setBassBoostEnabled(boolean z);

    void setBoostLimitEnabled(boolean z);

    void setEqualizer(TTEqualizer.Settings settings);

    void setEqualizerEnabled(boolean z);

    void setReverb(int i);

    void setReverbEnabled(boolean z);

    void setTrebleBoost(int i);

    void setTrebleBoostEnabled(boolean z);

    void setVirtualizer(int i);

    void setVirtualizerEnabled(boolean z);
}
