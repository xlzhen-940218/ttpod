package com.sds.android.ttpod.activities.mediascan.setting;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Checkable;
import android.widget.LinearLayout;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.activities.setting.CheckableSettingItem;
import com.sds.android.ttpod.activities.setting.SettingCard;
import com.sds.android.ttpod.activities.setting.SettingItem;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p085b.Card;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;

/* loaded from: classes.dex */
public class MediaScanSettingActivity extends SlidingClosableActivity {
    private static final int ID_AUTO_SCAN_FOLDER = 4;
    private static final int ID_EXCLUDE_60 = 0;
    private static final int ID_EXCLUDE_FOLDER = 3;
    private static final int ID_EXCLUDE_FORMATS = 1;
    private static final int ID_EXCLUDE_HIDE_FOLDER = 2;
    private static final int REQUEST_CODE_AUTO_SCAN = 0;
    private ActionItem.InterfaceC1135b mOnItemClickListener = new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.activities.mediascan.setting.MediaScanSettingActivity.1
        @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
        /* renamed from: a */
        public void mo5409a(ActionItem actionItem, int i) {
            switch (actionItem.m7005e()) {
                case 0:
                    Preferences.m2899c(((Checkable) actionItem).isChecked());
                    return;
                case 1:
                    Preferences.m2893d(((Checkable) actionItem).isChecked());
                    return;
                case 2:
                    Preferences.m2887e(((Checkable) actionItem).isChecked());
                    return;
                case 3:
                    MediaScanSettingActivity.this.startActivity(new Intent(MediaScanSettingActivity.this, MediaScanExcludeActivity.class));
                    return;
                case 4:
                    MediaScanSettingActivity.this.startActivityForResult(new Intent(MediaScanSettingActivity.this, MediaScanAutoScanActivity.class), 0);
                    return;
                default:
                    return;
            }
        }
    };

    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_mediascan_setting);
        setPage(SPage.PAGE_SCAN_MUSIC_SETTING);
        setTitle(R.string.mediascan_setting);
        getActionBarController().m7179d();
        ((LinearLayout) findViewById(R.id.setting_card_container_main)).addView(buildCommonCard().m6992e());
    }

    private Card buildCommonCard() {
        SettingCard settingCard = new SettingCard(this, new SettingItem[]{new CheckableSettingItem(0, 0, R.string.mediascan_setting_exclude_60, 0, 0, Preferences.durationLessThan60()), new CheckableSettingItem(1, 0, R.string.mediascan_setting_exclude_formats, 0, 0, Preferences.excludeAmrMid()), new CheckableSettingItem(2, 0, R.string.mediascan_setting_exclude_hidden_folders, 0, 0, Preferences.excludeHiddenFolder()), new SettingItem(3, 0, (int) R.string.mediascan_setting_exclude_folders, 0, (int) R.drawable.img_setting_right_arrow), new SettingItem(4, 0, (int) R.string.mediascan_setting_auto_scan_foders, 0, (int) R.drawable.img_setting_right_arrow)}, R.string.mediascan_setting_auto_scan_start, this.mOnItemClickListener);
        settingCard.m6995a(false);
        return settingCard;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case 0:
                if (i2 == -1) {
                    setResult(-1, null);
                    finish();
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ActionBarActivity
    public void onActionBarBackPressed() {
        finish();
    }
}
