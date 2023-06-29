package com.sds.android.ttpod.framework.modules.skin.lyric;

import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.d.d */
/* loaded from: classes.dex */
public class LrcMtvFormattedLyric implements FormattedLyric {

    /* renamed from: a */
    private final LrcLyric lrcLyric;

    /* renamed from: b */
    private final OnMeasureTextListener onMeasureTextListener;

    /* renamed from: c */
    private ArrayList<LrcSentence> lrcLineList;

    /* renamed from: d */
    private int index;

    public LrcMtvFormattedLyric(LrcLyric lrcLyric, int i, OnMeasureTextListener onMeasureTextListener) {
        this.lrcLyric = lrcLyric;
        this.onMeasureTextListener = onMeasureTextListener;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.FormattedLyric
    /* renamed from: a */
    public int getLrcLineSize() {
        return this.lrcLineList.size();
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.FormattedLyric
    /* renamed from: a */
    public Sentence getCurrentIndex(int i) {
        if (i < 0) {
            return null;
        }
        return this.lrcLineList.get(i);
    }

    /* renamed from: c */
    public FormattedLyric m3683c() {
        long j;
        int i;
        if (this.lrcLyric == null) {
            return null;
        }
        int mo3672b = this.lrcLyric.getLrcLineListSize();
        long startTime = 0;
        this.lrcLineList = new ArrayList<>(mo3672b);
        int i2 = 0;
        int i3 = 0;
        while (i2 < mo3672b) {
            LrcSentence lrcLine = this.lrcLyric.getLrcLine(i2);
            if (i2 == mo3672b - 1 || lrcLine.getCurrentLrcText().length() > 0) {
                m3684a(lrcLine);
                int i4 = i3;
                j = startTime;
                i = i4;
            } else {
                if (i3 == 0) {
                    startTime = lrcLine.getTimeStamp();
                }
                int duration = i3 + lrcLine.getDuration();
                if (this.lrcLyric.getLrcLine(i2 + 1).getCurrentLrcText().length() > 0) {
                    if (duration >= 7000) {
                        addEmpty(startTime, duration);
                    }
                    j = startTime;
                    i = 0;
                } else {
                    j = startTime;
                    i = duration;
                }
            }
            i2++;
            int i5 = i;
            startTime = j;
            i3 = i5;
        }
        LyricUtils.setLyricInfoToLyricLineList(this.lrcLineList, this.lrcLyric.getLyricInfo());
        return this;
    }

    /* renamed from: a */
    private void m3684a(LrcSentence lrcSentence) {
        LrcSentence lrcSentence2 = new LrcSentence(lrcSentence);
        this.lrcLineList.add(lrcSentence2);
        lrcSentence2.setLrcTextWidth(this.onMeasureTextListener.measureLrcTextWidth(lrcSentence.getCurrentLrcText()));
    }

    /* renamed from: a */
    private void addEmpty(long startTime, int duration) {
        this.lrcLineList.add(new LrcSentence(startTime, "", duration, 0, 0));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<LrcSentence> it = this.lrcLineList.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
        }
        return sb.toString();
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.FormattedLyric
    /* renamed from: a */
    public int getIndexByLrcTime(long lrcTime) {
        this.index = LyricUtils.getIndex(this.lrcLineList, lrcTime);
        return this.index;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.FormattedLyric
    /* renamed from: b */
    public int getCurrentIndex() {
        return this.index;
    }
}
