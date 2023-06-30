package com.sds.android.ttpod.adapter.p073e.p074a;

import android.content.Context;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.LabeledTTPodUser;
import com.sds.android.cloudapi.ttpod.data.Notice;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.SubPostDetailFragment;
import com.sds.android.ttpod.activities.musiccircle.message.LookNoticeListener;
import com.sds.android.ttpod.activities.musiccircle.message.MessageClickableSpan;
import com.sds.android.ttpod.adapter.BaseListAdapter;
import com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment;
import com.sds.android.ttpod.framework.modules.p124f.MusiccircleContentType;
import com.sds.android.ttpod.framework.modules.p124f.PostUtils;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.utils.TimeUtils;
import com.sds.android.ttpod.widget.TextViewFixTouchConsume;
import java.util.List;

/* renamed from: com.sds.android.ttpod.adapter.e.a.c */
/* loaded from: classes.dex */
public class NoticeAdapter extends BaseListAdapter<Notice> {

    /* renamed from: a */
    private SubPostDetailFragment.InterfaceC0795a f3267a;

    /* renamed from: e */
    private WrapUserPostListFragment.InterfaceC1704a f3268e;

    /* renamed from: a */
    public void m7545a(SubPostDetailFragment.InterfaceC0795a interfaceC0795a) {
        this.f3267a = interfaceC0795a;
    }

    /* renamed from: a */
    public void m7543a(WrapUserPostListFragment.InterfaceC1704a interfaceC1704a) {
        this.f3268e = interfaceC1704a;
    }

    public NoticeAdapter(Context context, List<Notice> list) {
        super(context, list);
    }

    @Override // com.sds.android.ttpod.adapter.BaseListAdapter
    /* renamed from: a */
    protected View getConvertView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_notice_repost_item, (ViewGroup) null, false);
        inflate.setTag(new C0977a(inflate));
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sds.android.ttpod.adapter.BaseListAdapter
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void buildDataUI(View view, Notice notice, int i) {
        String songListName;
        C0977a c0977a = (C0977a) view.getTag();
        c0977a.f3274c.setVisibility(notice.getReadStatus() ? View.GONE : View.VISIBLE);
        c0977a.f3275d.setText(TimeUtils.m8155a(getContext(), notice.getTimeStamp()));
        Post post = notice.getPost();
        if (post != null) {
            final LabeledTTPodUser user = post.getUser();
            ImageCacheUtils.displayImage(c0977a.f3273b, user.getAvatarUrl(), c0977a.f3273b.getWidth(), c0977a.f3273b.getHeight(), (int) R.drawable.img_avatar_default);
            c0977a.f3276e.setCompoundDrawablesWithIntrinsicBounds(user.isVerified() ? R.drawable.img_user_v : 0, 0, 0, 0);
            c0977a.f3276e.setText(user.getNickName());
            View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.adapter.e.a.c.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    NoticeAdapter.this.f3268e.mo5428a(user);
                }
            };
            c0977a.f3276e.setOnClickListener(onClickListener);
            c0977a.f3273b.setOnClickListener(onClickListener);
        }
        String str = "";
        Post m4029a = PostUtils.m4029a(notice.getOriginPost());
        if (m4029a != null) {
            if (m4029a.getType() < MusiccircleContentType.DJ.value()) {
                songListName = m4029a.getMediaItem().getTitle();
            } else {
                songListName = m4029a.getSongListName();
            }
            int length = "分享了你的 ".length();
            SpannableString spannableString = new SpannableString("分享了你的 " + songListName);
            spannableString.setSpan(new MessageClickableSpan(notice, m4029a, new LookNoticeListener() { // from class: com.sds.android.ttpod.adapter.e.a.c.2
                @Override // com.sds.android.ttpod.activities.musiccircle.message.LookNoticeListener
                /* renamed from: a */
                public void mo7541a(Notice notice2, Post post2) {
                    NoticeAdapter.this.f3267a.mo7918a(post2);
                }
            }), length, songListName.length() + length, 33);
            str = spannableString.toString();
        }
        c0977a.f3277f.setText(str);
        c0977a.f3277f.setMovementMethod(TextViewFixTouchConsume.C2250a.m1445a());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: NoticeAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.e.a.c$a */
    /* loaded from: classes.dex */
    public class C0977a {

        /* renamed from: b */
        private ImageView f3273b;

        /* renamed from: c */
        private View f3274c;

        /* renamed from: d */
        private TextView f3275d;

        /* renamed from: e */
        private TextView f3276e;

        /* renamed from: f */
        private TextViewFixTouchConsume f3277f;

        public C0977a(View view) {
            this.f3273b = (ImageView) view.findViewById(R.id.image_avatar);
            this.f3274c = view.findViewById(R.id.iv_flag);
            this.f3275d = (TextView) view.findViewById(R.id.tv_time);
            this.f3276e = (TextView) view.findViewById(R.id.tv_user_name);
            this.f3277f = (TextViewFixTouchConsume) view.findViewById(R.id.tv_tweet);
        }
    }
}
