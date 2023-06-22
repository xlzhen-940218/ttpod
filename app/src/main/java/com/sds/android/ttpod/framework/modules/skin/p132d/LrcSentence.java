package com.sds.android.ttpod.framework.modules.skin.p132d;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.d.f */
/* loaded from: classes.dex */
public class LrcSentence implements Sentence {

    /* renamed from: a */
    protected int f6618a;

    /* renamed from: b */
    protected String f6619b;

    /* renamed from: c */
    private long f6620c;

    /* renamed from: d */
    private int f6621d;

    /* renamed from: e */
    private int f6622e;

    /* renamed from: f */
    private LyricInfo f6623f;

    public LrcSentence() {
    }

    public LrcSentence(LrcSentence lrcSentence) {
        this.f6620c = lrcSentence.f6620c;
        this.f6618a = lrcSentence.f6618a;
        this.f6619b = lrcSentence.f6619b;
        this.f6621d = lrcSentence.f6621d;
        this.f6622e = lrcSentence.f6622e;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Sentence
    /* renamed from: a */
    public void mo3639a(LyricInfo lyricInfo) {
        this.f6623f = lyricInfo;
    }

    public LrcSentence(long j, String str, int i, int i2, int i3) {
        this.f6620c = j;
        this.f6618a = i < 1 ? 1 : i;
        this.f6619b = str;
        this.f6621d = i2;
        this.f6622e = i3;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Sentence
    /* renamed from: a */
    public int mo3640a() {
        return this.f6622e;
    }

    /* renamed from: a */
    public void m3680a(int i) {
        this.f6622e = i;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Sentence
    /* renamed from: b */
    public int mo3638b() {
        return this.f6621d;
    }

    /* renamed from: c */
    protected String mo3612c() {
        return this.f6619b;
    }

    public String toString() {
        long abs = Math.abs(this.f6620c);
        long j = abs / 1000;
        long j2 = j / 60;
        return String.format("[%s%02d:%02d.%03d]%s\n", this.f6620c < 0 ? "-" : "", Long.valueOf(j2), Long.valueOf(j - (j2 * 60)), Long.valueOf(abs - (j * 1000)), mo3612c());
    }

    /* renamed from: a */
    public void m3679a(long j) {
        this.f6620c = j;
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
        this.f6619b = str;
    }

    /* renamed from: d */
    public long m3676d() {
        return this.f6620c;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Sentence
    /* renamed from: e */
    public long mo3637e() {
        return (this.f6623f != null ? this.f6623f.m3656d() : 0L) + this.f6620c;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Sentence
    /* renamed from: f */
    public int mo3636f() {
        return this.f6618a;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Sentence
    /* renamed from: g */
    public String mo3635g() {
        return this.f6619b;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(Sentence sentence) {
        return (int) (mo3637e() - sentence.mo3637e());
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Sentence
    /* renamed from: c */
    public int mo3611c(int i) {
        if (LyricUtils.m3643a(this.f6619b)) {
            return 0;
        }
        return (this.f6622e * i) / this.f6618a;
    }
}
