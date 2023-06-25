package com.sds.android.ttpod.component.p086c;

import com.sds.android.cloudapi.ttpod.data.AlbumItem;
import com.sds.android.cloudapi.ttpod.data.MusicRank;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.RadioCategoryChannel;
import com.sds.android.cloudapi.ttpod.data.SingerData;
import com.sds.android.cloudapi.ttpod.result.OnlineMusicSubCategoryResult;
import com.sds.android.sdk.lib.util.StringUtils;

/* renamed from: com.sds.android.ttpod.component.c.c */
/* loaded from: classes.dex */
public class OnlinePlayingGroupUtils {
    /* renamed from: a */
    public static String m6917a(MusicRank musicRank) {
        return musicRank == null ? "" : "排行榜_" + musicRank.getTitle();
    }

    /* renamed from: a */
    public static boolean m6912a(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("排行榜_");
    }

    /* renamed from: a */
    public static String m6913a(OnlineMusicSubCategoryResult.SubCategoryData subCategoryData) {
        return subCategoryData == null ? "" : "分类_" + subCategoryData.getName();
    }

    /* renamed from: a */
    public static String m6915a(RadioCategoryChannel radioCategoryChannel) {
        return radioCategoryChannel == null ? "" : "电台_" + radioCategoryChannel.getCategoryChannelName();
    }

    /* renamed from: b */
    public static boolean m6909b(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("电台_");
    }

    /* renamed from: a */
    public static String m6914a(SingerData singerData) {
        return singerData == null ? "" : "歌手_" + singerData.getName();
    }

    /* renamed from: a */
    public static String m6916a(Post post) {
        return post == null ? "" : "音乐圈_" + post.getId();
    }

    /* renamed from: a */
    public static boolean m6910a(String str, Post post) {
        return StringUtils.m8344a(str, m6916a(post));
    }

    /* renamed from: c */
    public static String m6908c(String str) {
        return StringUtils.isEmpty(str) ? "" : "音乐圈_" + str;
    }

    /* renamed from: a */
    public static String m6918a(AlbumItem albumItem) {
        return albumItem == null ? "" : "专辑_" + albumItem.getId();
    }

    /* renamed from: a */
    public static boolean m6911a(String str, AlbumItem albumItem) {
        return StringUtils.m8344a(str, m6918a(albumItem));
    }
}
