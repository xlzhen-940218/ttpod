package com.sds.android.ttpod.fragment;

import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.ttpod.widget.SlidingClosableRelativeLayout;

/* loaded from: classes.dex */
public abstract class BaseGuideFragment extends DialogFragment {
    private SlidingClosableRelativeLayout mSlidingClosableRelativeLayout;

    protected abstract View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        setStyle(2, android.R.style.Theme_Translucent_NoTitleBar);
        return new Dialog(getActivity(), getTheme());
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mSlidingClosableRelativeLayout = new SlidingClosableRelativeLayout(getActivity());
        this.mSlidingClosableRelativeLayout.setSlidingCloseMode(3);
        this.mSlidingClosableRelativeLayout.setOnSlidingCloseListener(new SlidingClosableRelativeLayout.InterfaceC2237a() { // from class: com.sds.android.ttpod.fragment.BaseGuideFragment.1
            @Override // com.sds.android.ttpod.widget.SlidingClosableRelativeLayout.InterfaceC2237a
            /* renamed from: a */
            public void mo1483a() {
                if (BaseGuideFragment.this.mSlidingClosableRelativeLayout != null) {
                    BaseGuideFragment.this.mSlidingClosableRelativeLayout.setVisibility(View.GONE);
                }
                FragmentManager fragmentManager = BaseGuideFragment.this.getFragmentManager();
                if (fragmentManager != null) {
                    fragmentManager.beginTransaction().remove(BaseGuideFragment.this).commitAllowingStateLoss();
                }
            }
        });
        this.mSlidingClosableRelativeLayout.addView(onCreateContentView(layoutInflater, viewGroup, bundle), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return this.mSlidingClosableRelativeLayout;
    }

    public void setSlidingCloseMode(int i) {
        this.mSlidingClosableRelativeLayout.setSlidingCloseMode(i);
    }

    public void setSlidingEnableScrollingMask(boolean z) {
        this.mSlidingClosableRelativeLayout.setEnableScrollingMask(z);
    }

    @Override // androidx.fragment.app.DialogFragment
    public void show(FragmentManager fragmentManager, String str) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.add(this, str);
        beginTransaction.commitAllowingStateLoss();
    }

    @Override // androidx.fragment.app.DialogFragment
    public int show(FragmentTransaction fragmentTransaction, String str) {
        fragmentTransaction.add(this, str);
        return fragmentTransaction.commitAllowingStateLoss();
    }
}
