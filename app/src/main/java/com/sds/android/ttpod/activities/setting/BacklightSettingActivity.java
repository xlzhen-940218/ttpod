package com.sds.android.ttpod.activities.setting;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Checkable;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.p106a.p107a.SUserUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;

/* loaded from: classes.dex */
public class BacklightSettingActivity extends SlidingClosableActivity {
    private static final int ID_LANDSCAPE = 4;
    private static final int ID_LIST = 1;
    private static final int ID_LOCK_SCREEN = 3;
    private static final int ID_PLAYER = 2;
    private ActionItem.InterfaceC1135b mOnItemClickListener = new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.activities.setting.BacklightSettingActivity.1
        @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
        /* renamed from: a */
        public void mo5409a(ActionItem actionItem, int i) {
            if (!(actionItem instanceof Checkable)) {
                throw new IllegalArgumentException("must be Checkable!");
            }
            boolean isChecked = ((CheckableSettingItem) actionItem).isChecked();
            switch (actionItem.m7005e()) {
                case 1:
                    Preferences.m2847o(isChecked);
                    SUserUtils.m4955a(SAction.ACTION_SETTING_LIST_LIGHT, isChecked);
                    return;
                case 2:
                    Preferences.m2843p(isChecked);
                    SUserUtils.m4955a(SAction.ACTION_SETTING_PLAY_LIGHT, isChecked);
                    return;
                case 3:
                    Preferences.m2839q(isChecked);
                    SUserUtils.m4955a(SAction.ACTION_SETTING_LOCK_LIGHT, isChecked);
                    return;
                case 4:
                    Preferences.m2835r(isChecked);
                    SUserUtils.m4955a(SAction.ACTION_SETTING_LANDSCAPE_LIGHT, isChecked);
                    return;
                default:
                    throw new IllegalArgumentException("illegal ID");
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setPage(SPage.PAGE_SETTING_BACKLIGHT);
        setContentView(R.layout.activity_setting_backlight);
        SettingUtils.m7778a(this);
        ((ViewGroup) findViewById(R.id.setting_card_container_backlight)).addView(new SettingCard(this, new SettingItem[]{new CheckableSettingItem(1, 0, R.string.backlight_list, 0, 0, Preferences.m2813y()), new CheckableSettingItem(2, 0, R.string.backlight_player, 0, 0, Preferences.m2811z()), new CheckableSettingItem(3, 0, R.string.backlight_lockscreen, 0, 0, Preferences.m3080A()), new CheckableSettingItem(4, 0, R.string.backlight_landscape, 0, 0, Preferences.m3078B())}, R.string.setting_backlight, this.mOnItemClickListener).m6992e());
    }
}
