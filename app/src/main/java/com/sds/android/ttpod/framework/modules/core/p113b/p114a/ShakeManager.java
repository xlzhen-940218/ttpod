package com.sds.android.ttpod.framework.modules.core.p113b.p114a;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;

/* renamed from: com.sds.android.ttpod.framework.modules.core.b.a.a */
/* loaded from: classes.dex */
public class ShakeManager implements SensorEventListener {

    /* renamed from: g */
    private float[] f5905g;

    /* renamed from: o */
    private boolean f5913o;

    /* renamed from: s */
    private SensorManager f5917s;

    /* renamed from: t */
    private InterfaceC1857a f5918t;

    /* renamed from: u */
    private int f5919u;

    /* renamed from: v */
    private int f5920v;

    /* renamed from: w */
    private int f5921w;

    /* renamed from: a */
    private float f5899a = -12.0f;

    /* renamed from: b */
    private float f5900b = 12.0f;

    /* renamed from: c */
    private float f5901c = -12.0f;

    /* renamed from: d */
    private float f5902d = 12.0f;

    /* renamed from: e */
    private float f5903e = -12.0f;

    /* renamed from: f */
    private float f5904f = 12.0f;

    /* renamed from: h */
    private float f5906h = 0.0f;

    /* renamed from: i */
    private float f5907i = 0.0f;

    /* renamed from: j */
    private float f5908j = 0.0f;

    /* renamed from: k */
    private boolean f5909k = false;

    /* renamed from: l */
    private boolean f5910l = false;

    /* renamed from: m */
    private boolean f5911m = false;

    /* renamed from: n */
    private boolean f5912n = false;

    /* renamed from: p */
    private long f5914p = 0;

    /* renamed from: q */
    private long f5915q = 0;

    /* renamed from: r */
    private long f5916r = 0;

