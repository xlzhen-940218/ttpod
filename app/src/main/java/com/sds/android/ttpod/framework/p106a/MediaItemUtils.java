package com.sds.android.ttpod.framework.p106a;

import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.modules.skin.p130c.DateTimeUtils;
import com.sds.android.ttpod.framework.p106a.p107a.ErrorStatistic;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.media.MediaTag;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;

import java.util.ArrayList;

/* renamed from: com.sds.android.ttpod.framework.a.i */
/* loaded from: classes.dex */
public class MediaItemUtils {
    /* renamed from: a */
    public static MediaItem m4716a(OnlineMediaItem onlineMediaItem) {
        int i;
        int i2;
        int size;
        if (onlineMediaItem.getLLUrls().size() > 0) {
            int bitrate = onlineMediaItem.getLLUrls().get(0).getBitrate();
            i = (int) DateTimeUtils.m3746a(onlineMediaItem.getLLUrls().get(0).getDuration());
            i2 = bitrate;
        } else if (onlineMediaItem.getDownloadUrls() == null || (size = onlineMediaItem.getDownloadUrls().size()) <= 0) {
            i = 0;
            i2 = 0;
        } else {
            OnlineMediaItem.Url url = onlineMediaItem.getDownloadUrls().get(size - 1);
            int bitrate2 = url.getBitrate();
            i = (int) DateTimeUtils.m3746a(url.getDuration());
            i2 = bitrate2;
        }
        if (onlineMediaItem.getAuditionUrls() == null || onlineMediaItem.getAuditionUrls().size() == 0) {
            ErrorStatistic.m5243a(onlineMediaItem.getSongId());
        } else if (onlineMediaItem.getDownloadUrls() == null || onlineMediaItem.getDownloadUrls().size() == 0) {
            ErrorStatistic.m5238b(onlineMediaItem.getSongId());
        }
        long currentTimeMillis = System.currentTimeMillis();
        MediaItem mediaItem = new MediaItem(null, Long.valueOf(onlineMediaItem.getSongId()), null, null, onlineMediaItem.getTitle(), onlineMediaItem.getArtist(), onlineMediaItem.getAlbum(), null, null, onlineMediaItem.getMVUrls().size() > 0 ? MediaItem.MIMETYPE_MV : null, 0, Integer.valueOf(i), 0, 0, 0, Integer.valueOf(i2), 0, 0, null, 0, Integer.valueOf(onlineMediaItem.getPickCount()), Long.valueOf(currentTimeMillis), Long.valueOf(currentTimeMillis), null, false, JSONUtils.toJson(onlineMediaItem), MediaStorage.GROUP_ID_ONLINE_TEMPORARY);
        ArrayList<OnlineMediaItem.OutListItem> outList = onlineMediaItem.getOutList();
        if (outList == null) {
            outList = new ArrayList<>();
        }
        mediaItem.setOutListList(outList);
        mediaItem.setHasOutList(!ListUtils.m4718a(onlineMediaItem.getOutList()));
        mediaItem.setCensorLevel(onlineMediaItem.getCensorLevel());
        return mediaItem;
    }

    /* renamed from: a */
    public static MediaItem m4711a(String str, String str2, String str3, int i) {
        long currentTimeMillis = System.currentTimeMillis();
        String str4 = null;
        String str5 = null;
        if (StringUtils.m8346a(str) || !FileUtils.m8419a(str)) {
            str5 = str;
        } else {
            str4 = str;
        }
        return new MediaItem(null, null, str4, null, str2, str3, null, null, null, null, 0, Integer.valueOf(i), 0, 0, 0, 0, 0, 0, null, 0, 0, Long.valueOf(currentTimeMillis), Long.valueOf(currentTimeMillis), null, false, str5, MediaStorage.GROUP_ID_ONLINE_TEMPORARY);
    }

