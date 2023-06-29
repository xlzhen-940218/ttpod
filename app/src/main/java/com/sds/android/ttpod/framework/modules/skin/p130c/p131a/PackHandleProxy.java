package com.sds.android.ttpod.framework.modules.skin.p130c.p131a;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.c.a.b */
/* loaded from: classes.dex */
public class PackHandleProxy extends PackHandle {

    /* renamed from: a */
    private PackHandle packHandle;

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.p131a.PackHandle
    /* renamed from: a */
    public void mo3755a(String str) throws IOException {
        if (!streamNotNull()) {
            if (this.packHandle == null) {
                this.packHandle = new TskPackHandle();
            }
            try {
                this.packHandle.mo3755a(str);
            } catch (Exception e) {
                if (this.packHandle instanceof TskPackHandle) {
                    this.packHandle = new ZipPackHandle(str);
                } else {
                    this.packHandle = new TskPackHandle(str);
                }
            }
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.p131a.PackHandle
    /* renamed from: a */
    public void mo3756a(InputStream inputStream, boolean z) throws IOException {
        if (!streamNotNull()) {
            if (this.packHandle == null || !(this.packHandle instanceof TskPackHandle)) {
                this.packHandle = new TskPackHandle(inputStream);
            } else {
                this.packHandle.mo3756a(inputStream, z);
            }
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.p131a.PackHandle, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.packHandle != null) {
            this.packHandle.close();
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.p131a.PackHandle
    /* renamed from: a */
    public boolean streamNotNull() {
        return this.packHandle != null && this.packHandle.streamNotNull();
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.p131a.PackHandle, java.lang.Iterable
    public Iterator<String> iterator() {
        return this.packHandle.iterator();
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.p131a.PackHandle
    /* renamed from: b */
    public byte[] loadTskResource(String resName) throws IOException {
        return this.packHandle.loadTskResource(resName);
    }
}
