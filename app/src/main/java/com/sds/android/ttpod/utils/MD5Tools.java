package com.sds.android.ttpod.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/* loaded from: classes.dex */
public class MD5Tools {
    static final byte[] PADDING;
    static final int S11 = 7;
    static final int S12 = 12;
    static final int S13 = 17;
    static final int S14 = 22;
    static final int S21 = 5;
    static final int S22 = 9;
    static final int S23 = 14;
    static final int S24 = 20;
    static final int S31 = 4;
    static final int S32 = 11;
    static final int S33 = 16;
    static final int S34 = 23;
    static final int S41 = 6;
    static final int S42 = 10;
    static final int S43 = 15;
    static final int S44 = 21;
    public String digestHexStr;
    private long[] state = new long[4];
    private long[] count = new long[2];
    private byte[] buffer = new byte[64];
    private byte[] digest = new byte[16];

    static {
        byte[] bArr = new byte[64];
        bArr[0] = Byte.MIN_VALUE;
        PADDING = bArr;
    }

    public byte[] getMD5(byte[] bArr) {
        md5Init();
        md5Update(new ByteArrayInputStream(bArr), bArr.length);
        md5Final();
        return this.digest;
    }

    public byte[] getMD5(InputStream inputStream, long j) {
        md5Init();
        if (!md5Update(inputStream, j)) {
            return new byte[16];
        }
        md5Final();
        return this.digest;
    }

    public MD5Tools() {
        md5Init();
    }

    private void md5Init() {
        this.count[0] = 0;
        this.count[1] = 0;
        this.state[0] = 1732584193;
        this.state[1] = 4023233417L;
        this.state[2] = 2562383102L;
        this.state[3] = 271733878;
    }

    /* renamed from: F */
    private long m238F(long j, long j2, long j3) {
        return (j & j2) | (((-1) ^ j) & j3);
    }

    /* renamed from: G */
    private long m236G(long j, long j2, long j3) {
        return (j & j3) | (((-1) ^ j3) & j2);
    }

    /* renamed from: H */
    private long m234H(long j, long j2, long j3) {
        return (j ^ j2) ^ j3;
    }

    /* renamed from: I */
    private long m232I(long j, long j2, long j3) {
        return (((-1) ^ j3) | j) ^ j2;
    }

    /* renamed from: FF */
    private long m237FF(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        long m238F = m238F(j2, j3, j4) + j5 + j7 + j;
        return ((((int) m238F) >>> ((int) (32 - j6))) | (((int) m238F) << ((int) j6))) + j2;
    }

    /* renamed from: GG */
    private long m235GG(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        long m236G = m236G(j2, j3, j4) + j5 + j7 + j;
        return ((((int) m236G) >>> ((int) (32 - j6))) | (((int) m236G) << ((int) j6))) + j2;
    }

    /* renamed from: HH */
    private long m233HH(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        long m234H = m234H(j2, j3, j4) + j5 + j7 + j;
        return ((((int) m234H) >>> ((int) (32 - j6))) | (((int) m234H) << ((int) j6))) + j2;
    }

    /* renamed from: II */
    private long m231II(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        long m232I = m232I(j2, j3, j4) + j5 + j7 + j;
        return ((((int) m232I) >>> ((int) (32 - j6))) | (((int) m232I) << ((int) j6))) + j2;
    }

