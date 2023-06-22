package com.sds.android.ttpod.activities.user;

import com.sds.android.ttpod.R;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/* renamed from: com.sds.android.ttpod.activities.user.a */
/* loaded from: classes.dex */
public class BirthdayDate {

    /* renamed from: a */
    private final int f3093a;

    /* renamed from: b */
    private final int f3094b;

    /* renamed from: c */
    private final int f3095c;

    public BirthdayDate(int i, int i2, int i3) {
        this.f3093a = i;
        this.f3094b = i2;
        this.f3095c = i3;
    }

    public BirthdayDate(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(TimeUnit.SECONDS.toMillis(j));
        this.f3093a = calendar.get(1);
        this.f3094b = calendar.get(2);
        this.f3095c = calendar.get(5);
    }

    /* renamed from: a */
    public int m7727a() {
        return this.f3093a;
    }

    /* renamed from: b */
    public int m7726b() {
        return this.f3094b;
    }

    /* renamed from: c */
    public int m7725c() {
        return this.f3095c;
    }

    public String toString() {
        return String.format("%d-%d-%d", Integer.valueOf(this.f3093a), Integer.valueOf(this.f3094b + 1), Integer.valueOf(this.f3095c));
    }

    /* renamed from: d */
    public long m7724d() {
        return TimeUnit.MILLISECONDS.toSeconds(m7723e());
    }

    /* renamed from: e */
    public long m7723e() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(this.f3093a, this.f3094b, this.f3095c);
        return calendar.getTimeInMillis();
    }

    public boolean equals(Object obj) {
        return (obj instanceof BirthdayDate) && this.f3093a == ((BirthdayDate) obj).f3093a && this.f3094b == ((BirthdayDate) obj).f3094b && this.f3095c == ((BirthdayDate) obj).f3095c;
    }

    public int hashCode() {
        return (int) m7724d();
    }

    /* renamed from: f */
    public int m7722f() {
        switch (m7721g()) {
            case 0:
                return R.string.capricorn;
            case 1:
                return R.string.aquarius;
            case 2:
                return R.string.pisces;
            case 3:
                return R.string.aries;
            case 4:
                return R.string.taurus;
            case 5:
                return R.string.gemini;
            case 6:
                return R.string.cancer;
            case 7:
                return R.string.leo;
            case 8:
                return R.string.virgo;
            case 9:
                return R.string.libra;
            case 10:
                return R.string.scorpio;
            case 11:
                return R.string.sagittarius;
            default:
                return R.string.unknown;
        }
    }

    /* renamed from: g */
    public int m7721g() {
        switch (this.f3094b) {
            case 0:
                return this.f3095c >= 20 ? 1 : 0;
            case 1:
                return this.f3095c < 20 ? 1 : 2;
            case 2:
                if (this.f3095c < 21) {
                    return 2;
                }
                return 3;
            case 3:
                if (this.f3095c < 21) {
                    return 3;
                }
                return 4;
            case 4:
                if (this.f3095c < 21) {
                    return 4;
                }
                return 5;
            case 5:
                if (this.f3095c < 22) {
                    return 5;
                }
                return 6;
            case 6:
                if (this.f3095c < 23) {
                    return 6;
                }
                return 7;
            case 7:
                if (this.f3095c < 23) {
                    return 7;
                }
                return 8;
            case 8:
                if (this.f3095c < 23) {
                    return 8;
                }
                return 9;
            case 9:
                if (this.f3095c < 23) {
                    return 9;
                }
                return 10;
            case 10:
                if (this.f3095c < 22) {
                    return 10;
                }
                return 11;
            case 11:
                return this.f3095c < 22 ? 11 : 0;
            default:
                return 0;
        }
    }
}
