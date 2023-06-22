package com.sds.android.ttpod.fragment.main.list;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.mediascan.MediaScanActivity;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.EditTextDialog;
import com.sds.android.ttpod.component.p087d.p088a.MessageDialog;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.p107a.LocalStatistic;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.p106a.p107a.SUserUtils;
import com.sds.android.ttpod.media.mediastore.GroupItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.utils.GroupItemUtils;
import com.sds.android.ttpod.utils.ThemeUtils;
import java.util.ArrayList;
import java.util.Collection;

/* loaded from: classes.dex */
public class SubMediaListFragment extends SlidingClosableFragment implements IEditAble.InterfaceC1677a {
    public static final String KEY_GROUP_NAME = "group_name";
    private MediaListFragment mMediaListFragment;
    private ActionBarController.InterfaceC1072b mOnActionClickListener = new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.fragment.main.list.SubMediaListFragment.1
        @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
        /* renamed from: a */
        public void mo5433a(ActionBarController.C1070a c1070a) {
            if (c1070a == SubMediaListFragment.this.mSelectAction) {
                if (c1070a.m7150f() == null) {
                    SubMediaListFragment.this.mMediaListFragment.selectAll();
                } else {
                    SubMediaListFragment.this.mMediaListFragment.selectNone();
                }
            }
        }
    };
    private ActionBarController.C1070a mSelectAction;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        DebugUtils.m8426a(getArguments(), "Arguments");
        this.mSelectAction = getActionBarController().m7199a((Drawable) null);
        ThemeUtils.m8170a(this.mSelectAction, (int) R.string.icon_unchecked, ThemeElement.TOP_BAR_TEXT);
        this.mSelectAction.m7172a();
        this.mSelectAction.m7167a(this.mOnActionClickListener);
        updateActionBar();
        return layoutInflater.inflate(R.layout.fragment_local_sub_media_list, (ViewGroup) null);
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needMenuAction() {
        return true;
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        ThemeManager.m3269a(getView().findViewById(R.id.content_local_media_list), ThemeElement.BACKGROUND_MASK);
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mMediaListFragment = initMediaListFragment();
        this.mMediaListFragment.setEditRequestListener(this);
    }

    protected MediaListFragment initMediaListFragment() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            setPage(MediaListFragment.pageFromGroupId(arguments.getString(AbsMediaListFragment.KEY_GROUP_ID)));
        }
        MediaListFragment mediaListFragment = (MediaListFragment) Fragment.instantiate(getActivity(), selectMediaListFragmentClassName(), arguments);
        mediaListFragment.setNoDataViewMessage(R.string.icon_male, R.string.no_song_go_recommend);
        getChildFragmentManager().beginTransaction().replace(R.id.content_local_media_list, mediaListFragment).commitAllowingStateLoss();
        return mediaListFragment;
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mMediaListFragment = null;
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected void onSearchActionClicked() {
        if (this.mMediaListFragment != null) {
            this.mMediaListFragment.search();
            LocalStatistic.m5127af();
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.list.IEditAble.InterfaceC1677a
    public void onRemoveRequested() {
        if (isViewAccessAble() && this.mMediaListFragment.selectedCount() > 0) {
            this.mMediaListFragment.remove();
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.list.IEditAble.InterfaceC1677a
    public void onAddToRequested() {
        if (isViewAccessAble() && this.mMediaListFragment.selectedCount() > 0) {
            this.mMediaListFragment.addTo();
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.list.IEditAble.InterfaceC1677a
    public void onSendToRequested() {
        if (isViewAccessAble() && this.mMediaListFragment.selectedCount() > 0) {
            this.mMediaListFragment.sendTo();
        }
    }

    public void onStopEditRequested() {
        stopEdit();
    }

    @Override // com.sds.android.ttpod.fragment.main.list.IEditAble.InterfaceC1677a
    public void onSelectedCountChanged() {
        if (isViewAccessAble()) {
            updateActionBar();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onBackPressed() {
        if (PopupsUtils.m6716b()) {
            stopEdit();
        } else {
            super.onBackPressed();
        }
    }

    private void startEdit() {
        if (isViewAccessAble()) {
            PopupsUtils.m6742a(getActivity(), getView(), this);
            updateActionBar();
            this.mMediaListFragment.startEdit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopEdit() {
        if (isViewAccessAble()) {
            PopupsUtils.m6706c();
            updateActionBar();
            this.mMediaListFragment.stopEdit();
        }
    }

    private void updateActionBar() {
        ActionBarController actionBarController = getActionBarController();
        if (PopupsUtils.m6716b() && this.mMediaListFragment != null) {
            hideFixedAction();
            if (this.mMediaListFragment.totalCount() != 0 && this.mMediaListFragment.totalCount() == this.mMediaListFragment.selectedCount()) {
                this.mSelectAction.m7166a((Object) this.mSelectAction);
                ThemeUtils.m8170a(this.mSelectAction, (int) R.string.icon_checked, ThemeElement.TOP_BAR_TEXT);
            } else {
                this.mSelectAction.m7166a((Object) null);
                ThemeUtils.m8170a(this.mSelectAction, (int) R.string.icon_unchecked, ThemeElement.TOP_BAR_TEXT);
            }
            this.mSelectAction.m7163b();
            actionBarController.m7193a((CharSequence) getResources().getString(R.string.select_media_with_count, Integer.valueOf(this.mMediaListFragment.selectedCount())));
            return;
        }
        showFixedAction();
        this.mSelectAction.m7172a();
        actionBarController.m7193a((CharSequence) getArguments().getString(KEY_GROUP_NAME));
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected Collection<ActionItem> onCreateDropDownMenu() {
        boolean z = true;
        SUserUtils.m4956a(SAction.ACTION_OPEN_LOCAL_DROP_MENU, SPage.PAGE_NONE);
        String string = getArguments().getString(AbsMediaListFragment.KEY_GROUP_ID);
        ArrayList arrayList = new ArrayList();
        boolean z2 = PopupsUtils.m6716b() && this.mMediaListFragment != null;
        boolean isEmpty = this.mMediaListFragment != null ? this.mMediaListFragment.isEmpty() : true;
        if (!string.startsWith(MediaStorage.GROUP_ID_ARTIST_PREFIX) && !string.startsWith(MediaStorage.GROUP_ID_ALBUM_PREFIX) && !string.startsWith(MediaStorage.GROUP_ID_GENRE_PREFIX) && !string.startsWith(MediaStorage.GROUP_ID_FOLDER_PREFIX)) {
            z = false;
        }
        if (!z2 && z) {
            arrayList.add(new ActionItem(4, 0, (int) R.string.menu_scan_media));
        }
        if (!z2 && string.startsWith(MediaStorage.GROUP_ID_CUSTOM_PREFIX) && !string.equals(MediaStorage.GROUP_ID_RECENTLY_PLAY) && !string.equals(MediaStorage.GROUP_ID_RECENTLY_ADD)) {
            arrayList.add(new ActionItem(17, 0, (int) R.string.menu_add_meida));
        }
        if (!z2 && !isEmpty) {
            if (string.equals(MediaStorage.GROUP_ID_RECENTLY_PLAY)) {
                arrayList.add(new ActionItem(22, 0, (int) R.string.menu_clear_recent_play_list));
            } else {
                arrayList.add(new ActionItem(15, 0, (int) R.string.menu_batch_operate));
            }
        }
        if (!string.startsWith(MediaStorage.GROUP_ID_ALBUM_PREFIX) && !string.equals(MediaStorage.GROUP_ID_RECENTLY_PLAY) && !string.equals(MediaStorage.GROUP_ID_RECENTLY_ADD) && !string.equals(MediaStorage.GROUP_ID_KTV) && !isEmpty) {
            arrayList.add(new ActionItem(6, 0, (int) R.string.menu_sort_by_title).m7009a((Object) 7));
            arrayList.add(new ActionItem(6, 0, (int) R.string.menu_sort_by_add_time).m7009a((Object) 10));
        }
        if (!z2 && !isEmpty && (string.startsWith(MediaStorage.GROUP_ID_ALBUM_PREFIX) || string.startsWith(MediaStorage.GROUP_ID_FOLDER_PREFIX) || string.startsWith(MediaStorage.GROUP_ID_ARTIST_PREFIX))) {
            arrayList.add(new ActionItem(21, 0, (int) R.string.menu_add_to_songlist));
        }
        if (!z2 && string.startsWith(MediaStorage.GROUP_ID_CUSTOM_PREFIX) && !string.equals(MediaStorage.GROUP_ID_RECENTLY_PLAY) && !string.equals(MediaStorage.GROUP_ID_RECENTLY_ADD) && !string.equals(MediaStorage.GROUP_ID_KTV)) {
            arrayList.add(new ActionItem(23, 0, (int) R.string.menu_rename_songlist));
            arrayList.add(new ActionItem(24, 0, (int) R.string.menu_delete_songlist));
        }
        return arrayList;
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.fragment.base.OnDropdownMenuClickListener
    public void onDropDownMenuClicked(int i, ActionItem actionItem) {
        super.onDropDownMenuClicked(i, actionItem);
        String string = getArguments().getString(AbsMediaListFragment.KEY_GROUP_ID);
        String string2 = getArguments().getString(KEY_GROUP_NAME);
        switch (i) {
            case 4:
                SUserUtils.m4956a(SAction.ACTION_MENU_SCAN_MUSIC, SPage.PAGE_SCAN_MUSIC);
                startActivity(new Intent(getActivity(), MediaScanActivity.class));
                return;
            case 5:
                this.mMediaListFragment.search();
                return;
            case 6:
                if (this.mMediaListFragment != null) {
                    this.mMediaListFragment.order(((Number) actionItem.m7004f()).intValue());
                    return;
                }
                return;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 16:
            case 18:
            case 19:
            case 20:
            default:
                return;
            case 15:
                if (MediaStorage.GROUP_ID_RECENTLY_ADD.equals(string)) {
                    LocalStatistic.m5115ar();
                }
                SUserUtils.m4956a(SAction.ACTION_MENU_BATCH_OPERATE, SPage.PAGE_NONE);
                startEdit();
                return;
            case 17:
                SUserUtils.m4956a(SAction.ACTION_MENU_ADD_MEDIA, SPage.PAGE_NONE);
                if (this.mMediaListFragment instanceof DraggableMediaListFragment) {
                    ((DraggableMediaListFragment) this.mMediaListFragment).addMedia(false);
                }
                if (GroupItemUtils.m8267b(string)) {
                    LocalStatistic.m5120am();
                    return;
                }
                return;
            case 21:
                SUserUtils.m4956a(SAction.ACTION_LOCAL_SONG_DETAIL_ADD_TO_SONGLIST, SPage.PAGE_NONE);
                PopupsUtils.m6710b(getActivity(), FileUtils.m8401k(string2), new BaseDialog.InterfaceC1064a<EditTextDialog>() { // from class: com.sds.android.ttpod.fragment.main.list.SubMediaListFragment.2
                    @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                    /* renamed from: a  reason: avoid collision after fix types in other method */
                    public void mo2038a(EditTextDialog editTextDialog) {
                        if (SubMediaListFragment.this.mMediaListFragment != null && !SubMediaListFragment.this.mMediaListFragment.isEmpty()) {
                            SUserUtils.m4956a(SAction.ACTION_LOCAL_SONG_DETAIL_ADD_TO_SONGLIST_SURE, SPage.PAGE_NONE);
                            CommandCenter.m4607a().m4606a(new Command(CommandID.ADD_MEDIA_ITEM_LIST, (String) CommandCenter.m4607a().m4602a(new Command(CommandID.ADD_GROUP, editTextDialog.m6902c(0).m6896d().toString()), String.class), SubMediaListFragment.this.mMediaListFragment.getMediaItemList()));
                            PopupsUtils.m6760a((int) R.string.add_to_main_success);
                        }
                    }
                });
                return;
            case 22:
                if (this.mMediaListFragment != null) {
                    this.mMediaListFragment.removeAll();
                    LocalStatistic.m5116aq();
                    SUserUtils.m4956a(SAction.ACTION_MENU_CLEAR_PLAYLIST, SPage.PAGE_NONE);
                    return;
                }
                return;
            case 23:
                rename(string, string2);
                if (GroupItemUtils.m8267b(string)) {
                    LocalStatistic.m5119an();
                    SUserUtils.m4956a(SAction.ACTION_MENU_RENAME_SONGLIST, SPage.PAGE_NONE);
                    return;
                }
                return;
            case 24:
                remove(string);
                if (GroupItemUtils.m8267b(string)) {
                    SUserUtils.m4956a(SAction.ACTION_MENU_DELETE_SONGLIST, SPage.PAGE_NONE);
                    LocalStatistic.m5118ao();
                    return;
                }
                return;
        }
    }

    private void rename(final String str, String str2) {
        EditTextDialog editTextDialog = new EditTextDialog(getActivity(), new EditTextDialog.C1144a[]{new EditTextDialog.C1144a(1, "", str2, getActivity().getString(R.string.rename_hint))}, R.string.save, new BaseDialog.InterfaceC1064a<EditTextDialog>() { // from class: com.sds.android.ttpod.fragment.main.list.SubMediaListFragment.3
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
                    GroupItem groupItem = new GroupItem(m6902c.m6896d().toString(), str, Integer.valueOf(SubMediaListFragment.this.mMediaListFragment != null ? SubMediaListFragment.this.mMediaListFragment.totalCount() : 0));
                    SubMediaListFragment.this.getArguments().putString(SubMediaListFragment.KEY_GROUP_NAME, groupItem.getName());
                    SubMediaListFragment.this.getActionBarController().m7193a((CharSequence) groupItem.getName());
                    CommandCenter.m4607a().m4606a(new Command(CommandID.UPDATE_GROUP_ITEM, groupItem));
                }
            }
        }, null);
        editTextDialog.setTitle(R.string.rename);
        editTextDialog.show();
    }

    private void remove(final String str) {
        MessageDialog messageDialog = new MessageDialog(getActivity(), getActivity().getString(R.string.delete_message), new BaseDialog.InterfaceC1064a<MessageDialog>() { // from class: com.sds.android.ttpod.fragment.main.list.SubMediaListFragment.4
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(MessageDialog messageDialog2) {
                CommandCenter.m4607a().m4606a(new Command(CommandID.DELETE_GROUP, str));
                SubMediaListFragment.this.finish();
            }
        }, (BaseDialog.InterfaceC1064a<MessageDialog>) null);
        messageDialog.setTitle(R.string.menu_delete_songlist);
        messageDialog.show();
    }

    protected String selectMediaListFragmentClassName() {
        return MediaListFragment.class.getName();
    }

    protected MediaListFragment getMediaListFragment() {
        return this.mMediaListFragment;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment
    public void onSlidingClosed() {
        if (PopupsUtils.m6716b()) {
            new Handler().post(new Runnable() { // from class: com.sds.android.ttpod.fragment.main.list.SubMediaListFragment.5
                @Override // java.lang.Runnable
                public void run() {
                    SubMediaListFragment.this.stopEdit();
                }
            });
        }
        super.onSlidingClosed();
    }

    public static String selectSubMediaListFragmentClassName(String str) {
        if (GroupItemUtils.m8267b(str)) {
            return DraggableSubMediaListFragment.class.getName();
        }
        if (StringUtils.m8344a(str, MediaStorage.GROUP_ID_FAV)) {
            return FavoriteSubMediaListFragment.class.getName();
        }
        return SubMediaListFragment.class.getName();
    }
}
