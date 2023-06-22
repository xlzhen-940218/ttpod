package com.sds.android.ttpod.framework.p106a;

import android.os.Build;
import android.view.View;
import java.lang.reflect.Method;

/* renamed from: com.sds.android.ttpod.framework.a.s */
/* loaded from: classes.dex */
public class SmartBarUtils {
    /* renamed from: a */
    public static void m4642a(View view) {
        if (m4643a()) {
            try {
                Method method = View.class.getMethod("setSystemUiVisibility", Integer.TYPE);
                Object[] objArr = new Object[1];
                try {
                    objArr[0] = View.class.getField("SYSTEM_UI_FLAG_HIDE_NAVIGATION").get(null);
                } catch (Exception e) {
                }
                method.invoke(view, objArr);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public static boolean m4643a() {
        try {
            return ((Boolean) Class.forName("android.os.Build").getMethod("hasSmartBar", new Class[0]).invoke(null, new Object[0])).booleanValue();
        } catch (Exception e) {
            if (Build.DEVICE.equals("mx2")) {
                return true;
            }
            return (Build.DEVICE.equals("mx") || Build.DEVICE.equals("m9")) ? false : false;
        }
    }
}
