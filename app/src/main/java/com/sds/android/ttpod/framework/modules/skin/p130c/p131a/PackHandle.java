package com.sds.android.ttpod.framework.modules.skin.p130c.p131a;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.c.a.a */
/* loaded from: classes.dex */
public abstract class PackHandle implements Closeable, Iterable<String> {
    /* renamed from: a */
    public abstract void mo3756a(InputStream inputStream, boolean z) throws IOException;

    /* renamed from: a */
    public abstract void mo3755a(String str) throws IOException;

    /* renamed from: a */
    public abstract boolean mo3757a();

    /* renamed from: b */
    public abstract byte[] mo3753b(String str) throws IOException;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close() throws IOException;

    @Override // java.lang.Iterable
    public abstract Iterator<String> iterator();
}
