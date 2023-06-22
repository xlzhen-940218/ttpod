package com.sds.android.ttpod.component.lockscreen.p101a.p102a;

import android.view.View;
import com.sds.android.ttpod.component.lockscreen.p101a.p103b.Property;
import com.sds.android.ttpod.component.lockscreen.p101a.p104c.p105a.AnimatorProxy;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.sds.android.ttpod.component.lockscreen.a.a.h */
/* loaded from: classes.dex */
public final class ObjectAnimator extends ValueAnimator {

    /* renamed from: h */
    private static final Map<String, Property> f4646h = new HashMap();

    /* renamed from: i */
    private Object f4647i;

    /* renamed from: j */
    private String f4648j;

    /* renamed from: k */
    private Property f4649k;

    static {
        f4646h.put("alpha", PreHoneycombCompat.f4650a);
        f4646h.put("pivotX", PreHoneycombCompat.f4651b);
        f4646h.put("pivotY", PreHoneycombCompat.f4652c);
        f4646h.put("translationX", PreHoneycombCompat.f4653d);
        f4646h.put("translationY", PreHoneycombCompat.f4654e);
        f4646h.put("rotation", PreHoneycombCompat.f4655f);
        f4646h.put("rotationX", PreHoneycombCompat.f4656g);
        f4646h.put("rotationY", PreHoneycombCompat.f4657h);
        f4646h.put("scaleX", PreHoneycombCompat.f4658i);
        f4646h.put("scaleY", PreHoneycombCompat.f4659j);
        f4646h.put("scrollX", PreHoneycombCompat.f4660k);
        f4646h.put("scrollY", PreHoneycombCompat.f4661l);
        f4646h.put("x", PreHoneycombCompat.f4662m);
        f4646h.put("y", PreHoneycombCompat.f4663n);
    }

    /* renamed from: a */
    public void m6057a(String str) {
        if (this.f4705f != null) {
            PropertyValuesHolder propertyValuesHolder = this.f4705f[0];
            String m6015c = propertyValuesHolder.m6015c();
            propertyValuesHolder.m6021a(str);
            this.f4706g.remove(m6015c);
            this.f4706g.put(str, propertyValuesHolder);
        }
        this.f4648j = str;
        this.f4704e = false;
    }

    /* renamed from: a */
    public void m6060a(Property property) {
        if (this.f4705f != null) {
            PropertyValuesHolder propertyValuesHolder = this.f4705f[0];
            String m6015c = propertyValuesHolder.m6015c();
            propertyValuesHolder.m6027a(property);
            this.f4706g.remove(m6015c);
            this.f4706g.put(this.f4648j, propertyValuesHolder);
        }
        if (this.f4649k != null) {
            this.f4648j = property.m5964a();
        }
        this.f4649k = property;
        this.f4704e = false;
    }

    public ObjectAnimator() {
    }

    private ObjectAnimator(Object obj, String str) {
        this.f4647i = obj;
        m6057a(str);
    }

    /* renamed from: a */
    public static ObjectAnimator m6058a(Object obj, String str, int... iArr) {
        ObjectAnimator objectAnimator = new ObjectAnimator(obj, str);
        objectAnimator.mo5994a(iArr);
        return objectAnimator;
    }

    /* renamed from: a */
    public static ObjectAnimator m6059a(Object obj, String str, float... fArr) {
        ObjectAnimator objectAnimator = new ObjectAnimator(obj, str);
        objectAnimator.mo5995a(fArr);
        return objectAnimator;
    }

    @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.ValueAnimator
    /* renamed from: a */
    public void mo5994a(int... iArr) {
        if (this.f4705f == null || this.f4705f.length == 0) {
            if (this.f4649k != null) {
                m5993a(PropertyValuesHolder.m6025a((Property<?, Integer>) this.f4649k, iArr));
                return;
            } else {
                m5993a(PropertyValuesHolder.m6018a(this.f4648j, iArr));
                return;
            }
        }
        super.mo5994a(iArr);
    }

    @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.ValueAnimator
    /* renamed from: a */
    public void mo5995a(float... fArr) {
        if (this.f4705f == null || this.f4705f.length == 0) {
            if (this.f4649k != null) {
                m5993a(PropertyValuesHolder.m6026a(this.f4649k, fArr));
                return;
            } else {
                m5993a(PropertyValuesHolder.m6019a(this.f4648j, fArr));
                return;
            }
        }
        super.mo5995a(fArr);
    }

    @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.ValueAnimator, com.sds.android.ttpod.component.lockscreen.p101a.p102a.Animator
    /* renamed from: a */
    public void mo6004a() {
        super.mo6004a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.ValueAnimator
    /* renamed from: d */
    public void mo5986d() {
        if (!this.f4704e) {
            if (this.f4649k == null && AnimatorProxy.f4718a && (this.f4647i instanceof View) && f4646h.containsKey(this.f4648j)) {
                m6060a(f4646h.get(this.f4648j));
            }
            int length = this.f4705f.length;
            for (int i = 0; i < length; i++) {
                this.f4705f[i].m6022a(this.f4647i);
            }
            super.mo5986d();
        }
    }

    @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.ValueAnimator
    /* renamed from: a */
    public ObjectAnimator mo5991b(long j) {
        super.mo5991b(j);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.ValueAnimator
    /* renamed from: a */
    public void mo6003a(float f) {
        super.mo6003a(f);
        for (PropertyValuesHolder propertyValuesHolder : this.f4705f) {
            propertyValuesHolder.mo6008b(this.f4647i);
        }
    }

    @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.ValueAnimator
    /* renamed from: e */
    public ObjectAnimator clone() {
        return (ObjectAnimator) super.clone();
    }

    @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.ValueAnimator
    public String toString() {
        PropertyValuesHolder[] propertyValuesHolderArr = new PropertyValuesHolder[0];
        String str = "ObjectAnimator@" + Integer.toHexString(hashCode()) + ", target " + this.f4647i;
        if (this.f4705f != null) {
            int i = 0;
            while (i < this.f4705f.length) {
                i++;
                str = str + "\n    " + propertyValuesHolderArr[i].toString();
            }
        }
        return str;
    }
}
