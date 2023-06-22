package com.sds.android.ttpod.activities.setting;

import android.widget.Checkable;

/* renamed from: com.sds.android.ttpod.activities.setting.a */
/* loaded from: classes.dex */
public class CheckableSettingItem extends SettingItem implements Checkable {

    /* renamed from: a */
    private boolean f2995a;

    public CheckableSettingItem(int i, int i2, int i3, int i4, int i5, boolean z) {
        super(i, i2, i3, i4, i5);
        this.f2995a = z;
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.f2995a;
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        this.f2995a = z;
    }

    @Override // android.widget.Checkable
    public void toggle() {
        this.f2995a = !this.f2995a;
    }
}
