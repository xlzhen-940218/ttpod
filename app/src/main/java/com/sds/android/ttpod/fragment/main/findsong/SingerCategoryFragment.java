package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.sds.android.cloudapi.ttpod.data.SingerCategory;

import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.GridListAdapter;
import com.sds.android.ttpod.fragment.main.findsong.base.GridViewFragment;
import com.sds.android.ttpod.fragment.main.findsong.base.SingerListImageHeaderFragment;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.widget.NetworkLoadView;

/* loaded from: classes.dex */
public class SingerCategoryFragment extends GridViewFragment<SingerCategory> {
    private static final int ID_HOT = 110;
    private static final int ID_TOP100 = 54;
    private int mId;
    private NetworkLoadView.InterfaceC2206b mOnStartLoadingListener;
    private String mTitle;

    public SingerCategoryFragment(String str, int i) {
        super(new C1569a());
        this.mOnStartLoadingListener = new NetworkLoadView.InterfaceC2206b() { // from class: com.sds.android.ttpod.fragment.main.findsong.SingerCategoryFragment.1
            @Override // com.sds.android.ttpod.widget.NetworkLoadView.InterfaceC2206b
            /* renamed from: a */
            public void mo1678a() {
                SingerCategoryFragment.this.requestDataList();
            }
        };
        this.mId = i;
        this.mTitle = str;
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.GridViewFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mLoadView.setOnStartLoadingListener(this.mOnStartLoadingListener);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setPage(this.mTitle);
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.GridViewFragment
    protected String onLoadTitleText() {
        return this.mTitle;
    }

    protected void requestDataList() {
        CommandCenter.getInstance().m4606a(new Command(CommandID.GET_SINGER_CATEGORY_LIST, Integer.valueOf(this.mId)));
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        SingerCategory singerCategory = (SingerCategory) view.getTag(R.id.view_bind_data);
        int id = singerCategory.getId();
        String title = singerCategory.getTitle();
        if (id == ID_TOP100 || id == ID_HOT) {
            launchFragment(new SingerCategoryHotDetailFragment(title, id));
        } else {
            launchFragment(new SingerListImageHeaderFragment(title, id));
        }
        //MusicLibraryStatistic.m5060c(id, title);
        //new SUserEvent("PAGE_CLICK", SAction.ACTION_SINGERS_CLASSIFICATION.getValue(), this.mTitle, title).append(BaseFragment.KEY_SONG_LIST_ID, Integer.valueOf(id)).post();
    }

    /* renamed from: com.sds.android.ttpod.fragment.main.findsong.SingerCategoryFragment$a */
    /* loaded from: classes.dex */
    private static class C1569a extends GridListAdapter<SingerCategory> {
        private C1569a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.adapter.GridListAdapter
        /* renamed from: a */
        public String mo5568b(SingerCategory singerCategory) {
            return singerCategory.getTitle();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.adapter.GridListAdapter
        /* renamed from: b  reason: avoid collision after fix types in other method */
        public String mo5566c(SingerCategory singerCategory) {
            return this.f3156b.getString(R.string.count_of_singers, Integer.valueOf(singerCategory.getCount()));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.adapter.GridListAdapter
        /* renamed from: c  reason: avoid collision after fix types in other method */
        public String mo5565d(SingerCategory singerCategory) {
            return singerCategory.getPicUrl();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.adapter.GridListAdapter, com.sds.android.ttpod.adapter.BaseListAdapter
        /* renamed from: a */
        public void mo5400a(View view, SingerCategory singerCategory, int i) {
            super.mo5400a(view, singerCategory, i);
        }

        @Override // com.sds.android.ttpod.adapter.GridListAdapter, com.sds.android.ttpod.adapter.BaseListAdapter
        /* renamed from: a */
        protected View mo5402a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            View inflate = this.f3157c.inflate(R.layout.find_song_singer_category_with_num_grid_list_view_item, viewGroup, false);
            inflate.setTag(new GridListAdapter.C0966a(inflate));
            return inflate;
        }
    }
}
