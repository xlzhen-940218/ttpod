package com.sds.android.ttpod.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Region;
import android.media.ExifInterface;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import com.sds.android.sdk.lib.util.BitmapUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.apshare.ApShareReceiveFragment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public class CropImageView extends View {

    /* renamed from: n */
    private static final int f7498n = Color.argb(100, 0, 0, 0);

    /* renamed from: a */
    private Uri f7499a;

    /* renamed from: b */
    private Bitmap f7500b;

    /* renamed from: c */
    private Paint f7501c;

    /* renamed from: d */
    private Paint f7502d;

    /* renamed from: e */
    private Paint f7503e;

    /* renamed from: f */
    private RectF f7504f;

    /* renamed from: g */
    private RectF f7505g;

    /* renamed from: h */
    private RectF f7506h;

    /* renamed from: i */
    private RectF f7507i;

    /* renamed from: j */
    private RectF f7508j;

    /* renamed from: k */
    private Matrix f7509k;

    /* renamed from: l */
    private int f7510l;

    /* renamed from: m */
    private int f7511m;

    /* renamed from: o */
    private float f7512o;

    /* renamed from: p */
    private float f7513p;

    /* renamed from: q */
    private float f7514q;

    /* renamed from: r */
    private int f7515r;

    /* renamed from: s */
    private float f7516s;

    /* renamed from: t */
    private PointF f7517t;

    /* renamed from: u */
    private PointF f7518u;

    /* renamed from: v */
    private PointF f7519v;

    /* renamed from: w */
    private float[] f7520w;

    public CropImageView(Context context) {
        super(context);
        this.f7501c = new Paint();
        this.f7502d = new Paint();
        this.f7503e = new Paint();
        this.f7504f = new RectF();
        this.f7505g = new RectF();
        this.f7506h = new RectF();
        this.f7507i = new RectF();
        this.f7508j = new RectF();
        this.f7509k = new Matrix();
        this.f7510l = 4;
        this.f7511m = 4;
        this.f7515r = 0;
        this.f7517t = new PointF();
        this.f7518u = new PointF();
        this.f7519v = new PointF();
        this.f7520w = new float[9];
        m1890a(context);
    }

    public CropImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CropImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7501c = new Paint();
        this.f7502d = new Paint();
        this.f7503e = new Paint();
        this.f7504f = new RectF();
        this.f7505g = new RectF();
        this.f7506h = new RectF();
        this.f7507i = new RectF();
        this.f7508j = new RectF();
        this.f7509k = new Matrix();
        this.f7510l = 4;
        this.f7511m = 4;
        this.f7515r = 0;
        this.f7517t = new PointF();
        this.f7518u = new PointF();
        this.f7519v = new PointF();
        this.f7520w = new float[9];
        m1890a(context);
    }

    /* renamed from: a */
    public void m1891a(int i, int i2) {
        if (i <= 0) {
            i = 4;
        }
        this.f7510l = i;
        if (i2 <= 0) {
            i2 = 4;
        }
        this.f7511m = i2;
    }

    /* renamed from: a */
    private void m1890a(Context context) {
        this.f7502d.setColor(-1);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float applyDimension = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2.0f, displayMetrics);
        this.f7512o = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8.0f, displayMetrics);
        this.f7513p = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30.0f, displayMetrics);
        this.f7514q = this.f7513p * 4.0f;
        this.f7502d.setStrokeWidth(applyDimension);
        this.f7502d.setStyle(Paint.Style.STROKE);
        this.f7501c.setStyle(Paint.Style.FILL);
        this.f7501c.setColor(f7498n);
        this.f7503e.setColor(-16711936);
        this.f7503e.setStyle(Paint.Style.FILL);
        this.f7503e.setAntiAlias(true);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        LogUtils.debug("CropImageView", "onSizeChanged onBitmapDecodeComplete w=" + i + " h=" + i2);
        this.f7506h.set(0.0f, 0.0f, i, i2);
        this.f7507i.set(this.f7506h);
        this.f7507i.inset(this.f7512o, this.f7512o);
        m1894a();
    }

    public void setImageURI(Uri uri) {
        this.f7499a = uri;
        m1894a();
    }

    /* renamed from: b */
    private Bitmap m1883b(String str) throws Exception {
        Bitmap m8435b = BitmapUtils.m8435b(str, (int) this.f7506h.width(), (int) this.f7506h.height());
        if (new ExifInterface(str).getAttributeInt("Orientation", 0) == 6) {
            this.f7509k.reset();
            this.f7509k.postRotate(90.0f);
            Bitmap createBitmap = Bitmap.createBitmap(m8435b, 0, 0, m8435b.getWidth(), m8435b.getHeight(), this.f7509k, true);
            m8435b.recycle();
            LogUtils.debug("CropImageView", "getSDcardPic Rotate_90 bitmap=%b bitmapRotated=%b", Boolean.valueOf(m8435b.isRecycled()), Boolean.valueOf(createBitmap.isRecycled()));
            return createBitmap;
        }
        return m8435b;
    }

    private Bitmap getContentPic() throws Exception {
        Cursor query = getContext().getContentResolver().query(this.f7499a, null, null, null, null);
        query.moveToFirst();
        @SuppressLint("Range") String string = query.getString(query.getColumnIndex("_data"));
        query.close();
        return m1883b(string);
    }

    /* renamed from: a */
    private void m1894a() {
        Bitmap bitmap;
        if (this.f7506h.width() > 0.0f && this.f7506h.height() > 0.0f && this.f7499a != null) {
            if (this.f7500b != null && !this.f7500b.isRecycled()) {
                this.f7500b.recycle();
                this.f7500b = null;
                LogUtils.error("CropImageView", "showNewImage recycle old image");
            }
            try {
                if (this.f7499a.getScheme().equals("content")) {
                    bitmap = getContentPic();
                } else {
                    bitmap = m1883b(this.f7499a.getPath());
                }
            } catch (Exception e) {
                LogUtils.error("CropImageView", "show NewImage Exception e=" + e.toString());
                bitmap = null;
            } catch (OutOfMemoryError e2) {
                LogUtils.error("CropImageView", "show NewImage OutOfMemoryError: " + e2.toString());
                bitmap = null;
            }
            m1889a(bitmap, 0, null);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f7500b != null) {
            canvas.drawBitmap(this.f7500b, this.f7509k, null);
            canvas.save();
            canvas.clipRect(this.f7508j, Region.Op.DIFFERENCE);
            canvas.drawRect(this.f7506h, this.f7501c);
            canvas.restore();
            canvas.drawRect(this.f7508j, this.f7502d);
        }
    }

    /* renamed from: a */
    private void m1889a(Bitmap bitmap, int i, Context context) {
        float f;
        this.f7500b = bitmap;
        if (bitmap == null) {
            PopupsUtils.m6760a((int) R.string.userinfo_can_not_open_image);
        } else {
            this.f7504f.set(0.0f, 0.0f, bitmap.getWidth(), bitmap.getHeight());
            this.f7505g.set(this.f7504f);
            float width = (this.f7507i.width() / 4.0f) * 3.0f;
            float height = (this.f7507i.height() / 4.0f) * 3.0f;
            if (this.f7511m >= this.f7510l) {
                f = (this.f7510l * height) / this.f7511m;
                if (f > this.f7507i.width()) {
                    height = (this.f7511m * width) / this.f7510l;
                    f = width;
                }
            } else {
                float f2 = (this.f7511m * width) / this.f7510l;
                if (f2 > this.f7507i.height()) {
                    f = (this.f7510l * height) / this.f7511m;
                } else {
                    height = f2;
                    f = width;
                }
            }
            float width2 = (this.f7506h.width() - f) / 2.0f;
            float height2 = (this.f7506h.height() - height) / 2.0f;
            this.f7508j.set(width2, height2, f + width2, height + height2);
            LogUtils.info("CropImageView", "onBitmapDecodeComplete view(" + this.f7506h.width() + ", " + this.f7506h.height() + ") bitamap(" + bitmap.getWidth() + ", " + bitmap.getHeight() + ") chooseFrame=" + this.f7508j.toString() + " outPutWidth=" + this.f7510l + " outputHeight=" + this.f7511m);
            this.f7509k.reset();
            if (this.f7504f.width() > this.f7506h.width() && this.f7504f.height() > this.f7506h.height()) {
                this.f7509k.setRectToRect(this.f7504f, this.f7506h, Matrix.ScaleToFit.CENTER);
                m1886b();
            }
            if (this.f7505g.width() < this.f7508j.width() || this.f7505g.height() < this.f7508j.height()) {
                float max = Math.max(this.f7508j.width() / this.f7505g.width(), this.f7508j.height() / this.f7505g.height());
                this.f7509k.postScale(max, max);
                m1886b();
            }
            this.f7509k.postTranslate(((this.f7506h.width() - this.f7505g.width()) / 2.0f) - this.f7505g.left, ((this.f7506h.height() - this.f7505g.height()) / 2.0f) - this.f7505g.top);
            m1886b();
        }
        invalidate();
    }

    /* renamed from: b */
    private void m1886b() {
        this.f7509k.getValues(this.f7520w);
        this.f7505g.left = this.f7520w[2];
        this.f7505g.top = this.f7520w[5];
        this.f7505g.right = this.f7505g.left + (this.f7504f.width() * this.f7520w[0]);
        this.f7505g.bottom = this.f7505g.top + (this.f7504f.height() * this.f7520w[4]);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f7500b == null) {
            return false;
        }
        float rawX = motionEvent.getRawX();
        float rawY = motionEvent.getRawY();
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.f7515r = 1;
                this.f7517t.set(rawX, rawY);
                this.f7518u.set(this.f7517t);
                break;
            case 1:
            case 6:
                this.f7515r = 0;
                break;
            case 2:
                if (this.f7515r == 1) {
                    m1880c(rawX - this.f7518u.x, rawY - this.f7518u.y);
                    this.f7518u.set(rawX, rawY);
                    invalidate();
                    m1886b();
                    break;
                } else if (this.f7515r == 2) {
                    m1892a(rawX - this.f7518u.x, rawY - this.f7518u.y);
                    this.f7518u.set(rawX, rawY);
                    invalidate();
                    break;
                } else if (this.f7515r == 3) {
                    float m1888a = m1888a(motionEvent);
                    if (m1888a > 10.0f && m1878e(m1888a)) {
                        m1886b();
                        m1882c();
                        invalidate();
                        break;
                    }
                } else if (this.f7515r >= 101 && this.f7515r <= 108) {
                    m1884b(rawX - this.f7518u.x, rawY - this.f7518u.y);
                    this.f7518u.set(rawX, rawY);
                    invalidate();
                    break;
                }
                break;
            case 5:
                this.f7516s = m1888a(motionEvent);
                this.f7515r = 3;
                this.f7519v.set(this.f7508j.centerX(), this.f7508j.centerY());
                break;
        }
        return true;
    }

    /* renamed from: c */
    private void m1882c() {
        if (this.f7505g.left > this.f7508j.left) {
            this.f7509k.postTranslate(this.f7508j.left - this.f7505g.left, 0.0f);
        } else if (this.f7505g.right < this.f7508j.right) {
            this.f7509k.postTranslate(this.f7508j.right - this.f7505g.right, 0.0f);
        }
        if (this.f7505g.top > this.f7508j.top) {
            this.f7509k.postTranslate(0.0f, this.f7508j.top - this.f7505g.top);
        } else if (this.f7505g.bottom < this.f7508j.bottom) {
            this.f7509k.postTranslate(0.0f, this.f7508j.bottom - this.f7505g.bottom);
        }
        m1886b();
    }

    /* renamed from: a */
    private void m1892a(float f, float f2) {
        if (this.f7508j.left + f < this.f7507i.left) {
            f = this.f7507i.left - this.f7508j.left;
        } else if (this.f7508j.right + f > this.f7507i.right) {
            f = this.f7507i.right - this.f7508j.right;
        }
        if (this.f7508j.top + f2 < this.f7507i.top) {
            f2 = this.f7507i.top - this.f7508j.top;
        } else if (this.f7508j.bottom + f2 > this.f7507i.bottom) {
            f2 = this.f7507i.bottom - this.f7508j.bottom;
        }
        this.f7508j.offset(f, f2);
    }

    /* renamed from: a */
    private float m1893a(float f) {
        float f2 = this.f7508j.left + f;
        if (f2 < this.f7507i.left) {
            return this.f7507i.left - this.f7508j.left;
        }
        if (this.f7508j.right - f2 < this.f7514q) {
            return (this.f7508j.right - this.f7514q) - this.f7508j.left;
        }
        return f;
    }

    /* renamed from: b */
    private float m1885b(float f) {
        float f2 = this.f7508j.top + f;
        if (f2 < this.f7507i.top) {
            return this.f7507i.top - this.f7508j.top;
        }
        if (this.f7508j.bottom - f2 < this.f7514q) {
            return (this.f7508j.bottom - this.f7514q) - this.f7508j.top;
        }
        return f;
    }

    /* renamed from: c */
    private float m1881c(float f) {
        float f2 = this.f7508j.right + f;
        if (f2 > this.f7507i.right) {
            return this.f7507i.right - this.f7508j.right;
        }
        if (f2 - this.f7508j.left < this.f7514q) {
            return (this.f7508j.left + this.f7514q) - this.f7508j.right;
        }
        return f;
    }

    /* renamed from: d */
    private float m1879d(float f) {
        float f2 = this.f7508j.bottom + f;
        if (f2 > this.f7507i.bottom) {
            return this.f7507i.bottom - this.f7508j.bottom;
        }
        if (f2 - this.f7508j.top < this.f7514q) {
            return (this.f7508j.top + this.f7514q) - this.f7508j.bottom;
        }
        return f;
    }

    /* renamed from: b */
    private void m1884b(float f, float f2) {
        switch (this.f7515r) {
            case ApShareReceiveFragment.WHAT_GET_SHARED_LIST_COMPLETE /* 101 */:
                this.f7508j.left += m1893a(f);
                break;
            case ApShareReceiveFragment.WHAT_DOWNLOAD_CANCELED /* 102 */:
                this.f7508j.left += m1893a(f);
                this.f7508j.top += m1885b(f2);
                break;
            case 103:
                this.f7508j.top += m1885b(f2);
                break;
            case 104:
                this.f7508j.right += m1881c(f);
                this.f7508j.top += m1885b(f2);
                break;
            case 105:
                this.f7508j.right += m1881c(f);
                break;
            case 106:
                this.f7508j.right += m1881c(f);
                this.f7508j.bottom += m1879d(f2);
                break;
            case 107:
                this.f7508j.bottom += m1879d(f2);
                break;
            case 108:
                this.f7508j.left += m1893a(f);
                this.f7508j.bottom += m1879d(f2);
                break;
        }
        this.f7508j.intersect(this.f7507i);
    }

    /* renamed from: c */
    private void m1880c(float f, float f2) {
        float f3 = (f <= 0.0f || this.f7505g.left + f < this.f7508j.left) ? f : this.f7508j.left - this.f7505g.left;
        float f4 = (f3 >= 0.0f || this.f7505g.right + f3 > this.f7508j.right) ? f3 : this.f7508j.right - this.f7505g.right;
        float f5 = (f2 <= 0.0f || this.f7505g.top + f2 < this.f7508j.top) ? f2 : this.f7508j.top - this.f7505g.top;
        if (f5 < 0.0f && this.f7505g.bottom + f5 <= this.f7508j.bottom) {
            f5 = this.f7508j.bottom - this.f7505g.bottom;
        }
        this.f7509k.postTranslate(f4, f5);
    }

    /* renamed from: e */
    private boolean m1878e(float f) {
        float f2;
        boolean z = true;
        boolean z2 = false;
        float f3 = f / this.f7516s;
        if (f > this.f7516s) {
            if (this.f7505g.width() < (((int) this.f7504f.width()) << 2) || this.f7505g.width() < this.f7508j.width() || this.f7505g.height() < this.f7508j.height()) {
                z2 = true;
            }
            z = z2;
            f2 = f3;
        } else if (this.f7505g.width() <= this.f7508j.width() || this.f7505g.height() <= this.f7508j.height()) {
            z = false;
            f2 = f3;
        } else {
            f2 = (this.f7505g.width() * f3 < this.f7508j.width() || this.f7505g.height() * f3 < this.f7508j.height()) ? Math.max(this.f7508j.width() / this.f7505g.width(), this.f7508j.height() / this.f7505g.height()) : f3;
        }
        if (z) {
            this.f7509k.postScale(f2, f2, this.f7519v.x, this.f7519v.y);
            this.f7516s = f;
        }
        return z;
    }

    /* renamed from: a */
    private float m1888a(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((x * x) + (y * y));
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x022f: MOVE  (r7 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:83:0x022f */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0230: MOVE  (r8 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:83:0x022f */
    /* renamed from: a */
    public boolean m1887a(String str) {
        Bitmap bitmap = null;
        FileOutputStream fileOutputStream = null;
        Bitmap bitmap2;
        FileOutputStream fileOutputStream2 = null;
        if (this.f7500b == null || this.f7500b.isRecycled()) {
            return false;
        }
        FileOutputStream fileOutputStream3 = null;
        Bitmap bitmap3 = null;
        Throwable e;
        Throwable th;
        long m8398n = FileUtils.m8398n(str);
        try {
            try {
                try {
                    float f = (this.f7508j.left - this.f7505g.left) / this.f7520w[0];
                    float f2 = (this.f7508j.top - this.f7505g.top) / this.f7520w[4];
                    float width = (this.f7500b.getWidth() * this.f7508j.width()) / this.f7505g.width();
                    float height = (this.f7500b.getHeight() * this.f7508j.height()) / this.f7505g.height();
                    LogUtils.debug("CropImageView", "saveImage (%.2f %.2f, %.2f %.2f) %.2f recycyle=%b path=%s", Float.valueOf(f), Float.valueOf(f2), Float.valueOf(width), Float.valueOf(height), Float.valueOf(this.f7520w[0]), Boolean.valueOf(this.f7500b.isRecycled()), str);
                    Matrix matrix = null;
                    if (width > this.f7510l) {
                        float f3 = this.f7510l / width;
                        matrix = new Matrix();
                        matrix.setScale(f3, f3);
                    }
                    bitmap2 = Bitmap.createBitmap(this.f7500b, Math.round(f), Math.round(f2), Math.round(width), Math.round(height), matrix, matrix != null);
                    try {
                        fileOutputStream2 = new FileOutputStream(str);
                    } catch (IOException e1) {
                        e = e1;
                        bitmap3 = bitmap2;
                    } catch (Exception e2) {
                        e = e2;
                        bitmap3 = bitmap2;
                    } catch (OutOfMemoryError e3) {
                        e = e3;
                        fileOutputStream2 = null;
                    } catch (Throwable th1) {
                        th = th1;
                        bitmap3 = bitmap2;
                        if (fileOutputStream3 != null) {
                            try {
                                fileOutputStream3.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                            if (m8398n > 0) {
                                new File(str).setLastModified(1000 + m8398n);
                            }
                        }
                        if (bitmap3 != null) {
                            bitmap3.recycle();
                        }
                        this.f7500b.recycle();
                        throw th;
                    }
                    try {
                        LogUtils.info("CropImageView", "compressSuccess %b %d %d", Boolean.valueOf(bitmap2.compress(Bitmap.CompressFormat.JPEG, 70, fileOutputStream2)), Integer.valueOf(bitmap2.getWidth()), Integer.valueOf(bitmap2.getHeight()));
                        fileOutputStream2.flush();
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                            if (m8398n > 0) {
                                new File(str).setLastModified(1000 + m8398n);
                            }
                        }
                        if (bitmap2 != null) {
                            bitmap2.recycle();
                        }
                        this.f7500b.recycle();
                        return true;
                    } catch (IOException e6) {
                        e = e6;
                        bitmap3 = bitmap2;
                        fileOutputStream3 = fileOutputStream2;
                        LogUtils.error("CropImageView", "saveImage IOException " + e.toString());
                        e.printStackTrace();
                        if (fileOutputStream3 != null) {
                            try {
                                fileOutputStream3.close();
                            } catch (IOException e7) {
                                e7.printStackTrace();
                            }
                            if (m8398n > 0) {
                                new File(str).setLastModified(1000 + m8398n);
                            }
                        }
                        if (bitmap3 != null) {
                            bitmap3.recycle();
                        }
                        this.f7500b.recycle();
                        return false;
                    } catch (Exception e8) {
                        e = e8;
                        bitmap3 = bitmap2;
                        fileOutputStream3 = fileOutputStream2;
                        LogUtils.error("CropImageView", "saveImage Exception " + e.toString());
                        e.printStackTrace();
                        if (fileOutputStream3 != null) {
                            try {
                                fileOutputStream3.close();
                            } catch (IOException e9) {
                                e9.printStackTrace();
                            }
                            if (m8398n > 0) {
                                new File(str).setLastModified(1000 + m8398n);
                            }
                        }
                        if (bitmap3 != null) {
                            bitmap3.recycle();
                        }
                        this.f7500b.recycle();
                        return false;
                    } catch (OutOfMemoryError e10) {
                        e = e10;
                        LogUtils.error("CropImageView", "saveImage OutOfMemoryError:" + e.toString());
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (IOException e11) {
                                e11.printStackTrace();
                            }
                            if (m8398n > 0) {
                                new File(str).setLastModified(1000 + m8398n);
                            }
                        }
                        if (bitmap2 != null) {
                            bitmap2.recycle();
                        }
                        this.f7500b.recycle();
                        return false;
                    }
                } catch (IOException e12) {
                    e = e12;
                } catch (Exception e13) {
                    e = e13;
                } catch (OutOfMemoryError e14) {
                    e = e14;
                    bitmap2 = null;
                    fileOutputStream2 = null;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            bitmap3 = bitmap;
            fileOutputStream3 = fileOutputStream;
        }
        return false;
    }
}
