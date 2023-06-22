package com.sds.android.ttpod.component.lockscreen.p101a.p102a;

import java.util.ArrayList;

/* renamed from: com.sds.android.ttpod.component.lockscreen.a.a.a */
/* loaded from: classes.dex */
public abstract class Animator implements Cloneable {

    /* renamed from: a */
    ArrayList<InterfaceC1283a> f4625a = null;

    /* compiled from: Animator.java */
    /* renamed from: com.sds.android.ttpod.component.lockscreen.a.a.a$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1283a {
        /* renamed from: a */
        void mo5692a(Animator animator);

        /* renamed from: b */
        void mo5691b(Animator animator);

        /* renamed from: c */
        void mo5690c(Animator animator);

        /* renamed from: d */
        void mo5689d(Animator animator);
    }

    /* renamed from: a */
    public void mo6004a() {
    }

    /* renamed from: b */
    public void mo5992b() {
    }

    /* renamed from: a */
    public void m6088a(InterfaceC1283a interfaceC1283a) {
        if (this.f4625a == null) {
            this.f4625a = new ArrayList<>();
        }
        this.f4625a.add(interfaceC1283a);
    }

    @Override // 
    /* renamed from: c */
    public Animator clone() {
        try {
            Animator animator = (Animator) super.clone();
            if (this.f4625a != null) {
                ArrayList<InterfaceC1283a> arrayList = this.f4625a;
                animator.f4625a = new ArrayList<>();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    animator.f4625a.add(arrayList.get(i));
                }
            }
            return animator;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
