package com.sds.android.ttpod.framework.modules.core.audioeffect;

import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.io.File;

/* renamed from: com.sds.android.ttpod.framework.modules.core.audioeffect.d */
/* loaded from: classes.dex */
public class AudioEffectUtils {
    /* renamed from: a */
    public static String m4341a(MediaItem mediaItem) {
        if (mediaItem == null) {
            return null;
        }
        String title = mediaItem.getTitle();
        String artist = mediaItem.getArtist();
        Long songID = mediaItem.getSongID();
        StringBuilder sb = new StringBuilder(TTPodConfig.getEffectPath());
        sb.append(File.separator);
        if (songID != null) {
            sb.append(songID).append(".");
        }
        sb.append(title).append(".").append(artist).append(".ttce");
        return sb.toString();
    }

    /* renamed from: b */
    public static boolean m4339b(MediaItem mediaItem) {
        AudioEffectCache audioEffectCache;
        String m4341a = m4341a(mediaItem);
        if (FileUtils.isFile(m4341a)) {
            if (mediaItem.getSongID().longValue() != 0 && mediaItem.getLocalDataSource() == null) {
                return true;
            }
            String m8403i = FileUtils.m8403i(m4341a);
            if (m8403i != null && (audioEffectCache = (AudioEffectCache) JSONUtils.fromJson(m8403i, AudioEffectCache.class)) != null && StringUtils.equals(audioEffectCache.m4380p(), mediaItem.getLocalDataSource())) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public static String m4340a(Long l, String str, String str2) {
        StringBuilder sb = new StringBuilder(TTPodConfig.getCacheEffectPath());
        sb.append(File.separator);
        if (l != null) {
            sb.append(l).append(".");
        }
        sb.append(str).append(".").append(str2).append(".ttce");
        return sb.toString();
    }

    /* renamed from: b */
    public static String m4338b(Long l, String str, String str2) {
        StringBuilder sb = new StringBuilder(TTPodConfig.getEffectPath());
        sb.append(File.separator).append(l).append(".").append(str).append(".").append(str2).append(".ttce");
        return sb.toString();
    }
}
