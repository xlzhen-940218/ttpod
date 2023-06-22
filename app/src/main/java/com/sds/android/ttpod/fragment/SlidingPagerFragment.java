package com.sds.android.ttpod.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.sds.android.cloudapi.ttpod.data.VIPPolicy;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class SlidingPagerFragment extends Fragment {
    private static final long DELAY = 4000;
    private static final int DOT_WIDTH = 14;
    private static final int INT_100 = 100;
    private static final int WHAT_AUTO_SCROLL = 1;
    private LinearLayout mDotListLayout;
    private int[] mImageIds;
    private int mSize;
    private ViewPager mViewPager;
    private List<View> mGalleryList = new ArrayList();
    private List<ImageView> mDotList = new ArrayList();
    private boolean mAutoScroll = false;
    private Handler mHandler = new Handler() { // from class: com.sds.android.ttpod.fragment.SlidingPagerFragment.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            SlidingPagerFragment.this.mViewPager.setCurrentItem(SlidingPagerFragment.this.mViewPager.getCurrentItem() + 1);
            if (SlidingPagerFragment.this.mAutoScroll) {
                SlidingPagerFragment.this.mHandler.removeMessages(1);
                SlidingPagerFragment.this.mHandler.sendEmptyMessageDelayed(1, SlidingPagerFragment.DELAY);
            }
        }
    };
    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() { // from class: com.sds.android.ttpod.fragment.SlidingPagerFragment.2
        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            int i2 = i % SlidingPagerFragment.this.mSize;
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 < SlidingPagerFragment.this.mSize) {
                    if (i4 == i2) {
                        ((ImageView) SlidingPagerFragment.this.mDotList.get(i4)).setImageResource(R.drawable.img_imageview_navigate_dot_focused);
                    } else {
                        ((ImageView) SlidingPagerFragment.this.mDotList.get(i4)).setImageResource(R.drawable.img_imageview_navigate_dot_normal);
                    }
                    i3 = i4 + 1;
                } else {
                    return;
                }
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }
    };

    public SlidingPagerFragment(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException("ids can not be null or empty");
        }
        this.mImageIds = iArr;
        this.mSize = iArr.length;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_common_navigate, viewGroup, false);
        initGalleryList();
        this.mDotListLayout = (LinearLayout) inflate.findViewById(R.id.layout_dotlist);
        initDotList();
        this.mViewPager = (ViewPager) inflate.findViewById(R.id.pager);
        this.mViewPager.setAdapter(new C1350a());
        this.mViewPager.setOnPageChangeListener(this.mOnPageChangeListener);
        this.mViewPager.setCurrentItem(this.mSize * 100);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mHandler.removeMessages(1);
        if (this.mAutoScroll) {
            this.mHandler.sendEmptyMessageDelayed(1, DELAY);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mHandler.removeMessages(1);
    }

    public void setAutoScroll(boolean z) {
        this.mAutoScroll = z;
    }

    private void initDotList() {
        int m7223e = (int) (14.0f * DisplayUtils.m7223e());
        for (int i = 0; i < this.mImageIds.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(m7223e, -2));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            if (i == 0) {
                imageView.setImageResource(R.drawable.img_imageview_navigate_dot_focused);
            } else {
                imageView.setImageResource(R.drawable.img_imageview_navigate_dot_normal);
            }
            this.mDotList.add(imageView);
            this.mDotListLayout.addView(imageView);
        }
    }

    private void initGalleryList() {
        for (int i = 0; i < this.mImageIds.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            try {
                imageView.setImageResource(this.mImageIds[i]);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            imageView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setVisibility(View.VISIBLE);
            this.mGalleryList.add(imageView);
        }
    }

    /* renamed from: com.sds.android.ttpod.fragment.SlidingPagerFragment$a */
    /* loaded from: classes.dex */
    class C1350a extends PagerAdapter {
        C1350a() {
        }

        @Override // android.support.v4.view.PagerAdapter
        public int getCount() {
            return VIPPolicy.Entry.MAX_LIMIT;
        }

        @Override // android.support.v4.view.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override // android.support.v4.view.PagerAdapter
        public void destroyItem(View view, int i, Object obj) {
            ((ViewPager) view).removeView((View) SlidingPagerFragment.this.mGalleryList.get(i % SlidingPagerFragment.this.mSize));
        }

        @Override // android.support.v4.view.PagerAdapter
        public Object instantiateItem(View view, int i) {
            ((ViewPager) view).addView((View) SlidingPagerFragment.this.mGalleryList.get(i % SlidingPagerFragment.this.mSize), 0);
            return SlidingPagerFragment.this.mGalleryList.get(i % SlidingPagerFragment.this.mSize);
        }
    }
}
