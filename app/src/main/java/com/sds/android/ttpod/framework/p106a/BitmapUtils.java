package com.sds.android.ttpod.framework.p106a;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Locale;

/* compiled from: BitmapUtils.java */
/* renamed from: com.sds.android.ttpod.framework.a.b */
/* loaded from: classes.dex */
public class BitmapUtils {

    /* renamed from: b */
    private BitmapFactory.Options options;

    /* renamed from: c */
    private boolean newSDK = SDKVersionUtils.sdkThan13();

    /* renamed from: d */
    private boolean isOldOptions = false;

    /* renamed from: a */
    private BitmapFactory.Options newOptions = getOptions();

    /* renamed from: a */
    public void setNewSDK(boolean newSDK) {
        this.newSDK = newSDK;
    }

    /* renamed from: b */
    private BitmapFactory.Options getOptions() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inDither = this.newSDK;
        return options;
    }

    /* renamed from: b */
    private void setNewOptions(boolean cancelDecode) {
        if (cancelDecode && this.options != null) {
            this.options.requestCancelDecode();
        }
        this.options = cloneNewOptions();
        this.isOldOptions = false;
    }

    /* renamed from: c */
    private BitmapFactory.Options cloneNewOptions() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = this.newOptions.inDither;
        options.inJustDecodeBounds = this.newOptions.inJustDecodeBounds;
        options.inPreferredConfig = this.newOptions.inPreferredConfig;
        options.inDensity = this.newOptions.inDensity;
        options.inInputShareable = this.newOptions.inInputShareable;
        options.inTempStorage = this.newOptions.inTempStorage;
        options.inTargetDensity = this.newOptions.inTargetDensity;
        options.inScreenDensity = this.newOptions.inScreenDensity;
        options.inSampleSize = this.newOptions.inSampleSize;
        options.inPurgeable = this.newOptions.inPurgeable;
        return options;
    }

    /* renamed from: a */
    public BitmapFactory.Options getNewOptions() {
        return this.newOptions;
    }

    /* renamed from: a */
    public Bitmap m4779a(String str, int i, int i2) {
        return decodeBitmap(str, i, i2, false);
    }

    /* renamed from: a */
    public Bitmap decodeBitmap(String filePath, int width, int height, boolean cancelDecode) {
        try {
            if (!TextUtils.isEmpty(filePath) && width > 0 && height > 0) {
                setNewOptions(cancelDecode);
                this.options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(filePath, this.options);
                if (scaleBitmap(this.options, width, height)) {
                    if (m4769d()) {
                        m4783a(this.options);
                    }
                    LogUtils.debug("BitmapUtils", "decodeBitmap, filePath: " + filePath);
                    return m4788a(BitmapFactory.decodeFile(filePath, this.options));
                }
            }
        } catch (OutOfMemoryError e) {
            LogUtils.debug("BitmapUtils", "decodeBitmap OutOfMemoryError filePath=" + filePath);
        }
        return null;
    }

    /* renamed from: a */
    private Bitmap m4788a(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        if (SDKVersionUtils.sdkThan12()) {
            bitmap.setHasAlpha(!isNotPng(this.options));
            return bitmap;
        }
        return bitmap;
    }

    /* renamed from: d */
    private boolean m4769d() {
        return this.options.inPreferredConfig == Bitmap.Config.ARGB_8888 && !this.newSDK;
    }

    /* renamed from: a */
    public static boolean scaleBitmap(BitmapFactory.Options options, int width, int height) {
        if (!options.inJustDecodeBounds || options.outHeight <= 0 || options.outWidth <= 0) {
            return false;
        }
        if (options.outWidth > (width << 1) || options.outHeight > (height << 1)) {
            options.inSampleSize = Math.max(options.outWidth / width, options.outHeight / height);
        }
        options.inJustDecodeBounds = false;
        return true;
    }

    /* renamed from: a */
    public static boolean m4783a(BitmapFactory.Options options) {
        if (TextUtils.isEmpty(options.outMimeType)) {
            return false;
        }
        if (isNotPng(options)) {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inDither = false;
        } else {
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        }
        return true;
    }

    /* renamed from: b */
    public static boolean isNotPng(BitmapFactory.Options options) {
        String str = options.outMimeType;
        return !TextUtils.isEmpty(str) && !str.toLowerCase(Locale.US).endsWith("png");
    }

    /* renamed from: a */
    public Bitmap m4790a(Resources resources, int i) {
        if (i != 0) {
            setNewOptions(false);
            if (m4769d()) {
                this.options.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(resources, i, this.options);
                m4783a(this.options);
                this.options.inJustDecodeBounds = false;
            }
            try {
                return m4788a(BitmapFactory.decodeResource(resources, i, this.options));
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /* renamed from: a */
    public Bitmap m4789a(Resources resources, int i, int i2, int i3) {
        if (i != 0 && i3 > 0 && i2 > 0) {
            try {
                setNewOptions(false);
                this.options.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(resources, i, this.options);
                if (scaleBitmap(this.options, i2, i3)) {
                    if (m4769d()) {
                        m4783a(this.options);
                    }
                    return m4788a(BitmapFactory.decodeResource(resources, i, this.options));
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return null;
    }

    /* renamed from: a */
    public Bitmap m4780a(InputStream inputStream) {
        if (inputStream != null) {
            if (!this.isOldOptions) {
                setNewOptions(false);
                if (m4769d()) {
                    this.options.inJustDecodeBounds = true;
                    BitmapFactory.decodeStream(inputStream, null, this.options);
                    m4783a(this.options);
                    this.options.inJustDecodeBounds = false;
                }
            }
            try {
                return m4788a(BitmapFactory.decodeStream(inputStream, null, this.options));
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /* renamed from: a */
    public static Bitmap m4787a(Bitmap bitmap, int i) {
        if (bitmap == null) {
            return null;
        }
        int min = Math.min(bitmap.getWidth(), bitmap.getHeight());
        float min2 = Math.min(min, i) / min;
        Matrix matrix = new Matrix();
        matrix.setScale(min2, min2);
        LogUtils.debug("BitmapUtils", String.format("cropBitmapToSquare bitmapW=%d H=%d squareLen=%d scale=%f", Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight()), Integer.valueOf(i), Float.valueOf(min2)));
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, min, min, matrix, true);
        if (SDKVersionUtils.sdkThan12()) {
            createBitmap.setHasAlpha(bitmap.hasAlpha());
            return createBitmap;
        }
        return createBitmap;
    }

    /* renamed from: a */
    public static Bitmap m4791a(Context context, Bitmap bitmap, int i) {
        return m4770c(bitmap, i);
    }

    /* renamed from: b */
    private static int[] m4774b(Bitmap bitmap, int i) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[width * height];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        int i2 = width - 1;
        int i3 = height - 1;
        int i4 = width * height;
        int i5 = i + i + 1;
        int[] iArr2 = new int[i4];
        int[] iArr3 = new int[i4];
        int[] iArr4 = new int[i4];
        int[] iArr5 = new int[Math.max(width, height)];
        int i6 = (i5 + 1) >> 1;
        int i7 = i6 * i6;
        int[] iArr6 = new int[i7 * 256];
        int i8 = 0;
        int i9 = 0;
        while (true) {
            int i10 = i9;
            int i11 = i8;
            if (i10 >= 256) {
                break;
            }
            for (int i12 = 0; i12 < i7; i12++) {
                iArr6[i11 + i12] = i10;
            }
            i8 = i11 + i7;
            i9 = i10 + 1;
        }
        int[][] iArr7 = (int[][]) Array.newInstance(Integer.TYPE, i5, 3);
        int i13 = i + 1;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        while (true) {
            int i17 = i14;
            if (i17 >= height) {
                break;
            }
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            int i21 = 0;
            int i22 = 0;
            int i23 = 0;
            int i24 = 0;
            int i25 = 0;
            int i26 = 0;
            for (int i27 = -i; i27 <= i; i27++) {
                int i28 = iArr[Math.min(i2, Math.max(i27, 0)) + i16];
                int[] iArr8 = iArr7[i27 + i];
                iArr8[0] = (16711680 & i28) >> 16;
                iArr8[1] = (65280 & i28) >> 8;
                iArr8[2] = i28 & 255;
                int abs = i13 - Math.abs(i27);
                i25 += iArr8[0] * abs;
                i24 += iArr8[1] * abs;
                i23 += abs * iArr8[2];
                if (i27 > 0) {
                    i19 += iArr8[0];
                    i26 += iArr8[1];
                    i18 += iArr8[2];
                } else {
                    i22 += iArr8[0];
                    i21 += iArr8[1];
                    i20 += iArr8[2];
                }
            }
            int i29 = i25;
            int i30 = i24;
            int i31 = i23;
            int i32 = i16;
            int i33 = i;
            for (int i34 = 0; i34 < width; i34++) {
                iArr2[i32] = iArr6[i29];
                iArr3[i32] = iArr6[i30];
                iArr4[i32] = iArr6[i31];
                int i35 = i29 - i22;
                int i36 = i30 - i21;
                int i37 = i31 - i20;
                int[] iArr9 = iArr7[((i33 - i) + i5) % i5];
                int i38 = i22 - iArr9[0];
                int i39 = i21 - iArr9[1];
                int i40 = i20 - iArr9[2];
                if (i17 == 0) {
                    iArr5[i34] = Math.min(i34 + i + 1, i2);
                }
                int i41 = iArr[iArr5[i34] + i15];
                iArr9[0] = (16711680 & i41) >> 16;
                iArr9[1] = (65280 & i41) >> 8;
                iArr9[2] = i41 & 255;
                int i42 = i19 + iArr9[0];
                int i43 = i26 + iArr9[1];
                int i44 = i18 + iArr9[2];
                i29 = i35 + i42;
                i30 = i36 + i43;
                i31 = i37 + i44;
                i33 = (i33 + 1) % i5;
                int[] iArr10 = iArr7[i33 % i5];
                i22 = i38 + iArr10[0];
                i21 = i39 + iArr10[1];
                i20 = i40 + iArr10[2];
                i19 = i42 - iArr10[0];
                i26 = i43 - iArr10[1];
                i18 = i44 - iArr10[2];
                i32++;
            }
            i14 = i17 + 1;
            i15 += width;
            i16 = i32;
        }
        for (int i45 = 0; i45 < width; i45++) {
            int i46 = 0;
            int i47 = (-i) * width;
            int i48 = 0;
            int i49 = 0;
            int i50 = 0;
            int i51 = 0;
            int i52 = -i;
            int i53 = 0;
            int i54 = 0;
            int i55 = 0;
            int i56 = 0;
            while (i52 <= i) {
                int max = Math.max(0, i47) + i45;
                int[] iArr11 = iArr7[i52 + i];
                iArr11[0] = iArr2[max];
                iArr11[1] = iArr3[max];
                iArr11[2] = iArr4[max];
                int abs2 = i13 - Math.abs(i52);
                int i57 = (iArr2[max] * abs2) + i55;
                int i58 = (iArr3[max] * abs2) + i54;
                int i59 = (iArr4[max] * abs2) + i53;
                if (i52 > 0) {
                    i48 += iArr11[0];
                    i56 += iArr11[1];
                    i46 += iArr11[2];
                } else {
                    i51 += iArr11[0];
                    i50 += iArr11[1];
                    i49 += iArr11[2];
                }
                if (i52 < i3) {
                    i47 += width;
                }
                i52++;
                i53 = i59;
                i54 = i58;
                i55 = i57;
            }
            int i60 = i54;
            int i61 = i55;
            int i62 = i53;
            int i63 = i45;
            int i64 = i46;
            int i65 = i56;
            int i66 = i48;
            int i67 = i49;
            int i68 = i50;
            int i69 = i51;
            int i70 = i;
            for (int i71 = 0; i71 < height; i71++) {
                iArr[i63] = ((-16777216) & iArr[i63]) | (iArr6[i61] << 16) | (iArr6[i60] << 8) | iArr6[i62];
                int i72 = i61 - i69;
                int i73 = i60 - i68;
                int i74 = i62 - i67;
                int[] iArr12 = iArr7[((i70 - i) + i5) % i5];
                int i75 = i69 - iArr12[0];
                int i76 = i68 - iArr12[1];
                int i77 = i67 - iArr12[2];
                if (i45 == 0) {
                    iArr5[i71] = Math.min(i71 + i13, i3) * width;
                }
                int i78 = iArr5[i71] + i45;
                iArr12[0] = iArr2[i78];
                iArr12[1] = iArr3[i78];
                iArr12[2] = iArr4[i78];
                int i79 = i66 + iArr12[0];
                int i80 = i65 + iArr12[1];
                int i81 = i64 + iArr12[2];
                i61 = i72 + i79;
                i60 = i73 + i80;
                i62 = i74 + i81;
                i70 = (i70 + 1) % i5;
                int[] iArr13 = iArr7[i70];
                i69 = i75 + iArr13[0];
                i68 = i76 + iArr13[1];
                i67 = i77 + iArr13[2];
                i66 = i79 - iArr13[0];
                i65 = i80 - iArr13[1];
                i64 = i81 - iArr13[2];
                i63 += width;
            }
        }
        return iArr;
    }

    /* renamed from: c */
    private static Bitmap m4770c(Bitmap bitmap, int i) {
        if (i < 1 || bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= 0 || height <= 0) {
            return null;
        }
        return Bitmap.createBitmap(m4774b(bitmap, i), 0, width, width, height, bitmap.getConfig());
    }

    /* renamed from: a */
    public static void m4777a(String str, Bitmap bitmap) {
        FileOutputStream fileOutputStream;
        Exception e;
        try {
            fileOutputStream = new FileOutputStream(new File(str));
        } catch (Exception e1) {
            e = e1;
            fileOutputStream = null;
        }
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    /* renamed from: a */
    public static Bitmap m4781a(Drawable drawable, int i, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, drawable.getOpacity() != PixelFormat.UNKNOWN ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, i, i2);
        drawable.draw(canvas);
        return createBitmap;
    }

    /* renamed from: a */
    public static Bitmap m4784a(Bitmap bitmap, boolean z) {
        Throwable th;
        Bitmap bitmap2;
        int i = 0;
        if (bitmap == null) {
            return null;
        }
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        if (width > height) {
            i = (width - height) >> 1;
            width = i + height;
        } else {
            height = width;
        }
        try {
            bitmap2 = Bitmap.createBitmap(bitmap, i, 0, width, height);
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
            bitmap2 = null;
        }
        return bitmap2;
    }

    /* renamed from: a */
    public static Bitmap m4786a(Bitmap bitmap, int i, int i2, boolean z) {
        Bitmap bitmap2 = null;
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (width > height) {
                i = (width * i2) / height;
            } else {
                i2 = (height * i) / width;
            }
            try {
                bitmap2 = Bitmap.createScaledBitmap(bitmap, i, i2, false);
                if (bitmap2 != bitmap && z) {
                    bitmap.recycle();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return bitmap2;
    }

    /* renamed from: a */
    public static Bitmap m4785a(Bitmap bitmap, int i, boolean z) {
        Bitmap bitmap2;
        Throwable th;
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        if (!SDKVersionUtils.sdkThan11()) {
            config = Bitmap.Config.ARGB_4444;
        }
        try {
            bitmap2 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), config);
        } catch (Throwable th2) {
            bitmap2 = null;
            th = th2;
        }
        try {
            Canvas canvas = new Canvas(bitmap2);
            Paint paint = new Paint();
            Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            RectF rectF = new RectF(rect);
            paint.setAntiAlias(true);
            canvas.drawRoundRect(rectF, i, i, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, (Rect) null, rect, paint);
            if (bitmap2 != bitmap && z) {
                bitmap.recycle();
            }
        } catch (Throwable th3) {
            th = th3;
            th.printStackTrace();
            return bitmap2;
        }
        return bitmap2;
    }
}
