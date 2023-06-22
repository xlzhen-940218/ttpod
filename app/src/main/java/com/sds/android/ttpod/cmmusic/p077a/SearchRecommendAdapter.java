package com.sds.android.ttpod.cmmusic.p077a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sds.android.ttpod.R;

import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.cmmusic.a.d */
/* loaded from: classes.dex */
public class SearchRecommendAdapter extends BaseAdapter {

    /* renamed from: b */
    private static LayoutInflater f3473b = null;

    /* renamed from: a */
    private ArrayList<HashMap<String, String>> f3474a;

    /* renamed from: c */
    private TextView f3475c;

    public SearchRecommendAdapter(ArrayList<HashMap<String, String>> arrayList, Context context) {
        this.f3474a = arrayList;
        f3473b = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.f3474a == null) {
            return 0;
        }
        return this.f3474a.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (this.f3474a == null) {
            return null;
        }
        return this.f3474a.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = f3473b.inflate(R.layout.cmmusic_recommend_search_key_list_row, (ViewGroup) null);
        }
        this.f3475c = (TextView) view.findViewById(R.id.text_recommend_searchkey);
        this.f3475c.setText(this.f3474a.get(i).get("recommendKey"));
        return view;
    }
}
