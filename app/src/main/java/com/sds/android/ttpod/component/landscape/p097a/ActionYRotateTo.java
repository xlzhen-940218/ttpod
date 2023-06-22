package com.sds.android.ttpod.component.landscape.p097a;

import com.sds.android.ttpod.component.landscape.p098b.Scene;

/* renamed from: com.sds.android.ttpod.component.landscape.a.o */
/* loaded from: classes.dex */
public class ActionYRotateTo extends ActionRotateTo implements Cloneable {
    public ActionYRotateTo(float f, float f2) {
        super(f, f2);
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionInterval, com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: a */
    public void mo6344a(Scene scene) {
        super.mo6344a(scene);
        this.f4359e = scene.m6257m().m6243d();
        this.f4359e = this.f4359e > 0.0f ? this.f4359e % 360.0f : this.f4359e % (-360.0f);
        this.f4361g = ActionXRotateTo.m6349a(this.f4360f, this.f4359e, 360.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: b */
    public void mo6342b(float f) {
        this.f4351a.m6257m().m6245c(this.f4359e + (this.f4361g * f));
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionRotateTo, com.sds.android.ttpod.component.landscape.p097a.ActionInterval, com.sds.android.ttpod.component.landscape.p097a.ActionFiniteTime
    /* renamed from: f */
    public ActionYRotateTo clone() {
        return (ActionYRotateTo) super.clone();
    }
}
