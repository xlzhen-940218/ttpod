package com.sds.android.ttpod.fragment.musiccircle;

import android.os.Bundle;
import android.view.View;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.ttpod.activities.musiccircle.MusicCircleStatistic;
import com.sds.android.ttpod.adapter.p073e.StarCategoryAdapter;
import com.sds.android.ttpod.widget.StateView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class StarRankFragment extends BaseStarCategoryFragment {
    private static final int STAR_ACTIVE = 4;
    private static final int STAR_CAPACITY = 3;
    private static final int STAR_POPULARITY = 1;
    private static final int STAR_RISE = 2;
    private List<StarCategory> mDatas = new ArrayList();

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        StarCategory starCategory = new StarCategory();
        starCategory.setId(1);
        starCategory.setName("人气榜");
        this.mDatas.add(starCategory);
        StarCategory starCategory2 = new StarCategory();
        starCategory2.setId(4);
        starCategory2.setName("活跃榜");
        this.mDatas.add(starCategory2);
        StarCategory starCategory3 = new StarCategory();
        starCategory3.setId(2);
        starCategory3.setName("飙升榜");
        this.mDatas.add(starCategory3);
        StarCategory starCategory4 = new StarCategory();
        starCategory4.setId(3);
        starCategory4.setName("潜力榜");
        this.mDatas.add(starCategory4);
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.BaseStarCategoryFragment
    protected StarCategoryAdapter onCreateAdapter() {
        return new StarCategoryAdapter(getActivity(), this.mDatas);
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.BaseStarCategoryFragment
    protected int loadCategoryType() {
        return 0;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setLoadingState(StateView.EnumC2248b.SUCCESS);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.musiccircle.BaseStarCategoryFragment
    public void onItemClickEvent(StarCategory starCategory) {
        super.onItemClickEvent(starCategory);
        MusicCircleStatistic.m7983a(starCategory.getName());
        //OnlineMediaStatistic.m5045a("music-circle");
    }
}
