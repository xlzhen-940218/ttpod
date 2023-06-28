package com.sds.android.ttpod.framework.modules.skin.view;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;


/* loaded from: classes.dex */
public class TTPodButton extends androidx.appcompat.widget.AppCompatImageView {

    /* renamed from: a */
    private long elapsedRealtime;

    /* renamed from: b */
    private int clickCount;

    /* renamed from: c */
    private RepeatListener repeatListener;

    /* renamed from: d */
    private long repeatInterval;

    /* renamed from: e */
    private Runnable clickRunnable;

    /* renamed from: com.sds.android.ttpod.framework.modules.skin.view.TTPodButton$a */
    /* loaded from: classes.dex */
    public interface RepeatListener {
        /* renamed from: a */
        void onClick(View view, long differenceTime, int clickCount);
    }

    public TTPodButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.repeatInterval = 100L;
        this.clickRunnable = new Runnable() { // from class: com.sds.android.ttpod.framework.modules.skin.view.TTPodButton.1
            @Override // java.lang.Runnable
            public void run() {
                TTPodButton.this.onClick(false);
                if (TTPodButton.this.isPressed()) {
                    TTPodButton.this.postDelayed(this, TTPodButton.this.repeatInterval);
                }
            }
        };
    }

    public TTPodButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.repeatInterval = 100L;
        this.clickRunnable = new Runnable() { // from class: com.sds.android.ttpod.framework.modules.skin.view.TTPodButton.1
            @Override // java.lang.Runnable
            public void run() {
                TTPodButton.this.onClick(false);
                if (TTPodButton.this.isPressed()) {
                    TTPodButton.this.postDelayed(this, TTPodButton.this.repeatInterval);
                }
            }
        };
    }

    public TTPodButton(Context context) {
        super(context);
        this.repeatInterval = 100L;
        this.clickRunnable = new Runnable() { // from class: com.sds.android.ttpod.framework.modules.skin.view.TTPodButton.1
            @Override // java.lang.Runnable
            public void run() {
                TTPodButton.this.onClick(false);
                if (TTPodButton.this.isPressed()) {
                    TTPodButton.this.postDelayed(this, TTPodButton.this.repeatInterval);
                }
            }
        };
    }

    /* renamed from: a */
    public void setRepeatListener(RepeatListener repeatListener, long repeatInterval) {
        this.repeatListener = repeatListener;
        this.repeatInterval = repeatInterval;
        if (this.repeatListener != null) {
            super.setLongClickable(true);
        }
    }

    public void setRepeatListener(RepeatListener repeatListener) {
        setRepeatListener(repeatListener, 100L);
    }

    @Override // android.view.View
    public boolean performLongClick() {
        this.elapsedRealtime = SystemClock.elapsedRealtime();
        this.clickCount = 0;
        post(this.clickRunnable);
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
            removeCallbacks(this.clickRunnable);
            if (this.elapsedRealtime != 0) {
                onClick(true);
                this.elapsedRealtime = 0L;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:
                super.onKeyDown(keyCode, keyEvent);
                return true;
            default:
                return super.onKeyDown(keyCode, keyEvent);
        }
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int keyCode, KeyEvent keyEvent) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:
                removeCallbacks(this.clickRunnable);
                if (this.elapsedRealtime != 0) {
                    onClick(true);
                    this.elapsedRealtime = 0L;
                    break;
                }
                break;
        }
        return super.onKeyUp(keyCode, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void onClick(boolean keyUp) {
        int clickCount;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.repeatListener != null) {

            long differenceTime = elapsedRealtime - this.elapsedRealtime;
            if (keyUp) {
                clickCount = -1;
            } else {
                clickCount = this.clickCount;
                this.clickCount = clickCount + 1;
            }
            repeatListener.onClick(this, differenceTime, clickCount);
        }
    }

    public long getRepeatInterval() {
        return this.repeatInterval;
    }
}
