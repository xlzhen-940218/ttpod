package com.sds.android.sdk.lib.p061c.p062a.p063a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/* renamed from: com.sds.android.sdk.lib.c.a.a.b */
/* loaded from: classes.dex */
public @interface Field {
    /* renamed from: a */
    int m8643a() default 0;

    /* renamed from: b */
    int m8642b();

    /* renamed from: c */
    boolean m8641c() default false;

    /* renamed from: d */
    boolean m8640d() default false;

    /* renamed from: e */
    boolean m8639e() default false;

    /* renamed from: f */
    String m8638f() default "";

    /* renamed from: g */
    String m8637g() default "";
}
