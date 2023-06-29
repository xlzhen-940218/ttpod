package com.sds.android.ttpod.framework.modules.skin.lyric;

import androidx.annotation.NonNull;

import java.io.Serializable;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.d.s */
/* loaded from: classes.dex */
public final class TrcTimeRegion implements Serializable {

    /* renamed from: a */
    private final int charsCount;

    /* renamed from: b */
    private final int duration;

    /* renamed from: c */
    private int textWidth;

    /* renamed from: a */
    public int getTextWidth() {
        return this.textWidth;
    }

    /* renamed from: a */
    public void setTextWidth(int textWidth) {
        this.textWidth = textWidth;
    }

    public TrcTimeRegion(int charsCount, int duration) {
        this.charsCount = charsCount;
        this.duration = Math.max(duration, 1);
    }

    @NonNull
    public String toString() {
        return "TrcTimeRegion [mCharsCount=" + this.charsCount + ", mDuration=" + this.duration
                + ", mTextWidth=" + this.textWidth + "]";
    }

    /* renamed from: b */
    public int getCharsCount() {
        return this.charsCount;
    }

    /* renamed from: c */
    public int getDuration() {
        return this.duration;
    }
}
