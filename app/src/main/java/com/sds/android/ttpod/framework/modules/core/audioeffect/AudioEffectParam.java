package com.sds.android.ttpod.framework.modules.core.audioeffect;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.media.audiofx.TTEqualizer;

import java.io.Serializable;

/* loaded from: classes.dex */
public class AudioEffectParam implements Parcelable, Serializable {
    public static final Parcelable.Creator<AudioEffectParam> CREATOR = new Parcelable.Creator<AudioEffectParam>() { // from class: com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectParam.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public AudioEffectParam createFromParcel(Parcel parcel) {
            LogUtils.m8388a("Parcelable.AudioEffectParam.TAG", "createFromParcel");
            AudioEffectParam audioEffectParam = new AudioEffectParam();
            audioEffectParam.f5816b = parcel.readInt();
            audioEffectParam.f5817c = parcel.readInt();
            audioEffectParam.f5818d = parcel.readInt();
            audioEffectParam.f5819e = parcel.readInt();
            audioEffectParam.f5820f = parcel.readFloat();
            audioEffectParam.f5821g = parcel.readInt() != 0;
            audioEffectParam.f5822h = parcel.readString();
            audioEffectParam.f5826l = parcel.readInt();
            audioEffectParam.f5815a = parcel.readString();
            return audioEffectParam;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public AudioEffectParam[] newArray(int i) {
            LogUtils.m8388a("Parcelable.AudioEffectParam.TAG", "Person[] newArray(int size)");
            return new AudioEffectParam[i];
        }
    };
    @SerializedName(value = "_id")

    /* renamed from: a */
    private String f5815a = "";
    @SerializedName(value = "bass")

    /* renamed from: b */
    private int f5816b = 0;
    @SerializedName(value = "treble")

    /* renamed from: c */
    private int f5817c = 0;
    @SerializedName(value = "virtualizer")

    /* renamed from: d */
    private int f5818d = 0;
    @SerializedName(value = "reverb")

    /* renamed from: e */
    private int f5819e = 0;
    @SerializedName(value = "balance")

    /* renamed from: f */
    private float f5820f = 0.0f;
    @SerializedName(value = "limit")

    /* renamed from: g */
    private boolean f5821g = false;
    @SerializedName(value = "equalizer")

    /* renamed from: h */
    private String f5822h = new TTEqualizer.Settings(EqualizerPreset.m4334b(), (short) 10, EqualizerPreset.m4333b(EqualizerPreset.m4334b())).toString();
    @SerializedName(value = "isedit")

    /* renamed from: i */
    private boolean f5823i = false;
    @SerializedName(value = "nickname")

    /* renamed from: j */
    private String f5824j = "";
    @SerializedName(value = "style")

    /* renamed from: k */
    private int f5825k = 0;
    @SerializedName(value = "usedeffect")

    /* renamed from: l */
    private int f5826l = 0;

    /* renamed from: a */
    public void m4441a(String str) {
        this.f5824j = str;
    }

    /* renamed from: a */
    public void m4446a(int i) {
        this.f5825k = i;
    }

    /* renamed from: b */
    public void m4438b(int i) {
        this.f5816b = i;
    }

    /* renamed from: c */
    public void m4432c(int i) {
        this.f5817c = i;
    }

    /* renamed from: d */
    public void m4428d(int i) {
        this.f5818d = i;
    }

    /* renamed from: e */
    public void m4425e(int i) {
        this.f5819e = i;
    }

    /* renamed from: a */
    public void m4447a(float f) {
        this.f5820f = f;
    }

    /* renamed from: a */
    public void m4440a(boolean z) {
        this.f5821g = z;
    }

    /* renamed from: b */
    public void m4435b(String str) {
        this.f5822h = str;
    }

    /* renamed from: a */
    public int m4448a() {
        return this.f5816b;
    }

    /* renamed from: b */
    public int m4439b() {
        return this.f5817c;
    }

    /* renamed from: c */
    public int m4433c() {
        return this.f5818d;
    }

    /* renamed from: d */
    public int m4429d() {
        return this.f5819e;
    }

    /* renamed from: e */
    public float m4426e() {
        return this.f5820f;
    }

    /* renamed from: f */
    public boolean m4423f() {
        return this.f5821g;
    }

    /* renamed from: g */
    public String m4421g() {
        return this.f5822h;
    }

    /* renamed from: b */
    public void m4434b(boolean z) {
        this.f5823i = z;
    }

    /* renamed from: f */
    public void m4422f(int i) {
        this.f5826l = i;
    }

    /* renamed from: h */
    public int m4420h() {
        return this.f5826l;
    }

    /* renamed from: i */
    public String m4419i() {
        return this.f5815a;
    }

    /* renamed from: c */
    public void m4430c(String str) {
        this.f5815a = str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f5816b);
        parcel.writeInt(this.f5817c);
        parcel.writeInt(this.f5818d);
        parcel.writeInt(this.f5819e);
        parcel.writeFloat(this.f5820f);
        parcel.writeInt(this.f5821g ? 1 : 0);
        parcel.writeString(this.f5822h);
        parcel.writeInt(this.f5826l);
        parcel.writeString(this.f5815a);
    }
}
