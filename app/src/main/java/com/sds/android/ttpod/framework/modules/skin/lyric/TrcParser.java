package com.sds.android.ttpod.framework.modules.skin.lyric;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.d.q */
/* loaded from: classes.dex */
public class TrcParser extends LrcParser {

    /* renamed from: a */
    private static final Pattern PATTERN = Pattern.compile("<(\\d+?)>");

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.LrcParser, com.sds.android.ttpod.framework.modules.skin.p132d.LyricParser
    /* renamed from: a */
    protected Lyric getLyric(String lyricPath) {
        return new TrcLyric(lyricPath);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.LrcParser
    /* renamed from: b */
    public TrcSentence mo3623a(long j, String str) {
        TrcSentence trcSentence = new TrcSentence();
        trcSentence.setStartTime(j);
        StringBuilder sb = new StringBuilder();
        Matcher matcher = PATTERN.matcher(str);
        int i = 0;
        int i2 = 0;
        while (matcher.find()) {
            if (i2 > 0) {
                String substring = str.substring(i, matcher.start());
                sb.append(substring);
                trcSentence.m3616a(new TrcTimeRegion(substring.length(), i2));
            }
            try {
                i2 = Integer.parseInt(matcher.group(1).trim());
            } catch (NumberFormatException e) {
                i2 = 0;
            }
            i = matcher.end();
        }
        if (i2 > 0) {
            String substring2 = str.substring(i);
            sb.append(substring2);
            trcSentence.m3616a(new TrcTimeRegion(substring2.length(), i2));
        }
        if (trcSentence.m3609h() == null) {
            trcSentence.setLrcText(str);
        } else {
            trcSentence.setLrcText(sb.toString());
        }
        return trcSentence;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.LrcParser
    /* renamed from: a */
    protected long mo3622a(LrcSentence lrcSentence, long j) {
        long m3676d = lrcSentence.getTimeStamp();
        long j2 = 0;
        ArrayList<TrcTimeRegion> m3609h = ((TrcSentence) lrcSentence).m3609h();
        if (m3609h == null) {
            if (m3676d > j) {
                return 5000L;
            }
            return j - m3676d;
        }
        Iterator<TrcTimeRegion> it = m3609h.iterator();
        while (it.hasNext()) {
            j2 += it.next().getDuration();
        }
        return j2;
    }
}
