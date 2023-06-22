package com.mradar.sdk.record;

import com.voicedragon.musicclient.record.DoresoMusicTrack;

/* loaded from: classes.dex */
public interface MRadarSdkListener {
    void onError(int i, String str);

    void onFinish(DoresoMusicTrack[] doresoMusicTrackArr, String str);

    void onRecordEnd();

    void onVolumeChanged(double d);
}
