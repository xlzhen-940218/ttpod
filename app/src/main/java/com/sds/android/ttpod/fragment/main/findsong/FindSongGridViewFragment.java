package com.sds.android.ttpod.fragment.main.findsong;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.FindSongHotListData;
import com.sds.android.cloudapi.ttpod.data.RecommendData;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.ItemsAdapter;
import com.sds.android.ttpod.adapter.p071c.FindSongBaseAdapter;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.fragment.main.FindSongBaseViewFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.utils.FindSongConfig;
import com.sds.android.ttpod.widget.FoldableListLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/* loaded from: classes.dex */
public class FindSongGridViewFragment extends FindSongBaseViewFragment {
    private static final int FORWARD_TYPE_XIA_MI = 13;
    private int mDescTextLines = 1;
    protected C1516a mFindSongGridAdapter;
    private ViewGroup mFindSongGridViewContainer;

    /* JADX INFO: Access modifiers changed from: private */
    public int getDescTextLines() {
        return this.mDescTextLines;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDescTextLines(int i) {
        this.mDescTextLines = i;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mFindSongGridViewContainer == null) {
            this.mFindSongGridViewContainer = (ViewGroup) layoutInflater.inflate(R.layout.layout_null_linear_layout, (ViewGroup) null);
            addViewToContainer(createTitleBarView(layoutInflater), createGridView());
        }
        return this.mFindSongGridViewContainer;
    }

    private void addViewToContainer(View view, View view2) {
        int m7229a = DisplayUtils.m7229a(8);
        if (getModuleDataType().equals("song_list")) {
            this.mFindSongGridViewContainer.addView(view);
            this.mFindSongGridViewContainer.setPadding(m7229a, 0, m7229a, 0);
        } else {
            this.mFindSongGridViewContainer.setPadding(m7229a, m7229a, m7229a, 0);
        }
        this.mFindSongGridViewContainer.addView(view2);
    }

    private GridView createGridView() {
        GridView gridView = new GridView(getActivity());
        int m8275c = FindSongConfig.C0626a.m8275c(getModuleData().getStyle());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, FindSongConfig.C0626a.m8277a(getModuleData().getDataList().size(), m8275c, getModuleDataType(), getModuleData().getDataList()));
        gridView.setNumColumns(m8275c);
        gridView.setSelector(new ColorDrawable(0));
        gridView.setVerticalSpacing(DisplayUtils.m7229a(0));
        gridView.setHorizontalSpacing(DisplayUtils.m7229a(8));
        gridView.setStretchMode(2);
        gridView.setLayoutParams(layoutParams);
        gridView.setGravity(17);
        this.mFindSongGridAdapter = new C1516a(getActivity(), getModuleData().getDataList(), m8275c);
        gridView.setAdapter((ListAdapter) this.mFindSongGridAdapter);
        return gridView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.main.findsong.FindSongGridViewFragment$a */
    /* loaded from: classes.dex */
    public class C1516a extends FindSongBaseAdapter {

        /* renamed from: b */
        private int f5143b;

        /* renamed from: c */
        private final int f5144c;

        public C1516a(Context context, ArrayList<? extends RecommendData> arrayList, int i) {
            super(context, arrayList);
            this.f5144c = i;
        }

        @Override // android.widget.BaseAdapter
        public void notifyDataSetChanged() {
            this.f5143b = -1;
            super.notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate;
            if (FindSongGridViewFragment.this.getItemData(i).getForwardAction().getType() == 13) {
                inflate = m7605a().inflate(R.layout.find_song_grid_view_item, (ViewGroup) null);
            } else {
                inflate = m7605a().inflate(R.layout.fing_song_grid_view_normal_item, (ViewGroup) null);
            }
            if (!m5663a(i) || view == null) {
                m5661b(i, inflate);
                m5662a(i, inflate);
                m5660c(i, inflate);
                m5658e(i, inflate);
                m5659d(i, inflate);
                ThemeManager.m3269a(inflate.findViewById(R.id.id_grid_view_item_name), ThemeElement.SONG_LIST_ITEM_TEXT);
                return inflate;
            }
            return view;
        }

