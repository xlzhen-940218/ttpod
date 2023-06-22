package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/* loaded from: classes.dex */
public class CheckImageView extends ImageView {

    /* renamed from: a */
    private boolean f7492a;

    /* renamed from: b */
    private InterfaceC2165a f7493b;

    /* renamed from: c */
    private Drawable f7494c;

    /* renamed from: d */
    private Drawable f7495d;

    /* renamed from: e */
    private View.OnClickListener f7496e;

    /* renamed from: com.sds.android.ttpod.widget.CheckImageView$a */
    /* loaded from: classes.dex */
    public interface InterfaceC2165a {
        /* renamed from: a */
        void mo1895a(CheckImageView checkImageView, boolean z, boolean z2);
    }

    public CheckImageView(Context context) {
        super(context);
        this.f7496e = new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.CheckImageView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CheckImageView.this.m1896a(!CheckImageView.this.f7492a, true);
            }
        };
        setOnClickListener(this.f7496e);
    }

    public CheckImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7496e = new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.CheckImageView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CheckImageView.this.m1896a(!CheckImageView.this.f7492a, true);
            }
        };
        setOnClickListener(this.f7496e);
    }

    public CheckImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7496e = new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.CheckImageView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CheckImageView.this.m1896a(!CheckImageView.this.f7492a, true);
            }
        };
        setOnClickListener(this.f7496e);
    }

    /* renamed from: a */
    public void m1899a(int i, int i2) {
        if (i2 != 0) {
            this.f7495d = getContext().getResources().getDrawable(i2);
        } else {
            this.f7495d = null;
        }
        if (i != 0) {
            this.f7494c = getContext().getResources().getDrawable(i);
        } else {
            this.f7494c = this.f7495d;
        }
        setImageDrawable(this.f7492a ? this.f7495d : this.f7494c);
    }

    /* renamed from: a */
    public boolean m1900a() {
        return this.f7492a;
    }

    public void setChecked(boolean z) {
        m1896a(z, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m1896a(boolean z, boolean z2) {
        if (this.f7492a != z) {
            this.f7492a = z;
            if (this.f7493b != null) {
                this.f7493b.mo1895a(this, this.f7492a, z2);
            }
            setImageDrawable(this.f7492a ? this.f7495d : this.f7494c);
        }
    }

    public void setOnCheckedChangeListener(InterfaceC2165a interfaceC2165a) {
        this.f7493b = interfaceC2165a;
    }
}
