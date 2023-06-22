package com.voicedragon.musicclient.orm.social;

import android.content.Context;

/* loaded from: classes.dex */
public class SocialHelperAware {
    private Context mContext;
    private String mCurrentKey;
    private SocialHelper mSocialHelper;

    public SocialHelperAware(Context context, String uid) {
        this.mContext = context;
        this.mCurrentKey = uid;
        this.mSocialHelper = SocialHelper.getHelper(context, uid);
    }

    public SocialHelper getHelper(String uid) {
        if (!this.mCurrentKey.equals(uid)) {
            this.mSocialHelper.close();
            this.mCurrentKey = uid;
            this.mSocialHelper = SocialHelper.getHelper(this.mContext, uid);
        }
        return this.mSocialHelper;
    }

    public void release() {
        this.mSocialHelper.close();
        this.mSocialHelper = null;
    }
}
