package com.sds.android.ttpod.fragment.settings;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.LinearLayout;

import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.setting.AboutActivity;
import com.sds.android.ttpod.activities.setting.AuditionAndDownloadQualityActivity;
import com.sds.android.ttpod.activities.setting.CheckableSettingItem;
import com.sds.android.ttpod.activities.setting.DesktopLyricSettingActivity;
import com.sds.android.ttpod.activities.setting.DownloadLocationActivity;
import com.sds.android.ttpod.activities.setting.HeadsetControlActivity;
import com.sds.android.ttpod.activities.setting.LyricPictureSettingActivity;
import com.sds.android.ttpod.activities.setting.MoreSettingActivity;
import com.sds.android.ttpod.activities.setting.SettingCard;
import com.sds.android.ttpod.activities.setting.SettingItem;
import com.sds.android.ttpod.activities.setting.SettingUtils;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p085b.Card;
import com.sds.android.ttpod.component.p085b.CheckableActionItem;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.SingleChoiceListDialog;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.core.p113b.p114a.ShakeSensitivityType;
import com.sds.android.ttpod.framework.modules.p123e.LockScreenModule;
import com.sds.android.ttpod.framework.modules.version.VersionUpdateModule;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import java.util.HashMap;

/* loaded from: classes.dex */
public class SettingEntryFragment extends BaseFragment {
    private static final int ID_ABOUT_TTPOD = 10;
    private static final int ID_CLEAR_CACHE = 9;
    private static final int ID_DESKTOP_LYRIC = 0;
    private static final int ID_DOWNLOAD_ONLY_WIFI = 5;
    private static final int ID_DOWNLOAD_POSITION = 7;
    private static final int ID_HEADSET_CONTROL = 3;
    private static final int ID_LISTEN_AND_DOWNLOAD_QUALITY = 6;
    private static final int ID_LOCK_SCREEN_LYRIC = 1;
    private static final int ID_LYRIC_AND_PICTURE = 8;
    private static final int ID_MORE = 4;
    private static final int ID_SHAKE_CHANGE_SONG = 2;
    private static final int LOCK_SCREEN_LYRIC_CLOSE = 0;
    private static final int LOCK_SCREEN_LYRIC_FULL_SCREEN = 2;
    private static final int LOCK_SCREEN_LYRIC_NOT_FULL_SCREEN = 1;
    private static final int SHAKE_CLOSE = 0;
    private static final int STATUS_CLOSE = 2;
    private static final int STATUS_OPEN = 1;
    private static final int TYPE_LOCK_SCREEN_LYRIC_CLOSE = 0;
    private static final int TYPE_LOCK_SCREEN_LYRIC_FULL_SCREEN = 2;
    private static final int TYPE_LOCK_SCREEN_LYRIC_NOT_FULL_SCREEN = 1;
    private SettingCard mBaseSettingCard;
    private HashMap<ShakeSensitivityType, Integer> mSensitivityTextResIdMap = new HashMap<>(ShakeSensitivityType.values().length);
    private ActionItem.InterfaceC1135b mOnItemClickListener = new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.fragment.settings.SettingEntryFragment.1
        @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
        /* renamed from: a */
        public void mo5409a(ActionItem actionItem, int i) {
            CharSequence m7006d = actionItem.m7006d();
            FragmentActivity activity = SettingEntryFragment.this.getActivity();
            boolean z = false;
            if (actionItem instanceof Checkable) {
                z = ((Checkable) actionItem).isChecked();
            }
            switch (actionItem.m7005e()) {
                case 0:
                    SettingUtils.m7779a(activity, DesktopLyricSettingActivity.class, m7006d);
                    //SUserUtils.m4956a(SAction.ACTION_SETTING_MENU_DESK_LYRIC, SPage.PAGE_DESKTOP_LYRIC);
                    return;
                case 1:
                    SettingEntryFragment.this.showLockScreenLyricSetting(actionItem, i);
                    //SUserUtils.m4956a(SAction.ACTION_OPEN_MENU_OF_LOCK_LYRIC, SPage.PAGE_NONE);
                    return;
                case 2:
                    SettingEntryFragment.this.showSensitivitySetting(actionItem, i);
                    //SUserUtils.m4956a(SAction.ACTION_OPEN_MENU_OF_SHAKE_CHANGE_SONG, SPage.PAGE_NONE);
                    return;
                case 3:
                    SettingUtils.m7779a(activity, HeadsetControlActivity.class, m7006d);
                    //SUserUtils.m4956a(SAction.ACTION_SETTING_HEADSET_CONTROL, SPage.PAGE_HEADSET_CONTROL);
                    return;
                case 4:
                    SettingUtils.m7779a(activity, MoreSettingActivity.class, m7006d);
                    //LocalStatistic.m5088r();
                    //SUserUtils.m4956a(SAction.ACTION_SETTING_MORE, SPage.PAGE_SETTING_MORE);
                    return;
                case 5:
                    Preferences.m2814x(z);
                    //LocalStatistic.m5154a(z);
                    //SUserUtils.m4955a(SAction.ACTION_SETTING_ONLY_WIFI, z);
                    return;
                case 6:
                    SettingUtils.m7779a(activity, AuditionAndDownloadQualityActivity.class, m7006d);
                    //LocalStatistic.m5088r();
                    //SUserUtils.m4956a(SAction.ACTION_SETTING_PLAY_DOWNLOAD_QUILITY, SPage.PAGE_AUDITION_AND_DOWNLOAD_QUALITY);
                    return;
                case 7:
                    SettingUtils.m7779a(activity, DownloadLocationActivity.class, "歌曲存储位置设置");
                    //SUserUtils.m4956a(SAction.ACTION_SETTING_MUSIC_SAVE_PATH, SPage.PAGE_DOWNLOAD_LOCATION);
                    return;
                case 8:
                    SettingUtils.m7779a(activity, LyricPictureSettingActivity.class, m7006d);
                    //LocalStatistic.m5088r();
                    //SUserUtils.m4956a(SAction.ACTION_SETTING_MUSIC_PIC, SPage.PAGE_LYRIC_AND_PICTURE);
                    return;
                case 9:
                    SettingEntryFragment.this.cleanCache();
                    //LocalStatistic.m5090p();
                    //SUserUtils.m4956a(SAction.ACTION_SETTING_CLEAR_CACHE, SPage.PAGE_NONE);
                    return;
                case 10:
                    SettingUtils.m7779a(activity, AboutActivity.class, m7006d);
                    //LocalStatistic.m5088r();
                    //SUserUtils.m4956a(SAction.ACTION_SETTING_ABOUT, SPage.PAGE_ABOUT_TTPOD);
                    return;
                default:
                    return;
            }
        }
    };

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setPage(SPage.PAGE_SETTING_PAGE);
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_settings, viewGroup, false);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mSensitivityTextResIdMap.put(ShakeSensitivityType.SMOOTH_SHAKE, Integer.valueOf((int) R.string.shake_play_smooth));
        this.mSensitivityTextResIdMap.put(ShakeSensitivityType.EASY_SHAKE, Integer.valueOf((int) R.string.shake_play_easy));
        this.mSensitivityTextResIdMap.put(ShakeSensitivityType.NORMAL_SHAKE, Integer.valueOf((int) R.string.shake_play_normal));
        this.mSensitivityTextResIdMap.put(ShakeSensitivityType.HARD_SHAKE, Integer.valueOf((int) R.string.shake_play_hard));
        this.mSensitivityTextResIdMap.put(ShakeSensitivityType.EXTREME_SHAKE, Integer.valueOf((int) R.string.shake_play_extreme));
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.setting_card_container_main);
        this.mBaseSettingCard = (SettingCard) buildBaseSettingCard();
        ((LinearLayout) linearLayout.findViewById(R.id.setting_card_container_base_setting)).addView(this.mBaseSettingCard.m6992e());
        ((LinearLayout) linearLayout.findViewById(R.id.setting_card_container_download_setting)).addView(buildDownloadSettingCard().m6992e());
        ((LinearLayout) linearLayout.findViewById(R.id.setting_card_container_about)).addView(buildAboutSettingCard().m6992e());
    }

    private Card buildBaseSettingCard() {
        return new SettingCard(getActivity(), new SettingItem[]{new SettingItem(0, 0, (int) R.string.setting_desktop_lyric, 0, (int) R.string.icon_arrow_right, true), (SettingItem) new SettingItem(1, 0, (int) R.string.setting_lockscreen_lyric, 0, (int) R.string.icon_arrow_right, true).m7008b(getLockScreenLyricSettingText()), (SettingItem) new SettingItem(2, 0, (int) R.string.setting_shake_play, 0, (int) R.string.icon_arrow_right, true).m7008b(getSensitivitySettingText()), new SettingItem(3, 0, (int) R.string.setting_headset, 0, (int) R.string.icon_arrow_right, true), new SettingItem(4, 0, (int) R.string.more, 0, (int) R.string.icon_arrow_right, true)}, R.string.base_setting, this.mOnItemClickListener);
    }

    private Card buildDownloadSettingCard() {
        return new SettingCard(getActivity(), new SettingItem[]{new CheckableSettingItem(5, 0, R.string.wifi_only, 0, 0, Preferences.m3066H()), new SettingItem(6, 0, (int) R.string.setting_listen_and_download_quality, 0, (int) R.string.icon_arrow_right, true), new SettingItem(7, 0, (int) R.string.setting_audio_auto_download_dir, 0, (int) R.string.icon_arrow_right, true), new SettingItem(8, 0, (int) R.string.setting_lyric_and_pic, 0, (int) R.string.icon_arrow_right, true), new SettingItem(9, 0, (int) R.string.setting_cache_clean, 0, (int) R.string.icon_arrow_right, true)}, R.string.network_download, this.mOnItemClickListener);
    }

    private Card buildAboutSettingCard() {
        SettingCard settingCard = new SettingCard(getActivity(), new SettingItem[]{new SettingItem(10, 0, (int) R.string.setting_about_ttpod, 0, (int) R.string.icon_arrow_right, true)}, R.string.setting_about, this.mOnItemClickListener);
        setLatestUpdateVersionDot(settingCard);
        return settingCard;
    }

    private void setLatestUpdateVersionDot(SettingCard settingCard) {
        View m7801a = settingCard.m7801a(10);
        if (m7801a != null) {
            m7801a.setVisibility(VersionUpdateModule.hasNewVersion() ? View.VISIBLE : View.GONE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLockScreenLyricSetting(final ActionItem actionItem, final int i) {
        PopupsUtils.m6735a(getActivity(), actionItem.m7006d(), new CheckableActionItem[]{new CheckableActionItem(0, R.string.setting_close), new CheckableActionItem(1, R.string.setting_not_full_screen), new CheckableActionItem(2, R.string.setting_full_screen)}, getLockScreenLyricCheckedId(), new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.fragment.settings.SettingEntryFragment.2
            @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
            /* renamed from: a */
            public void mo5409a(ActionItem actionItem2, int i2) {
                if (i2 == 0) {
                    actionItem.m7010a(actionItem2.m7006d());
                    SettingEntryFragment.this.mBaseSettingCard.m7799a((SettingItem) actionItem, i);
                    Preferences.m2875h(false);
                } else {
                    Preferences.m2875h(true);
                    actionItem.m7010a(actionItem2.m7006d());
                    SettingEntryFragment.this.mBaseSettingCard.m7799a((SettingItem) actionItem, i);
                    if (i2 == 1) {
                        Preferences.m2819v(false);
                    } else {
                        Preferences.m2819v(true);
                    }
                }

            }
        }, new BaseDialog.InterfaceC1064a<SingleChoiceListDialog>() { // from class: com.sds.android.ttpod.fragment.settings.SettingEntryFragment.3
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(SingleChoiceListDialog singleChoiceListDialog) {
            }
        });
    }

    private int getLockScreenLyricCheckedId() {
        if (!Preferences.m2871i(LockScreenModule.isAllowDefaultLockScreen())) {
            return 0;
        }
        if (!Preferences.m3070F()) {
            return 1;
        }
        return 2;
    }

    private int getLockScreenLyricSettingText() {
        if (getLockScreenLyricCheckedId() == 0) {
            return R.string.setting_close;
        }
        if (getLockScreenLyricCheckedId() == 1) {
            return R.string.setting_not_full_screen;
        }
        return R.string.setting_full_screen;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSensitivitySetting(final ActionItem actionItem, final int i) {
        PopupsUtils.m6735a(getActivity(), actionItem.m7006d(), new CheckableActionItem[]{new CheckableActionItem(0, R.string.setting_close), new CheckableActionItem(ShakeSensitivityType.SMOOTH_SHAKE.ordinal() + 1, this.mSensitivityTextResIdMap.get(ShakeSensitivityType.SMOOTH_SHAKE).intValue()), new CheckableActionItem(ShakeSensitivityType.EASY_SHAKE.ordinal() + 1, this.mSensitivityTextResIdMap.get(ShakeSensitivityType.EASY_SHAKE).intValue()), new CheckableActionItem(ShakeSensitivityType.NORMAL_SHAKE.ordinal() + 1, this.mSensitivityTextResIdMap.get(ShakeSensitivityType.NORMAL_SHAKE).intValue()), new CheckableActionItem(ShakeSensitivityType.HARD_SHAKE.ordinal() + 1, this.mSensitivityTextResIdMap.get(ShakeSensitivityType.HARD_SHAKE).intValue()), new CheckableActionItem(ShakeSensitivityType.EXTREME_SHAKE.ordinal() + 1, this.mSensitivityTextResIdMap.get(ShakeSensitivityType.EXTREME_SHAKE).intValue())}, getSensitivitySettingCheckedId(), new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.fragment.settings.SettingEntryFragment.4
            @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
            /* renamed from: a */
            public void mo5409a(ActionItem actionItem2, int i2) {
                if (i2 == 0) {
                    actionItem.m7010a(actionItem2.m7006d());
                    SettingEntryFragment.this.mBaseSettingCard.m7799a((SettingItem) actionItem, i);
                    Preferences.m2851n(false);
                    CommandCenter.getInstance().execute(new Command(CommandID.SET_SHAKE_SWITCH_SONG_ENABLED, false));
                } else {
                    if (!Preferences.m2818w()) {
                        Preferences.m2851n(true);
                        CommandCenter.getInstance().execute(new Command(CommandID.SET_SHAKE_SWITCH_SONG_ENABLED, true));
                    }
                    ShakeSensitivityType shakeSensitivityType = ShakeSensitivityType.values()[actionItem2.m7005e() - 1];
                    actionItem.m7008b(((Integer) SettingEntryFragment.this.mSensitivityTextResIdMap.get(shakeSensitivityType)).intValue());
                    SettingEntryFragment.this.mBaseSettingCard.m7799a((SettingItem) actionItem, i);
                    CommandCenter.getInstance().execute(new Command(CommandID.SET_SHAKE_SWITCH_SONG_SENSITIVITY, shakeSensitivityType));
                }
                //new SUserEvent("PAGE_CLICK", SAction.ACTION_OPEN_MENU_OF_SHAKE_CHANGE_SONG_SELECT.getValue(), SPage.PAGE_SETTING_PAGE.getValue(), 0).append("type", Integer.valueOf(i2)).post();
            }
        }, new BaseDialog.InterfaceC1064a<SingleChoiceListDialog>() { // from class: com.sds.android.ttpod.fragment.settings.SettingEntryFragment.5
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(SingleChoiceListDialog singleChoiceListDialog) {
            }
        });
    }

    private int getSensitivitySettingCheckedId() {
        if (Preferences.m2818w()) {
            return Preferences.m2815x().ordinal() + 1;
        }
        return 0;
    }

    private int getSensitivitySettingText() {
        return getSensitivitySettingCheckedId() == 0 ? R.string.setting_close : this.mSensitivityTextResIdMap.get(Preferences.m2815x()).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cleanCache() {
        PopupsUtils.m6744a((Context) getActivity(), (int) R.string.cache_size_calculating, false, false);
        TaskScheduler.m8582a(new TaskScheduler.SchedulerAsyncTask<Void, String[]>(null) { // from class: com.sds.android.ttpod.fragment.settings.SettingEntryFragment.6
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public String[] inBackground(Void r12) {
                return new String[]{Formatter.formatFileSize(SettingEntryFragment.this.getActivity(), FileUtils.m8405g(TTPodConfig.getCacheObjectPath())), Formatter.formatFileSize(SettingEntryFragment.this.getActivity(), FileUtils.m8405g(TTPodConfig.getCacheMediaPath())), Formatter.formatFileSize(SettingEntryFragment.this.getActivity(), FileUtils.m8405g(TTPodConfig.getArtPath()) + FileUtils.m8405g(TTPodConfig.getArtistPath())), Formatter.formatFileSize(SettingEntryFragment.this.getActivity(), FileUtils.m8405g(TTPodConfig.getLyricPath()))};
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
            /* renamed from: a */
            public void postExecute(String[] strArr) {
                if (SettingEntryFragment.this.getActivity() != null && SettingEntryFragment.this.isResumeState()) {
                    PopupsUtils.m6761a();
                    PopupsUtils.m6724a(SettingEntryFragment.this.getActivity(), strArr, SettingEntryFragment.this.isResumeState());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isResumeState() {
        return isViewAccessAble();
    }
}
