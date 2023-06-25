package com.sds.android.ttpod.adapter.p069a;

import android.content.Context;
import android.os.SystemClock;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.component.apshare.ApShareUtils;
import com.sds.android.ttpod.component.apshare.DataTransferListener;
import com.sds.android.ttpod.fragment.apshare.TransmittableMediaItem;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.adapter.a.d */
/* loaded from: classes.dex */
public class ApSharingAdapter extends ApShareBaseAdapter implements DataTransferListener {

    /* renamed from: b */
    private HashMap<Long, Velocity> f3172b;

    /* renamed from: c */
    private InterfaceC0957a f3173c;

    /* compiled from: ApSharingAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.a.d$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0957a {
        void onTransmitBegin(TransmittableMediaItem transmittableMediaItem);

        void onTransmitComplete(TransmittableMediaItem transmittableMediaItem);
    }

    public ApSharingAdapter(Context context) {
        super(context);
        this.f3172b = new HashMap<>();
        this.f3159a = new ArrayList<>();
    }

    @Override // com.sds.android.ttpod.adapter.p069a.ApShareBaseAdapter
    /* renamed from: a */
    protected void mo7642a(ShareItemViewHolder shareItemViewHolder, TransmittableMediaItem transmittableMediaItem) {
        shareItemViewHolder.m7637a(transmittableMediaItem);
    }

    @Override // com.sds.android.ttpod.adapter.p069a.ApShareBaseAdapter
    /* renamed from: b */
    protected int mo7641b() {
        return 2;
    }

    @Override // com.sds.android.ttpod.component.apshare.DataTransferListener
    /* renamed from: d */
    public void mo7107d(TransmittableMediaItem transmittableMediaItem) {
        if (transmittableMediaItem != null && transmittableMediaItem.m5772a() != null) {
            transmittableMediaItem.m5767a(transmittableMediaItem.m5772a().getID() + SystemClock.currentThreadTimeMillis());
            m7657c(transmittableMediaItem);
            this.f3173c.onTransmitBegin(transmittableMediaItem);
        }
    }

    @Override // com.sds.android.ttpod.component.apshare.DataTransferListener
    /* renamed from: a */
    public void mo7108a(TransmittableMediaItem transmittableMediaItem, long j) {
        ApShareUtils.m7114a(this.f3159a, transmittableMediaItem, j);
        notifyDataSetChanged();
    }

    @Override // com.sds.android.ttpod.component.apshare.DataTransferListener
    /* renamed from: e */
    public void mo7106e(TransmittableMediaItem transmittableMediaItem) {
        TransmittableMediaItem m7113a = ApShareUtils.m7113a(this.f3159a, transmittableMediaItem.m5772a().getID());
        if (m7113a != null) {
            m7113a.m5768a(TransmittableMediaItem.EnumC1381a.TRANSMIT_FINISHED);
            m7113a.m5771a(100);
            notifyDataSetChanged();
            this.f3173c.onTransmitComplete(m7113a);
        }
    }

    @Override // com.sds.android.ttpod.component.apshare.DataTransferListener
    /* renamed from: f */
    public void mo7105f(TransmittableMediaItem transmittableMediaItem) {
        TransmittableMediaItem m7113a = ApShareUtils.m7113a(this.f3159a, transmittableMediaItem.m5772a().getID());
        LogUtils.info("ApSharingAdapter", "[apshare]-transfer failed");
        if (m7113a != null) {
            m7113a.m5768a(TransmittableMediaItem.EnumC1381a.TRANSMIT_FAILED);
            notifyDataSetChanged();
        }
    }

    @Override // com.sds.android.ttpod.component.apshare.DataTransferListener
    /* renamed from: g */
    public void mo7104g(TransmittableMediaItem transmittableMediaItem) {
        TransmittableMediaItem m7113a = ApShareUtils.m7113a(this.f3159a, transmittableMediaItem.m5772a().getID());
        LogUtils.info("ApSharingAdapter", "[apshare]-transfer cancelled");
        if (m7113a != null) {
            m7113a.m5768a(TransmittableMediaItem.EnumC1381a.TRANSMIT_CANCELLED);
            notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public void m7643a(InterfaceC0957a interfaceC0957a) {
        this.f3173c = interfaceC0957a;
    }
}
