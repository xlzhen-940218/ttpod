package com.sds.android.ttpod.component.lockscreen.p101a.p102a;

import android.view.animation.Interpolator;
import com.sds.android.ttpod.component.lockscreen.p101a.p102a.Keyframe;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.sds.android.ttpod.component.lockscreen.a.a.e */
/* loaded from: classes.dex */
public class IntKeyframeSet extends KeyframeSet {

    /* renamed from: g */
    private int f4630g;

    /* renamed from: h */
    private int f4631h;

    /* renamed from: i */
    private int f4632i;

    /* renamed from: j */
    private boolean f4633j;

    public IntKeyframeSet(Keyframe.C1285b... c1285bArr) {
        super(c1285bArr);
        this.f4633j = true;
    }

    @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.KeyframeSet
    /* renamed from: a */
    public Object mo6066a(float f) {
        return Integer.valueOf(m6082b(f));
    }

    @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.KeyframeSet
    /* renamed from: a */
    public IntKeyframeSet clone() {
        ArrayList<Keyframe> arrayList = this.f4644e;
        int size = this.f4644e.size();
        Keyframe.C1285b[] c1285bArr = new Keyframe.C1285b[size];
        for (int i = 0; i < size; i++) {
            c1285bArr[i] = (Keyframe.C1285b) arrayList.get(i).clone();
        }
        return new IntKeyframeSet(c1285bArr);
    }

    /* renamed from: b */
    public int m6082b(float f) {
        int i = 1;
        if (this.f4640a == 2) {
            if (this.f4633j) {
                this.f4633j = false;
                this.f4630g = ((Keyframe.C1285b) this.f4644e.get(0)).m6068f();
                this.f4631h = ((Keyframe.C1285b) this.f4644e.get(1)).m6068f();
                this.f4632i = this.f4631h - this.f4630g;
            }
            if (this.f4643d != null) {
                f = this.f4643d.getInterpolation(f);
            }
            if (this.f4645f == null) {
                return this.f4630g + ((int) (this.f4632i * f));
            }
            return ((Number) this.f4645f.mo6005a(f, Integer.valueOf(this.f4630g), Integer.valueOf(this.f4631h))).intValue();
        } else if (f <= 0.0f) {
            Keyframe.C1285b c1285b = (Keyframe.C1285b) this.f4644e.get(0);
            Keyframe.C1285b c1285b2 = (Keyframe.C1285b) this.f4644e.get(1);
            int m6068f = c1285b.m6068f();
            int m6068f2 = c1285b2.m6068f();
            float c = c1285b.m6075c();
            float c2 = c1285b2.m6075c();
            Interpolator d = c1285b2.m6074d();
            if (d != null) {
                f = d.getInterpolation(f);
            }
            float f2 = (f - c) / (c2 - c);
            return this.f4645f == null ? ((int) (f2 * (m6068f2 - m6068f))) + m6068f : ((Number) this.f4645f.mo6005a(f2, Integer.valueOf(m6068f), Integer.valueOf(m6068f2))).intValue();
        } else if (f >= 1.0f) {
            Keyframe.C1285b c1285b3 = (Keyframe.C1285b) this.f4644e.get(this.f4640a - 2);
            Keyframe.C1285b c1285b4 = (Keyframe.C1285b) this.f4644e.get(this.f4640a - 1);
            int m6068f3 = c1285b3.m6068f();
            int m6068f4 = c1285b4.m6068f();
            float c3 = c1285b3.m6075c();
            float c4 = c1285b4.m6075c();
            Interpolator d2 = c1285b4.m6074d();
            if (d2 != null) {
                f = d2.getInterpolation(f);
            }
            float f3 = (f - c3) / (c4 - c3);
            return this.f4645f == null ? ((int) (f3 * (m6068f4 - m6068f3))) + m6068f3 : ((Number) this.f4645f.mo6005a(f3, Integer.valueOf(m6068f3), Integer.valueOf(m6068f4))).intValue();
        } else {
            Keyframe.C1285b c1285b5 = (Keyframe.C1285b) this.f4644e.get(0);
            while (true) {
                Keyframe.C1285b c1285b6 = c1285b5;
                if (i < this.f4640a) {
                    c1285b5 = (Keyframe.C1285b) this.f4644e.get(i);
                    if (f >= c1285b5.m6075c()) {
                        i++;
                    } else {
                        Interpolator d3 = c1285b5.m6074d();
                        if (d3 != null) {
                            f = d3.getInterpolation(f);
                        }
                        float c5 = (f - c1285b6.m6075c()) / (c1285b5.m6075c() - c1285b6.m6075c());
                        int m6068f5 = c1285b6.m6068f();
                        int m6068f6 = c1285b5.m6068f();
                        return this.f4645f == null ? ((int) ((m6068f6 - m6068f5) * c5)) + m6068f5 : ((Number) this.f4645f.mo6005a(c5, Integer.valueOf(m6068f5), Integer.valueOf(m6068f6))).intValue();
                    }
                } else {
                    return ((Number) this.f4644e.get(this.f4640a - 1).mo6070b()).intValue();
                }
            }
        }
    }
}
