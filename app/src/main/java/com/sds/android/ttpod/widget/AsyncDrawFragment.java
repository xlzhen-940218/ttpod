package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/* loaded from: classes.dex */
public class AsyncDrawFragment extends FrameLayout {

    /* renamed from: a */
    private Handler f7478a;

    public AsyncDrawFragment(Context context) {
        super(context);
        this.f7478a = new Handler();
    }

    public AsyncDrawFragment(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7478a = new Handler();
    }

    public AsyncDrawFragment(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7478a = new Handler();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(final Canvas canvas) {
        try {
            super.dispatchDraw(canvas);
        } catch (StackOverflowError e) {
            e.printStackTrace();
            this.f7478a.post(new Runnable() { // from class: com.sds.android.ttpod.widget.AsyncDrawFragment.1
                @Override // java.lang.Runnable
                public void run() {
                    AsyncDrawFragment.super.dispatchDraw(canvas);
                }
            });
        }
    }
}
