package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.ttpod.widget.p141b.FoldShading;

/* renamed from: com.sds.android.ttpod.widget.c */
/* loaded from: classes.dex */
public class FoldableItemLayout extends FrameLayout {

    /* renamed from: a */
    private boolean f8240a;

    /* renamed from: b */
    private final C2264a f8241b;

    /* renamed from: c */
    private final C2265b f8242c;

    /* renamed from: d */
    private final C2265b f8243d;

    /* renamed from: e */
    private int f8244e;

    /* renamed from: f */
    private int f8245f;

    /* renamed from: g */
    private Bitmap f8246g;

    /* renamed from: h */
    private boolean f8247h;

    /* renamed from: i */
    private float f8248i;

    /* renamed from: j */
    private float f8249j;

    /* renamed from: k */
    private float f8250k;

    public FoldableItemLayout(Context context) {
        super(context);
        this.f8241b = new C2264a(this);
        this.f8242c = new C2265b(this, 48);
        this.f8243d = new C2265b(this, 80);
        setInTransformation(false);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.f8241b.m1355a(this, 3);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        if (this.f8248i != 0.0f) {
            m1361a();
        }
        super.dispatchDraw(canvas);
    }

    /* renamed from: a */
    private void m1361a() {
        this.f8244e = getWidth();
        this.f8245f = getHeight();
        if (this.f8246g == null || this.f8246g.getWidth() != this.f8244e || this.f8246g.getHeight() != this.f8245f) {
            if (this.f8246g != null) {
                this.f8246g.recycle();
                this.f8246g = null;
            }
            if (this.f8244e != 0 && this.f8245f != 0) {
                try {
                    this.f8246g = Bitmap.createBitmap(this.f8244e, this.f8245f, Bitmap.Config.ARGB_8888);
                } catch (OutOfMemoryError e) {
                    this.f8246g = null;
                }
            }
            m1360a(this.f8246g);
        }
    }

    /* renamed from: a */
    private void m1360a(Bitmap bitmap) {
        this.f8241b.m1359a(bitmap == null ? null : new Canvas(bitmap));
        this.f8242c.m1350a(bitmap);
        this.f8243d.m1350a(bitmap);
    }

    public void setFoldRotation(float f) {
        this.f8248i = f;
        this.f8242c.m1352a(f);
        this.f8243d.m1352a(f);
        setInTransformation(f != 0.0f);
        if (this.f8240a) {
            float f2 = 1.0f;
            if (this.f8244e > 0) {
                f2 = this.f8244e / ((((float) (this.f8245f * Math.abs(Math.sin(Math.toRadians(f))))) * 0.16666667f) + this.f8244e);
            }
            setScale(f2);
        }
    }

    public float getFoldRotation() {
        return this.f8248i;
    }

    public void setScale(float f) {
        this.f8249j = f;
        this.f8242c.m1341b(f);
        this.f8243d.m1341b(f);
    }

    public float getScale() {
        return this.f8249j;
    }

    public void setRollingDistance(float f) {
        this.f8250k = f;
        this.f8242c.m1351a(f, this.f8249j);
        this.f8243d.m1351a(f, this.f8249j);
    }

    public float getRollingDistance() {
        return this.f8250k;
    }

    private void setInTransformation(boolean z) {
        if (this.f8247h != z) {
            this.f8247h = z;
            this.f8241b.m1354a(z);
            this.f8242c.setVisibility(z ? View.VISIBLE : View.INVISIBLE);
            this.f8243d.setVisibility(z ? View.VISIBLE : View.INVISIBLE);
        }
    }

    public void setAutoScaleEnabled(boolean z) {
        this.f8240a = z;
    }

    public FrameLayout getBaseLayout() {
        return this.f8241b;
    }

    public void setLayoutVisibleBounds(Rect rect) {
        this.f8242c.m1349a(rect);
        this.f8243d.m1349a(rect);
    }

