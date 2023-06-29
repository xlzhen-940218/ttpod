package com.sds.android.ttpod.framework.modules.skin.p132d;

import java.util.Collections;
import java.util.List;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.d.k */
/* loaded from: classes.dex */
public final class LyricUtils {

    /* renamed from: a */
    private static final LrcSentence LRC_SENTENCE = new LrcSentence(0, "", 0, 0, 0);

    /* renamed from: a */
    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    /* renamed from: a */
    public static boolean isAToZ(char c) {
        return ('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z');
    }

    /* renamed from: a */
    public static int getIndex(List<? extends Sentence> list, long lrcTime) {
        LRC_SENTENCE.setLrcTime(lrcTime);
        int index = Collections.binarySearch(list, LRC_SENTENCE);
        if (index < 0) {
            index = (-index) - 2;
        }
        int size = list.size();
        return index >= size ? size - 1 : index;
    }

    /* renamed from: a */
    public static void setLyricInfoToLyricLineList(List<? extends Sentence> list, LyricInfo lyricInfo) {
        for (Sentence sentence : list) {
            sentence.setLyricInfo(lyricInfo);
        }
    }
}
