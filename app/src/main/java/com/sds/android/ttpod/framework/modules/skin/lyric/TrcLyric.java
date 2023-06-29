package com.sds.android.ttpod.framework.modules.skin.lyric;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.d.o */
/* loaded from: classes.dex */
public class TrcLyric extends LrcLyric {
    public TrcLyric(String lyricPath) {
        super(lyricPath);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.LrcLyric, com.sds.android.ttpod.framework.modules.skin.p132d.Lyric
    /* renamed from: a */
    public FormattedLyric getFormatterLyric(int lyricDisplayEnum, int width, OnMeasureTextListener onMeasureTextListener) {
        switch (lyricDisplayEnum) {
            case 1:
                return new TrcFormattedLyric(this, width, onMeasureTextListener).get();
            case 2:
                return new TrcMtvFormattedLyric(this, width, onMeasureTextListener).m3624c();
            default:
                return null;
        }
    }
}
