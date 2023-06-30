package com.sds.android.ttpod.adapter.p073e.p074a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.SystemNotice;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.BaseListAdapter;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.utils.TimeUtils;
import java.util.List;

/* renamed from: com.sds.android.ttpod.adapter.e.a.f */
/* loaded from: classes.dex */
public class SystemMessageAdapter extends BaseListAdapter<SystemNotice> {
    public SystemMessageAdapter(Context context, List<SystemNotice> list) {
        super(context, list);
    }

    @Override // com.sds.android.ttpod.adapter.BaseListAdapter
    /* renamed from: a */
    protected View getConvertView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_system_message_item, (ViewGroup) null, false);
        inflate.setTag(new C0985a(inflate));
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.adapter.BaseListAdapter
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void buildDataUI(View view, SystemNotice systemNotice, int i) {
        C0985a c0985a = (C0985a) view.getTag();
        c0985a.f3311b.setText(TimeUtils.m8157a(systemNotice.getTimeStamp()));
        c0985a.f3312c.setText(systemNotice.getTitle());
        c0985a.f3313d.setText(systemNotice.getMessage());
        ImageCacheUtils.displayImage(c0985a.f3314e, systemNotice.getPicture(), c0985a.f3314e.getWidth(), c0985a.f3314e.getHeight(), (int) R.drawable.img_avatar_default);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: SystemMessageAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.e.a.f$a */
    /* loaded from: classes.dex */
    public class C0985a {

        /* renamed from: b */
        private TextView f3311b;

        /* renamed from: c */
        private TextView f3312c;

        /* renamed from: d */
        private TextView f3313d;

        /* renamed from: e */
        private ImageView f3314e;

        public C0985a(View view) {
            this.f3311b = (TextView) view.findViewById(R.id.message_time);
            this.f3312c = (TextView) view.findViewById(R.id.message_title);
            this.f3313d = (TextView) view.findViewById(R.id.message_content);
            this.f3314e = (ImageView) view.findViewById(R.id.message_pic);
        }
    }
}
