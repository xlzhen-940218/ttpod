package com.sds.android.ttpod.fragment.musiccircle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.result.TTPodUserResult;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.widget.NetworkLoadView;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public class WrapUserPostListFragment extends SlidingClosableFragment {
    private NetworkLoadView mNetworkLoadingView;

    /* renamed from: com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1704a {
        /* renamed from: a */
        void mo5428a(TTPodUser tTPodUser);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        hideActionBar();
        View inflate = layoutInflater.inflate(R.layout.musiccircle_wrap_user_post_list_layout, viewGroup, false);
        initNetworkLoadingView(inflate);
        Bundle arguments = getArguments();
        TTPodUser tTPodUser = (TTPodUser) arguments.getSerializable("user");
        long j = arguments.getLong("user_id", -1L);
        if (tTPodUser == null) {
            if (j > 0) {
                this.mNetworkLoadingView.setLoadState(NetworkLoadView.EnumC2205a.LOADING);
            } else {
                throw new IllegalArgumentException("user is null, and user_id is invalid");
            }
        } else {
            onGetUserInfo(tTPodUser, arguments.getString("origin"), arguments.getString("title"));
        }
        return inflate;
    }

    private void initNetworkLoadingView(View view) {
        this.mNetworkLoadingView = (NetworkLoadView) view.findViewById(R.id.loading_view);
        this.mNetworkLoadingView.setOnStartLoadingListener(new NetworkLoadView.InterfaceC2206b() { // from class: com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment.1
            @Override // com.sds.android.ttpod.widget.NetworkLoadView.InterfaceC2206b
            /* renamed from: a */
            public void mo1678a() {
                WrapUserPostListFragment.this.requestUserInfo(WrapUserPostListFragment.this.getArguments().getLong("user_id", -1L));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestUserInfo(long j) {
        CommandCenter.getInstance().execute(new Command(CommandID.GET_USER_INFO_BY_ID, Preferences.m2954aq().getAccessToken(), Long.valueOf(j)));
    }

    private void onGetUserInfo(TTPodUser tTPodUser, String str, String str2) {
        UserPostListFragment userPostListFragment = new UserPostListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", tTPodUser);
        bundle.putString("origin", str);
        bundle.putString("title", str2);
        userPostListFragment.setArguments(bundle);
        getChildFragmentManager().beginTransaction().replace(R.id.musiccircle_wrap_user_post_list, userPostListFragment).commitAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_USER_INFO_BY_ID, ReflectUtils.m8375a(getClass(), "updateUserInfo", TTPodUserResult.class));
    }

    public void updateUserInfo(TTPodUserResult tTPodUserResult) {
        if (tTPodUserResult != null && tTPodUserResult.isSuccess()) {
            this.mNetworkLoadingView.setLoadState(NetworkLoadView.EnumC2205a.IDLE);
            onGetUserInfo(tTPodUserResult.getData(), "", "");
            return;
        }
        if (EnvironmentUtils.DeviceConfig.isConnected()) {
            PopupsUtils.m6760a((int) R.string.userinfo_not_found);
        } else {
            PopupsUtils.m6760a((int) R.string.network_unavailable);
        }
        this.mNetworkLoadingView.setLoadState(NetworkLoadView.EnumC2205a.FAILED);
    }

    public static WrapUserPostListFragment createUserPostListFragmentByUser(TTPodUser tTPodUser, String str) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", tTPodUser);
        bundle.putString("origin", str);
        WrapUserPostListFragment wrapUserPostListFragment = new WrapUserPostListFragment();
        wrapUserPostListFragment.setArguments(bundle);
        return wrapUserPostListFragment;
    }

    public static WrapUserPostListFragment createUserPostListFragmentByUserId(long j, String str) {
        Bundle bundle = new Bundle();
        bundle.putLong("user_id", j);
        bundle.putString("origin", str);
        WrapUserPostListFragment wrapUserPostListFragment = new WrapUserPostListFragment();
        wrapUserPostListFragment.setArguments(bundle);
        return wrapUserPostListFragment;
    }
}
