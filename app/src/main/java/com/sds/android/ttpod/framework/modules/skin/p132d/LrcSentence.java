package com.sds.android.ttpod.framework.modules.skin.p132d;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.d.f */
/* loaded from: classes.dex */
public class LrcSentence implements Sentence {

    /* renamed from: a */
    protected int f6618a;

    /* renamed from: b */
    protected String lrcText;

    /* renamed from: c */
    private long lrcTime;

    /* renamed from: d */
    private int index;

    /* renamed from: e */
    private int lrcTextWidth;

    /* renamed from: f */
    private LyricInfo lyricInfo;

    public LrcSentence() {
    }

    public LrcSentence(LrcSentence lrcSentence) {
        this.lrcTime = lrcSentence.lrcTime;
        this.f6618a = lrcSentence.f6618a;
        this.lrcText = lrcSentence.lrcText;
        this.index = lrcSentence.index;
        this.lrcTextWidth = lrcSentence.lrcTextWidth;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Sentence
    /* renamed from: a */
    public void setLyricInfo(LyricInfo lyricInfo) {
        this.lyricInfo = lyricInfo;
    }

    public LrcSentence(long lrcTime, String lrcText, int i, int index, int i3) {
        this.lrcTime = lrcTime;
        this.f6618a = i < 1 ? 1 : i;
        this.lrcText = lrcText;
        this.index = index;
        this.lrcTextWidth = i3;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Sentence
    /* renamed from: a */
    public int getLrcTextWidth() {
        return this.lrcTextWidth;
    }

    /* renamed from: a */
    public void setLrcTextWidth(int lrcTextWidth) {
        this.lrcTextWidth = lrcTextWidth;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Sentence
    /* renamed from: b */
    public int getIndex() {
        return this.index;
    }

    /* renamed from: c */
    protected String getLrcText() {
        return this.lrcText;
    }

    public String toString() {
        long abs = Math.abs(this.lrcTime);
        long j = abs / 1000;
        long j2 = j / 60;
        return String.format("[%s%02d:%02d.%03d]%s\n", this.lrcTime < 0 ? "-" : "", Long.valueOf(j2), Long.valueOf(j - (j2 * 60)), Long.valueOf(abs - (j * 1000)), getLrcText());
    }

    /* renamed from: a */
    public void setLrcTime(long lrcTime) {
        this.lrcTime = lrcTime;
    }

    /* renamed from: b */
    public void mo3614b(int i) {
        if (i < 1) {
            i = 1;
        }
        this.f6618a = i;
    }

    /* renamed from: a */
    public void m3677a(String str) {
        this.lrcText = str;
    }

    /* renamed from: d */
    public long getTimeStamp() {
        return this.lrcTime;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Sentence
    /* renamed from: e */
    public long mo3637e() {
        return (this.lyricInfo != null ? this.lyricInfo.getCurrent() : 0L) + this.lrcTime;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Sentence
    /* renamed from: f */
    public int mo3636f() {
        return this.f6618a;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Sentence
    /* renamed from: g */
    public String getCurrentLrcText() {
        return this.lrcText;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(Sentence sentence) {
        return (int) (mo3637e() - sentence.mo3637e());
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Sentence
    /* renamed from: c */
    public int mo3611c(int i) {
        if (LyricUtils.isEmpty(this.lrcText)) {
            return 0;
        }
        return (this.lrcTextWidth * i) / this.f6618a;
    }
}
