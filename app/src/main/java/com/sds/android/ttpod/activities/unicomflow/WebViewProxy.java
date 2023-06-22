package com.sds.android.ttpod.activities.unicomflow;

import android.content.Context;
import android.os.Build;

import com.sds.android.sdk.lib.util.LogUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.http.HttpHost;

/* renamed from: com.sds.android.ttpod.activities.unicomflow.c */
/* loaded from: classes.dex */
public class WebViewProxy {

    /* renamed from: a */
    private static final String f3070a = WebViewProxy.class.getSimpleName();

    /* renamed from: b */
    private static boolean f3071b = false;

    /* renamed from: a */
    private static Object m7737a(Object obj, String str) throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = obj.getClass().getDeclaredField(str);
        declaredField.setAccessible(true);
        return declaredField.get(obj);
    }

    /* renamed from: a */
    public static Object m7739a(Context context) throws Exception {
        Object m7735a;
        Class<?> cls = Class.forName("android.webkit.Network");
        if (cls == null || (m7735a = m7735a(cls, "getInstance", new Object[]{context}, Context.class)) == null) {
            return null;
        }
        return m7737a(m7735a, "mRequestQueue");
    }

    /* renamed from: a */
    public static boolean m7740a() {
        return false;
    }

    /* renamed from: a */
    private static Object m7735a(Object obj, String str, Object[] objArr, Class... clsArr) throws Exception {
        Class<?> cls = obj instanceof Class ? (Class) obj : obj.getClass();
        if (clsArr != null) {
            return cls.getMethod(str, clsArr).invoke(obj, objArr);
        }
        return cls.getMethod(str, new Class[0]).invoke(obj, new Object[0]);
    }

    /* renamed from: b */
    public static void m7733b(Context context) {
        f3071b = false;
        try {
            Object m7739a = m7739a(context);
            if (m7739a != null) {
                m7736a(m7739a, "mProxyHost", (Object) null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private static void m7736a(Object obj, String str, Object obj2) throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = obj.getClass().getDeclaredField(str);
        declaredField.setAccessible(true);
        declaredField.set(obj, obj2);
    }

    /* renamed from: a */
    public static boolean m7738a(Context context, String str, int i) {
        boolean z = false;
        try {
            if (Build.VERSION.SDK_INT < 14) {
                Object m7739a = m7739a(context);
                if (m7739a != null) {
                    m7736a(m7739a, "mProxyHost", new HttpHost(str, i, "http"));
                    z = true;
                }
            } else {
                z = m7734a(str, i);
            }
        } catch (Exception e) {
            LogUtils.m8383b(f3070a, "error setting up webkit proxying", e);
        }
        f3071b = z;
        return z;
    }

    /* renamed from: a */
    private static boolean m7734a(String str, int i) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<?> cls = Class.forName("android.webkit.WebViewCore");
        Class<?> cls2 = Class.forName("android.net.ProxyProperties");
        if (cls == null || cls2 == null) {
            return false;
        }
        Method declaredMethod = cls.getDeclaredMethod("sendStaticMessage", Integer.TYPE, Object.class);
        Constructor<?> constructor = cls2.getConstructor(String.class, Integer.TYPE, String.class);
        declaredMethod.setAccessible(true);
        constructor.setAccessible(true);
        declaredMethod.invoke(null, Integer.valueOf((int) 193), constructor.newInstance(str, Integer.valueOf(i), null));
        return true;
    }
}
