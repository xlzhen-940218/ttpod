package com.sds.android.ttpod.framework.base;

import com.sds.android.ttpod.framework.modules.CommandID;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* renamed from: com.sds.android.ttpod.framework.base.i */
/* loaded from: classes.dex */
public @interface ObserverCommandID {
    /* renamed from: a */
    CommandID[] m4563a();
}
