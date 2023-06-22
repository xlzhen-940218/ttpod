package com.sds.android.ttpod.framework.modules.skin.p132d;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.d.q */
/* loaded from: classes.dex */
public class TrcParser extends LrcParser {

    /* renamed from: a */
    private static final Pattern f6640a = Pattern.compile("<(\\d+?)>");

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.LrcParser, com.sds.android.ttpod.framework.modules.skin.p132d.LyricParser
    /* renamed from: a */
    protected Lyric mo3621a(String str) {
        return new TrcLyric(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.LrcParser
    /* renamed from: b */
    public TrcSentence mo3623a(long j, String str) {
        TrcSentence trcSentence = new TrcSentence();
        trcSentence.m3679a(j);
        StringBuilder sb = new StringBuilder();
        Matcher matcher = f6640a.matcher(str);
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
            trcSentence.m3677a(str);
        } else {
            trcSentence.m3677a(sb.toString());
        }
        return trcSentence;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.LrcParser
    /* renamed from: a */
    protected long mo3622a(LrcSentence lrcSentence, long j) {
        long m3676d = lrcSentence.m3676d();
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
            j2 += it.next().m3603c();
        }
        return j2;
    }
}
