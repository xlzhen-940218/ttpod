package com.sds.android.ttpod.framework.modules.skin.p130c.p131a;

import android.text.TextUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.c.a.e */
/* loaded from: classes.dex */
public class ZipPackHandle extends PackHandle {

    /* renamed from: a */
    private String f6521a;

    /* renamed from: b */
    private long f6522b;

    /* renamed from: c */
    private ZipFile f6523c;

    /* renamed from: d */
    private HashMap<String, ZipEntry> f6524d = new HashMap<>();

    public ZipPackHandle() {
    }

    public ZipPackHandle(String str) throws IOException {
        mo3755a(str);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.p131a.PackHandle, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.f6523c != null) {
            this.f6523c.close();
            this.f6523c = null;
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.p131a.PackHandle
    /* renamed from: a */
    public void mo3755a(String str) throws IOException {
        File file = new File(str);
        this.f6523c = new ZipFile(file);
        long lastModified = file.lastModified();
        if (this.f6524d.isEmpty() || !TextUtils.equals(this.f6521a, str) || lastModified != this.f6522b) {
            m3754b();
        }
        this.f6521a = str;
        this.f6522b = lastModified;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.p131a.PackHandle
    /* renamed from: a */
    public void mo3756a(InputStream inputStream, boolean z) throws IOException {
        throw new UnsupportedOperationException("zip pack cannot open in input stream");
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.p131a.PackHandle
    /* renamed from: a */
    public boolean mo3757a() {
        return this.f6523c != null;
    }

    /* renamed from: b */
    private void m3754b() {
        if (mo3757a()) {
            this.f6524d.clear();
            Enumeration<? extends ZipEntry> entries = this.f6523c.entries();
            while (entries.hasMoreElements()) {
                ZipEntry nextElement = entries.nextElement();
                if (!nextElement.isDirectory()) {
                    this.f6524d.put("/" + nextElement.getName(), nextElement);
                }
            }
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.p131a.PackHandle, java.lang.Iterable
    public Iterator<String> iterator() {
        return this.f6524d.keySet().iterator();
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.p131a.PackHandle
    /* renamed from: b */
    public byte[] mo3753b(String str) throws IOException {
        int size;
        byte[] bArr = null;
        ZipEntry m3752c = m3752c(str);
        if (m3752c != null && (size = (int) m3752c.getSize()) > 0) {
            bArr = new byte[size];
            InputStream inputStream = this.f6523c.getInputStream(m3752c);
            int i = 0;
            do {
                try {
                    int read = inputStream.read(bArr, i, bArr.length - i);
                    i += read;
                    if (read >= bArr.length) {
                        break;
                    }
                } finally {
                    inputStream.close();
                }
            } while (inputStream.available() > 0);
        }
        return bArr;
    }

    /* renamed from: c */
    private ZipEntry m3752c(String str) {
        return this.f6524d.get(str.toLowerCase(Locale.US));
    }
}
