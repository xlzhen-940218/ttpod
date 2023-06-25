package com.sds.android.ttpod.activities.setting;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.media.mediastore.AudioQuality;

/* loaded from: classes.dex */
public class AuditionAndDownloadQualityActivity extends SlidingClosableActivity {
    private static final int ID_AUDITION_COMPRESS_QUALITY = 1;
    private static final int ID_AUDITION_HIGH_QUALITY = 3;
    private static final int ID_AUDITION_NORMAL_QUALITY = 2;
    private static final int ID_DOWNLOAD_ASK_ME_EVERY_TIME = 4;
    private static final int ID_DOWNLOAD_COMPRESS_QUALITY = 5;
    private static final int ID_DOWNLOAD_HIGH_QUALITY = 7;
    private static final int ID_DOWNLOAD_LOSSLESS_QUALITY = 8;
    private static final int ID_DOWNLOAD_NORMAL_QUALITY = 6;
    private SettingCard mAuditionSettingCard;
    private SettingCard mDownloadSettingCard;
    private ActionItem.InterfaceC1135b mOnItemClickListener = new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.activities.setting.AuditionAndDownloadQualityActivity.1
        @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
        /* renamed from: a */
        public void mo5409a(ActionItem actionItem, int i) {
            switch (actionItem.m7005e()) {
                case 1:
                case 2:
                case 3:
                    AuditionAndDownloadQualityActivity.this.updateAuditionSettingItemActionIcon(actionItem);
                    return;
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    AuditionAndDownloadQualityActivity.this.updateDownloadSettingItemActionIcon(actionItem);
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
        setPage(SPage.PAGE_AUDITION_AND_DOWNLOAD_QUALITY);
        setContentView(R.layout.activity_setting_audition_download_quality);
        SettingUtils.m7778a(this);
        this.mAuditionSettingCard = buildAuditionSettingCard(R.string.setting_quality_audition_title);
        this.mDownloadSettingCard = buildDownloadSettingCard(R.string.setting_quality_download_title);
        addViewsToGroup();
    }

    private void addViewsToGroup() {
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.setting_card_container_audition_down_quality);
        viewGroup.addView(this.mAuditionSettingCard.m6992e());
        viewGroup.addView(buildDescriptionView(R.string.setting_quality_audition_desc));
        viewGroup.addView(this.mDownloadSettingCard.m6992e());
        viewGroup.addView(buildDescriptionView(R.string.setting_quality_dowload_desc));
    }

    private SettingCard buildAuditionSettingCard(int i) {
        return new SettingCard(this, new SettingItem[]{new SettingItem(1, 0, (int) R.string.setting_quality_compress, 0, getAuditionActionIcon(1)), new SettingItem(2, 0, (int) R.string.setting_quality_normal, 0, getAuditionActionIcon(2)), new SettingItem(3, 0, (int) R.string.setting_quality_high, 0, getAuditionActionIcon(3))}, i, this.mOnItemClickListener);
    }

    private SettingCard buildDownloadSettingCard(int i) {
        return new SettingCard(this, new SettingItem[]{new SettingItem(4, 0, (int) R.string.setting_quality_ask_me_every_time, 0, getDownloadActionIcon(4)), new SettingItem(5, 0, (int) R.string.setting_quality_compress, 0, getDownloadActionIcon(5)), new SettingItem(6, 0, (int) R.string.setting_quality_normal, 0, getDownloadActionIcon(6)), new SettingItem(7, 0, (int) R.string.setting_quality_high, 0, getDownloadActionIcon(7)), new SettingItem(8, 0, (int) R.string.lossless_title, 0, getDownloadActionIcon(8))}, i, this.mOnItemClickListener);
    }

    private View buildDescriptionView(int i) {
        View inflate = getLayoutInflater().inflate(R.layout.setting_container_desc, (ViewGroup) null, false);
        TextView textView = (TextView) inflate.findViewById(R.id.id_desc_sub_title);
        textView.setText(i);
        textView.setVisibility(View.VISIBLE);
        return inflate;
    }

    private AudioQuality getQualityById(int i) {
        if (i == 8) {
            return AudioQuality.LOSSLESS;
        }
        if (i == 5 || i == 1) {
            return AudioQuality.COMPRESSED;
        }
        if (i == 6 || i == 2) {
            return AudioQuality.STANDARD;
        }
        if (i == 7 || i == 3) {
            return AudioQuality.SUPER;
        }
        return AudioQuality.UNDEFINED;
    }

    private int getAuditionActionIcon(int i) {
        if (getQualityById(i).ordinal() == Preferences.m3060K().ordinal()) {
            return R.drawable.img_setting_single_choice_checked;
        }
        return 0;
    }

    private int getDownloadActionIcon(int i) {
        if (getQualityById(i).ordinal() == Preferences.m3058L().ordinal()) {
            return R.drawable.img_setting_single_choice_checked;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAuditionSettingItemActionIcon(ActionItem actionItem) {
        cleanSettingItemActionIcon(this.mAuditionSettingCard);
        ((SettingItem) actionItem).m7782a(getResources().getDrawable(R.drawable.img_setting_single_choice_checked));
        this.mAuditionSettingCard.m7796c();
        Preferences.m2901c(getQualityById(actionItem.m7005e()));

    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDownloadSettingItemActionIcon(ActionItem actionItem) {
        cleanSettingItemActionIcon(this.mDownloadSettingCard);
        ((SettingItem) actionItem).m7782a(getResources().getDrawable(R.drawable.img_setting_single_choice_checked));
        this.mDownloadSettingCard.m7796c();
        Preferences.m2895d(getQualityById(actionItem.m7005e()));

    }

    private void cleanSettingItemActionIcon(SettingCard settingCard) {
        for (SettingItem settingItem : settingCard.m7802a()) {
            settingItem.m7782a((Drawable) new ColorDrawable(0));
        }
    }
}
