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
    public static int parseInt(String str, int i) {
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
    public static float parseFloat(String str, float f) {
        try {
            return Float.parseFloat(str);
        } catch (Exception e) {
            LogUtils.debug("ValueParser", "parse float error, parse %s", str);
            return f;
        }
    }

    /* renamed from: b */
    public static int parseCommon(String str, int number) {
        int result;
        int type = 1;
        if (!TextUtils.isEmpty(str)) {
            String lowerCase = str.trim().trim().toLowerCase();
            if (lowerCase.endsWith("dp")) {
                result = parseInt(lowerCase, "dp", number);
            } else if (lowerCase.endsWith("dip")) {
                result = parseInt(lowerCase, "dip", number);
            } else if (lowerCase.endsWith("px")) {
                result = parseInt(lowerCase, "px", number);
                type = 0;
            } else if (lowerCase.endsWith("%")) {
                type = 2;
                result = parseInt(lowerCase, "%", number);
            } else {
                result = parseInt(lowerCase, number);
                type = 0;
            }
            return (result & 65535) | (type << 16);
        }
        return number;
    }

    /* renamed from: a */
    private static int parseInt(String str, String str2, int i) {
        return parseInt(str.substring(0, str.length() - str2.length()), i);
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
    public static Rect xmlToRect(String str, Rect rect) {
        int right;
        int top;
        int left;
        int bottom = 0;
        if (!TextUtils.isEmpty(str)) {
            String trim = str.trim();
            if (rect != null) {
                left = rect.left;
                top = rect.top;
                right = rect.right;
                bottom = rect.bottom;
            } else {
                right = 0;
                top = 0;
                left = 0;
            }
            TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(' ');
            simpleStringSplitter.setString(trim);
            if (simpleStringSplitter.hasNext()) {
                left = parseCommon(simpleStringSplitter.next(), left);
            }
            if (simpleStringSplitter.hasNext()) {
                top = parseCommon(simpleStringSplitter.next(), top);
            }
            if (simpleStringSplitter.hasNext()) {
                right = parseCommon(simpleStringSplitter.next(), right);
            }
            if (simpleStringSplitter.hasNext()) {
                bottom = parseCommon(simpleStringSplitter.next(), bottom);
            }
            return new Rect(left, top, right, bottom);
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
                i2 = (i2 << 8) | parseInt(simpleStringSplitter.next(), (int) ViewCompat.MEASURED_STATE_MASK);
            }
            return (16777215 & i2) | (ViewCompat.MEASURED_STATE_MASK - (i2 & ViewCompat.MEASURED_STATE_MASK));
        }
        return i;
    }

    /* renamed from: a */
    public static int[] stringToIntArray(String str, int[] iArr) {
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
    public static Layout.Alignment stringToAlign(String str, Layout.Alignment alignment) {
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
    public static boolean stringToBoolean(String str, boolean z) {
        if ("false".equalsIgnoreCase(str)) {
            return false;
        }
        if ("true".equalsIgnoreCase(str)) {
            return true;
        }
        return parseInt(str, z ? 1 : 0) == 1;
    }
}
