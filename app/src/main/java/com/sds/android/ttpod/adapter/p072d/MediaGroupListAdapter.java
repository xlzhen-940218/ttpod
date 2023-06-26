package com.sds.android.ttpod.adapter.p072d;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.BaseListAdapter;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.media.mediastore.GroupItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;

/* renamed from: com.sds.android.ttpod.adapter.d.b */
/* loaded from: classes.dex */
public class MediaGroupListAdapter extends BaseListAdapter<GroupItem> {
    @Override // com.sds.android.ttpod.adapter.BaseListAdapter
    /* renamed from: a */
    protected View getConvertView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.media_group_item, (ViewGroup) null);
        inflate.setTag(new C0969a(inflate));
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.adapter.BaseListAdapter
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public final void buildDataUI(View view, GroupItem groupItem, int i) {
        m7578a((C0969a) view.getTag(), groupItem);
        view.setEnabled(getData() == null || !StringUtils.equals(groupItem.getGroupID(), getData().getGroupID()));
    }

    /* renamed from: a */
    protected void m7578a(C0969a c0969a, GroupItem groupItem) {
        boolean startsWith = groupItem.getGroupID().startsWith(MediaStorage.GROUP_ID_FOLDER_PREFIX);
        c0969a.m7574e().setVisibility(View.GONE);
        c0969a.m7576c().setText(startsWith ? FileUtils.m8401k(groupItem.getName()) : groupItem.getName());
        c0969a.m7575d().setText(getContext().getString(R.string.count_of_media, groupItem.getCount()) + (startsWith ? "  " + groupItem.getName() : ""));
        c0969a.m7577b().setVisibility(groupItem.equals(getData()) ? View.VISIBLE : View.GONE);
    }

    /* compiled from: MediaGroupListAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.d.b$a */
    /* loaded from: classes.dex */
    public static class C0969a extends ThemeManager.AbstractC2018a {

        /* renamed from: a */
        private TextView f3236a;

        /* renamed from: b */
        private TextView f3237b;

        /* renamed from: c */
        private IconTextView f3238c;

        /* renamed from: d */
        private View f3239d;

        /* renamed from: e */
        private View f3240e;

        public C0969a(View view) {
            this.f3240e = view;
            this.f3236a = (TextView) view.findViewById(R.id.title_view);
            this.f3237b = (TextView) view.findViewById(R.id.subtitle_view);
            this.f3238c = (IconTextView) view.findViewById(R.id.menu_view);
            this.f3239d = view.findViewById(R.id.view_playstate_group);
        }

        @Override // com.sds.android.ttpod.framework.modules.theme.ThemeManager.AbstractC2018a
        /* renamed from: a */
        public void mo3251a() {
            ThemeManager.m3269a(this.f3236a, ThemeElement.SONG_LIST_ITEM_TEXT);
            ThemeManager.m3269a(this.f3237b, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
            ThemeManager.m3269a(this.f3240e, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        }

        /* renamed from: b */
        public View m7577b() {
            return this.f3239d;
        }

        /* renamed from: c */
        public TextView m7576c() {
            return this.f3236a;
        }

        /* renamed from: d */
        public TextView m7575d() {
            return this.f3237b;
        }

        /* renamed from: e */
        public IconTextView m7574e() {
            return this.f3238c;
        }
    }
}
