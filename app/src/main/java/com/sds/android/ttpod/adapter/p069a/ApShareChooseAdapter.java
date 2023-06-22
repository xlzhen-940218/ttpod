package com.sds.android.ttpod.adapter.p069a;

import android.content.Context;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.ttpod.fragment.apshare.TransmittableMediaItem;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.sds.android.ttpod.adapter.a.b */
/* loaded from: classes.dex */
public class ApShareChooseAdapter extends ApShareBaseAdapter {

    /* renamed from: b */
    private HashMap<String, MediaItem> f3161b;

    /* renamed from: c */
    private InterfaceC0954a f3162c;

    /* renamed from: d */
    private boolean f3163d;

    /* compiled from: ApShareChooseAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.a.b$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0954a {
        void onChooseAmountChanged();
    }

    public ApShareChooseAdapter(Context context, InterfaceC0954a interfaceC0954a) {
        super(context);
        this.f3161b = new HashMap<>();
        this.f3163d = false;
        this.f3162c = interfaceC0954a;
    }

    @Override // com.sds.android.ttpod.adapter.p069a.ApShareBaseAdapter
    /* renamed from: b */
    protected int mo7641b() {
        return 1;
    }

    /* renamed from: c */
    public boolean m7654c() {
        return this.f3159a.size() <= this.f3161b.size();
    }

    /* renamed from: d */
    public int m7653d() {
        return this.f3161b.size();
    }

    /* renamed from: a */
    public void m7655a(boolean z) {
        if (z) {
            Iterator<TransmittableMediaItem> it = this.f3159a.iterator();
            while (it.hasNext()) {
                MediaItem m5772a = it.next().m5772a();
                if (!this.f3161b.containsKey(m5772a.getID())) {
                    this.f3161b.put(m5772a.getID(), m5772a);
                }
            }
        } else {
            this.f3161b.clear();
        }
        notifyDataSetChanged();
    }

    /* renamed from: d */
    public void m7652d(TransmittableMediaItem transmittableMediaItem) {
        MediaItem m5772a = transmittableMediaItem.m5772a();
        String id = m5772a.getID();
        if (this.f3161b.containsKey(id)) {
            this.f3161b.remove(id);
        } else {
            this.f3161b.put(id, m5772a);
        }
        notifyDataSetChanged();
    }

    /* renamed from: e */
    public ArrayList<MediaItem> m7651e() {
        ArrayList<MediaItem> arrayList = new ArrayList<>(this.f3161b.size());
        Iterator<TransmittableMediaItem> it = this.f3159a.iterator();
        while (it.hasNext()) {
            MediaItem m5772a = it.next().m5772a();
            if (this.f3161b.containsKey(m5772a.getID())) {
                arrayList.add(m5772a);
                if (m5772a.getSize() <= 0) {
                    m5772a.setSize(FileUtils.m8405g(m5772a.getLocalDataSource()));
                }
            }
        }
        return arrayList;
    }

    /* renamed from: f */
    public void m7650f() {
        this.f3163d = true;
        notifyDataSetChanged();
    }

    @Override // com.sds.android.ttpod.adapter.p069a.ApShareBaseAdapter
    /* renamed from: a */
    public void mo7656a(List<TransmittableMediaItem> list) {
        super.mo7656a(list);
        m7655a(true);
    }

    @Override // com.sds.android.ttpod.adapter.p069a.ApShareBaseAdapter
    /* renamed from: a */
    protected void mo7642a(ShareItemViewHolder shareItemViewHolder, TransmittableMediaItem transmittableMediaItem) {
        if (this.f3163d) {
            shareItemViewHolder.m7640a();
        }
        shareItemViewHolder.m7636a(transmittableMediaItem, this.f3161b.containsKey(transmittableMediaItem.m5772a().getID()));
    }

    @Override // com.sds.android.ttpod.adapter.p069a.ApShareBaseAdapter, com.sds.android.ttpod.adapter.p069a.ShareItemViewHolder.InterfaceC0961a
    /* renamed from: a */
    public void mo7629a(boolean z, TransmittableMediaItem transmittableMediaItem) {
        super.mo7629a(z, transmittableMediaItem);
        MediaItem m5772a = transmittableMediaItem.m5772a();
        if (z) {
            this.f3161b.put(m5772a.getID(), m5772a);
        } else {
            this.f3161b.remove(m5772a.getID());
        }
        this.f3162c.onChooseAmountChanged();
    }
}
