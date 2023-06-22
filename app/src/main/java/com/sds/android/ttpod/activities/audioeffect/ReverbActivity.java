package com.sds.android.ttpod.activities.audioeffect;

import android.os.Bundle;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.fragment.audioeffect.ReverbFragment;

/* loaded from: classes.dex */
public class ReverbActivity extends SlidingClosableActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(R.string.effect_reverb_label);
        setContentView(R.layout.activity_reverb);
        initReverbFragment();
        ActionBarUtils.m8130a(getActionBarController());
    }

    private void initReverbFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.reverbfragment_content, new ReverbFragment()).commit();
    }
}
