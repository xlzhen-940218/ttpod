package com.sds.android.ttpod.component.p087d.p088a;

import android.content.Context;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;

/* renamed from: com.sds.android.ttpod.component.d.a.p */
/* loaded from: classes.dex */
public class SeekBarDialog extends BaseDialog {

    /* renamed from: a */
    private TextView f3990a;

    /* renamed from: b */
    private SeekBar f3991b;

    /* renamed from: c */
    private String f3992c;

    /* renamed from: d */
    private int f3993d;

    /* renamed from: e */
    private int f3994e;

    /* renamed from: f */
    private int f3995f;

    /* renamed from: g */
    private int f3996g;

    /* renamed from: h */
    private int f3997h;

    /* renamed from: i */
    private int f3998i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SeekBarDialog(Context context, int i, int i2, int i3, int i4, String str, BaseDialog.InterfaceC1064a<SeekBarDialog> interfaceC1064a) {
        super(context);
        int i5;
        int i6 = 1000;
        this.f3997h = 1;
        this.f3998i = 1;
        if (i3 <= i) {
            throw new IllegalArgumentException("maxProgress must be > minProgress");
        }
        if (i2 < i) {
            throw new IllegalArgumentException("curProgress must be >= minProgress");
        }
        if (i2 > i3) {
            throw new IllegalArgumentException("curProgress must be <= maxProgress");
        }
        if (i4 <= 0) {
            throw new IllegalArgumentException("step must be > 0");
        }
        m7261a(R.string.ok, interfaceC1064a, R.string.cancel, (BaseDialog.InterfaceC1064a) null);
        this.f3992c = str;
        this.f3993d = i2;
        this.f3994e = i3 - i;
        if (this.f3994e <= 1000) {
            this.f3996g = 1000 / this.f3994e;
            this.f3995f = this.f3996g * i;
            i5 = this.f3996g * this.f3993d;
        } else {
            this.f3996g = i4;
            this.f3995f = i;
            i6 = i3 - i;
            i5 = this.f3993d - i;
        }
        this.f3991b.setMax(i6);
        this.f3991b.setProgress(i5);
        m6784e();
        this.f3991b.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.sds.android.ttpod.component.d.a.p.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i7, boolean z) {
                if (SeekBarDialog.this.f3994e <= 1000) {
                    int i8 = (SeekBarDialog.this.f3995f + i7) / SeekBarDialog.this.f3996g;
                    if (SeekBarDialog.this.f3993d != i8) {
                        SeekBarDialog.this.f3993d = i8;
                        SeekBarDialog.this.m6784e();
                        return;
                    }
                    return;
                }
                int i9 = ((i7 / SeekBarDialog.this.f3996g) * SeekBarDialog.this.f3996g) + SeekBarDialog.this.f3995f;
                if (SeekBarDialog.this.f3993d != i9) {
                    SeekBarDialog.this.f3993d = i9;
                    SeekBarDialog.this.m6784e();
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    /* renamed from: a */
    public void m6792a(int i, int i2) {
        this.f3997h = i;
        this.f3998i = i2;
        m6784e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m6784e() {
        String str;
        if (this.f3997h == 1) {
            str = this.f3993d + this.f3992c;
        } else {
            str = ((1.0f * this.f3993d) / this.f3997h) + this.f3992c;
        }
        this.f3990a.setText(str);
    }

    /* renamed from: b */
    public int m6789b() {
        return this.f3993d;
    }

    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: a */
    protected View mo2034a(Context context) {
        View inflate = View.inflate(context, R.layout.dialog_body_seekbar, null);
        this.f3990a = (TextView) inflate.findViewById(R.id.dialog_display);
        this.f3991b = (SeekBar) inflate.findViewById(R.id.dialog_seekbar);
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: c */
    public SeekBarDialog mo2037a() {
        return this;
    }
}
