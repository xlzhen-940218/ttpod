package com.sds.android.ttpod.utils;

import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: com.sds.android.ttpod.a.n */
/* loaded from: classes.dex */
public class LogcatUtils {

    /* renamed from: a */
    private static String f2503a;

    /* renamed from: a */
    public static void m8261a() {
        List<String> m8258d = m8258d();
        try {
            Runtime.getRuntime().exec((String[]) m8258d.toArray(new String[m8258d.size()]));
        } catch (IOException e) {
            LogUtils.debug("LOGCAT", "something wrong with logcat");
        }
    }

    /* renamed from: d */
    private static List<String> m8258d() {
        f2503a = FilenameUtils.m8280a();
        ArrayList arrayList = new ArrayList(Arrays.asList("logcat", "-dv", "time", "-f"));
        arrayList.add(f2503a);
        arrayList.add("&");
        return arrayList;
    }

    /* renamed from: b */
    public static String m8260b() {
        return f2503a;
    }

    /* renamed from: c */
    public static void m8259c() {
        String m5309a = TTPodConfig.m5309a();
        String[] list = new File(m5309a).list();
        int length = list != null ? list.length : 0;
        for (int i = 0; i < length; i++) {
            FileUtils.m8404h(m5309a + File.separator + list[i]);
        }
    }
}
