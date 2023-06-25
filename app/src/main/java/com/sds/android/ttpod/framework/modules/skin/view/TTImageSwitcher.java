package com.sds.android.ttpod.framework.modules.skin.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageSwitcher;
import android.widget.ViewSwitcher;

import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.modules.skin.p130c.ClipBitmapDrawable;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.view.d */
/* loaded from: classes.dex */
public class TTImageSwitcher extends ImageSwitcher implements ViewSwitcher.ViewFactory {

    /* renamed from: a */
    private boolean f6914a;

    /* renamed from: b */
    private boolean f6915b;

    /* renamed from: c */
    private boolean f6916c;

    /* renamed from: d */
    private boolean f6917d;

    /* renamed from: e */
    private boolean f6918e;

    /* renamed from: f */
    private final FrameLayout.LayoutParams f6919f;

    /* renamed from: g */
    private ArrayList<Bitmap> f6920g;

    /* renamed from: h */
    private int f6921h;

    /* renamed from: i */
    private Drawable f6922i;

    /* renamed from: j */
    private Handler f6923j;

    /* renamed from: k */
    private final BroadcastReceiver f6924k;

    public TTImageSwitcher(Context context) {
        super(context);
        this.f6917d = true;
        this.f6918e = true;
        this.f6919f = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.f6920g = new ArrayList<>();
        this.f6923j = new Handler() { // from class: com.sds.android.ttpod.framework.modules.skin.view.d.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                LogUtils.debug("TTImageSwitcher", "handleMessage lookLyricPic what=%d", Integer.valueOf(i));
                if (i == 1) {
                    TTImageSwitcher.this.f6916c = true;
                    TTImageSwitcher.this.m3344b();
                    TTImageSwitcher.this.m3341c();
                } else if (i == 2) {
                    TTImageSwitcher.this.m3344b();
                    TTImageSwitcher.this.m3341c();
                } else if (i == 3) {
                    Bitmap bitmap = (Bitmap) message.obj;
                    LogUtils.debug("TTImageSwitcher", "handleMessage lookLyricPic USE_SINGLE_BITMAP bitmap width=%d height=%d", Integer.valueOf(bitmap != null ? bitmap.getWidth() : -1), Integer.valueOf(bitmap != null ? bitmap.getHeight() : -1));
                    View nextView = TTImageSwitcher.this.getNextView();
                    if (bitmap == null) {
                        nextView.setBackgroundDrawable(TTImageSwitcher.this.f6922i);
                    } else {
                        nextView.setBackgroundDrawable(new ClipBitmapDrawable(TTImageSwitcher.this.getResources(), bitmap));
                    }
                    TTImageSwitcher.this.showNext();
                }
            }
        };
        this.f6924k = new BroadcastReceiver() { // from class: com.sds.android.ttpod.framework.modules.skin.view.d.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                if ("android.intent.action.SCREEN_OFF".equals(action)) {
                    TTImageSwitcher.this.f6917d = false;
                    TTImageSwitcher.this.m3347a();
                } else if ("android.intent.action.USER_PRESENT".equals(action)) {
                    TTImageSwitcher.this.f6917d = true;
                    TTImageSwitcher.this.m3347a();
                } else if (Action.ACTION_AUTO_PLAY_ARTIST_IMAGE.equals(action)) {
                    TTImageSwitcher.this.setAllowStart(intent.getBooleanExtra("state", true));
                }
            }
        };
    }

    public void setAllowStart(boolean z) {
        if (z != this.f6918e) {
            this.f6918e = z;
            if (this.f6918e) {
                m3339d();
                return;
            }
            this.f6923j.removeCallbacksAndMessages(null);
            this.f6921h = -1;
            m3344b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m3347a() {
        boolean z = this.f6915b && this.f6917d && this.f6916c && this.f6918e;
        if (z != this.f6914a) {
            if (z) {
                m3341c();
            } else {
                this.f6923j.removeMessages(2);
            }
            this.f6914a = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m3344b() {
        this.f6921h++;
        int size = this.f6920g.size();
        if (this.f6921h >= size) {
            this.f6921h = 0;
        }
        View nextView = getNextView();
        if (this.f6921h < size) {
            nextView.setBackgroundDrawable(new ClipBitmapDrawable(getResources(), this.f6920g.get(this.f6921h)));
        } else {
            nextView.setBackgroundDrawable(this.f6922i);
        }
        showNext();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m3341c() {
        if ((this.f6915b && this.f6917d && this.f6916c && this.f6918e) && this.f6920g.size() > 1) {
            this.f6923j.sendMessageDelayed(this.f6923j.obtainMessage(2), 15000L);
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        LogUtils.debug("TTImageSwitcher", "setImageBitmap lookLyricPic bitmap width=%d height=%d", Integer.valueOf(bitmap != null ? bitmap.getWidth() : -1), Integer.valueOf(bitmap != null ? bitmap.getHeight() : -1));
        this.f6923j.removeCallbacksAndMessages(null);
        Message obtainMessage = this.f6923j.obtainMessage(3);
        obtainMessage.obj = bitmap;
        this.f6923j.sendMessageDelayed(obtainMessage, 200L);
    }

    /* renamed from: d */
    private void m3339d() {
        this.f6916c = false;
        this.f6921h = -1;
        this.f6923j.removeCallbacksAndMessages(null);
        this.f6923j.sendMessageDelayed(this.f6923j.obtainMessage(1), 200L);
    }

    @Override // android.widget.ViewSwitcher.ViewFactory
    public View makeView() {
        View view = new View(getContext());
        view.setLayoutParams(this.f6919f);
        return view;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        try {
            super.onDetachedFromWindow();
            this.f6915b = false;
            getContext().unregisterReceiver(this.f6924k);
            m3347a();
            Iterator<Bitmap> it = this.f6920g.iterator();
            while (it.hasNext()) {
                Bitmap next = it.next();
                if (next != null && !next.isRecycled()) {
                    next.recycle();
                }
            }
            this.f6920g.clear();
            this.f6923j.removeMessages(2);
            this.f6923j.removeMessages(1);
            this.f6923j.removeMessages(3);
        } catch (IllegalArgumentException e) {
            this.f6923j.removeMessages(2);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction(Action.ACTION_AUTO_PLAY_ARTIST_IMAGE);
        getContext().registerReceiver(this.f6924k, intentFilter);
        m3347a();
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.f6915b = i == 0;
        m3347a();
    }

    public void setDefaultImageDrawable(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            this.f6922i = new ClipBitmapDrawable(getResources(), ((BitmapDrawable) drawable).getBitmap());
        } else {
            this.f6922i = drawable;
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() == View.GONE) {
                childAt.setVisibility(View.INVISIBLE);
            }
        }
        super.onMeasure(i, i2);
    }
}
