package com.sds.android.ttpod.framework.modules.skin.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/* loaded from: classes.dex */
public class AnimationImageView extends androidx.appcompat.widget.AppCompatImageView {

    /* renamed from: a */
    private boolean f6716a;

    /* renamed from: b */
    private boolean f6717b;

    /* renamed from: c */
    private AnimationDrawable f6718c;

    /* renamed from: d */
    private Drawable f6719d;

    /* renamed from: e */
    private boolean f6720e;

    public AnimationImageView(Context context) {
        super(context);
    }

    public AnimationImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AnimationImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setIgnoreFocusChanged(boolean z) {
        this.f6720e = z;
    }

    /* renamed from: a */
    public void m3504a() {
        this.f6717b = true;
        m3496c();
    }

    /* renamed from: b */
    public void m3499b() {
        this.f6717b = false;
        m3494d();
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        if (this.f6719d != drawable) {
            this.f6719d = drawable;
            if (!this.f6717b) {
                super.setImageDrawable(drawable);
            }
        }
    }

    public void setAnimationDrawable(AnimationDrawable animationDrawable) {
        if (this.f6718c != animationDrawable) {
            this.f6718c = animationDrawable;
            if (this.f6717b) {
                m3496c();
            }
        }
    }

    public void setAnimationResource(int i) {
        Drawable drawable = getResources().getDrawable(i);
        if (drawable instanceof AnimationDrawable) {
            setAnimationDrawable((AnimationDrawable) drawable);
        }
    }

    /* renamed from: c */
    private void m3496c() {
        post(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.skin.view.Animation.1
            @Override // java.lang.Runnable
            public void run() {
                if (AnimationImageView.this.f6718c != null) {
                    if (AnimationImageView.this.getDrawable() != AnimationImageView.this.f6718c) {
                        AnimationImageView.super.setImageDrawable(AnimationImageView.this.f6718c);
                        AnimationImageView.this.f6716a = AnimationImageView.this.f6718c.isRunning();
                    }
                    if (!AnimationImageView.this.f6716a) {
                        AnimationImageView.this.f6718c.start();
                        AnimationImageView.this.f6716a = true;
                    }
                }
            }
        });
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (!this.f6720e) {
            m3500a(z);
        }
    }

    /* renamed from: d */
    private void m3494d() {
        post(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.skin.view.Animation.2
            @Override // java.lang.Runnable
            public void run() {
                if (AnimationImageView.this.getDrawable() == AnimationImageView.this.f6718c) {
                    if (AnimationImageView.this.f6718c != null) {
                        AnimationImageView.this.f6718c.stop();
                    }
                    if (AnimationImageView.this.f6719d != null) {
                        AnimationImageView.super.setImageDrawable(AnimationImageView.this.f6719d);
                    }
                    AnimationImageView.this.f6716a = false;
                }
            }
        });
    }

    /* renamed from: a */
    private void m3500a(boolean z) {
        if (z) {
            if (this.f6717b) {
                m3496c();
                return;
            }
            return;
        }
        m3494d();
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        m3500a(z);
    }
}
