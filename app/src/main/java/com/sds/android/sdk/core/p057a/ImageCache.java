package com.sds.android.sdk.core.p057a;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.util.LruCache;
import android.widget.ImageView;

import com.sds.android.sdk.lib.util.BitmapUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.sdk.lib.util.SecurityUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.sdk.lib.util.UrlUtils;
import java.io.File;

/* renamed from: com.sds.android.sdk.core.a.b */
/* loaded from: classes.dex */
public final class ImageCache implements ImageLoadTask.InterfaceC0567a {

    /* renamed from: c */
    private static ImageCache f2271c;

    /* renamed from: a */
    private LruCache<String, Bitmap> f2272a;

    /* renamed from: b */
    private File f2273b;

    /* renamed from: d */
    private ImageLoadTaskManger f2274d = new ImageLoadTaskManger();

    /* compiled from: ImageCache.java */
    /* renamed from: com.sds.android.sdk.core.a.b$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0565a {
        /* renamed from: a */
        void mo4733a(String str, int i, int i2, Bitmap bitmap);
    }

    /* renamed from: a */
    public static synchronized ImageCache m8814a(float f, String str) {
        ImageCache imageCache;
        synchronized (ImageCache.class) {
            if (f2271c == null) {
                f2271c = new ImageCache(f, str);
                imageCache = f2271c;
            } else {
                throw new IllegalStateException("ImageCache already existed!");
            }
        }
        return imageCache;
    }

    /* renamed from: a */
    public Bitmap m8807a(String str, String str2, int i, int i2) {
        return this.f2272a.get(m8800c(str, str2, i, i2));
    }

    /* renamed from: a */
    public void m8806a(String str, String str2, int i, int i2, Bitmap bitmap) {
        String m8800c = m8800c(str, str2, i, i2);
        if (m8800c != null && bitmap != null) {
            this.f2272a.put(m8800c, bitmap);
        }
    }

    /* renamed from: b */
    public void m8802b(String str, String str2, int i, int i2) {
        if (!StringUtils.isEmpty(str)) {
            this.f2272a.remove(m8800c(str, str2, i, i2));
            FileUtils.m8404h(this.f2273b.getAbsolutePath() + File.separator + m8808a(str, str2));
        }
    }

    /* renamed from: a */
    public void m8812a(String str, int i, int i2) {
        m8802b(str, null, i, i2);
    }

    /* renamed from: a */
    public String m8815a() {
        return this.f2273b.getAbsolutePath();
    }

    /* renamed from: a */
    private void m8805a(String str, String str2, int i, int i2, ImageView.ScaleType scaleType, InterfaceC0565a interfaceC0565a) {
        if (interfaceC0565a == null) {
            throw new IllegalArgumentException("Callback must not be null");
        }
        if (!this.f2274d.m8790a() && !StringUtils.isEmpty(str)) {
            this.f2274d.m8789a(new ImageLoadTask(new ImageRequestInfo(str, this.f2273b.getAbsolutePath(), str2, i, i2, scaleType, interfaceC0565a), this));
        }
    }

    /* renamed from: a */
    public void m8809a(String str, int i, int i2, InterfaceC0565a interfaceC0565a) {
        m8805a(str, null, i, i2, null, interfaceC0565a);
    }

    /* renamed from: a */
    public void m8810a(String str, int i, int i2, ImageView.ScaleType scaleType, InterfaceC0565a interfaceC0565a) {
        m8805a(str, null, i, i2, scaleType, interfaceC0565a);
    }

    /* renamed from: b */
    public void m8804b() {
        this.f2272a.evictAll();
    }

    private ImageCache(float f, String str) {
        if (f < 0.05f || f > 0.8f) {
            throw new IllegalArgumentException("setMemCacheSizePercent - percent must be between0.05and0.8 (inclusive)");
        }
        this.f2273b = FileUtils.m8406f(str);
        if (this.f2273b == null) {
            this.f2273b = new File("");
        }
        m8813a(Math.round(((float) Runtime.getRuntime().maxMemory()) * f));
    }

    /* renamed from: a */
    private void m8813a(int i) {
        if (!SDKVersionUtils.m8370d()) {
            i = 1024;
        }
        this.f2272a = new LruCache<String, Bitmap>(i / 1024) { // from class: com.sds.android.sdk.core.a.b.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.support.v4.util.LruCache
            /* renamed from: a */
            public void entryRemoved(boolean z, String str, Bitmap bitmap, Bitmap bitmap2) {
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.support.v4.util.LruCache
            /* renamed from: a */
            public int sizeOf(String str, Bitmap bitmap) {
                int m8453a = BitmapUtils.m8453a(bitmap) / 1024;
                if (m8453a == 0) {
                    return 1;
                }
                return m8453a;
            }
        };
    }

    @Override // com.sds.android.sdk.core.p057a.ImageLoadTask.InterfaceC0567a
    /* renamed from: a */
    public Bitmap mo8791a(String str, String str2, int i, int i2, ImageView.ScaleType scaleType) {
        return m8801b(str, str2, i, i2, scaleType);
    }

    @Override // com.sds.android.sdk.core.p057a.ImageLoadTask.InterfaceC0567a
    /* renamed from: a */
    public void mo8792a(ImageRequestInfo imageRequestInfo) {
        if (imageRequestInfo.m8779g() == null) {
            throw new IllegalArgumentException("bitmap must not be null!");
        }
        String m8784b = imageRequestInfo.m8784b();
        m8806a(m8784b, imageRequestInfo.m8783c(), imageRequestInfo.m8781e(), imageRequestInfo.m8780f(), imageRequestInfo.m8779g());
        imageRequestInfo.m8786a().mo4733a(m8784b, imageRequestInfo.m8781e(), imageRequestInfo.m8780f(), imageRequestInfo.m8779g());
    }

    /* renamed from: b */
    private Bitmap m8801b(String str, String str2, int i, int i2, ImageView.ScaleType scaleType) {
        if (FileUtils.m8414b(str2)) {
            try {
                FileUtils.m8418a(str2, System.currentTimeMillis());
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(str2, options);
                options.inSampleSize = BitmapUtils.m8436b(options, i, i2);
                options.inJustDecodeBounds = false;
                options.inPurgeable = true;
                options.inInputShareable = true;
                if (StringUtils.m8344a("image/jpeg", options.outMimeType)) {
                    options.inPreferredConfig = Bitmap.Config.RGB_565;
                }
                Bitmap decodeFile = BitmapFactory.decodeFile(str2, options);
                LogUtils.error("ImageCache", "scaleType:" + scaleType);
                if (scaleType == null || i <= 0 || i2 <= 0) {
                    return decodeFile;
                }
                if (decodeFile.getWidth() > i || decodeFile.getHeight() > i2) {
                    if (scaleType == ImageView.ScaleType.FIT_XY) {
                        return BitmapUtils.m8451a(decodeFile, i, i2, true);
                    }
                    if (scaleType == ImageView.ScaleType.FIT_CENTER) {
                        return BitmapUtils.m8438b(decodeFile, i, i2, true);
                    }
                    if (scaleType == ImageView.ScaleType.CENTER_CROP) {
                        return BitmapUtils.m8433c(decodeFile, i, i2, true);
                    }
                    return decodeFile;
                }
                return decodeFile;
            } catch (Throwable th) {
                th.printStackTrace();
                System.gc();
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static String m8800c(String str, String str2, int i, int i2) {
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("width and height must be > 0!!");
        }
        return m8808a(str, str2) + i + i2;
    }

    /* renamed from: a */
    private static String m8808a(String str, String str2) {
        return StringUtils.isEmpty(str2) ? SecurityUtils.C0610b.m8359b(str) : str2;
    }

    /* renamed from: a */
    public static String m8811a(String str, int i, int i2, ImageView.ScaleType scaleType) {
        if (!StringUtils.isEmpty(str) && str.startsWith("http://3p.pic.ttdtweb.com") && i > 0 && i2 > 0 && i <= 4096 && i2 <= 4096) {
            String m8334a = UrlUtils.m8334a(str);
            String substring = str.substring(m8334a.length());
            int m8803b = m8803b(i);
            int m8803b2 = m8803b(i2);
            String str2 = "1x." + FileUtils.getSuffix(str).toLowerCase();
            String str3 = "";
            switch (C05642.f2276a[scaleType.ordinal()]) {
                case 1:
                case 2:
                case 3:
                    str3 = "" + m8803b + "w_" + m8803b2 + "h_1c_1e_";
                    break;
                case 4:
                    str3 = "" + m8803b + "w_" + m8803b2 + "h_1c_0e_";
                    break;
                case 5:
                    str3 = "" + m8803b + "w_" + m8803b2 + "h_1c_1i_";
                    break;
            }
            if (!StringUtils.isEmpty(str3)) {
                str = m8334a + "@" + str3 + str2;
            }
            str = str + substring;
        }
        LogUtils.debug("ImageCache", "buildCropUrl url = " + str);
        return str;
    }

    /* compiled from: ImageCache.java */
    /* renamed from: com.sds.android.sdk.core.a.b$2 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C05642 {

        /* renamed from: a */
        static final /* synthetic */ int[] f2276a = new int[ImageView.ScaleType.values().length];

        static {
            try {
                f2276a[ImageView.ScaleType.FIT_XY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f2276a[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f2276a[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f2276a[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f2276a[ImageView.ScaleType.CENTER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    /* renamed from: b */
    private static int m8803b(int i) {
        return ((i + 25) / 50) * 50;
    }
}
