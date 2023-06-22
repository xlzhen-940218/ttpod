package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.lockscreen.p101a.p102a.ObjectAnimator;

/* loaded from: classes.dex */
public class DataListFooterView extends androidx.appcompat.widget.AppCompatTextView {

    /* renamed from: a */
    private Drawable f7521a;

    /* renamed from: b */
    private ObjectAnimator f7522b;

    public DataListFooterView(Context context) {
        this(context, null);
    }

    public DataListFooterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m1876a(context);
    }

    public DataListFooterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        m1876a(context);
    }

    /* renamed from: a */
    private void m1876a(Context context) {
        setPadding(0, (int) getResources().getDimension(R.dimen.footer_padding), 0, (int) getResources().getDimension(R.dimen.footer_padding));
        setTextSize(12.0f);
        this.f7521a = context.getResources().getDrawable(R.drawable.xml_refresh);
        this.f7521a.setBounds(0, 0, this.f7521a.getIntrinsicWidth(), this.f7521a.getIntrinsicHeight());
        setGravity(17);
        setVisibility(View.GONE);
        this.f7522b = ObjectAnimator.m6058a((Object) this.f7521a, "level", 0, 10000);
        this.f7522b.mo5991b(1000L);
        this.f7522b.m6002a(-1);
        this.f7522b.m6000a(new LinearInterpolator());
    }

    /* renamed from: a */
    public void m1877a() {
        setVisibility(View.VISIBLE);
        setText(R.string.loading);
        setCompoundDrawablesWithIntrinsicBounds((Drawable) null, this.f7521a, (Drawable) null, (Drawable) null);
        this.f7522b.mo6004a();
    }

    /* renamed from: a */
    public void m1875a(String str) {
        setVisibility(View.VISIBLE);
        m1872d();
        setText(str);
        setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
    }

    /* renamed from: b */
    public void m1874b() {
        setVisibility(View.VISIBLE);
        m1872d();
        setText(R.string.load_comment_fail);
    }

    /* renamed from: c */
    public void m1873c() {
        setVisibility(View.INVISIBLE);
        m1872d();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m1872d();
    }

    /* renamed from: d */
    private void m1872d() {
        this.f7522b.mo5992b();
    }
}
