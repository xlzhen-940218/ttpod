package com.sds.android.ttpod.share;

import android.app.Activity;
import com.sds.android.ttpod.share.p136a.BaseApi;
import com.sds.android.ttpod.share.p136a.MusicCircleApi;
import com.sds.android.ttpod.share.p136a.OtherApi;
import com.sds.android.ttpod.share.p136a.QQApi;
import com.sds.android.ttpod.share.p136a.QQWeiboApi;
import com.sds.android.ttpod.share.p136a.QQZoneApi;
import com.sds.android.ttpod.share.p136a.SinaWeiboApi;
import com.sds.android.ttpod.share.p136a.WeChatApi;
import com.sds.android.ttpod.share.p136a.WeChatFriendApi;
import com.sds.android.ttpod.share.p137b.AuthHandler;
import com.sds.android.ttpod.share.p137b.NoneAuthHandler;
import com.sds.android.ttpod.share.p137b.QQZoneAuthHandler;
import com.sds.android.ttpod.share.p137b.SinaWeiboAuthHandler;

/* renamed from: com.sds.android.ttpod.share.c */
/* loaded from: classes.dex */
public class ShareApiFactory {
    /* renamed from: a */
    public static BaseApi m2057a(ShareType shareType, Activity activity) {
        switch (shareType) {
            case MUSIC_CYCLE:
                return new MusicCircleApi();
            case SINA_WEIBO:
                return new SinaWeiboApi("3374293008");
            case QQ_WEIBO:
                return new QQWeiboApi("801317057");
            case QZONE:
                return new QQZoneApi(activity, "100240447");
            case QQ:
                return new QQApi("100240447", activity);
            case WECHAT:
                return new WeChatApi("wx35c4036acd33a2bc", activity);
            case WECHAT_FRIENDS:
                return new WeChatFriendApi("wx35c4036acd33a2bc", activity);
            case OTHER:
                return new OtherApi(activity);
            default:
                return null;
        }
    }

    /* renamed from: b */
    public static AuthHandler m2056b(ShareType shareType, Activity activity) {
        switch (shareType) {
            case SINA_WEIBO:
                return new SinaWeiboAuthHandler(activity);
            /*case QQ_WEIBO:
                return new QQWeiboAuthHandler(activity);*/
            case QZONE:
                return new QQZoneAuthHandler(activity);
            default:
                return new NoneAuthHandler(activity);
        }
    }
}
