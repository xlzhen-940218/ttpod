package com.sds.android.ttpod.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.skin.view.AnimTransView;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeFramework;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.utils.ThemeUtils;

/* loaded from: classes.dex */
public class ArtistFrameView extends RelativeLayout {

    /* renamed from: a */
    private AnimTransView f7475a;

    /* renamed from: b */
    private ImageView f7476b;

    /* renamed from: c */
    private int f7477c;

    public ArtistFrameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.layout_artist_frame, this);
        this.f7475a = (AnimTransView) findViewById(R.id.imageview_playcontrolbar_artist);
        this.f7476b = (ImageView) findViewById(R.id.imageview_play_bar_artist_mask);
    }

    public AnimTransView getAnimTransView() {
        return this.f7475a;
    }

    public ImageView getMaskView() {
        return this.f7476b;
    }

    /* renamed from: a */
    public void m1904a() {
        ThemeFramework.AbstractC2016e m3258b = ThemeManager.m3258b(ThemeElement.PLAY_BAR_ARTIST);
        ThemeUtils.m8179a(this.f7475a, m3258b);
        this.f7477c = m3258b != null ? m3258b.getCornerRadius() : 0;
        ThemeUtils.m8180a(this.f7476b, ThemeManager.m3265a(ThemeElement.PLAY_BAR_ARTIS_MASK_IMAGE));
    }

    public int getCornerRadius() {
        return this.f7477c;
    }
}
