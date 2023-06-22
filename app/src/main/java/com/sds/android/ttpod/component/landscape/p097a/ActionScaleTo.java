package com.sds.android.ttpod.component.landscape.p097a;

import com.sds.android.ttpod.component.landscape.p098b.Scene;

/* renamed from: com.sds.android.ttpod.component.landscape.a.j */
/* loaded from: classes.dex */
public class ActionScaleTo extends ActionInterval implements Cloneable {

    /* renamed from: e */
    protected float f4362e;

    /* renamed from: f */
    protected float f4363f;

    /* renamed from: g */
    protected float f4364g;

    /* renamed from: h */
    protected float f4365h;

    /* renamed from: i */
    protected float f4366i;

    /* renamed from: j */
    protected float f4367j;

    public ActionScaleTo(float f, float f2, float f3) {
        super(f);
        this.f4364g = f2;
        this.f4365h = f3;
    }

    public ActionScaleTo(float f, float f2) {
        this(f, f2, f2);
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionInterval, com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: a */
    public void mo6344a(Scene scene) {
        super.mo6344a(scene);
        this.f4362e = scene.m6257m().m6237f();
        this.f4363f = scene.m6257m().m6234g();
        this.f4366i = this.f4364g - this.f4362e;
        this.f4367j = this.f4365h - this.f4363f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: b */
    public void mo6342b(float f) {
        this.f4351a.m6257m().m6239e(this.f4362e + (this.f4366i * f));
        this.f4351a.m6257m().m6236f(this.f4363f + (this.f4367j * f));
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionInterval, com.sds.android.ttpod.component.landscape.p097a.ActionFiniteTime
    /* renamed from: c */
    public ActionScaleTo clone() {
        return (ActionScaleTo) super.clone();
    }
}
