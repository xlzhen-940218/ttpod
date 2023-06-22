package com.sds.android.ttpod.activities.musiccircle;

import android.content.Intent;
import com.sds.android.cloudapi.ttpod.data.Notice;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.p055a.PostAPI;
import com.sds.android.cloudapi.ttpod.result.CommentResult;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.modules.p124f.PostUtils;
import com.sds.android.ttpod.framework.p106a.p107a.ErrorStatistic;
import com.sds.android.ttpod.framework.storage.environment.Preferences;

/* loaded from: classes.dex */
public class CommentInputActivity extends BaseInputActivity {
    private Notice mNotice;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.mNotice = (Notice) intent.getSerializableExtra("notice");
    }

    @Override // com.sds.android.ttpod.activities.musiccircle.BaseInputActivity
    protected void onSend(String str) {
        if (str.length() == 0) {
            PopupsUtils.m6721a("请输入有效评论");
            return;
        }
        Post m4029a = PostUtils.m4029a(this.mNotice.getOriginPost());
        TTPodUser m2954aq = Preferences.m2954aq();
        if (m2954aq != null) {
            final Request<CommentResult> m8849a = PostAPI.m8849a(m2954aq.getAccessToken(), m4029a.getPostId(), str, this.mNotice.getComment().getUser().getUserId(), this.mNotice.getComment().getId());
            m8849a.m8544a(new RequestCallback<CommentResult>() { // from class: com.sds.android.ttpod.activities.musiccircle.CommentInputActivity.1
                @Override // com.sds.android.sdk.lib.request.RequestCallback
                /* renamed from: a */
                public void onRequestSuccess(CommentResult commentResult) {
                    CommentInputActivity.this.onSendFinished();
                }

                @Override // com.sds.android.sdk.lib.request.RequestCallback
                /* renamed from: b */
                public void onRequestFailure(CommentResult commentResult) {
                    CommentInputActivity.this.onSendFail();
                    PopupsUtils.m6721a("评论失败");
                    ErrorStatistic.m5232g(m8849a.m8532e());
                }
            });
        }
    }

    @Override // com.sds.android.ttpod.activities.musiccircle.BaseInputActivity
    protected String onLoadPicUrl() {
        return getFirstPicInPost(PostUtils.m4029a(this.mNotice.getOriginPost()));
    }

    @Override // com.sds.android.ttpod.activities.musiccircle.BaseInputActivity
    protected TTPodUser onLoadUser() {
        return this.mNotice.getComment().getUser();
    }

    @Override // com.sds.android.ttpod.activities.musiccircle.BaseInputActivity
    protected String onLoadTweet() {
        return this.mNotice.getComment().getTweet();
    }
}
