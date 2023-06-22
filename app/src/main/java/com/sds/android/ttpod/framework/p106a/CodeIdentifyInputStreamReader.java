package com.sds.android.ttpod.framework.p106a;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;

/* renamed from: com.sds.android.ttpod.framework.a.c */
/* loaded from: classes.dex */
public class CodeIdentifyInputStreamReader extends Reader {

    /* renamed from: b */
    private InputStream f5627b;

    /* renamed from: c */
    private CharsetDecoder f5628c;

    /* renamed from: a */
    private final ByteBuffer f5626a = ByteBuffer.allocate(8192);

    /* renamed from: d */
    private boolean f5629d = false;

    public CodeIdentifyInputStreamReader(InputStream inputStream) {
        int i;
        this.f5626a.limit(this.f5626a.capacity());
        try {
            i = inputStream.read(this.f5626a.array(), this.f5626a.arrayOffset(), this.f5626a.capacity());
        } catch (Exception e) {
            e.printStackTrace();
            i = 0;
        }
        if (i > 0) {
            String m4765a = m4765a(this.f5626a);
            this.f5627b = inputStream;
            try {
                m4766a(inputStream, m4765a);
            } catch (UnsupportedEncodingException e2) {
                this.f5628c = Charset.defaultCharset().newDecoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
            }
            this.f5626a.limit(i);
        }
    }

    /* renamed from: a */
    private void m4766a(InputStream inputStream, String str) throws UnsupportedEncodingException {
        if (str == null) {
            throw new NullPointerException("charsetName == null");
        }
        this.f5627b = inputStream;
        try {
            this.f5628c = Charset.forName(str).newDecoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
        } catch (IllegalArgumentException e) {
            throw ((UnsupportedEncodingException) new UnsupportedEncodingException(str).initCause(e));
        }
    }

    /* renamed from: a */
    private String m4765a(ByteBuffer byteBuffer) {
        byteBuffer.mark();
        if (byteBuffer.get() == -1 && byteBuffer.get() == -2) {
            return "UTF-16LE";
        }
        byteBuffer.reset();
        if (byteBuffer.get() == -2 && byteBuffer.get() == -1) {
            return "UTF-16BE";
        }
        byteBuffer.reset();
        if (byteBuffer.get() == -17 && byteBuffer.get() == -69 && byteBuffer.get() == -65) {
            return "UTF-8";
        }
        byteBuffer.reset();
        if (CodeUtils.m4764a(byteBuffer.array())) {
            return "UTF-8";
        }
        return "GBK";
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0021, code lost:
        if (r3.f5627b.available() > 0) goto L17;
     */
    @Override // java.io.Reader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean ready() throws IOException {
        boolean z = false;
        synchronized (this.lock) {
            if (this.f5627b == null) {
                throw new IOException("InputStreamReader is closed");
            }
            if (!this.f5626a.hasRemaining()) {
            }
            z = true;
        }
        return z;
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        CoderResult coderResult;
        int r0 = 0;
        synchronized (this.lock) {
            if (!m4768a()) {
                throw new IOException("CodeIdentifyInputStreamReader is closed");
            }
            m4767a(cArr.length, i, i2);
            if (i2 != 0) {
                CharBuffer wrap = CharBuffer.wrap(cArr, i, i2);
                CoderResult coderResult2 = CoderResult.UNDERFLOW;
                r0= this.f5626a.hasRemaining() ? 0 : 1;
                while (true) {
                    if (!wrap.hasRemaining()) {
                        coderResult = coderResult2;
                        break;
                    }
                    if (r0 != 0) {
                        try {
                            if (this.f5627b.available() == 0 && wrap.position() > i) {
                                coderResult = coderResult2;
                                break;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        int read = this.f5627b.read(this.f5626a.array(), this.f5626a.arrayOffset() + this.f5626a.limit(), this.f5626a.capacity() - this.f5626a.limit());
                        if (read == -1) {
                            this.f5629d = true;
                            coderResult = coderResult2;
                            break;
                        } else if (read == 0) {
                            coderResult = coderResult2;
                            break;
                        } else {
                            this.f5626a.limit(read + this.f5626a.limit());
                        }
                    }
                    coderResult = this.f5628c.decode(this.f5626a, wrap, false);
                    if (!coderResult.isUnderflow()) {
                        break;
                    }
                    if (this.f5626a.limit() == this.f5626a.capacity()) {
                        this.f5626a.compact();
                        this.f5626a.limit(this.f5626a.position());
                        this.f5626a.position(0);
                    }
                    coderResult2 = coderResult;
                    r0 = 1;
                }
                if (coderResult == CoderResult.UNDERFLOW && this.f5629d) {
                    coderResult = this.f5628c.decode(this.f5626a, wrap, true);
                    this.f5628c.flush(wrap);
                    this.f5628c.reset();
                }
                if (coderResult.isMalformed() || coderResult.isUnmappable()) {
                    coderResult.throwException();
                }
                r0 = wrap.position() - i == 0 ? -1 : wrap.position() - i;
            }
            return r0;
        }
    }

    /* renamed from: a */
    private boolean m4768a() {
        return this.f5627b != null;
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this.lock) {
            if (this.f5628c != null) {
                this.f5628c.reset();
            }
            this.f5628c = null;
            if (this.f5627b != null) {
                this.f5627b.close();
                this.f5627b = null;
            }
        }
    }

    /* renamed from: a */
    private void m4767a(int i, int i2, int i3) {
        if ((i2 | i3) < 0 || i2 > i || i - i2 < i3) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
