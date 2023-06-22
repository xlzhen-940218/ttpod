package com.sds.android.ttpod.component.landscape.p097a;

import com.sds.android.ttpod.component.landscape.p098b.Scene;

/* renamed from: com.sds.android.ttpod.component.landscape.a.n */
/* loaded from: classes.dex */
public class ActionXRotateTo extends ActionRotateTo implements Cloneable {
    public ActionXRotateTo(float f, float f2) {
        super(f, f2);
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionInterval, com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: a */
    public void mo6344a(Scene scene) {
        super.mo6344a(scene);
        this.f4359e = scene.m6257m().m6246c();
        this.f4359e = this.f4359e > 0.0f ? this.f4359e % 360.0f : this.f4359e % (-360.0f);
        this.f4361g = m6349a(this.f4360f, this.f4359e, 360.0f);
    }

    /* renamed from: a */
    public static float m6349a(float f, float f2, float f3) {
        float f4 = f - f2;
        if (f4 > 180.0f) {
            return f4 - f3;
        }
        if (f4 < -180.0f) {
            return f4 + f3;
        }
        return f4;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: b */
    public void mo6342b(float f) {
        this.f4351a.m6257m().m6248b(this.f4359e + (this.f4361g * f));
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionRotateTo, com.sds.android.ttpod.component.landscape.p097a.ActionInterval, com.sds.android.ttpod.component.landscape.p097a.ActionFiniteTime
    /* renamed from: g */
    public ActionXRotateTo clone() {
        return (ActionXRotateTo) super.clone();
    }
}
