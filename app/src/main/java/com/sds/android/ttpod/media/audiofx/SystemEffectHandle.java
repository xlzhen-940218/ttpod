package com.sds.android.ttpod.media.audiofx;

import android.media.audiofx.BassBoost;
import android.media.audiofx.Equalizer;
import android.media.audiofx.PresetReverb;
import android.media.audiofx.Virtualizer;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.ttpod.media.audiofx.TTEqualizer;

/* loaded from: classes.dex */
public class SystemEffectHandle implements IEffectHandle {
    private BassBoost mBassBoost;
    private Equalizer mEqualizer;
    private PresetReverb mReverb;
    private int mSessionID;
    private Virtualizer mVirtualizer;

    public SystemEffectHandle(int i) {
        this.mSessionID = i;
    }

    @Override // com.sds.android.ttpod.media.audiofx.IEffectHandle
    public void setEqualizerEnabled(boolean z) {
        try {
            if (SDKVersionUtils.m8372b()) {
                if (this.mEqualizer != null) {
                    this.mEqualizer.setEnabled(z);
                }
                if (this.mEqualizer != null && !z) {
                    this.mEqualizer.release();
                    this.mEqualizer = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sds.android.ttpod.media.audiofx.IEffectHandle
    public void setEqualizer(TTEqualizer.Settings settings) {
        int i = 0;
        try {
            if (SDKVersionUtils.m8372b()) {
                if (this.mEqualizer == null) {
                    this.mEqualizer = new Equalizer(0, this.mSessionID);
                }
                short[] bandLevels = settings.getBandLevels();
                int length = bandLevels.length;
                short s = 0;
                while (i < length) {
                    short s2 = (short) (s + 1);
                    this.mEqualizer.setBandLevel(s, bandLevels[i]);
                    i++;
                    s = s2;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sds.android.ttpod.media.audiofx.IEffectHandle
    public void setBassBoostEnabled(boolean z) {
        try {
            if (SDKVersionUtils.m8372b()) {
                if (this.mBassBoost != null) {
                    this.mBassBoost.setEnabled(z);
                }
                if (this.mBassBoost != null && !z) {
                    this.mBassBoost.release();
                    this.mBassBoost = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sds.android.ttpod.media.audiofx.IEffectHandle
    public void setBassBoost(int i) {
        try {
            if (SDKVersionUtils.m8372b()) {
                if (this.mBassBoost == null) {
                    this.mBassBoost = new BassBoost(0, this.mSessionID);
                }
                this.mBassBoost.setStrength((short) i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sds.android.ttpod.media.audiofx.IEffectHandle
    public void setBoostLimitEnabled(boolean z) {
    }

    @Override // com.sds.android.ttpod.media.audiofx.IEffectHandle
    public void setTrebleBoostEnabled(boolean z) {
    }

    @Override // com.sds.android.ttpod.media.audiofx.IEffectHandle
    public void setTrebleBoost(int i) {
    }

    @Override // com.sds.android.ttpod.media.audiofx.IEffectHandle
    public void setVirtualizerEnabled(boolean z) {
        try {
            if (SDKVersionUtils.m8372b()) {
                if (this.mVirtualizer != null) {
                    this.mVirtualizer.setEnabled(z);
                }
                if (this.mVirtualizer != null && !z) {
                    this.mVirtualizer.release();
                    this.mVirtualizer = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sds.android.ttpod.media.audiofx.IEffectHandle
    public void setVirtualizer(int i) {
        try {
            if (SDKVersionUtils.m8372b()) {
                if (this.mVirtualizer == null) {
                    this.mVirtualizer = new Virtualizer(0, this.mSessionID);
                }
                this.mVirtualizer.setStrength((short) i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sds.android.ttpod.media.audiofx.IEffectHandle
    public void setReverbEnabled(boolean z) {
        try {
            if (SDKVersionUtils.m8372b()) {
                if (this.mReverb != null) {
                    this.mReverb.setEnabled(z);
                }
                if (this.mReverb != null && !z) {
                    this.mReverb.release();
                    this.mReverb = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sds.android.ttpod.media.audiofx.IEffectHandle
    public void setReverb(int i) {
        try {
            if (SDKVersionUtils.m8372b()) {
                if (this.mReverb == null) {
                    this.mReverb = new PresetReverb(0, this.mSessionID);
                }
                this.mReverb.setPreset((short) i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sds.android.ttpod.media.audiofx.IEffectHandle
    public void release() {
        releaseAudioEffect();
    }

    private void releaseAudioEffect() {
        if (this.mEqualizer != null) {
            this.mEqualizer.release();
            this.mEqualizer = null;
        }
        if (this.mBassBoost != null) {
            this.mBassBoost.release();
            this.mBassBoost = null;
        }
        if (this.mReverb != null) {
            this.mReverb.release();
            this.mReverb = null;
        }
        if (this.mVirtualizer != null) {
            this.mVirtualizer.release();
            this.mVirtualizer = null;
        }
    }

    @Override // com.sds.android.ttpod.media.audiofx.IEffectHandle
    public void reset() {
    }
}
