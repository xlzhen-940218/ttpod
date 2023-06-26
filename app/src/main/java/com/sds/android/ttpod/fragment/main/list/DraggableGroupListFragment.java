package com.sds.android.ttpod.fragment.main.list;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.media.mediastore.GroupItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.widget.DraggableListView;
import com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView;
import java.util.List;

/* loaded from: classes.dex */
public class DraggableGroupListFragment extends GroupListFragment {
    @Override // com.sds.android.ttpod.fragment.main.list.GroupListFragment, com.sds.android.ttpod.fragment.main.list.BaseGroupListFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ActionExpandableListView listView = getListView();
        listView.setDragListener(new DraggableListView.InterfaceC2177b() { // from class: com.sds.android.ttpod.fragment.main.list.DraggableGroupListFragment.1
            @Override // com.sds.android.ttpod.widget.DraggableListView.InterfaceC2177b
            /* renamed from: a */
            public void mo1780a(int i, int i2) {
                List<GroupItem> groupItemList = DraggableGroupListFragment.this.getGroupItemList();
                if (i >= 0 && i2 >= 0 && i != i2 && i2 < DraggableGroupListFragment.this.bottomLimit() && i < DraggableGroupListFragment.this.bottomLimit()) {
                    GroupItem groupItem = groupItemList.get(i);
                    GroupItem groupItem2 = groupItemList.get(i2);
                    CommandCenter.getInstance().execute(new Command(CommandID.ADD_GROUP_ITEM_EXCHANGE_ORDER_EVENT, DraggableGroupListFragment.this.getGroupType(), groupItem.getGroupID(), groupItem2.getGroupID()));
                    groupItemList.set(i, groupItem2);
                    groupItemList.set(i2, groupItem);
                }
            }
        });
        listView.setDropListener(new DraggableListView.InterfaceC2181f() { // from class: com.sds.android.ttpod.fragment.main.list.DraggableGroupListFragment.2
            @Override // com.sds.android.ttpod.widget.DraggableListView.InterfaceC2181f
            /* renamed from: a */
            public void mo1773a(int i, int i2) {
                //SUserUtils.m4956a(SAction.ACTION_MY_SONGLIST_DRAG_SORT, SPage.PAGE_NONE);
                CommandCenter.getInstance().execute(new Command(CommandID.COMMIT_GROUP_ITEM_EXCHANGE_ORDER, DraggableGroupListFragment.this.getGroupType()));
                DraggableGroupListFragment.this.notifyDataSetChanged();
            }
        });
        listView.setDragStartViewId(R.id.drag_handle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.BaseGroupListFragment
    public View getGroupItemView(final GroupItem groupItem, View view, ViewGroup viewGroup) {
        View groupItemView = super.getGroupItemView(groupItem, view, viewGroup);
        BaseGroupListFragment.C1631b c1631b = (BaseGroupListFragment.C1631b) groupItemView.getTag();
        c1631b.m5462e().setVisibility(View.VISIBLE);
        String groupID = groupItem.getGroupID();
        if (groupID.startsWith(MediaStorage.GROUP_ID_CUSTOM_PREFIX) && !groupID.startsWith(MediaStorage.GROUP_ID_RECENTLY_ADD) && !groupID.startsWith(MediaStorage.GROUP_ID_RECENTLY_PLAY)) {
            c1631b.m5463d().setVisibility(View.VISIBLE);
            c1631b.m5463d().setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.list.DraggableGroupListFragment.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    DraggableGroupListFragment.this.showContextDialog(groupItem);
                }
            });
        } else {
            c1631b.m5463d().setVisibility(View.GONE);
        }
        return groupItemView;
    }

    protected void showContextDialog(GroupItem groupItem) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int bottomLimit() {
        if (getGroupItemList() != null) {
            return getGroupItemList().size();
        }
        return 0;
    }

    @Override // com.sds.android.ttpod.fragment.main.list.GroupListFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onKeyPressed(int i, KeyEvent keyEvent) {
    }
}
