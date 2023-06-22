package com.sds.android.ttpod.component.landscape.p097a;

import android.graphics.PointF;
import com.sds.android.ttpod.component.landscape.p098b.Scene;

/* renamed from: com.sds.android.ttpod.component.landscape.a.h */
/* loaded from: classes.dex */
public class ActionMoveTo extends ActionInterval implements Cloneable {

    /* renamed from: e */
    protected PointF f4356e;

    /* renamed from: f */
    protected PointF f4357f;

    /* renamed from: g */
    protected PointF f4358g;

    public ActionMoveTo(float f, PointF pointF) {
        super(f);
        this.f4356e = new PointF();
        this.f4357f = new PointF();
        this.f4358g = new PointF();
        this.f4357f.set(pointF);
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionInterval, com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: a */
    public void mo6344a(Scene scene) {
        super.mo6344a(scene);
        this.f4356e.set(scene.m6257m().m6255a());
        this.f4358g.set(this.f4357f.x - this.f4356e.x, this.f4357f.y - this.f4356e.y);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: b */
    public void mo6342b(float f) {
        this.f4351a.m6257m().m6253a(this.f4356e.x + (this.f4358g.x * f), this.f4356e.y + (this.f4358g.y * f));
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionInterval, com.sds.android.ttpod.component.landscape.p097a.ActionFiniteTime
    /* renamed from: c */
    public ActionMoveTo clone() {
        ActionMoveTo actionMoveTo = (ActionMoveTo) super.clone();
        actionMoveTo.f4356e = new PointF();
        actionMoveTo.f4356e.set(this.f4356e);
        actionMoveTo.f4357f = new PointF();
        actionMoveTo.f4357f.set(this.f4357f);
        actionMoveTo.f4358g = new PointF();
        actionMoveTo.f4358g.set(this.f4358g);
        return actionMoveTo;
    }
}
