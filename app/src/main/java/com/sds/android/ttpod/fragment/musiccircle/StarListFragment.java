package com.sds.android.ttpod.fragment.musiccircle;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.sds.android.cloudapi.ttpod.data.LabeledTTPodUser;
import com.sds.android.cloudapi.ttpod.result.LabeledTTPodUserListResult;
import com.sds.android.ttpod.adapter.p073e.UserInfoViewHolder;
import com.sds.android.ttpod.adapter.p073e.UserListAdapter;
import com.sds.android.ttpod.widget.StateView;
import java.util.List;

/* loaded from: classes.dex */
public abstract class StarListFragment extends UserListFragment<LabeledTTPodUser> implements UserListAdapter.InterfaceC0997a {
    private int mCategoryID;
    private C1695a mStarAdapter;
    private String mTitle;

    public abstract void onRequestData(int i, int i2, int i3);

    @Override // com.sds.android.ttpod.fragment.musiccircle.UserListFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mCategoryID = arguments.getInt("category_id", 0);
            this.mTitle = arguments.getString("title");
            if (this.mTitle == null) {
                this.mTitle = "";
            }
        }
    }

    public String getTitle() {
        return this.mTitle;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (isLoadFinished()) {
            setLoadingState(getDataCount() <= 0 ? StateView.EnumC2248b.FAILED : StateView.EnumC2248b.SUCCESS);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onRequestDataFinished(LabeledTTPodUserListResult labeledTTPodUserListResult) {
        setPageTotal(labeledTTPodUserListResult.getExtra().m8556b());
        addData(labeledTTPodUserListResult.getDataList(), true);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        setLoadingState(getDataCount() <= 0 ? StateView.EnumC2248b.LOADING : StateView.EnumC2248b.SUCCESS);
        setPageStart(1);
        setPageCurrent(1);
        reload();
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.UserListFragment
    protected UserListAdapter<LabeledTTPodUser> onCreateAdapter(List<LabeledTTPodUser> list) {
        this.mStarAdapter = new C1695a(getActivity(), list);
        this.mStarAdapter.m7430a((UserListAdapter.InterfaceC0997a) this);
        return this.mStarAdapter;
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.UserListFragment
    protected void onRequestData(int i, int i2) {
        onRequestData(this.mCategoryID, i, i2);
    }

    @Override // com.sds.android.ttpod.adapter.p073e.UserListAdapter.InterfaceC0997a
    public void onFollow(long j) {
    }

    @Override // com.sds.android.ttpod.adapter.p073e.UserListAdapter.InterfaceC0997a
    public void onUnFollow(long j) {
    }

    /* renamed from: com.sds.android.ttpod.fragment.musiccircle.StarListFragment$a */
    /* loaded from: classes.dex */
    class C1695a extends UserListAdapter<LabeledTTPodUser> {
        public C1695a(Context context, List<LabeledTTPodUser> list) {
            super(context, list);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.adapter.p073e.UserListAdapter
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo5434a(UserInfoViewHolder userInfoViewHolder, LabeledTTPodUser labeledTTPodUser) {
            super.mo5434a(userInfoViewHolder, labeledTTPodUser);
            userInfoViewHolder.m7433d().setText(labeledTTPodUser.getLabel());
        }
    }
}
