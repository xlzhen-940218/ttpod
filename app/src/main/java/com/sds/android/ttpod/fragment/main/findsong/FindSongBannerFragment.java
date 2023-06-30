package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.fragment.main.FindSongBaseViewFragment;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.widget.RoundedImageView;

/* loaded from: classes.dex */
public class FindSongBannerFragment extends FindSongBaseViewFragment {
    private ViewGroup mFindSongBannerViewContainer;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mFindSongBannerViewContainer == null) {
            int m7229a = DisplayUtils.dp2px(8);
            this.mFindSongBannerViewContainer = (ViewGroup) layoutInflater.inflate(R.layout.layout_null_relative_layout, (ViewGroup) null);
            this.mFindSongBannerViewContainer.setPadding(m7229a, m7229a, m7229a, m7229a);
            this.mFindSongBannerViewContainer.addView(createBannerView());
            this.mFindSongBannerViewContainer.addView(createClickView());
            this.mFindSongBannerViewContainer.addView(createCloseButton());
        }
        return this.mFindSongBannerViewContainer;
    }

    private RoundedImageView createBannerView() {
        RoundedImageView roundedImageView = new RoundedImageView(getActivity());
        roundedImageView.setCornerRadius(DisplayUtils.dp2px(4));
        int m7225c = DisplayUtils.getWidth();
        int i = (int) (m7225c * 0.2d);
        roundedImageView.setLayoutParams(new LinearLayout.LayoutParams(-1, i));
        roundedImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageCacheUtils.displayImage(roundedImageView, getItemData(0).getPicUrl(), m7225c, i, (int) R.drawable.img_background_publish_poster_gallery);
        return roundedImageView;
    }

    private ImageView createClickView() {
        ImageView imageView = new ImageView(getActivity());
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(-1, (int) (DisplayUtils.getWidth() * 0.2d)));
        imageView.setBackground(getResources().getDrawable(R.drawable.grid_view_item_click_bg));
        imageView.getBackground().setAlpha(50);
        imageView.setOnClickListener(createItemOnClickListener(0));
        return imageView;
    }

    private ImageView createCloseButton() {
        ImageView imageView = new ImageView(getActivity());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(11);
        layoutParams.addRule(10);
        imageView.setLayoutParams(layoutParams);
        imageView.setBackground(getResources().getDrawable(R.drawable.banner_close_button_click_bg));
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.FindSongBannerFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FindSongBannerFragment.this.mFindSongBannerViewContainer.setVisibility(View.GONE);
               // FindSongNewStatistic.m5230a(348, FindSongBannerFragment.this.getModuleData().getId());
               // FindSongNewStatistic.m5230a(324, FindSongBannerFragment.this.getItemData(0).getId());
                FindSongBannerFragment.this.doFindSongStatistic(0, SAction.ACTION_CLICK_ONLINE_FIND_SONG_ITEM_CLOSE);
            }
        });
        return imageView;
    }
}
