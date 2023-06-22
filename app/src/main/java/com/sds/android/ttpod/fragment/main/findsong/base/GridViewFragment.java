package com.sds.android.ttpod.fragment.main.findsong.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import com.sds.android.sdk.lib.request.DataListResult;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.GridListAdapter;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.widget.NetworkLoadView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class GridViewFragment<D> extends SlidingClosableFragment implements AdapterView.OnItemClickListener {
    protected boolean mDataLoading;
    protected GridListAdapter<D> mGridListAdapter;
    private GridView mGridView;
    protected NetworkLoadView mLoadView;
    private int mNumColumns;

    protected abstract String onLoadTitleText();

    protected void setAdapter(GridListAdapter<D> gridListAdapter) {
        this.mGridListAdapter = gridListAdapter;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setGridListAdapter(ListAdapter listAdapter) {
        this.mGridView.setAdapter(listAdapter);
    }

    public GridViewFragment(GridListAdapter<D> gridListAdapter) {
        this.mDataLoading = false;
        this.mNumColumns = 2;
        this.mGridListAdapter = gridListAdapter;
    }

    public GridViewFragment(GridListAdapter<D> gridListAdapter, int i) {
        this(gridListAdapter);
        this.mNumColumns = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_SINGER_CATEGORY_LIST, ReflectUtils.m8375a(getClass(), "updateSingerCategoryList", DataListResult.class));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_double_item_grid_list, viewGroup, false);
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        getActionBarController().m7193a((CharSequence) onLoadTitleText());
        this.mLoadView = (NetworkLoadView) view.findViewById(R.id.loading_view);
        this.mGridView = (GridView) view.findViewById(R.id.double_item_list_grid_view);
        this.mGridView.setNumColumns(this.mNumColumns);
        this.mGridView.setAdapter((ListAdapter) this.mGridListAdapter);
        this.mGridView.setOnItemClickListener(this);
        this.mGridView.setOnScrollListener(new ListViewUtils.C0631a());
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mLoadView.onThemeLoaded();
        ThemeManager.m3269a(this.mGridView, ThemeElement.BACKGROUND_MASK);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        this.mLoadView.setLoadState(NetworkLoadView.EnumC2205a.LOADING);
    }

    public void updateDataList(ArrayList<D> arrayList) {
        if (isViewAccessAble()) {
            this.mDataLoading = false;
            if (arrayList == null || arrayList.size() == 0) {
                this.mLoadView.setLoadState(NetworkLoadView.EnumC2205a.FAILED);
                return;
            }
            this.mLoadView.setLoadState(NetworkLoadView.EnumC2205a.IDLE);
            this.mGridListAdapter.m7663a((List) arrayList);
        }
    }

    public void updateSingerCategoryList(DataListResult dataListResult) {
        updateDataList(dataListResult.getDataList());
    }
}
