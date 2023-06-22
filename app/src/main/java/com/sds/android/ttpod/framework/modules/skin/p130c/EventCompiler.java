package com.sds.android.ttpod.framework.modules.skin.p130c;

import android.text.TextUtils;
import java.util.Iterator;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.c.f */
/* loaded from: classes.dex */
public class EventCompiler {
    /* renamed from: a */
    public static int[] m3742a(String str) {
        if (str != null) {
            if (!str.endsWith(";")) {
                str = str + ';';
            }
            int[] iArr = new int[m3738b(str)];
            TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(';');
            simpleStringSplitter.setString(str);
            Iterator<String> it = simpleStringSplitter.iterator();
            int i = 0;
            while (it.hasNext()) {
                TextUtils.SimpleStringSplitter simpleStringSplitter2 = new TextUtils.SimpleStringSplitter('|');
                simpleStringSplitter2.setString(it.next());
                Iterator<String> it2 = simpleStringSplitter2.iterator();
                while (it2.hasNext()) {
                    i = m3741a(it2.next(), iArr, i);
                }
                iArr[i] = 0;
                i++;
            }
            iArr[i] = -1;
            return iArr;
        }
        return null;
    }

    /* renamed from: b */
    private static int m3738b(String str) {
        int length = str.length();
        int i = 0;
        int i2 = 1;
        for (int i3 = 0; i3 < length; i3++) {
            switch (str.charAt(i3)) {
                case '(':
                    i2 += 2;
                    i = i3;
                    break;
                case ')':
                    if (i3 - i > 1) {
                        i2++;
                        i = 0;
                        break;
                    } else {
                        break;
                    }
                case ',':
                    i2++;
                    break;
                case ';':
                    i2++;
                    break;
            }
        }
        return i2;
    }

