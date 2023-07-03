package com.sds.android.sdk.lib.p061c;

import java.util.Map;

/* renamed from: com.sds.android.sdk.lib.c.a */
/* loaded from: classes.dex */
class Pair<K, V> implements Map.Entry<K, V> {

    /* renamed from: a */
    private K key;

    /* renamed from: b */
    private V value;

    public Pair() {
    }

    public Pair(K k, V v) {
        this.key = k;
        this.value = v;
    }

    @Override // java.util.Map.Entry
    public K getKey() {
        return this.key;
    }

    @Override // java.util.Map.Entry
    public V getValue() {
        return this.value;
    }

    @Override // java.util.Map.Entry
    public V setValue(V v) {
        V v2 = this.value;
        this.value = v;
        return v2;
    }
}
