package com.sds.android.ttpod.framework.base;

/* loaded from: classes.dex */
public final class Action {
    private static final String PREFIX = Action.class.getName() + ".";
    public static final String EXCEPTION_REPORT = buildAction("exception_report");
    public static final String PLAY_STATUS_CHANGED = buildAction("play_status_changed");
    public static final String PLAYLIST_IS_EMPTY = buildAction("playlist_is_empty");
    public static final String PLAY_BUFFERING_STARTED = buildAction("play_buffering_start");
    public static final String PLAY_BUFFERING_DONE = buildAction("play_buffering_done");
    public static final String UPDATE_MEDIA_DURATION = buildAction("update_media_duration");
    public static final String AUDIOEFFECT_CHANGED = buildAction("update_audioeffect_info");
    public static final String PLAY_HEADSET_PLUG = buildAction("play_headset_plug");
    public static final String PLAY_HEADSET_UNPLUG = buildAction("play_headset_unplug");
    public static final String SWITCH_ARTIST_BITMAP = buildAction("switch_artist_bitmap");
    public static final String PLAY_MEDIA_CHANGED = buildAction("play_media_changed");
    public static final String LYRIC_PIC_OPERATE_RESULT = buildAction("lyric_pic_operate_result");
    public static final String LYRIC_PIC_BATCH_OPERATE_RESULT = buildAction("lyric_pic_batch_operate_result");
    public static final String APP_WIDGET_QUERY = buildAction("app_widget_query");
    public static final String MINI_LYRIC_LOCK_STATUS_CHANGED = buildAction("mini_lyric_lock_status_changed");
    public static final String LOCK_SCREEN_ACTIVITY_ENTRY = buildAction("lock_screen_activity_entry");
    public static final String STOP_LOCK_SCREEN = buildAction("stop_lock_screen");
    public static final String EXIT = buildAction("exit");
    public static final String LAUNCHER = buildAction("launcher");
    public static final String START_ENTRY = buildAction("start_entry");
    public static final String NOTIFICATION_START_DOWNLOAD_MANAGER = buildAction("start_downloadmanager");
    public static final String NOTIFICATION_START_MEDIA_SCAN = buildAction("start_media_scan");
    public static final String ACTION_AUTO_PLAY_ARTIST_IMAGE = buildAction("auto_play_artist_image");
    public static final String PUSH_CLIENT_ID_BROADCAST = buildAction("push_client_id_broadcast");
    public static final String CALL_STATE_RINGING = buildAction("call_state_ringing");
    public static final String PREFERENCES_CHANGED = buildAction("preferences_changed");
    public static final String DOWNLOAD_TASK_STATE_CHANGED = buildAction("download_task_state_changed");
    public static final String APP_WIDGET_ENABLE_CHANGED = buildAction("app_widget_enable_changed");

    private static String buildAction(String str) {
        return PREFIX + str;
    }
}