    /* renamed from: a */
    private static int m3743a(TextUtils.SimpleStringSplitter simpleStringSplitter, int[] iArr, int i) {
        int i2;
        int i3 = i + 1;
        iArr[i] = 65537;
        int i4 = i3 + 1;
        iArr[i3] = 0;
        if (simpleStringSplitter.hasNext()) {
            i2 = i4 + 1;
            iArr[i4] = ValueParser.m3696b(simpleStringSplitter.next(), 0);
            iArr[i3] = iArr[i3] + 1;
        } else {
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = ValueParser.m3696b(simpleStringSplitter.next(), 0);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        return i2 - i;
    }

    /* renamed from: a */
    private static int m3740a(int[] iArr, int i) {
        int i2 = i + 1;
        iArr[i] = 65538;
        iArr[i2] = 0;
        return (i2 + 1) - i;
    }

    /* renamed from: b */
    private static int m3737b(int[] iArr, int i) {
        int i2 = i + 1;
        iArr[i] = 65544;
        iArr[i2] = 0;
        return (i2 + 1) - i;
    }

    /* renamed from: c */
    private static int m3735c(int[] iArr, int i) {
        int i2 = i + 1;
        iArr[i] = 65545;
        iArr[i2] = 0;
        return (i2 + 1) - i;
    }

    /* renamed from: b */
    private static int m3739b(TextUtils.SimpleStringSplitter simpleStringSplitter, int[] iArr, int i) {
        int i2;
        int i3 = i + 1;
        iArr[i] = 65539;
        int i4 = i3 + 1;
        iArr[i3] = 0;
        if (simpleStringSplitter.hasNext()) {
            i2 = i4 + 1;
            iArr[i4] = ValueParser.m3696b(simpleStringSplitter.next(), 0);
            iArr[i3] = iArr[i3] + 1;
        } else {
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = ValueParser.m3696b(simpleStringSplitter.next(), 0);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = ValueParser.m3702a(simpleStringSplitter.next(), 0);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        return i2 - i;
    }

    /* renamed from: c */
    private static int m3736c(TextUtils.SimpleStringSplitter simpleStringSplitter, int[] iArr, int i) {
        int i2;
        int i3 = i + 1;
        iArr[i] = 65543;
        int i4 = i3 + 1;
        iArr[i3] = 0;
        if (simpleStringSplitter.hasNext()) {
            i2 = i4 + 1;
            iArr[i4] = ValueParser.m3696b(simpleStringSplitter.next(), 0);
            iArr[i3] = iArr[i3] + 1;
        } else {
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = ValueParser.m3696b(simpleStringSplitter.next(), 0);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = ValueParser.m3702a(simpleStringSplitter.next(), 0);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        return i2 - i;
    }

    /* renamed from: d */
    private static int m3734d(TextUtils.SimpleStringSplitter simpleStringSplitter, int[] iArr, int i) {
        int i2;
        int i3 = i + 1;
        iArr[i] = 36864;
        int i4 = i3 + 1;
        iArr[i3] = 0;
        if (simpleStringSplitter.hasNext()) {
            i2 = i4 + 1;
            iArr[i4] = ValueParser.m3702a(simpleStringSplitter.next(), 0);
            iArr[i3] = iArr[i3] + 1;
        } else {
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = ValueParser.m3702a(simpleStringSplitter.next(), 500);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = ValueParser.m3702a(simpleStringSplitter.next(), 500);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        return i2 - i;
    }

    /* renamed from: e */
    private static int m3733e(TextUtils.SimpleStringSplitter simpleStringSplitter, int[] iArr, int i) {
        int i2;
        int i3 = i + 1;
        iArr[i] = 65541;
        int i4 = i3 + 1;
        iArr[i3] = 0;
        if (simpleStringSplitter.hasNext()) {
            i2 = i4 + 1;
            iArr[i4] = ValueParser.m3702a(simpleStringSplitter.next(), 1);
            iArr[i3] = iArr[i3] + 1;
        } else {
            i2 = i4;
        }
        return i2 - i;
    }

    /* renamed from: f */
    private static int m3732f(TextUtils.SimpleStringSplitter simpleStringSplitter, int[] iArr, int i) {
        int i2;
        int i3 = i + 1;
        iArr[i] = 65542;
        int i4 = i3 + 1;
        iArr[i3] = 0;
        if (simpleStringSplitter.hasNext()) {
            i2 = i4 + 1;
            iArr[i4] = ValueParser.m3702a(simpleStringSplitter.next(), 1);
            iArr[i3] = iArr[i3] + 1;
        } else {
            i2 = i4;
        }
        return i2 - i;
    }

    /* renamed from: g */
    private static int m3731g(TextUtils.SimpleStringSplitter simpleStringSplitter, int[] iArr, int i) {
        int i2;
        int i3 = i + 1;
        iArr[i] = 36865;
        int i4 = i3 + 1;
        iArr[i3] = 0;
        if (simpleStringSplitter.hasNext()) {
            i2 = i4 + 1;
            iArr[i4] = (int) (ValueParser.m3703a(simpleStringSplitter.next(), 0.0f) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
        } else {
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = (int) (ValueParser.m3703a(simpleStringSplitter.next(), 1.0f) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = ValueParser.m3702a(simpleStringSplitter.next(), 500);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = ValueParser.m3702a(simpleStringSplitter.next(), 500);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        return i2 - i;
    }

    /* renamed from: h */
    private static int m3730h(TextUtils.SimpleStringSplitter simpleStringSplitter, int[] iArr, int i) {
        int i2;
        int i3 = i + 1;
        iArr[i] = 36866;
        int i4 = i3 + 1;
        iArr[i3] = 0;
        if (simpleStringSplitter.hasNext()) {
            i2 = i4 + 1;
            iArr[i4] = (int) (ValueParser.m3703a(simpleStringSplitter.next(), 0.0f) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
        } else {
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = (int) (ValueParser.m3703a(simpleStringSplitter.next(), 1.0f) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = (int) (ValueParser.m3703a(simpleStringSplitter.next(), 0.0f) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = (int) (ValueParser.m3703a(simpleStringSplitter.next(), 1.0f) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = (int) (ValueParser.m3703a(simpleStringSplitter.next(), 0.5f) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = (int) (ValueParser.m3703a(simpleStringSplitter.next(), 0.5f) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = ValueParser.m3702a(simpleStringSplitter.next(), 500);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = ValueParser.m3702a(simpleStringSplitter.next(), 500);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        return i2 - i;
    }

    /* renamed from: i */
    private static int m3729i(TextUtils.SimpleStringSplitter simpleStringSplitter, int[] iArr, int i) {
        int i2;
        int i3 = i + 1;
        iArr[i] = 36867;
        int i4 = i3 + 1;
        iArr[i3] = 0;
        if (simpleStringSplitter.hasNext()) {
            i2 = i4 + 1;
            iArr[i4] = (int) (ValueParser.m3703a(simpleStringSplitter.next(), 0.0f) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
        } else {
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = (int) (ValueParser.m3703a(simpleStringSplitter.next(), 180.0f) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = (int) (ValueParser.m3703a(simpleStringSplitter.next(), 0.5f) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = (int) (ValueParser.m3703a(simpleStringSplitter.next(), 0.5f) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = ValueParser.m3702a(simpleStringSplitter.next(), 500);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = ValueParser.m3702a(simpleStringSplitter.next(), 500);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        return i2 - i;
    }

    /* renamed from: j */
    private static int m3728j(TextUtils.SimpleStringSplitter simpleStringSplitter, int[] iArr, int i) {
        int i2;
        int i3 = i + 1;
        iArr[i] = 36868;
        int i4 = i3 + 1;
        iArr[i3] = 0;
        if (simpleStringSplitter.hasNext()) {
            i2 = i4 + 1;
            iArr[i4] = (int) (ValueParser.m3703a(simpleStringSplitter.next(), 0.0f) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
        } else {
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = (int) (ValueParser.m3703a(simpleStringSplitter.next(), 180.0f) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = (int) (ValueParser.m3703a(simpleStringSplitter.next(), 0.5f) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = (int) (ValueParser.m3703a(simpleStringSplitter.next(), 0.5f) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = (int) (ValueParser.m3703a(simpleStringSplitter.next(), 0.5f) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = ValueParser.m3702a(simpleStringSplitter.next(), 500);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        if (simpleStringSplitter.hasNext()) {
            iArr[i2] = ValueParser.m3702a(simpleStringSplitter.next(), 500);
            iArr[i3] = iArr[i3] + 1;
            i2++;
        }
        return i2 - i;
    }

    /* renamed from: a */
    private static int m3741a(String str, int[] iArr, int i) {
        int lastIndexOf;
        TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(',');
        int indexOf = str.indexOf(40);
        if (indexOf > 0 && (lastIndexOf = str.lastIndexOf(41)) > indexOf) {
            String substring = str.substring(0, indexOf);
            int i2 = indexOf + 1;
            simpleStringSplitter.setString(i2 < lastIndexOf ? str.substring(i2, lastIndexOf) : "");
            if ("show".equals(substring)) {
                return i + m3743a(simpleStringSplitter, iArr, i);
            }
            if ("hide".equals(substring)) {
                return i + m3740a(iArr, i);
            }
            if ("move".equals(substring)) {
                return i + m3739b(simpleStringSplitter, iArr, i);
            }
            if ("animation".equals(substring)) {
                return i + m3734d(simpleStringSplitter, iArr, i);
            }
            if ("sleep".equals(substring)) {
                return i + m3733e(simpleStringSplitter, iArr, i);
            }
            if ("wait".equals(substring)) {
                return i + m3732f(simpleStringSplitter, iArr, i);
            }
            if ("alphaAnimation".equals(substring)) {
                return i + m3731g(simpleStringSplitter, iArr, i);
            }
            if ("scaleAnimation".equals(substring)) {
                return i + m3730h(simpleStringSplitter, iArr, i);
            }
            if ("rotateAnimation".equals(substring)) {
                return i + m3729i(simpleStringSplitter, iArr, i);
            }
            if ("rotate3dAnimation".equals(substring)) {
                return i + m3728j(simpleStringSplitter, iArr, i);
            }
            if ("offset".equals(substring)) {
                return i + m3736c(simpleStringSplitter, iArr, i);
            }
            if ("enable".equals(substring)) {
                return i + m3737b(iArr, i);
            }
            if ("disable".equals(substring)) {
                return i + m3735c(iArr, i);
            }
            return i;
        }
        return i;
    }
}
