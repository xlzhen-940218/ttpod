package com.sds.android.ttpod.activities.setting;

import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.ViewGroup;

import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p085b.CheckableActionItem;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.SingleChoiceListDialog;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;

/* loaded from: classes.dex */
public class NotificationSettingActivity extends SlidingClosableActivity {
    private static final int ID_HIDE_WHILE_PAUSING = 4;
    private static final int ID_PRIORITY = 1;
    private static final int ID_SHOW_CLOSE_BUTTON = 3;
    private static final int ID_SHOW_PREV_BUTTON = 2;
    private SettingCard mSettingCard;
    private SparseIntArray mPriorityResArray = new SparseIntArray();
    private ActionItem.InterfaceC1135b mOnItemClickListener = new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.activities.setting.NotificationSettingActivity.1

       boolean f2990a= !NotificationSettingActivity.class.desiredAssertionStatus();



        @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
        /* renamed from: a */
        public void mo5409a(ActionItem actionItem, int i) {
            switch (actionItem.m7005e()) {
                case 1:
                    NotificationSettingActivity.this.showPrioritySetting(actionItem, i);
                    //SUserUtils.m4956a(SAction.ACTION_SETTING_NOTIFICATION_PRIORITY, SPage.PAGE_NONE);
                    return;
                case 2:
                    if (!f2990a && !(actionItem instanceof CheckableSettingItem)) {
                        throw new AssertionError();
                    }
                    Preferences.m3031Y(((CheckableSettingItem) actionItem).isChecked());
                    //SUserUtils.m4955a(SAction.ACTION_SETTING_SHOW_PREVIOUS_BUTTON, ((CheckableSettingItem) actionItem).isChecked());
                    return;
                case 3:
                    if (!f2990a && !(actionItem instanceof CheckableSettingItem)) {
                        throw new AssertionError();
                    }
                    Preferences.m3029Z(((CheckableSettingItem) actionItem).isChecked());
                    //SUserUtils.m4955a(SAction.ACTION_SETTING_SHOW_CLOSE_BUTTON, ((CheckableSettingItem) actionItem).isChecked());
                    return;
                case 4:
                    if (!f2990a && !(actionItem instanceof CheckableSettingItem)) {
                        throw new AssertionError();
                    }
                    Preferences.m2979aa(!((CheckableSettingItem) actionItem).isChecked());
                    //SUserUtils.m4955a(SAction.ACTION_SETTING_PAUSE_AUTO_HIDE, ((CheckableSettingItem) actionItem).isChecked());
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
        setPage(SPage.PAGE_SETTING_NOTIFICATION);
        setContentView(R.layout.activity_setting_notification);
        SettingUtils.m7778a(this);
        this.mPriorityResArray.put(2, R.string.notification_priority_max);
        this.mPriorityResArray.put(1, R.string.notification_priority_high);
        this.mPriorityResArray.put(0, R.string.notification_priority_default);
        this.mPriorityResArray.put(-1, R.string.notification_priority_low);
        this.mPriorityResArray.put(-2, R.string.notification_priority_min);
        this.mSettingCard = new SettingCard(this, buildSettingItems(), R.string.setting_notification, this.mOnItemClickListener);
        ((ViewGroup) findViewById(R.id.setting_card_container_notification)).addView(this.mSettingCard.m6992e());
    }

    private SettingItem[] buildSettingItems() {
        SettingItem settingItem = new SettingItem(1, 0, (int) R.string.setting_notification_priority, this.mPriorityResArray.get(Preferences.m2989aR()), 0, true);
        CheckableSettingItem checkableSettingItem = new CheckableSettingItem(2, 0, R.string.setting_notification_prev, 0, 0, Preferences.m2988aS());
        CheckableSettingItem checkableSettingItem2 = new CheckableSettingItem(3, 0, R.string.setting_notification_close, 0, 0, Preferences.m2988aS());
        CheckableSettingItem checkableSettingItem3 = new CheckableSettingItem(4, 0, R.string.setting_notification_hide_while_pausing, 0, 0, !Preferences.m2986aU());
        return SDKVersionUtils.sdkThan16() ? new SettingItem[]{settingItem, checkableSettingItem, checkableSettingItem2, checkableSettingItem3} : SDKVersionUtils.sdkThan14() ? new SettingItem[]{checkableSettingItem, checkableSettingItem2, checkableSettingItem3} : new SettingItem[]{checkableSettingItem3};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPrioritySetting(final ActionItem actionItem, final int i) {
        PopupsUtils.m6735a(this, getString(R.string.notification_priority_title), new CheckableActionItem[]{new CheckableActionItem(2, R.string.notification_priority_max), new CheckableActionItem(1, R.string.notification_priority_high), new CheckableActionItem(0, R.string.notification_priority_default), new CheckableActionItem(-1, R.string.notification_priority_low), new CheckableActionItem(-2, R.string.notification_priority_min)}, Preferences.m2989aR(), new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.activities.setting.NotificationSettingActivity.2
            @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
            /* renamed from: a */
            public void mo5409a(ActionItem actionItem2, int i2) {
                actionItem.m7010a(actionItem2.m7006d());
                NotificationSettingActivity.this.mSettingCard.m7799a((SettingItem) actionItem, i);
                Preferences.m2845p(actionItem2.m7005e());

            }
        }, (BaseDialog.OnClickListener<SingleChoiceListDialog>) null);
    }
}
