package com.sds.android.sdk.lib.p061c;

import java.util.Map;

/* renamed from: com.sds.android.sdk.lib.c.a */
/* loaded from: classes.dex */
class Pair<K, V> implements Map.Entry<K, V> {

    /* renamed from: a */
    private K f2363a;

    /* renamed from: b */
    private V f2364b;

    public Pair() {
    }

    public Pair(K k, V v) {
        this.f2363a = k;
        this.f2364b = v;
    }

    @Override // java.util.Map.Entry
    public K getKey() {
        return this.f2363a;
    }

    @Override // java.util.Map.Entry
    public V getValue() {
        return this.f2364b;
    }

    @Override // java.util.Map.Entry
    public V setValue(V v) {
        V v2 = this.f2364b;
        this.f2364b = v;
        return v2;
    }
}
