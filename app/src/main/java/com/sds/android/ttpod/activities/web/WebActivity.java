package com.sds.android.ttpod.activities.web;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.fragment.WebFragment;
import com.sds.android.ttpod.framework.base.BaseFragment;

@Deprecated
/* loaded from: classes.dex */
public class WebActivity extends SlidingClosableActivity {
    private static final String TAG = "WebActivity";

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.framelayout_container);
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(WebFragment.EXTRA_URL, intent.getData().toString());
            bundle2.putAll(intent.getExtras());
            String string = bundle2.getString(WebFragment.EXTRA_TITLE);
            ActionBarController actionBarController = getActionBarController();
            if (StringUtils.isEmpty(string)) {
                string = "";
            }
            actionBarController.m7193a((CharSequence) string);
            if (!bundle2.getBoolean(WebFragment.EXTRA_ENABLE_SLIDING_CLOSABLE, true)) {
                setSlidingCloseMode(0);
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container, (BaseFragment) Fragment.instantiate(this, WebFragment.class.getName(), bundle2)).commitAllowingStateLoss();
        }
    }
}
