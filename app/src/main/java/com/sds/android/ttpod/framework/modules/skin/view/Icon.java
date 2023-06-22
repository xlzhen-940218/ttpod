package com.sds.android.ttpod.framework.modules.skin.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import java.util.Vector;

/* loaded from: classes.dex */
public class Icon extends ImageView {

    /* renamed from: a */
    private int f6730a;

    /* renamed from: b */
    private Vector<Drawable> f6731b;

    /* renamed from: c */
    private InterfaceC1992a f6732c;

    /* renamed from: com.sds.android.ttpod.framework.modules.skin.view.Icon$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1992a {
        /* renamed from: a */
        void m3487a(int i);
    }

    public Icon(Context context) {
        super(context);
        this.f6730a = 0;
        this.f6731b = new Vector<>();
    }

    public Icon(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Icon(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f6730a = 0;
        this.f6731b = new Vector<>();
    }

    public void setStateChangedListener(InterfaceC1992a interfaceC1992a) {
        this.f6732c = interfaceC1992a;
    }

    public void setState(int i) {
        if (i < 0) {
            i = 0;
        }
        if (i >= this.f6731b.size()) {
            i = this.f6731b.size() - 1;
        }
        if (i >= 0) {
            this.f6730a = i;
            setImageDrawable(this.f6731b.get(this.f6730a));
        }
        if (this.f6732c != null) {
            this.f6732c.m3487a(i);
        }
    }

    public int getState() {
        return this.f6730a;
    }

    /* renamed from: a */
    public void m3489a(int i, Drawable drawable) {
        int i2;
        this.f6731b.add(i, drawable);
        if (i < this.f6730a) {
            i2 = this.f6730a;
            this.f6730a = i2 + 1;
        } else {
            i2 = this.f6730a;
        }
        this.f6730a = i2;
        setState(this.f6730a);
    }

    /* renamed from: a */
    public void m3488a(Drawable drawable) {
        this.f6731b.add(drawable);
        setState(this.f6730a);
    }
}
