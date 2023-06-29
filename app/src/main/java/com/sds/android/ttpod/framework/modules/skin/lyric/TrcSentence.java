package com.sds.android.ttpod.framework.modules.skin.lyric;

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
    private ArrayList<TrcTimeRegion> trcTimeRegionArrayList;

    public TrcSentence() {
    }

    public TrcSentence(TrcSentence trcSentence) {
        super(trcSentence);
        this.trcTimeRegionArrayList = (ArrayList) cloneData(trcSentence.trcTimeRegionArrayList);
    }

    /* renamed from: a */
    private static <T> List<T> cloneData(List<T> list) {
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

    public TrcSentence(long startTime, String lrcText, int duration, int index, int lrcTextWidth) {
        super(startTime, lrcText, duration, index, lrcTextWidth);
    }

    /* renamed from: h */
    public ArrayList<TrcTimeRegion> m3609h() {
        return this.trcTimeRegionArrayList;
    }

    /* renamed from: a */
    public void m3616a(TrcTimeRegion trcTimeRegion) {
        if (this.trcTimeRegionArrayList == null) {
            this.trcTimeRegionArrayList = new ArrayList<>(8);
        }
        this.trcTimeRegionArrayList.add(trcTimeRegion);
    }

    /* renamed from: i */
    public int m3608i() {
        return this.trcTimeRegionArrayList == null ? this.duration : m3607j();
    }

    /* renamed from: j */
    private int m3607j() {
        int i = 0;
        Iterator<TrcTimeRegion> it = this.trcTimeRegionArrayList.iterator();
        while (true) {
            int i2 = i;
            if (it.hasNext()) {
                i = it.next().getDuration() + i2;
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
    protected String getLrcText() {
        if (this.trcTimeRegionArrayList == null) {
            return this.lrcText;
        }
        StringBuilder sb = new StringBuilder(this.trcTimeRegionArrayList.size() * 7);
        Iterator<TrcTimeRegion> it = this.trcTimeRegionArrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            TrcTimeRegion next = it.next();
            int m3604b = next.getCharsCount() + i;
            sb.append(String.format("<%d>%s", Integer.valueOf(next.getDuration()), this.lrcText.substring(i, m3604b)));
            i = m3604b;
        }
        return sb.toString();
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.LrcSentence, com.sds.android.ttpod.framework.modules.skin.p132d.Sentence
    /* renamed from: c */
    public int mo3611c(int i) {
        return this.trcTimeRegionArrayList == null ? super.mo3611c(i) : m3610d(i);
    }

    /* renamed from: d */
    private int m3610d(int i) {
        int i2 = 0;
        Iterator<TrcTimeRegion> it = this.trcTimeRegionArrayList.iterator();
        int i3 = 0;
        while (true) {
            int i4 = i2;
            if (it.hasNext()) {
                TrcTimeRegion trcTimeRegion = it.next();
                int nextDuration = trcTimeRegion.getDuration();
                if (i <= i3 + nextDuration) {
                    return i4 + ((trcTimeRegion.getTextWidth() * (i - i3)) / nextDuration);
                }
                i3 += nextDuration;
                i2 = trcTimeRegion.getTextWidth() + i4;
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
        Iterator<TrcTimeRegion> it = this.trcTimeRegionArrayList.iterator();
        while (true) {
            int i9 = i5;
            int i10 = i6;
            i3 = i7;
            if (!it.hasNext()) {
                i4 = i9;
                break;
            }
            TrcTimeRegion next = it.next();
            int m3604b = next.getCharsCount();
            i4 = next.getDuration();
            int i11 = m3604b == 0 ? i10 : (i10 + m3604b) - 1;
            if (i >= i10 && i8 <= i11) {
                if (m3604b > 0) {
                    i4 = (i4 * i2) / m3604b;
                }
                trcSentence.m3619a(i2, i4);
            } else {
                if (i >= i10 && i <= i11) {
                    int i12 = (m3604b - i) + i10;
                    if (i12 == 1 && this.lrcText.charAt(i) == ' ') {
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
                        if (m3604b - i13 != 1 || this.lrcText.charAt(i11) != ' ') {
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
        if (this.trcTimeRegionArrayList != null) {
            Iterator<TrcTimeRegion> it = this.trcTimeRegionArrayList.iterator();
            int i = 0;
            while (it.hasNext()) {
                TrcTimeRegion next = it.next();
                int m3604b = next.getCharsCount() + i;
                next.setTextWidth(onMeasureTextListener.measureLrcTextWidth(this.lrcText.substring(i, m3604b)));
                i = m3604b;
            }
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p132d.LrcSentence
    /* renamed from: b */
    public void setDuration(int duration) {
        super.setDuration(duration);
        if (this.trcTimeRegionArrayList != null && !this.trcTimeRegionArrayList.isEmpty()) {
            int size = this.trcTimeRegionArrayList.size();
            int i2 = 0;
            int i3 = 0;
            while (i2 < size) {
                int m3603c = i3 + this.trcTimeRegionArrayList.get(i2).getDuration();
                if (m3603c <= this.duration) {
                    i2++;
                    i3 = m3603c;
                } else {
                    m3613b(i2, this.duration - i3);
                    return;
                }
            }
        }
    }

    /* renamed from: b */
    private void m3613b(int i, int i2) {
        int i3 = 0;
        int i4 = 0;
        for (int size = this.trcTimeRegionArrayList.size() - 1; size >= i; size--) {
            TrcTimeRegion trcTimeRegion = this.trcTimeRegionArrayList.get(size);
            i4 += trcTimeRegion.getTextWidth();
            i3 += trcTimeRegion.getCharsCount();
            this.trcTimeRegionArrayList.remove(size);
        }
        TrcTimeRegion trcTimeRegion2 = new TrcTimeRegion(i3, i2);
        trcTimeRegion2.setTextWidth(i4);
        this.trcTimeRegionArrayList.add(trcTimeRegion2);
    }
}
