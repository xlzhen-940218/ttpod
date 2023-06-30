package com.sds.android.ttpod.fragment.main;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.result.PostResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.EditTextDialog;
import com.sds.android.ttpod.component.p087d.p088a.MessageDialog;
import com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.BaseGroupListFragment;
import com.sds.android.ttpod.fragment.main.list.DraggableGroupListFragment;
import com.sds.android.ttpod.fragment.main.list.FavoriteSubMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.SubGroupListFragment;
import com.sds.android.ttpod.fragment.main.list.SubMediaListFragment;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.CommonResult;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.media.mediastore.GroupItem;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.utils.EntryUtils;
import com.sds.android.ttpod.utils.GroupItemUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class SubCustomGroupListFragment extends SubGroupListFragment {
    private static final String KTV_NAME = "我的KTV";
    private boolean mSynchronizing = false;

    @Override // com.sds.android.ttpod.fragment.main.list.SubGroupListFragment
    public DraggableGroupListFragment getFragmentInstance() {
        C1503a c1503a = new C1503a();
        c1503a.setArguments(getArguments());
        return c1503a;
    }

    @Override // com.sds.android.ttpod.fragment.main.list.SubGroupListFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected Collection<ActionItem> onCreateDropDownMenu() {
        //SUserUtils.m4956a(SAction.ACTION_OPEN_LOCAL_DROP_MENU, SPage.PAGE_NONE);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ActionItem(16, 0, (int) R.string.menu_sync_playlists));
        return arrayList;
    }

    @Override // com.sds.android.ttpod.fragment.main.list.SubGroupListFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.fragment.base.OnDropdownMenuClickListener
    public void onDropDownMenuClicked(int i, ActionItem actionItem) {
        super.onDropDownMenuClicked(i, actionItem);
        switch (i) {
            case 16:
                //SUserUtils.m4956a(SAction.ACTION_MENU_SONGLIST_SYNC, SPage.PAGE_NONE);
                if (Preferences.m2954aq() == null) {
                    EntryUtils.m8297a(true);
                } else if (!EnvironmentUtils.DeviceConfig.isConnected()) {
                    PopupsUtils.m6760a((int) R.string.network_unavailable);
                    return;
                } else {
                    PopupsUtils.m6760a((int) R.string.sync_favorite_playlists_start);
                    ((C1503a) super.getFragmentInstance()).onReloadData();
                    this.mSynchronizing = true;
                }
                //LocalStatistic.m5121al();
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.main.SubCustomGroupListFragment$a */
    /* loaded from: classes.dex */
    public class C1503a extends DraggableGroupListFragment {
        private List<GroupItem> mMusicCircleGroups;

        private C1503a() {
            this.mMusicCircleGroups = new ArrayList();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.fragment.main.list.GroupListFragment, com.sds.android.ttpod.fragment.main.list.BaseGroupListFragment, com.sds.android.ttpod.framework.base.BaseFragment
        public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
            super.onLoadCommandMap(map);
            Class<?> cls = getClass();
            map.put(CommandID.UPDATE_POST_INFO_LIST_BY_ID, ReflectUtils.loadMethod(cls, "updateMusicCircleLists", PostResult.class, String.class));
            map.put(CommandID.UPDATE_ADD_FAVORITE_POSTS, ReflectUtils.loadMethod(cls, "onMusicCirclePostsChanged", BaseResult.class, String.class));
            map.put(CommandID.UPDATE_REMOVE_FAVORITE_POSTS, ReflectUtils.loadMethod(cls, "onMusicCirclePostsChanged", BaseResult.class, String.class));
            map.put(CommandID.LOGIN_FINISHED, ReflectUtils.loadMethod(cls, "onLogFinished", CommonResult.class));
            map.put(CommandID.ADD_POSTS_TO_MEDIA_GROUP_FINISHED, ReflectUtils.loadMethod(cls, "addPostToMediaGroupFinished", new Class[0]));
            map.put(CommandID.UPDATE_LOCAL_AND_ONLINE_GROUP_LIST, ReflectUtils.loadMethod(cls, "updateGroupList", List.class));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.fragment.main.list.GroupListFragment, com.sds.android.ttpod.fragment.main.list.BaseGroupListFragment
        public void onGroupItemClicked(GroupItem groupItem) {
            String groupID = groupItem.getGroupID();
            if (groupID.startsWith(MediaStorage.GROUP_ID_MUSICCIRCLE_PREFIX)) {
                //SUserUtils.m4956a(SAction.ACTION_SONG_LIST_ONLINE, SPage.PAGE_MY_SONGLIST_ONLINE_DETAIL);
                Bundle bundle = new Bundle();
                bundle.putString(SubMediaListFragment.KEY_GROUP_NAME, groupItem.getName());
                bundle.putString(AbsMediaListFragment.KEY_GROUP_ID, groupID);
                launchFragment((BaseFragment) instantiate(getActivity(), FavoriteSubMediaListFragment.class.getName(), bundle));
                return;
            }
            //SUserUtils.m4956a(SAction.ACTION_SONG_LIST_LOCAL, SPage.PAGE_MY_SONGLIST_LOCAL_DETAIL);
            super.onGroupItemClicked(groupItem);
        }

        @Override // com.sds.android.ttpod.fragment.main.list.GroupListFragment
        protected void configNoDataView(IconTextView iconTextView, TextView textView, TextView textView2) {
            iconTextView.setText(R.string.icon_no_songlist);
            textView.setText(R.string.no_song_songlist);
            textView2.setVisibility(View.GONE);
        }

        @Override // com.sds.android.ttpod.fragment.main.list.BaseGroupListFragment
        protected void onGroupItemLongClicked(GroupItem groupItem) {
            if (!groupItem.getGroupID().startsWith(MediaStorage.GROUP_ID_KTV) && (groupItem instanceof GroupItem)) {
                showContextDialog(groupItem);
            }
        }

        @Override // com.sds.android.ttpod.fragment.main.list.GroupListFragment, com.sds.android.ttpod.fragment.main.list.BaseGroupListFragment
        public void onReloadData() {
            if (Preferences.m2954aq() != null) {
                CommandCenter.getInstance().postInvokeResult(new Command(CommandID.REQUEST_FAVORITE_SONG_LIST_POSTS, new Object[0]));
            } else {
                CommandCenter.getInstance().postInvokeResult(new Command(CommandID.QUERY_LOCAL_AND_ONLINE_GROUP_LIST, new Object[0]));
            }
        }

        public void onMusicCirclePostsChanged(BaseResult baseResult, String str) {
            CommandCenter.getInstance().execute(new Command(CommandID.QUERY_GROUP_ITEM_LIST, GroupType.CUSTOM_ONLINE));
        }

        public void updateMusicCircleLists(PostResult postResult, String str) {
            if ("favorite".equals(str)) {
                updateFavoritePosts(postResult.getDataList());
            }
        }

        @Override // com.sds.android.ttpod.fragment.main.list.GroupListFragment
        public void updateMediaLibraryChanged(String str) {
            if (str.startsWith(MediaStorage.GROUP_ID_MUSICCIRCLE_PREFIX)) {
                super.updateMediaLibraryChanged(str);
            }
        }

        @Override // com.sds.android.ttpod.fragment.main.list.GroupListFragment
        public void updateGroupList(GroupType groupType, List<GroupItem> list) {
            if (groupType == GroupType.CUSTOM_LOCAL && getGroupItemList() != null && list != null) {
                List<GroupItem> groupItemList = getGroupItemList();
                groupItemList.removeAll(list);
                list.addAll(groupItemList);
                super.updateGroupList(groupType, list);
            }
        }

        public void onLogFinished(CommonResult commonResult) {
            onReloadData();
        }

        public void addPostToMediaGroupFinished() {
            CommandCenter.getInstance().postInvokeResult(new Command(CommandID.QUERY_LOCAL_AND_ONLINE_GROUP_LIST, new Object[0]));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.fragment.main.list.DraggableGroupListFragment, com.sds.android.ttpod.fragment.main.list.BaseGroupListFragment
        public View getGroupItemView(GroupItem groupItem, View view, ViewGroup viewGroup) {
            View groupItemView = super.getGroupItemView(groupItem, view, viewGroup);
            BaseGroupListFragment.C1631b c1631b = (BaseGroupListFragment.C1631b) groupItemView.getTag();
            c1631b.m5462e().setVisibility(View.VISIBLE);
            if (groupItem.getGroupID().startsWith(MediaStorage.GROUP_ID_KTV)) {
                c1631b.m5463d().setVisibility(View.GONE);
            } else {
                c1631b.m5463d().setVisibility(View.VISIBLE);
            }
            return groupItemView;
        }

        @Override // com.sds.android.ttpod.fragment.main.list.DraggableGroupListFragment
        protected void showContextDialog(final GroupItem groupItem) {
            PopupsUtils.m6725a(getActivity(), groupItem.getGroupID().startsWith(MediaStorage.GROUP_ID_MUSICCIRCLE_PREFIX) ? new ActionItem[]{new ActionItem(30, (Drawable) null, (int) R.string.cancel_favorite)} : new ActionItem[]{new ActionItem(1, (Drawable) null, (int) R.string.menu_rename_songlist), new ActionItem(0, (Drawable) null, (int) R.string.menu_delete_songlist)}, groupItem.getTitleName().toString(), new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.fragment.main.SubCustomGroupListFragment.a.1
                @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
                /* renamed from: a */
                public void mo5409a(ActionItem actionItem, int i) {
                    if (actionItem.m7005e() == 0) {
                        C1503a.this.showSongListDeleteDialog(groupItem);
                    } else if (actionItem.m7005e() == 1) {
                        C1503a.this.showSongListRenameDialog(groupItem);
                    } else if (actionItem.m7005e() == 30) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(MediaStorage.getPostIdByGroupId(groupItem.getGroupID()));
                        C1503a.this.removeGroupItem(groupItem);
                        CommandCenter.getInstance().postInvokeResult(new Command(CommandID.REMOVE_FAVORITE_POSTS, arrayList, ""));
                    }
                }
            });
        }

        private void updateFavoritePosts(List<Post> list) {
            String buildMusicCircleFavGroupIDPrefix = MediaStorage.buildMusicCircleFavGroupIDPrefix();
            ArrayList arrayList = new ArrayList();
            for (Post post : list) {
                if (!MediaStorage.isGroupExisted(BaseApplication.getApplication(), buildMusicCircleFavGroupIDPrefix + post.getPostId())) {
                    arrayList.add(post);
                }
            }
            CommandCenter.getInstance().postInvokeResult(new Command(CommandID.ADD_POSTS_TO_MEDIA_GROUP, arrayList));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showSongListRenameDialog(final GroupItem groupItem) {
            //SUserUtils.m4956a(SAction.ACTION_MENU_RENAME_SONGLIST, SPage.PAGE_NONE);
            EditTextDialog editTextDialog = new EditTextDialog(getActivity(), new EditTextDialog.C1144a[]{new EditTextDialog.C1144a(1, "", groupItem.getTitleName(), getActivity().getString(R.string.rename_hint))}, R.string.save, new BaseDialog.InterfaceC1064a<EditTextDialog>() { // from class: com.sds.android.ttpod.fragment.main.SubCustomGroupListFragment.a.2
                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo2038a(EditTextDialog editTextDialog2) {
                    EditTextDialog.C1144a m6902c = editTextDialog2.m6902c(1);
                    if (m6902c != null) {
                        if (GroupItemUtils.m8268a(m6902c.m6896d().toString())) {
                            editTextDialog2.m7242f(false);
                            PopupsUtils.m6760a((int) R.string.playlist_name_existed);
                            return;
                        }
                        editTextDialog2.m7242f(true);
                        CommandCenter.getInstance().execute(new Command(CommandID.UPDATE_GROUP_ITEM, new GroupItem(m6902c.m6896d().toString(), groupItem.getGroupID(), groupItem.getCount())));
                    }
                }
            }, null);
            editTextDialog.setTitle(R.string.rename);
            editTextDialog.show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showSongListDeleteDialog(final GroupItem groupItem) {
            //SUserUtils.m4956a(SAction.ACTION_MENU_DELETE_SONGLIST, SPage.PAGE_NONE);
            MessageDialog messageDialog = new MessageDialog(getActivity(), getActivity().getString(R.string.delete_message), new BaseDialog.InterfaceC1064a<MessageDialog>() { // from class: com.sds.android.ttpod.fragment.main.SubCustomGroupListFragment.a.3
                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo2038a(MessageDialog messageDialog2) {
                    C1503a.this.removeGroupItem(groupItem);
                    CommandCenter.getInstance().postInvokeResult(new Command(CommandID.DELETE_GROUP, groupItem.getGroupID()));
                }
            }, (BaseDialog.InterfaceC1064a<MessageDialog>) null);
            messageDialog.setTitle(R.string.menu_delete_songlist);
            messageDialog.show();
        }
    }
}
