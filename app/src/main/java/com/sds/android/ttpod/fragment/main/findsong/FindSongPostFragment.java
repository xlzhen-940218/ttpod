package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.sds.android.cloudapi.ttpod.data.RecommendData;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.fragment.main.FindSongBaseViewFragment;
import com.sds.android.ttpod.widget.PosterGallery;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class FindSongPostFragment extends FindSongBaseViewFragment {
    public static final double POST_IMAGE_WIDTH_HEIGHT_RATIO = 0.469d;
    private PosterGallery mFindSongPostView;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mFindSongPostView == null) {
            this.mFindSongPostView = createPostGallery();
            this.mFindSongPostView.m1586a((ArrayList<RecommendData>) getModuleData().getDataList());
            this.mFindSongPostView.setEnableAutoScroll(true);
        }
        return this.mFindSongPostView;
    }

    private PosterGallery createPostGallery() {
        PosterGallery posterGallery = new PosterGallery(getActivity());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, (int) (DisplayUtils.m7225c() * 0.469d));
        int m7229a = DisplayUtils.m7229a(4);
        layoutParams.setMargins(m7229a, m7229a, m7229a, m7229a);
        posterGallery.setLayoutParams(layoutParams);
        posterGallery.setPosterCallback(new PosterGallery.InterfaceC2222a() { // from class: com.sds.android.ttpod.fragment.main.findsong.FindSongPostFragment.1
            @Override // com.sds.android.ttpod.widget.PosterGallery.InterfaceC2222a
            /* renamed from: a */
            public View.OnClickListener mo1583a(int i) {
                return FindSongPostFragment.this.createItemOnClickListener(i);
            }
        });
        return posterGallery;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (this.mFindSongPostView != null) {
            this.mFindSongPostView.setEnableAutoScroll(z);
        }
    }
}
