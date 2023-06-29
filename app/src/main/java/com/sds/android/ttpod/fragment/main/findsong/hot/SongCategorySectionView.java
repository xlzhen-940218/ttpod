package com.sds.android.ttpod.fragment.main.findsong.hot;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.cloudapi.ttpod.result.OnlineMusicCategoryResult;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.FindSongGridSectionListAdapter;
import com.sds.android.ttpod.adapter.GridListAdapter;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.fragment.main.MusicLibraryFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.widget.SimpleSongView;

/* loaded from: classes.dex */
public class SongCategorySectionView extends SimpleSongView {
    public SongCategorySectionView(Context context) {
        this(context, null);
    }

    public SongCategorySectionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SongCategorySectionView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.widget.SimpleSongView
    /* renamed from: a */
    public void mo1547a(Context context) {
        super.mo1547a(context);
        int m7229a = DisplayUtils.dp2px(8);
        this.f7928b.setPadding(0, 0, 0, 0);
        ViewGroup.LayoutParams layoutParams = this.f7928b.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) layoutParams).setMargins(0, m7229a, 0, 0);
        }
        this.f7928b.setNumColumns(3);
        this.f7928b.setChildMargin(m7229a);
    }

    @Override // com.sds.android.ttpod.widget.SimpleSongView, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        ThemeManager.m3269a(this.f7927a, ThemeElement.TILE_TEXT);
        ThemeManager.m3269a(this.f7930d, ThemeElement.TILE_TEXT);
        ThemeManager.m3269a(this.f7931e, ThemeElement.TILE_MASK);
    }

    @Override // com.sds.android.ttpod.widget.SimpleSongView
    /* renamed from: a */
    protected void mo1546a(View view, Object obj) {
        view.setOnClickListener(this.f7935i);
    }

    @Override // com.sds.android.ttpod.widget.SimpleSongView
    /* renamed from: a */
    protected int mo1457a() {
        return R.string.music_library;
    }

    @Override // com.sds.android.ttpod.widget.SimpleSongView
    /* renamed from: b */
    protected void mo1456b(View view, Object obj) {
        if (obj instanceof FindSongGridSectionListAdapter.C0963a) {
            FindSongGridSectionListAdapter.C0963a<OnlineMusicCategoryResult.CategoryData> c0963a = (FindSongGridSectionListAdapter.C0963a) obj;
            view.setTag(R.id.view_bind_data, c0963a);
            GridListAdapter.GridViewHolder c0966a = (GridListAdapter.GridViewHolder) view.getTag();
            c0966a.getName().setText(c0963a.m7617c());
            c0966a.getNumber().setText(m5482a(c0963a));
            if (c0963a.m7617c().equals(MusicLibraryFragment.CATEGORY_MV)) {
                c0966a.getNumber().setText(R.string.music_library_mv_title);
            }
            if (!ListViewUtils.m8265a(c0966a.getImageView(), (int) R.drawable.img_music_default_icon)) {
                ThemeManager.m3269a(c0966a.getMask(), ThemeElement.TILE_BACKGROUND);
                ThemeManager.m3269a(c0966a.getName(), ThemeElement.TILE_TEXT);
                ThemeManager.m3269a(c0966a.getNumber(), ThemeElement.TILE_SUB_TEXT);
            }
        }
    }

    @Override // com.sds.android.ttpod.widget.SimpleSongView
    /* renamed from: b */
    protected View mo1541b() {
        View inflate = View.inflate(getContext(), R.layout.find_song_with_num_grid_list_view_item, null);
        inflate.setTag(new GridListAdapter.GridViewHolder(inflate));
        return inflate;
    }

    /* renamed from: a */
    protected String m5482a(FindSongGridSectionListAdapter.C0963a<OnlineMusicCategoryResult.CategoryData> c0963a) {
        return getContext().getString(R.string.count_of_channel, Integer.valueOf(c0963a.m7621a()));
    }
}
