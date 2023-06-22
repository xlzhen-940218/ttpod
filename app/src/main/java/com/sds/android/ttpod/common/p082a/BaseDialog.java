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
    private ViewGroup f3553d;

    /* renamed from: e */
    private ViewGroup f3554e;

    /* renamed from: f */
    private TextView f3555f;

    /* renamed from: g */
    private Button f3556g;

    /* renamed from: h */
    private View f3557h;

    /* renamed from: i */
    private Button f3558i;

    /* renamed from: j */
    private Button f3559j;

    /* renamed from: k */
    private Button f3560k;

    /* renamed from: l */
    private InterfaceC1064a f3561l;

    /* renamed from: m */
    private InterfaceC1064a f3562m;

    /* renamed from: n */
    private InterfaceC1064a f3563n;

    /* renamed from: o */
    private InterfaceC1064a f3564o;

    /* renamed from: p */
    private boolean f3565p;

    /* renamed from: q */
    private DialogInterface.OnCancelListener f3566q;

    /* renamed from: r */
    private DialogInterface.OnDismissListener f3567r;

    /* renamed from: s */
    private final DialogInterface.OnDismissListener f3568s;

    /* compiled from: BaseDialog.java */
    /* renamed from: com.sds.android.ttpod.common.a.a$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1064a<T> {
        /* renamed from: a */
        void mo2038a(T t);
    }

    /* renamed from: a */
    protected abstract View mo2034a(Context context);

    /* renamed from: a */
    protected abstract <T> T mo2037a();

    public BaseDialog(Context context, int i) {
        super(context, i);
        this.f3565p = true;
        this.f3566q = new DialogInterface.OnCancelListener() { // from class: com.sds.android.ttpod.common.a.a.2
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                if (BaseDialog.this.f3563n != null) {
                    BaseDialog.this.f3563n.mo2038a(BaseDialog.this.mo2037a());
                }
            }
        };
        this.f3568s = new DialogInterface.OnDismissListener() { // from class: com.sds.android.ttpod.common.a.a.3
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                if (BaseDialog.this.f3567r != null) {
                    BaseDialog.this.f3567r.onDismiss(dialogInterface);
                }
                AutoDelloc.m7232a((BaseDialog) dialogInterface);
            }
        };
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        this.mainDialog = View.inflate(getContext(), R.layout.dialog_main, null);
        this.headerDialog = (ViewGroup) this.mainDialog.findViewById(R.id.dialog_header);
        this.notificationDialog = (ViewGroup) this.mainDialog.findViewById(R.id.dialog_notification);
        this.f3553d = (ViewGroup) this.mainDialog.findViewById(R.id.dialog_body);
        this.f3554e = (ViewGroup) this.mainDialog.findViewById(R.id.dialog_footer);
        this.f3555f = (TextView) this.headerDialog.findViewById(R.id.title);
        this.f3556g = (Button) this.headerDialog.findViewById(R.id.header_button);
        this.f3557h = this.headerDialog.findViewById(R.id.header_button_divider);
        Button button = (Button) this.f3554e.findViewById(R.id.button_left);
        Button button2 = (Button) this.f3554e.findViewById(R.id.button_right);
        this.f3559j = (Button) this.f3554e.findViewById(R.id.button_middle);
        if (m7256b()) {
            this.f3558i = button;
            this.f3560k = button2;
        } else {
            this.f3558i = button2;
            this.f3560k = button;
        }
        m7250c();
        View mo6810d = mo6810d();
        if (mo6810d != null) {
            this.notificationDialog.addView(mo6810d);
        }
        this.f3553d.addView(mo2034a(getContext()));
        setContentView(this.mainDialog);
        setOnCancelListener(this.f3566q);
        super.setOnDismissListener(this.f3568s);
    }

    public BaseDialog(Context context) {
        this(context, R.style.Theme_Dialog);
    }

    /* renamed from: b */
    private boolean m7256b() {
        return !SDKVersionUtils.m8368f();
    }

    /* renamed from: c */
    private void m7250c() {
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.common.a.a.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view != BaseDialog.this.f3556g || BaseDialog.this.f3561l == null) {
                    if (view != BaseDialog.this.f3558i || BaseDialog.this.f3562m == null) {
                        if (view != BaseDialog.this.f3560k || BaseDialog.this.f3563n == null) {
                            if (view == BaseDialog.this.f3559j && BaseDialog.this.f3564o != null) {
                                BaseDialog.this.f3564o.mo2038a(BaseDialog.this.mo2037a());
                            }
                        } else {
                            BaseDialog.this.f3563n.mo2038a(BaseDialog.this.mo2037a());
                        }
                    } else {
                        BaseDialog.this.f3562m.mo2038a(BaseDialog.this.mo2037a());
                    }
                } else {
                    BaseDialog.this.f3561l.mo2038a(BaseDialog.this.mo2037a());
                }
                if (view == BaseDialog.this.f3560k || BaseDialog.this.f3565p) {
                    BaseDialog.this.dismiss();
                }
            }
        };
        this.f3556g.setOnClickListener(onClickListener);
        this.f3558i.setOnClickListener(onClickListener);
        this.f3560k.setOnClickListener(onClickListener);
        this.f3559j.setOnClickListener(onClickListener);
        this.f3558i.setText(R.string.ok);
        this.f3560k.setText(R.string.cancel);
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
        this.f3554e.setVisibility(z ? View.VISIBLE : View.GONE);
    }

    /* renamed from: a */
    public void m7264a(int i) {
        this.mainDialog.setBackgroundResource(i);
        this.headerDialog.setBackgroundResource(i);
        this.f3554e.setBackgroundResource(i);
        this.f3553d.setBackgroundResource(i);
    }

    /* renamed from: a */
    public void m7262a(int i, InterfaceC1064a interfaceC1064a) {
        this.f3557h.setVisibility(View.VISIBLE);
        m7263a(3, 0, i, interfaceC1064a);
    }

    /* renamed from: b */
    public void m7254b(int i, InterfaceC1064a interfaceC1064a) {
        this.f3558i.setVisibility(View.GONE);
        this.f3560k.setVisibility(View.GONE);
        m7263a(2, 0, i, interfaceC1064a);
    }

    /* renamed from: a */
    public void m7261a(int i, InterfaceC1064a interfaceC1064a, int i2, InterfaceC1064a interfaceC1064a2) {
        this.f3559j.setVisibility(View.GONE);
        m7263a(0, 0, i, interfaceC1064a);
        m7263a(1, 0, i2, interfaceC1064a2);
    }

    /* renamed from: a */
    public void m7263a(int i, int i2, int i3, InterfaceC1064a interfaceC1064a) {
        Button button;
        switch (i) {
            case 0:
                this.f3562m = interfaceC1064a;
                button = this.f3558i;
                break;
            case 1:
                this.f3563n = interfaceC1064a;
                button = this.f3560k;
                break;
            case 2:
            default:
                this.f3564o = interfaceC1064a;
                button = this.f3559j;
                break;
            case 3:
                this.f3561l = interfaceC1064a;
                button = this.f3556g;
                break;
        }
        button.setVisibility(i2);
        if (i3 != 0) {
            button.setText(i3);
        }
    }

    /* renamed from: b */
    public void m7255b(int i) {
        this.f3558i.setText(i);
    }

    /* renamed from: a */
    public void m7258a(String str) {
        this.f3558i.setText(str);
    }

    /* renamed from: b */
    public void m7252b(String str) {
        this.f3560k.setText(str);
    }

    @Override // android.app.Dialog
    public void setTitle(int i) {
        setTitle(getContext().getString(i));
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.f3555f.setText(charSequence);
    }

    /* renamed from: c */
    public void m7248c(boolean z) {
        m7260a(this.f3558i, z);
    }

    /* renamed from: d */
    public void m7246d(boolean z) {
        m7260a(this.f3560k, z);
    }

    /* renamed from: e */
    public void m7244e(boolean z) {
        m7260a(this.f3559j, z);
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
        this.f3567r = onDismissListener;
    }
}
