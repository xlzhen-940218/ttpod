package com.sds.android.ttpod.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.MusicCircleFirstPublish;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;

/* loaded from: classes.dex */
public class SongPublishSectionView extends SimpleSongView {
    public SongPublishSectionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SongPublishSectionView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.sds.android.ttpod.widget.SimpleSongView
    /* renamed from: a */
    protected int mo1457a() {
        return R.string.new_song_publish;
    }

    @Override // com.sds.android.ttpod.widget.SimpleSongView
    /* renamed from: b */
    protected void mo1456b(View view, Object obj) {
        MusicCircleFirstPublish musicCircleFirstPublish = (MusicCircleFirstPublish) obj;
        TextView textView = (TextView) view.findViewById(R.id.item_name);
        ImageView imageView = (ImageView) view.findViewById(R.id.item_picture);
        View findViewById = view.findViewById(R.id.first_publish_flag);
        textView.setText(musicCircleFirstPublish.getTitle());
        textView.setVisibility(View.GONE);
        findViewById.setVisibility(musicCircleFirstPublish.isFirstPublish() ? View.VISIBLE : View.GONE);
        ImageCacheUtils.m4752a(imageView, musicCircleFirstPublish.getPicUrl(), DisplayUtils.getWidth() / 4, DisplayUtils.getWidth() / 4, (int) R.drawable.img_background_song_publish);
    }
}
