package com.sds.android.ttpod.utils;

import android.graphics.Bitmap;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.sdk.lib.util.BitmapUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.SecurityUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.share.ShareInfo;
import com.sds.android.ttpod.share.p139d.ShareContentUtil;
import java.io.File;
import java.util.ArrayList;

/* renamed from: com.sds.android.ttpod.a.t */
/* loaded from: classes.dex */
public class ShareInfoConvertUtils {

    /* renamed from: a */
    private static final String f2517a = TTPodConfig.getCacheTmpPath() + File.separator + "Player.jpg";

    /* renamed from: a */
    public static ShareInfo m8237a(MediaItem mediaItem, Bitmap bitmap) {
        if (bitmap != null) {
            BitmapUtils.m8450a(bitmap, f2517a);
            bitmap.recycle();
        }
        return m8236a(mediaItem, f2517a);
    }

    /* renamed from: a */
    public static ShareInfo m8236a(MediaItem mediaItem, String str) {
        if (mediaItem != null) {
            ShareInfo shareInfo = new ShareInfo(mediaItem.getTitle(), mediaItem.getArtist(), mediaItem.getSongID());
            shareInfo.m1966b(str);
            shareInfo.m1953g(ShareContentUtil.m1932b(shareInfo));
            shareInfo.m1955f(m8238a(mediaItem));
            shareInfo.m1970a(mediaItem.getID());
            shareInfo.m1971a(mediaItem.getSongID());
            if (!FileUtils.m8419a(mediaItem.getLocalDataSource())) {
                shareInfo.m1972a(mediaItem.getArtistID());
                shareInfo.m1969a(false);
                return shareInfo;
            }
            return shareInfo;
        }
        return null;
    }

    /* renamed from: a */
    public static ShareInfo m8239a(Post post, String str) {
        OnlineMediaItem mediaItem;
        if (post != null) {
            if (post.getSongList() != null && post.getSongList().size() > 0) {
                mediaItem = post.getSongList().get(0);
            } else {
                mediaItem = post.getMediaItem();
            }
            if (mediaItem != null) {
                ShareInfo shareInfo = new ShareInfo(post.getSongListName(), post.getUser().getNickName(), Long.valueOf(mediaItem.getSongId()));
                String m8240a = m8240a(post);
                shareInfo.m1962c(m8240a);
                shareInfo.m1966b(m8235a(m8240a));
                shareInfo.m1953g("http://quan.dongting.com/do.html?m=music&a=details&id=" + post.getId());
                shareInfo.m1972a(mediaItem.getArtistId());
                shareInfo.m1969a(false);
                shareInfo.m1967b(post.getUser().getUserId());
                shareInfo.m1963c(post.getPostId());
                shareInfo.m1965b(true);
                return shareInfo;
            }
        }
        return null;
    }

    /* renamed from: a */
    private static String m8235a(String str) {
        String str2 = ImageCacheUtils.m4743b().m8815a() + File.separator + SecurityUtils.C0610b.m8359b(str);
        return new File(str2).exists() ? str2 : "";
    }

    /* renamed from: a */
    private static String m8240a(Post post) {
        ArrayList<String> picList = post.getPicList();
        if (picList == null || picList.size() <= 0) {
            return null;
        }
        return picList.get(0);
    }

    /* renamed from: a */
    private static String m8238a(MediaItem mediaItem) {
        String extra = mediaItem.getExtra();
        if (extra == null) {
            return "";
        }
        return ShareContentUtil.m1937a((OnlineMediaItem) JSONUtils.fromJson(extra, OnlineMediaItem.class));
    }
}
