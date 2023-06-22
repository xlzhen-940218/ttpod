package com.sds.android.ttpod.activities.setting;

import android.graphics.drawable.Drawable;
import com.sds.android.ttpod.component.p085b.ActionItem;

/* renamed from: com.sds.android.ttpod.activities.setting.c */
/* loaded from: classes.dex */
public class SettingItem extends ActionItem {

    /* renamed from: a */
    private boolean f3010a;

    /* renamed from: b */
    private Drawable f3011b;

    /* renamed from: c */
    private int f3012c;

    public SettingItem(int i, int i2, int i3, int i4, int i5) {
        super(i, i2, i3);
        this.f3010a = true;
        m7008b(i4);
        if (i5 != 0) {
            this.f3011b = m7002h().getDrawable(i5);
        }
    }

    public SettingItem(int i, int i2, int i3, int i4, int i5, boolean z) {
        super(i, 0, i3, i2);
        this.f3010a = true;
        m7008b(i4);
        this.f3012c = i5;
    }

    public SettingItem(int i, int i2, int i3, CharSequence charSequence, int i4) {
        super(i, i2, i3);
        this.f3010a = true;
        if (i4 != 0) {
            this.f3011b = m7002h().getDrawable(i4);
        }
        m7010a(charSequence);
    }

    public SettingItem(int i, int i2, int i3, CharSequence charSequence, int i4, boolean z) {
        super(i, 0, i3, i2);
        this.f3010a = true;
        m7010a(charSequence);
        this.f3012c = i4;
    }

    /* renamed from: a */
    public Drawable m7783a() {
        return this.f3011b;
    }

    /* renamed from: a */
    public void m7782a(Drawable drawable) {
        this.f3011b = drawable;
    }

    /* renamed from: b */
    public int m7781b() {
        return this.f3012c;
    }

    /* renamed from: c */
    public boolean m7780c() {
        return this.f3010a;
    }
}
