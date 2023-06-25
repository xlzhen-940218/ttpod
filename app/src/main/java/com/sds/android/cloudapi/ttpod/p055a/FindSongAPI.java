package com.sds.android.cloudapi.ttpod.p055a;

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
        return new GetMethodRequest(OnlineMusicCategoryResult.class, UrlList.f2261b).m8537b("page", Integer.valueOf(i)).m8537b("size", Integer.valueOf(i2));
    }

    /* renamed from: a */
    public static Request<OnlineMusicSubCategoryResult> m8905a(long j, int i, int i2) {
        return new GetMethodRequest(OnlineMusicSubCategoryResult.class, UrlList.f2262c).m8537b("id", Long.valueOf(j)).m8537b("page", Integer.valueOf(i)).m8537b("size", Integer.valueOf(i2));
    }

    /* renamed from: a */
    public static Request<SingerCategoryResult> m8909a(int i) {
        return new GetMethodRequest(SingerCategoryResult.class, "http://v1.ard.tj.itlily.com/ttpod?a=getnewttpod").m8537b("id", Integer.valueOf(i));
    }

    /* renamed from: b */
    public static Request<SingerListResult> m8902b(int i, int i2) {
        return new GetMethodRequest(SingerListResult.class, "http://v1.ard.tj.itlily.com/ttpod?a=getnewttpod").m8537b("id", Integer.valueOf(i)).m8537b("page", Integer.valueOf(i2)).m8537b("size", 1000);
    }

    /* renamed from: a */
    public static Request<OnlineMediaItemsResult> m8904a(String str, int i) {
        return m8903a(str, i, 50);
    }

    /* renamed from: a */
    public static Request<OnlineMediaItemsResult> m8903a(String str, int i, int i2) {
        return new GetMethodRequest(OnlineMediaItemsResult.class, "http://so.ard.iyyin.com/v2/songs/singersearch").m8537b("q", str).m8537b("page", Integer.valueOf(i)).m8537b("size", Integer.valueOf(i2));
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
        return new GetMethodRequest(IntroductionResult.class, UrlList.m8962a() + "/recomm/module_detail").m8537b("id", Long.valueOf(j));
    }

    /* renamed from: c */
    public static Request<MVListResult> m8901c(int i, int i2) {
        return new GetMethodRequest(MVListResult.class, "http://v1.ard.tj.itlily.com/ttpod?a=getnewttpod").m8537b("id", Integer.valueOf(i)).m8537b("page", Integer.valueOf(i2)).m8537b("size", 50);
    }
}
