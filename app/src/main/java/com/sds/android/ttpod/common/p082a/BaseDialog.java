package com.sds.android.ttpod.common.p082a;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p083b.AutoDelloc;

/* renamed from: com.sds.android.ttpod.common.a.a */
/* loaded from: classes.dex */
public abstract class BaseDialog extends Dialog {

    /* renamed from: a */
    private View mainDialog;

    /* renamed from: b */
    private ViewGroup headerDialog;

    /* renamed from: c */
    private ViewGroup notificationDialog;

    /* renamed from: d */
    private ViewGroup bodyView;

    /* renamed from: e */
    private ViewGroup footerView;

    /* renamed from: f */
    private TextView titleTextView;

    /* renamed from: g */
    private Button headerButton;

    /* renamed from: h */
    private View dividerButton;

    /* renamed from: i */
    private Button okButton;

    /* renamed from: j */
    private Button middleButton;

    /* renamed from: k */
    private Button cancelButton;

    /* renamed from: l */
    private OnClickListener headerOnClickListener;

    /* renamed from: m */
    private OnClickListener okOnClickListener;

    /* renamed from: n */
    private OnClickListener cancelOnClickListener;

    /* renamed from: o */
    private OnClickListener middleOnClickListener;

    /* renamed from: p */
    private boolean f3565p;

    /* renamed from: q */
    private DialogInterface.OnCancelListener onCancelListener;

    /* renamed from: r */
    private DialogInterface.OnDismissListener onDismissListener;

    /* renamed from: s */
    private final DialogInterface.OnDismissListener selfOnDismissListener;

    /* compiled from: BaseDialog.java */
    /* renamed from: com.sds.android.ttpod.common.a.a$a */
    /* loaded from: classes.dex */
    public interface OnClickListener<T> {
        /* renamed from: a */
        void onClick(T t);
    }

    /* renamed from: a */
    protected abstract View inflate(Context context);

    /* renamed from: a */
    protected abstract <T> T getDialog();

