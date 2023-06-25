package com.sds.android.ttpod.fragment.main;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;


import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.AudioEffectFragmentActivity;
import com.sds.android.ttpod.activities.ThemeManagementActivity;
import com.sds.android.ttpod.activities.base.ThemeActivity;
import com.sds.android.ttpod.activities.user.LoginActivity;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.EditTextDialog;
import com.sds.android.ttpod.component.p087d.p088a.MoreOptionalDialog;
import com.sds.android.ttpod.component.p087d.p088a.OptionalDialog;
import com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.skin.p130c.SkinEventHandler;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.utils.BlueToothUtils;
import com.sds.android.ttpod.utils.FavoriteUtils;
import java.io.File;

/* renamed from: com.sds.android.ttpod.fragment.main.a */
/* loaded from: classes.dex */
public class DefaultSkinEventHandler implements SkinEventHandler {

    /* renamed from: a */
    private Integer f5127a;

    /* renamed from: b */
    private ViewController f5128b;

    /* renamed from: c */
    private Activity f5129c;

    /* renamed from: d */
    private AudioManager f5130d;

    public DefaultSkinEventHandler(Activity activity, ViewController viewController) {
        this.f5129c = activity;
        this.f5128b = viewController;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.SkinEventHandler
    /* renamed from: a */
    public boolean mo3717a(int i, Object obj) {
        LogUtils.debug("DefaultSkinEventHandler", "actionId:" + i);
        final MediaItem m3225N = Cache.getInstance().getCurrentPlayMediaItem();
        switch (i) {
            case 0:
                if (this.f5129c instanceof ThemeActivity) {
                    ((ThemeActivity) this.f5129c).toggleMenu();
                    break;
                }
                break;
            case 1:
            case 2:
            case 3:
            case 7:
            case 8:
            case 9:
            case 12:
            case 13:
            case 16:
            case 17:
            case 29:
            default:
                return false;
            case 4:
                CommandCenter.getInstance().m4606a(new Command(CommandID.SET_POSITION, this.f5127a));
                this.f5127a = null;
                break;
            case 5:
                if (((Boolean) CommandCenter.getInstance().m4602a(new Command(CommandID.IS_SLEEP_MODE_ENABLED, new Object[0]), Boolean.class)).booleanValue()) {
                    CommandCenter.getInstance().m4606a(new Command(CommandID.STOP_SLEEP_MODE, new Object[0]));
                    PopupsUtils.m6721a(this.f5129c.getString(R.string.cancel_sleep_mode));
                    break;
                } else {
                    PopupsUtils.m6713b(this.f5129c, (BaseDialog.InterfaceC1064a<EditTextDialog>) null);
                    break;
                }
            case 6:
                CommandCenter.getInstance().m4606a(new Command(CommandID.SWITCH_PLAY_MODE, new Object[0]));
                m5668f();
                break;
            case 10:
                if (obj instanceof Number) {
                    m5675a(((Number) obj).intValue());
                    break;
                }
                break;
            case 11:
                if (!m3225N.isNull()) {
                    if (!m3225N.isThirdParty()) {
                        if (m3225N.isOnline() && Preferences.m2954aq() == null) {
                            this.f5129c.startActivity(new Intent(this.f5129c, LoginActivity.class));
                            break;
                        } else {
                            boolean z = !m3225N.getFav();
                            if (z) {
                                FavoriteUtils.m8283a(m3225N, true);
                            } else {
                                FavoriteUtils.m8282b(m3225N, false);
                            }
                            //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_PORTRAIT_ADD_FAVORITE.getValue(), SPage.PAGE_PORTRAIT_PLAYER.getValue(), SPage.PAGE_NONE.getValue()).append("status", Integer.valueOf(z ? 1 : 0)).post();
                            break;
                        }
                    } else {
                        PopupsUtils.m6721a("第三方无法歌曲不能收藏！");
                        break;
                    }
                }
                break;
            case 14:
            case 15:
                if (EnvironmentUtils.C0602a.m8502i() && (obj == null || !(obj instanceof Number))) {
                    throw new IllegalArgumentException("actionData must be Integer");
                }
                if (i == 15) {
                    this.f5127a = Integer.valueOf((this.f5127a == null ? SupportFactory.m2397a(BaseApplication.getApplication()).m2465k() : this.f5127a).intValue() + ((Number) obj).intValue());
                    this.f5127a = Integer.valueOf(Math.min(Math.max(0, this.f5127a.intValue()), Cache.getInstance().getCurrentPlayMediaItem().getDuration().intValue()));
                } else {
                    this.f5127a = Integer.valueOf(((Number) obj).intValue());
                }
                this.f5128b.mo6459a(this.f5127a.intValue(), SupportFactory.m2397a(BaseApplication.getApplication()).m2464l());
                break;
            case 18:
                if (SupportFactory.m2397a(BaseApplication.getApplication()).m2463m() == PlayStatus.STATUS_PAUSED) {
                    CommandCenter.getInstance().m4606a(new Command(CommandID.RESUME, new Object[0]));
                } else if (SupportFactory.m2397a(BaseApplication.getApplication()).m2463m() == PlayStatus.STATUS_STOPPED) {
                    CommandCenter.getInstance().m4606a(new Command(CommandID.START, new Object[0]));
                }
                //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_CLICK_PORTRAIT_PLAY, SPage.PAGE_PORTRAIT_PLAYER, SPage.PAGE_NONE);
                break;
            case 19:
                ((BaseActivity) this.f5129c).acquireFastClickSupport();
                CommandCenter.getInstance().m4606a(new Command(CommandID.PAUSE, new Object[0]));
                //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_CLICK_PORTRAIT_PAUSE, SPage.PAGE_PORTRAIT_PLAYER, SPage.PAGE_NONE);
                break;
            case 20:
                ((BaseActivity) this.f5129c).acquireFastClickSupport();
                CommandCenter.getInstance().m4606a(new Command(CommandID.NEXT, new Object[0]));
                //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_CLICK_PORTRAIT_NEXT, SPage.PAGE_PORTRAIT_PLAYER, SPage.PAGE_NONE);
                break;
            case 21:
                ((BaseActivity) this.f5129c).acquireFastClickSupport();
                CommandCenter.getInstance().m4606a(new Command(CommandID.PREVIOUS, new Object[0]));
                //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_CLICK_PORTRAIT_PREV, SPage.PAGE_PORTRAIT_PLAYER, SPage.PAGE_NONE);
                break;
            case 22:
                this.f5129c.startActivity(new Intent(this.f5129c, AudioEffectFragmentActivity.class));
                break;
            case 23:
                m5672b();
                break;
            case 24:
                if (Cache.getInstance().getCurrentPlayMediaItem().isOnline()) {
                    PopupsUtils.m6721a("网络歌曲不能添加到自定义列表");
                    break;
                } else {
                    PopupsUtils.m6729a(this.f5129c, Cache.getInstance().m3155k(), Cache.getInstance().getCurrentPlayMediaItem(), (ActionItem.InterfaceC1135b) null, (BaseDialog.InterfaceC1064a<EditTextDialog>) null);
                    break;
                }
            case 25:
                m5676a();
                //LocalStatistic.m5159X();
                break;
            case 26:
                m5670d();
                break;
            case 27:
                if (!m3225N.isNull()) {
                    PopupsUtils.m6747a(this.f5129c, (int) R.string.remove_option, this.f5129c.getString(R.string.media_delete_title), this.f5129c.getString(R.string.media_delete_single, new Object[]{m3225N.getTitle()}), new BaseDialog.InterfaceC1064a<OptionalDialog>() { // from class: com.sds.android.ttpod.fragment.main.a.1
                        @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                        /* renamed from: a  reason: avoid collision after fix types in other method */
                        public void mo2038a(OptionalDialog optionalDialog) {
                            CommandCenter.getInstance().m4606a(new Command(CommandID.DELETE_MEDIA_ITEM, m3225N.getGroupID(), m3225N, Boolean.valueOf(optionalDialog.m6808b())));
                        }
                    });
                    break;
                }
                break;
            case 28:
                if (!m3225N.isNull()) {
                    PopupsUtils.m6740a((Context) this.f5129c, m3225N);
                    break;
                }
                break;
            case 30:
                this.f5129c.startActivity(new Intent(this.f5129c, ThemeManagementActivity.class));
                //ThemeStatistic.m4886g("play");
                //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_PORTRAIT_SKIN.getValue(), SPage.PAGE_PORTRAIT_PLAYER.getValue(), SPage.PAGE_THEME_BACKGROUND.getValue()).post();
                //ThemeStatistic.m4873s();
                break;
        }
        return true;
    }

    /* renamed from: f */
    private void m5668f() {
        int[] iArr = {R.string.repeat_play, R.string.repeat_one_play, R.string.sequence_play, R.string.shuffle_play};
        int ordinal = Preferences.m2862l().ordinal();
        PopupsUtils.m6760a(iArr[ordinal]);
        //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_PORTRAIT_PLAY_MODE.getValue(), SPage.PAGE_PORTRAIT_PLAYER.getValue(), SPage.PAGE_NONE.getValue()).append("status", Integer.valueOf(ordinal + 1)).post();
    }

    /* renamed from: a */
    public void m5675a(int i) {
        m5669e().setStreamVolume(3, i, 0);
    }

    /* renamed from: a */
    public void m5674a(ViewController viewController) {
        this.f5128b = viewController;
    }

    /* renamed from: a */
    public void m5676a() {
        MediaItem m3225N = Cache.getInstance().getCurrentPlayMediaItem();
        if (!m3225N.isNull()) {
            PopupsUtils.m6756a(this.f5129c, m3225N);
        }
    }

    /* renamed from: b */
    public void m5672b() {
        final MediaItem m3225N = Cache.getInstance().getCurrentPlayMediaItem();
        if (!m3225N.isNull()) {
            if (FileUtils.m8419a(m3225N.getLocalDataSource())) {
                PopupsUtils.m6755a(this.f5129c, m3225N, (DialogInterface.OnDismissListener) null, new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.fragment.main.a.2
                    @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
                    /* renamed from: a */
                    public void mo5409a(ActionItem actionItem, int i) {
                        PopupsUtils.m6738a(DefaultSkinEventHandler.this.f5129c, m3225N, Preferences.m2858m(), (BaseDialog.InterfaceC1064a<MoreOptionalDialog>) null);
                    }
                }, new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.fragment.main.a.3
                    @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
                    /* renamed from: a */
                    public void mo5409a(ActionItem actionItem, int i) {
                        //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_PORTRAIT_MENU_MORE_ADD_TO_LIST.getValue(), SPage.PAGE_PLAYER_MENU_MORE.getValue(), SPage.PAGE_NONE.getValue()).post();
                        PopupsUtils.m6729a(DefaultSkinEventHandler.this.f5129c, Cache.getInstance().m3155k(), m3225N, (ActionItem.InterfaceC1135b) null, (BaseDialog.InterfaceC1064a<EditTextDialog>) null);
                    }
                });
            } else if (m3225N.isOnline()) {
                PopupsUtils.m6715b(this.f5129c, m3225N);
            }
        }
    }

    /* renamed from: c */
    public void m5671c() {
        if (this.f5128b != null && this.f5127a == null) {
            this.f5128b.mo6459a(SupportFactory.m2397a(BaseApplication.getApplication()).m2465k().intValue(), Cache.getInstance().getCurrentPlayMediaItem().isOnline() ? SupportFactory.m2397a(BaseApplication.getApplication()).m2464l() : 0.0f);
        }
    }

    /* renamed from: d */
    public void m5670d() {
        MediaItem m3225N = Cache.getInstance().getCurrentPlayMediaItem();
        if (!m3225N.isNull() && FileUtils.m8414b(m3225N.getLocalDataSource())) {
            try {
                BlueToothUtils.m8308a(this.f5129c, new File[]{new File(m3225N.getLocalDataSource())});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: e */
    public AudioManager m5669e() {
        if (this.f5130d == null) {
            this.f5130d = (AudioManager) this.f5129c.getSystemService("audio");
        }
        return this.f5130d;
    }
}