    /* renamed from: x */
    private Handler f5922x = new Handler() { // from class: com.sds.android.ttpod.framework.modules.core.b.a.a.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (ShakeManager.this.f5918t != null) {
                        if (ShakeManager.this.f5906h > 0.0f) {
                            ShakeManager.this.f5918t.mo4303a(1);
                        } else {
                            ShakeManager.this.f5918t.mo4303a(2);
                        }
                    }
                    ShakeManager.this.f5909k = false;
                    return;
                case 1:
                    if (ShakeManager.this.f5918t != null) {
                        if (ShakeManager.this.f5907i > 0.0f) {
                            ShakeManager.this.f5918t.mo4300b(1);
                        } else {
                            ShakeManager.this.f5918t.mo4300b(2);
                        }
                    }
                    ShakeManager.this.f5910l = false;
                    return;
                case 2:
                    if (ShakeManager.this.f5918t != null) {
                        if (ShakeManager.this.f5908j > 0.0f) {
                            ShakeManager.this.f5918t.mo4299c(1);
                        } else {
                            ShakeManager.this.f5918t.mo4299c(2);
                        }
                    }
                    ShakeManager.this.f5911m = false;
                    return;
                default:
                    return;
            }
        }
    };

    /* compiled from: ShakeManager.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.core.b.a.a$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1857a {
        /* renamed from: a */
        void mo4303a(int i);

        /* renamed from: b */
        void mo4300b(int i);

        /* renamed from: c */
        void mo4299c(int i);
    }

    public ShakeManager(Context context, boolean z, int i) {
        this.f5913o = false;
        this.f5917s = (SensorManager) context.getSystemService("sensor");
        if (-2 == i) {
            this.f5919u = 3;
            this.f5921w = 1000;
            this.f5913o = false;
        } else {
            this.f5919u = i;
            this.f5921w = 800;
            this.f5913o = z;
        }
        this.f5920v = 1;
    }

    /* renamed from: a */
    public void m4315a(int i, int i2) {
        if (i <= 0 && i >= -600 && i2 >= 0 && i2 <= 600) {
            m4316a((i - 30) / 10.0f, (i2 + 30) / 10.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    /* renamed from: a */
    private void m4316a(float f, float f2, float f3, float f4, float f5, float f6) {
        if (0.0f != f) {
            this.f5899a = f;
        }
        if (0.0f != f2) {
            this.f5900b = f2;
        }
        if (0.0f != f3) {
            this.f5901c = f3;
        }
        if (0.0f != f4) {
            this.f5902d = f4;
        }
        if (0.0f != f5) {
            this.f5903e = f5;
        }
        if (0.0f != f6) {
            this.f5904f = f6;
        }
    }

    /* renamed from: a */
    public void m4314a(InterfaceC1857a interfaceC1857a) {
        if (interfaceC1857a != null) {
            this.f5918t = interfaceC1857a;
            if (!this.f5912n) {
                this.f5917s.registerListener(this, this.f5917s.getDefaultSensor(1), this.f5919u);
                this.f5912n = true;
            }
        }
    }

    /* renamed from: a */
    public void m4319a() {
        if (this.f5912n) {
            this.f5917s.unregisterListener(this);
            this.f5912n = false;
        }
        this.f5918t = null;
    }

    /* renamed from: a */
    private float m4317a(float f, float f2) {
        return Math.abs(f) >= Math.abs(f2) ? f : f2;
    }

    /* renamed from: a */
    private void m4318a(float f) {
        if (f < this.f5899a || f > this.f5900b) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.f5913o) {
                if (this.f5909k) {
                    this.f5906h = m4317a(this.f5906h, f);
                } else if (currentTimeMillis - this.f5914p > this.f5921w) {
                    this.f5909k = true;
                    this.f5906h = f;
                    this.f5922x.sendEmptyMessageDelayed(0, 500L);
                }
            } else if (currentTimeMillis - this.f5914p > this.f5921w && this.f5918t != null) {
                this.f5918t.mo4303a(this.f5920v);
            }
            this.f5914p = currentTimeMillis;
        }
    }

    /* renamed from: b */
    private void m4311b(float f) {
        if (f < this.f5901c || f > this.f5902d) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.f5913o) {
                if (this.f5910l) {
                    this.f5907i = m4317a(this.f5907i, f);
                } else if (currentTimeMillis - this.f5915q > this.f5921w) {
                    this.f5910l = true;
                    this.f5907i = f;
                    this.f5922x.sendEmptyMessageDelayed(1, 500L);
                }
            } else if (currentTimeMillis - this.f5915q > this.f5921w && this.f5918t != null) {
                this.f5918t.mo4300b(this.f5920v);
            }
            this.f5915q = currentTimeMillis;
        }
    }

    /* renamed from: c */
    private void m4308c(float f) {
        if (f < this.f5903e || f > this.f5904f) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.f5913o) {
                if (this.f5911m) {
                    this.f5908j = m4317a(this.f5908j, f);
                } else if (currentTimeMillis - this.f5916r > this.f5921w) {
                    this.f5911m = true;
                    this.f5908j = f;
                    this.f5922x.sendEmptyMessageDelayed(2, 500L);
                }
            } else if (currentTimeMillis - this.f5916r > this.f5921w && this.f5918t != null) {
                this.f5918t.mo4299c(this.f5920v);
            }
            this.f5916r = currentTimeMillis;
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 1) {
            if (this.f5905g == null) {
                this.f5905g = new float[3];
                this.f5905g[0] = sensorEvent.values[0];
                this.f5905g[1] = sensorEvent.values[1];
                this.f5905g[2] = sensorEvent.values[2];
                return;
            }
            float[] fArr = sensorEvent.values;
            float f = fArr[0] - this.f5905g[0];
            float f2 = fArr[1] - this.f5905g[1];
            float f3 = fArr[2] - this.f5905g[2];
            this.f5905g[0] = sensorEvent.values[0];
            this.f5905g[1] = sensorEvent.values[1];
            this.f5905g[2] = sensorEvent.values[2];
            m4318a(f);
            m4311b(f2);
            m4308c(f3);
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
