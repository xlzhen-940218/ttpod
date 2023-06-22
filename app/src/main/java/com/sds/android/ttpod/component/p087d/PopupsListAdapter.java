package com.sds.android.ttpod.component.p087d;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.p085b.ActionItem;
import java.util.List;

/* renamed from: com.sds.android.ttpod.component.d.c */
/* loaded from: classes.dex */
public class PopupsListAdapter<M extends ActionItem> extends BaseAdapter {

    /* renamed from: a */
    private Context f4007a;

    /* renamed from: b */
    private List<M> f4008b;

    public PopupsListAdapter(Context context, List<M> list) {
        this.f4007a = context;
        this.f4008b = list;
    }

    /* renamed from: a */
    public void m6769a(List<M> list) {
        this.f4008b = list;
        notifyDataSetChanged();
    }

    /* renamed from: b */
    public List<M> m6768b() {
        return this.f4008b;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.f4008b == null) {
            return 0;
        }
        return this.f4008b.size();
    }

    @Override // android.widget.Adapter
    /* renamed from: a */
    public M getItem(int i) {
        if (this.f4008b == null) {
            return null;
        }
        return this.f4008b.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    /* renamed from: a */
    protected int mo6685a() {
        return R.layout.popups_list_item;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        if (view == null) {
            view2 = View.inflate(this.f4007a, mo6685a(), null);
            C1171a c1171a = new C1171a((ViewGroup) view2);
            view2.setTag(c1171a);
            mo6770a(c1171a);
        } else {
            view2 = view;
        }
        m6771a(i, view2);
        return view2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo6770a(C1171a c1171a) {
    }

    /* renamed from: a */
    private void m6771a(int i, View view) {
        C1171a c1171a = (C1171a) view.getTag();
        if (this.f4008b != null) {
            M m = this.f4008b.get(i);
            c1171a.m6765c().setText(m.m7006d());
            if (TextUtils.isEmpty(m.m7003g())) {
                c1171a.m6764d().setVisibility(View.GONE);
            } else {
                c1171a.m6764d().setText(m.m7003g());
            }
            mo6684a(c1171a, m);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo6684a(C1171a c1171a, M m) {
        if (c1171a.m6766b() != null || c1171a.m6763e() != null) {
            int m7001i = m.m7001i();
            if (ActionItem.EnumC1134a.TITLE_ICON == m.m7000j()) {
                c1171a.m6762f().setText(m7001i);
                if (R.string.icon_text_wusun == m7001i) {
                    c1171a.m6762f().setTextColor(-2185667);
                } else if (R.string.icon_text_gaozhi == m7001i) {
                    c1171a.m6762f().setTextColor(-8665764);
                }
                c1171a.m6766b().setVisibility(View.GONE);
                c1171a.m6763e().setVisibility(View.GONE);
                c1171a.m6762f().setVisibility(View.VISIBLE);
            } else if (m.getIcon() == null || ActionItem.EnumC1134a.NO_ICON == m.m7000j()) {
                c1171a.m6766b().setVisibility(View.GONE);
                c1171a.m6763e().setVisibility(View.GONE);
                c1171a.m6762f().setVisibility(View.GONE);
            } else if (ActionItem.EnumC1134a.LEFT_ICON == m.m7000j()) {
                c1171a.m6766b().setImageDrawable(m.getIcon());
                c1171a.m6766b().setVisibility(View.VISIBLE);
                c1171a.m6763e().setVisibility(View.GONE);
                c1171a.m6762f().setVisibility(View.GONE);
            } else if (ActionItem.EnumC1134a.RIGHT_ICON == m.m7000j()) {
                c1171a.m6763e().setImageDrawable(m.getIcon());
                c1171a.m6766b().setVisibility(View.GONE);
                c1171a.m6763e().setVisibility(View.VISIBLE);
                c1171a.m6762f().setVisibility(View.GONE);
            }
        }
    }

    /* compiled from: PopupsListAdapter.java */
    /* renamed from: com.sds.android.ttpod.component.d.c$a */
    /* loaded from: classes.dex */
    public static class C1171a {

        /* renamed from: a */
        private ImageView f4009a;

        /* renamed from: b */
        private ImageView f4010b;

        /* renamed from: c */
        private TextView f4011c;

        /* renamed from: d */
        private TextView f4012d;

        /* renamed from: e */
        private IconTextView f4013e;

        /* renamed from: f */
        private TextView f4014f;

        /* renamed from: g */
        private TextView f4015g;

        /* renamed from: h */
        private ImageView f4016h;

        public C1171a(ViewGroup viewGroup) {
            this.f4009a = (ImageView) viewGroup.findViewById(R.id.icon_left);
            this.f4010b = (ImageView) viewGroup.findViewById(R.id.icon_right);
            this.f4011c = (TextView) viewGroup.findViewById(R.id.title);
            this.f4012d = (TextView) viewGroup.findViewById(R.id.title_description);
            this.f4014f = (TextView) viewGroup.findViewById(R.id.subtitle);
            this.f4013e = (IconTextView) viewGroup.findViewById(R.id.title_icon);
            this.f4015g = (TextView) viewGroup.findViewById(R.id.tv_action);
            this.f4016h = (ImageView) viewGroup.findViewById(R.id.item_click_arrow);
        }

        /* renamed from: a */
        public TextView m6767a() {
            return this.f4015g;
        }

        /* renamed from: b */
        public ImageView m6766b() {
            return this.f4009a;
        }

        /* renamed from: c */
        public TextView m6765c() {
            return this.f4011c;
        }

        /* renamed from: d */
        public TextView m6764d() {
            return this.f4014f;
        }

        /* renamed from: e */
        public ImageView m6763e() {
            return this.f4010b;
        }

        /* renamed from: f */
        public IconTextView m6762f() {
            return this.f4013e;
        }
    }
}
