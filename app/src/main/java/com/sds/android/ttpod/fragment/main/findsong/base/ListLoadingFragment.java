package com.sds.android.ttpod.fragment.main.findsong.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.sds.android.cloudapi.ttpod.result.SingerListResult;
import com.sds.android.sdk.lib.request.ExtraDataListResult;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.BaseListAdapter;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ListUtils;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.widget.DataListFooterView;
import com.sds.android.ttpod.widget.NetworkLoadView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class ListLoadingFragment<D> extends SlidingClosableFragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    protected static final int HOME_PAGE = 1;
    protected DataListFooterView mFooterView;
    protected BaseListAdapter<D> mListAdapter;
    protected ListView mListView;
    protected NetworkLoadView mLoadingView;
    protected CommandID mRequestId;
    protected CommandID mResponseId;
    protected int mTotalPages;
    protected ArrayList<D> mDataList = null;
    protected int mCurrentPage = 1;
    protected boolean mDataLoading = false;
    protected boolean mIsReqSuccess = false;
    protected NetworkLoadView.InterfaceC2206b mOnStartLoadingListener = new NetworkLoadView.InterfaceC2206b() { // from class: com.sds.android.ttpod.fragment.main.findsong.base.ListLoadingFragment.2
        @Override // com.sds.android.ttpod.widget.NetworkLoadView.InterfaceC2206b
        /* renamed from: a */
        public void mo1678a() {
            if (ListLoadingFragment.this.mDataList != null) {
                ListLoadingFragment.this.updateDataListView(ListLoadingFragment.this.mDataList);
                if (ListLoadingFragment.this.mLoadingView != null) {
                    ListLoadingFragment.this.mLoadingView.setLoadState(NetworkLoadView.EnumC2205a.IDLE);
                }
                ListLoadingFragment.this.hideFooter();
                return;
            }
            ListLoadingFragment.this.requestDataList(1);
        }
    };
    protected AbsListView.OnScrollListener mOnScrollListener = new AbsListView.OnScrollListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.base.ListLoadingFragment.3
        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (!ListLoadingFragment.this.mDataLoading && ListLoadingFragment.this.mCurrentPage == ListLoadingFragment.this.mTotalPages) {
                ListLoadingFragment.this.showLastPageFooterText();
                if (ListLoadingFragment.this.mFooterView != null) {
                    ListLoadingFragment.this.mFooterView.setOnClickListener(null);
                }
            } else if (i2 > 0 && i3 >= i2 && i + i2 >= i3 && !ListLoadingFragment.this.mDataLoading && ListLoadingFragment.this.mCurrentPage < ListLoadingFragment.this.mTotalPages && ListViewUtils.m8262b(i, i2, i3)) {
                if (ListLoadingFragment.this.mIsReqSuccess) {
                    ListLoadingFragment listLoadingFragment = ListLoadingFragment.this;
                    ListLoadingFragment listLoadingFragment2 = ListLoadingFragment.this;
                    int i4 = listLoadingFragment2.mCurrentPage + 1;
                    listLoadingFragment2.mCurrentPage = i4;
                    listLoadingFragment.requestDataList(i4);
                } else {
                    ListLoadingFragment.this.requestDataList(ListLoadingFragment.this.mCurrentPage);
                }
                ListLoadingFragment.this.mDataLoading = true;
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
        }
    };

    protected abstract String onLoadTitleText();

    public ListLoadingFragment(CommandID commandID, CommandID commandID2, BaseListAdapter<D> baseListAdapter) {
        this.mRequestId = null;
        this.mResponseId = null;
        this.mRequestId = commandID;
        this.mResponseId = commandID2;
        this.mListAdapter = baseListAdapter;
    }

    public void setAdapter(BaseListAdapter<D> baseListAdapter) {
        this.mListAdapter = baseListAdapter;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        if (this.mResponseId != null) {
            map.put(this.mResponseId, ReflectUtils.m8375a(getClass(), "updateSingerData", SingerListResult.class));
        }
        map.put(CommandID.UPDATE_MV_LIST, ReflectUtils.m8375a(getClass(), "updateMVListData", ExtraDataListResult.class));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_base_list, viewGroup, false);
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initViews(view);
        setupTitleText();
        setupLoadingView();
        setupListView();
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mFooterView != null) {
            ThemeManager.m3269a(this.mFooterView, ThemeElement.BACKGROUND_MASK);
            ThemeManager.m3269a(this.mFooterView, ThemeElement.COMMON_SUB_TITLE_TEXT);
        }
        if (this.mLoadingView != null) {
            this.mLoadingView.onThemeLoaded();
        }
        ThemeManager.m3269a(this.mListView, ThemeElement.BACKGROUND_MASK);
        ThemeManager.m3269a(this.mListView, ThemeElement.COMMON_SEPARATOR);
        if (this.mListAdapter != null) {
            this.mListAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        showLoadingAnim();
    }

    public void updateData(ArrayList<D> arrayList, Integer num) {
        if (isViewAccessAble()) {
            this.mDataLoading = false;
            if (arrayList == null || arrayList.size() == 0) {
                showNetworkError();
                this.mIsReqSuccess = false;
                if (this.mDataList != null) {
                    this.mFooterView.m1874b();
                    this.mFooterView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.base.ListLoadingFragment.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            if (!ListLoadingFragment.this.mIsReqSuccess && !ListLoadingFragment.this.mDataLoading) {
                                ListLoadingFragment.this.mDataLoading = true;
                                ListLoadingFragment.this.requestDataList(ListLoadingFragment.this.mCurrentPage);
                            }
                        }
                    });
                    return;
                }
                return;
            }
            hideLoadingAnim();
            hideFooter();
            if (1 == this.mCurrentPage) {
                if (this.mDataList != null) {
                    this.mDataList.clear();
                }
                this.mDataList = arrayList;
                this.mTotalPages = num.intValue();
            } else {
                this.mDataList.addAll(arrayList);
            }
            if (this.mFooterView != null) {
                this.mFooterView.setOnClickListener(null);
            }
            this.mIsReqSuccess = true;
            updateDataListView(this.mDataList);
        }
    }

    public void updateSingerData(SingerListResult singerListResult) {
        ArrayList<D> arrayList = new ArrayList<>();
        arrayList.addAll((Collection<? extends D>) singerListResult.getDataList());
        updateData(arrayList, Integer.valueOf(singerListResult.getExtra().m8556b()));
    }

    public void updateMVListData(ExtraDataListResult extraDataListResult) {
        ArrayList<D> arrayList = new ArrayList<>();
        arrayList.addAll(extraDataListResult.getDataList());
        updateData(arrayList, Integer.valueOf(extraDataListResult.getExtra().m8556b()));
    }

    protected void requestDataList(int i) {
        if (!this.mDataLoading && this.mRequestId != null) {
            CommandCenter.getInstance().execute(new Command(this.mRequestId, Integer.valueOf(i)));
            this.mDataLoading = true;
        }
    }

    protected void initViews(View view) {
        this.mLoadingView = (NetworkLoadView) view.findViewById(R.id.loading_view);
        this.mListView = (ListView) view.findViewById(R.id.market_app_list);
    }

    protected void setupTitleText() {
        getActionBarController().m7193a((CharSequence) onLoadTitleText());
    }

    protected void setupLoadingView() {
        if (this.mLoadingView != null) {
            this.mLoadingView.setOnStartLoadingListener(this.mOnStartLoadingListener);
        }
    }

    protected void setupListView() {
        this.mListView.setEmptyView(this.mLoadingView);
        this.mListView.setOnScrollListener(this.mOnScrollListener);
        this.mListView.setOnItemClickListener(this);
        this.mListView.setOnItemLongClickListener(this);
        this.mListView.setAdapter((ListAdapter) this.mListAdapter);
        setupListHeader();
        setupListFooter();
    }

    protected void setupListHeader() {
    }

    protected void setupListFooter() {
        this.mFooterView = new DataListFooterView(getActivity());
        this.mListView.addFooterView(this.mFooterView);
    }

    protected void updateDataListView(ArrayList<D> arrayList) {
        this.mListAdapter.setDataList((List) arrayList);
    }

    protected void showLastPageFooterText() {
        if (this.mFooterView != null) {
            this.mFooterView.m1875a(BaseApplication.getApplication().getString(R.string.last_page_prompt));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideFooter() {
        if (this.mFooterView != null) {
            this.mFooterView.m1873c();
        }
    }

    private void showLoadingAnim() {
        if (this.mLoadingView != null && ListUtils.m4718a(this.mDataList)) {
            this.mLoadingView.setLoadState(NetworkLoadView.EnumC2205a.LOADING);
        }
    }

    private void hideLoadingAnim() {
        if (this.mLoadingView != null) {
            this.mLoadingView.setLoadState(NetworkLoadView.EnumC2205a.IDLE);
        }
    }

    private void showNetworkError() {
        if (this.mLoadingView != null) {
            this.mLoadingView.setLoadState(NetworkLoadView.EnumC2205a.FAILED);
        }
    }
}
