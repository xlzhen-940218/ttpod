package com.sds.android.sdk.lib.p060b;

import com.google.gson.reflect.TypeToken;
import com.sds.android.sdk.lib.util.JSONUtils;
import java.util.ArrayList;

/* renamed from: com.sds.android.sdk.lib.b.c */
/* loaded from: classes.dex */
public class DataListResultRest<T> extends BaseResultRest {

    /* renamed from: a */
    private ArrayList<T> f2350a = new ArrayList<>();

    /* renamed from: a */
    public ArrayList<T> mo8674a() {
        this.f2350a = (ArrayList) JSONUtils.fromJson(m8676e(), new TypeToken<ArrayList<T>>() { // from class: com.sds.android.sdk.lib.b.c.1
        }.getType());
        return this.f2350a;
    }
}
