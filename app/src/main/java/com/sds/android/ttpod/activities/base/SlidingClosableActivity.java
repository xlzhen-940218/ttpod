package com.sds.android.ttpod.activities.base;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.ttpod.widget.SlidingClosableRelativeLayout;

/* loaded from: classes.dex */
public class SlidingClosableActivity extends ActionBarActivity {
    private SlidingClosableRelativeLayout mSlidingClosableRelativeLayout;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSlidingClosableRelativeLayout = new SlidingClosableRelativeLayout(this);
        this.mSlidingClosableRelativeLayout.setSlidingCloseMode(3);
        this.mSlidingClosableRelativeLayout.setOnSlidingCloseListener(new SlidingClosableRelativeLayout.InterfaceC2237a() { // from class: com.sds.android.ttpod.activities.base.SlidingClosableActivity.1
            @Override // com.sds.android.ttpod.widget.SlidingClosableRelativeLayout.InterfaceC2237a
            /* renamed from: a */
            public void mo1483a() {
                if (SlidingClosableActivity.this.mSlidingClosableRelativeLayout != null) {
                    SlidingClosableActivity.this.mSlidingClosableRelativeLayout.setVisibility(View.GONE);
                    SlidingClosableActivity.super.finish();
                    SlidingClosableActivity.this.overridePendingTransition(0, 0);
                    return;
                }
                SlidingClosableActivity.super.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ActionBarActivity
    public View buildContentView(View view) {
        this.mSlidingClosableRelativeLayout.addView(super.buildContentView(view), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return this.mSlidingClosableRelativeLayout;
    }

    public SlidingClosableRelativeLayout getSlidingContainer() {
        return this.mSlidingClosableRelativeLayout;
    }

    public void setSlidingCloseMode(int i) {
        this.mSlidingClosableRelativeLayout.setSlidingCloseMode(i);
    }

    protected boolean needFinishAnimation() {
        return true;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.app.Activity
    public void finish() {
        if (this.mSlidingClosableRelativeLayout != null && needFinishAnimation()) {
            setSlidingCloseMode(2);
            this.mSlidingClosableRelativeLayout.m1520a(true);
            return;
        }
        super.finish();
    }
}
