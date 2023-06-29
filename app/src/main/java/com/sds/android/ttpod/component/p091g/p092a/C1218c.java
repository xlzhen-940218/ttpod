package com.sds.android.ttpod.component.p091g.p092a;


import com.sds.android.ttpod.framework.modules.skin.p129b.SMotion;
import com.sds.android.ttpod.framework.modules.skin.p130c.ValueParser;

/* compiled from: Event.java */
/* renamed from: com.sds.android.ttpod.component.g.a.c */
/* loaded from: classes.dex */
public class C1218c {

    /* renamed from: a */
    private String[] f4161a;

    /* renamed from: b */
    private Directives f4162b;

    /* renamed from: c */
    private EventCallback f4163c;

    /* renamed from: d */
    private int f4164d = 0;

    /* renamed from: e */
    private int f4165e = hashCode();

    /* renamed from: f */
    private int f4166f;

    /* renamed from: g */
    private int f4167g;

    /* renamed from: h */
    private int f4168h;

    /* renamed from: i */
    private int f4169i;

    /* renamed from: j */
    private EventHandler f4170j;

    /* renamed from: k */
    private int f4171k;

    /* renamed from: l */
    private int f4172l;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C1218c(SMotion sMotion, EventHandler eventHandler) {
        this.f4170j = eventHandler;
        this.f4161a = sMotion.m3796b();
        this.f4162b = new Directives(sMotion.m3795c());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public int m6569a() {
        return this.f4171k;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public int m6565b() {
        return this.f4172l;
    }

    /* renamed from: a */
    public void m6568a(int i) {
        this.f4164d = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:199:0x0449  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x00f0 A[SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void m6567a(int i, int i2) {
        int i3 = 0;
        int max;
        this.f4171k = i;
        this.f4172l = i2;
        if (this.f4163c != null) {
            if (this.f4164d == 1) {
                this.f4169i++;
            }
            this.f4170j.removeMessages(this.f4165e);
            if (this.f4164d == 2 && this.f4168h > 0) {
                this.f4170j.sendMessageDelayed(this.f4170j.obtainMessage(this.f4165e, this), this.f4168h);
                return;
            }
            this.f4168h = 0;
            if (!this.f4162b.m6584a()) {
                this.f4162b.m6579c();
                if (this.f4169i > 0) {
                    this.f4169i--;
                } else if (this.f4164d == 3) {
                    this.f4164d = 0;
                    return;
                }
            }
            this.f4164d = 0;
            AnimationCommand animationCommand = new AnimationCommand();
            int i4 = 0;
            Directives.C1217a m6580b = this.f4162b.m6580b();
            boolean z = false;
            while (true) {
                int i5 = i4;
                if (this.f4162b.m6584a()) {
                    this.f4162b.m6583a(m6580b);
                    switch (m6580b.m6572d()) {
                        case 0:
                            int i6 = 0;
                            if (z) {
                                this.f4162b.m6582a(m6580b, i5);
                                switch (m6580b.m6572d()) {
                                    case 65537 /* 65537 */:
                                        if (m6580b.m6578a()) {
                                            this.f4163c.mo6519a(this.f4161a, ValueParser.getSize(this.f4166f + m6580b.m6574b(), i), ValueParser.getSize((m6580b.m6578a() ? m6580b.m6574b() : 0) + this.f4167g, i2), animationCommand);
                                            i3 = 1;
                                            break;
                                        } else {
                                            this.f4163c.mo6518a(this.f4161a, animationCommand);
                                            i3 = 1;
                                            break;
                                        }
                                    case 65538:
                                        this.f4163c.mo6514b(this.f4161a, animationCommand);
                                        i3 = 1;
                                        break;
                                    case 65539:
                                        if (m6580b.m6578a()) {
                                            int m6574b = m6580b.m6574b() + this.f4166f;
                                            int m6574b2 = (m6580b.m6578a() ? m6580b.m6574b() : 0) + this.f4167g;
                                            i6 = m6580b.m6578a() ? m6580b.m6574b() : 0;
                                            this.f4163c.mo6520a(this.f4161a, ValueParser.getSize(m6574b, i), ValueParser.getSize(m6574b2, i2), i6, animationCommand);
                                            i3 = 1;
                                            break;
                                        }
                                        break;
                                    case 65540:
                                    default:
                                        i3 = 1;
                                        break;
                                    case 65541:
                                        if (animationCommand.m6586c() > 0) {
                                            this.f4163c.mo6513c(this.f4161a, animationCommand);
                                        }
                                        if (m6580b.m6578a()) {
                                            i6 = m6580b.m6574b();
                                            i3 = 1;
                                            break;
                                        } else {
                                            return;
                                        }
                                    case 65542:
                                        if (animationCommand.m6586c() > 0) {
                                            this.f4163c.mo6513c(this.f4161a, animationCommand);
                                        }
                                        if (m6580b.m6578a()) {
                                            i6 = m6580b.m6574b();
                                            this.f4168h = i6;
                                            if (i6 > 0) {
                                                i3 = 2;
                                                this.f4169i--;
                                                break;
                                            }
                                        } else {
                                            this.f4168h = 0;
                                            if (this.f4169i > 0) {
                                                this.f4169i--;
                                                i3 = 1;
                                                break;
                                            } else {
                                                this.f4164d = 2;
                                                return;
                                            }
                                        }
                                        break;
                                    case 65543:
                                        if (m6580b.m6578a()) {
                                            int m6574b3 = m6580b.m6574b();
                                            int m6574b4 = m6580b.m6578a() ? m6580b.m6574b() : 0;
                                            i6 = m6580b.m6578a() ? m6580b.m6574b() : 0;
                                            this.f4163c.mo6515b(this.f4161a, ValueParser.getSize(m6574b3, i), ValueParser.getSize(m6574b4, i2), i6, animationCommand);
                                            i3 = 1;
                                            break;
                                        }
                                        break;
                                    case 65544:
                                        this.f4163c.mo6516a(this.f4161a, true, animationCommand);
                                        i3 = 1;
                                        break;
                                    case 65545:
                                        this.f4163c.mo6516a(this.f4161a, false, animationCommand);
                                        i3 = 1;
                                        break;
                                }
                                max = Math.max(i6, animationCommand.m6587b());
                                animationCommand.m6585d();
                                if (max > 0) {
                                    z = false;
                                    break;
                                } else {
                                    this.f4164d = i3;
                                    this.f4170j.sendMessageDelayed(this.f4170j.obtainMessage(this.f4165e, this), max);
                                    return;
                                }
                            } else {
                                this.f4163c.mo6513c(this.f4161a, animationCommand);
                            }
                            i3 = 1;
                            max = Math.max(i6, animationCommand.m6587b());
                            animationCommand.m6585d();
                            if (max > 0) {
                            }
                        case 36864:
                            if (!m6580b.m6578a()) {
                                break;
                            } else {
                                animationCommand.m6589a(m6580b.m6574b(), m6580b.m6578a() ? m6580b.m6574b() : 0, m6580b.m6578a() ? m6580b.m6574b() : 0);
                                break;
                            }
                        case 36865:
                            if (!m6580b.m6578a()) {
                                break;
                            } else {
                                animationCommand.m6590a(m6580b.m6574b() / 100.0f, m6580b.m6578a() ? m6580b.m6574b() / 100.0f : 0.0f, m6580b.m6578a() ? m6580b.m6574b() : 0, m6580b.m6578a() ? m6580b.m6574b() : 0);
                                break;
                            }
                        case 36866:
                            if (!m6580b.m6578a()) {
                                break;
                            } else {
                                animationCommand.m6593a(m6580b.m6574b() / 100.0f, m6580b.m6578a() ? m6580b.m6574b() / 100.0f : 1.0f, m6580b.m6578a() ? m6580b.m6574b() / 100.0f : 0.0f, m6580b.m6578a() ? m6580b.m6574b() / 100.0f : 1.0f, m6580b.m6578a() ? m6580b.m6574b() / 100.0f : 0.5f, m6580b.m6578a() ? m6580b.m6574b() / 100.0f : 0.5f, m6580b.m6578a() ? m6580b.m6574b() : 0, m6580b.m6578a() ? m6580b.m6574b() : 0);
                                break;
                            }
                        case 36867:
                            if (!m6580b.m6578a()) {
                                break;
                            } else {
                                animationCommand.m6591a(m6580b.m6574b() / 100.0f, m6580b.m6578a() ? m6580b.m6574b() / 100.0f : 180.0f, m6580b.m6578a() ? m6580b.m6574b() / 100.0f : 0.5f, m6580b.m6578a() ? m6580b.m6574b() / 100.0f : 0.5f, m6580b.m6578a() ? m6580b.m6574b() : 0, m6580b.m6578a() ? m6580b.m6574b() : 0);
                                break;
                            }
                        case 36868:
                            if (!m6580b.m6578a()) {
                                break;
                            } else {
                                animationCommand.m6592a(m6580b.m6574b() / 100.0f, m6580b.m6578a() ? m6580b.m6574b() / 100.0f : 360.0f, m6580b.m6578a() ? m6580b.m6574b() / 100.0f : 0.5f, m6580b.m6578a() ? m6580b.m6574b() / 100.0f : 0.5f, m6580b.m6578a() ? m6580b.m6574b() / 100.0f : 0.5f, m6580b.m6578a() ? m6580b.m6574b() : 0, m6580b.m6578a() ? m6580b.m6574b() : 0);
                                break;
                            }
                        default:
                            if (!m6580b.m6573c()) {
                                break;
                            } else {
                                z = true;
                                i5 = m6580b.m6571e();
                                break;
                            }
                    }
                    i4 = i5;
                } else {
                    return;
                }
            }
        }
    }

    /* renamed from: a */
    public void m6566a(EventCallback eventCallback) {
        this.f4163c = eventCallback;
    }
}
