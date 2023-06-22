package com.sds.android.ttpod.activities.musiccircle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.utils.EntryUtils;
import com.sds.android.ttpod.utils.ShareInfoConvertUtils;
import com.sds.android.ttpod.share.ShareType;
import com.sds.android.ttpod.share.p138c.ShareSelectDialog;

/* renamed from: com.sds.android.ttpod.activities.musiccircle.d */
/* loaded from: classes.dex */
public class SharePostSelectDialog extends ShareSelectDialog {

    /* renamed from: c */
    private Post f2798c;

    public SharePostSelectDialog(Activity activity, Post post) {
        super(activity, ShareInfoConvertUtils.m8239a(post, ""));
        if (post == null) {
            throw new IllegalArgumentException("post should not be null");
        }
        this.f2798c = post;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.share.p138c.ShareSelectDialog
    /* renamed from: b */
    public void mo2020b() {
        super.mo2020b();
        if (Preferences.m2954aq() == null) {
            EntryUtils.m8297a(true);
            return;
        }
        Context context = getContext();
        context.startActivity(new Intent(context, RepostInputActivity.class).putExtra("post", this.f2798c));
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }

    @Override // com.sds.android.ttpod.share.p138c.ShareSelectDialog
    /* renamed from: a */
    protected void mo2022a(ShareType shareType) {
        if (this.f2798c != null) {
            new SUserEvent("PAGE_CLICK", m2017b(shareType), SPage.PAGE_SHARE_DIALOG.getValue(), SPage.PAGE_NONE.getValue()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(this.f2798c.getSonglistId())).post();
        }
    }

    @Override // com.sds.android.ttpod.share.p138c.ShareSelectDialog
    /* renamed from: c */
    protected void mo2016c() {
        this.f7399b.put(ShareType.WECHAT, SAction.ACTION_SONG_LIST_SHARE_TO_WECHAT);
        this.f7399b.put(ShareType.WECHAT_FRIENDS, SAction.ACTION_SONG_LIST_SHARE_TO_WECHAT_FRIEND_CIRCLE);
        this.f7399b.put(ShareType.MUSIC_CYCLE, SAction.ACTION_SONG_LIST_SHARE_TO_MUSIC_CIRCLE);
        this.f7399b.put(ShareType.QQ, SAction.ACTION_SONG_LIST_SHARE_TO_QQ_FRIEND);
        this.f7399b.put(ShareType.QZONE, SAction.ACTION_SONG_LIST_SHARE_TO_QQ_ZONE);
        this.f7399b.put(ShareType.SINA_WEIBO, SAction.ACTION_SONG_LIST_SHARE_TO_SINA_WEIBO);
        this.f7399b.put(ShareType.QQ_WEIBO, SAction.ACTION_SONG_LIST_SHARE_TO_TENCENT_WEIBO);
        this.f7399b.put(ShareType.OTHER, SAction.ACTION_SONG_LIST_SHARE_TO_OTHER);
    }
}
