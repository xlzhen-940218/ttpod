package com.sds.android.ttpod.fragment.skinmanager;

import android.os.Bundle;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.skinmanager.base.ActionBarThemeListFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.skin.SkinItem;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.widget.StateView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes.dex */
public class ThemeRankFragment extends ActionBarThemeListFragment {
    @Override // com.sds.android.ttpod.fragment.skinmanager.base.ActionBarThemeListFragment
    protected String getTitle() {
        return (String) getResources().getText(R.string.theme_rank);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.skinmanager.base.ActionBarThemeListFragment, com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.FINISH_UPDATE_SKIN_RANK_LIST, ReflectUtils.loadMethod(cls, "updateSkinRankResult", Boolean.class));
        map.put(CommandID.UPDATE_SKIN_RANK_LIST, ReflectUtils.loadMethod(cls, "updateDataListForAdapter", ArrayList.class));
    }

    public void updateSkinRankResult(Boolean bool) {
        this.mCacheMode = false;
        CommandCenter.getInstance().postInvokeResult(new Command(CommandID.REQUEST_SKIN_RANK_LIST, 0));
    }

    public void updateDataListForAdapter(ArrayList<SkinItem> arrayList) {
        this.mCacheMode = false;
        if (arrayList != null && arrayList.size() != 0) {
            if (checkIfReloadData(arrayList)) {
                setAdapterDataSource(arrayList);
                return;
            }
            return;
        }
        this.mStateView.setState(StateView.EnumC2248b.FAILED);
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSubClassTag = ThemeRankFragment.class.getSimpleName();
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.OnlineThemeFragment, com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment
    protected String getStatisticOrigin() {
        return "rank";
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
        Cache.getInstance().m3190b(getThemeDataList());
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment
    protected ArrayList<SkinItem> loadDataFromCache() {
        return Cache.getInstance().m3144t();
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment
    protected CommandID getLoadDataCommandID() {
        return CommandID.REQUEST_UPDATE_SKIN_RANK_LIST;
    }
}
