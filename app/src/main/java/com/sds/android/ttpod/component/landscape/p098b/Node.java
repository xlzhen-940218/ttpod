package com.sds.android.ttpod.component.landscape.p098b;

import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.sds.android.ttpod.component.landscape.b.f */
/* loaded from: classes.dex */
public class Node {

    /* renamed from: e */
    protected ArrayList<Node> f4482e = new ArrayList<>(5);

    /* renamed from: f */
    protected int f4483f;

    /* renamed from: a */
    public void m6276a(Node node, int i) {
        int size = this.f4482e.size();
        if (size == 0 || this.f4482e.get(size - 1).f4483f <= i) {
            this.f4482e.add(node);
        } else {
            int i2 = 0;
            Iterator<Node> it = this.f4482e.iterator();
            while (true) {
                int i3 = i2;
                if (!it.hasNext()) {
                    break;
                } else if (it.next().f4483f > i) {
                    this.f4482e.add(i3, node);
                    break;
                } else {
                    i2 = i3 + 1;
                }
            }
        }
        node.f4483f = i;
    }

    /* renamed from: a */
    public void mo6188a() {
        if (this.f4482e != null) {
            Iterator<Node> it = this.f4482e.iterator();
            while (it.hasNext()) {
                it.next().mo6188a();
            }
        }
    }

    /* renamed from: j */
    public void mo6258j() {
    }
}
