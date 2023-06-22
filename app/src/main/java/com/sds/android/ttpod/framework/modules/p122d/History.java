package com.sds.android.ttpod.framework.modules.p122d;

import android.text.TextUtils;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sds.android.ttpod.framework.modules.d.a */
/* loaded from: classes.dex */
public class History<D> {

    /* renamed from: a */
    private List<D> f6098a;

    /* renamed from: b */
    private OnLoadListener<List<D>> f6099b;

    /* renamed from: c */
    private String f6100c;

    /* renamed from: d */
    private int f6101d;

    public History(String str, int i, OnLoadListener<List<D>> onLoadListener) {
        this.f6098a = new ArrayList();
        this.f6099b = onLoadListener;
        this.f6100c = str;
        this.f6101d = i;
        if (TextUtils.isEmpty(str) || i <= 0) {
            throw new IllegalArgumentException("storePath must be valid, maxSize must be big than 0");
        }
        TaskScheduler.m8582a(new TaskScheduler.AbstractAsyncTaskC0595a<String, List<D>>(this.f6100c) { // from class: com.sds.android.ttpod.framework.modules.d.a.1


            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public List<D> mo1981a(String str2) {
                return History.this.m4094a(str2);
            }

            @Override
            protected void postExecute(List<D> ds) {
                m4087a(ds);
            }

            /* renamed from: a */
            protected void m4087a(List<D> list) {
                History.this.f6098a = list;
                if (History.this.f6099b != null) {
                    History.this.f6099b.mo4086a(History.this.f6098a);
                }
            }
        });
    }

    public History(String str, int i) {
        this.f6098a = new ArrayList();
        this.f6100c = str;
        this.f6101d = i;
        this.f6098a = m4094a(this.f6100c);
    }

    /* renamed from: a */
    public void m4095a(D d) {
        if (this.f6098a.contains(d)) {
            this.f6098a.remove(d);
        }
        this.f6098a.add(0, d);
        m4099a(this.f6101d);
        m4090b(this.f6100c);
    }

    /* renamed from: b */
    public void m4091b(D d) {
        if (this.f6098a.contains(d)) {
            this.f6098a.remove(d);
            m4090b(this.f6100c);
        }
    }

    /* renamed from: a */
    public void m4100a() {
        this.f6098a.clear();
        m4090b(this.f6100c);
    }

    /* renamed from: b */
    public List<D> m4093b() {
        return this.f6098a;
    }

    /* renamed from: c */
    public int m4089c() {
        return this.f6098a.size();
    }

    /* renamed from: a */
    private void m4099a(int i) {
        int size = this.f6098a.size();
        if (size > i) {
            for (int i2 = size - 1; i2 >= i; i2--) {
                this.f6098a.remove(i2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public List<D> m4094a(String str) {
        return Cache.m3218a().m3192b(str);
    }

    /* renamed from: b */
    private void m4090b(String str) {
        Cache.m3218a().m3204a(str, this.f6098a);
    }
}
