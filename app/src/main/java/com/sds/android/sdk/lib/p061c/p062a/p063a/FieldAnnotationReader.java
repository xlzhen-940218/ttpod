package com.sds.android.sdk.lib.p061c.p062a.p063a;

import com.sds.android.sdk.lib.p061c.p062a.FieldDescriptor;
import java.lang.reflect.Method;

/* renamed from: com.sds.android.sdk.lib.c.a.a.c */
/* loaded from: classes.dex */
public abstract class FieldAnnotationReader {
    /* renamed from: a */
    public abstract boolean mo8635a(FieldDescriptor fieldDescriptor, Method method);

    /* renamed from: a */
    public static FieldAnnotationReader m8636a() {
        return new FieldAnnotationReader() { // from class: com.sds.android.sdk.lib.c.a.a.c.1
            @Override // com.sds.android.sdk.lib.p061c.p062a.p063a.FieldAnnotationReader
            /* renamed from: a */
            public boolean mo8635a(FieldDescriptor fieldDescriptor, Method method) {
                Field field = (Field) method.getAnnotation(Field.class);
                if (field != null) {
                    fieldDescriptor.m8630a(field.m8643a());
                    fieldDescriptor.m8624b(field.m8642b());
                    fieldDescriptor.m8626a(field.m8641c());
                    fieldDescriptor.m8619c(field.m8640d());
                    fieldDescriptor.m8621b(field.m8639e());
                    fieldDescriptor.m8628a(field.m8638f());
                    fieldDescriptor.m8623b(field.m8637g());
                }
                return field != null;
            }
        };
    }
}
