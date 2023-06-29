package com.sds.android.ttpod.framework.modules.skin.lyric;

import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.d.c */
/* loaded from: classes.dex */
public class LrcLyric implements Lyric {

    /* renamed from: a */
    private LyricInfo lyricInfo = new LyricInfo();

    /* renamed from: b */
    private ArrayList<LrcSentence> lrcLineList = new ArrayList<>(32);

    public int hashCode() {
        return ((this.lyricInfo.hashCode() + 31) * 31) + this.lrcLineList.size();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        LrcLyric lrcLyric = (LrcLyric) obj;
        if (this.lyricInfo.equals(lrcLyric.lyricInfo)) {
            return this.lrcLineList.size() == lrcLyric.lrcLineList.size();
        }
        return false;
    }

    public LrcLyric(String lyricPath) {
        this.lyricInfo.setLyricPath(lyricPath);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.lyricInfo.toString());
        int size = this.lrcLineList.size();
        for (int i = 0; i < size; i++) {
            LrcSentence lrcSentence = this.lrcLineList.get(i);
            sb.append(lrcSentence.toString());
            if (i <= 2) {
                LogUtils.debug("LrcLyric", "Lyric lookLyricPic toString idx=%d sentence=%s timestamp=%d"
                        , Integer.valueOf(i), lrcSentence.toString(), Long.valueOf(lrcSentence.getTimeStamp()));
            }
        }
        return sb.toString();
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Lyric
    /* renamed from: a */
    public int next(int i) {
        return this.lyricInfo.next(i);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Lyric
    /* renamed from: a */
    public int syncToCurrent() {
        return this.lyricInfo.syncToCurrent();
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0055 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Lyric
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean mo3673a(boolean z) {
        FileOutputStream fileOutputStream;
        Exception exc;
        Throwable th;
        boolean z2 = true;
        if (this.lyricInfo.syncToOffset()) {
            String lyricPath = this.lyricInfo.getLyricPath();
            if (z && !LyricUtils.isEmpty(lyricPath)) {
                FileUtils.exists(lyricPath);
                FileOutputStream fileOutputStream2 = null;
                try {
                    fileOutputStream = new FileOutputStream(lyricPath);
                    try {
                        fileOutputStream.write(LyricConst.UTF_8);
                        fileOutputStream.write(toString().getBytes(StandardCharsets.UTF_8));
                        fileOutputStream.flush();
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e2) {
                        fileOutputStream2 = fileOutputStream;
                        exc = e2;
                        z2 = false;
                        try {
                            exc.printStackTrace();
                            if (fileOutputStream2 != null) {
                                try {
                                    fileOutputStream2.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                            return z2;
                        } catch (Throwable th1) {
                            th = th1;
                            fileOutputStream = fileOutputStream2;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileOutputStream != null) {
                        }
                        throw th;
                    }
                } catch (Exception e5) {
                    exc = e5;
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = null;
                }
            }
        }
        return z2;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Lyric
    /* renamed from: b */
    public int getLrcLineListSize() {
        return this.lrcLineList.size();
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Lyric
    /* renamed from: c */
    public ArrayList<LrcSentence> getLrcLineList() {
        return this.lrcLineList;
    }

    /* renamed from: b */
    public LrcSentence getLrcLine(int i) {
        return this.lrcLineList.get(i);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Lyric
    /* renamed from: d */
    public long getOffset() {
        return this.lyricInfo.getOffsetTime();
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Lyric
    /* renamed from: e */
    public long getCurrent() {
        return this.lyricInfo.getCurrentTime();
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Lyric
    /* renamed from: f */
    public long getLrcLastTime() {
        long total = this.lyricInfo.getTotalTime();
        if (total == 0 && getLrcLineListSize() > 0) {
            return this.lrcLineList.get(getLrcLineListSize() - 1).getTimeStamp() + 5000;
        }
        return total;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Lyric
    /* renamed from: g */
    public LyricInfo getLyricInfo() {
        return this.lyricInfo;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Lyric
    /* renamed from: a */
    public FormattedLyric getFormatterLyric(int lyricDisplayEnum, int width, OnMeasureTextListener onMeasureTextListener) {
        switch (lyricDisplayEnum) {
            case 1:
                return new LrcFormattedLyric(this, width, onMeasureTextListener).get();
            case 2:
                return new LrcMtvFormattedLyric(this, width, onMeasureTextListener).m3683c();
            default:
                return null;
        }
    }
}
