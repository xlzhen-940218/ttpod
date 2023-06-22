package com.sds.android.ttpod.activities.setting;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Checkable;

import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.ttpod.EntryActivity;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.SoundSearchActivity;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p085b.Card;
import com.sds.android.ttpod.component.p085b.CheckableActionItem;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.MultiChoiceListDialog;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.p106a.p107a.LocalStatistic;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.p106a.p107a.SUserUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.utils.LogcatUtils;
import com.sds.android.ttpod.utils.ShortcutUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class MoreSettingActivity extends SlidingClosableActivity {
    private static final int CREATE_RECOGNIZER_ICON = 2;
    private static final int CREATE_TTPOD_ICON = 1;
    private static final int ID_AIRPLANE_MODE_WHILE_SLEEPING = 5;
    private static final int ID_AUTO_ORIENTATE = 1;
    private static final int ID_BACKLIGHT = 3;
    private static final int ID_CREATE_SHORTCUT = 8;
    private static final int ID_DELETE_LOGCAT = 10;
    private static final int ID_LOGCAT = 9;
    private static final int ID_NOTIFICATION = 2;
    private static final int ID_PLAY_WHILE_STARTING = 4;
    private static final int ID_RECEIVE_PUSH_MESSAGE = 6;
    private static final int ID_SHOW_APP_RECOMMEND_MENU = 7;
    private ActionItem.InterfaceC1135b mOnItemClickListener = new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.activities.setting.MoreSettingActivity.1
        @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
        /* renamed from: a */
        public void mo5409a(ActionItem actionItem, int i) {
            boolean z = false;
            if (actionItem instanceof Checkable) {
                z = ((Checkable) actionItem).isChecked();
            }
            switch (actionItem.m7005e()) {
                case 1:
                    Preferences.m2883f(z);
                    LocalStatistic.m5105b(z);
                    SUserUtils.m4955a(SAction.ACTION_SETTING_LANDSCAPE, z);
                    return;
                case 2:
                    SettingUtils.m7779a(MoreSettingActivity.this, NotificationSettingActivity.class, actionItem.m7006d());
                    LocalStatistic.m5092n();
                    SUserUtils.m4956a(SAction.ACTION_SETTING_NOTIFICATION, SPage.PAGE_SETTING_NOTIFICATION);
                    return;
                case 3:
                    SettingUtils.m7779a(MoreSettingActivity.this, BacklightSettingActivity.class, actionItem.m7006d());
                    LocalStatistic.m5091o();
                    SUserUtils.m4956a(SAction.ACTION_SETTING_BACKLIGHT, SPage.PAGE_SETTING_BACKLIGHT);
                    return;
                case 4:
                    Preferences.m2831s(z);
                    SUserUtils.m4955a(SAction.ACTION_SETTING_APPLICATION_AUTO_PLAY, z);
                    return;
                case 5:
                    Preferences.m2827t(z);
                    return;
                case 6:
                    Preferences.m2816w(z);
                    SUserUtils.m4955a(SAction.ACTION_SETTING_RECEIVE_PUSH_MESSAGE, z);
                    try {
                        if (z) {
                            //PushManager.getInstance().initialize(BaseApplication.m4635c());
                        } else {
                            //PushManager.getInstance().stopService(BaseApplication.m4635c());
                        }
                        LocalStatistic.m5103c(z);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                case 7:
                    Preferences.m2967ag(z);
                    SUserUtils.m4955a(SAction.ACTION_SETTING_SHOW_APP_RECOMMAND, z);
                    return;
                case 8:
                    MoreSettingActivity.this.showCreateDesktopShortcutDialog();
                    SUserUtils.m4956a(SAction.ACTION_SETTING_CREATE_DESKTOP_ICON, SPage.PAGE_NONE);
                    return;
                case 9:
                    LogcatUtils.m8261a();
                    PopupsUtils.m6721a(MoreSettingActivity.this.getResources().getString(R.string.setting_logcat_msg) + LogcatUtils.m8260b());
                    SUserUtils.m4956a(SAction.ACTION_SETTING_SAVE_LOG, SPage.PAGE_NONE);
                    return;
                case 10:
                    LogcatUtils.m8259c();
                    PopupsUtils.m6721a(MoreSettingActivity.this.getResources().getString(R.string.setting_log_clear));
                    SUserUtils.m4956a(SAction.ACTION_SETTING_DELETE_LOG, SPage.PAGE_NONE);
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
        setPage(SPage.PAGE_SETTING_MORE);
        setContentView(R.layout.activity_setting_other);
        SettingUtils.m7778a(this);
        ((ViewGroup) findViewById(R.id.setting_card_container_other)).addView(buildMoreCard().m6992e());
        ((ViewGroup) findViewById(R.id.setting_card_container_other)).addView(buildSaveLogCard().m6992e());
    }

    private SettingCard buildMoreCard() {
        CheckableSettingItem checkableSettingItem = new CheckableSettingItem(1, 0, R.string.setting_auto_orientate, 0, 0, Preferences.m2842q());
        SettingItem settingItem = new SettingItem(2, 0, (int) R.string.setting_notification, 0, (int) R.string.icon_arrow_right, true);
        SettingItem settingItem2 = new SettingItem(3, 0, (int) R.string.setting_backlight, 0, (int) R.string.icon_arrow_right, true);
        CheckableSettingItem checkableSettingItem2 = new CheckableSettingItem(4, 0, R.string.play_while_starting, 0, 0, Preferences.m3076C());
        CheckableSettingItem checkableSettingItem3 = new CheckableSettingItem(5, 0, R.string.airplane_while_sleeping, 0, 0, Preferences.m3074D());
        CheckableSettingItem checkableSettingItem4 = new CheckableSettingItem(6, 0, R.string.recevie_push_message, 0, 0, Preferences.m3068G());
        CheckableSettingItem checkableSettingItem5 = new CheckableSettingItem(7, 0, R.string.show_app_recommend_menu, 0, 0, Preferences.m2913bp());
        SettingItem settingItem3 = new SettingItem(8, 0, (int) R.string.create_desktop_icon, 0, (int) R.string.icon_arrow_right, true);
        ArrayList arrayList = new ArrayList();
        arrayList.add(checkableSettingItem);
        arrayList.add(settingItem);
        arrayList.add(settingItem2);
        arrayList.add(checkableSettingItem2);
        if (!SDKVersionUtils.m8366h()) {
            arrayList.add(checkableSettingItem3);
        }
        arrayList.add(checkableSettingItem4);
        if (Preferences.m2912bq()) {
            arrayList.add(checkableSettingItem5);
        }
        arrayList.add(settingItem3);
        return new SettingCard(this, (SettingItem[]) arrayList.toArray(new SettingItem[arrayList.size()]), R.string.more, this.mOnItemClickListener);
    }

    private Card buildSaveLogCard() {
        return new SettingCard(this, new SettingItem[]{new SettingItem(9, 0, (int) R.string.setting_logcat, 0, (int) R.string.icon_arrow_right, true), new SettingItem(10, 0, (int) R.string.setting_delete_log, 0, (int) R.string.icon_arrow_right, true)}, R.string.setting_logcat, this.mOnItemClickListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCreateDesktopShortcutDialog() {
        MultiChoiceListDialog multiChoiceListDialog = new MultiChoiceListDialog(this, new CheckableActionItem[]{new CheckableActionItem(1, R.string.ttpod, true), new CheckableActionItem(2, R.string.search_sound_search, true)}, new BaseDialog.InterfaceC1064a<MultiChoiceListDialog>() { // from class: com.sds.android.ttpod.activities.setting.MoreSettingActivity.2
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(MultiChoiceListDialog multiChoiceListDialog2) {
                List<CheckableActionItem> m6813e = multiChoiceListDialog2.m6813e();
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                for (CheckableActionItem checkableActionItem : m6813e) {
                    int e = checkableActionItem.m7005e();
                    if (1 == e) {
                        ShortcutUtil.m8234a(MoreSettingActivity.this, EntryActivity.class, R.drawable.img_ttpod, R.string.ttpod);
                    } else if (2 == e) {
                        ShortcutUtil.m8234a(MoreSettingActivity.this, SoundSearchActivity.class, R.drawable.img_recognizer_song, R.string.search_sound_search);
                    }
                    sb.append(e);
                    sb.append(',');
                }
                sb.append("]");
                SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", SAction.ACTION_SETTING_CREATE_DESKTOP_ICON_OK.getValue(), 0, 0);
                sUserEvent.setPageParameter(true);
                sUserEvent.append("type", sb.toString());
                sUserEvent.post();
            }
        }, (BaseDialog.InterfaceC1064a<MultiChoiceListDialog>) null);
        multiChoiceListDialog.setTitle(getString(R.string.select_create_icon));
        multiChoiceListDialog.show();
    }
}
