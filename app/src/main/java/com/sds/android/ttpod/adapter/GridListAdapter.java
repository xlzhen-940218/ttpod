package com.sds.android.ttpod.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.modules.skin.view.AnimationImageView;
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
    protected View getConvertView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.find_song_with_num_grid_list_view_item, viewGroup, false);
        inflate.setTag(new GridViewHolder(inflate));
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.adapter.BaseListAdapter
    /* renamed from: a */
    public void buildDataUI(View view, D d, int i) {
        D d2 = this.dataList.get(i);
        view.setTag(R.id.view_bind_data, d2);
        GridViewHolder gridViewHolder = (GridViewHolder) view.getTag();
        gridViewHolder.name.setText(mo5568b(d2));
        gridViewHolder.number.setText(mo5566c(d2));
        m7606a(gridViewHolder.playIcon, (D) d2);
        ImageCacheUtils.displayImage(gridViewHolder.image, mo5565d(d2), DisplayUtils.getWidth() / 2, DisplayUtils.getHeight() / 4, (int) R.drawable.img_music_default_icon);
    }

    /* renamed from: a */
    protected void m7606a(AnimationImageView animation, D d) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m7607a(GridViewHolder c0966a) {
        ThemeManager.m3269a(c0966a.getMask(), ThemeElement.TILE_BACKGROUND);
        ThemeUtils.m8178a(c0966a.getName(), ThemeElement.TILE_TEXT, ThemeElement.HOME_TEXT);
        ThemeUtils.m8178a(c0966a.getNumber(), ThemeElement.TILE_SUB_TEXT, ThemeElement.HOME_SUB_TEXT);
    }

    /* compiled from: GridListAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.c$a */
    /* loaded from: classes.dex */
    public static class GridViewHolder {

        /* renamed from: a */
        private TextView name;

        /* renamed from: b */
        private TextView number;

        /* renamed from: c */
        private ImageView image;

        /* renamed from: d */
        private ImageView playIconBack;

        /* renamed from: e */
        private AnimationImageView playIcon;

        /* renamed from: f */
        private View convertView;

        /* renamed from: g */
        private View mask;

        public GridViewHolder(View view) {
            this.convertView = view;
            this.name = (TextView) view.findViewById(R.id.grid_view_item_name);
            this.number = (TextView) view.findViewById(R.id.grid_view_item_num);
            this.image = (ImageView) view.findViewById(R.id.grid_view_item_image);
            this.playIconBack = (ImageView) view.findViewById(R.id.grid_view_item_play_icon_back);
            this.playIcon = (AnimationImageView) view.findViewById(R.id.grid_view_item_play_icon);
            this.mask = view.findViewById(R.id.grid_view_item_mask);
        }

        /* renamed from: a */
        public View getConvertView() {
            return this.convertView;
        }

        /* renamed from: b */
        public TextView getName() {
            return this.name;
        }

        /* renamed from: c */
        public TextView getNumber() {
            return this.number;
        }

        /* renamed from: d */
        public ImageView getImageView() {
            return this.image;
        }

        /* renamed from: e */
        public AnimationImageView getPlayIcon() {
            return this.playIcon;
        }

        /* renamed from: f */
        public ImageView getPlayIconBack() {
            return this.playIconBack;
        }

        /* renamed from: g */
        public View getMask() {
            return this.mask;
        }
    }
}
