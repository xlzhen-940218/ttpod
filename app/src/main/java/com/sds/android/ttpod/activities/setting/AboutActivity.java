package com.sds.android.ttpod.activities.setting;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.ThirdParty.ThirdPartyManager;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.version.VersionUpdateModule;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;

/* loaded from: classes.dex */
public class AboutActivity extends SlidingClosableActivity {
    private static final int ID_CONTACT_US = 4;
    private static final int ID_FEEDBACK = 2;
    private static final int ID_RATE = 3;
    private static final int ID_UPGRADE = 1;
    private ActionItem.InterfaceC1135b mOnItemClickListener = new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.activities.setting.AboutActivity.1
        @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
        /* renamed from: a */
        public void mo5409a(ActionItem actionItem, int i) {
            CharSequence m7006d = actionItem.m7006d();
            switch (actionItem.m7005e()) {
                case 1:
                    if (EnvironmentUtils.AppConfig.getAppCheckUpdateEnable()) {
                        if (EnvironmentUtils.DeviceConfig.m8474e()) {
                            PopupsUtils.m6760a((int) R.string.version_upgrade_check_toast);
                            CommandCenter.getInstance().execute(new Command(CommandID.CHECK_UPGRADE, false));
                        } else {
                            PopupsUtils.m6760a((int) R.string.version_update_network_bad);
                        }
                    }
                    //SUserUtils.m4956a(SAction.ACTION_SETTING_CHECK_UPDATE, SPage.PAGE_NONE);
                    return;
                case 2:
                    SettingUtils.m7779a(AboutActivity.this, HelpFeedbackActivity.class, m7006d);
                    //LocalStatistic.m5087s();
                    //SUserUtils.m4956a(SAction.ACTION_SETTING_HELP_REPLAY, SPage.PAGE_SETTING_QUESTION);
                    return;
                case 3:
                    AboutActivity.this.ratingTTPod();
                    //SUserUtils.m4956a(SAction.ACTION_SETTING_SCORE, SPage.PAGE_NONE);
                    return;
                case 4:
                    SettingUtils.m7779a(AboutActivity.this, ContactUsActivity.class, actionItem.m7006d());
                    //SUserUtils.m4956a(SAction.ACTION_SETTING_CONTACT, SPage.PAGE_CONTACT_US);
                    return;
                default:
                    return;
            }
        }
    };
    private SettingCard mVersionCard;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setPage(SPage.PAGE_ABOUT_TTPOD);
        setContentView(R.layout.activity_setting_about);
        SettingUtils.m7778a(this);
        ((TextView) findViewById(R.id.id_about_version)).setText(EnvironmentUtils.AppConfig.getVersionName());
        this.mVersionCard = buildAboutCard();
        ((LinearLayout) findViewById(R.id.setting_card_container_about)).addView(this.mVersionCard.m6992e());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        ThirdPartyManager.m8312c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ThirdPartyManager.m8313b();
    }

    private SettingCard buildAboutCard() {
        SettingCard settingCard = new SettingCard(this, new SettingItem[]{(SettingItem) new SettingItem(1, 0, (int) R.string.version_update_about_check, 0, 0, true).m7008b(VersionUpdateModule.hasNewVersion() ? R.string.version_update_about_upgrade_enable : R.string.version_update_about_already_latest), new SettingItem(2, 0, (int) R.string.setting_help_feedback, 0, (int) R.string.icon_arrow_right, true), new SettingItem(3, 0, (int) R.string.app_rate, 0, (int) R.string.icon_arrow_right, true), new SettingItem(4, 0, (int) R.string.contact_us, 0, (int) R.string.icon_arrow_right, true)}, R.string.setting_about, this.mOnItemClickListener);
        settingCard.m6995a(false);
        return settingCard;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ratingTTPod() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("market://details?id=" + getPackageName()));
        try {
            startActivity(Intent.createChooser(intent, getString(R.string.market)));
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }
}
