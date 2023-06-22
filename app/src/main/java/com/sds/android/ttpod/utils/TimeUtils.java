package com.sds.android.ttpod.utils;

import android.content.Context;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/* renamed from: com.sds.android.ttpod.a.x */
/* loaded from: classes.dex */
public class TimeUtils {
    /* renamed from: a */
    public static CharSequence m8155a(Context context, long j) {
        long seconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) - j;
        long j2 = seconds / 31536000;
        if (j2 != 0) {
            return j2 + "年前";
        }
        long j3 = seconds / 2592000;
        if (j3 != 0) {
            return j3 + "月前";
        }
        long j4 = seconds / 86400;
        if (j4 != 0) {
            return j4 + "天前";
        }
        long j5 = seconds / 3600;
        if (j5 != 0) {
            return j5 + "小时前";
        }
        long j6 = seconds / 60;
        if (j6 != 0) {
            return j6 + "分钟前";
        }
        return "刚刚";
    }

    /* renamed from: a */
    public static String m8157a(long j) {
        String format;
        try {
            Date date = new Date(TimeUnit.SECONDS.toMillis(j));
            Date date2 = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long seconds = TimeUnit.MILLISECONDS.toSeconds(simpleDateFormat.parse(simpleDateFormat.format(new Date())).getTime()) - j;
            if (seconds <= 86400 && seconds + 86400 >= 0) {
                String format2 = new SimpleDateFormat("E").format(date);
                if (seconds <= 0) {
                    format = "今天 " + format2;
                } else {
                    format = "昨天 " + format2;
                }
            } else {
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy");
                if (simpleDateFormat2.format(date2).equals(simpleDateFormat2.format(date))) {
                    format = new SimpleDateFormat("MM-dd E").format(date);
                } else {
                    format = new SimpleDateFormat("yyyy-MM-dd E").format(date);
                }
            }
            return format;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static String m8154b(long j) {
        return new SimpleDateFormat("kk:mm").format(Long.valueOf(TimeUnit.SECONDS.toMillis(j)));
    }

    /* renamed from: a */
    public static String m8156a(long j, String str) {
        return new SimpleDateFormat(str).format(Long.valueOf(j));
    }
}
