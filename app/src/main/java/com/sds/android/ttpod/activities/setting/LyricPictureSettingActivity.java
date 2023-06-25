package com.sds.android.ttpod.activities.setting;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Checkable;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.p088a.SeekBarDialog;
import com.sds.android.ttpod.framework.modules.core.p113b.AutoDownloadNetworkType;
import com.sds.android.ttpod.framework.p106a.PlatformUtils;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes.dex */
public class LyricPictureSettingActivity extends SlidingClosableActivity {
    private static final int ID_AUTO_DOWNLOAD_LYRIC_NETWORK = 1;
    private static final int ID_DOWNLOAD_PIC_AMOUNT_2G = 3;
    private static final int ID_DOWNLOAD_PIC_AMOUNT_WIFI = 2;
    private static final int ID_PLAY_ARTIST_PICTURE = 4;
    private static final int ID_SHOW_INNER_PICTURE = 5;
    private static final int MAXPROGRESS = 8;
    private static final int MINPROGRESS = 0;
    private static final int STEP = 1;
    private SettingCard mLyricSettingCard;
    private SettingCard mPicSettingCard;
    private final HashMap<AutoDownloadNetworkType, Integer> mAutoDownloadNetworkTypeResIdMap = new HashMap<>(AutoDownloadNetworkType.values().length);
    private ActionItem.InterfaceC1135b mOnItemClickListener = new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.activities.setting.LyricPictureSettingActivity.2

        /* renamed from: a */
          /* synthetic */ boolean f2986a= !LyricPictureSettingActivity.class.desiredAssertionStatus();



        @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
        /* renamed from: a */
        public void mo5409a(ActionItem actionItem, int i) {
            switch (actionItem.m7005e()) {
                case 1:
                    LyricPictureSettingActivity.this.setLyricDownloadNetwork(((Checkable) actionItem).isChecked());
                    //SUserUtils.m4955a(SAction.ACTION_SETTING_AUTO_DOWNLOAD_LYRIC, ((Checkable) actionItem).isChecked());
                    return;
                case 2:
                    LyricPictureSettingActivity.this.setDownloadPicAmount(actionItem, i, Preferences.m3005aB(), true);
                    //SUserUtils.m4956a(SAction.ACTION_SETTING_WIFI_NUMBER, SPage.PAGE_NONE);
                    return;
                case 3:
                    LyricPictureSettingActivity.this.setDownloadPicAmount(actionItem, i, Preferences.m3004aC(), false);
                    //SUserUtils.m4956a(SAction.ACTION_SETTING_2G_3G_NUMBER, SPage.PAGE_NONE);
                    return;
                case 4:
                    if (!f2986a && !(actionItem instanceof Checkable)) {
                        throw new AssertionError();
                    }
                    Preferences.m3057L(((Checkable) actionItem).isChecked());
                    //SUserUtils.m4955a(SAction.ACTION_SETTING_PIC_AUTO, ((Checkable) actionItem).isChecked());
                    return;
                case 5:
                    if (!f2986a && !(actionItem instanceof Checkable)) {
                        throw new AssertionError();
                    }
                    Preferences.m2823u(((Checkable) actionItem).isChecked());
                    //SUserUtils.m4955a(SAction.ACTION_SETTING_SHOW_MUSIC_PIC, ((Checkable) actionItem).isChecked());
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
        setPage(SPage.PAGE_LYRIC_AND_PICTURE);
        setContentView(R.layout.activity_setting_lyric_pic);
        SettingUtils.m7778a(this);
        this.mAutoDownloadNetworkTypeResIdMap.put(AutoDownloadNetworkType.ALL, Integer.valueOf((int) R.string.all_network));
        this.mAutoDownloadNetworkTypeResIdMap.put(AutoDownloadNetworkType.WIFI, Integer.valueOf((int) R.string.wifi_only));
        this.mAutoDownloadNetworkTypeResIdMap.put(AutoDownloadNetworkType.DISABLE, Integer.valueOf((int) R.string.no_network));
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.setting_card_container_lyric_pic);
        this.mLyricSettingCard = buildLyricSettingCard();
        viewGroup.addView(this.mLyricSettingCard.m6992e());
        this.mPicSettingCard = buildPicSettingCard();
        viewGroup.addView(this.mPicSettingCard.m6992e());
    }

    private SettingCard buildLyricSettingCard() {
        return new SettingCard(this, new SettingItem[]{new CheckableSettingItem(1, 0, R.string.setting_auto_download_lyric, 0, 0, isLyricDownloadChecked())}, R.string.setting_lyric, this.mOnItemClickListener);
    }

    private SettingCard buildPicSettingCard() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingItem(3, 0, (int) R.string.image_down_amount_2g, (CharSequence) Integer.toString(Preferences.m3004aC()), (int) R.string.icon_arrow_right, true));
        arrayList.add(new SettingItem(2, 0, (int) R.string.image_down_amount_wifi, (CharSequence) Integer.toString(Preferences.m3005aB()), (int) R.string.icon_arrow_right, true));
        if (PlatformUtils.m4656a()) {
            arrayList.add(new CheckableSettingItem(4, 0, R.string.image_artist_play, 0, 0, Preferences.m3003aD()));
        }
        arrayList.add(new CheckableSettingItem(5, 0, R.string.show_inner_picture, 0, 0, Preferences.m3072E()));
        return new SettingCard(this, (SettingItem[]) arrayList.toArray(new SettingItem[arrayList.size()]), R.string.setting_pic, this.mOnItemClickListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDownloadPicAmount(final ActionItem actionItem, final int i, int i2, final boolean z) {
        SeekBarDialog seekBarDialog = new SeekBarDialog(this, 0, i2, 8, 1, "", new BaseDialog.InterfaceC1064a<SeekBarDialog>() { // from class: com.sds.android.ttpod.activities.setting.LyricPictureSettingActivity.1
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(SeekBarDialog seekBarDialog2) {
                int m6789b = seekBarDialog2.m6789b();
                actionItem.m7010a((CharSequence) (m6789b + ""));
                LyricPictureSettingActivity.this.mPicSettingCard.m7799a((SettingItem) actionItem, i);
                if (z) {
                    Preferences.m2853n(m6789b);
                } else {
                    Preferences.m2849o(m6789b);
                }
            }
        });
        seekBarDialog.setTitle(actionItem.m7006d());
        seekBarDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLyricDownloadNetwork(boolean z) {
        Preferences.m2939b(z ? AutoDownloadNetworkType.ALL : AutoDownloadNetworkType.DISABLE);
    }

    private boolean isLyricDownloadChecked() {
        return Preferences.m3052O() != AutoDownloadNetworkType.DISABLE;
    }
}
