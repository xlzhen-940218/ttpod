package com.sds.android.ttpod.ThirdParty.p066a;

import java.lang.reflect.Method;

/* renamed from: com.sds.android.ttpod.ThirdParty.a.a */
/* loaded from: classes.dex */
public class InstanceUtils {
    /* renamed from: a */
    public static <T> T m8328a(String str) {
        try {
            Class<?> cls = Class.forName(str);
            try {
                Method declaredMethod = cls.getDeclaredMethod("getInstance", new Class[0]);
                if (declaredMethod != null) {
                    return (T) declaredMethod.invoke(null, (Object) null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return (T) cls.newInstance();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
