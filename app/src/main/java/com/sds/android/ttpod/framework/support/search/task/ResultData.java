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
            resultData.title = parcel.readString();
            resultData.artist = parcel.readString();
            resultData.album = parcel.readString();
            resultData.allname = parcel.readString();
            if (parcel.readByte() != 0) {
                Parcelable[] readParcelableArray = parcel.readParcelableArray(Item.class.getClassLoader());
                resultData.lyricArray = (Item[]) ResultData.m2180b(readParcelableArray, readParcelableArray.length, Item[].class);
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
    protected String title;

    /* renamed from: b */
    protected String artist;

    /* renamed from: c */
    protected String album;

    /* renamed from: d */
    protected String allname;

    /* renamed from: e */
    protected Item[] lyricArray;

    public String toString() {
        StringBuilder sb = new StringBuilder(32);
        if (!TextUtils.isEmpty(this.artist)) {
            sb.append(this.artist);
        }
        if (!TextUtils.isEmpty(this.title)) {
            if (sb.length() > 0) {
                sb.append('-');
            }
            sb.append(this.title);
        }
        if (!TextUtils.isEmpty(this.album)) {
            if (sb.length() > 0) {
                sb.append('/');
            }
            sb.append(this.album);
        }
        if (sb.length() == 0 && !TextUtils.isEmpty(this.allname)) {
            sb.append(this.allname);
        }
        if (this.lyricArray != null && 1 == this.lyricArray.length && "trc".equals(this.lyricArray[0].getType())) {
            sb.append(" TRC");
        }
        return sb.toString();
    }

    /* renamed from: a */
    public String getTitle() {
        return this.title;
    }

    /* renamed from: b */
    public String getArtist() {
        return this.artist;
    }

    /* renamed from: c */
    public Item[] getLyricArray() {
        return this.lyricArray;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setAllname(String allname) {
        this.allname = allname;
    }

    public void setLyricArray(Item[] lyricArray) {
        this.lyricArray = lyricArray;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title != null ? this.title : "");
        parcel.writeString(this.artist != null ? this.artist : "");
        parcel.writeString(this.album != null ? this.album : "");
        parcel.writeString(this.allname != null ? this.allname : "");
        parcel.writeByte((byte) (this.lyricArray != null ? 1 : 0));
        if (this.lyricArray != null) {
            parcel.writeParcelableArray(this.lyricArray, i);
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
        protected String type;

        /* renamed from: b */
        protected String url;

        /* renamed from: c */
        protected String localLyricPath;

        /* renamed from: d */
        protected int id;

        public Item(String type, String url, String localLyricPath, int id) {
            this.type = type;
            this.url = url;
            this.localLyricPath = localLyricPath;
            this.id = id;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setLocalLyricPath(String localLyricPath) {
            this.localLyricPath = localLyricPath;
        }

        public void setId(int id) {
            this.id = id;
        }

        /* renamed from: a */
        public int getId() {
            return this.id;
        }

        /* renamed from: b */
        public String getType() {
            return this.type;
        }

        /* renamed from: c */
        public String getUrl() {
            return this.url;
        }

        /* renamed from: d */
        public String getLocalLyricPath() {
            return this.localLyricPath;
        }

        @Override // java.lang.Comparable
        /* renamed from: a */
        public int compareTo(Item item) {
            return this.id - item.id;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.type != null ? this.type : "");
            parcel.writeString(this.url != null ? this.url : "");
            parcel.writeString(this.localLyricPath != null ? this.localLyricPath : "");
            parcel.writeInt(this.id);
        }
    }
}
