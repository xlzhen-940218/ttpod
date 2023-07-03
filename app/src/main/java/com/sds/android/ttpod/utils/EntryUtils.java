package com.sds.android.ttpod.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.TTPodApplication;
import com.sds.android.ttpod.activities.AudioEffectFragmentActivity;
import com.sds.android.ttpod.activities.BackgroundManagementActivity;
import com.sds.android.ttpod.activities.RecommendWebFragment;
import com.sds.android.ttpod.activities.SoundSearchActivity;
import com.sds.android.ttpod.activities.ThemeManagementActivity;
import com.sds.android.ttpod.activities.ktv.KtvActivity;
import com.sds.android.ttpod.activities.mediascan.ApShareEntryActivity;
import com.sds.android.ttpod.activities.mediascan.MediaScanActivity;
import com.sds.android.ttpod.activities.mediascan.MediaScanWifiActivity;
import com.sds.android.ttpod.activities.setting.SettingEntryActivity;

import com.sds.android.ttpod.activities.user.LoginActivity;
import com.sds.android.ttpod.activities.user.UserInfoActivity;
import com.sds.android.ttpod.activities.web.WebActivity;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.WebFragment;
import com.sds.android.ttpod.framework.base.ActivityManager;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.p126h.FlowTrialStatus;
import com.sds.android.ttpod.framework.modules.p126h.UnicomFlowStatus;
import com.sds.android.ttpod.framework.modules.p126h.UnicomFlowUtil;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.media.audiofx.EffectDetect;


/* renamed from: com.sds.android.ttpod.a.f */
/* loaded from: classes.dex */
public final class EntryUtils {
    /* renamed from: a */
    public static void m8298a(BaseActivity baseActivity) {
        baseActivity.launchFragment(new RecommendWebFragment());
        //LocalStatistic.m5110aw();
    }

    /* renamed from: a */
    public static void m8300a(Context context) {
        //new SUserEvent("PAGE_CLICK", SAction.ACTION_GLOBAL_MENU_FM.getValue(), SPage.PAGE_GLOBAL_MENU.getValue(), SPage.PAGE_TTPOD_FM.getValue()).append("url", "http://fm.ttpod.com/mindex.html").post();
        Intent intent = new Intent(context, WebActivity.class);
        intent.setData(Uri.parse("http://fm.ttpod.com/mindex.html"));
        intent.putExtra(WebFragment.EXTRA_TITLE, context.getString(R.string.ttpod_fm));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        //LocalStatistic.m5168O();
    }

    /* renamed from: b */
    public static void m8294b(Context context) {
        context.startActivity(new Intent(context, KtvActivity.class));
    }

    /* renamed from: c */
    public static void m8292c(Context context) {
        context.startActivity(new Intent(context, SettingEntryActivity.class));
        //LocalStatistic.m5169N();
    }

    /* renamed from: d */
    public static void m8291d(Context context) {
        //SearchStatistic.m4925n();
        context.startActivity(new Intent(context, SoundSearchActivity.class));
    }

    /* renamed from: e */
    public static void m8290e(Context context) {
        context.startActivity(new Intent(context, ThemeManagementActivity.class));
        //ThemeStatistic.m4886g("setting");
        //LocalStatistic.m5172K();
        //ThemeStatistic.m4873s();
    }

