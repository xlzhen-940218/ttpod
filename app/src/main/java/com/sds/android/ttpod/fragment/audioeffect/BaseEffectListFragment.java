package com.sds.android.ttpod.fragment.audioeffect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.core.audioeffect.PrivateEffectItem;
import java.util.List;

/* loaded from: classes.dex */
public abstract class BaseEffectListFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    protected BaseAdapter mAdapter;
    protected ListView mListView;
    protected List<PrivateEffectItem> mMyEffectItemList;

    protected abstract View getEffectItem(int i, View view, ViewGroup viewGroup);

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.effect_listview, (ViewGroup) null);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mListView = (ListView) view.findViewById(R.id.listview_effect);
        this.mAdapter = new C1383a();
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
        this.mListView.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
    }

    public void updateData(List<PrivateEffectItem> list) {
        this.mMyEffectItemList = list;
        notifyDataSetChanged();
    }

    public List<PrivateEffectItem> getData() {
        return this.mMyEffectItemList;
    }

    public final void notifyDataSetChanged() {
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    /* renamed from: com.sds.android.ttpod.fragment.audioeffect.BaseEffectListFragment$a */
    /* loaded from: classes.dex */
    private class C1383a extends BaseAdapter {
        private C1383a() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            if (BaseEffectListFragment.this.mMyEffectItemList == null) {
                return 0;
            }
            return BaseEffectListFragment.this.mMyEffectItemList.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return 0L;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            return BaseEffectListFragment.this.getEffectItem(i, view, viewGroup);
        }
    }
}
