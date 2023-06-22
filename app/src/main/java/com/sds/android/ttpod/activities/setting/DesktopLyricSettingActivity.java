package com.sds.android.ttpod.activities.setting;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.p106a.p107a.SUserUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;

/* loaded from: classes.dex */
public class DesktopLyricSettingActivity extends SlidingClosableActivity {
    private static final int ID_DESKTOP_LYRIC = 0;
    private static final int ID_LYRIC_LOCKED = 1;
    private ActionItem.InterfaceC1135b mOnItemClickListener = new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.activities.setting.DesktopLyricSettingActivity.1
        @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
        /* renamed from: a */
        public void mo5409a(ActionItem actionItem, int i) {
            boolean z = false;
            if (actionItem instanceof Checkable) {
                z = ((Checkable) actionItem).isChecked();
            }
            switch (actionItem.m7005e()) {
                case 0:
                    Preferences.m2879g(z);
                    SUserUtils.m4955a(SAction.ACTION_SETTING_SHOW_DESTOP_LYRIC, z);
                    return;
                case 1:
                    Preferences.m2810z(z);
                    SUserUtils.m4955a(SAction.ACTION_SETTING_DESTOP_LYRIC_LOCK_SELECT, z);
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
        setPage(SPage.PAGE_DESKTOP_LYRIC);
        setContentView(R.layout.activity_setting_desktop_lyric);
        SettingUtils.m7778a(this);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.setting_card_container);
        linearLayout.addView(buildDesktopLyricSettingCard().m6992e());
        linearLayout.addView(buildDescriptionView(R.string.ask_why_no_desktop_lyric, R.string.answer_why_no_desktop_lyric));
        linearLayout.addView(buildDescriptionView(R.string.ask_what_is_lock_desktop_lyric, R.string.answer_what_is_lock_desktop_lyric));
    }

    private SettingCard buildDesktopLyricSettingCard() {
        SettingCard settingCard = new SettingCard(this, new SettingItem[]{new CheckableSettingItem(0, 0, R.string.show_desktop_lyric, 0, 0, Preferences.m2838r()), new CheckableSettingItem(1, 0, R.string.minilyric_lock_desktop_lyric, 0, 0, Preferences.m2980aa())}, R.string.setting_desktop_lyric, this.mOnItemClickListener);
        settingCard.m6995a(false);
        return settingCard;
    }

    private View buildDescriptionView(int i, int i2) {
        View inflate = getLayoutInflater().inflate(R.layout.setting_container_desc, (ViewGroup) null, false);
        setViewText(inflate, R.id.id_desc_title, i);
        setViewText(inflate, R.id.id_desc_sub_title, i2);
        return inflate;
    }

    private void setViewText(View view, int i, int i2) {
        TextView textView = (TextView) view.findViewById(i);
        textView.setText(i2);
        textView.setVisibility(View.VISIBLE);
    }
}
