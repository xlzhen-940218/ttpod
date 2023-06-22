package com.sds.android.ttpod.fragment.apshare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.audioeffect.ActionBarUtils;
import com.sds.android.ttpod.fragment.base.ActionBarFragment;

/* loaded from: classes.dex */
public class ApShareBaseFragment extends ActionBarFragment {
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        getRootView().setBackgroundResource(R.drawable.apshare_whole_bkg);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ActionBarUtils.m8129b(getActionBarController());
        return null;
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }
}
