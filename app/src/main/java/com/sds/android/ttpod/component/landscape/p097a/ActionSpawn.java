package com.sds.android.ttpod.component.landscape.p097a;

import com.sds.android.ttpod.component.landscape.p098b.Scene;

/* renamed from: com.sds.android.ttpod.component.landscape.a.l */
/* loaded from: classes.dex */
public class ActionSpawn extends ActionInterval implements Cloneable {

    /* renamed from: e */
    protected ActionFiniteTime f4371e;

    /* renamed from: f */
    protected ActionFiniteTime f4372f;

    public ActionSpawn(ActionFiniteTime actionFiniteTime, ActionFiniteTime actionFiniteTime2) {
        super(Math.max(actionFiniteTime.f4352b, actionFiniteTime2.f4352b));
        this.f4371e = actionFiniteTime;
        this.f4372f = actionFiniteTime2;
        float f = actionFiniteTime.f4352b;
        float f2 = actionFiniteTime2.f4352b;
        if (f > f2) {
            this.f4372f = new ActionSequence(actionFiniteTime2, new ActionDelayTime(f - f2));
        } else {
            this.f4371e = new ActionSequence(actionFiniteTime, new ActionDelayTime(f2 - f));
        }
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionInterval, com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: a */
    public void mo6344a(Scene scene) {
        super.mo6344a(scene);
        this.f4371e.mo6344a(scene);
        this.f4372f.mo6344a(scene);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: b */
    public void mo6342b(float f) {
        this.f4371e.mo6342b(f);
        this.f4372f.mo6342b(f);
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionInterval, com.sds.android.ttpod.component.landscape.p097a.ActionFiniteTime
    /* renamed from: c */
    public ActionSpawn clone() {
        ActionSpawn actionSpawn = (ActionSpawn) super.clone();
        actionSpawn.f4371e = this.f4371e.clone();
        actionSpawn.f4372f = this.f4372f.clone();
        return actionSpawn;
    }
}
