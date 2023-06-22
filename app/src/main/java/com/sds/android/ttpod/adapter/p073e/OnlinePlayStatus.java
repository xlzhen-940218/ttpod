package com.sds.android.ttpod.adapter.p073e;

import com.sds.android.ttpod.media.player.PlayStatus;

/* renamed from: com.sds.android.ttpod.adapter.e.c */
/* loaded from: classes.dex */
public enum OnlinePlayStatus {
    LOADING,
    PLAYING,
    PAUSE,
    STOP;

    public static OnlinePlayStatus from(PlayStatus playStatus) {
        switch (playStatus) {
            case STATUS_PLAYING:
                return PLAYING;
            case STATUS_PAUSED:
                return PAUSE;
            case STATUS_STOPPED:
                return STOP;
            default:
                return LOADING;
        }
    }
}
