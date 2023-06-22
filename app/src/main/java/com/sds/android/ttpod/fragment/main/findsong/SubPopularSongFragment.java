package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;

/* loaded from: classes.dex */
public class SubPopularSongFragment extends SlidingClosableFragment {
    private final PopularSongFragment mPopularSongFragment;

    public SubPopularSongFragment(long j) {
        this.mPopularSongFragment = new PopularSongFragment(j);
        initBundle(SPage.PAGE_ONLINE_POPULAR_SONG, String.valueOf(j));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.sub_fragment_container_layout, viewGroup, false);
        getChildFragmentManager().beginTransaction().replace(R.id.sub_fragment_container, this.mPopularSongFragment, null).commitAllowingStateLoss();
        this.mPopularSongFragment.setOnSetTitleListener(getActionBarController());
        return inflate;
    }
}
