package com.sds.android.ttpod.component.p085b;

import android.text.TextUtils;

/* renamed from: com.sds.android.ttpod.component.b.b */
/* loaded from: classes.dex */
public class AudioEffectItem {

    /* renamed from: a */
    private int f3821a;

    /* renamed from: b */
    private String f3822b;

    /* renamed from: c */
    private String f3823c;

    public AudioEffectItem(int i, String str, String str2) {
        this.f3821a = i;
        this.f3822b = str;
        this.f3823c = str2;
        if (TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str)) {
            this.f3823c = str;
        }
    }

    /* renamed from: a */
    public int m6999a() {
        return this.f3821a;
    }

    /* renamed from: b */
    public String m6998b() {
        return this.f3822b;
    }

    /* renamed from: c */
    public String m6997c() {
        return this.f3823c;
    }
}
