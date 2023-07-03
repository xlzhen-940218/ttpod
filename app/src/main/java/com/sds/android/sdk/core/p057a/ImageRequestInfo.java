package com.sds.android.sdk.core.p057a;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.sds.android.sdk.lib.util.SecurityUtils;
import java.io.File;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.sds.android.sdk.core.a.e */
/* loaded from: classes.dex */
public final class ImageRequestInfo {

    /* renamed from: a */
    private ImageCache.ImageLoadedCallback imageLoadedCallback;

    /* renamed from: b */
    private int width;

    /* renamed from: c */
    private int height;

    /* renamed from: d */
    private String imageUrl;

    /* renamed from: e */
    private String filename;

    /* renamed from: f */
    private String folderPath;

    /* renamed from: g */
    private Bitmap imageBitmap;

    /* renamed from: h */
    private ImageView.ScaleType scaleType;

    public ImageRequestInfo(String imageUrl, String folderPath, String filename, int width, int height, ImageView.ScaleType scaleType, ImageCache.ImageLoadedCallback imageLoadedCallback) {
        if (imageLoadedCallback == null) {
            throw new IllegalArgumentException("Callback must not be null");
        }
        this.imageLoadedCallback = imageLoadedCallback;
        this.imageUrl = imageUrl;
        this.folderPath = folderPath;
        this.filename = filename;
        this.width = width;
        this.height = height;
        this.scaleType = scaleType;
    }

    /* renamed from: a */
    public ImageCache.ImageLoadedCallback getImageLoadedCallback() {
        return this.imageLoadedCallback;
    }

    /* renamed from: b */
    public String getImageUrl() {
        return this.imageUrl;
    }

    /* renamed from: c */
    public String getFilename() {
        return this.filename;
    }

    /* renamed from: d */
    public String getLocalPath() {
        return this.folderPath + File.separator + (this.filename == null ? SecurityUtils.MD5Hex.stringToHex(this.imageUrl) : this.filename);
    }

    /* renamed from: e */
    public int getWidth() {
        return this.width;
    }

    /* renamed from: f */
    public int getHeight() {
        return this.height;
    }

    /* renamed from: g */
    public Bitmap getImageBitmap() {
        return this.imageBitmap;
    }

    /* renamed from: a */
    public void setImageBitmap(Bitmap bitmap) {
        this.imageBitmap = bitmap;
    }

    /* renamed from: h */
    public ImageView.ScaleType getScaleType() {
        return this.scaleType;
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ImageRequestInfo imageRequestInfo = (ImageRequestInfo) obj;
        if (this.imageUrl != null) {
            if (!this.imageUrl.equals(imageRequestInfo.imageUrl)) {
                return false;
            }
        } else if (imageRequestInfo.imageUrl != null) {
            return false;
        }
        if (this.filename != null) {
            if (!this.filename.equals(imageRequestInfo.filename)) {
                return false;
            }
        } else if (imageRequestInfo.filename != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((this.imageUrl != null ? this.imageUrl.hashCode() : 0)
                + (((((this.imageLoadedCallback.hashCode() * 31) + this.width) * 31) + this.height) * 31)) * 31)
                + (this.filename != null ? this.filename.hashCode() : 0);
    }
}
