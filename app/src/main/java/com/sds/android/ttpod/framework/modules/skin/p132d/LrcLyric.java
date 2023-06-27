package com.sds.android.ttpod.framework.modules.skin.p132d;

import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.d.c */
/* loaded from: classes.dex */
public class LrcLyric implements Lyric {

    /* renamed from: a */
    private LyricInfo f6611a = new LyricInfo();

    /* renamed from: b */
    private ArrayList<LrcSentence> f6612b = new ArrayList<>(32);

    public int hashCode() {
        return ((this.f6611a.hashCode() + 31) * 31) + this.f6612b.size();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        LrcLyric lrcLyric = (LrcLyric) obj;
        if (this.f6611a.equals(lrcLyric.f6611a)) {
            return this.f6612b.size() == lrcLyric.f6612b.size();
        }
        return false;
    }

    public LrcLyric(String str) {
        this.f6611a.m3663a(str);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.f6611a.toString());
        int size = this.f6612b.size();
        for (int i = 0; i < size; i++) {
            LrcSentence lrcSentence = this.f6612b.get(i);
            sb.append(lrcSentence.toString());
            if (i <= 2) {
                LogUtils.debug("LrcLyric", "Lyric lookLyricPic toString idx=%d sentence=%s timestamp=%d", Integer.valueOf(i), lrcSentence.toString(), Long.valueOf(lrcSentence.m3676d()));
            }
        }
        return sb.toString();
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Lyric
    /* renamed from: a */
    public int mo3674a(int i) {
        return this.f6611a.m3665a(i);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Lyric
    /* renamed from: a */
    public int mo3675a() {
        return this.f6611a.m3651g();
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
        if (this.f6611a.m3652f()) {
            String m3666a = this.f6611a.m3666a();
            if (z && !LyricUtils.m3643a(m3666a)) {
                FileUtils.exists(m3666a);
                FileOutputStream fileOutputStream2 = null;
                try {
                    fileOutputStream = new FileOutputStream(m3666a);
                    try {
                        fileOutputStream.write(LyricConst.f6624a);
                        fileOutputStream.write(toString().getBytes("UTF8"));
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
    public int mo3672b() {
        return this.f6612b.size();
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Lyric
    /* renamed from: c */
    public ArrayList<LrcSentence> mo3667h() {
        return this.f6612b;
    }

    /* renamed from: b */
    public LrcSentence m3687b(int i) {
        return this.f6612b.get(i);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Lyric
    /* renamed from: d */
    public long mo3671d() {
        return this.f6611a.m3659c();
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Lyric
    /* renamed from: e */
    public long mo3670e() {
        return this.f6611a.m3656d();
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Lyric
    /* renamed from: f */
    public long mo3669f() {
        long m3654e = this.f6611a.m3654e();
        if (m3654e == 0 && mo3672b() > 0) {
            return this.f6612b.get(mo3672b() - 1).m3676d() + 5000;
        }
        return m3654e;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Lyric
    /* renamed from: g */
    public LyricInfo mo3668g() {
        return this.f6611a;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.Lyric
    /* renamed from: a */
    public FormattedLyric mo3631a(int i, int i2, OnMeasureTextListener onMeasureTextListener) {
        switch (i) {
            case 1:
                return new LrcFormattedLyric(this, i2, onMeasureTextListener).m3688c();
            case 2:
                return new LrcMtvFormattedLyric(this, i2, onMeasureTextListener).m3683c();
            default:
                return null;
        }
    }
}
