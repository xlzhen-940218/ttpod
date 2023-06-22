package com.sds.android.ttpod.component.landscape.p097a;

import com.sds.android.ttpod.component.landscape.p098b.Scene;

/* renamed from: com.sds.android.ttpod.component.landscape.a.a */
/* loaded from: classes.dex */
public class Action implements Cloneable {

    /* renamed from: a */
    protected Scene f4351a;

    /* renamed from: a */
    public boolean mo6358a() {
        return true;
    }

    /* renamed from: a */
    public void mo6344a(Scene scene) {
        this.f4351a = scene;
    }

    /* renamed from: a */
    public void mo6357a(float f) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public void mo6342b(float f) {
    }

    @Override // 
    /* renamed from: b */
    public Action clone() {
        try {
            return (Action) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
