package com.sds.android.ttpod.adapter.p073e.p074a;

import android.content.Context;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.Comment;
import com.sds.android.cloudapi.ttpod.data.Notice;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.SubPostDetailFragment;
import com.sds.android.ttpod.activities.musiccircle.message.LookNoticeListener;
import com.sds.android.ttpod.activities.musiccircle.message.MessageClickableSpan;
import com.sds.android.ttpod.adapter.BaseListAdapter;
import com.sds.android.ttpod.component.emoticons.EmoticonConversionUtil;
import com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment;
import com.sds.android.ttpod.framework.modules.p124f.MusiccircleContentType;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.utils.TimeUtils;
import com.sds.android.ttpod.widget.TextViewFixTouchConsume;
import java.util.List;

/* renamed from: com.sds.android.ttpod.adapter.e.a.a */
/* loaded from: classes.dex */
public class CommentAdapter extends BaseListAdapter<Notice> {

    /* renamed from: a */
    private WrapUserPostListFragment.InterfaceC1704a f3249a;

    /* renamed from: e */
    private SubPostDetailFragment.InterfaceC0795a f3250e;

    /* renamed from: a */
    public void m7559a(WrapUserPostListFragment.InterfaceC1704a interfaceC1704a) {
        this.f3249a = interfaceC1704a;
    }

    /* renamed from: a */
    public void m7561a(SubPostDetailFragment.InterfaceC0795a interfaceC0795a) {
        this.f3250e = interfaceC0795a;
    }

    public CommentAdapter(Context context, List<Notice> list) {
        super(context, list);
    }

    @Override // com.sds.android.ttpod.adapter.BaseListAdapter
    /* renamed from: a */
    protected View mo5402a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_message_comment_item, (ViewGroup) null, false);
        inflate.setTag(new C0972a(inflate));
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.adapter.BaseListAdapter
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public final void mo5400a(View view, Notice notice, int i) {
        String songListName;
        String str;
        String str2;
        C0972a c0972a = (C0972a) view.getTag();
        Comment comment = notice.getComment();
        if (comment != null) {
            final TTPodUser user = comment.getUser();
            if (user != null) {
                ImageCacheUtils.m4752a(c0972a.f3255b, user.getAvatarUrl(), c0972a.f3255b.getWidth(), c0972a.f3255b.getHeight(), (int) R.drawable.img_avatar_default);
                c0972a.f3257d.setText(user.getNickName());
            }
            c0972a.f3256c.setText(TimeUtils.m8155a(m7664a(), comment.getCreateTimeInSecond()));
            View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.adapter.e.a.a.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (user != null) {
                        CommentAdapter.this.f3249a.mo5428a(user);
                    }
                }
            };
            c0972a.f3255b.setOnClickListener(onClickListener);
            c0972a.f3257d.setOnClickListener(onClickListener);
        }
        Comment originComment = notice.getOriginComment();
        EmoticonConversionUtil m6639b = EmoticonConversionUtil.m6639b();
        String tweet = (comment == null || comment.getUser() == null) ? null : comment.getTweet();
        if (!StringUtils.isEmpty(tweet)) {
            CharSequence m6641a = m6639b.m6641a(m7664a(), tweet);
            if (m6641a == null) {
                m6641a = "";
            }
            c0972a.f3258e.setText(m6641a);
            c0972a.f3258e.setTextColor(m7664a().getResources().getColor(R.color.post_text_tweet));
        } else {
            c0972a.f3258e.setText("抱歉，评论已被删除");
            c0972a.f3258e.setTextColor(m7664a().getResources().getColor(R.color.post_text_title));
        }
        Post originPost = notice.getOriginPost();
        if (originPost != null) {
            String str3 = "";
            if (originPost.getType() < MusiccircleContentType.DJ.value()) {
                OnlineMediaItem mediaItem = originPost.getMediaItem();
                if (mediaItem != null) {
                    str3 = mediaItem.getTitle();
                }
                songListName = str3;
            } else {
                songListName = originPost.getSongListName();
            }
            if (originComment == null) {
                str = "评论了 ";
                str2 = "评论了 " + songListName;
            } else {
                str = "在 ";
                str2 = "在 " + songListName + " 回复了我的评论: " + originComment.getTweet();
            }
            SpannableString spannableString = new SpannableString(str2);
            int length = str.length();
            spannableString.setSpan(new MessageClickableSpan(notice, originPost, new LookNoticeListener() { // from class: com.sds.android.ttpod.adapter.e.a.a.2
                @Override // com.sds.android.ttpod.activities.musiccircle.message.LookNoticeListener
                /* renamed from: a */
                public void mo7541a(Notice notice2, Post post) {
                    CommentAdapter.this.f3250e.mo7918a(post);
                }
            }), length, songListName.length() + length, 33);
            c0972a.f3259f.setText(m6639b.m6641a(m7664a(), spannableString));
            c0972a.f3259f.setMovementMethod(TextViewFixTouchConsume.C2250a.m1445a());
            return;
        }
        c0972a.f3259f.setText((CharSequence) null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CommentAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.e.a.a$a */
    /* loaded from: classes.dex */
    public class C0972a {

        /* renamed from: b */
        private ImageView f3255b;

        /* renamed from: c */
        private TextView f3256c;

        /* renamed from: d */
        private TextView f3257d;

        /* renamed from: e */
        private TextView f3258e;

        /* renamed from: f */
        private TextViewFixTouchConsume f3259f;

        public C0972a(View view) {
            View findViewById = view.findViewById(R.id.layout_avatar);
            findViewById.findViewById(R.id.iv_flag).setVisibility(View.GONE);
            this.f3255b = (ImageView) findViewById.findViewById(R.id.image_avatar);
            this.f3256c = (TextView) view.findViewById(R.id.tv_time);
            this.f3257d = (TextView) view.findViewById(R.id.tv_user_name);
            this.f3258e = (TextView) view.findViewById(R.id.tv_tweet);
            this.f3259f = (TextViewFixTouchConsume) view.findViewById(R.id.tv_content);
        }
    }
}
