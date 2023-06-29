package com.sds.android.ttpod.framework.modules.skin.p132d;

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
    public Sentence getLrcLineIndex(int i) {
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
        long j2 = 0;
        this.lrcLineList = new ArrayList<>(mo3672b);
        int i2 = 0;
        int i3 = 0;
        while (i2 < mo3672b) {
            LrcSentence m3687b = this.lrcLyric.getLrcLine(i2);
            if (i2 == mo3672b - 1 || m3687b.getCurrentLrcText().length() > 0) {
                m3684a(m3687b);
                int i4 = i3;
                j = j2;
                i = i4;
            } else {
                if (i3 == 0) {
                    j2 = m3687b.getTimeStamp();
                }
                int mo3636f = i3 + m3687b.mo3636f();
                if (this.lrcLyric.getLrcLine(i2 + 1).getCurrentLrcText().length() > 0) {
                    if (mo3636f >= 7000) {
                        m3685a(j2, mo3636f);
                    }
                    j = j2;
                    i = 0;
                } else {
                    j = j2;
                    i = mo3636f;
                }
            }
            i2++;
            int i5 = i;
            j2 = j;
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
    private void m3685a(long j, int i) {
        this.lrcLineList.add(new LrcSentence(j, "", i, 0, 0));
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
    public int getLrcLineIndex() {
        return this.index;
    }
}
