package com.sds.android.ttpod.framework.support.monitor;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import com.sds.android.sdk.core.statistic.SSystemEvent;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.SupportService;

/* loaded from: classes.dex */
public class MediaButtonReceiver extends BroadcastReceiver {

    /* renamed from: a */
    private static long f7240a = 0;

    /* renamed from: b */
    private static int f7241b = 0;

    /* renamed from: c */
    private static Handler f7242c = new Handler() { // from class: com.sds.android.ttpod.framework.support.monitor.MediaButtonReceiver.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    MediaButtonReceiver.f7242c.removeMessages(2);
                    MediaButtonReceiver.m2245i();
                    return;
                case 2:
                    int i = message.arg1;
                    if (i == 1 && !MediaButtonReceiver.f7242c.hasMessages(1)) {
                        MediaButtonReceiver.m2247g();
                        return;
                    } else if (i == 2) {
                        MediaButtonReceiver.m2246h();
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    };

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        KeyEvent keyEvent;
        String str;
        if (intent != null) {
            LogUtils.m8388a("MediaButtonReceiver", "onReceive: " + intent.toString());
        }
        if (Preferences.m2826u() && "android.intent.action.MEDIA_BUTTON".equals(intent.getAction()) && (keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT")) != null) {
            int keyCode = keyEvent.getKeyCode();
            int action = keyEvent.getAction();
            long eventTime = keyEvent.getEventTime();
            LogUtils.m8388a("Receiver", "keyCode: " + keyCode + " action: " + action + " eventTime: " + eventTime);
            switch (keyCode) {
                case 79:
                    int callState = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getCallState();
                    if (callState != 1 && callState != 2) {
                        str = "play_pause_command";
                        break;
                    }
                    str = null;
                    break;
                case 85:
                    str = "play_pause_command";
                    break;
                case 86:
                    str = "stop_command";
                    break;
                case 87:
                    str = "next_command";
                    break;
                case 88:
                    str = "previous_command";
                    break;
                case 126 /* 126 */:
                    str = "play_pause_command";
                    break;
                case 127 /* 127 */:
                    str = "play_pause_command";
                    break;
                default:
                    str = null;
                    break;
            }
            m2253a(keyCode);
            if (str != null) {
                if (action == 0) {
                    if (keyEvent.getRepeatCount() == 0) {
                        if (keyCode == 79) {
                            if (eventTime - f7240a < 500) {
                                if (f7241b == 1) {
                                    f7241b = 2;
                                    f7242c.removeMessages(2);
                                    f7242c.sendMessageDelayed(f7242c.obtainMessage(2, 2, 0), 500L);
                                    f7240a = eventTime;
                                } else {
                                    f7242c.removeMessages(2);
                                    f7240a = 0L;
                                    f7241b = 0;
                                    m2244j();
                                }
                            } else {
                                f7240a = eventTime;
                                f7241b = 1;
                                f7242c.sendMessageDelayed(f7242c.obtainMessage(2, 1, 0), 500L);
                                f7242c.sendMessageDelayed(f7242c.obtainMessage(1), 500L);
                            }
                        } else {
                            BaseApplication.getApplication().startService(new Intent(BaseApplication.getApplication(), SupportService.class).putExtra("command", str));
                        }
                    }
                } else {
                    f7242c.removeMessages(1);
                }
                if (isOrderedBroadcast()) {
                    abortBroadcast();
                }
            }
        }
    }

    /* renamed from: a */
    private void m2253a(int i) {
        if (i == 86 || i == 79 || i == 87 || i == 88 || i == 126 || i == 127 || i == 85) {
            new SSystemEvent("SYS_HEADSET", String.valueOf(i)).post();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public static void m2247g() {
        LogUtils.m8381c("MediaButtonReceiver", "performSingleClick");
        BaseApplication.getApplication().startService(new Intent(BaseApplication.getApplication(), SupportService.class).putExtra("command", "play_pause_command"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public static void m2246h() {
        LogUtils.m8381c("MediaButtonReceiver", "performDoubleClick");
        BaseApplication.getApplication().startService(new Intent(BaseApplication.getApplication(), SupportService.class).putExtra("command", Preferences.m2822v() ? "previous_command" : "next_command"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i */
    public static void m2245i() {
        LogUtils.m8381c("MediaButtonReceiver", "performLongPress");
        BaseApplication.getApplication().startService(new Intent(BaseApplication.getApplication(), SupportService.class).putExtra("command", Preferences.m2822v() ? "next_command" : "previous_command"));
    }

    /* renamed from: j */
    private static void m2244j() {
        LogUtils.m8381c("MediaButtonReceiver", "performThreeClick");
        BaseApplication.getApplication().startService(new Intent(BaseApplication.getApplication(), SupportService.class).putExtra("command", Preferences.m2822v() ? "next_command" : "previous_command"));
    }

    /* renamed from: a */
    public static void m2254a() {
        LogUtils.m8388a("MediaButtonReceiver", "reloadMediaButtonMonitorDelay");
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.sds.android.ttpod.framework.support.monitor.MediaButtonReceiver.2
            @Override // java.lang.Runnable
            public void run() {
                MediaButtonReceiver.m2252b();
            }
        }, 500L);
    }

    /* renamed from: b */
    public static void m2252b() {
        LogUtils.m8388a("MediaButtonReceiver", "registerMediaButtonEvent");
        try {
            BaseApplication m4635c = BaseApplication.getApplication();
            AudioManager audioManager = (AudioManager) m4635c.getSystemService(Context.AUDIO_SERVICE);
            ComponentName componentName = new ComponentName(m4635c, MediaButtonReceiver.class);
            audioManager.unregisterMediaButtonEventReceiver(new ComponentName(m4635c, "com.sds.android.ttpod.core.playback.MediaButtonIntentReceiver"));
            audioManager.unregisterMediaButtonEventReceiver(new ComponentName(m4635c, "com.sds.android.ttpod.app.support.monitor.MediaButtonReceiver"));
            boolean m2826u = Preferences.m2826u();
            if (m2826u) {
                audioManager.registerMediaButtonEventReceiver(componentName);
                LogUtils.m8388a("MediaButtonReceiver", "registerMediaButtonEvent...");
            } else {
                audioManager.unregisterMediaButtonEventReceiver(componentName);
                LogUtils.m8388a("MediaButtonReceiver", "unregisterMediaButtonEvent...");
            }
            BaseApplication.getApplication().getPackageManager().setComponentEnabledSetting(componentName
                    , m2826u ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED : PackageManager.COMPONENT_ENABLED_STATE_DISABLED
                    , PackageManager.DONT_KILL_APP);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
