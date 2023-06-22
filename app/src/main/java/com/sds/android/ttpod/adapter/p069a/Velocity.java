package com.sds.android.ttpod.adapter.p069a;

import java.text.NumberFormat;

/* renamed from: com.sds.android.ttpod.adapter.a.f */
/* loaded from: classes.dex */
public class Velocity {

    /* renamed from: a */
    private double f3188a;

    /* renamed from: b */
    private long f3189b;

    /* renamed from: c */
    private long f3190c;

    /* renamed from: d */
    private double f3191d = 0.0d;

    public Velocity(long j) {
        this.f3189b = j;
    }

    /* renamed from: a */
    public String m7626a(long j, long j2) {
        this.f3190c = j2 - this.f3189b;
        this.f3188a = j;
        if (this.f3190c != 0) {
            this.f3191d = (((this.f3188a / 1024.0d) / 1024.0d) / this.f3190c) * 1000.0d;
        }
        return m7627a(this.f3191d, 2);
    }

    /* renamed from: a */
    public String m7628a() {
        return m7627a(this.f3191d, 2) + "MB/s";
    }

    public String toString() {
        return "transfer: " + this.f3188a + ", duaring: " + this.f3190c + ", speed: " + m7628a() + "MB/s";
    }

    /* renamed from: a */
    private String m7627a(double d, int i) {
        NumberFormat numberInstance = NumberFormat.getNumberInstance();
        numberInstance.setMaximumIntegerDigits(i);
        return numberInstance.format(d);
    }
}
