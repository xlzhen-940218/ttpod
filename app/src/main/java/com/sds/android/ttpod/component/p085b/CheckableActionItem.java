package com.sds.android.ttpod.component.p085b;

import android.widget.Checkable;

/* renamed from: com.sds.android.ttpod.component.b.d */
/* loaded from: classes.dex */
public class CheckableActionItem extends ActionItem implements Checkable {

    /* renamed from: a */
    private boolean f3828a;

    public CheckableActionItem(int i, int i2) {
        this(i, i2, false);
    }

    public CheckableActionItem(int i, int i2, boolean z) {
        super(i, 0, i2);
        this.f3828a = false;
        this.f3828a = z;
    }

    public CheckableActionItem(int i, int i2, int i3, int i4, boolean z) {
        super(i, i2, i3, i4);
        this.f3828a = false;
        this.f3828a = z;
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.f3828a;
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        this.f3828a = z;
    }

    @Override // android.widget.Checkable
    public void toggle() {
        this.f3828a = !this.f3828a;
    }
}
