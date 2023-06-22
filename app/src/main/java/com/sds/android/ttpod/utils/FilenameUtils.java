package com.sds.android.ttpod.utils;

import com.sds.android.ttpod.framework.TTPodConfig;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/* renamed from: com.sds.android.ttpod.a.i */
/* loaded from: classes.dex */
public class FilenameUtils {
    /* renamed from: a */
    public static String m8280a() {
        String m5309a = TTPodConfig.m5309a();
        return m5309a + File.separator + m8279b();
    }

    /* renamed from: b */
    private static String m8279b() {
        Date date = new Date();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        DecimalFormat decimalFormat = new DecimalFormat("00");
        String format = new DecimalFormat("0000").format(gregorianCalendar.get(1));
        String format2 = decimalFormat.format(gregorianCalendar.get(2) + 1);
        String format3 = decimalFormat.format(gregorianCalendar.get(5));
        String format4 = decimalFormat.format(gregorianCalendar.get(11));
        String format5 = decimalFormat.format(gregorianCalendar.get(12));
        String format6 = decimalFormat.format(gregorianCalendar.get(13));
        StringBuilder sb = new StringBuilder();
        sb.append(format).append("-").append(format2).append("-").append(format3).append("-").append(format4).append("-").append(format5).append("-").append(format6);
        sb.append(".txt");
        return sb.toString();
    }
}
