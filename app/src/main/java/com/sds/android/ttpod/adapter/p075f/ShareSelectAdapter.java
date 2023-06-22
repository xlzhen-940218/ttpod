package com.sds.android.ttpod.adapter.p075f;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.share.ShareType;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sds.android.ttpod.adapter.f.b */
/* loaded from: classes.dex */
public class ShareSelectAdapter extends BaseAdapter {

    /* renamed from: a */
    private List<C1000a> f3392a = new ArrayList();

    /* renamed from: b */
    private Context f3393b;

    public ShareSelectAdapter(Context context) {
        this.f3393b = context == null ? BaseApplication.getApplication() : context;
    }

    /* renamed from: a */
    public void m7414a(List<C1000a> list) {
        this.f3392a = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.f3392a == null) {
            return 0;
        }
        return this.f3392a.size();
    }

    @Override // android.widget.Adapter
    /* renamed from: a */
    public C1000a getItem(int i) {
        return this.f3392a.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = view == null ? LayoutInflater.from(this.f3393b).inflate(R.layout.share_select_item, viewGroup, false) : view;
        TextView textView = (TextView) inflate;
        C1000a item = getItem(i);
        textView.setText(item.f3394a);
        textView.setCompoundDrawablesWithIntrinsicBounds(0, item.f3395b, 0, 0);
        return inflate;
    }

    /* compiled from: ShareSelectAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.f.b$a */
    /* loaded from: classes.dex */
    public static class C1000a {

        /* renamed from: a */
        private int f3394a;

        /* renamed from: b */
        private int f3395b;

        /* renamed from: c */
        private ShareType f3396c;

        public C1000a(int i, int i2, ShareType shareType) {
            this.f3394a = i;
            this.f3395b = i2;
            this.f3396c = shareType;
        }

        /* renamed from: a */
        public ShareType m7413a() {
            return this.f3396c;
        }
    }
}
