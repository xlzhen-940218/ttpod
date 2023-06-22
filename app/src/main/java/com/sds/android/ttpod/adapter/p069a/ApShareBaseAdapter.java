package com.sds.android.ttpod.adapter.p069a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.p069a.ShareItemViewHolder;
import com.sds.android.ttpod.fragment.apshare.TransmittableMediaItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.sds.android.ttpod.adapter.a.a */
/* loaded from: classes.dex */
public abstract class ApShareBaseAdapter extends BaseAdapter implements ShareItemViewHolder.InterfaceC0961a {

    /* renamed from: a */
    protected ArrayList<TransmittableMediaItem> f3159a = new ArrayList<>();

    /* renamed from: b */
    private Context f3160b;

    /* renamed from: a */
    protected abstract void mo7642a(ShareItemViewHolder shareItemViewHolder, TransmittableMediaItem transmittableMediaItem);

    /* renamed from: b */
    protected abstract int mo7641b();

    public ApShareBaseAdapter(Context context) {
        this.f3160b = context;
    }

    /* renamed from: a */
    public Context m7660a() {
        return this.f3160b;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    /* renamed from: a */
    public TransmittableMediaItem getItem(int i) {
        return this.f3159a.get(i);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f3159a.size();
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ShareItemViewHolder shareItemViewHolder;
        if (view == null) {
            view = LayoutInflater.from(this.f3160b).inflate(R.layout.apshare_media_list_item, (ViewGroup) null);
            shareItemViewHolder = new ShareItemViewHolder(view, mo7641b(), this);
            view.setTag(shareItemViewHolder);
        } else {
            shareItemViewHolder = (ShareItemViewHolder) view.getTag();
        }
        mo7642a(shareItemViewHolder, getItem(i));
        return view;
    }

    @Override // com.sds.android.ttpod.adapter.p069a.ShareItemViewHolder.InterfaceC0961a
    /* renamed from: a */
    public void mo7629a(boolean z, TransmittableMediaItem transmittableMediaItem) {
    }

    @Override // com.sds.android.ttpod.adapter.p069a.ShareItemViewHolder.InterfaceC0961a
    /* renamed from: a */
    public void mo7630a(TransmittableMediaItem transmittableMediaItem) {
    }

    /* renamed from: b */
    public void m7658b(TransmittableMediaItem transmittableMediaItem) {
        if (transmittableMediaItem != null) {
            Iterator<TransmittableMediaItem> it = this.f3159a.iterator();
            while (it.hasNext()) {
                if (transmittableMediaItem.m5766b().equals(it.next().m5766b())) {
                    return;
                }
            }
            this.f3159a.add(transmittableMediaItem);
            notifyDataSetChanged();
        }
    }

    /* renamed from: c */
    public void m7657c(TransmittableMediaItem transmittableMediaItem) {
        if (transmittableMediaItem != null) {
            Iterator<TransmittableMediaItem> it = this.f3159a.iterator();
            while (it.hasNext()) {
                if (transmittableMediaItem.m5766b().equals(it.next().m5766b())) {
                    return;
                }
            }
            this.f3159a.add(0, transmittableMediaItem);
            notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public void mo7656a(List<TransmittableMediaItem> list) {
        this.f3159a.clear();
        if (list != null) {
            for (TransmittableMediaItem transmittableMediaItem : list) {
                this.f3159a.add(transmittableMediaItem);
            }
        }
        notifyDataSetChanged();
    }
}
