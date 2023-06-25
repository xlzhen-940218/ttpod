package com.sds.android.ttpod.activities.share;

import android.app.Activity;

import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.utils.ScreenShotUtils;
import com.sds.android.ttpod.share.IShareAction;
import com.sds.android.ttpod.share.ShareInfo;
import com.sds.android.ttpod.share.ShareType;
import com.sds.android.ttpod.share.p136a.ShareResult;
import java.io.File;

/* renamed from: com.sds.android.ttpod.activities.share.a */
/* loaded from: classes.dex */
public class ShareAction implements IShareAction {

    /* renamed from: a */
    private static final String f3018a = TTPodConfig.m5298j() + File.separator + "Player.jpg";

    /* renamed from: b */
    private static Activity f3019b;

    /* renamed from: c */
    private static String f3020c;

    public ShareAction(Activity activity, String str) {
        f3019b = activity;
        f3020c = str;
    }

    @Override // com.sds.android.ttpod.share.IShareAction
    /* renamed from: a */
    public void mo2117a(ShareType shareType, ShareInfo shareInfo) {
        int m7776a;
     if ((ShareType.QQ_WEIBO == shareType || ShareType.SINA_WEIBO == shareType || ShareType.OTHER == shareType) && StringUtils.isEmpty(shareInfo.m1964c())) {
            ScreenShotUtils.m8244a(f3019b, f3018a);
            shareInfo.m1966b(f3018a);
        }
    }

    @Override // com.sds.android.ttpod.share.IShareAction
    /* renamed from: a */
    public void mo2116a(ShareType shareType, ShareInfo shareInfo, ShareResult shareResult) {
        //StatisticUtils.m4907a("share", "share", shareType.name().toLowerCase(), shareResult.m2092a() ? 1 : -1, shareInfo.m1942p() ? 1 : 0, shareInfo.m1956f(), shareInfo.m1942p() ? shareInfo.m1958e() : shareInfo.m1954g());
    }

    /* renamed from: a */
    private static int m7776a(ShareType shareType) {
        switch (shareType) {
            case MUSIC_CYCLE:
                return 261;
            case SINA_WEIBO:
                return 266;
            case QQ_WEIBO:
                return 267;
            case QZONE:
                return 263;
            case QQ:
                return 262;
            case WECHAT:
                return 264;
            case WECHAT_FRIENDS:
                return 265;
            case OTHER:
                return 268;
            default:
                return 0;
        }
    }
}
