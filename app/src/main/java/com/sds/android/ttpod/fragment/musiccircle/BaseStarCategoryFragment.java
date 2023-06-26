package com.sds.android.ttpod.fragment.musiccircle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.WrapStarListFragment;
import com.sds.android.ttpod.adapter.p073e.StarCategoryAdapter;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.widget.StateView;

/* loaded from: classes.dex */
public abstract class BaseStarCategoryFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private StarCategoryAdapter mAdapter;
    private ListView mListView;
    private View mReloadView;
    private StateView mStateView;

    protected abstract int loadCategoryType();

    protected abstract StarCategoryAdapter onCreateAdapter();

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_user_list_layout, (ViewGroup) null, false);
        this.mStateView = (StateView) inflate.findViewById(R.id.user_loadingview);
        this.mStateView.setState(StateView.EnumC2248b.SUCCESS);
        this.mReloadView = this.mStateView.findViewById(R.id.loadingview_failed);
        this.mReloadView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.musiccircle.BaseStarCategoryFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (EnvironmentUtils.DeviceConfig.isConnected()) {
                    BaseStarCategoryFragment.this.mStateView.setState(StateView.EnumC2248b.LOADING);
                    BaseStarCategoryFragment.this.onRequestData();
                    return;
                }
                PopupsUtils.m6760a((int) R.string.network_error);
            }
        });
        this.mListView = (ListView) this.mStateView.findViewById(R.id.user_listview);
        this.mListView.setOnItemClickListener(this);
        this.mAdapter = onCreateAdapter();
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setLoadingState(StateView.EnumC2248b enumC2248b) {
        this.mStateView.setState(enumC2248b);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        onRequestData();
    }

    protected void onRequestData() {
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int m8266a = ListViewUtils.m8266a(this.mListView.getHeaderViewsCount(), i, this.mAdapter.getCount());
        if (m8266a > -1) {
            StarCategory item = this.mAdapter.getItem(m8266a);
            Bundle bundle = new Bundle();
            bundle.putInt("category_type", loadCategoryType());
            bundle.putInt("category_id", item.getId());
            bundle.putString("title", item.getName());
            WrapStarListFragment wrapStarListFragment = new WrapStarListFragment();
            wrapStarListFragment.setArguments(bundle);
            launchFragment(wrapStarListFragment);
            onItemClickEvent(item);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onItemClickEvent(StarCategory starCategory) {
    }
}
