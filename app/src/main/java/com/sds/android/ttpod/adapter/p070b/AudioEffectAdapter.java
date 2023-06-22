package com.sds.android.ttpod.adapter.p070b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import java.util.List;

/* renamed from: com.sds.android.ttpod.adapter.b.a */
/* loaded from: classes.dex */
public class AudioEffectAdapter extends BaseAdapter {

    /* renamed from: a */
    private List<String> f3199a;

    /* renamed from: b */
    private Context f3200b;

    /* renamed from: c */
    private String f3201c;

    public AudioEffectAdapter(Context context, List<String> list, String str) {
        this.f3199a = list;
        this.f3200b = context;
        this.f3201c = str;
    }

    /* renamed from: a */
    public void m7619a(String str) {
        if (!this.f3201c.equals(str)) {
            this.f3201c = str;
            notifyDataSetChanged();
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f3199a.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.f3200b).inflate(R.layout.audio_effect_listview_item, (ViewGroup) null, false);
            view.setTag(new C0964a(view));
        }
        m7620a(view, i);
        return view;
    }

    /* renamed from: a */
    private void m7620a(View view, int i) {
        C0964a c0964a = (C0964a) view.getTag();
        String str = this.f3199a.get(i);
        boolean equals = this.f3201c.equals(str);
        c0964a.f3203b.setText(str);
        c0964a.f3204c.setVisibility(equals ? View.VISIBLE : View.GONE);
        view.setEnabled(equals);
        c0964a.f3203b.setEnabled(equals);
        c0964a.f3205d.setVisibility(equals ? View.VISIBLE : View.GONE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AudioEffectAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.b.a$a */
    /* loaded from: classes.dex */
    public class C0964a {

        /* renamed from: b */
        private TextView f3203b;

        /* renamed from: c */
        private ImageView f3204c;

        /* renamed from: d */
        private View f3205d;

        public C0964a(View view) {
            this.f3203b = (TextView) view.findViewById(R.id.textview_title);
            this.f3204c = (ImageView) view.findViewById(R.id.imageview_state);
            this.f3205d = view.findViewById(R.id.view_state_effect);
        }
    }
}
