package com.sds.android.cloudapi.ttpod.p055a;

import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.media.mediastore.old.MediaStoreOld;

import java.util.Collection;

/* renamed from: com.sds.android.cloudapi.ttpod.a.q */
/* loaded from: classes.dex */
public class OnlineMediaItemAPI {
    /* renamed from: a */
    public static Request<OnlineMediaItemsResult> m8866a(Long... lArr) {
        return new GetMethodRequest(OnlineMediaItemsResult.class, "http://ting.hotchanson.com/v2/songs", "download").m8537b("song_id", StringUtils.m8342a(",", (Object) lArr));
    }

    /* renamed from: a */
    public static Request<OnlineMediaItemsResult> m8867a(Collection<?> collection) {
        return new GetMethodRequest(OnlineMediaItemsResult.class, "http://ting.hotchanson.com/v2/songs", "download").m8537b("song_id", StringUtils.m8343a(",", collection));
    }

    /* renamed from: b */
    public static Request<OnlineMediaItemsResult> m8864b(Collection<?> collection) {
        return new GetMethodRequest(OnlineMediaItemsResult.class, "http://ting.hotchanson.com/songs", "downwhite").m8537b("song_id", StringUtils.m8343a(",", collection));
    }

    /* renamed from: a */
    public static Request<OnlineMediaItemsResult> m8868a(Long l) {
        return new GetMethodRequest(OnlineMediaItemsResult.class, "http://ting.hotchanson.com/songs", "downone").m8537b("song_id", l);
    }

    /* renamed from: b */
    public static Request<OnlineMediaItemsResult> m8865b(Long l) {
        return new GetMethodRequest(OnlineMediaItemsResult.class, "http://ting.hotchanson.com/songs", "downone_by_ip").m8537b("song_id", l);
    }
}
