package com.sds.android.ttpod.fragment.main.list;

import android.os.Bundle;
import android.view.View;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.framework.p106a.p107a.LocalStatistic;

/* loaded from: classes.dex */
public class SubFolderGroupFragment extends SubGroupListFragment {
    @Override // com.sds.android.ttpod.fragment.main.list.SubGroupListFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.findViewById(R.id.layout_create_playlist).setVisibility(View.GONE);
    }

    @Override // com.sds.android.ttpod.fragment.main.list.SubGroupListFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.fragment.base.OnDropdownMenuClickListener
    public void onDropDownMenuClicked(int i, ActionItem actionItem) {
        super.onDropDownMenuClicked(i, actionItem);
        switch (i) {
            case 4:
                LocalStatistic.m5114as();
                return;
            default:
                return;
        }
    }
}
