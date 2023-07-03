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
public final class ImageCache implements ImageLoadTask.LoadImageCallback {

    /* renamed from: c */
    private static ImageCache instance;

    /* renamed from: a */
    private LruCache<String, Bitmap> lruKeyBitmap;

    /* renamed from: b */
    private File cacheImageFile;

    /* renamed from: d */
    private ImageLoadTaskManger imageLoadTaskManger = new ImageLoadTaskManger();

    /* compiled from: ImageCache.java */
    /* renamed from: com.sds.android.sdk.core.a.b$a */
    /* loaded from: classes.dex */
    public interface ImageLoadedCallback {
        /* renamed from: a */
        void loaded(String url, int width, int height, Bitmap bitmap);
    }

    /* renamed from: a */
    public static synchronized ImageCache getInstance(float memCacheSizePercent, String str) {
        ImageCache imageCache;
        synchronized (ImageCache.class) {
            if (instance == null) {
                instance = new ImageCache(memCacheSizePercent, str);
                imageCache = instance;
            } else {
                throw new IllegalStateException("ImageCache already existed!");
            }
        }
        return imageCache;
    }

    /* renamed from: a */
    public Bitmap getLruCacheBitmap(String url, String filename, int width, int height) {
        return this.lruKeyBitmap.get(getFileNameSpliceWidthHeight(url, filename, width, height));
    }

    /* renamed from: a */
    public void putImageToLrcCache(String str, String str2, int i, int i2, Bitmap bitmap) {
        String fileNameSpliceWidthHeight = getFileNameSpliceWidthHeight(str, str2, i, i2);
        if (fileNameSpliceWidthHeight != null && bitmap != null) {
            this.lruKeyBitmap.put(fileNameSpliceWidthHeight, bitmap);
        }
    }

    /* renamed from: b */
    public void removeLruCache(String url, String filename, int width, int height) {
        if (!StringUtils.isEmpty(url)) {
            this.lruKeyBitmap.remove(getFileNameSpliceWidthHeight(url, filename, width, height));
            FileUtils.exists(this.cacheImageFile.getAbsolutePath() + File.separator + getFixFileName(url, filename));
        }
    }

    /* renamed from: a */
    public void removeLruCache(String url, int width, int height) {
        removeLruCache(url, null, width, height);
    }

    /* renamed from: a */
    public String getCacheImagePath() {
        return this.cacheImageFile.getAbsolutePath();
    }

    /* renamed from: a */
    private void addThreadPool(String imageUrl, String filename, int width, int height, ImageView.ScaleType scaleType, ImageLoadedCallback imageLoadedCallback) {
        if (imageLoadedCallback == null) {
            throw new IllegalArgumentException("Callback must not be null");
        }
        if (!this.imageLoadTaskManger.isCloseManager() && !StringUtils.isEmpty(imageUrl)) {
            this.imageLoadTaskManger.addThreadPool(new ImageLoadTask(new ImageRequestInfo(imageUrl
                    , this.cacheImageFile.getAbsolutePath(), filename, width, height, scaleType, imageLoadedCallback), this));
        }
    }

    /* renamed from: a */
    public void addThreadPool(String url, int width, int height, ImageLoadedCallback imageLoadedCallback) {
        addThreadPool(url, null, width, height, null, imageLoadedCallback);
    }

    /* renamed from: a */
    public void addThreadPool(String url, int width, int height, ImageView.ScaleType scaleType, ImageLoadedCallback imageLoadedCallback) {
        addThreadPool(url, null, width, height, scaleType, imageLoadedCallback);
    }

    /* renamed from: b */
    public void evictAll() {
        this.lruKeyBitmap.evictAll();
    }

    private ImageCache(float memCacheSizePercent, String cacheImagePath) {
        if (memCacheSizePercent < 0.05f || memCacheSizePercent > 0.8f) {
            throw new IllegalArgumentException("setMemCacheSizePercent - percent must be between0.05and0.8 (inclusive)");
        }
        this.cacheImageFile = FileUtils.createFolder(cacheImagePath);
        if (this.cacheImageFile == null) {
            this.cacheImageFile = new File("");
        }
        initLruCache(Math.round(((float) Runtime.getRuntime().maxMemory()) * memCacheSizePercent));
    }

