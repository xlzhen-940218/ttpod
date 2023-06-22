package com.sds.android.ttpod.media.player;

/* loaded from: classes.dex */
public class MediaPlayerNotificationInfo {
    private String mIP;
    private String mURL;

    public MediaPlayerNotificationInfo(String str, String str2) {
        this.mIP = str;
        this.mURL = str2;
    }

    public MediaPlayerNotificationInfo(MediaPlayerNotificationInfo mediaPlayerNotificationInfo) {
        this.mIP = mediaPlayerNotificationInfo.mIP;
        this.mURL = mediaPlayerNotificationInfo.mURL;
    }

    public String getIP() {
        return this.mIP;
    }

    public String getURL() {
        return this.mURL;
    }
}
