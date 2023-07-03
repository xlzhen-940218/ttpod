package com.sds.android.sdk.lib.p060b;

import com.google.gson.reflect.TypeToken;
import com.sds.android.sdk.lib.util.JSONUtils;
import java.util.ArrayList;

/* renamed from: com.sds.android.sdk.lib.b.c */
/* loaded from: classes.dex */
public class DataListResultRest<T> extends BaseResultRest {

    /* renamed from: a */
    public ArrayList<T> getData() {
        // from class: com.sds.android.sdk.lib.b.c.1
        /* renamed from: a */
        ArrayList<T> data = JSONUtils.fromJson(getContent(), new TypeToken<ArrayList<T>>() { // from class: com.sds.android.sdk.lib.b.c.1
        }.getType());
        return data;
    }
}
