package com.sds.android.ttpod.framework.modules.skin.p132d;

import java.util.Collections;
import java.util.List;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.d.k */
/* loaded from: classes.dex */
public final class LyricUtils {

    /* renamed from: a */
    private static final LrcSentence f6635a = new LrcSentence(0, "", 0, 0, 0);

    /* renamed from: a */
    public static boolean m3643a(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    /* renamed from: a */
    public static boolean m3644a(char c) {
        return ('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z');
    }

    /* renamed from: a */
    public static int m3642a(List<? extends Sentence> list, long j) {
        f6635a.setLrcTime(j);
        int binarySearch = Collections.binarySearch(list, f6635a);
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 2;
        }
        int size = list.size();
        return binarySearch >= size ? size - 1 : binarySearch;
    }

    /* renamed from: a */
    public static void m3641a(List<? extends Sentence> list, LyricInfo lyricInfo) {
        for (Sentence sentence : list) {
            sentence.getLyricInfo(lyricInfo);
        }
    }
}
