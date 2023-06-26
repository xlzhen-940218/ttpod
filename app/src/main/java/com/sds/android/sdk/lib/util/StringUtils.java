package com.sds.android.sdk.lib.util;

import android.text.TextUtils;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/* renamed from: com.sds.android.sdk.lib.util.l */
/* loaded from: classes.dex */
public class StringUtils {
    /* renamed from: a */
    public static String arrayToString(String str, Collection<?> collection) {
        StringBuilder sb = new StringBuilder();
        if (collection != null) {
            arrayToString(sb, str, collection);
        }
        String sb2 = sb.toString();
        if (sb2.endsWith(str)) {
            return sb2.substring(0, sb2.length() - str.length());
        }
        return sb2;
    }

    /* renamed from: a */
    public static String spliceStringAndArray(String str, Object... objArr) {
        StringBuilder sb = new StringBuilder();
        if (objArr != null && objArr.length > 0) {
            for (Object obj : objArr) {
                arrayToString(sb, str, obj);
            }
        }
        String sb2 = sb.toString();
        if (sb2.endsWith(str)) {
            return sb2.substring(0, sb2.length() - str.length());
        }
        return sb2;
    }

    /* renamed from: a */
    private static void spliceStringAndArray(StringBuilder sb, String str, Object obj) {
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            arrayToString(sb, str, Array.get(obj, i));
        }
    }

    /* renamed from: a */
    private static void arrayToString(StringBuilder sb, String str, Collection<?> collection) {
        for (Object o : collection) {
            arrayToString(sb, str, o);
        }
    }

    /* renamed from: b */
    private static void arrayToString(StringBuilder sb, String str, Object obj) {
        if (obj != null) {
            if (obj.getClass().isArray()) {
                spliceStringAndArray(sb, str, obj);
            } else if (obj instanceof Collection) {
                arrayToString(sb, str, (Collection<?>) obj);
            } else {
                String obj2 = obj.toString();
                sb.append(obj2);
                if (!isEmpty(obj2) && !obj2.endsWith(str)) {
                    sb.append(str);
                }
            }
        }
    }

    /* renamed from: a */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /* renamed from: a */
    public static boolean equals(String str, String str2) {
        return Objects.equals(str, str2);
    }


    /* renamed from: b */
    public static List<Long> stringToLongArray(String str, String str2) {
        List<String> m8335c = stringToArray(str, str2);
        ArrayList arrayList = new ArrayList(m8335c.size());
        for (String str3 : m8335c) {
            arrayList.add(Long.valueOf(Long.parseLong(str3)));
        }
        return arrayList;
    }

    /* renamed from: c */
    public static List<String> stringToArray(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        if (!isEmpty(str)) {
            int length = str.length();
            int i = 0;
            do {
                int indexOf = str.indexOf(str2, i);
                if (indexOf == -1) {
                    indexOf = length;
                }
                arrayList.add(str.substring(i, indexOf));
                i = indexOf + 1;
            } while (i < length);
            return arrayList;
        }
        return arrayList;
    }

    /* renamed from: a */
    public static String streamToString(InputStream inputStream) {
        try {
            try {
                byte[] bArr = new byte[4096];
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int read = inputStream.read(bArr, 0, 4096);
                    if (read <= 0) {
                        String byteArrayOutputStream2 = byteArrayOutputStream.toString("UTF-8");
                        try {
                            return byteArrayOutputStream2;
                        } catch (Exception e) {
                            return byteArrayOutputStream2;
                        }
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                try {
                    inputStream.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                return null;
            } catch (OutOfMemoryError e4) {
                e4.printStackTrace();
                try {
                    inputStream.close();
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
                return null;
            }
        } finally {
            try {
                inputStream.close();
            } catch (Exception e6) {
                e6.printStackTrace();
            }
        }
    }

    /* renamed from: b */
    public static boolean emailVerify(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return Pattern.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$", str);
    }

    /* renamed from: c */
    public static boolean m8336c(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return Pattern.matches("^\\s*\\w+(?:\\.?[\\w-]+\\.?)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$", str);
    }

    /* renamed from: a */
    public static boolean lengthVerify(String str, int min, int max) {
        return str != null && str.length() >= min && str.length() <= max;
    }
}
