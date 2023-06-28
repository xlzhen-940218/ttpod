package com.sds.android.ttpod.activities.local;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.ActionBarActivity;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.IEditAble;
import com.sds.android.ttpod.fragment.main.list.MediaListFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.utils.ThemeUtils;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class MediaPickerActivity extends SlidingClosableActivity implements ThemeManager.InterfaceC2019b {
    private View mConfirmView;
    private List<MediaItem> mMediaItemsInGroup;
    private MediaPickerFragment mMediaPickerFragment;
    private String mPickerObjectGroupId;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_media_picker);
        setTitle(R.string.choose_music);
        Intent intent = getIntent();
        if (intent != null) {
            this.mPickerObjectGroupId = intent.getStringExtra(AbsMediaListFragment.KEY_GROUP_ID);
        }
        if (TextUtils.isEmpty(this.mPickerObjectGroupId)) {
            finish();
        } else {
            addContentFragment();
        }
    }

    private void addContentFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.playing));
        bundle.putString(AbsMediaListFragment.KEY_GROUP_ID, MediaStorage.GROUP_ID_ALL_LOCAL);
        this.mMediaPickerFragment = (MediaPickerFragment) Fragment.instantiate(this, MediaPickerFragment.class.getName(), bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, this.mMediaPickerFragment).commitAllowingStateLoss();
        this.mMediaItemsInGroup = MediaStorage.queryMediaItemList(this, this.mPickerObjectGroupId, MediaStorage.MEDIA_ORDER_BY_CUSTOM);
        this.mMediaPickerFragment.putSelectedMediaItem(this.mMediaItemsInGroup);
        this.mConfirmView = findViewById(R.id.button_confirm);
        this.mConfirmView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.local.MediaPickerActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Collection<MediaItem> selectedMediaItems = MediaPickerActivity.this.mMediaPickerFragment.getSelectedMediaItems();
                Iterator<MediaItem> it = selectedMediaItems.iterator();
                while (it.hasNext()) {
                    MediaItem next = it.next();
                    if (MediaPickerActivity.this.mMediaItemsInGroup.contains(next)) {
                        it.remove();
                        MediaPickerActivity.this.mMediaItemsInGroup.remove(next);
                    }
                }
                if (MediaPickerActivity.this.mMediaItemsInGroup.size() > 0) {
                    CommandCenter.getInstance().execute(new Command(CommandID.DELETE_MEDIA_ITEM_LIST, MediaPickerActivity.this.mPickerObjectGroupId, MediaPickerActivity.this.mMediaItemsInGroup, false));
                }
                if (selectedMediaItems.size() > 0) {
                    CommandCenter.getInstance().execute(new Command(CommandID.ADD_MEDIA_ITEM_LIST, MediaPickerActivity.this.mPickerObjectGroupId, selectedMediaItems));
                }
                MediaPickerActivity.this.finish();
            }
        });
    }

    @Override // com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        ThemeManager.m3270a(getRootView(), ThemeUtils.m8182a());
        ThemeManager.m3270a(findViewById(R.id.layout_background), ThemeManager.m3273a());
        getActionBarController().onThemeLoaded();
    }

    /* loaded from: classes.dex */
    public static class MediaPickerFragment extends MediaListFragment implements IEditAble.EditRequestListener {
        private ActionBarController.C1070a mSelectAction;

        @Override // com.sds.android.ttpod.fragment.main.list.MediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            setAutoSelectPlayingMedia(false);
            setEditRequestListener(this);
            this.mSelectAction = getActionBarController().m7199a((Drawable) null);
            this.mSelectAction.m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.activities.local.MediaPickerActivity.MediaPickerFragment.1
                @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
                /* renamed from: a */
                public void mo5433a(ActionBarController.C1070a c1070a) {
                    if (MediaPickerFragment.this.mSelectAction.m7150f() == null) {
                        MediaPickerFragment.this.selectAll();
                        MediaPickerFragment.this.setSelectAllAction();
                        return;
                    }
                    MediaPickerFragment.this.selectNone();
                    MediaPickerFragment.this.setSelectNoneAction();
                }
            });
        }

        @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, com.sds.android.ttpod.framework.base.BaseFragment
        public void onLoadFinished() {
            super.onLoadFinished();
            startEdit();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            resetActionBar();
        }

        private ActionBarController getActionBarController() {
            FragmentActivity activity = getActivity();
            if (activity instanceof ActionBarActivity) {
                return ((ActionBarActivity) activity).getActionBarController();
            }
            return null;
        }

        private void resetActionBar() {
            if (getActionBarController() != null) {
                int i = totalCount();
                int selectedCount = selectedCount();
                if (selectedCount > 0 && i > 0 && i <= selectedCount) {
                    setSelectAllAction();
                } else {
                    setSelectNoneAction();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSelectAllAction() {
            this.mSelectAction.m7166a((Object) this.mSelectAction);
            ThemeUtils.m8170a(this.mSelectAction, (int) R.string.icon_checked, ThemeElement.TOP_BAR_TEXT);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSelectNoneAction() {
            this.mSelectAction.m7166a((Object) null);
            ThemeUtils.m8170a(this.mSelectAction, (int) R.string.icon_unchecked, ThemeElement.TOP_BAR_TEXT);
        }

        @Override // com.sds.android.ttpod.fragment.main.list.MediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
        public void configFailedView(View view) {
            super.configFailedView(view);
            view.findViewById(R.id.no_data_action_view).setVisibility(View.GONE);
        }

        @Override // com.sds.android.ttpod.fragment.main.list.IEditAble.InterfaceC1677a
        public void onRemoveRequested() {
        }

        @Override // com.sds.android.ttpod.fragment.main.list.IEditAble.InterfaceC1677a
        public void onAddToRequested() {
        }

        @Override // com.sds.android.ttpod.fragment.main.list.IEditAble.InterfaceC1677a
        public void onSendToRequested() {
        }

        @Override // com.sds.android.ttpod.fragment.main.list.IEditAble.InterfaceC1677a
        public void onStopEditRequested() {
        }

        @Override // com.sds.android.ttpod.fragment.main.list.IEditAble.InterfaceC1677a
        public void onSelectedCountChanged() {
            getActionBarController().m7193a((CharSequence) getResources().getString(R.string.select_media_with_count, Integer.valueOf(selectedCount())));
        }
    }
}