    private boolean md5Update(InputStream inputStream, long j) {
        int i;
        byte[] bArr = new byte[64];
        int i2 = ((int) (this.count[0] >>> 3)) & 63;
        long[] jArr = this.count;
        long j2 = jArr[0] + (j << 3);
        jArr[0] = j2;
        if (j2 < (j << 3)) {
            long[] jArr2 = this.count;
            jArr2[1] = jArr2[1] + 1;
        }
        long[] jArr3 = this.count;
        jArr3[1] = jArr3[1] + (j >>> 29);
        int i3 = 64 - i2;
        if (j >= i3) {
            byte[] bArr2 = new byte[i3];
            try {
                inputStream.read(bArr2, 0, i3);
                md5Memcpy(this.buffer, bArr2, i2, 0, i3);
                md5Transform(this.buffer);
                i = i3;
                while (i + 63 < j) {
                    try {
                        inputStream.read(bArr);
                        md5Transform(bArr);
                        i += 64;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                }
                i2 = 0;
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        } else {
            i = 0;
        }
        byte[] bArr3 = new byte[(int) (j - i)];
        try {
            inputStream.read(bArr3);
            md5Memcpy(this.buffer, bArr3, i2, 0, bArr3.length);
            return true;
        } catch (Exception e3) {
            e3.printStackTrace();
            return false;
        }
    }

    private void md5Update(byte[] bArr, int i) {
        int i2 = 0;
        byte[] bArr2 = new byte[64];
        int i3 = ((int) (this.count[0] >>> 3)) & 63;
        long[] jArr = this.count;
        long j = jArr[0] + (i << 3);
        jArr[0] = j;
        if (j < (i << 3)) {
            long[] jArr2 = this.count;
            jArr2[1] = jArr2[1] + 1;
        }
        long[] jArr3 = this.count;
        jArr3[1] = jArr3[1] + (i >>> 29);
        int i4 = 64 - i3;
        if (i >= i4) {
            md5Memcpy(this.buffer, bArr, i3, 0, i4);
            md5Transform(this.buffer);
            while (i4 + 63 < i) {
                md5Memcpy(bArr2, bArr, 0, i4, 64);
                md5Transform(bArr2);
                i4 += 64;
            }
            i3 = 0;
            i2 = i4;
        }
        md5Memcpy(this.buffer, bArr, i3, i2, i - i2);
    }

    private void md5Final() {
        byte[] bArr = new byte[8];
        Encode(bArr, this.count, 8);
        int i = ((int) (this.count[0] >>> 3)) & 63;
        md5Update(PADDING, i < 56 ? 56 - i : 120 - i);
        md5Update(bArr, 8);
        Encode(this.digest, this.state, 16);
    }

    private void md5Memcpy(byte[] bArr, byte[] bArr2, int i, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            bArr[i + i4] = bArr2[i2 + i4];
        }
    }

    private void md5Transform(byte[] bArr) {
        long j = this.state[0];
        long j2 = this.state[1];
        long j3 = this.state[2];
        long j4 = this.state[3];
        long[] jArr = new long[16];
        Decode(jArr, bArr, 64);
        long m237FF = m237FF(j, j2, j3, j4, jArr[0], 7L, 3614090360L);
        long m237FF2 = m237FF(j4, m237FF, j2, j3, jArr[1], 12L, 3905402710L);
        long m237FF3 = m237FF(j3, m237FF2, m237FF, j2, jArr[2], 17L, 606105819L);
        long m237FF4 = m237FF(j2, m237FF3, m237FF2, m237FF, jArr[3], 22L, 3250441966L);
        long m237FF5 = m237FF(m237FF, m237FF4, m237FF3, m237FF2, jArr[4], 7L, 4118548399L);
        long m237FF6 = m237FF(m237FF2, m237FF5, m237FF4, m237FF3, jArr[5], 12L, 1200080426L);
        long m237FF7 = m237FF(m237FF3, m237FF6, m237FF5, m237FF4, jArr[6], 17L, 2821735955L);
        long m237FF8 = m237FF(m237FF4, m237FF7, m237FF6, m237FF5, jArr[7], 22L, 4249261313L);
        long m237FF9 = m237FF(m237FF5, m237FF8, m237FF7, m237FF6, jArr[8], 7L, 1770035416L);
        long m237FF10 = m237FF(m237FF6, m237FF9, m237FF8, m237FF7, jArr[9], 12L, 2336552879L);
        long m237FF11 = m237FF(m237FF7, m237FF10, m237FF9, m237FF8, jArr[10], 17L, 4294925233L);
        long m237FF12 = m237FF(m237FF8, m237FF11, m237FF10, m237FF9, jArr[11], 22L, 2304563134L);
        long m237FF13 = m237FF(m237FF9, m237FF12, m237FF11, m237FF10, jArr[12], 7L, 1804603682L);
        long m237FF14 = m237FF(m237FF10, m237FF13, m237FF12, m237FF11, jArr[13], 12L, 4254626195L);
        long m237FF15 = m237FF(m237FF11, m237FF14, m237FF13, m237FF12, jArr[14], 17L, 2792965006L);
        long m237FF16 = m237FF(m237FF12, m237FF15, m237FF14, m237FF13, jArr[15], 22L, 1236535329L);
        long m235GG = m235GG(m237FF13, m237FF16, m237FF15, m237FF14, jArr[1], 5L, 4129170786L);
        long m235GG2 = m235GG(m237FF14, m235GG, m237FF16, m237FF15, jArr[6], 9L, 3225465664L);
        long m235GG3 = m235GG(m237FF15, m235GG2, m235GG, m237FF16, jArr[11], 14L, 643717713L);
        long m235GG4 = m235GG(m237FF16, m235GG3, m235GG2, m235GG, jArr[0], 20L, 3921069994L);
        long m235GG5 = m235GG(m235GG, m235GG4, m235GG3, m235GG2, jArr[5], 5L, 3593408605L);
        long m235GG6 = m235GG(m235GG2, m235GG5, m235GG4, m235GG3, jArr[10], 9L, 38016083L);
        long m235GG7 = m235GG(m235GG3, m235GG6, m235GG5, m235GG4, jArr[15], 14L, 3634488961L);
        long m235GG8 = m235GG(m235GG4, m235GG7, m235GG6, m235GG5, jArr[4], 20L, 3889429448L);
        long m235GG9 = m235GG(m235GG5, m235GG8, m235GG7, m235GG6, jArr[9], 5L, 568446438L);
        long m235GG10 = m235GG(m235GG6, m235GG9, m235GG8, m235GG7, jArr[14], 9L, 3275163606L);
        long m235GG11 = m235GG(m235GG7, m235GG10, m235GG9, m235GG8, jArr[3], 14L, 4107603335L);
        long m235GG12 = m235GG(m235GG8, m235GG11, m235GG10, m235GG9, jArr[8], 20L, 1163531501L);
        long m235GG13 = m235GG(m235GG9, m235GG12, m235GG11, m235GG10, jArr[13], 5L, 2850285829L);
        long m235GG14 = m235GG(m235GG10, m235GG13, m235GG12, m235GG11, jArr[2], 9L, 4243563512L);
        long m235GG15 = m235GG(m235GG11, m235GG14, m235GG13, m235GG12, jArr[7], 14L, 1735328473L);
        long m235GG16 = m235GG(m235GG12, m235GG15, m235GG14, m235GG13, jArr[12], 20L, 2368359562L);
        long m233HH = m233HH(m235GG13, m235GG16, m235GG15, m235GG14, jArr[5], 4L, 4294588738L);
        long m233HH2 = m233HH(m235GG14, m233HH, m235GG16, m235GG15, jArr[8], 11L, 2272392833L);
        long m233HH3 = m233HH(m235GG15, m233HH2, m233HH, m235GG16, jArr[11], 16L, 1839030562L);
        long m233HH4 = m233HH(m235GG16, m233HH3, m233HH2, m233HH, jArr[14], 23L, 4259657740L);
        long m233HH5 = m233HH(m233HH, m233HH4, m233HH3, m233HH2, jArr[1], 4L, 2763975236L);
        long m233HH6 = m233HH(m233HH2, m233HH5, m233HH4, m233HH3, jArr[4], 11L, 1272893353L);
        long m233HH7 = m233HH(m233HH3, m233HH6, m233HH5, m233HH4, jArr[7], 16L, 4139469664L);
        long m233HH8 = m233HH(m233HH4, m233HH7, m233HH6, m233HH5, jArr[10], 23L, 3200236656L);
        long m233HH9 = m233HH(m233HH5, m233HH8, m233HH7, m233HH6, jArr[13], 4L, 681279174L);
        long m233HH10 = m233HH(m233HH6, m233HH9, m233HH8, m233HH7, jArr[0], 11L, 3936430074L);
        long m233HH11 = m233HH(m233HH7, m233HH10, m233HH9, m233HH8, jArr[3], 16L, 3572445317L);
        long m233HH12 = m233HH(m233HH8, m233HH11, m233HH10, m233HH9, jArr[6], 23L, 76029189L);
        long m233HH13 = m233HH(m233HH9, m233HH12, m233HH11, m233HH10, jArr[9], 4L, 3654602809L);
        long m233HH14 = m233HH(m233HH10, m233HH13, m233HH12, m233HH11, jArr[12], 11L, 3873151461L);
        long m233HH15 = m233HH(m233HH11, m233HH14, m233HH13, m233HH12, jArr[15], 16L, 530742520L);
        long m233HH16 = m233HH(m233HH12, m233HH15, m233HH14, m233HH13, jArr[2], 23L, 3299628645L);
        long m231II = m231II(m233HH13, m233HH16, m233HH15, m233HH14, jArr[0], 6L, 4096336452L);
        long m231II2 = m231II(m233HH14, m231II, m233HH16, m233HH15, jArr[7], 10L, 1126891415L);
        long m231II3 = m231II(m233HH15, m231II2, m231II, m233HH16, jArr[14], 15L, 2878612391L);
        long m231II4 = m231II(m233HH16, m231II3, m231II2, m231II, jArr[5], 21L, 4237533241L);
        long m231II5 = m231II(m231II, m231II4, m231II3, m231II2, jArr[12], 6L, 1700485571L);
        long m231II6 = m231II(m231II2, m231II5, m231II4, m231II3, jArr[3], 10L, 2399980690L);
        long m231II7 = m231II(m231II3, m231II6, m231II5, m231II4, jArr[10], 15L, 4293915773L);
        long m231II8 = m231II(m231II4, m231II7, m231II6, m231II5, jArr[1], 21L, 2240044497L);
        long m231II9 = m231II(m231II5, m231II8, m231II7, m231II6, jArr[8], 6L, 1873313359L);
        long m231II10 = m231II(m231II6, m231II9, m231II8, m231II7, jArr[15], 10L, 4264355552L);
        long m231II11 = m231II(m231II7, m231II10, m231II9, m231II8, jArr[6], 15L, 2734768916L);
        long m231II12 = m231II(m231II8, m231II11, m231II10, m231II9, jArr[13], 21L, 1309151649L);
        long m231II13 = m231II(m231II9, m231II12, m231II11, m231II10, jArr[4], 6L, 4149444226L);
        long m231II14 = m231II(m231II10, m231II13, m231II12, m231II11, jArr[11], 10L, 3174756917L);
        long m231II15 = m231II(m231II11, m231II14, m231II13, m231II12, jArr[2], 15L, 718787259L);
        long m231II16 = m231II(m231II12, m231II15, m231II14, m231II13, jArr[9], 21L, 3951481745L);
        long[] jArr2 = this.state;
        jArr2[0] = jArr2[0] + m231II13;
        long[] jArr3 = this.state;
        jArr3[1] = m231II16 + jArr3[1];
        long[] jArr4 = this.state;
        jArr4[2] = jArr4[2] + m231II15;
        long[] jArr5 = this.state;
        jArr5[3] = jArr5[3] + m231II14;
    }

    private void Encode(byte[] bArr, long[] jArr, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3 += 4) {
            bArr[i3] = (byte) (jArr[i2] & 255);
            bArr[i3 + 1] = (byte) ((jArr[i2] >>> 8) & 255);
            bArr[i3 + 2] = (byte) ((jArr[i2] >>> 16) & 255);
            bArr[i3 + 3] = (byte) ((jArr[i2] >>> 24) & 255);
            i2++;
        }
    }

