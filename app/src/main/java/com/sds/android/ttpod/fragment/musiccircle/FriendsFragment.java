package com.sds.android.ttpod.fragment.musiccircle;

import android.os.Bundle;
import android.view.View;

import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.result.UserListResult;
import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.adapter.p073e.UserInfoViewHolder;
import com.sds.android.ttpod.adapter.p073e.UserListAdapter;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class FriendsFragment extends UserListFragment<TTPodUser> {
    private static final long YEAR_2035 = 2075240751;
    private long mCurrentTimeStamp = YEAR_2035;
    private long mUserId;

    protected abstract CommandID onLoadRequestDataCommandID();

    protected abstract CommandID onLoadUpdateRequestDataCommandID();

    @Override // com.sds.android.ttpod.fragment.musiccircle.UserListFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mUserId = arguments.getLong("user_id");
        }
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.UserListFragment
    protected UserListAdapter<TTPodUser> onCreateAdapter(List<TTPodUser> list) {
        return new UserListAdapter<TTPodUser>(getActivity(), list) { // from class: com.sds.android.ttpod.fragment.musiccircle.FriendsFragment.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sds.android.ttpod.adapter.p073e.UserListAdapter
            /* renamed from: a */
            public void mo5434a(UserInfoViewHolder userInfoViewHolder, TTPodUser tTPodUser) {
                super.mo5434a(userInfoViewHolder, tTPodUser);
                userInfoViewHolder.m7433d().setVisibility(View.GONE);
            }
        };
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.UserListFragment
    protected void onRequestData(int i, int i2) {
        if (this.mUserId > 0) {
            CommandCenter.getInstance().execute(new Command(onLoadRequestDataCommandID(), Long.valueOf(this.mUserId), Integer.valueOf(i), Integer.valueOf(i2), Long.valueOf(this.mCurrentTimeStamp), ""));
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        reload();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.musiccircle.UserListFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(onLoadUpdateRequestDataCommandID(), ReflectUtils.loadMethod(getClass(), "onUpdateData", UserListResult.class, String.class));
    }

    public void onUpdateData(UserListResult userListResult, String str) {
        updateRequestTime();
        addData(userListResult.getDataList(), true);
        setPageTotal(userListResult.getExtra().m8556b());
    }

    private void updateRequestTime() {
        if (this.mCurrentTimeStamp == YEAR_2035) {
            this.mCurrentTimeStamp = HttpRequest.Response.getDate();
        }
    }
}
