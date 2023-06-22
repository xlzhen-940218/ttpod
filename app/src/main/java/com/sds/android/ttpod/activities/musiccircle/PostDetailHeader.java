package com.sds.android.ttpod.activities.musiccircle;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.p124f.PostUtils;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.UserAvatarView;

/* renamed from: com.sds.android.ttpod.activities.musiccircle.c */
/* loaded from: classes.dex */
public class PostDetailHeader {

    /* renamed from: a */
    private Context f2777a;

    /* renamed from: b */
    private View f2778b;

    /* renamed from: c */
    private ImageView f2779c;

    /* renamed from: d */
    private TextView f2780d;

    /* renamed from: e */
    private TextView f2781e;

    /* renamed from: f */
    private IconTextView f2782f;

    /* renamed from: g */
    private IconTextView f2783g;

    /* renamed from: h */
    private IconTextView f2784h;

    /* renamed from: i */
    private IconTextView f2785i;

    /* renamed from: j */
    private TextView f2786j;

    /* renamed from: k */
    private TextView f2787k;

    /* renamed from: l */
    private TextView f2788l;

    /* renamed from: m */
    private TextView f2789m;

    /* renamed from: n */
    private IconTextView f2790n;

    /* renamed from: o */
    private UserAvatarView f2791o;

    /* renamed from: p */
    private View f2792p;

    /* renamed from: q */
    private View f2793q;

    /* renamed from: r */
    private RelativeLayout f2794r;

    /* renamed from: s */
    private RelativeLayout f2795s;

    /* renamed from: t */
    private ImageView f2796t;

    /* renamed from: u */
    private boolean f2797u;

    public PostDetailHeader(Context context, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        if (context == null) {
            throw new IllegalArgumentException("context and post must not be null");
        }
        this.f2777a = context;
        this.f2778b = layoutInflater.inflate(R.layout.musiccircle_post_detail_header, viewGroup, false);
        this.f2778b.setEnabled(false);
        this.f2778b.setOnClickListener(null);
        this.f2796t = (ImageView) this.f2778b.findViewById(R.id.post_header_picture);
        this.f2779c = (ImageView) this.f2778b.findViewById(R.id.post_header_play);
        this.f2780d = (TextView) this.f2778b.findViewById(R.id.post_header_download);
        this.f2781e = (TextView) this.f2778b.findViewById(R.id.post_header_share);
        this.f2782f = (IconTextView) this.f2778b.findViewById(R.id.post_head_favorite_icon_text);
        this.f2783g = (IconTextView) this.f2778b.findViewById(R.id.post_head_comment_icon_text);
        this.f2784h = (IconTextView) this.f2778b.findViewById(R.id.post_head_share_icon_text);
        this.f2785i = (IconTextView) this.f2778b.findViewById(R.id.post_head_download_icon_text);
        this.f2786j = (TextView) this.f2778b.findViewById(R.id.post_header_favorite);
        this.f2787k = (TextView) this.f2778b.findViewById(R.id.post_header_comment);
        this.f2788l = (TextView) this.f2778b.findViewById(R.id.post_header_user_name);
        this.f2789m = (TextView) this.f2778b.findViewById(R.id.post_header_tweet);
        this.f2790n = (IconTextView) this.f2778b.findViewById(R.id.post_header_right_arrow);
        this.f2791o = (UserAvatarView) this.f2778b.findViewById(R.id.post_header_user_avatar);
        this.f2792p = this.f2778b.findViewById(R.id.post_detail_header_operations);
        this.f2793q = this.f2778b.findViewById(R.id.post_header_background);
        this.f2794r = (RelativeLayout) this.f2778b.findViewById(R.id.post_header_user_click_bounds);
        this.f2795s = (RelativeLayout) this.f2778b.findViewById(R.id.post_header_tweet_bounds);
        mo7925b();
    }

    public PostDetailHeader() {
    }

    /* renamed from: a */
    public void m7930a(View.OnClickListener onClickListener) {
        this.f2780d.setOnClickListener(onClickListener);
        this.f2786j.setOnClickListener(onClickListener);
        this.f2781e.setOnClickListener(onClickListener);
        this.f2787k.setOnClickListener(onClickListener);
        this.f2779c.setOnClickListener(onClickListener);
        this.f2795s.setOnClickListener(onClickListener);
        this.f2794r.setOnClickListener(onClickListener);
    }

