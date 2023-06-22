package com.sds.android.ttpod.adapter.p073e;

import android.view.View;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.widget.TextViewFixTouchConsume;
import com.sds.android.ttpod.widget.UserAvatarView;

/* renamed from: com.sds.android.ttpod.adapter.e.a */
/* loaded from: classes.dex */
public class CommentViewHolder {

    /* renamed from: a */
    public static final int f3244a = "回复 %1$s: %2$s".indexOf("%1$s");

    /* renamed from: b */
    private UserAvatarView f3245b;

    /* renamed from: c */
    private TextView f3246c;

    /* renamed from: d */
    private TextView f3247d;

    /* renamed from: e */
    private TextView f3248e;

    public CommentViewHolder(View view) {
        this.f3245b = (UserAvatarView) view.findViewById(R.id.image_avatar);
        this.f3246c = (TextView) view.findViewById(R.id.tv_user_name);
        this.f3247d = (TextView) view.findViewById(R.id.tv_tweet);
        this.f3248e = (TextView) view.findViewById(R.id.tv_time);
        this.f3247d.setMovementMethod(TextViewFixTouchConsume.C2250a.m1445a());
    }

    /* renamed from: a */
    public UserAvatarView m7566a() {
        return this.f3245b;
    }

    /* renamed from: b */
    public TextView m7565b() {
        return this.f3246c;
    }

    /* renamed from: c */
    public TextView m7564c() {
        return this.f3247d;
    }

    /* renamed from: d */
    public TextView m7563d() {
        return this.f3248e;
    }
}
