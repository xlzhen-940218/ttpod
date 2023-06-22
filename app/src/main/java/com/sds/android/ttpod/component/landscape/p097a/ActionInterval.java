package com.sds.android.ttpod.component.landscape.p097a;

import com.sds.android.ttpod.component.landscape.p098b.Scene;

/* renamed from: com.sds.android.ttpod.component.landscape.a.g */
/* loaded from: classes.dex */
public class ActionInterval extends ActionFiniteTime implements Cloneable {

    /* renamed from: c */
    protected float f4354c;

    /* renamed from: d */
    protected boolean f4355d;

    /* JADX INFO: Access modifiers changed from: protected */
    public ActionInterval(float f) {
        super(f);
        this.f4354c = 0.0f;
        this.f4355d = true;
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: a */
    public boolean mo6358a() {
        return this.f4354c >= this.f4352b;
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: a */
    public void mo6357a(float f) {
        if (this.f4355d) {
            this.f4355d = false;
            this.f4354c = 0.0f;
        } else {
            this.f4354c += f;
        }
        mo6342b(Math.min(1.0f, this.f4354c / this.f4352b));
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: a */
    public void mo6344a(Scene scene) {
        super.mo6344a(scene);
        this.f4354c = 0.0f;
        this.f4355d = true;
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionFiniteTime
    /* renamed from: d */
    public ActionInterval clone() {
        return (ActionInterval) super.clone();
    }
}
