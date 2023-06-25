package com.sds.android.ttpod.activities.musiccircle.search;

import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.result.UserListResult;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.p073e.UserInfoViewHolder;
import com.sds.android.ttpod.adapter.p073e.UserListAdapter;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.musiccircle.UserListFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class SearchFriendsResultFragment extends SlidingClosableFragment {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        initView();
        return layoutInflater.inflate(R.layout.musiccircle_search_result_layout, viewGroup, false);
    }

    private void initView() {
        getActionBarController().m7189b(R.string.musiccircle_search_result);
        if (getArguments() != null && TextUtils.isEmpty(getArguments().getString("search_content"))) {
            throw new IllegalArgumentException("search content must not be null and length >= 0");
        }
        getChildFragmentManager().beginTransaction().replace(R.id.search_result, Fragment.instantiate(getActivity(), SearchResultFragment.class.getName(), getArguments())).commitAllowingStateLoss();
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    /* loaded from: classes.dex */
    public static class SearchResultFragment extends UserListFragment<TTPodUser> {
        private String mSearchContent;

        @Override // com.sds.android.ttpod.fragment.musiccircle.UserListFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            this.mSearchContent = getArguments().getString("search_content");
        }

        @Override // com.sds.android.ttpod.fragment.musiccircle.UserListFragment
        protected UserListAdapter<TTPodUser> onCreateAdapter(List<TTPodUser> list) {
            return new UserListAdapter<TTPodUser>(getActivity(), list) { // from class: com.sds.android.ttpod.activities.musiccircle.search.SearchFriendsResultFragment.SearchResultFragment.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.sds.android.ttpod.adapter.p073e.UserListAdapter
                /* renamed from: a */
                public void mo5434a(UserInfoViewHolder userInfoViewHolder, TTPodUser tTPodUser) {
                    super.mo5434a(userInfoViewHolder,  tTPodUser);
                    userInfoViewHolder.m7433d().setVisibility(View.GONE);
                }
            };
        }

        @Override // com.sds.android.ttpod.fragment.musiccircle.UserListFragment
        protected void onRequestData(int i, int i2) {
            CommandCenter.getInstance().m4606a(new Command(CommandID.MUSICCIRCLE_SEARCH, this.mSearchContent, "search"));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.fragment.musiccircle.UserListFragment, com.sds.android.ttpod.framework.base.BaseFragment
        public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
            super.onLoadCommandMap(map);
            map.put(CommandID.UPDATE_SEARCH_RESULT, ReflectUtils.m8375a(getClass(), "onUpdateSearchResult", UserListResult.class, String.class));
        }

        public void onUpdateSearchResult(UserListResult userListResult, String str) {
            if ("search".equals(str)) {
                addData(userListResult.getDataList(), false);
            }
        }

        @Override // com.sds.android.ttpod.framework.base.BaseFragment
        public void onLoadFinished() {
            super.onLoadFinished();
            reload();
        }
    }
}
