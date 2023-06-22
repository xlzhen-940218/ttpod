package com.sds.android.ttpod.component.lockscreen.p101a.p102a;

import android.view.animation.Interpolator;
import com.sds.android.ttpod.component.lockscreen.p101a.p102a.Keyframe;
import java.util.ArrayList;

/* renamed from: com.sds.android.ttpod.component.lockscreen.a.a.c */
/* loaded from: classes.dex */
class FloatKeyframeSet extends KeyframeSet {

    /* renamed from: g */
    private float f4626g;

    /* renamed from: h */
    private float f4627h;

    /* renamed from: i */
    private float f4628i;

    /* renamed from: j */
    private boolean f4629j;

    public FloatKeyframeSet(Keyframe.C1284a... c1284aArr) {
        super(c1284aArr);
        this.f4629j = true;
    }

    @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.KeyframeSet
    /* renamed from: a */
    public Object mo6066a(float f) {
        return Float.valueOf(m6085b(f));
    }

    @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.KeyframeSet
    /* renamed from: a */
    public FloatKeyframeSet clone() {
        ArrayList<Keyframe> arrayList = this.f4644e;
        int size = this.f4644e.size();
        Keyframe.C1284a[] c1284aArr = new Keyframe.C1284a[size];
        for (int i = 0; i < size; i++) {
            c1284aArr[i] = (Keyframe.C1284a) arrayList.get(i).clone();
        }
        return new FloatKeyframeSet(c1284aArr);
    }

    /* renamed from: b */
    public float m6085b(float f) {
        int i = 1;
        if (this.f4640a == 2) {
            if (this.f4629j) {
                this.f4629j = false;
                this.f4626g = ((Keyframe.C1284a) this.f4644e.get(0)).m6073f();
                this.f4627h = ((Keyframe.C1284a) this.f4644e.get(1)).m6073f();
                this.f4628i = this.f4627h - this.f4626g;
            }
            if (this.f4643d != null) {
                f = this.f4643d.getInterpolation(f);
            }
            if (this.f4645f == null) {
                return this.f4626g + (this.f4628i * f);
            }
            return ((Number) this.f4645f.mo6005a(f, Float.valueOf(this.f4626g), Float.valueOf(this.f4627h))).floatValue();
        } else if (f <= 0.0f) {
            Keyframe.C1284a c1284a = (Keyframe.C1284a) this.f4644e.get(0);
            Keyframe.C1284a c1284a2 = (Keyframe.C1284a) this.f4644e.get(1);
            float m6073f = c1284a.m6073f();
            float m6073f2 = c1284a2.m6073f();
            float c = c1284a.m6075c();
            float c2 = c1284a2.m6075c();
            Interpolator d = c1284a2.m6074d();
            if (d != null) {
                f = d.getInterpolation(f);
            }
            float f2 = (f - c) / (c2 - c);
            return this.f4645f == null ? (f2 * (m6073f2 - m6073f)) + m6073f : ((Number) this.f4645f.mo6005a(f2, Float.valueOf(m6073f), Float.valueOf(m6073f2))).floatValue();
        } else if (f >= 1.0f) {
            Keyframe.C1284a c1284a3 = (Keyframe.C1284a) this.f4644e.get(this.f4640a - 2);
            Keyframe.C1284a c1284a4 = (Keyframe.C1284a) this.f4644e.get(this.f4640a - 1);
            float m6073f3 = c1284a3.m6073f();
            float m6073f4 = c1284a4.m6073f();
            float c3 = c1284a3.m6075c();
            float c4 = c1284a4.m6075c();
            Interpolator d2 = c1284a4.m6074d();
            if (d2 != null) {
                f = d2.getInterpolation(f);
            }
            float f3 = (f - c3) / (c4 - c3);
            return this.f4645f == null ? (f3 * (m6073f4 - m6073f3)) + m6073f3 : ((Number) this.f4645f.mo6005a(f3, Float.valueOf(m6073f3), Float.valueOf(m6073f4))).floatValue();
        } else {
            Keyframe.C1284a c1284a5 = (Keyframe.C1284a) this.f4644e.get(0);
            while (true) {
                Keyframe.C1284a c1284a6 = c1284a5;
                if (i < this.f4640a) {
                    c1284a5 = (Keyframe.C1284a) this.f4644e.get(i);
                    if (f >= c1284a5.m6075c()) {
                        i++;
                    } else {
                        Interpolator d3 = c1284a5.m6074d();
                        if (d3 != null) {
                            f = d3.getInterpolation(f);
                        }
                        float c5 = (f - c1284a6.m6075c()) / (c1284a5.m6075c() - c1284a6.m6075c());
                        float m6073f5 = c1284a6.m6073f();
                        float m6073f6 = c1284a5.m6073f();
                        return this.f4645f == null ? ((m6073f6 - m6073f5) * c5) + m6073f5 : ((Number) this.f4645f.mo6005a(c5, Float.valueOf(m6073f5), Float.valueOf(m6073f6))).floatValue();
                    }
                } else {
                    return ((Number) this.f4644e.get(this.f4640a - 1).mo6070b()).floatValue();
                }
            }
        }
    }
}
