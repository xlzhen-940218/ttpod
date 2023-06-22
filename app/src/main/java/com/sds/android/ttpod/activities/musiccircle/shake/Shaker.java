package com.sds.android.ttpod.activities.musiccircle.shake;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/* renamed from: com.sds.android.ttpod.activities.musiccircle.shake.c */
/* loaded from: classes.dex */
public class Shaker implements SensorEventListener {

    /* renamed from: a */
    private Sensor f2926a;

    /* renamed from: b */
    private SensorManager f2927b;

    /* renamed from: c */
    private float[] f2928c;

    /* renamed from: d */
    private InterfaceC0849a f2929d;

    /* renamed from: e */
    private long f2930e;

    /* renamed from: f */
    private int f2931f = 1;

    /* compiled from: Shaker.java */
    /* renamed from: com.sds.android.ttpod.activities.musiccircle.shake.c$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0849a {
        void onShake();
    }

    public Shaker(Context context, InterfaceC0849a interfaceC0849a) {
        this.f2929d = interfaceC0849a;
        this.f2927b = (SensorManager) context.getSystemService("sensor");
        this.f2926a = this.f2927b.getDefaultSensor(1);
    }

    /* renamed from: a */
    public void m7830a() {
        this.f2931f = 0;
        this.f2927b.registerListener(this, this.f2926a, 1);
    }

    /* renamed from: b */
    public void m7827b() {
        this.f2931f = 1;
        this.f2927b.unregisterListener(this);
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 1) {
            float[] fArr = sensorEvent.values;
            if (this.f2928c == null) {
                this.f2928c = new float[3];
                m7828a(fArr);
                return;
            }
            float abs = Math.abs(fArr[0] - this.f2928c[0]);
            float abs2 = Math.abs(fArr[1] - this.f2928c[1]);
            float abs3 = Math.abs(fArr[2] - this.f2928c[2]);
            m7828a(fArr);
            if (this.f2931f == 0 && System.currentTimeMillis() - this.f2930e > 500 && m7829a(abs, abs2, abs3) && this.f2929d != null) {
                this.f2930e = System.currentTimeMillis();
                this.f2929d.onShake();
            }
        }
    }

    /* renamed from: a */
    private void m7828a(float[] fArr) {
        if (this.f2928c != null) {
            this.f2928c[0] = fArr[0];
            this.f2928c[1] = fArr[1];
            this.f2928c[2] = fArr[2];
        }
    }

    /* renamed from: a */
    private boolean m7829a(float f, float f2, float f3) {
        return f > 9.0f || f2 > 9.0f || f3 > 9.0f;
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
