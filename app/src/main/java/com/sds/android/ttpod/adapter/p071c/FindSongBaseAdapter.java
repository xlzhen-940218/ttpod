package com.sds.android.ttpod.adapter.p071c;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import com.sds.android.cloudapi.ttpod.data.RecommendData;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sds.android.ttpod.adapter.c.a */
/* loaded from: classes.dex */
public abstract class FindSongBaseAdapter extends BaseAdapter {

    /* renamed from: a */
    private LayoutInflater f3220a;

    /* renamed from: b */
    private List<RecommendData> f3221b = new ArrayList();

    public FindSongBaseAdapter(Context context, List<? extends RecommendData> list) {
        this.f3221b.addAll(list);
        this.f3220a = LayoutInflater.from(context);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f3221b.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.f3221b.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    /* renamed from: a */
    public LayoutInflater m7605a() {
        return this.f3220a;
    }
}
