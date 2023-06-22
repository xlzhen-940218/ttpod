package com.sds.android.sdk.lib.util;

import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.sds.android.sdk.lib.util.j */
/* loaded from: classes.dex */
public class SecurityUtils {

    /* compiled from: SecurityUtils.java */
    /* renamed from: com.sds.android.sdk.lib.util.j$b */
    /* loaded from: classes.dex */
    public static class C0610b {
        /* renamed from: a */
        public static String m8361a(String str) {
            return m8360a(m8357c(str));
        }

        /* renamed from: b */
        public static String m8359b(String str) {
            return m8358b(m8357c(str));
        }

        /* renamed from: c */
        private static byte[] m8357c(String str) {
            if (str == null) {
                return null;
            }
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(str.getBytes());
                return messageDigest.digest();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* renamed from: a */
        private static String m8360a(byte[] bArr) {
            if (bArr == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 4; i <= 11; i++) {
                sb.append(Integer.toHexString(bArr[i] & 255));
            }
            return sb.toString();
        }

        /* renamed from: b */
        private static String m8358b(byte[] bArr) {
            if (bArr == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder("");
            int length = bArr.length;
            for (int i = 0; i < length; i++) {
                int i2 = bArr[i];
                if (i2 < 0) {
                    i2 += 256;
                }
                if (i2 < 16) {
                    sb.append(FeedbackItem.STATUS_WAITING);
                }
                sb.append(Integer.toHexString(i2));
            }
            return sb.toString();
        }
    }

    /* compiled from: SecurityUtils.java */
    /* renamed from: com.sds.android.sdk.lib.util.j$d */
    /* loaded from: classes.dex */
    public static final class C0612d {

        /* renamed from: a */
        private static final int[] f2479a = {-1857714090, -47692346, -1232996205, 796674487};

        /* renamed from: a */
        public static String m8352a(String str) {
            if (StringUtils.m8346a(str)) {
                return "";
            }
            int[] iArr = new int[2];
            try {
                int[] m8351a = m8351a(m8350b(str), f2479a, 16);
                String lowerCase = String.format("%08x%08x", Integer.valueOf(m8351a[0]), Integer.valueOf(m8351a[1])).toLowerCase();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < lowerCase.length(); i++) {
                    int intValue = Integer.valueOf(lowerCase.substring(i, i + 1), 16).intValue();
                    if (intValue >= 10) {
                        sb.append(intValue - 10);
                    } else {
                        sb.append(intValue);
                    }
                }
                return sb.toString();
            } catch (IllegalArgumentException e) {
                return "";
            }
        }

        /* renamed from: b */
        private static int[] m8350b(String str) {
            boolean z;
            long longValue;
            long longValue2;
            StringBuilder sb = new StringBuilder(str.trim());
            if (sb.toString().equals(FeedbackItem.STATUS_WAITING)) {
                throw new IllegalArgumentException();
            }
            for (int i = 0; i < sb.length(); i++) {
                char charAt = sb.charAt(i);
                if (charAt < '0' || charAt > ';') {
                    z = true;
                    break;
                }
            }
            z = false;
            if (sb.length() < 16) {
                while (sb.length() < 16) {
                    sb.append(FeedbackItem.STATUS_WAITING);
                }
            }
            if (z) {
                Long valueOf = Long.valueOf(Long.parseLong(sb.substring(0, 8), 16));
                Long valueOf2 = Long.valueOf(Long.parseLong(sb.substring(8, 16), 16));
                longValue = valueOf.longValue();
                longValue2 = valueOf2.longValue();
            } else {
                longValue = Long.valueOf(sb.substring(0, 8), 16).longValue();
                longValue2 = Long.valueOf(sb.substring(8, 16), 16).longValue();
            }
            return new int[]{(int) longValue, (int) longValue2};
        }

        /* renamed from: a */
        public static int[] m8351a(int[] iArr, int[] iArr2, int i) {
            int i2 = iArr[0];
            int i3 = iArr[1];
            int i4 = iArr2[0];
            int i5 = iArr2[1];
            int i6 = iArr2[2];
            int i7 = iArr2[3];
            int i8 = i3;
            int i9 = i2;
            int i10 = 0;
            for (int i11 = 0; i11 < i; i11++) {
                i10 -= 1640531527;
                i9 += (((i8 << 4) + i4) ^ (i8 + i10)) ^ ((i8 >>> 5) + i5);
                i8 += (((i9 << 4) + i6) ^ (i9 + i10)) ^ ((i9 >>> 5) + i7);
            }
            return new int[]{i9, i8};
        }
    }

