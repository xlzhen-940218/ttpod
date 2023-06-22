package com.sds.android.ttpod.component.landscape.p097a;

/* renamed from: com.sds.android.ttpod.component.landscape.a.d */
/* loaded from: classes.dex */
public class ActionFadeOut extends ActionInterval implements Cloneable {
    public ActionFadeOut(float f) {
        super(f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: b */
    public void mo6342b(float f) {
        this.f4351a.m6257m().m6230h(1.0f - f);
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionInterval, com.sds.android.ttpod.component.landscape.p097a.ActionFiniteTime
    /* renamed from: c */
    public ActionFadeOut clone() {
        return (ActionFadeOut) super.clone();
    }
}
