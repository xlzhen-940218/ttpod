package com.sds.android.ttpod.framework.modules.skin.p132d;

import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.d.p */
/* loaded from: classes.dex */
public class TrcMtvFormattedLyric implements FormattedLyric {

    /* renamed from: a */
    private final TrcLyric f6636a;

    /* renamed from: b */
    private final OnMeasureTextListener f6637b;

    /* renamed from: c */
    private ArrayList<TrcSentence> f6638c;

    /* renamed from: d */
    private int f6639d;

    public TrcMtvFormattedLyric(TrcLyric trcLyric, int i, OnMeasureTextListener onMeasureTextListener) {
        this.f6636a = trcLyric;
        this.f6637b = onMeasureTextListener;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.FormattedLyric
    /* renamed from: a */
    public Sentence getLrcLineIndex(int i) {
        if (i < 0) {
            return null;
        }
        return this.f6638c.get(i);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.FormattedLyric
    /* renamed from: a */
    public int getLrcLineSize() {
        return this.f6638c.size();
    }

    /* renamed from: c */
    public FormattedLyric m3624c() {
        int i;
        if (this.f6636a == null) {
            return null;
        }
        int b = this.f6636a.mo3672b();
        long j = 0;
        int i2 = 0;
        this.f6638c = new ArrayList<>(b);
        for (int i3 = 0; i3 < b; i3++) {
            TrcSentence trcSentence = (TrcSentence) this.f6636a.m3687b(i3);
            if (i3 == b - 1) {
                m3626a(trcSentence);
            } else {
                int f = trcSentence.mo3636f();
                LrcSentence b2 = this.f6636a.m3687b(i3 + 1);
                if (trcSentence.getCurrentLrcText().length() > 0) {
                    m3626a(trcSentence);
                    f = (int) (b2.m3676d() - (trcSentence.m3676d() + trcSentence.m3608i()));
                }
                if (f > 0) {
                    j = i2 == 0 ? b2.m3676d() - f : j;
                    i = i2 + f;
                } else {
                    i = i2;
                }
                if (b2.getCurrentLrcText().length() > 0) {
                    if (i >= 7000) {
                        m3627a(j, i);
                    }
                    i2 = 0;
                } else {
                    i2 = i;
                }
            }
        }
        LyricUtils.m3641a(this.f6638c, this.f6636a.mo3668g());
        return this;
    }

    /* renamed from: a */
    private void m3626a(TrcSentence trcSentence) {
        TrcSentence trcSentence2 = new TrcSentence(trcSentence);
        this.f6638c.add(trcSentence2);
        trcSentence2.m3680a(this.f6637b.mo3467a(trcSentence.getCurrentLrcText()));
        trcSentence2.m3618a(this.f6637b);
    }

    /* renamed from: a */
    private void m3627a(long j, int i) {
        this.f6638c.add(new TrcSentence(j, "", i, 0, 0));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<TrcSentence> it = this.f6638c.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
        }
        return sb.toString();
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.FormattedLyric
    /* renamed from: b */
    public int getLrcLineIndex() {
        return this.f6639d;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.FormattedLyric
    /* renamed from: a */
    public int mo3628a(long j) {
        this.f6639d = LyricUtils.m3642a(this.f6638c, j);
        return this.f6639d;
    }
}
