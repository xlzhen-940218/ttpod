package com.sds.android.ttpod.component.lockscreen.p101a.p103b;

/* renamed from: com.sds.android.ttpod.component.lockscreen.a.b.b */
/* loaded from: classes.dex */
public abstract class IntProperty<T> extends Property<T, Integer> {
    /* renamed from: a */
    public abstract void mo5966a(T t, int i);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.Property
    /* renamed from: a */
    public /* bridge */ /* synthetic */ void mo5962a(Object obj, Integer num) {
        m5965a((T) obj, num);
    }

    public IntProperty(String str) {
        super(Integer.class, str);
    }

    /* renamed from: a  reason: avoid collision after fix types in other method */
    public final void m5965a(T t, Integer num) {
        m5965a( t, Integer.valueOf(num.intValue()));
    }
}
