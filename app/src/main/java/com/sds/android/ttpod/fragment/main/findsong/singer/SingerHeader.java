package com.sds.android.ttpod.fragment.main.findsong.singer;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.widget.RectangleImageView;
import com.sds.android.ttpod.widget.RoundedImageView;

/* renamed from: com.sds.android.ttpod.fragment.main.findsong.singer.b */
/* loaded from: classes.dex */
public class SingerHeader {

    /* renamed from: a */
    private View f5333a;

    /* renamed from: b */
    private RectangleImageView f5334b;

    /* renamed from: c */
    private RoundedImageView f5335c;

    /* renamed from: d */
    private TextView f5336d;

    /* renamed from: e */
    private ImageView f5337e;

    /* renamed from: f */
    private String f5338f;

    public SingerHeader(Context context, String str, String str2) {
        this.f5333a = View.inflate(context, R.layout.singer_detail_header, null);
        this.f5337e = (ImageView) this.f5333a.findViewById(R.id.album_play_button);
        this.f5337e.setVisibility(View.INVISIBLE);
        this.f5334b = (RectangleImageView) this.f5333a.findViewById(R.id.image_blur);
        this.f5334b.setAspectRatio(2.13f);
        this.f5335c = (RoundedImageView) this.f5333a.findViewById(R.id.image_singer_avatar);
        this.f5336d = (TextView) this.f5333a.findViewById(R.id.text_singer_name);
        this.f5336d.setText(str);
        this.f5338f = str2;
    }

    /* renamed from: a */
    public void m5472a() {
        if (this.f5338f != null) {
            int m7225c = DisplayUtils.getWidth();
            ImageCacheUtils.m4740b(this.f5334b, this.f5338f, m7225c, (int) (m7225c / 2.13f), R.drawable.img_background_ttpod_music_large_logo, 60);
            int m7229a = DisplayUtils.dp2px(64);
            ImageCacheUtils.m4752a(this.f5335c, this.f5338f, m7229a, m7229a, (int) R.drawable.img_avatar_default);
        }
    }

    /* renamed from: b */
    public View m5470b() {
        return this.f5333a;
    }

    /* renamed from: c */
    public ImageView m5469c() {
        return this.f5337e;
    }

    /* renamed from: a */
    public void m5471a(boolean z) {
        this.f5337e.setVisibility(z ? View.VISIBLE : View.INVISIBLE);
    }

    /* renamed from: d */
    public void m5468d() {
        ThemeManager.m3269a(this.f5336d, ThemeElement.TILE_TEXT);
    }
}
