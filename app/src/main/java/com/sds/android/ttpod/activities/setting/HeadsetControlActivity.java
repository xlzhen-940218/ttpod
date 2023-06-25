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
import com.sds.android.ttpod.component.p085b.Card;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;

/* loaded from: classes.dex */
public class HeadsetControlActivity extends SlidingClosableActivity {
    private static final int ID_EXCHANGE_DOUBLE_CLICK_LONG_CLICK = 4;
    private static final int ID_EXTRACT_TO_STOP = 1;
    private static final int ID_HEADSET_CONTROL_ENABLE = 3;
    private static final int ID_INSERT_TO_PLAY = 2;
    private ActionItem.InterfaceC1135b mOnItemClickListener = new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.activities.setting.HeadsetControlActivity.1
        @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
        /* renamed from: a */
        public void mo5409a(ActionItem actionItem, int i) {
            boolean z = false;
            if (actionItem instanceof Checkable) {
                z = ((Checkable) actionItem).isChecked();
            }
            switch (actionItem.m7005e()) {
                case 1:
                    Preferences.m2867j(z);
                    //SUserUtils.m4955a(SAction.ACTION_SETTING_HEADSET_PAUSE, z);
                    return;
                case 2:
                    Preferences.m2863k(z);
                    //SUserUtils.m4955a(SAction.ACTION_SETTING_HEADSET_PALY, z);
                    return;
                case 3:
                    Preferences.m2859l(z);
                    //SUserUtils.m4955a(SAction.ACTION_SETTING_ALLOW_LINE_CONTROL, z);
                    return;
                case 4:
                    Preferences.m2855m(z);
                    //SUserUtils.m4955a(SAction.ACTION_SETTING_ALLOW_UP_DOWN, z);
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
        setPage(SPage.PAGE_HEADSET_CONTROL);
        setContentView(R.layout.activity_setting_main);
        SettingUtils.m7778a(this);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.setting_card_container);
        linearLayout.addView(buildPlugHeadsetCard().m6992e());
        linearLayout.addView(buildHeadSetControlCard().m6992e());
        linearLayout.addView(buildHeadSetControlDescription());
    }

    private Card buildPlugHeadsetCard() {
        return new SettingCard(this, new SettingItem[]{new CheckableSettingItem(1, 0, R.string.headset_extract_to_stop, 0, 0, Preferences.m2834s()), new CheckableSettingItem(2, 0, R.string.headset_insert_to_play, 0, 0, Preferences.m2830t())}, R.string.headset_extract_insert, this.mOnItemClickListener);
    }

    private Card buildHeadSetControlCard() {
        return new SettingCard(this, new SettingItem[]{new CheckableSettingItem(3, 0, R.string.headset_control_enable, 0, 0, Preferences.m2826u()), new CheckableSettingItem(4, 0, R.string.headset_exchange_longclick_doubleclick, 0, 0, Preferences.m2822v())}, R.string.headset_control, this.mOnItemClickListener);
    }

    private View buildHeadSetControlDescription() {
        View inflate = getLayoutInflater().inflate(R.layout.setting_container_desc, (ViewGroup) null, false);
        TextView textView = (TextView) inflate.findViewById(R.id.id_desc_sub_title);
        textView.setText(R.string.headset_exchange_song_desc);
        textView.setVisibility(View.VISIBLE);
        return inflate;
    }
}
