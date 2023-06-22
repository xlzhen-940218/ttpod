package com.sds.android.ttpod.component.landscape.p097a;

import com.sds.android.ttpod.component.landscape.p098b.Scene;

/* renamed from: com.sds.android.ttpod.component.landscape.a.p */
/* loaded from: classes.dex */
public class ActionZDecelerateMoveTo extends ActionZMoveTo implements Cloneable {

    /* renamed from: e */
    protected float f4375e;

    /* renamed from: f */
    protected float f4376f;

    public ActionZDecelerateMoveTo(float f, float f2) {
        super(f, f2);
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionZMoveTo, com.sds.android.ttpod.component.landscape.p097a.ActionInterval, com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: a */
    public void mo6344a(Scene scene) {
        super.mo6344a(scene);
        this.f4375e = 2.0f * this.f4379i;
        this.f4376f = -this.f4375e;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionZMoveTo, com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: b */
    public void mo6342b(float f) {
        this.f4351a.m6257m().m6254a(this.f4377g + (this.f4375e * f) + (((this.f4376f * f) * f) / 2.0f));
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionZMoveTo
    /* renamed from: c */
    public ActionZDecelerateMoveTo clone() {
        return (ActionZDecelerateMoveTo) super.clone();
    }
}
