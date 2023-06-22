package com.sds.android.ttpod.component.lockscreen.p101a.p102a;

import android.view.animation.Interpolator;
import com.sds.android.ttpod.component.lockscreen.p101a.p102a.Keyframe;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.sds.android.ttpod.component.lockscreen.a.a.g */
/* loaded from: classes.dex */
public class KeyframeSet {

    /* renamed from: a */
    int f4640a;

    /* renamed from: b */
    Keyframe f4641b;

    /* renamed from: c */
    Keyframe f4642c;

    /* renamed from: d */
    Interpolator f4643d;

    /* renamed from: e */
    ArrayList<Keyframe> f4644e = new ArrayList<>();

    /* renamed from: f */
    TypeEvaluator f4645f;

    public KeyframeSet(Keyframe... keyframeArr) {
        this.f4640a = keyframeArr.length;
        this.f4644e.addAll(Arrays.asList(keyframeArr));
        this.f4641b = this.f4644e.get(0);
        this.f4642c = this.f4644e.get(this.f4640a - 1);
        this.f4643d = this.f4642c.m6074d();
    }

    /* renamed from: a */
    public static KeyframeSet m6063a(int... iArr) {
        int length = iArr.length;
        Keyframe.C1285b[] c1285bArr = new Keyframe.C1285b[Math.max(length, 2)];
        if (length == 1) {
            c1285bArr[0] = (Keyframe.C1285b) Keyframe.m6080a(0.0f);
            c1285bArr[1] = (Keyframe.C1285b) Keyframe.m6078a(1.0f, iArr[0]);
        } else {
            c1285bArr[0] = (Keyframe.C1285b) Keyframe.m6078a(0.0f, iArr[0]);
            for (int i = 1; i < length; i++) {
                c1285bArr[i] = (Keyframe.C1285b) Keyframe.m6078a(i / (length - 1), iArr[i]);
            }
        }
        return new IntKeyframeSet(c1285bArr);
    }

    /* renamed from: a */
    public static KeyframeSet m6064a(float... fArr) {
        int length = fArr.length;
        Keyframe.C1284a[] c1284aArr = new Keyframe.C1284a[Math.max(length, 2)];
        if (length == 1) {
            c1284aArr[0] = (Keyframe.C1284a) Keyframe.m6076b(0.0f);
            c1284aArr[1] = (Keyframe.C1284a) Keyframe.m6079a(1.0f, fArr[0]);
        } else {
            c1284aArr[0] = (Keyframe.C1284a) Keyframe.m6079a(0.0f, fArr[0]);
            for (int i = 1; i < length; i++) {
                c1284aArr[i] = (Keyframe.C1284a) Keyframe.m6079a(i / (length - 1), fArr[i]);
            }
        }
        return new FloatKeyframeSet(c1284aArr);
    }

    /* renamed from: a */
    public void m6065a(TypeEvaluator typeEvaluator) {
        this.f4645f = typeEvaluator;
    }

    @Override // 
    /* renamed from: b */
    public KeyframeSet clone() {
        ArrayList<Keyframe> arrayList = this.f4644e;
        int size = this.f4644e.size();
        Keyframe[] keyframeArr = new Keyframe[size];
        for (int i = 0; i < size; i++) {
            keyframeArr[i] = arrayList.get(i).clone();
        }
        return new KeyframeSet(keyframeArr);
    }

    /* renamed from: a */
    public Object mo6066a(float f) {
        if (this.f4640a == 2) {
            if (this.f4643d != null) {
                f = this.f4643d.getInterpolation(f);
            }
            return this.f4645f.mo6005a(f, this.f4641b.mo6070b(), this.f4642c.mo6070b());
        } else if (f <= 0.0f) {
            Keyframe keyframe = this.f4644e.get(1);
            Interpolator m6074d = keyframe.m6074d();
            if (m6074d != null) {
                f = m6074d.getInterpolation(f);
            }
            float m6075c = this.f4641b.m6075c();
            return this.f4645f.mo6005a((f - m6075c) / (keyframe.m6075c() - m6075c), this.f4641b.mo6070b(), keyframe.mo6070b());
        } else if (f >= 1.0f) {
            Keyframe keyframe2 = this.f4644e.get(this.f4640a - 2);
            Interpolator m6074d2 = this.f4642c.m6074d();
            if (m6074d2 != null) {
                f = m6074d2.getInterpolation(f);
            }
            float m6075c2 = keyframe2.m6075c();
            return this.f4645f.mo6005a((f - m6075c2) / (this.f4642c.m6075c() - m6075c2), keyframe2.mo6070b(), this.f4642c.mo6070b());
        } else {
            Keyframe keyframe3 = this.f4641b;
            int i = 1;
            while (i < this.f4640a) {
                Keyframe keyframe4 = this.f4644e.get(i);
                if (f >= keyframe4.m6075c()) {
                    i++;
                    keyframe3 = keyframe4;
                } else {
                    Interpolator m6074d3 = keyframe4.m6074d();
                    if (m6074d3 != null) {
                        f = m6074d3.getInterpolation(f);
                    }
                    float m6075c3 = keyframe3.m6075c();
                    return this.f4645f.mo6005a((f - m6075c3) / (keyframe4.m6075c() - m6075c3), keyframe3.mo6070b(), keyframe4.mo6070b());
                }
            }
            return this.f4642c.mo6070b();
        }
    }

    public String toString() {
        String str = " ";
        int i = 0;
        while (i < this.f4640a) {
            String str2 = str + this.f4644e.get(i).mo6070b() + "  ";
            i++;
            str = str2;
        }
        return str;
    }
}
