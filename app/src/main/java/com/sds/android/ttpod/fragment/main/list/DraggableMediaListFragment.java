package com.sds.android.ttpod.fragment.main.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.local.MediaPickerActivity;
import com.sds.android.ttpod.activities.mediascan.FilePickerActivity;
import com.sds.android.ttpod.activities.mediascan.MediaScanAnimationActivity;
import com.sds.android.ttpod.component.p085b.MediaItemViewHolder;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.media.mediastore.ExchangeOrderEntity;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.utils.GroupItemUtils;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.DraggableListView;
import com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class DraggableMediaListFragment extends MediaListFragment {
    private List<int[]> mExchangeOrderList = new ArrayList();

    public void addMedia(boolean z) {
        if (z) {
            startActivity(new Intent(getActivity(), MediaPickerActivity.class).putExtra(AbsMediaListFragment.KEY_GROUP_ID, getGroupID()));
            return;
        }
        if (GroupItemUtils.m8267b(getArguments().getString(AbsMediaListFragment.KEY_GROUP_ID))) {
            //LocalStatistic.m5117ap();
        }
        startActivityForResult(new Intent(getActivity(), FilePickerActivity.class).putExtra(AbsMediaListFragment.KEY_GROUP_ID, getGroupID()), 1);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            getActivity();
            if (i2 == -1) {
                String[] stringArrayExtra = intent.getStringArrayExtra(FilePickerActivity.KEY_EXTRA_SELECTED_FILES);
                Intent intent2 = new Intent(getActivity(), MediaScanAnimationActivity.class);
                intent2.putExtra(MediaScanAnimationActivity.KEY_SCAN_FILES, stringArrayExtra);
                intent2.putExtra(MediaScanAnimationActivity.KEY_GROUP_ID, getGroupID());
                startActivity(intent2);
            }
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.list.MediaListFragment, com.sds.android.ttpod.fragment.main.list.IEditAble
    public void startEdit() {
        super.startEdit();
        ActionExpandableListView listView = getListView();
        listView.setDragListener(null);
        listView.setDropListener(null);
    }

    @Override // com.sds.android.ttpod.fragment.main.list.MediaListFragment, com.sds.android.ttpod.fragment.main.list.IEditAble
    public void stopEdit() {
        super.stopEdit();
        setDragAndDropListener();
    }

    private void setDragAndDropListener() {
        ActionExpandableListView listView = getListView();
        listView.setDragListener(new DraggableListView.InterfaceC2177b() { // from class: com.sds.android.ttpod.fragment.main.list.DraggableMediaListFragment.1
            @Override // com.sds.android.ttpod.widget.DraggableListView.InterfaceC2177b
            /* renamed from: a */
            public void mo1780a(int i, int i2) {
                if (i >= 0 && i2 >= 0 && i != i2 && i2 < DraggableMediaListFragment.this.bottomLimit() && i < DraggableMediaListFragment.this.bottomLimit()) {
                    DraggableMediaListFragment.this.mExchangeOrderList.add(new int[]{i, i2});
                }
            }
        });
        listView.setDropListener(new DraggableListView.InterfaceC2181f() { // from class: com.sds.android.ttpod.fragment.main.list.DraggableMediaListFragment.2
            @Override // com.sds.android.ttpod.widget.DraggableListView.InterfaceC2181f
            /* renamed from: a */
            public void mo1773a(int i, int i2) {
                List<MediaItem> mediaItemList = DraggableMediaListFragment.this.getMediaItemList();
                ArrayList arrayList = new ArrayList(DraggableMediaListFragment.this.mExchangeOrderList.size());
                String groupID = DraggableMediaListFragment.this.getGroupID();
                for (int[] iArr : DraggableMediaListFragment.this.mExchangeOrderList) {
                    MediaItem mediaItem = mediaItemList.get(iArr[0]);
                    MediaItem mediaItem2 = mediaItemList.get(iArr[1]);
                    arrayList.add(new ExchangeOrderEntity(groupID, mediaItem.getID(), mediaItem2.getID()));
                    mediaItemList.set(iArr[0], mediaItem2);
                    mediaItemList.set(iArr[1], mediaItem);
                }
                CommandCenter.getInstance().m4606a(new Command(CommandID.COMMIT_EXCHANGE_ORDER, DraggableMediaListFragment.this.getGroupID(), arrayList));
                DraggableMediaListFragment.this.mExchangeOrderList.clear();
                DraggableMediaListFragment.this.notifyDataSetChanged();
            }
        });
        listView.setDragStartViewId(R.id.drag_handle);
    }

    @Override // com.sds.android.ttpod.fragment.main.list.MediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setDragAndDropListener();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.MediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public void configFailedView(View view) {
        view.findViewById(R.id.no_data_action_view).setVisibility(View.GONE);
        ((TextView) view.findViewById(R.id.textview_load_failed)).setText(R.string.no_song_empty_list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.MediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public View getMediaItemView(MediaItem mediaItem, View view, ViewGroup viewGroup, int i) {
        View mediaItemView = super.getMediaItemView(mediaItem, view, viewGroup, i);
        MediaItemViewHolder mediaItemViewHolder = (MediaItemViewHolder) mediaItemView.getTag();
        mediaItemViewHolder.m6952m().setVisibility(isEditing() ? View.INVISIBLE : View.VISIBLE);
        ThemeUtils.m8175a(mediaItemViewHolder.m6952m(), (int) R.string.icon_drag_sort, ThemeElement.SONG_LIST_ITEM_TEXT);
        return mediaItemView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int bottomLimit() {
        if (getMediaItemList() != null) {
            return getMediaItemList().size();
        }
        return 0;
    }
}
