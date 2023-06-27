package com.sds.android.ttpod.framework.modules.search.p127a;

import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.support.search.p135a.LyricSearchTaskInfo;
import com.sds.android.ttpod.framework.support.search.p135a.PictureSearchTaskInfo;
import com.sds.android.ttpod.media.mediastore.MediaItem;

/* renamed from: com.sds.android.ttpod.framework.modules.search.a.c */
/* loaded from: classes.dex */
public class SearchTaskInfoUtils {
    /* renamed from: a */
    public static LyricSearchTaskInfo m3888a(MediaItem mediaItem) {
        LyricSearchTaskInfo lyricSearchTaskInfo = new LyricSearchTaskInfo();
        lyricSearchTaskInfo.m2209a((Object) mediaItem);
        lyricSearchTaskInfo.setMediaItem(mediaItem);
        return lyricSearchTaskInfo;
    }

    /* renamed from: a */
    public static LyricSearchTaskInfo m3887a(MediaItem mediaItem, String str, String str2) {
        LyricSearchTaskInfo m3888a = m3888a(mediaItem);
        m3888a.setAuto(true);
        m3888a.m2208a(str);
        m3888a.m2205b(str2);
        return m3888a;
    }

    /* renamed from: b */
    public static PictureSearchTaskInfo m3886b(MediaItem mediaItem) {
        PictureSearchTaskInfo pictureSearchTaskInfo = new PictureSearchTaskInfo();
        pictureSearchTaskInfo.setMediaItem(mediaItem);
        pictureSearchTaskInfo.m2209a((Object) mediaItem);
        pictureSearchTaskInfo.m2188b(DisplayUtils.m7225c());
        pictureSearchTaskInfo.m2187c(DisplayUtils.m7224d());
        if ("artist".equals(mediaItem.getArtist())) {
            pictureSearchTaskInfo.m2189a(2);
        } else if ("album".equals(mediaItem.getAlbum())) {
            pictureSearchTaskInfo.m2189a(4);
        } else {
            pictureSearchTaskInfo.m2189a(6);
        }
        return pictureSearchTaskInfo;
    }

    /* renamed from: b */
    public static PictureSearchTaskInfo m3885b(MediaItem mediaItem, String str, String str2) {
        PictureSearchTaskInfo m3886b = m3886b(mediaItem);
        m3886b.setAuto(true);
        m3886b.m2208a(str);
        m3886b.m2205b(str2);
        return m3886b;
    }

    /* renamed from: c */
    public static LyricSearchTaskInfo m3884c(MediaItem mediaItem) {
        LyricSearchTaskInfo m3888a = m3888a(mediaItem);
        m3888a.m2207a(true);
        m3888a.m2201c(true);
        return m3888a;
    }

    /* renamed from: d */
    public static PictureSearchTaskInfo m3883d(MediaItem mediaItem) {
        PictureSearchTaskInfo pictureSearchTaskInfo = new PictureSearchTaskInfo();
        pictureSearchTaskInfo.setMediaItem(mediaItem);
        pictureSearchTaskInfo.m2209a((Object) mediaItem);
        pictureSearchTaskInfo.m2207a(true);
        pictureSearchTaskInfo.m2201c(true);
        return pictureSearchTaskInfo;
    }
}
