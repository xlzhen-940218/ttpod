package com.sds.android.ttpod.activities.setting;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Checkable;
import android.widget.LinearLayout;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.OptionalDialog;
import com.sds.android.ttpod.framework.modules.p123e.LockScreenModule;
import com.sds.android.ttpod.framework.p106a.p107a.LocalStatistic;
import com.sds.android.ttpod.framework.storage.environment.Preferences;

/* loaded from: classes.dex */
public class DisplaySettingActivity extends SlidingClosableActivity {
    private static final int ID_AUTO_ORIENTATE = 4;
    private static final int ID_BACKLIGHT = 6;
    private static final int ID_DESKTOP_LYRIC = 0;
    private static final int ID_HIDE_STATUS_BAR = 3;
    private static final int ID_LOCK_SCREEN_ENABLE = 2;
    private static final int ID_LYRIC_LOCKED = 1;
    private static final int ID_NOTIFICATION = 5;
    private ActionItem.InterfaceC1135b mOnItemClickListener = new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.activities.setting.DisplaySettingActivity.1
        @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
        /* renamed from: a */
        public void mo5409a(ActionItem actionItem, int i) {
            boolean z = false;
            if (actionItem instanceof Checkable) {
                z = ((Checkable) actionItem).isChecked();
            }
            DisplaySettingActivity displaySettingActivity = DisplaySettingActivity.this;
            switch (actionItem.m7005e()) {
                case 0:
                    DisplaySettingActivity.this.setDesktopLyric(actionItem);
                    return;
                case 1:
                    Preferences.m2810z(((Checkable) actionItem).isChecked());
                    return;
                case 2:
                    Preferences.m2875h(z);
                    return;
                case 3:
                    Preferences.m2819v(z);
                    return;
                case 4:
                    Preferences.m2883f(((Checkable) actionItem).isChecked());
                    LocalStatistic.m5105b(((Checkable) actionItem).isChecked());
                    return;
                case 5:
                    SettingUtils.m7779a(displaySettingActivity, NotificationSettingActivity.class, actionItem.m7006d());
                    LocalStatistic.m5092n();
                    return;
                case 6:
                    SettingUtils.m7779a(displaySettingActivity, BacklightSettingActivity.class, actionItem.m7006d());
                    LocalStatistic.m5091o();
                    return;
                default:
                    return;
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_setting_main);
        SettingUtils.m7778a(this);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.setting_card_container);
        linearLayout.addView(buildDesktopLyricSettingCard().m6992e());
        linearLayout.addView(buildLockscreenCard().m6992e());
        linearLayout.addView(buildLandscapeCard().m6992e());
        linearLayout.addView(buildNotificationCard().m6992e());
        linearLayout.addView(buildBacklightCard().m6992e());
    }

    private SettingCard buildLockscreenCard() {
        return new SettingCard(this, new SettingItem[]{new CheckableSettingItem(2, 0, R.string.setting_lockscreen_enable, 0, 0, Preferences.m2871i(LockScreenModule.isAllowDefaultLockScreen())), new CheckableSettingItem(3, 0, R.string.setting_hide_status_bar, 0, 0, Preferences.m3070F())}, R.string.setting_lockscreen, this.mOnItemClickListener);
    }

    private SettingCard buildLandscapeCard() {
        return new SettingCard(this, new SettingItem[]{new CheckableSettingItem(4, 0, R.string.setting_auto_orientate, 0, 0, Preferences.m2842q())}, R.string.setting_lockscreen, this.mOnItemClickListener);
    }

    private SettingCard buildNotificationCard() {
        return new SettingCard(this, new SettingItem[]{new SettingItem(5, 0, (int) R.string.setting_notification, 0, (int) R.string.icon_arrow_right, true)}, R.string.setting_lockscreen, this.mOnItemClickListener);
    }

    private SettingCard buildBacklightCard() {
        return new SettingCard(this, new SettingItem[]{new SettingItem(6, 0, (int) R.string.setting_backlight, 0, (int) R.string.icon_arrow_right, true)}, R.string.setting_lockscreen, this.mOnItemClickListener);
    }

    private SettingCard buildDesktopLyricSettingCard() {
        return new SettingCard(this, new SettingItem[]{new CheckableSettingItem(0, 0, R.string.show_desktop_lyric, 0, 0, Preferences.m2838r()), new CheckableSettingItem(1, 0, R.string.minilyric_button_lock, 0, 0, Preferences.m2980aa())}, R.string.setting_desktop_lyric, this.mOnItemClickListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDesktopLyric(ActionItem actionItem) {
        OptionalDialog m6747a;
        boolean isChecked = ((Checkable) actionItem).isChecked();
        Preferences.m2879g(isChecked);
        if (isChecked && Preferences.m3006aA() && (m6747a = PopupsUtils.m6747a(this, (int) R.string.never_show_again, "提示", "如果遇到MIUI V5系统无法显示桌面歌词的情况，请找到设置->应用->天天动听->打开悬浮窗即可", (BaseDialog.InterfaceC1064a<OptionalDialog>) null)) != null) {
            m6747a.m7261a(R.string.set_at_once, new BaseDialog.InterfaceC1064a<OptionalDialog>() { // from class: com.sds.android.ttpod.activities.setting.DisplaySettingActivity.2
                /* renamed from: a */
                private Intent m7817a() {
                    if (SDKVersionUtils.m8372b()) {
                        return new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + EnvironmentUtils.m8526a()));
                    }
                    if (SDKVersionUtils.m8373a()) {
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                        intent.putExtra("pkg", EnvironmentUtils.m8526a());
                        return intent;
                    }
                    Intent intent2 = new Intent("android.intent.action.VIEW");
                    intent2.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                    intent2.putExtra("com.android.settings.ApplicationPkgName", EnvironmentUtils.m8526a());
                    return intent2;
                }

                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo2038a(OptionalDialog optionalDialog) {
                    DisplaySettingActivity.this.startActivity(m7817a());
                    Preferences.m3061J(!optionalDialog.m6808b());
                }
            }, R.string.i_known, new BaseDialog.InterfaceC1064a<OptionalDialog>() { // from class: com.sds.android.ttpod.activities.setting.DisplaySettingActivity.3
                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo2038a(OptionalDialog optionalDialog) {
                    Preferences.m3061J(!optionalDialog.m6808b());
                }
            });
            m6747a.show();
        }
    }
}
