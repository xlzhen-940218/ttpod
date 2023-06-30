package com.sds.android.ttpod.fragment.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.fragment.SlidingGuideFragment;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.SlidingClosableRelativeLayout;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class SlidingClosableFragment extends ActionBarFragment {
    private static Boolean mIsShowSlidingGuideEnabled = null;
    private SlidingClosableRelativeLayout mSlidingClosableRelativeLayout;

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment, androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mSlidingClosableRelativeLayout = new SlidingClosableRelativeLayout(getActivity());
        this.mSlidingClosableRelativeLayout.setSlidingCloseMode(3);
        this.mSlidingClosableRelativeLayout.setOnSlidingCloseListener(new SlidingClosableRelativeLayout.OnSlidingCloseListener() { // from class: com.sds.android.ttpod.fragment.base.SlidingClosableFragment.1
            @Override // com.sds.android.ttpod.widget.SlidingClosableRelativeLayout.InterfaceC2237a
            /* renamed from: a */
            public void close() {
                if (SlidingClosableFragment.this.mSlidingClosableRelativeLayout != null) {
                    SlidingClosableFragment.this.mSlidingClosableRelativeLayout.setVisibility(View.GONE);
                }
                SlidingClosableFragment.this.onSlidingClosed();
            }
        });
        this.mSlidingClosableRelativeLayout.setOnSlidingScrollListener(new SlidingClosableRelativeLayout.InterfaceC2239c() { // from class: com.sds.android.ttpod.fragment.base.SlidingClosableFragment.2
            @Override // com.sds.android.ttpod.widget.SlidingClosableRelativeLayout.InterfaceC2239c
            /* renamed from: a */
            public void mo1481a() {
                SlidingClosableFragment.this.showPreviousFragment();
            }

            @Override // com.sds.android.ttpod.widget.SlidingClosableRelativeLayout.InterfaceC2239c
            /* renamed from: b */
            public void mo1480b() {
                SlidingClosableFragment.this.hidePreviousFragment();
            }
        });
        this.mSlidingClosableRelativeLayout.addView(super.onCreateView(layoutInflater, this.mSlidingClosableRelativeLayout, bundle));
        return this.mSlidingClosableRelativeLayout;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (mIsShowSlidingGuideEnabled == null) {
            mIsShowSlidingGuideEnabled = Boolean.valueOf(Preferences.m2995aL());
            if (mIsShowSlidingGuideEnabled.booleanValue()) {
                new SlidingGuideFragment().show(getFragmentManager(), "sliding");
                Preferences.m3045R(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_BACKGROUND, ReflectUtils.loadMethod(getClass(), "updateBackground", Drawable.class));
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        updateBackground(ThemeUtils.m8182a());
    }

    public void updateBackground(Drawable drawable) {
        if (drawable == null) {
            LogUtils.error("SlidingClosableFragment", "SlidingClosableFragment.updateBackground background is null");
        } else {
            ThemeManager.m3260b(getRootView(), drawable);
        }
    }

    public void setSlidingCloseMode(int i) {
        if (this.mSlidingClosableRelativeLayout != null) {
            this.mSlidingClosableRelativeLayout.setSlidingCloseMode(i);
        }
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mSlidingClosableRelativeLayout.setVisibility(View.GONE);
        this.mSlidingClosableRelativeLayout.setOnSlidingCloseListener(null);
        this.mSlidingClosableRelativeLayout.setOnSlidingScrollListener(null);
        this.mSlidingClosableRelativeLayout = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onSlidingClosed() {
        super.finish();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public final void finish() {
        if (this.mSlidingClosableRelativeLayout != null) {
            setSlidingCloseMode(2);
            this.mSlidingClosableRelativeLayout.m1520a(true);
            return;
        }
        super.finish();
    }

    public SlidingClosableRelativeLayout getSlidingContainer() {
        return this.mSlidingClosableRelativeLayout;
    }
}
