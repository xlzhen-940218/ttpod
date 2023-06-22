package com.sds.android.ttpod.component.landscape.p097a;

/* renamed from: com.sds.android.ttpod.component.landscape.a.e */
/* loaded from: classes.dex */
public class ActionFiniteTime extends Action implements Cloneable {

    /* renamed from: b */
    protected float f4352b;

    /* JADX INFO: Access modifiers changed from: protected */
    public ActionFiniteTime(float f) {
        this.f4352b = f == 0.0f ? 1.0E-6f : f;
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: e */
    public ActionFiniteTime clone() {
        return (ActionFiniteTime) super.clone();
    }
}
