package com.sds.android.ttpod.fragment.main.findsong;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.ttpod.R;

/* renamed from: com.sds.android.ttpod.fragment.main.findsong.d */
/* loaded from: classes.dex */
public class RecommendPostViewHolder {

    /* renamed from: a */
    private TextView f5293a;

    /* renamed from: b */
    private TextView f5294b;

    /* renamed from: c */
    private View f5295c;

    /* renamed from: d */
    private TextView f5296d;

    /* renamed from: e */
    private TextView f5297e;

    /* renamed from: f */
    private TextView f5298f;

    /* renamed from: g */
    private ImageView f5299g;

    /* renamed from: h */
    private ImageView f5300h;

    /* renamed from: i */
    private View f5301i;

    /* renamed from: j */
    private View f5302j;

    public RecommendPostViewHolder(View view) {
        this.f5293a = (TextView) view.findViewById(R.id.tv_author);
        this.f5294b = (TextView) view.findViewById(R.id.tv_count);
        this.f5295c = view.findViewById(R.id.layout_count);
        this.f5296d = (TextView) view.findViewById(R.id.tv_title);
        this.f5297e = (TextView) view.findViewById(R.id.text_time);
        this.f5299g = (ImageView) view.findViewById(R.id.iv_play_pause);
        this.f5298f = (TextView) view.findViewById(R.id.text_tweet);
        this.f5300h = (ImageView) view.findViewById(R.id.pic);
        this.f5301i = view.findViewById(R.id.layout_discovery_list_item);
        this.f5302j = view.findViewById(R.id.layout_container);
    }

    /* renamed from: a */
    public TextView m5500a() {
        return this.f5293a;
    }

    /* renamed from: b */
    public TextView m5499b() {
        return this.f5294b;
    }

    /* renamed from: c */
    public View m5498c() {
        return this.f5295c;
    }

    /* renamed from: d */
    public TextView m5497d() {
        return this.f5296d;
    }

    /* renamed from: e */
    public TextView m5496e() {
        return this.f5297e;
    }

    /* renamed from: f */
    public TextView m5495f() {
        return this.f5298f;
    }

    /* renamed from: g */
    public ImageView m5494g() {
        return this.f5299g;
    }

    /* renamed from: h */
    public ImageView m5493h() {
        return this.f5300h;
    }

    /* renamed from: i */
    public View m5492i() {
        return this.f5302j;
    }

    /* renamed from: j */
    public View m5491j() {
        return this.f5301i;
    }
}
