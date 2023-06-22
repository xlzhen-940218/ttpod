package com.sds.android.ttpod.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.modules.skin.view.Animation;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.utils.ThemeUtils;

/* renamed from: com.sds.android.ttpod.adapter.c */
/* loaded from: classes.dex */
public abstract class GridListAdapter<D> extends BaseListAdapter<D> {
    /* renamed from: b */
    protected abstract String mo5568b(D d);

    /* renamed from: c */
    protected abstract String mo5566c(D d);

    /* renamed from: d */
    protected abstract String mo5565d(D d);

    @Override // com.sds.android.ttpod.adapter.BaseListAdapter
    /* renamed from: a */
    protected View mo5402a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.find_song_with_num_grid_list_view_item, viewGroup, false);
        inflate.setTag(new C0966a(inflate));
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.adapter.BaseListAdapter
    /* renamed from: a */
    public void mo5400a(View view, D d, int i) {
        D d2 = this.f3158d.get(i);
        view.setTag(R.id.view_bind_data, d2);
        C0966a c0966a = (C0966a) view.getTag();
        c0966a.f3213a.setText(mo5568b(d2));
        c0966a.f3214b.setText(mo5566c(d2));
        m7606a(c0966a.f3217e, (D) d2);
        ImageCacheUtils.m4752a(c0966a.f3215c, mo5565d(d2), DisplayUtils.m7225c() / 2, DisplayUtils.m7224d() / 4, (int) R.drawable.img_music_default_icon);
    }

    /* renamed from: a */
    protected void m7606a(Animation animation, D d) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m7607a(C0966a c0966a) {
        ThemeManager.m3269a(c0966a.m7594g(), ThemeElement.TILE_BACKGROUND);
        ThemeUtils.m8178a(c0966a.m7602b(), ThemeElement.TILE_TEXT, ThemeElement.HOME_TEXT);
        ThemeUtils.m8178a(c0966a.m7600c(), ThemeElement.TILE_SUB_TEXT, ThemeElement.HOME_SUB_TEXT);
    }

    /* compiled from: GridListAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.c$a */
    /* loaded from: classes.dex */
    public static class C0966a {

        /* renamed from: a */
        private TextView f3213a;

        /* renamed from: b */
        private TextView f3214b;

        /* renamed from: c */
        private ImageView f3215c;

        /* renamed from: d */
        private ImageView f3216d;

        /* renamed from: e */
        private Animation f3217e;

        /* renamed from: f */
        private View f3218f;

        /* renamed from: g */
        private View f3219g;

        public C0966a(View view) {
            this.f3218f = view;
            this.f3213a = (TextView) view.findViewById(R.id.grid_view_item_name);
            this.f3214b = (TextView) view.findViewById(R.id.grid_view_item_num);
            this.f3215c = (ImageView) view.findViewById(R.id.grid_view_item_image);
            this.f3216d = (ImageView) view.findViewById(R.id.grid_view_item_play_icon_back);
            this.f3217e = (Animation) view.findViewById(R.id.grid_view_item_play_icon);
            this.f3219g = view.findViewById(R.id.grid_view_item_mask);
        }

        /* renamed from: a */
        public View m7604a() {
            return this.f3218f;
        }

        /* renamed from: b */
        public TextView m7602b() {
            return this.f3213a;
        }

        /* renamed from: c */
        public TextView m7600c() {
            return this.f3214b;
        }

        /* renamed from: d */
        public ImageView m7598d() {
            return this.f3215c;
        }

        /* renamed from: e */
        public Animation m7596e() {
            return this.f3217e;
        }

        /* renamed from: f */
        public ImageView m7595f() {
            return this.f3216d;
        }

        /* renamed from: g */
        public View m7594g() {
            return this.f3219g;
        }
    }
}