    public BaseDialog(Context context, int i) {
        super(context, i);
        this.f3565p = true;
        this.onCancelListener = new DialogInterface.OnCancelListener() { // from class: com.sds.android.ttpod.common.a.a.2
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                if (BaseDialog.this.cancelOnClickListener != null) {
                    BaseDialog.this.cancelOnClickListener.onClick(BaseDialog.this.getDialog());
                }
            }
        };
        this.selfOnDismissListener = new DialogInterface.OnDismissListener() { // from class: com.sds.android.ttpod.common.a.a.3
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                if (BaseDialog.this.onDismissListener != null) {
                    BaseDialog.this.onDismissListener.onDismiss(dialogInterface);
                }
                AutoDelloc.m7232a((BaseDialog) dialogInterface);
            }
        };
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        this.mainDialog = View.inflate(getContext(), R.layout.dialog_main, null);
        this.headerDialog = (ViewGroup) this.mainDialog.findViewById(R.id.dialog_header);
        this.notificationDialog = (ViewGroup) this.mainDialog.findViewById(R.id.dialog_notification);
        this.bodyView = (ViewGroup) this.mainDialog.findViewById(R.id.dialog_body);
        this.footerView = (ViewGroup) this.mainDialog.findViewById(R.id.dialog_footer);
        this.titleTextView = (TextView) this.headerDialog.findViewById(R.id.title);
        this.headerButton = (Button) this.headerDialog.findViewById(R.id.header_button);
        this.dividerButton = this.headerDialog.findViewById(R.id.header_button_divider);
        Button leftButton = (Button) this.footerView.findViewById(R.id.button_left);
        Button rightButton = (Button) this.footerView.findViewById(R.id.button_right);
        this.middleButton = (Button) this.footerView.findViewById(R.id.button_middle);
        if (newAndroidVersion()) {
            this.okButton = leftButton;
            this.cancelButton = rightButton;
        } else {
            this.okButton = rightButton;
            this.cancelButton = leftButton;
        }
        setListener();
        View mo6810d = mo6810d();
        if (mo6810d != null) {
            this.notificationDialog.addView(mo6810d);
        }
        this.bodyView.addView(inflate(getContext()));
        setContentView(this.mainDialog);
        setOnCancelListener(this.onCancelListener);
        super.setOnDismissListener(this.selfOnDismissListener);
    }

    public BaseDialog(Context context) {
        this(context, R.style.Theme_Dialog);
    }

    /* renamed from: b */
    private boolean newAndroidVersion() {
        return !SDKVersionUtils.sdkThan14();
    }

    /* renamed from: c */
    private void setListener() {
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.common.a.a.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view != BaseDialog.this.headerButton || BaseDialog.this.headerOnClickListener == null) {
                    if (view != BaseDialog.this.okButton || BaseDialog.this.okOnClickListener == null) {
                        if (view != BaseDialog.this.cancelButton || BaseDialog.this.cancelOnClickListener == null) {
                            if (view == BaseDialog.this.middleButton && BaseDialog.this.middleOnClickListener != null) {
                                BaseDialog.this.middleOnClickListener.onClick(BaseDialog.this.getDialog());
                            }
                        } else {
                            BaseDialog.this.cancelOnClickListener.onClick(BaseDialog.this.getDialog());
                        }
                    } else {
                        BaseDialog.this.okOnClickListener.onClick(BaseDialog.this.getDialog());
                    }
                } else {
                    BaseDialog.this.headerOnClickListener.onClick(BaseDialog.this.getDialog());
                }
                if (view == BaseDialog.this.cancelButton || BaseDialog.this.f3565p) {
                    BaseDialog.this.dismiss();
                }
            }
        };
        this.headerButton.setOnClickListener(onClickListener);
        this.okButton.setOnClickListener(onClickListener);
        this.cancelButton.setOnClickListener(onClickListener);
        this.middleButton.setOnClickListener(onClickListener);
        this.okButton.setText(R.string.ok);
        this.cancelButton.setText(R.string.cancel);
    }

    /* renamed from: d */
    protected View mo6810d() {
        return null;
    }

    /* renamed from: a */
    public void m7257a(boolean z) {
        this.headerDialog.setVisibility(z ? View.VISIBLE : View.GONE);
    }

    /* renamed from: b */
    public void m7251b(boolean z) {
        this.footerView.setVisibility(z ? View.VISIBLE : View.GONE);
    }

    /* renamed from: a */
    public void m7264a(int i) {
        this.mainDialog.setBackgroundResource(i);
        this.headerDialog.setBackgroundResource(i);
        this.footerView.setBackgroundResource(i);
        this.bodyView.setBackgroundResource(i);
    }

    /* renamed from: a */
    public void m7262a(int i, OnClickListener onClickListener) {
        this.dividerButton.setVisibility(View.VISIBLE);
        m7263a(3, 0, i, onClickListener);
    }

    /* renamed from: b */
    public void m7254b(int i, OnClickListener onClickListener) {
        this.okButton.setVisibility(View.GONE);
        this.cancelButton.setVisibility(View.GONE);
        m7263a(2, 0, i, onClickListener);
    }

    /* renamed from: a */
    public void m7261a(int i, OnClickListener onClickListener, int i2, OnClickListener onClickListener2) {
        this.middleButton.setVisibility(View.GONE);
        m7263a(0, 0, i, onClickListener);
        m7263a(1, 0, i2, onClickListener2);
    }

    /* renamed from: a */
    public void m7263a(int i, int i2, int i3, OnClickListener onClickListener) {
        Button button;
        switch (i) {
            case 0:
                this.okOnClickListener = onClickListener;
                button = this.okButton;
                break;
            case 1:
                this.cancelOnClickListener = onClickListener;
                button = this.cancelButton;
                break;
            case 2:
            default:
                this.middleOnClickListener = onClickListener;
                button = this.middleButton;
                break;
            case 3:
                this.headerOnClickListener = onClickListener;
                button = this.headerButton;
                break;
        }
        button.setVisibility(i2);
        if (i3 != 0) {
            button.setText(i3);
        }
    }

    /* renamed from: b */
    public void m7255b(int i) {
        this.okButton.setText(i);
    }

    /* renamed from: a */
    public void m7258a(String str) {
        this.okButton.setText(str);
    }

    /* renamed from: b */
    public void m7252b(String str) {
        this.cancelButton.setText(str);
    }

    @Override // android.app.Dialog
    public void setTitle(int i) {
        setTitle(getContext().getString(i));
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.titleTextView.setText(charSequence);
    }

    /* renamed from: c */
    public void m7248c(boolean z) {
        m7260a(this.okButton, z);
    }

    /* renamed from: d */
    public void m7246d(boolean z) {
        m7260a(this.cancelButton, z);
    }

    /* renamed from: e */
    public void m7244e(boolean z) {
        m7260a(this.middleButton, z);
    }

    /* renamed from: f */
    public void m7242f(boolean z) {
        this.f3565p = z;
    }

    @Override // android.app.Dialog
    public void show() {
        m7248c(true);
        super.show();
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m7260a(Button button, boolean z) {
        button.setBackgroundResource(z ? R.drawable.xml_dialog_footer_button_background_highlight : R.drawable.xml_dialog_footer_button_background);
        button.setTextColor(getContext().getResources().getColor(z ? R.color.dialog_footer_button_text_highlight : R.color.dialog_footer_button_text));
    }

    @Override // android.app.Dialog
    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }
}
