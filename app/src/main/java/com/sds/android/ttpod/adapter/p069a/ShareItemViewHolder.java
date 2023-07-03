package com.sds.android.ttpod.adapter.p069a;

import android.os.SystemClock;
import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.apshare.TransmittableMediaItem;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.widget.CheckImageView;

/* renamed from: com.sds.android.ttpod.adapter.a.e */
/* loaded from: classes.dex */
public final class ShareItemViewHolder {

    /* renamed from: a */
    private final ImageView f3174a;

    /* renamed from: b */
    private final CheckImageView f3175b;

    /* renamed from: c */
    private final TextView f3176c;

    /* renamed from: d */
    private final TextView f3177d;

    /* renamed from: e */
    private final TextView f3178e;

    /* renamed from: f */
    private final TextView f3179f;

    /* renamed from: g */
    private final TextView f3180g;

    /* renamed from: h */
    private final RelativeLayout f3181h;

    /* renamed from: i */
    private final ProgressBar f3182i;

    /* renamed from: j */
    private TransmittableMediaItem f3183j;

    /* renamed from: k */
    private InterfaceC0961a f3184k;

    /* compiled from: ShareItemViewHolder.java */
    /* renamed from: com.sds.android.ttpod.adapter.a.e$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0961a {
        /* renamed from: a */
        void mo7630a(TransmittableMediaItem transmittableMediaItem);

