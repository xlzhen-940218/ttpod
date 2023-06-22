package com.sds.android.ttpod.framework.modules.skin.p132d;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.d.r */
/* loaded from: classes.dex */
public class TrcSentence extends LrcSentence {

    /* renamed from: c */
    private ArrayList<TrcTimeRegion> f6641c;

    public TrcSentence() {
    }

    public TrcSentence(TrcSentence trcSentence) {
        super(trcSentence);
        this.f6641c = (ArrayList) m3615a(trcSentence.f6641c);
    }

    /* renamed from: a */
    private static <T> List<T> m3615a(List<T> list) {
        if (list != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                new ObjectOutputStream(byteArrayOutputStream).writeObject(list);
                return (List) new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())).readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public TrcSentence(long j, String str, int i, int i2, int i3) {
        super(j, str, i, i2, i3);
    }

    /* renamed from: h */
    public ArrayList<TrcTimeRegion> m3609h() {
        return this.f6641c;
    }

    /* renamed from: a */
    public void m3616a(TrcTimeRegion trcTimeRegion) {
        if (this.f6641c == null) {
            this.f6641c = new ArrayList<>(8);
        }
        this.f6641c.add(trcTimeRegion);
    }

    /* renamed from: i */
    public int m3608i() {
        return this.f6641c == null ? this.f6618a : m3607j();
    }

    /* renamed from: j */
    private int m3607j() {
        int i = 0;
        Iterator<TrcTimeRegion> it = this.f6641c.iterator();
        while (true) {
            int i2 = i;
            if (it.hasNext()) {
                i = it.next().m3603c() + i2;
            } else {
                return i2;
            }
        }
    }

    /* renamed from: a */
    public void m3619a(int i, int i2) {
        m3616a(new TrcTimeRegion(i, i2));
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.LrcSentence
    /* renamed from: c */
    protected String mo3612c() {
        if (this.f6641c == null) {
            return this.f6619b;
        }
        StringBuilder sb = new StringBuilder(this.f6641c.size() * 7);
        Iterator<TrcTimeRegion> it = this.f6641c.iterator();
        int i = 0;
        while (it.hasNext()) {
            TrcTimeRegion next = it.next();
            int m3604b = next.m3604b() + i;
            sb.append(String.format("<%d>%s", Integer.valueOf(next.m3603c()), this.f6619b.substring(i, m3604b)));
            i = m3604b;
        }
        return sb.toString();
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.LrcSentence, com.sds.android.ttpod.framework.modules.skin.p132d.Sentence
    /* renamed from: c */
    public int mo3611c(int i) {
        return this.f6641c == null ? super.mo3611c(i) : m3610d(i);
    }

    /* renamed from: d */
    private int m3610d(int i) {
        int i2 = 0;
        Iterator<TrcTimeRegion> it = this.f6641c.iterator();
        int i3 = 0;
        while (true) {
            int i4 = i2;
            if (it.hasNext()) {
                TrcTimeRegion next = it.next();
                int m3603c = next.m3603c();
                if (i <= i3 + m3603c) {
                    return i4 + ((next.m3606a() * (i - i3)) / m3603c);
                }
                i3 += m3603c;
                i2 = next.m3606a() + i4;
            } else {
                return i4;
            }
        }
    }

    /* renamed from: a */
    public int[] m3617a(TrcSentence trcSentence, int i, int i2) {
        int i3;
        int i4;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = (i + i2) - 1;
        Iterator<TrcTimeRegion> it = this.f6641c.iterator();
        while (true) {
            int i9 = i5;
            int i10 = i6;
            i3 = i7;
            if (!it.hasNext()) {
                i4 = i9;
                break;
            }
            TrcTimeRegion next = it.next();
            int m3604b = next.m3604b();
            i4 = next.m3603c();
            int i11 = m3604b == 0 ? i10 : (i10 + m3604b) - 1;
            if (i >= i10 && i8 <= i11) {
                if (m3604b > 0) {
                    i4 = (i4 * i2) / m3604b;
                }
                trcSentence.m3619a(i2, i4);
            } else {
                if (i >= i10 && i <= i11) {
                    int i12 = (m3604b - i) + i10;
                    if (i12 == 1 && this.f6619b.charAt(i) == ' ') {
                        i3 = 1;
                    } else {
                        if (i != i10) {
                            i4 = (i4 * i12) / m3604b;
                        }
                        trcSentence.m3619a(i12, i4);
                        i9 = i4;
                    }
                    i7 = i3;
                    i5 = i9;
                } else if (i >= i10 || i8 < i10) {
                    i7 = i3;
                    i5 = i9;
                } else if (m3604b == 0) {
                    trcSentence.m3619a(0, i4);
                    i7 = i3;
                    i5 = i9;
                } else {
                    int i13 = (i8 - i10) + 1;
                    if (i13 >= m3604b) {
                        trcSentence.m3619a(m3604b, i4);
                        i4 += i9;
                        if (i13 == m3604b) {
                            break;
                        }
                        i5 = i4;
                        i7 = i3;
                    } else {
                        if (m3604b - i13 != 1 || this.f6619b.charAt(i11) != ' ') {
                            i4 = (i4 * i13) / m3604b;
                        }
                        trcSentence.m3619a(i13, i4);
                        i4 += i9;
                    }
                }
                i6 = (m3604b == 0 ? 0 : 1) + i11;
            }
        }
        return new int[]{i4, i3};
    }

    /* renamed from: a */
    public void m3618a(OnMeasureTextListener onMeasureTextListener) {
        if (this.f6641c != null) {
            Iterator<TrcTimeRegion> it = this.f6641c.iterator();
            int i = 0;
            while (it.hasNext()) {
                TrcTimeRegion next = it.next();
                int m3604b = next.m3604b() + i;
                next.m3605a(onMeasureTextListener.mo3467a(this.f6619b.substring(i, m3604b)));
                i = m3604b;
            }
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.LrcSentence
    /* renamed from: b */
    public void mo3614b(int i) {
        super.mo3614b(i);
        if (this.f6641c != null && !this.f6641c.isEmpty()) {
            int size = this.f6641c.size();
            int i2 = 0;
            int i3 = 0;
            while (i2 < size) {
                int m3603c = i3 + this.f6641c.get(i2).m3603c();
                if (m3603c <= this.f6618a) {
                    i2++;
                    i3 = m3603c;
                } else {
                    m3613b(i2, this.f6618a - i3);
                    return;
                }
            }
        }
    }

    /* renamed from: b */
    private void m3613b(int i, int i2) {
        int i3 = 0;
        int i4 = 0;
        for (int size = this.f6641c.size() - 1; size >= i; size--) {
            TrcTimeRegion trcTimeRegion = this.f6641c.get(size);
            i4 += trcTimeRegion.m3606a();
            i3 += trcTimeRegion.m3604b();
            this.f6641c.remove(size);
        }
        TrcTimeRegion trcTimeRegion2 = new TrcTimeRegion(i3, i2);
        trcTimeRegion2.m3605a(i4);
        this.f6641c.add(trcTimeRegion2);
    }
}
