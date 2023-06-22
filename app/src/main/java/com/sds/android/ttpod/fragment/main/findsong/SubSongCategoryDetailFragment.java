package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.cloudapi.ttpod.result.OnlineMusicSubCategoryResult;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;

/* loaded from: classes.dex */
public class SubSongCategoryDetailFragment extends SlidingClosableFragment {
    private final SongCategoryDetailFragment mSongCategoryDetailFragment;

    public SubSongCategoryDetailFragment(String str) {
        this.mSongCategoryDetailFragment = new SongCategoryDetailFragment(str);
        initBundle(SPage.PAGE_ONLINE_SONG_CATEGORY, String.valueOf(str));
    }

    public SubSongCategoryDetailFragment(OnlineMusicSubCategoryResult.SubCategoryData subCategoryData) {
        this.mSongCategoryDetailFragment = new SongCategoryDetailFragment(subCategoryData);
        initBundle(SPage.PAGE_ONLINE_SONG_CATEGORY, String.valueOf(subCategoryData.getId()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.sub_fragment_container_layout, viewGroup, false);
        getChildFragmentManager().beginTransaction().replace(R.id.sub_fragment_container, this.mSongCategoryDetailFragment, null).commitAllowingStateLoss();
        this.mSongCategoryDetailFragment.setOnSetTitleListener(getActionBarController());
        return inflate;
    }
}
