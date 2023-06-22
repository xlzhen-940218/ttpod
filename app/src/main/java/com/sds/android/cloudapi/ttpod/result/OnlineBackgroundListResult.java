package com.sds.android.cloudapi.ttpod.result;

import android.text.TextUtils;
import com.sds.android.cloudapi.ttpod.result.OnlineSkinListResult;

/* loaded from: classes.dex */
public class OnlineBackgroundListResult extends OnlineSkinListResult {
    @Override // com.sds.android.cloudapi.ttpod.result.OnlineSkinListResult
    public String getMainUrl() {
        OnlineSkinListResult.OnlineThemeListExtra extra = getExtra();
        String picUrl = extra == null ? "" : extra.getPicUrl();
        return TextUtils.isEmpty(picUrl) ? "http://api.skin.ttpod.com/skin" : picUrl;
    }
}
