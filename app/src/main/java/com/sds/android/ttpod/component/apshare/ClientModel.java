package com.sds.android.ttpod.component.apshare;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


/* loaded from: classes.dex */
public class ClientModel implements Parcelable {
    public static final Parcelable.Creator<ClientModel> CREATOR = new Parcelable.Creator<ClientModel>() { // from class: com.sds.android.ttpod.component.apshare.ClientModel.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public ClientModel createFromParcel(Parcel parcel) {
            return new ClientModel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public ClientModel[] newArray(int i) {
            return new ClientModel[i];
        }
    };
    @SerializedName(value = "ip")

    /* renamed from: a */
    private String f3703a;
    @SerializedName(value = "name")

    /* renamed from: b */
    private String f3704b;

    public ClientModel(String str, String str2) {
        this.f3703a = str;
        this.f3704b = str2;
    }

    public ClientModel(Parcel parcel) {
        this.f3703a = parcel.readString();
        this.f3704b = parcel.readString();
    }

    /* renamed from: a */
    public String m7126a() {
        return this.f3703a;
    }

    /* renamed from: b */
    public String m7125b() {
        return this.f3704b;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3703a);
        parcel.writeString(this.f3704b);
    }

    public String toString() {
        return "name: " + this.f3704b + ", ip: " + this.f3703a;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ClientModel)) {
            return false;
        }
        return ((ClientModel) obj).m7126a().equals(this.f3703a);
    }

    public int hashCode() {
        return this.f3703a.hashCode();
    }
}
