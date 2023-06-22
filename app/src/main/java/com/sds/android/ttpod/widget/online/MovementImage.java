package com.sds.android.ttpod.widget.online;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import com.sds.android.ttpod.widget.RectangleImageView;

/* loaded from: classes.dex */
public class MovementImage extends RectangleImageView {

    /* renamed from: a */
    private int f8392a;

    /* renamed from: b */
    private Bitmap f8393b;

    /* renamed from: c */
    private int f8394c;

    /* renamed from: d */
    private int f8395d;

    /* renamed from: e */
    private int f8396e;

    /* renamed from: f */
    private int f8397f;

    /* renamed from: g */
    private int f8398g;

    /* renamed from: h */
    private int f8399h;

    /* renamed from: i */
    private int f8400i;

    /* renamed from: j */
    private EnumC2293a f8401j;

    /* renamed from: k */
    private Matrix f8402k;

    /* renamed from: l */
    private boolean f8403l;

    /* renamed from: m */
    private Paint f8404m;

    /* renamed from: n */
    private Handler f8405n;

    /* renamed from: com.sds.android.ttpod.widget.online.MovementImage$a */
    /* loaded from: classes.dex */
    private enum EnumC2293a {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    /* renamed from: b */
    static /* synthetic */ int m1208b(MovementImage movementImage, int i) {
        int i2 = movementImage.f8398g + i;
        movementImage.f8398g = i2;
        return i2;
    }

    /* renamed from: com.sds.android.ttpod.widget.online.MovementImage$2 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C22922 {

        /* renamed from: a */
        static final /* synthetic */ int[] f8407a = new int[EnumC2293a.values().length];

        static {
            try {
                f8407a[EnumC2293a.DOWN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f8407a[EnumC2293a.UP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f8407a[EnumC2293a.LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f8407a[EnumC2293a.RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public void setMoveSpeed(int i) {
        this.f8392a = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public int m1210b(int i, int i2) {
        if (i2 <= 0) {
            return 0;
        }
        if (this.f8392a + i >= i2) {
            return i2 - i;
        }
        return this.f8392a;
    }

    public MovementImage(Context context) {
        this(context, null);
    }

    public MovementImage(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MovementImage(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8392a = 3;
        this.f8398g = 0;
        this.f8401j = EnumC2293a.UP;
        this.f8405n = new Handler() { // from class: com.sds.android.ttpod.widget.online.MovementImage.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i2;
                int i3;
                switch (C22922.f8407a[MovementImage.this.f8401j.ordinal()]) {
                    case 1:
                        i3 = MovementImage.this.m1210b(MovementImage.this.f8398g, MovementImage.this.f8399h);
                        if (MovementImage.this.f8398g >= MovementImage.this.f8399h) {
                            MovementImage.this.f8401j = EnumC2293a.RIGHT;
                            MovementImage.this.f8398g = 0;
                        }
                        MovementImage.m1208b(MovementImage.this, i3);
                        i2 = 0;
                        break;
                    case 2:
                        int m1210b = MovementImage.this.m1210b(MovementImage.this.f8398g, MovementImage.this.f8399h);
                        if (MovementImage.this.f8398g >= MovementImage.this.f8399h) {
                            MovementImage.this.f8401j = EnumC2293a.LEFT;
                            MovementImage.this.f8398g = 0;
                        }
                        MovementImage.m1208b(MovementImage.this, m1210b);
                        i3 = -m1210b;
                        i2 = 0;
                        break;
                    case 3:
                        int m1210b2 = MovementImage.this.m1210b(MovementImage.this.f8398g, MovementImage.this.f8400i);
                        if (MovementImage.this.f8398g >= MovementImage.this.f8400i) {
                            MovementImage.this.f8401j = EnumC2293a.DOWN;
                            MovementImage.this.f8398g = 0;
                        }
                        MovementImage.m1208b(MovementImage.this, m1210b2);
                        i2 = -m1210b2;
                        i3 = 0;
                        break;
                    case 4:
                        int m1210b3 = MovementImage.this.m1210b(MovementImage.this.f8398g, MovementImage.this.f8400i);
                        if (MovementImage.this.f8398g >= MovementImage.this.f8400i) {
                            MovementImage.this.f8401j = EnumC2293a.UP;
                            MovementImage.this.f8398g = 0;
                        }
                        MovementImage.m1208b(MovementImage.this, m1210b3);
                        i2 = m1210b3;
                        i3 = 0;
                        break;
                    default:
                        i3 = 0;
                        i2 = 0;
                        break;
                }
                MovementImage.this.f8402k.postTranslate(i2, i3);
                MovementImage.this.invalidate();
                MovementImage.this.f8405n.sendEmptyMessageDelayed(0, 50L);
            }
        };
        this.f8402k = new Matrix();
        this.f8404m = new Paint();
    }

    @Override // android.widget.ImageView
    public void setAlpha(int i) {
        this.f8404m.setAlpha(i);
    }

    public void setMoveMentBitmap(Bitmap bitmap) {
        this.f8393b = bitmap;
        if (bitmap != null) {
            this.f8396e = bitmap.getWidth();
            this.f8397f = bitmap.getHeight();
        }
    }

    /* renamed from: a */
    public void m1216a(int i, int i2) {
        this.f8394c = i;
        this.f8395d = i2;
        this.f8399h = this.f8397f > i2 ? this.f8397f - i2 : 0;
        this.f8400i = this.f8396e > i ? this.f8396e - i : 0;
        if (i > this.f8396e && this.f8396e > 0) {
            float f = i / this.f8396e;
            this.f8402k.setScale(f, f);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f8393b != null) {
            canvas.drawBitmap(this.f8393b, this.f8402k, this.f8404m);
        }
    }

    /* renamed from: c */
    private boolean m1207c() {
        return this.f8394c < this.f8396e || this.f8395d < this.f8397f;
    }

    /* renamed from: a */
    public void m1217a() {
        if (m1207c() && !this.f8403l) {
            this.f8403l = true;
            this.f8405n.sendEmptyMessage(0);
        }
    }

    /* renamed from: b */
    public void m1211b() {
        if (this.f8403l) {
            this.f8403l = false;
            this.f8405n.removeMessages(0);
        }
    }
}
