package com.sds.android.ttpod.framework.modules.skin.p132d;

import com.sds.android.ttpod.framework.modules.skin.p130c.DateTimeUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.d.e */
/* loaded from: classes.dex */
public class LrcParser extends LyricParser {

    /* renamed from: a */
    private static final Pattern PATTERN = Pattern.compile("\\[(.+?)\\]");

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.LyricParser
    /* renamed from: a */
    protected Lyric getLyric(String str) {
        return new LrcLyric(str);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.LyricParser
    /* renamed from: a */
    protected void mo3649a(Lyric lyric, String lyricLine) {
        LrcLyric lrcLyric = (LrcLyric) lyric;
        int i = 0;
        ArrayList<Long> arrayList = new ArrayList<>();
        ArrayList<LrcSentence> lrcLineList = lrcLyric.getLrcLineList();
        Matcher matcher = PATTERN.matcher(lyricLine);
        while (matcher.find()) {
            if (!arrayList.isEmpty()) {
                String trim = lyricLine.substring(i, matcher.start()).trim();
                if (trim.length() > 0) {
                    m3681a(lrcLineList, arrayList, trim);
                    arrayList.clear();
                }
            }
            String group = matcher.group(1);
            if (!m3682a(lrcLyric.getLyricInfo(), group)) {
                long m3746a = DateTimeUtils.m3746a(group);
                if (m3746a != Long.MIN_VALUE) {
                    arrayList.add(Long.valueOf(m3746a));
                }
            }
            i = matcher.end();
        }
        if (!arrayList.isEmpty()) {
            m3681a(lrcLineList, arrayList, lyricLine.substring(i).trim());
        }
    }

    /* renamed from: a */
    private boolean m3682a(LyricInfo lyricInfo, String str) {
        int indexOf = str.indexOf(":");
        if (indexOf <= 0) {
            return true;
        }
        String trim = str.substring(0, indexOf).trim();
        String trim2 = str.substring(indexOf + 1).trim();
        if (trim.equalsIgnoreCase("ti")) {
            lyricInfo.setTitle(trim2);
            return true;
        } else if (trim.equalsIgnoreCase("ar")) {
            lyricInfo.setSinger(trim2);
            return true;
        } else if (trim.equalsIgnoreCase("al")) {
            lyricInfo.setAlbum(trim2);
            return true;
        } else if (trim.equalsIgnoreCase("by")) {
            lyricInfo.setByName(trim2);
            return true;
        } else if (trim.equalsIgnoreCase("offset")) {
            try {
                long parseLong = Long.parseLong(trim2);
                lyricInfo.setOffset(parseLong);
                lyricInfo.setCurrent(parseLong);
                return true;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return true;
            }
        } else if (trim.equalsIgnoreCase("total")) {
            try {
                lyricInfo.setTotal(Math.max(0L, Long.parseLong(trim2)));
                return true;
            } catch (NumberFormatException e2) {
                e2.printStackTrace();
                return true;
            }
        } else {
            return false;
        }
    }

    /* renamed from: a */
    private void m3681a(ArrayList<LrcSentence> arrayList, ArrayList<Long> arrayList2, String str) {
        Iterator<Long> it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList.add(mo3623a(it.next().longValue(), str));
        }
    }

    /* renamed from: a */
    protected LrcSentence mo3623a(long j, String str) {
        LrcSentence lrcSentence = new LrcSentence();
        lrcSentence.setLrcTime(j);
        lrcSentence.m3677a(str);
        return lrcSentence;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.LyricParser
    /* renamed from: a */
    protected void mo3650a(Lyric lyric) {
        long mo3622a;
        LrcLyric lrcLyric = (LrcLyric) lyric;
        ArrayList<LrcSentence> mo3667h = lrcLyric.getLrcLineList();
        int size = mo3667h.size() - 1;
        LyricInfo mo3668g = lrcLyric.getLyricInfo();
        for (int i = 0; i <= size; i++) {
            LrcSentence lrcSentence = mo3667h.get(i);
            long m3676d = lrcSentence.getTimeStamp();
            if (i < size) {
                mo3622a = mo3667h.get(i + 1).getTimeStamp() - m3676d;
            } else {
                mo3622a = mo3622a(lrcSentence, lyric.getLrcLastTime());
            }
            lrcSentence.mo3614b(mo3622a > 0 ? (int) mo3622a : 1);
            lrcSentence.setLyricInfo(mo3668g);
        }
    }

    /* renamed from: a */
    protected long mo3622a(LrcSentence lrcSentence, long j) {
        long m3676d = lrcSentence.getTimeStamp();
        if (m3676d > j) {
            return 5000L;
        }
        return j - m3676d;
    }
}
