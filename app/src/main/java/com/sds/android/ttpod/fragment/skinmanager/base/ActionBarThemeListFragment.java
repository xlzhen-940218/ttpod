package com.sds.android.ttpod.fragment.skinmanager.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.utils.ThemeUtils;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class ActionBarThemeListFragment extends OnlineThemeFragment {
    private ActionBarController mActionBarController;
    private View mRootView;

    protected abstract String getTitle();

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment, android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = View.inflate(getActivity(), R.layout.activity_actionbar, null);
        this.mActionBarController = ActionBarController.m7197a(this.mRootView.findViewById(R.id.action_bar_controller));
        this.mActionBarController.m7196a(new ActionBarController.InterfaceC1073c() { // from class: com.sds.android.ttpod.fragment.skinmanager.base.ActionBarThemeListFragment.1
            @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1073c
            /* renamed from: a */
            public void mo5380a(ActionBarController actionBarController) {
                ActionBarThemeListFragment.this.onTitleClicked();
            }
        });
        ViewGroup viewGroup2 = (ViewGroup) this.mRootView.findViewById(R.id.activity_body);
        viewGroup2.addView(layoutInflater.inflate(R.layout.fragment_base_theme_layout, viewGroup2, false));
        return this.mRootView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTitleClicked() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mActionBarController.m7193a((CharSequence) getTitle());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.APP_THEME_CHANGED, ReflectUtils.loadMethod(getClass(), "onThemeLoaded", new Class[0]));
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mActionBarController.onThemeLoaded();
        ThemeManager.m3260b(this.mRootView, ThemeUtils.m8182a());
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment
    public void updateBackground(Drawable drawable) {
        super.updateBackground(drawable);
        if (drawable != null) {
            ThemeManager.m3260b(getRootView(), drawable);
        }
    }

    protected final View getRootView() {
        return this.mRootView;
    }
}
