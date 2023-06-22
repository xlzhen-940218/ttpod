package com.sds.android.ttpod.fragment.skinmanager;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.ttpod.R;

/* renamed from: com.sds.android.ttpod.fragment.skinmanager.a */
/* loaded from: classes.dex */
public class CategoryViewHolder {

    /* renamed from: a */
    private ImageView f5517a;

    /* renamed from: b */
    private TextView f5518b;

    public CategoryViewHolder(View view) {
        this.f5517a = (ImageView) view.findViewById(R.id.imageview_thumb);
        this.f5518b = (TextView) view.findViewById(R.id.category_name);
    }

    /* renamed from: a */
    public ImageView m5392a() {
        return this.f5517a;
    }

    /* renamed from: b */
    public TextView m5391b() {
        return this.f5518b;
    }
}
