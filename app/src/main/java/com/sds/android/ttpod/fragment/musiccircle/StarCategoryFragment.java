package com.sds.android.ttpod.fragment.musiccircle;

import android.os.Bundle;
import android.view.View;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.result.StarCategoryResult;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.activities.musiccircle.MusicCircleStatistic;
import com.sds.android.ttpod.adapter.p073e.StarCategoryAdapter;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.widget.StateView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class StarCategoryFragment extends BaseStarCategoryFragment {
    private List<StarCategory> mDatas = new ArrayList();
    private StarCategoryAdapter mStarCategoryAdapter;

    @Override // com.sds.android.ttpod.fragment.musiccircle.BaseStarCategoryFragment
    protected StarCategoryAdapter onCreateAdapter() {
        this.mStarCategoryAdapter = new StarCategoryAdapter(getActivity(), this.mDatas);
        return this.mStarCategoryAdapter;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setLoadingState(this.mDatas.isEmpty() ? StateView.EnumC2248b.LOADING : StateView.EnumC2248b.SUCCESS);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_STAR_CATEGORIES_LIST, ReflectUtils.loadMethod(StarCategoryFragment.class, "onUpdateStarCategoryList", StarCategoryResult.class, String.class));
    }

    public void onUpdateStarCategoryList(StarCategoryResult starCategoryResult, String str) {
        if ("star_category".equals(str)) {
            ArrayList<StarCategory> dataList = starCategoryResult.getDataList();
            if (dataList.isEmpty()) {
                setLoadingState(StateView.EnumC2248b.FAILED);
                return;
            }
            setLoadingState(StateView.EnumC2248b.SUCCESS);
            this.mDatas.clear();
            this.mDatas = dataList;
            this.mStarCategoryAdapter.setDataList((List) this.mDatas);
        }
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.BaseStarCategoryFragment
    protected void onRequestData() {
        if (this.mDatas.isEmpty()) {
            CommandCenter.getInstance().execute(new Command(CommandID.REQUEST_STAR_CATEGORIES, "star_category"));
        }
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.BaseStarCategoryFragment
    protected int loadCategoryType() {
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.musiccircle.BaseStarCategoryFragment
    public void onItemClickEvent(StarCategory starCategory) {
        super.onItemClickEvent(starCategory);
        MusicCircleStatistic.m7973d(starCategory.getName());
        //OnlineMediaStatistic.m5045a("music-circle");
    }
}
