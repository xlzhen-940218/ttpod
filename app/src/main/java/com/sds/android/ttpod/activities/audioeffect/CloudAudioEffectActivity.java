package com.sds.android.ttpod.activities.audioeffect;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingPagerActivity;
import com.sds.android.ttpod.adapter.SlidingTabFragmentPagerAdapter;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.fragment.audioeffect.CloudAudioEffectFragment;
import com.sds.android.ttpod.fragment.audioeffect.MyAudioEffectFragment;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.p106a.p107a.ActionPage;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class CloudAudioEffectActivity extends SlidingPagerActivity {
    private static final int ID_FRAGMENT_CLOUD_AUDIO = 0;
    private static final int ID_FRAGMENT_MY_AUDIO = 1;
    private static final int INDICATOR_HEIGHT = 4;
    private static final ArrayList<ActionPage> SLIST = new ArrayList<ActionPage>(2) { // from class: com.sds.android.ttpod.activities.audioeffect.CloudAudioEffectActivity.3
        {
            add(new ActionPage(SAction.ACTION_EFFECT_CLOUD_AUDIO, SPage.PAGE_AUDIO_CLOUD_EFFECT));
            add(new ActionPage(SAction.ACTION_EFFECT_MY_EFFECT, SPage.PAGE_AUDIO_MY_CLOUD_EFFECT));
        }
    };
    private int mCurrentPage;
    private ActionBarController.C1070a mDeleteAction;
    private boolean mHasPrivateAudioEffect = false;
    private View mKnownView;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingPagerActivity, com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPagerTitle.setShouldExpand(true);
        setTitle(getString(R.string.effect_back));
        this.mPagerTitle.setBackground(new ColorDrawable(Color.parseColor("#222222")));
        this.mPagerTitle.setTextColor(Color.parseColor("#ffffff"));
        this.mPagerTitle.setIndicatorColorResource(R.color.effect_blue);
        this.mPagerTitle.setIndicatorHeight(DisplayUtils.dp2px(4));
        initActionBar();
        initFirstInto();
        this.mPagerTitle.setOnPageChangeListener(this);
    }

    private void initFirstInto() {
        if (Preferences.m2955ap()) {
            Preferences.m3071E(false);
            final View inflate = View.inflate(this, R.layout.audio_effect_guide, null);
            addContentView(inflate, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            this.mKnownView = inflate.findViewById(R.id.id_button_known);
            this.mKnownView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.audioeffect.CloudAudioEffectActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    inflate.setVisibility(View.GONE);
                }
            });
            inflate.setClickable(true);
            Toast.makeText(this, (int) R.string.effect_score_rule_changed, Toast.LENGTH_LONG).show();
        }
    }

    private void initActionBar() {
        this.mDeleteAction = getActionBarController().m7178d(R.drawable.img_edit_effect_delete);
        this.mDeleteAction.m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.activities.audioeffect.CloudAudioEffectActivity.2
            @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
            /* renamed from: a */
            public void mo5433a(ActionBarController.C1070a c1070a) {
                CloudAudioEffectActivity.this.onDeleteActionItemClick();
            }
        });
        this.mDeleteAction.m7155c(false);
        ActionBarUtils.m8130a(getActionBarController());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDeleteActionItemClick() {
        ((MyAudioEffectFragment) getSupportFragmentManager().findFragmentById(R.id.sliding_pager_content)).onDeleteActionItemClick();
    }

    @Override // com.sds.android.ttpod.activities.base.SlidingPagerActivity, androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        super.onPageSelected(i);
        this.mCurrentPage = i;
        //SUserUtils.m4956a(SLIST.get(i).m5275a(), SLIST.get(i).m5274b());
        if (i == 0) {
            this.mDeleteAction.m7155c(false);
        } else if (i == 1) {
            this.mDeleteAction.m7155c(this.mHasPrivateAudioEffect);
            //AudioEffectStatistic.m5249w();
        }
    }

    public ActionBarController.C1070a getDeleteAction() {
        return this.mDeleteAction;
    }

    public boolean isMyAudioEffectFragmentPage() {
        return this.mCurrentPage == 1;
    }

    public boolean getHasPrivateAudioEffect() {
        return this.mHasPrivateAudioEffect;
    }

    public void setHasPrivateAudioEffect(boolean z) {
        this.mHasPrivateAudioEffect = z;
    }

    @Override // com.sds.android.ttpod.activities.base.SlidingPagerActivity
    protected void onBuildActionBar(ActionBarController actionBarController) {
    }

    @Override // com.sds.android.ttpod.activities.base.SlidingPagerActivity
    protected void onBuildFragmentBinderList(List<SlidingTabFragmentPagerAdapter.C0998a> list) {
        BaseFragment baseFragment = (BaseFragment) Fragment.instantiate(this, CloudAudioEffectFragment.class.getName());
        baseFragment.setPage(SPage.PAGE_AUDIO_CLOUD_EFFECT);
        list.add(new SlidingTabFragmentPagerAdapter.C0998a(0L, (int) R.string.string_cloud_audio_effect, 0, baseFragment));
        BaseFragment baseFragment2 = (BaseFragment) Fragment.instantiate(this, MyAudioEffectFragment.class.getName());
        baseFragment2.setPage(SPage.PAGE_AUDIO_MY_CLOUD_EFFECT);
        list.add(new SlidingTabFragmentPagerAdapter.C0998a(1L, (int) R.string.my_effect, 0, baseFragment2));
    }
}
