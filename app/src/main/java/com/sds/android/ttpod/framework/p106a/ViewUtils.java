package com.sds.android.ttpod.framework.p106a;

import android.view.View;
import android.view.ViewParent;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import java.util.concurrent.TimeUnit;

/* renamed from: com.sds.android.ttpod.framework.a.t */
/* loaded from: classes.dex */
public final class ViewUtils {

    /* renamed from: a */
    private static long f5691a;

    /* renamed from: b */
    private static final long f5692b = TimeUnit.MILLISECONDS.toNanos(500);

    /* renamed from: a */
    public static void m4638a(View view, boolean z) {
        ViewParent parent = view != null ? view.getParent() : null;
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    /* renamed from: a */
    public static boolean m4641a() {
        long nanoTime = System.nanoTime();
        if (nanoTime - f5691a < f5692b) {
            return true;
        }
        f5691a = nanoTime;
        return false;
    }

    /* renamed from: a */
    public static void m4640a(long j, View view) {
        if (259200000 + j >= System.currentTimeMillis()) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    /* renamed from: a */
    public static void m4639a(View view) {
        if (view != null && view.getBackground() != null && !SDKVersionUtils.sdkThan14()) {
            view.getBackground().setCallback(null);
        }
    }
}
