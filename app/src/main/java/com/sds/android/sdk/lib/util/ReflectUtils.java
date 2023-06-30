package com.sds.android.sdk.lib.util;

import java.lang.reflect.Method;

/* renamed from: com.sds.android.sdk.lib.util.h */
/* loaded from: classes.dex */
public class ReflectUtils {
    /* renamed from: a */
    public static Method loadMethod(Class cls, String str, Class<?>... clsArr) throws NoSuchMethodException {
        Method[] declaredMethods;
        DebugUtils.m8426a(cls, "cls");
        DebugUtils.m8426a(str, "methodName");
        try {
            return cls.getMethod(str, clsArr);
        } catch (Exception e) {
            try {
                return cls.getDeclaredMethod(str, clsArr);
            } catch (Exception e2) {
                Class cls2 = cls;
                do {
                    try {
                        declaredMethods = cls2.getMethods();
                    } catch (Exception e3) {
                        declaredMethods = cls2.getDeclaredMethods();
                    }
                    try {
                        for (Method method : declaredMethods) {
                            if (method.getName().equals(str) && m8374a(clsArr, method.getParameterTypes())) {
                                return method;
                            }
                        }
                    } catch (Exception e4) {
                        e4.printStackTrace();
                        cls2 = cls2.getSuperclass();
                        if (cls2 == null) {
                            throw new NoSuchMethodException(str + " not found in class " + cls.getName());
                        }
                    }
                    cls2 = cls2.getSuperclass();
                } while (cls2 == null);
                throw new NoSuchMethodException(str + " not found in class " + cls.getName());
            }
        }
    }

    /* renamed from: a */
    private static boolean m8374a(Class[] clsArr, Class[] clsArr2) {
        if (clsArr == null && clsArr2 == null) {
            return true;
        }
        if (clsArr == null || clsArr2 == null || clsArr.length != clsArr2.length) {
            return false;
        }
        int length = clsArr.length;
        for (int i = 0; i < length; i++) {
            if (!clsArr[i].getName().equals(clsArr2[i].getName())) {
                return false;
            }
        }
        return true;
    }
}
