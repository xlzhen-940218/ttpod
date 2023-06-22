package com.sds.android.ttpod.component.lockscreen.p101a.p102a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AndroidRuntimeException;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.sds.android.ttpod.component.lockscreen.p101a.p102a.Animator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* renamed from: com.sds.android.ttpod.component.lockscreen.a.a.l */
/* loaded from: classes.dex */
public class ValueAnimator extends Animator {

    /* renamed from: h */
    private static ThreadLocal<HandlerC1308a> f4687h = new ThreadLocal<>();

    /* renamed from: i */
    private static final ThreadLocal<ArrayList<ValueAnimator>> f4688i = new ThreadLocal<ArrayList<ValueAnimator>>() { // from class: com.sds.android.ttpod.component.lockscreen.a.a.l.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public ArrayList<ValueAnimator> initialValue() {
            return new ArrayList<>();
        }
    };

    /* renamed from: j */
    private static final ThreadLocal<ArrayList<ValueAnimator>> f4689j = new ThreadLocal<ArrayList<ValueAnimator>>() { // from class: com.sds.android.ttpod.component.lockscreen.a.a.l.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public ArrayList<ValueAnimator> initialValue() {
            return new ArrayList<>();
        }
    };

    /* renamed from: k */
    private static final ThreadLocal<ArrayList<ValueAnimator>> f4690k = new ThreadLocal<ArrayList<ValueAnimator>>() { // from class: com.sds.android.ttpod.component.lockscreen.a.a.l.3
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public ArrayList<ValueAnimator> initialValue() {
            return new ArrayList<>();
        }
    };

    /* renamed from: l */
    private static final ThreadLocal<ArrayList<ValueAnimator>> f4691l = new ThreadLocal<ArrayList<ValueAnimator>>() { // from class: com.sds.android.ttpod.component.lockscreen.a.a.l.4
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public ArrayList<ValueAnimator> initialValue() {
            return new ArrayList<>();
        }
    };

    /* renamed from: m */
    private static final ThreadLocal<ArrayList<ValueAnimator>> f4692m = new ThreadLocal<ArrayList<ValueAnimator>>() { // from class: com.sds.android.ttpod.component.lockscreen.a.a.l.5
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public ArrayList<ValueAnimator> initialValue() {
            return new ArrayList<>();
        }
    };

    /* renamed from: n */
    private static final Interpolator f4693n = new AccelerateDecelerateInterpolator();

    /* renamed from: o */
    private static final TypeEvaluator f4694o = new IntEvaluator();

    /* renamed from: p */
    private static final TypeEvaluator f4695p = new FloatEvaluator();

    /* renamed from: z */
    private static long f4696z = 10;

    /* renamed from: b */
    long f4701b;

    /* renamed from: f */
    PropertyValuesHolder[] f4705f;

    /* renamed from: g */
    HashMap<String, PropertyValuesHolder> f4706g;

    /* renamed from: u */
    private long f4711u;

    /* renamed from: c */
    long f4702c = -1;

    /* renamed from: q */
    private boolean f4707q = false;

    /* renamed from: r */
    private int f4708r = 0;

    /* renamed from: s */
    private float f4709s = 0.0f;

    /* renamed from: t */
    private boolean f4710t = false;

    /* renamed from: d */
    int f4703d = 0;

    /* renamed from: v */
    private boolean f4712v = false;

    /* renamed from: w */
    private boolean f4713w = false;

    /* renamed from: e */
    boolean f4704e = false;

    /* renamed from: x */
    private long f4714x = 300;

    /* renamed from: y */
    private long f4715y = 0;

    /* renamed from: A */
    private int f4697A = 0;

    /* renamed from: B */
    private int f4698B = 1;

    /* renamed from: C */
    private Interpolator f4699C = f4693n;

    /* renamed from: D */
    private ArrayList<InterfaceC1309b> f4700D = null;

    /* compiled from: ValueAnimator.java */
    /* renamed from: com.sds.android.ttpod.component.lockscreen.a.a.l$b */
    /* loaded from: classes.dex */
    public interface InterfaceC1309b {
        /* renamed from: a */
        void m5969a(ValueAnimator valueAnimator);
    }

    /* renamed from: a */
    public void mo5994a(int... iArr) {
        if (iArr != null && iArr.length != 0) {
            if (this.f4705f == null || this.f4705f.length == 0) {
                m5993a(PropertyValuesHolder.m6018a("", iArr));
            } else {
                this.f4705f[0].mo6009a(iArr);
            }
            this.f4704e = false;
        }
    }

    /* renamed from: a */
    public void mo5995a(float... fArr) {
        if (fArr != null && fArr.length != 0) {
            if (this.f4705f == null || this.f4705f.length == 0) {
                m5993a(PropertyValuesHolder.m6019a("", fArr));
            } else {
                this.f4705f[0].mo6014a(fArr);
            }
            this.f4704e = false;
        }
    }

    /* renamed from: a */
    public void m5993a(PropertyValuesHolder... propertyValuesHolderArr) {
        int length = propertyValuesHolderArr.length;
        this.f4705f = propertyValuesHolderArr;
        this.f4706g = new HashMap<>(length);
        for (PropertyValuesHolder propertyValuesHolder : propertyValuesHolderArr) {
            this.f4706g.put(propertyValuesHolder.m6015c(), propertyValuesHolder);
        }
        this.f4704e = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo5986d() {
        if (!this.f4704e) {
            int length = this.f4705f.length;
            for (int i = 0; i < length; i++) {
                this.f4705f[i].m6017b();
            }
            this.f4704e = true;
        }
    }

    /* renamed from: b */
    public ValueAnimator mo5991b(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Animators cannot have negative duration: " + j);
        }
        this.f4714x = j;
        return this;
    }

    /* renamed from: c */
    public void m5988c(long j) {
        mo5986d();
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        if (this.f4703d != 1) {
            this.f4702c = j;
            this.f4703d = 2;
        }
        this.f4701b = currentAnimationTimeMillis - j;
        m5985d(currentAnimationTimeMillis);
    }

    /* renamed from: g */
    public long m5982g() {
        if (!this.f4704e || this.f4703d == 0) {
            return 0L;
        }
        return AnimationUtils.currentAnimationTimeMillis() - this.f4701b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ValueAnimator.java */
    /* renamed from: com.sds.android.ttpod.component.lockscreen.a.a.l$a */
    /* loaded from: classes.dex */
    public static class HandlerC1308a extends Handler {
        private HandlerC1308a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            boolean z;
            int i;
            ArrayList arrayList = (ArrayList) ValueAnimator.f4688i.get();
            ArrayList arrayList2 = (ArrayList) ValueAnimator.f4690k.get();
            switch (message.what) {
                case 0:
                    ArrayList arrayList3 = (ArrayList) ValueAnimator.f4689j.get();
                    if (arrayList.size() <= 0 && arrayList2.size() <= 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    while (arrayList3.size() > 0) {
                        ArrayList arrayList4 = (ArrayList) arrayList3.clone();
                        arrayList3.clear();
                        int size = arrayList4.size();
                        for (int i2 = 0; i2 < size; i2++) {
                            ValueAnimator valueAnimator = (ValueAnimator) arrayList4.get(i2);
                            if (valueAnimator.f4715y == 0) {
                                valueAnimator.m5975n();
                            } else {
                                arrayList2.add(valueAnimator);
                            }
                        }
                    }
                    break;
                case 1:
                    z = true;
                    break;
                default:
                    return;
            }
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            ArrayList arrayList5 = (ArrayList) ValueAnimator.f4692m.get();
            ArrayList arrayList6 = (ArrayList) ValueAnimator.f4691l.get();
            int size2 = arrayList2.size();
            for (int i3 = 0; i3 < size2; i3++) {
                ValueAnimator valueAnimator2 = (ValueAnimator) arrayList2.get(i3);
                if (valueAnimator2.m6001a(currentAnimationTimeMillis)) {
                    arrayList5.add(valueAnimator2);
                }
            }
            int size3 = arrayList5.size();
            if (size3 > 0) {
                for (int i4 = 0; i4 < size3; i4++) {
                    ValueAnimator valueAnimator3 = (ValueAnimator) arrayList5.get(i4);
                    valueAnimator3.m5975n();
                    valueAnimator3.f4712v = true;
                    arrayList2.remove(valueAnimator3);
                }
                arrayList5.clear();
            }
            int size4 = arrayList.size();
            int i5 = 0;
            while (i5 < size4) {
                ValueAnimator valueAnimator4 = (ValueAnimator) arrayList.get(i5);
                if (valueAnimator4.m5985d(currentAnimationTimeMillis)) {
                    arrayList6.add(valueAnimator4);
                }
                if (arrayList.size() == size4) {
                    i = i5 + 1;
                } else {
                    size4--;
                    arrayList6.remove(valueAnimator4);
                    i = i5;
                }
                size4 = size4;
                i5 = i;
            }
            if (arrayList6.size() > 0) {
                int i6 = 0;
                while (true) {
                    int i7 = i6;
                    if (i7 < arrayList6.size()) {
                        ((ValueAnimator) arrayList6.get(i7)).m5984e();
                        i6 = i7 + 1;
                    } else {
                        arrayList6.clear();
                    }
                }
            }
            if (z) {
                if (!arrayList.isEmpty() || !arrayList2.isEmpty()) {
                    sendEmptyMessageDelayed(1, Math.max(0L, ValueAnimator.f4696z - (AnimationUtils.currentAnimationTimeMillis() - currentAnimationTimeMillis)));
                }
            }
        }
    }

    /* renamed from: a */
    public void m6002a(int i) {
        this.f4697A = i;
    }

    /* renamed from: a */
    public void m6000a(Interpolator interpolator) {
        if (interpolator != null) {
            this.f4699C = interpolator;
        } else {
            this.f4699C = new LinearInterpolator();
        }
    }

    /* renamed from: a */
    private void m5996a(boolean z) {
        if (Looper.myLooper() == null) {
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        }
        this.f4707q = z;
        this.f4708r = 0;
        this.f4703d = 0;
        this.f4713w = true;
        this.f4710t = false;
        f4689j.get().add(this);
        if (this.f4715y == 0) {
            m5988c(m5982g());
            this.f4703d = 0;
            this.f4712v = true;
            if (this.f4625a != null) {
                ArrayList arrayList = (ArrayList) this.f4625a.clone();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ((Animator.InterfaceC1283a) arrayList.get(i)).mo5692a(this);
                }
            }
        }
        HandlerC1308a handlerC1308a = f4687h.get();
        if (handlerC1308a == null) {
            handlerC1308a = new HandlerC1308a();
            f4687h.set(handlerC1308a);
        }
        handlerC1308a.sendEmptyMessage(0);
    }

    @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.Animator
    /* renamed from: a */
    public void mo6004a() {
        m5996a(false);
    }

    @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.Animator
    /* renamed from: b */
    public void mo5992b() {
        if (this.f4703d != 0 || f4689j.get().contains(this) || f4690k.get().contains(this)) {
            if (this.f4712v && this.f4625a != null) {
                Iterator it = ((ArrayList) this.f4625a.clone()).iterator();
                while (it.hasNext()) {
                    ((Animator.InterfaceC1283a) it.next()).mo5690c(this);
                }
            }
            m5984e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m5984e() {
        f4688i.get().remove(this);
        f4689j.get().remove(this);
        f4690k.get().remove(this);
        this.f4703d = 0;
        if (this.f4712v && this.f4625a != null) {
            ArrayList arrayList = (ArrayList) this.f4625a.clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((Animator.InterfaceC1283a) arrayList.get(i)).mo5691b(this);
            }
        }
        this.f4712v = false;
        this.f4713w = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n */
    public void m5975n() {
        mo5986d();
        f4688i.get().add(this);
        if (this.f4715y > 0 && this.f4625a != null) {
            ArrayList arrayList = (ArrayList) this.f4625a.clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((Animator.InterfaceC1283a) arrayList.get(i)).mo5692a(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m6001a(long j) {
        if (!this.f4710t) {
            this.f4710t = true;
            this.f4711u = j;
        } else {
            long j2 = j - this.f4711u;
            if (j2 > this.f4715y) {
                this.f4701b = j - (j2 - this.f4715y);
                this.f4703d = 1;
                return true;
            }
        }
        return false;
    }

    /* renamed from: d */
    boolean m5985d(long j) {
        float f;
        boolean z = false;
        if (this.f4703d == 0) {
            this.f4703d = 1;
            if (this.f4702c < 0) {
                this.f4701b = j;
            } else {
                this.f4701b = j - this.f4702c;
                this.f4702c = -1L;
            }
        }
        switch (this.f4703d) {
            case 1:
            case 2:
                float f2 = this.f4714x > 0 ? ((float) (j - this.f4701b)) / ((float) this.f4714x) : 1.0f;
                if (f2 < 1.0f) {
                    f = f2;
                } else if (this.f4708r < this.f4697A || this.f4697A == -1) {
                    if (this.f4625a != null) {
                        int size = this.f4625a.size();
                        for (int i = 0; i < size; i++) {
                            this.f4625a.get(i).mo5689d(this);
                        }
                    }
                    if (this.f4698B == 2) {
                        this.f4707q = !this.f4707q;
                    }
                    this.f4708r += (int) f2;
                    f = f2 % 1.0f;
                    this.f4701b += this.f4714x;
                } else {
                    f = Math.min(f2, 1.0f);
                    z = true;
                }
                if (this.f4707q) {
                    f = 1.0f - f;
                }
                mo6003a(f);
                break;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo6003a(float f) {
        float interpolation = this.f4699C.getInterpolation(f);
        this.f4709s = interpolation;
        int length = this.f4705f.length;
        for (int i = 0; i < length; i++) {
            this.f4705f[i].mo6011a(interpolation);
        }
        if (this.f4700D != null) {
            int size = this.f4700D.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.f4700D.get(i2).m5969a(this);
            }
        }
    }

    @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.Animator
    /* renamed from: f */
    public ValueAnimator clone() {
        ValueAnimator valueAnimator = (ValueAnimator) super.clone();
        if (this.f4700D != null) {
            ArrayList<InterfaceC1309b> arrayList = this.f4700D;
            valueAnimator.f4700D = new ArrayList<>();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                valueAnimator.f4700D.add(arrayList.get(i));
            }
        }
        valueAnimator.f4702c = -1L;
        valueAnimator.f4707q = false;
        valueAnimator.f4708r = 0;
        valueAnimator.f4704e = false;
        valueAnimator.f4703d = 0;
        valueAnimator.f4710t = false;
        PropertyValuesHolder[] propertyValuesHolderArr = this.f4705f;
        if (propertyValuesHolderArr != null) {
            int length = propertyValuesHolderArr.length;
            valueAnimator.f4705f = new PropertyValuesHolder[length];
            valueAnimator.f4706g = new HashMap<>(length);
            for (int i2 = 0; i2 < length; i2++) {
                PropertyValuesHolder clone = propertyValuesHolderArr[i2].clone();
                valueAnimator.f4705f[i2] = clone;
                valueAnimator.f4706g.put(clone.m6015c(), clone);
            }
        }
        return valueAnimator;
    }

    public String toString() {
        String str = "ValueAnimator@" + Integer.toHexString(hashCode());
        if (this.f4705f != null) {
            for (int i = 0; i < this.f4705f.length; i++) {
                str = str + "\n    " + this.f4705f[i].toString();
            }
        }
        return str;
    }
}
