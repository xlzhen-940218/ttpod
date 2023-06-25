package com.sds.android.ttpod.framework.modules.skin.p130c;

import android.graphics.Rect;
import androidx.core.view.ViewCompat;
import android.text.Layout;
import android.text.TextUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import java.util.ArrayList;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.c.n */
/* loaded from: classes.dex */
public final class ValueParser {
    /* renamed from: a */
    public static int m3702a(String str, int i) {
        if (str != null) {
            try {
                str = str.trim();
                if (str.startsWith("#")) {
                    i = (int) Long.parseLong(str.substring("#".length()), 16);
                } else if (str.startsWith("0x") || str.startsWith("0X")) {
                    i = (int) Long.parseLong(str.substring("0X".length()), 16);
                } else {
                    i = (int) Long.parseLong(str);
                }
            } catch (Exception e) {
                LogUtils.debug("ValueParser", "parse int error, parse %s", str);
            }
        }
        return i;
    }

    /* renamed from: a */
    public static float m3703a(String str, float f) {
        try {
            return Float.parseFloat(str);
        } catch (Exception e) {
            LogUtils.debug("ValueParser", "parse float error, parse %s", str);
            return f;
        }
    }

    /* renamed from: b */
    public static int m3696b(String str, int i) {
        int m3702a;
        int i2 = 1;
        if (!TextUtils.isEmpty(str)) {
            String lowerCase = str.trim().trim().toLowerCase();
            if (lowerCase.endsWith("dp")) {
                m3702a = m3699a(lowerCase, "dp", i);
            } else if (lowerCase.endsWith("dip")) {
                m3702a = m3699a(lowerCase, "dip", i);
            } else if (lowerCase.endsWith("px")) {
                m3702a = m3699a(lowerCase, "px", i);
                i2 = 0;
            } else if (lowerCase.endsWith("%")) {
                i2 = 2;
                m3702a = m3699a(lowerCase, "%", i);
            } else {
                m3702a = m3702a(lowerCase, i);
                i2 = 0;
            }
            return (m3702a & 65535) | (i2 << 16);
        }
        return i;
    }

    /* renamed from: a */
    private static int m3699a(String str, String str2, int i) {
        return m3702a(str.substring(0, str.length() - str2.length()), i);
    }

    /* renamed from: a */
    public static int m3704a(int i, int i2) {
        int m3705a = m3705a(i);
        switch (((-65536) & i) >> 16) {
            case 1:
                return DisplayUtils.m7229a(m3705a);
            case 2:
                return (int) (m3705a * (i2 / 100.0f));
            default:
                return m3705a;
        }
    }

    /* renamed from: a */
    public static int m3705a(int i) {
        return (short) (65535 & i);
    }

    /* renamed from: a */
    public static Rect m3701a(String str, Rect rect) {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        if (!TextUtils.isEmpty(str)) {
            String trim = str.trim();
            if (rect != null) {
                i3 = rect.left;
                i2 = rect.top;
                i = rect.right;
                i4 = rect.bottom;
            } else {
                i = 0;
                i2 = 0;
                i3 = 0;
            }
            TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(' ');
            simpleStringSplitter.setString(trim);
            if (simpleStringSplitter.hasNext()) {
                i3 = m3696b(simpleStringSplitter.next(), i3);
            }
            if (simpleStringSplitter.hasNext()) {
                i2 = m3696b(simpleStringSplitter.next(), i2);
            }
            if (simpleStringSplitter.hasNext()) {
                i = m3696b(simpleStringSplitter.next(), i);
            }
            if (simpleStringSplitter.hasNext()) {
                i4 = m3696b(simpleStringSplitter.next(), i4);
            }
            return new Rect(i3, i2, i, i4);
        }
        return rect;
    }

    /* renamed from: c */
    public static int m3695c(String str, int i) {
        if (str != null) {
            TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(' ');
            simpleStringSplitter.setString(str);
            int i2 = 0;
            while (simpleStringSplitter.hasNext()) {
                i2 = (i2 << 8) | m3702a(simpleStringSplitter.next(), (int) ViewCompat.MEASURED_STATE_MASK);
            }
            return (16777215 & i2) | (ViewCompat.MEASURED_STATE_MASK - (i2 & ViewCompat.MEASURED_STATE_MASK));
        }
        return i;
    }

    /* renamed from: a */
    public static int[] m3697a(String str, int[] iArr) {
        if (str != null) {
            TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(',');
            simpleStringSplitter.setString(str);
            ArrayList arrayList = new ArrayList();
            while (simpleStringSplitter.hasNext()) {
                arrayList.add(Integer.valueOf(m3695c(simpleStringSplitter.next(), 0)));
            }
            int size = arrayList.size();
            if (size > 0) {
                iArr = new int[size];
                for (int i = 0; i < size; i++) {
                    iArr[i] = ((Integer) arrayList.get(i)).intValue();
                }
            }
        }
        return iArr;
    }

    /* renamed from: a */
    public static Layout.Alignment m3700a(String str, Layout.Alignment alignment) {
        if ("Center".equals(str)) {
            return Layout.Alignment.ALIGN_CENTER;
        }
        if ("Normal".equals(str)) {
            return Layout.Alignment.ALIGN_NORMAL;
        }
        if ("Opposite".equals(str)) {
            return Layout.Alignment.ALIGN_OPPOSITE;
        }
        return alignment;
    }

    /* renamed from: a */
    public static boolean m3698a(String str, boolean z) {
        if ("false".equalsIgnoreCase(str)) {
            return false;
        }
        if ("true".equalsIgnoreCase(str)) {
            return true;
        }
        return m3702a(str, z ? 1 : 0) == 1;
    }
}
