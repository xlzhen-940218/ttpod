package com.sds.android.ttpod.activities.audioeffect;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingPagerActivity;
import com.sds.android.ttpod.adapter.SlidingTabFragmentPagerAdapter;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.fragment.audioeffect.EqualizerAllFragment;
import com.sds.android.ttpod.fragment.audioeffect.EqualizerHandpickFragment;
import com.sds.android.ttpod.framework.p106a.p107a.AudioEffectStatistic;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.p106a.p107a.SUserUtils;
import java.util.List;

/* loaded from: classes.dex */
public class EqualizerFragmentActivity extends SlidingPagerActivity {
    private static final int ID_FRAGMENT_EQUALIZER_ALL = 1;
    private static final int ID_FRAGMENT_EQUALIZER_HANDPICK = 0;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingPagerActivity, com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPagerTitle.setShouldExpand(true);
        setTitle(R.string.effect_equalizer_default);
        ActionBarUtils.m8130a(getActionBarController());
        this.mPagerTitle.setBackgroundColor(Color.parseColor("#1f2223"));
        this.mPagerTitle.setTextColorResource(R.color.xml_local_media_tab_text_black);
        this.mPagerTitle.setIndicatorColorResource(R.color.effect_blue);
    }

    @Override // com.sds.android.ttpod.activities.base.SlidingPagerActivity
    protected void onBuildActionBar(ActionBarController actionBarController) {
    }

    @Override // com.sds.android.ttpod.activities.base.SlidingPagerActivity
    protected void onBuildFragmentBinderList(List<SlidingTabFragmentPagerAdapter.C0998a> list) {
        list.add(new SlidingTabFragmentPagerAdapter.C0998a(0L, (int) R.string.effect_equalizer_handpick, 0, Fragment.instantiate(this, EqualizerHandpickFragment.class.getName())));
        list.add(new SlidingTabFragmentPagerAdapter.C0998a(1L, (int) R.string.effect_equalizer_all, 0, Fragment.instantiate(this, EqualizerAllFragment.class.getName())));
    }

    @Override // com.sds.android.ttpod.activities.base.SlidingPagerActivity, androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        super.onPageSelected(i);
        if (i == 0) {
            AudioEffectStatistic.m5266f();
            SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_EFFECT_EQULIZER_DEFAULT_HANDPICK, SPage.PAGE_NONE, SPage.PAGE_NONE);
        } else if (i == 1) {
            AudioEffectStatistic.m5264h();
            SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_EFFECT_EQULIZER_ALL, SPage.PAGE_NONE, SPage.PAGE_NONE);
        }
    }
}
