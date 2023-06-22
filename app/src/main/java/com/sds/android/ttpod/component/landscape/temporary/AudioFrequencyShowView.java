package com.sds.android.ttpod.component.landscape.temporary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import androidx.core.internal.view.SupportMenu;
import android.util.AttributeSet;
import android.view.View;

/* loaded from: classes.dex */
public class AudioFrequencyShowView extends View {

    /* renamed from: a */
    private float f4606a;

    /* renamed from: b */
    private int f4607b;

    /* renamed from: c */
    private int f4608c;

    /* renamed from: d */
    private int[] f4609d;

    /* renamed from: e */
    private int f4610e;

    /* renamed from: f */
    private int f4611f;

    /* renamed from: g */
    private int f4612g;

    /* renamed from: h */
    private InterfaceC1278a f4613h;

    /* renamed from: i */
    private boolean f4614i;

    /* renamed from: j */
    private Handler f4615j;

    /* renamed from: com.sds.android.ttpod.component.landscape.temporary.AudioFrequencyShowView$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1278a {
        /* renamed from: a */
        int[] m6095a();
    }

    public AudioFrequencyShowView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AudioFrequencyShowView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f4610e = 512;
        this.f4612g = 32;
        this.f4615j = new Handler() { // from class: com.sds.android.ttpod.component.landscape.temporary.AudioFrequencyShowView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 12:
                        if (AudioFrequencyShowView.this.f4613h != null) {
                            AudioFrequencyShowView.this.f4609d = AudioFrequencyShowView.this.f4613h.m6095a();
                            AudioFrequencyShowView.this.invalidate();
                        }
                        if (AudioFrequencyShowView.this.f4614i) {
                            AudioFrequencyShowView.this.f4615j.sendEmptyMessageDelayed(12, 40L);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
    }

    public void setAudioFrequencyProvider(InterfaceC1278a interfaceC1278a) {
        this.f4613h = interfaceC1278a;
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f4615j.removeMessages(12);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f4607b = i;
        this.f4608c = i2;
        this.f4606a = this.f4608c / 100.0f;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int i = 0;
        super.draw(canvas);
        Paint paint = new Paint();
        if (this.f4609d != null) {
            paint.setColor(-16711936);
            this.f4610e = this.f4609d.length >= this.f4610e ? this.f4610e : this.f4609d.length;
            this.f4611f = this.f4611f < this.f4610e ? this.f4611f : this.f4610e - 1;
            this.f4612g = this.f4611f + this.f4612g <= this.f4610e ? this.f4612g : this.f4610e - this.f4611f;
            float f = this.f4607b / this.f4610e;
            float f2 = 0.0f;
            float f3 = this.f4608c;
            int i2 = 0;
            while (true) {
                int i3 = i;
                float f4 = f2;
                if (i2 < this.f4610e) {
                    f2 = f4 + f;
                    canvas.drawRect(f4, f3 - (this.f4609d[i2] * this.f4606a), f2, f3, paint);
                    if (i2 >= this.f4611f && i2 < this.f4611f + this.f4612g) {
                        i3 += this.f4609d[i2];
                    }
                    i = i3;
                    i2++;
                } else {
                    paint.setColor(SupportMenu.CATEGORY_MASK);
                    float f5 = f3 - ((i3 * this.f4606a) / this.f4612g);
                    canvas.drawLine(this.f4611f * f, f5, (this.f4611f + this.f4612g) * f, f5, paint);
                    return;
                }
            }
        }
    }
}
