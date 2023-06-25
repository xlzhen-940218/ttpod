package com.sds.android.ttpod.fragment.musiccircle;

import android.view.View;
import android.widget.AdapterView;
import com.sds.android.ttpod.activities.musiccircle.MusicCircleStatistic;

/* loaded from: classes.dex */
public class StarListOfRecommendFragment extends StarListOfRankFragment {
    private static final int RECOMMEND_ID = 0;

    @Override // com.sds.android.ttpod.fragment.musiccircle.StarListOfRankFragment, com.sds.android.ttpod.fragment.musiccircle.StarListFragment
    public void onRequestData(int i, int i2, int i3) {
        super.onRequestData(0, i2, i3);
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.StarListOfRankFragment, com.sds.android.ttpod.fragment.musiccircle.StarListFragment, com.sds.android.ttpod.adapter.p073e.UserListAdapter.InterfaceC0997a
    public void onFollow(long j) {
        MusicCircleStatistic.m7981b(1);
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.StarListOfRankFragment, com.sds.android.ttpod.fragment.musiccircle.StarListFragment, com.sds.android.ttpod.adapter.p073e.UserListAdapter.InterfaceC0997a
    public void onUnFollow(long j) {
        MusicCircleStatistic.m7981b(2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.musiccircle.StarListOfRankFragment, com.sds.android.ttpod.fragment.musiccircle.UserListFragment
    public void onItemClickEvent(AdapterView<?> adapterView, View view, int i, long j) {
        MusicCircleStatistic.m7957r();
        //OnlineMediaStatistic.m5045a("music-circle");
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.StarListOfRankFragment, com.sds.android.ttpod.fragment.musiccircle.UserListFragment
    protected String onCreateOrigin() {
        return "recommend";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.musiccircle.StarListOfRankFragment, com.sds.android.ttpod.fragment.musiccircle.UserListFragment
    public String onCreateTitle() {
        return super.onCreateTitle();
    }
}
