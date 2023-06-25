package com.sds.android.ttpod.framework.support.p134a;

import com.sds.android.sdk.lib.util.LogUtils;

/* renamed from: com.sds.android.ttpod.framework.support.a.d */
/* loaded from: classes.dex */
final class Normalizer {

    /* renamed from: a */
    private static final byte[] f7083a = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8, 9, 9, 9, 9};

    /* renamed from: b */
    private static final byte[] f7084b = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};

    /* renamed from: c */
    private static final short[] f7085c = {0, 4, 14, 30, 54, 82, 118, 162, 210, 264, 330, 398, 472, 554, 644, 738, 839, 948, 1052, 1102, 1140, 1192, 1240, 1320, 1380, 1508, 1648, 1798, 1938, 2100, 2257, 2307, 2364, 2420, 2480, 2510, 2548, 2728, 2916, 3112, 3315, 3527, 3746, 3972, 4208, 4451, 4702, 4862, 4892, 4920, 4950, 5008, 5056, 5339, 5632, 5935, 6248, 6572, 6907, 7253, 7610, 7878, 7904, 7998, 8062, 8120, 8194, 8615, 9051, 9511, 9978, 10452, 10960, 11466, 11902, 11980, 12012, 12068, 12128, 12796, 13421, 14105, 14814, 15500, 16313, 17104, 17923, 18020, 18134, 18297, 19138, 20447, 21596, 22795, 24047, 26674, 28197, 29127, 30203, Short.MAX_VALUE};

    /* renamed from: a */
    public static void m2649a(int[] iArr, int i) {
        iArr[0] = ((iArr[0] * 2) + iArr[1]) / 3;
        for (int i2 = 1; i2 < i; i2++) {
            iArr[i2] = ((iArr[i2] * 2) + iArr[i2 - 1]) / 3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v2, types: [int] */
    /* renamed from: a */
    public static int m2648a(int[] iArr, int i, short[] sArr, int i2) {
        byte[] bArr;
        int i3;
        if (iArr == null || sArr == null || i < 0 || i > 128 || sArr.length < i2) {
            LogUtils.debug("Normalizer", "argument error -1");
            return -1;
        } else if (i2 < i) {
            LogUtils.debug("Normalizer", "argument error -2");
            return -2;
        } else if (i2 != 512) {
            LogUtils.debug("Normalizer", "argument error -3");
            return -3;
        } else {
            if (i <= 64) {
                bArr = f7083a;
            } else if (i <= 128) {
                bArr = f7084b;
            } else {
                LogUtils.debug("Normalizer", "argument error -4");
                return -4;
            }
            int i4 = 0;
            int i5 = 0;
            while (i5 < i) {
                byte b = bArr[i5];
                int i6 = i4;
                byte b2 = 0;
                int i7 = 0;
                while (b2 < b) {
                    i7 += sArr[i6];
                    b2++;
                    i6++;
                }
                int i8 = 0;
                while (i8 < 100 && f7085c[i8] < i7) {
                    i8++;
                }
                if (i5 > 20) {
                    i3 = 4;
                } else if (i5 > 10) {
                    i3 = 5;
                } else {
                    i3 = 6;
                }
                int i9 = iArr[i5] - i3;
                if (i5 > 20) {
                    int i10 = iArr[i5];
                    if (i8 > i9) {
                        i9 = i8;
                    }
                    iArr[i5] = ((i9 * 2) + i10) / 3;
                } else {
                    if (i8 <= i9) {
                        i8 = i9;
                    }
                    iArr[i5] = i8;
                }
                i5++;
                i4 = i6;
            }
            m2649a(iArr, i);
            return 0;
        }
    }
}
