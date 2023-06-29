package com.sds.android.ttpod.fragment.main.findsong;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.RecommendData;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.p071c.FindSongBaseAdapter;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.fragment.main.FindSongBaseViewFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.utils.FindSongConfig;
import com.sds.android.ttpod.utils.ThemeUtils;
import java.util.List;

/* loaded from: classes.dex */
public class FindSongListViewFragment extends FindSongBaseViewFragment implements ThemeManager.InterfaceC2019b {
    private C1519a mFindSongListAdapter;
    private ViewGroup mFindSongListViewContainer;
    private ListView mListView;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mFindSongListViewContainer == null) {
            int m7229a = DisplayUtils.dp2px(8);
            this.mFindSongListViewContainer = (ViewGroup) layoutInflater.inflate(R.layout.find_song_title_bar, (ViewGroup) null);
            this.mFindSongListViewContainer.removeAllViews();
            this.mFindSongListViewContainer.setPadding(m7229a, 0, m7229a, m7229a);
            this.mFindSongListViewContainer.addView(createTitleBarView(layoutInflater));
            this.mFindSongListViewContainer.addView(createListView());
        }
        return this.mFindSongListViewContainer;
    }

    private ListView createListView() {
        this.mListView = new ListView(getActivity());
        this.mListView.addHeaderView(new View(getActivity()));
        this.mListView.setLayoutParams(new LinearLayout.LayoutParams(-1, FindSongConfig.C0627b.m8273a(getModuleData().getDataList().size())));
        this.mListView.setDivider(new ColorDrawable(0));
        this.mListView.setDividerHeight(DisplayUtils.dp2px(1));
        this.mListView.setHeaderDividersEnabled(false);
        this.mListView.setFooterDividersEnabled(false);
        this.mListView.setFadingEdgeLength(0);
        this.mFindSongListAdapter = new C1519a(getActivity(), getModuleData().getDataList());
        this.mListView.setAdapter((ListAdapter) this.mFindSongListAdapter);
        return this.mListView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.main.findsong.FindSongListViewFragment$a */
    /* loaded from: classes.dex */
    public class C1519a extends FindSongBaseAdapter {
        public C1519a(Context context, List<? extends RecommendData> list) {
            super(context, list);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: com.sds.android.ttpod.fragment.main.findsong.FindSongListViewFragment$a$a */
        /* loaded from: classes.dex */
        public class C1520a {

            /* renamed from: b */
            private ImageView f5153b;

            /* renamed from: c */
            private TextView f5154c;

            /* renamed from: d */
            private TextView f5155d;

            /* renamed from: e */
            private View f5156e;

            /* renamed from: f */
            private View f5157f;

            /* renamed from: g */
            private IconTextView f5158g;

            public C1520a(View view) {
                this.f5153b = (ImageView) view.findViewById(R.id.id_list_view_item_image);
                this.f5154c = (TextView) view.findViewById(R.id.id_list_view_item_name);
                this.f5155d = (TextView) view.findViewById(R.id.id_list_view_item_desc);
                this.f5156e = view.findViewById(R.id.id_click_view);
                this.f5157f = view;
                this.f5158g = (IconTextView) view.findViewById(R.id.id_img_arrow);
            }
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = m7605a().inflate(R.layout.find_song_list_view_item, (ViewGroup) null);
                view.setTag(new C1520a(view));
            }
            m5647a((C1520a) view.getTag(), i);
            m5648a((C1520a) view.getTag());
            return view;
        }

        /* renamed from: a */
        private void m5647a(C1520a c1520a, int i) {
            int m7229a = DisplayUtils.dp2px(75);
            ImageCacheUtils.m4752a(c1520a.f5153b, FindSongListViewFragment.this.getItemData(i).getPicUrl(), m7229a, m7229a, (int) R.drawable.img_music_default_icon);
            c1520a.f5154c.setText(FindSongListViewFragment.this.getItemData(i).getName());
            c1520a.f5155d.setText(FindSongListViewFragment.this.getItemData(i).getDesc());
            c1520a.f5157f.setOnClickListener(FindSongListViewFragment.this.createItemOnClickListener(i));
        }

        /* renamed from: a */
        private void m5648a(C1520a c1520a) {
            ThemeManager.m3269a(c1520a.f5155d, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
            ThemeManager.m3269a(c1520a.f5154c, ThemeElement.SONG_LIST_ITEM_TEXT);
            ThemeManager.m3269a(c1520a.f5156e, ThemeElement.TILE_BACKGROUND);
            ThemeManager.m3269a(c1520a.f5157f, ThemeElement.TILE_BACKGROUND);
            ThemeUtils.m8173a(c1520a.f5158g, ThemeElement.TILE_SUB_TEXT);
            m5646b();
        }

        /* renamed from: b */
        private void m5646b() {
            if (SDKVersionUtils.sdkThan11()) {
                FindSongListViewFragment.this.mListView.setDivider(m5649a(ThemeManager.m3265a(ThemeElement.COMMON_SEPARATOR), DisplayUtils.dp2px(85), 0));
                FindSongListViewFragment.this.mListView.setDividerHeight(1);
                FindSongListViewFragment.this.mListView.setHeaderDividersEnabled(true);
                FindSongListViewFragment.this.mListView.setFooterDividersEnabled(true);
            }
        }

        /* renamed from: a */
        private InsetDrawable m5649a(Drawable drawable, int i, int i2) {
            if (drawable != null) {
                return new InsetDrawable(drawable, i, 0, i2, 0);
            }
            return null;
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (isViewAccessAble() && this.mThemeReload) {
            setTitleBarTheme();
            this.mFindSongListAdapter.notifyDataSetChanged();
            this.mThemeReload = false;
        }
    }
}
