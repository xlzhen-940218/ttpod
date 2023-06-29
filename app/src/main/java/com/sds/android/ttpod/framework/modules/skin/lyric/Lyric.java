package com.sds.android.ttpod.framework.modules.skin.lyric;

import java.util.List;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.d.g */
/* loaded from: classes.dex */
public interface Lyric {
    /* renamed from: a */
    int syncToCurrent();

    /* renamed from: a */
    int next(int i);

    /* renamed from: a */
    FormattedLyric getFormatterLyric(int lyricDisplayEnum, int width, OnMeasureTextListener onMeasureTextListener);

    /* renamed from: a */
    boolean mo3673a(boolean z);

    /* renamed from: b */
    int getLrcLineListSize();

    /* renamed from: d */
    long getOffset();

    /* renamed from: e */
    long getCurrent();

    /* renamed from: f */
    long getLrcLastTime();

    /* renamed from: g */
    LyricInfo getLyricInfo();

    /* renamed from: h */
    List<? extends Sentence> getLrcLineList();
}
