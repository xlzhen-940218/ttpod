package com.sds.android.ttpod.component.video;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.sds.android.sdk.lib.util.LogUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* renamed from: com.sds.android.ttpod.component.video.b */
/* loaded from: classes.dex */
public class PPSPlayer extends BasePlayer {
    @Override // com.sds.android.ttpod.component.video.BasePlayer
    /* renamed from: b */
    protected String mo5788b() {
        return "PPSPlugin";
    }

    public PPSPlayer() {
    }

    @Override // com.sds.android.ttpod.component.video.VideoPlayerInterface
    /* renamed from: c */
    public String mo5781c() {
        return "tv.pps.mobile";
    }

    public PPSPlayer(String str) {
        super(str);
    }

    @Override // com.sds.android.ttpod.component.video.VideoPlayerInterface
    /* renamed from: a */
    public void mo5782a(Context context, String str, String str2) {
        try {
            str = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            str2 = URLEncoder.encode(str2, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        Uri parse = Uri.parse(String.format("ppsplay://video?source=ttpod_app&detail_name=%s&isexit=1&third_url=ttpod://&his_time=0&play_url=%s", str2, str));
        LogUtils.m8381c("PPSPlayer", "playVideoWithPPS uri=" + parse);
        context.startActivity(new Intent("android.intent.action.VIEW", parse));
    }
}
