package com.sds.android.ttpod.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.sds.android.cloudapi.ttpod.data.AudioEffectUser;
import com.sds.android.cloudapi.ttpod.result.AudioEffectAddResult;
import com.sds.android.cloudapi.ttpod.result.AudioEffectUserResult;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.audioeffect.ActionBarUtils;
import com.sds.android.ttpod.activities.audioeffect.CloudAudioEffectActivity;
import com.sds.android.ttpod.activities.base.SlidingPagerActivity;
import com.sds.android.ttpod.adapter.SlidingTabFragmentPagerAdapter;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.EffectShareDialog;
import com.sds.android.ttpod.fragment.audioeffect.BoostFragment;
import com.sds.android.ttpod.fragment.audioeffect.CustomEqualizerFragment;
import com.sds.android.ttpod.fragment.audioeffect.MyAudioEffectFragment;
import com.sds.android.ttpod.fragment.audioeffect.ReverbFragment;
import com.sds.android.ttpod.fragment.base.ActionBarFragment;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.p107a.ActionPage;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class AudioEffectFragmentActivity extends SlidingPagerActivity {
    private static final int EFFECT_REPEAT = 20000;
    private static final int ID_FRAGMENT_BOOST = 0;
    private static final int ID_FRAGMENT_CUSTOM_EQUALIZER = 1;
    private static final int ID_FRAGMENT_REVERB = 2;
    private static final int INDICATOR_HEIGHT = 4;
    private static final int MENU_CLOUD_EFFECT_RELATE = 100;
    private static final int MENU_RESET_ALL_AUDIO_EFFECT = 101;
    private static final int MENU_SAVE_TO_CLOUD = 102;
    private static final ArrayList<ActionPage> SLIST = new ArrayList<ActionPage>(3) { // from class: com.sds.android.ttpod.activities.AudioEffectFragmentActivity.1
        {
            add(new ActionPage(SAction.ACTION_EFFECT_BOOST, SPage.PAGE_AUDIO_BOOST));
            add(new ActionPage(SAction.ACTION_EFFECT_EQULIZER, SPage.PAGE_AUDIO_EQUALIZER));
            add(new ActionPage(SAction.ACTION_EFFECT_REVERB, SPage.PAGE_AUDIO_REVERB));
        }
    };
    private AudioEffectUser mAudioEffectUser;
    private ActionBarController.C1070a mDeleteAction;
    private int mCurrentPage = 0;
    private boolean mHasPrivateAudioEffect = false;

    public boolean getHasPrivateAudioEffect() {
        return this.mHasPrivateAudioEffect;
    }

    public void setHasPrivateAudioEffect(boolean z) {
        this.mHasPrivateAudioEffect = z;
    }

    public int getCurrentPage() {
        return this.mCurrentPage;
    }

    @Override // com.sds.android.ttpod.activities.base.SlidingPagerActivity, com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPagerContent.setOffscreenPageLimit(this.mPagerAdapter.getCount() - 1);
        CommandCenter.getInstance().postInvokeResult(new Command(CommandID.QUERY_EFFECT_USERINFO, new Object[0]));
        this.mPagerTitle.setShouldExpand(true);
        setTitle(getString(R.string.audio_effect));
        this.mPagerTitle.setBackground(new ColorDrawable(Color.parseColor("#222222")));
        this.mPagerTitle.setTextColor(Color.parseColor("#ffffff"));
        this.mPagerTitle.setIndicatorColorResource(R.color.effect_blue);
        this.mPagerTitle.setIndicatorHeight(DisplayUtils.dp2px(4));
        initActionBar();
        this.mPagerTitle.setOnPageChangeListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        PopupsUtils.m6723a(this.mDropDownMenu);
    }

    @Override // com.sds.android.ttpod.activities.base.ActionBarActivity
    public void onToggleMenuView(boolean z) {
        Collection<ActionItem> onCreateDropDownMenu;
        if (this.mDropDownMenu != null && this.mDropDownMenu.isShowing()) {
            this.mDropDownMenu.dismiss();
            this.mDropDownMenu = null;
        } else if (needMenuAction() && this.mMenuAction.m7157c() && (onCreateDropDownMenu = onCreateDropDownMenu()) != null && !onCreateDropDownMenu.isEmpty()) {
            this.mDropDownMenu = ActionBarFragment.popupMenu(this, onCreateDropDownMenu, z, this, R.layout.audio_effect_popup_menu_layout, R.id.lst_content, R.layout.audio_effect_popup_menu_choice_item, R.id.txt_title, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_QUERY_EFFECT_USERINFO, ReflectUtils.loadMethod(cls, "updateQueryEffectUserInfo", AudioEffectUserResult.class));
        map.put(CommandID.UPDATE_SAVE_EFFECT_TO_NETWORK, ReflectUtils.loadMethod(cls, "updateSaveEffectToNetwork", AudioEffectAddResult.class));
        map.put(CommandID.EFFECT_SAVE_RESULT, ReflectUtils.loadMethod(cls, "effectSaveResult", Boolean.class));
    }

    public void effectSaveResult(Boolean bool) {
        if (bool.booleanValue()) {
            PopupsUtils.m6721a(getString(R.string.cloud_effect_save_sucess));
        } else {
            PopupsUtils.m6721a(getString(R.string.cloud_effect_save_failed));
        }
    }

    public void updateSaveEffectToNetwork(AudioEffectAddResult audioEffectAddResult) {
        if (audioEffectAddResult.getCode() == 1) {
            PopupsUtils.m6721a(getString(R.string.cloud_effect_share_sucess));
        } else if (audioEffectAddResult.getCode() == EFFECT_REPEAT) {
            PopupsUtils.m6721a(getString(R.string.cloud_effect_share_repeat));
        } else {
            PopupsUtils.m6721a(getString(R.string.cloud_effect_share_failed));
        }
    }

    public void updateQueryEffectUserInfo(AudioEffectUserResult audioEffectUserResult) {
        this.mAudioEffectUser = audioEffectUserResult.getData();
    }

    @Override // com.sds.android.ttpod.activities.base.SlidingPagerActivity, androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        super.onPageSelected(i);
        if (i >= 0) {
            this.mCurrentPage = i;
            if (i == 0) {
                //AudioEffectStatistic.m5257o();
            } else if (i == 1) {
                //AudioEffectStatistic.m5268d();
            } else if (i == 2) {
                //AudioEffectStatistic.m5259m();
            }
            //SUserUtils.m4956a(SLIST.get(i).m5275a(), SLIST.get(i).m5274b());
        }
    }

    private void initActionBar() {
        this.mDeleteAction = getActionBarController().m7178d(R.drawable.img_edit_effect_delete);
        this.mDeleteAction.m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.activities.AudioEffectFragmentActivity.2
            @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
            /* renamed from: a */
            public void mo5433a(ActionBarController.C1070a c1070a) {
                AudioEffectFragmentActivity.this.onDeleteActionItemClick();
            }
        });
        this.mDeleteAction.m7155c(false);
        ActionBarUtils.m8130a(getActionBarController());
    }

    @Override // com.sds.android.ttpod.activities.base.ActionBarActivity
    protected boolean needMenuAction() {
        return true;
    }

    @Override // com.sds.android.ttpod.activities.base.ActionBarActivity
    protected Collection<ActionItem> onCreateDropDownMenu() {
        return new ArrayList<ActionItem>(1) { // from class: com.sds.android.ttpod.activities.AudioEffectFragmentActivity.3
            {
                add(new ActionItem(100, 0, (int) R.string.cloud_audio_effect_relative));
                add(new ActionItem(101, 0, (int) R.string.effect_custom_reset_all));
                add(new ActionItem(102, 0, (int) R.string.save_effect));
            }
        };
    }

    @Override // com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.fragment.base.OnDropdownMenuClickListener
    public void onDropDownMenuClicked(int i, ActionItem actionItem) {
        if (100 == i) {
            launcherCloudAudioEffect();
        } else if (101 == i) {
            handleRestAllAudioEffect();
        } else if (102 == i) {
            handleSaveToCloud();
        }
    }

    private void launcherCloudAudioEffect() {
        startActivity(new Intent(this, CloudAudioEffectActivity.class));
        //SUserUtils.m4956a(SAction.ACTION_EFFECT_CLOUD_AUDIO_RELATIVE, SPage.PAGE_AUDIO_CLOUD_EFFECT);
    }

    private void handleRestAllAudioEffect() {
        //AudioEffectStatistic.m5252t();
        //SUserUtils.m4956a(SAction.ACTION_EFFECT_AJUST_RESET, SPage.PAGE_NONE);
        CommandCenter.getInstance().postInvokeResult(new Command(CommandID.SET_AUDIO_EFFECT_RESET, new Object[0]));
    }

    private void handleSaveToCloud() {
        MediaItem m3225N = Cache.getInstance().getCurrentPlayMediaItem();
        if (m3225N.isNull()) {
            PopupsUtils.m6760a((int) R.string.can_not_save_effect_no_play);
            return;
        }
        //AudioEffectStatistic.m5251u();
        //SUserUtils.m4956a(SAction.ACTION_EFFECT_AJUST_SAVE, SPage.PAGE_NONE);
        AudioManager audioManager = (AudioManager) getSystemService("audio");
        if (audioManager != null) {
            new EffectShareDialog(this, m3225N, this.mAudioEffectUser, audioManager.isWiredHeadsetOn()).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDeleteActionItemClick() {
        ((MyAudioEffectFragment) getSupportFragmentManager().findFragmentById(R.id.sliding_pager_content)).onDeleteActionItemClick();
    }

    public ActionBarController.C1070a getDeleteAction() {
        return this.mDeleteAction;
    }

    @Override // com.sds.android.ttpod.activities.base.SlidingPagerActivity
    protected void onBuildActionBar(ActionBarController actionBarController) {
    }

    @Override // com.sds.android.ttpod.activities.base.SlidingPagerActivity
    protected void onBuildFragmentBinderList(List<SlidingTabFragmentPagerAdapter.C0998a> list) {
        BaseFragment baseFragment = (BaseFragment) Fragment.instantiate(this, BoostFragment.class.getName());
        baseFragment.setPage(SPage.PAGE_AUDIO_BOOST);
        list.add(new SlidingTabFragmentPagerAdapter.C0998a(0L, (int) R.string.boost_effect, 0, baseFragment));
        BaseFragment baseFragment2 = (BaseFragment) Fragment.instantiate(this, CustomEqualizerFragment.class.getName());
        baseFragment2.setPage(SPage.PAGE_AUDIO_EQUALIZER);
        list.add(new SlidingTabFragmentPagerAdapter.C0998a(1L, (int) R.string.effect_equalizer, 0, baseFragment2));
        BaseFragment baseFragment3 = (BaseFragment) Fragment.instantiate(this, ReverbFragment.class.getName());
        baseFragment3.setPage(SPage.PAGE_AUDIO_REVERB);
        list.add(new SlidingTabFragmentPagerAdapter.C0998a(2L, (int) R.string.effect_help_reverb_title, 0, baseFragment3));
    }
}
