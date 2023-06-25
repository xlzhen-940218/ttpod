package com.sds.android.ttpod.activities.setting;

import android.os.Bundle;
import android.view.ViewGroup;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.p088a.SeekBarDialog;
import com.sds.android.ttpod.framework.storage.environment.Preferences;

/* loaded from: classes.dex */
public class AudioFadeSettingActivity extends SlidingClosableActivity {
    private static final int ID_FADE_PLAY_PAUSE = 2;
    private static final int ID_FADE_SEEK = 3;
    private static final int ID_FADE_WHILE_CHANGING_MEDIA = 0;
    private static final int MAXPROGRESS = 5000;
    private static final int MINPROGRESS = 0;
    private static final float ONE_MILLIONSECOND = 1000.0f;
    private static final int STEP = 100;
    private SettingCard mFadeSettingCard;
    private ActionItem.InterfaceC1135b mOnItemClickListener = new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.activities.setting.AudioFadeSettingActivity.1
        @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
        /* renamed from: a */
        public void mo5409a(ActionItem actionItem, int i) {
            switch (actionItem.m7005e()) {
                case 0:
                    AudioFadeSettingActivity.this.setFadeLength(actionItem, i, 0);
                    return;
                case 1:
                default:
                    return;
                case 2:
                    AudioFadeSettingActivity.this.setFadeLength(actionItem, i, 2);
                    return;
                case 3:
                    AudioFadeSettingActivity.this.setFadeLength(actionItem, i, 3);
                    return;
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_setting_audio_fade);
        SettingUtils.m7778a(this);
        this.mFadeSettingCard = new SettingCard(this, new SettingItem[]{new SettingItem(0, 0, (int) R.string.setting_audio_fade_while_changing_media, (Preferences.m2985aV() / ONE_MILLIONSECOND) + getString(R.string.setting_audio_fade_length_unit_second), (int) R.drawable.img_setting_right_arrow), new SettingItem(2, 0, (int) R.string.setting_audio_fade_play_pause, (Preferences.m2984aW() / ONE_MILLIONSECOND) + getString(R.string.setting_audio_fade_length_unit_second), (int) R.drawable.img_setting_right_arrow), new SettingItem(3, 0, (int) R.string.setting_audio_fade_seek, (Preferences.m2982aY() / ONE_MILLIONSECOND) + getString(R.string.setting_audio_fade_length_unit_second), (int) R.drawable.img_setting_right_arrow)}, R.string.setting_audio_fade_in_out_time, this.mOnItemClickListener);
        ((ViewGroup) findViewById(R.id.setting_card_container_audio_fade)).addView(this.mFadeSettingCard.m6992e());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFadeLength(final ActionItem actionItem, final int i, final int i2) {
        int m2982aY;
        switch (i2) {
            case 0:
                m2982aY = Preferences.m2985aV();
                break;
            case 1:
            default:
                m2982aY = 0;
                break;
            case 2:
                m2982aY = Preferences.m2984aW();
                break;
            case 3:
                m2982aY = Preferences.m2982aY();
                break;
        }
        SeekBarDialog seekBarDialog = new SeekBarDialog(this, 0, m2982aY, MAXPROGRESS, 100, getString(R.string.setting_audio_fade_length_unit_second), new BaseDialog.InterfaceC1064a<SeekBarDialog>() { // from class: com.sds.android.ttpod.activities.setting.AudioFadeSettingActivity.2
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(SeekBarDialog seekBarDialog2) {
                int m6789b = seekBarDialog2.m6789b();
                actionItem.m7010a((CharSequence) ((m6789b / AudioFadeSettingActivity.ONE_MILLIONSECOND) + AudioFadeSettingActivity.this.getString(R.string.setting_audio_fade_length_unit_second)));
                AudioFadeSettingActivity.this.mFadeSettingCard.m7799a((SettingItem) actionItem, i);
                switch (i2) {
                    case 0:
                        Preferences.m2841q(m6789b);
                        //StatisticUtils.m4909a("local", "click", "fade-over-cut", m6789b);
                        return;
                    case 1:
                    default:
                        return;
                    case 2:
                        Preferences.m2837r(m6789b);
                        //StatisticUtils.m4909a("local", "click", "fade-over-play", m6789b);
                        return;
                    case 3:
                        Preferences.m2833s(m6789b);
                        //StatisticUtils.m4909a("local", "click", "fade-over-adjust", m6789b);
                        return;
                }
            }
        });
        seekBarDialog.setTitle(actionItem.m7006d());
        seekBarDialog.m6792a(1000, 1);
        seekBarDialog.show();
    }
}
