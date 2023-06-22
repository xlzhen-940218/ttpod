package com.sds.android.ttpod.fragment.main.findsong.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.sds.android.sdk.lib.request.DataListResult;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.FindSongGridSectionListAdapter;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ListUtils;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.widget.NetworkLoadView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class DoubleItemGridSectionListFragment<D> extends SlidingClosableFragment {
    protected FindSongGridSectionListAdapter<D> mAdapter;
    private ArrayList<FindSongGridSectionListAdapter.C0963a<D>> mListDataList;
    private ListView mListView;
    private NetworkLoadView mLoadingView;
    private CommandID mRequestId;
    private CommandID mResponseId;
    private boolean mDataLoading = false;
    private NetworkLoadView.InterfaceC2206b mOnLoadingViewStartLoadingListener = new NetworkLoadView.InterfaceC2206b() { // from class: com.sds.android.ttpod.fragment.main.findsong.base.DoubleItemGridSectionListFragment.1
        @Override // com.sds.android.ttpod.widget.NetworkLoadView.InterfaceC2206b
        /* renamed from: a */
        public void mo1678a() {
            if (DoubleItemGridSectionListFragment.this.mListDataList != null) {
                DoubleItemGridSectionListFragment.this.mAdapter.m7623a((ArrayList) DoubleItemGridSectionListFragment.this.mListDataList);
            } else {
                DoubleItemGridSectionListFragment.this.requestDataList();
            }
        }
    };

    protected abstract ArrayList<FindSongGridSectionListAdapter.C0963a<D>> convertDataList(ArrayList arrayList);

    protected abstract String onLoadTitleText();

    public DoubleItemGridSectionListFragment(CommandID commandID, CommandID commandID2, FindSongGridSectionListAdapter findSongGridSectionListAdapter) {
        this.mRequestId = null;
        this.mResponseId = null;
        this.mRequestId = commandID;
        this.mResponseId = commandID2;
        this.mAdapter = findSongGridSectionListAdapter;
    }

    public FindSongGridSectionListAdapter<D> getAdapter() {
        return this.mAdapter;
    }

    public void setAdapter(FindSongGridSectionListAdapter<D> findSongGridSectionListAdapter) {
        this.mAdapter = findSongGridSectionListAdapter;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        if (this.mResponseId != null) {
            map.put(this.mResponseId, ReflectUtils.m8375a(cls, "updateData", ArrayList.class));
        }
        map.put(CommandID.UPDATE_RADIO_CATEGORY_LIST, ReflectUtils.m8375a(cls, "updateRadioList", DataListResult.class));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_double_item_grid_section_list, viewGroup, false);
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        getActionBarController().m7193a((CharSequence) onLoadTitleText());
        this.mLoadingView = (NetworkLoadView) view.findViewById(R.id.market_load_view);
        this.mListView = (ListView) view.findViewById(R.id.market_app_list);
        this.mListView.setOnScrollListener(new ListViewUtils.C0631a());
        this.mLoadingView.setOnStartLoadingListener(this.mOnLoadingViewStartLoadingListener);
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
        this.mListView.setDividerHeight(0);
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mLoadingView != null) {
            this.mLoadingView.onThemeLoaded();
        }
        ThemeManager.m3269a(this.mListView, ThemeElement.BACKGROUND_MASK);
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    protected void requestDataList() {
        if (!this.mDataLoading) {
            CommandCenter.m4607a().m4606a(new Command(this.mRequestId, new Object[0]));
            this.mDataLoading = true;
        }
    }

    public void updateData(ArrayList arrayList) {
        if (isViewAccessAble()) {
            this.mDataLoading = false;
            if (arrayList == null) {
                this.mLoadingView.setLoadState(NetworkLoadView.EnumC2205a.FAILED);
                return;
            }
            this.mLoadingView.setLoadState(NetworkLoadView.EnumC2205a.IDLE);
            this.mListDataList = arrayList;
            this.mListDataList = convertDataList(arrayList);
            if (this.mListDataList == null) {
                this.mLoadingView.setLoadState(NetworkLoadView.EnumC2205a.FAILED);
            } else {
                this.mAdapter.m7623a((ArrayList) this.mListDataList);
            }
        }
    }

    public void updateRadioList(DataListResult dataListResult) {
        ArrayList<FindSongGridSectionListAdapter.C0963a<D>> dataList = dataListResult.getDataList();
        if (isViewAccessAble()) {
            this.mDataLoading = false;
            if (ListUtils.m4718a(dataList)) {
                this.mLoadingView.setLoadState(NetworkLoadView.EnumC2205a.FAILED);
                return;
            }
            this.mLoadingView.setLoadState(NetworkLoadView.EnumC2205a.IDLE);
            this.mListDataList = dataList;
            this.mListDataList = convertDataList(dataList);
            if (this.mListDataList == null) {
                this.mLoadingView.setLoadState(NetworkLoadView.EnumC2205a.FAILED);
            } else {
                this.mAdapter.m7623a((ArrayList) this.mListDataList);
            }
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        this.mLoadingView.setLoadState(NetworkLoadView.EnumC2205a.LOADING);
    }
}