        /* renamed from: a */
        void mo7629a(boolean z, TransmittableMediaItem transmittableMediaItem);
    }

    public ShareItemViewHolder(View view, int i, InterfaceC0961a interfaceC0961a) {
        this.f3184k = interfaceC0961a;
        this.f3181h = (RelativeLayout) view.findViewById(R.id.media_list_item_layout);
        this.f3174a = (ImageView) view.findViewById(R.id.iv_icon);
        this.f3175b = (CheckImageView) view.findViewById(R.id.ck_choose);
        this.f3176c = (TextView) view.findViewById(R.id.tv_title);
        this.f3177d = (TextView) view.findViewById(R.id.tv_size);
        this.f3178e = (TextView) view.findViewById(R.id.tv_speed);
        this.f3179f = (TextView) view.findViewById(R.id.tv_receiver);
        this.f3180g = (TextView) view.findViewById(R.id.tv_action);
        this.f3182i = (ProgressBar) view.findViewById(R.id.progress_bar);
        this.f3182i.setMax(100);
        if (i == 1) {
            this.f3182i.setVisibility(View.GONE);
            this.f3178e.setVisibility(View.GONE);
            this.f3179f.setVisibility(View.GONE);
            this.f3180g.setVisibility(View.GONE);
            this.f3175b.setOnCheckedChangeListener(new CheckImageView.OnCheckedChangeListener() { // from class: com.sds.android.ttpod.adapter.a.e.1
                @Override // com.sds.android.ttpod.widget.CheckImageView.InterfaceC2165a
                /* renamed from: a */
                public void onChecked(CheckImageView checkImageView, boolean checked, boolean manual) {
                    if (ShareItemViewHolder.this.f3184k != null) {
                        ShareItemViewHolder.this.f3184k.mo7629a(checked, ShareItemViewHolder.this.f3183j);
                    }
                }
            });
        } else if (i == 2) {
            this.f3175b.setVisibility(View.GONE);
            this.f3180g.setVisibility(View.GONE);
        } else if (i == 3) {
            this.f3175b.setVisibility(View.GONE);
            this.f3179f.setVisibility(View.GONE);
            this.f3180g.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.adapter.a.e.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (ShareItemViewHolder.this.f3184k != null) {
                        ShareItemViewHolder.this.f3184k.mo7630a(ShareItemViewHolder.this.f3183j);
                        ShareItemViewHolder.this.m7631e(ShareItemViewHolder.this.f3183j);
                    }
                }
            });
        }
        this.f3175b.m1899a(R.drawable.img_checkbox_multiselect_unchecked, R.drawable.img_checkbox_multiselect_checked);
    }

    /* renamed from: a */
    public void m7640a() {
        this.f3175b.setEnabled(false);
    }

    /* renamed from: c */
    private void m7633c(TransmittableMediaItem transmittableMediaItem) {
        this.f3183j = transmittableMediaItem;
        MediaItem m5772a = transmittableMediaItem.m5772a();
        String localDataSource = m5772a.getLocalDataSource();
        this.f3176c.setText(FileUtils.getFilename(localDataSource));
        long size = m5772a.getSize();
        if (size <= 0) {
            size = FileUtils.m8405g(localDataSource);
            m5772a.setSize(size);
        }
        this.f3177d.setText(Formatter.formatFileSize(this.f3181h.getContext(), size));
    }

    /* renamed from: a */
    public void m7636a(TransmittableMediaItem transmittableMediaItem, boolean z) {
        m7633c(transmittableMediaItem);
        this.f3175b.setChecked(z);
    }

    /* renamed from: a */
    public void m7637a(TransmittableMediaItem transmittableMediaItem) {
        m7633c(transmittableMediaItem);
        if (transmittableMediaItem.m5759f() == null) {
            this.f3178e.setText("");
        } else {
            Velocity m5759f = transmittableMediaItem.m5759f();
            transmittableMediaItem.m5770a(SystemClock.currentThreadTimeMillis());
            this.f3178e.setText(m5759f.m7628a());
        }
        this.f3182i.setProgress(transmittableMediaItem.m5758g());
        if (transmittableMediaItem.m5758g() == 100) {
            this.f3178e.setText(R.string.share_send_complete);
        }
        if (transmittableMediaItem.m5757h() == TransmittableMediaItem.EnumC1381a.TRANSMIT_CANCELLED) {
            this.f3178e.setText(R.string.share_receive_cancelled);
        }
        if (transmittableMediaItem.m5757h() == TransmittableMediaItem.EnumC1381a.TRANSMIT_FAILED) {
            this.f3178e.setText(R.string.share_send_failed);
        }
        this.f3179f.setText("接收者：" + transmittableMediaItem.m5762d());
    }

    /* renamed from: b */
    public void m7634b(TransmittableMediaItem transmittableMediaItem) {
        m7633c(transmittableMediaItem);
        m7631e(transmittableMediaItem);
        this.f3182i.setProgress(transmittableMediaItem.m5758g());
    }

    /* renamed from: d */
    private void m7632d(TransmittableMediaItem transmittableMediaItem) {
        Velocity m5759f = transmittableMediaItem.m5759f();
        if (m5759f != null) {
            transmittableMediaItem.m5770a(SystemClock.currentThreadTimeMillis());
            this.f3178e.setText(m5759f.m7628a());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m7631e(TransmittableMediaItem transmittableMediaItem) {
        switch (transmittableMediaItem.m5757h()) {
            case TRANSMIT_IDLE:
                this.f3178e.setText("");
                this.f3180g.setText(R.string.share_receive);
                this.f3180g.setBackgroundResource(R.drawable.xml_background_apshare_receive);
                return;
            case WAITING:
                this.f3178e.setText("");
                this.f3180g.setText(R.string.share_cancel);
                this.f3180g.setBackgroundResource(R.drawable.xml_background_apshare_cancel);
                return;
            case TRANSMITTING:
                m7632d(transmittableMediaItem);
                this.f3180g.setText(R.string.share_cancel);
                this.f3180g.setBackgroundResource(R.drawable.xml_background_apshare_cancel);
                return;
            case TRANSMIT_FINISHED:
                this.f3178e.setText(R.string.share_receive_complete);
                this.f3182i.setProgress(100);
                this.f3180g.setText(R.string.delete);
                this.f3180g.setBackgroundResource(R.drawable.xml_background_apshare_delete);
                return;
            case TRANSMIT_FAILED:
                this.f3178e.setText(R.string.share_receive_failed);
                this.f3180g.setText(R.string.share_failed);
                this.f3180g.setBackgroundResource(R.drawable.xml_background_apshare_cancel);
                return;
            case TRANSMIT_CANCELLED:
                this.f3178e.setText(R.string.share_receive_cancelled);
                this.f3180g.setText(R.string.share_receive);
                this.f3180g.setBackgroundResource(R.drawable.xml_background_apshare_receive);
                transmittableMediaItem.m5768a(TransmittableMediaItem.EnumC1381a.TRANSMIT_IDLE);
                return;
            default:
                return;
        }
    }
}
