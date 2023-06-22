package com.sds.android.ttpod.framework.modules.skin.view;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


/* loaded from: classes.dex */
public class TTPodButton extends androidx.appcompat.widget.AppCompatImageView {

    /* renamed from: a */
    private long f6874a;

    /* renamed from: b */
    private int f6875b;

    /* renamed from: c */
    private InterfaceC2007a f6876c;

    /* renamed from: d */
    private long f6877d;

    /* renamed from: e */
    private Runnable f6878e;

    /* renamed from: com.sds.android.ttpod.framework.modules.skin.view.TTPodButton$a */
    /* loaded from: classes.dex */
    public interface InterfaceC2007a {
        /* renamed from: a */
        void mo3357a(View view, long j, int i);
    }

    public TTPodButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f6877d = 100L;
        this.f6878e = new Runnable() { // from class: com.sds.android.ttpod.framework.modules.skin.view.TTPodButton.1
            @Override // java.lang.Runnable
            public void run() {
                TTPodButton.this.m3358a(false);
                if (TTPodButton.this.isPressed()) {
                    TTPodButton.this.postDelayed(this, TTPodButton.this.f6877d);
                }
            }
        };
    }

    public TTPodButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f6877d = 100L;
        this.f6878e = new Runnable() { // from class: com.sds.android.ttpod.framework.modules.skin.view.TTPodButton.1
            @Override // java.lang.Runnable
            public void run() {
                TTPodButton.this.m3358a(false);
                if (TTPodButton.this.isPressed()) {
                    TTPodButton.this.postDelayed(this, TTPodButton.this.f6877d);
                }
            }
        };
    }

    public TTPodButton(Context context) {
        super(context);
        this.f6877d = 100L;
        this.f6878e = new Runnable() { // from class: com.sds.android.ttpod.framework.modules.skin.view.TTPodButton.1
            @Override // java.lang.Runnable
            public void run() {
                TTPodButton.this.m3358a(false);
                if (TTPodButton.this.isPressed()) {
                    TTPodButton.this.postDelayed(this, TTPodButton.this.f6877d);
                }
            }
        };
    }

    /* renamed from: a */
    public void m3361a(InterfaceC2007a interfaceC2007a, long j) {
        this.f6876c = interfaceC2007a;
        this.f6877d = j;
        if (this.f6876c != null) {
            super.setLongClickable(true);
        }
    }

    public void setRepeatListener(InterfaceC2007a interfaceC2007a) {
        m3361a(interfaceC2007a, 100L);
    }

    @Override // android.view.View
    public boolean performLongClick() {
        this.f6874a = SystemClock.elapsedRealtime();
        this.f6875b = 0;
        post(this.f6878e);
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 1 || action == 3) {
            removeCallbacks(this.f6878e);
            if (this.f6874a != 0) {
                m3358a(true);
                this.f6874a = 0L;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case 23:
            case 66:
                super.onKeyDown(i, keyEvent);
                return true;
            default:
                return super.onKeyDown(i, keyEvent);
        }
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        switch (i) {
            case 23:
            case 66:
                removeCallbacks(this.f6878e);
                if (this.f6874a != 0) {
                    m3358a(true);
                    this.f6874a = 0L;
                    break;
                }
                break;
        }
        return super.onKeyUp(i, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m3358a(boolean z) {
        int i;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.f6876c != null) {
            InterfaceC2007a interfaceC2007a = this.f6876c;
            long j = elapsedRealtime - this.f6874a;
            if (z) {
                i = -1;
            } else {
                i = this.f6875b;
                this.f6875b = i + 1;
            }
            interfaceC2007a.mo3357a(this, j, i);
        }
    }

    public long getRepeatInterval() {
        return this.f6877d;
    }
}
