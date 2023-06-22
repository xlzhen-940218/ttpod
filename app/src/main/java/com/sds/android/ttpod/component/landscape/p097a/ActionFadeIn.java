package com.sds.android.ttpod.component.landscape.p097a;

/* renamed from: com.sds.android.ttpod.component.landscape.a.c */
/* loaded from: classes.dex */
public class ActionFadeIn extends ActionInterval implements Cloneable {
    public ActionFadeIn(float f) {
        super(f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: b */
    public void mo6342b(float f) {
        this.f4351a.m6257m().m6230h(f);
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionInterval, com.sds.android.ttpod.component.landscape.p097a.ActionFiniteTime
    /* renamed from: c */
    public ActionFadeIn clone() {
        return (ActionFadeIn) super.clone();
    }
}
