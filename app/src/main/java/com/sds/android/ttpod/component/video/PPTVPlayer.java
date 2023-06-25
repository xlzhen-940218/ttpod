package com.sds.android.ttpod.component.video;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.sds.android.sdk.lib.util.LogUtils;

/* renamed from: com.sds.android.ttpod.component.video.c */
/* loaded from: classes.dex */
public class PPTVPlayer extends BasePlayer {
    public PPTVPlayer(String str) {
        super(str);
    }

    public PPTVPlayer() {
    }

    @Override // com.sds.android.ttpod.component.video.BasePlayer
    /* renamed from: b */
    protected String mo5788b() {
        return "PPTVPlugin";
    }

    @Override // com.sds.android.ttpod.component.video.VideoPlayerInterface
    /* renamed from: c */
    public String mo5781c() {
        return "com.pplive.androidphone";
    }

    @Override // com.sds.android.ttpod.component.video.VideoPlayerInterface
    /* renamed from: a */
    public void mo5782a(Context context, String str, String str2) {
        LogUtils.error("PPTVPlayer", "playVideo url=" + str);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        intent.setComponent(new ComponentName("com.pplive.androidphone", "com.pplive.androidphone.ui.VideoPlayerFragmentActivity"));
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
