package com.sds.android.ttpod.component.landscape.p097a;

import com.sds.android.ttpod.component.landscape.p098b.Scene;

/* renamed from: com.sds.android.ttpod.component.landscape.a.q */
/* loaded from: classes.dex */
public class ActionZMoveTo extends ActionInterval implements Cloneable {

    /* renamed from: g */
    protected float f4377g;

    /* renamed from: h */
    protected float f4378h;

    /* renamed from: i */
    protected float f4379i;

    public ActionZMoveTo(float f, float f2) {
        super(f);
        this.f4378h = f2;
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionInterval, com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: a */
    public void mo6344a(Scene scene) {
        super.mo6344a(scene);
        this.f4377g = scene.m6257m().m6249b();
        this.f4379i = this.f4378h - this.f4377g;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: b */
    public void mo6342b(float f) {
        this.f4351a.m6257m().m6254a(this.f4377g + (this.f4379i * f));
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionInterval, com.sds.android.ttpod.component.landscape.p097a.ActionFiniteTime
    /* renamed from: f */
    public ActionZMoveTo clone() {
        return (ActionZMoveTo) super.clone();
    }
}
