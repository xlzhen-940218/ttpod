package com.sds.android.ttpod.adapter.p073e;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.LabeledTTPodUser;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.BaseListAdapter;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.modules.p124f.MusiccircleContentType;
import com.sds.android.ttpod.framework.modules.p124f.PostUtils;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.widget.UserAvatarView;
import java.util.List;

/* renamed from: com.sds.android.ttpod.adapter.e.b */
/* loaded from: classes.dex */
public class FavoriteAdapter extends BaseListAdapter<Post> {
    public FavoriteAdapter(Context context, List<Post> list) {
        super(context, list);
    }

    @Override // com.sds.android.ttpod.adapter.BaseListAdapter
    /* renamed from: a */
    protected View getConvertView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_favorite_item, (ViewGroup) null, false);
        inflate.setTag(new C0986a((UserAvatarView) inflate.findViewById(R.id.image_avatar), (TextView) inflate.findViewById(R.id.tv_tweet), (TextView) inflate.findViewById(R.id.tv_user_name)));
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.adapter.BaseListAdapter
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void buildDataUI(View view, Post post, int i) {
        C0986a c0986a = (C0986a) view.getTag();
        LabeledTTPodUser user = PostUtils.m4029a(post).getUser();
        c0986a.f3318d.setText(user.getNickName());
        int m7229a = DisplayUtils.dp2px(48);
        ImageCacheUtils.m4752a(c0986a.f3316b, user.getAvatarUrl(), m7229a, m7229a, (int) R.drawable.img_avatar_default);
        String str = "";
        if (post.getType() == MusiccircleContentType.SONG_LIST.value() || post.getType() == MusiccircleContentType.DJ.value()) {
            str = post.getSongListName();
        } else {
            OnlineMediaItem mediaItem = post.getMediaItem();
            if (mediaItem != null) {
                str = mediaItem.getTitle();
            }
        }
        c0986a.f3317c.setText(str);
        c0986a.f3316b.setVFlagVisible(user.isVerified());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FavoriteAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.e.b$a */
    /* loaded from: classes.dex */
    public class C0986a {

        /* renamed from: b */
        private UserAvatarView f3316b;

        /* renamed from: c */
        private TextView f3317c;

        /* renamed from: d */
        private TextView f3318d;

        public C0986a(UserAvatarView userAvatarView, TextView textView, TextView textView2) {
            this.f3316b = userAvatarView;
            this.f3317c = textView;
            this.f3318d = textView2;
        }
    }
}
