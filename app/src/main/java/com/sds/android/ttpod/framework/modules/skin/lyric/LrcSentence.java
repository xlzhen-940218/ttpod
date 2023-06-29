package com.sds.android.ttpod.framework.modules.skin.lyric;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.d.f */
/* loaded from: classes.dex */
public class LrcSentence implements Sentence {

    /* renamed from: a */
    protected int duration;

    /* renamed from: b */
    protected String lrcText;

    /* renamed from: c */
    private long startTime;

    /* renamed from: d */
    private int index;

    /* renamed from: e */
    private int lrcTextWidth;

    /* renamed from: f */
    private LyricInfo lyricInfo;

    public LrcSentence() {
    }

    public LrcSentence(LrcSentence lrcSentence) {
        this.startTime = lrcSentence.startTime;
        this.duration = lrcSentence.duration;
        this.lrcText = lrcSentence.lrcText;
        this.index = lrcSentence.index;
        this.lrcTextWidth = lrcSentence.lrcTextWidth;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Sentence
    /* renamed from: a */
    public void setLyricInfo(LyricInfo lyricInfo) {
        this.lyricInfo = lyricInfo;
    }

    public LrcSentence(long startTime, String lrcText, int duration, int index, int lrcTextWidth) {
        this.startTime = startTime;
        this.duration = duration < 1 ? 1 : duration;
        this.lrcText = lrcText;
        this.index = index;
        this.lrcTextWidth = lrcTextWidth;
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
        long abs = Math.abs(this.startTime);
        long second = abs / 1000;
        long minute = second / 60;
        return String.format("[%s%02d:%02d.%03d]%s\n", this.startTime < 0 ? "-" : ""
                , Long.valueOf(minute), Long.valueOf(second - (minute * 60))
                , Long.valueOf(abs - (second * 1000)), getLrcText());
    }

    /* renamed from: a */
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    /* renamed from: b */
    public void setDuration(int duration) {
        if (duration < 1) {
            duration = 1;
        }
        this.duration = duration;
    }

    /* renamed from: a */
    public void setLrcText(String lrcText) {
        this.lrcText = lrcText;
    }

    /* renamed from: d */
    public long getTimeStamp() {
        return this.startTime;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Sentence
    /* renamed from: e */
    public long getNextTime() {
        return (this.lyricInfo != null ? this.lyricInfo.getCurrentTime() : 0L) + this.startTime;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Sentence
    /* renamed from: f */
    public int getDuration() {
        return this.duration;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Sentence
    /* renamed from: g */
    public String getCurrentLrcText() {
        return this.lrcText;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(Sentence sentence) {
        return (int) (getNextTime() - sentence.getNextTime());
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Sentence
    /* renamed from: c */
    public int mo3611c(int i) {
        if (LyricUtils.isEmpty(this.lrcText)) {
            return 0;
        }
        return (this.lrcTextWidth * i) / this.duration;
    }
}
