package com.mradar.sdk.http;

/* loaded from: classes.dex */
public class StaticParam {

    /* loaded from: classes.dex */
    public enum CompressType {
        SPEEX,
        OPUS,
        ARM,
        NONE;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static CompressType[] valuesCustom() {
            CompressType[] valuesCustom = values();
            int length = valuesCustom.length;
            CompressType[] compressTypeArr = new CompressType[length];
            System.arraycopy(valuesCustom, 0, compressTypeArr, 0, length);
            return compressTypeArr;
        }
    }
}
