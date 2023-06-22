package com.sds.android.ttpod.widget.wheelview;

import android.view.View;
import android.widget.LinearLayout;
import java.util.LinkedList;
import java.util.List;

/* renamed from: com.sds.android.ttpod.widget.wheelview.e */
/* loaded from: classes.dex */
public class WheelRecycle {

    /* renamed from: a */
    private List<View> f8432a;

    /* renamed from: b */
    private List<View> f8433b;

    /* renamed from: c */
    private WheelView f8434c;

    public WheelRecycle(WheelView wheelView) {
        this.f8434c = wheelView;
    }

    /* renamed from: a */
    public int m1156a(LinearLayout linearLayout, int i, ItemsRange itemsRange) {
        int i2 = 0;
        int i3 = i;
        while (i2 < linearLayout.getChildCount()) {
            if (!itemsRange.m1171a(i)) {
                m1158a(linearLayout.getChildAt(i2), i);
                linearLayout.removeViewAt(i2);
                if (i2 == 0) {
                    i3++;
                }
            } else {
                i2++;
            }
            i++;
        }
        return i3;
    }

    /* renamed from: a */
    public View m1159a() {
        return m1155a(this.f8432a);
    }

    /* renamed from: b */
    public View m1154b() {
        return m1155a(this.f8433b);
    }

    /* renamed from: c */
    public void m1153c() {
        if (this.f8432a != null) {
            this.f8432a.clear();
        }
        if (this.f8433b != null) {
            this.f8433b.clear();
        }
    }

    /* renamed from: a */
    private List<View> m1157a(View view, List<View> list) {
        if (list == null) {
            list = new LinkedList<>();
        }
        list.add(view);
        return list;
    }

    /* renamed from: a */
    private void m1158a(View view, int i) {
        int m1168a = this.f8434c.getViewAdapter().m1168a();
        if ((i < 0 || i >= m1168a) && !this.f8434c.m1184c()) {
            this.f8433b = m1157a(view, this.f8433b);
            return;
        }
        while (i < 0) {
            i += m1168a;
        }
        int i2 = i % m1168a;
        this.f8432a = m1157a(view, this.f8432a);
    }

    /* renamed from: a */
    private View m1155a(List<View> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        View view = list.get(0);
        list.remove(0);
        return view;
    }
}
