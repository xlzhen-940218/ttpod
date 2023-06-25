package com.sds.android.sdk.lib.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.widget.ImageView;
import java.io.FileOutputStream;
import java.util.Locale;

/* renamed from: com.sds.android.sdk.lib.util.a */
/* loaded from: classes.dex */
public class BitmapUtils {

    /* renamed from: b */
    private BitmapFactory.Options f2465b;

    /* renamed from: c */
    private boolean f2466c = SDKVersionUtils.m8369e();

    /* renamed from: d */
    private boolean f2467d = false;

    /* renamed from: a */
    private BitmapFactory.Options f2464a = m8456a();

    /* renamed from: a */
    private BitmapFactory.Options m8456a() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inDither = this.f2466c;
        return options;
    }

    /* renamed from: a */
    private void m8442a(boolean z) {
        if (z && this.f2465b != null) {
            this.f2465b.requestCancelDecode();
        }
        this.f2465b = m8441b();
        this.f2467d = false;
    }

    /* renamed from: b */
    private BitmapFactory.Options m8441b() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = this.f2464a.inDither;
        options.inJustDecodeBounds = this.f2464a.inJustDecodeBounds;
        options.inPreferredConfig = this.f2464a.inPreferredConfig;
        options.inDensity = this.f2464a.inDensity;
        options.inInputShareable = this.f2464a.inInputShareable;
        options.inTempStorage = this.f2464a.inTempStorage;
        options.inTargetDensity = this.f2464a.inTargetDensity;
        options.inScreenDensity = this.f2464a.inScreenDensity;
        options.inSampleSize = this.f2464a.inSampleSize;
        options.inPurgeable = this.f2464a.inPurgeable;
        return options;
    }

    /* renamed from: a */
    public Bitmap m8446a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        m8442a(false);
        if (m8434c()) {
            this.f2465b.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, this.f2465b);
            m8449a(this.f2465b);
            this.f2465b.inJustDecodeBounds = false;
        }
        try {
            return m8439b(BitmapFactory.decodeFile(str, this.f2465b));
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public Bitmap m8444a(String str, int i, int i2) {
        return m8443a(str, i, i2, false);
    }

    /* renamed from: a */
    public Bitmap m8443a(String str, int i, int i2, boolean z) {
        try {
            if (!TextUtils.isEmpty(str) && i > 0 && i2 > 0) {
                m8442a(z);
                this.f2465b.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(str, this.f2465b);
                if (m8448a(this.f2465b, i, i2)) {
                    if (m8434c()) {
                        m8449a(this.f2465b);
                    }
                    LogUtils.debug("BitmapUtils", "decodeBitmap, filePath: " + str);
                    return m8439b(BitmapFactory.decodeFile(str, this.f2465b));
                }
            }
        } catch (OutOfMemoryError e) {
            LogUtils.debug("BitmapUtils", "decodeBitmap OutOfMemoryError filePath=" + str);
        }
        return null;
    }

    /* renamed from: b */
    private Bitmap m8439b(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT > 11) {
            bitmap.setHasAlpha(!m8437b(this.f2465b));
            return bitmap;
        }
        return bitmap;
    }

    /* renamed from: c */
    private boolean m8434c() {
        return this.f2465b.inPreferredConfig == Bitmap.Config.ARGB_8888 && !this.f2466c;
    }

    /* renamed from: a */
    public static boolean m8448a(BitmapFactory.Options options, int i, int i2) {
        if (!options.inJustDecodeBounds || options.outHeight <= 0 || options.outWidth <= 0) {
            return false;
        }
        if (options.outWidth > (i << 1) || options.outHeight > (i2 << 1)) {
            options.inSampleSize = Math.max(options.outWidth / i, options.outHeight / i2);
        }
        options.inJustDecodeBounds = false;
        return true;
    }

    /* renamed from: a */
    public static boolean m8449a(BitmapFactory.Options options) {
        if (TextUtils.isEmpty(options.outMimeType)) {
            return false;
        }
        if (m8437b(options)) {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inDither = false;
        } else {
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        }
        return true;
    }

    /* renamed from: b */
    public static boolean m8437b(BitmapFactory.Options options) {
        String str = options.outMimeType;
        return (TextUtils.isEmpty(str) || str.toLowerCase(Locale.US).endsWith("png")) ? false : true;
    }

    /* renamed from: a */
    public Bitmap m8455a(Resources resources, int i) {
        if (i != 0) {
            m8442a(false);
            if (m8434c()) {
                this.f2465b.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(resources, i, this.f2465b);
                m8449a(this.f2465b);
                this.f2465b.inJustDecodeBounds = false;
            }
            try {
                return m8439b(BitmapFactory.decodeResource(resources, i, this.f2465b));
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /* renamed from: a */
    public Bitmap m8454a(Resources resources, int i, int i2, int i3) {
        if (i != 0 && i3 > 0 && i2 > 0) {
            m8442a(false);
            this.f2465b.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(resources, i, this.f2465b);
            if (m8448a(this.f2465b, i2, i3)) {
                if (m8434c()) {
                    m8449a(this.f2465b);
                }
                try {
                    return m8439b(BitmapFactory.decodeResource(resources, i, this.f2465b));
                } catch (OutOfMemoryError e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public static Bitmap m8452a(Bitmap bitmap, int i) {
        if (bitmap != null) {
            int min = Math.min(bitmap.getWidth(), bitmap.getHeight());
            float min2 = Math.min(min, i) / min;
            Matrix matrix = new Matrix();
            matrix.setScale(min2, min2);
            LogUtils.debug("BitmapUtils", String.format("cropBitmapToSquare bitmapW=%d H=%d squareLen=%d scale=%f", Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight()), Integer.valueOf(i), Float.valueOf(min2)));
            try {
                Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, min, min, matrix, true);
                if (SDKVersionUtils.m8370d()) {
                    createBitmap.setHasAlpha(bitmap.hasAlpha());
                    return createBitmap;
                }
                return createBitmap;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return null;
    }

    /* renamed from: a */
    public static Bitmap m8445a(String str, int i) {
        Bitmap m8435b = m8435b(str, i, i);
        if (m8435b != null) {
            Bitmap m8452a = m8452a(m8435b, i);
            if (m8452a != m8435b) {
                m8435b.recycle();
                return m8452a;
            }
            return m8452a;
        }
        return null;
    }

    /* renamed from: b */
    public static Bitmap m8440b(Resources resources, int i, int i2, int i3) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, i, options);
        options.inSampleSize = m8436b(options, i2, i3);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, i, options);
    }

    /* renamed from: b */
    public static Bitmap m8435b(String str, int i, int i2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPurgeable = true;
        options.inInputShareable = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = m8436b(options, i, i2);
        options.inJustDecodeBounds = false;
        if (m8437b(options)) {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
        }
        try {
            return BitmapFactory.decodeFile(str, options);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static int m8436b(BitmapFactory.Options options, int i, int i2) {
        int i3;
        int i4 = options.outHeight;
        int i5 = options.outWidth;
        if (i <= 0 || i2 <= 0 || (i4 <= i2 && i5 <= i)) {
            i3 = 1;
        } else if (i5 > i4) {
            i3 = Math.round(i4 / i2);
        } else {
            i3 = Math.round(i5 / i);
        }
        if (i3 == 0) {
            return 1;
        }
        return i3;
    }

    /* renamed from: a */
    public static int m8453a(Bitmap bitmap) {
        if (bitmap == null) {
            throw new IllegalArgumentException("bitmap must be not null!");
        }
        return SDKVersionUtils.m8370d() ? bitmap.getByteCount() : bitmap.getRowBytes() * bitmap.getHeight();
    }

    /* renamed from: a */
    public static void m8450a(Bitmap bitmap, String str) {
        FileOutputStream fileOutputStream;
        DebugUtils.m8426a(bitmap, "bitmap");
        DebugUtils.m8426a(str, "savePath");
        FileUtils.m8404h(str);
        Throwable th;
        Exception e;
        try {
            fileOutputStream = new FileOutputStream(str);
        } catch (Exception e1) {
            e = e1;
            fileOutputStream = null;
        } catch (Throwable th1) {
            th = th1;
            fileOutputStream = null;
            try {
                fileOutputStream.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            //throw th;
        }
        try {
            try {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.flush();
                try {
                    fileOutputStream.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream.close();
                //throw th;
            }
        } catch (Exception e4) {
            e = e4;
            e.printStackTrace();
            try {
                fileOutputStream.close();
            } catch (Exception e5) {
                e5.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public static void m8447a(ImageView imageView) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            int intrinsicHeight = drawable != null ? drawable.getIntrinsicHeight() : 0;
            int intrinsicWidth = drawable != null ? drawable.getIntrinsicWidth() : 0;
            int width = imageView.getWidth();
            int height = imageView.getHeight();
            if (intrinsicHeight > 0 && intrinsicWidth > 0 && width > 0 && height > 0) {
                LogUtils.debug("BitmapUtils", String.format("amendMatrixForCenterCrop tag=%s view=%d,%d drawable=%d,%d", imageView.getTag(), Integer.valueOf(width), Integer.valueOf(height), Integer.valueOf(intrinsicWidth), Integer.valueOf(intrinsicHeight)));
                float f = (width * 1.0f) / intrinsicWidth;
                float f2 = (height * 1.0f) / intrinsicHeight;
                if (f2 >= f) {
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    LogUtils.debug("BitmapUtils", String.format("use system center_crop %f %f", Float.valueOf(f), Float.valueOf(f2)));
                    return;
                }
                imageView.setScaleType(ImageView.ScaleType.MATRIX);
                float max = Math.max(f, f2);
                Matrix matrix = new Matrix();
                matrix.postScale(max, max);
                imageView.setImageMatrix(matrix);
                LogUtils.debug("BitmapUtils", String.format("use my matrix %f %f scale=%f", Float.valueOf(f), Float.valueOf(f2), Float.valueOf(max)));
            }
        }
    }

    /* renamed from: a */
    public static Bitmap m8451a(Bitmap bitmap, int i, int i2, boolean z) {
        Throwable th;
        Bitmap bitmap2;
        if (bitmap == null || i <= 0 || i2 <= 0) {
            return bitmap;
        }
        try {
            bitmap2 = Bitmap.createScaledBitmap(bitmap, i, i2, true);
            if (bitmap2 != bitmap && z) {
                try {
                    bitmap.recycle();
                    return bitmap2;
                } catch (Throwable th2) {
                    th = th2;
                    th.printStackTrace();
                    return bitmap2;
                }
            }
            return bitmap2;
        } catch (Throwable th3) {
            th = th3;
            bitmap2 = bitmap;
        }
        return bitmap2;
    }

    /* renamed from: b */
    public static Bitmap m8438b(Bitmap bitmap, int i, int i2, boolean z) {
        int i3;
        if (bitmap != null && i > 0 && i2 > 0) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (width > i) {
                height = (height * i) / width;
                width = i;
            }
            if (height > i2) {
                i3 = (width * i2) / height;
            } else {
                i2 = height;
                i3 = width;
            }
            return m8451a(bitmap, i3, i2, z);
        }
        return bitmap;
    }

    /* renamed from: c */
    public static Bitmap m8433c(Bitmap bitmap, int i, int i2, boolean z) {
        Throwable th;
        Bitmap bitmap2;
        int i3 = 0;
        int i4;
        int i5;
        int i6 = 0;
        boolean z2 = false;
        if (bitmap == null || i == 0 || i2 == 0) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = null;
        try {
            if (i2 * width > height * i) {
                i6 = (height * i) / i2;
                i5 = (width - i6) / 2;
                i4 = 0;
                i3 = height;
            } else {
                i3 = (i2 * width) / i;
                i4 = (height - i3) / 2;
                i5 = 0;
                i6 = width;
            }
            if (i6 > i) {
                matrix = new Matrix();
                float f = i / i6;
                matrix.postScale(f, f);
                z2 = true;
            }
            bitmap2 = Bitmap.createBitmap(bitmap, i5, i4, i6, i3, matrix, z2);
        } catch (Throwable th2) {
            th = th2;
            bitmap2 = bitmap;
        }
        try {
            LogUtils.debug("BitmapUtils", "BitmapUtils src=" + width + "," + height + " new=" + i6 + "," + i3 + " dst=" + i + "," + i2 + " dstBitmap=" + bitmap2.getWidth() + "," + bitmap2.getHeight() + " tryRecycleSource=" + z);
            if (bitmap2 != bitmap && z) {
                bitmap.recycle();
                return bitmap2;
            }
            return bitmap2;
        } catch (Throwable th3) {
            th = th3;
            th.printStackTrace();
            return bitmap2;
        }
    }
}
