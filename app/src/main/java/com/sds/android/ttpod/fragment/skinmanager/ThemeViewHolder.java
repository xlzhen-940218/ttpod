package com.sds.android.ttpod.fragment.skinmanager;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sds.android.ttpod.R;

/* renamed from: com.sds.android.ttpod.fragment.skinmanager.b */
/* loaded from: classes.dex */
public class ThemeViewHolder {

    /* renamed from: a */
    private ImageView f5519a;

    /* renamed from: b */
    private ImageView f5520b;

    /* renamed from: c */
    private ImageView f5521c;

    /* renamed from: d */
    private ProgressBar f5522d;

    /* renamed from: e */
    private TextView f5523e;

    /* renamed from: f */
    private TextView f5524f;

    /* renamed from: g */
    private View f5525g;

    /* renamed from: h */
    private View f5526h;

    /* renamed from: i */
    private View f5527i;

    /* renamed from: j */
    private View f5528j;

    public ThemeViewHolder(View view) {
        this.f5527i = view;
        this.f5519a = (ImageView) view.findViewById(R.id.imageview_check);
        this.f5520b = (ImageView) view.findViewById(R.id.imageview_thumb);
        try {
            this.f5520b.setImageResource(R.drawable.img_skin_default_thumbnail);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        this.f5521c = (ImageView) view.findViewById(R.id.image_new_skin);
        this.f5528j = view.findViewById(R.id.update_click_area);
        this.f5522d = (ProgressBar) view.findViewById(R.id.progressbar_skin_download);
        this.f5523e = (TextView) view.findViewById(R.id.skin_name);
        this.f5524f = (TextView) view.findViewById(R.id.download_hint);
        this.f5525g = view.findViewById(R.id.view_download_start_icon);
        this.f5526h = view.findViewById(R.id.download_area);
    }

    /* renamed from: a */
    public ImageView m5390a() {
        return this.f5519a;
    }

    /* renamed from: b */
    public ImageView m5389b() {
        return this.f5520b;
    }

    /* renamed from: c */
    public ProgressBar m5388c() {
        return this.f5522d;
    }

    /* renamed from: d */
    public TextView m5387d() {
        return this.f5523e;
    }

    /* renamed from: e */
    public TextView m5386e() {
        return this.f5524f;
    }

    /* renamed from: f */
    public View m5385f() {
        return this.f5525g;
    }

    /* renamed from: g */
    public View m5384g() {
        return this.f5526h;
    }

    /* renamed from: h */
    public View m5383h() {
        return this.f5527i;
    }

    /* renamed from: i */
    public ImageView m5382i() {
        return this.f5521c;
    }

    /* renamed from: j */
    public View m5381j() {
        return this.f5528j;
    }
}