    private void Decode(long[] jArr, byte[] bArr, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3 += 4) {
            jArr[i2] = b2iu(bArr[i3]) | (b2iu(bArr[i3 + 1]) << 8) | (b2iu(bArr[i3 + 2]) << 16) | (b2iu(bArr[i3 + 3]) << 24);
            i2++;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:0:?, code lost:
        r2 = r2;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static long b2iu(byte b) {
        byte b2 = 0;
        if (b < 0) {
            b2 = (byte) (b & 255);
        }
        return b2;
    }

    public static String byteHEX(byte b) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        return new String(new char[]{cArr[(b >>> 4) & 15], cArr[b & 15]});
    }

    public static byte[] toMD5Byte(byte[] bArr) {
        return new MD5Tools().getMD5(bArr);
    }

    public static byte[] toMD5Byte(String str) {
        byte[] bytes;
        try {
            bytes = str.getBytes("ISO8859_1");
        } catch (UnsupportedEncodingException e) {
            bytes = str.getBytes();
        }
        return new MD5Tools().getMD5(bytes);
    }

    public static byte[] toMD5Byte(InputStream inputStream, long j) {
        return new MD5Tools().getMD5(inputStream, j);
    }

    public static String toMD5(InputStream inputStream, long j) {
        byte[] md5 = new MD5Tools().getMD5(inputStream, j);
        String str = "";
        for (int i = 0; i < 16; i++) {
            str = String.valueOf(str) + byteHEX(md5[i]);
        }
        return str;
    }