    /* renamed from: a */
    public static void m8302a(Activity activity) {
        Preferences.m3037V(false);
        if (EffectDetect.usingAudioPlus()) {
            try {
                activity.startActivityForResult(new Intent("android.media.action.DISPLAY_AUDIO_EFFECT_CONTROL_PANEL").putExtra("android.media.extra.AUDIO_SESSION", Preferences.m2981aZ()).addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION), 1);
            } catch (Exception e) {
                activity.startActivity(new Intent(activity, AudioEffectFragmentActivity.class));
                e.printStackTrace();
            }
        } else {
            activity.startActivity(new Intent(activity, AudioEffectFragmentActivity.class));
        }
        //LocalStatistic.m5173J();
    }

    /* renamed from: a */
    public static void m8303a() {
        CommandCenter.getInstance().execute(new Command(CommandID.SWITCH_PLAY_MODE, new Object[0]));
        //LocalStatistic.m5174I();
    }

    /* renamed from: a */
    public static void m8301a(Activity activity, int i) {
        activity.startActivityForResult(new Intent(activity, MediaScanWifiActivity.class), i);
    }

    /* renamed from: a */
    public static void m8299a(Context context, String str) {
        Intent intent = new Intent(context, ApShareEntryActivity.class);
        if (!StringUtils.isEmpty(str)) {
            intent.putExtra("key_media_id", str);
        }
        context.startActivity(intent);
        //LocalStatistic.m5104c();
    }

    /* renamed from: f */
    public static void m8289f(Context context) {
        context.startActivity(new Intent(context, MediaScanActivity.class));
    }

    /* renamed from: g */
    public static void m8288g(Context context) {
        context.startActivity(new Intent(context, UserInfoActivity.class));
    }

    /* renamed from: b */
    public static void m8295b(Activity activity) {
        Class cls = null;
        Preferences.m2977ab(false);
        int m3138z = Cache.getInstance().m3138z();
        int m3139y = Cache.getInstance().m3139y();
        if (UnicomFlowStatus.UNSUBSCRIBE.ordinal() == m3138z) {
            //cls = UnsubscribeDetailActivity.class;
        } else if (UnicomFlowStatus.OPEN.ordinal() == m3138z) {
            //cls = OpenDetailActivity.class;
        } else if (FlowTrialStatus.TRIAL.ordinal() == m3139y) {
            //cls = TrialDetailActivity.class;
        } else {
            //cls = OrderFlowDetailActivity.class;
        }
        activity.startActivity(new Intent(activity, cls));
    }

    /* renamed from: c */
    public static void m8293c(Activity activity) {
        if (UnicomFlowUtil.m3946f()) {
            m8295b(activity);
            //new SUserEvent("PAGE_CLICK", 1137, 700).post();
            return;
        }
        //new SUserEvent("PAGE_CLICK", 1136, 0).post();
        PopupsUtils.m6760a((int) R.string.not_unicom_user_message);
    }

    /* renamed from: h */
    public static void m8287h(Context context) {
        if (((Boolean) CommandCenter.getInstance().m4602a(new Command(CommandID.IS_SLEEP_MODE_ENABLED, new Object[0]), Boolean.class)).booleanValue()) {
            CommandCenter.getInstance().execute(new Command(CommandID.STOP_SLEEP_MODE, new Object[0]));
            //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_GLOBAL_MENU_CANCEL_SLEEP, SPage.PAGE_GLOBAL_MENU, SPage.PAGE_NONE);
            PopupsUtils.m6721a(context.getString(R.string.cancel_sleep_mode));
            return;
        }
        PopupsUtils.m6714b(context);
    }

    /* renamed from: b */
    public static void m8296b() {
        //LocalStatistic.m5167P();
        ActivityManager.getInstance().stopAllActivity();
        CommandCenter.getInstance().execute(new Command(CommandID.EXIT, new Object[0]));
    }

    /* renamed from: i */
    public static void m8286i(Context context) {
        context.startActivity(new Intent(context, BackgroundManagementActivity.class));
        //ThemeStatistic.m4885h();
    }

    /* renamed from: j */
    public static void m8285j(Context context) {
        context.startActivity(new Intent(context, BackgroundManagementActivity.class));
        //ThemeStatistic.m4885h();
    }

    /* renamed from: a */
    public static void m8297a(boolean z) {
        if (z) {
            PopupsUtils.m6760a((int) R.string.retry_after_login_tip);
        }
        BaseApplication c = TTPodApplication.getApplication();
        c.startActivity(new Intent(c, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
