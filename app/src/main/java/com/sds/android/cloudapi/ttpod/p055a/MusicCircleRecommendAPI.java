package com.sds.android.cloudapi.ttpod.p055a;

import com.sds.android.cloudapi.ttpod.result.CircleFirstPublishListResult;
import com.sds.android.cloudapi.ttpod.result.CirclePosterListResultLegacy;
import com.sds.android.cloudapi.ttpod.result.FirstPublishNewAlbumResult;
import com.sds.android.cloudapi.ttpod.result.FirstPublishNewSongCategoryResult;
import com.sds.android.cloudapi.ttpod.result.FirstPublishNewSongMoreResult;
import com.sds.android.cloudapi.ttpod.result.RecommendPostResult;

import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import java.util.Map;

/* renamed from: com.sds.android.cloudapi.ttpod.a.o */
/* loaded from: classes.dex */
public final class MusicCircleRecommendAPI {

    /* renamed from: a */
    private static final String f2267a = UrlList.recommend;

    /* renamed from: a */
    public static Request<CirclePosterListResultLegacy> m8880a() {
        return new GetMethodRequest(CirclePosterListResultLegacy.class, "http://v1.ard.tj.itlily.com/recommend", "poster").m8540a((Map<String, Object>) EnvironmentUtils.C0603b.m8488e());
    }

    /* renamed from: a */
    public static Request<RecommendPostResult> m8879a(int i, int i2) {
        return new GetMethodRequest(RecommendPostResult.class, f2267a, "more_recomm").putParams("userId", EnvironmentUtils.C0603b.m8488e().get("tid")).putParams("page", Integer.valueOf(i)).putParams("size", Integer.valueOf(i2));
    }

    /* renamed from: b */
    public static Request<CircleFirstPublishListResult> m8878b() {
        return new GetMethodRequest(CircleFirstPublishListResult.class, "http://v1.ard.tj.itlily.com/recommend", "new_songs");
    }

    /* renamed from: b */
    public static Request<FirstPublishNewSongMoreResult> m8877b(int i, int i2) {
        return new GetMethodRequest(FirstPublishNewSongMoreResult.class, f2267a, "new_songs_more").putParams("page", Integer.valueOf(i)).putParams("size", Integer.valueOf(i2));
    }

    /* renamed from: c */
    public static Request<FirstPublishNewSongCategoryResult> m8876c() {
        return new GetMethodRequest(FirstPublishNewSongCategoryResult.class, f2267a, "new_songlists");
    }

    /* renamed from: c */
    public static Request<FirstPublishNewAlbumResult> m8875c(int i, int i2) {
        return new GetMethodRequest(FirstPublishNewAlbumResult.class, f2267a, "new_albums").putParams("page", Integer.valueOf(i)).putParams("size", Integer.valueOf(i2));
    }
}
