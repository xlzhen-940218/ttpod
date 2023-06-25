package com.sds.android.ttpod.fragment.skinmanager;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.ThemeManagementActivity;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.MessageDialog;
import com.sds.android.ttpod.component.soundsearch.IThemeEditable;
import com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.skin.SkinItem;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.utils.ThemeUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/* loaded from: classes.dex */
public class MyThemeFragment extends BaseThemeFragment implements IThemeEditable, IThemeEditable.InterfaceC1333a {
    private IThemeEditable.InterfaceC1333a mEditRequestListener;
    private IThemeEditable.InterfaceC1333a mParentEditRequestListener;
    private Map<Integer, SkinItem> mSelectItemHashMap = new LinkedHashMap();
    private Queue<SkinItem> mReadOnlyItems = new LinkedList();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.LOAD_SKIN_ERROR, ReflectUtils.m8375a(cls, "onLoadSkinError", new Class[0]));
        map.put(CommandID.DECODE_SKIN_THUMBNAIL_FINISHED, ReflectUtils.m8375a(cls, "onSkinThumbnailCreated", SkinItem.class));
        map.put(CommandID.UPDATE_ALL_LOCAL_SKIN_LIST, ReflectUtils.m8375a(cls, "updateDataListForAdapter", ArrayList.class));
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setForLocal();
        this.mSubClassTag = MyThemeFragment.class.getSimpleName();
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mEditRequestListener = this;
        FragmentActivity activity = getActivity();
        if (activity instanceof ThemeManagementActivity) {
            this.mParentEditRequestListener = (IThemeEditable.InterfaceC1333a) activity;
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
        Cache.getInstance().m3180c(getThemeDataList());
    }

    @Override // android.support.v4.app.Fragment
    public void onStop() {
        if (isInEditMode()) {
            toggleEditMode();
        }
        super.onStop();
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment
    protected ArrayList<SkinItem> loadDataFromCache() {
        return Cache.getInstance().m3143u();
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment
    protected CommandID getLoadDataCommandID() {
        return CommandID.LOAD_ALL_LOCAL_SKIN_LIST;
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment
    protected String getStatisticOrigin() {
        return "my";
    }

    public void onLoadSkinError() {
        PopupsUtils.m6760a((int) R.string.load_theme_error_tip);
        BaseThemeFragment.C1761a adapter = getAdapter();
        if (adapter != null) {
            adapter.m5350a();
            adapter.notifyDataSetChanged();
        }
    }

    public void updateDataListForAdapter(ArrayList<SkinItem> arrayList) {
        this.mCacheMode = false;
        if (checkIfReloadData(arrayList)) {
            setAdapterDataSource(arrayList);
            refreshEditButton();
        }
    }

    public void onSkinThumbnailCreated(SkinItem skinItem) {
        if (skinItem != null) {
            this.mThemeAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment
    protected void onThemeItemSelected(SkinItem skinItem) {
        if (this.mInEditMode) {
            if (isLocalUnSelectedSkinItem(skinItem)) {
                int hashCode = skinItem.hashCode();
                if (this.mSelectItemHashMap.containsKey(Integer.valueOf(hashCode))) {
                    this.mSelectItemHashMap.remove(Integer.valueOf(hashCode));
                } else {
                    this.mSelectItemHashMap.put(Integer.valueOf(hashCode), skinItem);
                }
                notifyDataSetChanged();
                tryNotifySelectedCountChanged();
                return;
            }
            PopupsUtils.m6760a((int) R.string.in_edit_mode);
            return;
        }
        checkSkinItem(skinItem);
    }

    private void showConfirmDeleteDialog() {
        @SuppressLint("StringFormatMatches") MessageDialog messageDialog = new MessageDialog(getActivity(), getString(R.string.confirm_delete_skin, this.mSelectItemHashMap.size()), new BaseDialog.InterfaceC1064a<MessageDialog>() { // from class: com.sds.android.ttpod.fragment.skinmanager.MyThemeFragment.1
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(MessageDialog messageDialog2) {
                int selectedCount = MyThemeFragment.this.selectedCount();
                if (selectedCount > 0) {
                    Iterator it = MyThemeFragment.this.mSelectItemHashMap.keySet().iterator();
                    while (it.hasNext()) {
                        MyThemeFragment.this.deleteItem((SkinItem) MyThemeFragment.this.mSelectItemHashMap.get(it.next()));
                        it.remove();
                    }
                    //ThemeStatistic.m4900a(selectedCount);
                }
                MyThemeFragment.this.tryNotifyStopEditRequested();
            }
        }, (BaseDialog.InterfaceC1064a<MessageDialog>) null);
        messageDialog.setTitle(R.string.delete_theme);
        messageDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteItem(SkinItem skinItem) {
        if (deleteSkin(skinItem.getPath(), skinItem.m3575a()).booleanValue()) {
            this.mThemeAdapter.m5331c(skinItem);
            performSkinItemStateChange(skinItem.m3565g(), 4);
        }
        performSkinDeleted(skinItem);
    }

    private Boolean deleteSkin(String str, int i) {
        return (Boolean) CommandCenter.getInstance().m4602a(new Command(CommandID.DELETE_SKIN, str, Integer.valueOf(i)), Boolean.class);
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment
    protected void initThemeAdapter() {
        this.mThemeAdapter = new C1743a();
    }

    @Override // com.sds.android.ttpod.component.soundsearch.IThemeEditable
    public void selectAll() {
        ArrayList<SkinItem> m5332c = this.mThemeAdapter.m5332c();
        this.mSelectItemHashMap.clear();
        Iterator<SkinItem> it = m5332c.iterator();
        while (it.hasNext()) {
            SkinItem next = it.next();
            if (isLocalUnSelectedSkinItem(next)) {
                this.mSelectItemHashMap.put(Integer.valueOf(next.hashCode()), next);
            }
        }
        tryNotifySelectedCountChanged();
        notifyDataSetChanged();
    }

    @Override // com.sds.android.ttpod.component.soundsearch.IThemeEditable
    public void selectNone() {
        this.mSelectItemHashMap.clear();
        tryNotifySelectedCountChanged();
        notifyDataSetChanged();
    }

    public void startEdit() {
        PopupsUtils.m6743a(getActivity(), getView(), this.mEditRequestListener);
        ((ViewGroup.MarginLayoutParams) this.mThemeListView.getLayoutParams()).bottomMargin = getResources().getDimensionPixelSize(R.dimen.playcontrol_bar_height);
        notifyDataSetChanged();
    }

    public void stopEdit() {
        if (isViewAccessAble()) {
            PopupsUtils.m6706c();
            ((ViewGroup.MarginLayoutParams) this.mThemeListView.getLayoutParams()).bottomMargin = 0;
            this.mSelectItemHashMap.clear();
            notifyDataSetChanged();
        }
    }

    @Override // com.sds.android.ttpod.component.soundsearch.IThemeEditable
    public int selectedCount() {
        return this.mSelectItemHashMap.size();
    }

    @Override // com.sds.android.ttpod.component.soundsearch.IThemeEditable
    public int totalCount() {
        if (this.mThemeAdapter == null) {
            return 0;
        }
        return getLocalItemCount();
    }

    private int getLocalItemCount() {
        int i = 0;
        Iterator<SkinItem> it = this.mThemeAdapter.m5332c().iterator();
        while (true) {
            int i2 = i;
            if (it.hasNext()) {
                i = isLocalUnSelectedSkinItem(it.next()) ? i2 + 1 : i2;
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

    private void tryNotifySelectedCountChanged() {
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
        if (selectedCount() > 0) {
            remove();
        }
    }

    @Override // com.sds.android.ttpod.component.soundsearch.IThemeEditable.InterfaceC1333a
    public void onSelectedCountChanged() {
    }

    @Override // com.sds.android.ttpod.component.soundsearch.IThemeEditable.InterfaceC1333a
    public void onStopEditRequested() {
        toggleEditMode();
    }

    /* renamed from: com.sds.android.ttpod.fragment.skinmanager.MyThemeFragment$a */
    /* loaded from: classes.dex */
    private class C1743a extends BaseThemeFragment.C1761a {
        private C1743a() {
            super();
        }

        @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment.C1761a
        /* renamed from: a */
        protected void mo5325a(SkinItem skinItem, ImageView imageView) {
            Bitmap m4748a = ImageCacheUtils.m4748a(skinItem.getPath(), this.f5552b, this.f5553c);
            if (m4748a != null) {
                imageView.setImageBitmap(m4748a);
                return;
            }
            imageView.setImageResource(R.drawable.img_skin_default_thumbnail);
            m5393d(skinItem);
        }

        /* renamed from: d */
        private void m5393d(SkinItem skinItem) {
            CommandCenter.getInstance().m4596b(new Command(CommandID.DECODE_SKIN_THUMBNAIL, skinItem));
        }

        @SuppressLint("WrongConstant")
        @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment.C1761a
        /* renamed from: b */
        protected void mo5336b(SkinItem skinItem, ImageView imageView) {
            int i = 0;
            if (imageView != null && skinItem != null) {
                if (!this.f5555e.equals(skinItem.getPath())) {
                    if (!MyThemeFragment.this.mInEditMode) {
                        i = 4;
                    } else if (MyThemeFragment.this.mSelectItemHashMap.get(Integer.valueOf(skinItem.hashCode())) == null) {
                        i = 4;
                    }
                } else {
                    this.f5554d = skinItem;
                }
                imageView.setVisibility(i);
            }
        }
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment, com.sds.android.ttpod.component.soundsearch.EditModeToggle
    public void toggleEditMode() {
        super.toggleEditMode();
        this.mInEditMode = !this.mInEditMode;
        if (this.mInEditMode) {
            removeReadOnlyItem();
            startEdit();
            return;
        }
        restoreReadOnlyItem();
        stopEdit();
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment, com.sds.android.ttpod.component.soundsearch.EditModeToggle
    public boolean hasEditableContent() {
        if (this.mThemeData == null) {
            return false;
        }
        if (this.mThemeAdapter.m5326e() > this.mInternalThemeCount + 1) {
            return true;
        }
        Iterator<SkinItem> it = this.mThemeData.iterator();
        while (it.hasNext()) {
            SkinItem next = it.next();
            if (next.m3575a() != 1 && !this.mThemeAdapter.m5344a(next)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment
    protected void refreshEditButton() {
        this.mInternalThemeCount = ThemeUtils.m8159d();
        FragmentActivity activity = getActivity();
        if (activity instanceof ThemeManagementActivity) {
            ((ThemeManagementActivity) activity).refreshEditButton();
        }
    }

    private void removeReadOnlyItem() {
        for (int i = 0; i < this.mInternalThemeCount; i++) {
            this.mReadOnlyItems.offer(this.mThemeData.remove(0));
        }
        SkinItem m5328d = this.mThemeAdapter.m5328d();
        if (!this.mReadOnlyItems.contains(m5328d)) {
            this.mThemeData.remove(m5328d);
            this.mReadOnlyItems.offer(m5328d);
        }
    }

    private void restoreReadOnlyItem() {
        int i = 0;
        while (true) {
            SkinItem poll = this.mReadOnlyItems.poll();
            if (poll != null) {
                this.mThemeData.add(i, poll);
                i++;
            } else {
                return;
            }
        }
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment
    protected void updateSkinInfoForThemeName(String str, int i) {
        ArrayList<SkinItem> themeDataList = getThemeDataList();
        if (themeDataList != null && str != null && i != 4) {
            SkinItem skinItemForSkinFileName = getSkinItemForSkinFileName(str);
            if (skinItemForSkinFileName == null && i == 0) {
                themeDataList.add(new SkinItem(sOnlineSkinInfoMap.get(str)));
            } else if (skinItemForSkinFileName != null) {
                skinItemForSkinFileName.m3574a(i);
            }
        }
    }

    private SkinItem getSkinItemForSkinFileName(String str) {
        ArrayList<SkinItem> themeDataList = getThemeDataList();
        if (themeDataList == null) {
            return null;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= themeDataList.size()) {
                return null;
            }
            SkinItem skinItem = themeDataList.get(i2);
            if (!skinItem.m3564h().equals(str)) {
                i = i2 + 1;
            } else {
                return skinItem;
            }
        }
    }
}
