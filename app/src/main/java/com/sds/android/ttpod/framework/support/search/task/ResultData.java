package com.sds.android.ttpod.framework.support.search.task;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.lang.reflect.Array;

/* loaded from: classes.dex */
public class ResultData implements Parcelable {
    public static final Parcelable.Creator<ResultData> CREATOR = new Parcelable.Creator<ResultData>() { // from class: com.sds.android.ttpod.framework.support.search.task.ResultData.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public ResultData createFromParcel(Parcel parcel) {
            ResultData resultData = new ResultData();
            resultData.f7288a = parcel.readString();
            resultData.f7289b = parcel.readString();
            resultData.f7290c = parcel.readString();
            resultData.f7291d = parcel.readString();
            if (parcel.readByte() != 0) {
                Parcelable[] readParcelableArray = parcel.readParcelableArray(Item.class.getClassLoader());
                resultData.f7292e = (Item[]) ResultData.m2180b(readParcelableArray, readParcelableArray.length, Item[].class);
            }
            return resultData;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public ResultData[] newArray(int i) {
            return new ResultData[i];
        }
    };

    /* renamed from: a */
    protected String f7288a;

    /* renamed from: b */
    protected String f7289b;

    /* renamed from: c */
    protected String f7290c;

    /* renamed from: d */
    protected String f7291d;

    /* renamed from: e */
    protected Item[] f7292e;

    public String toString() {
        StringBuilder sb = new StringBuilder(32);
        if (!TextUtils.isEmpty(this.f7289b)) {
            sb.append(this.f7289b);
        }
        if (!TextUtils.isEmpty(this.f7288a)) {
            if (sb.length() > 0) {
                sb.append('-');
            }
            sb.append(this.f7288a);
        }
        if (!TextUtils.isEmpty(this.f7290c)) {
            if (sb.length() > 0) {
                sb.append('/');
            }
            sb.append(this.f7290c);
        }
        if (sb.length() == 0 && !TextUtils.isEmpty(this.f7291d)) {
            sb.append(this.f7291d);
        }
        if (this.f7292e != null && 1 == this.f7292e.length && "trc".equals(this.f7292e[0].m2174b())) {
            sb.append(" TRC");
        }
        return sb.toString();
    }

    /* renamed from: a */
    public String m2184a() {
        return this.f7288a;
    }

    /* renamed from: b */
    public String m2181b() {
        return this.f7289b;
    }

    /* renamed from: c */
    public Item[] m2179c() {
        return this.f7292e;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f7288a != null ? this.f7288a : "");
        parcel.writeString(this.f7289b != null ? this.f7289b : "");
        parcel.writeString(this.f7290c != null ? this.f7290c : "");
        parcel.writeString(this.f7291d != null ? this.f7291d : "");
        parcel.writeByte((byte) (this.f7292e != null ? 1 : 0));
        if (this.f7292e != null) {
            parcel.writeParcelableArray(this.f7292e, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static <T, U> T[] m2180b(U[] uArr, int i, Class<? extends T[]> cls) {
        if (i < 0) {
            throw new NegativeArraySizeException(Integer.toString(i));
        }
        return (T[]) m2183a(uArr, 0, i, cls);
    }

    /* renamed from: a */
    private static <T, U> T[] m2183a(U[] uArr, int i, int i2, Class<? extends T[]> cls) {
        if (i > i2) {
            throw new IllegalArgumentException();
        }
        int length = uArr.length;
        if (i < 0 || i > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i3 = i2 - i;
        int min = Math.min(i3, length - i);
        T[] tArr = (T[]) ((Object[]) Array.newInstance(cls.getComponentType(), i3));
        System.arraycopy(uArr, i, tArr, 0, min);
        return tArr;
    }

    /* loaded from: classes.dex */
    public static class Item implements Parcelable, Comparable<Item> {
        public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() { // from class: com.sds.android.ttpod.framework.support.search.task.ResultData.Item.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public Item createFromParcel(Parcel parcel) {
                return new Item(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt());
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public Item[] newArray(int i) {
                return new Item[i];
            }
        };

        /* renamed from: a */
        protected String f7293a;

        /* renamed from: b */
        protected String f7294b;

        /* renamed from: c */
        protected String f7295c;

        /* renamed from: d */
        protected int f7296d;

        public Item(String str, String str2, String str3, int i) {
            this.f7293a = str;
            this.f7294b = str2;
            this.f7295c = str3;
            this.f7296d = i;
        }

        /* renamed from: a */
        public int m2176a() {
            return this.f7296d;
        }

        /* renamed from: b */
        public String m2174b() {
            return this.f7293a;
        }

        /* renamed from: c */
        public String m2173c() {
            return this.f7294b;
        }

        /* renamed from: d */
        public String m2172d() {
            return this.f7295c;
        }

        @Override // java.lang.Comparable
        /* renamed from: a */
        public int compareTo(Item item) {
            return this.f7296d - item.f7296d;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f7293a != null ? this.f7293a : "");
            parcel.writeString(this.f7294b != null ? this.f7294b : "");
            parcel.writeString(this.f7295c != null ? this.f7295c : "");
            parcel.writeInt(this.f7296d);
        }
    }
}
