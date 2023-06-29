package com.sds.android.ttpod.framework.modules.skin.lyric;

import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.d.p */
/* loaded from: classes.dex */
public class TrcMtvFormattedLyric implements FormattedLyric {

    /* renamed from: a */
    private final TrcLyric trcLyric;

    /* renamed from: b */
    private final OnMeasureTextListener onMeasureTextListener;

    /* renamed from: c */
    private ArrayList<TrcSentence> trcLineList;

    /* renamed from: d */
    private int index;

    public TrcMtvFormattedLyric(TrcLyric trcLyric, int i, OnMeasureTextListener onMeasureTextListener) {
        this.trcLyric = trcLyric;
        this.onMeasureTextListener = onMeasureTextListener;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.FormattedLyric
    /* renamed from: a */
    public Sentence getCurrentIndex(int i) {
        if (i < 0) {
            return null;
        }
        return this.trcLineList.get(i);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.FormattedLyric
    /* renamed from: a */
    public int getLrcLineSize() {
        return this.trcLineList.size();
    }

    /* renamed from: c */
    public FormattedLyric m3624c() {
        int i;
        if (this.trcLyric == null) {
            return null;
        }
        int b = this.trcLyric.getLrcLineListSize();
        long j = 0;
        int i2 = 0;
        this.trcLineList = new ArrayList<>(b);
        for (int i3 = 0; i3 < b; i3++) {
            TrcSentence trcSentence = (TrcSentence) this.trcLyric.getLrcLine(i3);
            if (i3 == b - 1) {
                m3626a(trcSentence);
            } else {
                int f = trcSentence.getDuration();
                LrcSentence b2 = this.trcLyric.getLrcLine(i3 + 1);
                if (trcSentence.getCurrentLrcText().length() > 0) {
                    m3626a(trcSentence);
                    f = (int) (b2.getTimeStamp() - (trcSentence.getTimeStamp() + trcSentence.m3608i()));
                }
                if (f > 0) {
                    j = i2 == 0 ? b2.getTimeStamp() - f : j;
                    i = i2 + f;
                } else {
                    i = i2;
                }
                if (b2.getCurrentLrcText().length() > 0) {
                    if (i >= 7000) {
                        addEmpty(j, i);
                    }
                    i2 = 0;
                } else {
                    i2 = i;
                }
            }
        }
        LyricUtils.setLyricInfoToLyricLineList(this.trcLineList, this.trcLyric.getLyricInfo());
        return this;
    }

    /* renamed from: a */
    private void m3626a(TrcSentence trcSentence) {
        TrcSentence trcSentence2 = new TrcSentence(trcSentence);
        this.trcLineList.add(trcSentence2);
        trcSentence2.setLrcTextWidth(this.onMeasureTextListener.measureLrcTextWidth(trcSentence.getCurrentLrcText()));
        trcSentence2.m3618a(this.onMeasureTextListener);
    }

    /* renamed from: a */
    private void addEmpty(long startTime, int duration) {
        this.trcLineList.add(new TrcSentence(startTime, "", duration, 0, 0));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<TrcSentence> it = this.trcLineList.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
        }
        return sb.toString();
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.FormattedLyric
    /* renamed from: b */
    public int getCurrentIndex() {
        return this.index;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.FormattedLyric
    /* renamed from: a */
    public int getIndexByLrcTime(long j) {
        this.index = LyricUtils.getIndex(this.trcLineList, j);
        return this.index;
    }
}