    /* renamed from: a */
    public void m7929a(Post post) {
        ImageCacheUtils.m4752a(this.f2796t, PostUtils.m4023d(post), this.f2796t.getWidth(), this.f2796t.getHeight(), (int) R.drawable.img_musiccircle_post_pic_default);
        if (!StringUtils.m8346a(post.getTweet())) {
            this.f2789m.setText(post.getTweet());
        }
        this.f2788l.setText(post.getUser().getNickName());
        if (post.getCommentCount() > 0) {
            this.f2787k.setText(post.getCommentCount() + "");
        }
        ImageCacheUtils.m4752a(this.f2791o, post.getUser().getAvatarUrl(), this.f2791o.getWidth(), this.f2791o.getHeight(), (int) R.drawable.img_avatar_default);
        m7927a(((Boolean) CommandCenter.m4607a().m4602a(new Command(CommandID.IS_FAVORITE_POST, Long.valueOf(post.getId())), Boolean.class)).booleanValue(), post.getFavoriteCount());
    }

    /* renamed from: a */
    public void m7927a(boolean z, int i) {
        this.f2797u = z;
        this.f2782f.setText(z ? R.string.icon_post_header_favorite_yes : R.string.icon_post_header_favorite_no);
        m7928a(this.f2797u);
        this.f2786j.setText(i > 0 ? String.valueOf(i) : this.f2777a.getResources().getString(R.string.favorite));
    }

    /* renamed from: a */
    public View m7931a() {
        return this.f2778b;
    }

    /* renamed from: b */
    public void mo7925b() {
        ThemeManager.m3269a(this.f2788l, ThemeElement.SONG_LIST_ITEM_TEXT);
        ThemeManager.m3269a(this.f2789m, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        ThemeManager.m3269a(this.f2778b.findViewById(R.id.post_header_info_divider), ThemeElement.COMMON_SEPARATOR);
        ThemeManager.m3269a(this.f2778b.findViewById(R.id.post_header_bottom_divider), ThemeElement.COMMON_SEPARATOR);
        ThemeManager.m3269a(this.f2792p, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        ThemeManager.m3269a(this.f2793q, ThemeElement.TILE_MASK);
        ThemeUtils.m8172a(this.f2790n, ThemeElement.SONG_LIST_ITEM_SUB_TEXT, (int) R.string.icon_arrow_right, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        m7926c();
    }

    /* renamed from: c */
    private void m7926c() {
        String str = ThemeElement.SONG_LIST_ITEM_TEXT;
        String str2 = ThemeElement.SONG_LIST_ITEM_SUB_TEXT;
        m7928a(this.f2797u);
        ThemeUtils.m8172a(this.f2783g, str, (int) R.string.icon_post_header_comments, str);
        ThemeUtils.m8172a(this.f2784h, str, (int) R.string.icon_post_header_share, str);
        ThemeUtils.m8172a(this.f2785i, str, (int) R.string.icon_post_header_download, str);
        ThemeManager.m3269a(this.f2786j, str2);
        ThemeManager.m3269a(this.f2787k, str2);
        ThemeManager.m3269a(this.f2781e, str2);
        ThemeManager.m3269a(this.f2780d, str2);
    }

    /* renamed from: a */
    private void m7928a(boolean z) {
        if (z) {
            this.f2782f.setTextColor(Color.parseColor("#FF617C"));
        } else {
            ThemeUtils.m8172a(this.f2782f, ThemeElement.SONG_LIST_ITEM_TEXT, (int) R.string.icon_post_header_favorite_no, ThemeElement.SONG_LIST_ITEM_TEXT);
        }
    }

    /* compiled from: PostDetailHeader.java */
    /* renamed from: com.sds.android.ttpod.activities.musiccircle.c$a */
    /* loaded from: classes.dex */
    public static class C0797a extends PostDetailHeader {
        @Override // com.sds.android.ttpod.activities.musiccircle.PostDetailHeader
        /* renamed from: b */
        public void mo7925b() {
        }
    }
}
