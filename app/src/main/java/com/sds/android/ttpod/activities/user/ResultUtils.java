package com.sds.android.ttpod.activities.user;

import android.content.Context;
import android.content.Intent;
import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;

/* renamed from: com.sds.android.ttpod.activities.user.d */
/* loaded from: classes.dex */
public class ResultUtils {
    /* renamed from: a */
    public static boolean m7706a(Context context, BaseResult baseResult) {
        if (baseResult.getCode() == 30301 || baseResult.getCode() == 30305) {
            PopupsUtils.m6760a((int) R.string.userinfo_access_token_invalid);
            Preferences.m3022a((User) null);
            context.startActivity(new Intent(context, LoginActivity.class));
            return true;
        }
        return false;
    }
}
