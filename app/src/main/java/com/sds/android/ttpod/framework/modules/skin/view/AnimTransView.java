package com.sds.android.ttpod.framework.modules.skin.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import androidx.core.view.ViewCompat;
import android.util.AttributeSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.sds.android.ttpod.framework.modules.skin.p130c.ClipBitmapDrawable;
import com.sds.android.ttpod.framework.p106a.PlatformUtils;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class AnimTransView extends androidx.appcompat.widget.AppCompatImageView {

    /* renamed from: a */
    private final Rect f6697a;

    /* renamed from: b */
    private Drawable f6698b;

    /* renamed from: c */
    private Drawable f6699c;

    /* renamed from: d */
    private Drawable f6700d;

    /* renamed from: e */
    private int f6701e;

    /* renamed from: f */
    private int f6702f;

    /* renamed from: g */
    private int f6703g;

    /* renamed from: h */
    private int f6704h;

    /* renamed from: i */
    private int f6705i;

    /* renamed from: j */
    private int f6706j;

    /* renamed from: k */
    private int f6707k;

    /* renamed from: l */
    private int f6708l;

    /* renamed from: m */
    private Paint f6709m;

    /* renamed from: n */
    private Bitmap f6710n;

    /* renamed from: o */
    private android.view.animation.Animation f6711o;

    /* renamed from: p */
    private android.view.animation.Animation f6712p;

    /* renamed from: q */
    private android.view.animation.Animation f6713q;

    /* renamed from: r */
    private HandlerC1989a f6714r;

    public AnimTransView(Context context) {
        super(context);
        this.f6697a = new Rect();
        this.f6698b = null;
        this.f6699c = null;
        this.f6706j = 5;
        this.f6707k = -1862270977;
        this.f6708l = ViewCompat.MEASURED_SIZE_MASK;
        this.f6714r = new HandlerC1989a(this);
        m3513a(context);
        m3505g();
    }

    public AnimTransView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f6697a = new Rect();
        this.f6698b = null;
        this.f6699c = null;
        this.f6706j = 5;
        this.f6707k = -1862270977;
        this.f6708l = ViewCompat.MEASURED_SIZE_MASK;
        this.f6714r = new HandlerC1989a(this);
        m3513a(context);
        m3505g();
    }

    public AnimTransView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f6697a = new Rect();
        this.f6698b = null;
        this.f6699c = null;
        this.f6706j = 5;
        this.f6707k = -1862270977;
        this.f6708l = ViewCompat.MEASURED_SIZE_MASK;
        this.f6714r = new HandlerC1989a(this);
        m3513a(context);
        m3505g();
    }

    /* renamed from: a */
    private void m3513a(Context context) {
        if (this.f6699c == null) {
            this.f6699c = getDrawable();
        }
        if (this.f6712p == null) {
            this.f6712p = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
        }
        if (this.f6713q == null) {
            this.f6713q = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
        }
    }

    /* renamed from: b */
    private void m3510b() {
        if (PlatformUtils.m4656a()) {
            if (this.f6705i > 0 && (this.f6707k != 0 || this.f6708l != 0)) {
                if (this.f6709m == null) {
                    this.f6709m = new Paint();
                    this.f6709m.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
                }
                this.f6709m.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, this.f6705i, this.f6707k, this.f6708l, Shader.TileMode.CLAMP));
                int i = this.f6703g - this.f6701e;
                int i2 = this.f6704h - this.f6702f;
                if (this.f6710n == null || this.f6710n.getWidth() < i || this.f6710n.getHeight() < i2) {
                    m3509c();
                    if (i > 0) {
                        this.f6710n = Bitmap.createBitmap(i, this.f6705i, Bitmap.Config.ARGB_8888);
                        return;
                    }
                    return;
                }
                return;
            }
            this.f6709m = null;
            this.f6710n = null;
        }
    }

    /* renamed from: c */
    private void m3509c() {
        if (this.f6710n != null) {
            this.f6710n.recycle();
            this.f6710n = null;
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        m3505g();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m3509c();
        this.f6714r.removeCallbacksAndMessages(null);
    }

    /* renamed from: d */
    private void m3508d() {
        int i = (this.f6705i > 0 ? this.f6706j : 0) + this.f6705i;
        int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
        int height = ((getHeight() - getPaddingTop()) - getPaddingBottom()) - i;
        this.f6701e = getPaddingLeft();
        this.f6702f = getPaddingTop();
        this.f6703g = this.f6701e + width;
        this.f6704h = this.f6702f + height;
        if (width > 0 && height > 0) {
            m3507e();
        }
    }

    /* renamed from: e */
    private void m3507e() {
        Drawable drawable = this.f6700d;
        super.setImageDrawable(null);
        setImageDrawableInner(drawable);
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        super.setScaleType(scaleType);
        setWillNotCacheDrawing(false);
        m3505g();
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(i, i2, i3, i4);
        m3505g();
    }

    /* renamed from: f */
    private void m3506f() {
        if (PlatformUtils.m4656a() && this.f6710n != null && this.f6705i > 0) {
            this.f6710n.eraseColor(0);
            Drawable drawable = getDrawable();
            if (drawable != null) {
                Canvas canvas = new Canvas(this.f6710n);
                canvas.save();
                canvas.translate(0.0f, this.f6704h - this.f6702f);
                canvas.scale(1.0f, -1.0f);
                m3512a(canvas, drawable);
                canvas.restore();
                if (this.f6709m != null) {
                    canvas.drawRect(0.0f, 0.0f, this.f6703g - this.f6701e, this.f6705i, this.f6709m);
                }
            }
        }
    }

    /* renamed from: g */
    private void m3505g() {
        m3508d();
        m3510b();
        m3506f();
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        m3507e();
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        if (drawable == null || ((drawable instanceof BitmapDrawable) && ((BitmapDrawable) drawable).getBitmap() == null)) {
            drawable = this.f6699c;
        }
        if (drawable != this.f6700d) {
            if (getWidth() > 0 && getHeight() > 0) {
                android.view.animation.Animation animation = getAnimation();
                if (animation != null && animation != this.f6712p && animation != this.f6713q && animation.hasStarted() && !animation.hasEnded()) {
                    this.f6700d = drawable;
                    return;
                }
                clearAnimation();
                mo3515a();
                this.f6698b = null;
                if (isShown() && getGlobalVisibleRect(this.f6697a)) {
                    if (this.f6700d == null) {
                        setImageDrawableInner(drawable);
                        startAnimation(this.f6712p);
                        return;
                    } else if (this.f6713q != null) {
                        this.f6698b = drawable;
                        startAnimation(this.f6713q);
                        return;
                    } else {
                        setImageDrawableInner(drawable);
                        startAnimation(this.f6712p);
                        return;
                    }
                }
                setImageDrawableInner(drawable);
                return;
            }
            setImageDrawableInner(drawable);
        }
    }

    private void setImageDrawableInner(Drawable drawable) {
        if (drawable != null) {
            if (drawable != this.f6700d) {
                this.f6700d = drawable;
            }
        } else {
            this.f6700d = this.f6699c;
        }
        setScaledDrawable(this.f6700d);
        m3506f();
    }

    private void setScaledDrawable(Drawable drawable) {
        Drawable clipBitmapDrawable = (!(drawable instanceof BitmapDrawable) || (drawable instanceof ClipBitmapDrawable)) ? drawable : new ClipBitmapDrawable(getResources(), ((BitmapDrawable) drawable).getBitmap());
        if (clipBitmapDrawable instanceof ClipBitmapDrawable) {
            ((ClipBitmapDrawable) clipBitmapDrawable).m3751a(this.f6703g - this.f6701e, this.f6704h - this.f6702f);
        }
        super.setImageDrawable(clipBitmapDrawable);
    }

    public void setImageBitmapDelay(Bitmap bitmap) {
        this.f6714r.removeCallbacksAndMessages(null);
        Message obtainMessage = this.f6714r.obtainMessage(0);
        obtainMessage.obj = bitmap;
        this.f6714r.sendMessageDelayed(obtainMessage, bitmap == null ? 600L : 300L);
    }

    public void setInAnimation(android.view.animation.Animation animation) {
        this.f6712p = animation;
    }

    public void setOutAnimation(android.view.animation.Animation animation) {
        this.f6713q = animation;
    }

    public void setInAnimation(int i) {
        setInAnimation(AnimationUtils.loadAnimation(getContext(), i));
    }

    public void setOutAnimation(int i) {
        setOutAnimation(AnimationUtils.loadAnimation(getContext(), i));
    }

    @Override // android.view.View
    public void startAnimation(android.view.animation.Animation animation) {
        if (animation != null) {
            if (animation == this.f6712p || animation == this.f6713q) {
                this.f6711o = animation;
            } else {
                this.f6711o = null;
            }
            super.startAnimation(animation);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            canvas.save();
            canvas.translate(this.f6701e, this.f6702f);
            m3512a(canvas, drawable);
            if (this.f6705i > 0 && this.f6710n != null && PlatformUtils.m4656a()) {
                canvas.translate(0.0f, (this.f6704h - this.f6702f) + this.f6706j);
                canvas.drawBitmap(this.f6710n, 0.0f, 0.0f, (Paint) null);
            }
            canvas.restore();
        }
    }

    /* renamed from: a */
    private void m3512a(Canvas canvas, Drawable drawable) {
        drawable.setBounds(0, 0, this.f6703g - this.f6701e, this.f6704h - this.f6702f);
        drawable.draw(canvas);
    }

    @Override // android.view.View
    protected void onAnimationEnd() {
        super.onAnimationEnd();
        Drawable drawable = this.f6698b;
        this.f6698b = null;
        if (this.f6711o != null && this.f6711o == this.f6713q) {
            setImageDrawableInner(drawable);
            if (getDrawable() != null && this.f6712p != null) {
                startAnimation(this.f6712p);
            }
        } else if (this.f6700d != getDrawable()) {
            setImageDrawable(this.f6700d);
        }
    }

    public void setDefaultImageDrawable(Drawable drawable) {
        if (drawable != this.f6699c) {
            this.f6699c = drawable;
            if (getDrawable() == null) {
                setImageDrawableInner(null);
            }
        }
    }

    public void setDefaultImageBitmap(Bitmap bitmap) {
        setDefaultImageDrawable(new BitmapDrawable(getContext().getResources(), bitmap));
    }

    public void setDefaultImageResource(int i) {
        setDefaultImageDrawable(getContext().getResources().getDrawable(i));
    }

    public Drawable getDefaultDrawable() {
        return this.f6699c;
    }

    /* renamed from: a */
    protected void mo3515a() {
    }

    public void setReflectionHeight(int i) {
        this.f6705i = i;
        m3505g();
    }

    public void setDivideHeight(int i) {
        this.f6706j = i;
        m3508d();
    }

    /* renamed from: a */
    public void m3514a(int i, int i2) {
        this.f6707k = i;
        this.f6708l = i2;
        m3506f();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        m3505g();
    }

    /* renamed from: com.sds.android.ttpod.framework.modules.skin.view.AnimTransView$a */
    /* loaded from: classes.dex */
    static class HandlerC1989a extends Handler {

        /* renamed from: a */
        private WeakReference<AnimTransView> f6715a;

        public HandlerC1989a(AnimTransView animTransView) {
            this.f6715a = new WeakReference<>(animTransView);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            AnimTransView animTransView = this.f6715a.get();
            if (animTransView != null) {
                Bitmap bitmap = (Bitmap) message.obj;
                animTransView.clearAnimation();
                if (bitmap == null || bitmap.isRecycled()) {
                    animTransView.setImageDrawable(animTransView.f6699c);
                } else {
                    animTransView.setImageBitmap(bitmap);
                }
            }
        }
    }
}
