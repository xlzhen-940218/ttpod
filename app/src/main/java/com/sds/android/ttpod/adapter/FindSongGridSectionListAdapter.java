package com.sds.android.ttpod.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.utils.ThemeUtils;
import java.util.ArrayList;

/* renamed from: com.sds.android.ttpod.adapter.b */
/* loaded from: classes.dex */
public abstract class FindSongGridSectionListAdapter<D> extends SectionListAdapter {

    /* renamed from: b */
    private ArrayList<C0963a<D>> f3192b = new ArrayList<>();

    /* renamed from: c */
    private View.OnClickListener f3193c = new View.OnClickListener() { // from class: com.sds.android.ttpod.adapter.b.1
        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Object tag = view.getTag(R.id.view_bind_data);
            if (tag != null) {
                FindSongGridSectionListAdapter.this.mo5598c((D) tag);
            }
        }
    };

    /* renamed from: a */
    protected abstract String mo5603a(D d);

    /* renamed from: b */
    protected abstract String mo5600b(D d);

    /* renamed from: c */
    protected abstract void mo5598c(D d);

    /* renamed from: a */
    public void m7623a(ArrayList<C0963a<D>> arrayList) {
        this.f3192b = arrayList;
        notifyDataSetChanged();
    }

    @Override // com.sds.android.ttpod.adapter.SectionListAdapter
    /* renamed from: a */
    protected int mo5528a() {
        return this.f3192b.size();
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        m7572b();
        super.notifyDataSetChanged();
    }

    @Override // com.sds.android.ttpod.adapter.SectionListAdapter
    /* renamed from: a */
    protected int mo5527a(int i) {
        C0963a<D> c0963a;
        ArrayList<D> m7616d;
        if (i >= mo5528a() || (c0963a = this.f3192b.get(i)) == null || (m7616d = c0963a.m7616d()) == null) {
            return 0;
        }
        return (int) Math.ceil(m7616d.size() / 2.0d);
    }

    @Override // com.sds.android.ttpod.adapter.SectionListAdapter
    /* renamed from: a */
    protected D mo5526a(int i, int i2) {
        C0963a<D> c0963a;
        ArrayList<D> m7616d;
        if (i >= mo5528a() || (c0963a = this.f3192b.get(i)) == null || (m7616d = c0963a.m7616d()) == null || i2 >= m7616d.size()) {
            return null;
        }
        return m7616d.get(i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.adapter.SectionListAdapter
    /* renamed from: b */
    public C0963a mo5517c(int i) {
        return this.f3192b.get(i);
    }

    @Override // com.sds.android.ttpod.adapter.SectionListAdapter
    /* renamed from: a */
    protected View getSectionConvertView(ViewGroup viewGroup) {
        return this.layoutInflater.inflate(R.layout.findsong_grid_section_view, viewGroup, false);
    }

    @Override // com.sds.android.ttpod.adapter.SectionListAdapter
    /* renamed from: b */
    protected View getSubConvertView(ViewGroup viewGroup) {
        View inflate = this.layoutInflater.inflate(R.layout.findsong_grid_section_subitem_view, viewGroup, false);
        GridListAdapter.GridViewHolder c0966a = new GridListAdapter.GridViewHolder(inflate.findViewById(R.id.song_item1));
        ThemeUtils.m8178a(c0966a.getMask(), ThemeElement.TILE_BACKGROUND, ThemeElement.HOME_BACKGROUND);
        GridListAdapter.GridViewHolder c0966a2 = new GridListAdapter.GridViewHolder(inflate.findViewById(R.id.song_item2));
        inflate.setTag(new GridListAdapter.GridViewHolder[]{c0966a, c0966a2});
        ThemeUtils.m8178a(c0966a2.getMask(), ThemeElement.TILE_BACKGROUND, ThemeElement.HOME_BACKGROUND);
        return inflate;
    }

    /* renamed from: a */
    private void m7625a(GridListAdapter.GridViewHolder c0966a) {
        ThemeUtils.m8178a(c0966a.getName(), ThemeElement.TILE_TEXT, ThemeElement.HOME_TEXT);
        ThemeUtils.m8178a(c0966a.getNumber(), ThemeElement.TILE_SUB_TEXT, ThemeElement.HOME_SUB_TEXT);
    }

    @Override // com.sds.android.ttpod.adapter.SectionListAdapter
    /* renamed from: a */
    protected void mo5524a(int i, View view) {
        C0963a mo5517c = mo5517c(i);
        TextView textView = (TextView) view.findViewById(R.id.find_song_section_title);
        textView.setText(mo5517c.m7617c());
        ThemeManager.m3269a(textView, ThemeElement.SUB_BAR_TEXT);
        ThemeManager.m3269a(textView, ThemeElement.TILE_MASK);
    }

    @Override // com.sds.android.ttpod.adapter.SectionListAdapter
    /* renamed from: a */
    protected void mo5525a(int i, int i2, View view) {
        D mo5526a = mo5526a(i, i2 * 2);
        D mo5526a2 = mo5526a(i, (i2 * 2) + 1);
        Object tag = view.getTag();
        if (tag != null) {
            GridListAdapter.GridViewHolder[] c0966aArr = (GridListAdapter.GridViewHolder[]) tag;
            m7624a(c0966aArr[0], (D) mo5526a);
            m7624a(c0966aArr[1], (D) mo5526a2);
            m7625a(c0966aArr[0]);
            m7625a(c0966aArr[1]);
        }
    }

    /* renamed from: a */
    protected void m7624a(GridListAdapter.GridViewHolder c0966a, D d) {
        if (d != null) {
            c0966a.getConvertView().setVisibility(View.VISIBLE);
            c0966a.getName().setText(mo5603a((D) d));
            c0966a.getNumber().setText(mo5600b((D) d));
            mo5601b(c0966a, (D) d);
            c0966a.getConvertView().setTag(R.id.view_bind_data, d);
            c0966a.getConvertView().setOnClickListener(this.f3193c);
            return;
        }
        c0966a.getConvertView().setVisibility(View.INVISIBLE);
    }

    /* renamed from: b */
    protected void mo5601b(GridListAdapter.GridViewHolder c0966a, D d) {
    }

    /* compiled from: FindSongGridSectionListAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.b$a */
    /* loaded from: classes.dex */
    public static class C0963a<D> {

        /* renamed from: a */
        private String f3195a;

        /* renamed from: b */
        private int f3196b;

        /* renamed from: c */
        private long f3197c;

        /* renamed from: d */
        private ArrayList<D> f3198d;

        public C0963a(String str, int i, long j, ArrayList<D> arrayList) {
            this.f3195a = str;
            this.f3198d = arrayList;
            this.f3197c = j;
            this.f3196b = i;
        }

        /* renamed from: a */
        public int m7621a() {
            return this.f3196b;
        }

        /* renamed from: b */
        public long m7618b() {
            return this.f3197c;
        }

        /* renamed from: c */
        public String m7617c() {
            return this.f3195a;
        }

        /* renamed from: d */
        public ArrayList<D> m7616d() {
            return this.f3198d;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof C0963a) {
                C0963a c0963a = (C0963a) obj;
                if (this.f3195a != null) {
                    if (this.f3195a.equals(c0963a.f3195a)) {
                        return true;
                    }
                } else if (c0963a.f3195a == null) {
                    return true;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            if (this.f3195a != null) {
                return this.f3195a.hashCode();
            }
            return 0;
        }
    }
}