    /* renamed from: a */
    private void initLruCache(int cacheSize) {
        if (!SDKVersionUtils.sdkThan12()) {
            cacheSize = 1024;
        }
        this.lruKeyBitmap = new LruCache<String, Bitmap>(cacheSize / 1024) { // from class: com.sds.android.sdk.core.a.b.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.support.v4.util.LruCache
            /* renamed from: a */
            public void entryRemoved(boolean z, String str, Bitmap bitmap, Bitmap bitmap2) {
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.support.v4.util.LruCache
            /* renamed from: a */
            public int sizeOf(String str, Bitmap bitmap) {
                int bitmapSize = BitmapUtils.getBitmapSize(bitmap) / 1024;
                if (bitmapSize == 0) {
                    return 1;
                }
                return bitmapSize;
            }
        };
    }

    @Override // com.sds.android.sdk.core.p057a.ImageLoadTask.InterfaceC0567a
    /* renamed from: a */
    public Bitmap loadBitmapFromCache(String filename, String localPath, int width, int height, ImageView.ScaleType scaleType) {
        return loadBitmapFromLocal(filename, localPath, width, height, scaleType);
    }

    @Override // com.sds.android.sdk.core.p057a.ImageLoadTask.InterfaceC0567a
    /* renamed from: a */
    public void loadBitmapFromNetwork(ImageRequestInfo imageRequestInfo) {
        if (imageRequestInfo.getImageBitmap() == null) {
            throw new IllegalArgumentException("bitmap must not be null!");
        }
        String imageUrl = imageRequestInfo.getImageUrl();
        putImageToLrcCache(imageUrl, imageRequestInfo.getFilename(), imageRequestInfo.getWidth(), imageRequestInfo.getHeight(), imageRequestInfo.getImageBitmap());
        imageRequestInfo.getImageLoadedCallback().loaded(imageUrl, imageRequestInfo.getWidth(), imageRequestInfo.getHeight(), imageRequestInfo.getImageBitmap());
    }

    /* renamed from: b */
    private Bitmap loadBitmapFromLocal(String filename, String localPath, int width, int height, ImageView.ScaleType scaleType) {
        if (FileUtils.isFile(localPath)) {
            try {
                FileUtils.setLastModified(localPath, System.currentTimeMillis());
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(localPath, options);
                options.inSampleSize = BitmapUtils.getAspectRatio(options, width, height);
                options.inJustDecodeBounds = false;
                options.inPurgeable = true;
                options.inInputShareable = true;
                if (StringUtils.equals("image/jpeg", options.outMimeType)) {
                    options.inPreferredConfig = Bitmap.Config.RGB_565;
                }
                Bitmap decodeFile = BitmapFactory.decodeFile(localPath, options);
                LogUtils.error("ImageCache", "scaleType:" + scaleType);
                if (scaleType == null || width <= 0 || height <= 0) {
                    return decodeFile;
                }
                if (decodeFile.getWidth() > width || decodeFile.getHeight() > height) {
                    if (scaleType == ImageView.ScaleType.FIT_XY) {
                        return BitmapUtils.m8451a(decodeFile, width, height, true);
                    }
                    if (scaleType == ImageView.ScaleType.FIT_CENTER) {
                        return BitmapUtils.m8438b(decodeFile, width, height, true);
                    }
                    if (scaleType == ImageView.ScaleType.CENTER_CROP) {
                        return BitmapUtils.m8433c(decodeFile, width, height, true);
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

    /**
     * 获取文件名和宽高
     * @param url 图片链接
     * @param filename 文件名
     * @param width 宽
     * @param height 高
     * @return 返回拼接的文件名
     */
    public static String getFileNameSpliceWidthHeight(String url, String filename, int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("width and height must be > 0!!");
        }
        return getFixFileName(url, filename) + width + height;
    }

    /**
     * 获取修复后的文件名
     *
     * @param imageUrl 图片链接
     * @param filename 文件名
     * @return 返回文件名
     */
    private static String getFixFileName(String imageUrl, String filename) {
        return StringUtils.isEmpty(filename) ? SecurityUtils.MD5Hex.stringToHex(imageUrl) : filename;
    }

    /* renamed from: a */
    public static String buildCropUrl(String str, int i, int i2, ImageView.ScaleType scaleType) {
        if (!StringUtils.isEmpty(str) && str.startsWith("http://3p.pic.ttdtweb.com") && i > 0 && i2 > 0 && i <= 4096 && i2 <= 4096) {
            String m8334a = UrlUtils.m8334a(str);
            String substring = str.substring(m8334a.length());
            int m8803b = cropSize(i);
            int m8803b2 = cropSize(i2);
            String str2 = "1x." + FileUtils.getSuffix(str).toLowerCase();
            String str3 = "";
            switch (scaleType) {
                case FIT_XY:
                case FIT_START:
                case FIT_CENTER:
                    str3 = "" + m8803b + "w_" + m8803b2 + "h_1c_1e_";
                    break;
                case FIT_END:
                    str3 = "" + m8803b + "w_" + m8803b2 + "h_1c_0e_";
                    break;
                case CENTER:
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

    /* renamed from: b */
    private static int cropSize(int size) {
        return ((size + 25) / 50) * 50;
    }
}
