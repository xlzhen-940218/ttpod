package com.sds.android.sdk.lib.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/* renamed from: com.sds.android.sdk.lib.util.l */
/* loaded from: classes.dex */
public class StringUtils {
    /* renamed from: a */
    public static String m8343a(String str, Collection<?> collection) {
        StringBuilder sb = new StringBuilder();
        if (collection != null) {
            m8340a(sb, str, collection);
        }
        String sb2 = sb.toString();
        if (sb2.endsWith(str)) {
            return sb2.substring(0, sb2.length() - str.length());
        }
        return sb2;
    }

    /* renamed from: a */
    public static String m8342a(String str, Object... objArr) {
        StringBuilder sb = new StringBuilder();
        if (objArr != null &&  objArr.length > 0) {
            for (Object obj : objArr) {
                m8337b(sb, str, obj);
            }
        }
        String sb2 = sb.toString();
        if (sb2.endsWith(str)) {
            return sb2.substring(0, sb2.length() - str.length());
        }
        return sb2;
    }

    /* renamed from: a */
    private static void m8341a(StringBuilder sb, String str, Object obj) {
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            m8337b(sb, str, Array.get(obj, i));
        }
    }

    /* renamed from: a */
    private static void m8340a(StringBuilder sb, String str, Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            m8337b(sb, str, it.next());
        }
    }

    /* renamed from: b */
    private static void m8337b(StringBuilder sb, String str, Object obj) {
        if (obj != null) {
            if (obj.getClass().isArray()) {
                m8341a(sb, str, obj);
            } else if (obj instanceof Collection) {
                m8340a(sb, str, (Collection<?>) obj);
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
    public static boolean m8344a(String str, String str2) {
        return str == str2 || (str != null && str2 != null && str.length() == str2.length() && str.equals(str2));
    }

    /* renamed from: b */
    public static List<Long> m8338b(String str, String str2) {
        List<String> m8335c = m8335c(str, str2);
        ArrayList arrayList = new ArrayList(m8335c.size());
        for (String str3 : m8335c) {
            arrayList.add(Long.valueOf(Long.parseLong(str3)));
        }
        return arrayList;
    }

    /* renamed from: c */
    public static List<String> m8335c(String str, String str2) {
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
    public static String m8347a(InputStream inputStream) {
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
    public static boolean m8339b(String str) {
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
    public static boolean m8345a(String str, int i, int i2) {
        return str != null && str.length() >= i && str.length() <= i2;
    }
}
