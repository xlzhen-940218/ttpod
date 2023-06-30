package com.sds.android.sdk.lib.util;

import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import java.text.SimpleDateFormat;
import java.util.Date;

/* renamed from: com.sds.android.sdk.lib.util.b */
/* loaded from: classes.dex */
public class DateUtils {

    /* renamed from: a */
    static final /* synthetic */ boolean f2468a;

    /* renamed from: b */
    private static final String[] f2469b;

    static {
        f2468a = !DateUtils.class.desiredAssertionStatus();
        f2469b = new String[]{"yyyy-MM-dd", "yyyy-MM-dd-HH", "yyyy-MM-dd-HH-mm", "yyyy-MM-dd-HH-mm-ss"};
    }

    /* renamed from: a */
    public static String m8432a(int i) {
        return m8429a(new Date(), i, "");
    }

    /* renamed from: a */
    public static String m8431a(int i, String str) {
        return m8429a(new Date(), i, str);
    }

    /* renamed from: a */
    private static String m8429a(Date date, int i, String str) {
        return m8428b(i, str).format(date);
    }

    /* renamed from: b */
    private static SimpleDateFormat m8428b(int i, String str) {
        if (f2468a || (i >= 0 && i < f2469b.length)) {
            String str2 = f2469b[i];
            if (str != null) {
                str2 = str2.replace("-", str);
            }
            return new SimpleDateFormat(str2);
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public static String m8430a(long j) {
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
}
