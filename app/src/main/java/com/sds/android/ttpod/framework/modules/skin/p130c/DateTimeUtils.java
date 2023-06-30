package com.sds.android.ttpod.framework.modules.skin.p130c;

import android.text.TextUtils;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.c.d */
/* loaded from: classes.dex */
public final class DateTimeUtils {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v4, types: [int] */
    /* renamed from: a */
    public static long m3746a(String str) {
        int parseInt;
        int r2 = 0;
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        String[] split = str.split(":");
        if (split != null && split.length == 3) {
            try {
                int parseInt2 = Integer.parseInt(split[0]);
                int parseInt3 = Integer.parseInt(split[1]);
                String str2 = split[2];
                int indexOf = str2.indexOf(46);
                if (indexOf < 0) {
                    parseInt = Integer.parseInt(str2);
                } else {
                    parseInt = Integer.parseInt(str2.substring(0, indexOf));
                    i = m3744c(str2.substring(indexOf + 1));
                }
                r2 = ((parseInt2 * 60) + parseInt3) * 60;
                return i + ((parseInt + r2) * 1000);
            } catch (Exception e) {
                return r2;
            }
        }
        return m3745b(str);
    }

    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* renamed from: b */
    private static long m3745b(String str) {
        boolean z;
        int parseInt;
        boolean z2;
        String str2;
        String str3;
        boolean z3;
        long z4 = -9223372036854775808L;
        long j = Long.MIN_VALUE;
        int i = 0;
        try {
            int indexOf = str.indexOf(":");
            if (indexOf > 0) {
                String trim = str.substring(0, indexOf).trim();
                if (trim.length() <= 0 || trim.charAt(0) != '-') {
                    z = false;
                } else {
                    trim = trim.substring(1);
                    z = true;
                }
                long parseLong = Long.parseLong(trim);
                int i2 = indexOf + 1;
                int indexOf2 = str.indexOf(46, i2);
                int indexOf3 = indexOf2 < 0 ? str.indexOf(":", i2) : indexOf2;
                boolean z6;
                if (indexOf3 > 0) {
                    String trim2 = str.substring(i2, indexOf3).trim();
                    if (trim2.length() <= 0 || trim2.charAt(0) != '-') {
                        z2 = z;
                        str2 = trim2;
                    } else {
                        boolean z5 = !z;
                        String substring = trim2.substring(1);
                        z2 = z5;
                        str2 = substring;
                    }
                    int parseInt2 = Integer.parseInt(str2);
                    String trim3 = str.substring(indexOf3 + 1).trim();
                    int length = trim3.length();
                    z6 = z2;
                    if (length > 0) {
                        if (trim3.charAt(0) == '-') {
                            boolean z7 = !z2;
                            str3 = trim3.substring(1);
                            int i3 = length - 1;
                            z3 = z7;
                        } else {
                            str3 = trim3;
                            z3 = z2;
                        }
                        i = m3744c(str3);
                        z6 = z3;
                    }
                    //z4 = z6;
                    parseInt = parseInt2;
                } else {
                    String trim4 = str.substring(i2).trim();
                    boolean z8 = z;
                    if (trim4.length() > 0) {
                        z8 = z;
                        if (trim4.charAt(0) == '-') {
                            boolean z9 = !z;
                            trim4 = trim4.substring(1);
                            z8 = z9;
                        }
                    }
                    parseInt = Integer.parseInt(trim4);
                    z6 = z8;
                }
                long j2 = i + (((60 * parseLong) + parseInt) * 1000);
                if (z6) {
                    return -j2;
                }
                return j2;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            j = z4;
        }
        return j;
    }

    /* renamed from: c */
    private static int m3744c(String str) {
        int length = str.length();
        int parseInt = length > 0 ? Integer.parseInt(str) : 0;
        if (length == 1) {
            return parseInt * 100;
        }
        if (length == 2) {
            return parseInt * 10;
        }
        return parseInt;
    }

    /* renamed from: a */
    public static String m3748a(long j) {
        String str;
        String str2;
        int i = 0;
        int i2 = (int) (j / 1000);
        if (i2 > 60) {
            i = i2 / 60;
            i2 %= 60;
        }
        if (i / 10 == 0) {
            str = "0" + i;
        } else {
            str = "" + i;
        }
        if (i2 / 10 == 0) {
            str2 = "0" + i2;
        } else if (i2 == 60) {
            str = "" + i + 1;
            str2 = "00";
        } else {
            str2 = "" + i2;
        }
        return str + ":" + str2;
    }

    /* renamed from: a */
    public static String m3747a(long j, String str) {
        if (TextUtils.isEmpty(str)) {
            str = ":";
        }
        int i = (int) (j / 1000);
        int i2 = i % 60;
        int i3 = (i / 60) % 60;
        int i4 = (i / 60) / 60;
        return i4 > 0 ? String.format("%02d" + str + "%02d" + str + "%02d", Integer.valueOf(i4), Integer.valueOf(i3), Integer.valueOf(i2)) : String.format("%02d" + str + "%02d", Integer.valueOf(i3), Integer.valueOf(i2));
    }
}
