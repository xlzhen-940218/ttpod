package com.sds.android.ttpod.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;
import com.sds.android.sdk.lib.util.BitmapUtils;

/* renamed from: com.sds.android.ttpod.a.r */
/* loaded from: classes.dex */
public class ScreenShotUtils {
    /* renamed from: a */
    public static void m8244a(Activity activity, String str) {
        Activity m8245a = m8245a(activity);
        Rect rect = new Rect();
        m8245a.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int i = rect.top;
        int i2 = i >= 0 ? i : 0;
        View decorView = m8245a.getWindow().getDecorView();
        try {
            decorView.setDrawingCacheEnabled(true);
            decorView.buildDrawingCache();
            Bitmap drawingCache = decorView.getDrawingCache();
            Bitmap createBitmap = Bitmap.createBitmap(drawingCache, 0, i2, drawingCache.getWidth(), drawingCache.getHeight() - i2);
            BitmapUtils.m8450a(createBitmap, str);
            createBitmap.recycle();
        } catch (Throwable th) {
            try {
                th.printStackTrace();
                if (decorView != null) {
                    decorView.destroyDrawingCache();
                }
            } finally {
                if (decorView != null) {
                    decorView.destroyDrawingCache();
                }
            }
        }
    }

    /* renamed from: a */
    private static Activity m8245a(Activity activity) {
        while (activity.getParent() != null) {
            activity = activity.getParent();
        }
        return activity;
    }
}
