package com.sds.android.ttpod.component.lockscreen.p101a.p103b;

/* renamed from: com.sds.android.ttpod.component.lockscreen.a.b.a */
/* loaded from: classes.dex */
public abstract class FloatProperty<T> extends Property<T, Float> {
    /* renamed from: a */
    public abstract void mo5968a(T t, float f);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.Property
    /* renamed from: a */
    public /* bridge */ /* synthetic */ void mo5962a(Object obj, Float f) {
        m5967a((T) obj, f);
    }

    public FloatProperty(String str) {
        super(Float.class, str);
    }

    /* renamed from: a  reason: avoid collision after fix types in other method */
    public final void m5967a(T t, Float f) {
        mo5968a( t, f.floatValue());
    }
}
