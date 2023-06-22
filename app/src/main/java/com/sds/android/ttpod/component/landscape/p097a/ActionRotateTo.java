package com.sds.android.ttpod.component.landscape.p097a;

/* renamed from: com.sds.android.ttpod.component.landscape.a.i */
/* loaded from: classes.dex */
public class ActionRotateTo extends ActionInterval implements Cloneable {

    /* renamed from: e */
    protected float f4359e;

    /* renamed from: f */
    protected float f4360f;

    /* renamed from: g */
    protected float f4361g;

    public ActionRotateTo(float f, float f2) {
        super(f);
        this.f4360f = f2;
    }

    /* renamed from: c */
    public void m6355c(float f) {
        this.f4360f = f;
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionInterval, com.sds.android.ttpod.component.landscape.p097a.ActionFiniteTime
    /* renamed from: c */
    public ActionRotateTo clone() {
        return (ActionRotateTo) super.clone();
    }
}