    /* compiled from: SecurityUtils.java */
    /* renamed from: com.sds.android.sdk.lib.util.j$c */
    /* loaded from: classes.dex */
    public static class C0611c {

        /* renamed from: a */
        private static final byte[] f2478a = {99, 7, 66, 74, -81, -36, 45, -18, 106, 25, 126, 6, 71, -67, 47, 108, 116, 117, 113, 121, 78, 109, -119, 62, 29, 23, 24, 76, 125, -62, -89, 0, -72, 82, 102, 50, -51, 100, 81, 65, -105, -26, -65, 88, -74, 89, -69, 18, 87, 44, -23, -20, -63, 36, -88, 79, -32, 26, -115, -10, -127, -49, 124, -122, -6, 122, 91, 12, 70, -29, -31, 14, -2, -12, -123, 84, 27, 97, -87, -84, 98, 41, 120, 59, 39, -104, 52, 101, 33, 35, -17, 105, -86, -57, 75, -76, Byte.MIN_VALUE, 64, 3, -33, 119, 95, 57, -73, 60, -68, 1, 69, 46, 86, 42, -98, -99, -9, 118, -95, -16, -41, 54, -124, 17, -107, 10, -121, -97, -106, 55, -14, -96, -116, 110, -4, -94, 56, -21, 30, -55, -47, 31, 58, 37, -46, 83, -30, -93, 61, 123, -120, -85, -111, -66, -102, -113, -50, -110, -8, -91, 68, 107, 112, -27, -64, 90, -45, 4, -118, 115, -92, 72, -15, 67, -83, -56, -52, 51, -39, -19, -13, 114, 2, -53, -1, 16, -48, -7, -58, -117, -77, 93, 53, -101, -38, -126, -44, 11, -59, 103, 5, -80, 63, -82, -114, -5, -71, -28, -78, -42, -103, 19, 48, -43, 49, 34, -25, 22, 92, 80, -22, 32, Byte.MAX_VALUE, 104, -125, 20, 38, 15, 94, -11, -100, 8, -3, -79, 43, -24, 73, 9, -54, -108, -35, 96, -109, 111, -34, -40, -60, 77, 40, 28, 13, -70, 21, -75, -90, -37, -112, -61, 85};

        /* renamed from: a */
        public static String m8355a(String str) {
            if (str == null) {
                return null;
            }
            return new String(m8354a(m8353b(str)));
        }

        /* renamed from: b */
        private static byte[] m8353b(String str) {
            int length = str.length();
            byte[] bArr = new byte[length / 2];
            byte[] bytes = str.getBytes();
            for (int i = 0; i < length / 2; i++) {
                bArr[i] = m8356a(bytes[i * 2], bytes[(i * 2) + 1]);
            }
            return bArr;
        }

        /* renamed from: a */
        private static byte m8356a(byte b, byte b2) {
            return (byte) (((char) (((char) Byte.decode("0x" + new String(new byte[]{b})).byteValue()) << 4)) ^ ((char) Byte.decode("0x" + new String(new byte[]{b2})).byteValue()));
        }

        /* renamed from: a */
        private static byte[] m8354a(byte[] bArr) {
            byte[] bArr2 = (byte[]) f2478a.clone();
            byte[] bArr3 = new byte[bArr.length];
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < bArr.length; i3++) {
                i2 = (i2 + 1) & 255;
                i = (i + (bArr2[i2] & 255)) & 255;
                byte b = bArr2[i2];
                bArr2[i2] = bArr2[i];
                bArr2[i] = b;
                bArr3[i3] = (byte) (bArr2[((bArr2[i2] & 255) + (bArr2[i] & 255)) & 255] ^ bArr[i3]);
            }
            return bArr3;
        }
    }

    /* compiled from: SecurityUtils.java */
    /* renamed from: com.sds.android.sdk.lib.util.j$a */
    /* loaded from: classes.dex */
    public static class C0609a {

        /* renamed from: a */
        private static final byte[] f2477a = {84, 65, -33, 92, 57, 90, -104, 0, 112, 74, 69, -53, 106, -122, -107, -95};

        /* renamed from: a */
        public static String m8364a(String str) throws Exception {
            return new String(m8363a(f2477a, m8362b(str)));
        }

        /* renamed from: a */
        private static byte[] m8363a(byte[] bArr, byte[] bArr2) throws Exception {
            IvParameterSpec ivParameterSpec = new IvParameterSpec("0102030405060708".getBytes());
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(bArr2);
        }

        /* renamed from: b */
        private static byte[] m8362b(String str) {
            int length = str.length() / 2;
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                bArr[i] = Integer.valueOf(str.substring(i * 2, (i * 2) + 2), 16).byteValue();
            }
            return bArr;
        }
    }
}
