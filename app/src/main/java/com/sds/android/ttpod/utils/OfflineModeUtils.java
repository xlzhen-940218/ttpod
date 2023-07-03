package com.sds.android.ttpod.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.storage.environment.Preferences;

/* renamed from: com.sds.android.ttpod.a.p */
/* loaded from: classes.dex */
public class OfflineModeUtils {

    /* compiled from: OfflineModeUtils.java */
    /* renamed from: com.sds.android.ttpod.a.p$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0635a {
        /* renamed from: a */
        void mo5379a();
    }

    /* renamed from: a */
    public static View m8254a(View view) {
        return m8253a(view, (InterfaceC0635a) null);
    }

    /* renamed from: a */
    public static View m8253a(View view, final InterfaceC0635a interfaceC0635a) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        View findViewById = viewGroup.findViewById(R.id.offline_mode_frame);
        if (findViewById == null) {
            findViewById = m8252a(viewGroup);
            viewGroup.addView(findViewById, layoutParams);
        }
        findViewById.setVisibility(View.VISIBLE);
        findViewById.setOnTouchListener(new OfflineModeOnTouchListener(view));
        View finalFindViewById = findViewById;
        ((Button) findViewById.findViewById(R.id.button_offline_continue)).setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.a.p.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Preferences.m2814x(false);
                finalFindViewById.setVisibility(View.GONE);
                if (interfaceC0635a != null) {
                    interfaceC0635a.mo5379a();
                }
            }
        });
        return findViewById;
    }

    /* renamed from: a */
    private static View m8252a(ViewGroup viewGroup) {
        return LayoutInflater.from(BaseApplication.getApplication()).inflate(R.layout.layout_offline_mode, viewGroup, false);
    }

    /* renamed from: a */
    public static Dialog m8255a(Context context, final DialogInterface.OnClickListener onClickListener) {
        if (!m8256a()) {
            onClickListener.onClick(null, -1);
            return null;
        }
        final DialogC0636b dialogC0636b = new DialogC0636b(context);
        dialogC0636b.m7261a(R.string.continue_text, new BaseDialog.OnClickListener() { // from class: com.sds.android.ttpod.a.p.2
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a */
            public void onClick(Object obj) {
                Preferences.m2814x(false);
                onClickListener.onClick(dialogC0636b, -1);
            }
        }, R.string.cancel, new BaseDialog.OnClickListener() { // from class: com.sds.android.ttpod.a.p.3
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a */
            public void onClick(Object obj) {
                onClickListener.onClick(dialogC0636b, -2);
            }
        });
        dialogC0636b.m7257a(false);
        dialogC0636b.show();
        return dialogC0636b;
    }

    /* renamed from: a */
    public static boolean m8256a() {
        return EnvironmentUtils.DeviceConfig.getNetworkType() != 2 && Preferences.m3066H();
    }

    /* compiled from: OfflineModeUtils.java */
    /* renamed from: com.sds.android.ttpod.a.p$b */
    /* loaded from: classes.dex */
    public static class DialogC0636b extends BaseDialog {
        public DialogC0636b(Context context) {
            super(context);
        }

        @Override // com.sds.android.ttpod.common.p082a.BaseDialog
        /* renamed from: a */
        protected View inflate(Context context) {
            return View.inflate(context, R.layout.dialog_confirm_offline_mode, null);
        }

        @Override // com.sds.android.ttpod.common.p082a.BaseDialog
        /* renamed from: a */
        protected <T> T getDialog() {
            return null;
        }
    }

    /* compiled from: OfflineModeUtils.java */
    /* renamed from: com.sds.android.ttpod.a.p$c */
    /* loaded from: classes.dex */
    public static class OfflineModeOnTouchListener implements View.OnTouchListener {

        /* renamed from: a */
        private boolean moveAction = false;

        /* renamed from: b */
        private View touchView;

        public OfflineModeOnTouchListener(View view) {
            this.touchView = view;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN:
                    this.moveAction = false;
                    return this.touchView.onTouchEvent(motionEvent);
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_POINTER_UP:
                    if (this.moveAction) {
                        this.moveAction = false;
                        return this.touchView.onTouchEvent(motionEvent);
                    }
                    return false;
                case MotionEvent.ACTION_MOVE:
                    this.moveAction = true;
                    return this.touchView.onTouchEvent(motionEvent);
                case MotionEvent.ACTION_OUTSIDE:
                default:
                    return false;
            }
        }
    }
}
