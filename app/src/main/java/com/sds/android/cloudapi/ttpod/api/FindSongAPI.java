package com.sds.android.cloudapi.ttpod.api;

import com.sds.android.cloudapi.ttpod.result.FindSongCategoryResult;
import com.sds.android.cloudapi.ttpod.result.IntroductionResult;
import com.sds.android.cloudapi.ttpod.result.MVListResult;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.cloudapi.ttpod.result.OnlineMusicCategoryResult;
import com.sds.android.cloudapi.ttpod.result.OnlineMusicSubCategoryResult;
import com.sds.android.cloudapi.ttpod.result.SingerCategoryResult;
import com.sds.android.cloudapi.ttpod.result.SingerListResult;

import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.Request;


/* renamed from: com.sds.android.cloudapi.ttpod.a.h */
/* loaded from: classes.dex */
public class FindSongAPI {
    /* renamed from: a */
    public static Request<FindSongCategoryResult> m8910a() {
        return new GetMethodRequest(FindSongCategoryResult.class, "http://v1.ard.tj.itlily.com/indexchannel/list?sv=6.0");
    }

    /* renamed from: a */
    public static Request<OnlineMusicCategoryResult> m8908a(int i, int i2) {
        return new GetMethodRequest(OnlineMusicCategoryResult.class, UrlList.moduleCategory).putParams("page", Integer.valueOf(i)).putParams("size", Integer.valueOf(i2));
    }

    /* renamed from: a */
    public static Request<OnlineMusicSubCategoryResult> m8905a(long j, int i, int i2) {
        return new GetMethodRequest(OnlineMusicSubCategoryResult.class, UrlList.moduleSubCategory).putParams("id", Long.valueOf(j)).putParams("page", Integer.valueOf(i)).putParams("size", Integer.valueOf(i2));
    }

    /* renamed from: a */
    public static Request<SingerCategoryResult> m8909a(int i) {
        return new GetMethodRequest(SingerCategoryResult.class, "http://v1.ard.tj.itlily.com/ttpod?a=getnewttpod").putParams("id", Integer.valueOf(i));
    }

    /* renamed from: b */
    public static Request<SingerListResult> m8902b(int i, int i2) {
        return new GetMethodRequest(SingerListResult.class, "http://v1.ard.tj.itlily.com/ttpod?a=getnewttpod").putParams("id", Integer.valueOf(i)).putParams("page", Integer.valueOf(i2)).putParams("size", 1000);
    }

    /* renamed from: a */
    public static Request<OnlineMediaItemsResult> m8904a(String str, int i) {
        return m8903a(str, i, 50);
    }

    /* renamed from: a */
    public static Request<OnlineMediaItemsResult> m8903a(String str, int i, int i2) {
        return new GetMethodRequest(OnlineMediaItemsResult.class, "http://so.ard.iyyin.com/v2/songs/singersearch").putParams("q", str).putParams("page", Integer.valueOf(i)).putParams("size", Integer.valueOf(i2));
    }

    /* renamed from: a */
    public static Request<OnlineMediaItemsResult> m8907a(int i, String str) {
        String str2;
        if (1 == i) {
            str2 = "newest/50";
        } else {
            str2 = str + "/" + i + "/" + 50;
        }
        return new GetMethodRequest(OnlineMediaItemsResult.class, "http://v1.ard.h.itlily.com/new/plaza").m8542a(str2);
    }

    /* renamed from: a */
    public static Request<IntroductionResult> m8906a(long j) {
        return new GetMethodRequest(IntroductionResult.class, UrlList.getHost() + "/recomm/module_detail").putParams("id", Long.valueOf(j));
    }

    /* renamed from: c */
    public static Request<MVListResult> m8901c(int i, int i2) {
        return new GetMethodRequest(MVListResult.class, "http://v1.ard.tj.itlily.com/ttpod?a=getnewttpod").putParams("id", Integer.valueOf(i)).putParams("page", Integer.valueOf(i2)).putParams("size", 50);
    }
}
