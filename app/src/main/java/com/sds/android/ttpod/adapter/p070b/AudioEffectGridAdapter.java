package com.sds.android.ttpod.adapter.p070b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p085b.AudioEffectItem;
import java.util.List;

/* renamed from: com.sds.android.ttpod.adapter.b.b */
/* loaded from: classes.dex */
public class AudioEffectGridAdapter extends BaseAdapter {

    /* renamed from: a */
    private List<AudioEffectItem> f3206a;

    /* renamed from: b */
    private Context f3207b;

    /* renamed from: c */
    private String f3208c;

    public AudioEffectGridAdapter(Context context, List<AudioEffectItem> list, String str) {
        this.f3207b = context;
        this.f3206a = list;
        this.f3208c = str;
    }

    /* renamed from: a */
    public void m7611a(String str) {
        if (!this.f3208c.equals(str)) {
            this.f3208c = str;
            notifyDataSetChanged();
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f3206a.size();
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
            view = LayoutInflater.from(this.f3207b).inflate(R.layout.audio_effect_gridview_item, (ViewGroup) null, false);
            view.setTag(new C0965a(view));
        }
        m7612a(view, i);
        return view;
    }

    /* renamed from: a */
    public void m7612a(View view, int i) {
        C0965a c0965a = (C0965a) view.getTag();
        AudioEffectItem audioEffectItem = this.f3206a.get(i);
        c0965a.f3210b.setImageResource(audioEffectItem.m6999a());
        if (this.f3208c == null || !this.f3208c.equals(audioEffectItem.m6997c())) {
            c0965a.f3212d.setImageResource(R.drawable.img_audio_effect_reverb_grivd_view_unselector);
        } else {
            c0965a.f3212d.setImageResource(R.drawable.img_audio_effect_reverb_grivd_view_selector);
        }
        c0965a.f3211c.setText(audioEffectItem.m6998b());
        view.setTag(R.id.view_bind_data, audioEffectItem.m6997c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AudioEffectGridAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.b.b$a */
    /* loaded from: classes.dex */
    public class C0965a {

        /* renamed from: b */
        private ImageView f3210b;

        /* renamed from: c */
        private TextView f3211c;

        /* renamed from: d */
        private ImageView f3212d;

        public C0965a(View view) {
            this.f3210b = (ImageView) view.findViewById(R.id.imageview_icon);
            this.f3211c = (TextView) view.findViewById(R.id.textview_title);
            this.f3212d = (ImageView) view.findViewById(R.id.audio_effect_gridview_selector_state);
        }
    }
}
