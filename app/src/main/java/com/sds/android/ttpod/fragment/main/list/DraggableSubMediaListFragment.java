package com.sds.android.ttpod.fragment.main.list;

import com.sds.android.ttpod.utils.GroupItemUtils;

/* loaded from: classes.dex */
public class DraggableSubMediaListFragment extends SubMediaListFragment {
    @Override // com.sds.android.ttpod.fragment.main.list.SubMediaListFragment
    protected String selectMediaListFragmentClassName() {
        if (!GroupItemUtils.m8267b(getArguments().getString(AbsMediaListFragment.KEY_GROUP_ID))) {
            throw new IllegalArgumentException("list must be Draggable");
        }
        return DraggableMediaListFragment.class.getName();
    }
}
