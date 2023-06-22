package com.sds.android.sdk.lib.p061c.p062a.p063a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* renamed from: com.sds.android.sdk.lib.c.a.a.a */
/* loaded from: classes.dex */
public @interface Entity {
    /* renamed from: a */
    String m8646a() default "";

    /* renamed from: b */
    PrimaryKey m8645b() default @PrimaryKey;

    /* renamed from: c */
    Index[] m8644c() default {};
}
