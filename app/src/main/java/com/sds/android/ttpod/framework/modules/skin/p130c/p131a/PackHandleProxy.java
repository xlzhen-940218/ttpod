package com.sds.android.ttpod.framework.modules.skin.p130c.p131a;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.c.a.b */
/* loaded from: classes.dex */
public class PackHandleProxy extends PackHandle {

    /* renamed from: a */
    private PackHandle f6512a;

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.p131a.PackHandle
    /* renamed from: a */
    public void mo3755a(String str) throws IOException {
        if (!mo3757a()) {
            if (this.f6512a == null) {
                this.f6512a = new TskPackHandle();
            }
            try {
                this.f6512a.mo3755a(str);
            } catch (Exception e) {
                if (this.f6512a instanceof TskPackHandle) {
                    this.f6512a = new ZipPackHandle(str);
                } else {
                    this.f6512a = new TskPackHandle(str);
                }
            }
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.p131a.PackHandle
    /* renamed from: a */
    public void mo3756a(InputStream inputStream, boolean z) throws IOException {
        if (!mo3757a()) {
            if (this.f6512a == null || !(this.f6512a instanceof TskPackHandle)) {
                this.f6512a = new TskPackHandle(inputStream);
            } else {
                this.f6512a.mo3756a(inputStream, z);
            }
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.p131a.PackHandle, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.f6512a != null) {
            this.f6512a.close();
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.p131a.PackHandle
    /* renamed from: a */
    public boolean mo3757a() {
        return this.f6512a != null && this.f6512a.mo3757a();
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.p131a.PackHandle, java.lang.Iterable
    public Iterator<String> iterator() {
        return this.f6512a.iterator();
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.p131a.PackHandle
    /* renamed from: b */
    public byte[] mo3753b(String str) throws IOException {
        return this.f6512a.mo3753b(str);
    }
}
