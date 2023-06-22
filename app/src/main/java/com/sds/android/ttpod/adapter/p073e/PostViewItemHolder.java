package com.sds.android.ttpod.adapter.p073e;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.widget.UserAvatarView;

/* renamed from: com.sds.android.ttpod.adapter.e.f */
/* loaded from: classes.dex */
public class PostViewItemHolder {

    /* renamed from: a */
    private TextView f3348a;

    /* renamed from: b */
    private TextView f3349b;

    /* renamed from: c */
    private TextView f3350c;

    /* renamed from: d */
    private TextView f3351d;

    /* renamed from: e */
    private TextView f3352e;

    /* renamed from: f */
    private TextView f3353f;

    /* renamed from: g */
    private TextView f3354g;

    /* renamed from: h */
    private TextView f3355h;

    /* renamed from: i */
    private UserAvatarView f3356i;

    /* renamed from: j */
    private ImageView f3357j;

    /* renamed from: k */
    private ImageView f3358k;

    /* renamed from: l */
    private ImageView f3359l;

    /* renamed from: m */
    private View f3360m;

    /* renamed from: n */
    private View f3361n;

    /* renamed from: o */
    private View f3362o;

    /* renamed from: p */
    private View f3363p;

    /* renamed from: q */
    private View f3364q;

    /* renamed from: r */
    private View f3365r;

    /* renamed from: s */
    private View f3366s;

    /* renamed from: t */
    private View f3367t;

    /* renamed from: u */
    private boolean f3368u = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PostViewItemHolder(View view) {
        this.f3348a = (TextView) view.findViewById(R.id.text_name);
        this.f3349b = (TextView) view.findViewById(R.id.tv_post_title);
        this.f3350c = (TextView) view.findViewById(R.id.text_time);
        this.f3351d = (TextView) view.findViewById(R.id.tv_from);
        this.f3352e = (TextView) view.findViewById(R.id.text_pick_amount);
        this.f3353f = (TextView) view.findViewById(R.id.tv_reply_amount);
        this.f3354g = (TextView) view.findViewById(R.id.tv_repost_amount);
        this.f3356i = (UserAvatarView) view.findViewById(R.id.image_avatar);
        this.f3358k = (ImageView) view.findViewById(R.id.iv_post_type);
        this.f3357j = (ImageView) view.findViewById(R.id.iv_playpause);
        this.f3355h = (TextView) view.findViewById(R.id.text_tweet);
        this.f3359l = (ImageView) view.findViewById(R.id.pic);
        this.f3362o = view.findViewById(R.id.layout_pick_amount);
        this.f3360m = view.findViewById(R.id.layout_reply_amount);
        this.f3361n = view.findViewById(R.id.layout_repost_amount);
        this.f3363p = view.findViewById(R.id.layout_userinfo);
        this.f3364q = view.findViewById(R.id.layout_amount);
        this.f3365r = view.findViewById(R.id.view_amount_divider_reply);
        this.f3366s = view.findViewById(R.id.view_amount_divider_repost);
        this.f3367t = view.findViewById(R.id.layout_social_post_item_root);
    }

    /* renamed from: a */
    public View m7457a() {
        return this.f3363p;
    }

    /* renamed from: b */
    public TextView m7456b() {
        return this.f3348a;
    }

    /* renamed from: c */
    public TextView m7455c() {
        return this.f3349b;
    }

    /* renamed from: d */
    public TextView m7454d() {
        return this.f3350c;
    }

    /* renamed from: e */
    public TextView m7453e() {
        return this.f3351d;
    }

    /* renamed from: f */
    public TextView m7452f() {
        return this.f3352e;
    }

    /* renamed from: g */
    public TextView m7451g() {
        return this.f3353f;
    }

    /* renamed from: h */
    public TextView m7450h() {
        return this.f3354g;
    }

    /* renamed from: i */
    public TextView m7449i() {
        return this.f3355h;
    }

    /* renamed from: j */
    public UserAvatarView m7448j() {
        return this.f3356i;
    }

    /* renamed from: k */
    public ImageView m7447k() {
        return this.f3357j;
    }

    /* renamed from: l */
    public ImageView m7446l() {
        return this.f3358k;
    }

    /* renamed from: m */
    public ImageView m7445m() {
        return this.f3359l;
    }

    /* renamed from: n */
    public View m7444n() {
        return this.f3360m;
    }

    /* renamed from: o */
    public View m7443o() {
        return this.f3361n;
    }

    /* renamed from: p */
    public View m7442p() {
        return this.f3362o;
    }

    /* renamed from: q */
    public View m7441q() {
        return this.f3364q;
    }

    /* renamed from: r */
    public View m7440r() {
        return this.f3365r;
    }

    /* renamed from: s */
    public View m7439s() {
        return this.f3366s;
    }

    /* renamed from: t */
    public View m7438t() {
        return this.f3367t;
    }
}
