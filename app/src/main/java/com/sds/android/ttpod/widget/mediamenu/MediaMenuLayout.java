package com.sds.android.ttpod.widget.mediamenu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.widget.IconTextView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public abstract class MediaMenuLayout extends LinearLayout {

    /* renamed from: a */
    private SparseArray<View> f8381a;

    /* renamed from: b */
    private SparseArray<View> f8382b;

    /* renamed from: c */
    private List<MenuItem> f8383c;

    /* renamed from: a */
    public abstract List<MenuItem> mo1226a();

    public MediaMenuLayout(Context context) {
        super(context);
        this.f8381a = new SparseArray<>(5);
        this.f8382b = new SparseArray<>();
        m1229a(context);
    }

    public MediaMenuLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8381a = new SparseArray<>(5);
        this.f8382b = new SparseArray<>();
        m1229a(context);
    }

    /* renamed from: a */
    private void m1229a(Context context) {
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(17);
        m1228a(mo1226a());
    }

    /* renamed from: a */
    private void m1228a(List<MenuItem> list) {
        int size = list == null ? 0 : list.size();
        this.f8383c = new ArrayList();
        if (size > 0) {
            this.f8383c.addAll(list);
            m1227b();
        }
    }

    /* renamed from: b */
    private void m1227b() {
        for (MenuItem menuItem : this.f8383c) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_media_menu_item, (ViewGroup) this, false);
            IconTextView iconTextView = (IconTextView) inflate.findViewById(R.id.itv_media_menu_icon);
            TextView textView = (TextView) inflate.findViewById(R.id.tv_media_menu_text);
            if (menuItem.m1223c() > 0) {
                iconTextView.setCheckable(true);
                iconTextView.m7217a(menuItem.m1224b(), menuItem.m1223c());
            } else {
                iconTextView.setText(menuItem.m1224b());
            }
            iconTextView.setTextColor(Color.parseColor("#FFFFFF"));
            textView.setText(menuItem.m1222d());
            textView.setId(menuItem.m1225a());
            if (menuItem.m1225a() == R.id.media_menu_favor) {
                iconTextView.setId(R.id.media_menu_favor_icon);
                this.f8381a.put(R.id.media_menu_favor_icon, iconTextView);
            }
            addView(inflate);
            if (getChildCount() > 5) {
                inflate.setVisibility(View.GONE);
            }
            this.f8381a.put(menuItem.m1225a(), inflate);
            this.f8382b.put(menuItem.m1225a(), textView);
        }
    }

    /* renamed from: a */
    @SuppressLint("WrongConstant")
    public void m1230a(int i, boolean z) {
        int i2 = z ? 0 : 8;
        View view = this.f8381a.get(i);
        if (view != null && view.getVisibility() != i2) {
            view.setVisibility(i2);
        }
    }

    /* renamed from: a */
    public View m1232a(int i) {
        return this.f8381a.get(i);
    }

    public void setMenuItemClickListener(View.OnClickListener onClickListener) {
        for (int i = 0; i < this.f8382b.size(); i++) {
            m1231a(this.f8382b.keyAt(i), onClickListener);
        }
    }

    /* renamed from: a */
    public void m1231a(int i, View.OnClickListener onClickListener) {
        View view = this.f8382b.get(i);
        if (view != null) {
            view.setOnClickListener(onClickListener);
        }
    }
}
