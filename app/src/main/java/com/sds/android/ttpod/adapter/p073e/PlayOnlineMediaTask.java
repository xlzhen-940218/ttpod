package com.sds.android.ttpod.adapter.p073e;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.api.OnlineMediaItemAPI;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.BaseListAdapter;
import com.sds.android.ttpod.component.p086c.OnlinePlayingGroupUtils;
import com.sds.android.ttpod.framework.modules.p124f.PostUtils;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.framework.p106a.OnlineMediaUtils;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.sds.android.ttpod.adapter.e.d */
/* loaded from: classes.dex */
public class PlayOnlineMediaTask {

    /* renamed from: a */
    private List<MediaItem> f3320a;

    /* renamed from: b */
    private View f3321b;

    /* renamed from: c */
    private BaseListAdapter f3322c;

    /* renamed from: d */
    private OnlinePlayStatus f3323d;

    /* renamed from: e */
    private Context f3324e;

    public PlayOnlineMediaTask(Context context, View view, BaseListAdapter baseListAdapter, List<MediaItem> list, OnlinePlayStatus onlinePlayStatus) {
        this.f3320a = new ArrayList();
        this.f3324e = context;
        this.f3322c = baseListAdapter;
        this.f3321b = view;
        this.f3320a = list;
        this.f3323d = onlinePlayStatus;
    }

    /* renamed from: a */
    public void m7497a(final Post post) {
        TaskScheduler.m8582a(new TaskScheduler.AbstractAsyncTaskC0595a<List<Long>, OnlineMediaItemsResult>(PostUtils.m4025c(post)) { // from class: com.sds.android.ttpod.adapter.e.d.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public OnlineMediaItemsResult mo1981a(List<Long> list) {
                PlayOnlineMediaTask.this.f3321b.post(new Runnable() { // from class: com.sds.android.ttpod.adapter.e.d.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        PlayOnlineMediaTask.this.m7498a(PlayOnlineMediaTask.this.f3321b, post, PlayOnlineMediaTask.this.f3322c, PlayOnlineMediaTask.this.f3323d);
                    }
                });
                return OnlineMediaItemAPI.m8867a(list).execute();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
            /* renamed from: a */
            public void postExecute(OnlineMediaItemsResult onlineMediaItemsResult) {
                ArrayList<OnlineMediaItem> dataList = onlineMediaItemsResult.getDataList();
                PlayOnlineMediaTask.this.f3320a.clear();
                Iterator<OnlineMediaItem> it = dataList.iterator();
                while (it.hasNext()) {
                    PlayOnlineMediaTask.this.f3320a.add(MediaItemUtils.m4716a(it.next()));
                }
                PlayOnlineMediaTask.this.m7493b(post);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m7493b(Post post) {
        if (!this.f3320a.isEmpty()) {
            OnlineMediaUtils.m4679a(Cache.getInstance().getCurrentPlayMediaItem().getSongID().longValue(), this.f3320a, OnlinePlayingGroupUtils.m6916a(post));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m7498a(View view, Post post, BaseListAdapter baseListAdapter, OnlinePlayStatus onlinePlayStatus) {
        boolean z = false;
        if (m7491c(post)) {
            if (onlinePlayStatus == OnlinePlayStatus.LOADING) {
                view.setEnabled(false);
                view.setSelected(true);
                view.startAnimation(AnimationUtils.loadAnimation(this.f3324e, R.anim.unlimited_rotate));
                return;
            }
            view.setEnabled(true);
            view.clearAnimation();
            if (onlinePlayStatus == OnlinePlayStatus.PLAYING || (baseListAdapter.getData() != null && onlinePlayStatus == OnlinePlayStatus.STOP)) {
                z = true;
            }
            view.setSelected(z);
            return;
        }
        view.setEnabled(true);
        view.clearAnimation();
        view.setSelected(false);
    }

    /* renamed from: c */
    private boolean m7491c(Post post) {
        return OnlinePlayingGroupUtils.m6910a(OnlinePlayingGroupUtils.m6916a(post), post);
    }
}
