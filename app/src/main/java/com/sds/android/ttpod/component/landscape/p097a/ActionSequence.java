package com.sds.android.ttpod.component.landscape.p097a;

import com.sds.android.ttpod.component.landscape.p098b.Scene;

/* renamed from: com.sds.android.ttpod.component.landscape.a.k */
/* loaded from: classes.dex */
public class ActionSequence extends ActionInterval implements Cloneable {

    /* renamed from: e */
    protected ActionFiniteTime[] f4368e;

    /* renamed from: f */
    protected float f4369f;

    /* renamed from: g */
    protected int f4370g;

    public ActionSequence(ActionFiniteTime actionFiniteTime, ActionFiniteTime actionFiniteTime2) {
        super(actionFiniteTime.f4352b + actionFiniteTime2.f4352b);
        this.f4368e = new ActionFiniteTime[2];
        this.f4368e[0] = actionFiniteTime;
        this.f4368e[1] = actionFiniteTime2;
        this.f4369f = 0.0f;
        this.f4370g = 0;
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionInterval, com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: a */
    public void mo6344a(Scene scene) {
        super.mo6344a(scene);
        this.f4369f = this.f4368e[0].f4352b / this.f4352b;
        this.f4370g = -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.component.landscape.p097a.Action
    /* renamed from: b */
    public void mo6342b(float f) {
        if (this.f4368e[0].f4352b == 0.0f) {
            if (this.f4370g == -1) {
                this.f4368e[0].mo6344a(this.f4351a);
                this.f4368e[0].mo6342b(1.0f);
                this.f4368e[1].mo6344a(this.f4351a);
            }
            this.f4368e[1].mo6342b(f);
            this.f4370g = 1;
        } else if (this.f4368e[1].f4352b == 0.0f) {
            if (this.f4370g == -1) {
                this.f4368e[0].mo6344a(this.f4351a);
            }
            this.f4368e[0].mo6342b(f);
            this.f4370g = 0;
            if (Math.abs(f - 1.0f) < 1.0E-6f) {
                this.f4368e[1].mo6344a(this.f4351a);
                this.f4368e[1].mo6342b(1.0f);
            }
        } else if (Math.abs(f - 1.0f) < 1.0E-6f) {
            if (this.f4370g == -1) {
                this.f4368e[0].mo6344a(this.f4351a);
                this.f4368e[0].mo6342b(1.0f);
                this.f4368e[1].mo6344a(this.f4351a);
                this.f4368e[1].mo6342b(1.0f);
            } else if (this.f4370g == 0) {
                this.f4368e[0].mo6342b(1.0f);
                this.f4368e[1].mo6344a(this.f4351a);
                this.f4368e[1].mo6342b(1.0f);
            } else if (this.f4370g == 1) {
                this.f4368e[1].mo6342b(1.0f);
            }
        } else if (f > this.f4369f) {
            if (this.f4370g == -1) {
                this.f4368e[0].mo6344a(this.f4351a);
                this.f4368e[0].mo6342b(1.0f);
                this.f4368e[1].mo6344a(this.f4351a);
            } else if (this.f4370g == 0) {
                this.f4368e[0].mo6342b(1.0f);
                this.f4368e[1].mo6344a(this.f4351a);
            }
            this.f4368e[1].mo6342b((f - this.f4369f) / (1.0f - this.f4369f));
            this.f4370g = 1;
        } else if (Math.abs(f - this.f4369f) < 1.0E-6f) {
            if (this.f4370g == -1) {
                this.f4368e[0].mo6344a(this.f4351a);
            }
            this.f4368e[0].mo6342b(1.0f);
            this.f4368e[1].mo6344a(this.f4351a);
            this.f4368e[1].mo6342b(0.0f);
            this.f4370g = 1;
        } else {
            if (this.f4370g == -1) {
                this.f4368e[0].mo6344a(this.f4351a);
            }
            this.f4368e[0].mo6342b(f / this.f4369f);
            this.f4370g = 0;
        }
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionInterval, com.sds.android.ttpod.component.landscape.p097a.ActionFiniteTime
    /* renamed from: c */
    public ActionSequence clone() {
        ActionSequence actionSequence = (ActionSequence) super.clone();
        actionSequence.f4368e = new ActionFiniteTime[2];
        actionSequence.f4368e[0] = this.f4368e[0].clone();
        actionSequence.f4368e[1] = this.f4368e[1].clone();
        return actionSequence;
    }

    /* renamed from: a */
    public static ActionSequence m6353a(ActionFiniteTime... actionFiniteTimeArr) {
        ActionFiniteTime actionSequence;
        int length = actionFiniteTimeArr.length;
        ActionFiniteTime actionFiniteTime = actionFiniteTimeArr[0];
        if (length > 1) {
            actionSequence = actionFiniteTime;
            int i = 1;
            while (i < length) {
                ActionSequence actionSequence2 = new ActionSequence(actionSequence, actionFiniteTimeArr[i]);
                i++;
                actionSequence = actionSequence2;
            }
        } else {
            actionSequence = new ActionSequence(actionFiniteTime, new ActionDelayTime(0.0f));
        }
        return (ActionSequence) actionSequence;
    }
}
