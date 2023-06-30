package com.sds.android.ttpod.fragment.musiccircle;

import android.view.View;
import android.widget.AdapterView;
import com.sds.android.cloudapi.ttpod.result.LabeledTTPodUserListResult;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.activities.musiccircle.MusicCircleStatistic;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public class StarListOfCategoryFragment extends StarListFragment {
    @Override // com.sds.android.ttpod.fragment.musiccircle.StarListFragment
    public void onRequestData(int i, int i2, int i3) {
        CommandCenter.getInstance().execute(new Command(CommandID.REQUEST_STAR_USERS_BY_CATEGORY, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), "star_user_by_category"));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.musiccircle.UserListFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_STAR_USER_lIST_BY_CATEGORY, ReflectUtils.loadMethod(StarListOfCategoryFragment.class, "onUpdateStarUserListByCategory", LabeledTTPodUserListResult.class, String.class));
    }

    public void onUpdateStarUserListByCategory(LabeledTTPodUserListResult labeledTTPodUserListResult, String str) {
        if ("star_user_by_category".equals(str)) {
            onRequestDataFinished(labeledTTPodUserListResult);
        }
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.StarListFragment, com.sds.android.ttpod.adapter.p073e.UserListAdapter.InterfaceC0997a
    public void onFollow(long j) {
        super.onFollow(j);
        MusicCircleStatistic.m7980b(1, getTitle());
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.StarListFragment, com.sds.android.ttpod.adapter.p073e.UserListAdapter.InterfaceC0997a
    public void onUnFollow(long j) {
        super.onUnFollow(j);
        MusicCircleStatistic.m7980b(2, getTitle());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.musiccircle.UserListFragment
    public void onItemClickEvent(AdapterView<?> adapterView, View view, int i, long j) {
        super.onItemClickEvent(adapterView, view, i, j);
        MusicCircleStatistic.m7971e(getTitle());
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.UserListFragment
    protected String onCreateOrigin() {
        return "category";
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.UserListFragment
    protected String onCreateTitle() {
        return getTitle();
    }
}
