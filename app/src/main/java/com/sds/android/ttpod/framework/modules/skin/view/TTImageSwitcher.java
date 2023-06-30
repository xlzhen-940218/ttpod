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

    private static final int RESET_SWITCH = 1;
    /* renamed from: a */
    private boolean f6914a;

    /* renamed from: b */
    private boolean visible;

    /* renamed from: c */
    private boolean resetSwitch;

    /**
     * 用户在使用
     */
    private boolean userPresent;

    /* renamed from: e */
    private boolean allowStart;

    /* renamed from: f */
    private final FrameLayout.LayoutParams layoutParams;

    /* renamed from: g */
    private ArrayList<Bitmap> bitmapArrayList;

    /* renamed from: h */
    private int index;

    /* renamed from: i */
    private Drawable f6922i;

    /* renamed from: j */
    private Handler handler;

    /* renamed from: k */
    private final BroadcastReceiver broadcastReceiver;

    public TTImageSwitcher(Context context) {
        super(context);
        this.userPresent = true;
        this.allowStart = true;
        this.layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.bitmapArrayList = new ArrayList<>();
        this.handler = new Handler() { // from class: com.sds.android.ttpod.framework.modules.skin.view.d.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                LogUtils.debug("TTImageSwitcher", "handleMessage lookLyricPic what=%d", Integer.valueOf(i));
                if (i == RESET_SWITCH) {
                    TTImageSwitcher.this.resetSwitch = true;
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
                        nextView.setBackground(TTImageSwitcher.this.f6922i);
                    } else {
                        nextView.setBackground(new ClipBitmapDrawable(TTImageSwitcher.this.getResources(), bitmap));
                    }
                    TTImageSwitcher.this.showNext();
                }
            }
        };
        this.broadcastReceiver = new BroadcastReceiver() { // from class: com.sds.android.ttpod.framework.modules.skin.view.d.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                if ("android.intent.action.SCREEN_OFF".equals(action)) {
                    TTImageSwitcher.this.userPresent = false;
                    TTImageSwitcher.this.m3347a();
                } else if ("android.intent.action.USER_PRESENT".equals(action)) {
                    TTImageSwitcher.this.userPresent = true;
                    TTImageSwitcher.this.m3347a();
                } else if (Action.ACTION_AUTO_PLAY_ARTIST_IMAGE.equals(action)) {
                    TTImageSwitcher.this.setAllowStart(intent.getBooleanExtra("state", true));
                }
            }
        };
    }

    public void setAllowStart(boolean z) {
        if (z != this.allowStart) {
            this.allowStart = z;
            if (this.allowStart) {
                resetSwitch();
                return;
            }
            this.handler.removeCallbacksAndMessages(null);
            this.index = -1;
            m3344b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m3347a() {
        boolean z = this.visible && this.userPresent && this.resetSwitch && this.allowStart;
        if (z != this.f6914a) {
            if (z) {
                m3341c();
            } else {
                this.handler.removeMessages(2);
            }
            this.f6914a = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m3344b() {
        this.index++;
        int size = this.bitmapArrayList.size();
        if (this.index >= size) {
            this.index = 0;
        }
        View nextView = getNextView();
        if (this.index < size) {
            nextView.setBackground(new ClipBitmapDrawable(getResources(), this.bitmapArrayList.get(this.index)));
        } else {
            nextView.setBackground(this.f6922i);
        }
        showNext();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m3341c() {
        if ((this.visible && this.userPresent && this.resetSwitch && this.allowStart) && this.bitmapArrayList.size() > 1) {
            this.handler.sendMessageDelayed(this.handler.obtainMessage(2), 15000L);
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        LogUtils.debug("TTImageSwitcher", "setImageBitmap lookLyricPic bitmap width=%d height=%d", Integer.valueOf(bitmap != null ? bitmap.getWidth() : -1), Integer.valueOf(bitmap != null ? bitmap.getHeight() : -1));
        this.handler.removeCallbacksAndMessages(null);
        Message obtainMessage = this.handler.obtainMessage(3);
        obtainMessage.obj = bitmap;
        this.handler.sendMessageDelayed(obtainMessage, 200L);
    }

    /* renamed from: d */
    private void resetSwitch() {
        this.resetSwitch = false;
        this.index = -1;
        this.handler.removeCallbacksAndMessages(null);
        this.handler.sendMessageDelayed(this.handler.obtainMessage(RESET_SWITCH), 200L);
    }

    @Override // android.widget.ViewSwitcher.ViewFactory
    public View makeView() {
        View view = new View(getContext());
        view.setLayoutParams(this.layoutParams);
        return view;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        try {
            super.onDetachedFromWindow();
            this.visible = false;
            getContext().unregisterReceiver(this.broadcastReceiver);
            m3347a();
            Iterator<Bitmap> it = this.bitmapArrayList.iterator();
            while (it.hasNext()) {
                Bitmap next = it.next();
                if (next != null && !next.isRecycled()) {
                    next.recycle();
                }
            }
            this.bitmapArrayList.clear();
            this.handler.removeMessages(2);
            this.handler.removeMessages(1);
            this.handler.removeMessages(3);
        } catch (IllegalArgumentException e) {
            this.handler.removeMessages(2);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction(Action.ACTION_AUTO_PLAY_ARTIST_IMAGE);
        getContext().registerReceiver(this.broadcastReceiver, intentFilter);
        m3347a();
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        this.visible = visibility == VISIBLE;
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
