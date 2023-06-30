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
    private Drawable fadeOutDrawable;

    /* renamed from: c */
    private Drawable defaultDrawable;

    /* renamed from: d */
    private Drawable imageDrawableInner;

    /* renamed from: e */
    private int paddingLeft;

    /* renamed from: f */
    private int paddingTop;

    /* renamed from: g */
    private int width;

    /* renamed from: h */
    private int height;

    /* renamed from: i */
    private int reflectionHeight;

    /* renamed from: j */
    private int divideHieght;

    /* renamed from: k */
    private int reflectionMaskStartColor;

    /* renamed from: l */
    private int reflectionMaskEndColor;

    /* renamed from: m */
    private Paint reflectionPaint;

    /* renamed from: n */
    private Bitmap reflectionBitmap;

    /* renamed from: o */
    private android.view.animation.Animation currentAnim;

    /* renamed from: p */
    private android.view.animation.Animation fadeInAnim;

    /* renamed from: q */
    private android.view.animation.Animation fadeOutAnim;

    /* renamed from: r */
    private AnimHandler animHandler;

    public AnimTransView(Context context) {
        super(context);
        this.f6697a = new Rect();
        this.fadeOutDrawable = null;
        this.defaultDrawable = null;
        this.divideHieght = 5;
        this.reflectionMaskStartColor = -1862270977;
        this.reflectionMaskEndColor = 0x00ffffff;
        this.animHandler = new AnimHandler(this);
        initAnim(context);
        reDraw();
    }

    public AnimTransView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f6697a = new Rect();
        this.fadeOutDrawable = null;
        this.defaultDrawable = null;
        this.divideHieght = 5;
        this.reflectionMaskStartColor = -1862270977;
        this.reflectionMaskEndColor = 0x00ffffff;
        this.animHandler = new AnimHandler(this);
        initAnim(context);
        reDraw();
    }

    public AnimTransView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f6697a = new Rect();
        this.fadeOutDrawable = null;
        this.defaultDrawable = null;
        this.divideHieght = 5;
        this.reflectionMaskStartColor = -1862270977;
        this.reflectionMaskEndColor = 0x00ffffff;
        this.animHandler = new AnimHandler(this);
        initAnim(context);
        reDraw();
    }

    /* renamed from: a */
    private void initAnim(Context context) {
        if (this.defaultDrawable == null) {
            this.defaultDrawable = getDrawable();
        }
        if (this.fadeInAnim == null) {
            this.fadeInAnim = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
        }
        if (this.fadeOutAnim == null) {
            this.fadeOutAnim = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
        }
    }

    /* renamed from: b */
    private void initReflection() {
        if (PlatformUtils.sdkThan14()) {
            if (this.reflectionHeight > 0 && (this.reflectionMaskStartColor != 0 || this.reflectionMaskEndColor != 0)) {
                if (this.reflectionPaint == null) {
                    this.reflectionPaint = new Paint();
                    this.reflectionPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
                }
                this.reflectionPaint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, this.reflectionHeight
                        , this.reflectionMaskStartColor, this.reflectionMaskEndColor, Shader.TileMode.CLAMP));
                int i = this.width - this.paddingLeft;
                int i2 = this.height - this.paddingTop;
                if (this.reflectionBitmap == null || this.reflectionBitmap.getWidth() < i || this.reflectionBitmap.getHeight() < i2) {
                    recycle();
                    if (i > 0) {
                        this.reflectionBitmap = Bitmap.createBitmap(i, this.reflectionHeight, Bitmap.Config.ARGB_8888);
                        return;
                    }
                    return;
                }
                return;
            }
            this.reflectionPaint = null;
            this.reflectionBitmap = null;
        }
    }

    /* renamed from: c */
    private void recycle() {
        if (this.reflectionBitmap != null) {
            this.reflectionBitmap.recycle();
            this.reflectionBitmap = null;
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        reDraw();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        recycle();
        this.animHandler.removeCallbacksAndMessages(null);
    }

    /* renamed from: d */
    private void sizeChanged() {
        int i = (this.reflectionHeight > 0 ? this.divideHieght : 0) + this.reflectionHeight;
        int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
        int height = ((getHeight() - getPaddingTop()) - getPaddingBottom()) - i;
        this.paddingLeft = getPaddingLeft();
        this.paddingTop = getPaddingTop();
        this.width = this.paddingLeft + width;
        this.height = this.paddingTop + height;
        if (width > 0 && height > 0) {
            setImageDrawable();
        }
    }

    /* renamed from: e */
    private void setImageDrawable() {
        Drawable drawable = this.imageDrawableInner;
        super.setImageDrawable(null);
        setImageDrawableInner(drawable);
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        super.setScaleType(scaleType);
        setWillNotCacheDrawing(false);
        reDraw();
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(i, i2, i3, i4);
        reDraw();
    }

    /* renamed from: f */
    private void drawReflection() {
        if (PlatformUtils.sdkThan14() && this.reflectionBitmap != null && this.reflectionHeight > 0) {
            this.reflectionBitmap.eraseColor(0);
            Drawable drawable = getDrawable();
            if (drawable != null) {
                Canvas canvas = new Canvas(this.reflectionBitmap);
                canvas.save();
                canvas.translate(0.0f, this.height - this.paddingTop);
                canvas.scale(1.0f, -1.0f);
                m3512a(canvas, drawable);
                canvas.restore();
                if (this.reflectionPaint != null) {
                    canvas.drawRect(0.0f, 0.0f, this.width - this.paddingLeft, this.reflectionHeight, this.reflectionPaint);
                }
            }
        }
    }

    /* renamed from: g */
    private void reDraw() {
        sizeChanged();
        initReflection();
        drawReflection();
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        setImageDrawable();
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        if (drawable == null || ((drawable instanceof BitmapDrawable) && ((BitmapDrawable) drawable).getBitmap() == null)) {
            drawable = this.defaultDrawable;
        }
        if (drawable != this.imageDrawableInner) {
            if (getWidth() > 0 && getHeight() > 0) {
                android.view.animation.Animation animation = getAnimation();
                if (animation != null && animation != this.fadeInAnim && animation != this.fadeOutAnim && animation.hasStarted() && !animation.hasEnded()) {
                    this.imageDrawableInner = drawable;
                    return;
                }
                clearAnimation();
                loadAnim();
                this.fadeOutDrawable = null;
                if (isShown() /*&& getGlobalVisibleRect(this.f6697a)*/) {
                    if (this.imageDrawableInner == null) {
                        setImageDrawableInner(drawable);
                        startAnimation(this.fadeInAnim);
                        return;
                    } else if (this.fadeOutAnim != null) {
                        this.fadeOutDrawable = drawable;
                        startAnimation(this.fadeOutAnim);
                        return;
                    } else {
                        setImageDrawableInner(drawable);
                        startAnimation(this.fadeInAnim);
                        return;
                    }
                }
                setImageDrawableInner(drawable);
                return;
            }
            setImageDrawableInner(drawable);
        }
    }

    private void setImageDrawableInner(Drawable imageDrawableInner) {
        if (imageDrawableInner != null) {
            if (imageDrawableInner != this.imageDrawableInner) {
                this.imageDrawableInner = imageDrawableInner;
            }
        } else {
            this.imageDrawableInner = this.defaultDrawable;
        }
        setScaledDrawable(this.imageDrawableInner);
        drawReflection();
    }

    private void setScaledDrawable(Drawable drawable) {
        Drawable clipBitmapDrawable = (!(drawable instanceof BitmapDrawable) || (drawable instanceof ClipBitmapDrawable)) ? drawable : new ClipBitmapDrawable(getResources(), ((BitmapDrawable) drawable).getBitmap());
        if (clipBitmapDrawable instanceof ClipBitmapDrawable) {
            ((ClipBitmapDrawable) clipBitmapDrawable).m3751a(this.width - this.paddingLeft, this.height - this.paddingTop);
        }
        super.setImageDrawable(clipBitmapDrawable);
    }

    public void setImageBitmapDelay(Bitmap bitmap) {
        this.animHandler.removeCallbacksAndMessages(null);
        Message obtainMessage = this.animHandler.obtainMessage(0);
        obtainMessage.obj = bitmap;
        this.animHandler.sendMessageDelayed(obtainMessage, bitmap == null ? 600L : 300L);
    }

    public void setInAnimation(android.view.animation.Animation animation) {
        this.fadeInAnim = animation;
    }

    public void setOutAnimation(android.view.animation.Animation animation) {
        this.fadeOutAnim = animation;
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
            if (animation == this.fadeInAnim || animation == this.fadeOutAnim) {
                this.currentAnim = animation;
            } else {
                this.currentAnim = null;
            }
            super.startAnimation(animation);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            canvas.save();
            canvas.translate(this.paddingLeft, this.paddingTop);
            m3512a(canvas, drawable);
            if (this.reflectionHeight > 0 && this.reflectionBitmap != null && PlatformUtils.sdkThan14()) {
                canvas.translate(0.0f, (this.height - this.paddingTop) + this.divideHieght);
                canvas.drawBitmap(this.reflectionBitmap, 0.0f, 0.0f, (Paint) null);
            }
            canvas.restore();
        }
    }

    /* renamed from: a */
    private void m3512a(Canvas canvas, Drawable drawable) {
        drawable.setBounds(0, 0, this.width - this.paddingLeft, this.height - this.paddingTop);
        drawable.draw(canvas);
    }

    @Override // android.view.View
    protected void onAnimationEnd() {
        super.onAnimationEnd();
        Drawable drawable = this.fadeOutDrawable;
        this.fadeOutDrawable = null;
        if (this.currentAnim != null && this.currentAnim == this.fadeOutAnim) {
            setImageDrawableInner(drawable);
            if (getDrawable() != null && this.fadeInAnim != null) {
                startAnimation(this.fadeInAnim);
            }
        } else if (this.imageDrawableInner != getDrawable()) {
            setImageDrawable(this.imageDrawableInner);
        }
    }

    public void setDefaultImageDrawable(Drawable drawable) {
        if (drawable != this.defaultDrawable) {
            this.defaultDrawable = drawable;
            if (getDrawable() == null) {
                setImageDrawableInner(null);
            }
        }
    }

    public void setDefaultImageBitmap(Bitmap bitmap) {
        setDefaultImageDrawable(new BitmapDrawable(getContext().getResources(), bitmap));
    }

    public void setDefaultImageResource(int resId) {
        setDefaultImageDrawable(getContext().getResources().getDrawable(resId));
    }

    public Drawable getDefaultDrawable() {
        return this.defaultDrawable;
    }

    /* renamed from: a */
    protected void loadAnim() {
    }

    public void setReflectionHeight(int i) {
        this.reflectionHeight = i;
        reDraw();
    }

    public void setDivideHeight(int i) {
        this.divideHieght = i;
        sizeChanged();
    }

    /* renamed from: a */
    public void m3514a(int reflectionMaskStartColor, int reflectionMaskEndColor) {
        this.reflectionMaskStartColor = reflectionMaskStartColor;
        this.reflectionMaskEndColor = reflectionMaskEndColor;
        drawReflection();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        reDraw();
    }

    /* renamed from: com.sds.android.ttpod.framework.modules.skin.view.AnimTransView$a */
    /* loaded from: classes.dex */
    static class AnimHandler extends Handler {

        /* renamed from: a */
        private WeakReference<AnimTransView> animTransViewWeakReference;

        public AnimHandler(AnimTransView animTransView) {
            this.animTransViewWeakReference = new WeakReference<>(animTransView);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            AnimTransView animTransView = this.animTransViewWeakReference.get();
            if (animTransView != null) {
                Bitmap bitmap = (Bitmap) message.obj;
                animTransView.clearAnimation();
                if (bitmap == null || bitmap.isRecycled()) {
                    animTransView.setImageDrawable(animTransView.defaultDrawable);
                } else {
                    animTransView.setImageBitmap(bitmap);
                }
            }
        }
    }
}
