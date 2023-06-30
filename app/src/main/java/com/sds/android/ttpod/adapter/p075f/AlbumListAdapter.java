package com.sds.android.ttpod.adapter.p075f;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.AlbumItem;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.p106a.ListUtils;
import com.sds.android.ttpod.utils.ThemeUtils;
import java.util.List;

/* renamed from: com.sds.android.ttpod.adapter.f.a */
/* loaded from: classes.dex */
public class AlbumListAdapter extends BaseAdapter {

    /* renamed from: a */
    private List<AlbumItem> f3386a;

    /* renamed from: b */
    private Context f3387b;

    public AlbumListAdapter(Context context) {
        this.f3387b = context;
    }

    /* renamed from: a */
    public List<AlbumItem> m7425a() {
        return this.f3386a;
    }

    /* renamed from: a */
    public void m7423a(List<AlbumItem> list) {
        this.f3386a = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.f3386a == null) {
            return 0;
        }
        return this.f3386a.size();
    }

    @Override // android.widget.Adapter
    /* renamed from: a */
    public AlbumItem getItem(int i) {
        int count = getCount();
        if (!ListUtils.m4717b(this.f3386a) || i >= count) {
            return null;
        }
        return this.f3386a.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(this.f3387b, R.layout.album_list_item, null);
            view.setTag(new C0999a(view));
        }
        C0999a c0999a = (C0999a) view.getTag();
        AlbumItem albumItem = this.f3386a.get(i);
        ImageCacheUtils.displayImage(c0999a.f3389b, albumItem.getPic200(), DisplayUtils.dp2px(50), DisplayUtils.dp2px(50), (int) R.drawable.img_album_list_item_cover_default);
        c0999a.f3390c.setText((i + 1) + "." + albumItem.getName());
        c0999a.f3391d.setText(albumItem.getSingerName() + " " + albumItem.getPublishTime());
        c0999a.m3250a(ThemeUtils.m8163b());
        return view;
    }

    /* compiled from: AlbumListAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.f.a$a */
    /* loaded from: classes.dex */
    private static class C0999a extends ThemeManager.AbstractC2018a {

        /* renamed from: a */
        private View f3388a;

        /* renamed from: b */
        private ImageView f3389b;

        /* renamed from: c */
        private TextView f3390c;

        /* renamed from: d */
        private TextView f3391d;

        public C0999a(View view) {
            this.f3388a = view;
            this.f3389b = (ImageView) view.findViewById(R.id.image_album_cover);
            this.f3390c = (TextView) view.findViewById(R.id.title_view);
            this.f3391d = (TextView) view.findViewById(R.id.subtitle_view);
        }

        @Override // com.sds.android.ttpod.framework.modules.theme.ThemeManager.AbstractC2018a
        /* renamed from: a */
        protected void mo3251a() {
            ThemeManager.m3269a(this.f3390c, ThemeElement.SONG_LIST_ITEM_TEXT);
            ThemeManager.m3269a(this.f3391d, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
            ThemeManager.m3269a(this.f3388a, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        }
    }
}
