package com.sds.android.ttpod.activities.musiccircle.radar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.result.AlikeUserListResult;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.MusicCircleStatistic;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment;
import com.sds.android.ttpod.framework.modules.CommandID;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public class ScanRadarToFindFriendsFragment extends SlidingClosableFragment {
    private RadarAnimationManager mRadarAnimationManager;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getActionBarController().m7189b(R.string.alike_title);
        View inflate = layoutInflater.inflate(R.layout.musiccircle_radar_layout, viewGroup, false);
        initRadarAnimation(inflate);
        MusicCircleStatistic.m7967h();
        return inflate;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onBackPressed() {
        super.onBackPressed();
        MusicCircleStatistic.m7966i();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mRadarAnimationManager.m7902a();
    }

    private void initRadarAnimation(View view) {
        this.mRadarAnimationManager = new RadarAnimationManager(getActivity(), view);
        this.mRadarAnimationManager.m7877d();
        this.mRadarAnimationManager.m7891a(new RadarAnimationManager.InterfaceC0838a() { // from class: com.sds.android.ttpod.activities.musiccircle.radar.ScanRadarToFindFriendsFragment.1
        });
        this.mRadarAnimationManager.m7886a(new WrapUserPostListFragment.InterfaceC1704a() { // from class: com.sds.android.ttpod.activities.musiccircle.radar.ScanRadarToFindFriendsFragment.2
            @Override // com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment.InterfaceC1704a
            /* renamed from: a */
            public void mo5428a(TTPodUser tTPodUser) {
                ScanRadarToFindFriendsFragment.this.launchFragment(WrapUserPostListFragment.createUserPostListFragmentByUser(tTPodUser, "alike"));
            }
        });
        this.mRadarAnimationManager.m7884b();
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_ALIKE_USER_LIST, ReflectUtils.loadMethod(ScanRadarToFindFriendsFragment.class, "onUpdateAlikeUserList", AlikeUserListResult.class, String.class));
    }

    public void onUpdateAlikeUserList(AlikeUserListResult alikeUserListResult, String str) {
        if ("alike".equals(str)) {
            if (alikeUserListResult.getDataList().isEmpty()) {
                this.mRadarAnimationManager.m7875e();
            } else {
                this.mRadarAnimationManager.m7892a(alikeUserListResult);
            }
        }
    }
}
