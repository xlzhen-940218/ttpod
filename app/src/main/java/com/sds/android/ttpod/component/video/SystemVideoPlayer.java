package com.sds.android.ttpod.component.video;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.sds.android.ttpod.activities.DefaultVideoActivity;

/* renamed from: com.sds.android.ttpod.component.video.e */
/* loaded from: classes.dex */
public class SystemVideoPlayer implements VideoPlayerInterface {
    @Override // com.sds.android.ttpod.component.video.VideoPlayerInterface
    /* renamed from: a */
    public void mo5782a(Context context, String str, String str2) {
        ((Activity) context).startActivity(new Intent(context, DefaultVideoActivity.class).putExtra("param_video_url", str).putExtra("param_video_title", str2));
    }

    @Override // com.sds.android.ttpod.component.video.VideoPlayerInterface
    /* renamed from: a */
    public void mo5783a() {
    }

    @Override // com.sds.android.ttpod.component.video.VideoPlayerInterface
    /* renamed from: c */
    public String mo5781c() {
        return "";
    }
}
