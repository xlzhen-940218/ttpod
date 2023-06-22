package com.sds.android.sdk.lib.util;

import android.content.SharedPreferences;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: com.sds.android.sdk.lib.util.k */
/* loaded from: classes.dex */
public class SharedPreferencesUtils {

    /* renamed from: a */
    private static final Method f2480a = m8349a();

    /* renamed from: a */
    private static Method m8349a() {
        try {
            return SharedPreferences.Editor.class.getMethod("apply", new Class[0]);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static void m8348a(SharedPreferences.Editor editor) {
        if (f2480a != null) {
            try {
                f2480a.invoke(editor, new Object[0]);
                return;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e2) {
                e2.printStackTrace();
            }
        }
        editor.commit();
    }
}
