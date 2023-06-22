package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.cloudapi.ttpod.data.MusicRank;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;

/* loaded from: classes.dex */
public class SubRankDetailFragment extends SlidingClosableFragment {
    private final RankDetailFragment mRankDetailFragment;

    public SubRankDetailFragment(MusicRank musicRank) {
        this.mRankDetailFragment = new RankDetailFragment(musicRank);
        initBundle(SPage.PAGE_RANK_DETAIL, String.valueOf(musicRank.getId()));
    }

    public SubRankDetailFragment(int i) {
        this.mRankDetailFragment = new RankDetailFragment(i);
        initBundle(SPage.PAGE_RANK_DETAIL, String.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.sub_fragment_container_layout, viewGroup, false);
        getChildFragmentManager().beginTransaction().replace(R.id.sub_fragment_container, this.mRankDetailFragment, null).commitAllowingStateLoss();
        this.mRankDetailFragment.setOnSetTitleListener(getActionBarController());
        return inflate;
    }
}
