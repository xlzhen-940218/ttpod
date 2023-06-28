package com.sds.android.ttpod.framework.modules.skin.p132d;

import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.d.d */
/* loaded from: classes.dex */
public class LrcMtvFormattedLyric implements FormattedLyric {

    /* renamed from: a */
    private final LrcLyric f6613a;

    /* renamed from: b */
    private final OnMeasureTextListener f6614b;

    /* renamed from: c */
    private ArrayList<LrcSentence> f6615c;

    /* renamed from: d */
    private int f6616d;

    public LrcMtvFormattedLyric(LrcLyric lrcLyric, int i, OnMeasureTextListener onMeasureTextListener) {
        this.f6613a = lrcLyric;
        this.f6614b = onMeasureTextListener;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.FormattedLyric
    /* renamed from: a */
    public int getLrcLineSize() {
        return this.f6615c.size();
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.FormattedLyric
    /* renamed from: a */
    public Sentence getLrcLineIndex(int i) {
        if (i < 0) {
            return null;
        }
        return this.f6615c.get(i);
    }

    /* renamed from: c */
    public FormattedLyric m3683c() {
        long j;
        int i;
        if (this.f6613a == null) {
            return null;
        }
        int mo3672b = this.f6613a.mo3672b();
        long j2 = 0;
        this.f6615c = new ArrayList<>(mo3672b);
        int i2 = 0;
        int i3 = 0;
        while (i2 < mo3672b) {
            LrcSentence m3687b = this.f6613a.m3687b(i2);
            if (i2 == mo3672b - 1 || m3687b.getCurrentLrcText().length() > 0) {
                m3684a(m3687b);
                int i4 = i3;
                j = j2;
                i = i4;
            } else {
                if (i3 == 0) {
                    j2 = m3687b.m3676d();
                }
                int mo3636f = i3 + m3687b.mo3636f();
                if (this.f6613a.m3687b(i2 + 1).getCurrentLrcText().length() > 0) {
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
        LyricUtils.m3641a(this.f6615c, this.f6613a.mo3668g());
        return this;
    }

    /* renamed from: a */
    private void m3684a(LrcSentence lrcSentence) {
        LrcSentence lrcSentence2 = new LrcSentence(lrcSentence);
        this.f6615c.add(lrcSentence2);
        lrcSentence2.m3680a(this.f6614b.mo3467a(lrcSentence.getCurrentLrcText()));
    }

    /* renamed from: a */
    private void m3685a(long j, int i) {
        this.f6615c.add(new LrcSentence(j, "", i, 0, 0));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<LrcSentence> it = this.f6615c.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
        }
        return sb.toString();
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.FormattedLyric
    /* renamed from: a */
    public int mo3628a(long j) {
        this.f6616d = LyricUtils.m3642a(this.f6615c, j);
        return this.f6616d;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.FormattedLyric
    /* renamed from: b */
    public int getLrcLineIndex() {
        return this.f6616d;
    }
}
