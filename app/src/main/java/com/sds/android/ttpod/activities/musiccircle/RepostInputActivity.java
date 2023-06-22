package com.sds.android.ttpod.activities.musiccircle;

import android.content.Intent;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public class RepostInputActivity extends BaseInputActivity {
    private Post mPost;

    @Override // com.sds.android.ttpod.activities.musiccircle.BaseInputActivity
    protected void onSend(String str) {
        CommandCenter.m4607a().m4606a(new Command(CommandID.RE_POST, Long.valueOf(this.mPost.getId()), Long.valueOf(this.mPost.getUser().getUserId()), str, ""));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_RE_POSTED, ReflectUtils.m8375a(getClass(), "onRepostFinished", BaseResult.class, String.class));
    }

    public void onRepostFinished(BaseResult baseResult, String str) {
        if (baseResult.isSuccess()) {
            onSendFinished();
            this.mPost.setRepostCount(this.mPost.getRepostCount() + 1);
            CommandCenter.m4607a().m4606a(new Command(CommandID.CHANGE_POST_REPOST_COUNT, this.mPost));
            return;
        }
        onSendFail();
        PopupsUtils.m6721a("转发失败");
    }

    @Override // com.sds.android.ttpod.activities.musiccircle.BaseInputActivity
    protected String onLoadPicUrl() {
        return getFirstPicInPost(this.mPost);
    }

    @Override // com.sds.android.ttpod.activities.musiccircle.BaseInputActivity
    protected TTPodUser onLoadUser() {
        return this.mPost.getUser();
    }

    @Override // com.sds.android.ttpod.activities.musiccircle.BaseInputActivity
    protected String onLoadTweet() {
        return this.mPost.getTweet();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent == null) {
            throw new IllegalArgumentException("invalid intent");
        }
        this.mPost = (Post) intent.getSerializableExtra("post");
    }
}
