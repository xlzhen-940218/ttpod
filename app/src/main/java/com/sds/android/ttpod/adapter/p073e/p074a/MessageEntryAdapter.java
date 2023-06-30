package com.sds.android.ttpod.adapter.p073e.p074a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.PrivateMessageOverView;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.BaseListAdapter;
import com.sds.android.ttpod.component.emoticons.EmoticonConversionUtil;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.utils.TimeUtils;
import com.sds.android.ttpod.widget.UserAvatarView;
import java.util.List;

/* renamed from: com.sds.android.ttpod.adapter.e.a.b */
/* loaded from: classes.dex */
public class MessageEntryAdapter extends BaseListAdapter<PrivateMessageOverView> {
    public MessageEntryAdapter(Context context, List<PrivateMessageOverView> list) {
        super(context, list);
    }

    @Override // com.sds.android.ttpod.adapter.BaseListAdapter
    /* renamed from: a */
    protected View getConvertView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_private_message_item, (ViewGroup) null, false);
        inflate.setTag(new C0974a(inflate));
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.adapter.BaseListAdapter
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void buildDataUI(View view, PrivateMessageOverView privateMessageOverView, int i) {
        C0974a c0974a = (C0974a) view.getTag();
        TTPodUser user = privateMessageOverView.getUser();
        if (user == null) {
            return;
        }
        ImageCacheUtils.displayImage(c0974a.f3262b, user.getAvatarUrl(), c0974a.f3262b.getWidth(), c0974a.f3262b.getHeight(), (int) R.drawable.img_avatar_default);
        c0974a.f3262b.setVFlagVisible(user.isVerified());
        c0974a.f3264d.setText(user.getNickName());
        c0974a.f3265e.setText(TimeUtils.m8157a(privateMessageOverView.getLastModified()));
        CharSequence m6641a = EmoticonConversionUtil.m6639b().m6641a(getContext(), privateMessageOverView.getLastMsg());
        if (m6641a == null) {
            m6641a = "";
        }
        c0974a.f3266f.setText(m6641a);
        int unreadCount = privateMessageOverView.getUnreadCount();
        if (unreadCount > 0) {
            c0974a.f3263c.setVisibility(View.VISIBLE);
            c0974a.f3263c.setText(Integer.toString(unreadCount));
        } else {
            c0974a.f3263c.setVisibility(View.GONE);
        }
        c0974a.f3262b.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.adapter.e.a.b.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MessageEntryAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.e.a.b$a */
    /* loaded from: classes.dex */
    public class C0974a {

        /* renamed from: b */
        private UserAvatarView f3262b;

        /* renamed from: c */
        private TextView f3263c;

        /* renamed from: d */
        private TextView f3264d;

        /* renamed from: e */
        private TextView f3265e;

        /* renamed from: f */
        private TextView f3266f;

        public C0974a(View view) {
            this.f3262b = (UserAvatarView) view.findViewById(R.id.image_avatar);
            this.f3263c = (TextView) view.findViewById(R.id.text_count);
            this.f3264d = (TextView) view.findViewById(R.id.artist_name);
            this.f3265e = (TextView) view.findViewById(R.id.text_datetime);
            this.f3266f = (TextView) view.findViewById(R.id.text_message);
        }
    }
}
