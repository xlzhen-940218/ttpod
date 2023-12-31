package com.sds.android.ttpod.adapter.p076g;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.p089e.SearchHistory;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.utils.ThemeUtils;

/* renamed from: com.sds.android.ttpod.adapter.g.a */
/* loaded from: classes.dex */
public class SearchHistoryAdapter extends BaseAdapter {

    /* renamed from: a */
    private SearchHistory f3414a;

    /* renamed from: b */
    private InterfaceC1006a f3415b;

    /* compiled from: SearchHistoryAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.g.a$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1006a {
        /* renamed from: a */
        void mo5679a(String str);
    }

    public SearchHistoryAdapter(SearchHistory searchHistory) {
        this.f3414a = searchHistory;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.f3414a != null) {
            return this.f3414a.m4089c();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    /* renamed from: a */
    public String getItem(int i) {
        return this.f3414a.m4093b().get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        SearchHistoryViewHolder searchHistoryViewHolder;
        if (view == null) {
            view = LayoutInflater.from(BaseApplication.getApplication()).inflate(R.layout.online_search_history_item, viewGroup, false);
            SearchHistoryViewHolder c1007b2 = new SearchHistoryViewHolder((IconTextView) view.findViewById(R.id.search_history_clear_ic)
                    , view.findViewById(R.id.layout_search_history_clear)
                    , (TextView) view.findViewById(R.id.search_history_title));
            view.setTag(c1007b2);
            searchHistoryViewHolder = c1007b2;
        } else {
            searchHistoryViewHolder = (SearchHistoryViewHolder) view.getTag();
        }
        searchHistoryViewHolder.clear.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.adapter.g.a.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SearchHistoryAdapter.this.f3415b != null) {
                    SearchHistoryAdapter.this.f3415b.mo5679a((String) view2.getTag());
                }
            }
        });
        searchHistoryViewHolder.clear.setTag(this.f3414a.m4093b().get(i));
        searchHistoryViewHolder.title.setText(this.f3414a.m4093b().get(i));
        ThemeManager.m3269a(searchHistoryViewHolder.title, ThemeElement.SONG_LIST_ITEM_TEXT);
        ThemeManager.m3269a(view, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        ThemeUtils.m8173a(searchHistoryViewHolder.clearIc, ThemeElement.SONG_LIST_ITEM_TEXT);
        return view;
    }

    /* compiled from: SearchHistoryAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.g.a$b */
    /* loaded from: classes.dex */
    private final class SearchHistoryViewHolder {

        /* renamed from: b */
        private TextView title;

        /* renamed from: c */
        private IconTextView clearIc;

        /* renamed from: d */
        private View clear;

        SearchHistoryViewHolder(IconTextView iconTextView, View view, TextView textView) {
            this.clearIc = iconTextView;
            this.title = textView;
            this.clear = view;
        }
    }

    /* renamed from: a */
    public void m7390a(InterfaceC1006a interfaceC1006a) {
        this.f3415b = interfaceC1006a;
    }
}