        /* renamed from: a */
        private void m5662a(int i, View view) {
            if (FindSongGridViewFragment.this.isSongListItemInSongListModule(i)) {
                TextView textView = (TextView) view.findViewById(R.id.id_grid_view_item_author_name);
                textView.setText(((FindSongHotListData) FindSongGridViewFragment.this.getItemData(i)).getAuthor());
                float m7229a = DisplayUtils.m7229a(11) - (this.f5144c * 1.0f);
                textView.setTextSize(0, m7229a);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) m7229a, (int) m7229a);
                layoutParams.addRule(15);
                layoutParams.rightMargin = DisplayUtils.m7229a(3);
                layoutParams.leftMargin = DisplayUtils.m7229a(5);
                view.findViewById(R.id.id_author_icon).setLayoutParams(layoutParams);
                return;
            }
            view.findViewById(R.id.id_author_layout).setVisibility(View.GONE);
        }

        /* renamed from: b */
        private void m5661b(int i, View view) {
            if (FindSongGridViewFragment.this.isSongListItemInSongListModule(i)) {
                TextView textView = (TextView) view.findViewById(R.id.id_grid_view_item_listen_count);
                textView.setText(String.valueOf(((FindSongHotListData) FindSongGridViewFragment.this.getItemData(i)).getListenCount()));
                float m7229a = DisplayUtils.m7229a(10) - (this.f5144c * 1.0f);
                textView.setTextSize(0, m7229a);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(((int) m7229a) + 1, ((int) m7229a) + 1);
                layoutParams.addRule(15);
                layoutParams.rightMargin = DisplayUtils.m7229a(3);
                view.findViewById(R.id.id_listen_count_icon).setLayoutParams(layoutParams);
                return;
            }
            view.findViewById(R.id.id_listen_count_layout).setVisibility(View.GONE);
        }

        /* renamed from: c */
        private void m5660c(int i, View view) {
            int i2;
            TextView textView = (TextView) view.findViewById(R.id.id_grid_view_item_name);
            if (FindSongGridViewFragment.this.isInSongListModule()) {
                textView.setVisibility(View.VISIBLE);
                if (FindSongGridViewFragment.this.getItemData(i).getName() != null) {
                    textView.setText(FindSongGridViewFragment.this.getItemData(i).getName());
                } else {
                    textView.setText("");
                }
                textView.setTextSize(0, FindSongConfig.C0626a.m8274d(this.f5144c));
                if (FindSongGridViewFragment.this.getItemData(i).getName() != null) {
                    i2 = FindSongGridViewFragment.this.getItemData(i).getName().length() > FindSongConfig.C0626a.m8278a(this.f5144c) ? 2 : 1;
                } else {
                    i2 = 1;
                }
                if ((i + 1) % this.f5144c == 0) {
                    FindSongGridViewFragment.this.setDescTextLines(Math.max(FindSongGridViewFragment.this.getDescTextLines(), i2));
                    i2 = FindSongGridViewFragment.this.getDescTextLines();
                    FindSongGridViewFragment.this.setDescTextLines(1);
                } else {
                    FindSongGridViewFragment.this.setDescTextLines(Math.max(FindSongGridViewFragment.this.getDescTextLines(), i2));
                }
                textView.getLayoutParams().height = i2 == 2 ? DisplayUtils.m7229a(48) : DisplayUtils.m7229a(30);
                textView.setMaxLines(i2);
                return;
            }
            textView.setVisibility(View.GONE);
        }

        /* renamed from: d */
        private void m5659d(int i, View view) {
            view.findViewById(R.id.id_click_view).getBackground().setAlpha(50);
            view.findViewById(R.id.id_click_view).setOnClickListener(FindSongGridViewFragment.this.createItemOnClickListener(i));
        }

        /* renamed from: e */
        private void m5658e(int i, View view) {
            if (FindSongGridViewFragment.this.getItemData(i).getForwardAction().getType() == 13) {
                FoldableListLayout foldableListLayout = (FoldableListLayout) view.findViewById(R.id.id_grid_view_image_layout);
                int m8276b = FindSongConfig.C0626a.m8276b(this.f5144c);
                ViewGroup.LayoutParams layoutParams = foldableListLayout.getLayoutParams();
                layoutParams.height = this.f5144c == 1 ? m8276b / 2 : m8276b;
                layoutParams.width = m8276b;
                foldableListLayout.setLayoutParams(layoutParams);
                ArrayList arrayList = new ArrayList();
                arrayList.add(FindSongGridViewFragment.this.getItemData(i));
                Calendar calendar = Calendar.getInstance();
                if (Preferences.m2906bw() != calendar.get(5)) {
                    arrayList.add(FindSongGridViewFragment.this.getItemData(i));
                    Preferences.m2817w(calendar.get(5));
                }
                foldableListLayout.setAdapter(new C1517b(FindSongGridViewFragment.this.getActivity(), arrayList, this.f5144c));
                return;
            }
            ImageView imageView = (ImageView) view.findViewById(R.id.id_grid_view_no_fold_image);
            int m8276b2 = FindSongConfig.C0626a.m8276b(this.f5144c);
            ViewGroup.LayoutParams layoutParams2 = imageView.getLayoutParams();
            layoutParams2.height = this.f5144c == 1 ? m8276b2 / 2 : m8276b2;
            layoutParams2.width = m8276b2;
            imageView.setLayoutParams(layoutParams2);
            ImageCacheUtils.m4752a(imageView, FindSongGridViewFragment.this.getItemData(i).getPicUrl(), m8276b2, this.f5144c == 1 ? m8276b2 / 2 : m8276b2, (int) R.drawable.img_music_default_icon);
        }

        /* renamed from: a */
        private boolean m5663a(int i) {
            if (i == 0) {
                this.f5143b++;
            } else {
                this.f5143b = 0;
            }
            return this.f5143b > 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.main.findsong.FindSongGridViewFragment$b */
    /* loaded from: classes.dex */
    public class C1517b extends ItemsAdapter<RecommendData> {

        /* renamed from: b */
        private final int f5146b;

        public C1517b(Context context, List<RecommendData> list, int i) {
            super(context);
            m7592a(list);
            this.f5146b = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: com.sds.android.ttpod.fragment.main.findsong.FindSongGridViewFragment$b$a */
        /* loaded from: classes.dex */
        public class C1518a {

            /* renamed from: b */
            private ImageView f5148b;

            /* renamed from: c */
            private TextView f5149c;

            /* renamed from: d */
            private TextView f5150d;

            public C1518a(View view) {
                this.f5148b = (ImageView) view.findViewById(R.id.id_grid_view_item_image);
                this.f5149c = (TextView) view.findViewById(R.id.id_week_text);
                this.f5150d = (TextView) view.findViewById(R.id.id_day_text);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.adapter.ItemsAdapter
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public View mo5653a(RecommendData recommendData, int i, ViewGroup viewGroup, LayoutInflater layoutInflater) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_find_song_gridview_image, viewGroup, false);
            inflate.setTag(new C1518a(inflate));
            return inflate;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.adapter.ItemsAdapter
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo5654a(RecommendData recommendData, int i, View view) {
            m5655a(recommendData, (C1518a) view.getTag(), i);
        }

        /* renamed from: a */
        private void m5655a(RecommendData recommendData, C1518a c1518a, int i) {
            int m8276b = FindSongConfig.C0626a.m8276b(this.f5146b);
            ViewGroup.LayoutParams layoutParams = c1518a.f5148b.getLayoutParams();
            layoutParams.height = this.f5146b == 1 ? m8276b / 2 : m8276b;
            layoutParams.width = m8276b;
            c1518a.f5148b.setLayoutParams(layoutParams);
            ImageCacheUtils.m4752a(c1518a.f5148b, recommendData.getPicUrl(), m8276b, this.f5146b == 1 ? m8276b / 2 : m8276b, (int) R.drawable.img_music_default_icon);
            if (recommendData.getForwardAction().getType() == 13) {
                Calendar calendar = Calendar.getInstance();
                calendar.add(5, (i + 1) - m7593a().size());
                c1518a.f5150d.setText("" + calendar.get(5));
                c1518a.f5150d.setVisibility(View.VISIBLE);
                c1518a.f5149c.setText(FindSongConfig.C0626a.f2499a.get(calendar.get(7)));
                c1518a.f5149c.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (isViewAccessAble() && this.mThemeReload) {
            setTitleBarTheme();
            this.mFindSongGridAdapter.notifyDataSetChanged();
            this.mThemeReload = false;
        }
    }
}