    /* renamed from: a */
    public static MediaItem m4712a(String str) {
        DebugUtils.m8426a(str, "mediaSource");
        String m8396p = FileUtils.m8396p(str);
        if (FileUtils.m8414b(m8396p)) {
            String m8399m = FileUtils.m8399m(m8396p);
            if (m8399m.equalsIgnoreCase("mid") || m8399m.equalsIgnoreCase("midi") || m8399m.equalsIgnoreCase("amr")) {
                return new MediaItem(null, null, m8396p, FileUtils.m8400l(m8396p), FileUtils.m8401k(m8396p), "", "", "", null, FileUtils.m8399m(m8396p), 0, 0, 0, 0, 0, 0, 0, 0, null, 0, 0, Long.valueOf(System.currentTimeMillis()), Long.valueOf(System.currentTimeMillis()), 0L, false, null, null);
            }
            MediaTag mediaTag = new MediaTag();
            long currentTimeMillis = System.currentTimeMillis();
            if (mediaTag.openFile(m8396p, true)) {
                return new MediaItem(null, null, m8396p, FileUtils.m8400l(m8396p), mediaTag.getTitle(), mediaTag.getArtist(), mediaTag.getAlbum(), mediaTag.getGenre(), null, m8399m, 0, Integer.valueOf(mediaTag.duration()), Integer.valueOf(mediaTag.track()), Integer.valueOf(mediaTag.year()), null, Integer.valueOf(mediaTag.bitRate()), Integer.valueOf(mediaTag.sampleRate()), Integer.valueOf(mediaTag.channels()), mediaTag.getComment(), 0, 0, Long.valueOf(currentTimeMillis), Long.valueOf(currentTimeMillis), null, false, null, null);
            }
            mediaTag.close();
        }
        return null;
    }

    /* renamed from: a */
    public static void m4714a(MediaItem mediaItem, String str) {
        mediaItem.setLocalDataSource(str);
        if (FileUtils.m8414b(mediaItem.getLocalDataSource())) {
            MediaTag mediaTag = new MediaTag();
            String m8399m = FileUtils.m8399m(mediaItem.getLocalDataSource());
            if (mediaTag.openFile(mediaItem.getLocalDataSource(), true)) {
                mediaItem.setBitRate(Integer.valueOf(mediaTag.bitRate()));
                mediaItem.setDuration(Integer.valueOf(mediaTag.duration()));
                mediaItem.setTrack(Integer.valueOf(mediaTag.track()));
                mediaItem.setYear(Integer.valueOf(mediaTag.year()));
                mediaItem.setChannels(Integer.valueOf(mediaTag.channels()));
                mediaItem.setSampleRate(Integer.valueOf(mediaTag.sampleRate()));
                mediaItem.setMimeType(m8399m);
            }
            mediaTag.close();
            return;
        }
        mediaItem.setLocalDataSource("");
        OnlineMediaItem onlineMediaItem = (OnlineMediaItem) JSONUtils.fromJson(mediaItem.getExtra(), OnlineMediaItem.class);
        if (onlineMediaItem != null) {
            MediaItem m4716a = m4716a(onlineMediaItem);
            mediaItem.setBitRate(m4716a.getBitRate());
            mediaItem.setMimeType(m4716a.getMimeType());
        } else {
            mediaItem.setMimeType(null);
        }
        mediaItem.setDuration(0);
        mediaItem.setTrack(0);
        mediaItem.setYear(0);
        mediaItem.setChannels(0);
        mediaItem.setSampleRate(0);
    }

    /* renamed from: a */
    public static boolean m4715a(MediaItem mediaItem) {
        DebugUtils.m8426a(mediaItem, "mediaItem");
        if (mediaItem.isOnline()) {
            return Cache.m3218a().m3224O().contains(mediaItem.getID());
        }
        if (Cache.m3218a().m3220S().contains(mediaItem.getID())) {
            return true;
        }
        return m4713a(mediaItem.getSongID());
    }

    /* renamed from: a */
    public static boolean m4713a(Long l) {
        return (l == null || l.longValue() == 0 || !Cache.m3218a().m3224O().contains(MediaItem.genIDWithSongID(l))) ? false : true;
    }
}
