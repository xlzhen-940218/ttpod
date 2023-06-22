package com.voicedragon.musicclient.record;

import com.voicedragon.musicclient.api.RankBottomUrl;
import com.voicedragon.musicclient.api.RankTop;
import com.voicedragon.musicclient.orm.download.OrmDownloadEntry;
import com.voicedragon.musicclient.orm.playlist.OrmSongMenu;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class DoresoJSON {
    public static DoresoMusicTrack[] GetJSONString(String result) {
        try {
            JSONArray array = new JSONArray(result);
            int length = array.length();
            DoresoMusicTrack[] tracks = new DoresoMusicTrack[length];
            for (int i = 0; i < length; i++) {
                tracks[i] = build(array.getJSONObject(i));
            }
            return tracks;
        } catch (JSONException e) {
            return new DoresoMusicTrack[0];
        }
    }

    public static DoresoMusicTrack[] GetJSONString(JSONObject object) {
        try {
            if (object.getInt("status") == 1) {
                JSONArray array = object.getJSONArray("contents");
                int length = array.length();
                DoresoMusicTrack[] tracks = new DoresoMusicTrack[length];
                for (int i = 0; i < length; i++) {
                    JSONObject obj = array.getJSONObject(i);
                    JSONObject o = obj.getJSONObject("contents");
                    tracks[i] = new DoresoMusicTrack();
                    tracks[i].setName(o.getString("title"));
                    tracks[i].setMd5(o.getString("md5sum"));
                    tracks[i].setArtist(o.getString("artist"));
                }
                return tracks;
            }
            DoresoMusicTrack[] tracks2 = new DoresoMusicTrack[0];
            return tracks2;
        } catch (JSONException e) {
            DoresoMusicTrack[] tracks3 = new DoresoMusicTrack[0];
            return tracks3;
        }
    }

    public static List<DoresoMusicTrack> getTrackList(String result) {
        List<DoresoMusicTrack> tracks = null;
        Exception e;
        List<DoresoMusicTrack> tracks2 = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(result);
            int length = array.length();
            for (int i = 0; i < length; i++) {
                tracks2.add(build(array.getJSONObject(i)));
            }
            return tracks2;
        } catch (JSONException e1) {
            e = e1;
            tracks = tracks2;
            e.printStackTrace();
            return tracks;
        }
    }

    public static DoresoMusicTrack build(JSONObject json) {
        DoresoMusicTrack track = new DoresoMusicTrack();
        try {
            track.setId(json.getString("id"));
        } catch (Exception e) {
            track.setName("");
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
            track.setMd5(json.getString("md5sum"));
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

    public static RankBottomUrl[] getRankBottom(String result) {
        try {
            JSONArray array = new JSONArray(result);
            int length = array.length();
            RankBottomUrl[] rankbottoms = new RankBottomUrl[length];
            for (int i = 0; i < length; i++) {
                rankbottoms[i] = buildRankBottomUrl(array.getJSONObject(i));
            }
            return rankbottoms;
        } catch (JSONException e) {
            return new RankBottomUrl[0];
        }
    }

    public static List<RankTop> getRankTop(String result) {
        List<RankTop> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                list.add(buildRankTop(array.getJSONObject(i)));
            }
        } catch (JSONException e) {
        }
        return list;
    }

    protected static RankBottomUrl buildRankBottomUrl(JSONObject json) {
        RankBottomUrl rankbottom = new RankBottomUrl();
        try {
            rankbottom.setId(json.getString("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            rankbottom.setTitle(json.getString("title"));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        try {
            rankbottom.setUrl(json.getString(RankBottomUrl.URL));
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
        return rankbottom;
    }

    protected static RankTop buildRankTop(JSONObject json) {
        RankTop ranktop = new RankTop();
        try {
            ranktop.setType_id(Integer.parseInt(json.getString("type_id")));
        } catch (Exception e) {
            ranktop.setType_id(2);
        }
        switch (ranktop.getType_id()) {
            case 1:
                try {
                    JSONObject obj = json.getJSONObject("content");
                    try {
                        ranktop.setMd5(obj.getString("md5sum"));
                    } catch (Exception e2) {
                        ranktop.setMd5("");
                    }
                    try {
                        ranktop.setTitle(obj.getString("title"));
                    } catch (Exception e3) {
                        ranktop.setTitle("");
                    }
                    try {
                        ranktop.setArtist(obj.getString("artist"));
                        break;
                    } catch (Exception e4) {
                        ranktop.setArtist("");
                        break;
                    }
                } catch (JSONException e1) {
                    e1.printStackTrace();
                    break;
                }
            case 2:
                try {
                    ranktop.setContent(json.getString("content"));
                } catch (Exception e5) {
                    ranktop.setContent("");
                }
                try {
                    ranktop.setDescription(json.getString("description"));
                    break;
                } catch (Exception e6) {
                    ranktop.setDescription("");
                    break;
                }
            case 4:
                try {
                    ranktop.setContent(json.getString("content"));
                } catch (Exception e7) {
                    ranktop.setContent("");
                }
                try {
                    ranktop.setDescription(json.getString("description"));
                    break;
                } catch (Exception e8) {
                    ranktop.setDescription("");
                    break;
                }
        }
        try {
            ranktop.setBid(json.getString("bid"));
        } catch (Exception e9) {
            ranktop.setBid("");
        }
        try {
            ranktop.setPic_url(json.getString("pic_url"));
        } catch (Exception e10) {
            ranktop.setPic_url("");
        }
        return ranktop;
    }
}
