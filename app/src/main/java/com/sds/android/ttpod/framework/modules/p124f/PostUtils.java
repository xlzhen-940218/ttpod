package com.sds.android.ttpod.framework.modules.p124f;

import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.media.mediastore.MediaItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.sds.android.ttpod.framework.modules.f.f */
/* loaded from: classes.dex */
public class PostUtils {
    /* renamed from: a */
    public static Post m4029a(Post post) {
        for (Post repostedMsg = post != null ? post.getRepostedMsg() : null; repostedMsg != null; repostedMsg = repostedMsg.getRepostedMsg()) {
            post = repostedMsg;
        }
        return post;
    }

    /* renamed from: b */
    public static List<MediaItem> m4027b(Post post) {
        if (post == null) {
            throw new IllegalArgumentException("post should not be null");
        }
        Post m4029a = m4029a(post);
        int type = m4029a.getType();
        ArrayList arrayList = new ArrayList();
        if (MusiccircleContentType.SINGLE_SONG.value() == type) {
            m4028a(arrayList, m4029a);
        } else if (MusiccircleContentType.DJ.value() == type) {
            m4028a(arrayList, m4029a);
            m4026b(arrayList, m4029a);
        } else if (MusiccircleContentType.SONG_LIST.value() == type) {
            m4026b(arrayList, m4029a);
        }
        return arrayList;
    }

    /* renamed from: a */
    private static void m4028a(List<MediaItem> list, Post post) {
        OnlineMediaItem mediaItem = post.getMediaItem();
        if (mediaItem != null) {
            list.add(MediaItemUtils.m4716a(mediaItem));
        }
    }

    /* renamed from: b */
    private static void m4026b(List<MediaItem> list, Post post) {
        ArrayList<OnlineMediaItem> songList = post.getSongList();
        if (songList != null) {
            Iterator<OnlineMediaItem> it = songList.iterator();
            while (it.hasNext()) {
                OnlineMediaItem next = it.next();
                if (next != null) {
                    list.add(MediaItemUtils.m4716a(next));
                }
            }
        }
    }

    /* renamed from: c */
    public static List<Long> m4025c(Post post) {
        if (post == null) {
            throw new IllegalArgumentException("post should not be null");
        }
        Post m4029a = m4029a(post);
        int type = m4029a.getType();
        ArrayList arrayList = new ArrayList();
        if (MusiccircleContentType.SINGLE_SONG.value() == type) {
            m4024c(arrayList, m4029a);
        } else if (MusiccircleContentType.DJ.value() == type) {
            m4024c(arrayList, m4029a);
            m4022d(arrayList, m4029a);
        } else if (MusiccircleContentType.SONG_LIST.value() == type) {
            m4022d(arrayList, m4029a);
        }
        return arrayList;
    }

    /* renamed from: c */
    private static void m4024c(List<Long> list, Post post) {
        OnlineMediaItem mediaItem = post.getMediaItem();
        if (mediaItem != null) {
            list.add(Long.valueOf(mediaItem.getSongId()));
        }
    }

    /* renamed from: d */
    private static void m4022d(List<Long> list, Post post) {
        ArrayList<OnlineMediaItem> songList = post.getSongList();
        if (songList != null) {
            for (OnlineMediaItem onlineMediaItem : songList) {
                if (onlineMediaItem != null) {
                    list.add(Long.valueOf(onlineMediaItem.getSongId()));
                }
            }
        }
    }

    /* renamed from: d */
    public static String m4023d(Post post) {
        ArrayList<String> picList = post.getPicList();
        if (picList != null && !picList.isEmpty()) {
            return picList.get(0);
        }
        if (post.getType() == MusiccircleContentType.SINGLE_SONG.value()) {
            return m4030a(post.getMediaItem().getArtistId());
        }
        return "";
    }

    /* renamed from: a */
    private static String m4030a(long j) {
        return "http://pic.ttpod.cn/singerpic" + "/" + (j / 255) + "/" + (j / 7) + "/" + j + "_320.jpg";
    }
}
