package com.sds.android.ttpod.fragment.main.findsong.singer;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.AlbumItem;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;

/* renamed from: com.sds.android.ttpod.fragment.main.findsong.singer.a */
/* loaded from: classes.dex */
public class SingerAlbumIntroduceView {

    /* renamed from: a */
    private View f5330a;

    /* renamed from: b */
    private AlbumItem f5331b;

    /* renamed from: c */
    private Context f5332c;

    public SingerAlbumIntroduceView(Context context, AlbumItem albumItem) {
        this.f5332c = context;
        this.f5331b = albumItem;
        this.f5330a = View.inflate(context, R.layout.singer_album_introduce, null);
        m5473c();
    }

    /* renamed from: c */
    private void m5473c() {
        ((TextView) this.f5330a.findViewById(R.id.album_lang)).setText(this.f5332c.getString(R.string.album_lang, this.f5331b.getLang()));
        ((TextView) this.f5330a.findViewById(R.id.album_publish_time)).setText(this.f5332c.getString(R.string.album_publish_time, this.f5331b.getPublishTime()));
        ((TextView) this.f5330a.findViewById(R.id.album_introduce)).setText(this.f5331b.getDesc());
        m5474b();
    }

    /* renamed from: a */
    public View m5475a() {
        return this.f5330a;
    }

    /* renamed from: b */
    public void m5474b() {
        ThemeManager.m3269a(this.f5330a, "BackgroundMaskColor");
        ThemeManager.m3269a(this.f5330a.findViewById(R.id.album_lang), ThemeElement.CARD_SUB_TEXT);
        ThemeManager.m3269a(this.f5330a.findViewById(R.id.album_publish_time), ThemeElement.CARD_SUB_TEXT);
        ThemeManager.m3269a(this.f5330a.findViewById(R.id.album_introduce), ThemeElement.CARD_TEXT);
    }
}
