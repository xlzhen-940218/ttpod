package com.sds.android.ttpod.activities.setting;

import android.os.Bundle;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.settings.SettingEntryFragment;

/* loaded from: classes.dex */
public class SettingEntryActivity extends SlidingClosableActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(R.string.setting);
        setContentView(R.layout.activity_lightweight_entry);
        getActionBarController().m7179d();
        setLaunchFragmentAttr(R.id.layout_entry, R.anim.slide_in_right, R.anim.slide_out_right);
        setPrimaryFragment(new SettingEntryFragment());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        PopupsUtils.m6761a();
    }
}
