package com.sds.android.ttpod.framework.modules.core.p116d.p118b;

/* renamed from: com.sds.android.ttpod.framework.modules.core.d.b.d */
/* loaded from: classes.dex */
class KmpMatch {
    /* renamed from: a */
    public static long m4181a(byte[] bArr, int i, byte[] bArr2) {
        int[] m4182a = m4182a(bArr2);
        int i2 = 0;
        if (bArr.length == 0) {
            return -1L;
        }
        while (i < bArr.length) {
            while (i2 > 0 && bArr2[i2] != bArr[i]) {
                i2 = m4182a[i2 - 1];
            }
            if (bArr2[i2] == bArr[i]) {
                i2++;
            }
            if (i2 != bArr2.length) {
                i++;
            } else {
                return (i - bArr2.length) + 1;
            }
        }
        return -1L;
    }

    /* renamed from: a */
    private static int[] m4182a(byte[] bArr) {
        int[] iArr = new int[bArr.length];
        int i = 0;
        for (int i2 = 1; i2 < bArr.length; i2++) {
            while (i > 0 && bArr[i] != bArr[i2]) {
                i = iArr[i - 1];
            }
            if (bArr[i] == bArr[i2]) {
                i++;
            }
            iArr[i2] = i;
        }
        return iArr;
    }
}
