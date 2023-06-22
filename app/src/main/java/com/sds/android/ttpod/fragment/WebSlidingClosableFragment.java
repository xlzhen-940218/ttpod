package com.sds.android.ttpod.fragment;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.share.ShareInfo;


/* loaded from: classes.dex */
public class WebSlidingClosableFragment extends SlidingClosableFragment {
    public static final String EXTRA_PIC_URL = "extra_pic_url";
    private WebFragment mWebFragment;

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        setPage(SPage.PAGE_ONLINE_WEB);
        super.onCreate(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateContentView(layoutInflater, viewGroup, bundle);
        View inflate = layoutInflater.inflate(R.layout.framelayout_container, viewGroup, false);
        Bundle arguments = getArguments();
        final String string = arguments.getString(WebFragment.EXTRA_TITLE);
        final String string2 = arguments.getString(WebFragment.EXTRA_URL);
        final String string3 = arguments.getString(EXTRA_PIC_URL);
        getActionBarController().m7193a((CharSequence) (StringUtils.m8346a(string) ? "" : string));
        this.mWebFragment = (WebFragment) Fragment.instantiate(getActivity(), WebFragment.class.getName(), arguments);
        ActionBarController.C1070a m7199a = getActionBarController().m7199a((Drawable) null);
        ThemeUtils.m8170a(m7199a, (int) R.string.icon_share_action_bar, ThemeElement.TOP_BAR_TEXT);
        m7199a.m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.fragment.WebSlidingClosableFragment.1
            @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
            /* renamed from: a */
            public void mo5433a(ActionBarController.C1070a c1070a) {
                if (!StringUtils.m8346a(string3)) {
                    WebSlidingClosableFragment.this.doStatisticShare(string3);
                    PopupsUtils.m6753a((Activity) WebSlidingClosableFragment.this.getActivity(), WebSlidingClosableFragment.this.buildShareInfo(string, string2, string3));
                    return;
                }
                PopupsUtils.m6760a((int) R.string.can_not_share);
            }
        });
        getChildFragmentManager().beginTransaction().replace(R.id.container, this.mWebFragment).commitAllowingStateLoss();
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doStatisticShare(String str) {
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_ACTION_BAR_SHARE.getValue(), 0, SPage.PAGE_SHARE_DIALOG.getValue());
        sUserEvent.append("url", str);
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onBackPressed() {
        if (this.mWebFragment != null && this.mWebFragment.canBackward()) {
            this.mWebFragment.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public void onTitleClicked() {
        super.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ShareInfo buildShareInfo(String str, String str2, String str3) {
        ShareInfo shareInfo = new ShareInfo();
        shareInfo.m1961c(true);
        shareInfo.m1957e(str);
        shareInfo.m1962c(str3);
        shareInfo.m1951h(str2);
        shareInfo.m1959d(getString(R.string.from_ttpod));
        return shareInfo;
    }

    public static WebSlidingClosableFragment instantiate(String str, String str2, String str3, boolean z, boolean z2) {
        WebSlidingClosableFragment webSlidingClosableFragment = new WebSlidingClosableFragment();
        Bundle bundle = new Bundle();
        bundle.putString(WebFragment.EXTRA_URL, str);
        bundle.putString(WebFragment.EXTRA_TITLE, str2);
        bundle.putBoolean(WebFragment.EXTRA_HINT_BANNER_SHOW, z2);
        bundle.putBoolean(WebFragment.EXTRA_IS_SHOW_PLAY_CONTROL_BAR, z);
        bundle.putString(EXTRA_PIC_URL, str3);
        webSlidingClosableFragment.setArguments(bundle);
        return webSlidingClosableFragment;
    }

    public static WebSlidingClosableFragment instantiate(String str, String str2, String str3, boolean z) {
        return instantiate(str, str2, str3, z, false);
    }

    public static WebSlidingClosableFragment instantiate(Bundle bundle) {
        WebSlidingClosableFragment webSlidingClosableFragment = new WebSlidingClosableFragment();
        webSlidingClosableFragment.setArguments(bundle);
        return webSlidingClosableFragment;
    }
}
