package com.sds.android.ttpod.fragment.skinmanager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.BackgroundManagementActivity;
import com.sds.android.ttpod.activities.user.PickImageHelper;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.MessageDialog;
import com.sds.android.ttpod.component.soundsearch.EditModeToggle;
import com.sds.android.ttpod.component.soundsearch.IThemeEditable;
import com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import com.sds.android.ttpod.framework.modules.theme.BackgroundItem;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.StateView;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/* loaded from: classes.dex */
public class MyBackgroundFragment extends BackgroundBaseFragment implements EditModeToggle, IThemeEditable, IThemeEditable.InterfaceC1333a {
    private static final String KEY_REQUEST_CODE = "request_code";
    public static final String LOCAL_BACKGROUND_IMAGE_PATH_FORMAT = TTPodConfig.m5293o() + File.separator + "users-%d.jpg";
    private static final int REQUEST_IMAGE_BACKGROUND = 1;
    private Activity mActivity;
    private int mCachedRequestCode;
    private IThemeEditable.InterfaceC1333a mEditRequestListener;
    private View mHeaderView;
    private IThemeEditable.InterfaceC1333a mParentEditRequestListener;
    private EditModeToggle mParentEditToggle;
    private PickImageHelper mPickImageHelper;
    private String mUserBackgroundName;
    private Map<Integer, BackgroundItem> mSelectItemHashMap = new LinkedHashMap();
    private Queue<BackgroundItem> mReadOnlyItems = new LinkedList();
    private View.OnLongClickListener mOnBackgroundLongClickListener = new View.OnLongClickListener() { // from class: com.sds.android.ttpod.fragment.skinmanager.MyBackgroundFragment.3
        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            if (!MyBackgroundFragment.this.mInEditMode) {
                if (MyBackgroundFragment.this.mParentEditToggle != null) {
                    MyBackgroundFragment.this.mParentEditToggle.toggleEditMode();
                } else {
                    MyBackgroundFragment.this.toggleEditMode();
                }
            }
            return true;
        }
    };

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment
    protected BackgroundBaseFragment.C1752a initAdapter() {
        return new C1740a(getActivity().getLayoutInflater(), Preferences.m3034X());
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mEditRequestListener = this;
        FragmentActivity activity = getActivity();
        if (activity instanceof BackgroundManagementActivity) {
            this.mParentEditToggle = (EditModeToggle) activity;
            this.mParentEditRequestListener = (IThemeEditable.InterfaceC1333a) activity;
            ThemeUtils.m8181a(view);
        }
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment, android.support.v4.app.Fragment
    public void onStop() {
        if (isInEditMode()) {
            toggleEditMode();
        }
        super.onStop();
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment
    protected void initListViewHeader() {
        this.mHeaderView = View.inflate(getActivity(), R.layout.fragment_background_header_layout, null);
        this.mBackgroundListView.addHeaderView(this.mHeaderView);
        ((Button) this.mHeaderView.findViewById(R.id.button_from_gallery)).setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.skinmanager.MyBackgroundFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!MyBackgroundFragment.this.isInEditMode()) {
                    MyBackgroundFragment.this.getPickImageHelper().m7711a(MyBackgroundFragment.this, MyBackgroundFragment.this.getString(R.string.add_background), 1, DisplayUtils.m7225c(), DisplayUtils.m7224d());
                    //ThemeStatistic.m4881k();
                    return;
                }
                MyBackgroundFragment.this.promptInEditMode();
            }
        });
        ((Button) this.mHeaderView.findViewById(R.id.button_take_picture)).setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.skinmanager.MyBackgroundFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!MyBackgroundFragment.this.isInEditMode()) {
                    MyBackgroundFragment.this.getPickImageHelper().m7708b(MyBackgroundFragment.this, MyBackgroundFragment.this.getString(R.string.add_background), 1, DisplayUtils.m7225c(), DisplayUtils.m7224d());
                    //ThemeStatistic.m4880l();
                    return;
                }
                MyBackgroundFragment.this.promptInEditMode();
            }
        });
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment
    protected void initListViewFooter() {
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment
    public void updateBackground(Drawable drawable) {
        if (getActivity() instanceof BackgroundManagementActivity) {
            this.mBackgroundAdapter.m5359d();
            notifyDataSetChanged();
            return;
        }
        super.updateBackground(drawable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_LOCAL_BACKGROUND_LIST, ReflectUtils.m8375a(cls, "updateBackgroundList", ArrayList.class));
        map.put(CommandID.LOAD_SKIN_FINISHED, ReflectUtils.m8375a(cls, "loadSkinFinished", SkinCache.class));
    }

    @Override // android.support.v4.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mPickImageHelper = new PickImageHelper(getActivity());
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z && this.mBackgroundAdapter != null && this.mBackgroundAdapter.getCount() > 0 && this.mBackgroundAdapter.m5359d()) {
            this.mBackgroundAdapter.notifyDataSetChanged();
        }
        if (!z && isInEditMode()) {
            toggleEditMode();
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            bundle.putInt(KEY_REQUEST_CODE, this.mCachedRequestCode);
            getPickImageHelper().m7715a(bundle);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            switch (i) {
                case 1:
                    this.mUserBackgroundName = String.format(LOCAL_BACKGROUND_IMAGE_PATH_FORMAT, Integer.valueOf(getValidUserBackgroundNum()));
                    cropPhoto(i, intent);
                    this.mCachedRequestCode = i;
                    return;
                case 2:
                default:
                    return;
                case 3:
                    if (this.mCachedRequestCode == 1 && isAdded()) {
                        setAddedBackground(this.mUserBackgroundName);
                        return;
                    }
                    return;
            }
        }
    }

    public void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            this.mCachedRequestCode = bundle.getInt(KEY_REQUEST_CODE, this.mCachedRequestCode);
            getPickImageHelper().m7709b(bundle);
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        loadBackgroundList();
    }

    private void loadBackgroundList() {
        CommandCenter.getInstance().m4606a(new Command(CommandID.LOAD_BACKGROUND_LIST, true));
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment
    public void updateBackgroundList(ArrayList<BackgroundItem> arrayList) {
        if (this.mBackgroundAdapter == null || arrayList == null) {
            if (this.mStateView != null) {
                this.mStateView.setState(StateView.EnumC2248b.FAILED);
                return;
            }
            return;
        }
        this.mBackgroundAdapter.m5365b().clear();
        this.mBackgroundAdapter.m5357e(new BackgroundItem(null, BackgroundItem.EnumC2011a.FOLLOW_SKIN));
        this.mBackgroundAdapter.m5366a(arrayList);
        if (isInEditMode()) {
            tryNotifySelectedCountChanged();
        }
        this.mStateView.setState(StateView.EnumC2248b.SUCCESS);
    }

    public void loadSkinFinished(SkinCache skinCache) {
        super.onThemeChanged();
        if (this.mBackgroundAdapter != null) {
            this.mBackgroundAdapter.m5365b().get(0).m3336a((Bitmap) null);
            this.mBackgroundAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment
    protected void refreshEditButton() {
        FragmentActivity activity = getActivity();
        if (activity instanceof BackgroundManagementActivity) {
            ((BackgroundManagementActivity) activity).refreshEditButton();
        }
    }

    @Override // com.sds.android.ttpod.component.soundsearch.EditModeToggle
    public boolean isInEditMode() {
        return this.mInEditMode;
    }

    @Override // com.sds.android.ttpod.component.soundsearch.EditModeToggle
    public void toggleEditMode() {
        this.mInEditMode = !this.mInEditMode;
        if (this.mInEditMode) {
            removeReadOnlyItem();
            startEdit();
        } else {
            restoreReadOnlyItem();
            stopEdit();
        }
        this.mBackgroundAdapter.notifyDataSetChanged();
    }

    private void removeReadOnlyItem() {
        ArrayList<BackgroundItem> m5365b = this.mBackgroundAdapter.m5365b();
        this.mReadOnlyItems.offer(m5365b.remove(0));
        BackgroundItem m5378a = this.mBackgroundAdapter.m5378a();
        if (!this.mReadOnlyItems.contains(m5378a)) {
            m5365b.remove(m5378a);
            this.mReadOnlyItems.offer(m5378a);
        }
    }

    private void restoreReadOnlyItem() {
        ArrayList<BackgroundItem> m5365b = this.mBackgroundAdapter.m5365b();
        int i = 0;
        while (true) {
            BackgroundItem poll = this.mReadOnlyItems.poll();
            if (poll != null) {
                m5365b.add(i, poll);
                i++;
            } else {
                return;
            }
        }
    }

    @Override // com.sds.android.ttpod.component.soundsearch.EditModeToggle
    public boolean hasEditableContent() {
        if (this.mBackgroundAdapter == null) {
            return false;
        }
        Iterator<BackgroundItem> it = this.mBackgroundAdapter.m5365b().iterator();
        while (it.hasNext()) {
            BackgroundItem next = it.next();
            if (!this.mBackgroundAdapter.m5371a(next) && isLocalBackground(next.m3337a())) {
                return true;
            }
        }
        return false;
    }

    public void setAttachedActivity(Activity activity) {
        this.mActivity = activity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PickImageHelper getPickImageHelper() {
        if (this.mPickImageHelper == null) {
            this.mPickImageHelper = new PickImageHelper(this.mActivity);
        }
        return this.mPickImageHelper;
    }

    private void setAddedBackground(String str) {
        this.mUserBackgroundName = "file://" + str;
        BackgroundItem backgroundItem = new BackgroundItem(this.mUserBackgroundName);
        this.mBackgroundAdapter.m5357e(backgroundItem);
        this.mBackgroundAdapter.m5364b(backgroundItem);
        CommandCenter.getInstance().m4596b(new Command(CommandID.SET_BACKGROUND, this.mUserBackgroundName));
    }

    private void cropPhoto(int i, Intent intent) {
        if (i == 1) {
            getPickImageHelper().m7713a(this, intent == null ? null : intent.getData(), this.mUserBackgroundName);
        }
    }

    private int getValidUserBackgroundNum() {
        int i = 0;
        while (new File(String.format(LOCAL_BACKGROUND_IMAGE_PATH_FORMAT, Integer.valueOf(i))).exists()) {
            i++;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void promptInEditMode() {
        PopupsUtils.m6760a((int) R.string.in_edit_mode);
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public boolean isSupportOfflineMode() {
        return false;
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment
    public void updateBkgDownloadingState(DownloadTaskInfo downloadTaskInfo) {
        if (DownloadTaskInfo.TYPE_BACKGROUND.equals(downloadTaskInfo.getType()) && downloadTaskInfo.getState().intValue() == 4) {
            BackgroundItem backgroundItem = (BackgroundItem) downloadTaskInfo.getTag();
            if (!this.mBackgroundAdapter.m5365b().contains(backgroundItem)) {
                if (this.mInEditMode) {
                    this.mReadOnlyItems.offer(backgroundItem);
                    return;
                }
                this.mBackgroundAdapter.m5357e(backgroundItem);
                this.mBackgroundAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override // com.sds.android.ttpod.component.soundsearch.IThemeEditable
    public void selectAll() {
        ArrayList<BackgroundItem> m5365b = this.mBackgroundAdapter.m5365b();
        this.mSelectItemHashMap.clear();
        Iterator<BackgroundItem> it = m5365b.iterator();
        while (it.hasNext()) {
            BackgroundItem next = it.next();
            if (isLocalUnSelectedBackgroundItem(next)) {
                this.mSelectItemHashMap.put(Integer.valueOf(next.hashCode()), next);
            }
        }
        notifyDataSetChanged();
    }

    @Override // com.sds.android.ttpod.component.soundsearch.IThemeEditable
    public void selectNone() {
        this.mSelectItemHashMap.clear();
        notifyDataSetChanged();
    }

    public void startEdit() {
        PopupsUtils.m6743a(getActivity(), getView(), this.mEditRequestListener);
        ((ViewGroup.MarginLayoutParams) this.mBackgroundListView.getLayoutParams()).bottomMargin = getResources().getDimensionPixelSize(R.dimen.playcontrol_bar_height);
        this.mBackgroundListView.removeHeaderView(this.mHeaderView);
        notifyDataSetChanged();
    }

    public void stopEdit() {
        if (isViewAccessAble()) {
            PopupsUtils.m6706c();
            ((ViewGroup.MarginLayoutParams) this.mBackgroundListView.getLayoutParams()).bottomMargin = 0;
            this.mSelectItemHashMap.clear();
            this.mBackgroundListView.setAdapter((ListAdapter) null);
            this.mBackgroundListView.addHeaderView(this.mHeaderView);
            this.mBackgroundListView.setAdapter((ListAdapter) this.mBackgroundAdapter);
            notifyDataSetChanged();
        }
    }

    @Override // com.sds.android.ttpod.component.soundsearch.IThemeEditable
    public int selectedCount() {
        return this.mSelectItemHashMap.size();
    }

    @Override // com.sds.android.ttpod.component.soundsearch.IThemeEditable
    public int totalCount() {
        if (this.mBackgroundAdapter == null) {
            return 0;
        }
        return getLocalItemCount();
    }

    private int getLocalItemCount() {
        int i = 0;
        Iterator<BackgroundItem> it = this.mBackgroundAdapter.m5365b().iterator();
        while (true) {
            int i2 = i;
            if (it.hasNext()) {
                i = isLocalUnSelectedBackgroundItem(it.next()) ? i2 + 1 : i2;
            } else {
                return i2;
            }
        }
    }

    public void remove() {
        if (selectedCount() > 0) {
            showConfirmDeleteDialog();
        }
    }

    private void showConfirmDeleteDialog() {
        MessageDialog messageDialog = new MessageDialog(getActivity(), getString(R.string.confirm_delete_background, Integer.valueOf(this.mSelectItemHashMap.size())), new BaseDialog.InterfaceC1064a<MessageDialog>() { // from class: com.sds.android.ttpod.fragment.skinmanager.MyBackgroundFragment.4
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(MessageDialog messageDialog2) {
                int selectedCount = MyBackgroundFragment.this.selectedCount();
                if (selectedCount > 0) {
                    Iterator it = MyBackgroundFragment.this.mSelectItemHashMap.keySet().iterator();
                    while (it.hasNext()) {
                        MyBackgroundFragment.this.deleteItem((BackgroundItem) MyBackgroundFragment.this.mSelectItemHashMap.get(it.next()));
                        it.remove();
                    }
                    //ThemeStatistic.m4897b(selectedCount);
                }
                MyBackgroundFragment.this.tryNotifyStopEditRequested();
            }
        }, (BaseDialog.InterfaceC1064a<MessageDialog>) null);
        messageDialog.setTitle(R.string.delete);
        messageDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryNotifySelectedCountChanged() {
        if (this.mParentEditRequestListener != null) {
            this.mParentEditRequestListener.onSelectedCountChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryNotifyStopEditRequested() {
        if (this.mParentEditRequestListener != null) {
            this.mParentEditRequestListener.onStopEditRequested();
        }
    }

    @Override // com.sds.android.ttpod.component.soundsearch.IThemeEditable.InterfaceC1333a
    public void onRemoveRequested() {
        remove();
    }

    @Override // com.sds.android.ttpod.component.soundsearch.IThemeEditable.InterfaceC1333a
    public void onSelectedCountChanged() {
    }

    @Override // com.sds.android.ttpod.component.soundsearch.IThemeEditable.InterfaceC1333a
    public void onStopEditRequested() {
        toggleEditMode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteItem(BackgroundItem backgroundItem) {
        CommandCenter.getInstance().m4596b(new Command(CommandID.DELETE_BACKGROUND, backgroundItem.toString()));
        this.mBackgroundAdapter.m5358d(backgroundItem);
        performBkgDeleted(backgroundItem);
    }

    /* renamed from: com.sds.android.ttpod.fragment.skinmanager.MyBackgroundFragment$a */
    /* loaded from: classes.dex */
    class C1740a extends BackgroundBaseFragment.C1752a {
        public C1740a(LayoutInflater layoutInflater, String str) {
            super(layoutInflater, str);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment.C1752a
        /* renamed from: a */
        public void mo5368a(BackgroundItem backgroundItem, ThemeViewHolder themeViewHolder, boolean z) {
            super.mo5368a(backgroundItem, themeViewHolder, z);
            View m5383h = themeViewHolder.m5383h();
            m5383h.setOnLongClickListener(MyBackgroundFragment.this.mOnBackgroundLongClickListener);
            m5383h.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.skinmanager.MyBackgroundFragment.a.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    BackgroundItem backgroundItem2 = (BackgroundItem) view.getTag();
                    if (backgroundItem2 != null && !backgroundItem2.toString().equals(MyBackgroundFragment.this.mBackgroundAdapter.m5362c())) {
                        if (MyBackgroundFragment.this.mInEditMode) {
                            if (!MyBackgroundFragment.this.isLocalUnSelectedBackgroundItem(backgroundItem2)) {
                                MyBackgroundFragment.this.promptInEditMode();
                                return;
                            }
                            int hashCode = backgroundItem2.hashCode();
                            if (MyBackgroundFragment.this.mSelectItemHashMap.containsKey(Integer.valueOf(hashCode))) {
                                MyBackgroundFragment.this.mSelectItemHashMap.remove(Integer.valueOf(hashCode));
                            } else {
                                MyBackgroundFragment.this.mSelectItemHashMap.put(Integer.valueOf(hashCode), backgroundItem2);
                            }
                            C1740a.this.notifyDataSetChanged();
                            MyBackgroundFragment.this.tryNotifySelectedCountChanged();
                            return;
                        }
                        MyBackgroundFragment.this.checkNormalStateItem(backgroundItem2);
                    }
                }
            });
        }

        @Override // com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment.C1752a
        /* renamed from: a */
        protected void mo5370a(BackgroundItem backgroundItem, ImageView imageView) {
            int i = 0;
            if (imageView != null && backgroundItem != null) {
                if (!m5362c().equals(backgroundItem.toString())) {
                    if (!MyBackgroundFragment.this.mInEditMode) {
                        i = 4;
                    } else if (MyBackgroundFragment.this.mSelectItemHashMap.get(Integer.valueOf(backgroundItem.hashCode())) == null) {
                        i = 4;
                    }
                } else {
                    m5361c(backgroundItem);
                }
                imageView.setVisibility(i);
            }
        }
    }
}
