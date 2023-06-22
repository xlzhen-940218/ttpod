package com.sds.android.ttpod.activities.musiccircle;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.musiccircle.StarListFragment;
import com.sds.android.ttpod.fragment.musiccircle.StarListOfCategoryFragment;
import com.sds.android.ttpod.fragment.musiccircle.StarListOfRankFragment;

/* loaded from: classes.dex */
public class WrapStarListFragment extends SlidingClosableFragment {
    public static final int CATEGORY_TYPE_CATEGORY = 1;
    public static final int CATEGORY_TYPE_RANK = 0;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_starlist_layout, viewGroup, false);
        initView();
        return inflate;
    }

    private void initView() {
        getActionBarController().m7193a((CharSequence) getArguments().getString("title"));
        getChildFragmentManager().beginTransaction().replace(R.id.musiccircle_starlist, (StarListFragment) Fragment.instantiate(getActivity(), getArguments().getInt("category_type", 0) == 0 ? StarListOfRankFragment.class.getName() : StarListOfCategoryFragment.class.getName(), getArguments())).commitAllowingStateLoss();
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }
}
