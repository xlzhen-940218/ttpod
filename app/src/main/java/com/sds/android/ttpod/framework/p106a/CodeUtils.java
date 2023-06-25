package com.sds.android.ttpod.framework.p106a;



/* renamed from: com.sds.android.ttpod.framework.a.d */
/* loaded from: classes.dex */
public class CodeUtils {
    public static String m4763a(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int i5 = i2 + i;
        char[] cArr = new char[i5];
        int i6 = 0;
        while (i < i5) {
            int i7 = i + 1;
            int i8 = bArr[i] & 255;
            if ((i8 >> 5) == 6 && i7 < i5) {
                i3 = i6 + 1;
                int i9 = (i8 & 31) << 6;
                i4 = i7 + 1;
                cArr[i6] = (char) ((bArr[i7] & 63) | i9);
            } else if ((i8 >> 4) == 14 && i7 < i5 - 1) {
                i3 = i6 + 1;
                int i10 = i7 + 1;
                int i11 = ((bArr[i7] & 63) << 6) | ((i8 & 15) << 12);
                i4 = i10 + 1;
                cArr[i6] = (char) (i11 | (bArr[i10] & 63));
            } else {
                i3 = i6 + 1;
                cArr[i6] = (char) i8;
                i4 = i7;
            }
            i6 = i3;
            i = i4;
        }
        return new String(cArr, 0, i6);
    }


    /* JADX WARN: Code restructure failed: missing block: B:40:0x00ae, code lost:
        if (r6 >= r0) goto L47;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean m4764a(byte[] bArr) {
        boolean z;
        int i;
        int length = bArr.length;
        int i2 = 0;
        boolean z2 = true;
        int i3 = 0;
        while (true) {
            if (i2 < length) {
                int i4 = bArr[i2] & 255;
                if ((i4 & 128) != 0) {
                    z2 = false;
                }
                if (i3 == 0) {
                    if (i4 < 128) {
                        i2++;
                        continue;
                    } else {
                        if (i4 >= 252 && i4 <= 253) {
                            i = 6;
                        } else if (i4 < 248) {
                            if (i4 < 240) {
                                if (i4 < 224) {
                                    z = false;
                                    if (i4 < 192) {
                                        break;
                                    }
                                    i = 2;
                                } else {
                                    i = 3;
                                }
                            } else {
                                i = 4;
                            }
                        } else {
                            i = 5;
                        }
                        i3 = i - 1;
                    }
                    i2++;
                } else if ((i4 & 192) != 128) {
                    z = false;
                    break;
                } else {
                    i3--;
                    i2++;
                }
            } else {
                if (i3 > 0) {
                    z = false;
                }
                z = !z2;
                return z;
            }
        }
        return z;
    }

}
