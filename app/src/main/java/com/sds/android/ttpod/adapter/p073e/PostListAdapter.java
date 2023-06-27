package com.sds.android.ttpod.adapter.p073e;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.LabeledTTPodUser;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.CommentsFragment;
import com.sds.android.ttpod.adapter.BaseListAdapter;
import com.sds.android.ttpod.component.p086c.OnlinePlayingGroupUtils;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.p124f.MusiccircleContentType;
import com.sds.android.ttpod.framework.modules.p124f.PostUtils;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.p106a.OnlineMediaUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.environment.PreferencesID;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.utils.ArtistUtils;
import com.sds.android.ttpod.utils.EntryUtils;
import com.sds.android.ttpod.utils.TimeUtils;
import com.sds.android.ttpod.widget.UserAvatarView;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sds.android.ttpod.adapter.e.e */
/* loaded from: classes.dex */
public abstract class PostListAdapter extends BaseListAdapter<Post> {

    /* renamed from: a */
    private List<MediaItem> f3328a;

    /* renamed from: e */
    private OnlinePlayStatus f3329e;

    /* renamed from: f */
    private long f3330f;

    /* renamed from: g */
    private String f3331g;

    /* renamed from: h */
    private String f3332h;

    /* renamed from: i */
    private CommentsFragment.InterfaceC0785b f3333i;

    /* renamed from: a  reason: avoid collision after fix types in other method */
    protected abstract void mo5432a(Post post);

    /* renamed from: a */
    protected abstract void mo5431a(Post post, boolean z);

    /* renamed from: a */
    protected abstract void mo5430a(TTPodUser tTPodUser);

    public PostListAdapter(Context context, List<Post> list, String str) {
        super(context, list);
        this.f3328a = new ArrayList();
        this.f3332h = str;
        this.f3329e = OnlinePlayStatus.from(SupportFactory.getInstance(BaseApplication.getApplication()).m2463m());
        Long songID = Cache.getInstance().getCurrentPlayMediaItem().getSongID();
        this.f3330f = songID == null ? 0L : songID.longValue();
        this.f3331g = Preferences.getOnlineMediaListGroupName();
        Preferences.m3023a(context, PreferencesID.ONLINE_MEDIA_LIST_GROUP_NAME, new Preferences.InterfaceC2031a() { // from class: com.sds.android.ttpod.adapter.e.e.1
            @Override // com.sds.android.ttpod.framework.storage.environment.Preferences.InterfaceC2031a
            /* renamed from: a */
            public void mo2553a(PreferencesID preferencesID) {
                PostListAdapter.this.f3331g = Preferences.getOnlineMediaListGroupName();
                PostListAdapter.this.f3330f = Cache.getInstance().getCurrentPlayMediaItem().getSongID().longValue();
            }
        });
    }

    /* renamed from: b */
    public void m7463b(List<Post> list) {
        this.dataList.addAll(list);
        notifyDataSetChanged();
    }

    /* renamed from: a */
    public void m7482a(CommentsFragment.InterfaceC0785b interfaceC0785b) {
        this.f3333i = interfaceC0785b;
    }

    /* renamed from: a */
    public void m7468a(Long l) {
        this.f3330f = l == null ? 0L : l.longValue();
        this.f3329e = OnlinePlayStatus.LOADING;
        notifyDataSetChanged();
    }

    /* renamed from: a */
    public void m7469a(PlayStatus playStatus) {
        this.f3329e = OnlinePlayStatus.from(playStatus);
        notifyDataSetChanged();
    }

