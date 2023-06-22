package com.sds.android.ttpod.activities.ktv;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p085b.MediaItemViewHolder;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.util.ArrayList;

/* renamed from: com.sds.android.ttpod.activities.ktv.d */
/* loaded from: classes.dex */
public class KtvMediaItemViewHolder extends MediaItemViewHolder implements View.OnClickListener {

    /* renamed from: a */
    private Button f2635a;

    /* renamed from: b */
    private Context f2636b;

    /* renamed from: c */
    private MediaItem f2637c;

    /* renamed from: d */
    private View f2638d;

    public KtvMediaItemViewHolder(View view) {
        super(view);
        this.f2636b = view.getContext();
        this.f2638d = view.findViewById(R.id.menu_view);
        this.f2635a = (Button) view.findViewById(R.id.button_ktv);
        this.f2635a.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m8122a(boolean z) {
        if (z) {
            this.f2635a.setVisibility(View.VISIBLE);
            this.f2638d.setVisibility(View.GONE);
            return;
        }
        this.f2635a.setVisibility(View.GONE);
        this.f2638d.setVisibility(View.VISIBLE);
    }

    @Override // com.sds.android.ttpod.component.p085b.MediaItemViewHolder
    /* renamed from: a */
    public void mo6972a(MediaItem mediaItem) {
        super.mo6972a(mediaItem);
        this.f2637c = mediaItem;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.f2637c != null && this.f2636b != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KtvSongInfo(this.f2637c.getTitle(), this.f2637c.getArtist()));
            KtvSongControl.m8118a().m8115a(this.f2636b, arrayList);
        }
    }
}
