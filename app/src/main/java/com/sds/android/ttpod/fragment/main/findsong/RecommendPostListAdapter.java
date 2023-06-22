package com.sds.android.ttpod.fragment.main.findsong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.RecommendPost;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.BaseListAdapter;
import com.sds.android.ttpod.framework.modules.p124f.MusiccircleContentType;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.utils.TimeUtils;
import java.util.ArrayList;

/* renamed from: com.sds.android.ttpod.fragment.main.findsong.c */
/* loaded from: classes.dex */
public abstract class RecommendPostListAdapter extends BaseListAdapter<RecommendPost> {
    /* renamed from: a */
    protected abstract void mo5504a(RecommendPost recommendPost, RecommendPostViewHolder recommendPostViewHolder, int i);

    public RecommendPostListAdapter(Context context) {
        super(context, new ArrayList());
    }

    @Override // com.sds.android.ttpod.adapter.BaseListAdapter
    /* renamed from: a */
    protected View mo5402a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.recommend_post_list_item, (ViewGroup) null, false);
        inflate.setTag(new RecommendPostViewHolder(inflate));
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.adapter.BaseListAdapter
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public final void mo5400a(View view, RecommendPost recommendPost, int i) {
        RecommendPostViewHolder recommendPostViewHolder = (RecommendPostViewHolder) view.getTag();
        m5503a(recommendPostViewHolder);
        recommendPostViewHolder.m5500a().setText(recommendPost.getAuthor());
        m5501a(recommendPostViewHolder, recommendPost.getListenCount());
        m5507a(recommendPostViewHolder.m5496e(), recommendPost);
        m5506a(recommendPostViewHolder.m5495f(), recommendPost.getReason());
        m5502a(recommendPostViewHolder, recommendPost, i);
        m5505a(recommendPost, recommendPostViewHolder.m5493h());
    }

    /* renamed from: a */
    private void m5501a(RecommendPostViewHolder recommendPostViewHolder, String str) {
        if (!StringUtils.m8346a(str)) {
            recommendPostViewHolder.m5498c().setVisibility(View.VISIBLE);
            recommendPostViewHolder.m5499b().setText(str);
            return;
        }
        recommendPostViewHolder.m5498c().setVisibility(View.GONE);
    }

    /* renamed from: a */
    private void m5507a(TextView textView, RecommendPost recommendPost) {
        long time = recommendPost.getTime();
        textView.setText(time > 0 ? TimeUtils.m8155a(m7664a(), time) : "");
    }

    /* renamed from: a */
    private void m5506a(TextView textView, String str) {
        if (!StringUtils.m8346a(str)) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(str);
            return;
        }
        textView.setVisibility(View.GONE);
    }

    /* renamed from: a */
    private void m5505a(RecommendPost recommendPost, ImageView imageView) {
        if (!StringUtils.m8346a(recommendPost.getPicUrl())) {
            ImageCacheUtils.m4752a(imageView, recommendPost.getPicUrl(), imageView.getWidth(), imageView.getHeight(), (int) R.drawable.img_musiccircle_post_pic_default);
        } else {
            imageView.setImageResource(R.drawable.img_musiccircle_post_pic_default);
        }
    }

    /* renamed from: a */
    public void m5503a(RecommendPostViewHolder recommendPostViewHolder) {
        ThemeManager.m3269a(recommendPostViewHolder.m5492i(), ThemeElement.CARD_BACKGROUND);
        ThemeManager.m3269a(recommendPostViewHolder.m5491j(), ThemeElement.CARD_CONTROL_BACKGROUND);
        ThemeManager.m3269a(recommendPostViewHolder.m5497d(), ThemeElement.CARD_TEXT);
        ThemeManager.m3269a(recommendPostViewHolder.m5495f(), ThemeElement.CARD_SUB_TEXT);
    }

    /* renamed from: a */
    private void m5502a(final RecommendPostViewHolder recommendPostViewHolder, final RecommendPost recommendPost, final int i) {
        String name;
        int type = recommendPost.getType();
        OnlineMediaItem mediaItem = recommendPost.getMediaItem();
        m5508a(recommendPostViewHolder.m5494g(), recommendPost);
        recommendPostViewHolder.m5494g().setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.c.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RecommendPostListAdapter.this.mo5504a(recommendPost, recommendPostViewHolder, i);
            }
        });
        if (type < MusiccircleContentType.DJ.value() && type > 0) {
            name = mediaItem != null ? mediaItem.getTitle() + " - " + mediaItem.getArtist() : "没有获取到信息";
        } else {
            name = recommendPost.getName();
        }
        recommendPostViewHolder.m5497d().setText(name);
    }

    /* renamed from: a */
    protected void m5508a(ImageView imageView, RecommendPost recommendPost) {
    }
}
