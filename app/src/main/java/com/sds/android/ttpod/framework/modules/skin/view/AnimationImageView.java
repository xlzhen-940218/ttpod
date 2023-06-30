package com.sds.android.ttpod.framework.modules.skin.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/* loaded from: classes.dex */
public class AnimationImageView extends androidx.appcompat.widget.AppCompatImageView {

    /* renamed from: a */
    private boolean animRunning;

    /* renamed from: b */
    private boolean startAnim;

    /* renamed from: c */
    private AnimationDrawable animationDrawable;

    /* renamed from: d */
    private Drawable imageDrawable;

    /* renamed from: e */
    private boolean ignoreFocusChanged;

    public AnimationImageView(Context context) {
        super(context);
    }

    public AnimationImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AnimationImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setIgnoreFocusChanged(boolean ignoreFocusChanged) {
        this.ignoreFocusChanged = ignoreFocusChanged;
    }

    /* renamed from: a */
    public void startAnim() {
        this.startAnim = true;
        start();
    }

    /* renamed from: b */
    public void stopAnim() {
        this.startAnim = false;
        stop();
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        if (this.imageDrawable != drawable) {
            this.imageDrawable = drawable;
            if (!this.startAnim) {
                super.setImageDrawable(drawable);
            }
        }
    }

    public void setAnimationDrawable(AnimationDrawable animationDrawable) {
        if (this.animationDrawable != animationDrawable) {
            this.animationDrawable = animationDrawable;
            if (this.startAnim) {
                start();
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
    private void start() {
        post(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.skin.view.Animation.1
            @Override // java.lang.Runnable
            public void run() {
                if (AnimationImageView.this.animationDrawable != null) {
                    if (AnimationImageView.this.getDrawable() != AnimationImageView.this.animationDrawable) {
                        AnimationImageView.super.setImageDrawable(AnimationImageView.this.animationDrawable);
                        AnimationImageView.this.animRunning = AnimationImageView.this.animationDrawable.isRunning();
                    }
                    if (!AnimationImageView.this.animRunning) {
                        AnimationImageView.this.animationDrawable.start();
                        AnimationImageView.this.animRunning = true;
                    }
                }
            }
        });
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (!this.ignoreFocusChanged) {
            changeAnimType(z);
        }
    }

    /* renamed from: d */
    private void stop() {
        post(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.skin.view.Animation.2
            @Override // java.lang.Runnable
            public void run() {
                if (AnimationImageView.this.getDrawable() == AnimationImageView.this.animationDrawable) {
                    if (AnimationImageView.this.animationDrawable != null) {
                        AnimationImageView.this.animationDrawable.stop();
                    }
                    if (AnimationImageView.this.imageDrawable != null) {
                        AnimationImageView.super.setImageDrawable(AnimationImageView.this.imageDrawable);
                    }
                    AnimationImageView.this.animRunning = false;
                }
            }
        });
    }

    /* renamed from: a */
    private void changeAnimType(boolean start) {
        if (start) {
            if (this.startAnim) {
                start();
                return;
            }
            return;
        }
        stop();
    }

    @Override // android.view.View
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        changeAnimType(enabled);
    }
}
