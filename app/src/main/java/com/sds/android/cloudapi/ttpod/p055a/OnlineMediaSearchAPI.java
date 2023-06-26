package com.sds.android.cloudapi.ttpod.p055a;

import com.sds.android.cloudapi.ttpod.data.PlaylistResult;
import com.sds.android.cloudapi.ttpod.result.AlbumItemsResult;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.cloudapi.ttpod.result.SearchTypeResult;

import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.sdk.lib.util.EnvironmentUtils;

import java.util.Map;

/* renamed from: com.sds.android.cloudapi.ttpod.a.r */
/* loaded from: classes.dex */
public class OnlineMediaSearchAPI {
    /* renamed from: a */
    public static Request<BaseResult> m8862a(String str) {
        return new GetMethodRequest(BaseResult.class, "http://collect.log.ttpod.com/error/searchsong/index.html").putParams("q", str).m8540a(EnvironmentUtils.C0603b.m8488e());
    }

    /* renamed from: a */
    public static Request<OnlineMediaItemsResult> m8861a(String str, int i, int i2) {
        return new GetMethodRequest(OnlineMediaItemsResult.class, "http://so.ard.iyyin.com/v2", "songs/search").putParams("q", str).putParams("page", Integer.valueOf(i)).putParams("size", Integer.valueOf(i2)).m8540a((Map<String, Object>) EnvironmentUtils.C0603b.m8488e());
    }

    /* renamed from: a */
    public static Request<OnlineMediaItemsResult> m8860a(String str, int i, int i2, String str2) {
        return new GetMethodRequest(OnlineMediaItemsResult.class, "http://so.ard.iyyin.com/s/song_with_out").putParams("q", str).putParams("page", Integer.valueOf(i)).putParams("size", Integer.valueOf(i2)).putParams("client_id", str2).m8540a((Map<String, Object>) EnvironmentUtils.C0603b.m8488e());
    }

    /* renamed from: b */
    public static Request<AlbumItemsResult> m8859b(String str, int i, int i2) {
        return new GetMethodRequest(AlbumItemsResult.class, "http://so.ard.iyyin.com/albums", "search").putParams("q", str).putParams("page", Integer.valueOf(i)).putParams("size", Integer.valueOf(i2)).m8540a((Map<String, Object>) EnvironmentUtils.C0603b.m8488e());
    }

    /* renamed from: b */
    public static Request<AlbumItemsResult> m8858b(String str, int i, int i2, String str2) {
        return m8859b(str, i, i2).putParams("sugg", str2);
    }

    /* renamed from: c */
    public static Request<PlaylistResult> m8857c(String str, int i, int i2, String str2) {
        return new GetMethodRequest(PlaylistResult.class, "http://so.ard.iyyin.com/s/playlist").putParams("q", str).putParams("page", Integer.valueOf(i)).putParams("size", Integer.valueOf(i2)).putParams("sugg", str2).m8540a((Map<String, Object>) EnvironmentUtils.C0603b.m8488e());
    }

    /* renamed from: a */
    public static Request<SearchTypeResult> m8863a() {
        return new GetMethodRequest(SearchTypeResult.class, "http://so.ard.iyyin.com", "meta/query_tab");
    }
}
