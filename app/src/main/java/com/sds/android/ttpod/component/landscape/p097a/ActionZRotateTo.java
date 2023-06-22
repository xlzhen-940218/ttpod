package com.sds.android.ttpod.component.landscape.p097a;

import com.sds.android.ttpod.component.landscape.p098b.Scene;

/* renamed from: com.sds.android.ttpod.component.landscape.a.r */
/* loaded from: classes.dex */
public class ActionZRotateTo extends ActionRotateTo implements Cloneable {
    public ActionZRotateTo(float f, float f2) {
        super(f, f2);
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionInterval, com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: a */
    public void mo6344a(Scene scene) {
        super.mo6344a(scene);
        this.f4359e = scene.m6257m().m6240e();
        this.f4359e = this.f4359e > 0.0f ? this.f4359e % 360.0f : this.f4359e % (-360.0f);
        this.f4361g = ActionXRotateTo.m6349a(this.f4360f, this.f4359e, 360.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: b */
    public void mo6342b(float f) {
        this.f4351a.m6257m().m6242d(this.f4359e + (this.f4361g * f));
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionRotateTo, com.sds.android.ttpod.component.landscape.p097a.ActionInterval, com.sds.android.ttpod.component.landscape.p097a.ActionFiniteTime
    /* renamed from: f */
    public ActionZRotateTo clone() {
        return (ActionZRotateTo) super.clone();
    }
}
