package com.sds.android.ttpod.share.p136a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.share.ShareInfo;

import java.io.File;

/* renamed from: com.sds.android.ttpod.share.a.d */
/* loaded from: classes.dex */
public class OtherApi extends BaseApi {

    /* renamed from: a */
    private Context f7322a;

    public OtherApi(Context context) {
        this.f7322a = context;
        m2114a(Long.MAX_VALUE);
    }

    @Override // com.sds.android.ttpod.share.p136a.BaseApi
    /* renamed from: b */
    public ShareResult mo2076b(ShareInfo shareInfo, ApiCallback apiCallback) {
        m2108a(this.f7322a, "image/*", shareInfo.m1958e(), shareInfo.m1964c(), shareInfo.m1948j() ? this.f7322a.getString(R.string.forward_to) : this.f7322a.getString(R.string.share_to));
        return null;
    }

    /* renamed from: a */
    public void m2108a(Context context, String str, String str2, String str3, String str4) {
        Intent type = new Intent("android.intent.action.SEND").setType(str);
        String string = this.f7322a.getString(R.string.share);
        if (!StringUtils.isEmpty(str3)) {
            type.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(str3)));
        }
        type.putExtra("sms_body", str2);
        type.putExtra("android.intent.extra.SUBJECT", string);
        type.putExtra("android.intent.extra.TEXT", str2);
        type.setFlags(805306368);
        ((Activity) context).startActivityForResult(Intent.createChooser(type, str4), 1);
    }
}
