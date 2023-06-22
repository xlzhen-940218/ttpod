package com.mradar.sdk.record;

import com.voicedragon.musicclient.orm.download.OrmDownloadEntry;
import com.voicedragon.musicclient.orm.playlist.OrmSongMenu;
import com.voicedragon.musicclient.record.DoresoMusicTrack;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class MRadarSdkJSON {
    /* JADX INFO: Access modifiers changed from: protected */
    public static DoresoMusicTrack[] GetJSONString(String result) {
        DoresoMusicTrack[] tracks = null;
        try {
            JSONArray array = new JSONArray(result);
            int length = array.length();
            tracks = new DoresoMusicTrack[length];
            for (int i = 0; i < length; i++) {
                tracks[i] = build(array.getJSONObject(i));
            }
        } catch (JSONException e) {
        }
        return tracks;
    }

    protected static DoresoMusicTrack build(JSONObject json) {
        DoresoMusicTrack track = new DoresoMusicTrack();
        try {
            track.setId(json.getString("id"));
        } catch (Exception e) {
            track.setId("0");
        }
        try {
            track.setName(json.getString(OrmSongMenu.NAME));
        } catch (Exception e2) {
            track.setName("");
        }
        try {
            track.setArtist(json.getString(OrmDownloadEntry.ARTIST_NAME));
        } catch (Exception e3) {
            track.setArtist("");
        }
        try {
            track.setAlbum(json.getString("album"));
        } catch (Exception e4) {
            track.setAlbum("");
        }
        try {
            track.setMd5(json.getString("image"));
        } catch (Exception e5) {
            track.setMd5("");
        }
        try {
            track.setOffset(json.getLong("play_offset"));
        } catch (Exception e6) {
            track.setOffset(0L);
        }
        try {
            track.setRating(json.getDouble("rating"));
        } catch (Exception e7) {
            track.setRating(-1.0d);
        }
        try {
            track.setDuration(json.getLong("duration"));
        } catch (Exception e8) {
            track.setDuration(0L);
        }
        return track;
    }
}
