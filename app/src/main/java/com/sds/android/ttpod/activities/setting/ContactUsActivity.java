package com.sds.android.ttpod.activities.setting;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.URLUtil;
import android.widget.LinearLayout;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p085b.Card;


/* loaded from: classes.dex */
public class ContactUsActivity extends SlidingClosableActivity {
    private static final int ID_BUSINESS_MAIL = 6;
    private static final int ID_FORUM = 4;
    private static final int ID_MUSIC_CIRCLE = 1;
    private static final int ID_SERVICE_MAIL = 5;
    private static final int ID_WECHAT = 3;
    private static final int ID_WEIBO = 2;
    private ActionItem.InterfaceC1135b mOnItemClickListener = new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.activities.setting.ContactUsActivity.1
        @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
        /* renamed from: a */
        public void mo5409a(ActionItem actionItem, int i) {
            ContactUsActivity contactUsActivity = ContactUsActivity.this;
            switch (actionItem.m7005e()) {
                case 1:
                    ContactUsActivity.startupExternalBrowser(contactUsActivity, "http://quan.dongting.com");
                    return;
                case 2:
                    ContactUsActivity.startupExternalBrowser(contactUsActivity, "http://weibo.com/ittpod");
                    return;
                case 3:
                    try {
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setData(Uri.parse("http://weixin.qq.com/r/YHXPwBXE8iuNhwSjnyBE"));
                        intent.setFlags(268435456);
                        ContactUsActivity.this.startActivity(intent);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                case 4:
                    ContactUsActivity.startupExternalBrowser(contactUsActivity, "http://bbs.dongting.com");
                    return;
                case 5:
                    ContactUsActivity.this.serviceMail();
                    return;
                case 6:
                    ContactUsActivity.this.bussinessEmail();
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
        setContentView(R.layout.activity_setting_main);
        SettingUtils.m7778a(this);
        ((LinearLayout) findViewById(R.id.setting_card_container)).addView(buildContactCard().m6992e());
    }

    private Card buildContactCard() {
        return new SettingCard(this, new SettingItem[]{new SettingItem(1, 0, (int) R.string.apply_for_music_circle, (int) R.string.webset_music_circle, 0, true), new SettingItem(2, 0, (int) R.string.follow_weibo, (int) R.string.webset_weibo, 0, true), new SettingItem(3, 0, (int) R.string.follow_wechat, (int) R.string.webset_wechat, 0, true), new SettingItem(4, 0, (int) R.string.access_forum, (int) R.string.webset_forum, 0, true), new SettingItem(5, 0, (int) R.string.service_mail_title, (int) R.string.service_mail, 0, true), new SettingItem(6, 0, (int) R.string.business_mail_title, (int) R.string.business_mail, 0, true)}, R.string.contact_us, this.mOnItemClickListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void serviceMail() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{"support@ttpod.com"});
        intent.putExtra("android.intent.extra.SUBJECT", "Question about TTPod for Android");
        intent.putExtra("android.intent.extra.TEXT", getEmailMessage());
        intent.setType("plain/text");
        try {
            startActivity(Intent.createChooser(intent, getString(R.string.mail_box)));
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bussinessEmail() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{"market@ttpod.com"});
        intent.putExtra("android.intent.extra.SUBJECT", "market");
        intent.putExtra("android.intent.extra.TEXT", "");
        intent.setType("plain/text");
        try {
            startActivity(Intent.createChooser(intent, getString(R.string.mail_box)));
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void startupExternalBrowser(Context context, String str) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(amendNetworkUrl(str))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String amendNetworkUrl(String str) {
        if (!URLUtil.isNetworkUrl(str)) {
            return "http://" + str;
        }
        return str;
    }

    private String getEmailMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.version_info));
        sb.append("\n");
        sb.append("Version: ");
        sb.append(EnvironmentUtils.AppConfig.getAppVersion());
        sb.append("\n");
        sb.append("Market: ");
        sb.append(EnvironmentUtils.AppConfig.getChannelType());
        sb.append("\n");
        sb.append("\n");
        sb.append(getString(R.string.device_info));
        sb.append("\n");
        sb.append("Device: ");
        sb.append(Build.MANUFACTURER + " " + Build.MODEL + "(" + Build.DEVICE + ")");
        sb.append("\n");
        sb.append("Product: ");
        sb.append(Build.PRODUCT);
        sb.append("\n");
        sb.append("Android: ");
        sb.append(Build.VERSION.RELEASE);
        sb.append("(");
        sb.append(Build.VERSION.SDK_INT);
        sb.append(")");
        sb.append("\n");
        sb.append("FingerPrint: ");
        sb.append(Build.FINGERPRINT);
        sb.append("\n");
        sb.append("\n");
        sb.append(getString(R.string.support_description));
        sb.append("\n");
        return sb.toString();
    }
}
