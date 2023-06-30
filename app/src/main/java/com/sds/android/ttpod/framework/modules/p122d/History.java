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
    private List<D> dataList;

    /* renamed from: b */
    private OnLoadListener<List<D>> onLoadListener;

    /* renamed from: c */
    private String historyName;

    /* renamed from: d */
    private int loadCount;

    public History(String historyName, int loadCount, OnLoadListener<List<D>> onLoadListener) {
        this.dataList = new ArrayList();
        this.onLoadListener = onLoadListener;
        this.historyName = historyName;
        this.loadCount = loadCount;
        if (TextUtils.isEmpty(historyName) || loadCount <= 0) {
            throw new IllegalArgumentException("storePath must be valid, maxSize must be big than 0");
        }
        TaskScheduler.m8582a(new TaskScheduler.SchedulerAsyncTask<String, List<D>>(this.historyName) { // from class: com.sds.android.ttpod.framework.modules.d.a.1


            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public List<D> inBackground(String str2) {
                return History.this.m4094a(str2);
            }

            @Override
            protected void postExecute(List<D> ds) {
                m4087a(ds);
            }

            /* renamed from: a */
            protected void m4087a(List<D> list) {
                History.this.dataList = list;
                if (History.this.onLoadListener != null) {
                    History.this.onLoadListener.loaded(History.this.dataList);
                }
            }
        });
    }

    public History(String str, int i) {
        this.dataList = new ArrayList();
        this.historyName = str;
        this.loadCount = i;
        this.dataList = m4094a(this.historyName);
    }

    /* renamed from: a */
    public void m4095a(D d) {
        if (this.dataList.contains(d)) {
            this.dataList.remove(d);
        }
        this.dataList.add(0, d);
        m4099a(this.loadCount);
        m4090b(this.historyName);
    }

    /* renamed from: b */
    public void m4091b(D d) {
        if (this.dataList.contains(d)) {
            this.dataList.remove(d);
            m4090b(this.historyName);
        }
    }

    /* renamed from: a */
    public void m4100a() {
        this.dataList.clear();
        m4090b(this.historyName);
    }

    /* renamed from: b */
    public List<D> m4093b() {
        return this.dataList;
    }

    /* renamed from: c */
    public int m4089c() {
        return this.dataList.size();
    }

    /* renamed from: a */
    private void m4099a(int i) {
        int size = this.dataList.size();
        if (size > i) {
            for (int i2 = size - 1; i2 >= i; i2--) {
                this.dataList.remove(i2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public List<D> m4094a(String str) {
        return Cache.getInstance().getHistoryPrefixDataList(str);
    }

    /* renamed from: b */
    private void m4090b(String str) {
        Cache.getInstance().m3204a(str, this.dataList);
    }
}
