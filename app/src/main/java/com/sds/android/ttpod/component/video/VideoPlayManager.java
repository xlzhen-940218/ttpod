package com.sds.android.ttpod.component.video;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.p055a.GuideAPI;
import com.sds.android.cloudapi.ttpod.result.GuideResult;


import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.MessageDialog;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class VideoPlayManager {

    /* renamed from: d */
    private static JSONObject f4862d;

    /* renamed from: e */
    private static WeakReference<Context> f4863e;

    /* renamed from: f */
    private static MediaPlayer f4864f;

    /* renamed from: g */
    private static InterfaceC1344a f4865g;

    /* renamed from: a */
    private static boolean f4859a = false;

    /* renamed from: b */
    private static int f4860b = 0;

    /* renamed from: c */
    private static VideoPlayerInterface f4861c = null;

    /* renamed from: h */
    private static boolean f4866h = true;

    /* renamed from: i */
    private static BroadcastReceiver f4867i = new BroadcastReceiver() { // from class: com.sds.android.ttpod.component.video.VideoPlayManager.6
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
                String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
                VideoPlayManager.m5794i();
                if (!StringUtils.isEmpty(schemeSpecificPart)) {
                    if ("com.storm.smart".equals(schemeSpecificPart)) {
                        //MVStatistic.m5071c(true);
                    }
                    if (StringUtils.m8344a(Cache.getInstance().m3146r(), "waiting_intall")) {
                        Cache.getInstance().m3181c(schemeSpecificPart);
                    }
                }
            }
        }
    };

    /* renamed from: j */
    private static MediaPlayer.OnErrorListener f4868j = new MediaPlayer.OnErrorListener() { // from class: com.sds.android.ttpod.component.video.VideoPlayManager.7
        @Override // android.media.MediaPlayer.OnErrorListener
        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            Preferences.m2821v(2);
            if (VideoPlayManager.f4865g != null) {
                VideoPlayManager.f4865g.mo5789a(false);
            }
            if (VideoPlayManager.f4864f != null) {
                VideoPlayManager.f4864f.stop();
                VideoPlayManager.f4864f.release();
                return true;
            }
            return true;
        }
    };

    /* renamed from: k */
    private static MediaPlayer.OnCompletionListener f4869k = new MediaPlayer.OnCompletionListener() { // from class: com.sds.android.ttpod.component.video.VideoPlayManager.8
        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            Preferences.m2821v(1);
            if (VideoPlayManager.f4865g != null) {
                VideoPlayManager.f4865g.mo5789a(true);
            }
            if (VideoPlayManager.f4864f != null) {
                VideoPlayManager.f4864f.stop();
                VideoPlayManager.f4864f.release();
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.component.video.VideoPlayManager$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1344a {
        /* renamed from: a */
        void mo5789a(boolean z);
    }

    /* renamed from: a */
    public static MessageDialog m5818a(final Context context, int i, final String str, final String str2, final boolean z, final DialogInterface.OnClickListener onClickListener) {
        //MVStatistic.m5077a();
        MessageDialog messageDialog = new MessageDialog(context, i, (BaseDialog.InterfaceC1064a<MessageDialog>) null, (BaseDialog.InterfaceC1064a<MessageDialog>) null);
        messageDialog.m7261a(R.string.download_experience, new BaseDialog.InterfaceC1064a<MessageDialog>() { // from class: com.sds.android.ttpod.component.video.VideoPlayManager.1
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(MessageDialog messageDialog2) {
                messageDialog2.dismiss();
                if (onClickListener != null) {
                    onClickListener.onClick(messageDialog2, -1);
                }
                if (VideoPlayManager.f4861c != null) {
                    Cache.getInstance().m3181c("waiting_intall");
                    WeakReference unused = VideoPlayManager.f4863e = new WeakReference(context);
                    VideoPlayManager.m5806b(context);
                    VideoPlayManager.f4861c.mo5783a();
                    if (VideoPlayManager.f4861c instanceof StormPlayer) {

                        //MVStatistic.m5075a(true);
                        return;
                    }
                }
            }
        }, R.string.cancel, new BaseDialog.InterfaceC1064a<MessageDialog>() { // from class: com.sds.android.ttpod.component.video.VideoPlayManager.2
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(MessageDialog messageDialog2) {
                messageDialog2.dismiss();
                if (onClickListener != null) {
                    onClickListener.onClick(messageDialog2, -2);
                }
                if (VideoPlayManager.f4861c instanceof StormPlayer) {
                    //UmengStatisticUtils.m4866a(context, "storm", "uninstall_count");
                    //StatisticUtils.m4917a(219, (int) 65537, 1L);
                }
                if (z) {
                    //MVStatistic.m5075a(false);
                    VideoPlayerInterface unused = VideoPlayManager.f4861c = new SystemVideoPlayer();
                    VideoPlayManager.f4861c.mo5782a(context, str, str2);
                    //StatisticUtils.m4917a(215, (int) 65537, 1L);
                }
            }
        });
        messageDialog.setTitle(R.string.prompt_title);
        messageDialog.show();
        return messageDialog;
    }

    /* renamed from: a */
    public static void m5816a(Context context, MediaItem mediaItem) {
        List<OnlineMediaItem.Url> mVUrls;
        if (context != null && mediaItem != null && !StringUtils.isEmpty(mediaItem.getExtra()) && (mVUrls = ((OnlineMediaItem) JSONUtils.fromJson(mediaItem.getExtra(), OnlineMediaItem.class)).getMVUrls()) != null && mVUrls.size() > 0) {
            m5814a(context, mVUrls.get(0).getUrl(), mediaItem.getTitle());
        }
    }

    /* renamed from: a */
    public static void m5814a(Context context, String str, String str2) {
        LogUtils.debug("VideoPlayManager", "tryToPlayVideo url=%s title=%s", str, str2);
        if (StringUtils.isEmpty(str)) {
            PopupsUtils.m6760a((int) R.string.mv_url_illegal);
        } else if (f4861c != null) {
            m5800d(context, str, str2);
        } else {
            m5813a(context, str, str2, EnvironmentUtils.C0604c.m8476d() == 2);
        }
    }

    /* renamed from: c */
    private static void m5802c(Context context, String str, String str2) {
        if (!StringUtils.isEmpty(str)) {
            if (StringUtils.isEmpty(str2)) {
                str2 = "";
            }
            f4861c = new SystemVideoPlayer();
            f4861c.mo5782a(context, str, str2);
            m5796g();
            //MVStatistic.m5074b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public static void m5800d(Context context, String str, String str2) {
        if (f4861c != null && ((f4861c instanceof SystemVideoPlayer) || m5815a(context, f4861c.mo5781c()))) {
            f4861c.mo5782a(context, str, str2);
            m5796g();
            //MVStatistic.m5074b();
        } else if (f4866h) {
            f4866h = false;
            m5798e(context, str, str2);
        } else if (StringUtils.m8344a(Cache.getInstance().m3146r(), "tv.pps.mobile") || StringUtils.m8344a(Cache.getInstance().m3146r(), "com.pplive.androidphone") || StringUtils.m8344a(Cache.getInstance().m3146r(), "com.storm.smart")) {
            m5802c(context, str, str2);
        } else {
            m5798e(context, str, str2);
        }
    }

    /* renamed from: e */
    private static void m5798e(final Context context, final String str, final String str2) {
        m5817a(context, new InterfaceC1344a() { // from class: com.sds.android.ttpod.component.video.VideoPlayManager.3
            @Override // com.sds.android.ttpod.component.video.VideoPlayManager.InterfaceC1344a
            /* renamed from: a */
            public void mo5789a(boolean z) {
                VideoPlayManager.m5818a(context, z ? R.string.mv_player_install_hint : R.string.system_codec_not_support, str, str2, z, null);
            }
        });
    }

    /* loaded from: classes.dex */
    public static class VideoBroadcastReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogUtils.info("VideoPlayManager", action);
            if (action.equals("com.storm.smart.action.TTPOD_VIDEO_FINISH") || action.equals("com.sds.android.ttpod.video_finished")) {
                VideoPlayManager.m5795h();
            }
        }
    }

    /* renamed from: a */
    public static void m5813a(final Context context, final String str, final String str2, final boolean z) {
        String m3146r = Cache.getInstance().m3146r();
        if ("tv.pps.mobile".equals(m3146r)) {
            f4861c = m5815a(context, m3146r) ? new PPSPlayer() : new SystemVideoPlayer();
        } else if ("com.pplive.androidphone".equals(m3146r)) {
            f4861c = m5815a(context, m3146r) ? new PPTVPlayer() : new SystemVideoPlayer();
        } else if ("com.storm.smart".equals(m3146r)) {
            f4861c = m5815a(context, m3146r) ? new StormPlayer() : new SystemVideoPlayer();
        } else {
            m5817a(context, new InterfaceC1344a() { // from class: com.sds.android.ttpod.component.video.VideoPlayManager.4
                @Override // com.sds.android.ttpod.component.video.VideoPlayManager.InterfaceC1344a
                /* renamed from: a */
                public void mo5789a(final boolean z2) {
                    if (z2 && !z) {
                        VideoPlayerInterface unused = VideoPlayManager.f4861c = new SystemVideoPlayer();
                        if (!StringUtils.isEmpty(str)) {
                            VideoPlayManager.m5800d(context, str, str2);
                            return;
                        }
                        return;
                    }
                    TaskScheduler.m8580a(new Runnable() { // from class: com.sds.android.ttpod.component.video.VideoPlayManager.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Object[] objArr = new Object[4];
                            objArr[0] = "f" + EnvironmentUtils.C0602a.m8512b();
                            objArr[1] = Integer.valueOf(EnvironmentUtils.C0604c.m8476d());
                            objArr[2] = EnvironmentUtils.C0602a.m8506e();
                            objArr[3] = Integer.valueOf(z2 ? 1 : 0);
                            String format = String.format("http://api.busdh.com/market-api/splash/app?f=%s&net=%s&v=%s&type=2&local_play=%d", objArr);
                            HttpRequest.C0586a m8708a = HttpRequest.m8708a(new HttpGet(format), (HashMap<String, Object>) null, (HashMap<String, Object>) null);
                            JSONObject m8393a = m8708a == null ? null : JSONUtils.create(m8708a.m8688e());
                            if (m8393a != null) {
                                JSONObject unused2 = VideoPlayManager.f4862d = m8393a;
                                return;
                            }
                            //ErrorStatistic.m5235d(format);
                            //ErrorStatistic.m5239a("ad_sdk", format);
                        }
                    }, new Runnable() { // from class: com.sds.android.ttpod.component.video.VideoPlayManager.4.2
                        @Override // java.lang.Runnable
                        public void run() {
                            VideoPlayerInterface unused2 = VideoPlayManager.f4861c = VideoPlayManager.m5804b(context, VideoPlayManager.f4862d, z2);
                            if (!StringUtils.isEmpty(str)) {
                                VideoPlayManager.m5800d(context, str, str2);
                            }
                        }
                    });
                }
            });
        }
    }

    /* renamed from: a */


    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static VideoPlayerInterface m5804b(Context context, JSONObject jSONObject, boolean z) {
        if (jSONObject == null) {
            return new SystemVideoPlayer();
        }
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("data");
            int i = z ? 1 : 0;
            if (jSONArray != null && jSONArray.length() > 0) {
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                    if (jSONObject2 != null) {
                        String string = jSONObject2.getString("package_name");
                        String string2 = jSONObject2.getString("down_apk_url");
                        f4860b = jSONObject2.getInt("app_id");
                        int i3 = jSONObject2.getInt("local_paly");
                        boolean m5815a = m5815a(context, string);
                        if (!z) {
                            //m5810a(m5815a ? //MVStatistic.EnumC1771a.ALL_INSTALL : //MVStatistic.EnumC1771a.REQUEST_SUCCESS);
                            return new StormPlayer(string2);
                        } else if (i3 != i && !StringUtils.isEmpty(string2) && !StringUtils.isEmpty(string) && !m5815a) {
                            if (StringUtils.m8344a(string, "tv.pps.mobile")) {
                                //m5810a(//MVStatistic.EnumC1771a.REQUEST_SUCCESS);
                                return new PPSPlayer(string2);
                            } else if (StringUtils.m8344a(string, "com.pplive.androidphone")) {
                                //m5810a(//MVStatistic.EnumC1771a.REQUEST_SUCCESS);
                                return new PPTVPlayer(string2);
                            } else if (StringUtils.m8344a(string, "com.storm.smart")) {
                               // m5810a(//MVStatistic.EnumC1771a.REQUEST_SUCCESS);
                                return new StormPlayer(string2);
                            }
                        }
                    }
                }
               // m5810a(//MVStatistic.EnumC1771a.ALL_INSTALL);
            } else {
               // m5810a(//MVStatistic.EnumC1771a.DATA_NULL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new SystemVideoPlayer();
    }

    /* renamed from: a */
    private static boolean m5817a(Context context, InterfaceC1344a interfaceC1344a) {
        int m2914bo = 0;
        try {
            m2914bo = Preferences.m2914bo();
        } catch (Exception e) {
            e.printStackTrace();
            interfaceC1344a.mo5789a(false);
        }
        if (m2914bo == 2) {
            interfaceC1344a.mo5789a(false);
            return false;
        } else if (m2914bo == 1) {
            interfaceC1344a.mo5789a(true);
            return true;
        } else {
            if (m2914bo == 0) {
                f4864f = new MediaPlayer();
                f4864f.setAudioStreamType(3);
                AssetFileDescriptor openFd = null;
                try {
                    openFd = context.getAssets().openFd("mv_support_check.mp4");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    f4864f.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                f4865g = interfaceC1344a;
                f4864f.setOnErrorListener(f4868j);
                f4864f.setOnCompletionListener(f4869k);
                try {
                    f4864f.prepare();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (f4864f.getVideoWidth() <= 0) {
                    interfaceC1344a.mo5789a(false);
                    Preferences.m2821v(2);
                    return false;
                }
                f4864f.start();
            }
            return false;
        }
    }

    /* renamed from: g */
    private static void m5796g() {
        f4859a = SupportFactory.m2397a(BaseApplication.getApplication()).m2463m() == PlayStatus.STATUS_PLAYING;
        if (f4859a) {
            CommandCenter.getInstance().m4606a(new Command(CommandID.PAUSE, new Object[0]));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public static void m5795h() {
        if (f4859a) {
            CommandCenter.getInstance().m4606a(new Command(CommandID.RESUME, new Object[0]));
        }
    }

    /* renamed from: a */
    private static boolean m5815a(Context context, String str) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        for (ApplicationInfo applicationInfo : context.getPackageManager().getInstalledApplications(8192)) {
            if (StringUtils.m8344a(applicationInfo.packageName, str)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m5806b(Context context) {
        if (context != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
            intentFilter.addDataScheme("package");
            context.registerReceiver(f4867i, intentFilter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i */
    public static void m5794i() {
        try {
            if (f4863e != null && f4863e.get() != null) {
                f4863e.get().unregisterReceiver(f4867i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
