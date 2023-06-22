package com.sds.android.ttpod.component.landscape.p097a;

import com.sds.android.ttpod.component.landscape.p098b.Scene;

/* renamed from: com.sds.android.ttpod.component.landscape.a.f */
/* loaded from: classes.dex */
public class ActionInstant extends ActionFiniteTime implements Cloneable {

    /* renamed from: c */
    private InterfaceC1253a f4353c;

    /* compiled from: ActionInstant.java */
    /* renamed from: com.sds.android.ttpod.component.landscape.a.f$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1253a {
        /* renamed from: a */
        void mo6281a(Action action);
    }

    public ActionInstant() {
        super(0.0f);
    }

    /* renamed from: a */
    public void m6360a(InterfaceC1253a interfaceC1253a) {
        this.f4353c = interfaceC1253a;
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: a */
    public void mo6344a(Scene scene) {
        super.mo6344a(scene);
        if (this.f4353c != null) {
            this.f4353c.mo6281a(this);
        }
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: a */
    public boolean mo6358a() {
        return true;
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: a */
    public void mo6357a(float f) {
        mo6342b(1.0f);
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionFiniteTime
    /* renamed from: c */
    public ActionInstant clone() {
        return (ActionInstant) super.clone();
    }
}
