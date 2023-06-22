package com.sds.android.ttpod.framework.modules;

import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.Extra;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.util.ArrayList;

/* renamed from: com.sds.android.ttpod.framework.modules.b */
/* loaded from: classes.dex */
public class MediaItemListResult extends BaseResult {

    /* renamed from: a */
    private Extra f5758a;

    /* renamed from: b */
    private ArrayList<MediaItem> f5759b;

    /* renamed from: a */
    public void m4516a(Extra extra) {
        this.f5758a = extra;
    }

    /* renamed from: a */
    public void m4515a(ArrayList<MediaItem> arrayList) {
        this.f5759b = arrayList;
    }

    /* renamed from: a */
    public ArrayList<MediaItem> m4517a() {
        return this.f5759b;
    }

    /* renamed from: b */
    public Extra m4514b() {
        return this.f5758a;
    }
}
