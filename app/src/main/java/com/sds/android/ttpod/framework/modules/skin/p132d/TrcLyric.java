package com.sds.android.ttpod.framework.modules.skin.p132d;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.d.o */
/* loaded from: classes.dex */
public class TrcLyric extends LrcLyric {
    public TrcLyric(String str) {
        super(str);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.LrcLyric, com.sds.android.ttpod.framework.modules.skin.p132d.Lyric
    /* renamed from: a */
    public FormattedLyric mo3631a(int i, int i2, OnMeasureTextListener onMeasureTextListener) {
        switch (i) {
            case 1:
                return new TrcFormattedLyric(this, i2, onMeasureTextListener).m3688c();
            case 2:
                return new TrcMtvFormattedLyric(this, i2, onMeasureTextListener).m3624c();
            default:
                return null;
        }
    }
}
