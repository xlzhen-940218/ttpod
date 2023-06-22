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
public class StarListOfRankFragment extends StarListFragment {
    @Override // com.sds.android.ttpod.fragment.musiccircle.StarListFragment
    public void onRequestData(int i, int i2, int i3) {
        CommandCenter.m4607a().m4606a(new Command(CommandID.REQUEST_STAR_USERS_BY_RANK, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), "star_user_by_rank"));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.musiccircle.UserListFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_STAR_USER_LIST_BY_RANK, ReflectUtils.m8375a(StarListOfRankFragment.class, "onUpdateStarUserListByRank", LabeledTTPodUserListResult.class, String.class));
    }

    public void onUpdateStarUserListByRank(LabeledTTPodUserListResult labeledTTPodUserListResult, String str) {
        if ("star_user_by_rank".equals(str)) {
            onRequestDataFinished(labeledTTPodUserListResult);
        }
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.StarListFragment, com.sds.android.ttpod.adapter.p073e.UserListAdapter.InterfaceC0997a
    public void onFollow(long j) {
        super.onFollow(j);
        MusicCircleStatistic.m7985a(1, getTitle());
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.StarListFragment, com.sds.android.ttpod.adapter.p073e.UserListAdapter.InterfaceC0997a
    public void onUnFollow(long j) {
        super.onUnFollow(j);
        MusicCircleStatistic.m7985a(2, getTitle());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.musiccircle.UserListFragment
    public void onItemClickEvent(AdapterView<?> adapterView, View view, int i, long j) {
        super.onItemClickEvent(adapterView, view, i, j);
        MusicCircleStatistic.m7978b(getTitle());
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.UserListFragment
    protected String onCreateOrigin() {
        return "rank";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.musiccircle.UserListFragment
    public String onCreateTitle() {
        return getTitle();
    }
}
