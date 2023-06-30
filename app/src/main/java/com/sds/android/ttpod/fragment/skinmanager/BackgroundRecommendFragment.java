package com.sds.android.ttpod.fragment.skinmanager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.activities.BackgroundManagementActivity;
import com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment;
import com.sds.android.ttpod.fragment.skinmanager.base.BkgEditListener;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.BackgroundItem;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.StateView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class BackgroundRecommendFragment extends BackgroundBaseFragment implements BkgEditListener {
    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment
    protected void initListViewHeader() {
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment
    protected void initListViewFooter() {
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        sBkgEditListenerList.add(this);
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        sBkgEditListenerList.remove(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_BACKGROUND_LIST, ReflectUtils.loadMethod(getClass(), "updateBackgroundList", ArrayList.class));
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ThemeUtils.m8181a(view);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        loadBackgroundList();
    }

    private void loadBackgroundList() {
        CommandCenter.getInstance().execute(new Command(CommandID.LOAD_BACKGROUND_LIST, false));
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
        this.mBackgroundAdapter.m5366a(arrayList);
        this.mStateView.setState(StateView.EnumC2248b.SUCCESS);
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment
    public void updateBackground(Drawable drawable) {
        if (!(getActivity() instanceof BackgroundManagementActivity)) {
            super.updateBackground(drawable);
        }
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public boolean isSupportOfflineMode() {
        return true;
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BkgEditListener
    public void onBkgDeleted(BackgroundItem backgroundItem) {
        BackgroundItem itemForName = getItemForName(backgroundItem.getImageName());
        if (itemForName != null) {
            itemForName.setResourceTypeEnum(BackgroundItem.ResourceTypeEnum.ONLINE_BACKGROUND);
            notifyDataSetChanged();
        }
    }

    private BackgroundItem getItemForName(String str) {
        Iterator<BackgroundItem> it = this.mBackgroundAdapter.m5365b().iterator();
        while (it.hasNext()) {
            BackgroundItem next = it.next();
            if (next.getImageName().equals(str)) {
                return next;
            }
        }
        return null;
    }
}