    public void setFoldShading(FoldShading foldShading) {
        this.f8242c.m1348a(foldShading);
        this.f8243d.m1348a(foldShading);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FoldableItemLayout.java */
    /* renamed from: com.sds.android.ttpod.widget.c$a */
    /* loaded from: classes.dex */
    public static final class C2264a extends FrameLayout {

        /* renamed from: a */
        private Canvas f8251a;

        /* renamed from: b */
        private boolean f8252b;

        private C2264a(FoldableItemLayout foldableItemLayout) {
            super(foldableItemLayout.getContext());
            foldableItemLayout.addView(this, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            setBackground(foldableItemLayout.getBackground());
            foldableItemLayout.setBackground(null);
            setWillNotDraw(false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m1355a(FoldableItemLayout foldableItemLayout, int i) {
            while (foldableItemLayout.getChildCount() > i) {
                View childAt = foldableItemLayout.getChildAt(i);
                foldableItemLayout.removeViewAt(i);
                addView(childAt, (FrameLayout.LayoutParams) childAt.getLayoutParams());
            }
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            if (this.f8252b) {
                if (this.f8251a != null) {
                    this.f8251a.drawColor(0, PorterDuff.Mode.CLEAR);
                    super.draw(this.f8251a);
                    return;
                }
                return;
            }
            super.draw(canvas);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m1359a(Canvas canvas) {
            this.f8251a = canvas;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m1354a(boolean z) {
            if (this.f8252b != z) {
                this.f8252b = z;
                invalidate();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FoldableItemLayout.java */
    /* renamed from: com.sds.android.ttpod.widget.c$b */
    /* loaded from: classes.dex */
    public static class C2265b extends View {

        /* renamed from: a */
        private final int f8253a;

        /* renamed from: b */
        private Bitmap f8254b;

        /* renamed from: c */
        private final Rect f8255c;

        /* renamed from: d */
        private final float f8256d;

        /* renamed from: e */
        private float f8257e;

        /* renamed from: f */
        private final int f8258f;

        /* renamed from: g */
        private final int f8259g;

        /* renamed from: h */
        private final int f8260h;

        /* renamed from: i */
        private final Paint f8261i;

        /* renamed from: j */
        private Rect f8262j;

        /* renamed from: k */
        private int f8263k;

        /* renamed from: l */
        private int f8264l;

        /* renamed from: m */
        private float f8265m;

        /* renamed from: n */
        private FoldShading f8266n;

        public C2265b(FoldableItemLayout foldableItemLayout, int i) {
            super(foldableItemLayout.getContext());
            this.f8255c = new Rect();
            this.f8256d = 0.5f;
            this.f8257e = 0.5f;
            this.f8258f = 360;
            this.f8259g = 180;
            this.f8260h = 90;
            this.f8253a = i;
            foldableItemLayout.addView(this, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            this.f8261i = new Paint();
            this.f8261i.setDither(true);
            this.f8261i.setFilterBitmap(true);
            setWillNotDraw(false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m1350a(Bitmap bitmap) {
            this.f8254b = bitmap;
            m1353a();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m1349a(Rect rect) {
            this.f8262j = rect;
            m1353a();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m1348a(FoldShading foldShading) {
            this.f8266n = foldShading;
        }

        /* renamed from: a */
        private void m1353a() {
            if (this.f8254b == null) {
                this.f8255c.set(0, 0, 0, 0);
            } else {
                int height = this.f8254b.getHeight();
                int width = this.f8254b.getWidth();
                int i = this.f8253a == 48 ? 0 : (int) ((height * (1.0f - this.f8257e)) - 0.5f);
                if (this.f8253a == 48) {
                    height = (int) ((height * this.f8257e) + 0.5f);
                }
                this.f8255c.set(0, i, width, height);
                if (this.f8262j != null && !this.f8255c.intersect(this.f8262j)) {
                    this.f8255c.set(0, 0, 0, 0);
                }
            }
            invalidate();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m1352a(float f) {
            float f2 = 0.0f;
            while (f < 0.0f) {
                f += 360.0f;
            }
            float f3 = f % 360.0f;
            if (f3 > 180.0f) {
                f3 -= 360.0f;
            }
            boolean z = true;
            if (this.f8253a == 48) {
                if (f3 <= -90.0f || f3 == 180.0f) {
                    z = false;
                } else if (f3 < 0.0f) {
                    f2 = f3;
                }
            } else if (f3 >= 90.0f) {
                z = false;
            } else if (f3 > 0.0f) {
                f2 = f3;
            }
            if (SDKVersionUtils.checkVersionThanAndroid11()) {
                setRotationX(f2);
            }
            this.f8263k = z ? 0 : 4;
            m1342b();
            this.f8265m = f3;
            invalidate();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public void m1341b(float f) {
            if (SDKVersionUtils.checkVersionThanAndroid11()) {
                setScaleX(f);
                setScaleY(f);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m1351a(float f, float f2) {
            if (SDKVersionUtils.checkVersionThanAndroid11()) {
                setTranslationY((int) ((f * f2) + 0.5f));
            }
            int height = getHeight() / 2;
            float f3 = height != 0 ? ((height - f) / height) / 2.0f : 0.5f;
            if (this.f8253a != 48) {
                f3 = 1.0f - f3;
            }
            this.f8257e = f3;
            m1353a();
        }

        @Override // android.view.View
        public void setVisibility(int i) {
            this.f8264l = i;
            m1342b();
        }

        /* renamed from: b */
        private void m1342b() {
            super.setVisibility(this.f8264l == 0 ? this.f8263k : this.f8264l);
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            super.draw(canvas);
            if (this.f8266n != null) {
                this.f8266n.mo1363a(canvas, this.f8255c, this.f8265m, this.f8253a);
            }
            if (this.f8254b != null) {
                canvas.drawBitmap(this.f8254b, this.f8255c, this.f8255c, this.f8261i);
            }
            if (this.f8266n != null) {
                this.f8266n.mo1362b(canvas, this.f8255c, this.f8265m, this.f8253a);
            }
        }
    }
}
