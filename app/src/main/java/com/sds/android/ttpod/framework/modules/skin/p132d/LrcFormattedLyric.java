package com.sds.android.ttpod.framework.modules.skin.p132d;

import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.d.b */
/* loaded from: classes.dex */
public class LrcFormattedLyric implements FormattedLyric {

    /* renamed from: a */
    protected final LrcLyric f6606a;

    /* renamed from: b */
    protected final int f6607b;

    /* renamed from: c */
    protected final OnMeasureTextListener f6608c;

    /* renamed from: d */
    protected ArrayList<LrcSentence> lrcLineList;

    /* renamed from: e */
    private int f6610e;

    public LrcFormattedLyric(LrcLyric lrcLyric, int i, OnMeasureTextListener onMeasureTextListener) {
        this.f6606a = lrcLyric;
        this.f6607b = i;
        this.f6608c = onMeasureTextListener;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.FormattedLyric
    /* renamed from: a */
    public Sentence getLrcLineIndex(int i) {
        if (i < 0) {
            return null;
        }
        return this.lrcLineList.get(i);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.FormattedLyric
    /* renamed from: a */
    public int getLrcLineSize() {
        return this.lrcLineList.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<LrcSentence> it = this.lrcLineList.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
        }
        return sb.toString();
    }

    /* renamed from: c */
    public FormattedLyric m3688c() {
        if (this.f6606a == null || this.f6607b < 10 || this.f6608c == null) {
            return null;
        }
        int mo3672b = this.f6606a.mo3672b();
        this.lrcLineList = new ArrayList<>((mo3672b >> 1) + mo3672b);
        for (int i = 0; i < mo3672b; i++) {
            mo3634a(this.f6606a.m3687b(i), i);
        }
        LyricUtils.m3641a(this.lrcLineList, this.f6606a.mo3668g());
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo3634a(LrcSentence lrcSentence, int i) {
        int m3692a;
        String mo3635g = lrcSentence.getCurrentLrcText();
        long m3676d = lrcSentence.m3676d();
        int mo3636f = lrcSentence.mo3636f();
        int mo3467a = this.f6608c.mo3467a(mo3635g);
        if (mo3467a <= this.f6607b) {
            mo3633a(mo3635g, m3676d, mo3636f, i, mo3467a);
            return;
        }
        float f = mo3467a / this.f6607b;
        int i2 = 0;
        int i3 = mo3636f;
        String str = mo3635g;
        while (i2 <= 2) {
            if (f <= 2.0f) {
                m3692a = m3689b(str);
            } else {
                m3692a = m3692a(str);
            }
            String substring = str.substring(0, m3692a);
            String trim = str.substring(m3692a).trim();
            int mo3467a2 = this.f6608c.mo3467a(substring);
            int mo3467a3 = this.f6608c.mo3467a(trim);
            int i4 = (mo3467a2 * i3) / (mo3467a2 + mo3467a3);
            if (i4 <= 0) {
                i4 = 1;
            }
            mo3633a(substring, m3676d, i4, i, mo3467a2);
            m3676d += i4;
            int i5 = i3 - i4;
            if (mo3467a3 <= this.f6607b) {
                mo3633a(trim, m3676d, i5, i, mo3467a3);
                return;
            }
            i2++;
            i3 = i5;
            f = mo3467a3 / this.f6607b;
            str = trim;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public int m3692a(String str) {
        int lastIndexOf = str.lastIndexOf(32);
        while (lastIndexOf > 0) {
            String substring = str.substring(0, lastIndexOf);
            if (this.f6608c.mo3467a(substring) > this.f6607b) {
                lastIndexOf = substring.lastIndexOf(32);
            } else {
                return lastIndexOf;
            }
        }
        for (int min = Math.min(this.f6607b / ((int) this.f6608c.mo3486a()), str.length() - 1); min > 0; min--) {
            String substring2 = str.substring(0, min);
            if (this.f6608c.mo3467a(substring2) <= this.f6607b && min > 1) {
                return m3691a(substring2, str.charAt(min));
            }
        }
        return 1;
    }

    /* renamed from: a */
    private int m3691a(String str, char c) {
        int length = str.length();
        if (LyricUtils.m3644a(str.charAt(length - 1)) && LyricUtils.m3644a(c)) {
            do {
                length--;
                if (length <= 0) {
                    break;
                }
            } while (LyricUtils.m3644a(str.charAt(length - 1)));
        }
        return length > 0 ? length : str.length();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public int m3689b(String str) {
        int i = Integer.MAX_VALUE;
        int i2 = -1;
        int lastIndexOf = str.lastIndexOf(32);
        while (lastIndexOf > 0) {
            String substring = str.substring(0, lastIndexOf);
            if (this.f6608c.mo3467a(substring) <= this.f6607b) {
                int[] m3690a = m3690a(str, lastIndexOf, i2, i);
                i2 = m3690a[1];
                if (m3690a[0] != 1) {
                    i = m3690a[2];
                } else {
                    return i2;
                }
            }
            lastIndexOf = substring.lastIndexOf(32);
        }
        if (i2 <= 0) {
            int i3 = i2;
            int i4 = i;
            int length = str.length() - 1;
            while (length > 0) {
                String substring2 = str.substring(0, length);
                if (this.f6608c.mo3467a(substring2) <= this.f6607b) {
                    length = m3691a(substring2, str.charAt(length));
                    int[] m3690a2 = m3690a(str, length, i3, i4);
                    i3 = m3690a2[1];
                    if (m3690a2[0] == 1) {
                        return i3;
                    }
                    i4 = m3690a2[2];
                }
                length--;
            }
            return 1;
        }
        return i2;
    }

    /* renamed from: a */
    private int[] m3690a(String str, int i, int i2, int i3) {
        int i4;
        int i5;
        int mo3467a = this.f6608c.mo3467a(str.substring(0, i));
        int mo3467a2 = this.f6608c.mo3467a(str.substring(i).trim());
        if (mo3467a2 < this.f6607b) {
            int abs = Math.abs(mo3467a - mo3467a2);
            if (i2 <= 0 || abs < i3) {
                i5 = abs;
                i2 = i;
                i4 = 0;
            } else {
                i5 = abs;
                i4 = 1;
            }
        } else if (i2 <= 0) {
            i4 = 1;
            i5 = 0;
            i2 = i;
        } else {
            i4 = 1;
            i5 = 0;
        }
        return new int[]{i4, i2, i5};
    }

    /* renamed from: a */
    protected void mo3633a(String str, long j, int i, int i2, int i3) {
        this.lrcLineList.add(new LrcSentence(j, str, i, i2, i3));
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.FormattedLyric
    /* renamed from: b */
    public int getLrcLineIndex() {
        return this.f6610e;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.FormattedLyric
    /* renamed from: a */
    public int mo3628a(long j) {
        this.f6610e = LyricUtils.m3642a(this.lrcLineList, j);
        return this.f6610e;
    }
}
