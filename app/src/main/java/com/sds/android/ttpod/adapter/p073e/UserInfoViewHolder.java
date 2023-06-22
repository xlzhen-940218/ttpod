package com.sds.android.ttpod.adapter.p073e;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.widget.UserAvatarView;

/* renamed from: com.sds.android.ttpod.adapter.e.h */
/* loaded from: classes.dex */
public class UserInfoViewHolder {

    /* renamed from: a */
    private UserAvatarView f3369a;

    /* renamed from: b */
    private TextView f3370b;

    /* renamed from: c */
    private TextView f3371c;

    /* renamed from: d */
    private TextView f3372d;

    /* renamed from: e */
    private Button f3373e;

    public UserInfoViewHolder(View view) {
        this.f3369a = (UserAvatarView) view.findViewById(R.id.user_avatar);
        this.f3370b = (TextView) view.findViewById(R.id.user_name);
        this.f3371c = (TextView) view.findViewById(R.id.user_extra_info_1);
        this.f3372d = (TextView) view.findViewById(R.id.user_extra_info_2);
        this.f3373e = (Button) view.findViewById(R.id.follow_button);
    }

    /* renamed from: a */
    public UserAvatarView m7436a() {
        return this.f3369a;
    }

    /* renamed from: b */
    public TextView m7435b() {
        return this.f3370b;
    }

    /* renamed from: c */
    public TextView m7434c() {
        return this.f3371c;
    }

    /* renamed from: d */
    public TextView m7433d() {
        return this.f3372d;
    }

    /* renamed from: e */
    public Button m7432e() {
        return this.f3373e;
    }
}
