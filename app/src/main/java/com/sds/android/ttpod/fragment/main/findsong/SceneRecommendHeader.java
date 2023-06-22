package com.sds.android.ttpod.fragment.main.findsong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.result.OnlineMusicSubCategoryResult;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.RoundedImageView;

/* renamed from: com.sds.android.ttpod.fragment.main.findsong.e */
/* loaded from: classes.dex */
public class SceneRecommendHeader {

    /* renamed from: a */
    private Context f5303a;

    /* renamed from: b */
    private View f5304b;

    /* renamed from: c */
    private RoundedImageView f5305c;

    /* renamed from: d */
    private ImageView f5306d;

    /* renamed from: e */
    private TextView f5307e;

    /* renamed from: f */
    private IconTextView f5308f;

    /* renamed from: g */
    private IconTextView f5309g;

    /* renamed from: h */
    private IconTextView f5310h;

    /* renamed from: i */
    private TextView f5311i;

    /* renamed from: j */
    private TextView f5312j;

    /* renamed from: k */
    private View f5313k;

    /* renamed from: l */
    private View f5314l;

    public SceneRecommendHeader(Context context, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null, SongCategoryDetailHeader create fail");
        }
        this.f5303a = context;
        this.f5304b = layoutInflater.inflate(R.layout.song_category_detail_header, viewGroup, false);
        this.f5305c = (RoundedImageView) this.f5304b.findViewById(R.id.imageview_header);
        this.f5306d = (ImageView) this.f5304b.findViewById(R.id.imageview_header_play);
        this.f5307e = (TextView) this.f5304b.findViewById(R.id.textview_header_detail);
        this.f5311i = (TextView) this.f5304b.findViewById(R.id.text_next_page);
        this.f5312j = (TextView) this.f5304b.findViewById(R.id.text_download_all);
        this.f5308f = (IconTextView) this.f5304b.findViewById(R.id.imageview_next_page);
        this.f5309g = (IconTextView) this.f5304b.findViewById(R.id.imageview_download_all);
        this.f5310h = (IconTextView) this.f5304b.findViewById(R.id.icontext_arrow_right);
        this.f5313k = this.f5304b.findViewById(R.id.layout_detail_content);
        this.f5314l = this.f5304b.findViewById(R.id.layout_song_category_operation);
        m5483d();
    }

    /* renamed from: a */
    public View m5490a() {
        return this.f5304b;
    }

    /* renamed from: b */
    public void m5485b() {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.f5303a, R.anim.rotate);
        loadAnimation.setFillEnabled(true);
        loadAnimation.setFillAfter(true);
        this.f5308f.startAnimation(loadAnimation);
    }

    /* renamed from: a */
    public void m5488a(OnlineMusicSubCategoryResult.SubCategoryData subCategoryData) {
        if (subCategoryData != null) {
            m5486a(subCategoryData.getDetail(), subCategoryData.getLargePicUrl());
        }
    }

    /* renamed from: a */
    public void m5486a(String str, String str2) {
        if (StringUtils.m8346a(str)) {
            str = this.f5303a.getString(R.string.post_detail_header_tweet_default);
        }
        m5487a(str);
        int dimension = (int) this.f5303a.getResources().getDimension(R.dimen.song_category_detail_image_size);
        ImageCacheUtils.m4752a(this.f5305c, str2, dimension, dimension, (int) R.drawable.img_background_song_publish);
    }

    /* renamed from: c */
    public void m5484c() {
        this.f5308f.clearAnimation();
    }

    /* renamed from: a */
    public void m5489a(View.OnClickListener onClickListener) {
        this.f5306d.setOnClickListener(onClickListener);
        this.f5311i.setOnClickListener(onClickListener);
        this.f5312j.setOnClickListener(onClickListener);
        this.f5307e.setOnClickListener(onClickListener);
    }

    /* renamed from: a */
    public void m5487a(CharSequence charSequence) {
        this.f5307e.setText(charSequence);
    }

    /* renamed from: d */
    public void m5483d() {
        ThemeManager.m3269a(this.f5313k, ThemeElement.TILE_MASK);
        ThemeManager.m3269a(this.f5314l, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        ThemeManager.m3269a(this.f5307e, ThemeElement.COMMON_TITLE_TEXT);
        ThemeManager.m3269a(this.f5304b.findViewById(R.id.scene_recommend_divider), ThemeElement.COMMON_SEPARATOR);
        String str = ThemeElement.SONG_LIST_ITEM_TEXT;
        String str2 = ThemeElement.SONG_LIST_ITEM_SUB_TEXT;
        ThemeUtils.m8172a(this.f5308f, str, (int) R.string.icon_next_page, str);
        ThemeUtils.m8172a(this.f5309g, str, (int) R.string.icon_post_header_download, str);
        ThemeUtils.m8172a(this.f5310h, str2, (int) R.string.icon_arrow_right, str2);
        ThemeManager.m3269a(this.f5311i, str2);
        ThemeManager.m3269a(this.f5312j, str2);
    }
}
