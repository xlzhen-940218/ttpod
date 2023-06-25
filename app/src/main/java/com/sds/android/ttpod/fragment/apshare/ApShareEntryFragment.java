package com.sds.android.ttpod.fragment.apshare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.audioeffect.ActionBarUtils;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.utils.EntryUtils;

/* loaded from: classes.dex */
public class ApShareEntryFragment extends SlidingClosableFragment {
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.apshare.ApShareEntryFragment.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_share /* 2131230792 */:
                    ApShareEntryFragment.this.launchFragment(new ApShareChooseFragment());
                    //LocalStatistic.m5102d();
                    //SUserUtils.m4951b("PAGE_CLICK", SAction.ACTION_UPLOAD_SONG_SHARE, SPage.PAGE_UPLOAD_SONG, SPage.PAGE_UPLOAD_SONG_SHARE);
                    return;
                case R.id.tv_receive /* 2131230793 */:
                    ApShareEntryFragment.this.launchFragment(new ApShareReceiveFragment());
                    //LocalStatistic.m5101e();
                    //SUserUtils.m4951b("PAGE_CLICK", SAction.ACTION_UPLOAD_SONG_RECEIVE, SPage.PAGE_UPLOAD_SONG, SPage.PAGE_UPLOAD_SONG_RECEIVE);
                    return;
                case R.id.tv_pc_send /* 2131230794 */:
                    EntryUtils.m8301a(ApShareEntryFragment.this.getActivity(), 2);
                    //LocalStatistic.m5100f();
                    //SUserUtils.m4951b("PAGE_CLICK", SAction.ACTION_UPLOAD_SONG_COMPUTE, SPage.PAGE_UPLOAD_SONG, SPage.PAGE_UPLOAD_SONG_PC);
                    return;
                default:
                    return;
            }
        }
    };
    private TextView mReceive;
    private TextView mSend;
    private TextView mShare;

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setPage(SPage.PAGE_UPLOAD_SONG);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateContentView(layoutInflater, viewGroup, bundle);
        ActionBarController actionBarController = getActionBarController();
        ActionBarUtils.m8129b(actionBarController);
        actionBarController.m7189b(R.string.share_fast_send);
        View inflate = layoutInflater.inflate(R.layout.activity_apshare_entry, (ViewGroup) null);
        this.mShare = (TextView) inflate.findViewById(R.id.tv_share);
        this.mShare.setOnClickListener(this.mOnClickListener);
        this.mReceive = (TextView) inflate.findViewById(R.id.tv_receive);
        this.mReceive.setOnClickListener(this.mOnClickListener);
        this.mSend = (TextView) inflate.findViewById(R.id.tv_pc_send);
        this.mSend.setOnClickListener(this.mOnClickListener);
        return inflate;
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        unListener(this.mShare);
        unListener(this.mReceive);
        unListener(this.mSend);
        super.onDestroyView();
    }

    private void unListener(TextView textView) {
        if (textView != null) {
            textView.setOnClickListener(null);
        }
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        getRootView().setBackgroundResource(R.drawable.apshare_whole_bkg);
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }
}
