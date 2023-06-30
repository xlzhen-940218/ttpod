package com.sds.android.ttpod.framework.modules.skin.p130c.p131a;

import android.text.TextUtils;
import com.sds.android.ttpod.framework.p106a.CodeUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.c.a.d */
/* loaded from: classes.dex */
public class TskPackHandle extends PackHandle {

    /* renamed from: b */
    private String filePath;

    /* renamed from: c */
    private long lastModified;

    /* renamed from: e */
    private InputStream stream;

    /* renamed from: a */
    private String title = null;

    /* renamed from: d */
    private HashMap<String, int[]> skinResourceMaps = null;

    /* renamed from: f */
    private ArrayList f6520f = new ArrayList(10);

    public TskPackHandle() {
    }

    public TskPackHandle(String str) throws IOException {
        initTskPackHandleByFile(str);
    }

    public TskPackHandle(InputStream inputStream) throws IOException {
        initTskPackHandle(inputStream, true);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.p131a.PackHandle
    /* renamed from: a */
    public void initTskPackHandleByFile(String filePath) throws IOException {
        RandomAccessFileInputStream randomAccessFileInputStream;
        File file = new File(filePath);
        Exception e;
        long lastModified = file.lastModified();
        boolean z = (TextUtils.equals(this.filePath, filePath) && lastModified == this.lastModified) ? false : true;
        try {
            randomAccessFileInputStream = new RandomAccessFileInputStream(file, "r");
            try {
                initTskPackHandle(randomAccessFileInputStream, z);
                this.filePath = filePath;
                this.lastModified = lastModified;
            } catch (IOException e1) {
                e = e1;
                if (randomAccessFileInputStream != null) {
                    randomAccessFileInputStream.close();
                }
                //throw e;
            }
        } catch (IOException e2) {
            e = e2;
            randomAccessFileInputStream = null;
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.p131a.PackHandle
    /* renamed from: a */
    public boolean streamNotNull() {
        return this.stream != null;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.p131a.PackHandle, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.stream != null) {
            this.stream.close();
            this.stream = null;
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.p131a.PackHandle
    /* renamed from: b */
    public byte[] loadTskResource(String resName) throws IOException {
        if (resName == null) {
            throw new IOException("file name is null");
        }
        int[] iArr = this.skinResourceMaps.get(resName.toLowerCase(Locale.US));
        if (iArr != null) {
            this.stream.reset();
            this.stream.skip(iArr[0]);
            byte[] bArr = new byte[iArr[1]];
            this.stream.read(bArr);
            return bArr;
        }
        return null;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.p131a.PackHandle, java.lang.Iterable
    public Iterator<String> iterator() {
        return this.skinResourceMaps.keySet().iterator();
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.p131a.PackHandle
    /* renamed from: a */
    public void initTskPackHandle(InputStream inputStream, boolean loadStream) throws IOException {
        if (inputStream == null || !inputStream.markSupported()) {
            throw new IOException("INPUT STREAM NOT SUPPORTED");
        }
        inputStream.mark(inputStream.available());
        if (!loadStream && this.skinResourceMaps.size() > 0) {
            this.stream = inputStream;
            return;
        }
        byte[] bArr = new byte[16784];
        if (inputStream.read(bArr) > 4 && bArr[0] == 84 && bArr[1] == 80 && bArr[2] == 65 && bArr[3] == 75) {
            m3758a(bArr, 4);
            m3758a(bArr, 8);
            m3758a(bArr, 12);
            int m3758a = m3758a(bArr, 16);
            this.title = CodeUtils.m4763a(bArr, 24, m3758a(bArr, 20));
            int i = m3758a + 20;
            int[] iArr = new int[m3758a(bArr, i) >> 2];
            int i2 = i + 4;
            for (int i3 = 0; i3 < iArr.length; i3++) {
                iArr[i3] = m3758a(bArr, i2);
                i2 += 4;
            }
            this.skinResourceMaps = new HashMap<>();
            for (int i4 = 0; i4 < iArr.length - 2; i4 += 2) {
                int i5 = iArr[i4 + 2] - iArr[i4];
                int[] iArr2 = {iArr[i4 + 1], iArr[i4 + 3] - iArr[i4 + 1]};
                int m3758a2 = m3758a(bArr, i2);
                if (m3758a2 <= 256 && m3758a2 >= 0) {
                    String m4763a = CodeUtils.m4763a(bArr, i2 + 4, m3758a2);
                    i2 += i5;
                    if (m4763a != null) {
                        this.skinResourceMaps.put(m4763a.toLowerCase(Locale.US), iArr2);
                    }
                }
            }
            this.stream = inputStream;
            return;
        }
        throw new IOException("NOT TSK FORMATION");
    }

    /* renamed from: a */
    private int m3758a(byte[] bArr, int i) {
        return (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24);
    }
}
