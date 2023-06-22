package com.sds.android.ttpod.framework.modules.skin.p130c;

import android.graphics.Rect;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.c.h */
/* loaded from: classes.dex */
public class NinePatchChunk {

    /* renamed from: a */
    private final Rect f6535a = new Rect();

    /* renamed from: b */
    private int[] f6536b;

    /* renamed from: c */
    private int[] f6537c;

    /* renamed from: d */
    private int[] f6538d;

    /* renamed from: a */
    private static void m3722a(int[] iArr, ByteBuffer byteBuffer) {
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            iArr[i] = byteBuffer.getInt();
        }
    }

    /* renamed from: a */
    private static void m3724a(int i) {
        if (i == 0 || (i & 1) != 0) {
            throw new RuntimeException("invalid nine-patch: " + i);
        }
    }

    /* renamed from: a */
    public Rect m3725a() {
        return this.f6535a;
    }

    /* renamed from: a */
    public static NinePatchChunk m3723a(byte[] bArr) {
        ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.nativeOrder());
        if (order.get() == 0) {
            return null;
        }
        NinePatchChunk ninePatchChunk = new NinePatchChunk();
        ninePatchChunk.f6536b = new int[order.get()];
        ninePatchChunk.f6537c = new int[order.get()];
        ninePatchChunk.f6538d = new int[order.get()];
        m3724a(ninePatchChunk.f6536b.length);
        m3724a(ninePatchChunk.f6537c.length);
        order.getInt();
        order.getInt();
        ninePatchChunk.f6535a.left = order.getInt();
        ninePatchChunk.f6535a.right = order.getInt();
        ninePatchChunk.f6535a.top = order.getInt();
        ninePatchChunk.f6535a.bottom = order.getInt();
        order.getInt();
        m3722a(ninePatchChunk.f6536b, order);
        m3722a(ninePatchChunk.f6537c, order);
        m3722a(ninePatchChunk.f6538d, order);
        return ninePatchChunk;
    }
}
