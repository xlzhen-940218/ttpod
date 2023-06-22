package com.sds.android.ttpod.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.MusicRank;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;

/* loaded from: classes.dex */
public class MusicRankSectionView extends SimpleSongView {
    public MusicRankSectionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MusicRankSectionView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.sds.android.ttpod.widget.SimpleSongView
    /* renamed from: b */
    protected void mo1456b(View view, Object obj) {
        MusicRank musicRank = (MusicRank) obj;
        ((TextView) view.findViewById(R.id.item_name)).setText(musicRank.getTitle());
        ImageCacheUtils.m4752a((ImageView) view.findViewById(R.id.item_picture), musicRank.getPicUrl(), DisplayUtils.m7225c() / 4, DisplayUtils.m7225c() / 4, (int) R.drawable.img_background_song_publish);
    }

    @Override // com.sds.android.ttpod.widget.SimpleSongView
    /* renamed from: a */
    protected int mo1457a() {
        return R.string.rank;
    }
}
