package com.sds.android.ttpod.framework.modules.skin.p130c.p131a;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.c.a.c */
/* loaded from: classes.dex */
public class RandomAccessFileInputStream extends InputStream implements Closeable {

    /* renamed from: a */
    private RandomAccessFile f6513a;

    /* renamed from: b */
    private long f6514b;

    public RandomAccessFileInputStream(File file, String str) throws FileNotFoundException {
        this.f6514b = 0L;
        this.f6513a = new RandomAccessFile(file, str);
    }

    public RandomAccessFileInputStream(String str, String str2) throws FileNotFoundException {
        this(new File(str), str2);
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f6513a.close();
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        return this.f6513a.skipBytes((int) j);
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        return this.f6513a.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return this.f6513a.read(bArr);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.f6513a.read(bArr, i, i2);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return (int) (this.f6513a.length() - this.f6513a.getFilePointer());
    }

    @Override // java.io.InputStream
    public synchronized void mark(int i) {
        try {
            this.f6514b = this.f6513a.getFilePointer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        this.f6513a.seek(this.f6514b);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return true;
    }
}
