package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
import com.sds.android.ttpod.framework.modules.skin.p130c.ClipBitmapDrawable;

/* loaded from: classes.dex */
public class SizeChangeableListView extends ListView {

    /* renamed from: a */
    private InterfaceC2233a f7945a;

    /* renamed from: com.sds.android.ttpod.widget.SizeChangeableListView$a */
    /* loaded from: classes.dex */
    public interface InterfaceC2233a {
        /* renamed from: a */
        void m1535a(View view, int i, int i2, int i3, int i4);
    }

    public SizeChangeableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SizeChangeableListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setOnSizeChangedListener(InterfaceC2233a interfaceC2233a) {
        this.f7945a = interfaceC2233a;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Drawable background = getBackground();
        if (background instanceof ClipBitmapDrawable) {
            ((ClipBitmapDrawable) background).m3751a(i, i2);
        }
        if (this.f7945a != null) {
            this.f7945a.m1535a(this, i, i2, i3, i4);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if ((drawable instanceof BitmapDrawable) && !(drawable instanceof ClipBitmapDrawable)) {
            drawable = new ClipBitmapDrawable(getResources(), ((BitmapDrawable) drawable).getBitmap());
        }
        super.setBackgroundDrawable(drawable);
    }
}
