package com.sds.android.ttpod.framework.modules.skin.p130c;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.c.c */
/* loaded from: classes.dex */
public class ColorDrawableCreator extends DrawableCreator {

    /* renamed from: a */
    private int[] f6527a;

    /* renamed from: b */
    private GradientDrawable.Orientation f6528b;

    public ColorDrawableCreator(int[] iArr, int i) {
        this.f6527a = iArr;
        m3749a(i);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.DrawableCreator
    /* renamed from: a */
    public Drawable getDrawable(Resources resources) {
        return (this.f6528b == null || this.f6527a.length <= 1) ? new ColorDrawable(this.f6527a[0]) : new GradientDrawable(this.f6528b, this.f6527a);
    }

    /* renamed from: a */
    public void m3749a(int i) {
        switch (i) {
            case 0:
                this.f6528b = GradientDrawable.Orientation.LEFT_RIGHT;
                return;
            case 1:
                this.f6528b = GradientDrawable.Orientation.TOP_BOTTOM;
                return;
            case 2:
                this.f6528b = GradientDrawable.Orientation.RIGHT_LEFT;
                return;
            case 3:
                this.f6528b = GradientDrawable.Orientation.BOTTOM_TOP;
                return;
            case 4:
                this.f6528b = GradientDrawable.Orientation.TL_BR;
                return;
            case 5:
                this.f6528b = GradientDrawable.Orientation.BL_TR;
                return;
            case 6:
                this.f6528b = GradientDrawable.Orientation.TR_BL;
                return;
            case 7:
                this.f6528b = GradientDrawable.Orientation.BR_TL;
                return;
            default:
                this.f6528b = null;
                return;
        }
    }
}
