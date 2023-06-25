package com.sds.android.ttpod.adapter.p069a;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.apshare.ApShareUtils;
import com.sds.android.ttpod.component.apshare.DataTransferListener;
import com.sds.android.ttpod.component.p087d.p088a.MessageDialog;
import com.sds.android.ttpod.fragment.apshare.ApShareReceiveFragment;
import com.sds.android.ttpod.fragment.apshare.TransmittableMediaItem;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.sds.android.ttpod.adapter.a.c */
/* loaded from: classes.dex */
public class ApShareReceiveAdapter extends ApShareBaseAdapter implements ShareItemViewHolder.InterfaceC0961a, DataTransferListener {

    /* renamed from: b */
    private Handler f3164b;

    /* renamed from: c */
    private TransmittableMediaItem.EnumC1381a f3165c;

    /* renamed from: d */
    private List<TransmittableMediaItem> f3166d;

    /* renamed from: e */
    private TransmittableMediaItem f3167e;

    /* renamed from: f */
    private long f3168f;

    public ApShareReceiveAdapter(Context context, Handler handler) {
        super(context);
        this.f3165c = TransmittableMediaItem.EnumC1381a.TRANSMIT_IDLE;
        this.f3166d = new ArrayList();
        this.f3164b = handler;
    }

    @Override // com.sds.android.ttpod.adapter.p069a.ApShareBaseAdapter
    /* renamed from: a */
    protected void mo7642a(ShareItemViewHolder shareItemViewHolder, TransmittableMediaItem transmittableMediaItem) {
        shareItemViewHolder.m7634b(transmittableMediaItem);
    }

    @Override // com.sds.android.ttpod.adapter.p069a.ApShareBaseAdapter
    /* renamed from: b */
    protected int mo7641b() {
        return 3;
    }

    @Override // com.sds.android.ttpod.adapter.p069a.ApShareBaseAdapter, com.sds.android.ttpod.adapter.p069a.ShareItemViewHolder.InterfaceC0961a
    /* renamed from: a */
    public void mo7630a(final TransmittableMediaItem transmittableMediaItem) {
        LogUtils.info("ApShareReceiveAdapter", "onActionClicked size=%d title=%s state=%s", Integer.valueOf(this.f3166d.size()), transmittableMediaItem.m5772a().getTitle(), transmittableMediaItem.m5757h().toString());
        switch (transmittableMediaItem.m5757h()) {
            case TRANSMIT_IDLE:
                this.f3166d.add(transmittableMediaItem);
                transmittableMediaItem.m5768a(TransmittableMediaItem.EnumC1381a.TRANSMITTING);
                break;
            case TRANSMITTING:
            case WAITING:
                transmittableMediaItem.m5768a(TransmittableMediaItem.EnumC1381a.TRANSMIT_IDLE);
                if (this.f3167e == transmittableMediaItem) {
                    m7645h(transmittableMediaItem);
                    this.f3167e = null;
                    this.f3165c = TransmittableMediaItem.EnumC1381a.TRANSMIT_IDLE;
                }
                FileUtils.m8404h(transmittableMediaItem.m5772a().getLocalDataSource());
                break;
            case TRANSMIT_FINISHED:
                MessageDialog messageDialog = new MessageDialog(m7660a(), m7660a().getString(R.string.remove_confirm, FileUtils.getFilename(transmittableMediaItem.m5772a().getLocalDataSource())), new BaseDialog.InterfaceC1064a<MessageDialog>() { // from class: com.sds.android.ttpod.adapter.a.c.1
                    @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                    /* renamed from: a  reason: avoid collision after fix types in other method */
                    public void mo2038a(MessageDialog messageDialog2) {
                        transmittableMediaItem.m5768a(TransmittableMediaItem.EnumC1381a.TRANSMIT_IDLE);
                        FileUtils.m8404h(transmittableMediaItem.m5772a().getLocalDataSource());
                        CommandCenter.getInstance().m4596b(new Command(CommandID.DELETE_MEDIA_ITEM, MediaStorage.GROUP_ID_RECENTLY_ADD, transmittableMediaItem.m5772a(), true));
                        CommandCenter.getInstance().m4596b(new Command(CommandID.DELETE_MEDIA_ITEM, MediaStorage.GROUP_ID_ALL_LOCAL, transmittableMediaItem.m5772a(), true));
                        transmittableMediaItem.m5771a(0);
                        ApShareReceiveAdapter.this.notifyDataSetChanged();
                    }
                }, (BaseDialog.InterfaceC1064a<MessageDialog>) null);
                messageDialog.setTitle(R.string.prompt_title);
                messageDialog.show();
                break;
            case TRANSMIT_FAILED:
                this.f3166d.add(transmittableMediaItem);
                transmittableMediaItem.m5768a(TransmittableMediaItem.EnumC1381a.TRANSMITTING);
                break;
        }
        m7646e();
    }

