package com.sds.android.ttpod.widget;

import android.content.Context;
import androidx.core.view.ViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.sds.android.cloudapi.ttpod.data.RecommendData;

import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.utils.FindSongConfig;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class PosterGallery extends RelativeLayout implements ThemeManager.InterfaceC2019b {

    /* renamed from: a */
    private ScrollableViewGroup f7850a;

    /* renamed from: b */
    private IconPageIndicator f7851b;

    /* renamed from: c */
    private InterfaceC2222a f7852c;

    /* renamed from: d */
    private ArrayList<RecommendData> f7853d;

    /* renamed from: com.sds.android.ttpod.widget.PosterGallery$a */
    /* loaded from: classes.dex */
    public interface InterfaceC2222a {
        /* renamed from: a */
        View.OnClickListener mo1583a(int i);
    }

    public PosterGallery(Context context) {
        this(context, null);
    }

    public PosterGallery(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PosterGallery(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7852c = null;
        LayoutInflater.from(context).inflate(R.layout.find_song_poser_gallery, (ViewGroup) this, true);
        this.f7850a = (ScrollableViewGroup) findViewById(R.id.layout_gallery);
        this.f7851b = (IconPageIndicator) findViewById(R.id.page_indicator);
        this.f7851b.setVisibility(View.VISIBLE);
        this.f7851b.m1724a(R.drawable.img_page_indicator, R.drawable.img_page_indicator_selected);
        this.f7850a.setOnCurrentViewChangedListener(new ScrollableViewGroup.InterfaceC2225a() { // from class: com.sds.android.ttpod.widget.PosterGallery.1
            @Override // com.sds.android.ttpod.widget.ScrollableViewGroup.InterfaceC2225a
            /* renamed from: a */
            public void mo1554a(View view, int i2) {
                PosterGallery.this.f7851b.m1725a(i2);
                if (PosterGallery.this.f7853d != null && i2 < PosterGallery.this.f7853d.size()) {
                   // FindSongNewStatistic.m5229a(((RecommendData) PosterGallery.this.f7853d.get(i2)).getId(), i2 + 1);
                    //new SUserEvent("PAGE_CLICK", SAction.ACTION_POST_GALLERY_SCROLL.getValue(), SPage.PAGE_ONLINE_FIND_SONG.getValue()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(((RecommendData) PosterGallery.this.f7853d.get(i2)).getId())).append("song_list_name", ((RecommendData) PosterGallery.this.f7853d.get(i2)).getName()).post();
                }
            }
        });
        ViewCompat.setLayerType(this, View.LAYER_TYPE_SOFTWARE, null);
    }

    /* renamed from: a */
    public void m1586a(ArrayList<RecommendData> arrayList) {
        m1585a(arrayList, 1, 5);
    }

    /* renamed from: a */
    public void m1585a(ArrayList<RecommendData> arrayList, int i, int i2) {
        this.f7853d = arrayList;
        int size = arrayList.size();
        int min = Math.min(size % i == 0 ? size / i : (size / i) + 1, i2);
        this.f7851b.m1722b(min);
        this.f7850a.removeAllViews();
        for (int i3 = 0; i3 < min; i3++) {
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.layout_null_relative_layout, (ViewGroup) null);
            viewGroup.addView(m1588a(arrayList.get(i3), i3));
            if (arrayList.get(i3).getTag() >= 0 && arrayList.get(i3).getTag() <= 6) {
                viewGroup.addView(m1589a(arrayList.get(i3)));
            }
            this.f7850a.addView(viewGroup);
        }
    }

    /* renamed from: a */
    private ImageView m1588a(RecommendData recommendData, int i) {
        RoundedImageView roundedImageView = new RoundedImageView(getContext(), 16.0f);
        roundedImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(DisplayUtils.dp2px(4), 0, DisplayUtils.dp2px(4), 0);
        roundedImageView.setLayoutParams(layoutParams);
        roundedImageView.setOnClickListener(this.f7852c.mo1583a(i));
        String picUrl = recommendData.getPicUrl();
        roundedImageView.setTag(R.id.view_bind_data, recommendData);
        int m7225c = DisplayUtils.getWidth();
        int m7225c2 = (int) (DisplayUtils.getWidth() * 0.469d);
        LogUtils.debug("PosterGallery", "poster url: " + picUrl + ", size: " + m7225c + "*" + m7225c2);
        ImageCacheUtils.displayImage(roundedImageView, picUrl, m7225c, m7225c2, (int) R.drawable.img_background_publish_poster_gallery);
        return roundedImageView;
    }

    /* renamed from: a */
    private ImageView m1589a(RecommendData recommendData) {
        ImageView imageView = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(DisplayUtils.dp2px(73), DisplayUtils.dp2px(28));
        layoutParams.addRule(12);
        layoutParams.addRule(11);
        layoutParams.setMargins(0, 0, 0, DisplayUtils.dp2px(12));
        imageView.setLayoutParams(layoutParams);
        try {
            imageView.setBackgroundResource(FindSongConfig.C0628c.m8272a(recommendData.getTag()));
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        return imageView;
    }

    public void setEnableAutoScroll(boolean z) {
        if (this.f7850a != null) {
            this.f7850a.setEnableAutoScroll(z);
        }
    }

    public void setPosterCallback(InterfaceC2222a interfaceC2222a) {
        this.f7852c = interfaceC2222a;
    }

    @Override // com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
    }
}