    @Override // com.sds.android.ttpod.adapter.BaseListAdapter
    /* renamed from: a */
    protected View getConvertView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_post_item, (ViewGroup) null, false);
        inflate.setTag(new PostViewItemHolder(inflate));
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.adapter.BaseListAdapter
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public final void buildDataUI(View view, Post post, int i) {
        PostViewItemHolder postViewItemHolder = (PostViewItemHolder) view.getTag();
        m7475a(postViewItemHolder);
        m7470a(postViewItemHolder, post, PostUtils.m4029a(post), i);
    }

    /* renamed from: a */
    protected void m7470a(PostViewItemHolder postViewItemHolder, Post post, Post post2, int i) {
        m7474a(postViewItemHolder, post.getUser());
        long createTimeInSecond = post.getCreateTimeInSecond();
        postViewItemHolder.m7454d().setText(createTimeInSecond > 0 ? TimeUtils.m8155a(getContext(), createTimeInSecond) : "");
        String tweet = post.getTweet();
        if (TextUtils.isEmpty(tweet)) {
            if (postViewItemHolder.m7449i().getVisibility() != View.GONE) {
                postViewItemHolder.m7449i().setVisibility(View.GONE);
            }
        } else {
            postViewItemHolder.m7449i().setVisibility(View.VISIBLE);
            postViewItemHolder.m7449i().setText(tweet);
        }
        if (post != post2) {
            postViewItemHolder.m7453e().setVisibility(View.VISIBLE);
            LabeledTTPodUser user = post2.getUser();
            postViewItemHolder.m7453e().setText("来自 " + (user != null ? user.getNickName() : ""));
        } else {
            postViewItemHolder.m7453e().setVisibility(View.GONE);
        }
        m7472a(postViewItemHolder, post2, i);
        m7484a(post2, postViewItemHolder.m7445m());
        m7471a(postViewItemHolder, post, post2);
        m7473a(postViewItemHolder, post2);
        m7464b(postViewItemHolder, post2);
    }

    /* renamed from: a */
    private void m7484a(Post post, ImageView imageView) {
        ArrayList<String> picList = post.getPicList();
        if (picList != null && !picList.isEmpty()) {
            String str = picList.get(0);
            if (str != null) {
                ImageCacheUtils.m4752a(imageView, str, imageView.getWidth(), imageView.getHeight(), (int) R.drawable.img_musiccircle_post_pic_default);
            }
        } else if (post.getType() == MusiccircleContentType.SINGLE_SONG.value()) {
            ImageCacheUtils.m4752a(imageView, ArtistUtils.m8310a(post.getMediaItem().getArtistId()), imageView.getWidth(), imageView.getHeight(), (int) R.drawable.img_musiccircle_post_pic_default);
        } else {
            imageView.setImageResource(R.drawable.img_musiccircle_post_pic_default);
        }
    }

    /* renamed from: a */
    private void m7471a(PostViewItemHolder postViewItemHolder, final Post post, final Post post2) {
        postViewItemHolder.m7451g().setText(post2.getCommentCount() > 0 ? String.valueOf(post2.getCommentCount()) : "评论");
        postViewItemHolder.m7444n().setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.adapter.e.e.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PostListAdapter.this.m7483a(post, post2);
                PostListAdapter.this.mo5437b(post2);
            }
        });
    }

    /* renamed from: a */
    protected void m7483a(Post post, Post post2) {
        String str = this.f3332h + "_" + String.valueOf(post2.getTitleName());
        this.f3333i.mo5429a(post2.getId(), post2.getUser().getUserId());
    }

    /* renamed from: a */
    private void m7475a(PostViewItemHolder postViewItemHolder) {
        ThemeManager.m3269a(postViewItemHolder.m7452f(), ThemeElement.CARD_CONTROL_TEXT);
        ThemeManager.m3269a(postViewItemHolder.m7451g(), ThemeElement.CARD_CONTROL_TEXT);
        ThemeManager.m3269a(postViewItemHolder.m7450h(), ThemeElement.CARD_CONTROL_TEXT);
        ThemeManager.m3269a(postViewItemHolder.m7454d(), ThemeElement.CARD_SUB_TEXT);
        ThemeManager.m3269a(postViewItemHolder.m7453e(), ThemeElement.CARD_SUB_TEXT);
        ThemeManager.m3269a(postViewItemHolder.m7456b(), ThemeElement.CARD_TEXT);
        ThemeManager.m3269a(postViewItemHolder.m7449i(), ThemeElement.CARD_TEXT);
        ThemeManager.m3269a(postViewItemHolder.m7442p(), ThemeElement.CARD_CONTROL_BACKGROUND);
        ThemeManager.m3269a(postViewItemHolder.m7444n(), ThemeElement.CARD_CONTROL_BACKGROUND);
        ThemeManager.m3269a(postViewItemHolder.m7443o(), ThemeElement.CARD_CONTROL_BACKGROUND);
        ThemeManager.m3269a(postViewItemHolder.m7441q(), ThemeElement.CARD_CONTROL_BACKGROUND);
        ThemeManager.m3269a(postViewItemHolder.m7457a(), ThemeElement.COMMON_SUB_BAR);
        ThemeManager.m3269a(postViewItemHolder.m7440r(), ThemeElement.COMMON_SEPARATOR);
        ThemeManager.m3269a(postViewItemHolder.m7439s(), ThemeElement.COMMON_SEPARATOR);
        ThemeManager.m3269a(postViewItemHolder.m7438t(), ThemeElement.CARD_BACKGROUND);
    }

    /* renamed from: a */
    private void m7473a(PostViewItemHolder postViewItemHolder, final Post post) {
        postViewItemHolder.m7450h().setText(post.getRepostCount() > 0 ? String.valueOf(post.getRepostCount()) : "转发");
        postViewItemHolder.m7443o().setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.adapter.e.e.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Preferences.m2954aq() != null) {
                    PopupsUtils.m6759a((Activity) PostListAdapter.this.getContext(), post);
                    PostListAdapter.this.mo5436c(post);
                    return;
                }
                EntryUtils.m8297a(true);
            }
        });
    }

    /* renamed from: a */
    private void m7474a(PostViewItemHolder postViewItemHolder, final LabeledTTPodUser labeledTTPodUser) {
        TextView m7456b = postViewItemHolder.m7456b();
        m7456b.setText(labeledTTPodUser.getNickName());
        UserAvatarView m7448j = postViewItemHolder.m7448j();
        m7448j.setVFlagVisible(labeledTTPodUser.isVerified());
        ImageCacheUtils.m4752a(m7448j, labeledTTPodUser.getAvatarUrl(), m7448j.getWidth(), m7448j.getHeight(), (int) R.drawable.img_avatar_default);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.adapter.e.e.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Preferences.m2954aq() != null) {
                    PostListAdapter.this.mo5430a((TTPodUser) labeledTTPodUser);
                } else {
                    EntryUtils.m8297a(false);
                }
            }
        };
        postViewItemHolder.m7457a().setOnClickListener(onClickListener);
        m7456b.setOnClickListener(onClickListener);
        m7448j.setOnClickListener(onClickListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public void m7459g(Post post) {
        if (!this.f3328a.isEmpty()) {
            this.f3331g = OnlinePlayingGroupUtils.m6916a(post);
            OnlineMediaUtils.m4679a(this.f3330f, this.f3328a, this.f3331g);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public boolean m7458h(Post post) {
        return OnlinePlayingGroupUtils.m6910a(this.f3331g, post);
    }

    /* renamed from: a */
    private void m7472a(final PostViewItemHolder postViewItemHolder, final Post post, int i) {
        String songListName;
        int i2;
        boolean z = post.getType() < MusiccircleContentType.DJ.value();
        OnlineMediaItem mediaItem = post.getMediaItem();
        m7485a(postViewItemHolder.m7447k(), post, this.f3329e);
        postViewItemHolder.m7447k().setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.adapter.e.e.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!PostListAdapter.this.m7458h(post)) {
                    PostListAdapter.this.f3330f = 0L;
                    PostListAdapter.this.f3329e = OnlinePlayStatus.LOADING;
                    PostListAdapter.this.setData(post);
                    new PlayOnlineMediaTask(PostListAdapter.this.getContext(), postViewItemHolder.m7447k(), PostListAdapter.this, PostListAdapter.this.f3328a, PostListAdapter.this.f3329e).m7497a(post);
                } else {
                    PostListAdapter.this.f3328a = PostUtils.m4027b(post);
                    PostListAdapter.this.m7459g(post);
                }
                PostListAdapter.this.mo5432a(post);
            }
        });
        TextView m7455c = postViewItemHolder.m7455c();
        if (z) {
            songListName = mediaItem != null ? mediaItem.getTitle() + " - " + mediaItem.getArtist() : "没有获取到信息";
        } else {
            songListName = post.getSongListName();
        }
        m7455c.setText(songListName);
        ImageView m7446l = postViewItemHolder.m7446l();
        if (z) {
            i2 = R.drawable.img_musiccircle_song;
        } else {
            i2 = post.getType() == MusiccircleContentType.DJ.value() ? R.drawable.img_musiccircle_radio : R.drawable.img_musiccircle_songlist;
        }
        m7446l.setImageResource(i2);
    }

    /* renamed from: a */
    private void m7485a(View view, Post post, OnlinePlayStatus onlinePlayStatus) {
        boolean z = false;
        if (m7458h(post)) {
            if (onlinePlayStatus == OnlinePlayStatus.LOADING) {
                view.setEnabled(false);
                view.setSelected(true);
                view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.unlimited_rotate));
                return;
            }
            view.setEnabled(true);
            view.clearAnimation();
            if (onlinePlayStatus == OnlinePlayStatus.PLAYING || (getData() != null && onlinePlayStatus == OnlinePlayStatus.STOP)) {
                z = true;
            }
            view.setSelected(z);
            return;
        }
        view.setEnabled(true);
        view.clearAnimation();
        view.setSelected(false);
    }

    /* renamed from: b */
    private void m7464b(PostViewItemHolder postViewItemHolder, final Post post) {
        postViewItemHolder.m7452f().setText(post.getFavoriteCount() > 0 ? String.valueOf(post.getFavoriteCount()) : "收藏");
        final boolean booleanValue = ((Boolean) CommandCenter.getInstance().m4602a(new Command(CommandID.IS_FAVORITE_POST, Long.valueOf(post.getId())), Boolean.class)).booleanValue();
        postViewItemHolder.m7452f().setCompoundDrawablesWithIntrinsicBounds(booleanValue ? R.drawable.img_musiccircle_favorite_mark_yes : R.drawable.img_musiccircle_favorite_mark_no, 0, 0, 0);
        postViewItemHolder.m7442p().setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.adapter.e.e.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (EnvironmentUtils.DeviceConfig.isConnected() && Preferences.m2954aq() != null) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(Long.valueOf(post.getId()));
                    if (booleanValue) {
                        post.decreaseFavoriteCount();
                        CommandCenter.getInstance().execute(new Command(CommandID.REMOVE_FAVORITE_POSTS, arrayList, ""));
                    } else {
                        post.increaseFavoriteCount();
                        CommandCenter.getInstance().execute(new Command(CommandID.ADD_FAVORITE_POSTS, arrayList, ""));
                    }
                    PostListAdapter.this.mo5431a(post, booleanValue);
                    return;
                }
                EntryUtils.m8297a(true);
            }
        });
    }

    /* renamed from: b */
    protected void mo5437b(Post post) {
    }

    /* renamed from: c */
    protected void mo5436c(Post post) {
    }

    @Override // com.sds.android.ttpod.adapter.BaseListAdapter
    /* renamed from: d */
    public void setData(Post post) {
        super.setData(post);
    }

    /* renamed from: e */
    public void m7461e(Post post) {
        for (Post post2 : getDataList()) {
            Post m4029a = PostUtils.m4029a(post2);
            if (m4029a.getId() == post.getId()) {
                m4029a.setCommentCount(post.getCommentCount());
                return;
            }
        }
    }

    /* renamed from: f */
    public void m7460f(Post post) {
        for (Post post2 : getDataList()) {
            Post m4029a = PostUtils.m4029a(post2);
            if (m4029a.getId() == post.getId()) {
                m4029a.setRepostCount(post.getRepostCount());
                return;
            }
        }
    }
}
