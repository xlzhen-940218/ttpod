package com.sds.android.ttpod.fragment.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.sds.android.cloudapi.ttpod.data.VIPPolicy;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.base.BaseSlidingTabFragment;
import com.sds.android.ttpod.fragment.main.SearchFragment;
import com.sds.android.ttpod.fragment.main.SearchResultFragment;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.Pager;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.widget.DataListFooterView;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateListView;

/* loaded from: classes.dex */
public abstract class BaseSearchFragment extends BaseFragment implements SearchFragment.InterfaceC1490a, SearchResultFragment.InterfaceC1501a {
    public static final int DEFAULT_PAGE_SIZE = 10;
    private static final String TAG = "BaseSearchFragment";
    protected BaseAdapter mAdapter;
    protected DataListFooterView mFooterView;
    protected ListView mListView;
    protected BaseSlidingTabFragment.InterfaceC1421a mOnDataCountChangeListener;
    protected String mOrigin;
    protected View mRootView;
    protected StateView mStateView;
    protected String mUserInput;
    protected String mWord;
    protected Pager mPager = new Pager();
    protected boolean mIsLoading = true;
    protected boolean mIsErrorNotFirstPage = false;
    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.sds.android.ttpod.fragment.search.BaseSearchFragment.1
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            int m8266a = ListViewUtils.m8266a(BaseSearchFragment.this.mListView.getHeaderViewsCount(), i, BaseSearchFragment.this.mAdapter.getCount());
            if (m8266a > -1) {
                BaseSearchFragment.this.performOnItemClick(m8266a);
            }
        }
    };
    private AbsListView.OnScrollListener mOnScrollListener = new AbsListView.OnScrollListener() { // from class: com.sds.android.ttpod.fragment.search.BaseSearchFragment.2
        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (ListViewUtils.m8262b(i, i2, i3) && !BaseSearchFragment.this.mIsLoading) {
                if (BaseSearchFragment.this.mPager.m4669a() >= BaseSearchFragment.this.mPager.m4658g()) {
                    BaseSearchFragment.this.showLastPageFooterText();
                } else if (!BaseSearchFragment.this.mIsErrorNotFirstPage) {
                    BaseSearchFragment.this.requestData(BaseSearchFragment.this.mPager.m4662d());
                } else {
                    BaseSearchFragment.this.showNotFirstPageError();
                }
            }
        }
    };

    protected abstract BaseAdapter getAdapter();

    protected abstract int getSize();

    protected abstract void performOnItemClick(int i);

    protected abstract void search(String str, int i, int i2);

    protected abstract void showLastPageFooterText();

    /* JADX INFO: Access modifiers changed from: private */
    public void requestData(int i) {
        LogUtils.debug(TAG, "requestData page = " + i);
        if (i >= 1) {
            this.mIsLoading = true;
            this.mFooterView.m1877a();
            search(this.mWord, i, 10);
            this.mPager.m4663c(i);
        }
    }

    protected void showNotFirstPageError() {
        if (this.mFooterView != null) {
            this.mFooterView.m1875a("加载下一页出错, 请重新尝试");
            this.mFooterView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.search.BaseSearchFragment.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    BaseSearchFragment.this.requestData(BaseSearchFragment.this.mPager.m4662d());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onLoadNextPageError() {
        if (this.mPager.m4669a() > 1) {
            this.mIsErrorNotFirstPage = true;
            this.mPager.m4663c(this.mPager.m4669a() - 1);
            showNotFirstPageError();
            return;
        }
        this.mIsErrorNotFirstPage = false;
        this.mStateView.setState(StateView.EnumC2248b.FAILED);
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.fragment_result_search, viewGroup, false);
            this.mStateView = (StateView) this.mRootView.findViewById(R.id.loading_view);
            this.mStateView.setOnRetryRequestListener(new StateView.InterfaceC2247a() { // from class: com.sds.android.ttpod.fragment.search.BaseSearchFragment.4
                @Override // com.sds.android.ttpod.widget.StateView.InterfaceC2247a
                /* renamed from: a */
                public void mo1450a() {
                    BaseSearchFragment.this.search(BaseSearchFragment.this.mWord, BaseSearchFragment.this.mUserInput);
                }
            });
            this.mListView = (ListView) this.mStateView.findViewById(R.id.media_listview);
            this.mFooterView = new DataListFooterView(getActivity());
            this.mListView.addFooterView(this.mFooterView);
            this.mAdapter = getAdapter();
            this.mListView.setAdapter((ListAdapter) this.mAdapter);
            this.mListView.setOnItemClickListener(this.mOnItemClickListener);
            this.mListView.setOnScrollListener(this.mOnScrollListener);
            this.mPager.m4665b(VIPPolicy.Entry.MAX_LIMIT);
            this.mStateView.setNodataView(createEmptyView(layoutInflater));
            ((DragUpdateListView) this.mListView).setEnableDragUpdate(false);
        }
        return this.mRootView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public View createEmptyView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.search_result_nodata, (ViewGroup) null);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mStateView != null) {
            this.mStateView.onThemeLoaded();
        }
        ThemeManager.m3269a(this.mRootView, ThemeElement.BACKGROUND_MASK);
        ThemeManager.m3269a(this.mListView, ThemeElement.COMMON_SEPARATOR);
        ThemeManager.m3269a(this.mFooterView, ThemeElement.BACKGROUND_MASK);
        ThemeManager.m3269a(this.mFooterView, ThemeElement.SONG_LIST_ITEM_TEXT);
        this.mAdapter.notifyDataSetChanged();
    }

    @Override // com.sds.android.ttpod.fragment.main.SearchFragment.InterfaceC1490a
    public void search(String str, String str2) {
        this.mUserInput = str2;
        if (!StringUtils.isEmpty(str)) {
            this.mPager = new Pager();
            this.mPager.m4665b(VIPPolicy.Entry.MAX_LIMIT);
            this.mStateView.setState(StateView.EnumC2248b.LOADING);
            search(str, 1, 10);
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.SearchResultFragment.InterfaceC1501a
    public void onFragmentSelected(String str, String str2) {
        if (!StringUtils.m8344a(str, this.mWord)) {
            search(str, this.mUserInput);
        }
    }

    public String getOrigin() {
        return this.mOrigin;
    }

    public void setOrigin(String str) {
        this.mOrigin = str;
    }

    public void setOnDataCountChangeListener(BaseSlidingTabFragment.InterfaceC1421a interfaceC1421a) {
        this.mOnDataCountChangeListener = interfaceC1421a;
    }
}
