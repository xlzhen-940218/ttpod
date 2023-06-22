package com.sds.android.ttpod.component.landscape.p097a;

import com.sds.android.ttpod.component.landscape.p098b.Scene;

/* renamed from: com.sds.android.ttpod.component.landscape.a.m */
/* loaded from: classes.dex */
public class ActionXDecelerateRotateTo extends ActionXRotateTo implements Cloneable {

    /* renamed from: h */
    protected float f4373h;

    /* renamed from: i */
    protected float f4374i;

    public ActionXDecelerateRotateTo(float f, float f2) {
        super(f, f2);
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionXRotateTo, com.sds.android.ttpod.component.landscape.p097a.ActionInterval, com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: a */
    public void mo6344a(Scene scene) {
        super.mo6344a(scene);
        this.f4373h = 2.0f * this.f4361g;
        this.f4374i = -this.f4373h;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionXRotateTo, com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: b */
    public void mo6342b(float f) {
        this.f4351a.m6257m().m6248b(this.f4359e + (this.f4373h * f) + (((this.f4374i * f) * f) / 2.0f));
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionXRotateTo
    /* renamed from: f */
    public ActionXDecelerateRotateTo clone() {
        return (ActionXDecelerateRotateTo) super.clone();
    }
}