    public static String toMD5(byte[] bArr) {
        byte[] md5 = new MD5Tools().getMD5(bArr);
        String str = "";
        for (int i = 0; i < 16; i++) {
            str = String.valueOf(str) + byteHEX(md5[i]);
        }
        return str;
    }

    public static String toMD5(String str) {
        byte[] bytes;
        try {
            bytes = str.getBytes("ISO8859_1");
        } catch (UnsupportedEncodingException e) {
            bytes = str.getBytes();
        }
        byte[] md5 = new MD5Tools().getMD5(bytes);
        String str2 = "";
        for (int i = 0; i < 16; i++) {
            str2 = String.valueOf(str2) + byteHEX(md5[i]);
        }
        return str2;
    }

    public static String getMD5String(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bArr);
            byte[] digest = messageDigest.digest();
            char[] cArr2 = new char[32];
            int i = 0;
            for (int i2 = 0; i2 < 16; i2++) {
                byte b = digest[i2];
                int i3 = i + 1;
                cArr2[i] = cArr[(b >>> 4) & 15];
                i = i3 + 1;
                cArr2[i3] = cArr[b & 15];
            }
            return new String(cArr2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getFileMD5(File file) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bArr = new byte[1024];
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                while (true) {
                    int read = fileInputStream.read(bArr, 0, 1024);
                    if (read == -1) {
                        break;
                    }
                    messageDigest.update(bArr, 0, read);
                }
                byte[] digest = messageDigest.digest();
                char[] cArr2 = new char[32];
                int i = 0;
                for (int i2 = 0; i2 < 16; i2++) {
                    byte b = digest[i2];
                    int i3 = i + 1;
                    cArr2[i] = cArr[(b >>> 4) & 15];
                    i = i3 + 1;
                    cArr2[i3] = cArr[b & 15];
                }
                String str = new String(cArr2);
                try {
                    fileInputStream.close();
                    return str;
                } catch (IOException e) {
                    e.printStackTrace();
                    return str;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e32) {
                e32.printStackTrace();
            }
        }
    }

    public static String HEXByte(byte[] bArr) {
        try {
            byte[] bArr2 = new byte[bArr.length / 2];
            for (int i = 0; i < bArr2.length; i++) {
                bArr2[i] = (byte) ((getByte(bArr[i * 2]) << 4) + getByte(bArr[(i * 2) + 1]));
            }
            return new String(bArr2, "ISO-8859-1");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static byte getByte(byte b) {
        if (b >= 48 && b <= 57) {
            return (byte) (b - 48);
        }
        if (b >= 97 && b <= 102) {
            return (byte) ((b - 97) + 10);
        }
        if (b < 65 || b > 70) {
            return (byte) 48;
        }
        return (byte) ((b - 65) + 10);
    }
}