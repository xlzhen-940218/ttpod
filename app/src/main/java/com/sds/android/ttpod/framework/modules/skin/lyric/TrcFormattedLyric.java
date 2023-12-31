package com.sds.android.ttpod.framework.modules.skin.lyric;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.d.n */
/* loaded from: classes.dex */
public class TrcFormattedLyric extends LrcFormattedLyric {
    public TrcFormattedLyric(TrcLyric trcLyric, int i, OnMeasureTextListener onMeasureTextListener) {
        super(trcLyric, i, onMeasureTextListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.LrcFormattedLyric
    /* renamed from: a */
    public void mo3634a(LrcSentence lrcSentence, int i) {
        int a;
        TrcSentence trcSentence = (TrcSentence) lrcSentence;
        if (trcSentence.m3609h() == null) {
            super.mo3634a(lrcSentence, i);
            return;
        }
        String g = trcSentence.getCurrentLrcText();
        long d = trcSentence.getTimeStamp();
        int f = trcSentence.getDuration();
        int mo3467a = this.onMeasureTextListener.measureLrcTextWidth(g);
        if (mo3467a <= this.width) {
            m3632a(g, d, 0, i, f, true, mo3467a);
            return;
        }
        float f2 = mo3467a / this.width;
        int i2 = 0;
        String str = g;
        while (true) {
            if (f2 <= 2.0f) {
                a = m3689b(str);
            } else {
                a = m3692a(str);
            }
            String substring = str.substring(0, a);
            String substring2 = str.substring(a);
            int m3632a = m3632a(substring, d, i2, i, f, false, this.onMeasureTextListener.measureLrcTextWidth(substring));
            d += m3632a;
            f -= m3632a;
            i2 += a;
            int mo3467a2 = this.onMeasureTextListener.measureLrcTextWidth(substring2);
            if (mo3467a2 <= this.width) {
                m3632a(substring2, d, i2, i, f, true, mo3467a2);
                return;
            } else {
                f2 = mo3467a2 / this.width;
                str = substring2;
            }
        }
    }

    /* renamed from: a */
    private int m3632a(String str, long j, int i, int i2, int i3, boolean z, int i4) {
        int i5;
        TrcSentence trcSentence = new TrcSentence(j, str, 0, i2, i4);
        int[] m3617a = ((TrcSentence) this.lrcLyric.getLrcLine(i2)).m3617a(trcSentence, i, str.length());
        if (z) {
            i5 = i3;
        } else if (m3617a[0] >= i3) {
            i5 = i3 >> 1;
        } else {
            i5 = m3617a[0];
        }
        if (i5 <= 0) {
            i5 = 1;
        }
        trcSentence.setDuration(i5);
        if (m3617a[1] > 0) {
            trcSentence.setLrcText(str.substring(1));
        }
        trcSentence.m3618a(this.onMeasureTextListener);
        this.lrcLineList.add(trcSentence);
        return i5;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.LrcFormattedLyric
    /* renamed from: a */
    protected void mo3633a(String str, long j, int i, int i2, int i3) {
        this.lrcLineList.add(new TrcSentence(j, str, i, i2, i3));
    }
}
