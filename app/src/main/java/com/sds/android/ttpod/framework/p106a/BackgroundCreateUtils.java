package com.sds.android.ttpod.framework.p106a;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.SecurityUtils;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.theme.BackgroundItem;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.sds.android.ttpod.framework.a.a */
/* loaded from: classes.dex */
public class BackgroundCreateUtils {
    /* renamed from: a */
    public static Bitmap m5280a(BackgroundItem backgroundItem) {
        return m5279a(backgroundItem, 3);
    }

    /* renamed from: a */
    public static Bitmap m5279a(BackgroundItem backgroundItem, int i) {
        Bitmap bitmap = null;
        float m7225c = DisplayUtils.m7225c() / i;
        float m7224d = DisplayUtils.m7224d() / i;
        InputStream m5276c = m5276c(backgroundItem);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPurgeable = true;
        options.inInputShareable = true;
        BitmapFactory.decodeStream(m5276c, null, options);
        if (options.outWidth >= m7225c || options.outHeight >= m7224d) {
            options.inSampleSize = (int) Math.max(options.outWidth / m7225c, options.outHeight / m7224d);
            options.outWidth = (int) m7225c;
            options.outHeight = (int) m7224d;
        }
        options.inJustDecodeBounds = false;
        try {
            if (backgroundItem.m3337a() == BackgroundItem.EnumC2011a.ADD_BY_USER) {
                bitmap = BitmapFactory.decodeFile(TTPodConfig.m5293o() + File.separator + backgroundItem.m3331b(), options);
            } else {
                bitmap = BitmapFactory.decodeStream(m5276c, null, options);
            }
        } catch (Throwable th) {
            try {
                th.printStackTrace();
                if (m5276c != null) {
                    try {
                        m5276c.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                if (m5276c != null) {
                    try {
                        m5276c.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        return bitmap;
    }

    /* renamed from: c */
    private static InputStream m5276c(BackgroundItem backgroundItem) {
        BaseApplication m4635c = BaseApplication.getApplication();
        if (BackgroundItem.EnumC2011a.ORIGINAL == backgroundItem.m3337a()) {
            return m5281a(m4635c.getAssets(), backgroundItem.m3331b());
        }
        if (BackgroundItem.EnumC2011a.ADD_BY_USER != backgroundItem.m3337a()) {
            return null;
        }
        return m5278a(backgroundItem.m3331b());
    }

    /* renamed from: a */
    private static InputStream m5281a(AssetManager assetManager, String str) {
        try {
            return assetManager.open(TTPodConfig.m5292p() + File.separator + str, 1);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static InputStream m5278a(String str) {
        try {
            return new FileInputStream(TTPodConfig.m5293o() + File.separator + str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static void m5277b(BackgroundItem backgroundItem) {
        Bitmap bitmap;
        File file = new File(TTPodConfig.m5293o() + File.separator + backgroundItem.m3331b());
        if (file.exists()) {
            String str = TTPodConfig.m5298j() + File.separator + SecurityUtils.C0610b.m8361a(String.valueOf(file.lastModified()));
            if (FileUtils.m8419a(str)) {
                new File(str).delete();
            }
            file.delete();
        }
        Bitmap m3327f = backgroundItem.m3327f();
        if (m3327f != null && !m3327f.isRecycled()) {
            m3327f.recycle();
        }
        Drawable m3328e = backgroundItem.m3328e();
        if (m3328e != null && (m3328e instanceof BitmapDrawable) && (bitmap = ((BitmapDrawable) m3328e).getBitmap()) != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }
}
