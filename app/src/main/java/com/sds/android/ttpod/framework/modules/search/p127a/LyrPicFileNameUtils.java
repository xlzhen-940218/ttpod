package com.sds.android.ttpod.framework.modules.search.p127a;

import java.io.File;

/* renamed from: com.sds.android.ttpod.framework.modules.search.a.b */
/* loaded from: classes.dex */
public class LyrPicFileNameUtils {
    /* renamed from: a */
    public static String m3889a(String str) {
        if (str != null) {
            File file = new File(str);
            return Integer.toHexString(file.getName().hashCode()) + Integer.toHexString((int) file.length());
        }
        return null;
    }
}
