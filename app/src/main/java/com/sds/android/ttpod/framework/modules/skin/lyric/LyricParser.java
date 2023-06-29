package com.sds.android.ttpod.framework.modules.skin.lyric;

import com.sds.android.ttpod.framework.p106a.CodeIdentifyInputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.d.j */
/* loaded from: classes.dex */
public abstract class LyricParser {

    /* renamed from: a */
    private static final HashMap<String, Class<? extends LyricParser>> lrcTrcClassMaps = new HashMap<>();

    /* renamed from: a */
    protected abstract Lyric getLyric(String str);

    /* renamed from: a */
    protected abstract void mo3650a(Lyric lyric);

    /* renamed from: a */
    protected abstract void mo3649a(Lyric lyric, String str) throws Exception;

    static {
        lrcTrcClassMaps.put("lrc", LrcParser.class);
        lrcTrcClassMaps.put("trc", TrcParser.class);
    }

    /* renamed from: b */
    public static Lyric m3647b(String str) {
        try {
            return lrcTrcClassMaps.get(getName(str)).newInstance().m3646c(str);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: d */
    private static String getName(String lyricPath) {
        int lastIndexOf = lyricPath.lastIndexOf(".");
        if (lastIndexOf <= -1) {
            return "";
        }
        return lyricPath.substring(lastIndexOf + 1);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0034 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected final Lyric m3646c(String lyricPath) {
        BufferedReader bufferedReader;
        Throwable th;
        Exception e;
        Lyric lyric = null;
        try {
            bufferedReader = new BufferedReader(new CodeIdentifyInputStreamReader(new FileInputStream(lyricPath)));
        } catch (Exception e1) {
            e = e1;
            bufferedReader = null;
        } catch (Throwable th2) {
            bufferedReader = null;
            th = th2;
            if (bufferedReader != null) {
            }
            //throw th;
        }
        try {
            try {
                lyric = m3648a(bufferedReader, lyricPath);
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                //throw th;
            }
        } catch (Exception e4) {
            e = e4;
            e.printStackTrace();
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            return lyric;
        }
        return lyric;
    }

    /* renamed from: a */
    public final Lyric m3648a(BufferedReader bufferedReader, String lyricPath) throws Exception {
        Lyric mo3621a = getLyric(lyricPath);
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                String trim = readLine.trim();
                if (trim.length() > 0) {
                    mo3649a(mo3621a, trim);
                }
            } else {
                Collections.sort(mo3621a.getLrcLineList());
                mo3650a(mo3621a);
                return mo3621a;
            }
        }
    }
}
