package com.sds.android.ttpod.share;

import com.sds.android.ttpod.R;

/* renamed from: com.sds.android.ttpod.share.f */
/* loaded from: classes.dex */
public enum ShareType {
    NONE,
    FRIEND,
    MUSIC_CYCLE,
    SINA_WEIBO,
    QQ_WEIBO,
    QZONE,
    QQ,
    WECHAT,
    WECHAT_FRIENDS,
    OTHER;

    public static int getShareContentDialogTitle(ShareType shareType) {
        switch (shareType) {
            case SINA_WEIBO:
                return R.string.share_to_sina_weibo;
            case QQ_WEIBO:
                return R.string.share_to_qq_weibo;
            case QZONE:
                return R.string.share_to_qqzone;
            default:
                return R.string.share;
        }
    }
}