    /* renamed from: h */
    private void m7645h(TransmittableMediaItem transmittableMediaItem) {
        this.f3164b.sendMessage(this.f3164b.obtainMessage(ApShareReceiveFragment.WHAT_DOWNLOAD_CANCELED));
        this.f3165c = TransmittableMediaItem.EnumC1381a.TRANSMIT_IDLE;
    }

    /* renamed from: e */
    private void m7646e() {
        LogUtils.info("ApShareReceiveAdapter", "state = " + this.f3165c + ", size =" + this.f3166d.size());
        if (this.f3164b != null && this.f3165c == TransmittableMediaItem.EnumC1381a.TRANSMIT_IDLE && this.f3166d.size() > 0) {
            this.f3167e = this.f3166d.remove(0);
            this.f3164b.sendMessage(m7649a(100, this.f3167e));
        }
    }

    @Override // com.sds.android.ttpod.component.apshare.DataTransferListener
    /* renamed from: d */
    public void mo7107d(TransmittableMediaItem transmittableMediaItem) {
        if (transmittableMediaItem != null && transmittableMediaItem.m5772a() != null) {
            transmittableMediaItem.m5767a(transmittableMediaItem.m5772a().getID());
            transmittableMediaItem.m5770a(SystemClock.currentThreadTimeMillis());
            m7658b(transmittableMediaItem);
        }
        this.f3168f = SystemClock.elapsedRealtime();
    }

    @Override // com.sds.android.ttpod.component.apshare.DataTransferListener
    /* renamed from: a */
    public void mo7108a(TransmittableMediaItem transmittableMediaItem, long j) {
        this.f3165c = TransmittableMediaItem.EnumC1381a.TRANSMITTING;
        ApShareUtils.m7114a(this.f3159a, transmittableMediaItem, j);
        if (SystemClock.elapsedRealtime() - this.f3168f > 400) {
            notifyDataSetChanged();
            this.f3168f = SystemClock.elapsedRealtime();
        }
    }

    @Override // com.sds.android.ttpod.component.apshare.DataTransferListener
    /* renamed from: e */
    public void mo7106e(TransmittableMediaItem transmittableMediaItem) {
        TransmittableMediaItem m7113a = ApShareUtils.m7113a(this.f3159a, transmittableMediaItem.m5772a().getID());
        if (m7113a != null) {
            m7113a.m5768a(TransmittableMediaItem.EnumC1381a.TRANSMIT_FINISHED);
            m7113a.m5771a(100);
            notifyDataSetChanged();
            m7113a.m5769a((Velocity) null);
        }
        this.f3165c = TransmittableMediaItem.EnumC1381a.TRANSMIT_IDLE;
        m7646e();
    }

    @Override // com.sds.android.ttpod.component.apshare.DataTransferListener
    /* renamed from: f */
    public void mo7105f(TransmittableMediaItem transmittableMediaItem) {
    }

    @Override // com.sds.android.ttpod.component.apshare.DataTransferListener
    /* renamed from: g */
    public void mo7104g(TransmittableMediaItem transmittableMediaItem) {
        TransmittableMediaItem m7113a = ApShareUtils.m7113a(this.f3159a, transmittableMediaItem.m5772a().getID());
        if (m7113a != null) {
            m7113a.m5768a(TransmittableMediaItem.EnumC1381a.TRANSMIT_CANCELLED);
            m7113a.m5771a(0);
            notifyDataSetChanged();
            m7113a.m5769a((Velocity) null);
        }
        this.f3165c = TransmittableMediaItem.EnumC1381a.TRANSMIT_IDLE;
        m7646e();
    }

    /* renamed from: a */
    private Message m7649a(int i, Object obj) {
        if (this.f3164b != null) {
            Message obtainMessage = this.f3164b.obtainMessage();
            obtainMessage.what = i;
            obtainMessage.obj = obj;
            return obtainMessage;
        }
        return null;
    }

    /* renamed from: c */
    public void m7648c() {
        for (TransmittableMediaItem transmittableMediaItem : this.f3166d) {
            transmittableMediaItem.m5768a(TransmittableMediaItem.EnumC1381a.TRANSMIT_IDLE);
        }
        this.f3166d.clear();
        if (this.f3167e != null) {
            m7645h(this.f3167e);
            this.f3167e = null;
        }
        notifyDataSetChanged();
    }

    /* renamed from: d */
    public void m7647d() {
        Iterator<TransmittableMediaItem> it = this.f3159a.iterator();
        while (it.hasNext()) {
            TransmittableMediaItem next = it.next();
            TransmittableMediaItem.EnumC1381a m5757h = next.m5757h();
            if (m5757h == TransmittableMediaItem.EnumC1381a.TRANSMIT_IDLE || m5757h == TransmittableMediaItem.EnumC1381a.TRANSMIT_FAILED) {
                this.f3166d.add(next);
                next.m5768a(TransmittableMediaItem.EnumC1381a.WAITING);
            }
        }
        notifyDataSetChanged();
        m7646e();
    }
}
